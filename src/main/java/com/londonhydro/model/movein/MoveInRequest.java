package com.londonhydro.model.movein;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import com.londonhydro.model.BaseEntity;
import com.londonhydro.model.Ideable;

public class MoveInRequest extends BaseEntity<Long> implements Ideable<Long> {

	private static final long serialVersionUID = 1L;
	private String moveinState;
	private String premiseId;
	private Long transactionId;
	private Long loginId;
	private String customerId;
	private String accountId;
	private Long moveoutRequestId;
	private Date moveInDate;

	private String mailingAddressControl;
	private String careOf;
	private String nonCivic1;
	private String nonCivic2;
	private String streetNumber;
	private String streetName;
	private String streetUnit;
	private String city;
	private String province;
	private String postalCode;
	private String country;

	private boolean buying;
	private String lawyerName;
	private String lawyerPhone;
	private String ownerName;
	private String ownerPhone;

	private String resiPhone;
	private String bizPhone;
	private String bizPhoneExt;
	private String cellPhoneNumber;
	private String notifyPhoneNumber;
	private boolean person;
	private String profileInfo;
	private String businessContacts;
	private String currentAddress;
	private String previousAddress;

	private boolean mailingAddressChange;
	private boolean reuseExisitngBillNo;
	private String identificationCheck;
	private String creditReportCheck;

	private Date createDate;
	private Date updateDate;
	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isMailingAddressChange() {
		return mailingAddressChange;
	}

	public void setMailingAddressChange(boolean mailingAddressChange) {
		this.mailingAddressChange = mailingAddressChange;
	}

	public boolean isReuseExisitngBillNo() {
		return reuseExisitngBillNo;
	}

	public void setReuseExisitngBillNo(boolean reuseExisitngBillNo) {
		this.reuseExisitngBillNo = reuseExisitngBillNo;
	}

