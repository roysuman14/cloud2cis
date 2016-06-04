package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.model.movein.BusinessContact;
import com.londonhydro.model.movein.BusinessIncorporation;
import com.londonhydro.model.movein.BusinessPrivate;
import com.londonhydro.model.movein.MoveInRequest;
import com.londonhydro.model.movein.PersonalInfo;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess8Type;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData.BPGeneralData;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData.BPGeneralData.BusinessData;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData.BPGeneralData.BusinessData.ContactInfo;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData.BPGeneralData.BusinessData.OwnerInfo;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.CustomerData.BPGeneralData.PersonalData;
import com.londonhydro.sap.model.BusinessProcess8Type.MasterData.ResuseAccountNumber;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.BuyingPremise;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.LawyerName;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.LawyerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.MailingAddressControl;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.OwnerName;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.OwnerPhoneNumber;
import com.londonhydro.sap.model.BusinessProcess8Type.MoveIn.Premise;
import com.londonhydro.sap.model.CONameType;
import com.londonhydro.sap.model.CountryCodeType;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.EmailAddressGroupType;
import com.londonhydro.sap.model.EmailAddressType;
import com.londonhydro.sap.model.EmailCategory;
import com.londonhydro.sap.model.IdentificationType;
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
import com.londonhydro.sap.model.PhoneNumberCategory;
import com.londonhydro.sap.model.PhoneNumberType;
import com.londonhydro.sap.model.PhoneNumberType.Extension;
import com.londonhydro.sap.model.PhoneNumberType.PhoneNumber;
import com.londonhydro.sap.model.ServiceQueue;

public class MoveInProxy {
	protected static final Log logger = LogFactory.getLog(MoveInProxy.class);

	@SuppressWarnings("unused")
	public ServiceQueue marshal(List<MoveInRequest> moveInRequestList)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		Calendar systemTime = Calendar.getInstance();
		Date today = new Date();
		systemTime.setTime(today);
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess8Type moveInInfo = new BusinessProcess8Type();
		moveInInfo.setProcessID(Long.toString((moveInRequestList.get(0)
				.getTransactionId())));
		moveInInfo.setType(QueueTypes.MoveIn.toString());
		moveInInfo.setDateTime(systemTime);
		moveInInfo.setSubType("New");

