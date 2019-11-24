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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PlatformCommonInfo
 */


public class PlatformCommonInfo implements Parcelable
{
	public static final Parcelable.Creator<PlatformCommonInfo> CREATOR = new Parcelable.Creator<PlatformCommonInfo>()
	{
		public PlatformCommonInfo createFromParcel(Parcel in) {
			return new PlatformCommonInfo(in);
		}

		public PlatformCommonInfo[] newArray(int size) {
			return new PlatformCommonInfo[size];
		}
	};

	@SerializedName("platformCommission")
	private PlatformCommissionInfo platformCommission = null;

	@SerializedName("platformCurrencies")
	private List<PlatformCurrencyInfo> platformCurrencies = null;

	public PlatformCommonInfo() {
	}

	PlatformCommonInfo(Parcel in) {
		platformCommission = (PlatformCommissionInfo) in.readValue(PlatformCommissionInfo.class.getClassLoader());
		platformCurrencies = (List<PlatformCurrencyInfo>) in.readValue(PlatformCurrencyInfo.class.getClassLoader());
	}

	public PlatformCommonInfo platformCommission(PlatformCommissionInfo platformCommission) {
		this.platformCommission = platformCommission;
		return this;
	}

	/**
	 * Get platformCommission
	 *
	 * @return platformCommission
	 **/
	@Schema(description = "")
	public PlatformCommissionInfo getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(PlatformCommissionInfo platformCommission) {
		this.platformCommission = platformCommission;
	}

	public PlatformCommonInfo platformCurrencies(List<PlatformCurrencyInfo> platformCurrencies) {
		this.platformCurrencies = platformCurrencies;
		return this;
	}

	public PlatformCommonInfo addPlatformCurrenciesItem(PlatformCurrencyInfo platformCurrenciesItem) {
		if (this.platformCurrencies == null) {
			this.platformCurrencies = new ArrayList<PlatformCurrencyInfo>();
		}
		this.platformCurrencies.add(platformCurrenciesItem);
		return this;
	}

	/**
	 * Get platformCurrencies
	 *
	 * @return platformCurrencies
	 **/
	@Schema(description = "")
	public List<PlatformCurrencyInfo> getPlatformCurrencies() {
		return platformCurrencies;
	}

	public void setPlatformCurrencies(List<PlatformCurrencyInfo> platformCurrencies) {
		this.platformCurrencies = platformCurrencies;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlatformCommonInfo platformCommonInfo = (PlatformCommonInfo) o;
		return Objects.equals(this.platformCommission, platformCommonInfo.platformCommission) &&
				Objects.equals(this.platformCurrencies, platformCommonInfo.platformCurrencies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(platformCommission, platformCurrencies);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlatformCommonInfo {\n");

		sb.append("    platformCommission: ").append(toIndentedString(platformCommission)).append("\n");
		sb.append("    platformCurrencies: ").append(toIndentedString(platformCurrencies)).append("\n");
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
		out.writeValue(platformCommission);
		out.writeValue(platformCurrencies);
	}

	public int describeContents() {
		return 0;
	}
}
