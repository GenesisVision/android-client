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

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * BinanceRawFuturesPlaceOrder
 */


public class BinanceRawFuturesPlaceOrder implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesPlaceOrder> CREATOR = new Parcelable.Creator<BinanceRawFuturesPlaceOrder>()
	{
		public BinanceRawFuturesPlaceOrder createFromParcel(Parcel in) {
			return new BinanceRawFuturesPlaceOrder(in);
		}

		public BinanceRawFuturesPlaceOrder[] newArray(int size) {
			return new BinanceRawFuturesPlaceOrder[size];
		}
	};

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("side")
	private BinanceOrderSide side = null;

	@SerializedName("type")
	private BinanceOrderType type = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("positionSide")
	private BinancePositionSide positionSide = null;

	@SerializedName("timeInForce")
	private BinanceTimeInForce timeInForce = null;

	@SerializedName("reduceOnly")
	private Boolean reduceOnly = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("newClientOrderId")
	private String newClientOrderId = null;

	@SerializedName("stopPrice")
	private Double stopPrice = null;

	@SerializedName("activationPrice")
	private Double activationPrice = null;

	@SerializedName("callbackRate")
	private Double callbackRate = null;

	@SerializedName("workingType")
	private BinanceWorkingType workingType = null;

	@SerializedName("closePosition")
	private Boolean closePosition = null;

	@SerializedName("orderResponseType")
	private BinanceOrderResponseType orderResponseType = null;

	public BinanceRawFuturesPlaceOrder() {
	}

	BinanceRawFuturesPlaceOrder(Parcel in) {
		symbol = (String) in.readValue(null);
		side = (BinanceOrderSide) in.readValue(BinanceOrderSide.class.getClassLoader());
		type = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		quantity = (Double) in.readValue(null);
		positionSide = (BinancePositionSide) in.readValue(BinancePositionSide.class.getClassLoader());
		timeInForce = (BinanceTimeInForce) in.readValue(BinanceTimeInForce.class.getClassLoader());
		reduceOnly = (Boolean) in.readValue(null);
		price = (Double) in.readValue(null);
		newClientOrderId = (String) in.readValue(null);
		stopPrice = (Double) in.readValue(null);
		activationPrice = (Double) in.readValue(null);
		callbackRate = (Double) in.readValue(null);
		workingType = (BinanceWorkingType) in.readValue(BinanceWorkingType.class.getClassLoader());
		closePosition = (Boolean) in.readValue(null);
		orderResponseType = (BinanceOrderResponseType) in.readValue(BinanceOrderResponseType.class.getClassLoader());
	}

	public BinanceRawFuturesPlaceOrder symbol(String symbol) {
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

	public BinanceRawFuturesPlaceOrder side(BinanceOrderSide side) {
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

	public BinanceRawFuturesPlaceOrder type(BinanceOrderType type) {
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

	public BinanceRawFuturesPlaceOrder quantity(Double quantity) {
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

	public BinanceRawFuturesPlaceOrder positionSide(BinancePositionSide positionSide) {
		this.positionSide = positionSide;
		return this;
	}

	/**
	 * Get positionSide
	 *
	 * @return positionSide
	 **/
	@Schema(description = "")
	public BinancePositionSide getPositionSide() {
		return positionSide;
	}

	public void setPositionSide(BinancePositionSide positionSide) {
		this.positionSide = positionSide;
	}

	public BinanceRawFuturesPlaceOrder timeInForce(BinanceTimeInForce timeInForce) {
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

	public BinanceRawFuturesPlaceOrder reduceOnly(Boolean reduceOnly) {
		this.reduceOnly = reduceOnly;
		return this;
	}

	/**
	 * Get reduceOnly
	 *
	 * @return reduceOnly
	 **/
	@Schema(description = "")
	public Boolean isReduceOnly() {
		return reduceOnly;
	}

	public void setReduceOnly(Boolean reduceOnly) {
		this.reduceOnly = reduceOnly;
	}

	public BinanceRawFuturesPlaceOrder price(Double price) {
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

	public BinanceRawFuturesPlaceOrder newClientOrderId(String newClientOrderId) {
		this.newClientOrderId = newClientOrderId;
		return this;
	}

	/**
	 * Get newClientOrderId
	 *
	 * @return newClientOrderId
	 **/
	@Schema(description = "")
	public String getNewClientOrderId() {
		return newClientOrderId;
	}

	public void setNewClientOrderId(String newClientOrderId) {
		this.newClientOrderId = newClientOrderId;
	}

	public BinanceRawFuturesPlaceOrder stopPrice(Double stopPrice) {
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

	public BinanceRawFuturesPlaceOrder activationPrice(Double activationPrice) {
		this.activationPrice = activationPrice;
		return this;
	}

	/**
	 * Get activationPrice
	 *
	 * @return activationPrice
	 **/
	@Schema(description = "")
	public Double getActivationPrice() {
		return activationPrice;
	}

	public void setActivationPrice(Double activationPrice) {
		this.activationPrice = activationPrice;
	}

	public BinanceRawFuturesPlaceOrder callbackRate(Double callbackRate) {
		this.callbackRate = callbackRate;
		return this;
	}

	/**
	 * Get callbackRate
	 *
	 * @return callbackRate
	 **/
	@Schema(description = "")
	public Double getCallbackRate() {
		return callbackRate;
	}

	public void setCallbackRate(Double callbackRate) {
		this.callbackRate = callbackRate;
	}

	public BinanceRawFuturesPlaceOrder workingType(BinanceWorkingType workingType) {
		this.workingType = workingType;
		return this;
	}

	/**
	 * Get workingType
	 *
	 * @return workingType
	 **/
	@Schema(description = "")
	public BinanceWorkingType getWorkingType() {
		return workingType;
	}

	public void setWorkingType(BinanceWorkingType workingType) {
		this.workingType = workingType;
	}

	public BinanceRawFuturesPlaceOrder closePosition(Boolean closePosition) {
		this.closePosition = closePosition;
		return this;
	}

	/**
	 * Get closePosition
	 *
	 * @return closePosition
	 **/
	@Schema(description = "")
	public Boolean isClosePosition() {
		return closePosition;
	}

	public void setClosePosition(Boolean closePosition) {
		this.closePosition = closePosition;
	}

	public BinanceRawFuturesPlaceOrder orderResponseType(BinanceOrderResponseType orderResponseType) {
		this.orderResponseType = orderResponseType;
		return this;
	}

	/**
	 * Get orderResponseType
	 *
	 * @return orderResponseType
	 **/
	@Schema(description = "")
	public BinanceOrderResponseType getOrderResponseType() {
		return orderResponseType;
	}

	public void setOrderResponseType(BinanceOrderResponseType orderResponseType) {
		this.orderResponseType = orderResponseType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesPlaceOrder binanceRawFuturesPlaceOrder = (BinanceRawFuturesPlaceOrder) o;
		return Objects.equals(this.symbol, binanceRawFuturesPlaceOrder.symbol) &&
				Objects.equals(this.side, binanceRawFuturesPlaceOrder.side) &&
				Objects.equals(this.type, binanceRawFuturesPlaceOrder.type) &&
				Objects.equals(this.quantity, binanceRawFuturesPlaceOrder.quantity) &&
				Objects.equals(this.positionSide, binanceRawFuturesPlaceOrder.positionSide) &&
				Objects.equals(this.timeInForce, binanceRawFuturesPlaceOrder.timeInForce) &&
				Objects.equals(this.reduceOnly, binanceRawFuturesPlaceOrder.reduceOnly) &&
				Objects.equals(this.price, binanceRawFuturesPlaceOrder.price) &&
				Objects.equals(this.newClientOrderId, binanceRawFuturesPlaceOrder.newClientOrderId) &&
				Objects.equals(this.stopPrice, binanceRawFuturesPlaceOrder.stopPrice) &&
				Objects.equals(this.activationPrice, binanceRawFuturesPlaceOrder.activationPrice) &&
				Objects.equals(this.callbackRate, binanceRawFuturesPlaceOrder.callbackRate) &&
				Objects.equals(this.workingType, binanceRawFuturesPlaceOrder.workingType) &&
				Objects.equals(this.closePosition, binanceRawFuturesPlaceOrder.closePosition) &&
				Objects.equals(this.orderResponseType, binanceRawFuturesPlaceOrder.orderResponseType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, side, type, quantity, positionSide, timeInForce, reduceOnly, price, newClientOrderId, stopPrice, activationPrice, callbackRate, workingType, closePosition, orderResponseType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesPlaceOrder {\n");

		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    side: ").append(toIndentedString(side)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    positionSide: ").append(toIndentedString(positionSide)).append("\n");
		sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
		sb.append("    reduceOnly: ").append(toIndentedString(reduceOnly)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    newClientOrderId: ").append(toIndentedString(newClientOrderId)).append("\n");
		sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
		sb.append("    activationPrice: ").append(toIndentedString(activationPrice)).append("\n");
		sb.append("    callbackRate: ").append(toIndentedString(callbackRate)).append("\n");
		sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
		sb.append("    closePosition: ").append(toIndentedString(closePosition)).append("\n");
		sb.append("    orderResponseType: ").append(toIndentedString(orderResponseType)).append("\n");
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
		out.writeValue(symbol);
		out.writeValue(side);
		out.writeValue(type);
		out.writeValue(quantity);
		out.writeValue(positionSide);
		out.writeValue(timeInForce);
		out.writeValue(reduceOnly);
		out.writeValue(price);
		out.writeValue(newClientOrderId);
		out.writeValue(stopPrice);
		out.writeValue(activationPrice);
		out.writeValue(callbackRate);
		out.writeValue(workingType);
		out.writeValue(closePosition);
		out.writeValue(orderResponseType);
	}

	public int describeContents() {
		return 0;
	}
}