package vision.genesis.clientapp.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/08/2018.
 */
@JsonAdapter(TimeFrameEnum.Adapter.class)
public enum TimeFrameEnum
{
	DAY("Day"),

	WEEK("Week"),

	MONTH1("Month1"),

	MONTH3("Month3"),

	MONTH6("Month6"),

	YEAR("Year"),

	ALL("All");

	public static TimeFrameEnum fromValue(String text) {
		for (TimeFrameEnum b : TimeFrameEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	TimeFrameEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<TimeFrameEnum>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final TimeFrameEnum enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public TimeFrameEnum read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return TimeFrameEnum.fromValue(String.valueOf(value));
		}
	}
}