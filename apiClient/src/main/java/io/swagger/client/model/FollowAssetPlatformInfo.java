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
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.FollowCreateAssetPlatformInfo;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FollowAssetPlatformInfo
 */


public class FollowAssetPlatformInfo implements Parcelable {
  @SerializedName("facets")
  private List<AssetFacet> facets = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  @SerializedName("createFollowInfo")
  private FollowCreateAssetPlatformInfo createFollowInfo = null;

  @SerializedName("subscribeFixedCurrencies")
  private List<String> subscribeFixedCurrencies = null;

  public FollowAssetPlatformInfo() {
  }
  public FollowAssetPlatformInfo facets(List<AssetFacet> facets) {
    this.facets = facets;
    return this;
  }

  public FollowAssetPlatformInfo addFacetsItem(AssetFacet facetsItem) {
    if (this.facets == null) {
      this.facets = new ArrayList<AssetFacet>();
    }
    this.facets.add(facetsItem);
    return this;
  }

   /**
   * Get facets
   * @return facets
  **/
  @Schema(description = "")
  public List<AssetFacet> getFacets() {
    return facets;
  }

  public void setFacets(List<AssetFacet> facets) {
    this.facets = facets;
  }

  public FollowAssetPlatformInfo tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public FollowAssetPlatformInfo addTagsItem(Tag tagsItem) {
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

  public FollowAssetPlatformInfo createFollowInfo(FollowCreateAssetPlatformInfo createFollowInfo) {
    this.createFollowInfo = createFollowInfo;
    return this;
  }

   /**
   * Get createFollowInfo
   * @return createFollowInfo
  **/
  @Schema(description = "")
  public FollowCreateAssetPlatformInfo getCreateFollowInfo() {
    return createFollowInfo;
  }

  public void setCreateFollowInfo(FollowCreateAssetPlatformInfo createFollowInfo) {
    this.createFollowInfo = createFollowInfo;
  }

  public FollowAssetPlatformInfo subscribeFixedCurrencies(List<String> subscribeFixedCurrencies) {
    this.subscribeFixedCurrencies = subscribeFixedCurrencies;
    return this;
  }

  public FollowAssetPlatformInfo addSubscribeFixedCurrenciesItem(String subscribeFixedCurrenciesItem) {
    if (this.subscribeFixedCurrencies == null) {
      this.subscribeFixedCurrencies = new ArrayList<String>();
    }
    this.subscribeFixedCurrencies.add(subscribeFixedCurrenciesItem);
    return this;
  }

   /**
   * Get subscribeFixedCurrencies
   * @return subscribeFixedCurrencies
  **/
  @Schema(description = "")
  public List<String> getSubscribeFixedCurrencies() {
    return subscribeFixedCurrencies;
  }

  public void setSubscribeFixedCurrencies(List<String> subscribeFixedCurrencies) {
    this.subscribeFixedCurrencies = subscribeFixedCurrencies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowAssetPlatformInfo followAssetPlatformInfo = (FollowAssetPlatformInfo) o;
    return Objects.equals(this.facets, followAssetPlatformInfo.facets) &&
        Objects.equals(this.tags, followAssetPlatformInfo.tags) &&
        Objects.equals(this.createFollowInfo, followAssetPlatformInfo.createFollowInfo) &&
        Objects.equals(this.subscribeFixedCurrencies, followAssetPlatformInfo.subscribeFixedCurrencies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(facets, tags, createFollowInfo, subscribeFixedCurrencies);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowAssetPlatformInfo {\n");
    
    sb.append("    facets: ").append(toIndentedString(facets)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    createFollowInfo: ").append(toIndentedString(createFollowInfo)).append("\n");
    sb.append("    subscribeFixedCurrencies: ").append(toIndentedString(subscribeFixedCurrencies)).append("\n");
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
    out.writeValue(facets);
    out.writeValue(tags);
    out.writeValue(createFollowInfo);
    out.writeValue(subscribeFixedCurrencies);
  }

  FollowAssetPlatformInfo(Parcel in) {
    facets = (List<AssetFacet>)in.readValue(AssetFacet.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    createFollowInfo = (FollowCreateAssetPlatformInfo)in.readValue(FollowCreateAssetPlatformInfo.class.getClassLoader());
    subscribeFixedCurrencies = (List<String>)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FollowAssetPlatformInfo> CREATOR = new Parcelable.Creator<FollowAssetPlatformInfo>() {
    public FollowAssetPlatformInfo createFromParcel(Parcel in) {
      return new FollowAssetPlatformInfo(in);
    }
    public FollowAssetPlatformInfo[] newArray(int size) {
      return new FollowAssetPlatformInfo[size];
    }
  };
}
