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
 * AbsoluteProfitChart
 */


public class AbsoluteProfitChart implements Parcelable
{
	public static final Parcelable.Creator<AbsoluteProfitChart> CREATOR = new Parcelable.Creator<AbsoluteProfitChart>()
	{
		public AbsoluteProfitChart createFromParcel(Parcel in) {
			return new AbsoluteProfitChart(in);
		}

		public AbsoluteProfitChart[] newArray(int size) {
			return new AbsoluteProfitChart[size];
		}
	};

	@SerializedName("chart")
	private List<SimpleChartPoint> chart = null;

	public AbsoluteProfitChart() {
	}

	AbsoluteProfitChart(Parcel in) {
		chart = (List<SimpleChartPoint>) in.readValue(SimpleChartPoint.class.getClassLoader());
	}

	public AbsoluteProfitChart chart(List<SimpleChartPoint> chart) {
		this.chart = chart;
		return this;
	}

	public AbsoluteProfitChart addChartItem(SimpleChartPoint chartItem) {
		if (this.chart == null) {
			this.chart = new ArrayList<SimpleChartPoint>();
		}
		this.chart.add(chartItem);
		return this;
	}

	/**
	 * Get chart
	 *
	 * @return chart
	 **/
	@Schema(description = "")
	public List<SimpleChartPoint> getChart() {
		return chart;
	}

	public void setChart(List<SimpleChartPoint> chart) {
		this.chart = chart;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AbsoluteProfitChart absoluteProfitChart = (AbsoluteProfitChart) o;
		return Objects.equals(this.chart, absoluteProfitChart.chart);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chart);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AbsoluteProfitChart {\n");

		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
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
		out.writeValue(chart);
	}

	public int describeContents() {
		return 0;
	}
}
