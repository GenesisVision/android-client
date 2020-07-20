/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * MakeExchangeAccountProgram
 */


public class MakeExchangeAccountProgram implements Parcelable
{
	public static final Parcelable.Creator<MakeExchangeAccountProgram> CREATOR = new Parcelable.Creator<MakeExchangeAccountProgram>()
	{
		public MakeExchangeAccountProgram createFromParcel(Parcel in) {
			return new MakeExchangeAccountProgram(in);
		}

		public MakeExchangeAccountProgram[] newArray(int size) {
			return new MakeExchangeAccountProgram[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("hourProcessing")
	private Integer hourProcessing = null;

	@SerializedName("stopOutLevel")
	private Double stopOutLevel = null;

	@SerializedName("tradesDelay")
	private TradesDelay tradesDelay = null;

	@SerializedName("managementFee")
	private Double managementFee = null;

	@SerializedName("successFee")
	private Double successFee = null;

	@SerializedName("investmentLimit")
	private Double investmentLimit = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logo")
	private String logo = null;

	public MakeExchangeAccountProgram() {
	}

	MakeExchangeAccountProgram(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		hourProcessing = (Integer) in.readValue(null);
		stopOutLevel = (Double) in.readValue(null);
		tradesDelay = (TradesDelay) in.readValue(TradesDelay.class.getClassLoader());
		managementFee = (Double) in.readValue(null);
		successFee = (Double) in.readValue(null);
		investmentLimit = (Double) in.readValue(null);
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		logo = (String) in.readValue(null);
	}

	public MakeExchangeAccountProgram id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public MakeExchangeAccountProgram currency(Currency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public MakeExchangeAccountProgram hourProcessing(Integer hourProcessing) {
		this.hourProcessing = hourProcessing;
		return this;
	}

	/**
	 * Get hourProcessing
	 *
	 * @return hourProcessing
	 **/
	@Schema(description = "")
	public Integer getHourProcessing() {
		return hourProcessing;
	}

	public void setHourProcessing(Integer hourProcessing) {
		this.hourProcessing = hourProcessing;
	}

	public MakeExchangeAccountProgram stopOutLevel(Double stopOutLevel) {
		this.stopOutLevel = stopOutLevel;
		return this;
	}

	/**
	 * Get stopOutLevel
	 *
	 * @return stopOutLevel
	 **/
	@Schema(description = "")
	public Double getStopOutLevel() {
		return stopOutLevel;
	}

	public void setStopOutLevel(Double stopOutLevel) {
		this.stopOutLevel = stopOutLevel;
	}

	public MakeExchangeAccountProgram tradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
		return this;
	}

	/**
	 * Get tradesDelay
	 *
	 * @return tradesDelay
	 **/
	@Schema(description = "")
	public TradesDelay getTradesDelay() {
		return tradesDelay;
	}

	public void setTradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
	}

	public MakeExchangeAccountProgram managementFee(Double managementFee) {
		this.managementFee = managementFee;
		return this;
	}

	/**
	 * Get managementFee
	 *
	 * @return managementFee
	 **/
	@Schema(description = "")
	public Double getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(Double managementFee) {
		this.managementFee = managementFee;
	}

	public MakeExchangeAccountProgram successFee(Double successFee) {
		this.successFee = successFee;
		return this;
	}

	/**
	 * Get successFee
	 *
	 * @return successFee
	 **/
	@Schema(description = "")
	public Double getSuccessFee() {
		return successFee;
	}

	public void setSuccessFee(Double successFee) {
		this.successFee = successFee;
	}

	public MakeExchangeAccountProgram investmentLimit(Double investmentLimit) {
		this.investmentLimit = investmentLimit;
		return this;
	}

	/**
	 * Get investmentLimit
	 *
	 * @return investmentLimit
	 **/
	@Schema(description = "")
	public Double getInvestmentLimit() {
		return investmentLimit;
	}

	public void setInvestmentLimit(Double investmentLimit) {
		this.investmentLimit = investmentLimit;
	}

	public MakeExchangeAccountProgram title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MakeExchangeAccountProgram description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@Schema(description = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MakeExchangeAccountProgram logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MakeExchangeAccountProgram makeExchangeAccountProgram = (MakeExchangeAccountProgram) o;
		return Objects.equals(this.id, makeExchangeAccountProgram.id) &&
				Objects.equals(this.currency, makeExchangeAccountProgram.currency) &&
				Objects.equals(this.hourProcessing, makeExchangeAccountProgram.hourProcessing) &&
				Objects.equals(this.stopOutLevel, makeExchangeAccountProgram.stopOutLevel) &&
				Objects.equals(this.tradesDelay, makeExchangeAccountProgram.tradesDelay) &&
				Objects.equals(this.managementFee, makeExchangeAccountProgram.managementFee) &&
				Objects.equals(this.successFee, makeExchangeAccountProgram.successFee) &&
				Objects.equals(this.investmentLimit, makeExchangeAccountProgram.investmentLimit) &&
				Objects.equals(this.title, makeExchangeAccountProgram.title) &&
				Objects.equals(this.description, makeExchangeAccountProgram.description) &&
				Objects.equals(this.logo, makeExchangeAccountProgram.logo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, currency, hourProcessing, stopOutLevel, tradesDelay, managementFee, successFee, investmentLimit, title, description, logo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MakeExchangeAccountProgram {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    hourProcessing: ").append(toIndentedString(hourProcessing)).append("\n");
		sb.append("    stopOutLevel: ").append(toIndentedString(stopOutLevel)).append("\n");
		sb.append("    tradesDelay: ").append(toIndentedString(tradesDelay)).append("\n");
		sb.append("    managementFee: ").append(toIndentedString(managementFee)).append("\n");
		sb.append("    successFee: ").append(toIndentedString(successFee)).append("\n");
		sb.append("    investmentLimit: ").append(toIndentedString(investmentLimit)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
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

	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(id);
		out.writeValue(currency);
		out.writeValue(hourProcessing);
		out.writeValue(stopOutLevel);
		out.writeValue(tradesDelay);
		out.writeValue(managementFee);
		out.writeValue(successFee);
		out.writeValue(investmentLimit);
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(logo);
	}

	public int describeContents() {
		return 0;
	}
}
