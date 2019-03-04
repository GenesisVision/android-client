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
 * DashboardPortfolioEvent
 */

public class DashboardPortfolioEvent
{
	@SerializedName("assetId")
	private UUID assetId = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("valueTotal")
	private Double valueTotal = null;

	@SerializedName("feeSuccessManager")
	private Double feeSuccessManager = null;

	@SerializedName("feeSuccessManagerCurrency")
	private FeeSuccessManagerCurrencyEnum feeSuccessManagerCurrency = null;

	@SerializedName("feeSuccessPlatform")
	private Double feeSuccessPlatform = null;

	@SerializedName("feeSuccessPlatformCurrency")
	private FeeSuccessPlatformCurrencyEnum feeSuccessPlatformCurrency = null;

	@SerializedName("profitPercent")
	private Double profitPercent = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("assetType")
	private AssetTypeEnum assetType = null;

	public DashboardPortfolioEvent assetId(UUID assetId) {
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

	public DashboardPortfolioEvent date(DateTime date) {
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

	public DashboardPortfolioEvent title(String title) {
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

	public DashboardPortfolioEvent value(Double value) {
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

	public DashboardPortfolioEvent valueTotal(Double valueTotal) {
		this.valueTotal = valueTotal;
		return this;
	}

	/**
	 * Get valueTotal
	 *
	 * @return valueTotal
	 **/
	@ApiModelProperty(value = "")
	public Double getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(Double valueTotal) {
		this.valueTotal = valueTotal;
	}

	public DashboardPortfolioEvent feeSuccessManager(Double feeSuccessManager) {
		this.feeSuccessManager = feeSuccessManager;
		return this;
	}

	/**
	 * Get feeSuccessManager
	 *
	 * @return feeSuccessManager
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeSuccessManager() {
		return feeSuccessManager;
	}

	public void setFeeSuccessManager(Double feeSuccessManager) {
		this.feeSuccessManager = feeSuccessManager;
	}

	public DashboardPortfolioEvent feeSuccessManagerCurrency(FeeSuccessManagerCurrencyEnum feeSuccessManagerCurrency) {
		this.feeSuccessManagerCurrency = feeSuccessManagerCurrency;
		return this;
	}

	/**
	 * Get feeSuccessManagerCurrency
	 *
	 * @return feeSuccessManagerCurrency
	 **/
	@ApiModelProperty(value = "")
	public FeeSuccessManagerCurrencyEnum getFeeSuccessManagerCurrency() {
		return feeSuccessManagerCurrency;
	}

	public void setFeeSuccessManagerCurrency(FeeSuccessManagerCurrencyEnum feeSuccessManagerCurrency) {
		this.feeSuccessManagerCurrency = feeSuccessManagerCurrency;
	}

	public DashboardPortfolioEvent feeSuccessPlatform(Double feeSuccessPlatform) {
		this.feeSuccessPlatform = feeSuccessPlatform;
		return this;
	}

	/**
	 * Get feeSuccessPlatform
	 *
	 * @return feeSuccessPlatform
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeSuccessPlatform() {
		return feeSuccessPlatform;
	}

	public void setFeeSuccessPlatform(Double feeSuccessPlatform) {
		this.feeSuccessPlatform = feeSuccessPlatform;
	}

	public DashboardPortfolioEvent feeSuccessPlatformCurrency(FeeSuccessPlatformCurrencyEnum feeSuccessPlatformCurrency) {
		this.feeSuccessPlatformCurrency = feeSuccessPlatformCurrency;
		return this;
	}

	/**
	 * Get feeSuccessPlatformCurrency
	 *
	 * @return feeSuccessPlatformCurrency
	 **/
	@ApiModelProperty(value = "")
	public FeeSuccessPlatformCurrencyEnum getFeeSuccessPlatformCurrency() {
		return feeSuccessPlatformCurrency;
	}

	public void setFeeSuccessPlatformCurrency(FeeSuccessPlatformCurrencyEnum feeSuccessPlatformCurrency) {
		this.feeSuccessPlatformCurrency = feeSuccessPlatformCurrency;
	}

	public DashboardPortfolioEvent profitPercent(Double profitPercent) {
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

	public DashboardPortfolioEvent currency(CurrencyEnum currency) {
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

	public DashboardPortfolioEvent type(TypeEnum type) {
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

	public DashboardPortfolioEvent logo(String logo) {
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

	public DashboardPortfolioEvent color(String color) {
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

	public DashboardPortfolioEvent description(String description) {
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

	public DashboardPortfolioEvent assetType(AssetTypeEnum assetType) {
		this.assetType = assetType;
		return this;
	}

	/**
	 * Get assetType
	 *
	 * @return assetType
	 **/
	@ApiModelProperty(value = "")
	public AssetTypeEnum getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetTypeEnum assetType) {
		this.assetType = assetType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardPortfolioEvent dashboardPortfolioEvent = (DashboardPortfolioEvent) o;
		return Objects.equals(this.assetId, dashboardPortfolioEvent.assetId) &&
				Objects.equals(this.date, dashboardPortfolioEvent.date) &&
				Objects.equals(this.title, dashboardPortfolioEvent.title) &&
				Objects.equals(this.value, dashboardPortfolioEvent.value) &&
				Objects.equals(this.valueTotal, dashboardPortfolioEvent.valueTotal) &&
				Objects.equals(this.feeSuccessManager, dashboardPortfolioEvent.feeSuccessManager) &&
				Objects.equals(this.feeSuccessManagerCurrency, dashboardPortfolioEvent.feeSuccessManagerCurrency) &&
				Objects.equals(this.feeSuccessPlatform, dashboardPortfolioEvent.feeSuccessPlatform) &&
				Objects.equals(this.feeSuccessPlatformCurrency, dashboardPortfolioEvent.feeSuccessPlatformCurrency) &&
				Objects.equals(this.profitPercent, dashboardPortfolioEvent.profitPercent) &&
				Objects.equals(this.currency, dashboardPortfolioEvent.currency) &&
				Objects.equals(this.type, dashboardPortfolioEvent.type) &&
				Objects.equals(this.logo, dashboardPortfolioEvent.logo) &&
				Objects.equals(this.color, dashboardPortfolioEvent.color) &&
				Objects.equals(this.description, dashboardPortfolioEvent.description) &&
				Objects.equals(this.assetType, dashboardPortfolioEvent.assetType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assetId, date, title, value, valueTotal, feeSuccessManager, feeSuccessManagerCurrency, feeSuccessPlatform, feeSuccessPlatformCurrency, profitPercent, currency, type, logo, color, description, assetType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardPortfolioEvent {\n");

		sb.append("    assetId: ").append(toIndentedString(assetId)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    valueTotal: ").append(toIndentedString(valueTotal)).append("\n");
		sb.append("    feeSuccessManager: ").append(toIndentedString(feeSuccessManager)).append("\n");
		sb.append("    feeSuccessManagerCurrency: ").append(toIndentedString(feeSuccessManagerCurrency)).append("\n");
		sb.append("    feeSuccessPlatform: ").append(toIndentedString(feeSuccessPlatform)).append("\n");
		sb.append("    feeSuccessPlatformCurrency: ").append(toIndentedString(feeSuccessPlatformCurrency)).append("\n");
		sb.append("    profitPercent: ").append(toIndentedString(profitPercent)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    assetType: ").append(toIndentedString(assetType)).append("\n");
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
	 * Gets or Sets feeSuccessManagerCurrency
	 */
	@JsonAdapter(FeeSuccessManagerCurrencyEnum.Adapter.class)
	public enum FeeSuccessManagerCurrencyEnum
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

		public static FeeSuccessManagerCurrencyEnum fromValue(String text) {
			for (FeeSuccessManagerCurrencyEnum b : FeeSuccessManagerCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		FeeSuccessManagerCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<FeeSuccessManagerCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final FeeSuccessManagerCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public FeeSuccessManagerCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return FeeSuccessManagerCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}


	/**
	 * Gets or Sets feeSuccessPlatformCurrency
	 */
	@JsonAdapter(FeeSuccessPlatformCurrencyEnum.Adapter.class)
	public enum FeeSuccessPlatformCurrencyEnum
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

		public static FeeSuccessPlatformCurrencyEnum fromValue(String text) {
			for (FeeSuccessPlatformCurrencyEnum b : FeeSuccessPlatformCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		FeeSuccessPlatformCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<FeeSuccessPlatformCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final FeeSuccessPlatformCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public FeeSuccessPlatformCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return FeeSuccessPlatformCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
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

		INVEST("Invest"),

		WITHDRAW("Withdraw"),

		PROFIT("Profit"),

		LOSS("Loss"),

		REINVEST("Reinvest"),

		CANCELED("Canceled"),

		ENDED("Ended"),

		WITHDRAWBYSTOPOUT("WithdrawByStopOut");

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
	 * Gets or Sets assetType
	 */
	@JsonAdapter(AssetTypeEnum.Adapter.class)
	public enum AssetTypeEnum
	{
		PROGRAM("Program"),

		FUND("Fund");

		public static AssetTypeEnum fromValue(String text) {
			for (AssetTypeEnum b : AssetTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		AssetTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<AssetTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final AssetTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public AssetTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return AssetTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

