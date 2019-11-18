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

import org.joda.time.DateTime;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * RewardDetails
 */


public class RewardDetails implements Parcelable
{
	public static final Parcelable.Creator<RewardDetails> CREATOR = new Parcelable.Creator<RewardDetails>()
	{
		public RewardDetails createFromParcel(Parcel in) {
			return new RewardDetails(in);
		}

		public RewardDetails[] newArray(int size) {
			return new RewardDetails[size];
		}
	};

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("amount")
	private Double amount = null;

	public RewardDetails() {
	}

	RewardDetails(Parcel in) {
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		amount = (Double) in.readValue(null);
	}

	public RewardDetails date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@Schema(description = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public RewardDetails currency(Currency currency) {
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

	public RewardDetails amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@Schema(description = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RewardDetails rewardDetails = (RewardDetails) o;
		return Objects.equals(this.date, rewardDetails.date) &&
				Objects.equals(this.currency, rewardDetails.currency) &&
				Objects.equals(this.amount, rewardDetails.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, currency, amount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RewardDetails {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
		out.writeValue(date);
		out.writeValue(currency);
		out.writeValue(amount);
	}

	public int describeContents() {
		return 0;
	}
}
