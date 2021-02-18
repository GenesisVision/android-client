package vision.genesis.clientapp.model.terminal.binance_api;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */
@JsonAdapter(BinanceSymbolFilterType.Adapter.class)
public enum BinanceSymbolFilterType
{
	UNKNOWN("UNKNOWN"),
	PRICE("PRICE_FILTER"),
	PRICEPERCENT("PERCENT_PRICE"),
	LOTSIZE("LOT_SIZE"),
	MARKETLOTSIZE("MARKET_LOT_SIZE"),
	MINNOTIONAL("MIN_NOTIONAL"),
	MAXNUMBERORDERS("MAX_NUM_ORDERS"),
	MAXNUMBERALGORITHMICORDERS("MAX_NUM_ALGO_ORDERS"),
	MAXNUMBERICEBERGORDERS("MAX_NUM_ICEBERG_ORDERS"),
	ICEBERGPARTS("ICEBERG_PARTS"),
	MAXPOSITION("MAX_POSITION");

	public static BinanceSymbolFilterType fromValue(String text) {
		for (BinanceSymbolFilterType b : BinanceSymbolFilterType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceSymbolFilterType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceSymbolFilterType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceSymbolFilterType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceSymbolFilterType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return BinanceSymbolFilterType.fromValue(String.valueOf(value));
		}
	}
}
