package com.londonhydro.cloud2cis.bo;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.sap.model.BusinessProcess0Type;
import com.londonhydro.sap.model.BusinessProcess0Type.MasterData.Extension;
import com.londonhydro.sap.model.CountryCodeType;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.PhoneNumberCategory;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.PhoneNumberConstant;

public class PhoneNumberProxy {

	public ServiceQueue marshal(List<ServerQueue> phoneNumberChangeList)
			throws ParseException {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();
		BusinessProcess0Type bpData = new BusinessProcess0Type();

		bpData.setProcessID(Long.toString(phoneNumberChangeList.get(0)
				.getTransactionId()));
		bpData.setType(QueueTypes.ChangePhoneNumber.toString());
		bpData.setSubType(phoneNumberChangeList.get(0).getActionType());
		bpData.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess0Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess0Type.MasterData();

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(phoneNumberChangeList.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		for (ServerQueue serverQueue : phoneNumberChangeList) {
			if (serverQueue.getColumn().equals(
					PhoneNumberConstant.FIELD_PHONE_COUNTRY)) {
				CountryCodeType countryCode = new CountryCodeType();
				countryCode.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				masterData.setCountryCode(countryCode);
			} else if (serverQueue.getColumn().equals(
					PhoneNumberConstant.FIELD_PHONE_EXT)) {
				Extension extension = new Extension();
				extension.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				masterData.setExtension(extension);
			} else if (serverQueue.getColumn().equals(
					PhoneNumberConstant.FIELD_PHONE_NUMBER)) {
				com.londonhydro.sap.model.BusinessProcess0Type.MasterData.PhoneNumber phoneNumber = new com.londonhydro.sap.model.BusinessProcess0Type.MasterData.PhoneNumber();
				phoneNumber.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				masterData.setPhoneNumber(phoneNumber);
			} else if (serverQueue.getColumn().equals(
					PhoneNumberConstant.FIELD_CATEGORY)) {
				PhoneNumberCategory remarks = new PhoneNumberCategory();
				remarks.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				masterData.setRemarks(remarks);
			} else if (serverQueue.getColumn().equals(
					PhoneNumberConstant.FIELD_EFFECTIVE_DATE)) {
				masterData.setEffectiveDate(DataUtil.dateToCalendar(DataUtil
						.yyyymmddToDate(serverQueue.getNewValue())));
			}
		}

		bpData.setMasterData(masterData);
		sq.getBusinessProcess0().add(bpData);
		return sq;

	}
}
