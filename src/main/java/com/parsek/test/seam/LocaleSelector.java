package com.parsek.test.seam;

import org.jboss.seam.international.Alter;
import org.jboss.seam.solder.core.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Locale;

/**
 * @author Matija Mazi <br/>
 * @created 12.9.11 15:49
 */
@ApplicationScoped @Named
public class LocaleSelector {
    @Inject @Alter @Client private Event<Locale> localeEvent;
    @Inject private Messages messages;

    public void setUserLocale(String lang) {
        Locale newUserLocale = new Locale(lang);
        messages.addInfo("user.locale.changed", newUserLocale);
        localeEvent.fire(newUserLocale);
    }
}
