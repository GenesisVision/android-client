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
 * BinanceRawAggregatedTrade
 */


public class BinanceRawAggregatedTrade implements Parcelable
{
	@SerializedName("aggregateTradeId")
	private Long aggregateTradeId = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("firstTradeId")
	private Long firstTradeId = null;

	@SerializedName("lastTradeId")
	private Long lastTradeId = null;

	@SerializedName("tradeTime")
	private DateTime tradeTime = null;

	@SerializedName("buyerIsMaker")
	private Boolean buyerIsMaker = null;

	@SerializedName("wasBestPriceMatch")
	private Boolean wasBestPriceMatch = null;

	public BinanceRawAggregatedTrade() {
	}

	public BinanceRawAggregatedTrade aggregateTradeId(Long aggregateTradeId) {
		this.aggregateTradeId = aggregateTradeId;
		return this;
	}

	/**
	 * Get aggregateTradeId
	 *
	 * @return aggregateTradeId
	 **/
	@Schema(description = "")
	public Long getAggregateTradeId() {
		return aggregateTradeId;
	}

	public void setAggregateTradeId(Long aggregateTradeId) {
		this.aggregateTradeId = aggregateTradeId;
	}

	public BinanceRawAggregatedTrade price(Double price) {
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

	public BinanceRawAggregatedTrade quantity(Double quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * Get quantity
	 *
	 * @return quantity
	 **/
	@Schema(description = "")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BinanceRawAggregatedTrade firstTradeId(Long firstTradeId) {
		this.firstTradeId = firstTradeId;
		return this;
	}

	/**
	 * Get firstTradeId
	 *
	 * @return firstTradeId
	 **/
	@Schema(description = "")
	public Long getFirstTradeId() {
		return firstTradeId;
	}

	public void setFirstTradeId(Long firstTradeId) {
		this.firstTradeId = firstTradeId;
	}

	public BinanceRawAggregatedTrade lastTradeId(Long lastTradeId) {
		this.lastTradeId = lastTradeId;
		return this;
	}

	/**
	 * Get lastTradeId
	 *
	 * @return lastTradeId
	 **/
	@Schema(description = "")
	public Long getLastTradeId() {
		return lastTradeId;
	}

	public void setLastTradeId(Long lastTradeId) {
		this.lastTradeId = lastTradeId;
	}

	public BinanceRawAggregatedTrade tradeTime(DateTime tradeTime) {
		this.tradeTime = tradeTime;
		return this;
	}

	/**
	 * Get tradeTime
	 *
	 * @return tradeTime
	 **/
	@Schema(description = "")
	public DateTime getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(DateTime tradeTime) {
		this.tradeTime = tradeTime;
	}

	public BinanceRawAggregatedTrade buyerIsMaker(Boolean buyerIsMaker) {
		this.buyerIsMaker = buyerIsMaker;
		return this;
	}

	/**
	 * Get buyerIsMaker
	 *
	 * @return buyerIsMaker
	 **/
	@Schema(description = "")
	public Boolean isBuyerIsMaker() {
		return buyerIsMaker;
	}

	public void setBuyerIsMaker(Boolean buyerIsMaker) {
		this.buyerIsMaker = buyerIsMaker;
	}

	public BinanceRawAggregatedTrade wasBestPriceMatch(Boolean wasBestPriceMatch) {
		this.wasBestPriceMatch = wasBestPriceMatch;
		return this;
	}

	/**
	 * Get wasBestPriceMatch
	 *
	 * @return wasBestPriceMatch
	 **/
	@Schema(description = "")
	public Boolean isWasBestPriceMatch() {
		return wasBestPriceMatch;
	}

	public void setWasBestPriceMatch(Boolean wasBestPriceMatch) {
		this.wasBestPriceMatch = wasBestPriceMatch;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawAggregatedTrade binanceRawAggregatedTrade = (BinanceRawAggregatedTrade) o;
		return Objects.equals(this.aggregateTradeId, binanceRawAggregatedTrade.aggregateTradeId) &&
				Objects.equals(this.price, binanceRawAggregatedTrade.price) &&
				Objects.equals(this.quantity, binanceRawAggregatedTrade.quantity) &&
				Objects.equals(this.firstTradeId, binanceRawAggregatedTrade.firstTradeId) &&
				Objects.equals(this.lastTradeId, binanceRawAggregatedTrade.lastTradeId) &&
				Objects.equals(this.tradeTime, binanceRawAggregatedTrade.tradeTime) &&
				Objects.equals(this.buyerIsMaker, binanceRawAggregatedTrade.buyerIsMaker) &&
				Objects.equals(this.wasBestPriceMatch, binanceRawAggregatedTrade.wasBestPriceMatch);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aggregateTradeId, price, quantity, firstTradeId, lastTradeId, tradeTime, buyerIsMaker, wasBestPriceMatch);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawAggregatedTrade {\n");

		sb.append("    aggregateTradeId: ").append(toIndentedString(aggregateTradeId)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    firstTradeId: ").append(toIndentedString(firstTradeId)).append("\n");
		sb.append("    lastTradeId: ").append(toIndentedString(lastTradeId)).append("\n");
		sb.append("    tradeTime: ").append(toIndentedString(tradeTime)).append("\n");
		sb.append("    buyerIsMaker: ").append(toIndentedString(buyerIsMaker)).append("\n");
		sb.append("    wasBestPriceMatch: ").append(toIndentedString(wasBestPriceMatch)).append("\n");
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
		out.writeValue(aggregateTradeId);
		out.writeValue(price);
		out.writeValue(quantity);
		out.writeValue(firstTradeId);
		out.writeValue(lastTradeId);
		out.writeValue(tradeTime);
		out.writeValue(buyerIsMaker);
		out.writeValue(wasBestPriceMatch);
	}

	public static final Parcelable.Creator<BinanceRawAggregatedTrade> CREATOR = new Parcelable.Creator<BinanceRawAggregatedTrade>()
	{
		public BinanceRawAggregatedTrade createFromParcel(Parcel in) {
			return new BinanceRawAggregatedTrade(in);
		}

		public BinanceRawAggregatedTrade[] newArray(int size) {
			return new BinanceRawAggregatedTrade[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawAggregatedTrade(Parcel in) {
		aggregateTradeId = (Long) in.readValue(null);
		price = (Double) in.readValue(null);
		quantity = (Double) in.readValue(null);
		firstTradeId = (Long) in.readValue(null);
		lastTradeId = (Long) in.readValue(null);
		tradeTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		buyerIsMaker = (Boolean) in.readValue(null);
		wasBestPriceMatch = (Boolean) in.readValue(null);
	}
}
