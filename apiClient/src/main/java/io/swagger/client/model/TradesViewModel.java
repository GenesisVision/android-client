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

	@SerializedName("showDate")
	private Boolean showDate = null;

	@SerializedName("showDirection")
	private Boolean showDirection = null;

	@SerializedName("showPrice")
	private Boolean showPrice = null;

	@SerializedName("showPriceOpen")
	private Boolean showPriceOpen = null;

	@SerializedName("showProfit")
	private Boolean showProfit = null;

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
		showDate = (Boolean) in.readValue(null);
		showDirection = (Boolean) in.readValue(null);
		showPrice = (Boolean) in.readValue(null);
		showPriceOpen = (Boolean) in.readValue(null);
		showProfit = (Boolean) in.readValue(null);
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

	public TradesViewModel showDate(Boolean showDate) {
		this.showDate = showDate;
		return this;
	}

	/**
	 * Get showDate
	 *
	 * @return showDate
	 **/
	@Schema(description = "")
	public Boolean isShowDate() {
		return showDate;
	}

	public void setShowDate(Boolean showDate) {
		this.showDate = showDate;
	}

	public TradesViewModel showDirection(Boolean showDirection) {
		this.showDirection = showDirection;
		return this;
	}

	/**
	 * Get showDirection
	 *
	 * @return showDirection
	 **/
	@Schema(description = "")
	public Boolean isShowDirection() {
		return showDirection;
	}

	public void setShowDirection(Boolean showDirection) {
		this.showDirection = showDirection;
	}

	public TradesViewModel showPrice(Boolean showPrice) {
		this.showPrice = showPrice;
		return this;
	}

	/**
	 * Get showPrice
	 *
	 * @return showPrice
	 **/
	@Schema(description = "")
	public Boolean isShowPrice() {
		return showPrice;
	}

	public void setShowPrice(Boolean showPrice) {
		this.showPrice = showPrice;
	}

	public TradesViewModel showPriceOpen(Boolean showPriceOpen) {
		this.showPriceOpen = showPriceOpen;
		return this;
	}

	/**
	 * Get showPriceOpen
	 *
	 * @return showPriceOpen
	 **/
	@Schema(description = "")
	public Boolean isShowPriceOpen() {
		return showPriceOpen;
	}

	public void setShowPriceOpen(Boolean showPriceOpen) {
		this.showPriceOpen = showPriceOpen;
	}

	public TradesViewModel showProfit(Boolean showProfit) {
		this.showProfit = showProfit;
		return this;
	}

	/**
	 * Get showProfit
	 *
	 * @return showProfit
	 **/
	@Schema(description = "")
	public Boolean isShowProfit() {
		return showProfit;
	}

	public void setShowProfit(Boolean showProfit) {
		this.showProfit = showProfit;
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
				Objects.equals(this.showDate, tradesViewModel.showDate) &&
				Objects.equals(this.showDirection, tradesViewModel.showDirection) &&
				Objects.equals(this.showPrice, tradesViewModel.showPrice) &&
				Objects.equals(this.showPriceOpen, tradesViewModel.showPriceOpen) &&
				Objects.equals(this.showProfit, tradesViewModel.showProfit) &&
				Objects.equals(this.tradesDelay, tradesViewModel.tradesDelay) &&
				Objects.equals(this.items, tradesViewModel.items) &&
				Objects.equals(this.total, tradesViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(showSwaps, showTickets, showDate, showDirection, showPrice, showPriceOpen, showProfit, tradesDelay, items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradesViewModel {\n");

		sb.append("    showSwaps: ").append(toIndentedString(showSwaps)).append("\n");
		sb.append("    showTickets: ").append(toIndentedString(showTickets)).append("\n");
		sb.append("    showDate: ").append(toIndentedString(showDate)).append("\n");
		sb.append("    showDirection: ").append(toIndentedString(showDirection)).append("\n");
		sb.append("    showPrice: ").append(toIndentedString(showPrice)).append("\n");
		sb.append("    showPriceOpen: ").append(toIndentedString(showPriceOpen)).append("\n");
		sb.append("    showProfit: ").append(toIndentedString(showProfit)).append("\n");
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
		out.writeValue(showDate);
		out.writeValue(showDirection);
		out.writeValue(showPrice);
		out.writeValue(showPriceOpen);
		out.writeValue(showProfit);
		out.writeValue(tradesDelay);
		out.writeValue(items);
		out.writeValue(total);
	}

	public int describeContents() {
		return 0;
	}
}
