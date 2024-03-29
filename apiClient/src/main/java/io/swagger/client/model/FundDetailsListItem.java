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
import io.swagger.client.model.AmountWithCurrency;
import io.swagger.client.model.FundAssetPercent;
import io.swagger.client.model.PersonalFundDetailsList;
import io.swagger.client.model.ProfilePublicShort;
import io.swagger.client.model.ProfitChart;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FundDetailsListItem
 */


public class FundDetailsListItem implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("index")
  private Integer index = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("creationDate")
  private DateTime creationDate = null;

  @SerializedName("investorsCount")
  private Integer investorsCount = null;

  @SerializedName("status")
  private String status = null;

  @SerializedName("totalAssetsCount")
  private Integer totalAssetsCount = null;

  @SerializedName("topFundAssets")
  private List<FundAssetPercent> topFundAssets = null;

  @SerializedName("owner")
  private ProfilePublicShort owner = null;

  @SerializedName("statistic")
  private ProfitChart statistic = null;

  @SerializedName("personalDetails")
  private PersonalFundDetailsList personalDetails = null;

  @SerializedName("balance")
  private AmountWithCurrency balance = null;

  public FundDetailsListItem() {
  }
  public FundDetailsListItem id(UUID id) {
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

  public FundDetailsListItem logoUrl(String logoUrl) {
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

  public FundDetailsListItem url(String url) {
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

  public FundDetailsListItem color(String color) {
    this.color = color;
    return this;
  }

   /**
   * Get color
   * @return color
  **/
  @Schema(description = "")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public FundDetailsListItem index(Integer index) {
    this.index = index;
    return this;
  }

   /**
   * Get index
   * @return index
  **/
  @Schema(description = "")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public FundDetailsListItem title(String title) {
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

  public FundDetailsListItem description(String description) {
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

  public FundDetailsListItem creationDate(DateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

   /**
   * Get creationDate
   * @return creationDate
  **/
  @Schema(description = "")
  public DateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(DateTime creationDate) {
    this.creationDate = creationDate;
  }

  public FundDetailsListItem investorsCount(Integer investorsCount) {
    this.investorsCount = investorsCount;
    return this;
  }

   /**
   * Get investorsCount
   * @return investorsCount
  **/
  @Schema(description = "")
  public Integer getInvestorsCount() {
    return investorsCount;
  }

  public void setInvestorsCount(Integer investorsCount) {
    this.investorsCount = investorsCount;
  }

  public FundDetailsListItem status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FundDetailsListItem totalAssetsCount(Integer totalAssetsCount) {
    this.totalAssetsCount = totalAssetsCount;
    return this;
  }

   /**
   * Get totalAssetsCount
   * @return totalAssetsCount
  **/
  @Schema(description = "")
  public Integer getTotalAssetsCount() {
    return totalAssetsCount;
  }

  public void setTotalAssetsCount(Integer totalAssetsCount) {
    this.totalAssetsCount = totalAssetsCount;
  }

  public FundDetailsListItem topFundAssets(List<FundAssetPercent> topFundAssets) {
    this.topFundAssets = topFundAssets;
    return this;
  }

  public FundDetailsListItem addTopFundAssetsItem(FundAssetPercent topFundAssetsItem) {
    if (this.topFundAssets == null) {
      this.topFundAssets = new ArrayList<FundAssetPercent>();
    }
    this.topFundAssets.add(topFundAssetsItem);
    return this;
  }

   /**
   * Get topFundAssets
   * @return topFundAssets
  **/
  @Schema(description = "")
  public List<FundAssetPercent> getTopFundAssets() {
    return topFundAssets;
  }

  public void setTopFundAssets(List<FundAssetPercent> topFundAssets) {
    this.topFundAssets = topFundAssets;
  }

  public FundDetailsListItem owner(ProfilePublicShort owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Get owner
   * @return owner
  **/
  @Schema(description = "")
  public ProfilePublicShort getOwner() {
    return owner;
  }

  public void setOwner(ProfilePublicShort owner) {
    this.owner = owner;
  }

  public FundDetailsListItem statistic(ProfitChart statistic) {
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

  public FundDetailsListItem personalDetails(PersonalFundDetailsList personalDetails) {
    this.personalDetails = personalDetails;
    return this;
  }

   /**
   * Get personalDetails
   * @return personalDetails
  **/
  @Schema(description = "")
  public PersonalFundDetailsList getPersonalDetails() {
    return personalDetails;
  }

  public void setPersonalDetails(PersonalFundDetailsList personalDetails) {
    this.personalDetails = personalDetails;
  }

  public FundDetailsListItem balance(AmountWithCurrency balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @Schema(description = "")
  public AmountWithCurrency getBalance() {
    return balance;
  }

  public void setBalance(AmountWithCurrency balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FundDetailsListItem fundDetailsListItem = (FundDetailsListItem) o;
    return Objects.equals(this.id, fundDetailsListItem.id) &&
        Objects.equals(this.logoUrl, fundDetailsListItem.logoUrl) &&
        Objects.equals(this.url, fundDetailsListItem.url) &&
        Objects.equals(this.color, fundDetailsListItem.color) &&
        Objects.equals(this.index, fundDetailsListItem.index) &&
        Objects.equals(this.title, fundDetailsListItem.title) &&
        Objects.equals(this.description, fundDetailsListItem.description) &&
        Objects.equals(this.creationDate, fundDetailsListItem.creationDate) &&
        Objects.equals(this.investorsCount, fundDetailsListItem.investorsCount) &&
        Objects.equals(this.status, fundDetailsListItem.status) &&
        Objects.equals(this.totalAssetsCount, fundDetailsListItem.totalAssetsCount) &&
        Objects.equals(this.topFundAssets, fundDetailsListItem.topFundAssets) &&
        Objects.equals(this.owner, fundDetailsListItem.owner) &&
        Objects.equals(this.statistic, fundDetailsListItem.statistic) &&
        Objects.equals(this.personalDetails, fundDetailsListItem.personalDetails) &&
        Objects.equals(this.balance, fundDetailsListItem.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logoUrl, url, color, index, title, description, creationDate, investorsCount, status, totalAssetsCount, topFundAssets, owner, statistic, personalDetails, balance);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundDetailsListItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    investorsCount: ").append(toIndentedString(investorsCount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    totalAssetsCount: ").append(toIndentedString(totalAssetsCount)).append("\n");
    sb.append("    topFundAssets: ").append(toIndentedString(topFundAssets)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    statistic: ").append(toIndentedString(statistic)).append("\n");
    sb.append("    personalDetails: ").append(toIndentedString(personalDetails)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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
    out.writeValue(url);
    out.writeValue(color);
    out.writeValue(index);
    out.writeValue(title);
    out.writeValue(description);
    out.writeValue(creationDate);
    out.writeValue(investorsCount);
    out.writeValue(status);
    out.writeValue(totalAssetsCount);
    out.writeValue(topFundAssets);
    out.writeValue(owner);
    out.writeValue(statistic);
    out.writeValue(personalDetails);
    out.writeValue(balance);
  }

  FundDetailsListItem(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    logoUrl = (String)in.readValue(null);
    url = (String)in.readValue(null);
    color = (String)in.readValue(null);
    index = (Integer)in.readValue(null);
    title = (String)in.readValue(null);
    description = (String)in.readValue(null);
    creationDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    investorsCount = (Integer)in.readValue(null);
    status = (String)in.readValue(null);
    totalAssetsCount = (Integer)in.readValue(null);
    topFundAssets = (List<FundAssetPercent>)in.readValue(FundAssetPercent.class.getClassLoader());
    owner = (ProfilePublicShort)in.readValue(ProfilePublicShort.class.getClassLoader());
    statistic = (ProfitChart)in.readValue(ProfitChart.class.getClassLoader());
    personalDetails = (PersonalFundDetailsList)in.readValue(PersonalFundDetailsList.class.getClassLoader());
    balance = (AmountWithCurrency)in.readValue(AmountWithCurrency.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundDetailsListItem> CREATOR = new Parcelable.Creator<FundDetailsListItem>() {
    public FundDetailsListItem createFromParcel(Parcel in) {
      return new FundDetailsListItem(in);
    }
    public FundDetailsListItem[] newArray(int size) {
      return new FundDetailsListItem[size];
    }
  };
}
