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
 * CoinsTrade
 */


public class CoinsTrade implements Parcelable
{
	public static final Parcelable.Creator<CoinsTrade> CREATOR = new Parcelable.Creator<CoinsTrade>()
	{
		public CoinsTrade createFromParcel(Parcel in) {
			return new CoinsTrade(in);
		}

		public CoinsTrade[] newArray(int size) {
			return new CoinsTrade[size];
		}
	};

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("soldAmount")
	private Double soldAmount = null;

	@SerializedName("soldAsset")
	private BasePlatformAsset soldAsset = null;

	@SerializedName("boughtAmount")
	private Double boughtAmount = null;

	@SerializedName("boughtAsset")
	private BasePlatformAsset boughtAsset = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("commissionCurrency")
	private String commissionCurrency = null;

	public CoinsTrade() {
	}

	CoinsTrade(Parcel in) {
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		soldAmount = (Double) in.readValue(null);
		soldAsset = (BasePlatformAsset) in.readValue(BasePlatformAsset.class.getClassLoader());
		boughtAmount = (Double) in.readValue(null);
		boughtAsset = (BasePlatformAsset) in.readValue(BasePlatformAsset.class.getClassLoader());
		price = (Double) in.readValue(null);
		commission = (Double) in.readValue(null);
		commissionCurrency = (String) in.readValue(null);
	}

	public CoinsTrade date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@Schema(description = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public CoinsTrade soldAmount(Double soldAmount) {
		this.soldAmount = soldAmount;
		return this;
	}

	/**
	 * Get soldAmount
	 *
	 * @return soldAmount
	 **/
	@Schema(description = "")
	public Double getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(Double soldAmount) {
		this.soldAmount = soldAmount;
	}

	public CoinsTrade soldAsset(BasePlatformAsset soldAsset) {
		this.soldAsset = soldAsset;
		return this;
	}

	/**
	 * Get soldAsset
	 *
	 * @return soldAsset
	 **/
	@Schema(description = "")
	public BasePlatformAsset getSoldAsset() {
		return soldAsset;
	}

	public void setSoldAsset(BasePlatformAsset soldAsset) {
		this.soldAsset = soldAsset;
	}

	public CoinsTrade boughtAmount(Double boughtAmount) {
		this.boughtAmount = boughtAmount;
		return this;
	}

	/**
	 * Get boughtAmount
	 *
	 * @return boughtAmount
	 **/
	@Schema(description = "")
	public Double getBoughtAmount() {
		return boughtAmount;
	}

	public void setBoughtAmount(Double boughtAmount) {
		this.boughtAmount = boughtAmount;
	}

	public CoinsTrade boughtAsset(BasePlatformAsset boughtAsset) {
		this.boughtAsset = boughtAsset;
		return this;
	}

	/**
	 * Get boughtAsset
	 *
	 * @return boughtAsset
	 **/
	@Schema(description = "")
	public BasePlatformAsset getBoughtAsset() {
		return boughtAsset;
	}

	public void setBoughtAsset(BasePlatformAsset boughtAsset) {
		this.boughtAsset = boughtAsset;
	}

	public CoinsTrade price(Double price) {
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

	public CoinsTrade commission(Double commission) {
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

	public CoinsTrade commissionCurrency(String commissionCurrency) {
		this.commissionCurrency = commissionCurrency;
		return this;
	}

	/**
	 * Get commissionCurrency
	 *
	 * @return commissionCurrency
	 **/
	@Schema(description = "")
	public String getCommissionCurrency() {
		return commissionCurrency;
	}

	public void setCommissionCurrency(String commissionCurrency) {
		this.commissionCurrency = commissionCurrency;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CoinsTrade coinsTrade = (CoinsTrade) o;
		return Objects.equals(this.date, coinsTrade.date) &&
				Objects.equals(this.soldAmount, coinsTrade.soldAmount) &&
				Objects.equals(this.soldAsset, coinsTrade.soldAsset) &&
				Objects.equals(this.boughtAmount, coinsTrade.boughtAmount) &&
				Objects.equals(this.boughtAsset, coinsTrade.boughtAsset) &&
				Objects.equals(this.price, coinsTrade.price) &&
				Objects.equals(this.commission, coinsTrade.commission) &&
				Objects.equals(this.commissionCurrency, coinsTrade.commissionCurrency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, soldAmount, soldAsset, boughtAmount, boughtAsset, price, commission, commissionCurrency);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CoinsTrade {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    soldAmount: ").append(toIndentedString(soldAmount)).append("\n");
		sb.append("    soldAsset: ").append(toIndentedString(soldAsset)).append("\n");
		sb.append("    boughtAmount: ").append(toIndentedString(boughtAmount)).append("\n");
		sb.append("    boughtAsset: ").append(toIndentedString(boughtAsset)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    commissionCurrency: ").append(toIndentedString(commissionCurrency)).append("\n");
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
		out.writeValue(date);
		out.writeValue(soldAmount);
		out.writeValue(soldAsset);
		out.writeValue(boughtAmount);
		out.writeValue(boughtAsset);
		out.writeValue(price);
		out.writeValue(commission);
		out.writeValue(commissionCurrency);
	}

	public int describeContents() {
		return 0;
	}
}