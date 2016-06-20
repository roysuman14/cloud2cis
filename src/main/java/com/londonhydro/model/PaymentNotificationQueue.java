/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PaymentNotificationQueue.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Antonio Miceli, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  22Mar13     Antonio Miceli		Original version
 */

package com.londonhydro.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * PaymentNotificationQueue entity.
 * 
 * @author Antonio Miceli (amiceli@affsys.com)
 */

public class PaymentNotificationQueue extends BaseEntity<Long> implements Ideable<Long>,Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4398057222882181149L;
	@JsonProperty("accountId")
    private String accountId;
    @JsonProperty("loginId")
    private Long loginId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("paymentDate")
    private Date paymentDate;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("description")
    private String description;
    @JsonProperty("confirmationNumber")
    private String confirmationNumber;
    @JsonProperty("createDate")
    private Date createDate;
    @JsonProperty("username")
    private String username;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("transactionId")
    private Long transactionId;
    
    
    public String getAccountId() { return accountId; }  
    public void setAccountId(String accountId) { this.accountId = accountId; }  
    public Long getLoginId() { return loginId; }  
    public void setLoginId(Long loginId) { this.loginId = loginId; }
    public Double getAmount() {	return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
	public Date getPaymentDate() { return paymentDate; }
	public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
	public String getPaymentMethod() { return paymentMethod; }
	public Date getCreateDate() { return createDate; } 
	public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public void setPaymentMethod(String payment_method) { this.paymentMethod = payment_method; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getConfirmationNumber() { return confirmationNumber; }
	public void setConfirmationNumber(String confirmation_number) { this.confirmationNumber = confirmation_number; }
	public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getCustomerId() { return customerId; } 
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
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
        final PaymentNotificationQueue other = (PaymentNotificationQueue) obj;

        return com.google.common.base.Objects.equal(this.accountId,
            other.accountId)
                && com.google.common.base.Objects.equal(this.loginId,
                    other.loginId)
                && com.google.common.base.Objects.equal(this.getId(),
                    other.getId())
                && com.google.common.base.Objects.equal(this.amount,
                    other.amount)
                && com.google.common.base.Objects.equal(this.paymentDate,
                    other.paymentDate)
                && com.google.common.base.Objects.equal(this.paymentMethod,
                    other.paymentMethod)
                && com.google.common.base.Objects.equal(
                    this.confirmationNumber, other.confirmationNumber)
                && com.google.common.base.Objects.equal(this.description,
                    other.description)
                && com.google.common.base.Objects.equal(this.username,
                    other.username)
                && com.google.common.base.Objects.equal(this.customerId,
                    other.customerId)
                && com.google.common.base.Objects.equal(this.createDate,
                    other.createDate);
    }
    /**
     * Uses Google Guava to assist in providing hash code of this
     * PaymentNotificationQueue instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.accountId,
            this.loginId, this.amount, this.paymentDate, this.paymentMethod,
            this.confirmationNumber, this.description, this.getId(),
            this.createDate, this.username, this.customerId);
           
    }

    /**
     * Method using Google Guava to provide String representation of this
     * PaymentNotificationQueue instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.accountId).addValue(this.loginId)
                .addValue(this.amount).addValue(this.paymentDate)
                .addValue(this.paymentMethod).addValue(this.confirmationNumber)
                .addValue(this.description).addValue(this.getId())
                .addValue(this.createDate).addValue(this.username)
                .addValue(this.customerId).toString();
    }
}

