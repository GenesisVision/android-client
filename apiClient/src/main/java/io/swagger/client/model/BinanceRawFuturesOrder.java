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
 * BinanceRawFuturesOrder
 */


public class BinanceRawFuturesOrder implements Parcelable
{
	@SerializedName("accountId")
	private UUID accountId = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("orderId")
	private Long orderId = null;

	@SerializedName("tradeId")
	private Long tradeId = null;

	@SerializedName("clientOrderId")
	private String clientOrderId = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("avgPrice")
	private Double avgPrice = null;

	@SerializedName("reduceOnly")
	private Boolean reduceOnly = null;

	@SerializedName("closePosition")
	private Boolean closePosition = null;

	@SerializedName("side")
	private BinanceOrderSide side = null;

	@SerializedName("status")
	private BinanceOrderStatus status = null;

	public static final Parcelable.Creator<BinanceRawFuturesOrder> CREATOR = new Parcelable.Creator<BinanceRawFuturesOrder>()
	{
		public BinanceRawFuturesOrder createFromParcel(Parcel in) {
			return new BinanceRawFuturesOrder(in);
		}

		public BinanceRawFuturesOrder[] newArray(int size) {
			return new BinanceRawFuturesOrder[size];
		}
	};

	@SerializedName("stopPrice")
	private Double stopPrice = null;

	@SerializedName("timeInForce")
	private BinanceTimeInForce timeInForce = null;

	@SerializedName("originalType")
	private BinanceOrderType originalType = null;

	@SerializedName("activatePrice")
	private Double activatePrice = null;

	@SerializedName("type")
	private BinanceOrderType type = null;

	@SerializedName("updateTime")
	private DateTime updateTime = null;

	@SerializedName("createdTime")
	private DateTime createdTime = null;

	@SerializedName("positionSide")
	private BinancePositionSide positionSide = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("quantityFilled")
	private Double quantityFilled = null;

	@SerializedName("quoteQuantityFilled")
	private Double quoteQuantityFilled = null;

	@SerializedName("callbackRate")
	private Double callbackRate = null;

	@SerializedName("lastFilledQuantity")
	private Double lastFilledQuantity = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("commissionAsset")
	private String commissionAsset = null;

	@SerializedName("realizedProfit")
	private Double realizedProfit = null;

	@SerializedName("executionType")
	private BinanceExecutionType executionType = null;

	@SerializedName("priceLastFilledTrade")
	private Double priceLastFilledTrade = null;

	public BinanceRawFuturesOrder() {
	}

	@SerializedName("workingType")
	private BinanceWorkingType workingType = null;

	public BinanceRawFuturesOrder accountId(UUID accountId) {
		this.accountId = accountId;
		return this;
	}

