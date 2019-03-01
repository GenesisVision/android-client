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

import org.joda.time.DateTime;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProgramInvestInfo
 */

public class ProgramInvestInfo
{
	@SerializedName("periodEnds")
	private DateTime periodEnds = null;

	@SerializedName("availableToInvest")
	private Double availableToInvest = null;

	@SerializedName("availableToInvestBase")
	private Double availableToInvestBase = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("availableInWallet")
	private Double availableInWallet = null;

	@SerializedName("minInvestmentAmount")
	private Double minInvestmentAmount = null;

	@SerializedName("entryFee")
	private Double entryFee = null;

	@SerializedName("gvCommission")
	private Double gvCommission = null;

	@SerializedName("rate")
	private Double rate = null;

	@SerializedName("isOwnProgram")
	private Boolean isOwnProgram = null;

	public ProgramInvestInfo periodEnds(DateTime periodEnds) {
		this.periodEnds = periodEnds;
		return this;
	}

	/**
	 * Get periodEnds
	 *
	 * @return periodEnds
	 **/
	@ApiModelProperty(value = "")
	public DateTime getPeriodEnds() {
		return periodEnds;
	}

	public void setPeriodEnds(DateTime periodEnds) {
		this.periodEnds = periodEnds;
	}

	public ProgramInvestInfo availableToInvest(Double availableToInvest) {
		this.availableToInvest = availableToInvest;
		return this;
	}

	/**
	 * In GVT
	 *
	 * @return availableToInvest
	 **/
	@ApiModelProperty(value = "In GVT")
	public Double getAvailableToInvest() {
		return availableToInvest;
	}

	public void setAvailableToInvest(Double availableToInvest) {
		this.availableToInvest = availableToInvest;
	}

	public ProgramInvestInfo availableToInvestBase(Double availableToInvestBase) {
		this.availableToInvestBase = availableToInvestBase;
		return this;
	}

	/**
	 * In account currency
	 *
	 * @return availableToInvestBase
	 **/
	@ApiModelProperty(value = "In account currency")
	public Double getAvailableToInvestBase() {
		return availableToInvestBase;
	}

	public void setAvailableToInvestBase(Double availableToInvestBase) {
		this.availableToInvestBase = availableToInvestBase;
	}

	public ProgramInvestInfo title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@ApiModelProperty(value = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProgramInvestInfo availableInWallet(Double availableInWallet) {
		this.availableInWallet = availableInWallet;
		return this;
	}

	/**
	 * Get availableInWallet
	 *
	 * @return availableInWallet
	 **/
	@ApiModelProperty(value = "")
	public Double getAvailableInWallet() {
		return availableInWallet;
	}

	public void setAvailableInWallet(Double availableInWallet) {
		this.availableInWallet = availableInWallet;
	}

	public ProgramInvestInfo minInvestmentAmount(Double minInvestmentAmount) {
		this.minInvestmentAmount = minInvestmentAmount;
		return this;
	}

	/**
	 * Get minInvestmentAmount
	 *
	 * @return minInvestmentAmount
	 **/
	@ApiModelProperty(value = "")
	public Double getMinInvestmentAmount() {
		return minInvestmentAmount;
	}

	public void setMinInvestmentAmount(Double minInvestmentAmount) {
		this.minInvestmentAmount = minInvestmentAmount;
	}

	public ProgramInvestInfo entryFee(Double entryFee) {
		this.entryFee = entryFee;
		return this;
	}

	/**
	 * Get entryFee
	 *
	 * @return entryFee
	 **/
	@ApiModelProperty(value = "")
	public Double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Double entryFee) {
		this.entryFee = entryFee;
	}

	public ProgramInvestInfo gvCommission(Double gvCommission) {
		this.gvCommission = gvCommission;
		return this;
	}

	/**
	 * Get gvCommission
	 *
	 * @return gvCommission
	 **/
	@ApiModelProperty(value = "")
	public Double getGvCommission() {
		return gvCommission;
	}

	public void setGvCommission(Double gvCommission) {
		this.gvCommission = gvCommission;
	}

	public ProgramInvestInfo rate(Double rate) {
		this.rate = rate;
		return this;
	}

	/**
	 * Get rate
	 *
	 * @return rate
	 **/
	@ApiModelProperty(value = "")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public ProgramInvestInfo isOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
		return this;
	}

	/**
	 * Get isOwnProgram
	 *
	 * @return isOwnProgram
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsOwnProgram() {
		return isOwnProgram;
	}

	public void setIsOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramInvestInfo programInvestInfo = (ProgramInvestInfo) o;
		return Objects.equals(this.periodEnds, programInvestInfo.periodEnds) &&
				Objects.equals(this.availableToInvest, programInvestInfo.availableToInvest) &&
				Objects.equals(this.availableToInvestBase, programInvestInfo.availableToInvestBase) &&
				Objects.equals(this.title, programInvestInfo.title) &&
				Objects.equals(this.availableInWallet, programInvestInfo.availableInWallet) &&
				Objects.equals(this.minInvestmentAmount, programInvestInfo.minInvestmentAmount) &&
				Objects.equals(this.entryFee, programInvestInfo.entryFee) &&
				Objects.equals(this.gvCommission, programInvestInfo.gvCommission) &&
				Objects.equals(this.rate, programInvestInfo.rate) &&
				Objects.equals(this.isOwnProgram, programInvestInfo.isOwnProgram);
	}

	@Override
	public int hashCode() {
		return Objects.hash(periodEnds, availableToInvest, availableToInvestBase, title, availableInWallet, minInvestmentAmount, entryFee, gvCommission, rate, isOwnProgram);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramInvestInfo {\n");

		sb.append("    periodEnds: ").append(toIndentedString(periodEnds)).append("\n");
		sb.append("    availableToInvest: ").append(toIndentedString(availableToInvest)).append("\n");
		sb.append("    availableToInvestBase: ").append(toIndentedString(availableToInvestBase)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    availableInWallet: ").append(toIndentedString(availableInWallet)).append("\n");
		sb.append("    minInvestmentAmount: ").append(toIndentedString(minInvestmentAmount)).append("\n");
		sb.append("    entryFee: ").append(toIndentedString(entryFee)).append("\n");
		sb.append("    gvCommission: ").append(toIndentedString(gvCommission)).append("\n");
		sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
		sb.append("    isOwnProgram: ").append(toIndentedString(isOwnProgram)).append("\n");
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

