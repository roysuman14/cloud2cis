/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                        *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  CustomWebApplicationException.java
 *
 *  Facility:   London Hydro Utils
 *
 *  Author:     Juan Pablo Francisconi, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  10Jan13     Juan Pablo Francisconi 	Original version
 */

package com.londonhydro.exception;

import com.londonhydro.sap.SAPErrorCode;

public class CISException extends Exception {

	private static final long serialVersionUID = 1419194855746613335L;

	private SAPErrorCode error;

	public SAPErrorCode getError() {
		return error;
	}

	public void setError(SAPErrorCode error) {
		this.error = error;
	}

	public CISException(SAPErrorCode error) {
		this.error = error;
	}

}
