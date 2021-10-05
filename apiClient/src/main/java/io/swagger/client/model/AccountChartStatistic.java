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
 * AccountChartStatistic
 */


public class AccountChartStatistic implements Parcelable
{
	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("profitPercent")
	private Double profitPercent = null;

	@SerializedName("sharpeRatio")
	private Double sharpeRatio = null;

	@SerializedName("sortinoRatio")
	private Double sortinoRatio = null;

	@SerializedName("calmarRatio")
	private Double calmarRatio = null;

	@SerializedName("maxDrawdown")
	private Double maxDrawdown = null;

	@SerializedName("tradingVolume")
	private Double tradingVolume = null;

	@SerializedName("trades")
	private Integer trades = null;

	@SerializedName("successTradesPercent")
	private Double successTradesPercent = null;

	@SerializedName("profitFactor")
	private Double profitFactor = null;

	public AccountChartStatistic() {
	}

	public AccountChartStatistic balance(Double balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 **/
	@Schema(description = "")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountChartStatistic profitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
		return this;
	}

	/**
	 * Get profitPercent
	 *
	 * @return profitPercent
	 **/
	@Schema(description = "")
	public Double getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}

	public AccountChartStatistic sharpeRatio(Double sharpeRatio) {
		this.sharpeRatio = sharpeRatio;
		return this;
	}

	/**
	 * Get sharpeRatio
	 *
	 * @return sharpeRatio
	 **/
	@Schema(description = "")
	public Double getSharpeRatio() {
		return sharpeRatio;
	}

	public void setSharpeRatio(Double sharpeRatio) {
		this.sharpeRatio = sharpeRatio;
	}

	public AccountChartStatistic sortinoRatio(Double sortinoRatio) {
		this.sortinoRatio = sortinoRatio;
		return this;
	}

	/**
	 * Get sortinoRatio
	 *
	 * @return sortinoRatio
	 **/
	@Schema(description = "")
	public Double getSortinoRatio() {
		return sortinoRatio;
	}

	public void setSortinoRatio(Double sortinoRatio) {
		this.sortinoRatio = sortinoRatio;
	}

	public AccountChartStatistic calmarRatio(Double calmarRatio) {
		this.calmarRatio = calmarRatio;
		return this;
	}

	/**
	 * Get calmarRatio
	 *
	 * @return calmarRatio
	 **/
	@Schema(description = "")
	public Double getCalmarRatio() {
		return calmarRatio;
	}

	public void setCalmarRatio(Double calmarRatio) {
		this.calmarRatio = calmarRatio;
	}

	public AccountChartStatistic maxDrawdown(Double maxDrawdown) {
		this.maxDrawdown = maxDrawdown;
		return this;
	}

	/**
	 * Get maxDrawdown
	 *
	 * @return maxDrawdown
	 **/
	@Schema(description = "")
	public Double getMaxDrawdown() {
		return maxDrawdown;
	}

	public void setMaxDrawdown(Double maxDrawdown) {
		this.maxDrawdown = maxDrawdown;
	}

	public AccountChartStatistic tradingVolume(Double tradingVolume) {
		this.tradingVolume = tradingVolume;
		return this;
	}

	/**
	 * Get tradingVolume
	 *
	 * @return tradingVolume
	 **/
	@Schema(description = "")
	public Double getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(Double tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public AccountChartStatistic trades(Integer trades) {
		this.trades = trades;
		return this;
	}

	/**
	 * Get trades
	 *
	 * @return trades
	 **/
	@Schema(description = "")
	public Integer getTrades() {
		return trades;
	}

	public void setTrades(Integer trades) {
		this.trades = trades;
	}

	public AccountChartStatistic successTradesPercent(Double successTradesPercent) {
		this.successTradesPercent = successTradesPercent;
		return this;
	}

	/**
	 * Get successTradesPercent
	 *
	 * @return successTradesPercent
	 **/
	@Schema(description = "")
	public Double getSuccessTradesPercent() {
		return successTradesPercent;
	}

	public void setSuccessTradesPercent(Double successTradesPercent) {
		this.successTradesPercent = successTradesPercent;
	}

	public AccountChartStatistic profitFactor(Double profitFactor) {
		this.profitFactor = profitFactor;
		return this;
	}

	/**
	 * Get profitFactor
	 *
	 * @return profitFactor
	 **/
	@Schema(description = "")
	public Double getProfitFactor() {
		return profitFactor;
	}

	public void setProfitFactor(Double profitFactor) {
		this.profitFactor = profitFactor;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AccountChartStatistic accountChartStatistic = (AccountChartStatistic) o;
		return Objects.equals(this.balance, accountChartStatistic.balance) &&
				Objects.equals(this.profitPercent, accountChartStatistic.profitPercent) &&
				Objects.equals(this.sharpeRatio, accountChartStatistic.sharpeRatio) &&
				Objects.equals(this.sortinoRatio, accountChartStatistic.sortinoRatio) &&
				Objects.equals(this.calmarRatio, accountChartStatistic.calmarRatio) &&
				Objects.equals(this.maxDrawdown, accountChartStatistic.maxDrawdown) &&
				Objects.equals(this.tradingVolume, accountChartStatistic.tradingVolume) &&
				Objects.equals(this.trades, accountChartStatistic.trades) &&
				Objects.equals(this.successTradesPercent, accountChartStatistic.successTradesPercent) &&
				Objects.equals(this.profitFactor, accountChartStatistic.profitFactor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, profitPercent, sharpeRatio, sortinoRatio, calmarRatio, maxDrawdown, tradingVolume, trades, successTradesPercent, profitFactor);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AccountChartStatistic {\n");

		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    profitPercent: ").append(toIndentedString(profitPercent)).append("\n");
		sb.append("    sharpeRatio: ").append(toIndentedString(sharpeRatio)).append("\n");
		sb.append("    sortinoRatio: ").append(toIndentedString(sortinoRatio)).append("\n");
		sb.append("    calmarRatio: ").append(toIndentedString(calmarRatio)).append("\n");
		sb.append("    maxDrawdown: ").append(toIndentedString(maxDrawdown)).append("\n");
		sb.append("    tradingVolume: ").append(toIndentedString(tradingVolume)).append("\n");
		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
		sb.append("    successTradesPercent: ").append(toIndentedString(successTradesPercent)).append("\n");
		sb.append("    profitFactor: ").append(toIndentedString(profitFactor)).append("\n");
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
		out.writeValue(balance);
		out.writeValue(profitPercent);
		out.writeValue(sharpeRatio);
		out.writeValue(sortinoRatio);
		out.writeValue(calmarRatio);
		out.writeValue(maxDrawdown);
		out.writeValue(tradingVolume);
		out.writeValue(trades);
		out.writeValue(successTradesPercent);
		out.writeValue(profitFactor);
	}

	public static final Parcelable.Creator<AccountChartStatistic> CREATOR = new Parcelable.Creator<AccountChartStatistic>()
	{
		public AccountChartStatistic createFromParcel(Parcel in) {
			return new AccountChartStatistic(in);
		}

		public AccountChartStatistic[] newArray(int size) {
			return new AccountChartStatistic[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	AccountChartStatistic(Parcel in) {
		balance = (Double) in.readValue(null);
		profitPercent = (Double) in.readValue(null);
		sharpeRatio = (Double) in.readValue(null);
		sortinoRatio = (Double) in.readValue(null);
		calmarRatio = (Double) in.readValue(null);
		maxDrawdown = (Double) in.readValue(null);
		tradingVolume = (Double) in.readValue(null);
		trades = (Integer) in.readValue(null);
		successTradesPercent = (Double) in.readValue(null);
		profitFactor = (Double) in.readValue(null);
	}
}
