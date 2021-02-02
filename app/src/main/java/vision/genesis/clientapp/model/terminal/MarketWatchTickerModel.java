package vision.genesis.clientapp.model.terminal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.swagger.client.model.BinanceRawSymbol;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/01/2021.
 */

public class MarketWatchTickerModel implements Parcelable
{
	public static final Creator<MarketWatchTickerModel> CREATOR = new Creator<MarketWatchTickerModel>()
	{
		@Override
		public MarketWatchTickerModel createFromParcel(Parcel in) {
			return new MarketWatchTickerModel(in);
		}

		@Override
		public MarketWatchTickerModel[] newArray(int size) {
			return new MarketWatchTickerModel[size];
		}
	};

	public static MarketWatchTickerModel from(BinanceRawSymbol symbol) {
		MarketWatchTickerModel model = new MarketWatchTickerModel();
		model.setSymbol(symbol.getName());
		model.setBaseAsset(symbol.getBaseAsset());
		model.setQuoteAsset(symbol.getQuoteAsset());
		return model;
	}

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("base_asset")
	private String baseAsset;

	@SerializedName("quote_asset")
	private String quoteAsset;

	@SerializedName("last_price")
	private Double lastPrice = 0.0;

	@SerializedName("previous_price")
	private Double previousPrice;

	@SerializedName("change")
	private Double change = 0.0;

	@SerializedName("volume")
	private Double volume = 0.0;

	protected MarketWatchTickerModel(Parcel in) {
		symbol = in.readString();
		baseAsset = in.readString();
		quoteAsset = in.readString();
		if (in.readByte() == 0) {
			lastPrice = null;
		}
		else {
			lastPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			previousPrice = null;
		}
		else {
			previousPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			change = null;
		}
		else {
			change = in.readDouble();
		}
		if (in.readByte() == 0) {
			volume = null;
		}
		else {
			volume = in.readDouble();
		}
	}

	public MarketWatchTickerModel() {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(symbol);
		dest.writeString(baseAsset);
		dest.writeString(quoteAsset);
		if (lastPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(lastPrice);
		}
		if (previousPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(previousPrice);
		}
		if (change == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(change);
		}
		if (volume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(volume);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getPreviousPrice() {
		return previousPrice;
	}

	public void setPreviousPrice(Double previousPrice) {
		this.previousPrice = previousPrice;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getBaseAsset() {
		return baseAsset;
	}

	public void setBaseAsset(String baseAsset) {
		this.baseAsset = baseAsset;
	}

	public String getQuoteAsset() {
		return quoteAsset;
	}

	public void setQuoteAsset(String quoteAsset) {
		this.quoteAsset = quoteAsset;
	}
}
