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
 * BinanceRawFuturesBracket
 */


public class BinanceRawFuturesBracket implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesBracket> CREATOR = new Parcelable.Creator<BinanceRawFuturesBracket>()
	{
		public BinanceRawFuturesBracket createFromParcel(Parcel in) {
			return new BinanceRawFuturesBracket(in);
		}

		public BinanceRawFuturesBracket[] newArray(int size) {
			return new BinanceRawFuturesBracket[size];
		}
	};

	@SerializedName("bracket")
	private Integer bracket = null;

	@SerializedName("initialLeverage")
	private Integer initialLeverage = null;

	@SerializedName("cap")
	private Long cap = null;

	@SerializedName("floor")
	private Long floor = null;

	@SerializedName("maintenanceMarginRatio")
	private Double maintenanceMarginRatio = null;

	public BinanceRawFuturesBracket() {
	}

	BinanceRawFuturesBracket(Parcel in) {
		bracket = (Integer) in.readValue(null);
		initialLeverage = (Integer) in.readValue(null);
		cap = (Long) in.readValue(null);
		floor = (Long) in.readValue(null);
		maintenanceMarginRatio = (Double) in.readValue(null);
	}

	public BinanceRawFuturesBracket bracket(Integer bracket) {
		this.bracket = bracket;
		return this;
	}

	/**
	 * Get bracket
	 *
	 * @return bracket
	 **/
	@Schema(description = "")
	public Integer getBracket() {
		return bracket;
	}

	public void setBracket(Integer bracket) {
		this.bracket = bracket;
	}

	public BinanceRawFuturesBracket initialLeverage(Integer initialLeverage) {
		this.initialLeverage = initialLeverage;
		return this;
	}

	/**
	 * Get initialLeverage
	 *
	 * @return initialLeverage
	 **/
	@Schema(description = "")
	public Integer getInitialLeverage() {
		return initialLeverage;
	}

	public void setInitialLeverage(Integer initialLeverage) {
		this.initialLeverage = initialLeverage;
	}

	public BinanceRawFuturesBracket cap(Long cap) {
		this.cap = cap;
		return this;
	}

	/**
	 * Get cap
	 *
	 * @return cap
	 **/
	@Schema(description = "")
	public Long getCap() {
		return cap;
	}

	public void setCap(Long cap) {
		this.cap = cap;
	}

	public BinanceRawFuturesBracket floor(Long floor) {
		this.floor = floor;
		return this;
	}

	/**
	 * Get floor
	 *
	 * @return floor
	 **/
	@Schema(description = "")
	public Long getFloor() {
		return floor;
	}

	public void setFloor(Long floor) {
		this.floor = floor;
	}

	public BinanceRawFuturesBracket maintenanceMarginRatio(Double maintenanceMarginRatio) {
		this.maintenanceMarginRatio = maintenanceMarginRatio;
		return this;
	}

	/**
	 * Get maintenanceMarginRatio
	 *
	 * @return maintenanceMarginRatio
	 **/
	@Schema(description = "")
	public Double getMaintenanceMarginRatio() {
		return maintenanceMarginRatio;
	}

	public void setMaintenanceMarginRatio(Double maintenanceMarginRatio) {
		this.maintenanceMarginRatio = maintenanceMarginRatio;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesBracket binanceRawFuturesBracket = (BinanceRawFuturesBracket) o;
		return Objects.equals(this.bracket, binanceRawFuturesBracket.bracket) &&
				Objects.equals(this.initialLeverage, binanceRawFuturesBracket.initialLeverage) &&
				Objects.equals(this.cap, binanceRawFuturesBracket.cap) &&
				Objects.equals(this.floor, binanceRawFuturesBracket.floor) &&
				Objects.equals(this.maintenanceMarginRatio, binanceRawFuturesBracket.maintenanceMarginRatio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bracket, initialLeverage, cap, floor, maintenanceMarginRatio);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesBracket {\n");

		sb.append("    bracket: ").append(toIndentedString(bracket)).append("\n");
		sb.append("    initialLeverage: ").append(toIndentedString(initialLeverage)).append("\n");
		sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
		sb.append("    floor: ").append(toIndentedString(floor)).append("\n");
		sb.append("    maintenanceMarginRatio: ").append(toIndentedString(maintenanceMarginRatio)).append("\n");
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
		out.writeValue(bracket);
		out.writeValue(initialLeverage);
		out.writeValue(cap);
		out.writeValue(floor);
		out.writeValue(maintenanceMarginRatio);
	}

	public int describeContents() {
		return 0;
	}
}
