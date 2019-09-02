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
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * SignalDetails
 */

public class SignalDetails
{
	@SerializedName("personalDetails")
	private PersonalSignalDetailsFull personalDetails = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("level")
	private Integer level = null;

	@SerializedName("levelProgress")
	private Double levelProgress = null;

	@SerializedName("tags")
	private List<ProgramTag> tags = null;

	@SerializedName("subscribers")
	private Integer subscribers = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("manager")
	private ProfilePublic manager = null;

	@SerializedName("chart")
	private List<ChartSimple> chart = null;

	public SignalDetails personalDetails(PersonalSignalDetailsFull personalDetails) {
		this.personalDetails = personalDetails;
		return this;
	}

	/**
	 * Get personalDetails
	 *
	 * @return personalDetails
	 **/
	@ApiModelProperty(value = "")
	public PersonalSignalDetailsFull getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalSignalDetailsFull personalDetails) {
		this.personalDetails = personalDetails;
	}

	public SignalDetails currency(CurrencyEnum currency) {
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

	public SignalDetails level(Integer level) {
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

	public SignalDetails levelProgress(Double levelProgress) {
		this.levelProgress = levelProgress;
		return this;
	}

	/**
	 * Get levelProgress
	 *
	 * @return levelProgress
	 **/
	@ApiModelProperty(value = "")
	public Double getLevelProgress() {
		return levelProgress;
	}

	public void setLevelProgress(Double levelProgress) {
		this.levelProgress = levelProgress;
	}

	public SignalDetails tags(List<ProgramTag> tags) {
		this.tags = tags;
		return this;
	}

	public SignalDetails addTagsItem(ProgramTag tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<ProgramTag>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	/**
	 * Get tags
	 *
	 * @return tags
	 **/
	@ApiModelProperty(value = "")
	public List<ProgramTag> getTags() {
		return tags;
	}

	public void setTags(List<ProgramTag> tags) {
		this.tags = tags;
	}

	public SignalDetails subscribers(Integer subscribers) {
		this.subscribers = subscribers;
		return this;
	}

	/**
	 * Get subscribers
	 *
	 * @return subscribers
	 **/
	@ApiModelProperty(value = "")
	public Integer getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Integer subscribers) {
		this.subscribers = subscribers;
	}

	public SignalDetails id(UUID id) {
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

	public SignalDetails logo(String logo) {
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

	public SignalDetails url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * Get url
	 *
	 * @return url
	 **/
	@ApiModelProperty(value = "")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SignalDetails color(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Get color
	 *
	 * @return color
	 **/
	@ApiModelProperty(value = "")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public SignalDetails title(String title) {
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

	public SignalDetails description(String description) {
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

	public SignalDetails status(StatusEnum status) {
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

	public SignalDetails creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@ApiModelProperty(value = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public SignalDetails manager(ProfilePublic manager) {
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

	public SignalDetails chart(List<ChartSimple> chart) {
		this.chart = chart;
		return this;
	}

	public SignalDetails addChartItem(ChartSimple chartItem) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SignalDetails signalDetails = (SignalDetails) o;
		return Objects.equals(this.personalDetails, signalDetails.personalDetails) &&
				Objects.equals(this.currency, signalDetails.currency) &&
				Objects.equals(this.level, signalDetails.level) &&
				Objects.equals(this.levelProgress, signalDetails.levelProgress) &&
				Objects.equals(this.tags, signalDetails.tags) &&
				Objects.equals(this.subscribers, signalDetails.subscribers) &&
				Objects.equals(this.id, signalDetails.id) &&
				Objects.equals(this.logo, signalDetails.logo) &&
				Objects.equals(this.url, signalDetails.url) &&
				Objects.equals(this.color, signalDetails.color) &&
				Objects.equals(this.title, signalDetails.title) &&
				Objects.equals(this.description, signalDetails.description) &&
				Objects.equals(this.status, signalDetails.status) &&
				Objects.equals(this.creationDate, signalDetails.creationDate) &&
				Objects.equals(this.manager, signalDetails.manager) &&
				Objects.equals(this.chart, signalDetails.chart);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personalDetails, currency, level, levelProgress, tags, subscribers, id, logo, url, color, title, description, status, creationDate, manager, chart);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SignalDetails {\n");

		sb.append("    personalDetails: ").append(toIndentedString(personalDetails)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    level: ").append(toIndentedString(level)).append("\n");
		sb.append("    levelProgress: ").append(toIndentedString(levelProgress)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    subscribers: ").append(toIndentedString(subscribers)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
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

		USDT("USDT"),

		XRP("XRP"),

		BCH("BCH"),

		LTC("LTC"),

		DOGE("DOGE"),

		BNB("BNB"),

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

