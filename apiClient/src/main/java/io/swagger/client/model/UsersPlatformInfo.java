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
import io.swagger.client.model.BetaTestingType;
import io.swagger.client.model.Tag;
import io.swagger.client.model.UsersSocialLinkInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * UsersPlatformInfo
 */


public class UsersPlatformInfo implements Parcelable {
  @SerializedName("tags")
  private List<Tag> tags = null;

  @SerializedName("availableBetaFeatures")
  private List<BetaTestingType> availableBetaFeatures = null;

  @SerializedName("socialLinkTypes")
  private List<UsersSocialLinkInfo> socialLinkTypes = null;

  public UsersPlatformInfo() {
  }
  public UsersPlatformInfo tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public UsersPlatformInfo addTagsItem(Tag tagsItem) {
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

  public UsersPlatformInfo availableBetaFeatures(List<BetaTestingType> availableBetaFeatures) {
    this.availableBetaFeatures = availableBetaFeatures;
    return this;
  }

  public UsersPlatformInfo addAvailableBetaFeaturesItem(BetaTestingType availableBetaFeaturesItem) {
    if (this.availableBetaFeatures == null) {
      this.availableBetaFeatures = new ArrayList<BetaTestingType>();
    }
    this.availableBetaFeatures.add(availableBetaFeaturesItem);
    return this;
  }

   /**
   * Get availableBetaFeatures
   * @return availableBetaFeatures
  **/
  @Schema(description = "")
  public List<BetaTestingType> getAvailableBetaFeatures() {
    return availableBetaFeatures;
  }

  public void setAvailableBetaFeatures(List<BetaTestingType> availableBetaFeatures) {
    this.availableBetaFeatures = availableBetaFeatures;
  }

  public UsersPlatformInfo socialLinkTypes(List<UsersSocialLinkInfo> socialLinkTypes) {
    this.socialLinkTypes = socialLinkTypes;
    return this;
  }

  public UsersPlatformInfo addSocialLinkTypesItem(UsersSocialLinkInfo socialLinkTypesItem) {
    if (this.socialLinkTypes == null) {
      this.socialLinkTypes = new ArrayList<UsersSocialLinkInfo>();
    }
    this.socialLinkTypes.add(socialLinkTypesItem);
    return this;
  }

   /**
   * Get socialLinkTypes
   * @return socialLinkTypes
  **/
  @Schema(description = "")
  public List<UsersSocialLinkInfo> getSocialLinkTypes() {
    return socialLinkTypes;
  }

  public void setSocialLinkTypes(List<UsersSocialLinkInfo> socialLinkTypes) {
    this.socialLinkTypes = socialLinkTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersPlatformInfo usersPlatformInfo = (UsersPlatformInfo) o;
    return Objects.equals(this.tags, usersPlatformInfo.tags) &&
        Objects.equals(this.availableBetaFeatures, usersPlatformInfo.availableBetaFeatures) &&
        Objects.equals(this.socialLinkTypes, usersPlatformInfo.socialLinkTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tags, availableBetaFeatures, socialLinkTypes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersPlatformInfo {\n");
    
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    availableBetaFeatures: ").append(toIndentedString(availableBetaFeatures)).append("\n");
    sb.append("    socialLinkTypes: ").append(toIndentedString(socialLinkTypes)).append("\n");
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
    out.writeValue(tags);
    out.writeValue(availableBetaFeatures);
    out.writeValue(socialLinkTypes);
  }

  UsersPlatformInfo(Parcel in) {
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    availableBetaFeatures = (List<BetaTestingType>)in.readValue(BetaTestingType.class.getClassLoader());
    socialLinkTypes = (List<UsersSocialLinkInfo>)in.readValue(UsersSocialLinkInfo.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<UsersPlatformInfo> CREATOR = new Parcelable.Creator<UsersPlatformInfo>() {
    public UsersPlatformInfo createFromParcel(Parcel in) {
      return new UsersPlatformInfo(in);
    }
    public UsersPlatformInfo[] newArray(int size) {
      return new UsersPlatformInfo[size];
    }
  };
}
