package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.PaymentArrangementsProxy;
import com.londonhydro.exception.CISException;
import com.londonhydro.model.PaymentArrangementsQueue;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

/**
 * Job in charge of sending Payment Notifications (BusinessProcess5) to SAP.
 * 
 * @author TCS
 */
public class PushPaymentArrangementsToSAP extends SAPAbstractJob
{
    
	public void execute(List<PaymentArrangementsQueue> paymentArrangementsQueues) throws Exception
    {
    	Map<Long, List<PaymentArrangementsQueue>> paymentArrangementsQueuesMap = createServerQueuesMap(paymentArrangementsQueues);
        
        if(paymentArrangementsQueuesMap.isEmpty())
        {
            logger.info("There are no Payment Arrangements Queues to be sent.");
        }
        else
        {

			logger.info("Request to sent to SAP. request# "
					+ paymentArrangementsQueues.size() + " map size# "
					+ paymentArrangementsQueuesMap.size());
			for (Map.Entry<Long, List<PaymentArrangementsQueue>> entry : paymentArrangementsQueuesMap
					.entrySet()) {

				ServiceQueue sq = new ServiceQueue();

				sq = (new PaymentArrangementsProxy()).marshal(entry.getValue());
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

    private Map<Long, List<PaymentArrangementsQueue>> createServerQueuesMap(
			List<PaymentArrangementsQueue> serverQueues) {

		Map<Long, List<PaymentArrangementsQueue>> serverQueueMap = new HashMap<Long, List<PaymentArrangementsQueue>>();

		for (PaymentArrangementsQueue serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getTransactionId()) == null) {
				List<PaymentArrangementsQueue> sqList = new ArrayList<PaymentArrangementsQueue>();
				serverQueueMap.put(serverQueue.getTransactionId(), sqList);
			}
			serverQueueMap.get(serverQueue.getTransactionId()).add(serverQueue);
		}
		return serverQueueMap;
	}  
        
}
