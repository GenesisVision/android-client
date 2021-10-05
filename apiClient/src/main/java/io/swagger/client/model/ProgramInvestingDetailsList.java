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
import io.swagger.client.model.BrokerDetails;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PersonalInvestingProgramDetailsList;
import io.swagger.client.model.ProfilePublicShort;
import io.swagger.client.model.ProfitChart;
import io.swagger.client.model.ProgramDailyPeriodDetails;
import io.swagger.client.model.ProgramType;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * ProgramInvestingDetailsList
 */


public class ProgramInvestingDetailsList implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("type")
  private ProgramType type = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("creationDate")
  private DateTime creationDate = null;

  @SerializedName("currency")
  private Currency currency = null;

  @SerializedName("availableToInvest")
  private Double availableToInvest = null;

  @SerializedName("level")
  private Integer level = null;

  @SerializedName("levelProgress")
  private Double levelProgress = null;

  @SerializedName("periodDuration")
  private Integer periodDuration = null;

  @SerializedName("investorsCount")
  private Integer investorsCount = null;

  @SerializedName("periodStarts")
  private DateTime periodStarts = null;

  @SerializedName("periodEnds")
  private DateTime periodEnds = null;

  @SerializedName("managementFeeSelected")
  private Double managementFeeSelected = null;

  @SerializedName("managementFeeCurrent")
  private Double managementFeeCurrent = null;

  @SerializedName("successFeeSelected")
  private Double successFeeSelected = null;

  @SerializedName("successFeeCurrent")
  private Double successFeeCurrent = null;

  @SerializedName("totalAvailableInvestment")
  private Double totalAvailableInvestment = null;

  @SerializedName("stopOutLevelSelected")
  private Double stopOutLevelSelected = null;

  @SerializedName("stopOutLevelCurrent")
  private Double stopOutLevelCurrent = null;

  @SerializedName("owner")
  private ProfilePublicShort owner = null;

  @SerializedName("brokerDetails")
  private BrokerDetails brokerDetails = null;

  @SerializedName("personalDetails")
  private PersonalInvestingProgramDetailsList personalDetails = null;

  @SerializedName("tags")
  private List<Tag> tags = null;

  @SerializedName("statistic")
  private ProfitChart statistic = null;

  @SerializedName("balance")
  private AmountWithCurrency balance = null;

  @SerializedName("dailyPeriodDetails")
  private ProgramDailyPeriodDetails dailyPeriodDetails = null;

  public ProgramInvestingDetailsList() {
  }
  public ProgramInvestingDetailsList id(UUID id) {
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

  public ProgramInvestingDetailsList type(ProgramType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public ProgramType getType() {
    return type;
  }

  public void setType(ProgramType type) {
    this.type = type;
  }

  public ProgramInvestingDetailsList logoUrl(String logoUrl) {
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

  public ProgramInvestingDetailsList url(String url) {
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

  public ProgramInvestingDetailsList color(String color) {
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

  public ProgramInvestingDetailsList title(String title) {
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

  public ProgramInvestingDetailsList creationDate(DateTime creationDate) {
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

  public ProgramInvestingDetailsList currency(Currency currency) {
    this.currency = currency;
    return this;
  }

   /**
   * Get currency
   * @return currency
  **/
  @Schema(description = "")
  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public ProgramInvestingDetailsList availableToInvest(Double availableToInvest) {
    this.availableToInvest = availableToInvest;
    return this;
  }

   /**
   * Get availableToInvest
   * @return availableToInvest
  **/
  @Schema(description = "")
  public Double getAvailableToInvest() {
    return availableToInvest;
  }

  public void setAvailableToInvest(Double availableToInvest) {
    this.availableToInvest = availableToInvest;
  }

  public ProgramInvestingDetailsList level(Integer level) {
    this.level = level;
    return this;
  }

   /**
   * Get level
   * @return level
  **/
  @Schema(description = "")
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public ProgramInvestingDetailsList levelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
    return this;
  }

   /**
   * Get levelProgress
   * @return levelProgress
  **/
  @Schema(description = "")
  public Double getLevelProgress() {
    return levelProgress;
  }

  public void setLevelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
  }

  public ProgramInvestingDetailsList periodDuration(Integer periodDuration) {
    this.periodDuration = periodDuration;
    return this;
  }

   /**
   * Get periodDuration
   * @return periodDuration
  **/
  @Schema(description = "")
  public Integer getPeriodDuration() {
    return periodDuration;
  }

  public void setPeriodDuration(Integer periodDuration) {
    this.periodDuration = periodDuration;
  }

  public ProgramInvestingDetailsList investorsCount(Integer investorsCount) {
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

  public ProgramInvestingDetailsList periodStarts(DateTime periodStarts) {
    this.periodStarts = periodStarts;
    return this;
  }

   /**
   * Get periodStarts
   * @return periodStarts
  **/
  @Schema(description = "")
  public DateTime getPeriodStarts() {
    return periodStarts;
  }

  public void setPeriodStarts(DateTime periodStarts) {
    this.periodStarts = periodStarts;
  }

  public ProgramInvestingDetailsList periodEnds(DateTime periodEnds) {
    this.periodEnds = periodEnds;
    return this;
  }

   /**
   * Get periodEnds
   * @return periodEnds
  **/
  @Schema(description = "")
  public DateTime getPeriodEnds() {
    return periodEnds;
  }

  public void setPeriodEnds(DateTime periodEnds) {
    this.periodEnds = periodEnds;
  }

  public ProgramInvestingDetailsList managementFeeSelected(Double managementFeeSelected) {
    this.managementFeeSelected = managementFeeSelected;
    return this;
  }

   /**
   * Get managementFeeSelected
   * @return managementFeeSelected
  **/
  @Schema(description = "")
  public Double getManagementFeeSelected() {
    return managementFeeSelected;
  }

  public void setManagementFeeSelected(Double managementFeeSelected) {
    this.managementFeeSelected = managementFeeSelected;
  }

  public ProgramInvestingDetailsList managementFeeCurrent(Double managementFeeCurrent) {
    this.managementFeeCurrent = managementFeeCurrent;
    return this;
  }

   /**
   * Get managementFeeCurrent
   * @return managementFeeCurrent
  **/
  @Schema(description = "")
  public Double getManagementFeeCurrent() {
    return managementFeeCurrent;
  }

  public void setManagementFeeCurrent(Double managementFeeCurrent) {
    this.managementFeeCurrent = managementFeeCurrent;
  }

  public ProgramInvestingDetailsList successFeeSelected(Double successFeeSelected) {
    this.successFeeSelected = successFeeSelected;
    return this;
  }

   /**
   * Get successFeeSelected
   * @return successFeeSelected
  **/
  @Schema(description = "")
  public Double getSuccessFeeSelected() {
    return successFeeSelected;
  }

  public void setSuccessFeeSelected(Double successFeeSelected) {
    this.successFeeSelected = successFeeSelected;
  }

  public ProgramInvestingDetailsList successFeeCurrent(Double successFeeCurrent) {
    this.successFeeCurrent = successFeeCurrent;
    return this;
  }

   /**
   * Get successFeeCurrent
   * @return successFeeCurrent
  **/
  @Schema(description = "")
  public Double getSuccessFeeCurrent() {
    return successFeeCurrent;
  }

  public void setSuccessFeeCurrent(Double successFeeCurrent) {
    this.successFeeCurrent = successFeeCurrent;
  }

  public ProgramInvestingDetailsList totalAvailableInvestment(Double totalAvailableInvestment) {
    this.totalAvailableInvestment = totalAvailableInvestment;
    return this;
  }

   /**
   * Get totalAvailableInvestment
   * @return totalAvailableInvestment
  **/
  @Schema(description = "")
  public Double getTotalAvailableInvestment() {
    return totalAvailableInvestment;
  }

  public void setTotalAvailableInvestment(Double totalAvailableInvestment) {
    this.totalAvailableInvestment = totalAvailableInvestment;
  }

  public ProgramInvestingDetailsList stopOutLevelSelected(Double stopOutLevelSelected) {
    this.stopOutLevelSelected = stopOutLevelSelected;
    return this;
  }

   /**
   * Get stopOutLevelSelected
   * @return stopOutLevelSelected
  **/
  @Schema(description = "")
  public Double getStopOutLevelSelected() {
    return stopOutLevelSelected;
  }

  public void setStopOutLevelSelected(Double stopOutLevelSelected) {
    this.stopOutLevelSelected = stopOutLevelSelected;
  }

  public ProgramInvestingDetailsList stopOutLevelCurrent(Double stopOutLevelCurrent) {
    this.stopOutLevelCurrent = stopOutLevelCurrent;
    return this;
  }

   /**
   * Get stopOutLevelCurrent
   * @return stopOutLevelCurrent
  **/
  @Schema(description = "")
  public Double getStopOutLevelCurrent() {
    return stopOutLevelCurrent;
  }

  public void setStopOutLevelCurrent(Double stopOutLevelCurrent) {
    this.stopOutLevelCurrent = stopOutLevelCurrent;
  }

  public ProgramInvestingDetailsList owner(ProfilePublicShort owner) {
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

  public ProgramInvestingDetailsList brokerDetails(BrokerDetails brokerDetails) {
    this.brokerDetails = brokerDetails;
    return this;
  }

   /**
   * Get brokerDetails
   * @return brokerDetails
  **/
  @Schema(description = "")
  public BrokerDetails getBrokerDetails() {
    return brokerDetails;
  }

  public void setBrokerDetails(BrokerDetails brokerDetails) {
    this.brokerDetails = brokerDetails;
  }

  public ProgramInvestingDetailsList personalDetails(PersonalInvestingProgramDetailsList personalDetails) {
    this.personalDetails = personalDetails;
    return this;
  }

   /**
   * Get personalDetails
   * @return personalDetails
  **/
  @Schema(description = "")
  public PersonalInvestingProgramDetailsList getPersonalDetails() {
    return personalDetails;
  }

  public void setPersonalDetails(PersonalInvestingProgramDetailsList personalDetails) {
    this.personalDetails = personalDetails;
  }

  public ProgramInvestingDetailsList tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public ProgramInvestingDetailsList addTagsItem(Tag tagsItem) {
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

  public ProgramInvestingDetailsList statistic(ProfitChart statistic) {
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

  public ProgramInvestingDetailsList balance(AmountWithCurrency balance) {
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

  public ProgramInvestingDetailsList dailyPeriodDetails(ProgramDailyPeriodDetails dailyPeriodDetails) {
    this.dailyPeriodDetails = dailyPeriodDetails;
    return this;
  }

   /**
   * Get dailyPeriodDetails
   * @return dailyPeriodDetails
  **/
  @Schema(description = "")
  public ProgramDailyPeriodDetails getDailyPeriodDetails() {
    return dailyPeriodDetails;
  }

  public void setDailyPeriodDetails(ProgramDailyPeriodDetails dailyPeriodDetails) {
    this.dailyPeriodDetails = dailyPeriodDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramInvestingDetailsList programInvestingDetailsList = (ProgramInvestingDetailsList) o;
    return Objects.equals(this.id, programInvestingDetailsList.id) &&
        Objects.equals(this.type, programInvestingDetailsList.type) &&
        Objects.equals(this.logoUrl, programInvestingDetailsList.logoUrl) &&
        Objects.equals(this.url, programInvestingDetailsList.url) &&
        Objects.equals(this.color, programInvestingDetailsList.color) &&
        Objects.equals(this.title, programInvestingDetailsList.title) &&
        Objects.equals(this.creationDate, programInvestingDetailsList.creationDate) &&
        Objects.equals(this.currency, programInvestingDetailsList.currency) &&
        Objects.equals(this.availableToInvest, programInvestingDetailsList.availableToInvest) &&
        Objects.equals(this.level, programInvestingDetailsList.level) &&
        Objects.equals(this.levelProgress, programInvestingDetailsList.levelProgress) &&
        Objects.equals(this.periodDuration, programInvestingDetailsList.periodDuration) &&
        Objects.equals(this.investorsCount, programInvestingDetailsList.investorsCount) &&
        Objects.equals(this.periodStarts, programInvestingDetailsList.periodStarts) &&
        Objects.equals(this.periodEnds, programInvestingDetailsList.periodEnds) &&
        Objects.equals(this.managementFeeSelected, programInvestingDetailsList.managementFeeSelected) &&
        Objects.equals(this.managementFeeCurrent, programInvestingDetailsList.managementFeeCurrent) &&
        Objects.equals(this.successFeeSelected, programInvestingDetailsList.successFeeSelected) &&
        Objects.equals(this.successFeeCurrent, programInvestingDetailsList.successFeeCurrent) &&
        Objects.equals(this.totalAvailableInvestment, programInvestingDetailsList.totalAvailableInvestment) &&
        Objects.equals(this.stopOutLevelSelected, programInvestingDetailsList.stopOutLevelSelected) &&
        Objects.equals(this.stopOutLevelCurrent, programInvestingDetailsList.stopOutLevelCurrent) &&
        Objects.equals(this.owner, programInvestingDetailsList.owner) &&
        Objects.equals(this.brokerDetails, programInvestingDetailsList.brokerDetails) &&
        Objects.equals(this.personalDetails, programInvestingDetailsList.personalDetails) &&
        Objects.equals(this.tags, programInvestingDetailsList.tags) &&
        Objects.equals(this.statistic, programInvestingDetailsList.statistic) &&
        Objects.equals(this.balance, programInvestingDetailsList.balance) &&
        Objects.equals(this.dailyPeriodDetails, programInvestingDetailsList.dailyPeriodDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, logoUrl, url, color, title, creationDate, currency, availableToInvest, level, levelProgress, periodDuration, investorsCount, periodStarts, periodEnds, managementFeeSelected, managementFeeCurrent, successFeeSelected, successFeeCurrent, totalAvailableInvestment, stopOutLevelSelected, stopOutLevelCurrent, owner, brokerDetails, personalDetails, tags, statistic, balance, dailyPeriodDetails);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramInvestingDetailsList {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    availableToInvest: ").append(toIndentedString(availableToInvest)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    levelProgress: ").append(toIndentedString(levelProgress)).append("\n");
    sb.append("    periodDuration: ").append(toIndentedString(periodDuration)).append("\n");
    sb.append("    investorsCount: ").append(toIndentedString(investorsCount)).append("\n");
    sb.append("    periodStarts: ").append(toIndentedString(periodStarts)).append("\n");
    sb.append("    periodEnds: ").append(toIndentedString(periodEnds)).append("\n");
    sb.append("    managementFeeSelected: ").append(toIndentedString(managementFeeSelected)).append("\n");
    sb.append("    managementFeeCurrent: ").append(toIndentedString(managementFeeCurrent)).append("\n");
    sb.append("    successFeeSelected: ").append(toIndentedString(successFeeSelected)).append("\n");
    sb.append("    successFeeCurrent: ").append(toIndentedString(successFeeCurrent)).append("\n");
    sb.append("    totalAvailableInvestment: ").append(toIndentedString(totalAvailableInvestment)).append("\n");
    sb.append("    stopOutLevelSelected: ").append(toIndentedString(stopOutLevelSelected)).append("\n");
    sb.append("    stopOutLevelCurrent: ").append(toIndentedString(stopOutLevelCurrent)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    brokerDetails: ").append(toIndentedString(brokerDetails)).append("\n");
    sb.append("    personalDetails: ").append(toIndentedString(personalDetails)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    statistic: ").append(toIndentedString(statistic)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    dailyPeriodDetails: ").append(toIndentedString(dailyPeriodDetails)).append("\n");
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
    out.writeValue(type);
    out.writeValue(logoUrl);
    out.writeValue(url);
    out.writeValue(color);
    out.writeValue(title);
    out.writeValue(creationDate);
    out.writeValue(currency);
    out.writeValue(availableToInvest);
    out.writeValue(level);
    out.writeValue(levelProgress);
    out.writeValue(periodDuration);
    out.writeValue(investorsCount);
    out.writeValue(periodStarts);
    out.writeValue(periodEnds);
    out.writeValue(managementFeeSelected);
    out.writeValue(managementFeeCurrent);
    out.writeValue(successFeeSelected);
    out.writeValue(successFeeCurrent);
    out.writeValue(totalAvailableInvestment);
    out.writeValue(stopOutLevelSelected);
    out.writeValue(stopOutLevelCurrent);
    out.writeValue(owner);
    out.writeValue(brokerDetails);
    out.writeValue(personalDetails);
    out.writeValue(tags);
    out.writeValue(statistic);
    out.writeValue(balance);
    out.writeValue(dailyPeriodDetails);
  }

  ProgramInvestingDetailsList(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    type = (ProgramType)in.readValue(ProgramType.class.getClassLoader());
    logoUrl = (String)in.readValue(null);
    url = (String)in.readValue(null);
    color = (String)in.readValue(null);
    title = (String)in.readValue(null);
    creationDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    currency = (Currency)in.readValue(Currency.class.getClassLoader());
    availableToInvest = (Double)in.readValue(null);
    level = (Integer)in.readValue(null);
    levelProgress = (Double)in.readValue(null);
    periodDuration = (Integer)in.readValue(null);
    investorsCount = (Integer)in.readValue(null);
    periodStarts = (DateTime)in.readValue(DateTime.class.getClassLoader());
    periodEnds = (DateTime)in.readValue(DateTime.class.getClassLoader());
    managementFeeSelected = (Double)in.readValue(null);
    managementFeeCurrent = (Double)in.readValue(null);
    successFeeSelected = (Double)in.readValue(null);
    successFeeCurrent = (Double)in.readValue(null);
    totalAvailableInvestment = (Double)in.readValue(null);
    stopOutLevelSelected = (Double)in.readValue(null);
    stopOutLevelCurrent = (Double)in.readValue(null);
    owner = (ProfilePublicShort)in.readValue(ProfilePublicShort.class.getClassLoader());
    brokerDetails = (BrokerDetails)in.readValue(BrokerDetails.class.getClassLoader());
    personalDetails = (PersonalInvestingProgramDetailsList)in.readValue(PersonalInvestingProgramDetailsList.class.getClassLoader());
    tags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    statistic = (ProfitChart)in.readValue(ProfitChart.class.getClassLoader());
    balance = (AmountWithCurrency)in.readValue(AmountWithCurrency.class.getClassLoader());
    dailyPeriodDetails = (ProgramDailyPeriodDetails)in.readValue(ProgramDailyPeriodDetails.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ProgramInvestingDetailsList> CREATOR = new Parcelable.Creator<ProgramInvestingDetailsList>() {
    public ProgramInvestingDetailsList createFromParcel(Parcel in) {
      return new ProgramInvestingDetailsList(in);
    }
    public ProgramInvestingDetailsList[] newArray(int size) {
      return new ProgramInvestingDetailsList[size];
    }
  };
}
