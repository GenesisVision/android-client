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
 * BinanceRawKline
 */


public class BinanceRawKline implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawKline> CREATOR = new Parcelable.Creator<BinanceRawKline>()
	{
		public BinanceRawKline createFromParcel(Parcel in) {
			return new BinanceRawKline(in);
		}

		public BinanceRawKline[] newArray(int size) {
			return new BinanceRawKline[size];
		}
	};

	@SerializedName("openTime")
	private DateTime openTime = null;

	@SerializedName("open")
	private Double open = null;

	@SerializedName("high")
	private Double high = null;

	@SerializedName("low")
	private Double low = null;

	@SerializedName("close")
	private Double close = null;

	@SerializedName("baseVolume")
	private Double baseVolume = null;

	@SerializedName("closeTime")
	private DateTime closeTime = null;

	@SerializedName("quoteVolume")
	private Double quoteVolume = null;

	@SerializedName("tradeCount")
	private Integer tradeCount = null;

	@SerializedName("takerBuyBaseVolume")
	private Double takerBuyBaseVolume = null;

	@SerializedName("takerBuyQuoteVolume")
	private Double takerBuyQuoteVolume = null;

	public BinanceRawKline() {
	}

	BinanceRawKline(Parcel in) {
		openTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		open = (Double) in.readValue(null);
		high = (Double) in.readValue(null);
		low = (Double) in.readValue(null);
		close = (Double) in.readValue(null);
		baseVolume = (Double) in.readValue(null);
		closeTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		quoteVolume = (Double) in.readValue(null);
		tradeCount = (Integer) in.readValue(null);
		takerBuyBaseVolume = (Double) in.readValue(null);
		takerBuyQuoteVolume = (Double) in.readValue(null);
	}

	public BinanceRawKline openTime(DateTime openTime) {
		this.openTime = openTime;
		return this;
	}

	/**
	 * Get openTime
	 *
	 * @return openTime
	 **/
	@Schema(description = "")
	public DateTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(DateTime openTime) {
		this.openTime = openTime;
	}

	public BinanceRawKline open(Double open) {
		this.open = open;
		return this;
	}

	/**
	 * Get open
	 *
	 * @return open
	 **/
	@Schema(description = "")
	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public BinanceRawKline high(Double high) {
		this.high = high;
		return this;
	}

	/**
	 * Get high
	 *
	 * @return high
	 **/
	@Schema(description = "")
	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public BinanceRawKline low(Double low) {
		this.low = low;
		return this;
	}

	/**
	 * Get low
	 *
	 * @return low
	 **/
	@Schema(description = "")
	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public BinanceRawKline close(Double close) {
		this.close = close;
		return this;
	}

	/**
	 * Get close
	 *
	 * @return close
	 **/
	@Schema(description = "")
	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public BinanceRawKline baseVolume(Double baseVolume) {
		this.baseVolume = baseVolume;
		return this;
	}

	/**
	 * Get baseVolume
	 *
	 * @return baseVolume
	 **/
	@Schema(description = "")
	public Double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(Double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public BinanceRawKline closeTime(DateTime closeTime) {
		this.closeTime = closeTime;
		return this;
	}

	/**
	 * Get closeTime
	 *
	 * @return closeTime
	 **/
	@Schema(description = "")
	public DateTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(DateTime closeTime) {
		this.closeTime = closeTime;
	}

	public BinanceRawKline quoteVolume(Double quoteVolume) {
		this.quoteVolume = quoteVolume;
		return this;
	}

	/**
	 * Get quoteVolume
	 *
	 * @return quoteVolume
	 **/
	@Schema(description = "")
	public Double getQuoteVolume() {
		return quoteVolume;
	}

	public void setQuoteVolume(Double quoteVolume) {
		this.quoteVolume = quoteVolume;
	}

	public BinanceRawKline tradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
		return this;
	}

	/**
	 * Get tradeCount
	 *
	 * @return tradeCount
	 **/
	@Schema(description = "")
	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public BinanceRawKline takerBuyBaseVolume(Double takerBuyBaseVolume) {
		this.takerBuyBaseVolume = takerBuyBaseVolume;
		return this;
	}

	/**
	 * Get takerBuyBaseVolume
	 *
	 * @return takerBuyBaseVolume
	 **/
	@Schema(description = "")
	public Double getTakerBuyBaseVolume() {
		return takerBuyBaseVolume;
	}

	public void setTakerBuyBaseVolume(Double takerBuyBaseVolume) {
		this.takerBuyBaseVolume = takerBuyBaseVolume;
	}

	public BinanceRawKline takerBuyQuoteVolume(Double takerBuyQuoteVolume) {
		this.takerBuyQuoteVolume = takerBuyQuoteVolume;
		return this;
	}

	/**
	 * Get takerBuyQuoteVolume
	 *
	 * @return takerBuyQuoteVolume
	 **/
	@Schema(description = "")
	public Double getTakerBuyQuoteVolume() {
		return takerBuyQuoteVolume;
	}

	public void setTakerBuyQuoteVolume(Double takerBuyQuoteVolume) {
		this.takerBuyQuoteVolume = takerBuyQuoteVolume;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawKline binanceRawKline = (BinanceRawKline) o;
		return Objects.equals(this.openTime, binanceRawKline.openTime) &&
				Objects.equals(this.open, binanceRawKline.open) &&
				Objects.equals(this.high, binanceRawKline.high) &&
				Objects.equals(this.low, binanceRawKline.low) &&
				Objects.equals(this.close, binanceRawKline.close) &&
				Objects.equals(this.baseVolume, binanceRawKline.baseVolume) &&
				Objects.equals(this.closeTime, binanceRawKline.closeTime) &&
				Objects.equals(this.quoteVolume, binanceRawKline.quoteVolume) &&
				Objects.equals(this.tradeCount, binanceRawKline.tradeCount) &&
				Objects.equals(this.takerBuyBaseVolume, binanceRawKline.takerBuyBaseVolume) &&
				Objects.equals(this.takerBuyQuoteVolume, binanceRawKline.takerBuyQuoteVolume);
	}

	@Override
	public int hashCode() {
		return Objects.hash(openTime, open, high, low, close, baseVolume, closeTime, quoteVolume, tradeCount, takerBuyBaseVolume, takerBuyQuoteVolume);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawKline {\n");

		sb.append("    openTime: ").append(toIndentedString(openTime)).append("\n");
		sb.append("    open: ").append(toIndentedString(open)).append("\n");
		sb.append("    high: ").append(toIndentedString(high)).append("\n");
		sb.append("    low: ").append(toIndentedString(low)).append("\n");
		sb.append("    close: ").append(toIndentedString(close)).append("\n");
		sb.append("    baseVolume: ").append(toIndentedString(baseVolume)).append("\n");
		sb.append("    closeTime: ").append(toIndentedString(closeTime)).append("\n");
		sb.append("    quoteVolume: ").append(toIndentedString(quoteVolume)).append("\n");
		sb.append("    tradeCount: ").append(toIndentedString(tradeCount)).append("\n");
		sb.append("    takerBuyBaseVolume: ").append(toIndentedString(takerBuyBaseVolume)).append("\n");
		sb.append("    takerBuyQuoteVolume: ").append(toIndentedString(takerBuyQuoteVolume)).append("\n");
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
		out.writeValue(openTime);
		out.writeValue(open);
		out.writeValue(high);
		out.writeValue(low);
		out.writeValue(close);
		out.writeValue(baseVolume);
		out.writeValue(closeTime);
		out.writeValue(quoteVolume);
		out.writeValue(tradeCount);
		out.writeValue(takerBuyBaseVolume);
		out.writeValue(takerBuyQuoteVolume);
	}

	public int describeContents() {
		return 0;
	}
}