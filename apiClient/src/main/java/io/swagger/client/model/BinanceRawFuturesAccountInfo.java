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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * BinanceRawFuturesAccountInfo
 */


public class BinanceRawFuturesAccountInfo implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesAccountInfo> CREATOR = new Parcelable.Creator<BinanceRawFuturesAccountInfo>()
	{
		public BinanceRawFuturesAccountInfo createFromParcel(Parcel in) {
			return new BinanceRawFuturesAccountInfo(in);
		}

		public BinanceRawFuturesAccountInfo[] newArray(int size) {
			return new BinanceRawFuturesAccountInfo[size];
		}
	};

	@SerializedName("canDeposit")
	private Boolean canDeposit = null;

	@SerializedName("canTrade")
	private Boolean canTrade = null;

	@SerializedName("canWithdraw")
	private Boolean canWithdraw = null;

	@SerializedName("feeTier")
	private Integer feeTier = null;

	@SerializedName("maxWithdrawAmount")
	private Double maxWithdrawAmount = null;

	@SerializedName("totalInitialMargin")
	private Double totalInitialMargin = null;

	@SerializedName("totalMaintMargin")
	private Double totalMaintMargin = null;

	@SerializedName("totalMarginBalance")
	private Double totalMarginBalance = null;

	@SerializedName("totalOpenOrderInitialMargin")
	private Double totalOpenOrderInitialMargin = null;

	@SerializedName("totalPositionInitialMargin")
	private Double totalPositionInitialMargin = null;

	@SerializedName("totalUnrealizedProfit")
	private Double totalUnrealizedProfit = null;

	@SerializedName("totalWalletBalance")
	private Double totalWalletBalance = null;

	@SerializedName("totalCrossWalletBalance")
	private Double totalCrossWalletBalance = null;

	@SerializedName("totalCrossUnPnl")
	private Double totalCrossUnPnl = null;

	@SerializedName("availableBalance")
	private Double availableBalance = null;

	@SerializedName("updateTime")
	private DateTime updateTime = null;

	@SerializedName("assets")
	private List<BinanceRawFuturesAccountAsset> assets = null;

	@SerializedName("positions")
	private List<BinanceRawFuturesAccountPosition> positions = null;

	public BinanceRawFuturesAccountInfo() {
	}

	BinanceRawFuturesAccountInfo(Parcel in) {
		canDeposit = (Boolean) in.readValue(null);
		canTrade = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		feeTier = (Integer) in.readValue(null);
		maxWithdrawAmount = (Double) in.readValue(null);
		totalInitialMargin = (Double) in.readValue(null);
		totalMaintMargin = (Double) in.readValue(null);
		totalMarginBalance = (Double) in.readValue(null);
		totalOpenOrderInitialMargin = (Double) in.readValue(null);
		totalPositionInitialMargin = (Double) in.readValue(null);
		totalUnrealizedProfit = (Double) in.readValue(null);
		totalWalletBalance = (Double) in.readValue(null);
		totalCrossWalletBalance = (Double) in.readValue(null);
		totalCrossUnPnl = (Double) in.readValue(null);
		availableBalance = (Double) in.readValue(null);
		updateTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		assets = (List<BinanceRawFuturesAccountAsset>) in.readValue(BinanceRawFuturesAccountAsset.class.getClassLoader());
		positions = (List<BinanceRawFuturesAccountPosition>) in.readValue(BinanceRawFuturesAccountPosition.class.getClassLoader());
	}

	public BinanceRawFuturesAccountInfo canDeposit(Boolean canDeposit) {
		this.canDeposit = canDeposit;
		return this;
	}

	/**
	 * Get canDeposit
	 *
	 * @return canDeposit
	 **/
	@Schema(description = "")
	public Boolean isCanDeposit() {
		return canDeposit;
	}

	public void setCanDeposit(Boolean canDeposit) {
		this.canDeposit = canDeposit;
	}

	public BinanceRawFuturesAccountInfo canTrade(Boolean canTrade) {
		this.canTrade = canTrade;
		return this;
	}

	/**
	 * Get canTrade
	 *
	 * @return canTrade
	 **/
	@Schema(description = "")
	public Boolean isCanTrade() {
		return canTrade;
	}

	public void setCanTrade(Boolean canTrade) {
		this.canTrade = canTrade;
	}

	public BinanceRawFuturesAccountInfo canWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
		return this;
	}

	/**
	 * Get canWithdraw
	 *
	 * @return canWithdraw
	 **/
	@Schema(description = "")
	public Boolean isCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public BinanceRawFuturesAccountInfo feeTier(Integer feeTier) {
		this.feeTier = feeTier;
		return this;
	}

	/**
	 * Get feeTier
	 *
	 * @return feeTier
	 **/
	@Schema(description = "")
	public Integer getFeeTier() {
		return feeTier;
	}

	public void setFeeTier(Integer feeTier) {
		this.feeTier = feeTier;
	}

	public BinanceRawFuturesAccountInfo maxWithdrawAmount(Double maxWithdrawAmount) {
		this.maxWithdrawAmount = maxWithdrawAmount;
		return this;
	}

	/**
	 * Get maxWithdrawAmount
	 *
	 * @return maxWithdrawAmount
	 **/
	@Schema(description = "")
	public Double getMaxWithdrawAmount() {
		return maxWithdrawAmount;
	}

	public void setMaxWithdrawAmount(Double maxWithdrawAmount) {
		this.maxWithdrawAmount = maxWithdrawAmount;
	}

	public BinanceRawFuturesAccountInfo totalInitialMargin(Double totalInitialMargin) {
		this.totalInitialMargin = totalInitialMargin;
		return this;
	}

	/**
	 * Get totalInitialMargin
	 *
	 * @return totalInitialMargin
	 **/
	@Schema(description = "")
	public Double getTotalInitialMargin() {
		return totalInitialMargin;
	}

	public void setTotalInitialMargin(Double totalInitialMargin) {
		this.totalInitialMargin = totalInitialMargin;
	}

	public BinanceRawFuturesAccountInfo totalMaintMargin(Double totalMaintMargin) {
		this.totalMaintMargin = totalMaintMargin;
		return this;
	}

	/**
	 * Get totalMaintMargin
	 *
	 * @return totalMaintMargin
	 **/
	@Schema(description = "")
	public Double getTotalMaintMargin() {
		return totalMaintMargin;
	}

	public void setTotalMaintMargin(Double totalMaintMargin) {
		this.totalMaintMargin = totalMaintMargin;
	}

	public BinanceRawFuturesAccountInfo totalMarginBalance(Double totalMarginBalance) {
		this.totalMarginBalance = totalMarginBalance;
		return this;
	}

	/**
	 * Get totalMarginBalance
	 *
	 * @return totalMarginBalance
	 **/
	@Schema(description = "")
	public Double getTotalMarginBalance() {
		return totalMarginBalance;
	}

	public void setTotalMarginBalance(Double totalMarginBalance) {
		this.totalMarginBalance = totalMarginBalance;
	}

	public BinanceRawFuturesAccountInfo totalOpenOrderInitialMargin(Double totalOpenOrderInitialMargin) {
		this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
		return this;
	}

	/**
	 * Get totalOpenOrderInitialMargin
	 *
	 * @return totalOpenOrderInitialMargin
	 **/
	@Schema(description = "")
	public Double getTotalOpenOrderInitialMargin() {
		return totalOpenOrderInitialMargin;
	}

	public void setTotalOpenOrderInitialMargin(Double totalOpenOrderInitialMargin) {
		this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
	}

	public BinanceRawFuturesAccountInfo totalPositionInitialMargin(Double totalPositionInitialMargin) {
		this.totalPositionInitialMargin = totalPositionInitialMargin;
		return this;
	}

	/**
	 * Get totalPositionInitialMargin
	 *
	 * @return totalPositionInitialMargin
	 **/
	@Schema(description = "")
	public Double getTotalPositionInitialMargin() {
		return totalPositionInitialMargin;
	}

	public void setTotalPositionInitialMargin(Double totalPositionInitialMargin) {
		this.totalPositionInitialMargin = totalPositionInitialMargin;
	}

	public BinanceRawFuturesAccountInfo totalUnrealizedProfit(Double totalUnrealizedProfit) {
		this.totalUnrealizedProfit = totalUnrealizedProfit;
		return this;
	}

	/**
	 * Get totalUnrealizedProfit
	 *
	 * @return totalUnrealizedProfit
	 **/
	@Schema(description = "")
	public Double getTotalUnrealizedProfit() {
		return totalUnrealizedProfit;
	}

	public void setTotalUnrealizedProfit(Double totalUnrealizedProfit) {
		this.totalUnrealizedProfit = totalUnrealizedProfit;
	}

	public BinanceRawFuturesAccountInfo totalWalletBalance(Double totalWalletBalance) {
		this.totalWalletBalance = totalWalletBalance;
		return this;
	}

	/**
	 * Get totalWalletBalance
	 *
	 * @return totalWalletBalance
	 **/
	@Schema(description = "")
	public Double getTotalWalletBalance() {
		return totalWalletBalance;
	}

	public void setTotalWalletBalance(Double totalWalletBalance) {
		this.totalWalletBalance = totalWalletBalance;
	}

	public BinanceRawFuturesAccountInfo totalCrossWalletBalance(Double totalCrossWalletBalance) {
		this.totalCrossWalletBalance = totalCrossWalletBalance;
		return this;
	}

	/**
	 * Get totalCrossWalletBalance
	 *
	 * @return totalCrossWalletBalance
	 **/
	@Schema(description = "")
	public Double getTotalCrossWalletBalance() {
		return totalCrossWalletBalance;
	}

	public void setTotalCrossWalletBalance(Double totalCrossWalletBalance) {
		this.totalCrossWalletBalance = totalCrossWalletBalance;
	}

	public BinanceRawFuturesAccountInfo totalCrossUnPnl(Double totalCrossUnPnl) {
		this.totalCrossUnPnl = totalCrossUnPnl;
		return this;
	}

	/**
	 * Get totalCrossUnPnl
	 *
	 * @return totalCrossUnPnl
	 **/
	@Schema(description = "")
	public Double getTotalCrossUnPnl() {
		return totalCrossUnPnl;
	}

	public void setTotalCrossUnPnl(Double totalCrossUnPnl) {
		this.totalCrossUnPnl = totalCrossUnPnl;
	}

	public BinanceRawFuturesAccountInfo availableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
		return this;
	}

	/**
	 * Get availableBalance
	 *
	 * @return availableBalance
	 **/
	@Schema(description = "")
	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BinanceRawFuturesAccountInfo updateTime(DateTime updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	/**
	 * Get updateTime
	 *
	 * @return updateTime
	 **/
	@Schema(description = "")
	public DateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

	public BinanceRawFuturesAccountInfo assets(List<BinanceRawFuturesAccountAsset> assets) {
		this.assets = assets;
		return this;
	}

	public BinanceRawFuturesAccountInfo addAssetsItem(BinanceRawFuturesAccountAsset assetsItem) {
		if (this.assets == null) {
			this.assets = new ArrayList<BinanceRawFuturesAccountAsset>();
		}
		this.assets.add(assetsItem);
		return this;
	}

	/**
	 * Get assets
	 *
	 * @return assets
	 **/
	@Schema(description = "")
	public List<BinanceRawFuturesAccountAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<BinanceRawFuturesAccountAsset> assets) {
		this.assets = assets;
	}

	public BinanceRawFuturesAccountInfo positions(List<BinanceRawFuturesAccountPosition> positions) {
		this.positions = positions;
		return this;
	}

	public BinanceRawFuturesAccountInfo addPositionsItem(BinanceRawFuturesAccountPosition positionsItem) {
		if (this.positions == null) {
			this.positions = new ArrayList<BinanceRawFuturesAccountPosition>();
		}
		this.positions.add(positionsItem);
		return this;
	}

	/**
	 * Get positions
	 *
	 * @return positions
	 **/
	@Schema(description = "")
	public List<BinanceRawFuturesAccountPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<BinanceRawFuturesAccountPosition> positions) {
		this.positions = positions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesAccountInfo binanceRawFuturesAccountInfo = (BinanceRawFuturesAccountInfo) o;
		return Objects.equals(this.canDeposit, binanceRawFuturesAccountInfo.canDeposit) &&
				Objects.equals(this.canTrade, binanceRawFuturesAccountInfo.canTrade) &&
				Objects.equals(this.canWithdraw, binanceRawFuturesAccountInfo.canWithdraw) &&
				Objects.equals(this.feeTier, binanceRawFuturesAccountInfo.feeTier) &&
				Objects.equals(this.maxWithdrawAmount, binanceRawFuturesAccountInfo.maxWithdrawAmount) &&
				Objects.equals(this.totalInitialMargin, binanceRawFuturesAccountInfo.totalInitialMargin) &&
				Objects.equals(this.totalMaintMargin, binanceRawFuturesAccountInfo.totalMaintMargin) &&
				Objects.equals(this.totalMarginBalance, binanceRawFuturesAccountInfo.totalMarginBalance) &&
				Objects.equals(this.totalOpenOrderInitialMargin, binanceRawFuturesAccountInfo.totalOpenOrderInitialMargin) &&
				Objects.equals(this.totalPositionInitialMargin, binanceRawFuturesAccountInfo.totalPositionInitialMargin) &&
				Objects.equals(this.totalUnrealizedProfit, binanceRawFuturesAccountInfo.totalUnrealizedProfit) &&
				Objects.equals(this.totalWalletBalance, binanceRawFuturesAccountInfo.totalWalletBalance) &&
				Objects.equals(this.totalCrossWalletBalance, binanceRawFuturesAccountInfo.totalCrossWalletBalance) &&
				Objects.equals(this.totalCrossUnPnl, binanceRawFuturesAccountInfo.totalCrossUnPnl) &&
				Objects.equals(this.availableBalance, binanceRawFuturesAccountInfo.availableBalance) &&
				Objects.equals(this.updateTime, binanceRawFuturesAccountInfo.updateTime) &&
				Objects.equals(this.assets, binanceRawFuturesAccountInfo.assets) &&
				Objects.equals(this.positions, binanceRawFuturesAccountInfo.positions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(canDeposit, canTrade, canWithdraw, feeTier, maxWithdrawAmount, totalInitialMargin, totalMaintMargin, totalMarginBalance, totalOpenOrderInitialMargin, totalPositionInitialMargin, totalUnrealizedProfit, totalWalletBalance, totalCrossWalletBalance, totalCrossUnPnl, availableBalance, updateTime, assets, positions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesAccountInfo {\n");

		sb.append("    canDeposit: ").append(toIndentedString(canDeposit)).append("\n");
		sb.append("    canTrade: ").append(toIndentedString(canTrade)).append("\n");
		sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
		sb.append("    feeTier: ").append(toIndentedString(feeTier)).append("\n");
		sb.append("    maxWithdrawAmount: ").append(toIndentedString(maxWithdrawAmount)).append("\n");
		sb.append("    totalInitialMargin: ").append(toIndentedString(totalInitialMargin)).append("\n");
		sb.append("    totalMaintMargin: ").append(toIndentedString(totalMaintMargin)).append("\n");
		sb.append("    totalMarginBalance: ").append(toIndentedString(totalMarginBalance)).append("\n");
		sb.append("    totalOpenOrderInitialMargin: ").append(toIndentedString(totalOpenOrderInitialMargin)).append("\n");
		sb.append("    totalPositionInitialMargin: ").append(toIndentedString(totalPositionInitialMargin)).append("\n");
		sb.append("    totalUnrealizedProfit: ").append(toIndentedString(totalUnrealizedProfit)).append("\n");
		sb.append("    totalWalletBalance: ").append(toIndentedString(totalWalletBalance)).append("\n");
		sb.append("    totalCrossWalletBalance: ").append(toIndentedString(totalCrossWalletBalance)).append("\n");
		sb.append("    totalCrossUnPnl: ").append(toIndentedString(totalCrossUnPnl)).append("\n");
		sb.append("    availableBalance: ").append(toIndentedString(availableBalance)).append("\n");
		sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
		sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
		sb.append("    positions: ").append(toIndentedString(positions)).append("\n");
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
		out.writeValue(canDeposit);
		out.writeValue(canTrade);
		out.writeValue(canWithdraw);
		out.writeValue(feeTier);
		out.writeValue(maxWithdrawAmount);
		out.writeValue(totalInitialMargin);
		out.writeValue(totalMaintMargin);
		out.writeValue(totalMarginBalance);
		out.writeValue(totalOpenOrderInitialMargin);
		out.writeValue(totalPositionInitialMargin);
		out.writeValue(totalUnrealizedProfit);
		out.writeValue(totalWalletBalance);
		out.writeValue(totalCrossWalletBalance);
		out.writeValue(totalCrossUnPnl);
		out.writeValue(availableBalance);
		out.writeValue(updateTime);
		out.writeValue(assets);
		out.writeValue(positions);
	}

	public int describeContents() {
		return 0;
	}
}
