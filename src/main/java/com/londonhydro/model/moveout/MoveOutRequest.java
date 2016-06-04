/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *	File Name:	MoveOutRequest.java
 *
 *	Facility:	London Hydro Web App
 *
 *	Purpose:	This module contains a class implementation
 *
 *	Author:		Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  	Date			Author			Description
 *	----------------	---------------------	--------------------
 *	07May13		        Love Talwar		Original version
 *
 */
package com.londonhydro.model.moveout;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.londonhydro.model.BaseEntity;
import com.londonhydro.model.Ideable;

/**
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 *
 */
public class MoveOutRequest extends BaseEntity<Long> implements Ideable<Long>
{
    @JsonProperty("moveOutStateId")
    private Long moveOutStateId;
    @JsonProperty("moveOutState")
    private String moveOutState;
    @JsonProperty("confirmationNumber")
    private String confirmationNumber;
    @JsonProperty("moveOutDate")
    private Date moveOutDate;
    @JsonProperty("premiseId")
    private String premiseId;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("loginId")
    private Long loginId;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("ownerPhoneNumber")
    private String ownerPhoneNumber;
    @JsonProperty("dob")
    private Date dob;
    @JsonProperty("mailingAddress")
    private String mailingAddressControl;
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
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("lawyerName")
    private String lawyerName;
    @JsonProperty("lawyerPhone")
    private String lawyerPhone;
    @JsonProperty("createDate")
    private Date createDate;
    @JsonProperty("updateDate")
    private Date updateDate;
    @JsonProperty("electricReading")
    private Double electricReading;
    @JsonProperty("electricReadingDate")
    private Date electricReadingDate;
    @JsonProperty("waterReading")
    private Double waterReading;
    @JsonProperty("waterReadingDate")
    private Date waterReadingDate;
    @JsonProperty("selling")
    private boolean selling;
    @JsonProperty("outofProvince")
    private boolean outofProvince;
    @JsonProperty("username")
    private String username;
    @JsonProperty("careOf")
    private String careOf;    
    @JsonProperty("nonCivic1")
    private String nonCivic1;
    @JsonProperty("nonCivic2")
    private String nonCivic2;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("transactionId")
    private Long transactionId;
    @JsonProperty("transfer")
    private boolean transfer;
    
    
    public Long getMoveOutStateId() { return moveOutStateId; } 
    public void setMoveOutStateId(Long moveOutStateId) { this.moveOutStateId = moveOutStateId; } 
    public String getMoveOutState() { return moveOutState; } 
    public void setMoveOutState(String moveOutState) { this.moveOutState = moveOutState; } 
    public String getConfirmationNumber() { return confirmationNumber; } 
    public void setConfirmationNumber(String confirmationNumber) { this.confirmationNumber = confirmationNumber; }
    public Date getMoveOutDate() { return moveOutDate; } 
    public void setMoveOutDate(Date moveOutDate) { this.moveOutDate = moveOutDate; } 
    public String getPremiseId() { return premiseId; } 
    public void setPremiseId(String premiseId) { this.premiseId = premiseId; } 
    public String getAccountId() { return accountId; } 
    public void setAccountId(String accountId) { this.accountId = accountId; } 
    public Long getLoginId() { return loginId; }
    public void setLoginId(Long loginId) { this.loginId = loginId; } 
    public Date getDob() { return dob; } 
    public void setDob(Date dob) { this.dob = dob; }
    public String getMailingAddressControl() { return mailingAddressControl; }
    public void setMailingAddressControl(String mailingAddressControl) { this.mailingAddressControl = mailingAddressControl; }    
    public String getStreetNumber() { return streetNumber; } 
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; } 
    public String getStreetName() { return streetName; } 
    public void setStreetName(String streetName) { this.streetName = streetName; } 
    public String getStreetUnit() { return streetUnit; } 
    public void setStreetUnit(String streetUnit) { this.streetUnit = streetUnit; }
    public String getCity() { return city; } 
    public void setCity(String city) { this.city = city; } 
    public String getProvince() { return province; } 
    public void setProvince(String province) { this.province = province; } 
    public String getPostalCode() { return postalCode; } 
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; } 
    public String getCountry() { return country; } 
    public void setCountry(String country) { this.country = country; } 
    public String getPhoneNumber() { return phoneNumber; } 
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; } 
    public String getMobileNumber() { return mobileNumber; } 
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; } 
    public String getEmailAddress() { return emailAddress; } 
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; } 
    public String getLawyerName() { return lawyerName; } 
    public void setLawyerName(String lawyerName) { this.lawyerName = lawyerName; }
    public String getLawyerPhone() { return lawyerPhone; } 
    public void setLawyerPhone(String lawyerPhone) { this.lawyerPhone = lawyerPhone; }
    public Date getCreateDate() { return createDate; } 
    public void setCreateDate(Date createDate) { this.createDate = createDate; } 
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
    public Double getElectricReading() { return electricReading; } 
    public void setElectricReading(Double electricReading) { this.electricReading = electricReading; } 
    public Date getElectricReadingDate() { return electricReadingDate; } 
    public void setElectricReadingDate(Date electricReadingDate) { this.electricReadingDate = electricReadingDate; } 
    public Double getWaterReading() { return waterReading; } 
    public void setWaterReading(Double waterReading) { this.waterReading = waterReading; } 
    public Date getWaterReadingDate() { return waterReadingDate; } 
    public void setWaterReadingDate(Date waterReadingDate) { this.waterReadingDate = waterReadingDate; }
    public boolean isSelling() { return selling; } 
    public void setSelling(boolean selling) { this.selling = selling; } 
    public boolean isOutofProvince() { return outofProvince; } 
    public void setOutofProvince(boolean outofProvince) { this.outofProvince = outofProvince; }
    public String getUsername() { return username; } 
    public void setUsername(String username) { this.username = username; }
    
    
    public boolean isTransfer() {
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOwnerName()
    {
        return ownerName;
    }
    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }
    public String getOwnerPhoneNumber()
    {
        return ownerPhoneNumber;
    }
    public void setOwnerPhoneNumber(String ownerPhoneNumber)
    {
        this.ownerPhoneNumber = ownerPhoneNumber;
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
        final MoveOutRequest other = (MoveOutRequest) obj;

        return com.google.common.base.Objects.equal(this.getId(), other.getId())
                && com.google.common.base.Objects.equal(this.moveOutStateId, other.moveOutStateId)
                && com.google.common.base.Objects.equal(this.moveOutState == null ? null : this.moveOutState.toLowerCase(), 
                            other.moveOutState == null ? null : other.moveOutState.toLowerCase())
                && com.google.common.base.Objects.equal(this.confirmationNumber, other.confirmationNumber)
                && com.google.common.base.Objects.equal(this.moveOutDate, other.moveOutDate)
                && com.google.common.base.Objects.equal(this.premiseId, other.premiseId)
                && com.google.common.base.Objects.equal(this.accountId, other.accountId)
                && com.google.common.base.Objects.equal(this.ownerName, other.ownerName)
                && com.google.common.base.Objects.equal(this.ownerPhoneNumber, other.ownerPhoneNumber)
                && com.google.common.base.Objects.equal(this.dob, other.dob)
                && com.google.common.base.Objects.equal(this.streetNumber, other.streetNumber)
                && com.google.common.base.Objects.equal(this.streetName, other.streetName)
                && com.google.common.base.Objects.equal(this.streetUnit, other.streetUnit)
                && com.google.common.base.Objects.equal(this.city, other.city)
                && com.google.common.base.Objects.equal(this.province, other.province)
                && com.google.common.base.Objects.equal(this.postalCode, other.postalCode)
                && com.google.common.base.Objects.equal(this.country, other.country)
                && com.google.common.base.Objects.equal(this.phoneNumber, other.phoneNumber)
                && com.google.common.base.Objects.equal(this.mobileNumber, other.mobileNumber)
                && com.google.common.base.Objects.equal(this.emailAddress, other.emailAddress)
                && com.google.common.base.Objects.equal(this.lawyerName, other.lawyerName)
                && com.google.common.base.Objects.equal(this.lawyerPhone, other.lawyerPhone)
                && com.google.common.base.Objects.equal(this.createDate, other.createDate)
                && com.google.common.base.Objects.equal(this.electricReading, other.electricReading)
                && com.google.common.base.Objects.equal(this.electricReadingDate, other.electricReadingDate)
                && com.google.common.base.Objects.equal(this.waterReading, other.waterReading)
                && com.google.common.base.Objects.equal(this.waterReadingDate, other.waterReadingDate)
                && com.google.common.base.Objects.equal(this.selling, other.selling)
                && com.google.common.base.Objects.equal(this.outofProvince, other.outofProvince)
                && com.google.common.base.Objects.equal(this.username, other.username);
              
}

    /**
     * Uses Google Guava to assist in providing hash code of this MoveOutRequest
     * instance.
     * 
     * @return My hash code.
     */
    @Override
    public int hashCode()
    {
		return com.google.common.base.Objects.hashCode(this.getId(),
				this.moveOutStateId, this.moveOutState, this.confirmationNumber, this.moveOutDate,
				this.premiseId, this.accountId, this.loginId, this.ownerName,
				this.ownerPhoneNumber, this.dob, this.streetNumber, this.streetName,
				this.streetUnit, this.city, this.province, this.postalCode,
				this.country, this.phoneNumber, this.mobileNumber,
				this.emailAddress, this.lawyerName,
				this.lawyerPhone, this.createDate, this.updateDate,
				this.electricReading, this.electricReadingDate,
				this.waterReading, this.waterReadingDate, this.selling,
				this.outofProvince, this.username);

    }

    /**
     * Method using Google Guava to provide String representation of this
     * MoveOutRequest instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.getId()).addValue(this.moveOutStateId)
                .addValue(this.moveOutState).addValue(this.confirmationNumber)
                .addValue(this.moveOutDate).addValue(this.premiseId)
                .addValue(this.accountId).addValue(this.loginId)
                .addValue(this.ownerName).addValue(this.ownerPhoneNumber)
                .addValue(this.dob).addValue(this.streetNumber)
                .addValue(this.streetName).addValue(this.streetUnit)
                .addValue(this.city).addValue(this.province)
                .addValue(this.postalCode).addValue(this.country)
                .addValue(this.phoneNumber).addValue(this.mobileNumber)
                .addValue(this.emailAddress).addValue(this.lawyerName)
                .addValue(this.lawyerPhone).addValue(this.createDate)
                .addValue(this.updateDate).addValue(this.electricReading)
                .addValue(this.electricReadingDate).addValue(this.waterReading)
                .addValue(this.waterReadingDate).addValue(this.selling)
                .addValue(this.outofProvince).addValue(this.username)
                .toString();
    }
    

}
