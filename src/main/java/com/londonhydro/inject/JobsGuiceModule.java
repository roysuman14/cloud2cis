package com.londonhydro.inject;

import java.util.Properties;

import org.nnsoft.guice.guartz.QuartzModule;
import org.quartz.Job;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.londonhydro.cloud2cis.job.GetMoveInJob;
import com.londonhydro.cloud2cis.job.GetMoveOutJob;
import com.londonhydro.cloud2cis.job.GetMoveTransferJob;
import com.londonhydro.cloud2cis.job.GetServerQueueJob;
import com.londonhydro.cloud2cis.job.JobExecutor;
import com.londonhydro.cloud2cis.job.PushMoveInToSAP;
import com.londonhydro.cloud2cis.job.PushMoveOutToSAP;
import com.londonhydro.cloud2cis.job.PushMoveTransferToSAP;
import com.londonhydro.cloud2cis.job.PushServerQueueToSAP;
import com.londonhydro.utils.Constants;
import com.londonhydro.utils.QuartzUtils;

/**
 * Module in charge of the dependency injection related to the automate tasks
 * made by quartz.
 * 
 * @author Daniel I. Khan Ramiro (dikhan@affsys.com)
 */
public class JobsGuiceModule extends Cloud2CISPrivateModule {

	@Override
	protected void configure() {
		Properties properties = getProperties();
		// **** CHECK install(new TheModule(properties));
		Names.bindProperties(binder(), properties);

		binder().bind(GetServerQueueJob.class);
		binder().bind(PushServerQueueToSAP.class);

		binder().bind(GetMoveInJob.class);
		binder().bind(PushMoveInToSAP.class);

		binder().bind(GetMoveOutJob.class);
		binder().bind(PushMoveOutToSAP.class);

		binder().bind(GetMoveTransferJob.class);
		binder().bind(PushMoveTransferToSAP.class);

		// Expose classes
		expose(GetServerQueueJob.class);
		expose(GetMoveInJob.class);
		expose(GetMoveOutJob.class);
		expose(GetMoveTransferJob.class);

		System.err.println("JobsGuiceModule::configure" + properties);

	}

	public static class TheModule extends QuartzModule {

		private Properties properties;

		public TheModule(Properties properties) {
			this.properties = properties;
		}

		protected void schedule() {
			// Temporarily: Uncomment the below line if you'd like the scheduler
			// to be started manually
			// configureScheduler().withManualStart();

			binder().bind(JobExecutor.class).in(Singleton.class);
			binder().bind(QuartzUtils.class).in(Singleton.class);

			scheduleJobWithCronProperty(GetServerQueueJob.class,
					Constants.JOB_SERVER_QUEUE, Constants.TRIGGER_SERVER_QUEUE,
					Constants.PROPERTY_JOBS_SERVER_QUEUE);

			scheduleJobWithCronProperty(GetMoveInJob.class,
					Constants.JOB_MOVE_IN, Constants.TRIGGER_MOVE_IN,
					Constants.PROPERTY_JOBS_MOVEIN_QUEUE);

			scheduleJobWithCronProperty(GetMoveOutJob.class,
					Constants.JOB_MOVE_OUT, Constants.TRIGGER_MOVE_OUT,
					Constants.PROPERTY_JOBS_MOVE_OUT_QUEUE);

			System.err.println("JobsGuiceModule::schedule");

		}

		protected void scheduleJobWithCronProperty(
				Class<? extends Job> jobClass, String jobName,
				String triggerName, String cronProperty) {
			String jobCronExpression = properties.getProperty(cronProperty);
			scheduleJobWithCron(jobClass, jobName, triggerName,
					jobCronExpression);
		}

		protected void scheduleJobWithCron(Class<? extends Job> jobClass,
				String jobName, String triggerName, String jobCronExpression) {
			scheduleJob(jobClass).withJobName(jobName)
					.withJobGroup(Constants.JOB_GROUP_REPETITIVE)
					.withTriggerName(triggerName)
					.withTriggerGroup(Constants.JOB_GROUP_REPETITIVE)
					.withCronExpression(jobCronExpression);
		}
	}
}
