/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                        *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  GetPaymentNotificationQueueJob.java
 *
 *  Facility:   London Hydro Broker
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  02Aug13     Love Talwar         Original version
 */


package com.londonhydro.cloud2cis.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.resteasy.util.GenericType;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.londonhydro.model.PaymentNotificationQueue;
import com.londonhydro.utils.RestEasyUtils;


public class GetPaymentNotificationJob extends AbstractJob 
{
	private static final Log logger = LogFactory
			.getLog(GetPaymentNotificationJob.class);
	
	@Inject
	@Named("com.londonhydro.lh.server.url")
	private String lhServer;

	@Inject
	@Named("com.londonhydro.cloud2cis.jobs.paymentNotificationQueueService.endPoint")
	private String paymentNotificationQueueServiceEndPoint;

	@Inject
	PushPaymentNotificationsToSAP pushPaymentNotificationsToSAP;
	
	@Inject
	private RestEasyUtils restEasyUtils;
	
	@SuppressWarnings("unchecked")
	public void execute() throws Exception 
	{

		ClientResponse<?> response = null;
		
		try {
			
			// Getting transactions from LH and storing them in the DB
			// TODO: Change the authentication process
			
			response = restEasyUtils.invokeCall(lhServer, paymentNotificationQueueServiceEndPoint, null, null, null, null,
					RestEasyUtils.HTTP_METHOD.GET);
			
			if (response.getStatus() == 200) 
			{
			    List<PaymentNotificationQueue> paymentNotificationQueues = response.getEntity(new GenericType<List<PaymentNotificationQueue>>() {});
				if (paymentNotificationQueues != null && !paymentNotificationQueues.isEmpty())
				{
					logger.info("Request recevied form MyAccount. " + paymentNotificationQueues.size());
					
					pushPaymentNotificationsToSAP
							.execute(paymentNotificationQueues);

					// Sending back transactionIds stored in the DB so LH
					// Portal
					// can move them to the processed table
					for (PaymentNotificationQueue sq : paymentNotificationQueues) {
						logger.info("Data sent to CIS. Acknowledge the request " + sq.getTransactionId());
					}
					MultivaluedMap<String, String> formParams = addFormParams(paymentNotificationQueues);
					response = restEasyUtils.invokeCall(lhServer, paymentNotificationQueueServiceEndPoint, null, formParams, null, null,
							RestEasyUtils.HTTP_METHOD.PUT);
				
					if (response.getStatus() != 200) {

						logger.error(
								String.format("| Failed when PUT %s                                               |",
										paymentNotificationQueueServiceEndPoint));
						logger.error(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					} else {
						logger.info(
								"The following move in transactions has been processed                                      |");
						logger.info(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					}

				}else
					logger.info("The payment notifications queues are empty");
			}
			else if (response.getStatus() == 400)
            {
                logger.info("No payment notifications queues found in the response.");
            }
                            
            else                
                logger.error("Failed when GET " + paymentNotificationQueueServiceEndPoint);

		} catch (ClientProtocolException e) {
			
			logger.error(String.format("Client Protocol Error - Exception: %s", Throwables.getStackTraceAsString(e)));
			
		} catch (ClientResponseFailure e) {
			
			logger.error(String.format("The client failed to connect to the server(" + lhServer + "). Verify the server is up and running - Exception: %s", Throwables.getStackTraceAsString(e)));
			
		} catch (Exception e) {

			logger.error(String.format("There was an unexpected problem - Exception: %s", Throwables.getStackTraceAsString(e)));

		}

	}
	
	@SuppressWarnings("rawtypes")
	private MultivaluedMap addFormParams(List<PaymentNotificationQueue> paymentNotificationQueues) 
	{
		MultivaluedMap<String, String> formParameters = new MultivaluedMapImpl<String, String>();
		Map<Long, Long> transactions = new HashMap<Long, Long>();
		for (PaymentNotificationQueue serverQueue : paymentNotificationQueues) {
			String transactionId = serverQueue.getTransactionId().toString();
			if (!transactions.containsValue(serverQueue.getTransactionId())) {
				formParameters.add("paymentArrangementsId", transactionId);
			}
			transactions.put(serverQueue.getTransactionId(), serverQueue.getTransactionId());
		}
		return formParameters;
	}
	
}
