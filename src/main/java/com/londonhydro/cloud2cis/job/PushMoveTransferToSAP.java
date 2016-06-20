package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.MoveTransferProxy;
import com.londonhydro.exception.CISException;
import com.londonhydro.model.movein.TransferRequest;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

public class PushMoveTransferToSAP extends SAPAbstractJob {

	public void execute(List<TransferRequest> transferRequestList)
			throws Exception {

		Map<Long, List<TransferRequest>> moveInMap = createServerQueuesMap(transferRequestList);

		if (moveInMap.isEmpty()) {
			logger.info("There are no MoveIn to be sent.");
		} else {
			logger.info("Request to sent to SAP. request# "
					+ transferRequestList.size() + " map size# "
					+ moveInMap.size());
			for (Map.Entry<Long, List<TransferRequest>> entry : moveInMap
					.entrySet()) {

				ServiceQueue sq = new ServiceQueue();

				sq = (new MoveTransferProxy()).marshal(entry.getValue());
				Integer responseStatus = sendServiceQueue(sq);
				if (responseStatus != null
						&& responseStatus == SAPErrorCode.SAP_SUCCESS_STATUS_CODE
								.getCode()) {
					logger.info(String
							.format("Server Queues have been sent to SAP. Server Queues from id=%d to id=%d",
									entry.getValue().get(0).getMovein().getId(),
									entry.getValue()
											.get(entry.getValue().size() - 1)
											.getMovein().getId()));
				} else {

					SAPErrorCode sapErrorCode = SAPErrorCode
							.valueOf(responseStatus);
					logger.error(String
							.format("Failed when calling SAP end point [%s] - HTTP Status Code = %d | %s",
									sapWSEndPoint, sapErrorCode.getCode(),
									sapErrorCode.getDescription()));
					throw new CISException(sapErrorCode);

				}

			}

		}

	}

	private Map<Long, List<TransferRequest>> createServerQueuesMap(
			List<TransferRequest> serverQueues) {

		Map<Long, List<TransferRequest>> serverQueueMap = new HashMap<Long, List<TransferRequest>>();

		for (TransferRequest serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getMovein().getTransactionId()) == null) {
				List<TransferRequest> sqList = new ArrayList<TransferRequest>();
				serverQueueMap.put(serverQueue.getMovein().getTransactionId(),
						sqList);
			}
			serverQueueMap.get(serverQueue.getMovein().getTransactionId()).add(
					serverQueue);
		}
		return serverQueueMap;
	}

}
