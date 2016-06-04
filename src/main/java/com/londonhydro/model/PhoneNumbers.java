/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PhoneNumbers.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  21Feb13     Love Talwar 		Original version
 */

package com.londonhydro.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * PhoneNumbers entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class PhoneNumbers extends BaseEntity<String> implements Ideable<String>
{
    /**
     * Phone categories
     */
    // Applied to MyAccount
    public static final String PHONE_CATEGORY_RESIDENTIAL = "RESIDENTIAL";
    public static final String PHONE_CATEGORY_CELL = "CELL";
    public static final String PHONE_CATEGORY_BUSSINESS_WORK = "BUSINESS/WORK";
    public static final String PHONE_CATEGORY_NOTIFICATION = "NOTIFICATION";
    // Applied to everything else
    public static final String PHONE_CATEGORY_PARENT = "Parent";
    public static final String PHONE_CATEGORY_FAX = "Fax";
    public static final String PHONE_CATEGORY_MV90 = "MV90";
    public static final String PHONE_CATEGORY_TOLLFREE = "Toll Free";
    public static final String PHONE_CATEGORY_A_P = "A/P";
    public static final String PHONE_CATEGORY_CONTACT = "Contact";
 
    @JsonProperty("phoneCountry")
    private String phoneCountry;
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("sequenceNumber")
    private String sequenceNumber;
    @JsonProperty("standard")
    private boolean standard;
    @JsonProperty("category")
    private String category;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("sap")
    private boolean sap;
    @JsonProperty("effDate")
    private boolean effDate;
       
    public boolean isEffDate() {
		return effDate;
	}
	public void setEffDate(boolean effDate) {
		this.effDate = effDate;
	}
	public String getPhoneCountry() { return phoneCountry; } 
    public void setPhoneCountry(String phoneCountry) { this.phoneCountry = phoneCountry; } 
    public String getExtension() { return extension; } 
    public void setExtension(String extension) { this.extension = extension; } 
    public String getSequenceNumber() { return sequenceNumber; } 
    public void setSequenceNumber(String sequenceNumber) { this.sequenceNumber = sequenceNumber; }
    public boolean isStandard() { return standard; } 
    public void setStandard(boolean standard) { this.standard = standard; }
    public String getCategory() { return category; } 
    public void setCategory(String category) { this.category = category; }
    public String getCustomerId() { return customerId; }  
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getPhoneNumber() { return phoneNumber; } 
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public boolean isSap() { return sap; } 
    public void setSap(boolean sap) { this.sap = sap; }
    
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
        final PhoneNumbers other = (PhoneNumbers) obj;

        return com.google.common.base.Objects
                .equal(this.getId(), other.getId())
                && com.google.common.base.Objects.equal(this.phoneCountry,
                    other.phoneCountry)
                && com.google.common.base.Objects.equal(this.extension,
                    other.extension)
                && com.google.common.base.Objects.equal(this.sequenceNumber,
                    other.sequenceNumber)
                && com.google.common.base.Objects.equal(this.standard,
                    other.standard)
                && com.google.common.base.Objects.equal(this.category,
                    other.category)
                && com.google.common.base.Objects.equal(this.customerId,
                    other.customerId)
                && com.google.common.base.Objects.equal(this.phoneNumber,
                    other.phoneNumber);
                
    }

    /**
     * Uses Google Guava to assist in providing hash code of this
     * PhoneNumbers instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.getId(),
            this.phoneCountry, this.extension, this.sequenceNumber,
            this.standard, this.category, this.customerId, this.phoneNumber);
           
    }

    /**
     * Method using Google Guava to provide String representation of this
     * PhoneNumbers instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.getId()).addValue(this.phoneCountry)
                .addValue(this.extension).addValue(this.sequenceNumber)
                .addValue(this.standard).addValue(this.category)
                .addValue(this.customerId).addValue(this.phoneNumber)
                .toString();
    }
}
