package com.londonhydro.model.movein;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class BusinessIncorporation implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("businessPhoneExt")
	private String businessExt;
	@JsonProperty("businessPhone")
	private String businessPhone;
	@JsonProperty("businessType")
	private String businessType;
	@JsonProperty("corporationNumber")
	private String corporationNumber;
	@JsonProperty("email")
	private String email;
	@JsonProperty("faxNumber")
	private String faxNumber;
	@JsonProperty("legalName")
	private String legalName;
	@JsonProperty("notificationPhone")
	private String notificationPhone;
	@JsonProperty("operatingName")
	private String operatingName;
	@JsonProperty("registrationNumber")
	private String registrationNumber;
	@JsonProperty("website")
	private String website;
	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Using Google Guava to compare provided object to me for equality.
	 * 
	 * @param obj
	 *            Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or
	 *         {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BusinessIncorporation other = (BusinessIncorporation) obj;

		return com.google.common.base.Objects.equal(this.legalName, other.legalName)
				&& com.google.common.base.Objects.equal(this.operatingName, other.operatingName)
				&& com.google.common.base.Objects.equal(this.registrationNumber, other.registrationNumber)
				&& com.google.common.base.Objects.equal(this.corporationNumber, other.corporationNumber)
				&& com.google.common.base.Objects.equal(this.businessType, other.businessType)
				&& com.google.common.base.Objects.equal(this.website, other.website);

	}

	public String getBusinessExt() {
		return businessExt;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public String getBusinessType() {
		return businessType;
	}

	public String getCorporationNumber() {
		return corporationNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public String getLegalName() {
		return legalName;
	}

	public String getNotificationPhone() {
		return notificationPhone;
	}

	public String getOperatingName() {
		return operatingName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getWebsite() {
		return website;
	}

	/**
	 * Uses Google Guava to assist in providing hash code of this MoveInRequest
	 * instance.
	 * 
	 * @return My hash code.
	 */
	@Override
	public int hashCode() {
		return com.google.common.base.Objects.hashCode(this.legalName, this.operatingName, this.registrationNumber,
				this.corporationNumber, this.businessType, this.website);
	}

	public void setBusinessExt(String businessExt) {
		this.businessExt = businessExt;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public void setCorporationNumber(String corporationNumber) {
		this.corporationNumber = corporationNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public void setNotificationPhone(String notificationPhone) {
		this.notificationPhone = notificationPhone;
	}

	public void setOperatingName(String operatingName) {
		this.operatingName = operatingName;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Method using Google Guava to provide String representation of this
	 * MoveInRequest instance.
	 * 
	 * @return My String representation.
	 */
	@Override
	public String toString() {
		try {
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			return mapper.writeValueAsString(this);
		} catch (Exception ignore) {
		}
		return null;
	}

}