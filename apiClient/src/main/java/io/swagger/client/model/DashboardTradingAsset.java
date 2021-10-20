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
import io.swagger.client.model.AssetTypeExt;
import io.swagger.client.model.DashboardTradingAssetActions;
import io.swagger.client.model.DashboardTradingAssetBrokerDetails;
import io.swagger.client.model.DashboardTradingAssetCommonDetails;
import io.swagger.client.model.DashboardTradingAssetPublicDetails;
import io.swagger.client.model.DashboardTradingAssetSignalDetails;
import io.swagger.client.model.ProfitChart;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * DashboardTradingAsset
 */


public class DashboardTradingAsset implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("assetType")
  private AssetType assetType = null;

  @SerializedName("assetTypeExt")
  private AssetTypeExt assetTypeExt = null;

  @SerializedName("statistic")
  private ProfitChart statistic = null;

  @SerializedName("publicInfo")
  private DashboardTradingAssetPublicDetails publicInfo = null;

  @SerializedName("accountInfo")
  private DashboardTradingAssetCommonDetails accountInfo = null;

  @SerializedName("signalInfo")
  private DashboardTradingAssetSignalDetails signalInfo = null;

  @SerializedName("broker")
  private DashboardTradingAssetBrokerDetails broker = null;

  @SerializedName("actions")
  private DashboardTradingAssetActions actions = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  public DashboardTradingAsset() {
  }
  public DashboardTradingAsset id(UUID id) {
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

  public DashboardTradingAsset assetType(AssetType assetType) {
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

  public DashboardTradingAsset assetTypeExt(AssetTypeExt assetTypeExt) {
    this.assetTypeExt = assetTypeExt;
    return this;
  }

   /**
   * Get assetTypeExt
   * @return assetTypeExt
  **/
  @Schema(description = "")
  public AssetTypeExt getAssetTypeExt() {
    return assetTypeExt;
  }

  public void setAssetTypeExt(AssetTypeExt assetTypeExt) {
    this.assetTypeExt = assetTypeExt;
  }

  public DashboardTradingAsset statistic(ProfitChart statistic) {
    this.statistic = statistic;
    return this;
  }

   /**
   * Get statistic
   * @return statistic
  **/
  @Schema(description = "")
  public ProfitChart getStatistic() {
    return statistic;
  }

  public void setStatistic(ProfitChart statistic) {
    this.statistic = statistic;
  }

  public DashboardTradingAsset publicInfo(DashboardTradingAssetPublicDetails publicInfo) {
    this.publicInfo = publicInfo;
    return this;
  }

   /**
   * Get publicInfo
   * @return publicInfo
  **/
  @Schema(description = "")
  public DashboardTradingAssetPublicDetails getPublicInfo() {
    return publicInfo;
  }

  public void setPublicInfo(DashboardTradingAssetPublicDetails publicInfo) {
    this.publicInfo = publicInfo;
  }

  public DashboardTradingAsset accountInfo(DashboardTradingAssetCommonDetails accountInfo) {
    this.accountInfo = accountInfo;
    return this;
  }

   /**
   * Get accountInfo
   * @return accountInfo
  **/
  @Schema(description = "")
  public DashboardTradingAssetCommonDetails getAccountInfo() {
    return accountInfo;
  }

  public void setAccountInfo(DashboardTradingAssetCommonDetails accountInfo) {
    this.accountInfo = accountInfo;
  }

  public DashboardTradingAsset signalInfo(DashboardTradingAssetSignalDetails signalInfo) {
    this.signalInfo = signalInfo;
    return this;
  }

   /**
   * Get signalInfo
   * @return signalInfo
  **/
  @Schema(description = "")
  public DashboardTradingAssetSignalDetails getSignalInfo() {
    return signalInfo;
  }

  public void setSignalInfo(DashboardTradingAssetSignalDetails signalInfo) {
    this.signalInfo = signalInfo;
  }

  public DashboardTradingAsset broker(DashboardTradingAssetBrokerDetails broker) {
    this.broker = broker;
    return this;
  }

   /**
   * Get broker
   * @return broker
  **/
  @Schema(description = "")
  public DashboardTradingAssetBrokerDetails getBroker() {
    return broker;
  }

  public void setBroker(DashboardTradingAssetBrokerDetails broker) {
    this.broker = broker;
  }

  public DashboardTradingAsset actions(DashboardTradingAssetActions actions) {
    this.actions = actions;
    return this;
  }

   /**
   * Get actions
   * @return actions
  **/
  @Schema(description = "")
  public DashboardTradingAssetActions getActions() {
    return actions;
  }

  public void setActions(DashboardTradingAssetActions actions) {
    this.actions = actions;
  }

  public DashboardTradingAsset tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public DashboardTradingAsset addTagsItem(Tag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<Tag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @Schema(description = "")
  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardTradingAsset dashboardTradingAsset = (DashboardTradingAsset) o;
    return Objects.equals(this.id, dashboardTradingAsset.id) &&
        Objects.equals(this.assetType, dashboardTradingAsset.assetType) &&
        Objects.equals(this.assetTypeExt, dashboardTradingAsset.assetTypeExt) &&
        Objects.equals(this.statistic, dashboardTradingAsset.statistic) &&
        Objects.equals(this.publicInfo, dashboardTradingAsset.publicInfo) &&
        Objects.equals(this.accountInfo, dashboardTradingAsset.accountInfo) &&
        Objects.equals(this.signalInfo, dashboardTradingAsset.signalInfo) &&
        Objects.equals(this.broker, dashboardTradingAsset.broker) &&
        Objects.equals(this.actions, dashboardTradingAsset.actions) &&
        Objects.equals(this.tags, dashboardTradingAsset.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, assetType, assetTypeExt, statistic, publicInfo, accountInfo, signalInfo, broker, actions, tags);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardTradingAsset {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    assetType: ").append(toIndentedString(assetType)).append("\n");
    sb.append("    assetTypeExt: ").append(toIndentedString(assetTypeExt)).append("\n");
    sb.append("    statistic: ").append(toIndentedString(statistic)).append("\n");
    sb.append("    publicInfo: ").append(toIndentedString(publicInfo)).append("\n");
    sb.append("    accountInfo: ").append(toIndentedString(accountInfo)).append("\n");
    sb.append("    signalInfo: ").append(toIndentedString(signalInfo)).append("\n");
    sb.append("    broker: ").append(toIndentedString(broker)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
    out.writeValue(assetType);
    out.writeValue(assetTypeExt);
    out.writeValue(statistic);
    out.writeValue(publicInfo);
    out.writeValue(accountInfo);
    out.writeValue(signalInfo);
    out.writeValue(broker);
    out.writeValue(actions);
    out.writeValue(tags);
  }

  DashboardTradingAsset(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    assetType = (AssetType)in.readValue(AssetType.class.getClassLoader());
    assetTypeExt = (AssetTypeExt)in.readValue(AssetTypeExt.class.getClassLoader());
    statistic = (ProfitChart)in.readValue(ProfitChart.class.getClassLoader());
    publicInfo = (DashboardTradingAssetPublicDetails)in.readValue(DashboardTradingAssetPublicDetails.class.getClassLoader());
    accountInfo = (DashboardTradingAssetCommonDetails)in.readValue(DashboardTradingAssetCommonDetails.class.getClassLoader());
    signalInfo = (DashboardTradingAssetSignalDetails)in.readValue(DashboardTradingAssetSignalDetails.class.getClassLoader());
    broker = (DashboardTradingAssetBrokerDetails)in.readValue(DashboardTradingAssetBrokerDetails.class.getClassLoader());
    actions = (DashboardTradingAssetActions)in.readValue(DashboardTradingAssetActions.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<DashboardTradingAsset> CREATOR = new Parcelable.Creator<DashboardTradingAsset>() {
    public DashboardTradingAsset createFromParcel(Parcel in) {
      return new DashboardTradingAsset(in);
    }
    public DashboardTradingAsset[] newArray(int size) {
      return new DashboardTradingAsset[size];
    }
  };
}
