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
 * AttachToExternalSignalProviderExt
 */


public class AttachToExternalSignalProviderExt implements Parcelable
{
	@SerializedName("mode")
	private SubscriptionMode mode = null;

	@SerializedName("percent")
	private Double percent = null;

	@SerializedName("openTolerancePercent")
	private Double openTolerancePercent = null;

	@SerializedName("fixedVolume")
	private Double fixedVolume = null;

	@SerializedName("fixedCurrency")
	private Currency fixedCurrency = null;

	@SerializedName("tradingAccountId")
	private UUID tradingAccountId = null;

	public AttachToExternalSignalProviderExt() {
	}

	public AttachToExternalSignalProviderExt mode(SubscriptionMode mode) {
		this.mode = mode;
		return this;
	}

	/**
	 * Get mode
	 *
	 * @return mode
	 **/
	@Schema(description = "")
	public SubscriptionMode getMode() {
		return mode;
	}

	public void setMode(SubscriptionMode mode) {
		this.mode = mode;
	}

	public AttachToExternalSignalProviderExt percent(Double percent) {
		this.percent = percent;
		return this;
	}

	/**
	 * Get percent
	 *
	 * @return percent
	 **/
	@Schema(description = "")
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public AttachToExternalSignalProviderExt openTolerancePercent(Double openTolerancePercent) {
		this.openTolerancePercent = openTolerancePercent;
		return this;
	}

	/**
	 * Get openTolerancePercent
	 *
	 * @return openTolerancePercent
	 **/
	@Schema(description = "")
	public Double getOpenTolerancePercent() {
		return openTolerancePercent;
	}

	public void setOpenTolerancePercent(Double openTolerancePercent) {
		this.openTolerancePercent = openTolerancePercent;
	}

	public AttachToExternalSignalProviderExt fixedVolume(Double fixedVolume) {
		this.fixedVolume = fixedVolume;
		return this;
	}

	/**
	 * Get fixedVolume
	 *
	 * @return fixedVolume
	 **/
	@Schema(description = "")
	public Double getFixedVolume() {
		return fixedVolume;
	}

	public void setFixedVolume(Double fixedVolume) {
		this.fixedVolume = fixedVolume;
	}

	public AttachToExternalSignalProviderExt fixedCurrency(Currency fixedCurrency) {
		this.fixedCurrency = fixedCurrency;
		return this;
	}

	/**
	 * Get fixedCurrency
	 *
	 * @return fixedCurrency
	 **/
	@Schema(description = "")
	public Currency getFixedCurrency() {
		return fixedCurrency;
	}

	public void setFixedCurrency(Currency fixedCurrency) {
		this.fixedCurrency = fixedCurrency;
	}

	public AttachToExternalSignalProviderExt tradingAccountId(UUID tradingAccountId) {
		this.tradingAccountId = tradingAccountId;
		return this;
	}

	/**
	 * Get tradingAccountId
	 *
	 * @return tradingAccountId
	 **/
	@Schema(description = "")
	public UUID getTradingAccountId() {
		return tradingAccountId;
	}

	public void setTradingAccountId(UUID tradingAccountId) {
		this.tradingAccountId = tradingAccountId;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AttachToExternalSignalProviderExt attachToExternalSignalProviderExt = (AttachToExternalSignalProviderExt) o;
		return Objects.equals(this.mode, attachToExternalSignalProviderExt.mode) &&
				Objects.equals(this.percent, attachToExternalSignalProviderExt.percent) &&
				Objects.equals(this.openTolerancePercent, attachToExternalSignalProviderExt.openTolerancePercent) &&
				Objects.equals(this.fixedVolume, attachToExternalSignalProviderExt.fixedVolume) &&
				Objects.equals(this.fixedCurrency, attachToExternalSignalProviderExt.fixedCurrency) &&
				Objects.equals(this.tradingAccountId, attachToExternalSignalProviderExt.tradingAccountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, tradingAccountId);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AttachToExternalSignalProviderExt {\n");

		sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
		sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
		sb.append("    openTolerancePercent: ").append(toIndentedString(openTolerancePercent)).append("\n");
		sb.append("    fixedVolume: ").append(toIndentedString(fixedVolume)).append("\n");
		sb.append("    fixedCurrency: ").append(toIndentedString(fixedCurrency)).append("\n");
		sb.append("    tradingAccountId: ").append(toIndentedString(tradingAccountId)).append("\n");
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
		out.writeValue(mode);
		out.writeValue(percent);
		out.writeValue(openTolerancePercent);
		out.writeValue(fixedVolume);
		out.writeValue(fixedCurrency);
		out.writeValue(tradingAccountId);
	}

	public static final Parcelable.Creator<AttachToExternalSignalProviderExt> CREATOR = new Parcelable.Creator<AttachToExternalSignalProviderExt>()
	{
		public AttachToExternalSignalProviderExt createFromParcel(Parcel in) {
			return new AttachToExternalSignalProviderExt(in);
		}

		public AttachToExternalSignalProviderExt[] newArray(int size) {
			return new AttachToExternalSignalProviderExt[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	AttachToExternalSignalProviderExt(Parcel in) {
		mode = (SubscriptionMode) in.readValue(SubscriptionMode.class.getClassLoader());
		percent = (Double) in.readValue(null);
		openTolerancePercent = (Double) in.readValue(null);
		fixedVolume = (Double) in.readValue(null);
		fixedCurrency = (Currency) in.readValue(Currency.class.getClassLoader());
		tradingAccountId = (UUID) in.readValue(UUID.class.getClassLoader());
	}
}
