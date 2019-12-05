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
 * FollowProfitPercentCharts
 */


public class FollowProfitPercentCharts implements Parcelable
{
	public static final Parcelable.Creator<FollowProfitPercentCharts> CREATOR = new Parcelable.Creator<FollowProfitPercentCharts>()
	{
		public FollowProfitPercentCharts createFromParcel(Parcel in) {
			return new FollowProfitPercentCharts(in);
		}

		public FollowProfitPercentCharts[] newArray(int size) {
			return new FollowProfitPercentCharts[size];
		}
	};

	@SerializedName("statistic")
	private FollowChartStatistic statistic = null;

	@SerializedName("charts")
	private List<SimpleChart> charts = null;

	public FollowProfitPercentCharts() {
	}

	FollowProfitPercentCharts(Parcel in) {
		statistic = (FollowChartStatistic) in.readValue(FollowChartStatistic.class.getClassLoader());
		charts = (List<SimpleChart>) in.readValue(SimpleChart.class.getClassLoader());
	}

	public FollowProfitPercentCharts statistic(FollowChartStatistic statistic) {
		this.statistic = statistic;
		return this;
	}

	/**
	 * Get statistic
	 *
	 * @return statistic
	 **/
	@Schema(description = "")
	public FollowChartStatistic getStatistic() {
		return statistic;
	}

	public void setStatistic(FollowChartStatistic statistic) {
		this.statistic = statistic;
	}

	public FollowProfitPercentCharts charts(List<SimpleChart> charts) {
		this.charts = charts;
		return this;
	}

	public FollowProfitPercentCharts addChartsItem(SimpleChart chartsItem) {
		if (this.charts == null) {
			this.charts = new ArrayList<SimpleChart>();
		}
		this.charts.add(chartsItem);
		return this;
	}

	/**
	 * Get charts
	 *
	 * @return charts
	 **/
	@Schema(description = "")
	public List<SimpleChart> getCharts() {
		return charts;
	}

	public void setCharts(List<SimpleChart> charts) {
		this.charts = charts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FollowProfitPercentCharts followProfitPercentCharts = (FollowProfitPercentCharts) o;
		return Objects.equals(this.statistic, followProfitPercentCharts.statistic) &&
				Objects.equals(this.charts, followProfitPercentCharts.charts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statistic, charts);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FollowProfitPercentCharts {\n");

		sb.append("    statistic: ").append(toIndentedString(statistic)).append("\n");
		sb.append("    charts: ").append(toIndentedString(charts)).append("\n");
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
		out.writeValue(statistic);
		out.writeValue(charts);
	}

	public int describeContents() {
		return 0;
	}
}
