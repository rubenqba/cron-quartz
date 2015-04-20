/**
 * 
 */
package com.github.rubenqba.cron;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.concurrent.TimeUnit;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

/**
 * @author ruben.bressler
 *
 */
public class CronApplication {

    /**
     * @param args
     * @throws SchedulerException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws SchedulerException,
            InterruptedException {

        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        // definir el trabajo y atarlo a la clase ejecutora HelloJob
        JobDetail job = newJob(HelloJob.class).withIdentity("myJob", "group1")
                .build();

        // lanzar el trabajo ahora mismo y cada 5 segundos
        Trigger trigger = newTrigger().withIdentity("myTrigger", "group1")
                .withSchedule(cronSchedule("0/5 * * * * ?")).build();

        // decir a Quartz que planifique el trabajo usando el trigger
        sched.scheduleJob(job, trigger);

        // dejarlo que ejecute un rato
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));

        // terminar scheduler
        sched.shutdown();
    }

}
