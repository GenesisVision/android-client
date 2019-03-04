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
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * ManagerPortfolioEvent
 */

public class ManagerPortfolioEvent
{
	@SerializedName("assetId")
	private UUID assetId = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("feeValue")
	private Double feeValue = null;

	@SerializedName("profitPercent")
	private Double profitPercent = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("programType")
	private ProgramTypeEnum programType = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("periodNumber")
	private Integer periodNumber = null;

	public ManagerPortfolioEvent assetId(UUID assetId) {
		this.assetId = assetId;
		return this;
	}

	/**
	 * Get assetId
	 *
	 * @return assetId
	 **/
	@ApiModelProperty(value = "")
	public UUID getAssetId() {
		return assetId;
	}

	public void setAssetId(UUID assetId) {
		this.assetId = assetId;
	}

	public ManagerPortfolioEvent date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@ApiModelProperty(value = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public ManagerPortfolioEvent title(String title) {
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

	public ManagerPortfolioEvent value(Double value) {
		this.value = value;
		return this;
	}

	/**
	 * Get value
	 *
	 * @return value
	 **/
	@ApiModelProperty(value = "")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public ManagerPortfolioEvent feeValue(Double feeValue) {
		this.feeValue = feeValue;
		return this;
	}

	/**
	 * Get feeValue
	 *
	 * @return feeValue
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(Double feeValue) {
		this.feeValue = feeValue;
	}

	public ManagerPortfolioEvent profitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
		return this;
	}

	/**
	 * Get profitPercent
	 *
	 * @return profitPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}

	public ManagerPortfolioEvent currency(CurrencyEnum currency) {
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

	public ManagerPortfolioEvent type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@ApiModelProperty(value = "")
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public ManagerPortfolioEvent programType(ProgramTypeEnum programType) {
		this.programType = programType;
		return this;
	}

	/**
	 * Get programType
	 *
	 * @return programType
	 **/
	@ApiModelProperty(value = "")
	public ProgramTypeEnum getProgramType() {
		return programType;
	}

	public void setProgramType(ProgramTypeEnum programType) {
		this.programType = programType;
	}

	public ManagerPortfolioEvent logo(String logo) {
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

	public ManagerPortfolioEvent color(String color) {
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

	public ManagerPortfolioEvent description(String description) {
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

	public ManagerPortfolioEvent periodNumber(Integer periodNumber) {
		this.periodNumber = periodNumber;
		return this;
	}

	/**
	 * Get periodNumber
	 *
	 * @return periodNumber
	 **/
	@ApiModelProperty(value = "")
	public Integer getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(Integer periodNumber) {
		this.periodNumber = periodNumber;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ManagerPortfolioEvent managerPortfolioEvent = (ManagerPortfolioEvent) o;
		return Objects.equals(this.assetId, managerPortfolioEvent.assetId) &&
				Objects.equals(this.date, managerPortfolioEvent.date) &&
				Objects.equals(this.title, managerPortfolioEvent.title) &&
				Objects.equals(this.value, managerPortfolioEvent.value) &&
				Objects.equals(this.feeValue, managerPortfolioEvent.feeValue) &&
				Objects.equals(this.profitPercent, managerPortfolioEvent.profitPercent) &&
				Objects.equals(this.currency, managerPortfolioEvent.currency) &&
				Objects.equals(this.type, managerPortfolioEvent.type) &&
				Objects.equals(this.programType, managerPortfolioEvent.programType) &&
				Objects.equals(this.logo, managerPortfolioEvent.logo) &&
				Objects.equals(this.color, managerPortfolioEvent.color) &&
				Objects.equals(this.description, managerPortfolioEvent.description) &&
				Objects.equals(this.periodNumber, managerPortfolioEvent.periodNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, date, title, value, feeValue, profitPercent, currency, type, programType, logo, color, description, periodNumber);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ManagerPortfolioEvent {\n");

		sb.append("    assetId: ").append(toIndentedString(assetId)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    feeValue: ").append(toIndentedString(feeValue)).append("\n");
		sb.append("    profitPercent: ").append(toIndentedString(profitPercent)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    programType: ").append(toIndentedString(programType)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    periodNumber: ").append(toIndentedString(periodNumber)).append("\n");
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
		BTC("BTC"),

		ETH("ETH"),

		USDT("USDT"),

		GVT("GVT"),

		UNDEFINED("Undefined"),

		ADA("ADA"),

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
	 * Gets or Sets type
	 */
	@JsonAdapter(TypeEnum.Adapter.class)
	public enum TypeEnum
	{
		ALL("All"),

		ASSETSTARTED("AssetStarted"),

		PROGRAMPERIODSTARTS("ProgramPeriodStarts"),

		PROGRAMPERIODENDS("ProgramPeriodEnds"),

		INVESTORINVEST("InvestorInvest"),

		INVESTORWITHDRAW("InvestorWithdraw"),

		MANAGERINVEST("ManagerInvest"),

		MANAGERWITHDRAW("ManagerWithdraw"),

		ASSETFINISHED("AssetFinished"),

		ENTRANCEFEE("EntranceFee"),

		EXITFEE("ExitFee"),

		PROGRAMSTOPOUT("ProgramStopOut");

		public static TypeEnum fromValue(String text) {
			for (TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<TypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public TypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return TypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets programType
	 */
	@JsonAdapter(ProgramTypeEnum.Adapter.class)
	public enum ProgramTypeEnum
	{
		PROGRAM("Program"),

		FUND("Fund");

		public static ProgramTypeEnum fromValue(String text) {
			for (ProgramTypeEnum b : ProgramTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		ProgramTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<ProgramTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final ProgramTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public ProgramTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return ProgramTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

