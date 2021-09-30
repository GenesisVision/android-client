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

import org.joda.time.DateTime;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProgramDetailsFull
 */


public class ProgramDetailsFull implements Parcelable
{
  public static final Parcelable.Creator<ProgramDetailsFull> CREATOR = new Parcelable.Creator<ProgramDetailsFull>()
  {
    public ProgramDetailsFull createFromParcel(Parcel in) {
      return new ProgramDetailsFull(in);
    }

    public ProgramDetailsFull[] newArray(int size) {
      return new ProgramDetailsFull[size];
    }
  };

  @SerializedName("type")
  private ProgramType type = null;

  @SerializedName("level")
  private Integer level = null;

  @SerializedName("levelProgress")
  private Double levelProgress = null;

  @SerializedName("periodDuration")
  private Integer periodDuration = null;

  @SerializedName("periodStarts")
  private DateTime periodStarts = null;

  @SerializedName("periodEnds")
  private DateTime periodEnds = null;

  @SerializedName("tradesDelay")
  private TradesDelay tradesDelay = null;

  @SerializedName("ageDays")
  private Double ageDays = null;

  @SerializedName("genesisRatio")
  private Double genesisRatio = null;

  @SerializedName("investmentScale")
  private Double investmentScale = null;

  @SerializedName("volumeScale")
  private Double volumeScale = null;

  @SerializedName("managementFeeSelected")
  private Double managementFeeSelected = null;

  @SerializedName("managementFeeCurrent")
  private Double managementFeeCurrent = null;

  @SerializedName("successFeeSelected")
  private Double successFeeSelected = null;

  @SerializedName("successFeeCurrent")
  private Double successFeeCurrent = null;

  @SerializedName("stopOutLevelSelected")
  private Double stopOutLevelSelected = null;

  @SerializedName("stopOutLevelCurrent")
  private Double stopOutLevelCurrent = null;

  @SerializedName("availableInvestmentBase")
  private Double availableInvestmentBase = null;

  @SerializedName("availableInvestmentLimit")
  private Double availableInvestmentLimit = null;

  @SerializedName("totalAvailableInvestment")
  private Double totalAvailableInvestment = null;

  @SerializedName("canShowPeriodHistory")
  private Boolean canShowPeriodHistory = null;

  @SerializedName("canShowFinancialStatistic")
  private Boolean canShowFinancialStatistic = null;

  @SerializedName("dailyPeriodDetails")
  private ProgramDailyPeriodDetails dailyPeriodDetails = null;

  @SerializedName("personalDetails")
  private PersonalProgramDetails personalDetails = null;

  public ProgramDetailsFull() {
  }

  ProgramDetailsFull(Parcel in) {
    type = (ProgramType) in.readValue(ProgramType.class.getClassLoader());
    level = (Integer) in.readValue(null);
    levelProgress = (Double) in.readValue(null);
    periodDuration = (Integer) in.readValue(null);
    periodStarts = (DateTime) in.readValue(DateTime.class.getClassLoader());
    periodEnds = (DateTime) in.readValue(DateTime.class.getClassLoader());
    tradesDelay = (TradesDelay) in.readValue(TradesDelay.class.getClassLoader());
    ageDays = (Double) in.readValue(null);
    genesisRatio = (Double) in.readValue(null);
    investmentScale = (Double) in.readValue(null);
    volumeScale = (Double) in.readValue(null);
    managementFeeSelected = (Double) in.readValue(null);
    managementFeeCurrent = (Double) in.readValue(null);
    successFeeSelected = (Double) in.readValue(null);
    successFeeCurrent = (Double) in.readValue(null);
    stopOutLevelSelected = (Double) in.readValue(null);
    stopOutLevelCurrent = (Double) in.readValue(null);
    availableInvestmentBase = (Double) in.readValue(null);
    availableInvestmentLimit = (Double) in.readValue(null);
    totalAvailableInvestment = (Double) in.readValue(null);
    canShowPeriodHistory = (Boolean) in.readValue(null);
    canShowFinancialStatistic = (Boolean) in.readValue(null);
    dailyPeriodDetails = (ProgramDailyPeriodDetails) in.readValue(ProgramDailyPeriodDetails.class.getClassLoader());
    personalDetails = (PersonalProgramDetails) in.readValue(PersonalProgramDetails.class.getClassLoader());
  }

