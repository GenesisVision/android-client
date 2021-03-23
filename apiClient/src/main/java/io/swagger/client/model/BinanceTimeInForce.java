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
 * Gets or Sets BinanceTimeInForce
 */
@JsonAdapter(BinanceTimeInForce.Adapter.class)
public enum BinanceTimeInForce
{
	GOODTILLCANCEL("GoodTillCancel"),
	IMMEDIATEORCANCEL("ImmediateOrCancel"),
	FILLORKILL("FillOrKill"),
	GOODTILLCROSSING("GoodTillCrossing"),
	GOODTILLEXPIREDORCANCELED("GoodTillExpiredOrCanceled");

	private String value;

	BinanceTimeInForce(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static BinanceTimeInForce fromValue(String text) {
		for (BinanceTimeInForce b : BinanceTimeInForce.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public static class Adapter extends TypeAdapter<BinanceTimeInForce>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceTimeInForce enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceTimeInForce read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return BinanceTimeInForce.fromValue(String.valueOf(value));
		}
	}
}
