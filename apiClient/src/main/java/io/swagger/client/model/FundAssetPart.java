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
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FundAssetPart
 */


public class FundAssetPart implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("percent")
  private Double percent = null;

  public FundAssetPart() {
  }
  public FundAssetPart id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public FundAssetPart percent(Double percent) {
    this.percent = percent;
    return this;
  }

   /**
   * Get percent
   * @return percent
  **/
  @Schema(description = "")
  public Double getPercent() {
    return percent;
  }

  public void setPercent(Double percent) {
    this.percent = percent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FundAssetPart fundAssetPart = (FundAssetPart) o;
    return Objects.equals(this.id, fundAssetPart.id) &&
        Objects.equals(this.percent, fundAssetPart.percent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, percent);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundAssetPart {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
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
    out.writeValue(id);
    out.writeValue(percent);
  }

  FundAssetPart(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    percent = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundAssetPart> CREATOR = new Parcelable.Creator<FundAssetPart>() {
    public FundAssetPart createFromParcel(Parcel in) {
      return new FundAssetPart(in);
    }
    public FundAssetPart[] newArray(int size) {
      return new FundAssetPart[size];
    }
  };
}
