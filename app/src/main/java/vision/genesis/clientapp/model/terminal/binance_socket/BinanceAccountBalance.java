package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/09/2021.
 */
public class BinanceAccountBalance implements Parcelable
{
	public static final Creator<BinanceAccountBalance> CREATOR = new Creator<BinanceAccountBalance>()
	{
		@Override
		public BinanceAccountBalance createFromParcel(Parcel in) {
			return new BinanceAccountBalance(in);
		}

		@Override
		public BinanceAccountBalance[] newArray(int size) {
			return new BinanceAccountBalance[size];
		}
	};

	@SerializedName("a")
	private String asset;

	@SerializedName("f")
	private Double free;

	@SerializedName("l")
	private Double locked;

	protected BinanceAccountBalance(Parcel in) {
		asset = in.readString();
		if (in.readByte() == 0) {
			free = null;
		}
		else {
			free = in.readDouble();
		}
		if (in.readByte() == 0) {
			locked = null;
		}
		else {
			locked = in.readDouble();
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(asset);
		if (free == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(free);
		}
		if (locked == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(locked);
		}
	}

	public String getAsset() {
		return asset;
	}

	public Double getFree() {
		return free;
	}

	public Double getLocked() {
		return locked;
	}
}
