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
 * BinanceRawAccountInfo
 */


public class BinanceRawAccountInfo implements Parcelable
{
	@SerializedName("makerCommission")
	private Double makerCommission = null;

	@SerializedName("takerCommission")
	private Double takerCommission = null;

	@SerializedName("buyerCommission")
	private Double buyerCommission = null;

	@SerializedName("sellerCommission")
	private Double sellerCommission = null;

	@SerializedName("canTrade")
	private Boolean canTrade = null;

	@SerializedName("canWithdraw")
	private Boolean canWithdraw = null;

	@SerializedName("canDeposit")
	private Boolean canDeposit = null;

	@SerializedName("updateTime")
	private DateTime updateTime = null;

	@SerializedName("accountType")
	private BinanceAccountType accountType = null;

	@SerializedName("permissions")
	private List<BinanceAccountType> permissions = null;

	@SerializedName("balances")
	private List<BinanceRawBinanceBalance> balances = null;

	public BinanceRawAccountInfo() {
	}

	public BinanceRawAccountInfo makerCommission(Double makerCommission) {
		this.makerCommission = makerCommission;
		return this;
	}

	/**
	 * Get makerCommission
	 *
	 * @return makerCommission
	 **/
	@Schema(description = "")
	public Double getMakerCommission() {
		return makerCommission;
	}

	public void setMakerCommission(Double makerCommission) {
		this.makerCommission = makerCommission;
	}

	public BinanceRawAccountInfo takerCommission(Double takerCommission) {
		this.takerCommission = takerCommission;
		return this;
	}

	/**
	 * Get takerCommission
	 *
	 * @return takerCommission
	 **/
	@Schema(description = "")
	public Double getTakerCommission() {
		return takerCommission;
	}

	public void setTakerCommission(Double takerCommission) {
		this.takerCommission = takerCommission;
	}

	public BinanceRawAccountInfo buyerCommission(Double buyerCommission) {
		this.buyerCommission = buyerCommission;
		return this;
	}

	/**
	 * Get buyerCommission
	 *
	 * @return buyerCommission
	 **/
	@Schema(description = "")
	public Double getBuyerCommission() {
		return buyerCommission;
	}

	public void setBuyerCommission(Double buyerCommission) {
		this.buyerCommission = buyerCommission;
	}

	public BinanceRawAccountInfo sellerCommission(Double sellerCommission) {
		this.sellerCommission = sellerCommission;
		return this;
	}

	/**
	 * Get sellerCommission
	 *
	 * @return sellerCommission
	 **/
	@Schema(description = "")
	public Double getSellerCommission() {
		return sellerCommission;
	}

	public void setSellerCommission(Double sellerCommission) {
		this.sellerCommission = sellerCommission;
	}

