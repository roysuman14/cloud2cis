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
import com.londonhydro.model.moveout.MoveOutRequest;
import com.londonhydro.utils.RestEasyUtils;

public class GetMoveOutJob extends AbstractJob {

	private static final Log logger = LogFactory.getLog(GetMoveOutJob.class);

	@Inject
	@Named("com.londonhydro.lh.server.url")
	private String lhServer;

	@Inject
	@Named("com.londonhydro.broker.jobs.moveOut.endPoint")
	private String moveOutEndPoint;

	@Inject
	PushMoveOutToSAP pushMoveOutToSAP;

	@Inject
	private RestEasyUtils restEasyUtils;

	@SuppressWarnings("unchecked")
	public void execute() throws Exception {

		ClientResponse<?> response = null;

		try {

			// Getting transactions from LH and storing them in the DB

			response = restEasyUtils.invokeCall(lhServer, moveOutEndPoint, null, null, null, null,
					RestEasyUtils.HTTP_METHOD.GET);

			if (response.getStatus() == 200) {
				List<MoveOutRequest> moveOutRequestList = response.getEntity(new GenericType<List<MoveOutRequest>>() {
				});

				if (moveOutRequestList != null && !moveOutRequestList.isEmpty()) {

					pushMoveOutToSAP.execute(moveOutRequestList);

					// Sending back transactionIds stored in the DB so LH
					// Portal
					// can move them to the processed table
					for (MoveOutRequest sq : moveOutRequestList) {
						logger.info("Data sent to CIS. Acknowledge the request " + sq.getTransactionId());
					}
					MultivaluedMap<String, String> formParams = addFormParams(moveOutRequestList);
					response = restEasyUtils.invokeCall(lhServer, moveOutEndPoint, null, formParams, null, null,
							RestEasyUtils.HTTP_METHOD.PUT);

					if (response.getStatus() != 200) {

						logger.error(
								String.format("| Failed when PUT %s                                               |",
										moveOutEndPoint));
						logger.error(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					} else {
						logger.info(
								"The following move in transactions has been processed                                      |");
						logger.info(String.format("| Transaction Ids = %s", formParams.get("transactionId")));
					}

				} else {
					logger.info("The move-out request are empty");
				}

			}

			else if (response.getStatus() == 400) {
				logger.info("No move-out request found in the response.");
			}

			else
				logger.error("Failed when GET " + lhServer + moveOutEndPoint);

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
	private MultivaluedMap addFormParams(List<MoveOutRequest> serverQueues) {

		MultivaluedMap<String, String> formParameters = new MultivaluedMapImpl<String, String>();
		Map<Long, Long> transactions = new HashMap<Long, Long>();
		for (MoveOutRequest serverQueue : serverQueues) {
			String transactionId = serverQueue.getTransactionId().toString();
			if (!transactions.containsValue(serverQueue.getTransactionId()))
				formParameters.add("transactionId", transactionId);
			transactions.put(serverQueue.getTransactionId(), serverQueue.getTransactionId());
		}
		return formParameters;
	}

}
