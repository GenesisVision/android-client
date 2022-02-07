package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderStatus;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinanceRawFuturesOrder;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.TradingAccountPermission;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class BinanceOrder implements Parcelable
{
	public static final Creator<BinanceOrder> CREATOR = new Creator<BinanceOrder>()
	{
		@Override
		public BinanceOrder createFromParcel(Parcel in) {
			return new BinanceOrder(in);
		}

		@Override
		public BinanceOrder[] newArray(int size) {
			return new BinanceOrder[size];
		}
	};

	public static BinanceOrder from(BinanceRawOrder model) {
		BinanceOrder order = new BinanceOrder();
		order.setAccountId(model.getAccountId());
		order.setOrderId(model.getOrderId());
		order.setSymbol(model.getSymbol());
		order.setSide(model.getSide());
		order.setStatus(model.getStatus());
		order.setQuantity(model.getQuantity());
		order.setCreateTime(model.getCreateTime());
		order.setType(model.getType());
		order.setPrice(model.getPrice());
		order.setStopPrice(model.getStopPrice());
		order.setQuantityFilled(model.getQuantityFilled());
		order.setCommission(model.getCommission());
		order.setCommissionAsset(model.getCommissionAsset());
		order.setMarket(TradingAccountPermission.SPOT);
		return order;
	}

	public static BinanceOrder from(BinanceRawFuturesOrder model) {
		BinanceOrder order = new BinanceOrder();
		order.setAccountId(model.getAccountId());
		order.setOrderId(model.getOrderId());
		order.setClientOrderId(model.getClientOrderId());
		order.setSymbol(model.getSymbol());
		order.setSide(model.getSide());
		order.setStatus(model.getStatus());
		order.setQuantity(model.getQuantity());
		order.setCreateTime(model.getCreatedTime());
		order.setType(model.getType());
		order.setPrice(model.getPrice());
		order.setStopPrice(model.getStopPrice());
		order.setQuantityFilled(model.getQuantityFilled());
		order.setCommission(model.getCommission());
		order.setCommissionAsset(model.getCommissionAsset());
		order.setReduceOnly(model.isReduceOnly());
		order.setMarket(TradingAccountPermission.FUTURES);
		return order;
	}

	@SerializedName("accountId")
	private UUID accountId = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("side")
	private BinanceOrderSide side = null;

	@SerializedName("type")
	private BinanceOrderType type = null;

	@SerializedName("quantity")
	private Double quantity = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("stopPrice")
	private Double stopPrice = null;

	@SerializedName("status")
	private BinanceOrderStatus status = null;

	@SerializedName("orderId")
	private Long orderId = null;

	@SerializedName("clientOrderId")
	private String clientOrderId = null;

	@SerializedName("quantityFilled")
	private Double quantityFilled = null;

	@SerializedName("commission")
	private Double commission = null;

	@SerializedName("commissionAsset")
	private String commissionAsset = null;

	@SerializedName("createTime")
	private DateTime createTime = null;

	private Boolean reduceOnly = false;

	private TradingAccountPermission market;


	public BinanceOrder() {
	}

	protected BinanceOrder(Parcel in) {
		symbol = in.readString();
		if (in.readByte() == 0) {
			quantity = null;
		}
		else {
			quantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			price = null;
		}
		else {
			price = in.readDouble();
		}
		if (in.readByte() == 0) {
			stopPrice = null;
		}
		else {
			stopPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			orderId = null;
		}
		else {
			orderId = in.readLong();
		}
		clientOrderId = in.readString();
		if (in.readByte() == 0) {
			quantityFilled = null;
		}
		else {
			quantityFilled = in.readDouble();
		}
		if (in.readByte() == 0) {
			commission = null;
		}
		else {
			commission = in.readDouble();
		}
		commissionAsset = in.readString();
		createTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		reduceOnly = in.readByte() == 1;
		market = (TradingAccountPermission) in.readSerializable();
	}

	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BinanceOrderSide getSide() {
		return side;
	}

	public void setSide(BinanceOrderSide side) {
		this.side = side;
	}

	public BinanceOrderType getType() {
		return type;
	}

	public void setType(BinanceOrderType type) {
		this.type = type;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(Double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public BinanceOrderStatus getStatus() {
		return status;
	}

	public void setStatus(BinanceOrderStatus status) {
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getQuantityFilled() {
		return quantityFilled;
	}

	public void setQuantityFilled(Double quantityFilled) {
		this.quantityFilled = quantityFilled;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getCommissionAsset() {
		return commissionAsset;
	}

	public void setCommissionAsset(String commissionAsset) {
		this.commissionAsset = commissionAsset;
	}

	public DateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(symbol);
		if (quantity == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(quantity);
		}
		if (price == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(price);
		}
		if (stopPrice == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(stopPrice);
		}
		if (orderId == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeLong(orderId);
		}
		parcel.writeString(clientOrderId);
		if (quantityFilled == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(quantityFilled);
		}
		if (commission == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(commission);
		}
		parcel.writeString(commissionAsset);
		parcel.writeValue(createTime);
		parcel.writeByte(reduceOnly ? (byte) 1 : (byte) 0);
		parcel.writeSerializable(market);
	}

	public Boolean getReduceOnly() {
		return reduceOnly;
	}

	public void setReduceOnly(Boolean reduceOnly) {
		this.reduceOnly = reduceOnly;
	}

	public TradingAccountPermission getMarket() {
		return market;
	}

	public void setMarket(TradingAccountPermission market) {
		this.market = market;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
}
