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
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.utils.RestEasyUtils;

public class GetServerQueueJob extends AbstractJob {

	private static final Log logger = LogFactory.getLog(GetServerQueueJob.class);

	@Inject
	@Named("com.londonhydro.lh.server.url")
	private String lhServer;

	@Inject
	@Named("com.londonhydro.broker.jobs.queueService.endPoint")
	private String queueServiceEndPoint;

	@Inject
	PushServerQueueToSAP pushServerQueueToSAP;

	@Inject
	private RestEasyUtils restEasyUtils;

	@SuppressWarnings("unchecked")
	public void execute() throws Exception {

		ClientResponse<?> response = null;

		try {

			// Getting transactions from LH and storing them in the DB

			response = restEasyUtils.invokeCall(lhServer, queueServiceEndPoint, null, null, null, null,
					RestEasyUtils.HTTP_METHOD.GET);

			if (response.getStatus() == 200) {
				List<ServerQueue> serverQueues = response.getEntity(new GenericType<List<ServerQueue>>() {
				});

				if (serverQueues != null && !serverQueues.isEmpty()) {

					pushServerQueueToSAP.execute(serverQueues);

					// Sending back transactionIds stored in the DB so LH
					// Portal
					// can move them to the processed table
					for (ServerQueue sq : serverQueues) {
						logger.info("Data sent to CIS. Acknowledge the request " + sq.getTransactionId());
					}
					MultivaluedMap<String, String> formParams = addFormParams(serverQueues);
					response = restEasyUtils.invokeCall(lhServer, queueServiceEndPoint, null, formParams, null, null,
							RestEasyUtils.HTTP_METHOD.PUT);

					if (response.getStatus() != 200) {

						logger.error(
								String.format("| Failed when PUT %s                                               |",
										queueServiceEndPoint));
						logger.error(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					} else {
						logger.info(
								"The following server queue transactions has been processed                                      |");
						logger.info(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					}

				} else {
					logger.info("The server queues are empty");
				}

			}

			else if (response.getStatus() == 400) {
				logger.info("No server queues found in the response.");
			}

			else
				logger.error("Failed when GET " + lhServer + queueServiceEndPoint);

		} catch (ClientResponseFailure e) {

			logger.error(String.format(
					"The client failed to connect to the server(" + lhServer
							+ "). Verify the server is up and running - Exception: %s",
					Throwables.getStackTraceAsString(e)));

		} catch (Exception e) {

			logger.error(String.format("There was an unexpected problem - Exception: %s",
					Throwables.getStackTraceAsString(e)));

		}

	}

	@SuppressWarnings("rawtypes")
	private MultivaluedMap addFormParams(List<ServerQueue> serverQueues) {

		MultivaluedMap<String, String> formParameters = new MultivaluedMapImpl<String, String>();
		Map<Long, Long> transactions = new HashMap<Long, Long>();
		for (ServerQueue serverQueue : serverQueues) {
			String transactionId = serverQueue.getTransactionId().toString();
			if (!transactions.containsValue(serverQueue.getTransactionId()))
				formParameters.add("transactionId", transactionId);
			transactions.put(serverQueue.getTransactionId(), serverQueue.getTransactionId());
		}
		return formParameters;
	}

}
