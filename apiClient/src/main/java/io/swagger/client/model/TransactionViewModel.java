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
import io.swagger.client.model.AmountRowCell;
import io.swagger.client.model.MultiWalletTransactionStatus;
import io.swagger.client.model.TransactionAssetDetails;
import io.swagger.client.model.TransactionDetailItem;
import io.swagger.client.model.TransactionDetailsActions;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * TransactionViewModel
 */


public class TransactionViewModel implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("date")
  private DateTime date = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("amount")
  private AmountRowCell amount = null;

  @SerializedName("detailsTitle")
  private String detailsTitle = null;

  @SerializedName("asset")
  private TransactionAssetDetails asset = null;

  @SerializedName("details")
  private List<TransactionDetailItem> details = null;

  @SerializedName("actions")
  private TransactionDetailsActions actions = null;

  @SerializedName("status")
  private MultiWalletTransactionStatus status = null;

  public TransactionViewModel() {
  }
  public TransactionViewModel id(UUID id) {
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

  public TransactionViewModel date(DateTime date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @Schema(description = "")
  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
  }

  public TransactionViewModel description(String description) {
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

  public TransactionViewModel amount(AmountRowCell amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @Schema(description = "")
  public AmountRowCell getAmount() {
    return amount;
  }

  public void setAmount(AmountRowCell amount) {
    this.amount = amount;
  }

  public TransactionViewModel detailsTitle(String detailsTitle) {
    this.detailsTitle = detailsTitle;
    return this;
  }

   /**
   * Get detailsTitle
   * @return detailsTitle
  **/
  @Schema(description = "")
  public String getDetailsTitle() {
    return detailsTitle;
  }

  public void setDetailsTitle(String detailsTitle) {
    this.detailsTitle = detailsTitle;
  }

  public TransactionViewModel asset(TransactionAssetDetails asset) {
    this.asset = asset;
    return this;
  }

   /**
   * Get asset
   * @return asset
  **/
  @Schema(description = "")
  public TransactionAssetDetails getAsset() {
    return asset;
  }

  public void setAsset(TransactionAssetDetails asset) {
    this.asset = asset;
  }

  public TransactionViewModel details(List<TransactionDetailItem> details) {
    this.details = details;
    return this;
  }

  public TransactionViewModel addDetailsItem(TransactionDetailItem detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<TransactionDetailItem>();
    }
    this.details.add(detailsItem);
    return this;
  }

   /**
   * Get details
   * @return details
  **/
  @Schema(description = "")
  public List<TransactionDetailItem> getDetails() {
    return details;
  }

  public void setDetails(List<TransactionDetailItem> details) {
    this.details = details;
  }

  public TransactionViewModel actions(TransactionDetailsActions actions) {
    this.actions = actions;
    return this;
  }

   /**
   * Get actions
   * @return actions
  **/
  @Schema(description = "")
  public TransactionDetailsActions getActions() {
    return actions;
  }

  public void setActions(TransactionDetailsActions actions) {
    this.actions = actions;
  }

  public TransactionViewModel status(MultiWalletTransactionStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public MultiWalletTransactionStatus getStatus() {
    return status;
  }

  public void setStatus(MultiWalletTransactionStatus status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionViewModel transactionViewModel = (TransactionViewModel) o;
    return Objects.equals(this.id, transactionViewModel.id) &&
        Objects.equals(this.date, transactionViewModel.date) &&
        Objects.equals(this.description, transactionViewModel.description) &&
        Objects.equals(this.amount, transactionViewModel.amount) &&
        Objects.equals(this.detailsTitle, transactionViewModel.detailsTitle) &&
        Objects.equals(this.asset, transactionViewModel.asset) &&
        Objects.equals(this.details, transactionViewModel.details) &&
        Objects.equals(this.actions, transactionViewModel.actions) &&
        Objects.equals(this.status, transactionViewModel.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, description, amount, detailsTitle, asset, details, actions, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionViewModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    detailsTitle: ").append(toIndentedString(detailsTitle)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
    out.writeValue(date);
    out.writeValue(description);
    out.writeValue(amount);
    out.writeValue(detailsTitle);
    out.writeValue(asset);
    out.writeValue(details);
    out.writeValue(actions);
    out.writeValue(status);
  }

  TransactionViewModel(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    date = (DateTime)in.readValue(DateTime.class.getClassLoader());
    description = (String)in.readValue(null);
    amount = (AmountRowCell)in.readValue(AmountRowCell.class.getClassLoader());
    detailsTitle = (String)in.readValue(null);
    asset = (TransactionAssetDetails)in.readValue(TransactionAssetDetails.class.getClassLoader());
    details = (List<TransactionDetailItem>)in.readValue(TransactionDetailItem.class.getClassLoader());
    actions = (TransactionDetailsActions)in.readValue(TransactionDetailsActions.class.getClassLoader());
    status = (MultiWalletTransactionStatus)in.readValue(MultiWalletTransactionStatus.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<TransactionViewModel> CREATOR = new Parcelable.Creator<TransactionViewModel>() {
    public TransactionViewModel createFromParcel(Parcel in) {
      return new TransactionViewModel(in);
    }
    public TransactionViewModel[] newArray(int size) {
      return new TransactionViewModel[size];
    }
  };
}
