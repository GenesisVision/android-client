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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DashboardAssetChart
 */


public class DashboardAssetChart implements Parcelable
{
	public static final Parcelable.Creator<DashboardAssetChart> CREATOR = new Parcelable.Creator<DashboardAssetChart>()
	{
		public DashboardAssetChart createFromParcel(Parcel in) {
			return new DashboardAssetChart(in);
		}

		public DashboardAssetChart[] newArray(int size) {
			return new DashboardAssetChart[size];
		}
	};

	@SerializedName("assetId")
	private UUID assetId = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("chart")
	private List<SimpleChartPoint> chart = null;

	public DashboardAssetChart() {
	}

	DashboardAssetChart(Parcel in) {
		assetId = (UUID) in.readValue(UUID.class.getClassLoader());
		color = (String) in.readValue(null);
		chart = (List<SimpleChartPoint>) in.readValue(SimpleChartPoint.class.getClassLoader());
	}

	public DashboardAssetChart assetId(UUID assetId) {
		this.assetId = assetId;
		return this;
	}

	/**
	 * Get assetId
	 *
	 * @return assetId
	 **/
	@Schema(description = "")
	public UUID getAssetId() {
		return assetId;
	}

	public void setAssetId(UUID assetId) {
		this.assetId = assetId;
	}

	public DashboardAssetChart color(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Get color
	 *
	 * @return color
	 **/
	@Schema(description = "")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public DashboardAssetChart chart(List<SimpleChartPoint> chart) {
		this.chart = chart;
		return this;
	}

	public DashboardAssetChart addChartItem(SimpleChartPoint chartItem) {
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
		DashboardAssetChart dashboardAssetChart = (DashboardAssetChart) o;
		return Objects.equals(this.assetId, dashboardAssetChart.assetId) &&
				Objects.equals(this.color, dashboardAssetChart.color) &&
				Objects.equals(this.chart, dashboardAssetChart.chart);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, color, chart);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardAssetChart {\n");

		sb.append("    assetId: ").append(toIndentedString(assetId)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
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
		out.writeValue(assetId);
		out.writeValue(color);
		out.writeValue(chart);
	}

	public int describeContents() {
		return 0;
	}
}