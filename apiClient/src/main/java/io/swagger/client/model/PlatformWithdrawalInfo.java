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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PlatformWithdrawalInfo
 */


public class PlatformWithdrawalInfo implements Parcelable
{
	public static final Parcelable.Creator<PlatformWithdrawalInfo> CREATOR = new Parcelable.Creator<PlatformWithdrawalInfo>()
	{
		public PlatformWithdrawalInfo createFromParcel(Parcel in) {
			return new PlatformWithdrawalInfo(in);
		}

		public PlatformWithdrawalInfo[] newArray(int size) {
			return new PlatformWithdrawalInfo[size];
		}
	};

	@SerializedName("withdrawalFee")
	private Double withdrawalFee = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("isWithdrawalEnabled")
	private Boolean isWithdrawalEnabled = null;

	public PlatformWithdrawalInfo() {
	}

	PlatformWithdrawalInfo(Parcel in) {
		withdrawalFee = (Double) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		isWithdrawalEnabled = (Boolean) in.readValue(null);
	}

	public PlatformWithdrawalInfo withdrawalFee(Double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
		return this;
	}

	/**
	 * Get withdrawalFee
	 *
	 * @return withdrawalFee
	 **/
	@Schema(description = "")
	public Double getWithdrawalFee() {
		return withdrawalFee;
	}

	public void setWithdrawalFee(Double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public PlatformWithdrawalInfo currency(Currency currency) {
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

	public PlatformWithdrawalInfo isWithdrawalEnabled(Boolean isWithdrawalEnabled) {
		this.isWithdrawalEnabled = isWithdrawalEnabled;
		return this;
	}

	/**
	 * Get isWithdrawalEnabled
	 *
	 * @return isWithdrawalEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsWithdrawalEnabled() {
		return isWithdrawalEnabled;
	}

	public void setIsWithdrawalEnabled(Boolean isWithdrawalEnabled) {
		this.isWithdrawalEnabled = isWithdrawalEnabled;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlatformWithdrawalInfo platformWithdrawalInfo = (PlatformWithdrawalInfo) o;
		return Objects.equals(this.withdrawalFee, platformWithdrawalInfo.withdrawalFee) &&
				Objects.equals(this.currency, platformWithdrawalInfo.currency) &&
				Objects.equals(this.isWithdrawalEnabled, platformWithdrawalInfo.isWithdrawalEnabled);
	}

	@Override
	public int hashCode() {
		return Objects.hash(withdrawalFee, currency, isWithdrawalEnabled);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlatformWithdrawalInfo {\n");

		sb.append("    withdrawalFee: ").append(toIndentedString(withdrawalFee)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    isWithdrawalEnabled: ").append(toIndentedString(isWithdrawalEnabled)).append("\n");
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
		out.writeValue(withdrawalFee);
		out.writeValue(currency);
		out.writeValue(isWithdrawalEnabled);
	}

	public int describeContents() {
		return 0;
	}
}
