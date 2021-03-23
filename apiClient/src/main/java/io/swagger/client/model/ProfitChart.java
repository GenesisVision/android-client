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
 * ProfitChart
 */


public class ProfitChart implements Parcelable
{
	@SerializedName("chart")
	private List<SimpleChartPoint> chart = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("drawdown")
	private Double drawdown = null;

	public ProfitChart() {
	}

	public ProfitChart chart(List<SimpleChartPoint> chart) {
		this.chart = chart;
		return this;
	}

	public ProfitChart addChartItem(SimpleChartPoint chartItem) {
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

	public ProfitChart profit(Double profit) {
		this.profit = profit;
		return this;
	}

	/**
	 * Get profit
	 *
	 * @return profit
	 **/
	@Schema(description = "")
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public ProfitChart drawdown(Double drawdown) {
		this.drawdown = drawdown;
		return this;
	}

	/**
	 * Get drawdown
	 *
	 * @return drawdown
	 **/
	@Schema(description = "")
	public Double getDrawdown() {
		return drawdown;
	}

	public void setDrawdown(Double drawdown) {
		this.drawdown = drawdown;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProfitChart profitChart = (ProfitChart) o;
		return Objects.equals(this.chart, profitChart.chart) &&
				Objects.equals(this.profit, profitChart.profit) &&
				Objects.equals(this.drawdown, profitChart.drawdown);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chart, profit, drawdown);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfitChart {\n");

		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    drawdown: ").append(toIndentedString(drawdown)).append("\n");
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
		out.writeValue(profit);
		out.writeValue(drawdown);
	}

	public static final Parcelable.Creator<ProfitChart> CREATOR = new Parcelable.Creator<ProfitChart>()
	{
		public ProfitChart createFromParcel(Parcel in) {
			return new ProfitChart(in);
		}

		public ProfitChart[] newArray(int size) {
			return new ProfitChart[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ProfitChart(Parcel in) {
		chart = (List<SimpleChartPoint>) in.readValue(SimpleChartPoint.class.getClassLoader());
		profit = (Double) in.readValue(null);
		drawdown = (Double) in.readValue(null);
	}
}
