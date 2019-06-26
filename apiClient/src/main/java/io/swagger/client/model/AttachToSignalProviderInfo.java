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
 * AttachToSignalProviderInfo
 */

public class AttachToSignalProviderInfo
{
	@SerializedName("hasSignalAccount")
	private Boolean hasSignalAccount = null;

	@SerializedName("hasActiveSubscription")
	private Boolean hasActiveSubscription = null;

	@SerializedName("volumeFee")
	private Double volumeFee = null;

	@SerializedName("minDeposit")
	private Double minDeposit = null;

	@SerializedName("minDepositCurrency")
	private MinDepositCurrencyEnum minDepositCurrency = null;

	public AttachToSignalProviderInfo hasSignalAccount(Boolean hasSignalAccount) {
		this.hasSignalAccount = hasSignalAccount;
		return this;
	}

	/**
	 * Get hasSignalAccount
	 *
	 * @return hasSignalAccount
	 **/
	@ApiModelProperty(value = "")
	public Boolean isHasSignalAccount() {
		return hasSignalAccount;
	}

	public void setHasSignalAccount(Boolean hasSignalAccount) {
		this.hasSignalAccount = hasSignalAccount;
	}

	public AttachToSignalProviderInfo hasActiveSubscription(Boolean hasActiveSubscription) {
		this.hasActiveSubscription = hasActiveSubscription;
		return this;
	}

	/**
	 * Get hasActiveSubscription
	 *
	 * @return hasActiveSubscription
	 **/
	@ApiModelProperty(value = "")
	public Boolean isHasActiveSubscription() {
		return hasActiveSubscription;
	}

	public void setHasActiveSubscription(Boolean hasActiveSubscription) {
		this.hasActiveSubscription = hasActiveSubscription;
	}

	public AttachToSignalProviderInfo volumeFee(Double volumeFee) {
		this.volumeFee = volumeFee;
		return this;
	}

	/**
	 * Get volumeFee
	 *
	 * @return volumeFee
	 **/
	@ApiModelProperty(value = "")
	public Double getVolumeFee() {
		return volumeFee;
	}

	public void setVolumeFee(Double volumeFee) {
		this.volumeFee = volumeFee;
	}

	public AttachToSignalProviderInfo minDeposit(Double minDeposit) {
		this.minDeposit = minDeposit;
		return this;
	}

	/**
	 * Get minDeposit
	 *
	 * @return minDeposit
	 **/
	@ApiModelProperty(value = "")
	public Double getMinDeposit() {
		return minDeposit;
	}

	public void setMinDeposit(Double minDeposit) {
		this.minDeposit = minDeposit;
	}

	public AttachToSignalProviderInfo minDepositCurrency(MinDepositCurrencyEnum minDepositCurrency) {
		this.minDepositCurrency = minDepositCurrency;
		return this;
	}

	/**
	 * Get minDepositCurrency
	 *
	 * @return minDepositCurrency
	 **/
	@ApiModelProperty(value = "")
	public MinDepositCurrencyEnum getMinDepositCurrency() {
		return minDepositCurrency;
	}

	public void setMinDepositCurrency(MinDepositCurrencyEnum minDepositCurrency) {
		this.minDepositCurrency = minDepositCurrency;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AttachToSignalProviderInfo attachToSignalProviderInfo = (AttachToSignalProviderInfo) o;
		return Objects.equals(this.hasSignalAccount, attachToSignalProviderInfo.hasSignalAccount) &&
				Objects.equals(this.hasActiveSubscription, attachToSignalProviderInfo.hasActiveSubscription) &&
				Objects.equals(this.volumeFee, attachToSignalProviderInfo.volumeFee) &&
				Objects.equals(this.minDeposit, attachToSignalProviderInfo.minDeposit) &&
				Objects.equals(this.minDepositCurrency, attachToSignalProviderInfo.minDepositCurrency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hasSignalAccount, hasActiveSubscription, volumeFee, minDeposit, minDepositCurrency);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AttachToSignalProviderInfo {\n");

		sb.append("    hasSignalAccount: ").append(toIndentedString(hasSignalAccount)).append("\n");
		sb.append("    hasActiveSubscription: ").append(toIndentedString(hasActiveSubscription)).append("\n");
		sb.append("    volumeFee: ").append(toIndentedString(volumeFee)).append("\n");
		sb.append("    minDeposit: ").append(toIndentedString(minDeposit)).append("\n");
		sb.append("    minDepositCurrency: ").append(toIndentedString(minDepositCurrency)).append("\n");
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
	 * Gets or Sets minDepositCurrency
	 */
	@JsonAdapter(MinDepositCurrencyEnum.Adapter.class)
	public enum MinDepositCurrencyEnum
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

		public static MinDepositCurrencyEnum fromValue(String text) {
			for (MinDepositCurrencyEnum b : MinDepositCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		MinDepositCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<MinDepositCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final MinDepositCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public MinDepositCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return MinDepositCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

