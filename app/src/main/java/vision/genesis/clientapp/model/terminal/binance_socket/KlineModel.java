package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import io.swagger.client.model.BinanceRawKline;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/01/2021.
 */

public class KlineModel implements Parcelable
{
	public static final Creator<KlineModel> CREATOR = new Creator<KlineModel>()
	{
		@Override
		public KlineModel createFromParcel(Parcel in) {
			return new KlineModel(in);
		}

		@Override
		public KlineModel[] newArray(int size) {
			return new KlineModel[size];
		}
	};

	public static KlineModel fromBinanceRawKline(BinanceRawKline item) {
		return new KlineModel(item.getOpenTime(), item.getCloseTime(),
				item.getOpen(), item.getClose(),
				item.getHigh(), item.getLow(),
				item.getBaseVolume(), item.getQuoteVolume(),
				item.getTradeCount(), item.getTakerBuyBaseVolume(), item.getTakerBuyQuoteVolume());
	}

	@SerializedName("t")
	private DateTime openTime = null;

	@SerializedName("T")
	private DateTime closeTime = null;

	@SerializedName("o")
	private Double open = null;

	@SerializedName("c")
	private Double close = null;

	@SerializedName("h")
	private Double high = null;

	@SerializedName("l")
	private Double low = null;

	@SerializedName("v")
	private Double baseVolume = null;

	@SerializedName("q")
	private Double quoteVolume = null;

	@SerializedName("n")
	private Integer tradeCount = null;

	@SerializedName("V")
	private Double takerBuyBaseVolume = null;

	@SerializedName("Q")
	private Double takerBuyQuoteVolume = null;

	public KlineModel() {
	}

	protected KlineModel(Parcel in) {
		openTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		closeTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		if (in.readByte() == 0) {
			open = null;
		}
		else {
			open = in.readDouble();
		}
		if (in.readByte() == 0) {
			close = null;
		}
		else {
			close = in.readDouble();
		}
		if (in.readByte() == 0) {
			high = null;
		}
		else {
			high = in.readDouble();
		}
		if (in.readByte() == 0) {
			low = null;
		}
		else {
			low = in.readDouble();
		}
		if (in.readByte() == 0) {
			baseVolume = null;
		}
		else {
			baseVolume = in.readDouble();
		}
		if (in.readByte() == 0) {
			quoteVolume = null;
		}
		else {
			quoteVolume = in.readDouble();
		}
		if (in.readByte() == 0) {
			tradeCount = null;
		}
		else {
			tradeCount = in.readInt();
		}
		if (in.readByte() == 0) {
			takerBuyBaseVolume = null;
		}
		else {
			takerBuyBaseVolume = in.readDouble();
		}
		if (in.readByte() == 0) {
			takerBuyQuoteVolume = null;
		}
		else {
			takerBuyQuoteVolume = in.readDouble();
		}
	}

	public KlineModel(DateTime openTime, DateTime closeTime,
	                  Double open, Double close,
	                  Double high, Double low,
	                  Double baseVolume, Double quoteVolume,
	                  Integer tradeCount, Double takerBuyBaseVolume, Double takerBuyQuoteVolume) {
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.baseVolume = baseVolume;
		this.quoteVolume = quoteVolume;
		this.tradeCount = tradeCount;
		this.takerBuyBaseVolume = takerBuyBaseVolume;
		this.takerBuyQuoteVolume = takerBuyQuoteVolume;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(openTime);
		dest.writeValue(closeTime);
		if (open == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(open);
		}
		if (close == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(close);
		}
		if (high == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(high);
		}
		if (low == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(low);
		}
		if (baseVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(baseVolume);
		}
		if (quoteVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(quoteVolume);
		}
		if (tradeCount == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(tradeCount);
		}
		if (takerBuyBaseVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(takerBuyBaseVolume);
		}
		if (takerBuyQuoteVolume == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(takerBuyQuoteVolume);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public DateTime getOpenTime() {
		return openTime;
	}

	public DateTime getCloseTime() {
		return closeTime;
	}

	public Double getOpen() {
		return open;
	}

	public Double getClose() {
		return close;
	}

	public Double getHigh() {
		return high;
	}

	public Double getLow() {
		return low;
	}

	public Double getBaseVolume() {
		return baseVolume;
	}

	public Double getQuoteVolume() {
		return quoteVolume;
	}

	public Integer getTradeCount() {
		return tradeCount;
	}

	public Double getTakerBuyBaseVolume() {
		return takerBuyBaseVolume;
	}

	public Double getTakerBuyQuoteVolume() {
		return takerBuyQuoteVolume;
	}
}
