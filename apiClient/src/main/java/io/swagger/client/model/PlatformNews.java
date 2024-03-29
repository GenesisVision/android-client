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
 * PlatformNews
 */


public class PlatformNews implements Parcelable {
  @SerializedName("date")
  private DateTime date = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("body")
  private String body = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("isHot")
  private Boolean isHot = null;

  public PlatformNews() {
  }
  public PlatformNews date(DateTime date) {
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

  public PlatformNews title(String title) {
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

  public PlatformNews body(String body) {
    this.body = body;
    return this;
  }

   /**
   * Get body
   * @return body
  **/
  @Schema(description = "")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public PlatformNews logoUrl(String logoUrl) {
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

  public PlatformNews url(String url) {
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

  public PlatformNews isHot(Boolean isHot) {
    this.isHot = isHot;
    return this;
  }

   /**
   * Get isHot
   * @return isHot
  **/
  @Schema(description = "")
  public Boolean isIsHot() {
    return isHot;
  }

  public void setIsHot(Boolean isHot) {
    this.isHot = isHot;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformNews platformNews = (PlatformNews) o;
    return Objects.equals(this.date, platformNews.date) &&
        Objects.equals(this.title, platformNews.title) &&
        Objects.equals(this.body, platformNews.body) &&
        Objects.equals(this.logoUrl, platformNews.logoUrl) &&
        Objects.equals(this.url, platformNews.url) &&
        Objects.equals(this.isHot, platformNews.isHot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, title, body, logoUrl, url, isHot);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformNews {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    isHot: ").append(toIndentedString(isHot)).append("\n");
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
    out.writeValue(date);
    out.writeValue(title);
    out.writeValue(body);
    out.writeValue(logoUrl);
    out.writeValue(url);
    out.writeValue(isHot);
  }

  PlatformNews(Parcel in) {
    date = (DateTime)in.readValue(DateTime.class.getClassLoader());
    title = (String)in.readValue(null);
    body = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    url = (String)in.readValue(null);
    isHot = (Boolean)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PlatformNews> CREATOR = new Parcelable.Creator<PlatformNews>() {
    public PlatformNews createFromParcel(Parcel in) {
      return new PlatformNews(in);
    }
    public PlatformNews[] newArray(int size) {
      return new PlatformNews[size];
    }
  };
}
