/**
 * 
 */
package com.github.rubenqba.cron;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ruben.bressler
 *
 */
public class HelloJob implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Date now = new Date();
        System.out.println(arg0.getJobDetail().getKey() + ": " + now);
    }
}
