package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.MoveOutProxy;
import com.londonhydro.exception.CISException;
import com.londonhydro.model.moveout.MoveOutRequest;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

/**
 * Job in charge of sending Server Queues to SAP.
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class PushMoveOutToSAP extends SAPAbstractJob {

	public void execute(List<MoveOutRequest> moveOutRequestList)
			throws Exception {

		Map<Long, List<MoveOutRequest>> moveOutMap = createServerQueuesMap(moveOutRequestList);

		if (moveOutMap.isEmpty())
			logger.info("There are no MoveOut to be sent.");
		else {

			for (Map.Entry<Long, List<MoveOutRequest>> entry : moveOutMap
					.entrySet()) {

				ServiceQueue sq = new ServiceQueue();

				// QueueTypes serverType =
				// QueueTypes.valueOf(entry.getValue().get(0).getTransactionDescription());

				sq = (new MoveOutProxy()).marshal(entry.getValue());
				Integer responseStatus = sendServiceQueue(sq);
				if (responseStatus != null
						&& responseStatus == SAPErrorCode.SAP_SUCCESS_STATUS_CODE
								.getCode()) {
					logger.info(String
							.format("Server Queues have been sent to SAP. Server Queues from id=%d to id=%d",
									entry.getValue().get(0).getId(),
									entry.getValue()
											.get(entry.getValue().size() - 1)
											.getId()));
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

	private Map<Long, List<MoveOutRequest>> createServerQueuesMap(
			List<MoveOutRequest> serverQueues) {

		Map<Long, List<MoveOutRequest>> serverQueueMap = new HashMap<Long, List<MoveOutRequest>>();

		for (MoveOutRequest serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getTransactionId()) == null) {
				List<MoveOutRequest> sqList = new ArrayList<MoveOutRequest>();
				serverQueueMap.put(serverQueue.getTransactionId(), sqList);
			}
			serverQueueMap.get(serverQueue.getTransactionId()).add(serverQueue);
		}
		return serverQueueMap;
	}

}
