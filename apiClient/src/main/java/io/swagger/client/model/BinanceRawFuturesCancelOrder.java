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
 * BinanceRawFuturesCancelOrder
 */


public class BinanceRawFuturesCancelOrder implements Parcelable {
  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("orderId")
  private Long orderId = null;

  @SerializedName("clientOrderId")
  private String clientOrderId = null;

  @SerializedName("price")
  private Double price = null;

  @SerializedName("cumulativeQuantity")
  private Double cumulativeQuantity = null;

  @SerializedName("cumulativeQuoteQuantity")
  private Double cumulativeQuoteQuantity = null;

  @SerializedName("executedQuantity")
  private Double executedQuantity = null;

  @SerializedName("originalQuantity")
  private Double originalQuantity = null;

  @SerializedName("reduceOnly")
  private Boolean reduceOnly = null;

  @SerializedName("closePosition")
  private Boolean closePosition = null;

  @SerializedName("side")
  private BinanceOrderSide side = null;

  @SerializedName("status")
  private BinanceOrderStatus status = null;

  @SerializedName("stopPrice")
  private Double stopPrice = null;

  @SerializedName("timeInForce")
  private BinanceTimeInForce timeInForce = null;

  @SerializedName("originalType")
  private BinanceOrderType originalType = null;

  @SerializedName("type")
  private BinanceOrderType type = null;

  @SerializedName("activatePrice")
  private Double activatePrice = null;

  @SerializedName("priceRate")
  private Double priceRate = null;

  @SerializedName("updateTime")
  private DateTime updateTime = null;

  @SerializedName("workingType")
  private BinanceWorkingType workingType = null;

  @SerializedName("positionSide")
  private BinancePositionSide positionSide = null;

  public BinanceRawFuturesCancelOrder() {
  }
  public BinanceRawFuturesCancelOrder symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * Get symbol
   * @return symbol
  **/
  @Schema(description = "")
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public BinanceRawFuturesCancelOrder orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @Schema(description = "")
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public BinanceRawFuturesCancelOrder clientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

   /**
   * Get clientOrderId
   * @return clientOrderId
  **/
  @Schema(description = "")
  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public BinanceRawFuturesCancelOrder price(Double price) {
    this.price = price;
    return this;
  }

   /**
   * Get price
   * @return price
  **/
  @Schema(description = "")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public BinanceRawFuturesCancelOrder cumulativeQuantity(Double cumulativeQuantity) {
    this.cumulativeQuantity = cumulativeQuantity;
    return this;
  }

   /**
   * Get cumulativeQuantity
   * @return cumulativeQuantity
  **/
  @Schema(description = "")
  public Double getCumulativeQuantity() {
    return cumulativeQuantity;
  }

  public void setCumulativeQuantity(Double cumulativeQuantity) {
    this.cumulativeQuantity = cumulativeQuantity;
  }

  public BinanceRawFuturesCancelOrder cumulativeQuoteQuantity(Double cumulativeQuoteQuantity) {
    this.cumulativeQuoteQuantity = cumulativeQuoteQuantity;
    return this;
  }

   /**
   * Get cumulativeQuoteQuantity
   * @return cumulativeQuoteQuantity
  **/
  @Schema(description = "")
  public Double getCumulativeQuoteQuantity() {
    return cumulativeQuoteQuantity;
  }

  public void setCumulativeQuoteQuantity(Double cumulativeQuoteQuantity) {
    this.cumulativeQuoteQuantity = cumulativeQuoteQuantity;
  }

  public BinanceRawFuturesCancelOrder executedQuantity(Double executedQuantity) {
    this.executedQuantity = executedQuantity;
    return this;
  }

   /**
   * Get executedQuantity
   * @return executedQuantity
  **/
  @Schema(description = "")
  public Double getExecutedQuantity() {
    return executedQuantity;
  }

  public void setExecutedQuantity(Double executedQuantity) {
    this.executedQuantity = executedQuantity;
  }

  public BinanceRawFuturesCancelOrder originalQuantity(Double originalQuantity) {
    this.originalQuantity = originalQuantity;
    return this;
  }

   /**
   * Get originalQuantity
   * @return originalQuantity
  **/
  @Schema(description = "")
  public Double getOriginalQuantity() {
    return originalQuantity;
  }

  public void setOriginalQuantity(Double originalQuantity) {
    this.originalQuantity = originalQuantity;
  }

  public BinanceRawFuturesCancelOrder reduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

   /**
   * Get reduceOnly
   * @return reduceOnly
  **/
  @Schema(description = "")
  public Boolean isReduceOnly() {
    return reduceOnly;
  }

