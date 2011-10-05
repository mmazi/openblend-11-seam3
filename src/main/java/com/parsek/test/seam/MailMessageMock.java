package com.parsek.test.seam;

import org.jboss.seam.mail.core.EmailMessage;
import org.jboss.seam.mail.core.MailMessageImpl;
import org.jboss.seam.mail.core.SendFailedException;
import org.jboss.seam.mail.util.MailUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.mail.Session;

/**
 * @author Matija Mazi <br/>
 * @created 5.10.11 10:47
 */
@Alternative
public class MailMessageMock extends MailMessageImpl {
    private static final Logger log = LoggerFactory.getLogger(MailMessageMock.class);

    @Override
    public EmailMessage send(Session session) throws SendFailedException {
        this.mergeTemplates();
        log.info("Pretvarjam se, da pošiljam elektronsko sporočilo: {}", getEmailMessage().getHtmlBody());
        return getEmailMessage();
    }
}
