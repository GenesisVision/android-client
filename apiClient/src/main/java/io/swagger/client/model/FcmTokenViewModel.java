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
import io.swagger.client.model.AppPlatform;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FcmTokenViewModel
 */


public class FcmTokenViewModel implements Parcelable {
  @SerializedName("token")
  private String token = null;

  @SerializedName("platform")
  private AppPlatform platform = null;

  public FcmTokenViewModel() {
  }
  public FcmTokenViewModel token(String token) {
    this.token = token;
    return this;
  }

   /**
   * Get token
   * @return token
  **/
  @Schema(required = true, description = "")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public FcmTokenViewModel platform(AppPlatform platform) {
    this.platform = platform;
    return this;
  }

   /**
   * Get platform
   * @return platform
  **/
  @Schema(description = "")
  public AppPlatform getPlatform() {
    return platform;
  }

  public void setPlatform(AppPlatform platform) {
    this.platform = platform;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FcmTokenViewModel fcmTokenViewModel = (FcmTokenViewModel) o;
    return Objects.equals(this.token, fcmTokenViewModel.token) &&
        Objects.equals(this.platform, fcmTokenViewModel.platform);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, platform);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FcmTokenViewModel {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
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
    out.writeValue(token);
    out.writeValue(platform);
  }

  FcmTokenViewModel(Parcel in) {
    token = (String)in.readValue(null);
    platform = (AppPlatform)in.readValue(AppPlatform.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FcmTokenViewModel> CREATOR = new Parcelable.Creator<FcmTokenViewModel>() {
    public FcmTokenViewModel createFromParcel(Parcel in) {
      return new FcmTokenViewModel(in);
    }
    public FcmTokenViewModel[] newArray(int size) {
      return new FcmTokenViewModel[size];
    }
  };
}
