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
 * SignalTradingEventItemsViewModel
 */


public class SignalTradingEventItemsViewModel implements Parcelable
{
	public static final Parcelable.Creator<SignalTradingEventItemsViewModel> CREATOR = new Parcelable.Creator<SignalTradingEventItemsViewModel>()
	{
		public SignalTradingEventItemsViewModel createFromParcel(Parcel in) {
			return new SignalTradingEventItemsViewModel(in);
		}

		public SignalTradingEventItemsViewModel[] newArray(int size) {
			return new SignalTradingEventItemsViewModel[size];
		}
	};

	@SerializedName("items")
	private List<SignalTradingEvent> items = null;

	@SerializedName("total")
	private Integer total = null;

	public SignalTradingEventItemsViewModel() {
	}

	SignalTradingEventItemsViewModel(Parcel in) {
		items = (List<SignalTradingEvent>) in.readValue(SignalTradingEvent.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<SignalTradingEvent> getItems() {
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
		SignalTradingEventItemsViewModel signalTradingEventItemsViewModel = (SignalTradingEventItemsViewModel) o;
		return Objects.equals(this.items, signalTradingEventItemsViewModel.items) &&
				Objects.equals(this.total, signalTradingEventItemsViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SignalTradingEventItemsViewModel {\n");

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
