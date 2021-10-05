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
 * BinanceRawOrderTrade
 */


public class BinanceRawOrderTrade implements Parcelable
{
	@SerializedName("tradeId")
	private Long tradeId = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("commissionAsset")
	private String commissionAsset = null;

	public BinanceRawOrderTrade() {
	}

	public BinanceRawOrderTrade tradeId(Long tradeId) {
		this.tradeId = tradeId;
		return this;
	}

	/**
	 * Get tradeId
	 *
	 * @return tradeId
	 **/
	@Schema(description = "")
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public BinanceRawOrderTrade price(Double price) {
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

	public BinanceRawOrderTrade quantity(Double quantity) {
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

	public BinanceRawOrderTrade commission(Double commission) {
		this.commission = commission;
		return this;
	}

	/**
	 * Get commission
	 *
	 * @return commission
	 **/
	@Schema(description = "")
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public BinanceRawOrderTrade commissionAsset(String commissionAsset) {
		this.commissionAsset = commissionAsset;
		return this;
	}

	/**
	 * Get commissionAsset
	 *
	 * @return commissionAsset
	 **/
	@Schema(description = "")
	public String getCommissionAsset() {
		return commissionAsset;
	}

	public void setCommissionAsset(String commissionAsset) {
		this.commissionAsset = commissionAsset;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawOrderTrade binanceRawOrderTrade = (BinanceRawOrderTrade) o;
		return Objects.equals(this.tradeId, binanceRawOrderTrade.tradeId) &&
				Objects.equals(this.price, binanceRawOrderTrade.price) &&
				Objects.equals(this.quantity, binanceRawOrderTrade.quantity) &&
				Objects.equals(this.commission, binanceRawOrderTrade.commission) &&
				Objects.equals(this.commissionAsset, binanceRawOrderTrade.commissionAsset);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tradeId, price, quantity, commission, commissionAsset);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawOrderTrade {\n");

		sb.append("    tradeId: ").append(toIndentedString(tradeId)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    commissionAsset: ").append(toIndentedString(commissionAsset)).append("\n");
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
		out.writeValue(tradeId);
		out.writeValue(price);
		out.writeValue(quantity);
		out.writeValue(commission);
		out.writeValue(commissionAsset);
	}

	public static final Parcelable.Creator<BinanceRawOrderTrade> CREATOR = new Parcelable.Creator<BinanceRawOrderTrade>()
	{
		public BinanceRawOrderTrade createFromParcel(Parcel in) {
			return new BinanceRawOrderTrade(in);
		}

		public BinanceRawOrderTrade[] newArray(int size) {
			return new BinanceRawOrderTrade[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawOrderTrade(Parcel in) {
		tradeId = (Long) in.readValue(null);
		price = (Double) in.readValue(null);
		quantity = (Double) in.readValue(null);
		commission = (Double) in.readValue(null);
		commissionAsset = (String) in.readValue(null);
	}
}
