package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess2Type;
import com.londonhydro.sap.model.BusinessProcess2Type.MasterData.Paperless;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.Constants;

public class PaperlessBillingProxy {
	public ServiceQueue marshal(List<ServerQueue> paperlessBillingList) {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();
		BusinessProcess2Type bpData = new BusinessProcess2Type();

		bpData.setProcessID(Long.toString((paperlessBillingList.get(0)
				.getTransactionId())));
		bpData.setType(QueueTypes.ChangeBillDeliveryMethod.toString());
		bpData.setSubType(paperlessBillingList.get(0).getActionType());
		bpData.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess2Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess2Type.MasterData();

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(paperlessBillingList.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
		billingAccountNumber.setValue(paperlessBillingList.get(0)
				.getAccountId());
		masterData.setBillingAccountNumber(billingAccountNumber);

		for (ServerQueue serverQueue : paperlessBillingList) {
			if (serverQueue.getColumn().equals("is_paperless_billing")) {
				Paperless paperless = new Paperless();
				if (serverQueue.getNewValue() != null
						&& Boolean.valueOf(serverQueue.getNewValue())) {
					paperless.setValue(Constants.PAPERLESS_BILLING_FLAG);
				} else {
					paperless.setValue("");
				}

				masterData.setPaperless(paperless);
			}
		}

		bpData.setMasterData(masterData);
		sq.getBusinessProcess2().add(bpData);
		return sq;

	}

}
