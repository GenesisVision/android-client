package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/01/2021.
 */
public class TickerPriceModel implements Parcelable
{
	public static final Creator<TickerPriceModel> CREATOR = new Creator<TickerPriceModel>()
	{
		@Override
		public TickerPriceModel createFromParcel(Parcel in) {
			return new TickerPriceModel(in);
		}

		@Override
		public TickerPriceModel[] newArray(int size) {
			return new TickerPriceModel[size];
		}
	};

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("price")
	private Double price;

	public TickerPriceModel(String symbol, Double price) {
		this.symbol = symbol;
		this.price = price;
	}

	protected TickerPriceModel(Parcel in) {
		symbol = in.readString();
		if (in.readByte() == 0) {
			price = null;
		}
		else {
			price = in.readDouble();
		}
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(symbol);
		if (price == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(price);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getPrice() {
		return price;
	}
}
