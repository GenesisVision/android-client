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

import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * Accrual
 */

public class Accrual
{
	@SerializedName("investorId")
	private UUID investorId = null;

	@SerializedName("amount")
	private Double amount = null;

	public Accrual investorId(UUID investorId) {
		this.investorId = investorId;
		return this;
	}

	/**
	 * Get investorId
	 *
	 * @return investorId
	 **/
	@ApiModelProperty(value = "")
	public UUID getInvestorId() {
		return investorId;
	}

	public void setInvestorId(UUID investorId) {
		this.investorId = investorId;
	}

	public Accrual amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@ApiModelProperty(value = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Accrual accrual = (Accrual) o;
		return Objects.equals(this.investorId, accrual.investorId) &&
				Objects.equals(this.amount, accrual.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(investorId, amount);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Accrual {\n");

		sb.append("    investorId: ").append(toIndentedString(investorId)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
