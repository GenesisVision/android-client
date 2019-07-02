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
 * ExternalTransactionDetails
 */

public class ExternalTransactionDetails
{
	@SerializedName("description")
	private String description = null;

	@SerializedName("descriptionUrl")
	private String descriptionUrl = null;

	@SerializedName("fromAddress")
	private String fromAddress = null;

	@SerializedName("isEnableActions")
	private Boolean isEnableActions = null;

	public ExternalTransactionDetails description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@ApiModelProperty(value = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExternalTransactionDetails descriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
		return this;
	}

	/**
	 * Get descriptionUrl
	 *
	 * @return descriptionUrl
	 **/
	@ApiModelProperty(value = "")
	public String getDescriptionUrl() {
		return descriptionUrl;
	}

	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}

	public ExternalTransactionDetails fromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
		return this;
	}

	/**
	 * Get fromAddress
	 *
	 * @return fromAddress
	 **/
	@ApiModelProperty(value = "")
	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public ExternalTransactionDetails isEnableActions(Boolean isEnableActions) {
		this.isEnableActions = isEnableActions;
		return this;
	}

	/**
	 * Get isEnableActions
	 *
	 * @return isEnableActions
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsEnableActions() {
		return isEnableActions;
	}

	public void setIsEnableActions(Boolean isEnableActions) {
		this.isEnableActions = isEnableActions;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExternalTransactionDetails externalTransactionDetails = (ExternalTransactionDetails) o;
		return Objects.equals(this.description, externalTransactionDetails.description) &&
				Objects.equals(this.descriptionUrl, externalTransactionDetails.descriptionUrl) &&
				Objects.equals(this.fromAddress, externalTransactionDetails.fromAddress) &&
				Objects.equals(this.isEnableActions, externalTransactionDetails.isEnableActions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, descriptionUrl, fromAddress, isEnableActions);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExternalTransactionDetails {\n");

		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    descriptionUrl: ").append(toIndentedString(descriptionUrl)).append("\n");
		sb.append("    fromAddress: ").append(toIndentedString(fromAddress)).append("\n");
		sb.append("    isEnableActions: ").append(toIndentedString(isEnableActions)).append("\n");
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
