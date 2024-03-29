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
import io.swagger.client.model.AssetPublicDetails;
import io.swagger.client.model.BrokerDetails;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProfilePublic;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFullTradingAccountDetails;
import io.swagger.client.model.ProgramFollowOwnerActions;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * ProgramFollowDetailsFull
 */


public class ProgramFollowDetailsFull implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("publicInfo")
  private AssetPublicDetails publicInfo = null;

  @SerializedName("tradingAccountInfo")
  private ProgramFollowDetailsFullTradingAccountDetails tradingAccountInfo = null;

  @SerializedName("owner")
  private ProfilePublic owner = null;

  @SerializedName("brokerDetails")
  private BrokerDetails brokerDetails = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  @SerializedName("programDetails")
  private ProgramDetailsFull programDetails = null;

  @SerializedName("followDetails")
  private FollowDetailsFull followDetails = null;

  @SerializedName("ownerActions")
  private ProgramFollowOwnerActions ownerActions = null;

  public ProgramFollowDetailsFull() {
  }
  public ProgramFollowDetailsFull id(UUID id) {
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

  public ProgramFollowDetailsFull publicInfo(AssetPublicDetails publicInfo) {
    this.publicInfo = publicInfo;
    return this;
  }

   /**
   * Get publicInfo
   * @return publicInfo
  **/
  @Schema(description = "")
  public AssetPublicDetails getPublicInfo() {
    return publicInfo;
  }

  public void setPublicInfo(AssetPublicDetails publicInfo) {
    this.publicInfo = publicInfo;
  }

  public ProgramFollowDetailsFull tradingAccountInfo(ProgramFollowDetailsFullTradingAccountDetails tradingAccountInfo) {
    this.tradingAccountInfo = tradingAccountInfo;
    return this;
  }

   /**
   * Get tradingAccountInfo
   * @return tradingAccountInfo
  **/
  @Schema(description = "")
  public ProgramFollowDetailsFullTradingAccountDetails getTradingAccountInfo() {
    return tradingAccountInfo;
  }

  public void setTradingAccountInfo(ProgramFollowDetailsFullTradingAccountDetails tradingAccountInfo) {
    this.tradingAccountInfo = tradingAccountInfo;
  }

  public ProgramFollowDetailsFull owner(ProfilePublic owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Get owner
   * @return owner
  **/
  @Schema(description = "")
  public ProfilePublic getOwner() {
    return owner;
  }

  public void setOwner(ProfilePublic owner) {
    this.owner = owner;
  }

  public ProgramFollowDetailsFull brokerDetails(BrokerDetails brokerDetails) {
    this.brokerDetails = brokerDetails;
    return this;
  }

   /**
   * Get brokerDetails
   * @return brokerDetails
  **/
  @Schema(description = "")
  public BrokerDetails getBrokerDetails() {
    return brokerDetails;
  }

  public void setBrokerDetails(BrokerDetails brokerDetails) {
    this.brokerDetails = brokerDetails;
  }

  public ProgramFollowDetailsFull tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public ProgramFollowDetailsFull addTagsItem(Tag tagsItem) {
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

  public ProgramFollowDetailsFull programDetails(ProgramDetailsFull programDetails) {
    this.programDetails = programDetails;
    return this;
  }

   /**
   * Get programDetails
   * @return programDetails
  **/
  @Schema(description = "")
  public ProgramDetailsFull getProgramDetails() {
    return programDetails;
  }

  public void setProgramDetails(ProgramDetailsFull programDetails) {
    this.programDetails = programDetails;
  }

  public ProgramFollowDetailsFull followDetails(FollowDetailsFull followDetails) {
    this.followDetails = followDetails;
    return this;
  }

   /**
   * Get followDetails
   * @return followDetails
  **/
  @Schema(description = "")
  public FollowDetailsFull getFollowDetails() {
    return followDetails;
  }

  public void setFollowDetails(FollowDetailsFull followDetails) {
    this.followDetails = followDetails;
  }

  public ProgramFollowDetailsFull ownerActions(ProgramFollowOwnerActions ownerActions) {
    this.ownerActions = ownerActions;
    return this;
  }

   /**
   * Get ownerActions
   * @return ownerActions
  **/
  @Schema(description = "")
  public ProgramFollowOwnerActions getOwnerActions() {
    return ownerActions;
  }

  public void setOwnerActions(ProgramFollowOwnerActions ownerActions) {
    this.ownerActions = ownerActions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramFollowDetailsFull programFollowDetailsFull = (ProgramFollowDetailsFull) o;
    return Objects.equals(this.id, programFollowDetailsFull.id) &&
        Objects.equals(this.publicInfo, programFollowDetailsFull.publicInfo) &&
        Objects.equals(this.tradingAccountInfo, programFollowDetailsFull.tradingAccountInfo) &&
        Objects.equals(this.owner, programFollowDetailsFull.owner) &&
        Objects.equals(this.brokerDetails, programFollowDetailsFull.brokerDetails) &&
        Objects.equals(this.tags, programFollowDetailsFull.tags) &&
        Objects.equals(this.programDetails, programFollowDetailsFull.programDetails) &&
        Objects.equals(this.followDetails, programFollowDetailsFull.followDetails) &&
        Objects.equals(this.ownerActions, programFollowDetailsFull.ownerActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicInfo, tradingAccountInfo, owner, brokerDetails, tags, programDetails, followDetails, ownerActions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramFollowDetailsFull {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    publicInfo: ").append(toIndentedString(publicInfo)).append("\n");
    sb.append("    tradingAccountInfo: ").append(toIndentedString(tradingAccountInfo)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    brokerDetails: ").append(toIndentedString(brokerDetails)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    programDetails: ").append(toIndentedString(programDetails)).append("\n");
    sb.append("    followDetails: ").append(toIndentedString(followDetails)).append("\n");
    sb.append("    ownerActions: ").append(toIndentedString(ownerActions)).append("\n");
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
    out.writeValue(publicInfo);
    out.writeValue(tradingAccountInfo);
    out.writeValue(owner);
    out.writeValue(brokerDetails);
    out.writeValue(tags);
    out.writeValue(programDetails);
    out.writeValue(followDetails);
    out.writeValue(ownerActions);
  }

  ProgramFollowDetailsFull(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    publicInfo = (AssetPublicDetails)in.readValue(AssetPublicDetails.class.getClassLoader());
    tradingAccountInfo = (ProgramFollowDetailsFullTradingAccountDetails)in.readValue(ProgramFollowDetailsFullTradingAccountDetails.class.getClassLoader());
    owner = (ProfilePublic)in.readValue(ProfilePublic.class.getClassLoader());
    brokerDetails = (BrokerDetails)in.readValue(BrokerDetails.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    programDetails = (ProgramDetailsFull)in.readValue(ProgramDetailsFull.class.getClassLoader());
    followDetails = (FollowDetailsFull)in.readValue(FollowDetailsFull.class.getClassLoader());
    ownerActions = (ProgramFollowOwnerActions)in.readValue(ProgramFollowOwnerActions.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ProgramFollowDetailsFull> CREATOR = new Parcelable.Creator<ProgramFollowDetailsFull>() {
    public ProgramFollowDetailsFull createFromParcel(Parcel in) {
      return new ProgramFollowDetailsFull(in);
    }
    public ProgramFollowDetailsFull[] newArray(int size) {
      return new ProgramFollowDetailsFull[size];
    }
  };
}
