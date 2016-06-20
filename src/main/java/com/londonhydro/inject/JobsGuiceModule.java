package com.londonhydro.inject;

import java.util.Properties;

import org.nnsoft.guice.guartz.QuartzModule;
import org.quartz.Job;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.londonhydro.cloud2cis.job.GetMoveInJob;
import com.londonhydro.cloud2cis.job.GetMoveOutJob;
import com.londonhydro.cloud2cis.job.GetMoveTransferJob;
import com.londonhydro.cloud2cis.job.GetPaymentArrangementsJob;
import com.londonhydro.cloud2cis.job.GetPaymentNotificationJob;
import com.londonhydro.cloud2cis.job.GetServerQueueJob;
import com.londonhydro.cloud2cis.job.JobExecutor;
import com.londonhydro.cloud2cis.job.PushMoveInToSAP;
import com.londonhydro.cloud2cis.job.PushMoveOutToSAP;
import com.londonhydro.cloud2cis.job.PushMoveTransferToSAP;
import com.londonhydro.cloud2cis.job.PushPaymentArrangementsToSAP;
import com.londonhydro.cloud2cis.job.PushPaymentNotificationsToSAP;
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
		// **** CHECK
		install(new ScheduleModule(properties));
		Names.bindProperties(binder(), properties);

		binder().bind(GetServerQueueJob.class);
		binder().bind(PushServerQueueToSAP.class);

		binder().bind(GetMoveInJob.class);
		binder().bind(PushMoveInToSAP.class);

		binder().bind(GetMoveOutJob.class);
		binder().bind(PushMoveOutToSAP.class);

		binder().bind(GetMoveTransferJob.class);
		binder().bind(PushMoveTransferToSAP.class);

		binder().bind(GetPaymentArrangementsJob.class);
		binder().bind(PushPaymentArrangementsToSAP.class);

		binder().bind(GetPaymentNotificationJob.class);
		binder().bind(PushPaymentNotificationsToSAP.class);

		// Expose classes
		expose(GetServerQueueJob.class);
		expose(GetMoveInJob.class);
		expose(GetMoveOutJob.class);
		expose(GetMoveTransferJob.class);
		expose(GetPaymentArrangementsJob.class);
		expose(GetPaymentNotificationJob.class);

		System.err.println("JobsGuiceModule::configure" + properties);

	}

	public static class ScheduleModule extends QuartzModule {

		private Properties properties;

		public ScheduleModule(Properties properties) {
			this.properties = properties;
		}

		protected void schedule() {
			// Temporarily: Uncomment the below line if you'd like the scheduler
			// to be started manually
			// configureScheduler().withManualStart();

			binder().bind(JobExecutor.class).in(Singleton.class);
			binder().bind(QuartzUtils.class).in(Singleton.class);

			scheduleJobWithCronProperty(GetServerQueueJob.class,
					Constants.PROPERTY_JOBS_SERVER_QUEUE_CRON);

			scheduleJobWithCronProperty(GetMoveInJob.class,
					Constants.PROPERTY_JOBS_MOVEIN_QUEUE_CRON);

			scheduleJobWithCronProperty(GetMoveOutJob.class,
					Constants.PROPERTY_JOBS_MOVE_OUT_QUEUE_CRON);

			scheduleJobWithCronProperty(GetMoveTransferJob.class,
					Constants.PROPERTY_JOBS_TRANSFER_QUEUE_CRON);

			scheduleJobWithCronProperty(GetPaymentArrangementsJob.class,
					Constants.PROPERTY_JOBS_PAYMENT_ARRANGEMENTS_QUEUE_CRON);

			scheduleJobWithCronProperty(GetPaymentNotificationJob.class,
					Constants.PROPERTY_JOBS_PAYMENT_NOTIFICATION_QUEUE_CRON);

			System.err.println("JobsGuiceModule::schedule");

		}

		protected void scheduleJobWithCronProperty(
				Class<? extends Job> jobClass, String cronProperty) {
			String jobCronExpression = properties.getProperty(cronProperty);
			scheduleJobWithCron(jobClass, jobClass.getName(), "Trigger-"
					+ jobClass.getName(), jobCronExpression);
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
