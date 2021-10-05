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
 * DashboardSummary
 */


public class DashboardSummary implements Parcelable
{
	@SerializedName("invested")
	private Double invested = null;

	@SerializedName("trading")
	private Double trading = null;

	@SerializedName("wallets")
	private Double wallets = null;

	@SerializedName("total")
	private Double total = null;

	@SerializedName("profits")
	private DashboardProfits profits = null;

	@SerializedName("limitWithoutKyc")
	private LimitWithoutKyc limitWithoutKyc = null;

	public DashboardSummary() {
	}

	public DashboardSummary invested(Double invested) {
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

	public DashboardSummary trading(Double trading) {
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

	public DashboardSummary wallets(Double wallets) {
		this.wallets = wallets;
		return this;
	}

	/**
	 * Get wallets
	 *
	 * @return wallets
	 **/
	@Schema(description = "")
	public Double getWallets() {
		return wallets;
	}

	public void setWallets(Double wallets) {
		this.wallets = wallets;
	}

	public DashboardSummary total(Double total) {
		this.total = total;
		return this;
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

	public void setTotal(Double total) {
		this.total = total;
	}

	public DashboardSummary profits(DashboardProfits profits) {
		this.profits = profits;
		return this;
	}

	/**
	 * Get profits
	 *
	 * @return profits
	 **/
	@Schema(description = "")
	public DashboardProfits getProfits() {
		return profits;
	}

	public void setProfits(DashboardProfits profits) {
		this.profits = profits;
	}

	public DashboardSummary limitWithoutKyc(LimitWithoutKyc limitWithoutKyc) {
		this.limitWithoutKyc = limitWithoutKyc;
		return this;
	}

	/**
	 * Get limitWithoutKyc
	 *
	 * @return limitWithoutKyc
	 **/
	@Schema(description = "")
	public LimitWithoutKyc getLimitWithoutKyc() {
		return limitWithoutKyc;
	}

	public void setLimitWithoutKyc(LimitWithoutKyc limitWithoutKyc) {
		this.limitWithoutKyc = limitWithoutKyc;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardSummary dashboardSummary = (DashboardSummary) o;
		return Objects.equals(this.invested, dashboardSummary.invested) &&
				Objects.equals(this.trading, dashboardSummary.trading) &&
				Objects.equals(this.wallets, dashboardSummary.wallets) &&
				Objects.equals(this.total, dashboardSummary.total) &&
				Objects.equals(this.profits, dashboardSummary.profits) &&
				Objects.equals(this.limitWithoutKyc, dashboardSummary.limitWithoutKyc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(invested, trading, wallets, total, profits, limitWithoutKyc);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardSummary {\n");

		sb.append("    invested: ").append(toIndentedString(invested)).append("\n");
		sb.append("    trading: ").append(toIndentedString(trading)).append("\n");
		sb.append("    wallets: ").append(toIndentedString(wallets)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    profits: ").append(toIndentedString(profits)).append("\n");
		sb.append("    limitWithoutKyc: ").append(toIndentedString(limitWithoutKyc)).append("\n");
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
		out.writeValue(invested);
		out.writeValue(trading);
		out.writeValue(wallets);
		out.writeValue(total);
		out.writeValue(profits);
		out.writeValue(limitWithoutKyc);
	}

	public static final Parcelable.Creator<DashboardSummary> CREATOR = new Parcelable.Creator<DashboardSummary>()
	{
		public DashboardSummary createFromParcel(Parcel in) {
			return new DashboardSummary(in);
		}

		public DashboardSummary[] newArray(int size) {
			return new DashboardSummary[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	DashboardSummary(Parcel in) {
		invested = (Double) in.readValue(null);
		trading = (Double) in.readValue(null);
		wallets = (Double) in.readValue(null);
		total = (Double) in.readValue(null);
		profits = (DashboardProfits) in.readValue(DashboardProfits.class.getClassLoader());
		limitWithoutKyc = (LimitWithoutKyc) in.readValue(LimitWithoutKyc.class.getClassLoader());
	}
}
