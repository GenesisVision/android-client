/*
 * Core API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
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
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * InvestmentProgramDashboardInvestor
 */

public class InvestmentProgramDashboardInvestor
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("level")
	private Integer level = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("investedAmount")
	private Double investedAmount = null;

	@SerializedName("profitFromProgram")
	private Double profitFromProgram = null;

	@SerializedName("investedTokens")
	private Double investedTokens = null;

	@SerializedName("tradesCount")
	private Integer tradesCount = null;

	@SerializedName("investorsCount")
	private Integer investorsCount = null;

	@SerializedName("periodDuration")
	private Integer periodDuration = null;

	@SerializedName("startOfPeriod")
	private DateTime startOfPeriod = null;

	@SerializedName("endOfPeriod")
	private DateTime endOfPeriod = null;

	@SerializedName("profitAvg")
	private Double profitAvg = null;

	@SerializedName("profitTotal")
	private Double profitTotal = null;

	@SerializedName("profitAvgPercent")
	private Double profitAvgPercent = null;

	@SerializedName("profitTotalPercent")
	private Double profitTotalPercent = null;

	@SerializedName("profitTotalChange")
	private ProfitTotalChangeEnum profitTotalChange = null;

	@SerializedName("availableInvestment")
	private Double availableInvestment = null;

	@SerializedName("feeSuccess")
	private Double feeSuccess = null;

	@SerializedName("feeManagement")
	private Double feeManagement = null;

	@SerializedName("isEnabled")
	private Boolean isEnabled = null;

	@SerializedName("isArchived")
	private Boolean isArchived = null;

	@SerializedName("chart")
	private List<Chart> chart = null;

	@SerializedName("equityChart")
	private List<ChartByDate> equityChart = null;

	@SerializedName("freeTokens")
	private FreeTokens freeTokens = null;

	@SerializedName("manager")
	private ProfilePublicViewModel manager = null;

	@SerializedName("token")
	private Token token = null;

	@SerializedName("hasNewRequests")
	private Boolean hasNewRequests = null;

	@SerializedName("isHistoryEnable")
	private Boolean isHistoryEnable = null;

	@SerializedName("isInvestEnable")
	private Boolean isInvestEnable = null;

	@SerializedName("isWithdrawEnable")
	private Boolean isWithdrawEnable = null;

	@SerializedName("isOwnProgram")
	private Boolean isOwnProgram = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	public InvestmentProgramDashboardInvestor id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public InvestmentProgramDashboardInvestor title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@ApiModelProperty(value = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public InvestmentProgramDashboardInvestor description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@ApiModelProperty(value = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InvestmentProgramDashboardInvestor level(Integer level) {
		this.level = level;
		return this;
	}

	/**
	 * Get level
	 *
	 * @return level
	 **/
	@ApiModelProperty(value = "")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public InvestmentProgramDashboardInvestor logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@ApiModelProperty(value = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public InvestmentProgramDashboardInvestor balance(Double balance) {
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

	public InvestmentProgramDashboardInvestor currency(CurrencyEnum currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@ApiModelProperty(value = "")
	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public InvestmentProgramDashboardInvestor investedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
		return this;
	}

	/**
	 * Get investedAmount
	 *
	 * @return investedAmount
	 **/
	@ApiModelProperty(value = "")
	public Double getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public InvestmentProgramDashboardInvestor profitFromProgram(Double profitFromProgram) {
		this.profitFromProgram = profitFromProgram;
		return this;
	}

	/**
	 * Get profitFromProgram
	 *
	 * @return profitFromProgram
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitFromProgram() {
		return profitFromProgram;
	}

	public void setProfitFromProgram(Double profitFromProgram) {
		this.profitFromProgram = profitFromProgram;
	}

	public InvestmentProgramDashboardInvestor investedTokens(Double investedTokens) {
		this.investedTokens = investedTokens;
		return this;
	}

	/**
	 * Get investedTokens
	 *
	 * @return investedTokens
	 **/
	@ApiModelProperty(value = "")
	public Double getInvestedTokens() {
		return investedTokens;
	}

	public void setInvestedTokens(Double investedTokens) {
		this.investedTokens = investedTokens;
	}

	public InvestmentProgramDashboardInvestor tradesCount(Integer tradesCount) {
		this.tradesCount = tradesCount;
		return this;
	}

	/**
	 * Get tradesCount
	 *
	 * @return tradesCount
	 **/
	@ApiModelProperty(value = "")
	public Integer getTradesCount() {
		return tradesCount;
	}

	public void setTradesCount(Integer tradesCount) {
		this.tradesCount = tradesCount;
	}

	public InvestmentProgramDashboardInvestor investorsCount(Integer investorsCount) {
		this.investorsCount = investorsCount;
		return this;
	}

	/**
	 * Get investorsCount
	 *
	 * @return investorsCount
	 **/
	@ApiModelProperty(value = "")
	public Integer getInvestorsCount() {
		return investorsCount;
	}

	public void setInvestorsCount(Integer investorsCount) {
		this.investorsCount = investorsCount;
	}

	public InvestmentProgramDashboardInvestor periodDuration(Integer periodDuration) {
		this.periodDuration = periodDuration;
		return this;
	}

	/**
	 * Get periodDuration
	 *
	 * @return periodDuration
	 **/
	@ApiModelProperty(value = "")
	public Integer getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(Integer periodDuration) {
		this.periodDuration = periodDuration;
	}

	public InvestmentProgramDashboardInvestor startOfPeriod(DateTime startOfPeriod) {
		this.startOfPeriod = startOfPeriod;
		return this;
	}

	/**
	 * Get startOfPeriod
	 *
	 * @return startOfPeriod
	 **/
	@ApiModelProperty(value = "")
	public DateTime getStartOfPeriod() {
		return startOfPeriod;
	}

	public void setStartOfPeriod(DateTime startOfPeriod) {
		this.startOfPeriod = startOfPeriod;
	}

	public InvestmentProgramDashboardInvestor endOfPeriod(DateTime endOfPeriod) {
		this.endOfPeriod = endOfPeriod;
		return this;
	}

	/**
	 * Get endOfPeriod
	 *
	 * @return endOfPeriod
	 **/
	@ApiModelProperty(value = "")
	public DateTime getEndOfPeriod() {
		return endOfPeriod;
	}

	public void setEndOfPeriod(DateTime endOfPeriod) {
		this.endOfPeriod = endOfPeriod;
	}

	public InvestmentProgramDashboardInvestor profitAvg(Double profitAvg) {
		this.profitAvg = profitAvg;
		return this;
	}

	/**
	 * Get profitAvg
	 *
	 * @return profitAvg
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitAvg() {
		return profitAvg;
	}

	public void setProfitAvg(Double profitAvg) {
		this.profitAvg = profitAvg;
	}

	public InvestmentProgramDashboardInvestor profitTotal(Double profitTotal) {
		this.profitTotal = profitTotal;
		return this;
	}

	/**
	 * Get profitTotal
	 *
	 * @return profitTotal
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitTotal() {
		return profitTotal;
	}

	public void setProfitTotal(Double profitTotal) {
		this.profitTotal = profitTotal;
	}

	public InvestmentProgramDashboardInvestor profitAvgPercent(Double profitAvgPercent) {
		this.profitAvgPercent = profitAvgPercent;
		return this;
	}

	/**
	 * Get profitAvgPercent
	 *
	 * @return profitAvgPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitAvgPercent() {
		return profitAvgPercent;
	}

	public void setProfitAvgPercent(Double profitAvgPercent) {
		this.profitAvgPercent = profitAvgPercent;
	}

	public InvestmentProgramDashboardInvestor profitTotalPercent(Double profitTotalPercent) {
		this.profitTotalPercent = profitTotalPercent;
		return this;
	}

	/**
	 * Get profitTotalPercent
	 *
	 * @return profitTotalPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitTotalPercent() {
		return profitTotalPercent;
	}

	public void setProfitTotalPercent(Double profitTotalPercent) {
		this.profitTotalPercent = profitTotalPercent;
	}

	public InvestmentProgramDashboardInvestor profitTotalChange(ProfitTotalChangeEnum profitTotalChange) {
		this.profitTotalChange = profitTotalChange;
		return this;
	}

	/**
	 * Get profitTotalChange
	 *
	 * @return profitTotalChange
	 **/
	@ApiModelProperty(value = "")
	public ProfitTotalChangeEnum getProfitTotalChange() {
		return profitTotalChange;
	}

	public void setProfitTotalChange(ProfitTotalChangeEnum profitTotalChange) {
		this.profitTotalChange = profitTotalChange;
	}

	public InvestmentProgramDashboardInvestor availableInvestment(Double availableInvestment) {
		this.availableInvestment = availableInvestment;
		return this;
	}

	/**
	 * Get availableInvestment
	 *
	 * @return availableInvestment
	 **/
	@ApiModelProperty(value = "")
	public Double getAvailableInvestment() {
		return availableInvestment;
	}

	public void setAvailableInvestment(Double availableInvestment) {
		this.availableInvestment = availableInvestment;
	}

	public InvestmentProgramDashboardInvestor feeSuccess(Double feeSuccess) {
		this.feeSuccess = feeSuccess;
		return this;
	}

	/**
	 * Get feeSuccess
	 *
	 * @return feeSuccess
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeSuccess() {
		return feeSuccess;
	}

	public void setFeeSuccess(Double feeSuccess) {
		this.feeSuccess = feeSuccess;
	}

	public InvestmentProgramDashboardInvestor feeManagement(Double feeManagement) {
		this.feeManagement = feeManagement;
		return this;
	}

	/**
	 * Get feeManagement
	 *
	 * @return feeManagement
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeManagement() {
		return feeManagement;
	}

	public void setFeeManagement(Double feeManagement) {
		this.feeManagement = feeManagement;
	}

	public InvestmentProgramDashboardInvestor isEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

	/**
	 * Get isEnabled
	 *
	 * @return isEnabled
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public InvestmentProgramDashboardInvestor isArchived(Boolean isArchived) {
		this.isArchived = isArchived;
		return this;
	}

	/**
	 * Get isArchived
	 *
	 * @return isArchived
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public InvestmentProgramDashboardInvestor chart(List<Chart> chart) {
		this.chart = chart;
		return this;
	}

	public InvestmentProgramDashboardInvestor addChartItem(Chart chartItem) {
		if (this.chart == null) {
			this.chart = new ArrayList<Chart>();
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
	public List<Chart> getChart() {
		return chart;
	}

	public void setChart(List<Chart> chart) {
		this.chart = chart;
	}

	public InvestmentProgramDashboardInvestor equityChart(List<ChartByDate> equityChart) {
		this.equityChart = equityChart;
		return this;
	}

	public InvestmentProgramDashboardInvestor addEquityChartItem(ChartByDate equityChartItem) {
		if (this.equityChart == null) {
			this.equityChart = new ArrayList<ChartByDate>();
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
	public List<ChartByDate> getEquityChart() {
		return equityChart;
	}

	public void setEquityChart(List<ChartByDate> equityChart) {
		this.equityChart = equityChart;
	}

	public InvestmentProgramDashboardInvestor freeTokens(FreeTokens freeTokens) {
		this.freeTokens = freeTokens;
		return this;
	}

	/**
	 * Get freeTokens
	 *
	 * @return freeTokens
	 **/
	@ApiModelProperty(value = "")
	public FreeTokens getFreeTokens() {
		return freeTokens;
	}

	public void setFreeTokens(FreeTokens freeTokens) {
		this.freeTokens = freeTokens;
	}

	public InvestmentProgramDashboardInvestor manager(ProfilePublicViewModel manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * Get manager
	 *
	 * @return manager
	 **/
	@ApiModelProperty(value = "")
	public ProfilePublicViewModel getManager() {
		return manager;
	}

	public void setManager(ProfilePublicViewModel manager) {
		this.manager = manager;
	}

	public InvestmentProgramDashboardInvestor token(Token token) {
		this.token = token;
		return this;
	}

	/**
	 * Get token
	 *
	 * @return token
	 **/
	@ApiModelProperty(value = "")
	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public InvestmentProgramDashboardInvestor hasNewRequests(Boolean hasNewRequests) {
		this.hasNewRequests = hasNewRequests;
		return this;
	}

	/**
	 * Get hasNewRequests
	 *
	 * @return hasNewRequests
	 **/
	@ApiModelProperty(value = "")
	public Boolean isHasNewRequests() {
		return hasNewRequests;
	}

	public void setHasNewRequests(Boolean hasNewRequests) {
		this.hasNewRequests = hasNewRequests;
	}

	public InvestmentProgramDashboardInvestor isHistoryEnable(Boolean isHistoryEnable) {
		this.isHistoryEnable = isHistoryEnable;
		return this;
	}

	/**
	 * Get isHistoryEnable
	 *
	 * @return isHistoryEnable
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsHistoryEnable() {
		return isHistoryEnable;
	}

	public void setIsHistoryEnable(Boolean isHistoryEnable) {
		this.isHistoryEnable = isHistoryEnable;
	}

	public InvestmentProgramDashboardInvestor isInvestEnable(Boolean isInvestEnable) {
		this.isInvestEnable = isInvestEnable;
		return this;
	}

	/**
	 * Get isInvestEnable
	 *
	 * @return isInvestEnable
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsInvestEnable() {
		return isInvestEnable;
	}

	public void setIsInvestEnable(Boolean isInvestEnable) {
		this.isInvestEnable = isInvestEnable;
	}

	public InvestmentProgramDashboardInvestor isWithdrawEnable(Boolean isWithdrawEnable) {
		this.isWithdrawEnable = isWithdrawEnable;
		return this;
	}

	/**
	 * Get isWithdrawEnable
	 *
	 * @return isWithdrawEnable
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsWithdrawEnable() {
		return isWithdrawEnable;
	}

	public void setIsWithdrawEnable(Boolean isWithdrawEnable) {
		this.isWithdrawEnable = isWithdrawEnable;
	}

	public InvestmentProgramDashboardInvestor isOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
		return this;
	}

	/**
	 * Get isOwnProgram
	 *
	 * @return isOwnProgram
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsOwnProgram() {
		return isOwnProgram;
	}

	public void setIsOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
	}

	public InvestmentProgramDashboardInvestor isFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
		return this;
	}

	/**
	 * Get isFavorite
	 *
	 * @return isFavorite
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InvestmentProgramDashboardInvestor investmentProgramDashboardInvestor = (InvestmentProgramDashboardInvestor) o;
		return Objects.equals(this.id, investmentProgramDashboardInvestor.id) &&
				Objects.equals(this.title, investmentProgramDashboardInvestor.title) &&
				Objects.equals(this.description, investmentProgramDashboardInvestor.description) &&
				Objects.equals(this.level, investmentProgramDashboardInvestor.level) &&
				Objects.equals(this.logo, investmentProgramDashboardInvestor.logo) &&
				Objects.equals(this.balance, investmentProgramDashboardInvestor.balance) &&
				Objects.equals(this.currency, investmentProgramDashboardInvestor.currency) &&
				Objects.equals(this.investedAmount, investmentProgramDashboardInvestor.investedAmount) &&
				Objects.equals(this.profitFromProgram, investmentProgramDashboardInvestor.profitFromProgram) &&
				Objects.equals(this.investedTokens, investmentProgramDashboardInvestor.investedTokens) &&
				Objects.equals(this.tradesCount, investmentProgramDashboardInvestor.tradesCount) &&
				Objects.equals(this.investorsCount, investmentProgramDashboardInvestor.investorsCount) &&
				Objects.equals(this.periodDuration, investmentProgramDashboardInvestor.periodDuration) &&
				Objects.equals(this.startOfPeriod, investmentProgramDashboardInvestor.startOfPeriod) &&
				Objects.equals(this.endOfPeriod, investmentProgramDashboardInvestor.endOfPeriod) &&
				Objects.equals(this.profitAvg, investmentProgramDashboardInvestor.profitAvg) &&
				Objects.equals(this.profitTotal, investmentProgramDashboardInvestor.profitTotal) &&
				Objects.equals(this.profitAvgPercent, investmentProgramDashboardInvestor.profitAvgPercent) &&
				Objects.equals(this.profitTotalPercent, investmentProgramDashboardInvestor.profitTotalPercent) &&
				Objects.equals(this.profitTotalChange, investmentProgramDashboardInvestor.profitTotalChange) &&
				Objects.equals(this.availableInvestment, investmentProgramDashboardInvestor.availableInvestment) &&
				Objects.equals(this.feeSuccess, investmentProgramDashboardInvestor.feeSuccess) &&
				Objects.equals(this.feeManagement, investmentProgramDashboardInvestor.feeManagement) &&
				Objects.equals(this.isEnabled, investmentProgramDashboardInvestor.isEnabled) &&
				Objects.equals(this.isArchived, investmentProgramDashboardInvestor.isArchived) &&
				Objects.equals(this.chart, investmentProgramDashboardInvestor.chart) &&
				Objects.equals(this.equityChart, investmentProgramDashboardInvestor.equityChart) &&
				Objects.equals(this.freeTokens, investmentProgramDashboardInvestor.freeTokens) &&
				Objects.equals(this.manager, investmentProgramDashboardInvestor.manager) &&
				Objects.equals(this.token, investmentProgramDashboardInvestor.token) &&
				Objects.equals(this.hasNewRequests, investmentProgramDashboardInvestor.hasNewRequests) &&
				Objects.equals(this.isHistoryEnable, investmentProgramDashboardInvestor.isHistoryEnable) &&
				Objects.equals(this.isInvestEnable, investmentProgramDashboardInvestor.isInvestEnable) &&
				Objects.equals(this.isWithdrawEnable, investmentProgramDashboardInvestor.isWithdrawEnable) &&
				Objects.equals(this.isOwnProgram, investmentProgramDashboardInvestor.isOwnProgram) &&
				Objects.equals(this.isFavorite, investmentProgramDashboardInvestor.isFavorite);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, level, logo, balance, currency, investedAmount, profitFromProgram, investedTokens, tradesCount, investorsCount, periodDuration, startOfPeriod, endOfPeriod, profitAvg, profitTotal, profitAvgPercent, profitTotalPercent, profitTotalChange, availableInvestment, feeSuccess, feeManagement, isEnabled, isArchived, chart, equityChart, freeTokens, manager, token, hasNewRequests, isHistoryEnable, isInvestEnable, isWithdrawEnable, isOwnProgram, isFavorite);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InvestmentProgramDashboardInvestor {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    level: ").append(toIndentedString(level)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    investedAmount: ").append(toIndentedString(investedAmount)).append("\n");
		sb.append("    profitFromProgram: ").append(toIndentedString(profitFromProgram)).append("\n");
		sb.append("    investedTokens: ").append(toIndentedString(investedTokens)).append("\n");
		sb.append("    tradesCount: ").append(toIndentedString(tradesCount)).append("\n");
		sb.append("    investorsCount: ").append(toIndentedString(investorsCount)).append("\n");
		sb.append("    periodDuration: ").append(toIndentedString(periodDuration)).append("\n");
		sb.append("    startOfPeriod: ").append(toIndentedString(startOfPeriod)).append("\n");
		sb.append("    endOfPeriod: ").append(toIndentedString(endOfPeriod)).append("\n");
		sb.append("    profitAvg: ").append(toIndentedString(profitAvg)).append("\n");
		sb.append("    profitTotal: ").append(toIndentedString(profitTotal)).append("\n");
		sb.append("    profitAvgPercent: ").append(toIndentedString(profitAvgPercent)).append("\n");
		sb.append("    profitTotalPercent: ").append(toIndentedString(profitTotalPercent)).append("\n");
		sb.append("    profitTotalChange: ").append(toIndentedString(profitTotalChange)).append("\n");
		sb.append("    availableInvestment: ").append(toIndentedString(availableInvestment)).append("\n");
		sb.append("    feeSuccess: ").append(toIndentedString(feeSuccess)).append("\n");
		sb.append("    feeManagement: ").append(toIndentedString(feeManagement)).append("\n");
		sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
		sb.append("    isArchived: ").append(toIndentedString(isArchived)).append("\n");
		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
		sb.append("    equityChart: ").append(toIndentedString(equityChart)).append("\n");
		sb.append("    freeTokens: ").append(toIndentedString(freeTokens)).append("\n");
		sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
		sb.append("    token: ").append(toIndentedString(token)).append("\n");
		sb.append("    hasNewRequests: ").append(toIndentedString(hasNewRequests)).append("\n");
		sb.append("    isHistoryEnable: ").append(toIndentedString(isHistoryEnable)).append("\n");
		sb.append("    isInvestEnable: ").append(toIndentedString(isInvestEnable)).append("\n");
		sb.append("    isWithdrawEnable: ").append(toIndentedString(isWithdrawEnable)).append("\n");
		sb.append("    isOwnProgram: ").append(toIndentedString(isOwnProgram)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
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
	 * Gets or Sets currency
	 */
	@JsonAdapter(CurrencyEnum.Adapter.class)
	public enum CurrencyEnum
	{
		UNDEFINED("Undefined"),

		GVT("GVT"),

		ETH("ETH"),

		BTC("BTC"),

		ADA("ADA"),

		USD("USD"),

		EUR("EUR");

		public static CurrencyEnum fromValue(String text) {
			for (CurrencyEnum b : CurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		CurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<CurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final CurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public CurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return CurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets profitTotalChange
	 */
	@JsonAdapter(ProfitTotalChangeEnum.Adapter.class)
	public enum ProfitTotalChangeEnum
	{
		UNCHANGED("Unchanged"),

		UP("Up"),

		DOWN("Down");

		public static ProfitTotalChangeEnum fromValue(String text) {
			for (ProfitTotalChangeEnum b : ProfitTotalChangeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		ProfitTotalChangeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<ProfitTotalChangeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final ProfitTotalChangeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public ProfitTotalChangeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return ProfitTotalChangeEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

