/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                        *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  GetPaymentArrangementsQueueJob.java
 *
 *  Facility:   London Hydro Broker
 *
 *  Author:     Love Talwar, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  05Aug13     Love Talwar         Original version
 */

package com.londonhydro.cloud2cis.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.resteasy.util.GenericType;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.londonhydro.model.PaymentArrangementsQueue;
import com.londonhydro.utils.RestEasyUtils;

public class GetPaymentArrangementsJob extends AbstractJob {
	private static final Log logger = LogFactory
			.getLog(GetPaymentArrangementsJob.class);

	@Inject
	@Named("com.londonhydro.lh.server.url")
	private String lhServer;

	@Inject
	@Named("com.londonhydro.cloud2cis.jobs.paymentArrangementsQueueService.endPoint")
	private String paymentArrangementsQueueServiceEndPoint;

	@Inject
	PushPaymentArrangementsToSAP pushPaymentArrangementsToSAP;

	@Inject
	private RestEasyUtils restEasyUtils;

	@SuppressWarnings("unchecked")
	public void execute() throws Exception {

		ClientResponse<?> response = null;

		try {

			// Getting transactions from LH and storing them in the DB
			// TODO: Change the authentication process

			response = restEasyUtils.invokeCall(lhServer,
					paymentArrangementsQueueServiceEndPoint, null, null, null,
					null, RestEasyUtils.HTTP_METHOD.GET);

			if (response.getStatus() == 200) {
				List<PaymentArrangementsQueue> paymentArrangementsQueues = response
						.getEntity(new GenericType<List<PaymentArrangementsQueue>>() {
						});
				if (paymentArrangementsQueues != null
						&& !paymentArrangementsQueues.isEmpty()) {

					logger.info("Request recevied form MyAccount. "
							+ paymentArrangementsQueues.size());

					pushPaymentArrangementsToSAP
							.execute(paymentArrangementsQueues);

					// Sending back transactionIds stored in the DB so LH
					// Portal
					// can move them to the processed table
					for (PaymentArrangementsQueue sq : paymentArrangementsQueues) {
						logger.info("Data sent to CIS. Acknowledge the request "
								+ sq.getTransactionId());
					}
					MultivaluedMap<String, String> formParams = addFormParams(paymentArrangementsQueues);
					response = restEasyUtils.invokeCall(lhServer,
							paymentArrangementsQueueServiceEndPoint, null,
							formParams, null, null,
							RestEasyUtils.HTTP_METHOD.PUT);

					if (response.getStatus() != 200) {

						logger.error(String
								.format("| Failed when PUT %s                                               |",
										paymentArrangementsQueueServiceEndPoint));
						logger.error(String.format("| Transaction Ids = %s",
								formParams.get("transactionId")));
					} else {
						logger.info("The following move in transactions has been processed                                      |");
						logger.info(String.format("| Transaction Ids = %s",
								formParams.get("transactionId")));
					}

				} else
					logger.info("The payment arrangements queues are empty");
			} else if (response.getStatus() == 400) {
				logger.info("No payment arrangements queues found in the response.");
			}

			else
				logger.error("Failed when GET "
						+ paymentArrangementsQueueServiceEndPoint);

		} catch (ClientResponseFailure e) {

			logger.error(String
					.format("The client failed to connect to the server("
							+ lhServer
							+ "). Verify the server is up and running - Exception: %s",
							Throwables.getStackTraceAsString(e)));

		} catch (Exception e) {

			logger.error(String.format(
					"There was an unexpected problem - Exception: %s",
					Throwables.getStackTraceAsString(e)));

		}

	}

	@SuppressWarnings("rawtypes")
	private MultivaluedMap addFormParams(
			List<PaymentArrangementsQueue> paymentArrangementsQueues) {
		MultivaluedMap<String, String> formParameters = new MultivaluedMapImpl<String, String>();
		Map<Long, Long> transactions = new HashMap<Long, Long>();
		for (PaymentArrangementsQueue serverQueue : paymentArrangementsQueues) {
			String transactionId = serverQueue.getTransactionId().toString();
			if (!transactions.containsValue(serverQueue.getTransactionId())) {
				formParameters.add("paymentArrangementsId", transactionId);
			}
			transactions.put(serverQueue.getTransactionId(),
					serverQueue.getTransactionId());
		}
		return formParameters;

	}

}
