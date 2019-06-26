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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * BrokerAccountType
 */

public class BrokerAccountType
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("leverages")
	private List<Integer> leverages = null;

	@SerializedName("currencies")
	private List<String> currencies = null;

	@SerializedName("minimumDepositsAmount")
	private Map<String, Double> minimumDepositsAmount = null;

	@SerializedName("isForex")
	private Boolean isForex = null;

	@SerializedName("isSignalsAvailable")
	private Boolean isSignalsAvailable = null;

	public BrokerAccountType id(UUID id) {
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

	public BrokerAccountType name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@ApiModelProperty(value = "")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrokerAccountType description(String description) {
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

	public BrokerAccountType type(TypeEnum type) {
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

	public BrokerAccountType leverages(List<Integer> leverages) {
		this.leverages = leverages;
		return this;
	}

	public BrokerAccountType addLeveragesItem(Integer leveragesItem) {
		if (this.leverages == null) {
			this.leverages = new ArrayList<Integer>();
		}
		this.leverages.add(leveragesItem);
		return this;
	}

	/**
	 * Get leverages
	 *
	 * @return leverages
	 **/
	@ApiModelProperty(value = "")
	public List<Integer> getLeverages() {
		return leverages;
	}

	public void setLeverages(List<Integer> leverages) {
		this.leverages = leverages;
	}

	public BrokerAccountType currencies(List<String> currencies) {
		this.currencies = currencies;
		return this;
	}

	public BrokerAccountType addCurrenciesItem(String currenciesItem) {
		if (this.currencies == null) {
			this.currencies = new ArrayList<String>();
		}
		this.currencies.add(currenciesItem);
		return this;
	}

	/**
	 * Get currencies
	 *
	 * @return currencies
	 **/
	@ApiModelProperty(value = "")
	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

	public BrokerAccountType minimumDepositsAmount(Map<String, Double> minimumDepositsAmount) {
		this.minimumDepositsAmount = minimumDepositsAmount;
		return this;
	}

	public BrokerAccountType putMinimumDepositsAmountItem(String key, Double minimumDepositsAmountItem) {
		if (this.minimumDepositsAmount == null) {
			this.minimumDepositsAmount = new HashMap<String, Double>();
		}
		this.minimumDepositsAmount.put(key, minimumDepositsAmountItem);
		return this;
	}

	/**
	 * Get minimumDepositsAmount
	 *
	 * @return minimumDepositsAmount
	 **/
	@ApiModelProperty(value = "")
	public Map<String, Double> getMinimumDepositsAmount() {
		return minimumDepositsAmount;
	}

	public void setMinimumDepositsAmount(Map<String, Double> minimumDepositsAmount) {
		this.minimumDepositsAmount = minimumDepositsAmount;
	}

	public BrokerAccountType isForex(Boolean isForex) {
		this.isForex = isForex;
		return this;
	}

	/**
	 * Get isForex
	 *
	 * @return isForex
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsForex() {
		return isForex;
	}

	public void setIsForex(Boolean isForex) {
		this.isForex = isForex;
	}

	public BrokerAccountType isSignalsAvailable(Boolean isSignalsAvailable) {
		this.isSignalsAvailable = isSignalsAvailable;
		return this;
	}

	/**
	 * Get isSignalsAvailable
	 *
	 * @return isSignalsAvailable
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsSignalsAvailable() {
		return isSignalsAvailable;
	}

	public void setIsSignalsAvailable(Boolean isSignalsAvailable) {
		this.isSignalsAvailable = isSignalsAvailable;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BrokerAccountType brokerAccountType = (BrokerAccountType) o;
		return Objects.equals(this.id, brokerAccountType.id) &&
				Objects.equals(this.name, brokerAccountType.name) &&
				Objects.equals(this.description, brokerAccountType.description) &&
				Objects.equals(this.type, brokerAccountType.type) &&
				Objects.equals(this.leverages, brokerAccountType.leverages) &&
				Objects.equals(this.currencies, brokerAccountType.currencies) &&
				Objects.equals(this.minimumDepositsAmount, brokerAccountType.minimumDepositsAmount) &&
				Objects.equals(this.isForex, brokerAccountType.isForex) &&
				Objects.equals(this.isSignalsAvailable, brokerAccountType.isSignalsAvailable);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, type, leverages, currencies, minimumDepositsAmount, isForex, isSignalsAvailable);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BrokerAccountType {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    leverages: ").append(toIndentedString(leverages)).append("\n");
		sb.append("    currencies: ").append(toIndentedString(currencies)).append("\n");
		sb.append("    minimumDepositsAmount: ").append(toIndentedString(minimumDepositsAmount)).append("\n");
		sb.append("    isForex: ").append(toIndentedString(isForex)).append("\n");
		sb.append("    isSignalsAvailable: ").append(toIndentedString(isSignalsAvailable)).append("\n");
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
		UNDEFINED("Undefined"),

		METATRADER4("MetaTrader4"),

		METATRADER5("MetaTrader5"),

		NINJATRADER("NinjaTrader"),

		CTRADER("cTrader"),

		RUMUS("Rumus"),

		METASTOCK("Metastock"),

		IDEX("IDEX"),

		HUOBI("Huobi");

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

}

