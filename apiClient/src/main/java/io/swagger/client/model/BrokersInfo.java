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
import io.swagger.client.model.Broker;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * BrokersInfo
 */


public class BrokersInfo implements Parcelable {
  @SerializedName("brokers")
  private List<Broker> brokers = null;

  public BrokersInfo() {
  }
  public BrokersInfo brokers(List<Broker> brokers) {
    this.brokers = brokers;
    return this;
  }

  public BrokersInfo addBrokersItem(Broker brokersItem) {
    if (this.brokers == null) {
      this.brokers = new ArrayList<Broker>();
    }
    this.brokers.add(brokersItem);
    return this;
  }

   /**
   * Get brokers
   * @return brokers
  **/
  @Schema(description = "")
  public List<Broker> getBrokers() {
    return brokers;
  }

  public void setBrokers(List<Broker> brokers) {
    this.brokers = brokers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BrokersInfo brokersInfo = (BrokersInfo) o;
    return Objects.equals(this.brokers, brokersInfo.brokers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brokers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BrokersInfo {\n");
    
    sb.append("    brokers: ").append(toIndentedString(brokers)).append("\n");
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
    out.writeValue(brokers);
  }

  BrokersInfo(Parcel in) {
    brokers = (List<Broker>)in.readValue(Broker.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BrokersInfo> CREATOR = new Parcelable.Creator<BrokersInfo>() {
    public BrokersInfo createFromParcel(Parcel in) {
      return new BrokersInfo(in);
    }
    public BrokersInfo[] newArray(int size) {
      return new BrokersInfo[size];
    }
  };
}