  public void setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
  }

  public BinanceRawFuturesCancelOrder closePosition(Boolean closePosition) {
    this.closePosition = closePosition;
    return this;
  }

   /**
   * Get closePosition
   * @return closePosition
  **/
  @Schema(description = "")
  public Boolean isClosePosition() {
    return closePosition;
  }

  public void setClosePosition(Boolean closePosition) {
    this.closePosition = closePosition;
  }

  public BinanceRawFuturesCancelOrder side(BinanceOrderSide side) {
    this.side = side;
    return this;
  }

   /**
   * Get side
   * @return side
  **/
  @Schema(description = "")
  public BinanceOrderSide getSide() {
    return side;
  }

  public void setSide(BinanceOrderSide side) {
    this.side = side;
  }

  public BinanceRawFuturesCancelOrder status(BinanceOrderStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public BinanceOrderStatus getStatus() {
    return status;
  }

  public void setStatus(BinanceOrderStatus status) {
    this.status = status;
  }

  public BinanceRawFuturesCancelOrder stopPrice(Double stopPrice) {
    this.stopPrice = stopPrice;
    return this;
  }

   /**
   * Get stopPrice
   * @return stopPrice
  **/
  @Schema(description = "")
  public Double getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(Double stopPrice) {
    this.stopPrice = stopPrice;
  }

  public BinanceRawFuturesCancelOrder timeInForce(BinanceTimeInForce timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

   /**
   * Get timeInForce
   * @return timeInForce
  **/
  @Schema(description = "")
  public BinanceTimeInForce getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(BinanceTimeInForce timeInForce) {
    this.timeInForce = timeInForce;
  }

  public BinanceRawFuturesCancelOrder originalType(BinanceOrderType originalType) {
    this.originalType = originalType;
    return this;
  }

   /**
   * Get originalType
   * @return originalType
  **/
  @Schema(description = "")
  public BinanceOrderType getOriginalType() {
    return originalType;
  }

  public void setOriginalType(BinanceOrderType originalType) {
    this.originalType = originalType;
  }

  public BinanceRawFuturesCancelOrder type(BinanceOrderType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public BinanceOrderType getType() {
    return type;
  }

  public void setType(BinanceOrderType type) {
    this.type = type;
  }

  public BinanceRawFuturesCancelOrder activatePrice(Double activatePrice) {
    this.activatePrice = activatePrice;
    return this;
  }

   /**
   * Get activatePrice
   * @return activatePrice
  **/
  @Schema(description = "")
  public Double getActivatePrice() {
    return activatePrice;
  }

  public void setActivatePrice(Double activatePrice) {
    this.activatePrice = activatePrice;
  }

  public BinanceRawFuturesCancelOrder priceRate(Double priceRate) {
    this.priceRate = priceRate;
    return this;
  }

   /**
   * Get priceRate
   * @return priceRate
  **/
  @Schema(description = "")
  public Double getPriceRate() {
    return priceRate;
  }

  public void setPriceRate(Double priceRate) {
    this.priceRate = priceRate;
  }

  public BinanceRawFuturesCancelOrder updateTime(DateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

   /**
   * Get updateTime
   * @return updateTime
  **/
  @Schema(description = "")
  public DateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(DateTime updateTime) {
    this.updateTime = updateTime;
  }

  public BinanceRawFuturesCancelOrder workingType(BinanceWorkingType workingType) {
    this.workingType = workingType;
    return this;
  }

   /**
   * Get workingType
   * @return workingType
  **/
  @Schema(description = "")
  public BinanceWorkingType getWorkingType() {
    return workingType;
  }

  public void setWorkingType(BinanceWorkingType workingType) {
    this.workingType = workingType;
  }

  public BinanceRawFuturesCancelOrder positionSide(BinancePositionSide positionSide) {
    this.positionSide = positionSide;
    return this;
  }

   /**
   * Get positionSide
   * @return positionSide
  **/
  @Schema(description = "")
  public BinancePositionSide getPositionSide() {
    return positionSide;
  }

  public void setPositionSide(BinancePositionSide positionSide) {
    this.positionSide = positionSide;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinanceRawFuturesCancelOrder binanceRawFuturesCancelOrder = (BinanceRawFuturesCancelOrder) o;
    return Objects.equals(this.symbol, binanceRawFuturesCancelOrder.symbol) &&
        Objects.equals(this.orderId, binanceRawFuturesCancelOrder.orderId) &&
        Objects.equals(this.clientOrderId, binanceRawFuturesCancelOrder.clientOrderId) &&
        Objects.equals(this.price, binanceRawFuturesCancelOrder.price) &&
        Objects.equals(this.cumulativeQuantity, binanceRawFuturesCancelOrder.cumulativeQuantity) &&
        Objects.equals(this.cumulativeQuoteQuantity, binanceRawFuturesCancelOrder.cumulativeQuoteQuantity) &&
        Objects.equals(this.executedQuantity, binanceRawFuturesCancelOrder.executedQuantity) &&
        Objects.equals(this.originalQuantity, binanceRawFuturesCancelOrder.originalQuantity) &&
        Objects.equals(this.reduceOnly, binanceRawFuturesCancelOrder.reduceOnly) &&
        Objects.equals(this.closePosition, binanceRawFuturesCancelOrder.closePosition) &&
        Objects.equals(this.side, binanceRawFuturesCancelOrder.side) &&
        Objects.equals(this.status, binanceRawFuturesCancelOrder.status) &&
        Objects.equals(this.stopPrice, binanceRawFuturesCancelOrder.stopPrice) &&
        Objects.equals(this.timeInForce, binanceRawFuturesCancelOrder.timeInForce) &&
        Objects.equals(this.originalType, binanceRawFuturesCancelOrder.originalType) &&
        Objects.equals(this.type, binanceRawFuturesCancelOrder.type) &&
        Objects.equals(this.activatePrice, binanceRawFuturesCancelOrder.activatePrice) &&
        Objects.equals(this.priceRate, binanceRawFuturesCancelOrder.priceRate) &&
        Objects.equals(this.updateTime, binanceRawFuturesCancelOrder.updateTime) &&
        Objects.equals(this.workingType, binanceRawFuturesCancelOrder.workingType) &&
        Objects.equals(this.positionSide, binanceRawFuturesCancelOrder.positionSide);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, orderId, clientOrderId, price, cumulativeQuantity, cumulativeQuoteQuantity, executedQuantity, originalQuantity, reduceOnly, closePosition, side, status, stopPrice, timeInForce, originalType, type, activatePrice, priceRate, updateTime, workingType, positionSide);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawFuturesCancelOrder {\n");
    
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    cumulativeQuantity: ").append(toIndentedString(cumulativeQuantity)).append("\n");
    sb.append("    cumulativeQuoteQuantity: ").append(toIndentedString(cumulativeQuoteQuantity)).append("\n");
    sb.append("    executedQuantity: ").append(toIndentedString(executedQuantity)).append("\n");
    sb.append("    originalQuantity: ").append(toIndentedString(originalQuantity)).append("\n");
    sb.append("    reduceOnly: ").append(toIndentedString(reduceOnly)).append("\n");
    sb.append("    closePosition: ").append(toIndentedString(closePosition)).append("\n");
    sb.append("    side: ").append(toIndentedString(side)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
    sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
    sb.append("    originalType: ").append(toIndentedString(originalType)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    activatePrice: ").append(toIndentedString(activatePrice)).append("\n");
    sb.append("    priceRate: ").append(toIndentedString(priceRate)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
    sb.append("    positionSide: ").append(toIndentedString(positionSide)).append("\n");
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
    out.writeValue(cumulativeQuantity);
    out.writeValue(cumulativeQuoteQuantity);
    out.writeValue(executedQuantity);
    out.writeValue(originalQuantity);
    out.writeValue(reduceOnly);
    out.writeValue(closePosition);
    out.writeValue(side);
    out.writeValue(status);
    out.writeValue(stopPrice);
    out.writeValue(timeInForce);
    out.writeValue(originalType);
    out.writeValue(type);
    out.writeValue(activatePrice);
    out.writeValue(priceRate);
    out.writeValue(updateTime);
    out.writeValue(workingType);
    out.writeValue(positionSide);
  }

  BinanceRawFuturesCancelOrder(Parcel in) {
    symbol = (String)in.readValue(null);
    orderId = (Long)in.readValue(null);
    clientOrderId = (String)in.readValue(null);
    price = (Double)in.readValue(null);
    cumulativeQuantity = (Double)in.readValue(null);
    cumulativeQuoteQuantity = (Double)in.readValue(null);
    executedQuantity = (Double)in.readValue(null);
    originalQuantity = (Double)in.readValue(null);
    reduceOnly = (Boolean)in.readValue(null);
    closePosition = (Boolean)in.readValue(null);
    side = (BinanceOrderSide)in.readValue(BinanceOrderSide.class.getClassLoader());
    status = (BinanceOrderStatus)in.readValue(BinanceOrderStatus.class.getClassLoader());
    stopPrice = (Double)in.readValue(null);
    timeInForce = (BinanceTimeInForce)in.readValue(BinanceTimeInForce.class.getClassLoader());
    originalType = (BinanceOrderType)in.readValue(BinanceOrderType.class.getClassLoader());
    type = (BinanceOrderType)in.readValue(BinanceOrderType.class.getClassLoader());
    activatePrice = (Double)in.readValue(null);
    priceRate = (Double)in.readValue(null);
    updateTime = (DateTime)in.readValue(DateTime.class.getClassLoader());
    workingType = (BinanceWorkingType)in.readValue(BinanceWorkingType.class.getClassLoader());
    positionSide = (BinancePositionSide)in.readValue(BinancePositionSide.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawFuturesCancelOrder> CREATOR = new Parcelable.Creator<BinanceRawFuturesCancelOrder>() {
    public BinanceRawFuturesCancelOrder createFromParcel(Parcel in) {
      return new BinanceRawFuturesCancelOrder(in);
    }
    public BinanceRawFuturesCancelOrder[] newArray(int size) {
      return new BinanceRawFuturesCancelOrder[size];
    }
  };
}
