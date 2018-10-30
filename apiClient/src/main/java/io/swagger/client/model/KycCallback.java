/*
 * Core API v1.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * KycCallback
 */

public class KycCallback
{
	@SerializedName("applicantId")
	private String applicantId = null;

	@SerializedName("inspectionId")
	private String inspectionId = null;

	@SerializedName("correlationId")
	private String correlationId = null;

	@SerializedName("externalUserId")
	private String externalUserId = null;

	@SerializedName("success")
	private Boolean success = null;

	@SerializedName("details")
	private Object details = null;

	@SerializedName("type")
	private String type = null;

	@SerializedName("review")
	private Review review = null;

	public KycCallback applicantId(String applicantId) {
		this.applicantId = applicantId;
		return this;
	}

	/**
	 * Get applicantId
	 *
	 * @return applicantId
	 **/
	@ApiModelProperty(value = "")
	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public KycCallback inspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
		return this;
	}

	/**
	 * Get inspectionId
	 *
	 * @return inspectionId
	 **/
	@ApiModelProperty(value = "")
	public String getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}

	public KycCallback correlationId(String correlationId) {
		this.correlationId = correlationId;
		return this;
	}

	/**
	 * Get correlationId
	 *
	 * @return correlationId
	 **/
	@ApiModelProperty(value = "")
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public KycCallback externalUserId(String externalUserId) {
		this.externalUserId = externalUserId;
		return this;
	}

	/**
	 * Get externalUserId
	 *
	 * @return externalUserId
	 **/
	@ApiModelProperty(value = "")
	public String getExternalUserId() {
		return externalUserId;
	}

	public void setExternalUserId(String externalUserId) {
		this.externalUserId = externalUserId;
	}

	public KycCallback success(Boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * Get success
	 *
	 * @return success
	 **/
	@ApiModelProperty(value = "")
	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public KycCallback details(Object details) {
		this.details = details;
		return this;
	}

	/**
	 * Get details
	 *
	 * @return details
	 **/
	@ApiModelProperty(value = "")
	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public KycCallback type(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@ApiModelProperty(value = "")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public KycCallback review(Review review) {
		this.review = review;
		return this;
	}

	/**
	 * Get review
	 *
	 * @return review
	 **/
	@ApiModelProperty(value = "")
	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		KycCallback kycCallback = (KycCallback) o;
		return Objects.equals(this.applicantId, kycCallback.applicantId) &&
				Objects.equals(this.inspectionId, kycCallback.inspectionId) &&
				Objects.equals(this.correlationId, kycCallback.correlationId) &&
				Objects.equals(this.externalUserId, kycCallback.externalUserId) &&
				Objects.equals(this.success, kycCallback.success) &&
				Objects.equals(this.details, kycCallback.details) &&
				Objects.equals(this.type, kycCallback.type) &&
				Objects.equals(this.review, kycCallback.review);
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicantId, inspectionId, correlationId, externalUserId, success, details, type, review);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class KycCallback {\n");

		sb.append("    applicantId: ").append(toIndentedString(applicantId)).append("\n");
		sb.append("    inspectionId: ").append(toIndentedString(inspectionId)).append("\n");
		sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
		sb.append("    externalUserId: ").append(toIndentedString(externalUserId)).append("\n");
		sb.append("    success: ").append(toIndentedString(success)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    review: ").append(toIndentedString(review)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}

