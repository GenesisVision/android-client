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
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.BrokerDetails;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ProfilePublic;
import io.swagger.client.model.SignalDetachMode;
import io.swagger.client.model.SignalSubscriberInfo;
import io.swagger.client.model.SubscriptionMode;
import io.swagger.client.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * SignalSubscription
 */


public class SignalSubscription implements Parcelable {
  @SerializedName("subscriberInfo")
  private SignalSubscriberInfo subscriberInfo = null;

  @SerializedName("asset")
  private AssetDetails asset = null;

  @SerializedName("assetOwner")
  private ProfilePublic assetOwner = null;

  @SerializedName("assetBrokerDetails")
  private BrokerDetails assetBrokerDetails = null;

  @SerializedName("assetTags")
  private List<Tag> assetTags = null;

  @SerializedName("status")
  private String status = null;

  @SerializedName("subscriptionDate")
  private DateTime subscriptionDate = null;

  @SerializedName("unsubscriptionDate")
  private DateTime unsubscriptionDate = null;

  @SerializedName("hasSignalAccount")
  private Boolean hasSignalAccount = null;

  @SerializedName("hasActiveSubscription")
  private Boolean hasActiveSubscription = null;

  @SerializedName("isExternal")
  private Boolean isExternal = null;

  @SerializedName("mode")
  private SubscriptionMode mode = null;

  @SerializedName("detachMode")
  private SignalDetachMode detachMode = null;

  @SerializedName("percent")
  private Double percent = null;

  @SerializedName("openTolerancePercent")
  private Double openTolerancePercent = null;

  @SerializedName("fixedVolume")
  private Double fixedVolume = null;

  @SerializedName("fixedCurrency")
  private Currency fixedCurrency = null;

  @SerializedName("totalProfit")
  private Double totalProfit = null;

  @SerializedName("totalVolume")
  private Double totalVolume = null;

  @SerializedName("successFeePersonal")
  private Double successFeePersonal = null;

  @SerializedName("volumeFeePersonal")
  private Double volumeFeePersonal = null;

  @SerializedName("successFee")
  private Double successFee = null;

  @SerializedName("volumeFee")
  private Double volumeFee = null;

  public SignalSubscription() {
  }
  public SignalSubscription subscriberInfo(SignalSubscriberInfo subscriberInfo) {
    this.subscriberInfo = subscriberInfo;
    return this;
  }

   /**
   * Get subscriberInfo
   * @return subscriberInfo
  **/
  @Schema(description = "")
  public SignalSubscriberInfo getSubscriberInfo() {
    return subscriberInfo;
  }

  public void setSubscriberInfo(SignalSubscriberInfo subscriberInfo) {
    this.subscriberInfo = subscriberInfo;
  }

  public SignalSubscription asset(AssetDetails asset) {
    this.asset = asset;
    return this;
  }

   /**
   * Get asset
   * @return asset
  **/
  @Schema(description = "")
  public AssetDetails getAsset() {
    return asset;
  }

  public void setAsset(AssetDetails asset) {
    this.asset = asset;
  }

  public SignalSubscription assetOwner(ProfilePublic assetOwner) {
    this.assetOwner = assetOwner;
    return this;
  }

   /**
   * Get assetOwner
   * @return assetOwner
  **/
  @Schema(description = "")
  public ProfilePublic getAssetOwner() {
    return assetOwner;
  }

  public void setAssetOwner(ProfilePublic assetOwner) {
    this.assetOwner = assetOwner;
  }

  public SignalSubscription assetBrokerDetails(BrokerDetails assetBrokerDetails) {
    this.assetBrokerDetails = assetBrokerDetails;
    return this;
  }

   /**
   * Get assetBrokerDetails
   * @return assetBrokerDetails
  **/
  @Schema(description = "")
  public BrokerDetails getAssetBrokerDetails() {
    return assetBrokerDetails;
  }

  public void setAssetBrokerDetails(BrokerDetails assetBrokerDetails) {
    this.assetBrokerDetails = assetBrokerDetails;
  }

  public SignalSubscription assetTags(List<Tag> assetTags) {
    this.assetTags = assetTags;
    return this;
  }

  public SignalSubscription addAssetTagsItem(Tag assetTagsItem) {
    if (this.assetTags == null) {
      this.assetTags = new ArrayList<Tag>();
    }
    this.assetTags.add(assetTagsItem);
    return this;
  }

   /**
   * Get assetTags
   * @return assetTags
  **/
  @Schema(description = "")
  public List<Tag> getAssetTags() {
    return assetTags;
  }

  public void setAssetTags(List<Tag> assetTags) {
    this.assetTags = assetTags;
  }

