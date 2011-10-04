package com.parsek.test.seam;

import org.jboss.seam.international.status.MessageFactory;
import org.jboss.seam.international.status.builder.BundleKey;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * User: Matic<br/>
 * Date: 3.7.11<br/>
 */
public class Messages implements Serializable {
    @Inject org.jboss.seam.international.status.Messages seamMsgs;
    @Inject private MessageFactory msgFactory;

    public void addInfo(String key, Object... params) {
        seamMsgs.info(createBundleKey(key), params);
    }

    public FacesMessage createError(String msgKey) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msgFactory.error(createBundleKey(msgKey)).build().getText(), null);
    }

    private BundleKey createBundleKey(String key) {
        return new BundleKey("messages", key);
    }
}
