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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * WalletData
 */


public class WalletData implements Parcelable
{
	public static final Parcelable.Creator<WalletData> CREATOR = new Parcelable.Creator<WalletData>()
	{
		public WalletData createFromParcel(Parcel in) {
			return new WalletData(in);
		}

		public WalletData[] newArray(int size) {
			return new WalletData[size];
		}
	};

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("available")
	private Double available = null;

	@SerializedName("invested")
	private Double invested = null;

	@SerializedName("trading")
	private Double trading = null;

	@SerializedName("pending")
	private Double pending = null;

	@SerializedName("total")
	private Double total = null;

	@SerializedName("availableCcy")
	private Double availableCcy = null;

	@SerializedName("investedCcy")
	private Double investedCcy = null;

	@SerializedName("tradingCcy")
	private Double tradingCcy = null;

	@SerializedName("pendingCcy")
	private Double pendingCcy = null;

	@SerializedName("totalCcy")
	private Double totalCcy = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("isDepositEnabled")
	private Boolean isDepositEnabled = null;

	@SerializedName("isWithdrawalEnabled")
	private Boolean isWithdrawalEnabled = null;

	@SerializedName("withdrawalCommission")
	private Double withdrawalCommission = null;

	@SerializedName("depositAddress")
	private String depositAddress = null;

	@SerializedName("depositUrlCoindirect")
	private String depositUrlCoindirect = null;

	public WalletData() {
	}

	WalletData(Parcel in) {
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		available = (Double) in.readValue(null);
		invested = (Double) in.readValue(null);
		trading = (Double) in.readValue(null);
		pending = (Double) in.readValue(null);
		total = (Double) in.readValue(null);
		availableCcy = (Double) in.readValue(null);
		investedCcy = (Double) in.readValue(null);
		tradingCcy = (Double) in.readValue(null);
		pendingCcy = (Double) in.readValue(null);
		totalCcy = (Double) in.readValue(null);
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		title = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		isDepositEnabled = (Boolean) in.readValue(null);
		isWithdrawalEnabled = (Boolean) in.readValue(null);
		withdrawalCommission = (Double) in.readValue(null);
		depositAddress = (String) in.readValue(null);
		depositUrlCoindirect = (String) in.readValue(null);
	}

	public WalletData currency(Currency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public WalletData available(Double available) {
		this.available = available;
		return this;
	}

	/**
	 * Get available
	 *
	 * @return available
	 **/
	@Schema(description = "")
	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public WalletData invested(Double invested) {
		this.invested = invested;
		return this;
	}

	/**
	 * Get invested
	 *
	 * @return invested
	 **/
	@Schema(description = "")
	public Double getInvested() {
		return invested;
	}

	public void setInvested(Double invested) {
		this.invested = invested;
	}

	public WalletData trading(Double trading) {
		this.trading = trading;
		return this;
	}

	/**
	 * Get trading
	 *
	 * @return trading
	 **/
	@Schema(description = "")
	public Double getTrading() {
		return trading;
	}

	public void setTrading(Double trading) {
		this.trading = trading;
	}

	public WalletData pending(Double pending) {
		this.pending = pending;
		return this;
	}

	/**
	 * Get pending
	 *
	 * @return pending
	 **/
	@Schema(description = "")
	public Double getPending() {
		return pending;
	}

	public void setPending(Double pending) {
		this.pending = pending;
	}

	/**
	 * Get total
	 *
	 * @return total
	 **/
	@Schema(description = "")
	public Double getTotal() {
		return total;
	}

	public WalletData availableCcy(Double availableCcy) {
		this.availableCcy = availableCcy;
		return this;
	}

	/**
	 * Get availableCcy
	 *
	 * @return availableCcy
	 **/
	@Schema(description = "")
	public Double getAvailableCcy() {
		return availableCcy;
	}

	public void setAvailableCcy(Double availableCcy) {
		this.availableCcy = availableCcy;
	}

	public WalletData investedCcy(Double investedCcy) {
		this.investedCcy = investedCcy;
		return this;
	}

	/**
	 * Get investedCcy
	 *
	 * @return investedCcy
	 **/
	@Schema(description = "")
	public Double getInvestedCcy() {
		return investedCcy;
	}

	public void setInvestedCcy(Double investedCcy) {
		this.investedCcy = investedCcy;
	}

	public WalletData tradingCcy(Double tradingCcy) {
		this.tradingCcy = tradingCcy;
		return this;
	}

	/**
	 * Get tradingCcy
	 *
	 * @return tradingCcy
	 **/
	@Schema(description = "")
	public Double getTradingCcy() {
		return tradingCcy;
	}

	public void setTradingCcy(Double tradingCcy) {
		this.tradingCcy = tradingCcy;
	}

	public WalletData pendingCcy(Double pendingCcy) {
		this.pendingCcy = pendingCcy;
		return this;
	}

	/**
	 * Get pendingCcy
	 *
	 * @return pendingCcy
	 **/
	@Schema(description = "")
	public Double getPendingCcy() {
		return pendingCcy;
	}

	public void setPendingCcy(Double pendingCcy) {
		this.pendingCcy = pendingCcy;
	}

	/**
	 * Get totalCcy
	 *
	 * @return totalCcy
	 **/
	@Schema(description = "")
	public Double getTotalCcy() {
		return totalCcy;
	}

	public WalletData id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public WalletData title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WalletData logoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		return this;
	}

	/**
	 * Get logoUrl
	 *
	 * @return logoUrl
	 **/
	@Schema(description = "")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public WalletData isDepositEnabled(Boolean isDepositEnabled) {
		this.isDepositEnabled = isDepositEnabled;
		return this;
	}

