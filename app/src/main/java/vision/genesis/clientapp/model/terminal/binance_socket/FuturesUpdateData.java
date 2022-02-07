package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class FuturesUpdateData implements Parcelable
{
	public static final Creator<FuturesUpdateData> CREATOR = new Creator<FuturesUpdateData>()
	{
		@Override
		public FuturesUpdateData createFromParcel(Parcel in) {
			return new FuturesUpdateData(in);
		}

		@Override
		public FuturesUpdateData[] newArray(int size) {
			return new FuturesUpdateData[size];
		}
	};

	@SerializedName("m")
	private String eventTypeReason;

	@SerializedName("B")
	private List<BinanceFuturesAccountBalance> balances = null;

	public FuturesUpdateData() {
	}

	protected FuturesUpdateData(Parcel in) {
		eventTypeReason = in.readString();
		in.readList(balances, BinanceSpotAccountBalance.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(eventTypeReason);
		parcel.writeList(balances);
	}

	public String getEventTypeReason() {
		return eventTypeReason;
	}

	public List<BinanceFuturesAccountBalance> getBalances() {
		return balances;
	}
}
