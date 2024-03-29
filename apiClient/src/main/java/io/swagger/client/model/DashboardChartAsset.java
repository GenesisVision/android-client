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
import io.swagger.client.model.AssetType;
import io.swagger.client.model.ProgramAssetDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * DashboardChartAsset
 */


public class DashboardChartAsset implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("assetType")
  private AssetType assetType = null;

  @SerializedName("programDetails")
  private ProgramAssetDetails programDetails = null;

  @SerializedName("isPrivateAccount")
  private Boolean isPrivateAccount = null;

  public DashboardChartAsset() {
  }
  public DashboardChartAsset id(UUID id) {
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

  public DashboardChartAsset logoUrl(String logoUrl) {
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

  public DashboardChartAsset color(String color) {
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

  public DashboardChartAsset title(String title) {
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

  public DashboardChartAsset url(String url) {
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

  public DashboardChartAsset assetType(AssetType assetType) {
    this.assetType = assetType;
    return this;
  }

   /**
   * Get assetType
   * @return assetType
  **/
  @Schema(description = "")
  public AssetType getAssetType() {
    return assetType;
  }

  public void setAssetType(AssetType assetType) {
    this.assetType = assetType;
  }

  public DashboardChartAsset programDetails(ProgramAssetDetails programDetails) {
    this.programDetails = programDetails;
    return this;
  }

   /**
   * Get programDetails
   * @return programDetails
  **/
  @Schema(description = "")
  public ProgramAssetDetails getProgramDetails() {
    return programDetails;
  }

  public void setProgramDetails(ProgramAssetDetails programDetails) {
    this.programDetails = programDetails;
  }

  public DashboardChartAsset isPrivateAccount(Boolean isPrivateAccount) {
    this.isPrivateAccount = isPrivateAccount;
    return this;
  }

   /**
   * Get isPrivateAccount
   * @return isPrivateAccount
  **/
  @Schema(description = "")
  public Boolean isIsPrivateAccount() {
    return isPrivateAccount;
  }

  public void setIsPrivateAccount(Boolean isPrivateAccount) {
    this.isPrivateAccount = isPrivateAccount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardChartAsset dashboardChartAsset = (DashboardChartAsset) o;
    return Objects.equals(this.id, dashboardChartAsset.id) &&
        Objects.equals(this.logoUrl, dashboardChartAsset.logoUrl) &&
        Objects.equals(this.color, dashboardChartAsset.color) &&
        Objects.equals(this.title, dashboardChartAsset.title) &&
        Objects.equals(this.url, dashboardChartAsset.url) &&
        Objects.equals(this.assetType, dashboardChartAsset.assetType) &&
        Objects.equals(this.programDetails, dashboardChartAsset.programDetails) &&
        Objects.equals(this.isPrivateAccount, dashboardChartAsset.isPrivateAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logoUrl, color, title, url, assetType, programDetails, isPrivateAccount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardChartAsset {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    assetType: ").append(toIndentedString(assetType)).append("\n");
    sb.append("    programDetails: ").append(toIndentedString(programDetails)).append("\n");
    sb.append("    isPrivateAccount: ").append(toIndentedString(isPrivateAccount)).append("\n");
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
    out.writeValue(logoUrl);
    out.writeValue(color);
    out.writeValue(title);
    out.writeValue(url);
    out.writeValue(assetType);
    out.writeValue(programDetails);
    out.writeValue(isPrivateAccount);
  }

  DashboardChartAsset(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    logoUrl = (String)in.readValue(null);
    color = (String)in.readValue(null);
    title = (String)in.readValue(null);
    url = (String)in.readValue(null);
    assetType = (AssetType)in.readValue(AssetType.class.getClassLoader());
    programDetails = (ProgramAssetDetails)in.readValue(ProgramAssetDetails.class.getClassLoader());
    isPrivateAccount = (Boolean)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<DashboardChartAsset> CREATOR = new Parcelable.Creator<DashboardChartAsset>() {
    public DashboardChartAsset createFromParcel(Parcel in) {
      return new DashboardChartAsset(in);
    }
    public DashboardChartAsset[] newArray(int size) {
      return new DashboardChartAsset[size];
    }
  };
}
