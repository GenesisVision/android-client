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
 * ProgramsLevelsInfo
 */

public class ProgramsLevelsInfo
{
	@SerializedName("levels")
	private List<LevelInfo> levels = null;

	public ProgramsLevelsInfo levels(List<LevelInfo> levels) {
		this.levels = levels;
		return this;
	}

	public ProgramsLevelsInfo addLevelsItem(LevelInfo levelsItem) {
		if (this.levels == null) {
			this.levels = new ArrayList<LevelInfo>();
		}
		this.levels.add(levelsItem);
		return this;
	}

	/**
	 * Get levels
	 *
	 * @return levels
	 **/
	@ApiModelProperty(value = "")
	public List<LevelInfo> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelInfo> levels) {
		this.levels = levels;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramsLevelsInfo programsLevelsInfo = (ProgramsLevelsInfo) o;
		return Objects.equals(this.levels, programsLevelsInfo.levels);
	}

	@Override
	public int hashCode() {
		return Objects.hash(levels);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramsLevelsInfo {\n");

		sb.append("    levels: ").append(toIndentedString(levels)).append("\n");
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
