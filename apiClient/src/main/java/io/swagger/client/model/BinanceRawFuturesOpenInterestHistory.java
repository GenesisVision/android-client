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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * BinanceRawFuturesOpenInterestHistory
 */


public class BinanceRawFuturesOpenInterestHistory implements Parcelable {
  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("sumOpenInterest")
  private Double sumOpenInterest = null;

  @SerializedName("sumOpenInterestValue")
  private Double sumOpenInterestValue = null;

  @SerializedName("timestamp")
  private DateTime timestamp = null;

  public BinanceRawFuturesOpenInterestHistory() {
  }
  public BinanceRawFuturesOpenInterestHistory symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * Get symbol
   * @return symbol
  **/
  @Schema(description = "")
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public BinanceRawFuturesOpenInterestHistory sumOpenInterest(Double sumOpenInterest) {
    this.sumOpenInterest = sumOpenInterest;
    return this;
  }

   /**
   * Get sumOpenInterest
   * @return sumOpenInterest
  **/
  @Schema(description = "")
  public Double getSumOpenInterest() {
    return sumOpenInterest;
  }

  public void setSumOpenInterest(Double sumOpenInterest) {
    this.sumOpenInterest = sumOpenInterest;
  }

  public BinanceRawFuturesOpenInterestHistory sumOpenInterestValue(Double sumOpenInterestValue) {
    this.sumOpenInterestValue = sumOpenInterestValue;
    return this;
  }

   /**
   * Get sumOpenInterestValue
   * @return sumOpenInterestValue
  **/
  @Schema(description = "")
  public Double getSumOpenInterestValue() {
    return sumOpenInterestValue;
  }

  public void setSumOpenInterestValue(Double sumOpenInterestValue) {
    this.sumOpenInterestValue = sumOpenInterestValue;
  }

  public BinanceRawFuturesOpenInterestHistory timestamp(DateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  @Schema(description = "")
  public DateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(DateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinanceRawFuturesOpenInterestHistory binanceRawFuturesOpenInterestHistory = (BinanceRawFuturesOpenInterestHistory) o;
    return Objects.equals(this.symbol, binanceRawFuturesOpenInterestHistory.symbol) &&
        Objects.equals(this.sumOpenInterest, binanceRawFuturesOpenInterestHistory.sumOpenInterest) &&
        Objects.equals(this.sumOpenInterestValue, binanceRawFuturesOpenInterestHistory.sumOpenInterestValue) &&
        Objects.equals(this.timestamp, binanceRawFuturesOpenInterestHistory.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, sumOpenInterest, sumOpenInterestValue, timestamp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawFuturesOpenInterestHistory {\n");
    
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    sumOpenInterest: ").append(toIndentedString(sumOpenInterest)).append("\n");
    sb.append("    sumOpenInterestValue: ").append(toIndentedString(sumOpenInterestValue)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
    out.writeValue(symbol);
    out.writeValue(sumOpenInterest);
    out.writeValue(sumOpenInterestValue);
    out.writeValue(timestamp);
  }

  BinanceRawFuturesOpenInterestHistory(Parcel in) {
    symbol = (String)in.readValue(null);
    sumOpenInterest = (Double)in.readValue(null);
    sumOpenInterestValue = (Double)in.readValue(null);
    timestamp = (DateTime)in.readValue(DateTime.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawFuturesOpenInterestHistory> CREATOR = new Parcelable.Creator<BinanceRawFuturesOpenInterestHistory>() {
    public BinanceRawFuturesOpenInterestHistory createFromParcel(Parcel in) {
      return new BinanceRawFuturesOpenInterestHistory(in);
    }
    public BinanceRawFuturesOpenInterestHistory[] newArray(int size) {
      return new BinanceRawFuturesOpenInterestHistory[size];
    }
  };
}
