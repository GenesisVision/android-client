package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 09/02/2021.
 */
public class DepthListModel implements Parcelable
{
	public static final Creator<DepthListModel> CREATOR = new Creator<DepthListModel>()
	{
		@Override
		public DepthListModel createFromParcel(Parcel in) {
			return new DepthListModel(in);
		}

		@Override
		public DepthListModel[] newArray(int size) {
			return new DepthListModel[size];
		}
	};

	@SerializedName("lastUpdateId")
	private Long lastUpdateId;

	@SerializedName("bids")
	private List<List<String>> bids;

	@SerializedName("asks")
	private List<List<String>> asks;

	protected DepthListModel(Parcel in) {
		lastUpdateId = in.readLong();
		in.readList(bids, List.class.getClassLoader());
		in.readList(asks, List.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(lastUpdateId);
		dest.writeList(bids);
		dest.writeList(asks);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public Long getLastUpdateId() {
		return lastUpdateId;
	}

	public List<List<String>> getBids() {
		return bids;
	}

	public List<List<String>> getAsks() {
		return asks;
	}
}
