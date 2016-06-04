package com.londonhydro.utils;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.google.inject.Inject;

/**
 * Utility module created to include content related to Quartz functionality.
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class QuartzUtils 
{
	
    private static final Log logger = LogFactory.getLog(QuartzUtils.class);

	@Inject
	private Scheduler scheduler;
	
	public void rescheduleJob(String jobName, String jobGroup, String triggerName) throws SchedulerException 
	{
		
		logger.info(String.format("Rescheduling %s - Group: %s - Trigger Name: %s", jobName, jobGroup, triggerName));
		
		// Retrieve the trigger
		Trigger oldTrigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, jobGroup));
		
		// Obtain a builder that would produce the trigger
		
		TriggerBuilder<? extends Trigger> tb = oldTrigger.getTriggerBuilder();
		
		// update the schedule associated with the builder, and build the new trigger
		// (other builder methods could be called, to change the trigger in any desired way)
		
		@SuppressWarnings("static-access")
        Trigger newTrigger = tb.newTrigger() 
			    .withIdentity(oldTrigger.getKey().getName(), oldTrigger.getKey().getGroup())
			    .withSchedule(simpleSchedule()
			    		//.withIntervalInSeconds(10)
			    		.withIntervalInMinutes(5)
					    .repeatForever())
			    .startAt(futureDate(3, IntervalUnit.MINUTE)) // Use DateBuilder to create a date in the future
			    .forJob(jobName, jobGroup) // identify job with name, group strings
			    .build();
		
		
		scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
		
		logger.info(String.format("%s has been re-scheduled - The trigger will be fired next at: %s", jobName, newTrigger.getStartTime()));
		
	}
	
	@SuppressWarnings("static-access")
    public void rescheduleJobWithCron(String jobName, String jobGroup, String triggerName, String cronExpression) throws SchedulerException
	{
                
		logger.info(String.format("Rescheduling %s - Group: %s - Trigger Name: %s - Cron Expression: %s", jobName, jobGroup, triggerName, cronExpression));
		
		// Retrieve the trigger
		Trigger oldTrigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, jobGroup));
		
		// Obtain a builder that would produce the trigger
		
		TriggerBuilder<? extends Trigger> tb = oldTrigger.getTriggerBuilder();
		
		// update the schedule associated with the builder, and build the new trigger
		// (other builder methods could be called, to change the trigger in any desired way)
		
		Trigger newTrigger = null;
		
		newTrigger = tb.newTrigger() 
			    .withIdentity(oldTrigger.getKey().getName(), oldTrigger.getKey().getGroup())
			    .withSchedule(cronSchedule(cronExpression)
			    			.inTimeZone(TimeZone.getDefault()))
			    .startAt(futureDate(3, IntervalUnit.MINUTE)) // Use DateBuilder to create a date in the future
			    .forJob(jobName, jobGroup) // identify job with name, group strings
			    .build();
		
		scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
		
		logger.info(String.format("%s has been re-scheduled - The trigger will be fired next at: %s", jobName, newTrigger.getStartTime()));
        
	}
	
	public boolean isJobRunning(String jobName, String groupoName) throws SchedulerException
	{
	    List<JobExecutionContext> currentJobs = scheduler.getCurrentlyExecutingJobs();
	    
	    for (JobExecutionContext jobCtx: currentJobs){
	        String jn = jobCtx.getJobDetail().getKey().getName();
	        String gn = jobCtx.getJobDetail().getKey().getGroup();
	        if (jn.equalsIgnoreCase(jobName) && gn.equalsIgnoreCase(groupoName)) {
	            logger.warn("the job is already running - do nothing");
	            return true;
	        }
	    }
	    
	    return false;
	    
	}
	
	
}
