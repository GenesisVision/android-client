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
 * Gets or Sets SocialViewMode
 */
@JsonAdapter(SocialViewMode.Adapter.class)
public enum SocialViewMode
{
	ALLUSERS("AllUsers"),
	ONLYME("OnlyMe");

	public static SocialViewMode fromValue(String text) {
		for (SocialViewMode b : SocialViewMode.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	SocialViewMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<SocialViewMode>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final SocialViewMode enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public SocialViewMode read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return SocialViewMode.fromValue(String.valueOf(value));
		}
	}
}
