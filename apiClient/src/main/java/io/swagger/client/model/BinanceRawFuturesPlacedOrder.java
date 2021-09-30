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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * BinanceRawFuturesPlacedOrder
 */


public class BinanceRawFuturesPlacedOrder implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesPlacedOrder> CREATOR = new Parcelable.Creator<BinanceRawFuturesPlacedOrder>()
	{
		public BinanceRawFuturesPlacedOrder createFromParcel(Parcel in) {
			return new BinanceRawFuturesPlacedOrder(in);
		}

		public BinanceRawFuturesPlacedOrder[] newArray(int size) {
			return new BinanceRawFuturesPlacedOrder[size];
		}
	};

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("orderId")
	private Long orderId = null;

	@SerializedName("clientOrderId")
	private String clientOrderId = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("avgPrice")
	private Double avgPrice = null;

	@SerializedName("reduceOnly")
	private Boolean reduceOnly = null;

	@SerializedName("side")
	private BinanceOrderSide side = null;

	@SerializedName("positionSide")
	private BinancePositionSide positionSide = null;

	@SerializedName("status")
	private BinanceOrderStatus status = null;

	@SerializedName("stopPrice")
	private Double stopPrice = null;

	@SerializedName("closePosition")
	private Boolean closePosition = null;

	@SerializedName("timeInForce")
	private BinanceTimeInForce timeInForce = null;

	@SerializedName("type")
	private BinanceOrderType type = null;

	@SerializedName("originalType")
	private BinanceOrderType originalType = null;

	@SerializedName("activatePrice")
	private Double activatePrice = null;

	@SerializedName("priceRate")
	private Double priceRate = null;

	@SerializedName("updateTime")
	private DateTime updateTime = null;

	@SerializedName("workingType")
	private BinanceWorkingType workingType = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("quantityFilled")
	private Double quantityFilled = null;

	@SerializedName("lastFilledQuantity")
	private Double lastFilledQuantity = null;

	@SerializedName("quoteQuantityFilled")
	private Double quoteQuantityFilled = null;

	public BinanceRawFuturesPlacedOrder() {
	}

	BinanceRawFuturesPlacedOrder(Parcel in) {
		symbol = (String) in.readValue(null);
		orderId = (Long) in.readValue(null);
		clientOrderId = (String) in.readValue(null);
		price = (Double) in.readValue(null);
		avgPrice = (Double) in.readValue(null);
		reduceOnly = (Boolean) in.readValue(null);
		side = (BinanceOrderSide) in.readValue(BinanceOrderSide.class.getClassLoader());
		positionSide = (BinancePositionSide) in.readValue(BinancePositionSide.class.getClassLoader());
		status = (BinanceOrderStatus) in.readValue(BinanceOrderStatus.class.getClassLoader());
		stopPrice = (Double) in.readValue(null);
		closePosition = (Boolean) in.readValue(null);
		timeInForce = (BinanceTimeInForce) in.readValue(BinanceTimeInForce.class.getClassLoader());
		type = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		originalType = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		activatePrice = (Double) in.readValue(null);
		priceRate = (Double) in.readValue(null);
		updateTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		workingType = (BinanceWorkingType) in.readValue(BinanceWorkingType.class.getClassLoader());
		quantity = (Double) in.readValue(null);
		quantityFilled = (Double) in.readValue(null);
		lastFilledQuantity = (Double) in.readValue(null);
		quoteQuantityFilled = (Double) in.readValue(null);
	}

	public BinanceRawFuturesPlacedOrder symbol(String symbol) {
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

	public BinanceRawFuturesPlacedOrder orderId(Long orderId) {
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

	public BinanceRawFuturesPlacedOrder clientOrderId(String clientOrderId) {
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

	public BinanceRawFuturesPlacedOrder price(Double price) {
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

	public BinanceRawFuturesPlacedOrder avgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
		return this;
	}

	/**
	 * Get avgPrice
	 *
	 * @return avgPrice
	 **/
	@Schema(description = "")
	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public BinanceRawFuturesPlacedOrder reduceOnly(Boolean reduceOnly) {
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

	public BinanceRawFuturesPlacedOrder side(BinanceOrderSide side) {
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

	public BinanceRawFuturesPlacedOrder positionSide(BinancePositionSide positionSide) {
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

	public BinanceRawFuturesPlacedOrder status(BinanceOrderStatus status) {
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

	public BinanceRawFuturesPlacedOrder stopPrice(Double stopPrice) {
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

	public BinanceRawFuturesPlacedOrder closePosition(Boolean closePosition) {
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

	public BinanceRawFuturesPlacedOrder timeInForce(BinanceTimeInForce timeInForce) {
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

	public BinanceRawFuturesPlacedOrder type(BinanceOrderType type) {
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

	public BinanceRawFuturesPlacedOrder originalType(BinanceOrderType originalType) {
		this.originalType = originalType;
		return this;
	}

	/**
	 * Get originalType
	 *
	 * @return originalType
	 **/
	@Schema(description = "")
	public BinanceOrderType getOriginalType() {
		return originalType;
	}

	public void setOriginalType(BinanceOrderType originalType) {
		this.originalType = originalType;
	}

	public BinanceRawFuturesPlacedOrder activatePrice(Double activatePrice) {
		this.activatePrice = activatePrice;
		return this;
	}

	/**
	 * Get activatePrice
	 *
	 * @return activatePrice
	 **/
	@Schema(description = "")
	public Double getActivatePrice() {
		return activatePrice;
	}

	public void setActivatePrice(Double activatePrice) {
		this.activatePrice = activatePrice;
	}

	public BinanceRawFuturesPlacedOrder priceRate(Double priceRate) {
		this.priceRate = priceRate;
		return this;
	}

	/**
	 * Get priceRate
	 *
	 * @return priceRate
	 **/
	@Schema(description = "")
	public Double getPriceRate() {
		return priceRate;
	}

	public void setPriceRate(Double priceRate) {
		this.priceRate = priceRate;
	}

	public BinanceRawFuturesPlacedOrder updateTime(DateTime updateTime) {
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

	public BinanceRawFuturesPlacedOrder workingType(BinanceWorkingType workingType) {
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

	public BinanceRawFuturesPlacedOrder quantity(Double quantity) {
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

	public BinanceRawFuturesPlacedOrder quantityFilled(Double quantityFilled) {
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

	public BinanceRawFuturesPlacedOrder lastFilledQuantity(Double lastFilledQuantity) {
		this.lastFilledQuantity = lastFilledQuantity;
		return this;
	}

	/**
	 * Get lastFilledQuantity
	 *
	 * @return lastFilledQuantity
	 **/
	@Schema(description = "")
	public Double getLastFilledQuantity() {
		return lastFilledQuantity;
	}

	public void setLastFilledQuantity(Double lastFilledQuantity) {
		this.lastFilledQuantity = lastFilledQuantity;
	}

	public BinanceRawFuturesPlacedOrder quoteQuantityFilled(Double quoteQuantityFilled) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesPlacedOrder binanceRawFuturesPlacedOrder = (BinanceRawFuturesPlacedOrder) o;
		return Objects.equals(this.symbol, binanceRawFuturesPlacedOrder.symbol) &&
				Objects.equals(this.orderId, binanceRawFuturesPlacedOrder.orderId) &&
				Objects.equals(this.clientOrderId, binanceRawFuturesPlacedOrder.clientOrderId) &&
				Objects.equals(this.price, binanceRawFuturesPlacedOrder.price) &&
				Objects.equals(this.avgPrice, binanceRawFuturesPlacedOrder.avgPrice) &&
				Objects.equals(this.reduceOnly, binanceRawFuturesPlacedOrder.reduceOnly) &&
				Objects.equals(this.side, binanceRawFuturesPlacedOrder.side) &&
				Objects.equals(this.positionSide, binanceRawFuturesPlacedOrder.positionSide) &&
				Objects.equals(this.status, binanceRawFuturesPlacedOrder.status) &&
				Objects.equals(this.stopPrice, binanceRawFuturesPlacedOrder.stopPrice) &&
				Objects.equals(this.closePosition, binanceRawFuturesPlacedOrder.closePosition) &&
				Objects.equals(this.timeInForce, binanceRawFuturesPlacedOrder.timeInForce) &&
				Objects.equals(this.type, binanceRawFuturesPlacedOrder.type) &&
				Objects.equals(this.originalType, binanceRawFuturesPlacedOrder.originalType) &&
				Objects.equals(this.activatePrice, binanceRawFuturesPlacedOrder.activatePrice) &&
				Objects.equals(this.priceRate, binanceRawFuturesPlacedOrder.priceRate) &&
				Objects.equals(this.updateTime, binanceRawFuturesPlacedOrder.updateTime) &&
				Objects.equals(this.workingType, binanceRawFuturesPlacedOrder.workingType) &&
				Objects.equals(this.quantity, binanceRawFuturesPlacedOrder.quantity) &&
				Objects.equals(this.quantityFilled, binanceRawFuturesPlacedOrder.quantityFilled) &&
				Objects.equals(this.lastFilledQuantity, binanceRawFuturesPlacedOrder.lastFilledQuantity) &&
				Objects.equals(this.quoteQuantityFilled, binanceRawFuturesPlacedOrder.quoteQuantityFilled);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, orderId, clientOrderId, price, avgPrice, reduceOnly, side, positionSide, status, stopPrice, closePosition, timeInForce, type, originalType, activatePrice, priceRate, updateTime, workingType, quantity, quantityFilled, lastFilledQuantity, quoteQuantityFilled);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesPlacedOrder {\n");

		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
		sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
		sb.append("    reduceOnly: ").append(toIndentedString(reduceOnly)).append("\n");
		sb.append("    side: ").append(toIndentedString(side)).append("\n");
		sb.append("    positionSide: ").append(toIndentedString(positionSide)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
		sb.append("    closePosition: ").append(toIndentedString(closePosition)).append("\n");
		sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    originalType: ").append(toIndentedString(originalType)).append("\n");
		sb.append("    activatePrice: ").append(toIndentedString(activatePrice)).append("\n");
		sb.append("    priceRate: ").append(toIndentedString(priceRate)).append("\n");
		sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
		sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    quantityFilled: ").append(toIndentedString(quantityFilled)).append("\n");
		sb.append("    lastFilledQuantity: ").append(toIndentedString(lastFilledQuantity)).append("\n");
		sb.append("    quoteQuantityFilled: ").append(toIndentedString(quoteQuantityFilled)).append("\n");
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
		out.writeValue(orderId);
		out.writeValue(clientOrderId);
		out.writeValue(price);
		out.writeValue(avgPrice);
		out.writeValue(reduceOnly);
		out.writeValue(side);
		out.writeValue(positionSide);
		out.writeValue(status);
		out.writeValue(stopPrice);
		out.writeValue(closePosition);
		out.writeValue(timeInForce);
		out.writeValue(type);
		out.writeValue(originalType);
		out.writeValue(activatePrice);
		out.writeValue(priceRate);
		out.writeValue(updateTime);
		out.writeValue(workingType);
		out.writeValue(quantity);
		out.writeValue(quantityFilled);
		out.writeValue(lastFilledQuantity);
		out.writeValue(quoteQuantityFilled);
	}

	public int describeContents() {
		return 0;
	}
}