  public SignalSubscription status(String status) {
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

  public SignalSubscription subscriptionDate(DateTime subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
    return this;
  }

   /**
   * Get subscriptionDate
   * @return subscriptionDate
  **/
  @Schema(description = "")
  public DateTime getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(DateTime subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  public SignalSubscription unsubscriptionDate(DateTime unsubscriptionDate) {
    this.unsubscriptionDate = unsubscriptionDate;
    return this;
  }

   /**
   * Get unsubscriptionDate
   * @return unsubscriptionDate
  **/
  @Schema(description = "")
  public DateTime getUnsubscriptionDate() {
    return unsubscriptionDate;
  }

  public void setUnsubscriptionDate(DateTime unsubscriptionDate) {
    this.unsubscriptionDate = unsubscriptionDate;
  }

  public SignalSubscription hasSignalAccount(Boolean hasSignalAccount) {
    this.hasSignalAccount = hasSignalAccount;
    return this;
  }

   /**
   * Get hasSignalAccount
   * @return hasSignalAccount
  **/
  @Schema(description = "")
  public Boolean isHasSignalAccount() {
    return hasSignalAccount;
  }

  public void setHasSignalAccount(Boolean hasSignalAccount) {
    this.hasSignalAccount = hasSignalAccount;
  }

  public SignalSubscription hasActiveSubscription(Boolean hasActiveSubscription) {
    this.hasActiveSubscription = hasActiveSubscription;
    return this;
  }

   /**
   * Get hasActiveSubscription
   * @return hasActiveSubscription
  **/
  @Schema(description = "")
  public Boolean isHasActiveSubscription() {
    return hasActiveSubscription;
  }

  public void setHasActiveSubscription(Boolean hasActiveSubscription) {
    this.hasActiveSubscription = hasActiveSubscription;
  }

  public SignalSubscription isExternal(Boolean isExternal) {
    this.isExternal = isExternal;
    return this;
  }

   /**
   * Get isExternal
   * @return isExternal
  **/
  @Schema(description = "")
  public Boolean isIsExternal() {
    return isExternal;
  }

  public void setIsExternal(Boolean isExternal) {
    this.isExternal = isExternal;
  }

  public SignalSubscription mode(SubscriptionMode mode) {
    this.mode = mode;
    return this;
  }

   /**
   * Get mode
   * @return mode
  **/
  @Schema(description = "")
  public SubscriptionMode getMode() {
    return mode;
  }

  public void setMode(SubscriptionMode mode) {
    this.mode = mode;
  }

  public SignalSubscription detachMode(SignalDetachMode detachMode) {
    this.detachMode = detachMode;
    return this;
  }

   /**
   * Get detachMode
   * @return detachMode
  **/
  @Schema(description = "")
  public SignalDetachMode getDetachMode() {
    return detachMode;
  }

  public void setDetachMode(SignalDetachMode detachMode) {
    this.detachMode = detachMode;
  }

  public SignalSubscription percent(Double percent) {
    this.percent = percent;
    return this;
  }

   /**
   * Get percent
   * @return percent
  **/
  @Schema(description = "")
  public Double getPercent() {
    return percent;
  }

  public void setPercent(Double percent) {
    this.percent = percent;
  }

  public SignalSubscription openTolerancePercent(Double openTolerancePercent) {
    this.openTolerancePercent = openTolerancePercent;
    return this;
  }

   /**
   * Get openTolerancePercent
   * @return openTolerancePercent
  **/
  @Schema(description = "")
  public Double getOpenTolerancePercent() {
    return openTolerancePercent;
  }

  public void setOpenTolerancePercent(Double openTolerancePercent) {
    this.openTolerancePercent = openTolerancePercent;
  }

  public SignalSubscription fixedVolume(Double fixedVolume) {
    this.fixedVolume = fixedVolume;
    return this;
  }

   /**
   * Get fixedVolume
   * @return fixedVolume
  **/
  @Schema(description = "")
  public Double getFixedVolume() {
    return fixedVolume;
  }

  public void setFixedVolume(Double fixedVolume) {
    this.fixedVolume = fixedVolume;
  }

  public SignalSubscription fixedCurrency(Currency fixedCurrency) {
    this.fixedCurrency = fixedCurrency;
    return this;
  }

   /**
   * Get fixedCurrency
   * @return fixedCurrency
  **/
  @Schema(description = "")
  public Currency getFixedCurrency() {
    return fixedCurrency;
  }

  public void setFixedCurrency(Currency fixedCurrency) {
    this.fixedCurrency = fixedCurrency;
  }

  public SignalSubscription totalProfit(Double totalProfit) {
    this.totalProfit = totalProfit;
    return this;
  }

   /**
   * Get totalProfit
   * @return totalProfit
  **/
  @Schema(description = "")
  public Double getTotalProfit() {
    return totalProfit;
  }

  public void setTotalProfit(Double totalProfit) {
    this.totalProfit = totalProfit;
  }

  public SignalSubscription totalVolume(Double totalVolume) {
    this.totalVolume = totalVolume;
    return this;
  }

   /**
   * Get totalVolume
   * @return totalVolume
  **/
  @Schema(description = "")
  public Double getTotalVolume() {
    return totalVolume;
  }

  public void setTotalVolume(Double totalVolume) {
    this.totalVolume = totalVolume;
  }

  public SignalSubscription successFeePersonal(Double successFeePersonal) {
    this.successFeePersonal = successFeePersonal;
    return this;
  }

   /**
   * Get successFeePersonal
   * @return successFeePersonal
  **/
  @Schema(description = "")
  public Double getSuccessFeePersonal() {
    return successFeePersonal;
  }

  public void setSuccessFeePersonal(Double successFeePersonal) {
    this.successFeePersonal = successFeePersonal;
  }

  public SignalSubscription volumeFeePersonal(Double volumeFeePersonal) {
    this.volumeFeePersonal = volumeFeePersonal;
    return this;
  }

   /**
   * Get volumeFeePersonal
   * @return volumeFeePersonal
  **/
  @Schema(description = "")
  public Double getVolumeFeePersonal() {
    return volumeFeePersonal;
  }

  public void setVolumeFeePersonal(Double volumeFeePersonal) {
    this.volumeFeePersonal = volumeFeePersonal;
  }

  public SignalSubscription successFee(Double successFee) {
    this.successFee = successFee;
    return this;
  }

   /**
   * Get successFee
   * @return successFee
  **/
  @Schema(description = "")
  public Double getSuccessFee() {
    return successFee;
  }

  public void setSuccessFee(Double successFee) {
    this.successFee = successFee;
  }

  public SignalSubscription volumeFee(Double volumeFee) {
    this.volumeFee = volumeFee;
    return this;
  }

   /**
   * Get volumeFee
   * @return volumeFee
  **/
  @Schema(description = "")
  public Double getVolumeFee() {
    return volumeFee;
  }

  public void setVolumeFee(Double volumeFee) {
    this.volumeFee = volumeFee;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignalSubscription signalSubscription = (SignalSubscription) o;
    return Objects.equals(this.subscriberInfo, signalSubscription.subscriberInfo) &&
        Objects.equals(this.asset, signalSubscription.asset) &&
        Objects.equals(this.assetOwner, signalSubscription.assetOwner) &&
        Objects.equals(this.assetBrokerDetails, signalSubscription.assetBrokerDetails) &&
        Objects.equals(this.assetTags, signalSubscription.assetTags) &&
        Objects.equals(this.status, signalSubscription.status) &&
        Objects.equals(this.subscriptionDate, signalSubscription.subscriptionDate) &&
        Objects.equals(this.unsubscriptionDate, signalSubscription.unsubscriptionDate) &&
        Objects.equals(this.hasSignalAccount, signalSubscription.hasSignalAccount) &&
        Objects.equals(this.hasActiveSubscription, signalSubscription.hasActiveSubscription) &&
        Objects.equals(this.isExternal, signalSubscription.isExternal) &&
        Objects.equals(this.mode, signalSubscription.mode) &&
        Objects.equals(this.detachMode, signalSubscription.detachMode) &&
        Objects.equals(this.percent, signalSubscription.percent) &&
        Objects.equals(this.openTolerancePercent, signalSubscription.openTolerancePercent) &&
        Objects.equals(this.fixedVolume, signalSubscription.fixedVolume) &&
        Objects.equals(this.fixedCurrency, signalSubscription.fixedCurrency) &&
        Objects.equals(this.totalProfit, signalSubscription.totalProfit) &&
        Objects.equals(this.totalVolume, signalSubscription.totalVolume) &&
        Objects.equals(this.successFeePersonal, signalSubscription.successFeePersonal) &&
        Objects.equals(this.volumeFeePersonal, signalSubscription.volumeFeePersonal) &&
        Objects.equals(this.successFee, signalSubscription.successFee) &&
        Objects.equals(this.volumeFee, signalSubscription.volumeFee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriberInfo, asset, assetOwner, assetBrokerDetails, assetTags, status, subscriptionDate, unsubscriptionDate, hasSignalAccount, hasActiveSubscription, isExternal, mode, detachMode, percent, openTolerancePercent, fixedVolume, fixedCurrency, totalProfit, totalVolume, successFeePersonal, volumeFeePersonal, successFee, volumeFee);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignalSubscription {\n");
    
    sb.append("    subscriberInfo: ").append(toIndentedString(subscriberInfo)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    assetOwner: ").append(toIndentedString(assetOwner)).append("\n");
    sb.append("    assetBrokerDetails: ").append(toIndentedString(assetBrokerDetails)).append("\n");
    sb.append("    assetTags: ").append(toIndentedString(assetTags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    subscriptionDate: ").append(toIndentedString(subscriptionDate)).append("\n");
    sb.append("    unsubscriptionDate: ").append(toIndentedString(unsubscriptionDate)).append("\n");
    sb.append("    hasSignalAccount: ").append(toIndentedString(hasSignalAccount)).append("\n");
    sb.append("    hasActiveSubscription: ").append(toIndentedString(hasActiveSubscription)).append("\n");
    sb.append("    isExternal: ").append(toIndentedString(isExternal)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    detachMode: ").append(toIndentedString(detachMode)).append("\n");
    sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
    sb.append("    openTolerancePercent: ").append(toIndentedString(openTolerancePercent)).append("\n");
    sb.append("    fixedVolume: ").append(toIndentedString(fixedVolume)).append("\n");
    sb.append("    fixedCurrency: ").append(toIndentedString(fixedCurrency)).append("\n");
    sb.append("    totalProfit: ").append(toIndentedString(totalProfit)).append("\n");
    sb.append("    totalVolume: ").append(toIndentedString(totalVolume)).append("\n");
    sb.append("    successFeePersonal: ").append(toIndentedString(successFeePersonal)).append("\n");
    sb.append("    volumeFeePersonal: ").append(toIndentedString(volumeFeePersonal)).append("\n");
    sb.append("    successFee: ").append(toIndentedString(successFee)).append("\n");
    sb.append("    volumeFee: ").append(toIndentedString(volumeFee)).append("\n");
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
    out.writeValue(subscriberInfo);
    out.writeValue(asset);
    out.writeValue(assetOwner);
    out.writeValue(assetBrokerDetails);
    out.writeValue(assetTags);
    out.writeValue(status);
    out.writeValue(subscriptionDate);
    out.writeValue(unsubscriptionDate);
    out.writeValue(hasSignalAccount);
    out.writeValue(hasActiveSubscription);
    out.writeValue(isExternal);
    out.writeValue(mode);
    out.writeValue(detachMode);
    out.writeValue(percent);
    out.writeValue(openTolerancePercent);
    out.writeValue(fixedVolume);
    out.writeValue(fixedCurrency);
    out.writeValue(totalProfit);
    out.writeValue(totalVolume);
    out.writeValue(successFeePersonal);
    out.writeValue(volumeFeePersonal);
    out.writeValue(successFee);
    out.writeValue(volumeFee);
  }

  SignalSubscription(Parcel in) {
    subscriberInfo = (SignalSubscriberInfo)in.readValue(SignalSubscriberInfo.class.getClassLoader());
    asset = (AssetDetails)in.readValue(AssetDetails.class.getClassLoader());
    assetOwner = (ProfilePublic)in.readValue(ProfilePublic.class.getClassLoader());
    assetBrokerDetails = (BrokerDetails)in.readValue(BrokerDetails.class.getClassLoader());
    assetTags = (List<Tag>)in.readValue(Tag.class.getClassLoader());
    status = (String)in.readValue(null);
    subscriptionDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    unsubscriptionDate = (DateTime)in.readValue(DateTime.class.getClassLoader());
    hasSignalAccount = (Boolean)in.readValue(null);
    hasActiveSubscription = (Boolean)in.readValue(null);
    isExternal = (Boolean)in.readValue(null);
    mode = (SubscriptionMode)in.readValue(SubscriptionMode.class.getClassLoader());
    detachMode = (SignalDetachMode)in.readValue(SignalDetachMode.class.getClassLoader());
    percent = (Double)in.readValue(null);
    openTolerancePercent = (Double)in.readValue(null);
    fixedVolume = (Double)in.readValue(null);
    fixedCurrency = (Currency)in.readValue(Currency.class.getClassLoader());
    totalProfit = (Double)in.readValue(null);
    totalVolume = (Double)in.readValue(null);
    successFeePersonal = (Double)in.readValue(null);
    volumeFeePersonal = (Double)in.readValue(null);
    successFee = (Double)in.readValue(null);
    volumeFee = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<SignalSubscription> CREATOR = new Parcelable.Creator<SignalSubscription>() {
    public SignalSubscription createFromParcel(Parcel in) {
      return new SignalSubscription(in);
    }
    public SignalSubscription[] newArray(int size) {
      return new SignalSubscription[size];
    }
  };
}
