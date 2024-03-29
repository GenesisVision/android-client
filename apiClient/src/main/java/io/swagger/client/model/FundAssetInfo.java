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
 * FundAssetInfo
 */


public class FundAssetInfo implements Parcelable {
  @SerializedName("asset")
  private String asset = null;

  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("target")
  private Double target = null;

  @SerializedName("current")
  private Double current = null;

  @SerializedName("currentAmount")
  private Double currentAmount = null;

  @SerializedName("url")
  private String url = null;

  public FundAssetInfo() {
  }
  public FundAssetInfo asset(String asset) {
    this.asset = asset;
    return this;
  }

   /**
   * Get asset
   * @return asset
  **/
  @Schema(description = "")
  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public FundAssetInfo symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * Get symbol
   * @return symbol
  **/
  @Schema(description = "")
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public FundAssetInfo logoUrl(String logoUrl) {
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

  public FundAssetInfo target(Double target) {
    this.target = target;
    return this;
  }

   /**
   * Get target
   * @return target
  **/
  @Schema(description = "")
  public Double getTarget() {
    return target;
  }

  public void setTarget(Double target) {
    this.target = target;
  }

  public FundAssetInfo current(Double current) {
    this.current = current;
    return this;
  }

   /**
   * Get current
   * @return current
  **/
  @Schema(description = "")
  public Double getCurrent() {
    return current;
  }

  public void setCurrent(Double current) {
    this.current = current;
  }

  public FundAssetInfo currentAmount(Double currentAmount) {
    this.currentAmount = currentAmount;
    return this;
  }

   /**
   * Get currentAmount
   * @return currentAmount
  **/
  @Schema(description = "")
  public Double getCurrentAmount() {
    return currentAmount;
  }

  public void setCurrentAmount(Double currentAmount) {
    this.currentAmount = currentAmount;
  }

  public FundAssetInfo url(String url) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FundAssetInfo fundAssetInfo = (FundAssetInfo) o;
    return Objects.equals(this.asset, fundAssetInfo.asset) &&
        Objects.equals(this.symbol, fundAssetInfo.symbol) &&
        Objects.equals(this.logoUrl, fundAssetInfo.logoUrl) &&
        Objects.equals(this.target, fundAssetInfo.target) &&
        Objects.equals(this.current, fundAssetInfo.current) &&
        Objects.equals(this.currentAmount, fundAssetInfo.currentAmount) &&
        Objects.equals(this.url, fundAssetInfo.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asset, symbol, logoUrl, target, current, currentAmount, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundAssetInfo {\n");
    
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    current: ").append(toIndentedString(current)).append("\n");
    sb.append("    currentAmount: ").append(toIndentedString(currentAmount)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
    out.writeValue(asset);
    out.writeValue(symbol);
    out.writeValue(logoUrl);
    out.writeValue(target);
    out.writeValue(current);
    out.writeValue(currentAmount);
    out.writeValue(url);
  }

  FundAssetInfo(Parcel in) {
    asset = (String)in.readValue(null);
    symbol = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    target = (Double)in.readValue(null);
    current = (Double)in.readValue(null);
    currentAmount = (Double)in.readValue(null);
    url = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundAssetInfo> CREATOR = new Parcelable.Creator<FundAssetInfo>() {
    public FundAssetInfo createFromParcel(Parcel in) {
      return new FundAssetInfo(in);
    }
    public FundAssetInfo[] newArray(int size) {
      return new FundAssetInfo[size];
    }
  };
}
