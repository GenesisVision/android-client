package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/08/2018.
 */
public class DateRange implements Parcelable
{
	public static final Creator<DateRange> CREATOR = new Creator<DateRange>()
	{
		@Override
		public DateRange createFromParcel(Parcel in) {
			return new DateRange(in);
		}

		@Override
		public DateRange[] newArray(int size) {
			return new DateRange[size];
		}
	};

	private DateTime from = new DateTime();

	private DateTime to = new DateTime();

	public DateRange() {

	}

	private DateRange(Parcel in) {
		from = DateTime.parse(in.readString());
		to = DateTime.parse(in.readString());
	}

	public DateTime getFrom() {
		return from;
	}

	public void setFrom(DateTime from) {
		this.from = from;
	}

	public DateTime getTo() {
		return to;
	}

	public void setTo(DateTime to) {
		this.to = to;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (from == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(from.toString());
		}
		if (to == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(to.toString());
		}
	}
}
