/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets BinanceSymbolStatus
 */
@JsonAdapter(BinanceSymbolStatus.Adapter.class)
public enum BinanceSymbolStatus
{
	PRETRADING("PreTrading"),
	PENDINGTRADING("PendingTrading"),
	TRADING("Trading"),
	POSTTRADING("PostTrading"),
	ENDOFDAY("EndOfDay"),
	HALT("Halt"),
	AUCTIONMATCH("AuctionMatch"),
	BREAK("Break"),
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
			Object value = jsonReader.nextString();
			return BinanceSymbolStatus.fromValue(String.valueOf(value));
		}
	}
}
