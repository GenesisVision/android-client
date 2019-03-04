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
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * TransactionDetails
 */

public class TransactionDetails
{
	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("programDetails")
	private ProgramTransactionDetails programDetails = null;

	@SerializedName("convertingDetails")
	private ConvertingDetails convertingDetails = null;

	@SerializedName("externalTransactionDetails")
	private ExternalTransactionDetails externalTransactionDetails = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("currencyName")
	private String currencyName = null;

	@SerializedName("currencyLogo")
	private String currencyLogo = null;

	@SerializedName("gvCommission")
	private Double gvCommission = null;

	@SerializedName("gvCommissionCurrency")
	private GvCommissionCurrencyEnum gvCommissionCurrency = null;

	@SerializedName("gvCommissionPercent")
	private Double gvCommissionPercent = null;

	@SerializedName("amount")
	private Double amount = null;

	public TransactionDetails type(TypeEnum type) {
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

	public TransactionDetails programDetails(ProgramTransactionDetails programDetails) {
		this.programDetails = programDetails;
		return this;
	}

	/**
	 * Get programDetails
	 *
	 * @return programDetails
	 **/
	@ApiModelProperty(value = "")
	public ProgramTransactionDetails getProgramDetails() {
		return programDetails;
	}

	public void setProgramDetails(ProgramTransactionDetails programDetails) {
		this.programDetails = programDetails;
	}

	public TransactionDetails convertingDetails(ConvertingDetails convertingDetails) {
		this.convertingDetails = convertingDetails;
		return this;
	}

	/**
	 * Get convertingDetails
	 *
	 * @return convertingDetails
	 **/
	@ApiModelProperty(value = "")
	public ConvertingDetails getConvertingDetails() {
		return convertingDetails;
	}

	public void setConvertingDetails(ConvertingDetails convertingDetails) {
		this.convertingDetails = convertingDetails;
	}

	public TransactionDetails externalTransactionDetails(ExternalTransactionDetails externalTransactionDetails) {
		this.externalTransactionDetails = externalTransactionDetails;
		return this;
	}

	/**
	 * Get externalTransactionDetails
	 *
	 * @return externalTransactionDetails
	 **/
	@ApiModelProperty(value = "")
	public ExternalTransactionDetails getExternalTransactionDetails() {
		return externalTransactionDetails;
	}

	public void setExternalTransactionDetails(ExternalTransactionDetails externalTransactionDetails) {
		this.externalTransactionDetails = externalTransactionDetails;
	}

	public TransactionDetails status(StatusEnum status) {
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

	public TransactionDetails currency(CurrencyEnum currency) {
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

	public TransactionDetails currencyName(String currencyName) {
		this.currencyName = currencyName;
		return this;
	}

	/**
	 * Get currencyName
	 *
	 * @return currencyName
	 **/
	@ApiModelProperty(value = "")
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public TransactionDetails currencyLogo(String currencyLogo) {
		this.currencyLogo = currencyLogo;
		return this;
	}

	/**
	 * Get currencyLogo
	 *
	 * @return currencyLogo
	 **/
	@ApiModelProperty(value = "")
	public String getCurrencyLogo() {
		return currencyLogo;
	}

	public void setCurrencyLogo(String currencyLogo) {
		this.currencyLogo = currencyLogo;
	}

	public TransactionDetails gvCommission(Double gvCommission) {
		this.gvCommission = gvCommission;
		return this;
	}

	/**
	 * Get gvCommission
	 *
	 * @return gvCommission
	 **/
	@ApiModelProperty(value = "")
	public Double getGvCommission() {
		return gvCommission;
	}

	public void setGvCommission(Double gvCommission) {
		this.gvCommission = gvCommission;
	}

	public TransactionDetails gvCommissionCurrency(GvCommissionCurrencyEnum gvCommissionCurrency) {
		this.gvCommissionCurrency = gvCommissionCurrency;
		return this;
	}

	/**
	 * Get gvCommissionCurrency
	 *
	 * @return gvCommissionCurrency
	 **/
	@ApiModelProperty(value = "")
	public GvCommissionCurrencyEnum getGvCommissionCurrency() {
		return gvCommissionCurrency;
	}

	public void setGvCommissionCurrency(GvCommissionCurrencyEnum gvCommissionCurrency) {
		this.gvCommissionCurrency = gvCommissionCurrency;
	}

	public TransactionDetails gvCommissionPercent(Double gvCommissionPercent) {
		this.gvCommissionPercent = gvCommissionPercent;
		return this;
	}

	/**
	 * Get gvCommissionPercent
	 *
	 * @return gvCommissionPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getGvCommissionPercent() {
		return gvCommissionPercent;
	}

	public void setGvCommissionPercent(Double gvCommissionPercent) {
		this.gvCommissionPercent = gvCommissionPercent;
	}

	public TransactionDetails amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@ApiModelProperty(value = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TransactionDetails transactionDetails = (TransactionDetails) o;
		return Objects.equals(this.type, transactionDetails.type) &&
				Objects.equals(this.programDetails, transactionDetails.programDetails) &&
				Objects.equals(this.convertingDetails, transactionDetails.convertingDetails) &&
				Objects.equals(this.externalTransactionDetails, transactionDetails.externalTransactionDetails) &&
				Objects.equals(this.status, transactionDetails.status) &&
				Objects.equals(this.currency, transactionDetails.currency) &&
				Objects.equals(this.currencyName, transactionDetails.currencyName) &&
				Objects.equals(this.currencyLogo, transactionDetails.currencyLogo) &&
				Objects.equals(this.gvCommission, transactionDetails.gvCommission) &&
				Objects.equals(this.gvCommissionCurrency, transactionDetails.gvCommissionCurrency) &&
				Objects.equals(this.gvCommissionPercent, transactionDetails.gvCommissionPercent) &&
				Objects.equals(this.amount, transactionDetails.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, programDetails, convertingDetails, externalTransactionDetails, status, currency, currencyName, currencyLogo, gvCommission, gvCommissionCurrency, gvCommissionPercent, amount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TransactionDetails {\n");

		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    programDetails: ").append(toIndentedString(programDetails)).append("\n");
		sb.append("    convertingDetails: ").append(toIndentedString(convertingDetails)).append("\n");
		sb.append("    externalTransactionDetails: ").append(toIndentedString(externalTransactionDetails)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    currencyName: ").append(toIndentedString(currencyName)).append("\n");
		sb.append("    currencyLogo: ").append(toIndentedString(currencyLogo)).append("\n");
		sb.append("    gvCommission: ").append(toIndentedString(gvCommission)).append("\n");
		sb.append("    gvCommissionCurrency: ").append(toIndentedString(gvCommissionCurrency)).append("\n");
		sb.append("    gvCommissionPercent: ").append(toIndentedString(gvCommissionPercent)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
	 * Gets or Sets type
	 */
	@JsonAdapter(TypeEnum.Adapter.class)
	public enum TypeEnum
	{
		INVESTING("Investing"),

		WITHDRAWAL("Withdrawal"),

		EXTERNALWITHDRAWAL("ExternalWithdrawal"),

		EXTERNALDEPOSIT("ExternalDeposit"),

		CONVERTING("Converting"),

		OPEN("Open"),

		CLOSE("Close"),

		PROFIT("Profit"),

		PLATFORMFEE("PlatformFee");

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
	 * Gets or Sets status
	 */
	@JsonAdapter(StatusEnum.Adapter.class)
	public enum StatusEnum
	{
		DONE("Done"),

		PENDING("Pending"),

		CANCELED("Canceled"),

		ERROR("Error");

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
	 * Gets or Sets gvCommissionCurrency
	 */
	@JsonAdapter(GvCommissionCurrencyEnum.Adapter.class)
	public enum GvCommissionCurrencyEnum
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

		public static GvCommissionCurrencyEnum fromValue(String text) {
			for (GvCommissionCurrencyEnum b : GvCommissionCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		GvCommissionCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<GvCommissionCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final GvCommissionCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public GvCommissionCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return GvCommissionCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

