package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/01/2021.
 */

public class KlinePayloadModel implements Parcelable
{
	public static final Creator<KlinePayloadModel> CREATOR = new Creator<KlinePayloadModel>()
	{
		@Override
		public KlinePayloadModel createFromParcel(Parcel in) {
			return new KlinePayloadModel(in);
		}

		@Override
		public KlinePayloadModel[] newArray(int size) {
			return new KlinePayloadModel[size];
		}
	};

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("s")
	private String symbol;

	@SerializedName("k")
	private KlineModel kline;

	public KlinePayloadModel() {
	}

	protected KlinePayloadModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		symbol = in.readString();
		kline = in.readParcelable(KlineModel.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventType);
		dest.writeValue(eventTime);
		dest.writeString(symbol);
		dest.writeParcelable(kline, flags);
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

	public KlineModel getKline() {
		return kline;
	}
}