	public BinanceRawFuturesOrder symbol(String symbol) {
		this.symbol = symbol;
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

	BinanceRawFuturesOrder(Parcel in) {
		accountId = (UUID) in.readValue(UUID.class.getClassLoader());
		symbol = (String) in.readValue(null);
		orderId = (Long) in.readValue(null);
		tradeId = (Long) in.readValue(null);
		clientOrderId = (String) in.readValue(null);
		price = (Double) in.readValue(null);
		avgPrice = (Double) in.readValue(null);
		reduceOnly = (Boolean) in.readValue(null);
		closePosition = (Boolean) in.readValue(null);
		side = (BinanceOrderSide) in.readValue(BinanceOrderSide.class.getClassLoader());
		status = (BinanceOrderStatus) in.readValue(BinanceOrderStatus.class.getClassLoader());
		stopPrice = (Double) in.readValue(null);
		timeInForce = (BinanceTimeInForce) in.readValue(BinanceTimeInForce.class.getClassLoader());
		originalType = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		type = (BinanceOrderType) in.readValue(BinanceOrderType.class.getClassLoader());
		activatePrice = (Double) in.readValue(null);
		updateTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		createdTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		workingType = (BinanceWorkingType) in.readValue(BinanceWorkingType.class.getClassLoader());
		positionSide = (BinancePositionSide) in.readValue(BinancePositionSide.class.getClassLoader());
		quantity = (Double) in.readValue(null);
		quantityFilled = (Double) in.readValue(null);
		quoteQuantityFilled = (Double) in.readValue(null);
		callbackRate = (Double) in.readValue(null);
		lastFilledQuantity = (Double) in.readValue(null);
		commission = (Double) in.readValue(null);
		commissionAsset = (String) in.readValue(null);
		realizedProfit = (Double) in.readValue(null);
		executionType = (BinanceExecutionType) in.readValue(BinanceExecutionType.class.getClassLoader());
		priceLastFilledTrade = (Double) in.readValue(null);
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BinanceRawFuturesOrder orderId(Long orderId) {
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

	public BinanceRawFuturesOrder tradeId(Long tradeId) {
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

	public BinanceRawFuturesOrder clientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
		return this;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public BinanceRawFuturesOrder price(Double price) {
		this.price = price;
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

	/**
	 * Get clientOrderId
	 *
	 * @return clientOrderId
	 **/
	@Schema(description = "")
	public String getClientOrderId() {
		return clientOrderId;
	}

	public BinanceRawFuturesOrder avgPrice(Double avgPrice) {
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

	public BinanceRawFuturesOrder reduceOnly(Boolean reduceOnly) {
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

  public BinanceRawFuturesOrder closePosition(Boolean closePosition) {
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

  public BinanceRawFuturesOrder side(BinanceOrderSide side) {
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

  public BinanceRawFuturesOrder status(BinanceOrderStatus status) {
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

  public BinanceRawFuturesOrder stopPrice(Double stopPrice) {
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

  public BinanceRawFuturesOrder timeInForce(BinanceTimeInForce timeInForce) {
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

  public BinanceRawFuturesOrder originalType(BinanceOrderType originalType) {
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

  public BinanceRawFuturesOrder type(BinanceOrderType type) {
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

  public BinanceRawFuturesOrder activatePrice(Double activatePrice) {
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

  public BinanceRawFuturesOrder updateTime(DateTime updateTime) {
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

  public BinanceRawFuturesOrder createdTime(DateTime createdTime) {
    this.createdTime = createdTime;
    return this;
  }

   /**
   * Get createdTime
   * @return createdTime
  **/
  @Schema(description = "")
  public DateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(DateTime createdTime) {
    this.createdTime = createdTime;
  }

  public BinanceRawFuturesOrder workingType(BinanceWorkingType workingType) {
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

  public BinanceRawFuturesOrder positionSide(BinancePositionSide positionSide) {
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

  public BinanceRawFuturesOrder quantity(Double quantity) {
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

  public BinanceRawFuturesOrder quantityFilled(Double quantityFilled) {
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

  public BinanceRawFuturesOrder quoteQuantityFilled(Double quoteQuantityFilled) {
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

	public void setCallbackRate(Double callbackRate) {
		this.callbackRate = callbackRate;
	}

	public BinanceRawFuturesOrder lastFilledQuantity(Double lastFilledQuantity) {
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

	public BinanceRawFuturesOrder commission(Double commission) {
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

	public BinanceRawFuturesOrder commissionAsset(String commissionAsset) {
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

	public BinanceRawFuturesOrder realizedProfit(Double realizedProfit) {
		this.realizedProfit = realizedProfit;
		return this;
	}

	/**
	 * Get realizedProfit
	 *
	 * @return realizedProfit
	 **/
	@Schema(description = "")
	public Double getRealizedProfit() {
		return realizedProfit;
	}

	public void setRealizedProfit(Double realizedProfit) {
		this.realizedProfit = realizedProfit;
	}

	public BinanceRawFuturesOrder executionType(BinanceExecutionType executionType) {
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

	public BinanceRawFuturesOrder priceLastFilledTrade(Double priceLastFilledTrade) {
		this.priceLastFilledTrade = priceLastFilledTrade;
		return this;
	}

	/**
	 * Get priceLastFilledTrade
	 *
	 * @return priceLastFilledTrade
	 **/
	@Schema(description = "")
	public Double getPriceLastFilledTrade() {
		return priceLastFilledTrade;
	}

	public void setPriceLastFilledTrade(Double priceLastFilledTrade) {
		this.priceLastFilledTrade = priceLastFilledTrade;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesOrder binanceRawFuturesOrder = (BinanceRawFuturesOrder) o;
		return Objects.equals(this.accountId, binanceRawFuturesOrder.accountId) &&
				Objects.equals(this.symbol, binanceRawFuturesOrder.symbol) &&
				Objects.equals(this.orderId, binanceRawFuturesOrder.orderId) &&
				Objects.equals(this.tradeId, binanceRawFuturesOrder.tradeId) &&
				Objects.equals(this.clientOrderId, binanceRawFuturesOrder.clientOrderId) &&
				Objects.equals(this.price, binanceRawFuturesOrder.price) &&
				Objects.equals(this.avgPrice, binanceRawFuturesOrder.avgPrice) &&
				Objects.equals(this.reduceOnly, binanceRawFuturesOrder.reduceOnly) &&
				Objects.equals(this.closePosition, binanceRawFuturesOrder.closePosition) &&
				Objects.equals(this.side, binanceRawFuturesOrder.side) &&
				Objects.equals(this.status, binanceRawFuturesOrder.status) &&
				Objects.equals(this.stopPrice, binanceRawFuturesOrder.stopPrice) &&
				Objects.equals(this.timeInForce, binanceRawFuturesOrder.timeInForce) &&
				Objects.equals(this.originalType, binanceRawFuturesOrder.originalType) &&
				Objects.equals(this.type, binanceRawFuturesOrder.type) &&
				Objects.equals(this.activatePrice, binanceRawFuturesOrder.activatePrice) &&
				Objects.equals(this.updateTime, binanceRawFuturesOrder.updateTime) &&
				Objects.equals(this.createdTime, binanceRawFuturesOrder.createdTime) &&
				Objects.equals(this.workingType, binanceRawFuturesOrder.workingType) &&
				Objects.equals(this.positionSide, binanceRawFuturesOrder.positionSide) &&
				Objects.equals(this.quantity, binanceRawFuturesOrder.quantity) &&
				Objects.equals(this.quantityFilled, binanceRawFuturesOrder.quantityFilled) &&
				Objects.equals(this.quoteQuantityFilled, binanceRawFuturesOrder.quoteQuantityFilled) &&
				Objects.equals(this.callbackRate, binanceRawFuturesOrder.callbackRate) &&
				Objects.equals(this.lastFilledQuantity, binanceRawFuturesOrder.lastFilledQuantity) &&
				Objects.equals(this.commission, binanceRawFuturesOrder.commission) &&
				Objects.equals(this.commissionAsset, binanceRawFuturesOrder.commissionAsset) &&
				Objects.equals(this.realizedProfit, binanceRawFuturesOrder.realizedProfit) &&
				Objects.equals(this.executionType, binanceRawFuturesOrder.executionType) &&
				Objects.equals(this.priceLastFilledTrade, binanceRawFuturesOrder.priceLastFilledTrade);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, symbol, orderId, tradeId, clientOrderId, price, avgPrice, reduceOnly, closePosition, side, status, stopPrice, timeInForce, originalType, type, activatePrice, updateTime, createdTime, workingType, positionSide, quantity, quantityFilled, quoteQuantityFilled, callbackRate, lastFilledQuantity, commission, commissionAsset, realizedProfit, executionType, priceLastFilledTrade);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesOrder {\n");

		sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
		sb.append("    tradeId: ").append(toIndentedString(tradeId)).append("\n");
		sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
		sb.append("    reduceOnly: ").append(toIndentedString(reduceOnly)).append("\n");
		sb.append("    closePosition: ").append(toIndentedString(closePosition)).append("\n");
		sb.append("    side: ").append(toIndentedString(side)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
		sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
		sb.append("    originalType: ").append(toIndentedString(originalType)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    activatePrice: ").append(toIndentedString(activatePrice)).append("\n");
		sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
		sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
		sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
		sb.append("    positionSide: ").append(toIndentedString(positionSide)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    quantityFilled: ").append(toIndentedString(quantityFilled)).append("\n");
		sb.append("    quoteQuantityFilled: ").append(toIndentedString(quoteQuantityFilled)).append("\n");
		sb.append("    callbackRate: ").append(toIndentedString(callbackRate)).append("\n");
		sb.append("    lastFilledQuantity: ").append(toIndentedString(lastFilledQuantity)).append("\n");
		sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
		sb.append("    commissionAsset: ").append(toIndentedString(commissionAsset)).append("\n");
		sb.append("    realizedProfit: ").append(toIndentedString(realizedProfit)).append("\n");
		sb.append("    executionType: ").append(toIndentedString(executionType)).append("\n");
		sb.append("    priceLastFilledTrade: ").append(toIndentedString(priceLastFilledTrade)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public BinanceRawFuturesOrder callbackRate(Double callbackRate) {
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
		out.writeValue(orderId);
		out.writeValue(tradeId);
		out.writeValue(clientOrderId);
		out.writeValue(price);
		out.writeValue(avgPrice);
		out.writeValue(reduceOnly);
		out.writeValue(closePosition);
		out.writeValue(side);
		out.writeValue(status);
		out.writeValue(stopPrice);
		out.writeValue(timeInForce);
		out.writeValue(originalType);
		out.writeValue(type);
		out.writeValue(activatePrice);
		out.writeValue(updateTime);
		out.writeValue(createdTime);
		out.writeValue(workingType);
		out.writeValue(positionSide);
		out.writeValue(quantity);
		out.writeValue(quantityFilled);
		out.writeValue(quoteQuantityFilled);
		out.writeValue(callbackRate);
		out.writeValue(lastFilledQuantity);
		out.writeValue(commission);
		out.writeValue(commissionAsset);
		out.writeValue(realizedProfit);
		out.writeValue(executionType);
		out.writeValue(priceLastFilledTrade);
	}

	public int describeContents() {
		return 0;
	}
}
