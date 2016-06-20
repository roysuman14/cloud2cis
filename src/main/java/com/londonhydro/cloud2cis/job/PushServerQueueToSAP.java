package com.londonhydro.cloud2cis.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.londonhydro.cloud2cis.bo.AccountMailingAddressProxy;
import com.londonhydro.cloud2cis.bo.BudgetBillingProxy;
import com.londonhydro.cloud2cis.bo.EmailAddressProxy;
import com.londonhydro.cloud2cis.bo.IdentificationDataProxy;
import com.londonhydro.cloud2cis.bo.MailingAddressProxy;
import com.londonhydro.cloud2cis.bo.MeterReadingProxy;
import com.londonhydro.cloud2cis.bo.PaperlessBillingProxy;
import com.londonhydro.cloud2cis.bo.PhoneNumberProxy;
import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.exception.CISException;
import com.londonhydro.sap.SAPErrorCode;
import com.londonhydro.sap.model.ServiceQueue;

/**
 * Job in charge of sending Server Queues to SAP.
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class PushServerQueueToSAP extends SAPAbstractJob {
	Calendar systemTime;

	public void execute(List<ServerQueue> serverQueues) throws Exception {

		systemTime = Calendar.getInstance();
		Map<Long, List<ServerQueue>> serverQueuesMap = createServerQueuesMap(serverQueues);

		if (serverQueuesMap.isEmpty())
			logger.info("There are no Server Queues to be sent.");
		else {

			for (Map.Entry<Long, List<ServerQueue>> entry : serverQueuesMap
					.entrySet()) {

				ServiceQueue sq = new ServiceQueue();

				QueueTypes serverType = QueueTypes.valueOf(entry.getValue()
						.get(0).getTransactionDescription());

				switch (serverType) {
				case ChangePhoneNumber:
					sq = (new PhoneNumberProxy()).marshal(entry.getValue());
					break;
				case ChangeEmailAddress:
					sq = (new EmailAddressProxy()).marshal(entry.getValue());
					break;
				case ChangeBillDeliveryMethod:
					sq = (new PaperlessBillingProxy())
							.marshal(entry.getValue());
					break;
				case MeterReading:
					sq = (new MeterReadingProxy()).marshal(entry.getValue());
					break;
				case CreateBudgetBilling:
					sq = (new BudgetBillingProxy()).marshalCreate(entry.getValue());
					break;
				case UpdateBudgetBilling:
					sq = (new BudgetBillingProxy()).marshal(entry.getValue());
					break;
				case ChangeMailingAddress:
					sq = (new MailingAddressProxy()).marshal(entry.getValue());
					break;
				case ChangeAccountAddress:
					sq = (new AccountMailingAddressProxy()).marshal(entry
							.getValue());
					break;
				case ChangeMasterData:
					sq = (new IdentificationDataProxy()).marshal(entry
							.getValue());
					break;
				default:
					break;
				}

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

	private Map<Long, List<ServerQueue>> createServerQueuesMap(
			List<ServerQueue> serverQueues) {

		Map<Long, List<ServerQueue>> serverQueueMap = new HashMap<Long, List<ServerQueue>>();

		for (ServerQueue serverQueue : serverQueues) {
			if (serverQueueMap.get(serverQueue.getTransactionId()) == null) {
				List<ServerQueue> sqList = new ArrayList<ServerQueue>();
				serverQueueMap.put(serverQueue.getTransactionId(), sqList);
			}

			serverQueueMap.get(serverQueue.getTransactionId()).add(serverQueue);

		}

		return serverQueueMap;
	}

}
