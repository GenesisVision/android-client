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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * TradingAccountDetails
 */


public class TradingAccountDetails implements Parcelable
{
	public static final Parcelable.Creator<TradingAccountDetails> CREATOR = new Parcelable.Creator<TradingAccountDetails>()
	{
		public TradingAccountDetails createFromParcel(Parcel in) {
			return new TradingAccountDetails(in);
		}

		public TradingAccountDetails[] newArray(int size) {
			return new TradingAccountDetails[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("apiKey")
	private String apiKey = null;

	@SerializedName("asset")
	private AssetDetails asset = null;

	public TradingAccountDetails() {
	}

	TradingAccountDetails(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		currency = (CurrencyEnum) in.readValue(null);
		login = (String) in.readValue(null);
		apiKey = (String) in.readValue(null);
		asset = (AssetDetails) in.readValue(AssetDetails.class.getClassLoader());
	}

	public TradingAccountDetails id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public TradingAccountDetails currency(CurrencyEnum currency) {
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

	public TradingAccountDetails login(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Get login
	 *
	 * @return login
	 **/
	@Schema(description = "")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public TradingAccountDetails apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}

	/**
	 * Get apiKey
	 *
	 * @return apiKey
	 **/
	@Schema(description = "")
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public TradingAccountDetails asset(AssetDetails asset) {
		this.asset = asset;
		return this;
	}

	/**
	 * Get asset
	 *
	 * @return asset
	 **/
	@Schema(description = "")
	public AssetDetails getAsset() {
		return asset;
	}

	public void setAsset(AssetDetails asset) {
		this.asset = asset;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradingAccountDetails tradingAccountDetails = (TradingAccountDetails) o;
		return Objects.equals(this.id, tradingAccountDetails.id) &&
				Objects.equals(this.currency, tradingAccountDetails.currency) &&
				Objects.equals(this.login, tradingAccountDetails.login) &&
				Objects.equals(this.apiKey, tradingAccountDetails.apiKey) &&
				Objects.equals(this.asset, tradingAccountDetails.asset);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, currency, login, apiKey, asset);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradingAccountDetails {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
		sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
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
		out.writeValue(id);
		out.writeValue(currency);
		out.writeValue(login);
		out.writeValue(apiKey);
		out.writeValue(asset);
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
		USD("USD"),
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
