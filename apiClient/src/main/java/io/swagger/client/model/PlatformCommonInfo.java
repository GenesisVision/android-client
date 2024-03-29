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
import io.swagger.client.model.PlatformCommissionInfo;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformUrlInfo;
import io.swagger.client.model.PlatformWithdrawalInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PlatformCommonInfo
 */


public class PlatformCommonInfo implements Parcelable {
  @SerializedName("platformCommission")
  private PlatformCommissionInfo platformCommission = null;

  @SerializedName("platformCurrencies")
  private List<PlatformCurrencyInfo> platformCurrencies = null;

  @SerializedName("platformUrls")
  private List<PlatformUrlInfo> platformUrls = null;

  @SerializedName("platformWithdrawalInfo")
  private List<PlatformWithdrawalInfo> platformWithdrawalInfo = null;

  public PlatformCommonInfo() {
  }
  public PlatformCommonInfo platformCommission(PlatformCommissionInfo platformCommission) {
    this.platformCommission = platformCommission;
    return this;
  }

   /**
   * Get platformCommission
   * @return platformCommission
  **/
  @Schema(description = "")
  public PlatformCommissionInfo getPlatformCommission() {
    return platformCommission;
  }

  public void setPlatformCommission(PlatformCommissionInfo platformCommission) {
    this.platformCommission = platformCommission;
  }

  public PlatformCommonInfo platformCurrencies(List<PlatformCurrencyInfo> platformCurrencies) {
    this.platformCurrencies = platformCurrencies;
    return this;
  }

  public PlatformCommonInfo addPlatformCurrenciesItem(PlatformCurrencyInfo platformCurrenciesItem) {
    if (this.platformCurrencies == null) {
      this.platformCurrencies = new ArrayList<PlatformCurrencyInfo>();
    }
    this.platformCurrencies.add(platformCurrenciesItem);
    return this;
  }

   /**
   * Get platformCurrencies
   * @return platformCurrencies
  **/
  @Schema(description = "")
  public List<PlatformCurrencyInfo> getPlatformCurrencies() {
    return platformCurrencies;
  }

  public void setPlatformCurrencies(List<PlatformCurrencyInfo> platformCurrencies) {
    this.platformCurrencies = platformCurrencies;
  }

  public PlatformCommonInfo platformUrls(List<PlatformUrlInfo> platformUrls) {
    this.platformUrls = platformUrls;
    return this;
  }

  public PlatformCommonInfo addPlatformUrlsItem(PlatformUrlInfo platformUrlsItem) {
    if (this.platformUrls == null) {
      this.platformUrls = new ArrayList<PlatformUrlInfo>();
    }
    this.platformUrls.add(platformUrlsItem);
    return this;
  }

   /**
   * Get platformUrls
   * @return platformUrls
  **/
  @Schema(description = "")
  public List<PlatformUrlInfo> getPlatformUrls() {
    return platformUrls;
  }

  public void setPlatformUrls(List<PlatformUrlInfo> platformUrls) {
    this.platformUrls = platformUrls;
  }

  public PlatformCommonInfo platformWithdrawalInfo(List<PlatformWithdrawalInfo> platformWithdrawalInfo) {
    this.platformWithdrawalInfo = platformWithdrawalInfo;
    return this;
  }

  public PlatformCommonInfo addPlatformWithdrawalInfoItem(PlatformWithdrawalInfo platformWithdrawalInfoItem) {
    if (this.platformWithdrawalInfo == null) {
      this.platformWithdrawalInfo = new ArrayList<PlatformWithdrawalInfo>();
    }
    this.platformWithdrawalInfo.add(platformWithdrawalInfoItem);
    return this;
  }

   /**
   * Get platformWithdrawalInfo
   * @return platformWithdrawalInfo
  **/
  @Schema(description = "")
  public List<PlatformWithdrawalInfo> getPlatformWithdrawalInfo() {
    return platformWithdrawalInfo;
  }

  public void setPlatformWithdrawalInfo(List<PlatformWithdrawalInfo> platformWithdrawalInfo) {
    this.platformWithdrawalInfo = platformWithdrawalInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformCommonInfo platformCommonInfo = (PlatformCommonInfo) o;
    return Objects.equals(this.platformCommission, platformCommonInfo.platformCommission) &&
        Objects.equals(this.platformCurrencies, platformCommonInfo.platformCurrencies) &&
        Objects.equals(this.platformUrls, platformCommonInfo.platformUrls) &&
        Objects.equals(this.platformWithdrawalInfo, platformCommonInfo.platformWithdrawalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(platformCommission, platformCurrencies, platformUrls, platformWithdrawalInfo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformCommonInfo {\n");
    
    sb.append("    platformCommission: ").append(toIndentedString(platformCommission)).append("\n");
    sb.append("    platformCurrencies: ").append(toIndentedString(platformCurrencies)).append("\n");
    sb.append("    platformUrls: ").append(toIndentedString(platformUrls)).append("\n");
    sb.append("    platformWithdrawalInfo: ").append(toIndentedString(platformWithdrawalInfo)).append("\n");
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
    out.writeValue(platformCommission);
    out.writeValue(platformCurrencies);
    out.writeValue(platformUrls);
    out.writeValue(platformWithdrawalInfo);
  }

  PlatformCommonInfo(Parcel in) {
    platformCommission = (PlatformCommissionInfo)in.readValue(PlatformCommissionInfo.class.getClassLoader());
    platformCurrencies = (List<PlatformCurrencyInfo>)in.readValue(PlatformCurrencyInfo.class.getClassLoader());
    platformUrls = (List<PlatformUrlInfo>)in.readValue(PlatformUrlInfo.class.getClassLoader());
    platformWithdrawalInfo = (List<PlatformWithdrawalInfo>)in.readValue(PlatformWithdrawalInfo.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PlatformCommonInfo> CREATOR = new Parcelable.Creator<PlatformCommonInfo>() {
    public PlatformCommonInfo createFromParcel(Parcel in) {
      return new PlatformCommonInfo(in);
    }
    public PlatformCommonInfo[] newArray(int size) {
      return new PlatformCommonInfo[size];
    }
  };
}