  public ProgramDetailsFull type(ProgramType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   **/
  @Schema(description = "")
  public ProgramType getType() {
    return type;
  }

  public void setType(ProgramType type) {
    this.type = type;
  }

  public ProgramDetailsFull level(Integer level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   *
   * @return level
   **/
  @Schema(description = "")
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public ProgramDetailsFull levelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
    return this;
  }

  /**
   * Get levelProgress
   *
   * @return levelProgress
   **/
  @Schema(description = "")
  public Double getLevelProgress() {
    return levelProgress;
  }

  public void setLevelProgress(Double levelProgress) {
    this.levelProgress = levelProgress;
  }

  public ProgramDetailsFull periodDuration(Integer periodDuration) {
    this.periodDuration = periodDuration;
    return this;
  }

  /**
   * Get periodDuration
   *
   * @return periodDuration
   **/
  @Schema(description = "")
  public Integer getPeriodDuration() {
    return periodDuration;
  }

  public void setPeriodDuration(Integer periodDuration) {
    this.periodDuration = periodDuration;
  }

  public ProgramDetailsFull periodStarts(DateTime periodStarts) {
    this.periodStarts = periodStarts;
    return this;
  }

  /**
   * Get periodStarts
   *
   * @return periodStarts
   **/
  @Schema(description = "")
  public DateTime getPeriodStarts() {
    return periodStarts;
  }

  public void setPeriodStarts(DateTime periodStarts) {
    this.periodStarts = periodStarts;
  }

  public ProgramDetailsFull periodEnds(DateTime periodEnds) {
    this.periodEnds = periodEnds;
    return this;
  }

  /**
   * Get periodEnds
   *
   * @return periodEnds
   **/
  @Schema(description = "")
  public DateTime getPeriodEnds() {
    return periodEnds;
  }

  public void setPeriodEnds(DateTime periodEnds) {
    this.periodEnds = periodEnds;
  }

  public ProgramDetailsFull tradesDelay(TradesDelay tradesDelay) {
    this.tradesDelay = tradesDelay;
    return this;
  }

  /**
   * Get tradesDelay
   *
   * @return tradesDelay
   **/
  @Schema(description = "")
  public TradesDelay getTradesDelay() {
    return tradesDelay;
  }

  public void setTradesDelay(TradesDelay tradesDelay) {
    this.tradesDelay = tradesDelay;
  }

  public ProgramDetailsFull ageDays(Double ageDays) {
    this.ageDays = ageDays;
    return this;
  }

  /**
   * Get ageDays
   *
   * @return ageDays
   **/
  @Schema(description = "")
  public Double getAgeDays() {
    return ageDays;
  }

  public void setAgeDays(Double ageDays) {
    this.ageDays = ageDays;
  }

  public ProgramDetailsFull genesisRatio(Double genesisRatio) {
    this.genesisRatio = genesisRatio;
    return this;
  }

  /**
   * Get genesisRatio
   *
   * @return genesisRatio
   **/
  @Schema(description = "")
  public Double getGenesisRatio() {
    return genesisRatio;
  }

  public void setGenesisRatio(Double genesisRatio) {
    this.genesisRatio = genesisRatio;
  }

  public ProgramDetailsFull investmentScale(Double investmentScale) {
    this.investmentScale = investmentScale;
    return this;
  }

  /**
   * Get investmentScale
   *
   * @return investmentScale
   **/
  @Schema(description = "")
  public Double getInvestmentScale() {
    return investmentScale;
  }

  public void setInvestmentScale(Double investmentScale) {
    this.investmentScale = investmentScale;
  }

  public ProgramDetailsFull volumeScale(Double volumeScale) {
    this.volumeScale = volumeScale;
    return this;
  }

  /**
   * Get volumeScale
   *
   * @return volumeScale
   **/
  @Schema(description = "")
  public Double getVolumeScale() {
    return volumeScale;
  }

  public void setVolumeScale(Double volumeScale) {
    this.volumeScale = volumeScale;
  }

  public ProgramDetailsFull managementFeeSelected(Double managementFeeSelected) {
    this.managementFeeSelected = managementFeeSelected;
    return this;
  }

  /**
   * Get managementFeeSelected
   *
   * @return managementFeeSelected
   **/
  @Schema(description = "")
  public Double getManagementFeeSelected() {
    return managementFeeSelected;
  }

  public void setManagementFeeSelected(Double managementFeeSelected) {
    this.managementFeeSelected = managementFeeSelected;
  }

  public ProgramDetailsFull managementFeeCurrent(Double managementFeeCurrent) {
    this.managementFeeCurrent = managementFeeCurrent;
    return this;
  }

  /**
   * Get managementFeeCurrent
   *
   * @return managementFeeCurrent
   **/
  @Schema(description = "")
  public Double getManagementFeeCurrent() {
    return managementFeeCurrent;
  }

  public void setManagementFeeCurrent(Double managementFeeCurrent) {
    this.managementFeeCurrent = managementFeeCurrent;
  }

  public ProgramDetailsFull successFeeSelected(Double successFeeSelected) {
    this.successFeeSelected = successFeeSelected;
    return this;
  }

  /**
   * Get successFeeSelected
   *
   * @return successFeeSelected
   **/
  @Schema(description = "")
  public Double getSuccessFeeSelected() {
    return successFeeSelected;
  }

  public void setSuccessFeeSelected(Double successFeeSelected) {
    this.successFeeSelected = successFeeSelected;
  }

  public ProgramDetailsFull successFeeCurrent(Double successFeeCurrent) {
    this.successFeeCurrent = successFeeCurrent;
    return this;
  }

  /**
   * Get successFeeCurrent
   *
   * @return successFeeCurrent
   **/
  @Schema(description = "")
  public Double getSuccessFeeCurrent() {
    return successFeeCurrent;
  }

  public void setSuccessFeeCurrent(Double successFeeCurrent) {
    this.successFeeCurrent = successFeeCurrent;
  }

  public ProgramDetailsFull stopOutLevelSelected(Double stopOutLevelSelected) {
    this.stopOutLevelSelected = stopOutLevelSelected;
    return this;
  }

  /**
   * Get stopOutLevelSelected
   *
   * @return stopOutLevelSelected
   **/
  @Schema(description = "")
  public Double getStopOutLevelSelected() {
    return stopOutLevelSelected;
  }

  public void setStopOutLevelSelected(Double stopOutLevelSelected) {
    this.stopOutLevelSelected = stopOutLevelSelected;
  }

  public ProgramDetailsFull stopOutLevelCurrent(Double stopOutLevelCurrent) {
    this.stopOutLevelCurrent = stopOutLevelCurrent;
    return this;
  }

  /**
   * Get stopOutLevelCurrent
   *
   * @return stopOutLevelCurrent
   **/
  @Schema(description = "")
  public Double getStopOutLevelCurrent() {
    return stopOutLevelCurrent;
  }

  public void setStopOutLevelCurrent(Double stopOutLevelCurrent) {
    this.stopOutLevelCurrent = stopOutLevelCurrent;
  }

  public ProgramDetailsFull availableInvestmentBase(Double availableInvestmentBase) {
    this.availableInvestmentBase = availableInvestmentBase;
    return this;
  }

  /**
   * Get availableInvestmentBase
   *
   * @return availableInvestmentBase
   **/
  @Schema(description = "")
  public Double getAvailableInvestmentBase() {
    return availableInvestmentBase;
  }

  public void setAvailableInvestmentBase(Double availableInvestmentBase) {
    this.availableInvestmentBase = availableInvestmentBase;
  }

  public ProgramDetailsFull availableInvestmentLimit(Double availableInvestmentLimit) {
    this.availableInvestmentLimit = availableInvestmentLimit;
    return this;
  }

  /**
   * Get availableInvestmentLimit
   *
   * @return availableInvestmentLimit
   **/
  @Schema(description = "")
  public Double getAvailableInvestmentLimit() {
    return availableInvestmentLimit;
  }

  public void setAvailableInvestmentLimit(Double availableInvestmentLimit) {
    this.availableInvestmentLimit = availableInvestmentLimit;
  }

  public ProgramDetailsFull totalAvailableInvestment(Double totalAvailableInvestment) {
    this.totalAvailableInvestment = totalAvailableInvestment;
    return this;
  }

  /**
   * Get totalAvailableInvestment
   *
   * @return totalAvailableInvestment
   **/
  @Schema(description = "")
  public Double getTotalAvailableInvestment() {
    return totalAvailableInvestment;
  }

  public void setTotalAvailableInvestment(Double totalAvailableInvestment) {
    this.totalAvailableInvestment = totalAvailableInvestment;
  }

  public ProgramDetailsFull canShowPeriodHistory(Boolean canShowPeriodHistory) {
    this.canShowPeriodHistory = canShowPeriodHistory;
    return this;
  }

  /**
   * Get canShowPeriodHistory
   *
   * @return canShowPeriodHistory
   **/
  @Schema(description = "")
  public Boolean isCanShowPeriodHistory() {
    return canShowPeriodHistory;
  }

  public void setCanShowPeriodHistory(Boolean canShowPeriodHistory) {
    this.canShowPeriodHistory = canShowPeriodHistory;
  }

  public ProgramDetailsFull canShowFinancialStatistic(Boolean canShowFinancialStatistic) {
    this.canShowFinancialStatistic = canShowFinancialStatistic;
    return this;
  }

  /**
   * Get canShowFinancialStatistic
   *
   * @return canShowFinancialStatistic
   **/
  @Schema(description = "")
  public Boolean isCanShowFinancialStatistic() {
    return canShowFinancialStatistic;
  }

  public void setCanShowFinancialStatistic(Boolean canShowFinancialStatistic) {
    this.canShowFinancialStatistic = canShowFinancialStatistic;
  }

  public ProgramDetailsFull dailyPeriodDetails(ProgramDailyPeriodDetails dailyPeriodDetails) {
    this.dailyPeriodDetails = dailyPeriodDetails;
    return this;
  }

  /**
   * Get dailyPeriodDetails
   *
   * @return dailyPeriodDetails
   **/
  @Schema(description = "")
  public ProgramDailyPeriodDetails getDailyPeriodDetails() {
    return dailyPeriodDetails;
  }

  public void setDailyPeriodDetails(ProgramDailyPeriodDetails dailyPeriodDetails) {
    this.dailyPeriodDetails = dailyPeriodDetails;
  }

  public ProgramDetailsFull personalDetails(PersonalProgramDetails personalDetails) {
    this.personalDetails = personalDetails;
    return this;
  }

  /**
   * Get personalDetails
   *
   * @return personalDetails
   **/
  @Schema(description = "")
  public PersonalProgramDetails getPersonalDetails() {
    return personalDetails;
  }

  public void setPersonalDetails(PersonalProgramDetails personalDetails) {
    this.personalDetails = personalDetails;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramDetailsFull programDetailsFull = (ProgramDetailsFull) o;
    return Objects.equals(this.type, programDetailsFull.type) &&
            Objects.equals(this.level, programDetailsFull.level) &&
            Objects.equals(this.levelProgress, programDetailsFull.levelProgress) &&
            Objects.equals(this.periodDuration, programDetailsFull.periodDuration) &&
            Objects.equals(this.periodStarts, programDetailsFull.periodStarts) &&
            Objects.equals(this.periodEnds, programDetailsFull.periodEnds) &&
            Objects.equals(this.tradesDelay, programDetailsFull.tradesDelay) &&
            Objects.equals(this.ageDays, programDetailsFull.ageDays) &&
            Objects.equals(this.genesisRatio, programDetailsFull.genesisRatio) &&
            Objects.equals(this.investmentScale, programDetailsFull.investmentScale) &&
            Objects.equals(this.volumeScale, programDetailsFull.volumeScale) &&
            Objects.equals(this.managementFeeSelected, programDetailsFull.managementFeeSelected) &&
            Objects.equals(this.managementFeeCurrent, programDetailsFull.managementFeeCurrent) &&
            Objects.equals(this.successFeeSelected, programDetailsFull.successFeeSelected) &&
            Objects.equals(this.successFeeCurrent, programDetailsFull.successFeeCurrent) &&
            Objects.equals(this.stopOutLevelSelected, programDetailsFull.stopOutLevelSelected) &&
            Objects.equals(this.stopOutLevelCurrent, programDetailsFull.stopOutLevelCurrent) &&
            Objects.equals(this.availableInvestmentBase, programDetailsFull.availableInvestmentBase) &&
            Objects.equals(this.availableInvestmentLimit, programDetailsFull.availableInvestmentLimit) &&
            Objects.equals(this.totalAvailableInvestment, programDetailsFull.totalAvailableInvestment) &&
            Objects.equals(this.canShowPeriodHistory, programDetailsFull.canShowPeriodHistory) &&
            Objects.equals(this.canShowFinancialStatistic, programDetailsFull.canShowFinancialStatistic) &&
            Objects.equals(this.dailyPeriodDetails, programDetailsFull.dailyPeriodDetails) &&
            Objects.equals(this.personalDetails, programDetailsFull.personalDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, level, levelProgress, periodDuration, periodStarts, periodEnds, tradesDelay, ageDays, genesisRatio, investmentScale, volumeScale, managementFeeSelected, managementFeeCurrent, successFeeSelected, successFeeCurrent, stopOutLevelSelected, stopOutLevelCurrent, availableInvestmentBase, availableInvestmentLimit, totalAvailableInvestment, canShowPeriodHistory, canShowFinancialStatistic, dailyPeriodDetails, personalDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramDetailsFull {\n");

    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    levelProgress: ").append(toIndentedString(levelProgress)).append("\n");
    sb.append("    periodDuration: ").append(toIndentedString(periodDuration)).append("\n");
    sb.append("    periodStarts: ").append(toIndentedString(periodStarts)).append("\n");
    sb.append("    periodEnds: ").append(toIndentedString(periodEnds)).append("\n");
    sb.append("    tradesDelay: ").append(toIndentedString(tradesDelay)).append("\n");
    sb.append("    ageDays: ").append(toIndentedString(ageDays)).append("\n");
    sb.append("    genesisRatio: ").append(toIndentedString(genesisRatio)).append("\n");
    sb.append("    investmentScale: ").append(toIndentedString(investmentScale)).append("\n");
    sb.append("    volumeScale: ").append(toIndentedString(volumeScale)).append("\n");
    sb.append("    managementFeeSelected: ").append(toIndentedString(managementFeeSelected)).append("\n");
    sb.append("    managementFeeCurrent: ").append(toIndentedString(managementFeeCurrent)).append("\n");
    sb.append("    successFeeSelected: ").append(toIndentedString(successFeeSelected)).append("\n");
    sb.append("    successFeeCurrent: ").append(toIndentedString(successFeeCurrent)).append("\n");
    sb.append("    stopOutLevelSelected: ").append(toIndentedString(stopOutLevelSelected)).append("\n");
    sb.append("    stopOutLevelCurrent: ").append(toIndentedString(stopOutLevelCurrent)).append("\n");
    sb.append("    availableInvestmentBase: ").append(toIndentedString(availableInvestmentBase)).append("\n");
    sb.append("    availableInvestmentLimit: ").append(toIndentedString(availableInvestmentLimit)).append("\n");
    sb.append("    totalAvailableInvestment: ").append(toIndentedString(totalAvailableInvestment)).append("\n");
    sb.append("    canShowPeriodHistory: ").append(toIndentedString(canShowPeriodHistory)).append("\n");
    sb.append("    canShowFinancialStatistic: ").append(toIndentedString(canShowFinancialStatistic)).append("\n");
    sb.append("    dailyPeriodDetails: ").append(toIndentedString(dailyPeriodDetails)).append("\n");
    sb.append("    personalDetails: ").append(toIndentedString(personalDetails)).append("\n");
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
    out.writeValue(level);
    out.writeValue(levelProgress);
    out.writeValue(periodDuration);
    out.writeValue(periodStarts);
    out.writeValue(periodEnds);
    out.writeValue(tradesDelay);
    out.writeValue(ageDays);
    out.writeValue(genesisRatio);
    out.writeValue(investmentScale);
    out.writeValue(volumeScale);
    out.writeValue(managementFeeSelected);
    out.writeValue(managementFeeCurrent);
    out.writeValue(successFeeSelected);
    out.writeValue(successFeeCurrent);
    out.writeValue(stopOutLevelSelected);
    out.writeValue(stopOutLevelCurrent);
    out.writeValue(availableInvestmentBase);
    out.writeValue(availableInvestmentLimit);
    out.writeValue(totalAvailableInvestment);
    out.writeValue(canShowPeriodHistory);
    out.writeValue(canShowFinancialStatistic);
    out.writeValue(dailyPeriodDetails);
    out.writeValue(personalDetails);
  }

  public int describeContents() {
    return 0;
  }
}
