/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *	File Name:	BrokerPrivateModule.java
 *
 *	Facility:	London Hydro Web App
 *
 *	Purpose:	This module contains a class implementation
 *
 *	Author:		vpinchevski, Affinity Systems
 *
 *  Revision History
 *
 *  	Date			Author			Description
 *	----------------	---------------------	--------------------
 *	Aug 27, 2013		vpinchevski		Original version
 *
 */
package com.londonhydro.inject;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.PrivateModule;
import com.londonhydro.utils.PropertiesUtil;

/**
 * @author vpinchevski
 *
 */
public abstract class Cloud2CISPrivateModule extends PrivateModule {
	private static final Log logger = LogFactory.getLog(Cloud2CISPrivateModule.class);

	protected static Properties getProperties() {
		Properties properties = PropertiesUtil.getInstance().loadProperties("/cloud2cis.properties");
		String path = System.getProperty("cloud2cis.properties.path");
		if (path != null && properties != null) {
			logger.info("Overwriting default properties from: " + path);
			properties.putAll(PropertiesUtil.getInstance().loadProperties(path));
		}

		return properties;
	}

}
