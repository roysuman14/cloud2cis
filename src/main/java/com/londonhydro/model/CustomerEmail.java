/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  CustomerEmail.java
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
 * CustomerEmail entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class CustomerEmail extends BaseEntity<String> implements Ideable<String>
{
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("emailaddr")
    private String emailAddress;
    @JsonProperty("standard")
    private boolean standard;
    @JsonProperty("notification")
    private boolean notification;
    
    public boolean isStandard() { return standard; } 
    public void setStandard(boolean standard) { this.standard = standard; }
    public String getCustomerId() { return customerId; }  
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getEmailAddress() { return emailAddress; } 
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public boolean isNotification() { return notification; } 
    public void setNotification(boolean notification) { this.notification = notification; }
    
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
        final CustomerEmail other = (CustomerEmail) obj;

        return com.google.common.base.Objects
                .equal(this.getId(), other.getId())
                && com.google.common.base.Objects.equal(this.standard,
                    other.standard)
                && com.google.common.base.Objects.equal(this.customerId,
                    other.customerId)
                     && com.google.common.base.Objects.equal(this.notification,
                    other.notification)
                && com.google.common.base.Objects.equal(this.emailAddress,
                    other.emailAddress);

    }

    /**
     * Uses Google Guava to assist in providing hash code of this
     * CustomerEmail instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.getId(),
            this.standard, this.customerId, this.emailAddress, this.notification);
           
    }

    /**
     * Method using Google Guava to provide String representation of this
     * CustomerEmail instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.getId()).addValue(this.standard)
                .addValue(this.customerId).addValue(this.emailAddress)
                .toString();
    }
}
