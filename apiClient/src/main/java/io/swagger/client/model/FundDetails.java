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
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * FundDetails
 */

public class FundDetails
{
	@SerializedName("totalAssetsCount")
	private Integer totalAssetsCount = null;

	@SerializedName("topFundAssets")
	private List<FundAssetPercent> topFundAssets = null;

	@SerializedName("statistic")
	private FundDetailsListStatistic statistic = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("manager")
	private ProfilePublic manager = null;

	@SerializedName("chart")
	private List<ChartSimple> chart = null;

	@SerializedName("personalProgramDetails")
	private PersonalProgramDetailsList personalProgramDetails = null;

	@SerializedName("dashboardProgramDetails")
	private DashboardProgramDetails dashboardProgramDetails = null;

	public FundDetails totalAssetsCount(Integer totalAssetsCount) {
		this.totalAssetsCount = totalAssetsCount;
		return this;
	}

	/**
	 * Get totalAssetsCount
	 *
	 * @return totalAssetsCount
	 **/
	@ApiModelProperty(value = "")
	public Integer getTotalAssetsCount() {
		return totalAssetsCount;
	}

	public void setTotalAssetsCount(Integer totalAssetsCount) {
		this.totalAssetsCount = totalAssetsCount;
	}

	public FundDetails topFundAssets(List<FundAssetPercent> topFundAssets) {
		this.topFundAssets = topFundAssets;
		return this;
	}

	public FundDetails addTopFundAssetsItem(FundAssetPercent topFundAssetsItem) {
		if (this.topFundAssets == null) {
			this.topFundAssets = new ArrayList<FundAssetPercent>();
		}
		this.topFundAssets.add(topFundAssetsItem);
		return this;
	}

	/**
	 * Get topFundAssets
	 *
	 * @return topFundAssets
	 **/
	@ApiModelProperty(value = "")
	public List<FundAssetPercent> getTopFundAssets() {
		return topFundAssets;
	}

	public void setTopFundAssets(List<FundAssetPercent> topFundAssets) {
		this.topFundAssets = topFundAssets;
	}

	public FundDetails statistic(FundDetailsListStatistic statistic) {
		this.statistic = statistic;
		return this;
	}

	/**
	 * Get statistic
	 *
	 * @return statistic
	 **/
	@ApiModelProperty(value = "")
	public FundDetailsListStatistic getStatistic() {
		return statistic;
	}

	public void setStatistic(FundDetailsListStatistic statistic) {
		this.statistic = statistic;
	}

	public FundDetails id(UUID id) {
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

	public FundDetails logo(String logo) {
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

	public FundDetails title(String title) {
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

	public FundDetails description(String description) {
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

	public FundDetails status(StatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@ApiModelProperty(value = "")
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public FundDetails manager(ProfilePublic manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * Get manager
	 *
	 * @return manager
	 **/
	@ApiModelProperty(value = "")
	public ProfilePublic getManager() {
		return manager;
	}

	public void setManager(ProfilePublic manager) {
		this.manager = manager;
	}

	public FundDetails chart(List<ChartSimple> chart) {
		this.chart = chart;
		return this;
	}

	public FundDetails addChartItem(ChartSimple chartItem) {
		if (this.chart == null) {
			this.chart = new ArrayList<ChartSimple>();
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
	public List<ChartSimple> getChart() {
		return chart;
	}

	public void setChart(List<ChartSimple> chart) {
		this.chart = chart;
	}

	public FundDetails personalProgramDetails(PersonalProgramDetailsList personalProgramDetails) {
		this.personalProgramDetails = personalProgramDetails;
		return this;
	}

	/**
	 * Fields for authorized user
	 *
	 * @return personalProgramDetails
	 **/
	@ApiModelProperty(value = "Fields for authorized user")
	public PersonalProgramDetailsList getPersonalProgramDetails() {
		return personalProgramDetails;
	}

	public void setPersonalProgramDetails(PersonalProgramDetailsList personalProgramDetails) {
		this.personalProgramDetails = personalProgramDetails;
	}

	public FundDetails dashboardProgramDetails(DashboardProgramDetails dashboardProgramDetails) {
		this.dashboardProgramDetails = dashboardProgramDetails;
		return this;
	}

	/**
	 * Fields for dashboard
	 *
	 * @return dashboardProgramDetails
	 **/
	@ApiModelProperty(value = "Fields for dashboard")
	public DashboardProgramDetails getDashboardProgramDetails() {
		return dashboardProgramDetails;
	}

	public void setDashboardProgramDetails(DashboardProgramDetails dashboardProgramDetails) {
		this.dashboardProgramDetails = dashboardProgramDetails;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundDetails fundDetails = (FundDetails) o;
		return Objects.equals(this.totalAssetsCount, fundDetails.totalAssetsCount) &&
				Objects.equals(this.topFundAssets, fundDetails.topFundAssets) &&
				Objects.equals(this.statistic, fundDetails.statistic) &&
				Objects.equals(this.id, fundDetails.id) &&
				Objects.equals(this.logo, fundDetails.logo) &&
				Objects.equals(this.title, fundDetails.title) &&
				Objects.equals(this.description, fundDetails.description) &&
				Objects.equals(this.status, fundDetails.status) &&
				Objects.equals(this.manager, fundDetails.manager) &&
				Objects.equals(this.chart, fundDetails.chart) &&
				Objects.equals(this.personalProgramDetails, fundDetails.personalProgramDetails) &&
				Objects.equals(this.dashboardProgramDetails, fundDetails.dashboardProgramDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalAssetsCount, topFundAssets, statistic, id, logo, title, description, status, manager, chart, personalProgramDetails, dashboardProgramDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundDetails {\n");

		sb.append("    totalAssetsCount: ").append(toIndentedString(totalAssetsCount)).append("\n");
		sb.append("    topFundAssets: ").append(toIndentedString(topFundAssets)).append("\n");
		sb.append("    statistic: ").append(toIndentedString(statistic)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
		sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
		sb.append("    personalProgramDetails: ").append(toIndentedString(personalProgramDetails)).append("\n");
		sb.append("    dashboardProgramDetails: ").append(toIndentedString(dashboardProgramDetails)).append("\n");
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
	 * Gets or Sets status
	 */
	@JsonAdapter(StatusEnum.Adapter.class)
	public enum StatusEnum
	{
		NONE("None"),

		PENDING("Pending"),

		ERRORCREATING("ErrorCreating"),

		ACTIVE("Active"),

		CLOSED("Closed"),

		ARCHIVED("Archived"),

		CLOSEDDUETOINACTIVITY("ClosedDueToInactivity");

		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<StatusEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public StatusEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return StatusEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

