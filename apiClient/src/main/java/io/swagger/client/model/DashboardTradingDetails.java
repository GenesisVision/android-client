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
 * DashboardTradingDetails
 */


public class DashboardTradingDetails implements Parcelable
{
	public static final Parcelable.Creator<DashboardTradingDetails> CREATOR = new Parcelable.Creator<DashboardTradingDetails>()
	{
		public DashboardTradingDetails createFromParcel(Parcel in) {
			return new DashboardTradingDetails(in);
		}

		public DashboardTradingDetails[] newArray(int size) {
			return new DashboardTradingDetails[size];
		}
	};

	@SerializedName("equity")
	private Double equity = null;

	@SerializedName("aum")
	private Double aum = null;

	@SerializedName("total")
	private Double total = null;

	@SerializedName("profits")
	private DashboardProfits profits = null;

	@SerializedName("events")
	private ItemsViewModelInvestmentEventViewModel events = null;

	public DashboardTradingDetails() {
	}

	DashboardTradingDetails(Parcel in) {
		equity = (Double) in.readValue(null);
		aum = (Double) in.readValue(null);
		total = (Double) in.readValue(null);
		profits = (DashboardProfits) in.readValue(DashboardProfits.class.getClassLoader());
		events = (ItemsViewModelInvestmentEventViewModel) in.readValue(ItemsViewModelInvestmentEventViewModel.class.getClassLoader());
	}

	public DashboardTradingDetails equity(Double equity) {
		this.equity = equity;
		return this;
	}

	/**
	 * Get equity
	 *
	 * @return equity
	 **/
	@Schema(description = "")
	public Double getEquity() {
		return equity;
	}

	public void setEquity(Double equity) {
		this.equity = equity;
	}

	public DashboardTradingDetails aum(Double aum) {
		this.aum = aum;
		return this;
	}

	/**
	 * Get aum
	 *
	 * @return aum
	 **/
	@Schema(description = "")
	public Double getAum() {
		return aum;
	}

	public void setAum(Double aum) {
		this.aum = aum;
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

	public DashboardTradingDetails profits(DashboardProfits profits) {
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

	public DashboardTradingDetails events(ItemsViewModelInvestmentEventViewModel events) {
		this.events = events;
		return this;
	}

	/**
	 * Get events
	 *
	 * @return events
	 **/
	@Schema(description = "")
	public ItemsViewModelInvestmentEventViewModel getEvents() {
		return events;
	}

	public void setEvents(ItemsViewModelInvestmentEventViewModel events) {
		this.events = events;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardTradingDetails dashboardTradingDetails = (DashboardTradingDetails) o;
		return Objects.equals(this.equity, dashboardTradingDetails.equity) &&
				Objects.equals(this.aum, dashboardTradingDetails.aum) &&
				Objects.equals(this.total, dashboardTradingDetails.total) &&
				Objects.equals(this.profits, dashboardTradingDetails.profits) &&
				Objects.equals(this.events, dashboardTradingDetails.events);
	}

	@Override
	public int hashCode() {
		return Objects.hash(equity, aum, total, profits, events);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardTradingDetails {\n");

		sb.append("    equity: ").append(toIndentedString(equity)).append("\n");
		sb.append("    aum: ").append(toIndentedString(aum)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    profits: ").append(toIndentedString(profits)).append("\n");
		sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
		out.writeValue(equity);
		out.writeValue(aum);
		out.writeValue(total);
		out.writeValue(profits);
		out.writeValue(events);
	}

	public int describeContents() {
		return 0;
	}
}