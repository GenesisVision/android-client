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

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * OrderSignalModel
 */


public class OrderSignalModel implements Parcelable
{
	public static final Parcelable.Creator<OrderSignalModel> CREATOR = new Parcelable.Creator<OrderSignalModel>()
	{
		public OrderSignalModel createFromParcel(Parcel in) {
			return new OrderSignalModel(in);
		}

		public OrderSignalModel[] newArray(int size) {
			return new OrderSignalModel[size];
		}
	};

	@SerializedName("providers")
	private List<OrderSignalProgramInfo> providers = null;

	@SerializedName("totalCommission")
	private Double totalCommission = null;

	@SerializedName("totalCommissionByType")
	private List<FeeDetails> totalCommissionByType = null;

	@SerializedName("tradingAccountId")
	private UUID tradingAccountId = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("ticket")
	private String ticket = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("volume")
	private Double volume = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("direction")
	private TradeDirectionType direction = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("priceCurrent")
	private Double priceCurrent = null;

	@SerializedName("entry")
	private TradeEntryType entry = null;

	@SerializedName("baseVolume")
	private Double baseVolume = null;

	@SerializedName("originalCommission")
	private Double originalCommission = null;

	@SerializedName("originalCommissionCurrency")
	private String originalCommissionCurrency = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("swap")
	private Double swap = null;

	@SerializedName("showOriginalCommission")
	private Boolean showOriginalCommission = null;

	@SerializedName("signalData")
	private OrderModelSignalData signalData = null;

	@SerializedName("externalSignalAccountId")
	private UUID externalSignalAccountId = null;

	public OrderSignalModel() {
	}

	OrderSignalModel(Parcel in) {
		providers = (List<OrderSignalProgramInfo>) in.readValue(OrderSignalProgramInfo.class.getClassLoader());
		totalCommission = (Double) in.readValue(null);
		totalCommissionByType = (List<FeeDetails>) in.readValue(FeeDetails.class.getClassLoader());
		tradingAccountId = (UUID) in.readValue(UUID.class.getClassLoader());
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		login = (String) in.readValue(null);
		ticket = (String) in.readValue(null);
		symbol = (String) in.readValue(null);
		volume = (Double) in.readValue(null);
		profit = (Double) in.readValue(null);
		direction = (TradeDirectionType) in.readValue(TradeDirectionType.class.getClassLoader());
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		price = (Double) in.readValue(null);
		priceCurrent = (Double) in.readValue(null);
		entry = (TradeEntryType) in.readValue(TradeEntryType.class.getClassLoader());
		baseVolume = (Double) in.readValue(null);
		originalCommission = (Double) in.readValue(null);
		originalCommissionCurrency = (String) in.readValue(null);
		commission = (Double) in.readValue(null);
		swap = (Double) in.readValue(null);
		showOriginalCommission = (Boolean) in.readValue(null);
		signalData = (OrderModelSignalData) in.readValue(OrderModelSignalData.class.getClassLoader());
		externalSignalAccountId = (UUID) in.readValue(UUID.class.getClassLoader());
	}

	public OrderSignalModel providers(List<OrderSignalProgramInfo> providers) {
		this.providers = providers;
		return this;
	}

	public OrderSignalModel addProvidersItem(OrderSignalProgramInfo providersItem) {
		if (this.providers == null) {
			this.providers = new ArrayList<OrderSignalProgramInfo>();
		}
		this.providers.add(providersItem);
		return this;
	}

	/**
	 * Get providers
	 *
	 * @return providers
	 **/
	@Schema(description = "")
	public List<OrderSignalProgramInfo> getProviders() {
		return providers;
	}

	public void setProviders(List<OrderSignalProgramInfo> providers) {
		this.providers = providers;
	}

	public OrderSignalModel totalCommission(Double totalCommission) {
		this.totalCommission = totalCommission;
		return this;
	}

	/**
	 * Get totalCommission
	 *
	 * @return totalCommission
	 **/
	@Schema(description = "")
	public Double getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(Double totalCommission) {
		this.totalCommission = totalCommission;
	}

	public OrderSignalModel totalCommissionByType(List<FeeDetails> totalCommissionByType) {
		this.totalCommissionByType = totalCommissionByType;
		return this;
	}

	public OrderSignalModel addTotalCommissionByTypeItem(FeeDetails totalCommissionByTypeItem) {
		if (this.totalCommissionByType == null) {
			this.totalCommissionByType = new ArrayList<FeeDetails>();
		}
		this.totalCommissionByType.add(totalCommissionByTypeItem);
		return this;
	}

	/**
	 * Get totalCommissionByType
	 *
	 * @return totalCommissionByType
	 **/
	@Schema(description = "")
	public List<FeeDetails> getTotalCommissionByType() {
		return totalCommissionByType;
	}

	public void setTotalCommissionByType(List<FeeDetails> totalCommissionByType) {
		this.totalCommissionByType = totalCommissionByType;
	}

	public OrderSignalModel tradingAccountId(UUID tradingAccountId) {
		this.tradingAccountId = tradingAccountId;
		return this;
	}

