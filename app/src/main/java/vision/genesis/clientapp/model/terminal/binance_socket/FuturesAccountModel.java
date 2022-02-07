package vision.genesis.clientapp.model.terminal.binance_socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/02/2022.
 */
public class FuturesAccountModel implements Parcelable
{
	public static final Creator<FuturesAccountModel> CREATOR = new Creator<FuturesAccountModel>()
	{
		@Override
		public FuturesAccountModel createFromParcel(Parcel in) {
			return new FuturesAccountModel(in);
		}

		@Override
		public FuturesAccountModel[] newArray(int size) {
			return new FuturesAccountModel[size];
		}
	};

	@SerializedName("e")
	private String eventType;

	@SerializedName("E")
	private DateTime eventTime;

	@SerializedName("T")
	private DateTime updateTime = null;

	@SerializedName("o")
	private FuturesOrderModel order = null;

	@SerializedName("a")
	private FuturesUpdateData updateData = null;

	@SerializedName("p")
	private List<FuturesPositionModel> positions = null;

	@SerializedName("accountId")
	private UUID accountId = null;

	public FuturesAccountModel() {
	}

	protected FuturesAccountModel(Parcel in) {
		eventType = in.readString();
		eventTime = (DateTime) in.readValue(DateTime.class.getClassLoader());
		order = (FuturesOrderModel) in.readValue(FuturesOrderModel.class.getClassLoader());
		updateData = (FuturesUpdateData) in.readValue(FuturesUpdateData.class.getClassLoader());
		in.readList(positions, FuturesPositionModel.class.getClassLoader());
		accountId = (UUID) in.readValue(DateTime.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(eventType);
		parcel.writeValue(eventTime);
		parcel.writeValue(order);
		parcel.writeValue(updateData);
		parcel.writeList(positions);
		parcel.writeValue(accountId);
	}

	public String getEventType() {
		return eventType;
	}

	public FuturesOrderModel getOrder() {
		return order;
	}

	public FuturesUpdateData getUpdateData() {
		return updateData;
	}

	public List<FuturesPositionModel> getPositions() {
		return positions;
	}

	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
}
