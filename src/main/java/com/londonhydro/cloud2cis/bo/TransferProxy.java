package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.model.TransferRequest;
import com.londonhydro.sap.model.BillingAccountNumberType;
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
import com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.PhoneNumber;
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


public class TransferProxy {
	public ServiceQueue marshal(List<TransferRequest> transferRequestList) {
		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();
		
		BusinessProcess9Type tarnsferInfo = new BusinessProcess9Type();
		tarnsferInfo.setProcessID(Long.toString((transferRequestList.get(0).getTransactionId())));
		tarnsferInfo.setType(QueueTypes.Transfer.toString());
		tarnsferInfo.setDateTime(systemTime);
		
		for(TransferRequest transferRequest : transferRequestList){
			
				if(transferRequest.isTransfer()){
					MasterData masterData = new MasterData();
					MoveOut moveOut = new MoveOut();
					CustomerNumberType customerNumberType = new CustomerNumberType();
					BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
					
					if(transferRequest.getCustomerId()!=null && !transferRequest.getCustomerId().isEmpty()){
						customerNumberType.setValue(transferRequest.getCustomerId());
						masterData.setCustomerNumber(customerNumberType);
					}else{
						customerNumberType.setValue("");
						masterData.setCustomerNumber(customerNumberType);
					}
					
					if(transferRequest.getAccountId()!=null && !transferRequest.getAccountId().isEmpty()){
						billingAccountNumber.setValue(transferRequest.getAccountId());
						masterData.setBillingAccountNumber(billingAccountNumber);
					}else{
						billingAccountNumber.setValue("");
						masterData.setBillingAccountNumber(billingAccountNumber);
					}
					
					tarnsferInfo.setMasterData(masterData);
					
					Calendar effDate = Calendar.getInstance();
					effDate.setTime(transferRequest.getMoveOutDate());
					effDate.add(Calendar.HOUR_OF_DAY, 4);//Added 4 hrs due to time-zone difference from GMT to EDT
					moveOut.setMoveOutDate(effDate);
					
					Premise premise = new Premise();
					if (transferRequest.getPremiseId()!=null && !transferRequest.getPremiseId().isEmpty())
						premise.setValue(transferRequest.getPremiseId());
					else
						premise.setValue("");
					moveOut.setPremise(premise);
					
					MailingAddressControl mailingAddressControl = new MailingAddressControl();
					if(transferRequest.getMailingAddressControl()!=null && !transferRequest.getMailingAddressControl().isEmpty())
						mailingAddressControl.setValue(transferRequest.getMailingAddressControl());
					else
						mailingAddressControl.setValue("");
					moveOut.setMailingAddressControl(mailingAddressControl);
					
					PhoneNumber phoneNumber = new PhoneNumber();
					if(transferRequest.getOwnerPhoneNumber()!=null && !transferRequest.getOwnerPhoneNumber().isEmpty())
						phoneNumber.setValue(transferRequest.getOwnerPhoneNumber());
					else
						phoneNumber.setValue("");
					moveOut.setPhoneNumber(phoneNumber);
					
					SellingPremise sellingPremise = new SellingPremise();
					if(transferRequest.isSelling())
						sellingPremise.setValue("1");
					else
						sellingPremise.setValue("0");
					moveOut.setSellingPremise(sellingPremise);
					
					LawyerName lawyerName = new LawyerName();
					if(transferRequest.getLawyerName()!=null && !transferRequest.getLawyerName().isEmpty())
						lawyerName.setValue(transferRequest.getLawyerName());
					else
						lawyerName.setValue("");
					moveOut.setLawyerName(lawyerName);
					
					LawyerPhoneNumber lawyerPhoneNumber = new LawyerPhoneNumber();
					if(transferRequest.getLawyerPhone()!=null && !transferRequest.getLawyerPhone().isEmpty())
						lawyerPhoneNumber.setValue(transferRequest.getLawyerPhone());
					else
						lawyerPhoneNumber.setValue("");
					moveOut.setLawyerPhoneNumber(lawyerPhoneNumber);
					
					NewOwnerName newOwnerName = new NewOwnerName();
					if(transferRequest.getOwnerName()!=null && !transferRequest.getOwnerName().isEmpty())
						newOwnerName.setValue(transferRequest.getOwnerName());
					else
						newOwnerName.setValue("");
					moveOut.setNewOwnerName(newOwnerName);
					
					com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.NewOwnerPhoneNumber newOwnerPhoneNumber = new com.londonhydro.sap.model.BusinessProcess9Type.MoveOut.NewOwnerPhoneNumber();
					if(transferRequest.getOwnerPhoneNumber()!=null && !transferRequest.getOwnerPhoneNumber().isEmpty())
						newOwnerPhoneNumber.setValue(transferRequest.getOwnerPhoneNumber());
					else
						newOwnerPhoneNumber.setValue("");
					moveOut.setNewOwnerPhoneNumber(newOwnerPhoneNumber);
					
					MailingAddressType mailingAddressType = new MailingAddressType();
					HouseNumber1 houseNumber1 = new HouseNumber1();
					if(transferRequest.getStreetNumber()!=null && !transferRequest.getStreetNumber().isEmpty())
						houseNumber1.setValue(transferRequest.getStreetNumber());
					else
						houseNumber1.setValue("");
					mailingAddressType.setHouseNumber1(houseNumber1);
					
					StreetName streetName = new StreetName();
					if(transferRequest.getStreetName()!=null && !transferRequest.getStreetName().isEmpty())
						streetName.setValue(transferRequest.getStreetName());
					else
						streetName.setValue("");
					mailingAddressType.setStreetName(streetName);
					
					Unit unit = new Unit();
					if(transferRequest.getStreetUnit()!=null && !transferRequest.getStreetUnit().isEmpty())
						unit.setValue(transferRequest.getStreetUnit());
					else
						unit.setValue("");
					mailingAddressType.setUnit(unit);
					
					City city = new City();
					if(transferRequest.getCity()!=null && !transferRequest.getCity().isEmpty())
						city.setValue(transferRequest.getCity());
					else
						city.setValue("");
					mailingAddressType.setCity(city);
					
					Province province = new Province();
					if(transferRequest.getProvince()!=null && !transferRequest.getProvince().isEmpty())
						province.setValue(transferRequest.getProvince());
					else
						province.setValue("");
					mailingAddressType.setProvince(province);
					
					PostalCode postalCode = new PostalCode();
					if(transferRequest.getPostalCode()!=null && !transferRequest.getPostalCode().isEmpty())
						postalCode.setValue(transferRequest.getPostalCode());
					else
						postalCode.setValue("");
					mailingAddressType.setPostalCode(postalCode);
					
					Country country = new Country();
					if(transferRequest.getCountry()!=null && !transferRequest.getCountry().isEmpty())
						country.setValue(transferRequest.getCountry());
					else
						country.setValue("");
					mailingAddressType.setCountry(country);
					
					NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
					if(transferRequest.getNonCivic1()!=null && !transferRequest.getNonCivic1().isEmpty())
						nonCivicInformation1.setValue(transferRequest.getNonCivic1());
					else
						nonCivicInformation1.setValue("");
					mailingAddressType.setNonCivicInformation1(nonCivicInformation1);
					
					NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
					if(transferRequest.getNonCivic2()!=null && !transferRequest.getNonCivic2().isEmpty())
						nonCivicInformation2.setValue(transferRequest.getNonCivic2());
					else
						nonCivicInformation2.setValue("");
					mailingAddressType.setNonCivicInformation2(nonCivicInformation2);
					
					CONameType coNameType = new CONameType();
					if(transferRequest.getCareOf()!=null && !transferRequest.getCareOf().isEmpty())
						coNameType.setValue(transferRequest.getCareOf());
					else
						coNameType.setValue("");
					mailingAddressType.setCOName(coNameType);
					
					if (mailingAddressType.getCountry() == null || (mailingAddressType.getCountry().getValue() == null
							|| mailingAddressType.getCountry().getValue().trim().equals(""))) {
						country = new Country();
						country.setValue("CA");
						mailingAddressType.setCountry(country);
					}
					
					if (mailingAddressType.getProvince() == null || (mailingAddressType.getProvince().getValue() == null
							|| mailingAddressType.getProvince().getValue().trim().equals(""))) {
						province = new Province();
						province.setValue("ON");
						mailingAddressType.setProvince(province);
					}
					if (mailingAddressType.getCountry().getValue().trim().equals("CA")
							&& mailingAddressType.getPostalCode().getValue().trim().length() == 6) {
						String pc = mailingAddressType.getPostalCode().getValue().trim();
						pc = pc.substring(0, 3) + " " + pc.substring(3);
						postalCode = new PostalCode();
						postalCode.setValue(pc);
						mailingAddressType.setPostalCode(postalCode);
					}
					moveOut.setMailingAddress(mailingAddressType);
					tarnsferInfo.setMoveOut(moveOut);
					
					/****************Move-In*******************/
					
					MoveIn moveIn = new MoveIn();
					Calendar moveInDate = Calendar.getInstance();
					moveInDate.setTime(transferRequest.getMoveOutDate());
					moveInDate.add(Calendar.HOUR_OF_DAY, 4);//Added 4 hrs due to time-zone difference from GMT to EDT
					moveIn.setMoveInDate(moveInDate);

					BusinessProcess9Type.MoveIn.Premise moveInPremise = new BusinessProcess9Type.MoveIn.Premise();
					if (transferRequest.getMoveInPremiseId()!=null && !transferRequest.getMoveInPremiseId().isEmpty())
						moveInPremise.setValue(transferRequest.getMoveInPremiseId());
					else
						moveInPremise.setValue("");
					moveIn.setPremise(moveInPremise);

					BusinessProcess9Type.MoveIn.MailingAddressControl moveInMailingAddressControl = new BusinessProcess9Type.MoveIn.MailingAddressControl();
					if(transferRequest.getMoveInMailingAddressControl() !=null && !transferRequest.getMoveInMailingAddressControl().isEmpty())
						moveInMailingAddressControl.setValue(transferRequest.getMoveInMailingAddressControl());
					else
						moveInMailingAddressControl.setValue("");
					moveIn.setMailingAddressControl(moveInMailingAddressControl);
					
					BuyingPremise buyingPremise = new BuyingPremise();
					if(transferRequest.getMoveInBuying()!=null && !transferRequest.getMoveInBuying().isEmpty())
						buyingPremise.setValue(transferRequest.getMoveInBuying());
					else
						buyingPremise.setValue("");
					moveIn.setBuyingPremise(buyingPremise);
					
					NewOwnerPhoneNumber moveInOwnerPhoneNumber = new NewOwnerPhoneNumber();
					if(transferRequest.getMoveInOwnerPhone()!=null && !transferRequest.getMoveInOwnerPhone().isEmpty())
						moveInOwnerPhoneNumber.setValue(transferRequest.getMoveInOwnerPhone());
					else
						moveInOwnerPhoneNumber.setValue("");
					moveIn.setNewOwnerPhoneNumber(moveInOwnerPhoneNumber);

					BusinessProcess9Type.MoveIn.NewOwnerName moveInNewOwnerName = new BusinessProcess9Type.MoveIn.NewOwnerName();
					if(transferRequest.getMoveInOwnerName()!=null && !transferRequest.getMoveInOwnerName().isEmpty())
						moveInNewOwnerName.setValue(transferRequest.getMoveInOwnerName());
					else
						moveInNewOwnerName.setValue("");
					moveIn.setNewOwnerName(moveInNewOwnerName);
					
					BusinessProcess9Type.MoveIn.LawyerName moveInLawyerName = new BusinessProcess9Type.MoveIn.LawyerName();
					if(transferRequest.getMoveInLawyerName()!=null && !transferRequest.getMoveInLawyerName().isEmpty())
						moveInLawyerName.setValue(transferRequest.getMoveInLawyerName());
					else
						moveInLawyerName.setValue("");
					moveIn.setLawyerName(moveInLawyerName);

					BusinessProcess9Type.MoveIn.LawyerPhoneNumber moveInLawyerPhoneNumber = new BusinessProcess9Type.MoveIn.LawyerPhoneNumber();
					if(transferRequest.getMoveInLawyerPhone()!=null && !transferRequest.getMoveInLawyerPhone().isEmpty())
						moveInLawyerPhoneNumber.setValue(transferRequest.getMoveInLawyerPhone());
					else
						moveInLawyerPhoneNumber.setValue("");
					moveIn.setLawyerPhoneNumber(moveInLawyerPhoneNumber);

					MailingAddressType moveInMailingAddressType = new MailingAddressType();
					HouseNumber1 houseNumber = new HouseNumber1();
					if(transferRequest.getMoveInCustomerAddress().getStreetNumber()!=null && !transferRequest.getMoveInCustomerAddress().getStreetNumber().isEmpty())
						houseNumber.setValue(transferRequest.getMoveInCustomerAddress().getStreetNumber());
					else
						houseNumber.setValue("");
					moveInMailingAddressType.setHouseNumber1(houseNumber);
					
					StreetName moevInStreetName = new StreetName();
					if(transferRequest.getMoveInCustomerAddress().getStreetName()!=null && !transferRequest.getMoveInCustomerAddress().getStreetName().isEmpty())
						moevInStreetName.setValue(transferRequest.getMoveInCustomerAddress().getStreetName());
					else
						moevInStreetName.setValue("");
					moveInMailingAddressType.setStreetName(moevInStreetName);
					
					Unit moveInUnit = new Unit();
					if(transferRequest.getMoveInCustomerAddress().getStreetUnit()!=null && !transferRequest.getMoveInCustomerAddress().getStreetUnit().isEmpty())
						moveInUnit.setValue(transferRequest.getMoveInCustomerAddress().getStreetUnit());
					else
						moveInUnit.setValue("");
					moveInMailingAddressType.setUnit(moveInUnit);
					
					City moveInCity = new City();
					if(transferRequest.getMoveInCustomerAddress().getCity()!=null && !transferRequest.getMoveInCustomerAddress().getCity().isEmpty())
						moveInCity.setValue(transferRequest.getMoveInCustomerAddress().getCity());
					else
						moveInCity.setValue("");
					moveInMailingAddressType.setCity(moveInCity);
					
					Province moveInProvince = new Province();
					if(transferRequest.getMoveInCustomerAddress().getProvince()!=null && !transferRequest.getMoveInCustomerAddress().getProvince().isEmpty())
						moveInProvince.setValue(transferRequest.getMoveInCustomerAddress().getProvince());
					else
						moveInProvince.setValue("");
					moveInMailingAddressType.setProvince(moveInProvince);
						
					PostalCode moevInPostalCode = new PostalCode();
					if(transferRequest.getMoveInCustomerAddress().getPostalCode()!=null && !transferRequest.getMoveInCustomerAddress().getPostalCode().isEmpty())
						moevInPostalCode.setValue(transferRequest.getMoveInCustomerAddress().getPostalCode());
					else
						moevInPostalCode.setValue("");
					moveInMailingAddressType.setPostalCode(moevInPostalCode);
					
					Country moveInCountry = new Country();
					if(transferRequest.getMoveInCustomerAddress().getCountry()!=null && !transferRequest.getMoveInCustomerAddress().getCountry().isEmpty())
						moveInCountry.setValue(transferRequest.getMoveInCustomerAddress().getCountry());
					else
						moveInCountry.setValue("");
					moveInMailingAddressType.setCountry(moveInCountry);
					
					NonCivicInformation1 moveInNonCivicInformation1 = new NonCivicInformation1();
					if(transferRequest.getMoveInCustomerAddress().getStreetSupplemental1()!=null && !transferRequest.getMoveInCustomerAddress().getStreetSupplemental1().isEmpty())
						moveInNonCivicInformation1.setValue(transferRequest.getMoveInCustomerAddress().getStreetSupplemental1());
					else
						moveInNonCivicInformation1.setValue("");
					moveInMailingAddressType.setNonCivicInformation1(moveInNonCivicInformation1);
					
					NonCivicInformation2 moveInNonCivicInformation2 = new NonCivicInformation2();
					if(transferRequest.getMoveInCustomerAddress().getStreetSupplemental2()!=null && !transferRequest.getMoveInCustomerAddress().getStreetSupplemental2().isEmpty())
						moveInNonCivicInformation2.setValue(transferRequest.getMoveInCustomerAddress().getStreetSupplemental2());
					else
						moveInNonCivicInformation2.setValue("");
					moveInMailingAddressType.setNonCivicInformation2(moveInNonCivicInformation2);
				
					CONameType moveInCoNameType = new CONameType();
					if(transferRequest.getMoveInCustomerAddress().getCareOf()!=null && !transferRequest.getMoveInCustomerAddress().getCareOf().isEmpty())
						moveInCoNameType.setValue(transferRequest.getMoveInCustomerAddress().getCareOf());
					else
						moveInCoNameType.setValue("");
						moveInMailingAddressType.setCOName(moveInCoNameType);

					if (moveInMailingAddressType.getCountry() == null || (moveInMailingAddressType.getCountry().getValue() == null
							|| moveInMailingAddressType.getCountry().getValue().trim().equals(""))) {
						moveInCountry.setValue("CA");
						moveInMailingAddressType.setCountry(moveInCountry);
					}

					Province moveInprovince = new Province();
					if (moveInMailingAddressType.getProvince() == null || (moveInMailingAddressType.getProvince().getValue() == null
							|| moveInMailingAddressType.getProvince().getValue().trim().equals(""))) {
						moveInprovince.setValue("ON");
						moveInMailingAddressType.setProvince(moveInprovince);
					}
					if (moveInMailingAddressType.getCountry().getValue().trim().equals("CA")
							&& moveInMailingAddressType.getPostalCode().getValue().trim().length() == 6) {
						String pc = moveInMailingAddressType.getPostalCode().getValue().trim();
						pc = pc.substring(0, 3) + " " + pc.substring(3);
						PostalCode moveInpostalCode = new PostalCode();
						moveInpostalCode.setValue(pc);
						moveInMailingAddressType.setPostalCode(moveInpostalCode);
					}
					moveIn.setMailingAddress(moveInMailingAddressType);
					tarnsferInfo.setMoveIn(moveIn);
					
				}
			}
		sq.getBusinessProcess9().add(tarnsferInfo);
		return sq;
	}

}
