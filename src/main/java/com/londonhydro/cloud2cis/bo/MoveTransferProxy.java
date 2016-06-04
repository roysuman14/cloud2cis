package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.model.movein.MoveInRequest;
import com.londonhydro.model.movein.TransferRequest;
import com.londonhydro.model.moveout.MoveOutRequest;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.OwnerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess9Type;
import com.londonhydro.sap.model.BusinessProcess9Type.MasterData;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveIn;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveIn.BuyingPremise;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveIn.NewOwnerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.LawyerName;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.LawyerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.MailingAddressControl;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.NewOwnerName;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.Premise;
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.SellingPremise;
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

public class MoveTransferProxy {
	public ServiceQueue marshal(List<TransferRequest> transferRequestList) {
		Calendar today = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();

		// header information
		BusinessProcess9Type tarnsferInfo = new BusinessProcess9Type();
		tarnsferInfo.setProcessID(Long.toString((transferRequestList.get(0)
				.getMovein().getTransactionId())));
		tarnsferInfo.setType(QueueTypes.Transfer.toString());
		tarnsferInfo.setDateTime(today);

		for (TransferRequest transferRequest : transferRequestList) {

			MasterData masterData = new MasterData();
			tarnsferInfo.setMasterData(masterData);

			// customer /account information
			CustomerNumberType customerNumberType = new CustomerNumberType();
			customerNumberType.setValue(transferRequest.getMoveout()
					.getCustomerId());
			masterData.setCustomerNumber(customerNumberType);

			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
			billingAccountNumber.setValue(transferRequest.getMoveout()
					.getAccountId());
			masterData.setBillingAccountNumber(billingAccountNumber);

			// move-out & move-in information
			tarnsferInfo
					.setMoveOut(marshalMoveOut(transferRequest.getMoveout()));
			tarnsferInfo.setMoveIn(marshalMoveIn(transferRequest.getMovein()));

		}
		sq.getBusinessProcess9().add(tarnsferInfo);
		return sq;
	}

	private MoveOut marshalMoveOut(MoveOutRequest moveOutRequest) {
		MoveOut moveOut = new MoveOut();

		// moveout data
		moveOut.setMoveOutDate(DataUtil.dateToCalendar(moveOutRequest
				.getMoveOutDate()));

		// premise
		Premise premise = new Premise();
		premise.setValue(moveOutRequest.getPremiseId());
		moveOut.setPremise(premise);

		// buying/selling with owner/lawyer
		SellingPremise sellingPremise = new SellingPremise();
		sellingPremise.setValue(DataUtil.bool01(moveOutRequest.isSelling()));
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

		com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.NewOwnerPhoneNumber newOwnerPhone = new com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.NewOwnerPhoneNumber();
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
		coNameType
				.setValue(DataUtil.sanitizeString(moveOutRequest.getCareOf()));
		mailingAddressType.setCOName(coNameType);

		NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
		nonCivicInformation1.setValue(DataUtil.sanitizeString(moveOutRequest
				.getNonCivic1()));
		mailingAddressType.setNonCivicInformation1(nonCivicInformation1);

		NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
		nonCivicInformation2.setValue(DataUtil.sanitizeString(moveOutRequest
				.getNonCivic2()));
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
		unit.setValue(DataUtil.sanitizeString(moveOutRequest.getStreetUnit()));
		mailingAddressType.setUnit(unit);

		City city = new City();
		city.setValue(DataUtil.sanitizeString(moveOutRequest.getCity()));
		mailingAddressType.setCity(city);

		Province province = new Province();
		province.setValue(DataUtil.sanitizeString(moveOutRequest.getProvince()));
		mailingAddressType.setProvince(province);

		PostalCode postalCode = new PostalCode();
		postalCode.setValue(DataUtil.sanitizeStringRemoveWS(moveOutRequest
				.getPostalCode()));
		mailingAddressType.setPostalCode(postalCode);

		Country country = new Country();
		country.setValue(DataUtil.sanitizeString(moveOutRequest.getCountry()));
		mailingAddressType.setCountry(country);

		moveOut.setMailingAddress(mailingAddressType);
		return moveOut;
	}

