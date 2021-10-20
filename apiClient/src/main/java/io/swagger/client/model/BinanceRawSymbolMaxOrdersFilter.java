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
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.BinanceSymbolFilterType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * BinanceRawSymbolMaxOrdersFilter
 */


public class BinanceRawSymbolMaxOrdersFilter implements Parcelable {
  @SerializedName("filterType")
  private BinanceSymbolFilterType filterType = null;

  @SerializedName("maxNumberOrders")
  private Integer maxNumberOrders = null;

  public BinanceRawSymbolMaxOrdersFilter() {
  }
  public BinanceRawSymbolMaxOrdersFilter filterType(BinanceSymbolFilterType filterType) {
    this.filterType = filterType;
    return this;
  }

   /**
   * Get filterType
   * @return filterType
  **/
  @Schema(description = "")
  public BinanceSymbolFilterType getFilterType() {
    return filterType;
  }

  public void setFilterType(BinanceSymbolFilterType filterType) {
    this.filterType = filterType;
  }

  public BinanceRawSymbolMaxOrdersFilter maxNumberOrders(Integer maxNumberOrders) {
    this.maxNumberOrders = maxNumberOrders;
    return this;
  }

   /**
   * Get maxNumberOrders
   * @return maxNumberOrders
  **/
  @Schema(description = "")
  public Integer getMaxNumberOrders() {
    return maxNumberOrders;
  }

  public void setMaxNumberOrders(Integer maxNumberOrders) {
    this.maxNumberOrders = maxNumberOrders;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinanceRawSymbolMaxOrdersFilter binanceRawSymbolMaxOrdersFilter = (BinanceRawSymbolMaxOrdersFilter) o;
    return Objects.equals(this.filterType, binanceRawSymbolMaxOrdersFilter.filterType) &&
        Objects.equals(this.maxNumberOrders, binanceRawSymbolMaxOrdersFilter.maxNumberOrders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filterType, maxNumberOrders);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawSymbolMaxOrdersFilter {\n");
    
    sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
    sb.append("    maxNumberOrders: ").append(toIndentedString(maxNumberOrders)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public void writeToParcel(Parcel out, int flags) {
    out.writeValue(filterType);
    out.writeValue(maxNumberOrders);
  }

  BinanceRawSymbolMaxOrdersFilter(Parcel in) {
    filterType = (BinanceSymbolFilterType)in.readValue(BinanceSymbolFilterType.class.getClassLoader());
    maxNumberOrders = (Integer)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawSymbolMaxOrdersFilter> CREATOR = new Parcelable.Creator<BinanceRawSymbolMaxOrdersFilter>() {
    public BinanceRawSymbolMaxOrdersFilter createFromParcel(Parcel in) {
      return new BinanceRawSymbolMaxOrdersFilter(in);
    }
    public BinanceRawSymbolMaxOrdersFilter[] newArray(int size) {
      return new BinanceRawSymbolMaxOrdersFilter[size];
    }
  };
}
