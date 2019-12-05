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
 * TradingAccountAssetPlatformInfo
 */


public class TradingAccountAssetPlatformInfo implements Parcelable
{
	public static final Parcelable.Creator<TradingAccountAssetPlatformInfo> CREATOR = new Parcelable.Creator<TradingAccountAssetPlatformInfo>()
	{
		public TradingAccountAssetPlatformInfo createFromParcel(Parcel in) {
			return new TradingAccountAssetPlatformInfo(in);
		}

		public TradingAccountAssetPlatformInfo[] newArray(int size) {
			return new TradingAccountAssetPlatformInfo[size];
		}
	};

	@SerializedName("minAmounts")
	private List<TradingAccountMinCreateAmount> minAmounts = null;

	public TradingAccountAssetPlatformInfo() {
	}

	TradingAccountAssetPlatformInfo(Parcel in) {
		minAmounts = (List<TradingAccountMinCreateAmount>) in.readValue(TradingAccountMinCreateAmount.class.getClassLoader());
	}

	public TradingAccountAssetPlatformInfo minAmounts(List<TradingAccountMinCreateAmount> minAmounts) {
		this.minAmounts = minAmounts;
		return this;
	}

	public TradingAccountAssetPlatformInfo addMinAmountsItem(TradingAccountMinCreateAmount minAmountsItem) {
		if (this.minAmounts == null) {
			this.minAmounts = new ArrayList<TradingAccountMinCreateAmount>();
		}
		this.minAmounts.add(minAmountsItem);
		return this;
	}

	/**
	 * Get minAmounts
	 *
	 * @return minAmounts
	 **/
	@Schema(description = "")
	public List<TradingAccountMinCreateAmount> getMinAmounts() {
		return minAmounts;
	}

	public void setMinAmounts(List<TradingAccountMinCreateAmount> minAmounts) {
		this.minAmounts = minAmounts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradingAccountAssetPlatformInfo tradingAccountAssetPlatformInfo = (TradingAccountAssetPlatformInfo) o;
		return Objects.equals(this.minAmounts, tradingAccountAssetPlatformInfo.minAmounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minAmounts);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradingAccountAssetPlatformInfo {\n");

		sb.append("    minAmounts: ").append(toIndentedString(minAmounts)).append("\n");
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
		out.writeValue(minAmounts);
	}

	public int describeContents() {
		return 0;
	}
}
