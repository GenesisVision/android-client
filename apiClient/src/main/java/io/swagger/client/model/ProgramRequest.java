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
 * ProgramRequest
 */

public class ProgramRequest
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("programId")
	private UUID programId = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("valueGvt")
	private Double valueGvt = null;

	@SerializedName("feeEntry")
	private Double feeEntry = null;

	@SerializedName("feeExit")
	private Double feeExit = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("fundWithdrawPercent")
	private Double fundWithdrawPercent = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("canCancelRequest")
	private Boolean canCancelRequest = null;

	@SerializedName("programType")
	private ProgramTypeEnum programType = null;

	public ProgramRequest id(UUID id) {
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

	public ProgramRequest programId(UUID programId) {
		this.programId = programId;
		return this;
	}

	/**
	 * Get programId
	 *
	 * @return programId
	 **/
	@ApiModelProperty(value = "")
	public UUID getProgramId() {
		return programId;
	}

	public void setProgramId(UUID programId) {
		this.programId = programId;
	}

	public ProgramRequest date(DateTime date) {
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

	public ProgramRequest value(Double value) {
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

	public ProgramRequest valueGvt(Double valueGvt) {
		this.valueGvt = valueGvt;
		return this;
	}

	/**
	 * Get valueGvt
	 *
	 * @return valueGvt
	 **/
	@ApiModelProperty(value = "")
	public Double getValueGvt() {
		return valueGvt;
	}

	public void setValueGvt(Double valueGvt) {
		this.valueGvt = valueGvt;
	}

	public ProgramRequest feeEntry(Double feeEntry) {
		this.feeEntry = feeEntry;
		return this;
	}

	/**
	 * Get feeEntry
	 *
	 * @return feeEntry
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeEntry() {
		return feeEntry;
	}

	public void setFeeEntry(Double feeEntry) {
		this.feeEntry = feeEntry;
	}

	public ProgramRequest feeExit(Double feeExit) {
		this.feeExit = feeExit;
		return this;
	}

	/**
	 * Get feeExit
	 *
	 * @return feeExit
	 **/
	@ApiModelProperty(value = "")
	public Double getFeeExit() {
		return feeExit;
	}

	public void setFeeExit(Double feeExit) {
		this.feeExit = feeExit;
	}

	public ProgramRequest currency(CurrencyEnum currency) {
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

	public ProgramRequest fundWithdrawPercent(Double fundWithdrawPercent) {
		this.fundWithdrawPercent = fundWithdrawPercent;
		return this;
	}

	/**
	 * Used only in fund withdraw request
	 *
	 * @return fundWithdrawPercent
	 **/
	@ApiModelProperty(value = "Used only in fund withdraw request")
	public Double getFundWithdrawPercent() {
		return fundWithdrawPercent;
	}

	public void setFundWithdrawPercent(Double fundWithdrawPercent) {
		this.fundWithdrawPercent = fundWithdrawPercent;
	}

	public ProgramRequest type(TypeEnum type) {
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

	public ProgramRequest status(StatusEnum status) {
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

	public ProgramRequest logo(String logo) {
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

	public ProgramRequest title(String title) {
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

	public ProgramRequest color(String color) {
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

	public ProgramRequest canCancelRequest(Boolean canCancelRequest) {
		this.canCancelRequest = canCancelRequest;
		return this;
	}

	/**
	 * Get canCancelRequest
	 *
	 * @return canCancelRequest
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanCancelRequest() {
		return canCancelRequest;
	}

	public void setCanCancelRequest(Boolean canCancelRequest) {
		this.canCancelRequest = canCancelRequest;
	}

	public ProgramRequest programType(ProgramTypeEnum programType) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramRequest programRequest = (ProgramRequest) o;
		return Objects.equals(this.id, programRequest.id) &&
				Objects.equals(this.programId, programRequest.programId) &&
				Objects.equals(this.date, programRequest.date) &&
				Objects.equals(this.value, programRequest.value) &&
				Objects.equals(this.valueGvt, programRequest.valueGvt) &&
				Objects.equals(this.feeEntry, programRequest.feeEntry) &&
				Objects.equals(this.feeExit, programRequest.feeExit) &&
				Objects.equals(this.currency, programRequest.currency) &&
				Objects.equals(this.fundWithdrawPercent, programRequest.fundWithdrawPercent) &&
				Objects.equals(this.type, programRequest.type) &&
				Objects.equals(this.status, programRequest.status) &&
				Objects.equals(this.logo, programRequest.logo) &&
				Objects.equals(this.title, programRequest.title) &&
				Objects.equals(this.color, programRequest.color) &&
				Objects.equals(this.canCancelRequest, programRequest.canCancelRequest) &&
				Objects.equals(this.programType, programRequest.programType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, programId, date, value, valueGvt, feeEntry, feeExit, currency, fundWithdrawPercent, type, status, logo, title, color, canCancelRequest, programType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramRequest {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    programId: ").append(toIndentedString(programId)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    valueGvt: ").append(toIndentedString(valueGvt)).append("\n");
		sb.append("    feeEntry: ").append(toIndentedString(feeEntry)).append("\n");
		sb.append("    feeExit: ").append(toIndentedString(feeExit)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    fundWithdrawPercent: ").append(toIndentedString(fundWithdrawPercent)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    canCancelRequest: ").append(toIndentedString(canCancelRequest)).append("\n");
		sb.append("    programType: ").append(toIndentedString(programType)).append("\n");
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
		INVEST("Invest"),

		WITHDRAWAL("Withdrawal");

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
		NEW("New"),

		EXECUTED("Executed"),

		CANCELLED("Cancelled");

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

