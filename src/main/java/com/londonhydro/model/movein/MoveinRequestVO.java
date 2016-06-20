package com.londonhydro.model.movein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class MoveinRequestVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("accountId")
	private String accountId;
	@JsonProperty("moveinDate")
	private Date moveInDate;
	@JsonProperty("moveoutRequestId")
	private String moveoutRequestId;
	@JsonProperty("premiseId")
	private String premiseId;

	@JsonProperty("isBuying")
	private boolean buying;
	@JsonProperty("lawyerName")
	private String lawyerName;
	@JsonProperty("lawyerPhone")
	private String lawyerPhone;
	@JsonProperty("ownerName")
	private String ownerName;
	@JsonProperty("ownerPhone")
	private String ownerPhone;

	@JsonProperty("isPerson")
	private boolean isPerson;
	@JsonProperty("mailingAddressControl")
	private String mailingAddressControl;
	@JsonProperty("mailingAddress")
	private AddressVO mailingAddress;

	@JsonProperty("personalInfo")
	private PersonalInfo personalInfo;
	@JsonProperty("personalContact")
	private PersonalContact personalContact;
	@JsonProperty("currentAddress")
	private AddressVO currentAddress;
	@JsonProperty("previousAddress")
	private AddressVO previousAddress;
	@JsonProperty("isIncorporated")
	private boolean incorporated;
	@JsonProperty("businessIncorp")
	private BusinessIncorporation businessIncorporation;
	@JsonProperty("businessPrivate")
	private BusinessPrivate businessPrivate;
	@JsonProperty("businessContacts")
	private List<BusinessContact> businessContacts;

	@JsonProperty("mailingAddressChange")
	private boolean mailingAddressChange;
	@JsonProperty("reuseExisitngBillNo")
	private boolean reuseExisitngBillNo;

	public PersonalContact getPersonalContact() {
		return personalContact;
	}

	public void setPersonalContact(PersonalContact personalContact) {
		this.personalContact = personalContact;
	}

	public AddressVO getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(AddressVO currentAddress) {
		this.currentAddress = currentAddress;
	}

	public AddressVO getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(AddressVO previousAddress) {
		this.previousAddress = previousAddress;
	}

	public String getAccountId() {
		if (accountId == null)
			return accountId;
		else if (accountId.equals(""))
			this.accountId = null;
		return accountId;
	}

	public String getLawyerName() {
		if (lawyerName != null && !lawyerName.equals("")) {
			lawyerName = lawyerName.toUpperCase();
		}
		return lawyerName;
	}

	public String getLawyerPhone() {
		return lawyerPhone;
	}

	public AddressVO getMailingAddress() {
		return mailingAddress;
	}

	public String getMailingAddressControl() {
		return mailingAddressControl;
	}

	public Date getMoveInDate() {
		return moveInDate;
	}

	public Long getMoveoutId() {

		if (moveoutRequestId != null)
			return Long.valueOf(moveoutRequestId);
		else {
			moveoutRequestId = "0";
			return Long.valueOf(moveoutRequestId);
		}
	}

	public String getMoveoutRequestId() {
		return moveoutRequestId;
	}

	public String getOwnerName() {
		if (ownerName != null && !ownerName.equals("")) {
			ownerName = ownerName.toUpperCase();
		}
		return ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;

	}

	public String getPremiseId() {
		return premiseId;
	}

	public boolean isBuying() {
		return buying;
	}

	public boolean isMailingAddressChange() {
		return mailingAddressChange;
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public boolean isPerson() {
		return isPerson;
	}

	public boolean isReuseExisitngBillNo() {
		return reuseExisitngBillNo;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setBuying(boolean buying) {
		this.buying = buying;
	}

	public void setIsPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public void setLawyerPhone(String lawyerPhone) {
		this.lawyerPhone = lawyerPhone;
	}

	public void setMailingAddress(AddressVO mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public void setMailingAddressChange(boolean mailingAddressChange) {
		this.mailingAddressChange = mailingAddressChange;
	}

	public void setMailingAddressControl(String mailingAddressControl) {
		this.mailingAddressControl = mailingAddressControl;
	}

	public void setMoveInDate(Date moveInDate) {
		this.moveInDate = moveInDate;
	}

	public void setMoveoutRequestId(String moveoutRequestId) {
		this.moveoutRequestId = moveoutRequestId;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public void setPremiseId(String premiseId) {
		this.premiseId = premiseId;
	}

	public void setReuseExisitngBillNo(boolean reuseExisitngBillNo) {
		this.reuseExisitngBillNo = reuseExisitngBillNo;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public boolean isIncorporated() {
		return incorporated;
	}

	public void setIncorporated(boolean incorporated) {
		this.incorporated = incorporated;
	}

	public BusinessIncorporation getBusinessIncorporation() {
		return businessIncorporation;
	}

	public void setBusinessIncorporation(BusinessIncorporation businessIncorp) {
		this.businessIncorporation = businessIncorp;
	}

	public BusinessPrivate getBusinessPrivate() {
		return businessPrivate;
	}

	public void setBusinessPrivate(BusinessPrivate businessPrivate) {
		this.businessPrivate = businessPrivate;
	}

	public List<BusinessContact> getBusinessContacts() {
		return businessContacts;
	}

	public void setBusinessContacts(List<BusinessContact> businessContacts) {
		this.businessContacts = businessContacts;
	}

	public MoveInRequest createMoveinReqest() {
		MoveInRequest moveInRequest = new MoveInRequest();

		moveInRequest.setMoveInDate(this.moveInDate);
		moveInRequest.setMoveoutRequestId(this.getMoveoutId());

		moveInRequest.setBuying(this.isBuying());
		moveInRequest.setLawyerName(this.getLawyerName());
		moveInRequest.setLawyerPhone(this.getLawyerPhone());
		moveInRequest.setOwnerName(this.getOwnerName());
		moveInRequest.setOwnerPhone(this.getOwnerPhone());

		moveInRequest.setMailingAddressControl(this.getMailingAddressControl());
		if (this.getMailingAddress() != null) {
			moveInRequest.setCareOf(this.getMailingAddress().getCareOf());
			moveInRequest.setNonCivic1(this.getMailingAddress().getNonCivic1());
			moveInRequest.setNonCivic2(this.getMailingAddress().getNonCivic2());
			moveInRequest.setStreetNumber(this.getMailingAddress().getStreetNumber());
			moveInRequest.setStreetName(this.getMailingAddress().getStreetName());
			moveInRequest.setStreetUnit(this.getMailingAddress().getStreetUnit());
			moveInRequest.setCity(this.getMailingAddress().getCity());
			moveInRequest.setProvince(this.getMailingAddress().getProvince());
			moveInRequest.setCountry(this.getMailingAddress().getCountry());
			moveInRequest.setPostalCode(this.getMailingAddress().getPostalCode());
		}

		moveInRequest.setPerson(this.isPerson());

		try {
			if (this.isPerson() && getPersonalInfo() != null) {
				String personalInfo = this.getPersonalInfo().toString();
				moveInRequest.setProfileInfo(personalInfo);
			} else if (!this.isPerson() && this.isIncorporated()) {
				String bizIncorporated = this.getPersonalInfo().toString();
				moveInRequest.setProfileInfo(bizIncorporated);
			} else if (!this.isPerson() && !this.isIncorporated()) {
				String bizPrivate = this.getBusinessPrivate().toString();
				moveInRequest.setProfileInfo(bizPrivate);
			}
		} catch (Exception ignore) {
			ignore.printStackTrace(System.err);
		}

		if (this.getPersonalContact() != null) {
			moveInRequest.setBizPhone(this.getPersonalContact().getBizPhone());
			moveInRequest.setBizPhoneExt(this.getPersonalContact().getBizPhoneExt());
			moveInRequest.setResiPhone(this.getPersonalContact().getResiPhone());
			moveInRequest.setCellPhoneNumber(this.getPersonalContact().getCellPhoneNumber());
			moveInRequest.setNotifyPhoneNumber(this.getPersonalContact().getNotificationPhone());
		}
		if (this.getCurrentAddress() != null)
			moveInRequest.setCurrentAddress(this.getCurrentAddress().toString());
		if (this.getPreviousAddress() != null)
			moveInRequest.setPreviousAddress(this.getPreviousAddress().toString());

		moveInRequest.setMailingAddressChange(this.isMailingAddressChange());
		moveInRequest.setReuseExisitngBillNo(this.isReuseExisitngBillNo());
		return moveInRequest;
	}

	public boolean isCreditcheckApplicable() {
		if (this.isPerson())
			if (this.getCurrentAddress() != null)
				if (this.getCurrentAddress().isCanadaAddress())
					return true;
		return false;
	}

}