package com.londonhydro.model.movein;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class BusinessPrivate implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("businessName")
	private String businessName;
	@JsonProperty("ownerName")
	private String ownerName;
	@JsonProperty("position")
	private String position;
	@JsonProperty("driverLicense")
	private String driverLicense;
	@JsonProperty("businessLicense")
	private String businessLicense;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("email")
	private String email;
	@JsonProperty("faxNumber")
	private String faxNumber;
	@JsonProperty("bisnessPhone")
	private String businessPhone;
	@JsonProperty("bisnessExt")
	private String businessExt;
	@JsonProperty("notificationPhone")
	private String notificationPhone;
	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessExt() {
		return businessExt;
	}

	public void setBusinessExt(String businessExt) {
		this.businessExt = businessExt;
	}

	public String getNotificationPhone() {
		return notificationPhone;
	}

	public void setNotificationPhone(String notificationPhone) {
		this.notificationPhone = notificationPhone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String name) {
		this.ownerName = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
		final BusinessPrivate other = (BusinessPrivate) obj;

		return com.google.common.base.Objects.equal(this.ownerName, other.ownerName)
				&& com.google.common.base.Objects.equal(this.position, other.position)
				&& com.google.common.base.Objects.equal(this.phoneNumber, other.phoneNumber)
				&& com.google.common.base.Objects.equal(this.email, other.email)
				&& com.google.common.base.Objects.equal(this.driverLicense, other.driverLicense)
				&& com.google.common.base.Objects.equal(this.birthDate, other.birthDate)
				&& com.google.common.base.Objects.equal(this.businessLicense, other.businessLicense);

	}

	/**
	 * Uses Google Guava to assist in providing hash code of this MoveInRequest
	 * instance.
	 * 
	 * @return My hash code.
	 */
	@Override
	public int hashCode() {
		return com.google.common.base.Objects.hashCode(this.ownerName, this.position, this.phoneNumber, this.email,
				this.driverLicense, this.birthDate, this.businessLicense);
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
			ignore.printStackTrace();
		}
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
