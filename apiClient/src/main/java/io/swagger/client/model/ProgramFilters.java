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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProgramFilters
 */

public class ProgramFilters
{
	@SerializedName("programTags")
	private List<ProgramTag> programTags = null;

	@SerializedName("actionType")
	private List<String> actionType = null;

	@SerializedName("customNotificationType")
	private List<String> customNotificationType = null;

	@SerializedName("managerNotificationType")
	private AssetEvent managerNotificationType = null;

	@SerializedName("investorNotificationType")
	private AssetEvent investorNotificationType = null;

	public ProgramFilters programTags(List<ProgramTag> programTags) {
		this.programTags = programTags;
		return this;
	}

	public ProgramFilters addProgramTagsItem(ProgramTag programTagsItem) {
		if (this.programTags == null) {
			this.programTags = new ArrayList<ProgramTag>();
		}
		this.programTags.add(programTagsItem);
		return this;
	}

	/**
	 * Get programTags
	 *
	 * @return programTags
	 **/
	@ApiModelProperty(value = "")
	public List<ProgramTag> getProgramTags() {
		return programTags;
	}

	public void setProgramTags(List<ProgramTag> programTags) {
		this.programTags = programTags;
	}

	public ProgramFilters actionType(List<String> actionType) {
		this.actionType = actionType;
		return this;
	}

	public ProgramFilters addActionTypeItem(String actionTypeItem) {
		if (this.actionType == null) {
			this.actionType = new ArrayList<String>();
		}
		this.actionType.add(actionTypeItem);
		return this;
	}

	/**
	 * Get actionType
	 *
	 * @return actionType
	 **/
	@ApiModelProperty(value = "")
	public List<String> getActionType() {
		return actionType;
	}

	public void setActionType(List<String> actionType) {
		this.actionType = actionType;
	}

	public ProgramFilters customNotificationType(List<String> customNotificationType) {
		this.customNotificationType = customNotificationType;
		return this;
	}

	public ProgramFilters addCustomNotificationTypeItem(String customNotificationTypeItem) {
		if (this.customNotificationType == null) {
			this.customNotificationType = new ArrayList<String>();
		}
		this.customNotificationType.add(customNotificationTypeItem);
		return this;
	}

	/**
	 * Get customNotificationType
	 *
	 * @return customNotificationType
	 **/
	@ApiModelProperty(value = "")
	public List<String> getCustomNotificationType() {
		return customNotificationType;
	}

	public void setCustomNotificationType(List<String> customNotificationType) {
		this.customNotificationType = customNotificationType;
	}

	public ProgramFilters managerNotificationType(AssetEvent managerNotificationType) {
		this.managerNotificationType = managerNotificationType;
		return this;
	}

	/**
	 * Get managerNotificationType
	 *
	 * @return managerNotificationType
	 **/
	@ApiModelProperty(value = "")
	public AssetEvent getManagerNotificationType() {
		return managerNotificationType;
	}

	public void setManagerNotificationType(AssetEvent managerNotificationType) {
		this.managerNotificationType = managerNotificationType;
	}

	public ProgramFilters investorNotificationType(AssetEvent investorNotificationType) {
		this.investorNotificationType = investorNotificationType;
		return this;
	}

	/**
	 * Get investorNotificationType
	 *
	 * @return investorNotificationType
	 **/
	@ApiModelProperty(value = "")
	public AssetEvent getInvestorNotificationType() {
		return investorNotificationType;
	}

	public void setInvestorNotificationType(AssetEvent investorNotificationType) {
		this.investorNotificationType = investorNotificationType;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramFilters programFilters = (ProgramFilters) o;
		return Objects.equals(this.programTags, programFilters.programTags) &&
				Objects.equals(this.actionType, programFilters.actionType) &&
				Objects.equals(this.customNotificationType, programFilters.customNotificationType) &&
				Objects.equals(this.managerNotificationType, programFilters.managerNotificationType) &&
				Objects.equals(this.investorNotificationType, programFilters.investorNotificationType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(programTags, actionType, customNotificationType, managerNotificationType, investorNotificationType);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramFilters {\n");

		sb.append("    programTags: ").append(toIndentedString(programTags)).append("\n");
		sb.append("    actionType: ").append(toIndentedString(actionType)).append("\n");
		sb.append("    customNotificationType: ").append(toIndentedString(customNotificationType)).append("\n");
		sb.append("    managerNotificationType: ").append(toIndentedString(managerNotificationType)).append("\n");
		sb.append("    investorNotificationType: ").append(toIndentedString(investorNotificationType)).append("\n");
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
