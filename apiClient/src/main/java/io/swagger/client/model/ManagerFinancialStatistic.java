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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * ManagerFinancialStatistic
 */


public class ManagerFinancialStatistic implements Parcelable {
  @SerializedName("successFee")
  private Double successFee = null;

  @SerializedName("entryFee")
  private Double entryFee = null;

  @SerializedName("profit")
  private Double profit = null;

  @SerializedName("balance")
  private Double balance = null;

  public ManagerFinancialStatistic() {
  }
  public ManagerFinancialStatistic successFee(Double successFee) {
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

  public ManagerFinancialStatistic entryFee(Double entryFee) {
    this.entryFee = entryFee;
    return this;
  }

   /**
   * Get entryFee
   * @return entryFee
  **/
  @Schema(description = "")
  public Double getEntryFee() {
    return entryFee;
  }

  public void setEntryFee(Double entryFee) {
    this.entryFee = entryFee;
  }

  public ManagerFinancialStatistic profit(Double profit) {
    this.profit = profit;
    return this;
  }

   /**
   * Get profit
   * @return profit
  **/
  @Schema(description = "")
  public Double getProfit() {
    return profit;
  }

  public void setProfit(Double profit) {
    this.profit = profit;
  }

  public ManagerFinancialStatistic balance(Double balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @Schema(description = "")
  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
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
    ManagerFinancialStatistic managerFinancialStatistic = (ManagerFinancialStatistic) o;
    return Objects.equals(this.successFee, managerFinancialStatistic.successFee) &&
        Objects.equals(this.entryFee, managerFinancialStatistic.entryFee) &&
        Objects.equals(this.profit, managerFinancialStatistic.profit) &&
        Objects.equals(this.balance, managerFinancialStatistic.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(successFee, entryFee, profit, balance);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManagerFinancialStatistic {\n");
    
    sb.append("    successFee: ").append(toIndentedString(successFee)).append("\n");
    sb.append("    entryFee: ").append(toIndentedString(entryFee)).append("\n");
    sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
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
    out.writeValue(successFee);
    out.writeValue(entryFee);
    out.writeValue(profit);
    out.writeValue(balance);
  }

  ManagerFinancialStatistic(Parcel in) {
    successFee = (Double)in.readValue(null);
    entryFee = (Double)in.readValue(null);
    profit = (Double)in.readValue(null);
    balance = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ManagerFinancialStatistic> CREATOR = new Parcelable.Creator<ManagerFinancialStatistic>() {
    public ManagerFinancialStatistic createFromParcel(Parcel in) {
      return new ManagerFinancialStatistic(in);
    }
    public ManagerFinancialStatistic[] newArray(int size) {
      return new ManagerFinancialStatistic[size];
    }
  };
}
