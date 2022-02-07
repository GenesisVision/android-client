package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class BinanceFuturesAccountBalance implements Parcelable
{
	public static final Creator<BinanceFuturesAccountBalance> CREATOR = new Creator<BinanceFuturesAccountBalance>()
	{
		@Override
		public BinanceFuturesAccountBalance createFromParcel(Parcel in) {
			return new BinanceFuturesAccountBalance(in);
		}

		@Override
		public BinanceFuturesAccountBalance[] newArray(int size) {
			return new BinanceFuturesAccountBalance[size];
		}
	};

	@SerializedName("a")
	private String asset;

	@SerializedName("wb")
	private Double walletBalance;

	@SerializedName("cw")
	private Double crossWalletBalance;

	@SerializedName("bc")
	private Double balanceChange;

	protected BinanceFuturesAccountBalance(Parcel in) {
		asset = in.readString();
		if (in.readByte() == 0) {
			walletBalance = null;
		}
		else {
			walletBalance = in.readDouble();
		}
		if (in.readByte() == 0) {
			crossWalletBalance = null;
		}
		else {
			crossWalletBalance = in.readDouble();
		}
		if (in.readByte() == 0) {
			balanceChange = null;
		}
		else {
			balanceChange = in.readDouble();
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(asset);
		if (walletBalance == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(walletBalance);
		}
		if (crossWalletBalance == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(crossWalletBalance);
		}
		if (balanceChange == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(balanceChange);
		}
	}

	public String getAsset() {
		return asset;
	}

	public Double getWalletBalance() {
		return walletBalance;
	}

	public Double getCrossWalletBalance() {
		return crossWalletBalance;
	}

	public Double getBalanceChange() {
		return balanceChange;
	}
}
