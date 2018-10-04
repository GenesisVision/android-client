/*
 * Core API v1.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProgramProfitChart
 */

public class ProgramProfitChart
{
	@SerializedName("totalProgramCurrencyProfit")
	private Double totalProgramCurrencyProfit = null;

	@SerializedName("timeframeProgramCurrencyProfit")
	private Double timeframeProgramCurrencyProfit = null;

	@SerializedName("programCurrency")
	private ProgramCurrencyEnum programCurrency = null;

	@SerializedName("trades")
	private Integer trades = null;

	@SerializedName("successTradesPercent")
	private Double successTradesPercent = null;

	@SerializedName("profitFactor")
	private Double profitFactor = null;

	@SerializedName("maxDrawdown")
	private Double maxDrawdown = null;

	@SerializedName("chart")
	private List<ChartProgramDetails> chart = null;

	@SerializedName("pnLChart")
	private List<ChartSimple> pnLChart = null;

	@SerializedName("equity")
	private Double equity = null;

	@SerializedName("sharpeRatio")
	private Double sharpeRatio = null;

	@SerializedName("sortinoRatio")
	private Double sortinoRatio = null;

	@SerializedName("calmarRatio")
	private Double calmarRatio = null;

	@SerializedName("totalGvtProfit")
	private Double totalGvtProfit = null;

	@SerializedName("timeframeGvtProfit")
	private Double timeframeGvtProfit = null;

	@SerializedName("profitChangePercent")
	private Double profitChangePercent = null;

	public ProgramProfitChart totalProgramCurrencyProfit(Double totalProgramCurrencyProfit) {
		this.totalProgramCurrencyProfit = totalProgramCurrencyProfit;
		return this;
	}

	/**
	 * Get totalProgramCurrencyProfit
	 *
	 * @return totalProgramCurrencyProfit
	 **/
	@ApiModelProperty(value = "")
	public Double getTotalProgramCurrencyProfit() {
		return totalProgramCurrencyProfit;
	}

	public void setTotalProgramCurrencyProfit(Double totalProgramCurrencyProfit) {
		this.totalProgramCurrencyProfit = totalProgramCurrencyProfit;
	}

	public ProgramProfitChart timeframeProgramCurrencyProfit(Double timeframeProgramCurrencyProfit) {
		this.timeframeProgramCurrencyProfit = timeframeProgramCurrencyProfit;
		return this;
	}

	/**
	 * Get timeframeProgramCurrencyProfit
	 *
	 * @return timeframeProgramCurrencyProfit
	 **/
	@ApiModelProperty(value = "")
	public Double getTimeframeProgramCurrencyProfit() {
		return timeframeProgramCurrencyProfit;
	}

	public void setTimeframeProgramCurrencyProfit(Double timeframeProgramCurrencyProfit) {
		this.timeframeProgramCurrencyProfit = timeframeProgramCurrencyProfit;
	}

	public ProgramProfitChart programCurrency(ProgramCurrencyEnum programCurrency) {
		this.programCurrency = programCurrency;
		return this;
	}

	/**
	 * Get programCurrency
	 *
	 * @return programCurrency
	 **/
	@ApiModelProperty(value = "")
	public ProgramCurrencyEnum getProgramCurrency() {
		return programCurrency;
	}

	public void setProgramCurrency(ProgramCurrencyEnum programCurrency) {
		this.programCurrency = programCurrency;
	}

	public ProgramProfitChart trades(Integer trades) {
		this.trades = trades;
		return this;
	}

	/**
	 * Get trades
	 *
	 * @return trades
	 **/
	@ApiModelProperty(value = "")
	public Integer getTrades() {
		return trades;
	}

	public void setTrades(Integer trades) {
		this.trades = trades;
	}

	public ProgramProfitChart successTradesPercent(Double successTradesPercent) {
		this.successTradesPercent = successTradesPercent;
		return this;
	}

	/**
	 * Get successTradesPercent
	 *
	 * @return successTradesPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getSuccessTradesPercent() {
		return successTradesPercent;
	}

	public void setSuccessTradesPercent(Double successTradesPercent) {
		this.successTradesPercent = successTradesPercent;
	}

	public ProgramProfitChart profitFactor(Double profitFactor) {
		this.profitFactor = profitFactor;
		return this;
	}

	/**
	 * Get profitFactor
	 *
	 * @return profitFactor
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitFactor() {
		return profitFactor;
	}

	public void setProfitFactor(Double profitFactor) {
		this.profitFactor = profitFactor;
	}

	public ProgramProfitChart maxDrawdown(Double maxDrawdown) {
		this.maxDrawdown = maxDrawdown;
		return this;
	}

	/**
	 * Get maxDrawdown
	 *
	 * @return maxDrawdown
	 **/
	@ApiModelProperty(value = "")
	public Double getMaxDrawdown() {
		return maxDrawdown;
	}

