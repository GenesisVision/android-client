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
 * TransactionAssetDetails
 */


public class TransactionAssetDetails implements Parcelable
{
  public static final Parcelable.Creator<TransactionAssetDetails> CREATOR = new Parcelable.Creator<TransactionAssetDetails>()
  {
    public TransactionAssetDetails createFromParcel(Parcel in) {
      return new TransactionAssetDetails(in);
    }

    public TransactionAssetDetails[] newArray(int size) {
      return new TransactionAssetDetails[size];
    }
  };

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

  @SerializedName("description")
  private String description = null;

  @SerializedName("manager")
  private String manager = null;

  public TransactionAssetDetails() {
  }

  TransactionAssetDetails(Parcel in) {
    id = (UUID) in.readValue(UUID.class.getClassLoader());
    logoUrl = (String) in.readValue(null);
    color = (String) in.readValue(null);
    title = (String) in.readValue(null);
    url = (String) in.readValue(null);
    assetType = (AssetType) in.readValue(AssetType.class.getClassLoader());
    programDetails = (ProgramAssetDetails) in.readValue(ProgramAssetDetails.class.getClassLoader());
    description = (String) in.readValue(null);
    manager = (String) in.readValue(null);
  }

  public TransactionAssetDetails id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   **/
  @Schema(description = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public TransactionAssetDetails logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

  /**
   * Get logoUrl
   *
   * @return logoUrl
   **/
  @Schema(description = "")
  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public TransactionAssetDetails color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   *
   * @return color
   **/
  @Schema(description = "")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public TransactionAssetDetails title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   *
   * @return title
   **/
  @Schema(description = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TransactionAssetDetails url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   *
   * @return url
   **/
  @Schema(description = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public TransactionAssetDetails assetType(AssetType assetType) {
    this.assetType = assetType;
    return this;
  }

  /**
   * Get assetType
   *
   * @return assetType
   **/
  @Schema(description = "")
  public AssetType getAssetType() {
    return assetType;
  }

  public void setAssetType(AssetType assetType) {
    this.assetType = assetType;
  }

  public TransactionAssetDetails programDetails(ProgramAssetDetails programDetails) {
    this.programDetails = programDetails;
    return this;
  }

  /**
   * Get programDetails
   *
   * @return programDetails
   **/
  @Schema(description = "")
  public ProgramAssetDetails getProgramDetails() {
    return programDetails;
  }

  public void setProgramDetails(ProgramAssetDetails programDetails) {
    this.programDetails = programDetails;
  }

  public TransactionAssetDetails description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   *
   * @return description
   **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TransactionAssetDetails manager(String manager) {
    this.manager = manager;
    return this;
  }

  /**
   * Get manager
   *
   * @return manager
   **/
  @Schema(description = "")
  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionAssetDetails transactionAssetDetails = (TransactionAssetDetails) o;
    return Objects.equals(this.id, transactionAssetDetails.id) &&
            Objects.equals(this.logoUrl, transactionAssetDetails.logoUrl) &&
            Objects.equals(this.color, transactionAssetDetails.color) &&
            Objects.equals(this.title, transactionAssetDetails.title) &&
            Objects.equals(this.url, transactionAssetDetails.url) &&
            Objects.equals(this.assetType, transactionAssetDetails.assetType) &&
            Objects.equals(this.programDetails, transactionAssetDetails.programDetails) &&
            Objects.equals(this.description, transactionAssetDetails.description) &&
            Objects.equals(this.manager, transactionAssetDetails.manager);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logoUrl, color, title, url, assetType, programDetails, description, manager);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionAssetDetails {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    assetType: ").append(toIndentedString(assetType)).append("\n");
    sb.append("    programDetails: ").append(toIndentedString(programDetails)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
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
    out.writeValue(description);
    out.writeValue(manager);
  }

  public int describeContents() {
    return 0;
  }
}
