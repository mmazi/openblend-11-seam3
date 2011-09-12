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

    public void countMembers(@Observes @Every(nth = 10, value = Interval.SECOND) Trigger t) {
        log.info("We have {} members.", em.createQuery("select count(m) from Member m").getSingleResult());
    }

    public void daily(@Observes @Scheduled("0 0 15 ? * * *") Trigger trigger) {
        log.info("It is time.");
    }
}

/*
11:02:26,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-3) Hello World 3s!
11:02:29,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-4) Hello World 3s!
11:02:32,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-1) Hello World 3s!
11:02:35,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-2) Hello World 3s!
11:02:38,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-3) Hello World 3s!
11:02:41,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-4) Hello World 3s!
11:02:44,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-1) Hello World 3s!
11:02:47,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-2) Hello World 3s!
11:02:50,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-3) Hello World 3s!
11:02:53,723 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-4) Hello World 3s!
11:02:56,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-1) Hello World 3s!
11:02:59,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-2) Hello World 3s!
11:03:00,001 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-3) It is time.
11:03:02,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-4) Hello World 3s!
11:03:05,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-1) Hello World 3s!
11:03:08,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-2) Hello World 3s!
11:03:11,722 INFO  [com.profpay.CronExample] (SimpleThreadPoolWorker-3) Hello World 3s!
*/
