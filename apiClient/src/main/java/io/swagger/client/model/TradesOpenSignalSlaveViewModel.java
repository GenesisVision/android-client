/*
 * Core API v1.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
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
 * TradesOpenSignalSlaveViewModel
 */

public class TradesOpenSignalSlaveViewModel
{
	@SerializedName("trades")
	private List<OrderOpenSignalSlaveModel> trades = null;

	@SerializedName("total")
	private Integer total = null;

	public TradesOpenSignalSlaveViewModel trades(List<OrderOpenSignalSlaveModel> trades) {
		this.trades = trades;
		return this;
	}

	public TradesOpenSignalSlaveViewModel addTradesItem(OrderOpenSignalSlaveModel tradesItem) {
		if (this.trades == null) {
			this.trades = new ArrayList<OrderOpenSignalSlaveModel>();
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
	public List<OrderOpenSignalSlaveModel> getTrades() {
		return trades;
	}

	public void setTrades(List<OrderOpenSignalSlaveModel> trades) {
		this.trades = trades;
	}

	public TradesOpenSignalSlaveViewModel total(Integer total) {
		this.total = total;
		return this;
	}

	/**
	 * Get total
	 *
	 * @return total
	 **/
	@ApiModelProperty(value = "")
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
		TradesOpenSignalSlaveViewModel tradesOpenSignalSlaveViewModel = (TradesOpenSignalSlaveViewModel) o;
		return Objects.equals(this.trades, tradesOpenSignalSlaveViewModel.trades) &&
				Objects.equals(this.total, tradesOpenSignalSlaveViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trades, total);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradesOpenSignalSlaveViewModel {\n");

		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
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

}

