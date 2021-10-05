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
 * BinanceRawPlaceOrder
 */


public class BinanceRawPlaceOrder implements Parcelable {
  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("side")
  private BinanceOrderSide side = null;

  @SerializedName("type")
  private BinanceOrderType type = null;

  @SerializedName("quantity")
  private Double quantity = null;

  @SerializedName("quoteOrderQuantity")
  private Double quoteOrderQuantity = null;

  @SerializedName("newClientOrderId")
  private String newClientOrderId = null;

  @SerializedName("price")
  private Double price = null;

  @SerializedName("timeInForce")
  private BinanceTimeInForce timeInForce = null;

  @SerializedName("stopPrice")
  private Double stopPrice = null;

  @SerializedName("icebergQuantity")
  private Double icebergQuantity = null;

  @SerializedName("orderResponseType")
  private BinanceOrderResponseType orderResponseType = null;

  public BinanceRawPlaceOrder() {
  }
  public BinanceRawPlaceOrder symbol(String symbol) {
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

  public BinanceRawPlaceOrder side(BinanceOrderSide side) {
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

  public BinanceRawPlaceOrder type(BinanceOrderType type) {
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

  public BinanceRawPlaceOrder quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @Schema(description = "")
  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public BinanceRawPlaceOrder quoteOrderQuantity(Double quoteOrderQuantity) {
    this.quoteOrderQuantity = quoteOrderQuantity;
    return this;
  }

   /**
   * Get quoteOrderQuantity
   * @return quoteOrderQuantity
  **/
  @Schema(description = "")
  public Double getQuoteOrderQuantity() {
    return quoteOrderQuantity;
  }

  public void setQuoteOrderQuantity(Double quoteOrderQuantity) {
    this.quoteOrderQuantity = quoteOrderQuantity;
  }

  public BinanceRawPlaceOrder newClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
    return this;
  }

   /**
   * Get newClientOrderId
   * @return newClientOrderId
  **/
  @Schema(description = "")
  public String getNewClientOrderId() {
    return newClientOrderId;
  }

  public void setNewClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
  }

  public BinanceRawPlaceOrder price(Double price) {
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

  public BinanceRawPlaceOrder timeInForce(BinanceTimeInForce timeInForce) {
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

  public BinanceRawPlaceOrder stopPrice(Double stopPrice) {
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

  public BinanceRawPlaceOrder icebergQuantity(Double icebergQuantity) {
    this.icebergQuantity = icebergQuantity;
    return this;
  }

   /**
   * Get icebergQuantity
   * @return icebergQuantity
  **/
  @Schema(description = "")
  public Double getIcebergQuantity() {
    return icebergQuantity;
  }

  public void setIcebergQuantity(Double icebergQuantity) {
    this.icebergQuantity = icebergQuantity;
  }

  public BinanceRawPlaceOrder orderResponseType(BinanceOrderResponseType orderResponseType) {
    this.orderResponseType = orderResponseType;
    return this;
  }

   /**
   * Get orderResponseType
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
    BinanceRawPlaceOrder binanceRawPlaceOrder = (BinanceRawPlaceOrder) o;
    return Objects.equals(this.symbol, binanceRawPlaceOrder.symbol) &&
        Objects.equals(this.side, binanceRawPlaceOrder.side) &&
        Objects.equals(this.type, binanceRawPlaceOrder.type) &&
        Objects.equals(this.quantity, binanceRawPlaceOrder.quantity) &&
        Objects.equals(this.quoteOrderQuantity, binanceRawPlaceOrder.quoteOrderQuantity) &&
        Objects.equals(this.newClientOrderId, binanceRawPlaceOrder.newClientOrderId) &&
        Objects.equals(this.price, binanceRawPlaceOrder.price) &&
        Objects.equals(this.timeInForce, binanceRawPlaceOrder.timeInForce) &&
        Objects.equals(this.stopPrice, binanceRawPlaceOrder.stopPrice) &&
        Objects.equals(this.icebergQuantity, binanceRawPlaceOrder.icebergQuantity) &&
        Objects.equals(this.orderResponseType, binanceRawPlaceOrder.orderResponseType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, side, type, quantity, quoteOrderQuantity, newClientOrderId, price, timeInForce, stopPrice, icebergQuantity, orderResponseType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawPlaceOrder {\n");
    
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    side: ").append(toIndentedString(side)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    quoteOrderQuantity: ").append(toIndentedString(quoteOrderQuantity)).append("\n");
    sb.append("    newClientOrderId: ").append(toIndentedString(newClientOrderId)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
    sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
    sb.append("    icebergQuantity: ").append(toIndentedString(icebergQuantity)).append("\n");
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
    out.writeValue(quoteOrderQuantity);
    out.writeValue(newClientOrderId);
    out.writeValue(price);
    out.writeValue(timeInForce);
    out.writeValue(stopPrice);
    out.writeValue(icebergQuantity);
    out.writeValue(orderResponseType);
  }

  BinanceRawPlaceOrder(Parcel in) {
    symbol = (String)in.readValue(null);
    side = (BinanceOrderSide)in.readValue(BinanceOrderSide.class.getClassLoader());
    type = (BinanceOrderType)in.readValue(BinanceOrderType.class.getClassLoader());
    quantity = (Double)in.readValue(null);
    quoteOrderQuantity = (Double)in.readValue(null);
    newClientOrderId = (String)in.readValue(null);
    price = (Double)in.readValue(null);
    timeInForce = (BinanceTimeInForce)in.readValue(BinanceTimeInForce.class.getClassLoader());
    stopPrice = (Double)in.readValue(null);
    icebergQuantity = (Double)in.readValue(null);
    orderResponseType = (BinanceOrderResponseType)in.readValue(BinanceOrderResponseType.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawPlaceOrder> CREATOR = new Parcelable.Creator<BinanceRawPlaceOrder>() {
    public BinanceRawPlaceOrder createFromParcel(Parcel in) {
      return new BinanceRawPlaceOrder(in);
    }
    public BinanceRawPlaceOrder[] newArray(int size) {
      return new BinanceRawPlaceOrder[size];
    }
  };
}
