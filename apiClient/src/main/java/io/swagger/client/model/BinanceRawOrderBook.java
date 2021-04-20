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
 * BinanceRawOrderBook
 */


public class BinanceRawOrderBook implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawOrderBook> CREATOR = new Parcelable.Creator<BinanceRawOrderBook>()
	{
		public BinanceRawOrderBook createFromParcel(Parcel in) {
			return new BinanceRawOrderBook(in);
		}

		public BinanceRawOrderBook[] newArray(int size) {
			return new BinanceRawOrderBook[size];
		}
	};

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("lastUpdateId")
	private Long lastUpdateId = null;

	@SerializedName("bids")
	private List<BinanceRawOrderBookEntry> bids = null;

	@SerializedName("asks")
	private List<BinanceRawOrderBookEntry> asks = null;

	public BinanceRawOrderBook() {
	}

	BinanceRawOrderBook(Parcel in) {
		symbol = (String) in.readValue(null);
		lastUpdateId = (Long) in.readValue(null);
		bids = (List<BinanceRawOrderBookEntry>) in.readValue(BinanceRawOrderBookEntry.class.getClassLoader());
		asks = (List<BinanceRawOrderBookEntry>) in.readValue(BinanceRawOrderBookEntry.class.getClassLoader());
	}

	public BinanceRawOrderBook symbol(String symbol) {
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

	public BinanceRawOrderBook lastUpdateId(Long lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
		return this;
	}

	/**
	 * Get lastUpdateId
	 *
	 * @return lastUpdateId
	 **/
	@Schema(description = "")
	public Long getLastUpdateId() {
		return lastUpdateId;
	}

	public void setLastUpdateId(Long lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	public BinanceRawOrderBook bids(List<BinanceRawOrderBookEntry> bids) {
		this.bids = bids;
		return this;
	}

	public BinanceRawOrderBook addBidsItem(BinanceRawOrderBookEntry bidsItem) {
		if (this.bids == null) {
			this.bids = new ArrayList<BinanceRawOrderBookEntry>();
		}
		this.bids.add(bidsItem);
		return this;
	}

	/**
	 * Get bids
	 *
	 * @return bids
	 **/
	@Schema(description = "")
	public List<BinanceRawOrderBookEntry> getBids() {
		return bids;
	}

	public void setBids(List<BinanceRawOrderBookEntry> bids) {
		this.bids = bids;
	}

	public BinanceRawOrderBook asks(List<BinanceRawOrderBookEntry> asks) {
		this.asks = asks;
		return this;
	}

	public BinanceRawOrderBook addAsksItem(BinanceRawOrderBookEntry asksItem) {
		if (this.asks == null) {
			this.asks = new ArrayList<BinanceRawOrderBookEntry>();
		}
		this.asks.add(asksItem);
		return this;
	}

	/**
	 * Get asks
	 *
	 * @return asks
	 **/
	@Schema(description = "")
	public List<BinanceRawOrderBookEntry> getAsks() {
		return asks;
	}

	public void setAsks(List<BinanceRawOrderBookEntry> asks) {
		this.asks = asks;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawOrderBook binanceRawOrderBook = (BinanceRawOrderBook) o;
		return Objects.equals(this.symbol, binanceRawOrderBook.symbol) &&
				Objects.equals(this.lastUpdateId, binanceRawOrderBook.lastUpdateId) &&
				Objects.equals(this.bids, binanceRawOrderBook.bids) &&
				Objects.equals(this.asks, binanceRawOrderBook.asks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, lastUpdateId, bids, asks);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawOrderBook {\n");

		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    lastUpdateId: ").append(toIndentedString(lastUpdateId)).append("\n");
		sb.append("    bids: ").append(toIndentedString(bids)).append("\n");
		sb.append("    asks: ").append(toIndentedString(asks)).append("\n");
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
		out.writeValue(lastUpdateId);
		out.writeValue(bids);
		out.writeValue(asks);
	}

	public int describeContents() {
		return 0;
	}
}
