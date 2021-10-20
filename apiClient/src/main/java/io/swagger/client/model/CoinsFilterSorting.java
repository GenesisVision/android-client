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
 * Gets or Sets CoinsFilterSorting
 */
@JsonAdapter(CoinsFilterSorting.Adapter.class)
public enum CoinsFilterSorting {
  BYASSETASC("ByAssetAsc"),
  BYASSETDESC("ByAssetDesc"),
  BYSYMBOLASC("BySymbolAsc"),
  BYSYMBOLDESC("BySymbolDesc"),
  BYPRICEASC("ByPriceAsc"),
  BYPRICEDESC("ByPriceDesc"),
  BYCHANGEASC("ByChangeAsc"),
  BYCHANGEDESC("ByChangeDesc"),
  BYMARKETCAPASC("ByMarketCapAsc"),
  BYMARKETCAPDESC("ByMarketCapDesc"),
  BYVOLUMEASC("ByVolumeAsc"),
  BYVOLUMEDESC("ByVolumeDesc"),
  BYAMOUNTASC("ByAmountAsc"),
  BYAMOUNTDESC("ByAmountDesc"),
  BYAVERAGEPRICEASC("ByAveragePriceAsc"),
  BYAVERAGEPRICEDESC("ByAveragePriceDesc"),
  BYPROFITASC("ByProfitAsc"),
  BYPROFITDESC("ByProfitDesc"),
  BYTOTALASC("ByTotalAsc"),
  BYTOTALDESC("ByTotalDesc");

  private String value;

  CoinsFilterSorting(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static CoinsFilterSorting fromValue(String text) {
    for (CoinsFilterSorting b : CoinsFilterSorting.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<CoinsFilterSorting> {
    @Override
    public void write(final JsonWriter jsonWriter, final CoinsFilterSorting enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public CoinsFilterSorting read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return CoinsFilterSorting.fromValue(String.valueOf(value));
    }
  }
}
