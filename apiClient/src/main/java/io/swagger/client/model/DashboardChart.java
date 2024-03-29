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
import io.swagger.client.model.DashboardAssetChart;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * DashboardChart
 */


public class DashboardChart implements Parcelable {
  @SerializedName("charts")
  private List<DashboardAssetChart> charts = null;

  public DashboardChart() {
  }
  public DashboardChart charts(List<DashboardAssetChart> charts) {
    this.charts = charts;
    return this;
  }

  public DashboardChart addChartsItem(DashboardAssetChart chartsItem) {
    if (this.charts == null) {
      this.charts = new ArrayList<DashboardAssetChart>();
    }
    this.charts.add(chartsItem);
    return this;
  }

   /**
   * Get charts
   * @return charts
  **/
  @Schema(description = "")
  public List<DashboardAssetChart> getCharts() {
    return charts;
  }

  public void setCharts(List<DashboardAssetChart> charts) {
    this.charts = charts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardChart dashboardChart = (DashboardChart) o;
    return Objects.equals(this.charts, dashboardChart.charts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(charts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardChart {\n");
    
    sb.append("    charts: ").append(toIndentedString(charts)).append("\n");
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
    out.writeValue(charts);
  }

  DashboardChart(Parcel in) {
    charts = (List<DashboardAssetChart>)in.readValue(DashboardAssetChart.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<DashboardChart> CREATOR = new Parcelable.Creator<DashboardChart>() {
    public DashboardChart createFromParcel(Parcel in) {
      return new DashboardChart(in);
    }
    public DashboardChart[] newArray(int size) {
      return new DashboardChart[size];
    }
  };
}
