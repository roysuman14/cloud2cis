package com.londonhydro.model.movein;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.londonhydro.model.BaseEntity;
import com.londonhydro.model.Ideable;

public class PersonalInfo extends BaseEntity<Long> implements Ideable<Long>, Cloneable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("employerAddress")
	private String employerAddress;
	@JsonProperty("employerName")
	private String employerName;

	@JsonProperty("personalId")
	private String personalId;
	@JsonProperty("personalIdType")
	private String personalIdType;
	@JsonProperty("photoId")
	private String photoId;
	@JsonProperty("photoIdType")
	private String photoIdType;

	@JsonProperty("isStudent")
	private boolean student;
	@JsonProperty("studentInfo")
	private Student studentInfo;

	@JsonIgnore
	private transient ObjectMapper mapper = new ObjectMapper();

	public Date getBirthDate() {
		return birthDate;
	}

	public String getEmployerAddress() {
		return employerAddress;
	}

	public String getEmployerName() {
		return employerName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getPersonalId() {
		return personalId;
	}

	public String getPersonalIdType() {
		return personalIdType;
	}

	public String getPhotoId() {
		return photoId;
	}

	public String getPhotoIdType() {
		return photoIdType;
	}

	public Student getStudentInfo() {
		return studentInfo;
	}

	public boolean isStudent() {
		return student;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public void setPersonalIdType(String personalIdType) {
		this.personalIdType = personalIdType;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public void setPhotoIdType(String photoIdType) {
		this.photoIdType = photoIdType;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public void setStudentInfo(Student studentInfo) {
		this.studentInfo = studentInfo;
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

}
