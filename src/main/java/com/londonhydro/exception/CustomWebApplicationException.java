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

import java.net.HttpURLConnection;

import javax.ws.rs.WebApplicationException;

/**
 * Custom Web Application to manage custom error code & messages.
 * 
 * @author Juan Pablo Francisconi (jpfrancisconi@affsys.com)
 */
public class CustomWebApplicationException extends WebApplicationException {

	private static final long serialVersionUID = 1419194855746613335L;

	private int errorCode;
	private String msg;

	public CustomWebApplicationException(int httpError, String message) {
		errorCode = httpError;
		msg = message;
	}

	public static CustomWebApplicationException ERR_BAD_LOGIN(Object... objects) {
		return new CustomWebApplicationException(HttpURLConnection.HTTP_UNAUTHORIZED,
				String.format("Unable to log in: %s", objects));
	}

	public static CustomWebApplicationException ERR_BAD_REQ(Object... objects) {
		return new CustomWebApplicationException(HttpURLConnection.HTTP_BAD_REQUEST,
				String.format("Bad request: %s", objects));
	}

	public static CustomWebApplicationException ERR_NOT_FOUND(Object... objects) {
		return new CustomWebApplicationException(HttpURLConnection.HTTP_NOT_FOUND,
				String.format("Not found: %s", objects));
	}

	public static CustomWebApplicationException ERR_BAD_INPUT_DATA_JSON(Object... objects) {
		return new CustomWebApplicationException(HttpURLConnection.HTTP_BAD_REQUEST,
				String.format("Check the json object: %s", objects));
	}

	public static CustomWebApplicationException ERR_INVALID_PERMISSION(Object... objects) {
		return new CustomWebApplicationException(HttpURLConnection.HTTP_FORBIDDEN,
				String.format("Your are not allowed to access this resource", objects));
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getCustomMessage() {
		return msg;
	}

}
