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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable;
import android.os.Parcel;
import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets SubscriptionMode
 */
@JsonAdapter(SubscriptionMode.Adapter.class)
public enum SubscriptionMode {
  BYBALANCE("ByBalance"),
  PERCENT("Percent"),
  FIXED("Fixed");

  private String value;

  SubscriptionMode(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static SubscriptionMode fromValue(String text) {
    for (SubscriptionMode b : SubscriptionMode.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<SubscriptionMode> {
    @Override
    public void write(final JsonWriter jsonWriter, final SubscriptionMode enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SubscriptionMode read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return SubscriptionMode.fromValue(String.valueOf(value));
    }
  }
}
