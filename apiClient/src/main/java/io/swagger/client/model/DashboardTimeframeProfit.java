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
 * DashboardTimeframeProfit
 */


public class DashboardTimeframeProfit implements Parcelable {
  @SerializedName("profit")
  private Double profit = null;

  @SerializedName("profitPercent")
  private Double profitPercent = null;

  public DashboardTimeframeProfit() {
  }
  public DashboardTimeframeProfit profit(Double profit) {
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

  public DashboardTimeframeProfit profitPercent(Double profitPercent) {
    this.profitPercent = profitPercent;
    return this;
  }

   /**
   * Get profitPercent
   * @return profitPercent
  **/
  @Schema(description = "")
  public Double getProfitPercent() {
    return profitPercent;
  }

  public void setProfitPercent(Double profitPercent) {
    this.profitPercent = profitPercent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardTimeframeProfit dashboardTimeframeProfit = (DashboardTimeframeProfit) o;
    return Objects.equals(this.profit, dashboardTimeframeProfit.profit) &&
        Objects.equals(this.profitPercent, dashboardTimeframeProfit.profitPercent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profit, profitPercent);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardTimeframeProfit {\n");
    
    sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
    sb.append("    profitPercent: ").append(toIndentedString(profitPercent)).append("\n");
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
    out.writeValue(profit);
    out.writeValue(profitPercent);
  }

  DashboardTimeframeProfit(Parcel in) {
    profit = (Double)in.readValue(null);
    profitPercent = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<DashboardTimeframeProfit> CREATOR = new Parcelable.Creator<DashboardTimeframeProfit>() {
    public DashboardTimeframeProfit createFromParcel(Parcel in) {
      return new DashboardTimeframeProfit(in);
    }
    public DashboardTimeframeProfit[] newArray(int size) {
      return new DashboardTimeframeProfit[size];
    }
  };
}
