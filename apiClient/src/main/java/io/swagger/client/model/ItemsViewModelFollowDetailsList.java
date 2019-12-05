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

import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ItemsViewModelFollowDetailsList
 */


public class ItemsViewModelFollowDetailsList implements Parcelable
{
	public static final Parcelable.Creator<ItemsViewModelFollowDetailsList> CREATOR = new Parcelable.Creator<ItemsViewModelFollowDetailsList>()
	{
		public ItemsViewModelFollowDetailsList createFromParcel(Parcel in) {
			return new ItemsViewModelFollowDetailsList(in);
		}

		public ItemsViewModelFollowDetailsList[] newArray(int size) {
			return new ItemsViewModelFollowDetailsList[size];
		}
	};

	@SerializedName("items")
	private List<FollowDetailsList> items = null;

	@SerializedName("total")
	private Integer total = null;

	public ItemsViewModelFollowDetailsList() {
	}

	ItemsViewModelFollowDetailsList(Parcel in) {
		items = (List<FollowDetailsList>) in.readValue(FollowDetailsList.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<FollowDetailsList> getItems() {
		return items;
	}

	/**
	 * Get total
	 *
	 * @return total
	 **/
	@Schema(description = "")
	public Integer getTotal() {
		return total;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ItemsViewModelFollowDetailsList itemsViewModelFollowDetailsList = (ItemsViewModelFollowDetailsList) o;
		return Objects.equals(this.items, itemsViewModelFollowDetailsList.items) &&
				Objects.equals(this.total, itemsViewModelFollowDetailsList.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ItemsViewModelFollowDetailsList {\n");

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

	public int describeContents() {
		return 0;
	}
}
