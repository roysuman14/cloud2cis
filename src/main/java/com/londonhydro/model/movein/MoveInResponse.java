
package com.londonhydro.model.movein;

public class MoveInResponse {

	private Long transactionId;
	private String identificationCheck;
	private String creditCheck;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getIdentificationCheck() {
		return identificationCheck;
	}

	public void setIdentificationCheck(String identificationCheck) {
		this.identificationCheck = identificationCheck;
	}

	public String getCreditCheck() {
		return creditCheck;
	}

	public void setCreditCheck(String creditCheck) {
		this.creditCheck = creditCheck;
	}

}
