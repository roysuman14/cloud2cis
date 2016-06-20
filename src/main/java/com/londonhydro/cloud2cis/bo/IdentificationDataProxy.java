package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.sap.model.BusinessProcess13Type;
import com.londonhydro.sap.model.BusinessProcess13Type.MasterData;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.IdentificationType;
import com.londonhydro.sap.model.ServiceQueue;

public class IdentificationDataProxy {

	String profileInfo = null;

	public ServiceQueue marshal(List<ServerQueue> serverQueues) {
		Calendar systemTime = Calendar.getInstance();
		Date date = new Date();
		systemTime.setTime(date);
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess13Type bpData = new BusinessProcess13Type();
		MasterData masterData = new MasterData();
		bpData.setType(QueueTypes.ChangeMasterData.toString());
		bpData.setSubType(serverQueues.get(0).getActionType());
		bpData.setProcessID(Long.toString(serverQueues.get(0)
				.getTransactionId()));
		bpData.setDateTime(systemTime);

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(serverQueues.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);
		masterData.setEffectiveDate(systemTime);

		for (ServerQueue serverQueue : serverQueues) {
			if (serverQueue.getColumn().equals("dob_hash")) {
				masterData.setDateOfBirth(DataUtil.dateToCalendar(DataUtil
						.yyyymmddToDate(serverQueue.getNewValue())));// Decryption
																		// to be
																		// implemented
			} else if (serverQueue.getColumn().equals("document_hash")) {
				IdentificationType identificationType = new IdentificationType();
				String strArray[] = serverQueue.getNewValue().split("=");// Decryption
																			// to
																			// be
																			// implemented
				identificationType.setIDType(DataUtil
						.sanitizeString(strArray[0]));
				identificationType.setIdentificationNumber(DataUtil
						.sanitizeString(strArray[1]));
				identificationType.setEffectiveDate(systemTime);
				masterData.getIdentification().add(identificationType);
			}
		}
		bpData.setMasterData(masterData);
		sq.getBusinessProcess13().add(bpData);
		return sq;
	}
}
