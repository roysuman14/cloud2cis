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
	String JOB_PHONE_NO = "PhoneNumberJob";
	String JOB_MAILING_ADDRESS = "MailingAddressJob";
	String JOB_FIXED_MAILING_ADDRESS = "FixedMailingAddressJob";
	String JOB_EMAIL = "EmailJob";
	String JOB_MASTER_DATA = "MasterDataJob";
	String JOB_MOVE_OUT = "MoveOutJob";
	String JOB_TRANSFER = "TransferJob";

	// TRIGGER NAMES

	String TRIGGER_SERVER_QUEUE = "TriggerServerQueueJob";
	String TRIGGER_MOVE_IN = "TriggerMoveInJob";
	String TRIGGER_PHONE_NO = "TriggerPhoneNumberJob";
	String TRIGGER_MAILING_ADDRESS = "TriggerMailingAddressJob";
	String TRIGGER_FIXED_MAILING_ADDRESS = "TriggerFixedMailingAddressJob";
	String TRIGGER_EMAIL = "EmailJob";
	String TRIGGER_MASTER_DATA = "MasterDataJob";
	String TRIGGER_MOVE_OUT = "MoveOutJob";
	String TRIGGER_TRANSFER = "TransferJob";

	// CRON PROPERTIES

	String PROPERTY_SCHEDULER_START_DELAYED = "com.londonhydro.broker.scheduler.secondsStartdDelayed";
	String PROPERTY_JOBs_ONE_TIME_DELAY = "com.londonhydro.broker.jobs.oneTimeMillisecondsDelay";

	String PROPERTY_DUMMY = "com.londonhydro.broker.jobs.dummyJob.cron";
	String PROPERTY_JOBS_SERVER_QUEUE = "com.londonhydro.cloud2cis.jobs.serverQueueJob.cron";
	String PROPERTY_JOBS_MOVEIN_QUEUE = "com.londonhydro.cloud2cis.jobs.moveInJob.cron";
	String PROPERTY_JOBS_PHONENO_QUEUE = "com.londonhydro.cloud2cis.jobs.phoneNumber.cron";
	String PROPERTY_JOBS_MAILING_ADDRESS_QUEUE = "com.londonhydro.cloud2cis.jobs.mailingAddress.cron";
	String PROPERTY_JOBS_FIXED_MAILING_ADDRESS_QUEUE = "com.londonhydro.cloud2cis.jobs.fixedMailingAddress.cron";
	String PROPERTY_JOBS_EMAIL_QUEUE = "com.londonhydro.cloud2cis.jobs.emailAddress.cron";
	String PROPERTY_JOBS_MASTER_DATA_QUEUE = "com.londonhydro.cloud2cis.jobs.masterData.cron";
	String PROPERTY_JOBS_MOVE_OUT_QUEUE = "com.londonhydro.cloud2cis.jobs.moveOut.cron";
	String PROPERTY_JOBS_TRANSFER_QUEUE = "com.londonhydro.cloud2cis.jobs.transfer.cron";

	
	
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
}
