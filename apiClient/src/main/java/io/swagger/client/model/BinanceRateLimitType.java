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
 * Gets or Sets BinanceRateLimitType
 */
@JsonAdapter(BinanceRateLimitType.Adapter.class)
public enum BinanceRateLimitType
{
	REQUESTWEIGHT("RequestWeight"),
	ORDERS("Orders"),
	RAWREQUESTS("RawRequests");

	public static BinanceRateLimitType fromValue(String text) {
		for (BinanceRateLimitType b : BinanceRateLimitType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	BinanceRateLimitType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<BinanceRateLimitType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final BinanceRateLimitType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public BinanceRateLimitType read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return BinanceRateLimitType.fromValue(String.valueOf(value));
		}
	}
}
