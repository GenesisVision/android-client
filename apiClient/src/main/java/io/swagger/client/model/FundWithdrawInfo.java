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
 * FundWithdrawInfo
 */


public class FundWithdrawInfo implements Parcelable {
  @SerializedName("title")
  private String title = null;

  @SerializedName("availableToWithdraw")
  private Double availableToWithdraw = null;

  @SerializedName("isOwner")
  private Boolean isOwner = null;

  @SerializedName("withheldInvestment")
  private Double withheldInvestment = null;

  @SerializedName("exitFee")
  private Double exitFee = null;

  public FundWithdrawInfo() {
  }
  public FundWithdrawInfo title(String title) {
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

  public FundWithdrawInfo availableToWithdraw(Double availableToWithdraw) {
    this.availableToWithdraw = availableToWithdraw;
    return this;
  }

   /**
   * Get availableToWithdraw
   * @return availableToWithdraw
  **/
  @Schema(description = "")
  public Double getAvailableToWithdraw() {
    return availableToWithdraw;
  }

  public void setAvailableToWithdraw(Double availableToWithdraw) {
    this.availableToWithdraw = availableToWithdraw;
  }

  public FundWithdrawInfo isOwner(Boolean isOwner) {
    this.isOwner = isOwner;
    return this;
  }

   /**
   * Get isOwner
   * @return isOwner
  **/
  @Schema(description = "")
  public Boolean isIsOwner() {
    return isOwner;
  }

  public void setIsOwner(Boolean isOwner) {
    this.isOwner = isOwner;
  }

  public FundWithdrawInfo withheldInvestment(Double withheldInvestment) {
    this.withheldInvestment = withheldInvestment;
    return this;
  }

   /**
   * Get withheldInvestment
   * @return withheldInvestment
  **/
  @Schema(description = "")
  public Double getWithheldInvestment() {
    return withheldInvestment;
  }

  public void setWithheldInvestment(Double withheldInvestment) {
    this.withheldInvestment = withheldInvestment;
  }

  public FundWithdrawInfo exitFee(Double exitFee) {
    this.exitFee = exitFee;
    return this;
  }

   /**
   * Get exitFee
   * @return exitFee
  **/
  @Schema(description = "")
  public Double getExitFee() {
    return exitFee;
  }

  public void setExitFee(Double exitFee) {
    this.exitFee = exitFee;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FundWithdrawInfo fundWithdrawInfo = (FundWithdrawInfo) o;
    return Objects.equals(this.title, fundWithdrawInfo.title) &&
        Objects.equals(this.availableToWithdraw, fundWithdrawInfo.availableToWithdraw) &&
        Objects.equals(this.isOwner, fundWithdrawInfo.isOwner) &&
        Objects.equals(this.withheldInvestment, fundWithdrawInfo.withheldInvestment) &&
        Objects.equals(this.exitFee, fundWithdrawInfo.exitFee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, availableToWithdraw, isOwner, withheldInvestment, exitFee);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundWithdrawInfo {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    availableToWithdraw: ").append(toIndentedString(availableToWithdraw)).append("\n");
    sb.append("    isOwner: ").append(toIndentedString(isOwner)).append("\n");
    sb.append("    withheldInvestment: ").append(toIndentedString(withheldInvestment)).append("\n");
    sb.append("    exitFee: ").append(toIndentedString(exitFee)).append("\n");
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
    out.writeValue(availableToWithdraw);
    out.writeValue(isOwner);
    out.writeValue(withheldInvestment);
    out.writeValue(exitFee);
  }

  FundWithdrawInfo(Parcel in) {
    title = (String)in.readValue(null);
    availableToWithdraw = (Double)in.readValue(null);
    isOwner = (Boolean)in.readValue(null);
    withheldInvestment = (Double)in.readValue(null);
    exitFee = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundWithdrawInfo> CREATOR = new Parcelable.Creator<FundWithdrawInfo>() {
    public FundWithdrawInfo createFromParcel(Parcel in) {
      return new FundWithdrawInfo(in);
    }
    public FundWithdrawInfo[] newArray(int size) {
      return new FundWithdrawInfo[size];
    }
  };
}
