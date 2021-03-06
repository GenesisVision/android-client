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
 * BinanceRawSymbolFilter
 */


public class BinanceRawSymbolFilter implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawSymbolFilter> CREATOR = new Parcelable.Creator<BinanceRawSymbolFilter>()
	{
		public BinanceRawSymbolFilter createFromParcel(Parcel in) {
			return new BinanceRawSymbolFilter(in);
		}

		public BinanceRawSymbolFilter[] newArray(int size) {
			return new BinanceRawSymbolFilter[size];
		}
	};

	@SerializedName("filterType")
	private BinanceRawSymbolFilterType filterType = null;

	public BinanceRawSymbolFilter() {
	}

	BinanceRawSymbolFilter(Parcel in) {
		filterType = (BinanceRawSymbolFilterType) in.readValue(BinanceRawSymbolFilterType.class.getClassLoader());
	}

	public BinanceRawSymbolFilter filterType(BinanceRawSymbolFilterType filterType) {
		this.filterType = filterType;
		return this;
	}

	/**
	 * Get filterType
	 *
	 * @return filterType
	 **/
	@Schema(description = "")
	public BinanceRawSymbolFilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(BinanceRawSymbolFilterType filterType) {
		this.filterType = filterType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawSymbolFilter binanceRawSymbolFilter = (BinanceRawSymbolFilter) o;
		return Objects.equals(this.filterType, binanceRawSymbolFilter.filterType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filterType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawSymbolFilter {\n");

		sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
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
		out.writeValue(filterType);
	}

	public int describeContents() {
		return 0;
	}
}
