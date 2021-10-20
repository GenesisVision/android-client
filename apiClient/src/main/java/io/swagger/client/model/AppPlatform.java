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
 * Gets or Sets AppPlatform
 */
@JsonAdapter(AppPlatform.Adapter.class)
public enum AppPlatform {
  IOS("IOs"),
  ANDROID("Android"),
  WEB("Web");

  private String value;

  AppPlatform(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static AppPlatform fromValue(String text) {
    for (AppPlatform b : AppPlatform.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<AppPlatform> {
    @Override
    public void write(final JsonWriter jsonWriter, final AppPlatform enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public AppPlatform read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return AppPlatform.fromValue(String.valueOf(value));
    }
  }
}
