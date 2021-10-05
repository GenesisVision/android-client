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

import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * BasePlatformAsset
 */


public class BasePlatformAsset implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("asset")
  private String asset = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("provider")
  private AssetProvider provider = null;

  public BasePlatformAsset() {
  }
  public BasePlatformAsset id(UUID id) {
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

  public BasePlatformAsset name(String name) {
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

  public BasePlatformAsset asset(String asset) {
    this.asset = asset;
    return this;
  }

   /**
   * Get asset
   * @return asset
  **/
  @Schema(description = "")
  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public BasePlatformAsset description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BasePlatformAsset logoUrl(String logoUrl) {
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

  public BasePlatformAsset color(String color) {
    this.color = color;
    return this;
  }

   /**
   * Get color
   * @return color
  **/
  @Schema(description = "")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public BasePlatformAsset url(String url) {
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

  public BasePlatformAsset provider(AssetProvider provider) {
    this.provider = provider;
    return this;
  }

   /**
   * Get provider
   * @return provider
  **/
  @Schema(description = "")
  public AssetProvider getProvider() {
    return provider;
  }

  public void setProvider(AssetProvider provider) {
    this.provider = provider;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BasePlatformAsset basePlatformAsset = (BasePlatformAsset) o;
    return Objects.equals(this.id, basePlatformAsset.id) &&
        Objects.equals(this.name, basePlatformAsset.name) &&
        Objects.equals(this.asset, basePlatformAsset.asset) &&
        Objects.equals(this.description, basePlatformAsset.description) &&
        Objects.equals(this.logoUrl, basePlatformAsset.logoUrl) &&
        Objects.equals(this.color, basePlatformAsset.color) &&
        Objects.equals(this.url, basePlatformAsset.url) &&
        Objects.equals(this.provider, basePlatformAsset.provider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, asset, description, logoUrl, color, url, provider);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BasePlatformAsset {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
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
    out.writeValue(name);
    out.writeValue(asset);
    out.writeValue(description);
    out.writeValue(logoUrl);
    out.writeValue(color);
    out.writeValue(url);
    out.writeValue(provider);
  }

  BasePlatformAsset(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    name = (String)in.readValue(null);
    asset = (String)in.readValue(null);
    description = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    color = (String)in.readValue(null);
    url = (String)in.readValue(null);
    provider = (AssetProvider)in.readValue(AssetProvider.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<BasePlatformAsset> CREATOR = new Parcelable.Creator<BasePlatformAsset>() {
    public BasePlatformAsset createFromParcel(Parcel in) {
      return new BasePlatformAsset(in);
    }
    public BasePlatformAsset[] newArray(int size) {
      return new BasePlatformAsset[size];
    }
  };
}
