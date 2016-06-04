package com.londonhydro.model.movein;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class Student implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("studentId")
	private String studentId;
	@JsonProperty("schoolName")
	private String schoolName;
	@JsonProperty("parentName")
	private String parentName;
	@JsonProperty("parentPhone")
	private String parentPhone;
	@JsonProperty("parentAddress")
	private String parentAddress;
	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public String getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}

	/**
	 * Method using Google Guava to provide String representation of this
	 * MoveInRequest instance.
	 * 
	 * @return My String representation.
	 */
	@Override
	public String toString() {
		try {
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			return mapper.writeValueAsString(this);
		} catch (Exception ignore) {
		}
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
