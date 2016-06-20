/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PaymentArrangementsQueue.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  06Jul13     Love Talwar		   Original version
 */

package com.londonhydro.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * PaymentArrangementsQueue entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class PaymentArrangementsQueue extends BaseEntity<Long> implements Ideable<Long>,Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 548550645697071862L;
	@JsonProperty("accountId")
    private String accountId;
    @JsonProperty("installmentId")
    private String installmentId;
    @JsonProperty("installmentDate")
    private Long installmentDate;
    @JsonProperty("installmentAmount")
    private Double installmentAmount;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("paymentDate")
    private Date paymentDate;
    @JsonProperty("transactionId")
    private Long transactionId;
    
 
    public String getPaymentMethod() { return paymentMethod; } 
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getAccountId() { return accountId; } 
    public void setAccountId(String accountId) { this.accountId = accountId; } 
    public String getInstallmentId() { return installmentId; } 
    public void setInstallmentId(String installmentId) { this.installmentId = installmentId; } 
    public Long getInstallmentDate() { return installmentDate; } 
    public void setInstallmentDate(Long installmentDate) { this.installmentDate = installmentDate; } 
    public Double getInstallmentAmount() { return installmentAmount; } 
    public void setInstallmentAmount(Double installmentAmount) { this.installmentAmount = installmentAmount; }
    
    
    public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Date getPaymentDate() 
    { 
		if(paymentDate != null)
			return paymentDate;
    	if(installmentDate != null && installmentDate != 0)
    		return new Date(installmentDate);
    	
    	return null;
    } 
	
    
   /* public Date getInstallmentDate() 
    { 
        try
        {
            return installmentDateString != null ?  new SimpleDateFormat("yyyy-MM-dd").parse(installmentDateString) : null;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null;
    } */
     
    
    public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getCustomerId() { return customerId; } 
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
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
        final PaymentArrangementsQueue other = (PaymentArrangementsQueue) obj;

        return com.google.common.base.Objects.equal(this.accountId,
            other.accountId)
                && com.google.common.base.Objects.equal(this.getId(),
                    other.getId())
                && com.google.common.base.Objects.equal(this.installmentId,
                    other.installmentId)
                && com.google.common.base.Objects.equal(this.installmentDate,
                    other.installmentDate)
                && com.google.common.base.Objects.equal(this.paymentMethod,
                    other.paymentMethod)
                && com.google.common.base.Objects.equal(this.customerId,
                    other.customerId)
                && com.google.common.base.Objects.equal(this.installmentAmount,
                    other.installmentAmount);
    }
    /**
     * Uses Google Guava to assist in providing hash code of this
     * PaymentArrangementsQueue instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.accountId,
            this.installmentId, this.installmentDate, this.paymentMethod,
            this.installmentAmount, this.getId(), this.customerId);

    }

    /**
     * Method using Google Guava to provide String representation of this
     * PaymentArrangements instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.accountId).addValue(this.installmentId)
                .addValue(this.installmentDate)
                .addValue(this.installmentAmount).addValue(this.getId())
                .addValue(this.paymentMethod).addValue(this.customerId)
                .toString();
    }
}

