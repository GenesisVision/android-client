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
import android.os.Parcelable;
import android.os.Parcel;
/**
 * IOsAppVersion
 */


public class IOsAppVersion implements Parcelable {
  @SerializedName("minVersion")
  private String minVersion = null;

  @SerializedName("lastVersion")
  private String lastVersion = null;

  public IOsAppVersion() {
  }
  public IOsAppVersion minVersion(String minVersion) {
    this.minVersion = minVersion;
    return this;
  }

   /**
   * Get minVersion
   * @return minVersion
  **/
  @Schema(description = "")
  public String getMinVersion() {
    return minVersion;
  }

  public void setMinVersion(String minVersion) {
    this.minVersion = minVersion;
  }

  public IOsAppVersion lastVersion(String lastVersion) {
    this.lastVersion = lastVersion;
    return this;
  }

   /**
   * Get lastVersion
   * @return lastVersion
  **/
  @Schema(description = "")
  public String getLastVersion() {
    return lastVersion;
  }

  public void setLastVersion(String lastVersion) {
    this.lastVersion = lastVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IOsAppVersion iosAppVersion = (IOsAppVersion) o;
    return Objects.equals(this.minVersion, iosAppVersion.minVersion) &&
        Objects.equals(this.lastVersion, iosAppVersion.lastVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minVersion, lastVersion);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IOsAppVersion {\n");
    
    sb.append("    minVersion: ").append(toIndentedString(minVersion)).append("\n");
    sb.append("    lastVersion: ").append(toIndentedString(lastVersion)).append("\n");
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
    out.writeValue(minVersion);
    out.writeValue(lastVersion);
  }

  IOsAppVersion(Parcel in) {
    minVersion = (String)in.readValue(null);
    lastVersion = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<IOsAppVersion> CREATOR = new Parcelable.Creator<IOsAppVersion>() {
    public IOsAppVersion createFromParcel(Parcel in) {
      return new IOsAppVersion(in);
    }
    public IOsAppVersion[] newArray(int size) {
      return new IOsAppVersion[size];
    }
  };
}