	/**
	 * Get isDepositEnabled
	 *
	 * @return isDepositEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsDepositEnabled() {
		return isDepositEnabled;
	}

	public void setIsDepositEnabled(Boolean isDepositEnabled) {
		this.isDepositEnabled = isDepositEnabled;
	}

	public WalletData isWithdrawalEnabled(Boolean isWithdrawalEnabled) {
		this.isWithdrawalEnabled = isWithdrawalEnabled;
		return this;
	}

	/**
	 * Get isWithdrawalEnabled
	 *
	 * @return isWithdrawalEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsWithdrawalEnabled() {
		return isWithdrawalEnabled;
	}

	public void setIsWithdrawalEnabled(Boolean isWithdrawalEnabled) {
		this.isWithdrawalEnabled = isWithdrawalEnabled;
	}

	public WalletData withdrawalCommission(Double withdrawalCommission) {
		this.withdrawalCommission = withdrawalCommission;
		return this;
	}

	/**
	 * Get withdrawalCommission
	 *
	 * @return withdrawalCommission
	 **/
	@Schema(description = "")
	public Double getWithdrawalCommission() {
		return withdrawalCommission;
	}

	public void setWithdrawalCommission(Double withdrawalCommission) {
		this.withdrawalCommission = withdrawalCommission;
	}

	public WalletData depositAddress(String depositAddress) {
		this.depositAddress = depositAddress;
		return this;
	}

	/**
	 * Get depositAddress
	 *
	 * @return depositAddress
	 **/
	@Schema(description = "")
	public String getDepositAddress() {
		return depositAddress;
	}

	public void setDepositAddress(String depositAddress) {
		this.depositAddress = depositAddress;
	}

	public WalletData depositUrlCoindirect(String depositUrlCoindirect) {
		this.depositUrlCoindirect = depositUrlCoindirect;
		return this;
	}

	/**
	 * Get depositUrlCoindirect
	 *
	 * @return depositUrlCoindirect
	 **/
	@Schema(description = "")
	public String getDepositUrlCoindirect() {
		return depositUrlCoindirect;
	}

	public void setDepositUrlCoindirect(String depositUrlCoindirect) {
		this.depositUrlCoindirect = depositUrlCoindirect;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WalletData walletData = (WalletData) o;
		return Objects.equals(this.currency, walletData.currency) &&
				Objects.equals(this.available, walletData.available) &&
				Objects.equals(this.invested, walletData.invested) &&
				Objects.equals(this.trading, walletData.trading) &&
				Objects.equals(this.pending, walletData.pending) &&
				Objects.equals(this.total, walletData.total) &&
				Objects.equals(this.availableCcy, walletData.availableCcy) &&
				Objects.equals(this.investedCcy, walletData.investedCcy) &&
				Objects.equals(this.tradingCcy, walletData.tradingCcy) &&
				Objects.equals(this.pendingCcy, walletData.pendingCcy) &&
				Objects.equals(this.totalCcy, walletData.totalCcy) &&
				Objects.equals(this.id, walletData.id) &&
				Objects.equals(this.title, walletData.title) &&
				Objects.equals(this.logoUrl, walletData.logoUrl) &&
				Objects.equals(this.isDepositEnabled, walletData.isDepositEnabled) &&
				Objects.equals(this.isWithdrawalEnabled, walletData.isWithdrawalEnabled) &&
				Objects.equals(this.withdrawalCommission, walletData.withdrawalCommission) &&
				Objects.equals(this.depositAddress, walletData.depositAddress) &&
				Objects.equals(this.depositUrlCoindirect, walletData.depositUrlCoindirect);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, available, invested, trading, pending, total, availableCcy, investedCcy, tradingCcy, pendingCcy, totalCcy, id, title, logoUrl, isDepositEnabled, isWithdrawalEnabled, withdrawalCommission, depositAddress, depositUrlCoindirect);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WalletData {\n");

		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    available: ").append(toIndentedString(available)).append("\n");
		sb.append("    invested: ").append(toIndentedString(invested)).append("\n");
		sb.append("    trading: ").append(toIndentedString(trading)).append("\n");
		sb.append("    pending: ").append(toIndentedString(pending)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    availableCcy: ").append(toIndentedString(availableCcy)).append("\n");
		sb.append("    investedCcy: ").append(toIndentedString(investedCcy)).append("\n");
		sb.append("    tradingCcy: ").append(toIndentedString(tradingCcy)).append("\n");
		sb.append("    pendingCcy: ").append(toIndentedString(pendingCcy)).append("\n");
		sb.append("    totalCcy: ").append(toIndentedString(totalCcy)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    isDepositEnabled: ").append(toIndentedString(isDepositEnabled)).append("\n");
		sb.append("    isWithdrawalEnabled: ").append(toIndentedString(isWithdrawalEnabled)).append("\n");
		sb.append("    withdrawalCommission: ").append(toIndentedString(withdrawalCommission)).append("\n");
		sb.append("    depositAddress: ").append(toIndentedString(depositAddress)).append("\n");
		sb.append("    depositUrlCoindirect: ").append(toIndentedString(depositUrlCoindirect)).append("\n");
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
		out.writeValue(currency);
		out.writeValue(available);
		out.writeValue(invested);
		out.writeValue(trading);
		out.writeValue(pending);
		out.writeValue(total);
		out.writeValue(availableCcy);
		out.writeValue(investedCcy);
		out.writeValue(tradingCcy);
		out.writeValue(pendingCcy);
		out.writeValue(totalCcy);
		out.writeValue(id);
		out.writeValue(title);
		out.writeValue(logoUrl);
		out.writeValue(isDepositEnabled);
		out.writeValue(isWithdrawalEnabled);
		out.writeValue(withdrawalCommission);
		out.writeValue(depositAddress);
		out.writeValue(depositUrlCoindirect);
	}

	public int describeContents() {
		return 0;
	}
}