		for (MoveInRequest moveInRequest : moveInRequestList) {

			logger.info(String.format(
					"Processing move-in request # %d isPerson: %s ",
					moveInRequest.getId(), moveInRequest.isPerson()));

			MoveIn moveIn = new MoveIn();

			// move-in premise & date
			Premise premise = new Premise();
			premise.setValue(moveInRequest.getPremiseId());
			moveIn.setPremise(premise);

			moveIn.setMoveInDate(dateToCalendar(moveInRequest.getMoveInDate()));

			// previous owner/laywer details
			BuyingPremise buyingPremise = new BuyingPremise();
			moveIn.setBuyingPremise(buyingPremise);
			buyingPremise.setValue(moveInRequest.isBuying() ? "Y" : "N");

			OwnerName ownerName = new OwnerName();
			ownerName.setValue(sanitizeString(moveInRequest.getOwnerName()));
			moveIn.setOwnerName(ownerName);

			OwnerPhoneNumber ownerPhoneNumber = new OwnerPhoneNumber();
			ownerPhoneNumber.setValue(sanitizeString(moveInRequest
					.getOwnerPhone()));
			moveIn.setOwnerPhoneNumber(ownerPhoneNumber);

			LawyerName lawyerName = new LawyerName();
			lawyerName.setValue(sanitizeString(moveInRequest.getLawyerName()));
			moveIn.setLawyerName(lawyerName);

			LawyerPhoneNumber lawyerPhoneNumber = new LawyerPhoneNumber();
			lawyerPhoneNumber.setValue(sanitizeString(moveInRequest
					.getLawyerPhone()));
			moveIn.setLawyerPhoneNumber(lawyerPhoneNumber);

			// mailing address & control flag
			MailingAddressControl mac = new MailingAddressControl();
			mac.setValue(sanitizeString(moveInRequest
					.getMailingAddressControl()));
			moveIn.setMailingAddressControl(mac);

			MailingAddressType mailingAddress = new MailingAddressType();
			moveIn.setMailingAddress(mailingAddress);

			CONameType coNameType = new CONameType();
			coNameType.setValue(sanitizeString(moveInRequest.getCareOf()));
			mailingAddress.setCOName(coNameType);

			NonCivicInformation1 nonCivicInformation1 = new NonCivicInformation1();
			nonCivicInformation1.setValue(sanitizeString(moveInRequest
					.getNonCivic1()));
			mailingAddress.setNonCivicInformation1(nonCivicInformation1);

			NonCivicInformation2 nonCivicInformation2 = new NonCivicInformation2();
			nonCivicInformation2.setValue(sanitizeString(moveInRequest
					.getNonCivic2()));
			mailingAddress.setNonCivicInformation2(nonCivicInformation2);

			HouseNumber1 houseNumber1 = new HouseNumber1();
			houseNumber1.setValue(sanitizeString(moveInRequest
					.getStreetNumber()));
			mailingAddress.setHouseNumber1(houseNumber1);

			StreetName streetName = new StreetName();
			streetName.setValue(sanitizeString(moveInRequest.getStreetName()));
			mailingAddress.setStreetName(streetName);

			Unit unit = new Unit();
			unit.setValue(sanitizeString(moveInRequest.getStreetUnit()));
			mailingAddress.setUnit(unit);

			City city = new City();
			city.setValue(sanitizeString(moveInRequest.getCity()));
			mailingAddress.setCity(city);

			Province province = new Province();
			province.setValue(sanitizeString(moveInRequest.getProvince()));
			mailingAddress.setProvince(province);

			PostalCode postalCode = new PostalCode();
			postalCode.setValue(sanitizeStringRemoveWS(moveInRequest
					.getPostalCode()));
			mailingAddress.setPostalCode(postalCode);

			Country country = new Country();
			country.setValue(sanitizeString(moveInRequest.getCountry()));
			mailingAddress.setCountry(country);

			// customer personal/business info
			MasterData masterData = new MasterData();

			CustomerNumberType customerNumberType = new CustomerNumberType();
			customerNumberType.setValue(sanitizeString(moveInRequest
					.getCustomerId()));
			masterData.setCustomerNumber(customerNumberType);

			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
			billingAccountNumber.setValue(sanitizeString(moveInRequest
					.getAccountId()));
			masterData.setBillingAccountNumber(billingAccountNumber);

			ResuseAccountNumber resuseAccountNumber = new ResuseAccountNumber();
			resuseAccountNumber
					.setValue(moveInRequest.isReuseExisitngBillNo() ? "YES"
							: "NO");
			masterData.setResuseAccountNumber(resuseAccountNumber);

			CustomerData customerData = new CustomerData();
			BPGeneralData bpGeneralData = new BPGeneralData();

			// bpGeneralData.setCustomerType(moveInRequest.isPerson() ? "P" :
			// "O");
			bpGeneralData.setCustomerType(2);
			// for new move-in
			if (false) {
				EmailAddressGroupType emailAddressGroupType = new EmailAddressGroupType();
				emailAddressGroupType.getEmailAddress().setValue(
						"emailAdd@emailAdd.com");
				emailAddressGroupType.setEffectiveDate(systemTime);
				EmailCategory emailCategory = new EmailCategory();
				emailCategory.setValue("PRIMARY EMAIL");
				emailAddressGroupType.setRemarks(emailCategory);
				customerData.getEmailAddresses().add(emailAddressGroupType);
			}

			if (moveInRequest.isPerson()) {
				PersonalInfo personalInfo = mapper.readValue(
						moveInRequest.getProfileInfo(), PersonalInfo.class);

				PersonalData personalDataType = new PersonalData();
				bpGeneralData.setPersonalData(personalDataType);

				personalDataType.setFirstName(personalInfo.getFirstName());
				personalDataType.setLastName(personalInfo.getLastName());
				personalDataType.setMiddleName(personalInfo.getMiddleName());

				Calendar dob = Calendar.getInstance();
				dob.setTime(personalInfo.getBirthDate());
				personalDataType.setDateOfBirth(dob);

				personalDataType.setEmployerName(sanitizeString(personalInfo
						.getEmployerName()));
				personalDataType.setEmployerAddress(sanitizeString(personalInfo
						.getEmployerAddress()));

				// additional student information
				if (personalInfo.isStudent()) {

					// to be check
					// personalDataType.setParentName(sanitizeString(personalInfo
					// .getStudentInfo().getStudentId()));

					personalDataType.setSchool(sanitizeString(personalInfo
							.getStudentInfo().getSchoolName()));
					personalDataType.setParentName(sanitizeString(personalInfo
							.getStudentInfo().getParentName()));
					personalDataType
							.setParentPhoneNumber(sanitizeString(personalInfo
									.getStudentInfo().getParentPhone()));
					personalDataType
							.setParentAddress(sanitizeString(personalInfo
									.getStudentInfo().getParentAddress()));
				}

				if (!isEmpty(personalInfo.getPersonalIdType())) {
					IdentificationType identificationType1 = new IdentificationType();
					identificationType1.setIDType(personalInfo
							.getPersonalIdType());
					identificationType1.setIdentificationNumber(personalInfo
							.getPersonalId());
					identificationType1.setEffectiveDate(systemTime);
					customerData.getIdentification().add(identificationType1);
				}
				if (!isEmpty(personalInfo.getPhotoIdType())) {
					IdentificationType identificationType = new IdentificationType();
					identificationType.setIDType(personalInfo.getPhotoIdType());
					identificationType.setIdentificationNumber(personalInfo
							.getPhotoIdType());
					identificationType.setEffectiveDate(systemTime);
					customerData.getIdentification().add(identificationType);
				}

				// customer phone numbers
				if (!isEmpty(moveInRequest.getResiPhone())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber("RESIDENTIAL",
									moveInRequest.getResiPhone(), null,
									systemTime));
				}

				if (!isEmpty(moveInRequest.getCellPhoneNumber())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber("MOBILE",
									moveInRequest.getCellPhoneNumber(), null,
									systemTime));
				}
				if (!isEmpty(moveInRequest.getBizPhone())) {
					customerData
							.getPhoneNumbers()
							.add(createPhoneNumber("WORK",
									moveInRequest.getBizPhone(),
									moveInRequest.getBizPhoneExt(), systemTime));
				}
				if (!isEmpty(moveInRequest.getNotifyPhoneNumber())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber("NOTIFICATION",
									moveInRequest.getNotifyPhoneNumber(), null,
									systemTime));
				}
				// customer present/past address
				// TO BE checked
			} else {
				// business customer data
				BusinessData businessData = new BusinessData();
				bpGeneralData.setBusinessData(businessData);

				BusinessIncorporation businessIncorporation = null;
				BusinessPrivate businessPrivate = null;
				try {
					businessIncorporation = mapper.readValue(
							moveInRequest.getProfileInfo(),
							BusinessIncorporation.class);
				} catch (Exception ex) {
					// try for private
					businessPrivate = mapper.readValue(
							moveInRequest.getProfileInfo(),
							BusinessPrivate.class);
				}
				if (businessIncorporation != null) {
					businessData.setBusinessType("Incorporated");

					businessData.setCompanyName(businessIncorporation
							.getLegalName());
					businessData.setTradingName(businessIncorporation
							.getOperatingName());
					businessData.setWebsite(businessIncorporation.getWebsite());

				} else if (businessPrivate != null) {
					businessData.setBusinessType("Private");

					OwnerInfo ownerInfo = new OwnerInfo();
					ownerInfo.setOwnerName(sanitizeString(businessPrivate
							.getOwnerName()));
					ownerInfo.setDateOfBirth(dateToCalendar(businessPrivate
							.getBirthDate()));
					ownerInfo.setPhoneNumber(sanitizeString(businessPrivate
							.getPhoneNumber()));
					businessData.getOwnerInfo().add(ownerInfo);

					ownerInfo.setDriverLicenceNumber(businessPrivate
							.getDriverLicense());
					EmailAddressType eat = new EmailAddressType();
					eat.setValue(sanitizeString(businessPrivate.getEmail()));
					ownerInfo.setEmailAddress(eat);

					ownerInfo.setPosition(businessPrivate.getPosition());

				}
				
				System.out.println("moveInRequest.getBusinessContacts() "+moveInRequest.getBusinessContacts());

				// business contact
				List<BusinessContact> bizContacts = mapper.readValue(
						moveInRequest.getBusinessContacts(),
						mapper.getTypeFactory().constructCollectionType(
								List.class, BusinessContact.class));
				if (bizContacts != null && !bizContacts.isEmpty()) {
					for (BusinessContact bc : bizContacts) {

						ContactInfo contactInfo = new ContactInfo();

						EmailAddressType emailAddressType = new EmailAddressType();
						emailAddressType
								.setValue(sanitizeString(bc.getEmail()));
						contactInfo.setEmailAddress(emailAddressType);
						contactInfo.setContactName(bc.getName());
						contactInfo.setPhoneNumber(bc.getPhoneNumber());
						// DJ contactInfo.setPostion(bc.getPosition());

						businessData.getContactInfo().add(contactInfo);						
					}
				}
			}

			customerData.setBPGeneralData(bpGeneralData);
			masterData.setCustomerData(customerData);

			moveInInfo.setMasterData(masterData);
			moveInInfo.setMoveIn(moveIn);
		}
		sq.getBusinessProcess8().add(moveInInfo);
		return sq;
	}

	private static PhoneNumberType createPhoneNumber(String category,
			String phone, String ext, Calendar effDate) {
		PhoneNumberType phoneNumberType = new PhoneNumberType();

		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setValue(phone);
		phoneNumberType.setPhoneNumber(phoneNumber);

		if (!isEmpty(ext)) {
			Extension extension = new Extension();
			extension.setValue(ext);
			// phoneNumberType.setExtension(extension);
		}

		PhoneNumberCategory phoneNumberCategory = new PhoneNumberCategory();
		phoneNumberCategory.setValue(category);
		phoneNumberType.setRemarks(phoneNumberCategory);
		CountryCodeType countryCodeType = new CountryCodeType();
		countryCodeType.setValue("CA");
		phoneNumberType.setCountryCode(countryCodeType);
		phoneNumberType.setEffectiveDate(effDate);
		return phoneNumberType;
	}

	private static String sanitizeStringRemoveWS(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		return s.replaceAll("\\s+", "");

	}

	private static boolean isEmpty(String s) {
		return (s == null || s.isEmpty());
	}

	private static String sanitizeString(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		return s;
	}

	private static Calendar dateToCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

}