package com.londonhydro.cloud2cis.model;


/**
 * These types match the types SAP is expecting to receive.
 * Please don't modify unless double checked before.
 * 
 *   o   Business Process 0  -----  type : ChangePhoneNumber
 *   o   Business Process 1  -----  type : ChangeEmailAddress
 *   o   Business Process 2  -----  type : ChangeBillDeliveryMethod
 *   o   Business Process 3  -----  type : ChangeMailingAddress
 *   o   Business Process 4  -----  type : MeterReading
 *   o   Business Process 5  -----  type : PaymentArrangement
 *   o   Business Process 6  -----  type : PaymentNotification
 *   o   Business Process 7  -----  type : MoveOut
 *   o   Business Process 8  -----  type : MoveIn
 *   o   Business Process 9  -----  type : Transfer
 *   o   Business Process 10 -----  type : CreateBudgetBilling
 *   o   Business Process 11 -----  type : UpdateBudgetBilling
 *   o   Business Process 12 -----  type : FixedMailingAddress
 *   o   Business Process 13 -----  type : ChangeMasterData
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 *
 */
public enum QueueTypes
{

    ChangePhoneNumber,
    ChangeEmailAddress,
    ChangeBillDeliveryMethod,
    ChangeMailingAddress,
    MeterReading,
    PaymentArrangement,
    PaymentNotification,
    MoveOut,
    MoveIn,
    Transfer,
    CreateBudgetBilling,
    UpdateBudgetBilling,
    ChangeFixedAddress,
    ChangeMasterData;

    public static QueueTypes parse(String text)
    {

        if (ChangePhoneNumber.name().equalsIgnoreCase(text))
            return ChangePhoneNumber;
        if (ChangeEmailAddress.name().equalsIgnoreCase(text))
            return ChangeEmailAddress;
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
        
        throw new IllegalArgumentException(text);
    }
    
};
