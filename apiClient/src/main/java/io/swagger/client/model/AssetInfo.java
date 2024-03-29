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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * AssetInfo
 */


public class AssetInfo implements Parcelable {
  @SerializedName("name")
  private String name = null;

  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("chartSymbol")
  private String chartSymbol = null;

  @SerializedName("lastModified")
  private DateTime lastModified = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  @SerializedName("socialLinks")
  private List<SocialLinkViewModel> socialLinks = null;

  public AssetInfo() {
  }
  public AssetInfo name(String name) {
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

  public AssetInfo symbol(String symbol) {
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

  public AssetInfo logoUrl(String logoUrl) {
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

  public AssetInfo description(String description) {
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

  public AssetInfo chartSymbol(String chartSymbol) {
    this.chartSymbol = chartSymbol;
    return this;
  }

   /**
   * Get chartSymbol
   * @return chartSymbol
  **/
  @Schema(description = "")
  public String getChartSymbol() {
    return chartSymbol;
  }

  public void setChartSymbol(String chartSymbol) {
    this.chartSymbol = chartSymbol;
  }

  public AssetInfo lastModified(DateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

   /**
   * Get lastModified
   * @return lastModified
  **/
  @Schema(description = "")
  public DateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(DateTime lastModified) {
    this.lastModified = lastModified;
  }

  public AssetInfo tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public AssetInfo addTagsItem(Tag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<Tag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @Schema(description = "")
  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public AssetInfo socialLinks(List<SocialLinkViewModel> socialLinks) {
    this.socialLinks = socialLinks;
    return this;
  }

  public AssetInfo addSocialLinksItem(SocialLinkViewModel socialLinksItem) {
    if (this.socialLinks == null) {
      this.socialLinks = new ArrayList<SocialLinkViewModel>();
    }
    this.socialLinks.add(socialLinksItem);
    return this;
  }

   /**
   * Get socialLinks
   * @return socialLinks
  **/
  @Schema(description = "")
  public List<SocialLinkViewModel> getSocialLinks() {
    return socialLinks;
  }

  public void setSocialLinks(List<SocialLinkViewModel> socialLinks) {
    this.socialLinks = socialLinks;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssetInfo assetInfo = (AssetInfo) o;
    return Objects.equals(this.name, assetInfo.name) &&
        Objects.equals(this.symbol, assetInfo.symbol) &&
        Objects.equals(this.logoUrl, assetInfo.logoUrl) &&
        Objects.equals(this.description, assetInfo.description) &&
        Objects.equals(this.chartSymbol, assetInfo.chartSymbol) &&
        Objects.equals(this.lastModified, assetInfo.lastModified) &&
        Objects.equals(this.tags, assetInfo.tags) &&
        Objects.equals(this.socialLinks, assetInfo.socialLinks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, symbol, logoUrl, description, chartSymbol, lastModified, tags, socialLinks);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssetInfo {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    chartSymbol: ").append(toIndentedString(chartSymbol)).append("\n");
    sb.append("    lastModified: ").append(toIndentedString(lastModified)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    socialLinks: ").append(toIndentedString(socialLinks)).append("\n");
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
    out.writeValue(name);
    out.writeValue(symbol);
    out.writeValue(logoUrl);
    out.writeValue(description);
    out.writeValue(chartSymbol);
    out.writeValue(lastModified);
    out.writeValue(tags);
    out.writeValue(socialLinks);
  }

  AssetInfo(Parcel in) {
    name = (String)in.readValue(null);
    symbol = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    description = (String)in.readValue(null);
    chartSymbol = (String)in.readValue(null);
    lastModified = (DateTime)in.readValue(DateTime.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    socialLinks = (List<SocialLinkViewModel>)in.readValue(SocialLinkViewModel.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<AssetInfo> CREATOR = new Parcelable.Creator<AssetInfo>() {
    public AssetInfo createFromParcel(Parcel in) {
      return new AssetInfo(in);
    }
    public AssetInfo[] newArray(int size) {
      return new AssetInfo[size];
    }
  };
}
