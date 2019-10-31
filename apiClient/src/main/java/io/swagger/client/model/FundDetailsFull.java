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

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * FundDetailsFull
 */


public class FundDetailsFull implements Parcelable
{
	public static final Parcelable.Creator<FundDetailsFull> CREATOR = new Parcelable.Creator<FundDetailsFull>()
	{
		public FundDetailsFull createFromParcel(Parcel in) {
			return new FundDetailsFull(in);
		}

		public FundDetailsFull[] newArray(int size) {
			return new FundDetailsFull[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("entryFeeSelected")
	private Double entryFeeSelected = null;

	@SerializedName("entryFeeCurrent")
	private Double entryFeeCurrent = null;

	@SerializedName("exitFeeSelected")
	private Double exitFeeSelected = null;

	@SerializedName("exitFeeCurrent")
	private Double exitFeeCurrent = null;

	@SerializedName("assetsStructure")
	private List<FundAssetInfo> assetsStructure = null;

	@SerializedName("personalDetails")
	private PersonalFundDetails personalDetails = null;

	@SerializedName("manager")
	private ProfilePublic manager = null;

	public FundDetailsFull() {
	}

	FundDetailsFull(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		logo = (String) in.readValue(null);
		url = (String) in.readValue(null);
		color = (String) in.readValue(null);
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		creationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		entryFeeSelected = (Double) in.readValue(null);
		entryFeeCurrent = (Double) in.readValue(null);
		exitFeeSelected = (Double) in.readValue(null);
		exitFeeCurrent = (Double) in.readValue(null);
		assetsStructure = (List<FundAssetInfo>) in.readValue(FundAssetInfo.class.getClassLoader());
		personalDetails = (PersonalFundDetails) in.readValue(PersonalFundDetails.class.getClassLoader());
		manager = (ProfilePublic) in.readValue(ProfilePublic.class.getClassLoader());
	}

	public FundDetailsFull id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public FundDetailsFull logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public FundDetailsFull url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * Get url
	 *
	 * @return url
	 **/
	@Schema(description = "")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FundDetailsFull color(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Get color
	 *
	 * @return color
	 **/
	@Schema(description = "")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public FundDetailsFull title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FundDetailsFull description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@Schema(description = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FundDetailsFull creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@Schema(description = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public FundDetailsFull entryFeeSelected(Double entryFeeSelected) {
		this.entryFeeSelected = entryFeeSelected;
		return this;
	}

	/**
	 * Get entryFeeSelected
	 *
	 * @return entryFeeSelected
	 **/
	@Schema(description = "")
	public Double getEntryFeeSelected() {
		return entryFeeSelected;
	}

	public void setEntryFeeSelected(Double entryFeeSelected) {
		this.entryFeeSelected = entryFeeSelected;
	}

	public FundDetailsFull entryFeeCurrent(Double entryFeeCurrent) {
		this.entryFeeCurrent = entryFeeCurrent;
		return this;
	}

	/**
	 * Get entryFeeCurrent
	 *
	 * @return entryFeeCurrent
	 **/
	@Schema(description = "")
	public Double getEntryFeeCurrent() {
		return entryFeeCurrent;
	}

	public void setEntryFeeCurrent(Double entryFeeCurrent) {
		this.entryFeeCurrent = entryFeeCurrent;
	}

	public FundDetailsFull exitFeeSelected(Double exitFeeSelected) {
		this.exitFeeSelected = exitFeeSelected;
		return this;
	}

	/**
	 * Get exitFeeSelected
	 *
	 * @return exitFeeSelected
	 **/
	@Schema(description = "")
	public Double getExitFeeSelected() {
		return exitFeeSelected;
	}

	public void setExitFeeSelected(Double exitFeeSelected) {
		this.exitFeeSelected = exitFeeSelected;
	}

	public FundDetailsFull exitFeeCurrent(Double exitFeeCurrent) {
		this.exitFeeCurrent = exitFeeCurrent;
		return this;
	}

	/**
	 * Get exitFeeCurrent
	 *
	 * @return exitFeeCurrent
	 **/
	@Schema(description = "")
	public Double getExitFeeCurrent() {
		return exitFeeCurrent;
	}

	public void setExitFeeCurrent(Double exitFeeCurrent) {
		this.exitFeeCurrent = exitFeeCurrent;
	}

	public FundDetailsFull assetsStructure(List<FundAssetInfo> assetsStructure) {
		this.assetsStructure = assetsStructure;
		return this;
	}

	public FundDetailsFull addAssetsStructureItem(FundAssetInfo assetsStructureItem) {
		if (this.assetsStructure == null) {
			this.assetsStructure = new ArrayList<FundAssetInfo>();
		}
		this.assetsStructure.add(assetsStructureItem);
		return this;
	}

	/**
	 * Get assetsStructure
	 *
	 * @return assetsStructure
	 **/
	@Schema(description = "")
	public List<FundAssetInfo> getAssetsStructure() {
		return assetsStructure;
	}

	public void setAssetsStructure(List<FundAssetInfo> assetsStructure) {
		this.assetsStructure = assetsStructure;
	}

	public FundDetailsFull personalDetails(PersonalFundDetails personalDetails) {
		this.personalDetails = personalDetails;
		return this;
	}

	/**
	 * Get personalDetails
	 *
	 * @return personalDetails
	 **/
	@Schema(description = "")
	public PersonalFundDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalFundDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public FundDetailsFull manager(ProfilePublic manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * Get manager
	 *
	 * @return manager
	 **/
	@Schema(description = "")
	public ProfilePublic getManager() {
		return manager;
	}

	public void setManager(ProfilePublic manager) {
		this.manager = manager;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundDetailsFull fundDetailsFull = (FundDetailsFull) o;
		return Objects.equals(this.id, fundDetailsFull.id) &&
				Objects.equals(this.logo, fundDetailsFull.logo) &&
				Objects.equals(this.url, fundDetailsFull.url) &&
				Objects.equals(this.color, fundDetailsFull.color) &&
				Objects.equals(this.title, fundDetailsFull.title) &&
				Objects.equals(this.description, fundDetailsFull.description) &&
				Objects.equals(this.creationDate, fundDetailsFull.creationDate) &&
				Objects.equals(this.entryFeeSelected, fundDetailsFull.entryFeeSelected) &&
				Objects.equals(this.entryFeeCurrent, fundDetailsFull.entryFeeCurrent) &&
				Objects.equals(this.exitFeeSelected, fundDetailsFull.exitFeeSelected) &&
				Objects.equals(this.exitFeeCurrent, fundDetailsFull.exitFeeCurrent) &&
				Objects.equals(this.assetsStructure, fundDetailsFull.assetsStructure) &&
				Objects.equals(this.personalDetails, fundDetailsFull.personalDetails) &&
				Objects.equals(this.manager, fundDetailsFull.manager);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, logo, url, color, title, description, creationDate, entryFeeSelected, entryFeeCurrent, exitFeeSelected, exitFeeCurrent, assetsStructure, personalDetails, manager);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundDetailsFull {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    entryFeeSelected: ").append(toIndentedString(entryFeeSelected)).append("\n");
		sb.append("    entryFeeCurrent: ").append(toIndentedString(entryFeeCurrent)).append("\n");
		sb.append("    exitFeeSelected: ").append(toIndentedString(exitFeeSelected)).append("\n");
		sb.append("    exitFeeCurrent: ").append(toIndentedString(exitFeeCurrent)).append("\n");
		sb.append("    assetsStructure: ").append(toIndentedString(assetsStructure)).append("\n");
		sb.append("    personalDetails: ").append(toIndentedString(personalDetails)).append("\n");
		sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
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
		out.writeValue(id);
		out.writeValue(logo);
		out.writeValue(url);
		out.writeValue(color);
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(creationDate);
		out.writeValue(entryFeeSelected);
		out.writeValue(entryFeeCurrent);
		out.writeValue(exitFeeSelected);
		out.writeValue(exitFeeCurrent);
		out.writeValue(assetsStructure);
		out.writeValue(personalDetails);
		out.writeValue(manager);
	}

	public int describeContents() {
		return 0;
	}
}
