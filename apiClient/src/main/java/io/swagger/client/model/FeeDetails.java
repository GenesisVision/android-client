/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * FeeDetails
 */


public class FeeDetails implements Parcelable
{
	public static final Parcelable.Creator<FeeDetails> CREATOR = new Parcelable.Creator<FeeDetails>()
	{
		public FeeDetails createFromParcel(Parcel in) {
			return new FeeDetails(in);
		}

		public FeeDetails[] newArray(int size) {
			return new FeeDetails[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	public FeeDetails() {
	}

	FeeDetails(Parcel in) {
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		type = (TypeEnum) in.readValue(null);
		amount = (Double) in.readValue(null);
		currency = (CurrencyEnum) in.readValue(null);
	}

	public FeeDetails title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FeeDetails description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@Schema(description = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FeeDetails type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public FeeDetails amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@Schema(description = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public FeeDetails currency(CurrencyEnum currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FeeDetails feeDetails = (FeeDetails) o;
		return Objects.equals(this.title, feeDetails.title) &&
				Objects.equals(this.description, feeDetails.description) &&
				Objects.equals(this.type, feeDetails.type) &&
				Objects.equals(this.amount, feeDetails.amount) &&
				Objects.equals(this.currency, feeDetails.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, type, amount, currency);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FeeDetails {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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


	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(type);
		out.writeValue(amount);
		out.writeValue(currency);
	}

	public int describeContents() {
		return 0;
	}

	/**
	 * Gets or Sets type
	 */
	@JsonAdapter(TypeEnum.Adapter.class)
	public enum TypeEnum
	{
		UNDEFINED("Undefined"),
		GVPROGRAMENTRY("GvProgramEntry"),
		GVPROGRAMSUCCESS("GvProgramSuccess"),
		GVPROGRAMSUCCESSSUM("GvProgramSuccessSum"),
		GVFUNDENTRY("GvFundEntry"),
		GVGMGVTHOLDERFEE("GvGmGvtHolderFee"),
		MANAGERPROGRAMENTRY("ManagerProgramEntry"),
		MANAGERPROGRAMSUCCESS("ManagerProgramSuccess"),
		MANAGERPROGRAMSUCCESSSUM("ManagerProgramSuccessSum"),
		MANAGERFUNDENTRY("ManagerFundEntry"),
		MANAGERFUNDEXIT("ManagerFundExit"),
		GVWITHDRAWAL("GvWithdrawal"),
		MANAGERSIGNALMASTERSUCCESSFEE("ManagerSignalMasterSuccessFee"),
		MANAGERSIGNALMASTERVOLUMEFEE("ManagerSignalMasterVolumeFee"),
		GVSIGNALSUCCESSFEE("GvSignalSuccessFee"),
		GVFUNDTRADE("GvFundTrade");

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
}
