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
 * WalletWithdrawalInfo
 */


public class WalletWithdrawalInfo implements Parcelable
{
	public static final Parcelable.Creator<WalletWithdrawalInfo> CREATOR = new Parcelable.Creator<WalletWithdrawalInfo>()
	{
		public WalletWithdrawalInfo createFromParcel(Parcel in) {
			return new WalletWithdrawalInfo(in);
		}

		public WalletWithdrawalInfo[] newArray(int size) {
			return new WalletWithdrawalInfo[size];
		}
	};

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("rateToGvt")
	private Double rateToGvt = null;

	@SerializedName("availableToWithdrawal")
	private Double availableToWithdrawal = null;

	public WalletWithdrawalInfo() {
	}

	WalletWithdrawalInfo(Parcel in) {
		currency = (CurrencyEnum) in.readValue(null);
		description = (String) in.readValue(null);
		logo = (String) in.readValue(null);
		commission = (Double) in.readValue(null);
		rateToGvt = (Double) in.readValue(null);
		availableToWithdrawal = (Double) in.readValue(null);
	}

	public WalletWithdrawalInfo currency(CurrencyEnum currency) {
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

	public WalletWithdrawalInfo description(String description) {
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

	public WalletWithdrawalInfo logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public WalletWithdrawalInfo commission(Double commission) {
		this.commission = commission;
		return this;
	}

	/**
	 * Get commission
	 *
	 * @return commission
	 **/
	@Schema(description = "")
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public WalletWithdrawalInfo rateToGvt(Double rateToGvt) {
		this.rateToGvt = rateToGvt;
		return this;
	}

	/**
	 * Get rateToGvt
	 *
	 * @return rateToGvt
	 **/
	@Schema(description = "")
	public Double getRateToGvt() {
		return rateToGvt;
	}

	public void setRateToGvt(Double rateToGvt) {
		this.rateToGvt = rateToGvt;
	}

	public WalletWithdrawalInfo availableToWithdrawal(Double availableToWithdrawal) {
		this.availableToWithdrawal = availableToWithdrawal;
		return this;
	}

	/**
	 * Get availableToWithdrawal
	 *
	 * @return availableToWithdrawal
	 **/
	@Schema(description = "")
	public Double getAvailableToWithdrawal() {
		return availableToWithdrawal;
	}

	public void setAvailableToWithdrawal(Double availableToWithdrawal) {
		this.availableToWithdrawal = availableToWithdrawal;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WalletWithdrawalInfo walletWithdrawalInfo = (WalletWithdrawalInfo) o;
		return Objects.equals(this.currency, walletWithdrawalInfo.currency) &&
				Objects.equals(this.description, walletWithdrawalInfo.description) &&
				Objects.equals(this.logo, walletWithdrawalInfo.logo) &&
				Objects.equals(this.commission, walletWithdrawalInfo.commission) &&
				Objects.equals(this.rateToGvt, walletWithdrawalInfo.rateToGvt) &&
				Objects.equals(this.availableToWithdrawal, walletWithdrawalInfo.availableToWithdrawal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, description, logo, commission, rateToGvt, availableToWithdrawal);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WalletWithdrawalInfo {\n");

		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    rateToGvt: ").append(toIndentedString(rateToGvt)).append("\n");
		sb.append("    availableToWithdrawal: ").append(toIndentedString(availableToWithdrawal)).append("\n");
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
		out.writeValue(currency);
		out.writeValue(description);
		out.writeValue(logo);
		out.writeValue(commission);
		out.writeValue(rateToGvt);
		out.writeValue(availableToWithdrawal);
	}

	public int describeContents() {
		return 0;
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
