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
 *  24July13     Love Talwar         Original version
 */


package com.londonhydro.cloud2cis.model;



public enum QueueStatus
{
	LH_PORTAL_RECEIVED,
    SAP_SENT,
    SAP_SENT_FAILED;
    
    public static QueueStatus parse(String text)
    {
        if (LH_PORTAL_RECEIVED.name().equalsIgnoreCase(text))
            return LH_PORTAL_RECEIVED;
        if (SAP_SENT.name().equalsIgnoreCase(text))
            return SAP_SENT;
        if (SAP_SENT_FAILED.name().equalsIgnoreCase(text))
            return SAP_SENT_FAILED;
        throw new IllegalArgumentException(text);
    }
    
};
