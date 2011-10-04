package com.parsek.test.seam;

import com.parsek.test.Member;
import org.jboss.seam.mail.api.MailMessage;
import org.jboss.seam.mail.templating.freemarker.FreeMarkerTemplate;
import org.jboss.seam.solder.resourceLoader.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.Serializable;

public class MailSender implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(MailSender.class);

    @Inject @Resource("mail/welcome.ftl") private InputStream templateStream;
    @Inject private MailMessage mailMessage;

    public void sayWelcome(Member member) {
        try {
            mailMessage
                    .from("no-reply@example.com", "Seam 3 Example App")
                    .to(member.getEmail())
                    .subject("Welcome!")
                    .bodyHtml(new FreeMarkerTemplate(templateStream))
                    .put("member", member)
                    .send();
        } catch (Exception e) {
            log.error("Error sending email: {}", e.toString());
        }
    }
}
