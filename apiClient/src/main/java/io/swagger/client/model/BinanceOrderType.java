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
 * Gets or Sets BinanceOrderType
 */
@JsonAdapter(BinanceOrderType.Adapter.class)
public enum BinanceOrderType
{
	LIMIT("Limit"),
	MARKET("Market"),
	STOPLOSS("StopLoss"),
	STOPLOSSLIMIT("StopLossLimit"),
	STOP("Stop"),
	STOPMARKET("StopMarket"),
	TAKEPROFIT("TakeProfit"),
	TAKEPROFITMARKET("TakeProfitMarket"),
	TAKEPROFITLIMIT("TakeProfitLimit"),
	LIMITMAKER("LimitMaker"),
	TRAILINGSTOPMARKET("TrailingStopMarket");

	public static BinanceOrderType fromValue(String text) {
		for (BinanceOrderType b : BinanceOrderType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceOrderType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceOrderType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceOrderType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceOrderType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return BinanceOrderType.fromValue(String.valueOf(value));
		}
	}
}