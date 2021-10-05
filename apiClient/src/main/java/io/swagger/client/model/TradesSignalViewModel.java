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
 * TradesSignalViewModel
 */


public class TradesSignalViewModel implements Parcelable
{
	public static final Parcelable.Creator<TradesSignalViewModel> CREATOR = new Parcelable.Creator<TradesSignalViewModel>()
	{
		public TradesSignalViewModel createFromParcel(Parcel in) {
			return new TradesSignalViewModel(in);
		}

		public TradesSignalViewModel[] newArray(int size) {
			return new TradesSignalViewModel[size];
		}
	};

	@SerializedName("items")
	private List<OrderSignalModel> items = null;

	@SerializedName("total")
	private Integer total = null;

	@SerializedName("showSwaps")
	private Boolean showSwaps = null;

	@SerializedName("tradesDelay")
	private TradesDelay tradesDelay = null;

	@SerializedName("showTickets")
	private Boolean showTickets = null;

	public TradesSignalViewModel() {
	}

	TradesSignalViewModel(Parcel in) {
		items = (List<OrderSignalModel>) in.readValue(OrderSignalModel.class.getClassLoader());
		total = (Integer) in.readValue(null);
		showSwaps = (Boolean) in.readValue(null);
		showTickets = (Boolean) in.readValue(null);
		tradesDelay = (TradesDelay) in.readValue(TradesDelay.class.getClassLoader());
	}

	public TradesSignalViewModel items(List<OrderSignalModel> items) {
		this.items = items;
		return this;
	}

	public TradesSignalViewModel addItemsItem(OrderSignalModel itemsItem) {
		if (this.items == null) {
			this.items = new ArrayList<OrderSignalModel>();
		}
		this.items.add(itemsItem);
		return this;
	}

	public TradesSignalViewModel total(Integer total) {
		this.total = total;
		return this;
	}

	public TradesSignalViewModel showSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
		return this;
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<OrderSignalModel> getItems() {
		return items;
	}

	public void setItems(List<OrderSignalModel> items) {
		this.items = items;
	}

	public void setShowSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
	}

	public TradesSignalViewModel showTickets(Boolean showTickets) {
		this.showTickets = showTickets;
		return this;
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

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setShowTickets(Boolean showTickets) {
		this.showTickets = showTickets;
	}

	public TradesSignalViewModel tradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
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

	/**
	 * Get showTickets
	 *
	 * @return showTickets
	 **/
	@Schema(description = "")
	public Boolean isShowTickets() {
		return showTickets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total, showSwaps, showTickets, tradesDelay);
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
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradesSignalViewModel tradesSignalViewModel = (TradesSignalViewModel) o;
		return Objects.equals(this.items, tradesSignalViewModel.items) &&
				Objects.equals(this.total, tradesSignalViewModel.total) &&
				Objects.equals(this.showSwaps, tradesSignalViewModel.showSwaps) &&
				Objects.equals(this.showTickets, tradesSignalViewModel.showTickets) &&
				Objects.equals(this.tradesDelay, tradesSignalViewModel.tradesDelay);
	}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
	  sb.append("class TradesSignalViewModel {\n");

	  sb.append("    items: ").append(toIndentedString(items)).append("\n");
	  sb.append("    total: ").append(toIndentedString(total)).append("\n");
	  sb.append("    showSwaps: ").append(toIndentedString(showSwaps)).append("\n");
	  sb.append("    showTickets: ").append(toIndentedString(showTickets)).append("\n");
	  sb.append("    tradesDelay: ").append(toIndentedString(tradesDelay)).append("\n");
	  sb.append("}");
	  return sb.toString();
  }

	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(items);
		out.writeValue(total);
		out.writeValue(showSwaps);
		out.writeValue(showTickets);
		out.writeValue(tradesDelay);
	}

	public int describeContents() {
		return 0;
	}
}
