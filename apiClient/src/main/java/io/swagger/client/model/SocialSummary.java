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
 * SocialSummary
 */


public class SocialSummary implements Parcelable
{
	public static final Parcelable.Creator<SocialSummary> CREATOR = new Parcelable.Creator<SocialSummary>()
	{
		public SocialSummary createFromParcel(Parcel in) {
			return new SocialSummary(in);
		}

		public SocialSummary[] newArray(int size) {
			return new SocialSummary[size];
		}
	};

	@SerializedName("hotTopics")
	private List<SocialSummaryHashTag> hotTopics = null;

	@SerializedName("topStrategies")
	private List<SocialSummaryStrategy> topStrategies = null;

	@SerializedName("topAssets")
	private List<SocialPostPlatformAsset> topAssets = null;

	public SocialSummary() {
	}

	SocialSummary(Parcel in) {
		hotTopics = (List<SocialSummaryHashTag>) in.readValue(SocialSummaryHashTag.class.getClassLoader());
		topStrategies = (List<SocialSummaryStrategy>) in.readValue(SocialSummaryStrategy.class.getClassLoader());
		topAssets = (List<SocialPostPlatformAsset>) in.readValue(SocialPostPlatformAsset.class.getClassLoader());
	}

	public SocialSummary hotTopics(List<SocialSummaryHashTag> hotTopics) {
		this.hotTopics = hotTopics;
		return this;
	}

	public SocialSummary addHotTopicsItem(SocialSummaryHashTag hotTopicsItem) {
		if (this.hotTopics == null) {
			this.hotTopics = new ArrayList<SocialSummaryHashTag>();
		}
		this.hotTopics.add(hotTopicsItem);
		return this;
	}

	/**
	 * Get hotTopics
	 *
	 * @return hotTopics
	 **/
	@Schema(description = "")
	public List<SocialSummaryHashTag> getHotTopics() {
		return hotTopics;
	}

	public void setHotTopics(List<SocialSummaryHashTag> hotTopics) {
		this.hotTopics = hotTopics;
	}

	public SocialSummary topStrategies(List<SocialSummaryStrategy> topStrategies) {
		this.topStrategies = topStrategies;
		return this;
	}

	public SocialSummary addTopStrategiesItem(SocialSummaryStrategy topStrategiesItem) {
		if (this.topStrategies == null) {
			this.topStrategies = new ArrayList<SocialSummaryStrategy>();
		}
		this.topStrategies.add(topStrategiesItem);
		return this;
	}

	/**
	 * Get topStrategies
	 *
	 * @return topStrategies
	 **/
	@Schema(description = "")
	public List<SocialSummaryStrategy> getTopStrategies() {
		return topStrategies;
	}

	public void setTopStrategies(List<SocialSummaryStrategy> topStrategies) {
		this.topStrategies = topStrategies;
	}

	public SocialSummary topAssets(List<SocialPostPlatformAsset> topAssets) {
		this.topAssets = topAssets;
		return this;
	}

	public SocialSummary addTopAssetsItem(SocialPostPlatformAsset topAssetsItem) {
		if (this.topAssets == null) {
			this.topAssets = new ArrayList<SocialPostPlatformAsset>();
		}
		this.topAssets.add(topAssetsItem);
		return this;
	}

	/**
	 * Get topAssets
	 *
	 * @return topAssets
	 **/
	@Schema(description = "")
	public List<SocialPostPlatformAsset> getTopAssets() {
		return topAssets;
	}

	public void setTopAssets(List<SocialPostPlatformAsset> topAssets) {
		this.topAssets = topAssets;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SocialSummary socialSummary = (SocialSummary) o;
		return Objects.equals(this.hotTopics, socialSummary.hotTopics) &&
				Objects.equals(this.topStrategies, socialSummary.topStrategies) &&
				Objects.equals(this.topAssets, socialSummary.topAssets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hotTopics, topStrategies, topAssets);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SocialSummary {\n");

		sb.append("    hotTopics: ").append(toIndentedString(hotTopics)).append("\n");
		sb.append("    topStrategies: ").append(toIndentedString(topStrategies)).append("\n");
		sb.append("    topAssets: ").append(toIndentedString(topAssets)).append("\n");
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
		out.writeValue(hotTopics);
		out.writeValue(topStrategies);
		out.writeValue(topAssets);
	}

	public int describeContents() {
		return 0;
	}
}
