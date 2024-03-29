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
import io.swagger.client.model.DashboardTradingAssetStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PrivateTradingAccountFullPublicDetails
 */


public class PrivateTradingAccountFullPublicDetails implements Parcelable {
  @SerializedName("title")
  private String title = null;

  @SerializedName("creationDate")
  private DateTime creationDate = null;

  @SerializedName("status")
  private DashboardTradingAssetStatus status = null;

  public PrivateTradingAccountFullPublicDetails() {
  }
  public PrivateTradingAccountFullPublicDetails title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @Schema(description = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PrivateTradingAccountFullPublicDetails creationDate(DateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

   /**
   * Get creationDate
   * @return creationDate
  **/
  @Schema(description = "")
  public DateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(DateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PrivateTradingAccountFullPublicDetails status(DashboardTradingAssetStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public DashboardTradingAssetStatus getStatus() {
    return status;
  }

  public void setStatus(DashboardTradingAssetStatus status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrivateTradingAccountFullPublicDetails privateTradingAccountFullPublicDetails = (PrivateTradingAccountFullPublicDetails) o;
    return Objects.equals(this.title, privateTradingAccountFullPublicDetails.title) &&
        Objects.equals(this.creationDate, privateTradingAccountFullPublicDetails.creationDate) &&
        Objects.equals(this.status, privateTradingAccountFullPublicDetails.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, creationDate, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrivateTradingAccountFullPublicDetails {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
    out.writeValue(title);
    out.writeValue(creationDate);
    out.writeValue(status);
  }

  PrivateTradingAccountFullPublicDetails(Parcel in) {
    title = (String)in.readValue(null);
    creationDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    status = (DashboardTradingAssetStatus)in.readValue(DashboardTradingAssetStatus.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PrivateTradingAccountFullPublicDetails> CREATOR = new Parcelable.Creator<PrivateTradingAccountFullPublicDetails>() {
    public PrivateTradingAccountFullPublicDetails createFromParcel(Parcel in) {
      return new PrivateTradingAccountFullPublicDetails(in);
    }
    public PrivateTradingAccountFullPublicDetails[] newArray(int size) {
      return new PrivateTradingAccountFullPublicDetails[size];
    }
  };
}
