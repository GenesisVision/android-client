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
import io.swagger.client.model.PostImageResize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PostImage
 */


public class PostImage implements Parcelable {
  @SerializedName("id")
  private String id = null;

  @SerializedName("resizes")
  private List<PostImageResize> resizes = null;

  public PostImage() {
  }
  public PostImage id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PostImage resizes(List<PostImageResize> resizes) {
    this.resizes = resizes;
    return this;
  }

  public PostImage addResizesItem(PostImageResize resizesItem) {
    if (this.resizes == null) {
      this.resizes = new ArrayList<PostImageResize>();
    }
    this.resizes.add(resizesItem);
    return this;
  }

   /**
   * Get resizes
   * @return resizes
  **/
  @Schema(description = "")
  public List<PostImageResize> getResizes() {
    return resizes;
  }

  public void setResizes(List<PostImageResize> resizes) {
    this.resizes = resizes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostImage postImage = (PostImage) o;
    return Objects.equals(this.id, postImage.id) &&
        Objects.equals(this.resizes, postImage.resizes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resizes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostImage {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resizes: ").append(toIndentedString(resizes)).append("\n");
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
    out.writeValue(resizes);
  }

  PostImage(Parcel in) {
    id = (String)in.readValue(null);
    resizes = (List<PostImageResize>)in.readValue(PostImageResize.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PostImage> CREATOR = new Parcelable.Creator<PostImage>() {
    public PostImage createFromParcel(Parcel in) {
      return new PostImage(in);
    }
    public PostImage[] newArray(int size) {
      return new PostImage[size];
    }
  };
}
