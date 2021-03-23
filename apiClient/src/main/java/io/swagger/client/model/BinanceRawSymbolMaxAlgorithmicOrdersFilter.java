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
 * BinanceRawSymbolMaxAlgorithmicOrdersFilter
 */


public class BinanceRawSymbolMaxAlgorithmicOrdersFilter implements Parcelable
{
	@SerializedName("filterType")
	private BinanceSymbolFilterType filterType = null;

	@SerializedName("maxNumberAlgorithmicOrders")
	private Integer maxNumberAlgorithmicOrders = null;

	public BinanceRawSymbolMaxAlgorithmicOrdersFilter() {
	}

	public BinanceRawSymbolMaxAlgorithmicOrdersFilter filterType(BinanceSymbolFilterType filterType) {
		this.filterType = filterType;
		return this;
	}

	/**
	 * Get filterType
	 *
	 * @return filterType
	 **/
	@Schema(description = "")
	public BinanceSymbolFilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(BinanceSymbolFilterType filterType) {
		this.filterType = filterType;
	}

	public BinanceRawSymbolMaxAlgorithmicOrdersFilter maxNumberAlgorithmicOrders(Integer maxNumberAlgorithmicOrders) {
		this.maxNumberAlgorithmicOrders = maxNumberAlgorithmicOrders;
		return this;
	}

	/**
	 * Get maxNumberAlgorithmicOrders
	 *
	 * @return maxNumberAlgorithmicOrders
	 **/
	@Schema(description = "")
	public Integer getMaxNumberAlgorithmicOrders() {
		return maxNumberAlgorithmicOrders;
	}

	public void setMaxNumberAlgorithmicOrders(Integer maxNumberAlgorithmicOrders) {
		this.maxNumberAlgorithmicOrders = maxNumberAlgorithmicOrders;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawSymbolMaxAlgorithmicOrdersFilter binanceRawSymbolMaxAlgorithmicOrdersFilter = (BinanceRawSymbolMaxAlgorithmicOrdersFilter) o;
		return Objects.equals(this.filterType, binanceRawSymbolMaxAlgorithmicOrdersFilter.filterType) &&
				Objects.equals(this.maxNumberAlgorithmicOrders, binanceRawSymbolMaxAlgorithmicOrdersFilter.maxNumberAlgorithmicOrders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filterType, maxNumberAlgorithmicOrders);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawSymbolMaxAlgorithmicOrdersFilter {\n");

		sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
		sb.append("    maxNumberAlgorithmicOrders: ").append(toIndentedString(maxNumberAlgorithmicOrders)).append("\n");
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
		out.writeValue(maxNumberAlgorithmicOrders);
	}

	public static final Parcelable.Creator<BinanceRawSymbolMaxAlgorithmicOrdersFilter> CREATOR = new Parcelable.Creator<BinanceRawSymbolMaxAlgorithmicOrdersFilter>()
	{
		public BinanceRawSymbolMaxAlgorithmicOrdersFilter createFromParcel(Parcel in) {
			return new BinanceRawSymbolMaxAlgorithmicOrdersFilter(in);
		}

		public BinanceRawSymbolMaxAlgorithmicOrdersFilter[] newArray(int size) {
			return new BinanceRawSymbolMaxAlgorithmicOrdersFilter[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawSymbolMaxAlgorithmicOrdersFilter(Parcel in) {
		filterType = (BinanceSymbolFilterType) in.readValue(BinanceSymbolFilterType.class.getClassLoader());
		maxNumberAlgorithmicOrders = (Integer) in.readValue(null);
	}
}
