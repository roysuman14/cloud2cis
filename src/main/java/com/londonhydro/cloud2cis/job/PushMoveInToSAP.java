package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.MoveInProxy;
import com.londonhydro.model.movein.MoveInRequest;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

/**
 * Job in charge of sending Server Queues to SAP.
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class PushMoveInToSAP extends SAPAbstractJob {
	Calendar systemTime;

	public void execute(List<MoveInRequest> moveInRequestList) throws Exception {

		systemTime = Calendar.getInstance();
		Map<Long, List<MoveInRequest>> moveInMap = createServerQueuesMap(moveInRequestList);

		if (moveInMap.isEmpty()) {
			logger.info("There are no MoveIn to be sent.");
		} else {
			logger.info("Request to sent to SAP. request# "
					+ moveInRequestList.size() + " map size# "
					+ moveInMap.size());
			for (Map.Entry<Long, List<MoveInRequest>> entry : moveInMap
					.entrySet()) {				

				ServiceQueue sq = new ServiceQueue();

				// QueueTypes serverType =
				// QueueTypes.valueOf(entry.getValue().get(0).getTransactionDescription());				
				sq = (new MoveInProxy()).marshal(entry.getValue());
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

				}

			}

		}

	}

	private Map<Long, List<MoveInRequest>> createServerQueuesMap(
			List<MoveInRequest> serverQueues) {

		Map<Long, List<MoveInRequest>> serverQueueMap = new HashMap<Long, List<MoveInRequest>>();

		for (MoveInRequest serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getTransactionId()) == null) {
				List<MoveInRequest> sqList = new ArrayList<MoveInRequest>();
				serverQueueMap.put(serverQueue.getTransactionId(), sqList);
			}
			serverQueueMap.get(serverQueue.getTransactionId()).add(serverQueue);
		}
		return serverQueueMap;
	}

}
