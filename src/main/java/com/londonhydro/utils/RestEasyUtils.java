package com.londonhydro.utils;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.google.inject.Inject;
import com.londonhydro.inject.PortalAuthenticationProvider;

/**
 * Utility module created to wrap all the content concerning to the
 * RestEasyClient
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class RestEasyUtils {

	// private static final com.affsys.jcl.log.Log logger = Log.BROKER;

	@Inject
	PortalAuthenticationProvider portalAuthenticationProvider;
	
	public static enum HTTP_METHOD {
		GET, PUT, POST
	};

	public ClientRequest createRequest(String serverUrl, String endPoint) {

		ClientRequest request = new ClientRequest(serverUrl + endPoint);
		request.accept(MediaType.APPLICATION_JSON);
		portalAuthenticationProvider.addAuthorizationHeader(request);
		return request;
	}

	public void addQueryParams(ClientRequest request, Map<String, String> multivaluedMap) {
		for (Map.Entry<String, String> entry : multivaluedMap.entrySet()) {
			request.queryParameter(entry.getKey().toString(), entry.getValue().toString());
		}

	}

	public void addFormParams(ClientRequest request, MultivaluedMap<String, String> multivaluedMap) {

		MultivaluedMap<String, String> formParameters = request.getFormParameters();
		formParameters.putAll(multivaluedMap);
		/*
		 * for (Entry<String, List<String>> entry : multivaluedMap.entrySet()) {
		 * formParameters.add(entry.getKey().toString(), entry.getValue()); }
		 */

	}

	/**
	 * Method in charge of making HTTP requests using Rest Easy Client.
	 * 
	 * @param serverUrl
	 * @param endPoint
	 * @param queryParams
	 * @param formParams
	 * @param header
	 *            If provided the param 'body' must have the proper annotations
	 *            associated with the media Type. Rest Easy Client will marshal
	 *            the object automatically once making the request.
	 * @param body
	 *            Object containing proper RS annotations.
	 * @param method
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ClientResponse invokeCall(String serverUrl, String endPoint, Map<String, String> queryParams,
			MultivaluedMap<String, String> formParams, MediaType header, Object body, HTTP_METHOD method) {

		try {

			ClientRequest request = createRequest(serverUrl, endPoint);

			if (queryParams != null)
				addQueryParams(request, queryParams);

			if (formParams != null)
				addFormParams(request, formParams);

			if (header != null)
				request.body(header, body);

			ClientResponse response = null;
			switch (method) {
			case POST:
				response = request.post();
				break;
			case PUT:
				response = request.put();
				break;
			case GET:
				response = request.get();
				break;
			}

			return response;

		} catch (Exception e) {
			throw new RuntimeException(
					String.format("RestEasy ClientResponse failed. Error Message: " + e.getMessage()), e);
		}

	}

}
