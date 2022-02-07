package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.BinanceExecutionType;
import io.swagger.client.model.BinanceOrderRejectReason;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderStatus;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinancePositionSide;
import io.swagger.client.model.BinanceTimeInForce;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class FuturesOrderModel implements Parcelable
{
	public static final Creator<FuturesOrderModel> CREATOR = new Creator<FuturesOrderModel>()
	{
		@Override
		public FuturesOrderModel createFromParcel(Parcel in) {
			return new FuturesOrderModel(in);
		}

		@Override
		public FuturesOrderModel[] newArray(int size) {
			return new FuturesOrderModel[size];
		}
	};

	@SerializedName("s")
	private String symbol = null;

	@SerializedName("c")
	private String clientOrderId = null;

	@SerializedName("S")
	private BinanceOrderSide side = null;

	@SerializedName("o")
	private BinanceOrderType type = null;

	@SerializedName("f")
	private BinanceTimeInForce timeInForce = null;

	@SerializedName("q")
	private Double quantity = null;

	@SerializedName("p")
	private Double price = null;

	@SerializedName("sp")
	private Double stopPrice = null;

	@SerializedName("F")
	private Double icebergQuantity = null;

	@SerializedName("C")
	private String originalClientOrderId = null;

	@SerializedName("x")
	private BinanceExecutionType executionType = null;

	@SerializedName("X")
	private BinanceOrderStatus status = null;

	@SerializedName("r")
	private BinanceOrderRejectReason rejectReason = null;

	@SerializedName("i")
	private Long orderId = null;

	@SerializedName("l")
	private Double lastQuantityFilled = null;

	@SerializedName("z")
	private Double quantityFilled = null;

	@SerializedName("L")
	private Double lastPriceFilled = null;

	@SerializedName("n")
	private Double commission = null;

	@SerializedName("N")
	private String commissionAsset = null;

	@SerializedName("T")
	private DateTime updateTime = null;

	@SerializedName("t")
	private Long tradeId = null;

	@SerializedName("O")
	private DateTime createTime = null;

	@SerializedName("Z")
	private Double quoteQuantityFilled = null;

	@SerializedName("Y")
	private Double quoteQuantity = null;

	@SerializedName("Q")
	private Double lastQuoteQuantity = null;

	@SerializedName("R")
	private Boolean reduceOnly = false;

	@SerializedName("ps")
	private BinancePositionSide positionSide = null;

	@SerializedName("accountId")
	private UUID accountId = null;

	@SerializedName("B")
	private List<BinanceSpotAccountBalance> balances = null;

	public FuturesOrderModel() {
	}

	protected FuturesOrderModel(Parcel in) {
		symbol = in.readString();
		clientOrderId = in.readString();
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
			icebergQuantity = null;
		}
		else {
			icebergQuantity = in.readDouble();
		}
		originalClientOrderId = in.readString();
		if (in.readByte() == 0) {
			orderId = null;
		}
		else {
			orderId = in.readLong();
		}
		if (in.readByte() == 0) {
			lastQuantityFilled = null;
		}
		else {
			lastQuantityFilled = in.readDouble();
		}
		if (in.readByte() == 0) {
			quantityFilled = null;
		}
		else {
			quantityFilled = in.readDouble();
		}
		if (in.readByte() == 0) {
			lastPriceFilled = null;
		}
		else {
			lastPriceFilled = in.readDouble();
		}
		if (in.readByte() == 0) {
			commission = null;
		}
		else {
			commission = in.readDouble();
		}
		commissionAsset = in.readString();
		if (in.readByte() == 0) {
			tradeId = null;
		}
		else {
			tradeId = in.readLong();
		}
		if (in.readByte() == 0) {
			quoteQuantityFilled = null;
		}
		else {
			quoteQuantityFilled = in.readDouble();
		}
		if (in.readByte() == 0) {
			quoteQuantity = null;
		}
		else {
			quoteQuantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			lastQuoteQuantity = null;
		}
		else {
			lastQuoteQuantity = in.readDouble();
		}
		reduceOnly = in.readByte() == 1;
		positionSide = (BinancePositionSide) in.readValue(BinancePositionSide.class.getClassLoader());
		accountId = (UUID) in.readValue(DateTime.class.getClassLoader());
		in.readList(balances, BinanceSpotAccountBalance.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(symbol);
		parcel.writeString(clientOrderId);
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
		if (icebergQuantity == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(icebergQuantity);
		}
		parcel.writeString(originalClientOrderId);
		if (orderId == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeLong(orderId);
		}
		if (lastQuantityFilled == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(lastQuantityFilled);
		}
		if (quantityFilled == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(quantityFilled);
		}
		if (lastPriceFilled == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(lastPriceFilled);
		}
		if (commission == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(commission);
		}
		parcel.writeString(commissionAsset);
		if (tradeId == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeLong(tradeId);
		}
		if (quoteQuantityFilled == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(quoteQuantityFilled);
		}
		if (quoteQuantity == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(quoteQuantity);
		}
		if (lastQuoteQuantity == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(lastQuoteQuantity);
		}
		parcel.writeByte(reduceOnly ? (byte) 1 : (byte) 0);
		parcel.writeValue(positionSide);
		parcel.writeValue(accountId);
		parcel.writeList(balances);
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public BinancePositionSide getPositionSide() {
		return positionSide;
	}

	public Long getOrderId() {
		return orderId;
	}

	public BinanceExecutionType getExecutionType() {
		return executionType;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Boolean getReduceOnly() {
		return reduceOnly;
	}

	public Double getQuantityFilled() {
		return quantityFilled;
	}

	public List<BinanceSpotAccountBalance> getBalances() {
		return balances;
	}

	public BinanceOrder toBinanceOrder() {
		BinanceOrder order = new BinanceOrder();
		order.setAccountId(accountId);
		order.setOrderId(orderId);
		order.setClientOrderId(clientOrderId);
		order.setSymbol(symbol);
		order.setSide(side);
		order.setStatus(status);
		order.setQuantity(quantity);
		order.setCreateTime(createTime);
		order.setType(type);
		order.setPrice(price);
		order.setStopPrice(stopPrice);
		order.setQuantityFilled(quantityFilled);
		order.setCommission(commission);
		order.setCommissionAsset(commissionAsset);
		order.setReduceOnly(reduceOnly);
		return order;
	}
}
