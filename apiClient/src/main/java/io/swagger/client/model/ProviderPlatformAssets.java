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
 * ProviderPlatformAssets
 */


public class ProviderPlatformAssets implements Parcelable
{
  public static final Parcelable.Creator<ProviderPlatformAssets> CREATOR = new Parcelable.Creator<ProviderPlatformAssets>()
  {
    public ProviderPlatformAssets createFromParcel(Parcel in) {
      return new ProviderPlatformAssets(in);
    }

    public ProviderPlatformAssets[] newArray(int size) {
      return new ProviderPlatformAssets[size];
    }
  };

  @SerializedName("type")
  private AssetProvider type = null;

  @SerializedName("tradingSchedule")
  private TradingScheduleInfo tradingSchedule = null;

  public ProviderPlatformAssets() {
  }

  ProviderPlatformAssets(Parcel in) {
    type = (AssetProvider) in.readValue(AssetProvider.class.getClassLoader());
    tradingSchedule = (TradingScheduleInfo) in.readValue(TradingScheduleInfo.class.getClassLoader());
  }

  public ProviderPlatformAssets type(AssetProvider type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   **/
  @Schema(description = "")
  public AssetProvider getType() {
    return type;
  }

  public void setType(AssetProvider type) {
    this.type = type;
  }

  public ProviderPlatformAssets tradingSchedule(TradingScheduleInfo tradingSchedule) {
    this.tradingSchedule = tradingSchedule;
    return this;
  }

  /**
   * Get tradingSchedule
   *
   * @return tradingSchedule
   **/
  @Schema(description = "")
  public TradingScheduleInfo getTradingSchedule() {
    return tradingSchedule;
  }

  public void setTradingSchedule(TradingScheduleInfo tradingSchedule) {
    this.tradingSchedule = tradingSchedule;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderPlatformAssets providerPlatformAssets = (ProviderPlatformAssets) o;
    return Objects.equals(this.type, providerPlatformAssets.type) &&
            Objects.equals(this.tradingSchedule, providerPlatformAssets.tradingSchedule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, tradingSchedule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderPlatformAssets {\n");

    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tradingSchedule: ").append(toIndentedString(tradingSchedule)).append("\n");
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
    out.writeValue(type);
    out.writeValue(tradingSchedule);
  }

  public int describeContents() {
    return 0;
  }
}
