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

import io.swagger.annotations.ApiModelProperty;

/**
 * ProgramLevelInfo
 */

public class ProgramLevelInfo
{
	@SerializedName("isKycPassed")
	private Boolean isKycPassed = null;

	@SerializedName("level")
	private Integer level = null;

	@SerializedName("levelProgressPercent")
	private Double levelProgressPercent = null;

	@SerializedName("genesisRatio")
	private Double genesisRatio = null;

	@SerializedName("programAge")
	private Double programAge = null;

	@SerializedName("weightedVolumeScale")
	private Double weightedVolumeScale = null;

	@SerializedName("managerBalance")
	private Double managerBalance = null;

	@SerializedName("investmentScale")
	private Double investmentScale = null;

	@SerializedName("totalAvailableToInvest")
	private Double totalAvailableToInvest = null;

	public ProgramLevelInfo isKycPassed(Boolean isKycPassed) {
		this.isKycPassed = isKycPassed;
		return this;
	}

	/**
	 * Get isKycPassed
	 *
	 * @return isKycPassed
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsKycPassed() {
		return isKycPassed;
	}

	public void setIsKycPassed(Boolean isKycPassed) {
		this.isKycPassed = isKycPassed;
	}

	public ProgramLevelInfo level(Integer level) {
		this.level = level;
		return this;
	}

	/**
	 * Get level
	 *
	 * @return level
	 **/
	@ApiModelProperty(value = "")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public ProgramLevelInfo levelProgressPercent(Double levelProgressPercent) {
		this.levelProgressPercent = levelProgressPercent;
		return this;
	}

	/**
	 * Get levelProgressPercent
	 *
	 * @return levelProgressPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getLevelProgressPercent() {
		return levelProgressPercent;
	}

	public void setLevelProgressPercent(Double levelProgressPercent) {
		this.levelProgressPercent = levelProgressPercent;
	}

	public ProgramLevelInfo genesisRatio(Double genesisRatio) {
		this.genesisRatio = genesisRatio;
		return this;
	}

	/**
	 * Get genesisRatio
	 *
	 * @return genesisRatio
	 **/
	@ApiModelProperty(value = "")
	public Double getGenesisRatio() {
		return genesisRatio;
	}

	public void setGenesisRatio(Double genesisRatio) {
		this.genesisRatio = genesisRatio;
	}

	public ProgramLevelInfo programAge(Double programAge) {
		this.programAge = programAge;
		return this;
	}

	/**
	 * Get programAge
	 *
	 * @return programAge
	 **/
	@ApiModelProperty(value = "")
	public Double getProgramAge() {
		return programAge;
	}

	public void setProgramAge(Double programAge) {
		this.programAge = programAge;
	}

	public ProgramLevelInfo weightedVolumeScale(Double weightedVolumeScale) {
		this.weightedVolumeScale = weightedVolumeScale;
		return this;
	}

	/**
	 * Get weightedVolumeScale
	 *
	 * @return weightedVolumeScale
	 **/
	@ApiModelProperty(value = "")
	public Double getWeightedVolumeScale() {
		return weightedVolumeScale;
	}

	public void setWeightedVolumeScale(Double weightedVolumeScale) {
		this.weightedVolumeScale = weightedVolumeScale;
	}

	public ProgramLevelInfo managerBalance(Double managerBalance) {
		this.managerBalance = managerBalance;
		return this;
	}

	/**
	 * Get managerBalance
	 *
	 * @return managerBalance
	 **/
	@ApiModelProperty(value = "")
	public Double getManagerBalance() {
		return managerBalance;
	}

	public void setManagerBalance(Double managerBalance) {
		this.managerBalance = managerBalance;
	}

	public ProgramLevelInfo investmentScale(Double investmentScale) {
		this.investmentScale = investmentScale;
		return this;
	}

	/**
	 * Get investmentScale
	 *
	 * @return investmentScale
	 **/
	@ApiModelProperty(value = "")
	public Double getInvestmentScale() {
		return investmentScale;
	}

	public void setInvestmentScale(Double investmentScale) {
		this.investmentScale = investmentScale;
	}

	public ProgramLevelInfo totalAvailableToInvest(Double totalAvailableToInvest) {
		this.totalAvailableToInvest = totalAvailableToInvest;
		return this;
	}

	/**
	 * Get totalAvailableToInvest
	 *
	 * @return totalAvailableToInvest
	 **/
	@ApiModelProperty(value = "")
	public Double getTotalAvailableToInvest() {
		return totalAvailableToInvest;
	}

	public void setTotalAvailableToInvest(Double totalAvailableToInvest) {
		this.totalAvailableToInvest = totalAvailableToInvest;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramLevelInfo programLevelInfo = (ProgramLevelInfo) o;
		return Objects.equals(this.isKycPassed, programLevelInfo.isKycPassed) &&
				Objects.equals(this.level, programLevelInfo.level) &&
				Objects.equals(this.levelProgressPercent, programLevelInfo.levelProgressPercent) &&
				Objects.equals(this.genesisRatio, programLevelInfo.genesisRatio) &&
				Objects.equals(this.programAge, programLevelInfo.programAge) &&
				Objects.equals(this.weightedVolumeScale, programLevelInfo.weightedVolumeScale) &&
				Objects.equals(this.managerBalance, programLevelInfo.managerBalance) &&
				Objects.equals(this.investmentScale, programLevelInfo.investmentScale) &&
				Objects.equals(this.totalAvailableToInvest, programLevelInfo.totalAvailableToInvest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isKycPassed, level, levelProgressPercent, genesisRatio, programAge, weightedVolumeScale, managerBalance, investmentScale, totalAvailableToInvest);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramLevelInfo {\n");

		sb.append("    isKycPassed: ").append(toIndentedString(isKycPassed)).append("\n");
		sb.append("    level: ").append(toIndentedString(level)).append("\n");
		sb.append("    levelProgressPercent: ").append(toIndentedString(levelProgressPercent)).append("\n");
		sb.append("    genesisRatio: ").append(toIndentedString(genesisRatio)).append("\n");
		sb.append("    programAge: ").append(toIndentedString(programAge)).append("\n");
		sb.append("    weightedVolumeScale: ").append(toIndentedString(weightedVolumeScale)).append("\n");
		sb.append("    managerBalance: ").append(toIndentedString(managerBalance)).append("\n");
		sb.append("    investmentScale: ").append(toIndentedString(investmentScale)).append("\n");
		sb.append("    totalAvailableToInvest: ").append(toIndentedString(totalAvailableToInvest)).append("\n");
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
