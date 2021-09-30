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
 * TickerChart
 */


public class TickerChart implements Parcelable
{
	public static final Parcelable.Creator<TickerChart> CREATOR = new Parcelable.Creator<TickerChart>()
	{
		public TickerChart createFromParcel(Parcel in) {
			return new TickerChart(in);
		}

		public TickerChart[] newArray(int size) {
			return new TickerChart[size];
		}
	};

	@SerializedName("chart")
	private List<SimpleChartPoint> chart = null;

	@SerializedName("lastPrice")
	private Double lastPrice = null;

	@SerializedName("changePercent")
	private Double changePercent = null;

	public TickerChart() {
	}

	TickerChart(Parcel in) {
		chart = (List<SimpleChartPoint>) in.readValue(SimpleChartPoint.class.getClassLoader());
		lastPrice = (Double) in.readValue(null);
		changePercent = (Double) in.readValue(null);
	}

	public TickerChart chart(List<SimpleChartPoint> chart) {
		this.chart = chart;
		return this;
	}

	public TickerChart addChartItem(SimpleChartPoint chartItem) {
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

	public TickerChart lastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	/**
	 * Get lastPrice
	 *
	 * @return lastPrice
	 **/
	@Schema(description = "")
	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public TickerChart changePercent(Double changePercent) {
		this.changePercent = changePercent;
		return this;
	}

	/**
	 * Get changePercent
	 *
	 * @return changePercent
	 **/
	@Schema(description = "")
	public Double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TickerChart tickerChart = (TickerChart) o;
		return Objects.equals(this.chart, tickerChart.chart) &&
				Objects.equals(this.lastPrice, tickerChart.lastPrice) &&
				Objects.equals(this.changePercent, tickerChart.changePercent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chart, lastPrice, changePercent);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TickerChart {\n");

		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
		sb.append("    lastPrice: ").append(toIndentedString(lastPrice)).append("\n");
		sb.append("    changePercent: ").append(toIndentedString(changePercent)).append("\n");
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
		out.writeValue(lastPrice);
		out.writeValue(changePercent);
	}

	public int describeContents() {
		return 0;
	}
}
