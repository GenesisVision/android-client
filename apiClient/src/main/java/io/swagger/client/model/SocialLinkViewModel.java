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
import io.swagger.client.model.SocialLinkType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * SocialLinkViewModel
 */


public class SocialLinkViewModel implements Parcelable {
  @SerializedName("url")
  private String url = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("value")
  private String value = null;

  @SerializedName("type")
  private SocialLinkType type = null;

  public SocialLinkViewModel() {
  }
  public SocialLinkViewModel url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @Schema(description = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public SocialLinkViewModel logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

   /**
   * Get logoUrl
   * @return logoUrl
  **/
  @Schema(description = "")
  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public SocialLinkViewModel name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SocialLinkViewModel value(String value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public SocialLinkViewModel type(SocialLinkType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public SocialLinkType getType() {
    return type;
  }

  public void setType(SocialLinkType type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SocialLinkViewModel socialLinkViewModel = (SocialLinkViewModel) o;
    return Objects.equals(this.url, socialLinkViewModel.url) &&
        Objects.equals(this.logoUrl, socialLinkViewModel.logoUrl) &&
        Objects.equals(this.name, socialLinkViewModel.name) &&
        Objects.equals(this.value, socialLinkViewModel.value) &&
        Objects.equals(this.type, socialLinkViewModel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, logoUrl, name, value, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SocialLinkViewModel {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
    out.writeValue(url);
    out.writeValue(logoUrl);
    out.writeValue(name);
    out.writeValue(value);
    out.writeValue(type);
  }

  SocialLinkViewModel(Parcel in) {
    url = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    name = (String)in.readValue(null);
    value = (String)in.readValue(null);
    type = (SocialLinkType)in.readValue(SocialLinkType.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<SocialLinkViewModel> CREATOR = new Parcelable.Creator<SocialLinkViewModel>() {
    public SocialLinkViewModel createFromParcel(Parcel in) {
      return new SocialLinkViewModel(in);
    }
    public SocialLinkViewModel[] newArray(int size) {
      return new SocialLinkViewModel[size];
    }
  };
}
