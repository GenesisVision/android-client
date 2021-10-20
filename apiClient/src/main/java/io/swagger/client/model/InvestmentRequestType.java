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
 * Gets or Sets InvestmentRequestType
 */
@JsonAdapter(InvestmentRequestType.Adapter.class)
public enum InvestmentRequestType {
  INVEST("Invest"),
  WITHDRAWAL("Withdrawal");

  private String value;

  InvestmentRequestType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static InvestmentRequestType fromValue(String text) {
    for (InvestmentRequestType b : InvestmentRequestType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<InvestmentRequestType> {
    @Override
    public void write(final JsonWriter jsonWriter, final InvestmentRequestType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public InvestmentRequestType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return InvestmentRequestType.fromValue(String.valueOf(value));
    }
  }
}
