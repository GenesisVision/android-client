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

import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * FundFacet
 */


public class FundFacet implements Parcelable
{
	public static final Parcelable.Creator<FundFacet> CREATOR = new Parcelable.Creator<FundFacet>()
	{
		public FundFacet createFromParcel(Parcel in) {
			return new FundFacet(in);
		}

		public FundFacet[] newArray(int size) {
			return new FundFacet[size];
		}
	};

	@SerializedName("sorting")
	private FundsFilterSorting sorting = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("sortType")
	private FacetSortType sortType = null;

	@SerializedName("timeframe")
	private Timeframe timeframe = null;

	public FundFacet() {
	}

	FundFacet(Parcel in) {
		sorting = (FundsFilterSorting) in.readValue(FundsFilterSorting.class.getClassLoader());
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		logo = (String) in.readValue(null);
		url = (String) in.readValue(null);
		sortType = (FacetSortType) in.readValue(FacetSortType.class.getClassLoader());
		timeframe = (Timeframe) in.readValue(Timeframe.class.getClassLoader());
	}

	public FundFacet sorting(FundsFilterSorting sorting) {
		this.sorting = sorting;
		return this;
	}

	/**
	 * Get sorting
	 *
	 * @return sorting
	 **/
	@Schema(description = "")
	public FundsFilterSorting getSorting() {
		return sorting;
	}

	public void setSorting(FundsFilterSorting sorting) {
		this.sorting = sorting;
	}

	public FundFacet id(UUID id) {
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

	public FundFacet title(String title) {
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

	public FundFacet description(String description) {
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

	public FundFacet logo(String logo) {
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

	public FundFacet url(String url) {
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

	public FundFacet sortType(FacetSortType sortType) {
		this.sortType = sortType;
		return this;
	}

	/**
	 * Get sortType
	 *
	 * @return sortType
	 **/
	@Schema(description = "")
	public FacetSortType getSortType() {
		return sortType;
	}

	public void setSortType(FacetSortType sortType) {
		this.sortType = sortType;
	}

	public FundFacet timeframe(Timeframe timeframe) {
		this.timeframe = timeframe;
		return this;
	}

	/**
	 * Get timeframe
	 *
	 * @return timeframe
	 **/
	@Schema(description = "")
	public Timeframe getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(Timeframe timeframe) {
		this.timeframe = timeframe;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundFacet fundFacet = (FundFacet) o;
		return Objects.equals(this.sorting, fundFacet.sorting) &&
				Objects.equals(this.id, fundFacet.id) &&
				Objects.equals(this.title, fundFacet.title) &&
				Objects.equals(this.description, fundFacet.description) &&
				Objects.equals(this.logo, fundFacet.logo) &&
				Objects.equals(this.url, fundFacet.url) &&
				Objects.equals(this.sortType, fundFacet.sortType) &&
				Objects.equals(this.timeframe, fundFacet.timeframe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sorting, id, title, description, logo, url, sortType, timeframe);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundFacet {\n");

		sb.append("    sorting: ").append(toIndentedString(sorting)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    sortType: ").append(toIndentedString(sortType)).append("\n");
		sb.append("    timeframe: ").append(toIndentedString(timeframe)).append("\n");
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
		out.writeValue(sorting);
		out.writeValue(id);
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(logo);
		out.writeValue(url);
		out.writeValue(sortType);
		out.writeValue(timeframe);
	}

	public int describeContents() {
		return 0;
	}
}
