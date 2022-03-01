package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinancePositionSide;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class FuturesPositionModel implements Parcelable
{
	public static final Creator<FuturesPositionModel> CREATOR = new Creator<FuturesPositionModel>()
	{
		@Override
		public FuturesPositionModel createFromParcel(Parcel in) {
			return new FuturesPositionModel(in);
		}

		@Override
		public FuturesPositionModel[] newArray(int size) {
			return new FuturesPositionModel[size];
		}
	};

	@SerializedName("s")
	private String symbol;

	@SerializedName("pa")
	private Double positionAmount;

	@SerializedName("ep")
	private Double entryPrice;

	@SerializedName("cr")
	private Double accumulatedRealized;

	@SerializedName("up")
	private Double unrealizedPnl;

	@SerializedName("mt")
	private BinanceFuturesMarginType marginType;

	@SerializedName("iw")
	private Double isolatedWallet;

	@SerializedName("ps")
	private BinancePositionSide positionSide;

	protected FuturesPositionModel(Parcel in) {
		symbol = in.readString();
		if (in.readByte() == 0) {
			positionAmount = null;
		}
		else {
			positionAmount = in.readDouble();
		}
		if (in.readByte() == 0) {
			entryPrice = null;
		}
		else {
			entryPrice = in.readDouble();
		}
		if (in.readByte() == 0) {
			accumulatedRealized = null;
		}
		else {
			accumulatedRealized = in.readDouble();
		}
		if (in.readByte() == 0) {
			unrealizedPnl = null;
		}
		else {
			unrealizedPnl = in.readDouble();
		}
		marginType = (BinanceFuturesMarginType) in.readSerializable();
		if (in.readByte() == 0) {
			isolatedWallet = null;
		}
		else {
			isolatedWallet = in.readDouble();
		}
		positionSide = (BinancePositionSide) in.readSerializable();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(symbol);
		if (positionAmount == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(positionAmount);
		}
		if (entryPrice == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(entryPrice);
		}
		if (accumulatedRealized == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(accumulatedRealized);
		}
		if (unrealizedPnl == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(unrealizedPnl);
		}
		parcel.writeSerializable(marginType);
		if (isolatedWallet == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(isolatedWallet);
		}
		parcel.writeSerializable(positionSide);
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getPositionAmount() {
		return positionAmount;
	}

	public Double getEntryPrice() {
		return entryPrice;
	}

	public Double getAccumulatedRealized() {
		return accumulatedRealized;
	}

	public Double getUnrealizedPnl() {
		return unrealizedPnl;
	}

	public BinanceFuturesMarginType getMarginType() {
		return marginType;
	}

	public Double getIsolatedWallet() {
		return isolatedWallet;
	}

	public BinancePositionSide getPositionSide() {
		return positionSide;
	}
}
