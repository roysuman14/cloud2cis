package com.londonhydro.model.movein;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonalContact implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("bizPhone")
	private String bizPhone;
	@JsonProperty("bizPhoneExt")
	private String bizPhoneExt;
	@JsonProperty("cellPhoneNumber")
	private String cellPhoneNumber;
	@JsonProperty("resiPhone")
	private String resiPhone;
	@JsonProperty("notifyPhoneNumber")
	private String notifyPhoneNumber;

	public String getBizPhone() {
		return bizPhone;
	}

	public void setBizPhone(String bizPhone) {
		this.bizPhone = bizPhone;
	}

	public String getBizPhoneExt() {
		return bizPhoneExt;
	}

	public void setBizPhoneExt(String bizPhoneExt) {
		this.bizPhoneExt = bizPhoneExt;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getResiPhone() {
		return resiPhone;
	}

	public void setResiPhone(String resiPhone) {
		this.resiPhone = resiPhone;
	}

	public String getNotifyPhoneNumber() {
		return notifyPhoneNumber;
	}

	public void setNotifyPhoneNumber(String notifyPhoneNumber) {
		this.notifyPhoneNumber = notifyPhoneNumber;
	}
}
