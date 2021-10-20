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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderStatus;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinanceRawOrderTrade;
import io.swagger.client.model.BinanceTimeInForce;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * BinanceRawPlacedOrder
 */


public class BinanceRawPlacedOrder implements Parcelable {
  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("orderId")
  private Long orderId = null;

  @SerializedName("orderListId")
  private Long orderListId = null;

  @SerializedName("clientOrderId")
  private String clientOrderId = null;

  @SerializedName("originalClientOrderId")
  private String originalClientOrderId = null;

  @SerializedName("createTime")
  private DateTime createTime = null;

  @SerializedName("price")
  private Double price = null;

  @SerializedName("quantity")
  private Double quantity = null;

  @SerializedName("quantityFilled")
  private Double quantityFilled = null;

  @SerializedName("quoteQuantityFilled")
  private Double quoteQuantityFilled = null;

  @SerializedName("quoteQuantity")
  private Double quoteQuantity = null;

  @SerializedName("status")
  private BinanceOrderStatus status = null;

  @SerializedName("timeInForce")
  private BinanceTimeInForce timeInForce = null;

  @SerializedName("type")
  private BinanceOrderType type = null;

  @SerializedName("side")
  private BinanceOrderSide side = null;

  @SerializedName("fills")
  private List<BinanceRawOrderTrade> fills = null;

  @SerializedName("stopPrice")
  private Double stopPrice = null;

  @SerializedName("marginBuyBorrowAmount")
  private Double marginBuyBorrowAmount = null;

  @SerializedName("marginBuyBorrowAsset")
  private String marginBuyBorrowAsset = null;

