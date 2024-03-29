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

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * AssetPlatformInfo
 */


public class AssetPlatformInfo implements Parcelable {
  @SerializedName("programInfo")
  private ProgramAssetPlatformInfo programInfo = null;

  @SerializedName("tradingAccountInfo")
  private TradingAccountAssetPlatformInfo tradingAccountInfo = null;

  @SerializedName("fundInfo")
  private FundAssetPlatformInfo fundInfo = null;

  @SerializedName("followInfo")
  private FollowAssetPlatformInfo followInfo = null;

  @SerializedName("anonymousInfo")
  private AnonymousPlatformInfo anonymousInfo = null;

  public AssetPlatformInfo() {
  }
  public AssetPlatformInfo programInfo(ProgramAssetPlatformInfo programInfo) {
    this.programInfo = programInfo;
    return this;
  }

   /**
   * Get programInfo
   * @return programInfo
  **/
  @Schema(description = "")
  public ProgramAssetPlatformInfo getProgramInfo() {
    return programInfo;
  }

  public void setProgramInfo(ProgramAssetPlatformInfo programInfo) {
    this.programInfo = programInfo;
  }

  public AssetPlatformInfo tradingAccountInfo(TradingAccountAssetPlatformInfo tradingAccountInfo) {
    this.tradingAccountInfo = tradingAccountInfo;
    return this;
  }

   /**
   * Get tradingAccountInfo
   * @return tradingAccountInfo
  **/
  @Schema(description = "")
  public TradingAccountAssetPlatformInfo getTradingAccountInfo() {
    return tradingAccountInfo;
  }

  public void setTradingAccountInfo(TradingAccountAssetPlatformInfo tradingAccountInfo) {
    this.tradingAccountInfo = tradingAccountInfo;
  }

  public AssetPlatformInfo fundInfo(FundAssetPlatformInfo fundInfo) {
    this.fundInfo = fundInfo;
    return this;
  }

   /**
   * Get fundInfo
   * @return fundInfo
  **/
  @Schema(description = "")
  public FundAssetPlatformInfo getFundInfo() {
    return fundInfo;
  }

  public void setFundInfo(FundAssetPlatformInfo fundInfo) {
    this.fundInfo = fundInfo;
  }

  public AssetPlatformInfo followInfo(FollowAssetPlatformInfo followInfo) {
    this.followInfo = followInfo;
    return this;
  }

   /**
   * Get followInfo
   * @return followInfo
  **/
  @Schema(description = "")
  public FollowAssetPlatformInfo getFollowInfo() {
    return followInfo;
  }

  public void setFollowInfo(FollowAssetPlatformInfo followInfo) {
    this.followInfo = followInfo;
  }

  public AssetPlatformInfo anonymousInfo(AnonymousPlatformInfo anonymousInfo) {
    this.anonymousInfo = anonymousInfo;
    return this;
  }

   /**
   * Get anonymousInfo
   * @return anonymousInfo
  **/
  @Schema(description = "")
  public AnonymousPlatformInfo getAnonymousInfo() {
    return anonymousInfo;
  }

  public void setAnonymousInfo(AnonymousPlatformInfo anonymousInfo) {
    this.anonymousInfo = anonymousInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssetPlatformInfo assetPlatformInfo = (AssetPlatformInfo) o;
    return Objects.equals(this.programInfo, assetPlatformInfo.programInfo) &&
        Objects.equals(this.tradingAccountInfo, assetPlatformInfo.tradingAccountInfo) &&
        Objects.equals(this.fundInfo, assetPlatformInfo.fundInfo) &&
        Objects.equals(this.followInfo, assetPlatformInfo.followInfo) &&
        Objects.equals(this.anonymousInfo, assetPlatformInfo.anonymousInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(programInfo, tradingAccountInfo, fundInfo, followInfo, anonymousInfo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssetPlatformInfo {\n");
    
    sb.append("    programInfo: ").append(toIndentedString(programInfo)).append("\n");
    sb.append("    tradingAccountInfo: ").append(toIndentedString(tradingAccountInfo)).append("\n");
    sb.append("    fundInfo: ").append(toIndentedString(fundInfo)).append("\n");
    sb.append("    followInfo: ").append(toIndentedString(followInfo)).append("\n");
    sb.append("    anonymousInfo: ").append(toIndentedString(anonymousInfo)).append("\n");
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
    out.writeValue(programInfo);
    out.writeValue(tradingAccountInfo);
    out.writeValue(fundInfo);
    out.writeValue(followInfo);
    out.writeValue(anonymousInfo);
  }

  AssetPlatformInfo(Parcel in) {
    programInfo = (ProgramAssetPlatformInfo)in.readValue(ProgramAssetPlatformInfo.class.getClassLoader());
    tradingAccountInfo = (TradingAccountAssetPlatformInfo)in.readValue(TradingAccountAssetPlatformInfo.class.getClassLoader());
    fundInfo = (FundAssetPlatformInfo)in.readValue(FundAssetPlatformInfo.class.getClassLoader());
    followInfo = (FollowAssetPlatformInfo)in.readValue(FollowAssetPlatformInfo.class.getClassLoader());
    anonymousInfo = (AnonymousPlatformInfo)in.readValue(AnonymousPlatformInfo.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<AssetPlatformInfo> CREATOR = new Parcelable.Creator<AssetPlatformInfo>() {
    public AssetPlatformInfo createFromParcel(Parcel in) {
      return new AssetPlatformInfo(in);
    }
    public AssetPlatformInfo[] newArray(int size) {
      return new AssetPlatformInfo[size];
    }
  };
}
