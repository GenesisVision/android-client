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
import android.os.Parcelable;
import android.os.Parcel;
/**
 * ProgramAssetDetails
 */


public class ProgramAssetDetails implements Parcelable {
  @SerializedName("level")
  private Integer level = null;

  @SerializedName("levelProgress")
  private Double levelProgress = null;

  public ProgramAssetDetails() {
  }
  public ProgramAssetDetails level(Integer level) {
    this.level = level;
    return this;
  }

   /**
   * Get level
   * @return level
  **/
  @Schema(description = "")
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public ProgramAssetDetails levelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
    return this;
  }

   /**
   * Get levelProgress
   * @return levelProgress
  **/
  @Schema(description = "")
  public Double getLevelProgress() {
    return levelProgress;
  }

  public void setLevelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramAssetDetails programAssetDetails = (ProgramAssetDetails) o;
    return Objects.equals(this.level, programAssetDetails.level) &&
        Objects.equals(this.levelProgress, programAssetDetails.levelProgress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, levelProgress);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramAssetDetails {\n");
    
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    levelProgress: ").append(toIndentedString(levelProgress)).append("\n");
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
    out.writeValue(level);
    out.writeValue(levelProgress);
  }

  ProgramAssetDetails(Parcel in) {
    level = (Integer)in.readValue(null);
    levelProgress = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ProgramAssetDetails> CREATOR = new Parcelable.Creator<ProgramAssetDetails>() {
    public ProgramAssetDetails createFromParcel(Parcel in) {
      return new ProgramAssetDetails(in);
    }
    public ProgramAssetDetails[] newArray(int size) {
      return new ProgramAssetDetails[size];
    }
  };
}
