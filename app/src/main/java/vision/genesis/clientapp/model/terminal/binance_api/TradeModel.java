package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import vision.genesis.clientapp.model.terminal.binance_socket.NewTradeModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */
public class TradeModel implements Parcelable
{
	public static final Creator<TradeModel> CREATOR = new Creator<TradeModel>()
	{
		@Override
		public TradeModel createFromParcel(Parcel in) {
			return new TradeModel(in);
		}

		@Override
		public TradeModel[] newArray(int size) {
			return new TradeModel[size];
		}
	};

	public static TradeModel from(NewTradeModel newTrade) {
		return new TradeModel(newTrade.getTradeId(), newTrade.getPrice(), newTrade.getQuantity(), newTrade.getTradeTime());
	}

	@SerializedName("id")
	private Long id;

	@SerializedName("price")
	private Double price;

	@SerializedName("qty")
	private Double quantity;

	@SerializedName("quoteQty")
	private Double quoteQuantity;

	@SerializedName("time")
	private Long time;

	@SerializedName("isBuyerMaker")
	private Boolean isBuyerMaker;

	@SerializedName("isBestMatch")
	private Boolean isBestMatch;

	public TradeModel(Long id, Double price, Double quantity, DateTime time) {
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.time = time.getMillis();
	}

	protected TradeModel(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		}
		else {
			id = in.readLong();
		}
		if (in.readByte() == 0) {
			price = null;
		}
		else {
			price = in.readDouble();
		}
		if (in.readByte() == 0) {
			quantity = null;
		}
		else {
			quantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			quoteQuantity = null;
		}
		else {
			quoteQuantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			time = null;
		}
		else {
			time = in.readLong();
		}
		byte tmpIsBuyerMaker = in.readByte();
		isBuyerMaker = tmpIsBuyerMaker == 0 ? null : tmpIsBuyerMaker == 1;
		byte tmpIsBestMatch = in.readByte();
		isBestMatch = tmpIsBestMatch == 0 ? null : tmpIsBestMatch == 1;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (id == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(id);
		}
		if (price == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(price);
		}
		if (quantity == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(quantity);
		}
		if (quoteQuantity == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(quoteQuantity);
		}
		if (time == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(time);
		}
		dest.writeByte((byte) (isBuyerMaker == null ? 0 : isBuyerMaker ? 1 : 2));
		dest.writeByte((byte) (isBestMatch == null ? 0 : isBestMatch ? 1 : 2));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Double getQuoteQuantity() {
		return quoteQuantity;
	}

	public DateTime getTime() {
		return new DateTime(time);
	}

	public Boolean getBuyerMaker() {
		return isBuyerMaker;
	}

	public Boolean getBestMatch() {
		return isBestMatch;
	}
}
