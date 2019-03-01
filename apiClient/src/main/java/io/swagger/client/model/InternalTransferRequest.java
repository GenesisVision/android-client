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
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * InternalTransferRequest
 */

public class InternalTransferRequest
{
	@SerializedName("sourceId")
	private UUID sourceId = null;

	@SerializedName("sourceType")
	private SourceTypeEnum sourceType = null;

	@SerializedName("destinationId")
	private UUID destinationId = null;

	@SerializedName("destinationType")
	private DestinationTypeEnum destinationType = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("transferAll")
	private Boolean transferAll = null;

	public InternalTransferRequest sourceId(UUID sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	/**
	 * Get sourceId
	 *
	 * @return sourceId
	 **/
	@ApiModelProperty(value = "")
	public UUID getSourceId() {
		return sourceId;
	}

	public void setSourceId(UUID sourceId) {
		this.sourceId = sourceId;
	}

	public InternalTransferRequest sourceType(SourceTypeEnum sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	/**
	 * Get sourceType
	 *
	 * @return sourceType
	 **/
	@ApiModelProperty(value = "")
	public SourceTypeEnum getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceTypeEnum sourceType) {
		this.sourceType = sourceType;
	}

	public InternalTransferRequest destinationId(UUID destinationId) {
		this.destinationId = destinationId;
		return this;
	}

	/**
	 * Get destinationId
	 *
	 * @return destinationId
	 **/
	@ApiModelProperty(value = "")
	public UUID getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(UUID destinationId) {
		this.destinationId = destinationId;
	}

	public InternalTransferRequest destinationType(DestinationTypeEnum destinationType) {
		this.destinationType = destinationType;
		return this;
	}

	/**
	 * Get destinationType
	 *
	 * @return destinationType
	 **/
	@ApiModelProperty(value = "")
	public DestinationTypeEnum getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(DestinationTypeEnum destinationType) {
		this.destinationType = destinationType;
	}

	public InternalTransferRequest amount(Double amount) {
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

	public InternalTransferRequest transferAll(Boolean transferAll) {
		this.transferAll = transferAll;
		return this;
	}

	/**
	 * Get transferAll
	 *
	 * @return transferAll
	 **/
	@ApiModelProperty(value = "")
	public Boolean isTransferAll() {
		return transferAll;
	}

	public void setTransferAll(Boolean transferAll) {
		this.transferAll = transferAll;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InternalTransferRequest internalTransferRequest = (InternalTransferRequest) o;
		return Objects.equals(this.sourceId, internalTransferRequest.sourceId) &&
				Objects.equals(this.sourceType, internalTransferRequest.sourceType) &&
				Objects.equals(this.destinationId, internalTransferRequest.destinationId) &&
				Objects.equals(this.destinationType, internalTransferRequest.destinationType) &&
				Objects.equals(this.amount, internalTransferRequest.amount) &&
				Objects.equals(this.transferAll, internalTransferRequest.transferAll);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceId, sourceType, destinationId, destinationType, amount, transferAll);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InternalTransferRequest {\n");

		sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
		sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
		sb.append("    destinationId: ").append(toIndentedString(destinationId)).append("\n");
		sb.append("    destinationType: ").append(toIndentedString(destinationType)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    transferAll: ").append(toIndentedString(transferAll)).append("\n");
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
	 * Gets or Sets sourceType
	 */
	@JsonAdapter(SourceTypeEnum.Adapter.class)
	public enum SourceTypeEnum
	{
		UNDEFINED("Undefined"),

		WALLET("Wallet");

		public static SourceTypeEnum fromValue(String text) {
			for (SourceTypeEnum b : SourceTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		SourceTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<SourceTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final SourceTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public SourceTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return SourceTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets destinationType
	 */
	@JsonAdapter(DestinationTypeEnum.Adapter.class)
	public enum DestinationTypeEnum
	{
		UNDEFINED("Undefined"),

		WALLET("Wallet");

		public static DestinationTypeEnum fromValue(String text) {
			for (DestinationTypeEnum b : DestinationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DestinationTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DestinationTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DestinationTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DestinationTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DestinationTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

