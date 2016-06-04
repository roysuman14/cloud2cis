/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  LoginResult.java
 *
 *  Facility:   London Hydro Model
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  03Apr13     Love Talwar 		Original version
 */

package com.londonhydro.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * LoginResult entity.
 * 
 * @author Love Talwar (ltalwar@affsys.com)
 */

public class LoginResult {

	@JsonProperty("timeKey")
	private String timeKey;
	@JsonProperty("timeFrame")
	private String timeFrame;
	@JsonProperty("guid")
	private String guid;

	public String getTimeKey() {
		return timeKey;
	}

	public void setTimeKey(String timeKey) {
		this.timeKey = timeKey;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