  public BinanceRawPlacedOrder() {
  }
  public BinanceRawPlacedOrder symbol(String symbol) {
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

  public BinanceRawPlacedOrder orderId(Long orderId) {
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

  public BinanceRawPlacedOrder orderListId(Long orderListId) {
    this.orderListId = orderListId;
    return this;
  }

   /**
   * Get orderListId
   * @return orderListId
  **/
  @Schema(description = "")
  public Long getOrderListId() {
    return orderListId;
  }

  public void setOrderListId(Long orderListId) {
    this.orderListId = orderListId;
  }

  public BinanceRawPlacedOrder clientOrderId(String clientOrderId) {
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

  public BinanceRawPlacedOrder originalClientOrderId(String originalClientOrderId) {
    this.originalClientOrderId = originalClientOrderId;
    return this;
  }

   /**
   * Get originalClientOrderId
   * @return originalClientOrderId
  **/
  @Schema(description = "")
  public String getOriginalClientOrderId() {
    return originalClientOrderId;
  }

  public void setOriginalClientOrderId(String originalClientOrderId) {
    this.originalClientOrderId = originalClientOrderId;
  }

  public BinanceRawPlacedOrder createTime(DateTime createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * Get createTime
   * @return createTime
  **/
  @Schema(description = "")
  public DateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(DateTime createTime) {
    this.createTime = createTime;
  }

  public BinanceRawPlacedOrder price(Double price) {
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

  public BinanceRawPlacedOrder quantity(Double quantity) {
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

  public BinanceRawPlacedOrder quantityFilled(Double quantityFilled) {
    this.quantityFilled = quantityFilled;
    return this;
  }

   /**
   * Get quantityFilled
   * @return quantityFilled
  **/
  @Schema(description = "")
  public Double getQuantityFilled() {
    return quantityFilled;
  }

  public void setQuantityFilled(Double quantityFilled) {
    this.quantityFilled = quantityFilled;
  }

  public BinanceRawPlacedOrder quoteQuantityFilled(Double quoteQuantityFilled) {
    this.quoteQuantityFilled = quoteQuantityFilled;
    return this;
  }

   /**
   * Get quoteQuantityFilled
   * @return quoteQuantityFilled
  **/
  @Schema(description = "")
  public Double getQuoteQuantityFilled() {
    return quoteQuantityFilled;
  }

  public void setQuoteQuantityFilled(Double quoteQuantityFilled) {
    this.quoteQuantityFilled = quoteQuantityFilled;
  }

  public BinanceRawPlacedOrder quoteQuantity(Double quoteQuantity) {
    this.quoteQuantity = quoteQuantity;
    return this;
  }

   /**
   * Get quoteQuantity
   * @return quoteQuantity
  **/
  @Schema(description = "")
  public Double getQuoteQuantity() {
    return quoteQuantity;
  }

  public void setQuoteQuantity(Double quoteQuantity) {
    this.quoteQuantity = quoteQuantity;
  }

  public BinanceRawPlacedOrder status(BinanceOrderStatus status) {
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

  public BinanceRawPlacedOrder timeInForce(BinanceTimeInForce timeInForce) {
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

  public BinanceRawPlacedOrder type(BinanceOrderType type) {
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

  public BinanceRawPlacedOrder side(BinanceOrderSide side) {
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

  public BinanceRawPlacedOrder fills(List<BinanceRawOrderTrade> fills) {
    this.fills = fills;
    return this;
  }

  public BinanceRawPlacedOrder addFillsItem(BinanceRawOrderTrade fillsItem) {
    if (this.fills == null) {
      this.fills = new ArrayList<BinanceRawOrderTrade>();
    }
    this.fills.add(fillsItem);
    return this;
  }

   /**
   * Get fills
   * @return fills
  **/
  @Schema(description = "")
  public List<BinanceRawOrderTrade> getFills() {
    return fills;
  }

  public void setFills(List<BinanceRawOrderTrade> fills) {
    this.fills = fills;
  }

  public BinanceRawPlacedOrder stopPrice(Double stopPrice) {
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

  public BinanceRawPlacedOrder marginBuyBorrowAmount(Double marginBuyBorrowAmount) {
    this.marginBuyBorrowAmount = marginBuyBorrowAmount;
    return this;
  }

   /**
   * Get marginBuyBorrowAmount
   * @return marginBuyBorrowAmount
  **/
  @Schema(description = "")
  public Double getMarginBuyBorrowAmount() {
    return marginBuyBorrowAmount;
  }

  public void setMarginBuyBorrowAmount(Double marginBuyBorrowAmount) {
    this.marginBuyBorrowAmount = marginBuyBorrowAmount;
  }

  public BinanceRawPlacedOrder marginBuyBorrowAsset(String marginBuyBorrowAsset) {
    this.marginBuyBorrowAsset = marginBuyBorrowAsset;
    return this;
  }

   /**
   * Get marginBuyBorrowAsset
   * @return marginBuyBorrowAsset
  **/
  @Schema(description = "")
  public String getMarginBuyBorrowAsset() {
    return marginBuyBorrowAsset;
  }

  public void setMarginBuyBorrowAsset(String marginBuyBorrowAsset) {
    this.marginBuyBorrowAsset = marginBuyBorrowAsset;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinanceRawPlacedOrder binanceRawPlacedOrder = (BinanceRawPlacedOrder) o;
    return Objects.equals(this.symbol, binanceRawPlacedOrder.symbol) &&
        Objects.equals(this.orderId, binanceRawPlacedOrder.orderId) &&
        Objects.equals(this.orderListId, binanceRawPlacedOrder.orderListId) &&
        Objects.equals(this.clientOrderId, binanceRawPlacedOrder.clientOrderId) &&
        Objects.equals(this.originalClientOrderId, binanceRawPlacedOrder.originalClientOrderId) &&
        Objects.equals(this.createTime, binanceRawPlacedOrder.createTime) &&
        Objects.equals(this.price, binanceRawPlacedOrder.price) &&
        Objects.equals(this.quantity, binanceRawPlacedOrder.quantity) &&
        Objects.equals(this.quantityFilled, binanceRawPlacedOrder.quantityFilled) &&
        Objects.equals(this.quoteQuantityFilled, binanceRawPlacedOrder.quoteQuantityFilled) &&
        Objects.equals(this.quoteQuantity, binanceRawPlacedOrder.quoteQuantity) &&
        Objects.equals(this.status, binanceRawPlacedOrder.status) &&
        Objects.equals(this.timeInForce, binanceRawPlacedOrder.timeInForce) &&
        Objects.equals(this.type, binanceRawPlacedOrder.type) &&
        Objects.equals(this.side, binanceRawPlacedOrder.side) &&
        Objects.equals(this.fills, binanceRawPlacedOrder.fills) &&
        Objects.equals(this.stopPrice, binanceRawPlacedOrder.stopPrice) &&
        Objects.equals(this.marginBuyBorrowAmount, binanceRawPlacedOrder.marginBuyBorrowAmount) &&
        Objects.equals(this.marginBuyBorrowAsset, binanceRawPlacedOrder.marginBuyBorrowAsset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, orderId, orderListId, clientOrderId, originalClientOrderId, createTime, price, quantity, quantityFilled, quoteQuantityFilled, quoteQuantity, status, timeInForce, type, side, fills, stopPrice, marginBuyBorrowAmount, marginBuyBorrowAsset);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawPlacedOrder {\n");
    
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    orderListId: ").append(toIndentedString(orderListId)).append("\n");
    sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
    sb.append("    originalClientOrderId: ").append(toIndentedString(originalClientOrderId)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    quantityFilled: ").append(toIndentedString(quantityFilled)).append("\n");
    sb.append("    quoteQuantityFilled: ").append(toIndentedString(quoteQuantityFilled)).append("\n");
    sb.append("    quoteQuantity: ").append(toIndentedString(quoteQuantity)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    side: ").append(toIndentedString(side)).append("\n");
    sb.append("    fills: ").append(toIndentedString(fills)).append("\n");
    sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
    sb.append("    marginBuyBorrowAmount: ").append(toIndentedString(marginBuyBorrowAmount)).append("\n");
    sb.append("    marginBuyBorrowAsset: ").append(toIndentedString(marginBuyBorrowAsset)).append("\n");
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
    out.writeValue(orderListId);
    out.writeValue(clientOrderId);
    out.writeValue(originalClientOrderId);
    out.writeValue(createTime);
    out.writeValue(price);
    out.writeValue(quantity);
    out.writeValue(quantityFilled);
    out.writeValue(quoteQuantityFilled);
    out.writeValue(quoteQuantity);
    out.writeValue(status);
    out.writeValue(timeInForce);
    out.writeValue(type);
    out.writeValue(side);
    out.writeValue(fills);
    out.writeValue(stopPrice);
    out.writeValue(marginBuyBorrowAmount);
    out.writeValue(marginBuyBorrowAsset);
  }

  BinanceRawPlacedOrder(Parcel in) {
    symbol = (String)in.readValue(null);
    orderId = (Long)in.readValue(null);
    orderListId = (Long)in.readValue(null);
    clientOrderId = (String)in.readValue(null);
    originalClientOrderId = (String)in.readValue(null);
    createTime = (DateTime)in.readValue(DateTime.class.getClassLoader());
    price = (Double)in.readValue(null);
    quantity = (Double)in.readValue(null);
    quantityFilled = (Double)in.readValue(null);
    quoteQuantityFilled = (Double)in.readValue(null);
    quoteQuantity = (Double)in.readValue(null);
    status = (BinanceOrderStatus)in.readValue(BinanceOrderStatus.class.getClassLoader());
    timeInForce = (BinanceTimeInForce)in.readValue(BinanceTimeInForce.class.getClassLoader());
    type = (BinanceOrderType)in.readValue(BinanceOrderType.class.getClassLoader());
    side = (BinanceOrderSide)in.readValue(BinanceOrderSide.class.getClassLoader());
    fills = (List<BinanceRawOrderTrade>)in.readValue(BinanceRawOrderTrade.class.getClassLoader());
    stopPrice = (Double)in.readValue(null);
    marginBuyBorrowAmount = (Double)in.readValue(null);
    marginBuyBorrowAsset = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawPlacedOrder> CREATOR = new Parcelable.Creator<BinanceRawPlacedOrder>() {
    public BinanceRawPlacedOrder createFromParcel(Parcel in) {
      return new BinanceRawPlacedOrder(in);
    }
    public BinanceRawPlacedOrder[] newArray(int size) {
      return new BinanceRawPlacedOrder[size];
    }
  };
}
