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

import io.swagger.annotations.ApiModelProperty;

/**
 * ClosePeriodData
 */

public class ClosePeriodData
{
	@SerializedName("currentPeriod")
	private Period currentPeriod = null;

	@SerializedName("nextPeriod")
	private Period nextPeriod = null;

	@SerializedName("canCloseCurrentPeriod")
	private Boolean canCloseCurrentPeriod = null;

	public ClosePeriodData currentPeriod(Period currentPeriod) {
		this.currentPeriod = currentPeriod;
		return this;
	}

	/**
	 * Get currentPeriod
	 *
	 * @return currentPeriod
	 **/
	@ApiModelProperty(value = "")
	public Period getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Period currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public ClosePeriodData nextPeriod(Period nextPeriod) {
		this.nextPeriod = nextPeriod;
		return this;
	}

	/**
	 * Get nextPeriod
	 *
	 * @return nextPeriod
	 **/
	@ApiModelProperty(value = "")
	public Period getNextPeriod() {
		return nextPeriod;
	}

	public void setNextPeriod(Period nextPeriod) {
		this.nextPeriod = nextPeriod;
	}

	public ClosePeriodData canCloseCurrentPeriod(Boolean canCloseCurrentPeriod) {
		this.canCloseCurrentPeriod = canCloseCurrentPeriod;
		return this;
	}

	/**
	 * Get canCloseCurrentPeriod
	 *
	 * @return canCloseCurrentPeriod
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanCloseCurrentPeriod() {
		return canCloseCurrentPeriod;
	}

	public void setCanCloseCurrentPeriod(Boolean canCloseCurrentPeriod) {
		this.canCloseCurrentPeriod = canCloseCurrentPeriod;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ClosePeriodData closePeriodData = (ClosePeriodData) o;
		return Objects.equals(this.currentPeriod, closePeriodData.currentPeriod) &&
				Objects.equals(this.nextPeriod, closePeriodData.nextPeriod) &&
				Objects.equals(this.canCloseCurrentPeriod, closePeriodData.canCloseCurrentPeriod);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentPeriod, nextPeriod, canCloseCurrentPeriod);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ClosePeriodData {\n");

		sb.append("    currentPeriod: ").append(toIndentedString(currentPeriod)).append("\n");
		sb.append("    nextPeriod: ").append(toIndentedString(nextPeriod)).append("\n");
		sb.append("    canCloseCurrentPeriod: ").append(toIndentedString(canCloseCurrentPeriod)).append("\n");
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
