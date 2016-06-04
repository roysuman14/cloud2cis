package com.londonhydro.model.movein;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class AddressVO implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("careOf")
	private String careOf;
	@JsonProperty("nonCivic1")
	private String nonCivic1;
	@JsonProperty("nonCivic2")
	private String nonCivic2;
	@JsonProperty("streetNumber")
	private String streetNumber;
	@JsonProperty("streetName")
	private String streetName;
	@JsonProperty("streetUnit")
	private String streetUnit;
	@JsonProperty("city")
	private String city;
	@JsonProperty("province")
	private String province;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("country")
	private String country;
	@JsonProperty("lineAddress")
	private String lineAddress;
	@JsonProperty("canadaAddress")
	private boolean canadaAddress;
	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	public boolean isCanadaAddress() {
		return canadaAddress;
	}

	public void setCanadaAddress(boolean canadaAddress) {
		this.canadaAddress = canadaAddress;
	}

	public String getLineAddress() {
		return lineAddress;
	}

	public void setLineAddress(String lineAddress) {
		this.lineAddress = lineAddress;
	}

	public String getCareOf() {
		if (this.careOf != null)
			this.careOf.toUpperCase();
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getNonCivic1() {
		if (this.nonCivic1 != null)
			this.nonCivic1.toUpperCase();
		return nonCivic1;
	}

	public void setNonCivic1(String nonCivic1) {
		this.nonCivic1 = nonCivic1;
	}

	public String getNonCivic2() {
		if (this.nonCivic2 != null)
			this.nonCivic2.toUpperCase();
		return nonCivic2;
	}

	public void setNonCivic2(String nonCivic2) {
		this.nonCivic2 = nonCivic2;
	}

	public String getStreetNumber() {
		if (this.streetNumber != null)
			this.streetNumber.toUpperCase();
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		if (this.streetName != null)
			this.streetName.toUpperCase();
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetUnit() {
		if (this.streetUnit != null)
			this.streetUnit.toUpperCase();
		return streetUnit;
	}

	public void setStreetUnit(String streetUnit) {
		this.streetUnit = streetUnit;
	}

	public String getCity() {
		if (this.city != null)
			this.city.toUpperCase();
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		if (this.province != null)
			this.province.toUpperCase();
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		if (this.postalCode != null)
			this.postalCode.toUpperCase();
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		if (this.country != null)
			this.country.toUpperCase();
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		try {
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			return mapper.writeValueAsString(this);
		} catch (Exception ignore) {
		}
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
