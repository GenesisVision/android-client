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
 * BinanceRawSymbolPercentPriceFilter
 */


public class BinanceRawSymbolPercentPriceFilter implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawSymbolPercentPriceFilter> CREATOR = new Parcelable.Creator<BinanceRawSymbolPercentPriceFilter>()
	{
		public BinanceRawSymbolPercentPriceFilter createFromParcel(Parcel in) {
			return new BinanceRawSymbolPercentPriceFilter(in);
		}

		public BinanceRawSymbolPercentPriceFilter[] newArray(int size) {
			return new BinanceRawSymbolPercentPriceFilter[size];
		}
	};

	@SerializedName("filterType")
	private BinanceRawSymbolFilterType filterType = null;

	@SerializedName("multiplierUp")
	private Double multiplierUp = null;

	@SerializedName("multiplierDown")
	private Double multiplierDown = null;

	@SerializedName("averagePriceMinutes")
	private Integer averagePriceMinutes = null;

	public BinanceRawSymbolPercentPriceFilter() {
	}

	BinanceRawSymbolPercentPriceFilter(Parcel in) {
		filterType = (BinanceRawSymbolFilterType) in.readValue(BinanceRawSymbolFilterType.class.getClassLoader());
		multiplierUp = (Double) in.readValue(null);
		multiplierDown = (Double) in.readValue(null);
		averagePriceMinutes = (Integer) in.readValue(null);
	}

	public BinanceRawSymbolPercentPriceFilter filterType(BinanceRawSymbolFilterType filterType) {
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

	public BinanceRawSymbolPercentPriceFilter multiplierUp(Double multiplierUp) {
		this.multiplierUp = multiplierUp;
		return this;
	}

	/**
	 * Get multiplierUp
	 *
	 * @return multiplierUp
	 **/
	@Schema(description = "")
	public Double getMultiplierUp() {
		return multiplierUp;
	}

	public void setMultiplierUp(Double multiplierUp) {
		this.multiplierUp = multiplierUp;
	}

	public BinanceRawSymbolPercentPriceFilter multiplierDown(Double multiplierDown) {
		this.multiplierDown = multiplierDown;
		return this;
	}

	/**
	 * Get multiplierDown
	 *
	 * @return multiplierDown
	 **/
	@Schema(description = "")
	public Double getMultiplierDown() {
		return multiplierDown;
	}

	public void setMultiplierDown(Double multiplierDown) {
		this.multiplierDown = multiplierDown;
	}

	public BinanceRawSymbolPercentPriceFilter averagePriceMinutes(Integer averagePriceMinutes) {
		this.averagePriceMinutes = averagePriceMinutes;
		return this;
	}

	/**
	 * Get averagePriceMinutes
	 *
	 * @return averagePriceMinutes
	 **/
	@Schema(description = "")
	public Integer getAveragePriceMinutes() {
		return averagePriceMinutes;
	}

	public void setAveragePriceMinutes(Integer averagePriceMinutes) {
		this.averagePriceMinutes = averagePriceMinutes;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawSymbolPercentPriceFilter binanceRawSymbolPercentPriceFilter = (BinanceRawSymbolPercentPriceFilter) o;
		return Objects.equals(this.filterType, binanceRawSymbolPercentPriceFilter.filterType) &&
				Objects.equals(this.multiplierUp, binanceRawSymbolPercentPriceFilter.multiplierUp) &&
				Objects.equals(this.multiplierDown, binanceRawSymbolPercentPriceFilter.multiplierDown) &&
				Objects.equals(this.averagePriceMinutes, binanceRawSymbolPercentPriceFilter.averagePriceMinutes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filterType, multiplierUp, multiplierDown, averagePriceMinutes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawSymbolPercentPriceFilter {\n");

		sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
		sb.append("    multiplierUp: ").append(toIndentedString(multiplierUp)).append("\n");
		sb.append("    multiplierDown: ").append(toIndentedString(multiplierDown)).append("\n");
		sb.append("    averagePriceMinutes: ").append(toIndentedString(averagePriceMinutes)).append("\n");
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
		out.writeValue(multiplierUp);
		out.writeValue(multiplierDown);
		out.writeValue(averagePriceMinutes);
	}

	public int describeContents() {
		return 0;
	}
}
