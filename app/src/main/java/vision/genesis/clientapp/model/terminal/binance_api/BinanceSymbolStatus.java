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
@JsonAdapter(BinanceSymbolStatus.Adapter.class)
public enum BinanceSymbolStatus
{
	PRETRADING("PRE_TRADING"),
	PENDINGTRADING("PENDING_TRADING"),
	TRADING("TRADING"),
	POSTTRADING("POST_TRADING"),
	ENDOFDAY("END_OF_DAY"),
	HALT("HALT"),
	AUCTIONMATCH("AUCTION_MATCH"),
	BREAK("BREAK"),
	CLOSE("Close"),
	PREDELIVERING("PreDelivering"),
	DELIVERING("Delivering"),
	PRESETTLE("PreSettle"),
	SETTLING("Settling");

	public static BinanceSymbolStatus fromValue(String text) {
		for (BinanceSymbolStatus b : BinanceSymbolStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceSymbolStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceSymbolStatus>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceSymbolStatus enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceSymbolStatus read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return BinanceSymbolStatus.fromValue(String.valueOf(value));
		}
	}
}
