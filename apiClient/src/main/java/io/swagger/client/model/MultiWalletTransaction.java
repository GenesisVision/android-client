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
 * MultiWalletTransaction
 */

public class MultiWalletTransaction
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("currencyFrom")
	private CurrencyFromEnum currencyFrom = null;

	@SerializedName("currencyTo")
	private CurrencyToEnum currencyTo = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("logoFrom")
	private String logoFrom = null;

	@SerializedName("logoTo")
	private String logoTo = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("amountTo")
	private Double amountTo = null;

	public MultiWalletTransaction id(UUID id) {
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

	public MultiWalletTransaction currencyFrom(CurrencyFromEnum currencyFrom) {
		this.currencyFrom = currencyFrom;
		return this;
	}

	/**
	 * Get currencyFrom
	 *
	 * @return currencyFrom
	 **/
	@ApiModelProperty(value = "")
	public CurrencyFromEnum getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(CurrencyFromEnum currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public MultiWalletTransaction currencyTo(CurrencyToEnum currencyTo) {
		this.currencyTo = currencyTo;
		return this;
	}

	/**
	 * Get currencyTo
	 *
	 * @return currencyTo
	 **/
	@ApiModelProperty(value = "")
	public CurrencyToEnum getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(CurrencyToEnum currencyTo) {
		this.currencyTo = currencyTo;
	}

	public MultiWalletTransaction type(TypeEnum type) {
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

	public MultiWalletTransaction date(DateTime date) {
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

	public MultiWalletTransaction status(StatusEnum status) {
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

	public MultiWalletTransaction logoFrom(String logoFrom) {
		this.logoFrom = logoFrom;
		return this;
	}

	/**
	 * Get logoFrom
	 *
	 * @return logoFrom
	 **/
	@ApiModelProperty(value = "")
	public String getLogoFrom() {
		return logoFrom;
	}

	public void setLogoFrom(String logoFrom) {
		this.logoFrom = logoFrom;
	}

	public MultiWalletTransaction logoTo(String logoTo) {
		this.logoTo = logoTo;
		return this;
	}

	/**
	 * Get logoTo
	 *
	 * @return logoTo
	 **/
	@ApiModelProperty(value = "")
	public String getLogoTo() {
		return logoTo;
	}

	public void setLogoTo(String logoTo) {
		this.logoTo = logoTo;
	}

	public MultiWalletTransaction description(String description) {
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

	public MultiWalletTransaction amount(Double amount) {
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

	public MultiWalletTransaction amountTo(Double amountTo) {
		this.amountTo = amountTo;
		return this;
	}

	/**
	 * Get amountTo
	 *
	 * @return amountTo
	 **/
	@ApiModelProperty(value = "")
	public Double getAmountTo() {
		return amountTo;
	}

	public void setAmountTo(Double amountTo) {
		this.amountTo = amountTo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MultiWalletTransaction multiWalletTransaction = (MultiWalletTransaction) o;
		return Objects.equals(this.id, multiWalletTransaction.id) &&
				Objects.equals(this.currencyFrom, multiWalletTransaction.currencyFrom) &&
				Objects.equals(this.currencyTo, multiWalletTransaction.currencyTo) &&
				Objects.equals(this.type, multiWalletTransaction.type) &&
				Objects.equals(this.date, multiWalletTransaction.date) &&
				Objects.equals(this.status, multiWalletTransaction.status) &&
				Objects.equals(this.logoFrom, multiWalletTransaction.logoFrom) &&
				Objects.equals(this.logoTo, multiWalletTransaction.logoTo) &&
				Objects.equals(this.description, multiWalletTransaction.description) &&
				Objects.equals(this.amount, multiWalletTransaction.amount) &&
				Objects.equals(this.amountTo, multiWalletTransaction.amountTo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, currencyFrom, currencyTo, type, date, status, logoFrom, logoTo, description, amount, amountTo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MultiWalletTransaction {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    currencyFrom: ").append(toIndentedString(currencyFrom)).append("\n");
		sb.append("    currencyTo: ").append(toIndentedString(currencyTo)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    logoFrom: ").append(toIndentedString(logoFrom)).append("\n");
		sb.append("    logoTo: ").append(toIndentedString(logoTo)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    amountTo: ").append(toIndentedString(amountTo)).append("\n");
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
	 * Gets or Sets currencyFrom
	 */
	@JsonAdapter(CurrencyFromEnum.Adapter.class)
	public enum CurrencyFromEnum
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

		public static CurrencyFromEnum fromValue(String text) {
			for (CurrencyFromEnum b : CurrencyFromEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		CurrencyFromEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<CurrencyFromEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final CurrencyFromEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public CurrencyFromEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return CurrencyFromEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets currencyTo
	 */
	@JsonAdapter(CurrencyToEnum.Adapter.class)
	public enum CurrencyToEnum
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

		public static CurrencyToEnum fromValue(String text) {
			for (CurrencyToEnum b : CurrencyToEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		CurrencyToEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<CurrencyToEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final CurrencyToEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public CurrencyToEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return CurrencyToEnum.fromValue(String.valueOf(value));
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

		INVESTMENT("Investment"),

		CONVERTING("Converting"),

		WITHDRAWAL("Withdrawal"),

		CLOSE("Close"),

		OPEN("Open"),

		FEE("Fee"),

		PROFITS("Profits"),

		SUBSCRIBESIGNAL("SubscribeSignal"),

		RECEIVESIGNAL("ReceiveSignal"),

		DEPOSITSIGNAL("DepositSignal"),

		WITHDRAWALSIGNAL("WithdrawalSignal"),

		PLATFORM("Platform");

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

}

