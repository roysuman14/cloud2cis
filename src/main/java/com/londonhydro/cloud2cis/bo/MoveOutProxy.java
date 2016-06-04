package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.model.moveout.MoveOutRequest;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess7Type;
import com.londonhydro.sap.model.BusinessProcess7Type.MasterData;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.LawyerName;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.LawyerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.MailingAddressControl;
import com.londonhydro.sap.model.BusinessProcess7Type.MoveOut.NewOwnerName;
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
		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();
		
		BusinessProcess7Type moveOutInfo = new BusinessProcess7Type();
		moveOutInfo.setProcessID(Long.toString((moveOutRequestList.get(0).getTransactionId())));
		moveOutInfo.setType(QueueTypes.MoveOut.toString());
		moveOutInfo.setDateTime(systemTime);
		
		for(MoveOutRequest moveOutRequest : moveOutRequestList){
			MasterData masterData = new MasterData();
			MoveOut moveOut = new MoveOut();
			CustomerNumberType customerNumberType = new CustomerNumberType();
			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
			
			if(moveOutRequest.getCustomerId()!=null && !moveOutRequest.getCustomerId().isEmpty())
				customerNumberType.setValue(moveOutRequest.getCustomerId());
			else
				customerNumberType.setValue("");
			masterData.setCustomerNumber(customerNumberType);
			
			if(moveOutRequest.getAccountId()!=null && !moveOutRequest.getAccountId().isEmpty())
				billingAccountNumber.setValue(moveOutRequest.getAccountId());
			else
				billingAccountNumber.setValue("");
			masterData.setBillingAccountNumber(billingAccountNumber);
			moveOutInfo.setMasterData(masterData);
			
			Calendar effDate = Calendar.getInstance();
			effDate.setTime(moveOutRequest.getMoveOutDate());
			effDate.add(Calendar.HOUR_OF_DAY, 4);//Added 4 hrs due to time-zone difference from GMT to EDT
			moveOut.setMoveOutDate(effDate);
			
			Premise premise = new Premise();
			if (moveOutRequest.getPremiseId()!=null && !moveOutRequest.getPremiseId().isEmpty())
				premise.setValue(moveOutRequest.getPremiseId());
			else
				premise.setValue("");
			moveOut.setPremise(premise);
			
			MailingAddressControl mailingAddressControl = new MailingAddressControl();
			if(moveOutRequest.getMailingAddressControl()!=null && !moveOutRequest.getMailingAddressControl().isEmpty())
				mailingAddressControl.setValue(moveOutRequest.getMailingAddressControl());
			else
				mailingAddressControl.setValue("");
			moveOut.setMailingAddressControl(mailingAddressControl);
			
			PhoneNumber phoneNumber = new PhoneNumber();
			if(moveOutRequest.getPhoneNumber()!=null && !moveOutRequest.getPhoneNumber().isEmpty())
				phoneNumber.setValue(moveOutRequest.getPhoneNumber());
			else
				phoneNumber.setValue("");
			moveOut.setPhoneNumber(phoneNumber);
			
			SellingPremise sellingPremise = new SellingPremise();
			if(moveOutRequest.isSelling())
				sellingPremise.setValue("1");
			else
				sellingPremise.setValue("0");
			moveOut.setSellingPremise(sellingPremise);
			
			LawyerName lawyerName = new LawyerName();
			if(moveOutRequest.getLawyerName()!=null && !moveOutRequest.getLawyerName().isEmpty())
				lawyerName.setValue(moveOutRequest.getLawyerName());
			else
				lawyerName.setValue("");
			moveOut.setLawyerName(lawyerName);
			
			LawyerPhoneNumber lawyerPhoneNumber = new LawyerPhoneNumber();
			if(moveOutRequest.getLawyerPhone()!=null && !moveOutRequest.getLawyerPhone().isEmpty())
				lawyerPhoneNumber.setValue(moveOutRequest.getLawyerPhone());
			else
				lawyerPhoneNumber.setValue("");
			moveOut.setLawyerPhoneNumber(lawyerPhoneNumber);
			
			NewOwnerName newOwnerName = new NewOwnerName();
			if(moveOutRequest.getOwnerName()!=null && !moveOutRequest.getOwnerName().isEmpty())
				newOwnerName.setValue(moveOutRequest.getOwnerName());
			else
				newOwnerName.setValue("");
			moveOut.setNewOwnerName(newOwnerName);
			
			MailingAddressType mailingAddressType = new MailingAddressType();
			
			HouseNumber1 houseNumber1 = new HouseNumber1();
			if(moveOutRequest.getStreetNumber()!=null && !moveOutRequest.getStreetNumber().isEmpty())
				houseNumber1.setValue(moveOutRequest.getStreetNumber());
			else
				houseNumber1.setValue("");
			mailingAddressType.setHouseNumber1(houseNumber1);

			StreetName streetName = new StreetName();
			if(moveOutRequest.getStreetName()!=null && !moveOutRequest.getStreetName().isEmpty())
				streetName.setValue(moveOutRequest.getStreetName());
			else
				streetName.setValue("");
			mailingAddressType.setStreetName(streetName);

			Unit unit = new Unit();
			if(moveOutRequest.getStreetUnit()!=null && !moveOutRequest.getStreetUnit().isEmpty())
				unit.setValue(moveOutRequest.getStreetUnit());
			else
				unit.setValue("");
			mailingAddressType.setUnit(unit);

			City city = new City();
			if(moveOutRequest.getCity()!=null && !moveOutRequest.getCity().isEmpty())
				city.setValue(moveOutRequest.getCity());
			else
				city.setValue("");
			mailingAddressType.setCity(city);

			Province province = new Province();
			if(moveOutRequest.getProvince()!=null && !moveOutRequest.getProvince().isEmpty())
				province.setValue(moveOutRequest.getProvince());
			else
				province.setValue("");
			mailingAddressType.setProvince(province);

			PostalCode postalCode = new PostalCode();
			if(moveOutRequest.getPostalCode()!=null && !moveOutRequest.getPostalCode().isEmpty())
				postalCode.setValue(moveOutRequest.getPostalCode());
			else
				postalCode.setValue("");
			mailingAddressType.setPostalCode(postalCode);

			Country country = new Country();
			if(moveOutRequest.getCountry()!=null && !moveOutRequest.getCountry().isEmpty())
				country.setValue(moveOutRequest.getCountry());
			else
				country.setValue("");
			mailingAddressType.setCountry(country);

			NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
			if(moveOutRequest.getNonCivic1()!=null && !moveOutRequest.getNonCivic1().isEmpty())
				nonCivicInformation1.setValue(moveOutRequest.getNonCivic1());
			else
				nonCivicInformation1.setValue("");
			mailingAddressType.setNonCivicInformation1(nonCivicInformation1);

			NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
			if(moveOutRequest.getNonCivic2()!=null && !moveOutRequest.getNonCivic2().isEmpty())
				nonCivicInformation2.setValue(moveOutRequest.getNonCivic2());
			else
				nonCivicInformation2.setValue("");
			mailingAddressType.setNonCivicInformation2(nonCivicInformation2);

			CONameType coNameType = new CONameType();
			if(moveOutRequest.getCareOf()!=null && !moveOutRequest.getCareOf().isEmpty())
				coNameType.setValue(moveOutRequest.getCareOf());
			else
				coNameType.setValue("");
			mailingAddressType.setCOName(coNameType);

			if (mailingAddressType.getCountry() == null || (mailingAddressType.getCountry().getValue() == null
					|| mailingAddressType.getCountry().getValue().trim().equals(""))) {
				country.setValue("CA");
				mailingAddressType.setCountry(country);
			}

			if (mailingAddressType.getProvince() == null || (mailingAddressType.getProvince().getValue() == null
					|| mailingAddressType.getProvince().getValue().trim().equals(""))) {
				province.setValue("ON");
				mailingAddressType.setProvince(province);
			}
			if (mailingAddressType.getCountry().getValue().trim().equals("CA")
					&& mailingAddressType.getPostalCode().getValue().trim().length() == 6) {
				String pc = mailingAddressType.getPostalCode().getValue().trim();
				pc = pc.substring(0, 3) + " " + pc.substring(3);
				postalCode.setValue(pc);
				mailingAddressType.setPostalCode(postalCode);
			}
			moveOut.setMailingAddress(mailingAddressType);
			moveOutInfo.setMoveOut(moveOut);
		}
		sq.getBusinessProcess7().add(moveOutInfo);
		return sq;
	}

}
