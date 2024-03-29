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
import io.swagger.client.model.Post;
import io.swagger.client.model.PostAssetDetailsWithPrices;
import io.swagger.client.model.PostEvent;
import io.swagger.client.model.PostLink;
import io.swagger.client.model.PostPlatformAssetDetailsWithPrices;
import io.swagger.client.model.ProfilePublic;
import io.swagger.client.model.SocialPostTagType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PostTag
 */


public class PostTag implements Parcelable {
  @SerializedName("title")
  private String title = null;

  @SerializedName("number")
  private Integer number = null;

  @SerializedName("type")
  private SocialPostTagType type = null;

  @SerializedName("assetDetails")
  private PostAssetDetailsWithPrices assetDetails = null;

  @SerializedName("userDetails")
  private ProfilePublic userDetails = null;

  @SerializedName("platformAssetDetails")
  private PostPlatformAssetDetailsWithPrices platformAssetDetails = null;

  @SerializedName("post")
  private Post post = null;

  @SerializedName("event")
  private PostEvent event = null;

  @SerializedName("link")
  private PostLink link = null;

  public PostTag() {
  }
  public PostTag title(String title) {
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

  public PostTag number(Integer number) {
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @Schema(description = "")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public PostTag type(SocialPostTagType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public SocialPostTagType getType() {
    return type;
  }

  public void setType(SocialPostTagType type) {
    this.type = type;
  }

  public PostTag assetDetails(PostAssetDetailsWithPrices assetDetails) {
    this.assetDetails = assetDetails;
    return this;
  }

   /**
   * Get assetDetails
   * @return assetDetails
  **/
  @Schema(description = "")
  public PostAssetDetailsWithPrices getAssetDetails() {
    return assetDetails;
  }

  public void setAssetDetails(PostAssetDetailsWithPrices assetDetails) {
    this.assetDetails = assetDetails;
  }

  public PostTag userDetails(ProfilePublic userDetails) {
    this.userDetails = userDetails;
    return this;
  }

   /**
   * Get userDetails
   * @return userDetails
  **/
  @Schema(description = "")
  public ProfilePublic getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(ProfilePublic userDetails) {
    this.userDetails = userDetails;
  }

  public PostTag platformAssetDetails(PostPlatformAssetDetailsWithPrices platformAssetDetails) {
    this.platformAssetDetails = platformAssetDetails;
    return this;
  }

   /**
   * Get platformAssetDetails
   * @return platformAssetDetails
  **/
  @Schema(description = "")
  public PostPlatformAssetDetailsWithPrices getPlatformAssetDetails() {
    return platformAssetDetails;
  }

  public void setPlatformAssetDetails(PostPlatformAssetDetailsWithPrices platformAssetDetails) {
    this.platformAssetDetails = platformAssetDetails;
  }

  public PostTag post(Post post) {
    this.post = post;
    return this;
  }

   /**
   * Get post
   * @return post
  **/
  @Schema(description = "")
  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public PostTag event(PostEvent event) {
    this.event = event;
    return this;
  }

   /**
   * Get event
   * @return event
  **/
  @Schema(description = "")
  public PostEvent getEvent() {
    return event;
  }

  public void setEvent(PostEvent event) {
    this.event = event;
  }

  public PostTag link(PostLink link) {
    this.link = link;
    return this;
  }

   /**
   * Get link
   * @return link
  **/
  @Schema(description = "")
  public PostLink getLink() {
    return link;
  }

  public void setLink(PostLink link) {
    this.link = link;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostTag postTag = (PostTag) o;
    return Objects.equals(this.title, postTag.title) &&
        Objects.equals(this.number, postTag.number) &&
        Objects.equals(this.type, postTag.type) &&
        Objects.equals(this.assetDetails, postTag.assetDetails) &&
        Objects.equals(this.userDetails, postTag.userDetails) &&
        Objects.equals(this.platformAssetDetails, postTag.platformAssetDetails) &&
        Objects.equals(this.post, postTag.post) &&
        Objects.equals(this.event, postTag.event) &&
        Objects.equals(this.link, postTag.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, number, type, assetDetails, userDetails, platformAssetDetails, post, event, link);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostTag {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    assetDetails: ").append(toIndentedString(assetDetails)).append("\n");
    sb.append("    userDetails: ").append(toIndentedString(userDetails)).append("\n");
    sb.append("    platformAssetDetails: ").append(toIndentedString(platformAssetDetails)).append("\n");
    sb.append("    post: ").append(toIndentedString(post)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
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
    out.writeValue(number);
    out.writeValue(type);
    out.writeValue(assetDetails);
    out.writeValue(userDetails);
    out.writeValue(platformAssetDetails);
    out.writeValue(post);
    out.writeValue(event);
    out.writeValue(link);
  }

  PostTag(Parcel in) {
    title = (String)in.readValue(null);
    number = (Integer)in.readValue(null);
    type = (SocialPostTagType)in.readValue(SocialPostTagType.class.getClassLoader());
    assetDetails = (PostAssetDetailsWithPrices)in.readValue(PostAssetDetailsWithPrices.class.getClassLoader());
    userDetails = (ProfilePublic)in.readValue(ProfilePublic.class.getClassLoader());
    platformAssetDetails = (PostPlatformAssetDetailsWithPrices)in.readValue(PostPlatformAssetDetailsWithPrices.class.getClassLoader());
    post = (Post)in.readValue(Post.class.getClassLoader());
    event = (PostEvent)in.readValue(PostEvent.class.getClassLoader());
    link = (PostLink)in.readValue(PostLink.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PostTag> CREATOR = new Parcelable.Creator<PostTag>() {
    public PostTag createFromParcel(Parcel in) {
      return new PostTag(in);
    }
    public PostTag[] newArray(int size) {
      return new PostTag[size];
    }
  };
}
