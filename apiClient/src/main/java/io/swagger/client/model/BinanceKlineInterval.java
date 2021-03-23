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
 * Gets or Sets BinanceKlineInterval
 */
@JsonAdapter(BinanceKlineInterval.Adapter.class)
public enum BinanceKlineInterval
{
	ONEMINUTE("OneMinute"),
	THREEMINUTES("ThreeMinutes"),
	FIVEMINUTES("FiveMinutes"),
	FIFTEENMINUTES("FifteenMinutes"),
	THIRTYMINUTES("ThirtyMinutes"),
	ONEHOUR("OneHour"),
	TWOHOUR("TwoHour"),
	FOURHOUR("FourHour"),
	SIXHOUR("SixHour"),
	EIGHTHOUR("EightHour"),
	TWELVEHOUR("TwelveHour"),
	ONEDAY("OneDay"),
	THREEDAY("ThreeDay"),
	ONEWEEK("OneWeek"),
	ONEMONTH("OneMonth");

	public static BinanceKlineInterval fromValue(String text) {
		for (BinanceKlineInterval b : BinanceKlineInterval.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceKlineInterval(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceKlineInterval>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceKlineInterval enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceKlineInterval read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return BinanceKlineInterval.fromValue(String.valueOf(value));
		}
	}
}
