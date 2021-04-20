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
 * Gets or Sets AssetFilterType
 */
@JsonAdapter(AssetFilterType.Adapter.class)
public enum AssetFilterType
{
	ALL("All"),
	PROGRAM("Program"),
	FUND("Fund"),
	FOLLOW("Follow");

	public static AssetFilterType fromValue(String text) {
		for (AssetFilterType b : AssetFilterType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	AssetFilterType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<AssetFilterType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final AssetFilterType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public AssetFilterType read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return AssetFilterType.fromValue(String.valueOf(value));
		}
	}
}
