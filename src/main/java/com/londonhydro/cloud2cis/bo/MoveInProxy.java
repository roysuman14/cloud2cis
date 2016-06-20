package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.util.DataUtil;
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
import com.londonhydro.utils.Constants;
import com.londonhydro.utils.IdentificationConstant;
import com.londonhydro.utils.MoveInConstant;
import com.londonhydro.utils.PhoneNumberConstant;

public class MoveInProxy {
	protected static final Log logger = LogFactory.getLog(MoveInProxy.class);

	@SuppressWarnings("unused")
	public ServiceQueue marshal(List<MoveInRequest> moveInRequestList)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		// today
		Calendar today = Calendar.getInstance();

		ServiceQueue sq = new ServiceQueue();

		BusinessProcess8Type moveInInfo = new BusinessProcess8Type();

		// header information
		moveInInfo.setProcessID(Long.toString((moveInRequestList.get(0)
				.getTransactionId())));
		moveInInfo.setType(QueueTypes.MoveIn.toString());
		moveInInfo.setDateTime(today);
		moveInInfo.setSubType("0".equals(moveInRequestList.get(0)
				.getCustomerId()) ? Constants.BP_SUB_TYPE_NEW : Constants.BP_SUB_TYPE_EXISTING);

		for (MoveInRequest moveInRequest : moveInRequestList) {

			logger.info(String.format(
					"Processing move-in request # %d isPerson: %s ",
					moveInRequest.getId(), moveInRequest.isPerson()));

			MoveIn moveIn = new MoveIn();

			// move-in premise & date
			Premise premise = new Premise();
			premise.setValue(moveInRequest.getPremiseId());
			moveIn.setPremise(premise);

			moveIn.setMoveInDate(DataUtil.dateToCalendar(moveInRequest
					.getMoveInDate()));

			// previous owner/laywer details
			BuyingPremise buyingPremise = new BuyingPremise();
			moveIn.setBuyingPremise(buyingPremise);
			buyingPremise.setValue(moveInRequest.isBuying() ? Constants.YN_CODE_Y : Constants.YN_CODE_N);

			OwnerName ownerName = new OwnerName();
			ownerName.setValue(DataUtil.sanitizeString(moveInRequest
					.getOwnerName()));
			moveIn.setOwnerName(ownerName);

			OwnerPhoneNumber ownerPhoneNumber = new OwnerPhoneNumber();
			ownerPhoneNumber.setValue(DataUtil.sanitizeString(moveInRequest
					.getOwnerPhone()));
			moveIn.setOwnerPhoneNumber(ownerPhoneNumber);

			LawyerName lawyerName = new LawyerName();
			lawyerName.setValue(DataUtil.sanitizeString(moveInRequest
					.getLawyerName()));
			moveIn.setLawyerName(lawyerName);

			LawyerPhoneNumber lawyerPhoneNumber = new LawyerPhoneNumber();
			lawyerPhoneNumber.setValue(DataUtil.sanitizeString(moveInRequest
					.getLawyerPhone()));
			moveIn.setLawyerPhoneNumber(lawyerPhoneNumber);

			// mailing address & control flag
			MailingAddressControl mac = new MailingAddressControl();
			mac.setValue(DataUtil.sanitizeString(moveInRequest
					.getMailingAddressControl()));
			moveIn.setMailingAddressControl(mac);

			MailingAddressType mailingAddress = new MailingAddressType();
			moveIn.setMailingAddress(mailingAddress);

			CONameType coNameType = new CONameType();
			coNameType.setValue(DataUtil.sanitizeString(moveInRequest
					.getCareOf()));
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
			province.setValue(DataUtil.sanitizeString(moveInRequest
					.getProvince()));
			mailingAddress.setProvince(province);

			PostalCode postalCode = new PostalCode();
			postalCode.setValue(DataUtil.sanitizeString(moveInRequest
					.getPostalCode()));
			mailingAddress.setPostalCode(postalCode);

			Country country = new Country();
			country.setValue(DataUtil.sanitizeString(moveInRequest.getCountry()));
			mailingAddress.setCountry(country);

			// customer personal/business info
			MasterData masterData = new MasterData();

			CustomerNumberType customerNumberType = new CustomerNumberType();
			customerNumberType.setValue(DataUtil.sanitizeString(moveInRequest
					.getCustomerId()));
			masterData.setCustomerNumber(customerNumberType);

			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
			billingAccountNumber.setValue(DataUtil.sanitizeString(moveInRequest
					.getAccountId()));
			masterData.setBillingAccountNumber(billingAccountNumber);

			ResuseAccountNumber resuseAccountNumber = new ResuseAccountNumber();
			resuseAccountNumber
					.setValue(moveInRequest.isReuseExisitngBillNo() ? Constants.YN_CODE_YES
							: Constants.YN_CODE_NO);
			masterData.setResuseAccountNumber(resuseAccountNumber);

			CustomerData customerData = new CustomerData();
			BPGeneralData bpGeneralData = new BPGeneralData();

			// 1-person, 2 =organization
			bpGeneralData.setCustomerType(moveInRequest.isPerson() ? MoveInConstant.MOVEIN_CUSTOMER_TYPE_PERSON : MoveInConstant
					.MOVEIN_CUSTOMER_TYPE_ORGANIZATION);

			// for new move-in
			if (false) {
				EmailAddressGroupType emailAddressGroupType = new EmailAddressGroupType();
				emailAddressGroupType.getEmailAddress().setValue("tobechecked@email.com");
				emailAddressGroupType.setEffectiveDate(today);
				EmailCategory emailCategory = new EmailCategory();
				emailCategory.setValue(MoveInConstant.MOVEIN_NEW_EMAIL_CATEGORY);
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

				personalDataType.setEmployerName(DataUtil
						.sanitizeString(personalInfo.getEmployerName()));
				personalDataType.setEmployerAddress(DataUtil
						.sanitizeString(personalInfo.getEmployerAddress()));

				// additional student information
				if (personalInfo.isStudent()) {

					personalDataType.setSchoolName(DataUtil
							.sanitizeString(personalInfo.getStudentInfo()
									.getSchoolName()));
					personalDataType.setParentName(DataUtil
							.sanitizeString(personalInfo.getStudentInfo()
									.getParentName()));
					personalDataType.setParentAddress(DataUtil
							.sanitizeString(personalInfo.getStudentInfo()
									.getParentAddress()));

					// student id
					if (!DataUtil.isEmpty(personalInfo.getStudentInfo()
							.getStudentId())) {
						IdentificationType studentId = new IdentificationType();
						// ****** CHECK
						studentId.setIDType(MoveInConstant.MOVEIN_ID_TYPE_STUDENT);
						studentId.setIdentificationNumber(personalInfo
								.getStudentInfo().getStudentId());
						studentId.setEffectiveDate(today);
						customerData.getIdentification().add(studentId);
					}
				}

				if (!DataUtil.isEmpty(personalInfo.getPersonalIdType())) {
					IdentificationType identificationType1 = new IdentificationType();
					identificationType1.setIDType(personalInfo
							.getPersonalIdType());
					identificationType1.setIdentificationNumber(personalInfo
							.getPersonalId());
					identificationType1.setEffectiveDate(today);
					customerData.getIdentification().add(identificationType1);
				}
				if (!DataUtil.isEmpty(personalInfo.getPhotoIdType())) {
					IdentificationType identificationType = new IdentificationType();
					identificationType.setIDType(personalInfo.getPhotoIdType());
					identificationType.setIdentificationNumber(personalInfo
							.getPhotoIdType());
					identificationType.setEffectiveDate(today);
					customerData.getIdentification().add(identificationType);
				}

				// customer phone numbers
				if (!DataUtil.isEmpty(moveInRequest.getResiPhone())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber(PhoneNumberConstant.RESIDENTIAL,
									moveInRequest.getResiPhone(), null, today));
				}

				if (!DataUtil.isEmpty(moveInRequest.getCellPhoneNumber())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber(PhoneNumberConstant.MOBILE,
									moveInRequest.getCellPhoneNumber(), null,
									today));
				}
				if (!DataUtil.isEmpty(moveInRequest.getBizPhone())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber(PhoneNumberConstant.BUSINESS,
									moveInRequest.getBizPhone(),
									moveInRequest.getBizPhoneExt(), today));
				}
				if (!DataUtil.isEmpty(moveInRequest.getNotifyPhoneNumber())) {
					customerData.getPhoneNumbers().add(
							createPhoneNumber(PhoneNumberConstant.NOTIFICATION,
									moveInRequest.getNotifyPhoneNumber(), null,
									today));
				}
				// for student, parent phone will be store as PARENT
				if (personalInfo.isStudent()) {
					if (!DataUtil.isEmpty(personalInfo.getStudentInfo()
							.getParentPhone())) {
						customerData
								.getPhoneNumbers()
								.add(createPhoneNumber(
										PhoneNumberConstant.PARENT,
										personalInfo.getStudentInfo()
												.getParentPhone(), null, today));
					}
				}

				// customer present/past address
				personalDataType.setCurrentAddress(moveInRequest
						.getCurrentAddress());
				personalDataType.setPreviosuAddress(moveInRequest
						.getPreviousAddress());
			} else {
				// business customer data
				BusinessData businessData = new BusinessData();
				bpGeneralData.setBusinessData(businessData);

				BusinessIncorporation businessIncorporation = null;
				BusinessPrivate businessPrivate = null;
				if (moveInRequest.getProfileInfo() != null) {
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
				}
				if (businessIncorporation != null) {
					businessData.setBusinessCategory(MoveInConstant.MOVEIN_BUSINESS_CATEGORY_INC);

					businessData.setCompanyName(businessIncorporation
							.getLegalName());
					businessData.setTradingName(businessIncorporation
							.getOperatingName());
					businessData.setWebsite(businessIncorporation.getWebsite());
					businessData.setBusinessType(businessIncorporation
							.getBusinessType());
					EmailAddressType eat = new EmailAddressType();
					eat.setValue(DataUtil.sanitizeString(businessIncorporation
							.getEmail()));
					businessData.setEmailAddress(eat);

					// business identity
					// **** CHECK ID type value
					if (!DataUtil.isEmpty(businessIncorporation
							.getRegistrationNumber())) {
						IdentificationType identification = new IdentificationType();
						identification
								.setIDType(IdentificationConstant.REGISTRATION_NUMBER);
						identification
								.setIdentificationNumber(businessIncorporation
										.getRegistrationNumber());
						identification.setEffectiveDate(today);
						customerData.getIdentification().add(identification);
					}
					if (!DataUtil.isEmpty(businessIncorporation
							.getCorporationNumber())) {
						IdentificationType identification = new IdentificationType();
						identification
								.setIDType(IdentificationConstant.CORPORATION_NUMBER);
						identification
								.setIdentificationNumber(businessIncorporation
										.getCorporationNumber());
						identification.setEffectiveDate(today);
						customerData.getIdentification().add(identification);
					}

					// business phone numbers
					if (!DataUtil.isEmpty(businessIncorporation
							.getBusinessPhone())) {
						customerData.getPhoneNumbers().add(
								createPhoneNumber(PhoneNumberConstant.BUSINESS,
										businessIncorporation
												.getBusinessPhone(),
										businessIncorporation.getBusinessExt(),
										today));
					}
					if (!DataUtil.isEmpty(businessIncorporation
							.getNotificationPhone())) {
						customerData.getPhoneNumbers().add(
								createPhoneNumber(
										PhoneNumberConstant.NOTIFICATION,
										businessIncorporation
												.getNotificationPhone(), null,
										today));
					}
					if (!DataUtil.isEmpty(businessIncorporation.getFaxNumber())) {
						customerData.getPhoneNumbers().add(
								createPhoneNumber(PhoneNumberConstant.FAX,
										businessIncorporation.getFaxNumber(),
										null, today));
					}

				} else if (businessPrivate != null) {
					businessData.setBusinessCategory(MoveInConstant.MOVEIN_BUSINESS_CATEGORY_PVT);

					// business info
					businessData.setCompanyName(businessPrivate
							.getBusinessName());
					EmailAddressType eat = new EmailAddressType();
					eat.setValue(DataUtil.sanitizeString(businessPrivate
							.getEmail()));
					businessData.setEmailAddress(eat);

					// business identification
					if (!DataUtil.isEmpty(businessPrivate.getBusinessLicense())) {
						IdentificationType identification = new IdentificationType();
						identification.setIDType(MoveInConstant.MOVEIN_BUSINESS_ID);
						identification.setIdentificationNumber(businessPrivate
								.getBusinessLicense());
						identification.setEffectiveDate(today);
						customerData.getIdentification().add(identification);
					}

					// business primary owner info
					OwnerInfo ownerInfo = new OwnerInfo();
					ownerInfo.setOwnerName(DataUtil
							.sanitizeString(businessPrivate.getOwnerName()));
					ownerInfo.setDateOfBirth(DataUtil
							.dateToCalendar(businessPrivate.getBirthDate()));

					businessData.getOwnerInfo().add(ownerInfo);

					ownerInfo.setDriverLicenceNumber(businessPrivate
							.getDriverLicense());

					ownerInfo.setPosition(businessPrivate.getPosition());

					// business phone numbers
					if (!DataUtil.isEmpty(businessPrivate.getBusinessPhone())) {
						customerData
								.getPhoneNumbers()
								.add(createPhoneNumber(
										PhoneNumberConstant.BUSINESS,
										businessPrivate.getBusinessPhone(),
										businessPrivate.getBusinessExt(), today));
					}
					if (!DataUtil.isEmpty(businessPrivate
							.getNotificationPhone())) {
						customerData.getPhoneNumbers().add(
								createPhoneNumber(
										PhoneNumberConstant.NOTIFICATION,
										businessPrivate.getNotificationPhone(),
										null, today));
					}
					if (!DataUtil.isEmpty(businessPrivate.getFaxNumber())) {
						customerData.getPhoneNumbers().add(
								createPhoneNumber(PhoneNumberConstant.FAX,
										businessPrivate.getFaxNumber(), null,
										today));
					}

				}

				// business contact
				if (moveInRequest.getBusinessContacts() != null) {
					List<BusinessContact> bizContacts = mapper.readValue(
							moveInRequest.getBusinessContacts(),
							mapper.getTypeFactory().constructCollectionType(
									List.class, BusinessContact.class));
					if (bizContacts != null && !bizContacts.isEmpty()) {
						for (BusinessContact bc : bizContacts) {

							ContactInfo contactInfo = new ContactInfo();

							EmailAddressType emailAddressType = new EmailAddressType();
							emailAddressType.setValue(DataUtil
									.sanitizeString(bc.getEmail()));
							contactInfo.setContactName(bc.getName());
							contactInfo.setPosition(bc.getPosition());
							contactInfo.setPhoneNumber(bc.getPhoneNumber());
							contactInfo.setEmailAddress(emailAddressType);

							businessData.getContactInfo().add(contactInfo);
						}
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

		if (!DataUtil.isEmpty(ext)) {
			Extension extension = new Extension();
			extension.setValue(ext);
			// phoneNumberType.setExtension(extension);
		}

		PhoneNumberCategory phoneNumberCategory = new PhoneNumberCategory();
		phoneNumberCategory.setValue(category);
		phoneNumberType.setRemarks(phoneNumberCategory);
		CountryCodeType countryCodeType = new CountryCodeType();
		countryCodeType.setValue(MoveInConstant.MOVEIN_COUNTRY_CODE);
		phoneNumberType.setCountryCode(countryCodeType);
		phoneNumberType.setEffectiveDate(effDate);
		return phoneNumberType;
	}

}
