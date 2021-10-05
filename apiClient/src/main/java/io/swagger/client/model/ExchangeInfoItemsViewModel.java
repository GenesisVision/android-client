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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ExchangeInfoItemsViewModel
 */


public class ExchangeInfoItemsViewModel implements Parcelable {
  @SerializedName("items")
  private List<ExchangeInfo> items = null;

  @SerializedName("total")
  private Integer total = null;

  public ExchangeInfoItemsViewModel() {
  }
  public ExchangeInfoItemsViewModel items(List<ExchangeInfo> items) {
    this.items = items;
    return this;
  }

  public ExchangeInfoItemsViewModel addItemsItem(ExchangeInfo itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<ExchangeInfo>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @Schema(description = "")
  public List<ExchangeInfo> getItems() {
    return items;
  }

  public void setItems(List<ExchangeInfo> items) {
    this.items = items;
  }

  public ExchangeInfoItemsViewModel total(Integer total) {
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @Schema(description = "")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeInfoItemsViewModel exchangeInfoItemsViewModel = (ExchangeInfoItemsViewModel) o;
    return Objects.equals(this.items, exchangeInfoItemsViewModel.items) &&
        Objects.equals(this.total, exchangeInfoItemsViewModel.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, total);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeInfoItemsViewModel {\n");
    
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
    out.writeValue(items);
    out.writeValue(total);
  }

  ExchangeInfoItemsViewModel(Parcel in) {
    items = (List<ExchangeInfo>)in.readValue(ExchangeInfo.class.getClassLoader());
    total = (Integer)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ExchangeInfoItemsViewModel> CREATOR = new Parcelable.Creator<ExchangeInfoItemsViewModel>() {
    public ExchangeInfoItemsViewModel createFromParcel(Parcel in) {
      return new ExchangeInfoItemsViewModel(in);
    }
    public ExchangeInfoItemsViewModel[] newArray(int size) {
      return new ExchangeInfoItemsViewModel[size];
    }
  };
}
