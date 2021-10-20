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
 * BinanceRawFuturesSymbolMarketLotSizeFilter
 */


public class BinanceRawFuturesSymbolMarketLotSizeFilter implements Parcelable {
  @SerializedName("filterType")
  private BinanceSymbolFilterType filterType = null;

  @SerializedName("minQuantity")
  private Double minQuantity = null;

  @SerializedName("maxQuantity")
  private Double maxQuantity = null;

  @SerializedName("stepSize")
  private Double stepSize = null;

  public BinanceRawFuturesSymbolMarketLotSizeFilter() {
  }
  public BinanceRawFuturesSymbolMarketLotSizeFilter filterType(BinanceSymbolFilterType filterType) {
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

  public BinanceRawFuturesSymbolMarketLotSizeFilter minQuantity(Double minQuantity) {
    this.minQuantity = minQuantity;
    return this;
  }

   /**
   * Get minQuantity
   * @return minQuantity
  **/
  @Schema(description = "")
  public Double getMinQuantity() {
    return minQuantity;
  }

  public void setMinQuantity(Double minQuantity) {
    this.minQuantity = minQuantity;
  }

  public BinanceRawFuturesSymbolMarketLotSizeFilter maxQuantity(Double maxQuantity) {
    this.maxQuantity = maxQuantity;
    return this;
  }

   /**
   * Get maxQuantity
   * @return maxQuantity
  **/
  @Schema(description = "")
  public Double getMaxQuantity() {
    return maxQuantity;
  }

  public void setMaxQuantity(Double maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  public BinanceRawFuturesSymbolMarketLotSizeFilter stepSize(Double stepSize) {
    this.stepSize = stepSize;
    return this;
  }

   /**
   * Get stepSize
   * @return stepSize
  **/
  @Schema(description = "")
  public Double getStepSize() {
    return stepSize;
  }

  public void setStepSize(Double stepSize) {
    this.stepSize = stepSize;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinanceRawFuturesSymbolMarketLotSizeFilter binanceRawFuturesSymbolMarketLotSizeFilter = (BinanceRawFuturesSymbolMarketLotSizeFilter) o;
    return Objects.equals(this.filterType, binanceRawFuturesSymbolMarketLotSizeFilter.filterType) &&
        Objects.equals(this.minQuantity, binanceRawFuturesSymbolMarketLotSizeFilter.minQuantity) &&
        Objects.equals(this.maxQuantity, binanceRawFuturesSymbolMarketLotSizeFilter.maxQuantity) &&
        Objects.equals(this.stepSize, binanceRawFuturesSymbolMarketLotSizeFilter.stepSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filterType, minQuantity, maxQuantity, stepSize);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawFuturesSymbolMarketLotSizeFilter {\n");
    
    sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
    sb.append("    minQuantity: ").append(toIndentedString(minQuantity)).append("\n");
    sb.append("    maxQuantity: ").append(toIndentedString(maxQuantity)).append("\n");
    sb.append("    stepSize: ").append(toIndentedString(stepSize)).append("\n");
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
    out.writeValue(minQuantity);
    out.writeValue(maxQuantity);
    out.writeValue(stepSize);
  }

  BinanceRawFuturesSymbolMarketLotSizeFilter(Parcel in) {
    filterType = (BinanceSymbolFilterType)in.readValue(BinanceSymbolFilterType.class.getClassLoader());
    minQuantity = (Double)in.readValue(null);
    maxQuantity = (Double)in.readValue(null);
    stepSize = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawFuturesSymbolMarketLotSizeFilter> CREATOR = new Parcelable.Creator<BinanceRawFuturesSymbolMarketLotSizeFilter>() {
    public BinanceRawFuturesSymbolMarketLotSizeFilter createFromParcel(Parcel in) {
      return new BinanceRawFuturesSymbolMarketLotSizeFilter(in);
    }
    public BinanceRawFuturesSymbolMarketLotSizeFilter[] newArray(int size) {
      return new BinanceRawFuturesSymbolMarketLotSizeFilter[size];
    }
  };
}
