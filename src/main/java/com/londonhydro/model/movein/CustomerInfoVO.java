package com.londonhydro.model.movein;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class CustomerInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("identificationStatus")
	private String identificationStatus;
	@JsonProperty("creditCheckStatus")
	private String creditCheckStatus;
	@JsonProperty("currentAddress")
	private AddressVO currentAddress;

	public String getIdentificationStatus() {
		return identificationStatus;
	}

	public void setIdentificationStatus(String identificationStatus) {
		this.identificationStatus = identificationStatus;
	}

	public String getCreditCheckStatus() {
		return creditCheckStatus;
	}

	public void setCreditCheckStatus(String creditCheckStatus) {
		this.creditCheckStatus = creditCheckStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public AddressVO getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(AddressVO currentAddress) {
		this.currentAddress = currentAddress;
	}

}
