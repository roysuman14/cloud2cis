package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.model.moveout.MoveOutRequest;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess7Type;
import com.londonhydro.sap.model.BusinessProcess7Type.MasterData;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.LawyerName;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.LawyerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.MailingAddressControl;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.NewOwnerName;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.NewOwnerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.PhoneNumber;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.Premise;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.SellingPremise;
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

public class MoveOutProxy {
	public ServiceQueue marshal(List<MoveOutRequest> moveOutRequestList) {

		// today
		Calendar today = Calendar.getInstance();

		ServiceQueue sq = new ServiceQueue();

		BusinessProcess7Type moveOutInfo = new BusinessProcess7Type();
		// header information
		moveOutInfo.setProcessID(Long.toString((moveOutRequestList.get(0)
				.getTransactionId())));
		moveOutInfo.setType(QueueTypes.MoveOut.toString());
		moveOutInfo.setDateTime(today);

		for (MoveOutRequest moveOutRequest : moveOutRequestList) {

			MoveOut moveOut = new MoveOut();

			MasterData masterData = new MasterData();
			moveOutInfo.setMasterData(masterData);
			// customer /account id
			CustomerNumberType customerNumberType = new CustomerNumberType();
			customerNumberType.setValue(DataUtil.sanitizeString(moveOutRequest
					.getCustomerId()));
			masterData.setCustomerNumber(customerNumberType);

			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();

			billingAccountNumber.setValue(DataUtil
					.sanitizeString(moveOutRequest.getAccountId()));
			masterData.setBillingAccountNumber(billingAccountNumber);

			// premise
			Premise premise = new Premise();
			premise.setValue(DataUtil.sanitizeString(moveOutRequest
					.getPremiseId()));
			moveOut.setPremise(premise);

			// move-out date
			moveOut.setMoveOutDate(DataUtil.dateToCalendar(moveOutRequest
					.getMoveOutDate()));

			// buying/selling with owner/lawyer
			SellingPremise sellingPremise = new SellingPremise();
			sellingPremise
					.setValue(DataUtil.bool01(moveOutRequest.isSelling()));
			moveOut.setSellingPremise(sellingPremise);

			LawyerName lawyerName = new LawyerName();

			lawyerName.setValue(DataUtil.sanitizeString(moveOutRequest
					.getLawyerName()));
			moveOut.setLawyerName(lawyerName);

			LawyerPhoneNumber lawyerPhoneNumber = new LawyerPhoneNumber();
			lawyerPhoneNumber.setValue(DataUtil.sanitizeString(moveOutRequest
					.getLawyerPhone()));
			moveOut.setLawyerPhoneNumber(lawyerPhoneNumber);

			NewOwnerName newOwnerName = new NewOwnerName();
			newOwnerName.setValue(DataUtil.sanitizeString(moveOutRequest
					.getOwnerName()));
			moveOut.setNewOwnerName(newOwnerName);

			NewOwnerPhoneNumber newOwnerPhone = new NewOwnerPhoneNumber();
			newOwnerPhone.setValue(DataUtil.sanitizeString(moveOutRequest
					.getOwnerPhoneNumber()));
			moveOut.setNewOwnerPhoneNumber(newOwnerPhone);

			// mailing address

			MailingAddressControl mailingAddressControl = new MailingAddressControl();
			mailingAddressControl.setValue(moveOutRequest
					.getMailingAddressControl());
			moveOut.setMailingAddressControl(mailingAddressControl);

			MailingAddressType mailingAddressType = new MailingAddressType();

			CONameType coNameType = new CONameType();
			coNameType.setValue(DataUtil.sanitizeString(moveOutRequest
					.getCareOf()));
			mailingAddressType.setCOName(coNameType);

			NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
			nonCivicInformation1.setValue(DataUtil
					.sanitizeString(moveOutRequest.getNonCivic1()));
			mailingAddressType.setNonCivicInformation1(nonCivicInformation1);

			NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
			nonCivicInformation2.setValue(DataUtil
					.sanitizeString(moveOutRequest.getNonCivic2()));
			mailingAddressType.setNonCivicInformation2(nonCivicInformation2);

			HouseNumber1 houseNumber1 = new HouseNumber1();
			houseNumber1.setValue(DataUtil.sanitizeString(moveOutRequest
					.getStreetNumber()));
			mailingAddressType.setHouseNumber1(houseNumber1);

			StreetName streetName = new StreetName();
			streetName.setValue(DataUtil.sanitizeString(moveOutRequest
					.getStreetName()));
			mailingAddressType.setStreetName(streetName);

			Unit unit = new Unit();
			unit.setValue(DataUtil.sanitizeString(moveOutRequest
					.getStreetUnit()));
			mailingAddressType.setUnit(unit);

			City city = new City();
			city.setValue(DataUtil.sanitizeString(moveOutRequest.getCity()));
			mailingAddressType.setCity(city);

			Province province = new Province();
			province.setValue(DataUtil.sanitizeString(moveOutRequest
					.getProvince()));
			mailingAddressType.setProvince(province);

			PostalCode postalCode = new PostalCode();
			postalCode.setValue(DataUtil.sanitizeStringRemoveWS(moveOutRequest
					.getPostalCode()));
			mailingAddressType.setPostalCode(postalCode);

			Country country = new Country();
			country.setValue(DataUtil.sanitizeString(moveOutRequest
					.getCountry()));
			mailingAddressType.setCountry(country);

			moveOut.setMailingAddress(mailingAddressType);

			// Phone Number
			// ***CHECK: for business /cell phone number
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setValue(moveOutRequest.getResiPhone());
			moveOut.setPhoneNumber(phoneNumber);

			moveOutInfo.setMoveOut(moveOut);
		}
		sq.getBusinessProcess7().add(moveOutInfo);
		return sq;
	}

}
