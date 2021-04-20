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
 * Gets or Sets DashboardAssetStatus
 */
@JsonAdapter(DashboardAssetStatus.Adapter.class)
public enum DashboardAssetStatus
{
	ALL("All"),
	ACTIVE("Active");

	public static DashboardAssetStatus fromValue(String text) {
		for (DashboardAssetStatus b : DashboardAssetStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	DashboardAssetStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<DashboardAssetStatus>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final DashboardAssetStatus enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public DashboardAssetStatus read(final JsonReader jsonReader) throws IOException {
			Object value = jsonReader.nextString();
			return DashboardAssetStatus.fromValue(String.valueOf(value));
		}
	}
}
