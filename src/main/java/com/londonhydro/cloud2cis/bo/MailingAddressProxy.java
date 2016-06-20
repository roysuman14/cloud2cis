package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.sap.model.BusinessProcess3Type;
import com.londonhydro.sap.model.CONameType;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.MailingAddressType;
import com.londonhydro.sap.model.MailingAddressType.City;
import com.londonhydro.sap.model.MailingAddressType.Country;
import com.londonhydro.sap.model.MailingAddressType.HouseNumber1;
import com.londonhydro.sap.model.MailingAddressType.NonCivicInformation1;
import com.londonhydro.sap.model.MailingAddressType.NonCivicInformation2;
import com.londonhydro.sap.model.MailingAddressType.PostalCode;
import com.londonhydro.sap.model.MailingAddressType.Province;
import com.londonhydro.sap.model.MailingAddressType.StreetName;
import com.londonhydro.sap.model.MailingAddressType.Unit;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.MailingAddressConstant;

public class MailingAddressProxy {
	public ServiceQueue marshal(List<ServerQueue> serverQueues) {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess3Type bpData = new BusinessProcess3Type();

		bpData.setProcessID(Long.toString((serverQueues.get(0)
				.getTransactionId())));
		bpData.setType(QueueTypes.ChangeMailingAddress.toString());
		bpData.setSubType(serverQueues.get(0).getActionType());
		bpData.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess3Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess3Type.MasterData();

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(serverQueues.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		MailingAddressType mailingAddress = new MailingAddressType();
		for (ServerQueue serverQueue : serverQueues) {
			if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_STRRET_NUMBER)) {
				HouseNumber1 houseNumber1 = new HouseNumber1();
				houseNumber1.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setHouseNumber1(houseNumber1);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_STREET_NAME)) {
				StreetName streetName = new StreetName();
				streetName.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setStreetName(streetName);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_STEET_UNIT)) {
				Unit unit = new Unit();
				unit.setValue(DataUtil.sanitizeString(serverQueue.getNewValue()));
				mailingAddress.setUnit(unit);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_CITY)) {
				City city = new City();
				city.setValue(DataUtil.sanitizeString(serverQueue.getNewValue()));
				mailingAddress.setCity(city);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_PROVINCE)) {
				Province province = new Province();
				province.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setProvince(province);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_POSTIAL_CODE)) {
				PostalCode postalCode = new PostalCode();
				postalCode.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setPostalCode(postalCode);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_COUNRTY)) {
				Country country = new Country();
				country.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setCountry(country);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_SUP_1)) {
				NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
				nonCivicInformation1.setValue(DataUtil
						.sanitizeString(serverQueue.getNewValue()));
				mailingAddress.setNonCivicInformation1(nonCivicInformation1);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_SUP_2)) {
				NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
				nonCivicInformation2.setValue(DataUtil
						.sanitizeString(serverQueue.getNewValue()));
				mailingAddress.setNonCivicInformation2(nonCivicInformation2);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_CARE_OF)) {
				CONameType coName = new CONameType();
				coName.setValue(DataUtil.sanitizeString(serverQueue
						.getNewValue()));
				mailingAddress.setCOName(coName);
			} else if (serverQueue.getColumn().equals(
					MailingAddressConstant.FIELD_EFFECTIVE_DATE)) {
				masterData.setEffectiveDate(DataUtil.dateToCalendar(DataUtil
						.yyyymmddToDate(serverQueue.getNewValue())));
			}
		}
		masterData.setMailingAddress(mailingAddress);
		bpData.setMasterData(masterData);
		sq.getBusinessProcess3().add(bpData);

		return sq;
	}
}
