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
 * BrokersViewModel
 */

public class BrokersViewModel
{
	@SerializedName("brokers")
	private List<BrokerTradeServer> brokers = null;

	@SerializedName("total")
	private Integer total = null;

	public BrokersViewModel brokers(List<BrokerTradeServer> brokers) {
		this.brokers = brokers;
		return this;
	}

	public BrokersViewModel addBrokersItem(BrokerTradeServer brokersItem) {
		if (this.brokers == null) {
			this.brokers = new ArrayList<BrokerTradeServer>();
		}
		this.brokers.add(brokersItem);
		return this;
	}

	/**
	 * Get brokers
	 *
	 * @return brokers
	 **/
	@ApiModelProperty(value = "")
	public List<BrokerTradeServer> getBrokers() {
		return brokers;
	}

	public void setBrokers(List<BrokerTradeServer> brokers) {
		this.brokers = brokers;
	}

	public BrokersViewModel total(Integer total) {
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
		BrokersViewModel brokersViewModel = (BrokersViewModel) o;
		return Objects.equals(this.brokers, brokersViewModel.brokers) &&
				Objects.equals(this.total, brokersViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(brokers, total);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BrokersViewModel {\n");

		sb.append("    brokers: ").append(toIndentedString(brokers)).append("\n");
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

