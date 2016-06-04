/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PasswordQuestion.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  15Feb13     Love Talwar 		Original version
 */

package com.londonhydro.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * MasterData entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class MasterDataResult extends BaseEntity<Long> implements Ideable<Long>
{

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("profileInfo")
    private String profileInfo;
    @JsonProperty("id")
    private String idType;
    @JsonProperty("transactionId")
    private String transactionId;
    

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
        final MasterDataResult other = (MasterDataResult) obj;

		return com.google.common.base.Objects
				.equal(this.getId(), other.getId())
				&& com.google.common.base.Objects.equal(this.customerId,
						other.customerId)
				&& com.google.common.base.Objects.equal(this.effectiveDate,
	                    other.effectiveDate)
                && com.google.common.base.Objects.equal(this.transactionId,
                		other.transactionId)
                && com.google.common.base.Objects.equal(this.profileInfo,
                		other.profileInfo)
				&& com.google.common.base.Objects.equal(this.idType,
						other.idType)
				&& com.google.common.base.Objects.equal(this.transactionId,
						other.transactionId);
    }

    /**
     * Uses Google Guava to assist in providing hash code of this MasterData
     * instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.getId(), this.customerId, this.effectiveDate, this.profileInfo,
        		this.transactionId, this.idType, this.transactionId);
    }

    /**
     * Method using Google Guava to provide String representation of this
     * MasterData instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
		return com.google.common.base.Objects.toStringHelper(this)
				.addValue(this.getId())
				.addValue(this.customerId)
				.addValue(this.effectiveDate)
				.addValue(this.profileInfo)
				.addValue(this.transactionId)
				.addValue(this.idType)
				.addValue(this.transactionId).toString();
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(String profileInfo) {
		this.profileInfo = profileInfo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

}
