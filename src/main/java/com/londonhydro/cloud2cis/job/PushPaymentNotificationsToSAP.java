package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.PaymentNotificationProxy;
import com.londonhydro.exception.CISException;
import com.londonhydro.model.PaymentNotificationQueue;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

/**
 * Job in charge of sending Payment Notifications (BusinessProcess6) to SAP.
 * 
 * @author TCS
 */
public class PushPaymentNotificationsToSAP extends SAPAbstractJob
{
    
    public void execute(List<PaymentNotificationQueue> paymentNotificationsQueues) throws Exception
    {
    	Map<Long, List<PaymentNotificationQueue>> paymentNotificationsQueuesMap  = createServerQueuesMap(paymentNotificationsQueues);
        
        if(paymentNotificationsQueuesMap.isEmpty())
        {
            logger.info("There are no Payment Noticiation Queues to be sent.");
        }
        else
        {
			logger.info("Request to sent to SAP. request# "
					+ paymentNotificationsQueues.size() + " map size# "
					+ paymentNotificationsQueuesMap.size());
			for (Map.Entry<Long, List<PaymentNotificationQueue>> entry : paymentNotificationsQueuesMap
					.entrySet()) {

				ServiceQueue sq = new ServiceQueue();

				sq = (new PaymentNotificationProxy()).marshal(entry.getValue());
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
    
    private Map<Long, List<PaymentNotificationQueue>> createServerQueuesMap(
			List<PaymentNotificationQueue> serverQueues) {

		Map<Long, List<PaymentNotificationQueue>> serverQueueMap = new HashMap<Long, List<PaymentNotificationQueue>>();

		for (PaymentNotificationQueue serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getTransactionId()) == null) {
				List<PaymentNotificationQueue> sqList = new ArrayList<PaymentNotificationQueue>();
				serverQueueMap.put(serverQueue.getTransactionId(), sqList);
			}
			serverQueueMap.get(serverQueue.getTransactionId()).add(serverQueue);
		}
		return serverQueueMap;
	} 
        
}