	public boolean isPerson() {
		return person;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(String previousAddress) {
		this.previousAddress = previousAddress;
	}

	public String getResiPhone() {
		return resiPhone;
	}

	public void setResiPhone(String resiPhone) {
		this.resiPhone = resiPhone;
	}

	public String getBizPhone() {
		return bizPhone;
	}

	public void setBizPhone(String bizPhone) {
		this.bizPhone = bizPhone;
	}

	public String getBizPhoneExt() {
		return bizPhoneExt;
	}

	public void setBizPhoneExt(String bizPhoneExt) {
		this.bizPhoneExt = bizPhoneExt;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getNotifyPhoneNumber() {
		return notifyPhoneNumber;
	}

	public void setNotifyPhoneNumber(String notifyPhoneNumber) {
		this.notifyPhoneNumber = notifyPhoneNumber;
	}

	public void setPerson(boolean person) {
		this.person = person;
	}

	public String getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(String profileInfo) {
		this.profileInfo = profileInfo;
	}

	public String getBusinessContacts() {
		return businessContacts;
	}

	public void setBusinessContacts(String businessContacts) {
		this.businessContacts = businessContacts;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getMoveinState() {
		return moveinState;
	}

	public void setMoveinState(String moveInState) {
		this.moveinState = moveInState;
	}

	public Date getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(Date moveInDate) {
		this.moveInDate = moveInDate;
	}

	public String getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(String premiseId) {
		this.premiseId = premiseId;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getMailingAddressControl() {
		return mailingAddressControl;
	}

	public void setMailingAddressControl(String mailingAddressControl) {
		this.mailingAddressControl = mailingAddressControl;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetUnit() {
		return streetUnit;
	}

	public void setStreetUnit(String streetUnit) {
		this.streetUnit = streetUnit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public String getLawyerPhone() {
		return lawyerPhone;
	}

	public void setLawyerPhone(String lawyerPhone) {
		this.lawyerPhone = lawyerPhone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isBuying() {
		return buying;
	}

	public void setBuying(boolean buying) {
		this.buying = buying;
	}

	public Long getMoveoutRequestId() {
		return moveoutRequestId;
	}

	public void setMoveoutRequestId(Long moveoutRequestId) {
		this.moveoutRequestId = moveoutRequestId;
	}

	public String getCareOf() {
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getNonCivic1() {
		return nonCivic1;
	}

	public void setNonCivic1(String nonCivic1) {
		this.nonCivic1 = nonCivic1;
	}

	public String getNonCivic2() {
		return nonCivic2;
	}

	public void setNonCivic2(String nonCivic2) {
		this.nonCivic2 = nonCivic2;
	}

	public String getIdentificationCheck() {
		return identificationCheck;
	}

	public void setIdentificationCheck(String identificationCheck) {
		this.identificationCheck = identificationCheck;
	}

	public String getCreditReportCheck() {
		return creditReportCheck;
	}

	public void setCreditReportCheck(String creditReportCheck) {
		this.creditReportCheck = creditReportCheck;
	}

	public CustomerInfoVO createCustomerInfoVO() {

		mapper = new ObjectMapper();
		CustomerInfoVO customerInfoVO = new CustomerInfoVO();
		if (this.isPerson()) {
			if (this.getProfileInfo() != null) {
				try {
					PersonalInfo personalInfo = mapper.readValue(
							this.getProfileInfo(), PersonalInfo.class);
					customerInfoVO.setFirstName(personalInfo.getFirstName());
					customerInfoVO.setLastName(personalInfo.getLastName());
					customerInfoVO.setBirthDate(personalInfo.getBirthDate());
				} catch (Exception ignore) {
					ignore.printStackTrace(System.err);
				}
			}
			System.err.println("this.getCurrentAddress() "
					+ this.getCurrentAddress());
			if (this.getCurrentAddress() != null) {
				try {
					AddressVO currentAddress = mapper.readValue(
							this.getCurrentAddress(), AddressVO.class);
					customerInfoVO.setCurrentAddress(currentAddress);
				} catch (Exception ignore) {
					ignore.printStackTrace(System.err);
				}
			}

		}
		return customerInfoVO;
	}

	public MoveinRequestVO createMoveinRequestVO() {
		mapper = new ObjectMapper();
		MoveinRequestVO moveinRequestVO = new MoveinRequestVO();

		moveinRequestVO.setMoveInDate(this.getMoveInDate());
		moveinRequestVO.setPremiseId(this.getPremiseId());

		moveinRequestVO.setBuying(this.isBuying());
		moveinRequestVO.setLawyerName(this.getLawyerName());
		moveinRequestVO.setLawyerPhone(this.getLawyerPhone());
		moveinRequestVO.setOwnerName(this.getOwnerName());
		moveinRequestVO.setOwnerPhone(this.getOwnerPhone());

		moveinRequestVO.setMailingAddressControl(this
				.getMailingAddressControl());
		AddressVO mailingAddress = new AddressVO();
		mailingAddress.setCareOf(this.getCareOf());
		mailingAddress.setNonCivic1(this.getNonCivic1());
		mailingAddress.setNonCivic2(this.getNonCivic2());
		mailingAddress.setStreetNumber(this.getStreetNumber());
		mailingAddress.setStreetName(this.getStreetName());
		mailingAddress.setStreetUnit(this.getStreetUnit());
		mailingAddress.setCity(this.getCity());
		mailingAddress.setProvince(this.getProvince());
		mailingAddress.setCountry(this.getCountry());
		mailingAddress.setPostalCode(this.getPostalCode());
		moveinRequestVO.setMailingAddress(mailingAddress);

		moveinRequestVO.setIsPerson(this.isPerson());
		if (this.isPerson()) {
			if (this.getProfileInfo() != null) {
				try {
					PersonalInfo personalInfo = mapper.readValue(
							this.getProfileInfo(), PersonalInfo.class);
					moveinRequestVO.setPersonalInfo(personalInfo);
				} catch (Exception ignore) {
					ignore.printStackTrace(System.err);
				}
			}
			if (this.getCurrentAddress() != null) {
				try {
					AddressVO currentAddress = mapper.readValue(
							this.getCurrentAddress(), AddressVO.class);
					moveinRequestVO.setCurrentAddress(currentAddress);
				} catch (Exception ignore) {
					ignore.printStackTrace(System.err);
				}
			}
			if (this.getPreviousAddress() != null) {
				try {
					AddressVO previousAddress = mapper.readValue(
							this.getPreviousAddress(), AddressVO.class);
					moveinRequestVO.setPreviousAddress(previousAddress);
				} catch (Exception ignore) {
					ignore.printStackTrace(System.err);
				}
			}
			PersonalContact personalContact = new PersonalContact();
			personalContact.setBizPhone(this.getBizPhone());
			personalContact.setBizPhoneExt(this.getBizPhoneExt());
			personalContact.setCellPhoneNumber(this.getCellPhoneNumber());
			personalContact.setNotifyPhoneNumber(this.getNotifyPhoneNumber());
			personalContact.setResiPhone(this.getResiPhone());
			moveinRequestVO.setPersonalContact(personalContact);
		}

		moveinRequestVO.setMailingAddressChange(isMailingAddressChange());
		moveinRequestVO.setReuseExisitngBillNo(isReuseExisitngBillNo());

		return moveinRequestVO;
	}

}