	/**
	 * Get tradingAccountId
	 *
	 * @return tradingAccountId
	 **/
	@Schema(description = "")
	public UUID getTradingAccountId() {
		return tradingAccountId;
	}

	public void setTradingAccountId(UUID tradingAccountId) {
		this.tradingAccountId = tradingAccountId;
	}

	public OrderSignalModel currency(Currency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public OrderSignalModel id(UUID id) {
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

	public OrderSignalModel login(String login) {
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

	public OrderSignalModel ticket(String ticket) {
		this.ticket = ticket;
		return this;
	}

	/**
	 * Get ticket
	 *
	 * @return ticket
	 **/
	@Schema(description = "")
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public OrderSignalModel symbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	/**
	 * Get symbol
	 *
	 * @return symbol
	 **/
	@Schema(description = "")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public OrderSignalModel volume(Double volume) {
		this.volume = volume;
		return this;
	}

	/**
	 * Get volume
	 *
	 * @return volume
	 **/
	@Schema(description = "")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public OrderSignalModel profit(Double profit) {
		this.profit = profit;
		return this;
	}

	/**
	 * Get profit
	 *
	 * @return profit
	 **/
	@Schema(description = "")
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public OrderSignalModel direction(TradeDirectionType direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * Get direction
	 *
	 * @return direction
	 **/
	@Schema(description = "")
	public TradeDirectionType getDirection() {
		return direction;
	}

	public void setDirection(TradeDirectionType direction) {
		this.direction = direction;
	}

	public OrderSignalModel date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@Schema(description = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public OrderSignalModel price(Double price) {
		this.price = price;
		return this;
	}

	/**
	 * Get price
	 *
	 * @return price
	 **/
	@Schema(description = "")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderSignalModel priceCurrent(Double priceCurrent) {
		this.priceCurrent = priceCurrent;
		return this;
	}

	/**
	 * Get priceCurrent
	 *
	 * @return priceCurrent
	 **/
	@Schema(description = "")
	public Double getPriceCurrent() {
		return priceCurrent;
	}

	public void setPriceCurrent(Double priceCurrent) {
		this.priceCurrent = priceCurrent;
	}

	public OrderSignalModel entry(TradeEntryType entry) {
		this.entry = entry;
		return this;
	}

	/**
	 * Get entry
	 *
	 * @return entry
	 **/
	@Schema(description = "")
	public TradeEntryType getEntry() {
		return entry;
	}

	public void setEntry(TradeEntryType entry) {
		this.entry = entry;
	}

	public OrderSignalModel baseVolume(Double baseVolume) {
		this.baseVolume = baseVolume;
		return this;
	}

	/**
	 * Volume in account currency. Only filled when trade have zero commission (for paying fees with GVT)
	 *
	 * @return baseVolume
	 **/
	@Schema(description = "Volume in account currency. Only filled when trade have zero commission (for paying fees with GVT)")
	public Double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(Double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public OrderSignalModel originalCommission(Double originalCommission) {
		this.originalCommission = originalCommission;
		return this;
	}

	/**
	 * Huobi: sell - quote currency (right), buy - base symbol currency (left)
	 *
	 * @return originalCommission
	 **/
	@Schema(description = "Huobi: sell - quote currency (right), buy - base symbol currency (left)")
	public Double getOriginalCommission() {
		return originalCommission;
	}

	public void setOriginalCommission(Double originalCommission) {
		this.originalCommission = originalCommission;
	}

	public OrderSignalModel originalCommissionCurrency(String originalCommissionCurrency) {
		this.originalCommissionCurrency = originalCommissionCurrency;
		return this;
	}

	/**
	 * Get originalCommissionCurrency
	 *
	 * @return originalCommissionCurrency
	 **/
	@Schema(description = "")
	public String getOriginalCommissionCurrency() {
		return originalCommissionCurrency;
	}

	public void setOriginalCommissionCurrency(String originalCommissionCurrency) {
		this.originalCommissionCurrency = originalCommissionCurrency;
	}

	public OrderSignalModel commission(Double commission) {
		this.commission = commission;
		return this;
	}

	/**
	 * In account currency
	 *
	 * @return commission
	 **/
	@Schema(description = "In account currency")
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public OrderSignalModel swap(Double swap) {
		this.swap = swap;
		return this;
	}

	/**
	 * Get swap
	 *
	 * @return swap
	 **/
	@Schema(description = "")
	public Double getSwap() {
		return swap;
	}

	public void setSwap(Double swap) {
		this.swap = swap;
	}

	public OrderSignalModel showOriginalCommission(Boolean showOriginalCommission) {
		this.showOriginalCommission = showOriginalCommission;
		return this;
	}

	/**
	 * Get showOriginalCommission
	 *
	 * @return showOriginalCommission
	 **/
	@Schema(description = "")
	public Boolean isShowOriginalCommission() {
		return showOriginalCommission;
	}

	public void setShowOriginalCommission(Boolean showOriginalCommission) {
		this.showOriginalCommission = showOriginalCommission;
	}

	public OrderSignalModel signalData(OrderModelSignalData signalData) {
		this.signalData = signalData;
		return this;
	}

	/**
	 * Get signalData
	 *
	 * @return signalData
	 **/
	@Schema(description = "")
	public OrderModelSignalData getSignalData() {
		return signalData;
	}

	public void setSignalData(OrderModelSignalData signalData) {
		this.signalData = signalData;
	}

	public OrderSignalModel externalSignalAccountId(UUID externalSignalAccountId) {
		this.externalSignalAccountId = externalSignalAccountId;
		return this;
	}

	/**
	 * Get externalSignalAccountId
	 *
	 * @return externalSignalAccountId
	 **/
	@Schema(description = "")
	public UUID getExternalSignalAccountId() {
		return externalSignalAccountId;
	}

	public void setExternalSignalAccountId(UUID externalSignalAccountId) {
		this.externalSignalAccountId = externalSignalAccountId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderSignalModel orderSignalModel = (OrderSignalModel) o;
		return Objects.equals(this.providers, orderSignalModel.providers) &&
				Objects.equals(this.totalCommission, orderSignalModel.totalCommission) &&
				Objects.equals(this.totalCommissionByType, orderSignalModel.totalCommissionByType) &&
				Objects.equals(this.tradingAccountId, orderSignalModel.tradingAccountId) &&
				Objects.equals(this.currency, orderSignalModel.currency) &&
				Objects.equals(this.id, orderSignalModel.id) &&
				Objects.equals(this.login, orderSignalModel.login) &&
				Objects.equals(this.ticket, orderSignalModel.ticket) &&
				Objects.equals(this.symbol, orderSignalModel.symbol) &&
				Objects.equals(this.volume, orderSignalModel.volume) &&
				Objects.equals(this.profit, orderSignalModel.profit) &&
				Objects.equals(this.direction, orderSignalModel.direction) &&
				Objects.equals(this.date, orderSignalModel.date) &&
				Objects.equals(this.price, orderSignalModel.price) &&
				Objects.equals(this.priceCurrent, orderSignalModel.priceCurrent) &&
				Objects.equals(this.entry, orderSignalModel.entry) &&
				Objects.equals(this.baseVolume, orderSignalModel.baseVolume) &&
				Objects.equals(this.originalCommission, orderSignalModel.originalCommission) &&
				Objects.equals(this.originalCommissionCurrency, orderSignalModel.originalCommissionCurrency) &&
				Objects.equals(this.commission, orderSignalModel.commission) &&
				Objects.equals(this.swap, orderSignalModel.swap) &&
				Objects.equals(this.showOriginalCommission, orderSignalModel.showOriginalCommission) &&
				Objects.equals(this.signalData, orderSignalModel.signalData) &&
				Objects.equals(this.externalSignalAccountId, orderSignalModel.externalSignalAccountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(providers, totalCommission, totalCommissionByType, tradingAccountId, currency, id, login, ticket, symbol, volume, profit, direction, date, price, priceCurrent, entry, baseVolume, originalCommission, originalCommissionCurrency, commission, swap, showOriginalCommission, signalData, externalSignalAccountId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OrderSignalModel {\n");

		sb.append("    providers: ").append(toIndentedString(providers)).append("\n");
		sb.append("    totalCommission: ").append(toIndentedString(totalCommission)).append("\n");
		sb.append("    totalCommissionByType: ").append(toIndentedString(totalCommissionByType)).append("\n");
		sb.append("    tradingAccountId: ").append(toIndentedString(tradingAccountId)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    ticket: ").append(toIndentedString(ticket)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    priceCurrent: ").append(toIndentedString(priceCurrent)).append("\n");
		sb.append("    entry: ").append(toIndentedString(entry)).append("\n");
		sb.append("    baseVolume: ").append(toIndentedString(baseVolume)).append("\n");
		sb.append("    originalCommission: ").append(toIndentedString(originalCommission)).append("\n");
		sb.append("    originalCommissionCurrency: ").append(toIndentedString(originalCommissionCurrency)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    swap: ").append(toIndentedString(swap)).append("\n");
		sb.append("    showOriginalCommission: ").append(toIndentedString(showOriginalCommission)).append("\n");
		sb.append("    signalData: ").append(toIndentedString(signalData)).append("\n");
		sb.append("    externalSignalAccountId: ").append(toIndentedString(externalSignalAccountId)).append("\n");
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
		out.writeValue(providers);
		out.writeValue(totalCommission);
		out.writeValue(totalCommissionByType);
		out.writeValue(tradingAccountId);
		out.writeValue(currency);
		out.writeValue(id);
		out.writeValue(login);
		out.writeValue(ticket);
		out.writeValue(symbol);
		out.writeValue(volume);
		out.writeValue(profit);
		out.writeValue(direction);
		out.writeValue(date);
		out.writeValue(price);
		out.writeValue(priceCurrent);
		out.writeValue(entry);
		out.writeValue(baseVolume);
		out.writeValue(originalCommission);
		out.writeValue(originalCommissionCurrency);
		out.writeValue(commission);
		out.writeValue(swap);
		out.writeValue(showOriginalCommission);
		out.writeValue(signalData);
		out.writeValue(externalSignalAccountId);
	}

	public int describeContents() {
		return 0;
	}
}
