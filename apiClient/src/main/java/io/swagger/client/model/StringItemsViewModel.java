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
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * StringItemsViewModel
 */


public class StringItemsViewModel implements Parcelable {
  @SerializedName("items")
  private List<String> items = null;

  @SerializedName("total")
  private Integer total = null;

  public StringItemsViewModel() {
  }
  public StringItemsViewModel items(List<String> items) {
    this.items = items;
    return this;
  }

  public StringItemsViewModel addItemsItem(String itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<String>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @Schema(description = "")
  public List<String> getItems() {
    return items;
  }

  public void setItems(List<String> items) {
    this.items = items;
  }

  public StringItemsViewModel total(Integer total) {
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
    StringItemsViewModel stringItemsViewModel = (StringItemsViewModel) o;
    return Objects.equals(this.items, stringItemsViewModel.items) &&
        Objects.equals(this.total, stringItemsViewModel.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, total);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StringItemsViewModel {\n");
    
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

  StringItemsViewModel(Parcel in) {
    items = (List<String>)in.readValue(null);
    total = (Integer)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<StringItemsViewModel> CREATOR = new Parcelable.Creator<StringItemsViewModel>() {
    public StringItemsViewModel createFromParcel(Parcel in) {
      return new StringItemsViewModel(in);
    }
    public StringItemsViewModel[] newArray(int size) {
      return new StringItemsViewModel[size];
    }
  };
}