	private MoveIn marshalMoveIn(MoveInRequest moveInRequest) {

		MoveIn moveIn = new MoveIn();

		// move-in premise & date
		BusinessProcess9Type.MoveIn.Premise premise = new BusinessProcess9Type.MoveIn.Premise();
		premise.setValue(moveInRequest.getPremiseId());
		moveIn.setPremise(premise);

		moveIn.setMoveInDate(DataUtil.dateToCalendar(moveInRequest
				.getMoveInDate()));

		// previous owner/laywer details
		BuyingPremise buyingPremise = new BuyingPremise();
		moveIn.setBuyingPremise(buyingPremise);
		buyingPremise.setValue(moveInRequest.isBuying() ? "Y" : "N");

		BusinessProcess9Type.MoveIn.NewOwnerName ownerName = new BusinessProcess9Type.MoveIn.NewOwnerName();
		ownerName
				.setValue(DataUtil.sanitizeString(moveInRequest.getOwnerName()));
		moveIn.setNewOwnerName(ownerName);

		BusinessProcess9Type.MoveIn.NewOwnerPhoneNumber ownerPhoneNumber = new BusinessProcess9Type.MoveIn.NewOwnerPhoneNumber();
		ownerPhoneNumber.setValue(DataUtil.sanitizeString(moveInRequest
				.getOwnerPhone()));
		moveIn.setNewOwnerPhoneNumber(ownerPhoneNumber);

		BusinessProcess9Type.MoveIn.LawyerName lawyerName = new BusinessProcess9Type.MoveIn.LawyerName();
		lawyerName.setValue(DataUtil.sanitizeString(moveInRequest
				.getLawyerName()));
		moveIn.setLawyerName(lawyerName);

		BusinessProcess9Type.MoveIn.LawyerPhoneNumber lawyerPhoneNumber = new BusinessProcess9Type.MoveIn.LawyerPhoneNumber();
		lawyerPhoneNumber.setValue(DataUtil.sanitizeString(moveInRequest
				.getLawyerPhone()));
		moveIn.setLawyerPhoneNumber(lawyerPhoneNumber);

		// mailing address & control flag
		BusinessProcess9Type.MoveIn.MailingAddressControl mac = new BusinessProcess9Type.MoveIn.MailingAddressControl();
		mac.setValue(DataUtil.sanitizeString(moveInRequest
				.getMailingAddressControl()));
		moveIn.setMailingAddressControl(mac);

		MailingAddressType mailingAddress = new MailingAddressType();
		moveIn.setMailingAddress(mailingAddress);

		CONameType coNameType = new CONameType();
		coNameType.setValue(DataUtil.sanitizeString(moveInRequest.getCareOf()));
		mailingAddress.setCOName(coNameType);

		NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
		nonCivicInformation1.setValue(DataUtil.sanitizeString(moveInRequest
				.getNonCivic1()));
		mailingAddress.setNonCivicInformation1(nonCivicInformation1);

		NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
		nonCivicInformation2.setValue(DataUtil.sanitizeString(moveInRequest
				.getNonCivic2()));
		mailingAddress.setNonCivicInformation2(nonCivicInformation2);

		HouseNumber1 houseNumber1 = new HouseNumber1();
		houseNumber1.setValue(DataUtil.sanitizeString(moveInRequest
				.getStreetNumber()));
		mailingAddress.setHouseNumber1(houseNumber1);

		StreetName streetName = new StreetName();
		streetName.setValue(DataUtil.sanitizeString(moveInRequest
				.getStreetName()));
		mailingAddress.setStreetName(streetName);

		Unit unit = new Unit();
		unit.setValue(DataUtil.sanitizeString(moveInRequest.getStreetUnit()));
		mailingAddress.setUnit(unit);

		City city = new City();
		city.setValue(DataUtil.sanitizeString(moveInRequest.getCity()));
		mailingAddress.setCity(city);

		Province province = new Province();
		province.setValue(DataUtil.sanitizeString(moveInRequest.getProvince()));
		mailingAddress.setProvince(province);

		PostalCode postalCode = new PostalCode();
		postalCode.setValue(DataUtil.sanitizeStringRemoveWS(moveInRequest
				.getPostalCode()));
		mailingAddress.setPostalCode(postalCode);

		Country country = new Country();
		country.setValue(DataUtil.sanitizeString(moveInRequest.getCountry()));
		mailingAddress.setCountry(country);

		return moveIn;
	}

}
