/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PropertiesUtil.java
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

package com.londonhydro.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class in charge of Properties Management.
 * 
 * @author Juan Pablo Francisconi (jpfrancisconi@affsys.com)
 */
public class PropertiesUtil {

	private static final Log logger = LogFactory.getLog(PropertiesUtil.class);
	private static PropertiesUtil instance = new PropertiesUtil();

	private PropertiesUtil() {
		// FIXME if its not caching properties files to not load the same one
		// twice, does it make sense to it to be a Singleton?
	}

	public static PropertiesUtil getInstance() {
		return instance;
	}

	public Properties loadProperties(String propsName) {
		PropertiesConfiguration configuration = null;
		try {
			URL url = getClass().getResource(propsName);
			if (url == null)
				configuration = new PropertiesConfiguration(propsName); // propsName
																		// is an
																		// absolute
																		// path
																		// to
																		// the
																		// file.
			else
				configuration = new PropertiesConfiguration(url); // propsName
																	// is a path
																	// to the
																	// file and
																	// was found
																	// in the
																	// classpath.
			return ConfigurationConverter.getProperties(configuration);
		} catch (Exception e) {
			logger.error(String.format(String.format("Error loading Properties file %s.", propsName), e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Pretty printing the property list
	 * 
	 * @param header
	 *            Header for the property list. if null, default header is
	 *            printed
	 * @param properties
	 *            <code>Properties</code> instance
	 * 
	 * @author vpinchevski@affsys.com
	 */
	public static void printProperties(String header, Properties properties) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);

		// Calculate the padding for the pretty print
		int len = 0;
		for (String s : properties.stringPropertyNames()) {
			if (s.length() > len)
				len = s.length();
		}
		if (header != null && !header.isEmpty())
			ps.println(String.format("\n %s %s\n", StringUtils.leftPad(" " + header, len, '+'),
					StringUtils.rightPad("", len, "+")));
		else
			ps.println(String.format("\n %s %s\n", StringUtils.leftPad(" Listing properties", len, '+'),
					StringUtils.rightPad("", len, "+")));
		// Print the output nicely
		for (String s : properties.stringPropertyNames())
			ps.println(String.format(" \t%s = %s", StringUtils.rightPad(s, len, ' '), properties.get(s)));
		logger.info(baos.toString());
	}

}
