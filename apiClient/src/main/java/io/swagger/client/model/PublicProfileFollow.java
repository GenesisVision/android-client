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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PublicProfileFollow
 */


public class PublicProfileFollow implements Parcelable
{
  public static final Parcelable.Creator<PublicProfileFollow> CREATOR = new Parcelable.Creator<PublicProfileFollow>()
  {
    public PublicProfileFollow createFromParcel(Parcel in) {
      return new PublicProfileFollow(in);
    }

    public PublicProfileFollow[] newArray(int size) {
      return new PublicProfileFollow[size];
    }
  };

  @SerializedName("followers")
  private List<ProfilePublicShort> followers = null;

  @SerializedName("following")
  private List<ProfilePublicShort> following = null;

  public PublicProfileFollow() {
  }

  PublicProfileFollow(Parcel in) {
    followers = (List<ProfilePublicShort>) in.readValue(ProfilePublicShort.class.getClassLoader());
    following = (List<ProfilePublicShort>) in.readValue(ProfilePublicShort.class.getClassLoader());
  }

  public PublicProfileFollow followers(List<ProfilePublicShort> followers) {
    this.followers = followers;
    return this;
  }

  public PublicProfileFollow addFollowersItem(ProfilePublicShort followersItem) {
    if (this.followers == null) {
      this.followers = new ArrayList<ProfilePublicShort>();
    }
    this.followers.add(followersItem);
    return this;
  }

  /**
   * Get followers
   *
   * @return followers
   **/
  @Schema(description = "")
  public List<ProfilePublicShort> getFollowers() {
    return followers;
  }

  public void setFollowers(List<ProfilePublicShort> followers) {
    this.followers = followers;
  }

  public PublicProfileFollow following(List<ProfilePublicShort> following) {
    this.following = following;
    return this;
  }

  public PublicProfileFollow addFollowingItem(ProfilePublicShort followingItem) {
    if (this.following == null) {
      this.following = new ArrayList<ProfilePublicShort>();
    }
    this.following.add(followingItem);
    return this;
  }

  /**
   * Get following
   *
   * @return following
   **/
  @Schema(description = "")
  public List<ProfilePublicShort> getFollowing() {
    return following;
  }

  public void setFollowing(List<ProfilePublicShort> following) {
    this.following = following;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicProfileFollow publicProfileFollow = (PublicProfileFollow) o;
    return Objects.equals(this.followers, publicProfileFollow.followers) &&
            Objects.equals(this.following, publicProfileFollow.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(followers, following);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicProfileFollow {\n");

    sb.append("    followers: ").append(toIndentedString(followers)).append("\n");
    sb.append("    following: ").append(toIndentedString(following)).append("\n");
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
    out.writeValue(followers);
    out.writeValue(following);
  }

  public int describeContents() {
    return 0;
  }
}
