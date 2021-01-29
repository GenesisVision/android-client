package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import io.swagger.client.model.BinanceRawSymbol;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/01/2021.
 */
public class MiniTickerModel implements Parcelable
{
	public static final Creator<MiniTickerModel> CREATOR = new Creator<MiniTickerModel>()
	{
		@Override
		public MiniTickerModel createFromParcel(Parcel in) {
			return new MiniTickerModel(in);
		}

		@Override
		public MiniTickerModel[] newArray(int size) {
			return new MiniTickerModel[size];
		}
	};

	public static MiniTickerModel from(BinanceRawSymbol symbol) {
		MiniTickerModel model = new MiniTickerModel();

		return model;
	}

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("s")
	private String symbol;

	@SerializedName("c")
	private Double closePrice;

	@SerializedName("o")
	private Double openPrice;

	@SerializedName("h")
	private Double highPrice;

	@SerializedName("l")
	private Double lowPrice;

	@SerializedName("v")
	private Double totalTradedBaseAssetVolume;

	@SerializedName("q")
	private Double totalTradedQuoteAssetVolume;

	public MiniTickerModel() {
	}

	protected MiniTickerModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		symbol = in.readString();
		if (in.readByte() == 0) {
			closePrice = null;
		}
		else {
			closePrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			openPrice = null;
		}
		else {
			openPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			highPrice = null;
		}
		else {
			highPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			lowPrice = null;
		}
		else {
			lowPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			totalTradedBaseAssetVolume = null;
		}
		else {
			totalTradedBaseAssetVolume = in.readDouble();
		}
		if (in.readByte() == 0) {
			totalTradedQuoteAssetVolume = null;
		}
		else {
			totalTradedQuoteAssetVolume = in.readDouble();
		}
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventType);
		dest.writeValue(eventTime);
		dest.writeString(symbol);
		if (closePrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(closePrice);
		}
		if (openPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(openPrice);
		}
		if (highPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(highPrice);
		}
		if (lowPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(lowPrice);
		}
		if (totalTradedBaseAssetVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(totalTradedBaseAssetVolume);
		}
		if (totalTradedQuoteAssetVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(totalTradedQuoteAssetVolume);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
