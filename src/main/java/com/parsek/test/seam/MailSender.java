package com.parsek.test.seam;

import com.parsek.test.model.Member;
import org.jboss.seam.mail.api.MailMessage;
import org.jboss.seam.mail.templating.freemarker.FreeMarkerTemplate;
import org.jboss.seam.solder.resourceLoader.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.io.Serializable;

public class MailSender implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(MailSender.class);

    @PersistenceContext private EntityManager entityManager;
    @Inject @Resource("mail/welcome.ftl") private InputStream templateStream;
    @Inject private MailMessage mailMessage;
//    @javax.annotation.Resource(mappedName = "java:/Mail") private Session session;

    public void sayWelcome(Member member) {
        mailMessage
                .from("no-reply@example.com", "Seam 3 Example App")
                .to(member.getEmail())
                .subject("Welcome!")
                .bodyHtml(new FreeMarkerTemplate(templateStream))
                .put("member", member)
                .send();
    }
}
