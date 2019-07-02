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
 * OpenSignalTradesList
 */

public class OpenSignalTradesList
{
	@SerializedName("signalTrades")
	private List<OpenSignalTrade> signalTrades = null;

	public OpenSignalTradesList signalTrades(List<OpenSignalTrade> signalTrades) {
		this.signalTrades = signalTrades;
		return this;
	}

	public OpenSignalTradesList addSignalTradesItem(OpenSignalTrade signalTradesItem) {
		if (this.signalTrades == null) {
			this.signalTrades = new ArrayList<OpenSignalTrade>();
		}
		this.signalTrades.add(signalTradesItem);
		return this;
	}

	/**
	 * Get signalTrades
	 *
	 * @return signalTrades
	 **/
	@ApiModelProperty(value = "")
	public List<OpenSignalTrade> getSignalTrades() {
		return signalTrades;
	}

	public void setSignalTrades(List<OpenSignalTrade> signalTrades) {
		this.signalTrades = signalTrades;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OpenSignalTradesList openSignalTradesList = (OpenSignalTradesList) o;
		return Objects.equals(this.signalTrades, openSignalTradesList.signalTrades);
	}

	@Override
	public int hashCode() {
		return Objects.hash(signalTrades);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OpenSignalTradesList {\n");

		sb.append("    signalTrades: ").append(toIndentedString(signalTrades)).append("\n");
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
