
package com.londonhydro.model.movein;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreditCheckResponse {

	public static final String SKIP = "SKIP";
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";

	public static final String SKIP_CC = "SKIP_CREDITCHECK";
	public static final String WITH_SD = "MOVEIN_ WITHOUT_SD";
	public static final String WITHOUT_SD = "MOVEIN_WITH_SD";
	public static final String PARK_DOC = "MOVEIN_PARK_DOCUMET";
	public static final String BUSINESS_MOVEIN = "BUSINESS_MOVEIN";

	@JsonProperty("identificationCheck")
	private String identificationCheck;
	@JsonProperty("creditCheckStatus")
	private String creditCheckStatus;
	@JsonProperty("decision")
	private String decision;

	@JsonProperty("moveinRequestId")
	private String moveinRequestId;

	public String getMoveinRequestId() {
		return moveinRequestId;
	}

	public void setMoveinRequestId(String moveinRequestId) {
		this.moveinRequestId = moveinRequestId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getIdentificationCheck() {
		return identificationCheck;
	}

	public void setIdentificationCheck(String identificationCheck) {
		this.identificationCheck = identificationCheck;
	}

	public String getCreditCheckStatus() {
		return creditCheckStatus;
	}

	public void setCreditCheckStatus(String creditCheckStatus) {
		this.creditCheckStatus = creditCheckStatus;
	}

}
