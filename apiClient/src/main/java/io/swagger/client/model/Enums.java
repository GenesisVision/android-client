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
 * Enums
 */

public class Enums
{
	@SerializedName("multiWallet")
	private MultiWalletFilters multiWallet = null;

	@SerializedName("program")
	private ProgramFilters program = null;

	@SerializedName("fund")
	private FundFilters fund = null;

	@SerializedName("event")
	private EventFilters event = null;

	@SerializedName("assetTypes")
	private List<String> assetTypes = null;

	public Enums multiWallet(MultiWalletFilters multiWallet) {
		this.multiWallet = multiWallet;
		return this;
	}

	/**
	 * Get multiWallet
	 *
	 * @return multiWallet
	 **/
	@ApiModelProperty(value = "")
	public MultiWalletFilters getMultiWallet() {
		return multiWallet;
	}

	public void setMultiWallet(MultiWalletFilters multiWallet) {
		this.multiWallet = multiWallet;
	}

	public Enums program(ProgramFilters program) {
		this.program = program;
		return this;
	}

	/**
	 * Get program
	 *
	 * @return program
	 **/
	@ApiModelProperty(value = "")
	public ProgramFilters getProgram() {
		return program;
	}

	public void setProgram(ProgramFilters program) {
		this.program = program;
	}

	public Enums fund(FundFilters fund) {
		this.fund = fund;
		return this;
	}

	/**
	 * Get fund
	 *
	 * @return fund
	 **/
	@ApiModelProperty(value = "")
	public FundFilters getFund() {
		return fund;
	}

	public void setFund(FundFilters fund) {
		this.fund = fund;
	}

	public Enums event(EventFilters event) {
		this.event = event;
		return this;
	}

	/**
	 * Get event
	 *
	 * @return event
	 **/
	@ApiModelProperty(value = "")
	public EventFilters getEvent() {
		return event;
	}

	public void setEvent(EventFilters event) {
		this.event = event;
	}

	public Enums assetTypes(List<String> assetTypes) {
		this.assetTypes = assetTypes;
		return this;
	}

	public Enums addAssetTypesItem(String assetTypesItem) {
		if (this.assetTypes == null) {
			this.assetTypes = new ArrayList<String>();
		}
		this.assetTypes.add(assetTypesItem);
		return this;
	}

	/**
	 * Get assetTypes
	 *
	 * @return assetTypes
	 **/
	@ApiModelProperty(value = "")
	public List<String> getAssetTypes() {
		return assetTypes;
	}

	public void setAssetTypes(List<String> assetTypes) {
		this.assetTypes = assetTypes;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Enums enums = (Enums) o;
		return Objects.equals(this.multiWallet, enums.multiWallet) &&
				Objects.equals(this.program, enums.program) &&
				Objects.equals(this.fund, enums.fund) &&
				Objects.equals(this.event, enums.event) &&
				Objects.equals(this.assetTypes, enums.assetTypes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(multiWallet, program, fund, event, assetTypes);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Enums {\n");

		sb.append("    multiWallet: ").append(toIndentedString(multiWallet)).append("\n");
		sb.append("    program: ").append(toIndentedString(program)).append("\n");
		sb.append("    fund: ").append(toIndentedString(fund)).append("\n");
		sb.append("    event: ").append(toIndentedString(event)).append("\n");
		sb.append("    assetTypes: ").append(toIndentedString(assetTypes)).append("\n");
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

