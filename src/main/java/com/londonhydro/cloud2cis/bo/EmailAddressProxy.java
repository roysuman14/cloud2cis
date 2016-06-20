package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.sap.model.BusinessProcess1Type;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.EmailAddressType;
import com.londonhydro.sap.model.ServiceQueue;

public class EmailAddressProxy {
	public ServiceQueue marshal(List<ServerQueue> emailAddressChangeList) {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess1Type emailAddressChange = new BusinessProcess1Type();

		emailAddressChange.setProcessID(Long.toString(emailAddressChangeList
				.get(0).getTransactionId()));
		emailAddressChange.setType(QueueTypes.ChangeEmailAddress.toString());
		emailAddressChange.setSubType(emailAddressChangeList.get(0)
				.getActionType());
		emailAddressChange.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess1Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess1Type.MasterData();

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(emailAddressChangeList.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		for (ServerQueue serverQueue : emailAddressChangeList) {
			if (serverQueue.getColumn().equals("emailAddr")) {

				EmailAddressType emailAddress = new EmailAddressType();
				emailAddress.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));

				masterData.setEmailAddress(emailAddress);
			}
			if (serverQueue.getColumn().equals("effectiveDate")) {
				masterData.setEffectiveDate(systemTime);
			}
		}

		emailAddressChange.setMasterData(masterData);
		sq.getBusinessProcess1().add(emailAddressChange);
		return sq;

	}

}
