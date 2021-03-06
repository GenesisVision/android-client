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
 * Gets or Sets BinanceRawSymbolStatus
 */
@JsonAdapter(BinanceRawSymbolStatus.Adapter.class)
public enum BinanceRawSymbolStatus
{
	PRETRADING("PreTrading"),
	TRADING("Trading"),
	POSTTRADING("PostTrading"),
	ENDOFDAY("EndOfDay"),
	HALT("Halt"),
	AUCTIONMATCH("AuctionMatch"),
	BREAK("Break"),
	CLOSE("Close");

	public static BinanceRawSymbolStatus fromValue(String text) {
		for (BinanceRawSymbolStatus b : BinanceRawSymbolStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceRawSymbolStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceRawSymbolStatus>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceRawSymbolStatus enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceRawSymbolStatus read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return BinanceRawSymbolStatus.fromValue(String.valueOf(value));
		}
	}
}
