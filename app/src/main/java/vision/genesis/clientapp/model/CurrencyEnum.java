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
@JsonAdapter(CurrencyEnum.Adapter.class)
public enum CurrencyEnum
{
	UNDEFINED("Undefined"),

	GVT("GVT"),

	ETH("ETH"),

	BTC("BTC"),

	ADA("ADA"),

	USD("USD"),

	EUR("EUR");

	public static CurrencyEnum fromValue(String text) {
		for (CurrencyEnum b : CurrencyEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	CurrencyEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<CurrencyEnum>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final CurrencyEnum enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public CurrencyEnum read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return CurrencyEnum.fromValue(String.valueOf(value));
		}
	}
}