package com.londonhydro.cloud2cis.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.sap.model.BillingAccountNumberType;
import com.londonhydro.sap.model.BusinessProcess10Type;
import com.londonhydro.sap.model.BusinessProcess10Type.BudgetBillingDetail;
import com.londonhydro.sap.model.BusinessProcess10Type.BudgetBillingDetail.PaymentPlanType;
import com.londonhydro.sap.model.BusinessProcess11Type;
import com.londonhydro.sap.model.CustomerNumberType;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.utils.Constants;

public class BudgetBillingProxy {
	public ServiceQueue marshal(List<ServerQueue> updateBudgetBillingList) {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();
		BusinessProcess11Type updateBudgetBilling = new BusinessProcess11Type();

		updateBudgetBilling.setProcessID(Long.toString((updateBudgetBillingList.get(0).getTransactionId())));
		updateBudgetBilling.setType(QueueTypes.UpdateBudgetBilling.toString());
		updateBudgetBilling.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess11Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess11Type.MasterData();

		BillingAccountNumberType updateBillingAccountNumber = new BillingAccountNumberType();
		updateBillingAccountNumber.setValue(updateBudgetBillingList.get(0).getAccountId());
		masterData.setBillingAccountNumber(updateBillingAccountNumber);

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(updateBudgetBillingList.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		updateBudgetBilling.setMasterData(masterData);

		com.londonhydro.sap.model.BusinessProcess11Type.BudgetBillingDetail budgetBillingDetail = new com.londonhydro.sap.model.BusinessProcess11Type.BudgetBillingDetail();

		for (ServerQueue serverQueue : updateBudgetBillingList) {
			// if(serverQueue.getColumn().equals("original_amount"))
			// {
			// BudgetBillingPlanNumber budgetBillingPlanNumber = new
			// BudgetBillingPlanNumber();
			// budgetBillingPlanNumber.setValue("???"); //TODO: Review this
			// budgetBillingDetail.setBudgetBillingPlanNumber(budgetBillingPlanNumber);
			// }
			// else
			if (serverQueue.getColumn().equals("monthly_amount")) {
				budgetBillingDetail.setAmount(serverQueue.getNewValue());
			}
		}

		updateBudgetBilling.setBudgetBillingDetail(budgetBillingDetail);

		sq.getBusinessProcess11().add(updateBudgetBilling);
		return sq;
	}

	public ServiceQueue marshalCreate(List<ServerQueue> budgetBillingList) {
		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess10Type budgetBilling = new BusinessProcess10Type();

		budgetBilling.setProcessID(Long.toString((budgetBillingList.get(0).getTransactionId())));
		budgetBilling.setType(QueueTypes.CreateBudgetBilling.toString());
		budgetBilling.setDateTime(systemTime);

		com.londonhydro.sap.model.BusinessProcess10Type.MasterData masterData = new com.londonhydro.sap.model.BusinessProcess10Type.MasterData();

		BillingAccountNumberType billingAccountNumber = new BillingAccountNumberType();
		billingAccountNumber.setValue(budgetBillingList.get(0).getAccountId());
		masterData.setBillingAccountNumber(billingAccountNumber);

		CustomerNumberType customerNumber = new CustomerNumberType();
		customerNumber.setValue(budgetBillingList.get(0).getCustomerId());
		masterData.setCustomerNumber(customerNumber);

		budgetBilling.setMasterData(masterData);

		BudgetBillingDetail budgetBillingDetail = new BudgetBillingDetail();

		for (ServerQueue serverQueue : budgetBillingList) {
			if (serverQueue.getColumn().equals("suggested_amount")) {
				budgetBillingDetail.setSuggestedAmount(serverQueue.getNewValue());
			} else if (serverQueue.getColumn().equals("monthly_amount")) {
				budgetBillingDetail.setAmount(serverQueue.getNewValue());
			} else if (serverQueue.getColumn().equals("billing_day")) {
				budgetBillingDetail
						.setStartingMonth(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()));
				budgetBillingDetail
						.setStartingYear(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
			}
		}

		PaymentPlanType paymentPlanType = new PaymentPlanType();
		paymentPlanType.setValue(Constants.BUDGET_BILLING_PAYMENT_TYPE);
		budgetBillingDetail.setPaymentPlanType(paymentPlanType);

		budgetBilling.setBudgetBillingDetail(budgetBillingDetail);

		sq.getBusinessProcess10().add(budgetBilling);
		return sq;
	}

}