	public BinanceRawAccountInfo canTrade(Boolean canTrade) {
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

	public BinanceRawAccountInfo canWithdraw(Boolean canWithdraw) {
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

	public BinanceRawAccountInfo canDeposit(Boolean canDeposit) {
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

	public BinanceRawAccountInfo updateTime(DateTime updateTime) {
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

	public BinanceRawAccountInfo accountType(BinanceAccountType accountType) {
		this.accountType = accountType;
		return this;
	}

	/**
	 * Get accountType
	 *
	 * @return accountType
	 **/
	@Schema(description = "")
	public BinanceAccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(BinanceAccountType accountType) {
		this.accountType = accountType;
	}

	public BinanceRawAccountInfo permissions(List<BinanceAccountType> permissions) {
		this.permissions = permissions;
		return this;
	}

	public BinanceRawAccountInfo addPermissionsItem(BinanceAccountType permissionsItem) {
		if (this.permissions == null) {
			this.permissions = new ArrayList<BinanceAccountType>();
		}
		this.permissions.add(permissionsItem);
		return this;
	}

	/**
	 * Get permissions
	 *
	 * @return permissions
	 **/
	@Schema(description = "")
	public List<BinanceAccountType> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<BinanceAccountType> permissions) {
		this.permissions = permissions;
	}

	public BinanceRawAccountInfo balances(List<BinanceRawBinanceBalance> balances) {
		this.balances = balances;
		return this;
	}

	public BinanceRawAccountInfo addBalancesItem(BinanceRawBinanceBalance balancesItem) {
		if (this.balances == null) {
			this.balances = new ArrayList<BinanceRawBinanceBalance>();
		}
		this.balances.add(balancesItem);
		return this;
	}

	/**
	 * Get balances
	 *
	 * @return balances
	 **/
	@Schema(description = "")
	public List<BinanceRawBinanceBalance> getBalances() {
		return balances;
	}

	public void setBalances(List<BinanceRawBinanceBalance> balances) {
		this.balances = balances;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawAccountInfo binanceRawAccountInfo = (BinanceRawAccountInfo) o;
		return Objects.equals(this.makerCommission, binanceRawAccountInfo.makerCommission) &&
				Objects.equals(this.takerCommission, binanceRawAccountInfo.takerCommission) &&
				Objects.equals(this.buyerCommission, binanceRawAccountInfo.buyerCommission) &&
				Objects.equals(this.sellerCommission, binanceRawAccountInfo.sellerCommission) &&
				Objects.equals(this.canTrade, binanceRawAccountInfo.canTrade) &&
				Objects.equals(this.canWithdraw, binanceRawAccountInfo.canWithdraw) &&
				Objects.equals(this.canDeposit, binanceRawAccountInfo.canDeposit) &&
				Objects.equals(this.updateTime, binanceRawAccountInfo.updateTime) &&
				Objects.equals(this.accountType, binanceRawAccountInfo.accountType) &&
				Objects.equals(this.permissions, binanceRawAccountInfo.permissions) &&
				Objects.equals(this.balances, binanceRawAccountInfo.balances);
	}

	@Override
	public int hashCode() {
		return Objects.hash(makerCommission, takerCommission, buyerCommission, sellerCommission, canTrade, canWithdraw, canDeposit, updateTime, accountType, permissions, balances);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawAccountInfo {\n");

		sb.append("    makerCommission: ").append(toIndentedString(makerCommission)).append("\n");
		sb.append("    takerCommission: ").append(toIndentedString(takerCommission)).append("\n");
		sb.append("    buyerCommission: ").append(toIndentedString(buyerCommission)).append("\n");
		sb.append("    sellerCommission: ").append(toIndentedString(sellerCommission)).append("\n");
		sb.append("    canTrade: ").append(toIndentedString(canTrade)).append("\n");
		sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
		sb.append("    canDeposit: ").append(toIndentedString(canDeposit)).append("\n");
		sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
		sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
		sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
		sb.append("    balances: ").append(toIndentedString(balances)).append("\n");
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
		out.writeValue(makerCommission);
		out.writeValue(takerCommission);
		out.writeValue(buyerCommission);
		out.writeValue(sellerCommission);
		out.writeValue(canTrade);
		out.writeValue(canWithdraw);
		out.writeValue(canDeposit);
		out.writeValue(updateTime);
		out.writeValue(accountType);
		out.writeValue(permissions);
		out.writeValue(balances);
	}

	public static final Parcelable.Creator<BinanceRawAccountInfo> CREATOR = new Parcelable.Creator<BinanceRawAccountInfo>()
	{
		public BinanceRawAccountInfo createFromParcel(Parcel in) {
			return new BinanceRawAccountInfo(in);
		}

		public BinanceRawAccountInfo[] newArray(int size) {
			return new BinanceRawAccountInfo[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawAccountInfo(Parcel in) {
		makerCommission = (Double) in.readValue(null);
		takerCommission = (Double) in.readValue(null);
		buyerCommission = (Double) in.readValue(null);
		sellerCommission = (Double) in.readValue(null);
		canTrade = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		canDeposit = (Boolean) in.readValue(null);
		updateTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		accountType = (BinanceAccountType) in.readValue(BinanceAccountType.class.getClassLoader());
		permissions = (List<BinanceAccountType>) in.readValue(BinanceAccountType.class.getClassLoader());
		balances = (List<BinanceRawBinanceBalance>) in.readValue(BinanceRawBinanceBalance.class.getClassLoader());
	}
}
