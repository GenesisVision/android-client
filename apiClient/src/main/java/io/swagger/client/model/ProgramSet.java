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

import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProgramSet
 */

public class ProgramSet
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("count")
	private Integer count = null;

	public ProgramSet id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ProgramSet name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@ApiModelProperty(value = "")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProgramSet logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@ApiModelProperty(value = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public ProgramSet count(Integer count) {
		this.count = count;
		return this;
	}

	/**
	 * Get count
	 *
	 * @return count
	 **/
	@ApiModelProperty(value = "")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramSet programSet = (ProgramSet) o;
		return Objects.equals(this.id, programSet.id) &&
				Objects.equals(this.name, programSet.name) &&
				Objects.equals(this.logo, programSet.logo) &&
				Objects.equals(this.count, programSet.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, logo, count);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramSet {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    count: ").append(toIndentedString(count)).append("\n");
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
