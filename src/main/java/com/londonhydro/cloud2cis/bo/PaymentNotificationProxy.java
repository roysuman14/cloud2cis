package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.util.DataUtil;
import com.londonhydro.model.PaymentNotificationQueue;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess6Type;
import com.londonhydro.sap.model.BusinessProcess6Type.MasterData;
import com.londonhydro.sap.model.BusinessProcess6Type.PaymentDetail;
import com.londonhydro.sap.model.BusinessProcess6Type.PaymentDetail.ConfirmationNumber;
import com.londonhydro.sap.model.BusinessProcess6Type.PaymentDetail.PaymentMethod;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.PaymentConstant;

public class PaymentNotificationProxy {
	protected static final Log logger = LogFactory.getLog(PaymentNotificationProxy.class);

	public ServiceQueue marshal(List<PaymentNotificationQueue> paymentNotificationQueue)
			throws Exception {
		
		ServiceQueue sq = new ServiceQueue();
		BusinessProcess6Type submitPaymentNotification = new BusinessProcess6Type();
        
        for(PaymentNotificationQueue notificationQueue : paymentNotificationQueue){
        	
        	Calendar systemTime = Calendar.getInstance();
        	
        	submitPaymentNotification.setType(QueueTypes.PaymentNotification.toString());
        	submitPaymentNotification.setProcessID(Long.toString(paymentNotificationQueue.get(0).getTransactionId()));
        	submitPaymentNotification.setDateTime(systemTime);
        	
        	MasterData masterData = new MasterData();
        	
        	BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
        	billingAccountNumber.setValue(notificationQueue.getAccountId());
        	CustomerNumberType customerNumber = new CustomerNumberType();
        	customerNumber.setValue(notificationQueue.getCustomerId());
        	
        	masterData.setBillingAccountNumber(billingAccountNumber);
        	masterData.setCustomerNumber(customerNumber);       
        	
        	
        	PaymentDetail paymentDetail = new PaymentDetail();
        	paymentDetail.setAmount(String.valueOf(notificationQueue.getAmount()));
        	ConfirmationNumber confirmationNumber = new ConfirmationNumber();
        	confirmationNumber.setValue(notificationQueue.getConfirmationNumber() == null ? "" : notificationQueue.getConfirmationNumber());
        	paymentDetail.setConfirmationNumber(confirmationNumber);
        	paymentDetail.setPaymentDate(DataUtil.dateToCalendar(notificationQueue.getPaymentDate()));
        	PaymentMethod paymentMethod = new PaymentMethod();
        	String pm="";
        	if(notificationQueue.getPaymentMethod().equalsIgnoreCase(PaymentConstant.PAYMENT_METHOD_BANK)){
        		pm=PaymentConstant.PAYMENT_METHOD_BANK_CIS_CODE;
        	}
        	else if(notificationQueue.getPaymentMethod().equalsIgnoreCase(PaymentConstant.PAYMENT_METHOD_LH_DROPBOX)){
        		pm=PaymentConstant.PAYMENT_METHOD_LH_DROPBOX_CIS_CODE;
        	}
        	else if(notificationQueue.getPaymentMethod().equalsIgnoreCase(PaymentConstant.PAYMENT_METHOD_CREDIT_CARD)){
        		pm=PaymentConstant.PAYMENT_METHOD_CREDIT_CARD_CIS_CODE;
        	}
        	else if(notificationQueue.getPaymentMethod().equalsIgnoreCase(PaymentConstant.PAYMENT_METHOD_MAIL)){
        		pm=PaymentConstant.PAYMENT_METHOD_MAIL_CIS_CODE;
        	}
        	paymentMethod.setValue(pm);
        	paymentDetail.setPaymentMethod(paymentMethod);
        	submitPaymentNotification.setMasterData(masterData);
        	submitPaymentNotification.setPaymentDetail(paymentDetail);
        	
        	}  
        sq.getBusinessProcess6().add(submitPaymentNotification);
        return sq;
        }
	}