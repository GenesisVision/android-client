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
 * DashboardProfits
 */


public class DashboardProfits implements Parcelable
{
	public static final Parcelable.Creator<DashboardProfits> CREATOR = new Parcelable.Creator<DashboardProfits>()
	{
		public DashboardProfits createFromParcel(Parcel in) {
			return new DashboardProfits(in);
		}

		public DashboardProfits[] newArray(int size) {
			return new DashboardProfits[size];
		}
	};

	@SerializedName("day")
	private DashboardTimeframeProfit day = null;

	@SerializedName("week")
	private DashboardTimeframeProfit week = null;

	@SerializedName("month")
	private DashboardTimeframeProfit month = null;

	public DashboardProfits() {
	}

	DashboardProfits(Parcel in) {
		day = (DashboardTimeframeProfit) in.readValue(DashboardTimeframeProfit.class.getClassLoader());
		week = (DashboardTimeframeProfit) in.readValue(DashboardTimeframeProfit.class.getClassLoader());
		month = (DashboardTimeframeProfit) in.readValue(DashboardTimeframeProfit.class.getClassLoader());
	}

	public DashboardProfits day(DashboardTimeframeProfit day) {
		this.day = day;
		return this;
	}

	/**
	 * Get day
	 *
	 * @return day
	 **/
	@Schema(description = "")
	public DashboardTimeframeProfit getDay() {
		return day;
	}

	public void setDay(DashboardTimeframeProfit day) {
		this.day = day;
	}

	public DashboardProfits week(DashboardTimeframeProfit week) {
		this.week = week;
		return this;
	}

	/**
	 * Get week
	 *
	 * @return week
	 **/
	@Schema(description = "")
	public DashboardTimeframeProfit getWeek() {
		return week;
	}

	public void setWeek(DashboardTimeframeProfit week) {
		this.week = week;
	}

	public DashboardProfits month(DashboardTimeframeProfit month) {
		this.month = month;
		return this;
	}

	/**
	 * Get month
	 *
	 * @return month
	 **/
	@Schema(description = "")
	public DashboardTimeframeProfit getMonth() {
		return month;
	}

	public void setMonth(DashboardTimeframeProfit month) {
		this.month = month;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardProfits dashboardProfits = (DashboardProfits) o;
		return Objects.equals(this.day, dashboardProfits.day) &&
				Objects.equals(this.week, dashboardProfits.week) &&
				Objects.equals(this.month, dashboardProfits.month);
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, week, month);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardProfits {\n");

		sb.append("    day: ").append(toIndentedString(day)).append("\n");
		sb.append("    week: ").append(toIndentedString(week)).append("\n");
		sb.append("    month: ").append(toIndentedString(month)).append("\n");
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
		out.writeValue(day);
		out.writeValue(week);
		out.writeValue(month);
	}

	public int describeContents() {
		return 0;
	}
}
