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
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * CreateApiKeyModel
 */


public class CreateApiKeyModel implements Parcelable {
  @SerializedName("isIpRestrict")
  private Boolean isIpRestrict = null;

  @SerializedName("allowedIps")
  private List<String> allowedIps = null;

  @SerializedName("isTradingEnabled")
  private Boolean isTradingEnabled = null;

  @SerializedName("twoFactorCode")
  private String twoFactorCode = null;

  @SerializedName("title")
  private String title = null;

  public CreateApiKeyModel() {
  }
  public CreateApiKeyModel isIpRestrict(Boolean isIpRestrict) {
    this.isIpRestrict = isIpRestrict;
    return this;
  }

   /**
   * Get isIpRestrict
   * @return isIpRestrict
  **/
  @Schema(description = "")
  public Boolean isIsIpRestrict() {
    return isIpRestrict;
  }

  public void setIsIpRestrict(Boolean isIpRestrict) {
    this.isIpRestrict = isIpRestrict;
  }

  public CreateApiKeyModel allowedIps(List<String> allowedIps) {
    this.allowedIps = allowedIps;
    return this;
  }

  public CreateApiKeyModel addAllowedIpsItem(String allowedIpsItem) {
    if (this.allowedIps == null) {
      this.allowedIps = new ArrayList<String>();
    }
    this.allowedIps.add(allowedIpsItem);
    return this;
  }

   /**
   * Get allowedIps
   * @return allowedIps
  **/
  @Schema(description = "")
  public List<String> getAllowedIps() {
    return allowedIps;
  }

  public void setAllowedIps(List<String> allowedIps) {
    this.allowedIps = allowedIps;
  }

  public CreateApiKeyModel isTradingEnabled(Boolean isTradingEnabled) {
    this.isTradingEnabled = isTradingEnabled;
    return this;
  }

   /**
   * Get isTradingEnabled
   * @return isTradingEnabled
  **/
  @Schema(description = "")
  public Boolean isIsTradingEnabled() {
    return isTradingEnabled;
  }

  public void setIsTradingEnabled(Boolean isTradingEnabled) {
    this.isTradingEnabled = isTradingEnabled;
  }

  public CreateApiKeyModel twoFactorCode(String twoFactorCode) {
    this.twoFactorCode = twoFactorCode;
    return this;
  }

   /**
   * Get twoFactorCode
   * @return twoFactorCode
  **/
  @Schema(description = "")
  public String getTwoFactorCode() {
    return twoFactorCode;
  }

  public void setTwoFactorCode(String twoFactorCode) {
    this.twoFactorCode = twoFactorCode;
  }

  public CreateApiKeyModel title(String title) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateApiKeyModel createApiKeyModel = (CreateApiKeyModel) o;
    return Objects.equals(this.isIpRestrict, createApiKeyModel.isIpRestrict) &&
        Objects.equals(this.allowedIps, createApiKeyModel.allowedIps) &&
        Objects.equals(this.isTradingEnabled, createApiKeyModel.isTradingEnabled) &&
        Objects.equals(this.twoFactorCode, createApiKeyModel.twoFactorCode) &&
        Objects.equals(this.title, createApiKeyModel.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isIpRestrict, allowedIps, isTradingEnabled, twoFactorCode, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateApiKeyModel {\n");
    
    sb.append("    isIpRestrict: ").append(toIndentedString(isIpRestrict)).append("\n");
    sb.append("    allowedIps: ").append(toIndentedString(allowedIps)).append("\n");
    sb.append("    isTradingEnabled: ").append(toIndentedString(isTradingEnabled)).append("\n");
    sb.append("    twoFactorCode: ").append(toIndentedString(twoFactorCode)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
    out.writeValue(isIpRestrict);
    out.writeValue(allowedIps);
    out.writeValue(isTradingEnabled);
    out.writeValue(twoFactorCode);
    out.writeValue(title);
  }

  CreateApiKeyModel(Parcel in) {
    isIpRestrict = (Boolean)in.readValue(null);
    allowedIps = (List<String>)in.readValue(null);
    isTradingEnabled = (Boolean)in.readValue(null);
    twoFactorCode = (String)in.readValue(null);
    title = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<CreateApiKeyModel> CREATOR = new Parcelable.Creator<CreateApiKeyModel>() {
    public CreateApiKeyModel createFromParcel(Parcel in) {
      return new CreateApiKeyModel(in);
    }
    public CreateApiKeyModel[] newArray(int size) {
      return new CreateApiKeyModel[size];
    }
  };
}
