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
 * BinanceRawPrice
 */


public class BinanceRawPrice implements Parcelable
{
	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("timestamp")
	private DateTime timestamp = null;

	public BinanceRawPrice() {
	}

	public BinanceRawPrice symbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	/**
	 * Get symbol
	 *
	 * @return symbol
	 **/
	@Schema(description = "")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BinanceRawPrice price(Double price) {
		this.price = price;
		return this;
	}

	/**
	 * Get price
	 *
	 * @return price
	 **/
	@Schema(description = "")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BinanceRawPrice timestamp(DateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	/**
	 * Get timestamp
	 *
	 * @return timestamp
	 **/
	@Schema(description = "")
	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawPrice binanceRawPrice = (BinanceRawPrice) o;
		return Objects.equals(this.symbol, binanceRawPrice.symbol) &&
				Objects.equals(this.price, binanceRawPrice.price) &&
				Objects.equals(this.timestamp, binanceRawPrice.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, price, timestamp);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawPrice {\n");

		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
		out.writeValue(symbol);
		out.writeValue(price);
		out.writeValue(timestamp);
	}

	public static final Parcelable.Creator<BinanceRawPrice> CREATOR = new Parcelable.Creator<BinanceRawPrice>()
	{
		public BinanceRawPrice createFromParcel(Parcel in) {
			return new BinanceRawPrice(in);
		}

		public BinanceRawPrice[] newArray(int size) {
			return new BinanceRawPrice[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawPrice(Parcel in) {
		symbol = (String) in.readValue(null);
		price = (Double) in.readValue(null);
		timestamp = (DateTime) in.readValue(DateTime.class.getClassLoader());
	}
}
