package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Objects;

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

	private DateTime from = DateTime.now().minusDays(1);

	private DateTime to = DateTime.now();

	private DateRangeEnum selectedRange = DateRangeEnum.DAY;

	public DateRange() {

	}

	public DateRange(String dateRange, Long dateRangeFrom, Long dateRangeTo) {
		if (!dateRange.isEmpty()) {
			this.setSelectedRange(DateRangeEnum.fromValue(dateRange));
			this.setFrom(new DateTime(dateRangeFrom));
			this.setTo(new DateTime(dateRangeTo));
		}
	}

	private DateRange(Parcel in) {
		from = DateTime.parse(in.readString());
		to = DateTime.parse(in.readString());
		try {
			selectedRange = DateRangeEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			selectedRange = null;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(selectedRange, from, to);
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
		if (selectedRange == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(selectedRange.name());
		}
	}

	public DateRangeEnum getSelectedRange() {
		return selectedRange;
	}

	public void setSelectedRange(DateRangeEnum selectedRange) {
		this.selectedRange = selectedRange;
	}

	public void updateDates(DateRangeEnum selectedRange) {
		if (!selectedRange.equals(DateRangeEnum.CUSTOM)) {
			this.setTo(DateTime.now());
			switch (selectedRange) {
				case DAY:
					this.setFrom(this.getTo().minusDays(1));
					break;
				case WEEK:
					this.setFrom(this.getTo().minusWeeks(1));
					break;
				case MONTH:
					this.setFrom(this.getTo().minusMonths(1));
					break;
				case YEAR:
					this.setFrom(this.getTo().minusYears(1));
					break;
				default:
					break;
			}
		}
	}

	public enum DateRangeEnum
	{
		DAY("Day"),

		WEEK("Week"),

		MONTH("Month"),

		YEAR("Year"),

		CUSTOM("Custom");

		public static DateRangeEnum fromValue(String text) {
			for (DateRangeEnum b : DateRangeEnum.values()) {
				if (String.valueOf(b.value.toLowerCase()).equals(text.toLowerCase())) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DateRangeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DateRangeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DateRangeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DateRangeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DateRangeEnum.fromValue(String.valueOf(value));
			}
		}
	}
}
