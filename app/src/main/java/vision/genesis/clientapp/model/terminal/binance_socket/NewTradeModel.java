package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */
public class NewTradeModel implements Parcelable
{
	public static final Creator<NewTradeModel> CREATOR = new Creator<NewTradeModel>()
	{
		@Override
		public NewTradeModel createFromParcel(Parcel in) {
			return new NewTradeModel(in);
		}

		@Override
		public NewTradeModel[] newArray(int size) {
			return new NewTradeModel[size];
		}
	};

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("s")
	private String symbol;

	@SerializedName("t")
	private Long tradeId;

	@SerializedName("p")
	private Double price;

	@SerializedName("q")
	private Double quantity;

	@SerializedName("b")
	private Long buyerOrderId;

	@SerializedName("a")
	private Long sellerOrderId;

	@SerializedName("T")
	private DateTime tradeTime;

	public NewTradeModel() {
	}

	protected NewTradeModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		symbol = in.readString();
		if (in.readByte() == 0) {
			tradeId = null;
		}
		else {
			tradeId = in.readLong();
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
			buyerOrderId = null;
		}
		else {
			buyerOrderId = in.readLong();
		}
		if (in.readByte() == 0) {
			sellerOrderId = null;
		}
		else {
			sellerOrderId = in.readLong();
		}
		tradeTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventType);
		dest.writeValue(eventTime);
		dest.writeString(symbol);
		if (tradeId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(tradeId);
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
		if (buyerOrderId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(buyerOrderId);
		}
		if (sellerOrderId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(sellerOrderId);
		}
		dest.writeValue(tradeTime);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getEventType() {
		return eventType;
	}

	public DateTime getEventTime() {
		return eventTime;
	}

	public String getSymbol() {
		return symbol;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public Double getPrice() {
		return price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Long getBuyerOrderId() {
		return buyerOrderId;
	}

	public Long getSellerOrderId() {
		return sellerOrderId;
	}

	public DateTime getTradeTime() {
		return tradeTime;
	}
}
