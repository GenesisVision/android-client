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
 * TradesViewModel
 */


public class TradesViewModel implements Parcelable
{
	public static final Parcelable.Creator<TradesViewModel> CREATOR = new Parcelable.Creator<TradesViewModel>()
	{
		public TradesViewModel createFromParcel(Parcel in) {
			return new TradesViewModel(in);
		}

		public TradesViewModel[] newArray(int size) {
			return new TradesViewModel[size];
		}
	};

	@SerializedName("showSwaps")
	private Boolean showSwaps = null;

	@SerializedName("showTickets")
	private Boolean showTickets = null;

	@SerializedName("tradesDelay")
	private TradesDelay tradesDelay = null;

	@SerializedName("items")
	private List<OrderModel> items = null;

	@SerializedName("total")
	private Integer total = null;

	public TradesViewModel() {
	}

	TradesViewModel(Parcel in) {
		showSwaps = (Boolean) in.readValue(null);
		showTickets = (Boolean) in.readValue(null);
		tradesDelay = (TradesDelay) in.readValue(TradesDelay.class.getClassLoader());
		items = (List<OrderModel>) in.readValue(OrderModel.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	public TradesViewModel showSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
		return this;
	}

	/**
	 * Get showSwaps
	 *
	 * @return showSwaps
	 **/
	@Schema(description = "")
	public Boolean isShowSwaps() {
		return showSwaps;
	}

	public void setShowSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
	}

	public TradesViewModel showTickets(Boolean showTickets) {
		this.showTickets = showTickets;
		return this;
	}

	/**
	 * Get showTickets
	 *
	 * @return showTickets
	 **/
	@Schema(description = "")
	public Boolean isShowTickets() {
		return showTickets;
	}

	public void setShowTickets(Boolean showTickets) {
		this.showTickets = showTickets;
	}

	public TradesViewModel tradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
		return this;
	}

	/**
	 * Get tradesDelay
	 *
	 * @return tradesDelay
	 **/
	@Schema(description = "")
	public TradesDelay getTradesDelay() {
		return tradesDelay;
	}

	public void setTradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<OrderModel> getItems() {
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
		TradesViewModel tradesViewModel = (TradesViewModel) o;
		return Objects.equals(this.showSwaps, tradesViewModel.showSwaps) &&
				Objects.equals(this.showTickets, tradesViewModel.showTickets) &&
				Objects.equals(this.tradesDelay, tradesViewModel.tradesDelay) &&
				Objects.equals(this.items, tradesViewModel.items) &&
				Objects.equals(this.total, tradesViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(showSwaps, showTickets, tradesDelay, items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradesViewModel {\n");

		sb.append("    showSwaps: ").append(toIndentedString(showSwaps)).append("\n");
		sb.append("    showTickets: ").append(toIndentedString(showTickets)).append("\n");
		sb.append("    tradesDelay: ").append(toIndentedString(tradesDelay)).append("\n");
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
		out.writeValue(showSwaps);
		out.writeValue(showTickets);
		out.writeValue(tradesDelay);
		out.writeValue(items);
		out.writeValue(total);
	}

	public int describeContents() {
		return 0;
	}
}
