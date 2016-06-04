package com.londonhydro.cloud2cis.job;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.common.base.Throwables;

/**
 * Super-class definition for every scheduled Job which outputs logging
 * information about Jobs, catches any Throawable and check that the same
 * process is not overlapping with a previous execution of itself.
 * 
 * @author Daniel I. Khan Ramiro (dikhan@affsys.com)
 */
public abstract class AbstractJob implements Job {

	private final AtomicBoolean isExecuting = new AtomicBoolean(false);

	protected static final Log logger = LogFactory.getLog(AbstractJob.class);

	public final void execute(JobExecutionContext context) throws JobExecutionException {
		final String jobName = context.getJobDetail().getKey().getName();

		if (isExecuting.compareAndSet(false, true)) {
			logger.info(String.format("JOB {%s}: started", jobName));
			try {
				execute();
				logger.info(String.format("JOB {%s}: finished", jobName));
			} catch (Throwable e) // According to Quartz guidelines, all
									// exceptions must be handle to avoid
									// re-execution
			{
				logger.error(String.format("JOB {%s}: unexpected error. Message [%s] ; Exception: %s", jobName,
						e.getMessage(), Throwables.getStackTraceAsString(e)));
			} finally {
				isExecuting.set(false);
			}
		} else
			logger.info(String.format("JOB {%s}: skip execution, there is previous process that has not finished yet.",
					jobName));
	}

	public abstract void execute() throws Exception;
}