	public void setMaxDrawdown(Double maxDrawdown) {
		this.maxDrawdown = maxDrawdown;
	}

	public ProgramProfitChart chart(List<ChartProgramDetails> chart) {
		this.chart = chart;
		return this;
	}

	public ProgramProfitChart addChartItem(ChartProgramDetails chartItem) {
		if (this.chart == null) {
			this.chart = new ArrayList<ChartProgramDetails>();
		}
		this.chart.add(chartItem);
		return this;
	}

	/**
	 * Get chart
	 *
	 * @return chart
	 **/
	@ApiModelProperty(value = "")
	public List<ChartProgramDetails> getChart() {
		return chart;
	}

	public void setChart(List<ChartProgramDetails> chart) {
		this.chart = chart;
	}

	public ProgramProfitChart pnLChart(List<ChartSimple> pnLChart) {
		this.pnLChart = pnLChart;
		return this;
	}

	public ProgramProfitChart addPnLChartItem(ChartSimple pnLChartItem) {
		if (this.pnLChart == null) {
			this.pnLChart = new ArrayList<ChartSimple>();
		}
		this.pnLChart.add(pnLChartItem);
		return this;
	}

	/**
	 * Get pnLChart
	 *
	 * @return pnLChart
	 **/
	@ApiModelProperty(value = "")
	public List<ChartSimple> getPnLChart() {
		return pnLChart;
	}

	public void setPnLChart(List<ChartSimple> pnLChart) {
		this.pnLChart = pnLChart;
	}

	public ProgramProfitChart equity(Double equity) {
		this.equity = equity;
		return this;
	}

	/**
	 * Get equity
	 *
	 * @return equity
	 **/
	@ApiModelProperty(value = "")
	public Double getEquity() {
		return equity;
	}

	public void setEquity(Double equity) {
		this.equity = equity;
	}

	public ProgramProfitChart sharpeRatio(Double sharpeRatio) {
		this.sharpeRatio = sharpeRatio;
		return this;
	}

	/**
	 * Get sharpeRatio
	 *
	 * @return sharpeRatio
	 **/
	@ApiModelProperty(value = "")
	public Double getSharpeRatio() {
		return sharpeRatio;
	}

	public void setSharpeRatio(Double sharpeRatio) {
		this.sharpeRatio = sharpeRatio;
	}

	public ProgramProfitChart sortinoRatio(Double sortinoRatio) {
		this.sortinoRatio = sortinoRatio;
		return this;
	}

	/**
	 * Get sortinoRatio
	 *
	 * @return sortinoRatio
	 **/
	@ApiModelProperty(value = "")
	public Double getSortinoRatio() {
		return sortinoRatio;
	}

	public void setSortinoRatio(Double sortinoRatio) {
		this.sortinoRatio = sortinoRatio;
	}

	public ProgramProfitChart calmarRatio(Double calmarRatio) {
		this.calmarRatio = calmarRatio;
		return this;
	}

	/**
	 * Get calmarRatio
	 *
	 * @return calmarRatio
	 **/
	@ApiModelProperty(value = "")
	public Double getCalmarRatio() {
		return calmarRatio;
	}

	public void setCalmarRatio(Double calmarRatio) {
		this.calmarRatio = calmarRatio;
	}

	public ProgramProfitChart totalGvtProfit(Double totalGvtProfit) {
		this.totalGvtProfit = totalGvtProfit;
		return this;
	}

	/**
	 * Get totalGvtProfit
	 *
	 * @return totalGvtProfit
	 **/
	@ApiModelProperty(value = "")
	public Double getTotalGvtProfit() {
		return totalGvtProfit;
	}

	public void setTotalGvtProfit(Double totalGvtProfit) {
		this.totalGvtProfit = totalGvtProfit;
	}

	public ProgramProfitChart timeframeGvtProfit(Double timeframeGvtProfit) {
		this.timeframeGvtProfit = timeframeGvtProfit;
		return this;
	}

	/**
	 * Get timeframeGvtProfit
	 *
	 * @return timeframeGvtProfit
	 **/
	@ApiModelProperty(value = "")
	public Double getTimeframeGvtProfit() {
		return timeframeGvtProfit;
	}

