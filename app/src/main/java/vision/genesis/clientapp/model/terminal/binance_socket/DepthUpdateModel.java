package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/02/2021.
 */
public class DepthUpdateModel implements Parcelable
{
	public static final Creator<DepthUpdateModel> CREATOR = new Creator<DepthUpdateModel>()
	{
		@Override
		public DepthUpdateModel createFromParcel(Parcel in) {
			return new DepthUpdateModel(in);
		}

		@Override
		public DepthUpdateModel[] newArray(int size) {
			return new DepthUpdateModel[size];
		}
	};

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("s")
	private String symbol;

	@SerializedName("U")
	private Long firstUpdateId;

	@SerializedName("u")
	private Long finalUpdateId;

	@SerializedName("b")
	private List<List<String>> bids;

	@SerializedName("a")
	private List<List<String>> asks;

	public DepthUpdateModel() {
	}

	protected DepthUpdateModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		symbol = in.readString();
		if (in.readByte() == 0) {
			firstUpdateId = null;
		}
		else {
			firstUpdateId = in.readLong();
		}
		if (in.readByte() == 0) {
			finalUpdateId = null;
		}
		else {
			finalUpdateId = in.readLong();
		}
		in.readList(bids, List.class.getClassLoader());
		in.readList(asks, List.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventType);
		dest.writeValue(eventTime);
		dest.writeString(symbol);
		if (firstUpdateId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(firstUpdateId);
		}
		if (finalUpdateId == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeLong(finalUpdateId);
		}
		dest.writeList(bids);
		dest.writeList(asks);
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

	public Long getFirstUpdateId() {
		return firstUpdateId;
	}

	public Long getFinalUpdateId() {
		return finalUpdateId;
	}

	public List<List<String>> getBids() {
		return bids;
	}

	public List<List<String>> getAsks() {
		return asks;
	}
}
