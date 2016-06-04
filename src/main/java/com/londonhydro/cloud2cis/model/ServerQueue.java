/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  ServerQueue.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  02Apr13     Love Talwar 		Original version
 */

package com.londonhydro.cloud2cis.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.londonhydro.model.BaseEntity;
import com.londonhydro.model.Ideable;

/**
 * ServerQueue entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class ServerQueue extends BaseEntity<Long> implements Ideable<Long>
{
    
    @JsonProperty("actionType")
    private String actionType;    
    @JsonProperty("table")
    private String table;    
    @JsonProperty("column")
    private String column;
    @JsonProperty("oldValue")
    private String oldValue;    
    @JsonProperty("newValue")
    private String newValue;    
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("loginId")
    private Long loginId;    
    @JsonProperty("transactionId")
    private Long transactionId;
    @JsonProperty("transaction_description")
    private String transactionDescription;    
    @JsonProperty("createDate")
    private Date createDate;
    @JsonProperty("username")
    private String username;
    
    private String status; // Used in the broker project
    private boolean encryptValues;
    
    public String getActionType() { return actionType; } 
    public void setActionType(String actionType) { this.actionType = actionType; } 
    public String getTable() { return table; }  
    public void setTable(String table) { this.table = table; } 
    public String getColumn() { return column; }  
    public void setColumn(String column) { this.column = column; }  
    public String getOldValue() { return oldValue; }  
    public void setOldValue(String oldValue) { this.oldValue = oldValue; } 
    public String getNewValue() { return newValue; } 
    public void setNewValue(String newValue) { this.newValue = newValue; }  
    public String getAccountId() { return accountId; }  
    public void setAccountId(String accountId) { this.accountId = accountId; } 
    public Long getLoginId() { return loginId; }  
    public void setLoginId(Long loginId) { this.loginId = loginId; }
    public Long getTransactionId() { return transactionId; }  
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; } 
    public String getTransactionDescription() { return transactionDescription; }
    public void setTransactionDescription(String transactionDescription) { this.transactionDescription = transactionDescription; }
    public Date getCreateDate() { return createDate; }  
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }   
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isEncryptValues() { return encryptValues; }    
    public void setEncryptValues(boolean encryptValues) {this.encryptValues = encryptValues;}

    public ServerQueue() {}    

    public ServerQueue(String actionType, String table, String column,
            String oldValue, String newValue,
            String accountId, Long loginId , Long transactionId)
    {
        this(actionType, table, column, oldValue, newValue, accountId, loginId, transactionId, false);
    }

    public ServerQueue(String actionType, String table, String column,
            String oldValue, String newValue,
            String accountId, Long loginId , Long transactionId, boolean encryptValues)
    {
        super();
        this.actionType = actionType;
        this.table = table;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.encryptValues = encryptValues;
        this.accountId = accountId;
        this.loginId = loginId;
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
        final ServerQueue other = (ServerQueue) obj;

        return com.google.common.base.Objects
                .equal(this.getId(), other.getId())
                && com.google.common.base.Objects.equal(this.actionType,
                    other.actionType)
                && com.google.common.base.Objects
                        .equal(this.table, other.table)
                && com.google.common.base.Objects.equal(this.column,
                    other.column)
                && com.google.common.base.Objects.equal(this.oldValue,
                    other.oldValue)
                && com.google.common.base.Objects.equal(this.newValue,
                    other.newValue)
                && com.google.common.base.Objects.equal(this.accountId,
                    other.accountId)
                && com.google.common.base.Objects.equal(this.loginId,
                    other.loginId)
                && com.google.common.base.Objects.equal(this.transactionId,
                    other.transactionId)
                && com.google.common.base.Objects.equal(this.username,
                    other.username)
                && com.google.common.base.Objects.equal(this.createDate,
                    other.createDate);
                
    }

    /**
     * Uses Google Guava to assist in providing hash code of this ServerQueue
     * instance.
     * 
     * @return My hash code.
     */
    @Override public int hashCode()
    {
        return com.google.common.base.Objects.hashCode(this.getId(),
            this.actionType, this.table, this.column, this.oldValue,
            this.newValue, this.accountId, this.loginId, this.transactionId,
            this.createDate, this.username);
    }

    /**
     * Method using Google Guava to provide String representation of this ServerQueue
     * instance.
     * 
     * @return My String representation.
     */
    @Override public String toString()
    {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.getId()).addValue(this.actionType)
                .addValue(this.table).addValue(this.column)
                .addValue(this.oldValue).addValue(this.newValue)
                .addValue(this.accountId).addValue(this.loginId)
                .addValue(this.transactionId).addValue(this.createDate)
                .addValue(this.username).toString();
    }
}
