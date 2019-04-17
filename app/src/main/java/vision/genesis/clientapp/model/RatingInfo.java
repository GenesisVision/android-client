package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */
public class RatingInfo implements Parcelable
{
	public static final Creator<RatingInfo> CREATOR = new Creator<RatingInfo>()
	{
		@Override
		public RatingInfo createFromParcel(Parcel in) {
			return new RatingInfo(in);
		}

		@Override
		public RatingInfo[] newArray(int size) {
			return new RatingInfo[size];
		}
	};

	private final Integer level;

	private final Integer total;

	private final Integer quota;

	private final Double targetProfit;

	public RatingInfo(Integer level, Integer total, Integer quota, Double targetProfit) {
		this.level = level;
		this.total = total;
		this.quota = quota;
		this.targetProfit = targetProfit;
	}

	protected RatingInfo(Parcel in) {
		if (in.readByte() == 0) {
			level = null;
		}
		else {
			level = in.readInt();
		}
		if (in.readByte() == 0) {
			total = null;
		}
		else {
			total = in.readInt();
		}
		if (in.readByte() == 0) {
			quota = null;
		}
		else {
			quota = in.readInt();
		}
		if (in.readByte() == 0) {
			targetProfit = null;
		}
		else {
			targetProfit = in.readDouble();
		}
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getQuota() {
		return quota;
	}

	public Double getTargetProfit() {
		return targetProfit;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		if (level == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(level);
		}
		if (total == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(total);
		}
		if (quota == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(quota);
		}
		if (targetProfit == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(targetProfit);
		}
	}
}
