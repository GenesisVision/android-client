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
 * ExchangeCredentialsIpInfo
 */


public class ExchangeCredentialsIpInfo implements Parcelable {
  @SerializedName("ip")
  private String ip = null;

  @SerializedName("date")
  private DateTime date = null;

  public ExchangeCredentialsIpInfo() {
  }
  public ExchangeCredentialsIpInfo ip(String ip) {
    this.ip = ip;
    return this;
  }

   /**
   * Get ip
   * @return ip
  **/
  @Schema(description = "")
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public ExchangeCredentialsIpInfo date(DateTime date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @Schema(description = "")
  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeCredentialsIpInfo exchangeCredentialsIpInfo = (ExchangeCredentialsIpInfo) o;
    return Objects.equals(this.ip, exchangeCredentialsIpInfo.ip) &&
        Objects.equals(this.date, exchangeCredentialsIpInfo.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ip, date);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeCredentialsIpInfo {\n");
    
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
    out.writeValue(ip);
    out.writeValue(date);
  }

  ExchangeCredentialsIpInfo(Parcel in) {
    ip = (String)in.readValue(null);
    date = (DateTime)in.readValue(DateTime.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ExchangeCredentialsIpInfo> CREATOR = new Parcelable.Creator<ExchangeCredentialsIpInfo>() {
    public ExchangeCredentialsIpInfo createFromParcel(Parcel in) {
      return new ExchangeCredentialsIpInfo(in);
    }
    public ExchangeCredentialsIpInfo[] newArray(int size) {
      return new ExchangeCredentialsIpInfo[size];
    }
  };
}
