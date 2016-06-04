package com.londonhydro.cloud2cis.job;

import static com.londonhydro.utils.Constants.JOB_GROUP_ONE_TIME;
import static com.londonhydro.utils.Constants.JOB_GROUP_REPETITIVE;
import static com.londonhydro.utils.Constants.PROPERTY_JOBs_ONE_TIME_DELAY;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;

import com.google.inject.Inject;
import com.google.inject.name.Named;


public class JobExecutor {

	@Inject @Named(PROPERTY_JOBs_ONE_TIME_DELAY)
	private int oneTimeJobsDelay;
	
	private Scheduler scheduler;
	private Map<String, JobKey> jobKeys;
	
	private static final Log logger = LogFactory.getLog(JobExecutor.class);
	
	@Inject
	public JobExecutor(Scheduler scheduler)
	{
		super();
		this.scheduler = scheduler;
		this.jobKeys = retrieveScheduledJobs(scheduler);
	}
	
	public void triggerRepetitiveJob(String jobName) throws SchedulerException
	{
		JobKey key = jobKeys.get(jobName.toLowerCase());
		if (key == null)
			throw new IllegalArgumentException("The job's name provided is not registered in the application: " + jobName);
		
		scheduler.triggerJob(key);
	}
	
	public void triggerOneTimeJob(Class<? extends Job> jobClass, String jobName) throws SchedulerException
	{
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP_ONE_TIME).build();
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.startAt(DateBuilder.futureDate(oneTimeJobsDelay, IntervalUnit.MILLISECOND))
				.build();
		
		scheduler.scheduleJob(jobDetail, trigger);
		
	}
	
    public void triggerOneTimeJob(Class<? extends Job> jobClass, Map<String, Object> jobData, String jobName) throws SchedulerException
    {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP_ONE_TIME).build();
        jobDetail.getJobDataMap().putAll(jobData);
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(oneTimeJobsDelay, IntervalUnit.MILLISECOND))
                .build();
        
        scheduler.scheduleJob(jobDetail, trigger);
        
    }
	
	private Map<String, JobKey> retrieveScheduledJobs(Scheduler scheduler)
	{
		
		Set<JobKey> jobKeys = null;
		try
		{
			jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(JOB_GROUP_REPETITIVE));
			System.err.println("jobKeys.........."+jobKeys.toString());
		} catch (SchedulerException e)
		{
			logger.error("QUARTZ: Error getting job keys from Scheduler.", e);
			throw new RuntimeException(e);
		}
		
		Map<String, JobKey> jobKeysMap = null;
		if (jobKeys == null)
		{
			jobKeysMap = Collections.emptyMap();
			logger.debug("QUARTZ: No job's key was found.");
		}
		else
		{
			jobKeysMap = new HashMap<String, JobKey>(jobKeys.size());
			for (JobKey key : jobKeys)
			{
				jobKeysMap.put(key.getName().toLowerCase(), key);
				logger.debug(String.format("QUARTZ: adding job for manual execution: {}", key.getName()));
			}
		}
		return jobKeysMap;
	}
	
}
