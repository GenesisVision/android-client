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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * BinanceRawFuturesLongShortRatio
 */


public class BinanceRawFuturesLongShortRatio implements Parcelable {
  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("longShortRatio")
  private Double longShortRatio = null;

  @SerializedName("longAccount")
  private Double longAccount = null;

  @SerializedName("shortAccount")
  private Double shortAccount = null;

  @SerializedName("timestamp")
  private DateTime timestamp = null;

  public BinanceRawFuturesLongShortRatio() {
  }
  public BinanceRawFuturesLongShortRatio symbol(String symbol) {
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

  public BinanceRawFuturesLongShortRatio longShortRatio(Double longShortRatio) {
    this.longShortRatio = longShortRatio;
    return this;
  }

   /**
   * Get longShortRatio
   * @return longShortRatio
  **/
  @Schema(description = "")
  public Double getLongShortRatio() {
    return longShortRatio;
  }

  public void setLongShortRatio(Double longShortRatio) {
    this.longShortRatio = longShortRatio;
  }

  public BinanceRawFuturesLongShortRatio longAccount(Double longAccount) {
    this.longAccount = longAccount;
    return this;
  }

   /**
   * Get longAccount
   * @return longAccount
  **/
  @Schema(description = "")
  public Double getLongAccount() {
    return longAccount;
  }

  public void setLongAccount(Double longAccount) {
    this.longAccount = longAccount;
  }

  public BinanceRawFuturesLongShortRatio shortAccount(Double shortAccount) {
    this.shortAccount = shortAccount;
    return this;
  }

   /**
   * Get shortAccount
   * @return shortAccount
  **/
  @Schema(description = "")
  public Double getShortAccount() {
    return shortAccount;
  }

  public void setShortAccount(Double shortAccount) {
    this.shortAccount = shortAccount;
  }

  public BinanceRawFuturesLongShortRatio timestamp(DateTime timestamp) {
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
    BinanceRawFuturesLongShortRatio binanceRawFuturesLongShortRatio = (BinanceRawFuturesLongShortRatio) o;
    return Objects.equals(this.symbol, binanceRawFuturesLongShortRatio.symbol) &&
        Objects.equals(this.longShortRatio, binanceRawFuturesLongShortRatio.longShortRatio) &&
        Objects.equals(this.longAccount, binanceRawFuturesLongShortRatio.longAccount) &&
        Objects.equals(this.shortAccount, binanceRawFuturesLongShortRatio.shortAccount) &&
        Objects.equals(this.timestamp, binanceRawFuturesLongShortRatio.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(symbol, longShortRatio, longAccount, shortAccount, timestamp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BinanceRawFuturesLongShortRatio {\n");
    
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    longShortRatio: ").append(toIndentedString(longShortRatio)).append("\n");
    sb.append("    longAccount: ").append(toIndentedString(longAccount)).append("\n");
    sb.append("    shortAccount: ").append(toIndentedString(shortAccount)).append("\n");
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
    out.writeValue(longShortRatio);
    out.writeValue(longAccount);
    out.writeValue(shortAccount);
    out.writeValue(timestamp);
  }

  BinanceRawFuturesLongShortRatio(Parcel in) {
    symbol = (String)in.readValue(null);
    longShortRatio = (Double)in.readValue(null);
    longAccount = (Double)in.readValue(null);
    shortAccount = (Double)in.readValue(null);
    timestamp = (DateTime)in.readValue(DateTime.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BinanceRawFuturesLongShortRatio> CREATOR = new Parcelable.Creator<BinanceRawFuturesLongShortRatio>() {
    public BinanceRawFuturesLongShortRatio createFromParcel(Parcel in) {
      return new BinanceRawFuturesLongShortRatio(in);
    }
    public BinanceRawFuturesLongShortRatio[] newArray(int size) {
      return new BinanceRawFuturesLongShortRatio[size];
    }
  };
}
