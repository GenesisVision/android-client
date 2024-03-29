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
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * Broker
 */


public class Broker implements Parcelable {
  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("terms")
  private String terms = null;

  @SerializedName("assets")
  private String assets = null;

  @SerializedName("fee")
  private Double fee = null;

  @SerializedName("leverageMin")
  private Integer leverageMin = null;

  @SerializedName("leverageMax")
  private Integer leverageMax = null;

  @SerializedName("isKycRequired")
  private Boolean isKycRequired = null;

  @SerializedName("accountTypes")
  private List<BrokerAccountType> accountTypes = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  public Broker() {
  }
  public Broker name(String name) {
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

  public Broker description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Broker logoUrl(String logoUrl) {
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

  public Broker terms(String terms) {
    this.terms = terms;
    return this;
  }

   /**
   * Get terms
   * @return terms
  **/
  @Schema(description = "")
  public String getTerms() {
    return terms;
  }

  public void setTerms(String terms) {
    this.terms = terms;
  }

  public Broker assets(String assets) {
    this.assets = assets;
    return this;
  }

   /**
   * Get assets
   * @return assets
  **/
  @Schema(description = "")
  public String getAssets() {
    return assets;
  }

  public void setAssets(String assets) {
    this.assets = assets;
  }

  public Broker fee(Double fee) {
    this.fee = fee;
    return this;
  }

   /**
   * Get fee
   * @return fee
  **/
  @Schema(description = "")
  public Double getFee() {
    return fee;
  }

  public void setFee(Double fee) {
    this.fee = fee;
  }

   /**
   * Get leverageMin
   * @return leverageMin
  **/
  @Schema(description = "")
  public Integer getLeverageMin() {
    return leverageMin;
  }

   /**
   * Get leverageMax
   * @return leverageMax
  **/
  @Schema(description = "")
  public Integer getLeverageMax() {
    return leverageMax;
  }

   /**
   * Get isKycRequired
   * @return isKycRequired
  **/
  @Schema(description = "")
  public Boolean isIsKycRequired() {
    return isKycRequired;
  }

  public Broker accountTypes(List<BrokerAccountType> accountTypes) {
    this.accountTypes = accountTypes;
    return this;
  }

  public Broker addAccountTypesItem(BrokerAccountType accountTypesItem) {
    if (this.accountTypes == null) {
      this.accountTypes = new ArrayList<BrokerAccountType>();
    }
    this.accountTypes.add(accountTypesItem);
    return this;
  }

   /**
   * Get accountTypes
   * @return accountTypes
  **/
  @Schema(description = "")
  public List<BrokerAccountType> getAccountTypes() {
    return accountTypes;
  }

  public void setAccountTypes(List<BrokerAccountType> accountTypes) {
    this.accountTypes = accountTypes;
  }

  public Broker tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public Broker addTagsItem(Tag tagsItem) {
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
    Broker broker = (Broker) o;
    return Objects.equals(this.name, broker.name) &&
        Objects.equals(this.description, broker.description) &&
        Objects.equals(this.logoUrl, broker.logoUrl) &&
        Objects.equals(this.terms, broker.terms) &&
        Objects.equals(this.assets, broker.assets) &&
        Objects.equals(this.fee, broker.fee) &&
        Objects.equals(this.leverageMin, broker.leverageMin) &&
        Objects.equals(this.leverageMax, broker.leverageMax) &&
        Objects.equals(this.isKycRequired, broker.isKycRequired) &&
        Objects.equals(this.accountTypes, broker.accountTypes) &&
        Objects.equals(this.tags, broker.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, logoUrl, terms, assets, fee, leverageMin, leverageMax, isKycRequired, accountTypes, tags);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Broker {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    terms: ").append(toIndentedString(terms)).append("\n");
    sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
    sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
    sb.append("    leverageMin: ").append(toIndentedString(leverageMin)).append("\n");
    sb.append("    leverageMax: ").append(toIndentedString(leverageMax)).append("\n");
    sb.append("    isKycRequired: ").append(toIndentedString(isKycRequired)).append("\n");
    sb.append("    accountTypes: ").append(toIndentedString(accountTypes)).append("\n");
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
    out.writeValue(name);
    out.writeValue(description);
    out.writeValue(logoUrl);
    out.writeValue(terms);
    out.writeValue(assets);
    out.writeValue(fee);
    out.writeValue(leverageMin);
    out.writeValue(leverageMax);
    out.writeValue(isKycRequired);
    out.writeValue(accountTypes);
    out.writeValue(tags);
  }

  Broker(Parcel in) {
    name = (String)in.readValue(null);
    description = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    terms = (String)in.readValue(null);
    assets = (String)in.readValue(null);
    fee = (Double)in.readValue(null);
    leverageMin = (Integer)in.readValue(null);
    leverageMax = (Integer)in.readValue(null);
    isKycRequired = (Boolean)in.readValue(null);
    accountTypes = (List<BrokerAccountType>)in.readValue(BrokerAccountType.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<Broker> CREATOR = new Parcelable.Creator<Broker>() {
    public Broker createFromParcel(Parcel in) {
      return new Broker(in);
    }
    public Broker[] newArray(int size) {
      return new Broker[size];
    }
  };
}
