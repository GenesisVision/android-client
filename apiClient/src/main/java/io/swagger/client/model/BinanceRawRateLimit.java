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
 * BinanceRawRateLimit
 */


public class BinanceRawRateLimit implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawRateLimit> CREATOR = new Parcelable.Creator<BinanceRawRateLimit>()
	{
		public BinanceRawRateLimit createFromParcel(Parcel in) {
			return new BinanceRawRateLimit(in);
		}

		public BinanceRawRateLimit[] newArray(int size) {
			return new BinanceRawRateLimit[size];
		}
	};

	@SerializedName("interval")
	private BinanceRateLimitInterval interval = null;

	@SerializedName("type")
	private BinanceRateLimitType type = null;

	@SerializedName("intervalNumber")
	private Integer intervalNumber = null;

	@SerializedName("limit")
	private Integer limit = null;

	public BinanceRawRateLimit() {
	}

	BinanceRawRateLimit(Parcel in) {
		interval = (BinanceRateLimitInterval) in.readValue(BinanceRateLimitInterval.class.getClassLoader());
		type = (BinanceRateLimitType) in.readValue(BinanceRateLimitType.class.getClassLoader());
		intervalNumber = (Integer) in.readValue(null);
		limit = (Integer) in.readValue(null);
	}

	public BinanceRawRateLimit interval(BinanceRateLimitInterval interval) {
		this.interval = interval;
		return this;
	}

	/**
	 * Get interval
	 *
	 * @return interval
	 **/
	@Schema(description = "")
	public BinanceRateLimitInterval getInterval() {
		return interval;
	}

	public void setInterval(BinanceRateLimitInterval interval) {
		this.interval = interval;
	}

	public BinanceRawRateLimit type(BinanceRateLimitType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public BinanceRateLimitType getType() {
		return type;
	}

	public void setType(BinanceRateLimitType type) {
		this.type = type;
	}

	public BinanceRawRateLimit intervalNumber(Integer intervalNumber) {
		this.intervalNumber = intervalNumber;
		return this;
	}

	/**
	 * Get intervalNumber
	 *
	 * @return intervalNumber
	 **/
	@Schema(description = "")
	public Integer getIntervalNumber() {
		return intervalNumber;
	}

	public void setIntervalNumber(Integer intervalNumber) {
		this.intervalNumber = intervalNumber;
	}

	public BinanceRawRateLimit limit(Integer limit) {
		this.limit = limit;
		return this;
	}

	/**
	 * Get limit
	 *
	 * @return limit
	 **/
	@Schema(description = "")
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawRateLimit binanceRawRateLimit = (BinanceRawRateLimit) o;
		return Objects.equals(this.interval, binanceRawRateLimit.interval) &&
				Objects.equals(this.type, binanceRawRateLimit.type) &&
				Objects.equals(this.intervalNumber, binanceRawRateLimit.intervalNumber) &&
				Objects.equals(this.limit, binanceRawRateLimit.limit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(interval, type, intervalNumber, limit);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawRateLimit {\n");

		sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    intervalNumber: ").append(toIndentedString(intervalNumber)).append("\n");
		sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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
		out.writeValue(interval);
		out.writeValue(type);
		out.writeValue(intervalNumber);
		out.writeValue(limit);
	}

	public int describeContents() {
		return 0;
	}
}