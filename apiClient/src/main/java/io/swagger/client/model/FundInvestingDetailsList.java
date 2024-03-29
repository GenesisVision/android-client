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
import io.swagger.client.model.PersonalInvestingFundDetailsList;
import io.swagger.client.model.ProfilePublicShort;
import io.swagger.client.model.ProfitChart;
import io.swagger.client.model.TradingScheduleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FundInvestingDetailsList
 */


public class FundInvestingDetailsList implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("creationDate")
  private DateTime creationDate = null;

  @SerializedName("investorsCount")
  private Integer investorsCount = null;

  @SerializedName("entryFeeCurrent")
  private Double entryFeeCurrent = null;

  @SerializedName("entryFeeSelected")
  private Double entryFeeSelected = null;

  @SerializedName("exitFeeCurrent")
  private Double exitFeeCurrent = null;

  @SerializedName("exitFeeSelected")
  private Double exitFeeSelected = null;

  @SerializedName("totalAssetsCount")
  private Integer totalAssetsCount = null;

  @SerializedName("topFundAssets")
  private List<FundAssetPercent> topFundAssets = null;

  @SerializedName("owner")
  private ProfilePublicShort owner = null;

  @SerializedName("tradingSchedule")
  private TradingScheduleInfo tradingSchedule = null;

  @SerializedName("statistic")
  private ProfitChart statistic = null;

  @SerializedName("personalDetails")
  private PersonalInvestingFundDetailsList personalDetails = null;

  @SerializedName("balance")
  private AmountWithCurrency balance = null;

  public FundInvestingDetailsList() {
  }
  public FundInvestingDetailsList id(UUID id) {
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

  public FundInvestingDetailsList logoUrl(String logoUrl) {
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

  public FundInvestingDetailsList url(String url) {
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

  public FundInvestingDetailsList color(String color) {
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

  public FundInvestingDetailsList title(String title) {
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

  public FundInvestingDetailsList description(String description) {
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

  public FundInvestingDetailsList creationDate(DateTime creationDate) {
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

  public FundInvestingDetailsList investorsCount(Integer investorsCount) {
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

  public FundInvestingDetailsList entryFeeCurrent(Double entryFeeCurrent) {
    this.entryFeeCurrent = entryFeeCurrent;
    return this;
  }

   /**
   * Get entryFeeCurrent
   * @return entryFeeCurrent
  **/
  @Schema(description = "")
  public Double getEntryFeeCurrent() {
    return entryFeeCurrent;
  }

  public void setEntryFeeCurrent(Double entryFeeCurrent) {
    this.entryFeeCurrent = entryFeeCurrent;
  }

  public FundInvestingDetailsList entryFeeSelected(Double entryFeeSelected) {
    this.entryFeeSelected = entryFeeSelected;
    return this;
  }

   /**
   * Get entryFeeSelected
   * @return entryFeeSelected
  **/
  @Schema(description = "")
  public Double getEntryFeeSelected() {
    return entryFeeSelected;
  }

  public void setEntryFeeSelected(Double entryFeeSelected) {
    this.entryFeeSelected = entryFeeSelected;
  }

  public FundInvestingDetailsList exitFeeCurrent(Double exitFeeCurrent) {
    this.exitFeeCurrent = exitFeeCurrent;
    return this;
  }

   /**
   * Get exitFeeCurrent
   * @return exitFeeCurrent
  **/
  @Schema(description = "")
  public Double getExitFeeCurrent() {
    return exitFeeCurrent;
  }

  public void setExitFeeCurrent(Double exitFeeCurrent) {
    this.exitFeeCurrent = exitFeeCurrent;
  }

  public FundInvestingDetailsList exitFeeSelected(Double exitFeeSelected) {
    this.exitFeeSelected = exitFeeSelected;
    return this;
  }

   /**
   * Get exitFeeSelected
   * @return exitFeeSelected
  **/
  @Schema(description = "")
  public Double getExitFeeSelected() {
    return exitFeeSelected;
  }

  public void setExitFeeSelected(Double exitFeeSelected) {
    this.exitFeeSelected = exitFeeSelected;
  }

  public FundInvestingDetailsList totalAssetsCount(Integer totalAssetsCount) {
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

  public FundInvestingDetailsList topFundAssets(List<FundAssetPercent> topFundAssets) {
    this.topFundAssets = topFundAssets;
    return this;
  }

  public FundInvestingDetailsList addTopFundAssetsItem(FundAssetPercent topFundAssetsItem) {
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

  public FundInvestingDetailsList owner(ProfilePublicShort owner) {
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

  public FundInvestingDetailsList tradingSchedule(TradingScheduleInfo tradingSchedule) {
    this.tradingSchedule = tradingSchedule;
    return this;
  }

   /**
   * Get tradingSchedule
   * @return tradingSchedule
  **/
  @Schema(description = "")
  public TradingScheduleInfo getTradingSchedule() {
    return tradingSchedule;
  }

  public void setTradingSchedule(TradingScheduleInfo tradingSchedule) {
    this.tradingSchedule = tradingSchedule;
  }

  public FundInvestingDetailsList statistic(ProfitChart statistic) {
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

  public FundInvestingDetailsList personalDetails(PersonalInvestingFundDetailsList personalDetails) {
    this.personalDetails = personalDetails;
    return this;
  }

   /**
   * Get personalDetails
   * @return personalDetails
  **/
  @Schema(description = "")
  public PersonalInvestingFundDetailsList getPersonalDetails() {
    return personalDetails;
  }

  public void setPersonalDetails(PersonalInvestingFundDetailsList personalDetails) {
    this.personalDetails = personalDetails;
  }

  public FundInvestingDetailsList balance(AmountWithCurrency balance) {
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
    FundInvestingDetailsList fundInvestingDetailsList = (FundInvestingDetailsList) o;
    return Objects.equals(this.id, fundInvestingDetailsList.id) &&
        Objects.equals(this.logoUrl, fundInvestingDetailsList.logoUrl) &&
        Objects.equals(this.url, fundInvestingDetailsList.url) &&
        Objects.equals(this.color, fundInvestingDetailsList.color) &&
        Objects.equals(this.title, fundInvestingDetailsList.title) &&
        Objects.equals(this.description, fundInvestingDetailsList.description) &&
        Objects.equals(this.creationDate, fundInvestingDetailsList.creationDate) &&
        Objects.equals(this.investorsCount, fundInvestingDetailsList.investorsCount) &&
        Objects.equals(this.entryFeeCurrent, fundInvestingDetailsList.entryFeeCurrent) &&
        Objects.equals(this.entryFeeSelected, fundInvestingDetailsList.entryFeeSelected) &&
        Objects.equals(this.exitFeeCurrent, fundInvestingDetailsList.exitFeeCurrent) &&
        Objects.equals(this.exitFeeSelected, fundInvestingDetailsList.exitFeeSelected) &&
        Objects.equals(this.totalAssetsCount, fundInvestingDetailsList.totalAssetsCount) &&
        Objects.equals(this.topFundAssets, fundInvestingDetailsList.topFundAssets) &&
        Objects.equals(this.owner, fundInvestingDetailsList.owner) &&
        Objects.equals(this.tradingSchedule, fundInvestingDetailsList.tradingSchedule) &&
        Objects.equals(this.statistic, fundInvestingDetailsList.statistic) &&
        Objects.equals(this.personalDetails, fundInvestingDetailsList.personalDetails) &&
        Objects.equals(this.balance, fundInvestingDetailsList.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, logoUrl, url, color, title, description, creationDate, investorsCount, entryFeeCurrent, entryFeeSelected, exitFeeCurrent, exitFeeSelected, totalAssetsCount, topFundAssets, owner, tradingSchedule, statistic, personalDetails, balance);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundInvestingDetailsList {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    investorsCount: ").append(toIndentedString(investorsCount)).append("\n");
    sb.append("    entryFeeCurrent: ").append(toIndentedString(entryFeeCurrent)).append("\n");
    sb.append("    entryFeeSelected: ").append(toIndentedString(entryFeeSelected)).append("\n");
    sb.append("    exitFeeCurrent: ").append(toIndentedString(exitFeeCurrent)).append("\n");
    sb.append("    exitFeeSelected: ").append(toIndentedString(exitFeeSelected)).append("\n");
    sb.append("    totalAssetsCount: ").append(toIndentedString(totalAssetsCount)).append("\n");
    sb.append("    topFundAssets: ").append(toIndentedString(topFundAssets)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    tradingSchedule: ").append(toIndentedString(tradingSchedule)).append("\n");
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
    out.writeValue(title);
    out.writeValue(description);
    out.writeValue(creationDate);
    out.writeValue(investorsCount);
    out.writeValue(entryFeeCurrent);
    out.writeValue(entryFeeSelected);
    out.writeValue(exitFeeCurrent);
    out.writeValue(exitFeeSelected);
    out.writeValue(totalAssetsCount);
    out.writeValue(topFundAssets);
    out.writeValue(owner);
    out.writeValue(tradingSchedule);
    out.writeValue(statistic);
    out.writeValue(personalDetails);
    out.writeValue(balance);
  }

  FundInvestingDetailsList(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    logoUrl = (String)in.readValue(null);
    url = (String)in.readValue(null);
    color = (String)in.readValue(null);
    title = (String)in.readValue(null);
    description = (String)in.readValue(null);
    creationDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    investorsCount = (Integer)in.readValue(null);
    entryFeeCurrent = (Double)in.readValue(null);
    entryFeeSelected = (Double)in.readValue(null);
    exitFeeCurrent = (Double)in.readValue(null);
    exitFeeSelected = (Double)in.readValue(null);
    totalAssetsCount = (Integer)in.readValue(null);
    topFundAssets = (List<FundAssetPercent>)in.readValue(FundAssetPercent.class.getClassLoader());
    owner = (ProfilePublicShort)in.readValue(ProfilePublicShort.class.getClassLoader());
    tradingSchedule = (TradingScheduleInfo)in.readValue(TradingScheduleInfo.class.getClassLoader());
    statistic = (ProfitChart)in.readValue(ProfitChart.class.getClassLoader());
    personalDetails = (PersonalInvestingFundDetailsList)in.readValue(PersonalInvestingFundDetailsList.class.getClassLoader());
    balance = (AmountWithCurrency)in.readValue(AmountWithCurrency.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundInvestingDetailsList> CREATOR = new Parcelable.Creator<FundInvestingDetailsList>() {
    public FundInvestingDetailsList createFromParcel(Parcel in) {
      return new FundInvestingDetailsList(in);
    }
    public FundInvestingDetailsList[] newArray(int size) {
      return new FundInvestingDetailsList[size];
    }
  };
}
