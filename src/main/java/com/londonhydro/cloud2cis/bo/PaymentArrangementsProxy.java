package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.model.PaymentArrangementsQueue;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess5Type;
import com.londonhydro.sap.model.BusinessProcess5Type.PaymentArrangementDetails;
import com.londonhydro.sap.model.BusinessProcess5Type.PaymentArrangementDetails.ArrangementItem;
import com.londonhydro.sap.model.BusinessProcess5Type.PaymentArrangementDetails.ArrangementItem.PaymentMethod;
import com.londonhydro.sap.model.BusinessProcess5Type.PaymentArrangementHeader;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.PaymentConstant;

public class PaymentArrangementsProxy {
	protected static final Log logger = LogFactory
			.getLog(PaymentArrangementsProxy.class);

	public ServiceQueue marshal(
			List<PaymentArrangementsQueue> arrangementsQueues) throws Exception {
		ServiceQueue sq = new ServiceQueue();
		BusinessProcess5Type submitPaymentArrangement = new BusinessProcess5Type();

		for (PaymentArrangementsQueue arrangementsQueue : arrangementsQueues) {

			Calendar systemTime = Calendar.getInstance();

			submitPaymentArrangement.setProcessID(Long
					.toString(arrangementsQueue.getTransactionId()));
			submitPaymentArrangement.setType(QueueTypes.PaymentArrangement
					.toString());
			submitPaymentArrangement.setDateTime(systemTime);

			double paymentTotal = 0.0;

			PaymentArrangementDetails paymentArrangementDetails = new PaymentArrangementDetails();

			ArrangementItem arrangementItem = new ArrangementItem();
			arrangementItem.setAmount(Double.toString(arrangementsQueue
					.getInstallmentAmount()));
			arrangementItem.setItemNo(arrangementsQueue.getInstallmentId());
			arrangementItem.setPaymentDate(DataUtil
					.dateToCalendar(arrangementsQueue.getPaymentDate()));
			PaymentMethod paymentMethod = new PaymentMethod();
			String pm = "";
			if (arrangementsQueue.getPaymentMethod().equalsIgnoreCase(PaymentConstant.PAYMENT_METHOD_BANK)){
				pm = PaymentConstant.PAYMENT_METHOD_BANK_CIS_CODE;
			}
			else if (arrangementsQueue.getPaymentMethod().equalsIgnoreCase(
					PaymentConstant.PAYMENT_METHOD_LH_DROPBOX)){
				pm = PaymentConstant.PAYMENT_METHOD_LH_DROPBOX_CIS_CODE;
			}
			else if (arrangementsQueue.getPaymentMethod().equalsIgnoreCase(
					PaymentConstant.PAYMENT_METHOD_CREDIT_CARD)){
				pm =PaymentConstant.PAYMENT_METHOD_CREDIT_CARD_CIS_CODE;
			}
			else if (arrangementsQueue.getPaymentMethod().equalsIgnoreCase(
					PaymentConstant.PAYMENT_METHOD_MAIL)){
				pm = PaymentConstant.PAYMENT_METHOD_MAIL_CIS_CODE;
			}
			paymentMethod.setValue(pm);
			arrangementItem.setPaymentMethod(paymentMethod);

			paymentArrangementDetails.getArrangementItem().add(arrangementItem);

			paymentTotal += arrangementsQueue.getInstallmentAmount();

			submitPaymentArrangement
					.setPaymentArrangementDetails(paymentArrangementDetails);

			PaymentArrangementHeader paymentArrangementHeader = new PaymentArrangementHeader();
			BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
			billingAccountNumber.setValue(arrangementsQueue.getAccountId());
			paymentArrangementHeader
					.setBillingAccountNumber(billingAccountNumber);
			CustomerNumberType customerNumber = new CustomerNumberType();
			customerNumber.setValue(arrangementsQueue.getCustomerId());
			paymentArrangementHeader.setCustomerNumber(customerNumber);
			paymentArrangementHeader.setPaymentTotal(Double
					.toString(paymentTotal));

			submitPaymentArrangement
					.setPaymentArrangementHeader(paymentArrangementHeader);

		}
		sq.getBusinessProcess5().add(submitPaymentArrangement);
		return sq;
	}
}
