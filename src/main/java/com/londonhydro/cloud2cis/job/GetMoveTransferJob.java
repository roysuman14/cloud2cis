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
import com.londonhydro.model.movein.MoveInRequest;
import com.londonhydro.model.movein.TransferRequest;
import com.londonhydro.utils.RestEasyUtils;

public class GetMoveTransferJob extends AbstractJob {

	private static final Log logger = LogFactory
			.getLog(GetMoveTransferJob.class);

	@Inject
	@Named("com.londonhydro.lh.server.url")
	private String lhServer;

	@Inject
	@Named("com.londonhydro.cloud2cis.jobs.transfer.endPoint")
	private String transferEndPoint;

	@Inject
	PushMoveTransferToSAP pushTransferToSAP;

	@Inject
	private RestEasyUtils restEasyUtils;

	@SuppressWarnings("unchecked")
	public void execute() throws Exception {

		ClientResponse<?> response = null;

		try {

			// Getting transactions from LH and storing them in the DB

			response = restEasyUtils.invokeCall(lhServer, transferEndPoint,
					null, null, null, null, RestEasyUtils.HTTP_METHOD.GET);

			if (response.getStatus() == 200) {
				List<TransferRequest> transferRequestList = response
						.getEntity(new GenericType<List<TransferRequest>>() {
						});

				if (transferRequestList != null
						&& !transferRequestList.isEmpty()) {

					logger.info("Request recevied form MyAccount. "
							+ transferRequestList.size());

					pushTransferToSAP.execute(transferRequestList);

					// Sending back transactionIds stored in the DB so LH
					// Portal
					// can move them to the processed table
					for (TransferRequest sq : transferRequestList) {
						logger.info("Data sent to CIS. Acknowledge the request "
								+ sq.getMovein().getTransactionId());
					}
					MultivaluedMap<String, String> formParams = addFormParams(transferRequestList);
					response = restEasyUtils.invokeCall(lhServer,
							transferEndPoint, null, formParams, null, null,
							RestEasyUtils.HTTP_METHOD.PUT);

					if (response.getStatus() != 200) {

						logger.error(String
								.format("| Failed when PUT %s                                               |",
										transferEndPoint));
						logger.error(String.format("| Transaction Ids = %s",
								formParams.get("transactionId")));
					} else {
						logger.info("The following move in transactions has been processed                                      |");
						logger.info(String.format("| Transaction Ids = %s",
								formParams.get("transactionId")));
					}

				} else {
					logger.info("The move-in request are empty");
				}

			}

			else if (response.getStatus() == 400) {
				logger.info("No move-in request found in the response.");
			}

			else
				logger.error("Failed when GET " + lhServer + transferEndPoint);

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
	private MultivaluedMap addFormParams(List<TransferRequest> serverQueues) {

		MultivaluedMap<String, String> formParameters = new MultivaluedMapImpl<String, String>();
		Map<Long, Long> transactions = new HashMap<Long, Long>();
		for (TransferRequest serverQueue : serverQueues) {
			String transactionId = serverQueue.getMovein().getTransactionId()
					.toString();
			if (!transactions.containsValue(serverQueue.getMovein()
					.getTransactionId())) {
				formParameters.add("moveinRequestId", transactionId);
			}
			transactions.put(serverQueue.getMovein().getTransactionId(),
					serverQueue.getMovein().getTransactionId());
		}
		return formParameters;
	}

}
