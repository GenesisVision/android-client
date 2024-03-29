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
import io.swagger.client.model.BalanceChartPoint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * FundBalanceChart
 */


public class FundBalanceChart implements Parcelable {
  @SerializedName("balance")
  private Double balance = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("chart")
  private List<BalanceChartPoint> chart = null;

  public FundBalanceChart() {
  }
  public FundBalanceChart balance(Double balance) {
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

  public FundBalanceChart color(String color) {
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

  public FundBalanceChart chart(List<BalanceChartPoint> chart) {
    this.chart = chart;
    return this;
  }

  public FundBalanceChart addChartItem(BalanceChartPoint chartItem) {
    if (this.chart == null) {
      this.chart = new ArrayList<BalanceChartPoint>();
    }
    this.chart.add(chartItem);
    return this;
  }

   /**
   * Get chart
   * @return chart
  **/
  @Schema(description = "")
  public List<BalanceChartPoint> getChart() {
    return chart;
  }

  public void setChart(List<BalanceChartPoint> chart) {
    this.chart = chart;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FundBalanceChart fundBalanceChart = (FundBalanceChart) o;
    return Objects.equals(this.balance, fundBalanceChart.balance) &&
        Objects.equals(this.color, fundBalanceChart.color) &&
        Objects.equals(this.chart, fundBalanceChart.chart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balance, color, chart);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FundBalanceChart {\n");
    
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
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
    out.writeValue(balance);
    out.writeValue(color);
    out.writeValue(chart);
  }

  FundBalanceChart(Parcel in) {
    balance = (Double)in.readValue(null);
    color = (String)in.readValue(null);
    chart = (List<BalanceChartPoint>)in.readValue(BalanceChartPoint.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<FundBalanceChart> CREATOR = new Parcelable.Creator<FundBalanceChart>() {
    public FundBalanceChart createFromParcel(Parcel in) {
      return new FundBalanceChart(in);
    }
    public FundBalanceChart[] newArray(int size) {
      return new FundBalanceChart[size];
    }
  };
}
