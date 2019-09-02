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

import org.joda.time.DateTime;

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
	@SerializedName("equityChart")
	private List<ChartSimple> equityChart = null;

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

	@SerializedName("pnLChart")
	private List<ChartSimple> pnLChart = null;

	@SerializedName("periods")
	private List<PeriodDate> periods = null;

	@SerializedName("lastPeriodStarts")
	private DateTime lastPeriodStarts = null;

	@SerializedName("lastPeriodEnds")
	private DateTime lastPeriodEnds = null;

	@SerializedName("tradingVolume")
	private Double tradingVolume = null;

	@SerializedName("totalGvtProfit")
	private Double totalGvtProfit = null;

	@SerializedName("timeframeGvtProfit")
	private Double timeframeGvtProfit = null;

	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("investors")
	private Integer investors = null;

	@SerializedName("profitChangePercent")
	private Double profitChangePercent = null;

	@SerializedName("sharpeRatio")
	private Double sharpeRatio = null;

	@SerializedName("sortinoRatio")
	private Double sortinoRatio = null;

	@SerializedName("calmarRatio")
	private Double calmarRatio = null;

	@SerializedName("maxDrawdown")
	private Double maxDrawdown = null;

	@SerializedName("rate")
	private Double rate = null;

	public ProgramProfitChart equityChart(List<ChartSimple> equityChart) {
		this.equityChart = equityChart;
		return this;
	}

	public ProgramProfitChart addEquityChartItem(ChartSimple equityChartItem) {
		if (this.equityChart == null) {
			this.equityChart = new ArrayList<ChartSimple>();
		}
		this.equityChart.add(equityChartItem);
		return this;
	}

	/**
	 * Get equityChart
	 *
	 * @return equityChart
	 **/
	@ApiModelProperty(value = "")
	public List<ChartSimple> getEquityChart() {
		return equityChart;
	}

	public void setEquityChart(List<ChartSimple> equityChart) {
		this.equityChart = equityChart;
	}

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

	public ProgramProfitChart periods(List<PeriodDate> periods) {
		this.periods = periods;
		return this;
	}

	public ProgramProfitChart addPeriodsItem(PeriodDate periodsItem) {
		if (this.periods == null) {
			this.periods = new ArrayList<PeriodDate>();
		}
		this.periods.add(periodsItem);
		return this;
	}

	/**
	 * Get periods
	 *
	 * @return periods
	 **/
	@ApiModelProperty(value = "")
	public List<PeriodDate> getPeriods() {
		return periods;
	}

	public void setPeriods(List<PeriodDate> periods) {
		this.periods = periods;
	}

	public ProgramProfitChart lastPeriodStarts(DateTime lastPeriodStarts) {
		this.lastPeriodStarts = lastPeriodStarts;
		return this;
	}

	/**
	 * Get lastPeriodStarts
	 *
	 * @return lastPeriodStarts
	 **/
	@ApiModelProperty(value = "")
	public DateTime getLastPeriodStarts() {
		return lastPeriodStarts;
	}

	public void setLastPeriodStarts(DateTime lastPeriodStarts) {
		this.lastPeriodStarts = lastPeriodStarts;
	}

	public ProgramProfitChart lastPeriodEnds(DateTime lastPeriodEnds) {
		this.lastPeriodEnds = lastPeriodEnds;
		return this;
	}

	/**
	 * Get lastPeriodEnds
	 *
	 * @return lastPeriodEnds
	 **/
	@ApiModelProperty(value = "")
	public DateTime getLastPeriodEnds() {
		return lastPeriodEnds;
	}

	public void setLastPeriodEnds(DateTime lastPeriodEnds) {
		this.lastPeriodEnds = lastPeriodEnds;
	}

	public ProgramProfitChart tradingVolume(Double tradingVolume) {
		this.tradingVolume = tradingVolume;
		return this;
	}

	/**
	 * Get tradingVolume
	 *
	 * @return tradingVolume
	 **/
	@ApiModelProperty(value = "")
	public Double getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(Double tradingVolume) {
		this.tradingVolume = tradingVolume;
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

	public ProgramProfitChart balance(Double balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 **/
	@ApiModelProperty(value = "")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ProgramProfitChart investors(Integer investors) {
		this.investors = investors;
		return this;
	}

	/**
	 * Get investors
	 *
	 * @return investors
	 **/
	@ApiModelProperty(value = "")
	public Integer getInvestors() {
		return investors;
	}

	public void setInvestors(Integer investors) {
		this.investors = investors;
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

	public ProgramProfitChart rate(Double rate) {
		this.rate = rate;
		return this;
	}

	/**
	 * Get rate
	 *
	 * @return rate
	 **/
	@ApiModelProperty(value = "")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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
		return Objects.equals(this.equityChart, programProfitChart.equityChart) &&
				Objects.equals(this.totalProgramCurrencyProfit, programProfitChart.totalProgramCurrencyProfit) &&
				Objects.equals(this.timeframeProgramCurrencyProfit, programProfitChart.timeframeProgramCurrencyProfit) &&
				Objects.equals(this.programCurrency, programProfitChart.programCurrency) &&
				Objects.equals(this.trades, programProfitChart.trades) &&
				Objects.equals(this.successTradesPercent, programProfitChart.successTradesPercent) &&
				Objects.equals(this.profitFactor, programProfitChart.profitFactor) &&
				Objects.equals(this.pnLChart, programProfitChart.pnLChart) &&
				Objects.equals(this.periods, programProfitChart.periods) &&
				Objects.equals(this.lastPeriodStarts, programProfitChart.lastPeriodStarts) &&
				Objects.equals(this.lastPeriodEnds, programProfitChart.lastPeriodEnds) &&
				Objects.equals(this.tradingVolume, programProfitChart.tradingVolume) &&
				Objects.equals(this.totalGvtProfit, programProfitChart.totalGvtProfit) &&
				Objects.equals(this.timeframeGvtProfit, programProfitChart.timeframeGvtProfit) &&
				Objects.equals(this.balance, programProfitChart.balance) &&
				Objects.equals(this.investors, programProfitChart.investors) &&
				Objects.equals(this.profitChangePercent, programProfitChart.profitChangePercent) &&
				Objects.equals(this.sharpeRatio, programProfitChart.sharpeRatio) &&
				Objects.equals(this.sortinoRatio, programProfitChart.sortinoRatio) &&
				Objects.equals(this.calmarRatio, programProfitChart.calmarRatio) &&
				Objects.equals(this.maxDrawdown, programProfitChart.maxDrawdown) &&
				Objects.equals(this.rate, programProfitChart.rate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(equityChart, totalProgramCurrencyProfit, timeframeProgramCurrencyProfit, programCurrency, trades, successTradesPercent, profitFactor, pnLChart, periods, lastPeriodStarts, lastPeriodEnds, tradingVolume, totalGvtProfit, timeframeGvtProfit, balance, investors, profitChangePercent, sharpeRatio, sortinoRatio, calmarRatio, maxDrawdown, rate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramProfitChart {\n");

		sb.append("    equityChart: ").append(toIndentedString(equityChart)).append("\n");
		sb.append("    totalProgramCurrencyProfit: ").append(toIndentedString(totalProgramCurrencyProfit)).append("\n");
		sb.append("    timeframeProgramCurrencyProfit: ").append(toIndentedString(timeframeProgramCurrencyProfit)).append("\n");
		sb.append("    programCurrency: ").append(toIndentedString(programCurrency)).append("\n");
		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
		sb.append("    successTradesPercent: ").append(toIndentedString(successTradesPercent)).append("\n");
		sb.append("    profitFactor: ").append(toIndentedString(profitFactor)).append("\n");
		sb.append("    pnLChart: ").append(toIndentedString(pnLChart)).append("\n");
		sb.append("    periods: ").append(toIndentedString(periods)).append("\n");
		sb.append("    lastPeriodStarts: ").append(toIndentedString(lastPeriodStarts)).append("\n");
		sb.append("    lastPeriodEnds: ").append(toIndentedString(lastPeriodEnds)).append("\n");
		sb.append("    tradingVolume: ").append(toIndentedString(tradingVolume)).append("\n");
		sb.append("    totalGvtProfit: ").append(toIndentedString(totalGvtProfit)).append("\n");
		sb.append("    timeframeGvtProfit: ").append(toIndentedString(timeframeGvtProfit)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    investors: ").append(toIndentedString(investors)).append("\n");
		sb.append("    profitChangePercent: ").append(toIndentedString(profitChangePercent)).append("\n");
		sb.append("    sharpeRatio: ").append(toIndentedString(sharpeRatio)).append("\n");
		sb.append("    sortinoRatio: ").append(toIndentedString(sortinoRatio)).append("\n");
		sb.append("    calmarRatio: ").append(toIndentedString(calmarRatio)).append("\n");
		sb.append("    maxDrawdown: ").append(toIndentedString(maxDrawdown)).append("\n");
		sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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
		UNDEFINED("Undefined"),

		GVT("GVT"),

		ETH("ETH"),

		BTC("BTC"),

		ADA("ADA"),

		USDT("USDT"),

		XRP("XRP"),

		BCH("BCH"),

		LTC("LTC"),

		DOGE("DOGE"),

		BNB("BNB"),

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

