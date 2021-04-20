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

import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * BinanceRawOrder
 */


public class BinanceRawOrder implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawOrder> CREATOR = new Parcelable.Creator<BinanceRawOrder>()
	{
		public BinanceRawOrder createFromParcel(Parcel in) {
			return new BinanceRawOrder(in);
		}

		public BinanceRawOrder[] newArray(int size) {
			return new BinanceRawOrder[size];
		}
	};

	@SerializedName("accountId")
	private UUID accountId = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("clientOrderId")
	private String clientOrderId = null;

	@SerializedName("side")
	private BinanceOrderSide side = null;

	@SerializedName("type")
	private BinanceOrderType type = null;

	@SerializedName("timeInForce")
	private BinanceTimeInForce timeInForce = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("stopPrice")
	private Double stopPrice = null;

	@SerializedName("icebergQuantity")
	private Double icebergQuantity = null;

	@SerializedName("originalClientOrderId")
	private String originalClientOrderId = null;

	@SerializedName("executionType")
	private BinanceExecutionType executionType = null;

	@SerializedName("status")
	private BinanceOrderStatus status = null;

	@SerializedName("rejectReason")
	private BinanceOrderRejectReason rejectReason = null;

	@SerializedName("orderId")
	private Long orderId = null;

	@SerializedName("lastQuantityFilled")
	private Double lastQuantityFilled = null;

	@SerializedName("quantityFilled")
	private Double quantityFilled = null;

	@SerializedName("lastPriceFilled")
	private Double lastPriceFilled = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("commissionAsset")
	private String commissionAsset = null;

	@SerializedName("updateTime")
	private DateTime updateTime = null;

	@SerializedName("tradeId")
	private Long tradeId = null;

	@SerializedName("isWorking")
	private Boolean isWorking = null;

	@SerializedName("buyerIsMaker")
	private Boolean buyerIsMaker = null;

	@SerializedName("createTime")
	private DateTime createTime = null;

	@SerializedName("quoteQuantityFilled")
	private Double quoteQuantityFilled = null;

	@SerializedName("quoteQuantity")
	private Double quoteQuantity = null;

	@SerializedName("lastQuoteQuantity")
	private Double lastQuoteQuantity = null;

	@SerializedName("orderListId")
	private Long orderListId = null;

	@SerializedName("pnL")
	private Double pnL = null;

	public BinanceRawOrder() {
	}

	BinanceRawOrder(Parcel in) {
		accountId = (UUID) in.readValue(UUID.class.getClassLoader());
		symbol = (String) in.readValue(null);
		clientOrderId = (String) in.readValue(null);
		side = (BinanceOrderSide) in.readValue(BinanceOrderSide.class.getClassLoader());
		type = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		timeInForce = (BinanceTimeInForce) in.readValue(BinanceTimeInForce.class.getClassLoader());
		quantity = (Double) in.readValue(null);
		price = (Double) in.readValue(null);
		stopPrice = (Double) in.readValue(null);
		icebergQuantity = (Double) in.readValue(null);
		originalClientOrderId = (String) in.readValue(null);
		executionType = (BinanceExecutionType) in.readValue(BinanceExecutionType.class.getClassLoader());
		status = (BinanceOrderStatus) in.readValue(BinanceOrderStatus.class.getClassLoader());
		rejectReason = (BinanceOrderRejectReason) in.readValue(BinanceOrderRejectReason.class.getClassLoader());
		orderId = (Long) in.readValue(null);
		lastQuantityFilled = (Double) in.readValue(null);
		quantityFilled = (Double) in.readValue(null);
		lastPriceFilled = (Double) in.readValue(null);
		commission = (Double) in.readValue(null);
		commissionAsset = (String) in.readValue(null);
		updateTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		tradeId = (Long) in.readValue(null);
		isWorking = (Boolean) in.readValue(null);
		buyerIsMaker = (Boolean) in.readValue(null);
		createTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		quoteQuantityFilled = (Double) in.readValue(null);
		quoteQuantity = (Double) in.readValue(null);
		lastQuoteQuantity = (Double) in.readValue(null);
		orderListId = (Long) in.readValue(null);
		pnL = (Double) in.readValue(null);
	}

	public BinanceRawOrder accountId(UUID accountId) {
		this.accountId = accountId;
		return this;
	}

	/**
	 * Get accountId
	 *
	 * @return accountId
	 **/
	@Schema(description = "")
	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public BinanceRawOrder symbol(String symbol) {
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

	public BinanceRawOrder clientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
		return this;
	}

	/**
	 * Get clientOrderId
	 *
	 * @return clientOrderId
	 **/
	@Schema(description = "")
	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public BinanceRawOrder side(BinanceOrderSide side) {
		this.side = side;
		return this;
	}

	/**
	 * Get side
	 *
	 * @return side
	 **/
	@Schema(description = "")
	public BinanceOrderSide getSide() {
		return side;
	}

	public void setSide(BinanceOrderSide side) {
		this.side = side;
	}

	public BinanceRawOrder type(BinanceOrderType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public BinanceOrderType getType() {
		return type;
	}

	public void setType(BinanceOrderType type) {
		this.type = type;
	}

	public BinanceRawOrder timeInForce(BinanceTimeInForce timeInForce) {
		this.timeInForce = timeInForce;
		return this;
	}

	/**
	 * Get timeInForce
	 *
	 * @return timeInForce
	 **/
	@Schema(description = "")
	public BinanceTimeInForce getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(BinanceTimeInForce timeInForce) {
		this.timeInForce = timeInForce;
	}

	public BinanceRawOrder quantity(Double quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * Get quantity
	 *
	 * @return quantity
	 **/
	@Schema(description = "")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BinanceRawOrder price(Double price) {
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

	public BinanceRawOrder stopPrice(Double stopPrice) {
		this.stopPrice = stopPrice;
		return this;
	}

	/**
	 * Get stopPrice
	 *
	 * @return stopPrice
	 **/
	@Schema(description = "")
	public Double getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(Double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public BinanceRawOrder icebergQuantity(Double icebergQuantity) {
		this.icebergQuantity = icebergQuantity;
		return this;
	}

	/**
	 * Get icebergQuantity
	 *
	 * @return icebergQuantity
	 **/
	@Schema(description = "")
	public Double getIcebergQuantity() {
		return icebergQuantity;
	}

	public void setIcebergQuantity(Double icebergQuantity) {
		this.icebergQuantity = icebergQuantity;
	}

	public BinanceRawOrder originalClientOrderId(String originalClientOrderId) {
		this.originalClientOrderId = originalClientOrderId;
		return this;
	}

	/**
	 * Get originalClientOrderId
	 *
	 * @return originalClientOrderId
	 **/
	@Schema(description = "")
	public String getOriginalClientOrderId() {
		return originalClientOrderId;
	}

	public void setOriginalClientOrderId(String originalClientOrderId) {
		this.originalClientOrderId = originalClientOrderId;
	}

	public BinanceRawOrder executionType(BinanceExecutionType executionType) {
		this.executionType = executionType;
		return this;
	}

	/**
	 * Get executionType
	 *
	 * @return executionType
	 **/
	@Schema(description = "")
	public BinanceExecutionType getExecutionType() {
		return executionType;
	}

	public void setExecutionType(BinanceExecutionType executionType) {
		this.executionType = executionType;
	}

	public BinanceRawOrder status(BinanceOrderStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public BinanceOrderStatus getStatus() {
		return status;
	}

	public void setStatus(BinanceOrderStatus status) {
		this.status = status;
	}

	public BinanceRawOrder rejectReason(BinanceOrderRejectReason rejectReason) {
		this.rejectReason = rejectReason;
		return this;
	}

	/**
	 * Get rejectReason
	 *
	 * @return rejectReason
	 **/
	@Schema(description = "")
	public BinanceOrderRejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(BinanceOrderRejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public BinanceRawOrder orderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	/**
	 * Get orderId
	 *
	 * @return orderId
	 **/
	@Schema(description = "")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BinanceRawOrder lastQuantityFilled(Double lastQuantityFilled) {
		this.lastQuantityFilled = lastQuantityFilled;
		return this;
	}

	/**
	 * Get lastQuantityFilled
	 *
	 * @return lastQuantityFilled
	 **/
	@Schema(description = "")
	public Double getLastQuantityFilled() {
		return lastQuantityFilled;
	}

	public void setLastQuantityFilled(Double lastQuantityFilled) {
		this.lastQuantityFilled = lastQuantityFilled;
	}

	public BinanceRawOrder quantityFilled(Double quantityFilled) {
		this.quantityFilled = quantityFilled;
		return this;
	}

	/**
	 * Get quantityFilled
	 *
	 * @return quantityFilled
	 **/
	@Schema(description = "")
	public Double getQuantityFilled() {
		return quantityFilled;
	}

	public void setQuantityFilled(Double quantityFilled) {
		this.quantityFilled = quantityFilled;
	}

	public BinanceRawOrder lastPriceFilled(Double lastPriceFilled) {
		this.lastPriceFilled = lastPriceFilled;
		return this;
	}

	/**
	 * Get lastPriceFilled
	 *
	 * @return lastPriceFilled
	 **/
	@Schema(description = "")
	public Double getLastPriceFilled() {
		return lastPriceFilled;
	}

	public void setLastPriceFilled(Double lastPriceFilled) {
		this.lastPriceFilled = lastPriceFilled;
	}

	public BinanceRawOrder commission(Double commission) {
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

	public BinanceRawOrder commissionAsset(String commissionAsset) {
		this.commissionAsset = commissionAsset;
		return this;
	}

	/**
	 * Get commissionAsset
	 *
	 * @return commissionAsset
	 **/
	@Schema(description = "")
	public String getCommissionAsset() {
		return commissionAsset;
	}

	public void setCommissionAsset(String commissionAsset) {
		this.commissionAsset = commissionAsset;
	}

	public BinanceRawOrder updateTime(DateTime updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	/**
	 * Get updateTime
	 *
	 * @return updateTime
	 **/
	@Schema(description = "")
	public DateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

	public BinanceRawOrder tradeId(Long tradeId) {
		this.tradeId = tradeId;
		return this;
	}

	/**
	 * Get tradeId
	 *
	 * @return tradeId
	 **/
	@Schema(description = "")
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public BinanceRawOrder isWorking(Boolean isWorking) {
		this.isWorking = isWorking;
		return this;
	}

	/**
	 * Get isWorking
	 *
	 * @return isWorking
	 **/
	@Schema(description = "")
	public Boolean isIsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

	public BinanceRawOrder buyerIsMaker(Boolean buyerIsMaker) {
		this.buyerIsMaker = buyerIsMaker;
		return this;
	}

	/**
	 * Get buyerIsMaker
	 *
	 * @return buyerIsMaker
	 **/
	@Schema(description = "")
	public Boolean isBuyerIsMaker() {
		return buyerIsMaker;
	}

	public void setBuyerIsMaker(Boolean buyerIsMaker) {
		this.buyerIsMaker = buyerIsMaker;
	}

	public BinanceRawOrder createTime(DateTime createTime) {
		this.createTime = createTime;
		return this;
	}

	/**
	 * Get createTime
	 *
	 * @return createTime
	 **/
	@Schema(description = "")
	public DateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	public BinanceRawOrder quoteQuantityFilled(Double quoteQuantityFilled) {
		this.quoteQuantityFilled = quoteQuantityFilled;
		return this;
	}

	/**
	 * Get quoteQuantityFilled
	 *
	 * @return quoteQuantityFilled
	 **/
	@Schema(description = "")
	public Double getQuoteQuantityFilled() {
		return quoteQuantityFilled;
	}

	public void setQuoteQuantityFilled(Double quoteQuantityFilled) {
		this.quoteQuantityFilled = quoteQuantityFilled;
	}

	public BinanceRawOrder quoteQuantity(Double quoteQuantity) {
		this.quoteQuantity = quoteQuantity;
		return this;
	}

	/**
	 * Get quoteQuantity
	 *
	 * @return quoteQuantity
	 **/
	@Schema(description = "")
	public Double getQuoteQuantity() {
		return quoteQuantity;
	}

	public void setQuoteQuantity(Double quoteQuantity) {
		this.quoteQuantity = quoteQuantity;
	}

	public BinanceRawOrder lastQuoteQuantity(Double lastQuoteQuantity) {
		this.lastQuoteQuantity = lastQuoteQuantity;
		return this;
	}

	/**
	 * Get lastQuoteQuantity
	 *
	 * @return lastQuoteQuantity
	 **/
	@Schema(description = "")
	public Double getLastQuoteQuantity() {
		return lastQuoteQuantity;
	}

	public void setLastQuoteQuantity(Double lastQuoteQuantity) {
		this.lastQuoteQuantity = lastQuoteQuantity;
	}

	public BinanceRawOrder orderListId(Long orderListId) {
		this.orderListId = orderListId;
		return this;
	}

	/**
	 * Get orderListId
	 *
	 * @return orderListId
	 **/
	@Schema(description = "")
	public Long getOrderListId() {
		return orderListId;
	}

	public void setOrderListId(Long orderListId) {
		this.orderListId = orderListId;
	}

	public BinanceRawOrder pnL(Double pnL) {
		this.pnL = pnL;
		return this;
	}

	/**
	 * Get pnL
	 *
	 * @return pnL
	 **/
	@Schema(description = "")
	public Double getPnL() {
		return pnL;
	}

	public void setPnL(Double pnL) {
		this.pnL = pnL;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawOrder binanceRawOrder = (BinanceRawOrder) o;
		return Objects.equals(this.accountId, binanceRawOrder.accountId) &&
				Objects.equals(this.symbol, binanceRawOrder.symbol) &&
				Objects.equals(this.clientOrderId, binanceRawOrder.clientOrderId) &&
				Objects.equals(this.side, binanceRawOrder.side) &&
				Objects.equals(this.type, binanceRawOrder.type) &&
				Objects.equals(this.timeInForce, binanceRawOrder.timeInForce) &&
				Objects.equals(this.quantity, binanceRawOrder.quantity) &&
				Objects.equals(this.price, binanceRawOrder.price) &&
				Objects.equals(this.stopPrice, binanceRawOrder.stopPrice) &&
				Objects.equals(this.icebergQuantity, binanceRawOrder.icebergQuantity) &&
				Objects.equals(this.originalClientOrderId, binanceRawOrder.originalClientOrderId) &&
				Objects.equals(this.executionType, binanceRawOrder.executionType) &&
				Objects.equals(this.status, binanceRawOrder.status) &&
				Objects.equals(this.rejectReason, binanceRawOrder.rejectReason) &&
				Objects.equals(this.orderId, binanceRawOrder.orderId) &&
				Objects.equals(this.lastQuantityFilled, binanceRawOrder.lastQuantityFilled) &&
				Objects.equals(this.quantityFilled, binanceRawOrder.quantityFilled) &&
				Objects.equals(this.lastPriceFilled, binanceRawOrder.lastPriceFilled) &&
				Objects.equals(this.commission, binanceRawOrder.commission) &&
				Objects.equals(this.commissionAsset, binanceRawOrder.commissionAsset) &&
				Objects.equals(this.updateTime, binanceRawOrder.updateTime) &&
				Objects.equals(this.tradeId, binanceRawOrder.tradeId) &&
				Objects.equals(this.isWorking, binanceRawOrder.isWorking) &&
				Objects.equals(this.buyerIsMaker, binanceRawOrder.buyerIsMaker) &&
				Objects.equals(this.createTime, binanceRawOrder.createTime) &&
				Objects.equals(this.quoteQuantityFilled, binanceRawOrder.quoteQuantityFilled) &&
				Objects.equals(this.quoteQuantity, binanceRawOrder.quoteQuantity) &&
				Objects.equals(this.lastQuoteQuantity, binanceRawOrder.lastQuoteQuantity) &&
				Objects.equals(this.orderListId, binanceRawOrder.orderListId) &&
				Objects.equals(this.pnL, binanceRawOrder.pnL);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, symbol, clientOrderId, side, type, timeInForce, quantity, price, stopPrice, icebergQuantity, originalClientOrderId, executionType, status, rejectReason, orderId, lastQuantityFilled, quantityFilled, lastPriceFilled, commission, commissionAsset, updateTime, tradeId, isWorking, buyerIsMaker, createTime, quoteQuantityFilled, quoteQuantity, lastQuoteQuantity, orderListId, pnL);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawOrder {\n");

		sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
		sb.append("    side: ").append(toIndentedString(side)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
		sb.append("    icebergQuantity: ").append(toIndentedString(icebergQuantity)).append("\n");
		sb.append("    originalClientOrderId: ").append(toIndentedString(originalClientOrderId)).append("\n");
		sb.append("    executionType: ").append(toIndentedString(executionType)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    rejectReason: ").append(toIndentedString(rejectReason)).append("\n");
		sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
		sb.append("    lastQuantityFilled: ").append(toIndentedString(lastQuantityFilled)).append("\n");
		sb.append("    quantityFilled: ").append(toIndentedString(quantityFilled)).append("\n");
		sb.append("    lastPriceFilled: ").append(toIndentedString(lastPriceFilled)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    commissionAsset: ").append(toIndentedString(commissionAsset)).append("\n");
		sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
		sb.append("    tradeId: ").append(toIndentedString(tradeId)).append("\n");
		sb.append("    isWorking: ").append(toIndentedString(isWorking)).append("\n");
		sb.append("    buyerIsMaker: ").append(toIndentedString(buyerIsMaker)).append("\n");
		sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
		sb.append("    quoteQuantityFilled: ").append(toIndentedString(quoteQuantityFilled)).append("\n");
		sb.append("    quoteQuantity: ").append(toIndentedString(quoteQuantity)).append("\n");
		sb.append("    lastQuoteQuantity: ").append(toIndentedString(lastQuoteQuantity)).append("\n");
		sb.append("    orderListId: ").append(toIndentedString(orderListId)).append("\n");
		sb.append("    pnL: ").append(toIndentedString(pnL)).append("\n");
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
		out.writeValue(accountId);
		out.writeValue(symbol);
		out.writeValue(clientOrderId);
		out.writeValue(side);
		out.writeValue(type);
		out.writeValue(timeInForce);
		out.writeValue(quantity);
		out.writeValue(price);
		out.writeValue(stopPrice);
		out.writeValue(icebergQuantity);
		out.writeValue(originalClientOrderId);
		out.writeValue(executionType);
		out.writeValue(status);
		out.writeValue(rejectReason);
		out.writeValue(orderId);
		out.writeValue(lastQuantityFilled);
		out.writeValue(quantityFilled);
		out.writeValue(lastPriceFilled);
		out.writeValue(commission);
		out.writeValue(commissionAsset);
		out.writeValue(updateTime);
		out.writeValue(tradeId);
		out.writeValue(isWorking);
		out.writeValue(buyerIsMaker);
		out.writeValue(createTime);
		out.writeValue(quoteQuantityFilled);
		out.writeValue(quoteQuantity);
		out.writeValue(lastQuoteQuantity);
		out.writeValue(orderListId);
		out.writeValue(pnL);
	}

	public int describeContents() {
		return 0;
	}
}
