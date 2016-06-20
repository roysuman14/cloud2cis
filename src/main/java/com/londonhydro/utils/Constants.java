package com.londonhydro.utils;

/**
 * Constants class - Mostly used by Quartz
 * 
 * @author Daniel Isaac Khan Ramiro
 *
 */
public interface Constants {

	String PROPERTIES_CRON_FILE = "/cron.properties";

	// JOB NAMES

	String JOB_SERVER_QUEUE = "ServerQueueJob";
	String JOB_MOVE_IN = "MoveInJob";
	String JOB_MOVE_OUT = "MoveOutJob";
	String JOB_TRANSFER = "TransferJob";
	String JOB_PAYMENT_NOTIFICATION_QUEUE = "PaymentNotificationQueueJob";
	String JOB_PAYMENT_ARRANGEMENTS_QUEUE = "PaymentArrangementsQueueJob";


	// CRON PROPERTIES

	String PROPERTY_SCHEDULER_START_DELAYED = "com.londonhydro.cloud2cis.scheduler.secondsStartdDelayed";
	String PROPERTY_JOBs_ONE_TIME_DELAY = "com.londonhydro.cloud2cis.jobs.oneTimeMillisecondsDelay";

	String PROPERTY_DUMMY = "com.londonhydro.cloud2cis.jobs.dummyJob.cron";
	String PROPERTY_JOBS_SERVER_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.serverQueueJob.cron";
	String PROPERTY_JOBS_MOVEIN_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.moveInJob.cron";
	String PROPERTY_JOBS_MOVE_OUT_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.moveOut.cron";
	String PROPERTY_JOBS_TRANSFER_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.transfer.cron";
	String PROPERTY_JOBS_PAYMENT_NOTIFICATION_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.paymentNotificationQueueJob.cron";
	String PROPERTY_JOBS_PAYMENT_ARRANGEMENTS_QUEUE_CRON = "com.londonhydro.cloud2cis.jobs.paymentArrangementsQueueJob.cron";

	
	
	String JOB_GROUP_REPETITIVE = "Repetitive";
	String JOB_GROUP_ONE_TIME = "OneTime";

	/*
	 * Merge XML SQL Statements
	 * 
	 * These below constant names must match with the sql tag names in the
	 * following external XML file: File Path:
	 * london-hydro-broker\src\main\resources\com\londonhydro\broker\merge\sql\
	 * MergeSQL.xml
	 * 
	 */
	String XML_SQL_GET_NEXT_BATCHID = "getNextBatchId";
	String XML_SQL_GET_RECORDS_BY_BATCHID = "getRecordsByBatchId";
	
	String BUDGET_BILLING_PAYMENT_TYPE="0001";
	String NOT_APPLICABLE_CODE="NA";
	
	String BP_SUB_TYPE_NEW="New";
	String BP_SUB_TYPE_EXISTING="Exsiting";
	
	String YN_CODE_Y="Y";
	String YN_CODE_N="N";
	String YN_CODE_YES="Yes";
	String YN_CODE_NO="No";
	
	String PAPERLESS_BILLING_FLAG="X";
}
