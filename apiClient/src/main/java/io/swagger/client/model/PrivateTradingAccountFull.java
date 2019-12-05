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

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * PrivateTradingAccountFull
 */


public class PrivateTradingAccountFull implements Parcelable
{
	public static final Parcelable.Creator<PrivateTradingAccountFull> CREATOR = new Parcelable.Creator<PrivateTradingAccountFull>()
	{
		public PrivateTradingAccountFull createFromParcel(Parcel in) {
			return new PrivateTradingAccountFull(in);
		}

		public PrivateTradingAccountFull[] newArray(int size) {
			return new PrivateTradingAccountFull[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("leverage")
	private Integer leverage = null;

	@SerializedName("apiKey")
	private String apiKey = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("type")
	private PrivateTradingAccountType type = null;

	@SerializedName("status")
	private DashboardTradingAssetStatus status = null;

	@SerializedName("signalSubscriptions")
	private List<SignalSubscription> signalSubscriptions = null;

	@SerializedName("brokerDetails")
	private BrokerDetails brokerDetails = null;

	@SerializedName("ownerActions")
	private PrivateTradingAccountOwnerActions ownerActions = null;

	public PrivateTradingAccountFull() {
	}

	PrivateTradingAccountFull(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		creationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		currency = (CurrencyEnum) in.readValue(null);
		leverage = (Integer) in.readValue(null);
		apiKey = (String) in.readValue(null);
		login = (String) in.readValue(null);
		balance = (Double) in.readValue(null);
		type = (PrivateTradingAccountType) in.readValue(PrivateTradingAccountType.class.getClassLoader());
		status = (DashboardTradingAssetStatus) in.readValue(DashboardTradingAssetStatus.class.getClassLoader());
		signalSubscriptions = (List<SignalSubscription>) in.readValue(SignalSubscription.class.getClassLoader());
		brokerDetails = (BrokerDetails) in.readValue(BrokerDetails.class.getClassLoader());
		ownerActions = (PrivateTradingAccountOwnerActions) in.readValue(PrivateTradingAccountOwnerActions.class.getClassLoader());
	}

	public PrivateTradingAccountFull id(UUID id) {
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

	public PrivateTradingAccountFull creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@Schema(description = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public PrivateTradingAccountFull currency(CurrencyEnum currency) {
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

	public PrivateTradingAccountFull leverage(Integer leverage) {
		this.leverage = leverage;
		return this;
	}

	/**
	 * Get leverage
	 *
	 * @return leverage
	 **/
	@Schema(description = "")
	public Integer getLeverage() {
		return leverage;
	}

	public void setLeverage(Integer leverage) {
		this.leverage = leverage;
	}

	public PrivateTradingAccountFull apiKey(String apiKey) {
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

	public PrivateTradingAccountFull login(String login) {
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

	public PrivateTradingAccountFull balance(Double balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 **/
	@Schema(description = "")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public PrivateTradingAccountFull type(PrivateTradingAccountType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public PrivateTradingAccountType getType() {
		return type;
	}

	public void setType(PrivateTradingAccountType type) {
		this.type = type;
	}

	public PrivateTradingAccountFull status(DashboardTradingAssetStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public DashboardTradingAssetStatus getStatus() {
		return status;
	}

	public void setStatus(DashboardTradingAssetStatus status) {
		this.status = status;
	}

	public PrivateTradingAccountFull signalSubscriptions(List<SignalSubscription> signalSubscriptions) {
		this.signalSubscriptions = signalSubscriptions;
		return this;
	}

	public PrivateTradingAccountFull addSignalSubscriptionsItem(SignalSubscription signalSubscriptionsItem) {
		if (this.signalSubscriptions == null) {
			this.signalSubscriptions = new ArrayList<SignalSubscription>();
		}
		this.signalSubscriptions.add(signalSubscriptionsItem);
		return this;
	}

	/**
	 * Get signalSubscriptions
	 *
	 * @return signalSubscriptions
	 **/
	@Schema(description = "")
	public List<SignalSubscription> getSignalSubscriptions() {
		return signalSubscriptions;
	}

	public void setSignalSubscriptions(List<SignalSubscription> signalSubscriptions) {
		this.signalSubscriptions = signalSubscriptions;
	}

	public PrivateTradingAccountFull brokerDetails(BrokerDetails brokerDetails) {
		this.brokerDetails = brokerDetails;
		return this;
	}

	/**
	 * Get brokerDetails
	 *
	 * @return brokerDetails
	 **/
	@Schema(description = "")
	public BrokerDetails getBrokerDetails() {
		return brokerDetails;
	}

	public void setBrokerDetails(BrokerDetails brokerDetails) {
		this.brokerDetails = brokerDetails;
	}

	public PrivateTradingAccountFull ownerActions(PrivateTradingAccountOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
		return this;
	}

	/**
	 * Get ownerActions
	 *
	 * @return ownerActions
	 **/
	@Schema(description = "")
	public PrivateTradingAccountOwnerActions getOwnerActions() {
		return ownerActions;
	}

	public void setOwnerActions(PrivateTradingAccountOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PrivateTradingAccountFull privateTradingAccountFull = (PrivateTradingAccountFull) o;
		return Objects.equals(this.id, privateTradingAccountFull.id) &&
				Objects.equals(this.creationDate, privateTradingAccountFull.creationDate) &&
				Objects.equals(this.currency, privateTradingAccountFull.currency) &&
				Objects.equals(this.leverage, privateTradingAccountFull.leverage) &&
				Objects.equals(this.apiKey, privateTradingAccountFull.apiKey) &&
				Objects.equals(this.login, privateTradingAccountFull.login) &&
				Objects.equals(this.balance, privateTradingAccountFull.balance) &&
				Objects.equals(this.type, privateTradingAccountFull.type) &&
				Objects.equals(this.status, privateTradingAccountFull.status) &&
				Objects.equals(this.signalSubscriptions, privateTradingAccountFull.signalSubscriptions) &&
				Objects.equals(this.brokerDetails, privateTradingAccountFull.brokerDetails) &&
				Objects.equals(this.ownerActions, privateTradingAccountFull.ownerActions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, creationDate, currency, leverage, apiKey, login, balance, type, status, signalSubscriptions, brokerDetails, ownerActions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PrivateTradingAccountFull {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    leverage: ").append(toIndentedString(leverage)).append("\n");
		sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    signalSubscriptions: ").append(toIndentedString(signalSubscriptions)).append("\n");
		sb.append("    brokerDetails: ").append(toIndentedString(brokerDetails)).append("\n");
		sb.append("    ownerActions: ").append(toIndentedString(ownerActions)).append("\n");
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
		out.writeValue(creationDate);
		out.writeValue(currency);
		out.writeValue(leverage);
		out.writeValue(apiKey);
		out.writeValue(login);
		out.writeValue(balance);
		out.writeValue(type);
		out.writeValue(status);
		out.writeValue(signalSubscriptions);
		out.writeValue(brokerDetails);
		out.writeValue(ownerActions);
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
