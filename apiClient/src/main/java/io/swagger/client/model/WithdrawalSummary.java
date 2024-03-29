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
import io.swagger.client.model.WalletWithdrawalInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * WithdrawalSummary
 */


public class WithdrawalSummary implements Parcelable {
  @SerializedName("availableToWithdrawal")
  private Double availableToWithdrawal = null;

  @SerializedName("wallets")
  private List<WalletWithdrawalInfo> wallets = null;

  public WithdrawalSummary() {
  }
  public WithdrawalSummary availableToWithdrawal(Double availableToWithdrawal) {
    this.availableToWithdrawal = availableToWithdrawal;
    return this;
  }

   /**
   * Get availableToWithdrawal
   * @return availableToWithdrawal
  **/
  @Schema(description = "")
  public Double getAvailableToWithdrawal() {
    return availableToWithdrawal;
  }

  public void setAvailableToWithdrawal(Double availableToWithdrawal) {
    this.availableToWithdrawal = availableToWithdrawal;
  }

  public WithdrawalSummary wallets(List<WalletWithdrawalInfo> wallets) {
    this.wallets = wallets;
    return this;
  }

  public WithdrawalSummary addWalletsItem(WalletWithdrawalInfo walletsItem) {
    if (this.wallets == null) {
      this.wallets = new ArrayList<WalletWithdrawalInfo>();
    }
    this.wallets.add(walletsItem);
    return this;
  }

   /**
   * Get wallets
   * @return wallets
  **/
  @Schema(description = "")
  public List<WalletWithdrawalInfo> getWallets() {
    return wallets;
  }

  public void setWallets(List<WalletWithdrawalInfo> wallets) {
    this.wallets = wallets;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithdrawalSummary withdrawalSummary = (WithdrawalSummary) o;
    return Objects.equals(this.availableToWithdrawal, withdrawalSummary.availableToWithdrawal) &&
        Objects.equals(this.wallets, withdrawalSummary.wallets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(availableToWithdrawal, wallets);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WithdrawalSummary {\n");
    
    sb.append("    availableToWithdrawal: ").append(toIndentedString(availableToWithdrawal)).append("\n");
    sb.append("    wallets: ").append(toIndentedString(wallets)).append("\n");
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
    out.writeValue(availableToWithdrawal);
    out.writeValue(wallets);
  }

  WithdrawalSummary(Parcel in) {
    availableToWithdrawal = (Double)in.readValue(null);
    wallets = (List<WalletWithdrawalInfo>)in.readValue(WalletWithdrawalInfo.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<WithdrawalSummary> CREATOR = new Parcelable.Creator<WithdrawalSummary>() {
    public WithdrawalSummary createFromParcel(Parcel in) {
      return new WithdrawalSummary(in);
    }
    public WithdrawalSummary[] newArray(int size) {
      return new WithdrawalSummary[size];
    }
  };
}
