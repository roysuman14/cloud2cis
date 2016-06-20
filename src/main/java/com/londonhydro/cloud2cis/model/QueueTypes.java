package com.londonhydro.cloud2cis.model;

public enum QueueTypes {

	ChangePhoneNumber, ChangeEmailAddress, ChangeBillDeliveryMethod, ChangeMailingAddress, MeterReading, PaymentArrangement, PaymentNotification, MoveOut, MoveIn, Transfer, CreateBudgetBilling, UpdateBudgetBilling, ChangeFixedAddress, ChangeAccountAddress, ChangeMasterData;

	public static QueueTypes parse(String text) {

		if (ChangePhoneNumber.name().equalsIgnoreCase(text))
			return ChangePhoneNumber;
		if (ChangeEmailAddress.name().equalsIgnoreCase(text))
			return ChangeEmailAddress;
		if (ChangeFixedAddress.name().equalsIgnoreCase(text))
			return ChangeFixedAddress;
		if (ChangeAccountAddress.name().equalsIgnoreCase(text))
			return ChangeAccountAddress;
		if (ChangeBillDeliveryMethod.name().equalsIgnoreCase(text))
			return ChangeBillDeliveryMethod;
		if (ChangeMailingAddress.name().equalsIgnoreCase(text))
			return ChangeMailingAddress;
		if (MeterReading.name().equalsIgnoreCase(text))
			return MeterReading;
		if (PaymentArrangement.name().equalsIgnoreCase(text))
			return PaymentArrangement;
		if (PaymentNotification.name().equalsIgnoreCase(text))
			return PaymentNotification;
		if (MoveOut.name().equalsIgnoreCase(text))
			return MoveOut;
		if (MoveIn.name().equalsIgnoreCase(text))
			return MoveIn;
		if (Transfer.name().equalsIgnoreCase(text))
			return Transfer;
		if (CreateBudgetBilling.name().equalsIgnoreCase(text))
			return CreateBudgetBilling;
		if (UpdateBudgetBilling.name().equalsIgnoreCase(text))
			return UpdateBudgetBilling;
		if (ChangeMasterData.name().equalsIgnoreCase(text))
			return ChangeMasterData;

		throw new IllegalArgumentException(text);
	}

};
