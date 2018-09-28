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
 * FundWithdrawInfo
 */

public class FundWithdrawInfo
{
	@SerializedName("exitFee")
	private Double exitFee = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("availableToWithdraw")
	private Double availableToWithdraw = null;

	@SerializedName("periodEnds")
	private DateTime periodEnds = null;

	@SerializedName("rate")
	private Double rate = null;

	public FundWithdrawInfo exitFee(Double exitFee) {
		this.exitFee = exitFee;
		return this;
	}

	/**
	 * Get exitFee
	 *
	 * @return exitFee
	 **/
	@ApiModelProperty(value = "")
	public Double getExitFee() {
		return exitFee;
	}

	public void setExitFee(Double exitFee) {
		this.exitFee = exitFee;
	}

	public FundWithdrawInfo title(String title) {
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

	public FundWithdrawInfo availableToWithdraw(Double availableToWithdraw) {
		this.availableToWithdraw = availableToWithdraw;
		return this;
	}

	/**
	 * Get availableToWithdraw
	 *
	 * @return availableToWithdraw
	 **/
	@ApiModelProperty(value = "")
	public Double getAvailableToWithdraw() {
		return availableToWithdraw;
	}

	public void setAvailableToWithdraw(Double availableToWithdraw) {
		this.availableToWithdraw = availableToWithdraw;
	}

	public FundWithdrawInfo periodEnds(DateTime periodEnds) {
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

	public FundWithdrawInfo rate(Double rate) {
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


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundWithdrawInfo fundWithdrawInfo = (FundWithdrawInfo) o;
		return Objects.equals(this.exitFee, fundWithdrawInfo.exitFee) &&
				Objects.equals(this.title, fundWithdrawInfo.title) &&
				Objects.equals(this.availableToWithdraw, fundWithdrawInfo.availableToWithdraw) &&
				Objects.equals(this.periodEnds, fundWithdrawInfo.periodEnds) &&
				Objects.equals(this.rate, fundWithdrawInfo.rate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(exitFee, title, availableToWithdraw, periodEnds, rate);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundWithdrawInfo {\n");

		sb.append("    exitFee: ").append(toIndentedString(exitFee)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    availableToWithdraw: ").append(toIndentedString(availableToWithdraw)).append("\n");
		sb.append("    periodEnds: ").append(toIndentedString(periodEnds)).append("\n");
		sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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