	public void setTimeframeGvtProfit(Double timeframeGvtProfit) {
		this.timeframeGvtProfit = timeframeGvtProfit;
	}

	public ProgramProfitChart profitChangePercent(Double profitChangePercent) {
		this.profitChangePercent = profitChangePercent;
		return this;
	}

	/**
	 * Get profitChangePercent
	 *
	 * @return profitChangePercent
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitChangePercent() {
		return profitChangePercent;
	}

	public void setProfitChangePercent(Double profitChangePercent) {
		this.profitChangePercent = profitChangePercent;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramProfitChart programProfitChart = (ProgramProfitChart) o;
		return Objects.equals(this.totalProgramCurrencyProfit, programProfitChart.totalProgramCurrencyProfit) &&
				Objects.equals(this.timeframeProgramCurrencyProfit, programProfitChart.timeframeProgramCurrencyProfit) &&
				Objects.equals(this.programCurrency, programProfitChart.programCurrency) &&
				Objects.equals(this.trades, programProfitChart.trades) &&
				Objects.equals(this.successTradesPercent, programProfitChart.successTradesPercent) &&
				Objects.equals(this.profitFactor, programProfitChart.profitFactor) &&
				Objects.equals(this.maxDrawdown, programProfitChart.maxDrawdown) &&
				Objects.equals(this.chart, programProfitChart.chart) &&
				Objects.equals(this.pnLChart, programProfitChart.pnLChart) &&
				Objects.equals(this.equity, programProfitChart.equity) &&
				Objects.equals(this.sharpeRatio, programProfitChart.sharpeRatio) &&
				Objects.equals(this.sortinoRatio, programProfitChart.sortinoRatio) &&
				Objects.equals(this.calmarRatio, programProfitChart.calmarRatio) &&
				Objects.equals(this.totalGvtProfit, programProfitChart.totalGvtProfit) &&
				Objects.equals(this.timeframeGvtProfit, programProfitChart.timeframeGvtProfit) &&
				Objects.equals(this.profitChangePercent, programProfitChart.profitChangePercent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalProgramCurrencyProfit, timeframeProgramCurrencyProfit, programCurrency, trades, successTradesPercent, profitFactor, maxDrawdown, chart, pnLChart, equity, sharpeRatio, sortinoRatio, calmarRatio, totalGvtProfit, timeframeGvtProfit, profitChangePercent);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramProfitChart {\n");

		sb.append("    totalProgramCurrencyProfit: ").append(toIndentedString(totalProgramCurrencyProfit)).append("\n");
		sb.append("    timeframeProgramCurrencyProfit: ").append(toIndentedString(timeframeProgramCurrencyProfit)).append("\n");
		sb.append("    programCurrency: ").append(toIndentedString(programCurrency)).append("\n");
		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
		sb.append("    successTradesPercent: ").append(toIndentedString(successTradesPercent)).append("\n");
		sb.append("    profitFactor: ").append(toIndentedString(profitFactor)).append("\n");
		sb.append("    maxDrawdown: ").append(toIndentedString(maxDrawdown)).append("\n");
		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
		sb.append("    pnLChart: ").append(toIndentedString(pnLChart)).append("\n");
		sb.append("    equity: ").append(toIndentedString(equity)).append("\n");
		sb.append("    sharpeRatio: ").append(toIndentedString(sharpeRatio)).append("\n");
		sb.append("    sortinoRatio: ").append(toIndentedString(sortinoRatio)).append("\n");
		sb.append("    calmarRatio: ").append(toIndentedString(calmarRatio)).append("\n");
		sb.append("    totalGvtProfit: ").append(toIndentedString(totalGvtProfit)).append("\n");
		sb.append("    timeframeGvtProfit: ").append(toIndentedString(timeframeGvtProfit)).append("\n");
		sb.append("    profitChangePercent: ").append(toIndentedString(profitChangePercent)).append("\n");
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

	/**
	 * Gets or Sets programCurrency
	 */
	@JsonAdapter(ProgramCurrencyEnum.Adapter.class)
	public enum ProgramCurrencyEnum
	{
		ETH("ETH"),

		GVT("GVT"),

		BTC("BTC"),

		UNDEFINED("Undefined"),

		ADA("ADA"),

		USD("USD"),

		EUR("EUR");

		public static ProgramCurrencyEnum fromValue(String text) {
			for (ProgramCurrencyEnum b : ProgramCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		ProgramCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<ProgramCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final ProgramCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public ProgramCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return ProgramCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

}
