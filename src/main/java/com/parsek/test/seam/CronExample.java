package com.parsek.test.seam;

import org.jboss.seam.cron.api.scheduling.Every;
import org.jboss.seam.cron.api.scheduling.Interval;
import org.jboss.seam.cron.api.scheduling.Scheduled;
import org.jboss.seam.cron.api.scheduling.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CronExample {
    private static final Logger log = LoggerFactory.getLogger(CronExample.class);

    @PersistenceContext private EntityManager em;

    public void countMembers(@Observes @Every(nth = 3, value = Interval.SECOND) Trigger t) {
        log.info("We have {} members.", em.createQuery("select count(m) from Member m").getSingleResult());
    }

    public void reminder(@Observes @Scheduled("0 0 15 15 9 ? *") Trigger trigger) {
        log.info("IT'S TIME FOR A REFRESHMENT!");
    }
}

