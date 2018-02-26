/*
 * Core API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * TradesViewModel
 */

public class TradesViewModel
{
	@SerializedName("trades")
	private List<OrderModel> trades = null;

	public TradesViewModel trades(List<OrderModel> trades) {
		this.trades = trades;
		return this;
	}

	public TradesViewModel addTradesItem(OrderModel tradesItem) {
		if (this.trades == null) {
			this.trades = new ArrayList<OrderModel>();
		}
		this.trades.add(tradesItem);
		return this;
	}

	/**
	 * Get trades
	 *
	 * @return trades
	 **/
	@ApiModelProperty(value = "")
	public List<OrderModel> getTrades() {
		return trades;
	}

	public void setTrades(List<OrderModel> trades) {
		this.trades = trades;
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
		return Objects.equals(this.trades, tradesViewModel.trades);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trades);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradesViewModel {\n");

		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
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

}

