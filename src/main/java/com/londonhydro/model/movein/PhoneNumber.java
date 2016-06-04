package com.londonhydro.model.movein;

import org.codehaus.jackson.annotate.JsonProperty;

public class PhoneNumber {

	public static final String PHONE_CATEGORY_RESIDENTIAL = "RESIDENTIAL";
	public static final String PHONE_CATEGORY_CELL = "CELL";
	public static final String PHONE_CATEGORY_BUSSINESS = "BUSINESS";
	public static final String PHONE_CATEGORY_NOTIFICATION = "NOTIFICATION";

	@JsonProperty("category")
	private String category;
	@JsonProperty("extension")
	private String extension;
	@JsonProperty("phoneNumber")
	private String phoneNumber;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Using Google Guava to compare provided object to me for equality.
	 * 
	 * @param obj
	 *            Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or
	 *         {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PhoneNumber other = (PhoneNumber) obj;

		return com.google.common.base.Objects.equal(this.extension, other.extension)
				&& com.google.common.base.Objects.equal(this.category, other.category)
				&& com.google.common.base.Objects.equal(this.phoneNumber, other.phoneNumber);

	}

	/**
	 * Uses Google Guava to assist in providing hash code of this PhoneNumbers
	 * instance.
	 * 
	 * @return My hash code.
	 */
	@Override
	public int hashCode() {
		return com.google.common.base.Objects.hashCode(this.extension, this.category, this.phoneNumber);

	}

	/**
	 * Method using Google Guava to provide String representation of this
	 * PhoneNumbers instance.
	 * 
	 * @return My String representation.
	 */
	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).addValue(this.category).addValue(this.extension)
				.addValue(this.phoneNumber).toString();
	}

}
