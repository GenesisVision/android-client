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
 * Gets or Sets AssetType
 */
@JsonAdapter(AssetType.Adapter.class)
public enum AssetType
{
	NONE("None"),
	PROGRAM("Program"),
	FUND("Fund"),
	FOLLOW("Follow");

	public static AssetType fromValue(String text) {
		for (AssetType b : AssetType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	AssetType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<AssetType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final AssetType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public AssetType read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return AssetType.fromValue(String.valueOf(value));
		}
	}
}
