package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/01/2021.
 */

public class TickerModel implements Parcelable
{
	public static final Creator<TickerModel> CREATOR = new Creator<TickerModel>()
	{
		@Override
		public TickerModel createFromParcel(Parcel in) {
			return new TickerModel(in);
		}

		@Override
		public TickerModel[] newArray(int size) {
			return new TickerModel[size];
		}
	};

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("s")
	private String symbol;

	@SerializedName("p")
	private Double priceChange;

	@SerializedName("P")
	private Double priceChangePercent;

	@SerializedName("w")
	private Double weightedAveragePrice;

	@SerializedName("x")
	private Double firstTradePrice;

	@SerializedName("c")
	private Double lastPrice;

	@SerializedName("Q")
	private Double lastQuantity;

	@SerializedName("b")
	private Double bestBidPrice;

	@SerializedName("B")
	private Double bestBidQuantity;

	@SerializedName("a")
	private Double bestAskPrice;

	@SerializedName("A")
	private Double bestAskQuantity;

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

	@SerializedName("O")
	private DateTime statisticsOpenTime;

	@SerializedName("C")
	private DateTime statisticsCloseTime;

	@SerializedName("F")
	private Integer firstTradeId;

	@SerializedName("L")
	private Integer lastTradeId;

	@SerializedName("n")
	private Integer totalTrades;

	public TickerModel() {
	}

	protected TickerModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		symbol = in.readString();
		if (in.readByte() == 0) {
			priceChange = null;
		}
		else {
			priceChange = in.readDouble();
		}
		if (in.readByte() == 0) {
			priceChangePercent = null;
		}
		else {
			priceChangePercent = in.readDouble();
		}
		if (in.readByte() == 0) {
			weightedAveragePrice = null;
		}
		else {
			weightedAveragePrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			firstTradePrice = null;
		}
		else {
			firstTradePrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			lastPrice = null;
		}
		else {
			lastPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			lastQuantity = null;
		}
		else {
			lastQuantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			bestBidPrice = null;
		}
		else {
			bestBidPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			bestBidQuantity = null;
		}
		else {
			bestBidQuantity = in.readDouble();
		}
		if (in.readByte() == 0) {
			bestAskPrice = null;
		}
		else {
			bestAskPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			bestAskQuantity = null;
		}
		else {
			bestAskQuantity = in.readDouble();
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
		statisticsOpenTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		statisticsCloseTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		if (in.readByte() == 0) {
			firstTradeId = null;
		}
		else {
			firstTradeId = in.readInt();
		}
		if (in.readByte() == 0) {
			lastTradeId = null;
		}
		else {
			lastTradeId = in.readInt();
		}
		if (in.readByte() == 0) {
			totalTrades = null;
		}
		else {
			totalTrades = in.readInt();
		}
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventType);
		dest.writeValue(eventTime);
		dest.writeString(symbol);
		if (priceChange == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(priceChange);
		}
		if (priceChangePercent == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(priceChangePercent);
		}
		if (weightedAveragePrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(weightedAveragePrice);
		}
		if (firstTradePrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(firstTradePrice);
		}
		if (lastPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(lastPrice);
		}
		if (lastQuantity == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(lastQuantity);
		}
		if (bestBidPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(bestBidPrice);
		}
		if (bestBidQuantity == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(bestBidQuantity);
		}
		if (bestAskPrice == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(bestAskPrice);
		}
		if (bestAskQuantity == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(bestAskQuantity);
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
		dest.writeValue(statisticsOpenTime);
		dest.writeValue(statisticsCloseTime);
		if (firstTradeId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(firstTradeId);
		}
		if (lastTradeId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(lastTradeId);
		}
		if (totalTrades == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(totalTrades);
		}
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

	public Double getPriceChange() {
		return priceChange;
	}

	public Double getPriceChangePercent() {
		return priceChangePercent;
	}

	public Double getWeightedAveragePrice() {
		return weightedAveragePrice;
	}

	public Double getFirstTradePrice() {
		return firstTradePrice;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public Double getLastQuantity() {
		return lastQuantity;
	}

	public Double getBestBidPrice() {
		return bestBidPrice;
	}

	public Double getBestBidQuantity() {
		return bestBidQuantity;
	}

	public Double getBestAskPrice() {
		return bestAskPrice;
	}

	public Double getBestAskQuantity() {
		return bestAskQuantity;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public Double getTotalTradedBaseAssetVolume() {
		return totalTradedBaseAssetVolume;
	}

	public Double getTotalTradedQuoteAssetVolume() {
		return totalTradedQuoteAssetVolume;
	}

	public DateTime getStatisticsOpenTime() {
		return statisticsOpenTime;
	}

	public DateTime getStatisticsCloseTime() {
		return statisticsCloseTime;
	}

	public Integer getFirstTradeId() {
		return firstTradeId;
	}

	public Integer getLastTradeId() {
		return lastTradeId;
	}

	public Integer getTotalTrades() {
		return totalTrades;
	}
}
