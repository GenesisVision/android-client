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
 * Invest
 */

public class Invest
{
	@SerializedName("investmentProgramId")
	private UUID investmentProgramId = null;

	@SerializedName("userId")
	private UUID userId = null;

	@SerializedName("amount")
	private Double amount = null;

	public Invest investmentProgramId(UUID investmentProgramId) {
		this.investmentProgramId = investmentProgramId;
		return this;
	}

	/**
	 * Get investmentProgramId
	 *
	 * @return investmentProgramId
	 **/
	@ApiModelProperty(value = "")
	public UUID getInvestmentProgramId() {
		return investmentProgramId;
	}

	public void setInvestmentProgramId(UUID investmentProgramId) {
		this.investmentProgramId = investmentProgramId;
	}

	public Invest userId(UUID userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * Get userId
	 *
	 * @return userId
	 **/
	@ApiModelProperty(value = "")
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Invest amount(Double amount) {
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
		Invest invest = (Invest) o;
		return Objects.equals(this.investmentProgramId, invest.investmentProgramId) &&
				Objects.equals(this.userId, invest.userId) &&
				Objects.equals(this.amount, invest.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(investmentProgramId, userId, amount);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Invest {\n");

		sb.append("    investmentProgramId: ").append(toIndentedString(investmentProgramId)).append("\n");
		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
