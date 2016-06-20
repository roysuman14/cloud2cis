package com.londonhydro.cloud2cis.bo;

import java.util.Calendar;
import java.util.List;

import com.londonhydro.cloud2cis.model.QueueTypes;
import com.londonhydro.cloud2cis.model.ServerQueue;
import com.londonhydro.sap.model.BusinessProcess4Type;
import com.londonhydro.sap.model.BusinessProcess4Type.MeterNumber;
import com.londonhydro.sap.model.BusinessProcess4Type.MeterReading;
import com.londonhydro.sap.model.ServiceQueue;

public class MeterReadingProxy {
	public ServiceQueue marshal(List<ServerQueue> meterReadingList) {

		Calendar systemTime = Calendar.getInstance();
		ServiceQueue sq = new ServiceQueue();

		BusinessProcess4Type submitMeterReading = new BusinessProcess4Type();

		submitMeterReading.setProcessID(Long.toString((meterReadingList.get(0).getTransactionId())));
		submitMeterReading.setType(QueueTypes.MeterReading.toString());
		submitMeterReading.setDateTime(systemTime);

		for (ServerQueue serverQueue : meterReadingList) {
			if (serverQueue.getColumn().equals("meter_id")) {
				MeterNumber meterNumber = new MeterNumber();
				meterNumber.setValue(serverQueue.getNewValue());
				submitMeterReading.setMeterNumber(meterNumber);
			} else if (serverQueue.getColumn().equals("read_date")) {
				submitMeterReading.setReadTime(serverQueue.getNewValue());
				submitMeterReading.setReadDate(serverQueue.getNewValue());
			} else if (serverQueue.getColumn().equals("read_value")) {
				MeterReading meterReading = new MeterReading();
				meterReading.setValue(serverQueue.getNewValue());
				submitMeterReading.setMeterReading(meterReading);
			}
		}
		sq.getBusinessProcess4().add(submitMeterReading);

		return sq;
	}
}
