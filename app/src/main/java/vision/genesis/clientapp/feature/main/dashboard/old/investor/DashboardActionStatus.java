package vision.genesis.clientapp.feature.main.dashboard.old.investor;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */
public enum DashboardActionStatus
{
	ALL("All"),

	ACTIVE("Active");

	public static DashboardActionStatus fromValue(String text) {
		for (DashboardActionStatus b : DashboardActionStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	DashboardActionStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<DashboardActionStatus>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final DashboardActionStatus enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public DashboardActionStatus read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return DashboardActionStatus.fromValue(String.valueOf(value));
		}
	}
}