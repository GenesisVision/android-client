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
 * Gets or Sets SocialPostTagType
 */
@JsonAdapter(SocialPostTagType.Adapter.class)
public enum SocialPostTagType {
  UNDEFINED("Undefined"),
  PROGRAM("Program"),
  FUND("Fund"),
  FOLLOW("Follow"),
  USER("User"),
  ASSET("Asset"),
  EVENT("Event"),
  POST("Post"),
  URL("Url");

  private String value;

  SocialPostTagType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static SocialPostTagType fromValue(String text) {
    for (SocialPostTagType b : SocialPostTagType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<SocialPostTagType> {
    @Override
    public void write(final JsonWriter jsonWriter, final SocialPostTagType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SocialPostTagType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return SocialPostTagType.fromValue(String.valueOf(value));
    }
  }
}
