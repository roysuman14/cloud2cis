/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  BaseEntity.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Juan Pablo Francisconi, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  10Jan13     Juan Pablo Francisconi 	Original version
 */

package com.londonhydro.model;

import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * Base class for every entity within the business model.
 * 
 * @author Juan Pablo Francisconi (jpfrancisconi@affsys.com)
 */
@JsonPropertyOrder(
{ "id" })
public class BaseEntity<ID_TYPE>
{
    private ID_TYPE id;

    public BaseEntity() { super(); }

    public BaseEntity(ID_TYPE id)
    {
        this();
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    public void setId(ID_TYPE id) 
    { 
        if (id instanceof BigInteger) 
            this.id = (ID_TYPE)Long.valueOf(((BigInteger) id).longValue());
        else
            this.id = id; 
    }

    public ID_TYPE getId() { return id; }
    
}
