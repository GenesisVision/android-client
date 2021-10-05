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
 * DashboardTradingAssetBrokerDetails
 */


public class DashboardTradingAssetBrokerDetails implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("type")
  private BrokerTradeServerType type = null;

  public DashboardTradingAssetBrokerDetails() {
  }
  public DashboardTradingAssetBrokerDetails id(UUID id) {
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

  public DashboardTradingAssetBrokerDetails logoUrl(String logoUrl) {
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

  public DashboardTradingAssetBrokerDetails name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DashboardTradingAssetBrokerDetails type(BrokerTradeServerType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public BrokerTradeServerType getType() {
    return type;
  }

  public void setType(BrokerTradeServerType type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardTradingAssetBrokerDetails dashboardTradingAssetBrokerDetails = (DashboardTradingAssetBrokerDetails) o;
    return Objects.equals(this.id, dashboardTradingAssetBrokerDetails.id) &&
        Objects.equals(this.logoUrl, dashboardTradingAssetBrokerDetails.logoUrl) &&
        Objects.equals(this.name, dashboardTradingAssetBrokerDetails.name) &&
        Objects.equals(this.type, dashboardTradingAssetBrokerDetails.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logoUrl, name, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardTradingAssetBrokerDetails {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
    out.writeValue(name);
    out.writeValue(type);
  }

  DashboardTradingAssetBrokerDetails(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    logoUrl = (String)in.readValue(null);
    name = (String)in.readValue(null);
    type = (BrokerTradeServerType)in.readValue(BrokerTradeServerType.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<DashboardTradingAssetBrokerDetails> CREATOR = new Parcelable.Creator<DashboardTradingAssetBrokerDetails>() {
    public DashboardTradingAssetBrokerDetails createFromParcel(Parcel in) {
      return new DashboardTradingAssetBrokerDetails(in);
    }
    public DashboardTradingAssetBrokerDetails[] newArray(int size) {
      return new DashboardTradingAssetBrokerDetails[size];
    }
  };
}
