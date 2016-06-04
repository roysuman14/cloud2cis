/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  CustomerAddress.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  10Jan13     Love Talwar 		Original version
 */

package com.londonhydro.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * CustomerAddress entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class CustomerAddress extends BaseEntity<String> implements Ideable<String>
{

    @JsonProperty("customerID")
    private String customerID;
    @JsonProperty("customer")
    private String customer;
    @JsonProperty("careOf")
    private String careOf;
    @JsonProperty("streetNumber")
    private String streetNumber;
    @JsonProperty("streetName")
    private String streetName;
    @JsonProperty("streetUnit")
    private String streetUnit;
    @JsonProperty("streetSupplemental1")
    private String streetSupplemental1;
    @JsonProperty("streetSupplemental2")
    private String streetSupplemental2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("effDate")
    private Date effDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("addressId")
    private String addressId;
    @JsonProperty("fixedAddressId")
    private String fixedAddressId;
    
    public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getFixedAddressId() {
		return fixedAddressId;
	}
	public void setFixedAddressId(String fixedAddressId) {
		this.fixedAddressId = fixedAddressId;
	}
    public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCustomerID() { return customerID; }  
    public void setCustomerID(String customerID) { this.customerID = customerID; }  
    public String getCareOf() { return careOf; }  
    public void setCareOf(String careOf) { this.careOf = careOf; }  
    public String getStreetNumber() { return streetNumber; }  
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }  
    public String getStreetName() { return streetName; }  
    public void setStreetName(String streetName) { this.streetName = streetName; }  
    public String getStreetUnit() { return streetUnit; }  
    public void setStreetUnit(String streetUnit) { this.streetUnit = streetUnit; }  
    public String getStreetSupplemental1() { return streetSupplemental1; }  
    public void setStreetSupplemental1(String streetSupplemental1) { this.streetSupplemental1 = streetSupplemental1; }  
    public String getStreetSupplemental2() { return streetSupplemental2; }  
    public void setStreetSupplemental2(String streetSupplemental2) { this.streetSupplemental2 = streetSupplemental2; }  
    public String getCity() { return city; }  
    public void setCity(String city) { this.city = city; }  
    public String getProvince() { return province; }  
    public void setProvince(String province) { this.province = province; }  
    public String getPostalCode() { return postalCode; }  
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }  
    public String getCountry() { return country; }  
    public void setCountry(String country) { this.country = country; }

    public Date getEffDate() { return effDate; }
    public void setEffDate(Date effDate) { this.effDate = effDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate;
    }

    /**
     * Using Google Guava to compare provided object to me for equality.
     * 
     * @param obj
     *            Object to be compared to me for equality.
     * @return {@code true} if provided object is considered equal to me or
     *         {@code false} if provided object is not considered equal to me.
     */
    @Override public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final CustomerAddress other = (CustomerAddress) obj;

        return com.google.common.base.Objects.equal(this.customerID,
            other.customerID)
                && com.google.common.base.Objects.equal(this.getId(),
                    other.getId())
                && com.google.common.base.Objects.equal(this.careOf,
                    other.careOf)
                && com.google.common.base.Objects.equal(this.streetNumber,
                    other.streetNumber)
                && com.google.common.base.Objects.equal(this.streetName,
                    other.streetName)
                && com.google.common.base.Objects.equal(this.streetUnit,
                    other.streetUnit)
                && com.google.common.base.Objects.equal(
                    this.streetSupplemental1, other.streetSupplemental1)
                && com.google.common.base.Objects.equal(
                    this.streetSupplemental2, other.streetSupplemental2)
                && com.google.common.base.Objects.equal(this.city, other.city)
                && com.google.common.base.Objects.equal(this.province,
                    other.province)
                && com.google.common.base.Objects.equal(this.postalCode,
                    other.postalCode)
                && com.google.common.base.Objects.equal(this.country,
                    other.country)
                && com.google.common.base.Objects.equal(this.effDate,
                    other.effDate)
                && com.google.common.base.Objects.equal(this.endDate,
                    other.endDate);
    }

    /**
     * Uses Google Guava to assist in providing hash code of this
     * CustomerAddress instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.customerID,
            this.getId(), this.careOf, this.streetNumber, this.streetName,
            this.streetUnit, this.streetSupplemental1,
            this.streetSupplemental2, this.city, this.province,
            this.postalCode, this.country, this.effDate, this.endDate);
    }

    /**
     * Method using Google Guava to provide String representation of this
     * CustomerAddress instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.customerID).addValue(this.getId())
                .addValue(this.careOf).addValue(this.streetNumber)
                .addValue(this.streetName).addValue(this.streetUnit)
                .addValue(this.streetSupplemental1)
                .addValue(this.streetSupplemental2).addValue(this.city)
                .addValue(this.province).addValue(this.postalCode)
                .addValue(this.country).addValue(this.effDate)
                .addValue(this.endDate).toString();
    }
}
