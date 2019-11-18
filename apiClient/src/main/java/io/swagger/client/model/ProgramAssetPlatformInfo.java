/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ProgramAssetPlatformInfo
 */


public class ProgramAssetPlatformInfo implements Parcelable
{
	public static final Parcelable.Creator<ProgramAssetPlatformInfo> CREATOR = new Parcelable.Creator<ProgramAssetPlatformInfo>()
	{
		public ProgramAssetPlatformInfo createFromParcel(Parcel in) {
			return new ProgramAssetPlatformInfo(in);
		}

		public ProgramAssetPlatformInfo[] newArray(int size) {
			return new ProgramAssetPlatformInfo[size];
		}
	};

	@SerializedName("facets")
	private List<AssetFacet> facets = null;

	@SerializedName("tags")
	private List<ProgramTag> tags = null;

	@SerializedName("availableProgramCurrencies")
	private List<String> availableProgramCurrencies = null;

	@SerializedName("minInvestAmounts")
	private List<ProgramMinInvestAmount> minInvestAmounts = null;

	@SerializedName("periods")
	private List<Integer> periods = null;

	@SerializedName("createProgramInfo")
	private ProgramCreateAssetPlatformInfo createProgramInfo = null;

	public ProgramAssetPlatformInfo() {
	}

	ProgramAssetPlatformInfo(Parcel in) {
		facets = (List<AssetFacet>) in.readValue(AssetFacet.class.getClassLoader());
		tags = (List<ProgramTag>) in.readValue(ProgramTag.class.getClassLoader());
		availableProgramCurrencies = (List<String>) in.readValue(null);
		minInvestAmounts = (List<ProgramMinInvestAmount>) in.readValue(ProgramMinInvestAmount.class.getClassLoader());
		periods = (List<Integer>) in.readValue(null);
		createProgramInfo = (ProgramCreateAssetPlatformInfo) in.readValue(ProgramCreateAssetPlatformInfo.class.getClassLoader());
	}

	public ProgramAssetPlatformInfo facets(List<AssetFacet> facets) {
		this.facets = facets;
		return this;
	}

	public ProgramAssetPlatformInfo addFacetsItem(AssetFacet facetsItem) {
		if (this.facets == null) {
			this.facets = new ArrayList<AssetFacet>();
		}
		this.facets.add(facetsItem);
		return this;
	}

	/**
	 * Get facets
	 *
	 * @return facets
	 **/
	@Schema(description = "")
	public List<AssetFacet> getFacets() {
		return facets;
	}

	public void setFacets(List<AssetFacet> facets) {
		this.facets = facets;
	}

	public ProgramAssetPlatformInfo tags(List<ProgramTag> tags) {
		this.tags = tags;
		return this;
	}

	public ProgramAssetPlatformInfo addTagsItem(ProgramTag tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<ProgramTag>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	/**
	 * Get tags
	 *
	 * @return tags
	 **/
	@Schema(description = "")
	public List<ProgramTag> getTags() {
		return tags;
	}

	public void setTags(List<ProgramTag> tags) {
		this.tags = tags;
	}

	public ProgramAssetPlatformInfo availableProgramCurrencies(List<String> availableProgramCurrencies) {
		this.availableProgramCurrencies = availableProgramCurrencies;
		return this;
	}

	public ProgramAssetPlatformInfo addAvailableProgramCurrenciesItem(String availableProgramCurrenciesItem) {
		if (this.availableProgramCurrencies == null) {
			this.availableProgramCurrencies = new ArrayList<String>();
		}
		this.availableProgramCurrencies.add(availableProgramCurrenciesItem);
		return this;
	}

	/**
	 * Get availableProgramCurrencies
	 *
	 * @return availableProgramCurrencies
	 **/
	@Schema(description = "")
	public List<String> getAvailableProgramCurrencies() {
		return availableProgramCurrencies;
	}

	public void setAvailableProgramCurrencies(List<String> availableProgramCurrencies) {
		this.availableProgramCurrencies = availableProgramCurrencies;
	}

	public ProgramAssetPlatformInfo minInvestAmounts(List<ProgramMinInvestAmount> minInvestAmounts) {
		this.minInvestAmounts = minInvestAmounts;
		return this;
	}

	public ProgramAssetPlatformInfo addMinInvestAmountsItem(ProgramMinInvestAmount minInvestAmountsItem) {
		if (this.minInvestAmounts == null) {
			this.minInvestAmounts = new ArrayList<ProgramMinInvestAmount>();
		}
		this.minInvestAmounts.add(minInvestAmountsItem);
		return this;
	}

	/**
	 * Get minInvestAmounts
	 *
	 * @return minInvestAmounts
	 **/
	@Schema(description = "")
	public List<ProgramMinInvestAmount> getMinInvestAmounts() {
		return minInvestAmounts;
	}

	public void setMinInvestAmounts(List<ProgramMinInvestAmount> minInvestAmounts) {
		this.minInvestAmounts = minInvestAmounts;
	}

	public ProgramAssetPlatformInfo periods(List<Integer> periods) {
		this.periods = periods;
		return this;
	}

	public ProgramAssetPlatformInfo addPeriodsItem(Integer periodsItem) {
		if (this.periods == null) {
			this.periods = new ArrayList<Integer>();
		}
		this.periods.add(periodsItem);
		return this;
	}

	/**
	 * Get periods
	 *
	 * @return periods
	 **/
	@Schema(description = "")
	public List<Integer> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Integer> periods) {
		this.periods = periods;
	}

	public ProgramAssetPlatformInfo createProgramInfo(ProgramCreateAssetPlatformInfo createProgramInfo) {
		this.createProgramInfo = createProgramInfo;
		return this;
	}

	/**
	 * Get createProgramInfo
	 *
	 * @return createProgramInfo
	 **/
	@Schema(description = "")
	public ProgramCreateAssetPlatformInfo getCreateProgramInfo() {
		return createProgramInfo;
	}

	public void setCreateProgramInfo(ProgramCreateAssetPlatformInfo createProgramInfo) {
		this.createProgramInfo = createProgramInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramAssetPlatformInfo programAssetPlatformInfo = (ProgramAssetPlatformInfo) o;
		return Objects.equals(this.facets, programAssetPlatformInfo.facets) &&
				Objects.equals(this.tags, programAssetPlatformInfo.tags) &&
				Objects.equals(this.availableProgramCurrencies, programAssetPlatformInfo.availableProgramCurrencies) &&
				Objects.equals(this.minInvestAmounts, programAssetPlatformInfo.minInvestAmounts) &&
				Objects.equals(this.periods, programAssetPlatformInfo.periods) &&
				Objects.equals(this.createProgramInfo, programAssetPlatformInfo.createProgramInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(facets, tags, availableProgramCurrencies, minInvestAmounts, periods, createProgramInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramAssetPlatformInfo {\n");

		sb.append("    facets: ").append(toIndentedString(facets)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    availableProgramCurrencies: ").append(toIndentedString(availableProgramCurrencies)).append("\n");
		sb.append("    minInvestAmounts: ").append(toIndentedString(minInvestAmounts)).append("\n");
		sb.append("    periods: ").append(toIndentedString(periods)).append("\n");
		sb.append("    createProgramInfo: ").append(toIndentedString(createProgramInfo)).append("\n");
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

	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(facets);
		out.writeValue(tags);
		out.writeValue(availableProgramCurrencies);
		out.writeValue(minInvestAmounts);
		out.writeValue(periods);
		out.writeValue(createProgramInfo);
	}

	public int describeContents() {
		return 0;
	}
}
