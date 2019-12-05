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
 * FollowAssetPlatformInfo
 */


public class FollowAssetPlatformInfo implements Parcelable
{
	public static final Parcelable.Creator<FollowAssetPlatformInfo> CREATOR = new Parcelable.Creator<FollowAssetPlatformInfo>()
	{
		public FollowAssetPlatformInfo createFromParcel(Parcel in) {
			return new FollowAssetPlatformInfo(in);
		}

		public FollowAssetPlatformInfo[] newArray(int size) {
			return new FollowAssetPlatformInfo[size];
		}
	};

	@SerializedName("facets")
	private List<AssetFacet> facets = null;

	@SerializedName("createFollowInfo")
	private FollowCreateAssetPlatformInfo createFollowInfo = null;

	public FollowAssetPlatformInfo() {
	}

	FollowAssetPlatformInfo(Parcel in) {
		facets = (List<AssetFacet>) in.readValue(AssetFacet.class.getClassLoader());
		createFollowInfo = (FollowCreateAssetPlatformInfo) in.readValue(FollowCreateAssetPlatformInfo.class.getClassLoader());
	}

	public FollowAssetPlatformInfo facets(List<AssetFacet> facets) {
		this.facets = facets;
		return this;
	}

	public FollowAssetPlatformInfo addFacetsItem(AssetFacet facetsItem) {
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

	public FollowAssetPlatformInfo createFollowInfo(FollowCreateAssetPlatformInfo createFollowInfo) {
		this.createFollowInfo = createFollowInfo;
		return this;
	}

	/**
	 * Get createFollowInfo
	 *
	 * @return createFollowInfo
	 **/
	@Schema(description = "")
	public FollowCreateAssetPlatformInfo getCreateFollowInfo() {
		return createFollowInfo;
	}

	public void setCreateFollowInfo(FollowCreateAssetPlatformInfo createFollowInfo) {
		this.createFollowInfo = createFollowInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FollowAssetPlatformInfo followAssetPlatformInfo = (FollowAssetPlatformInfo) o;
		return Objects.equals(this.facets, followAssetPlatformInfo.facets) &&
				Objects.equals(this.createFollowInfo, followAssetPlatformInfo.createFollowInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(facets, createFollowInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FollowAssetPlatformInfo {\n");

		sb.append("    facets: ").append(toIndentedString(facets)).append("\n");
		sb.append("    createFollowInfo: ").append(toIndentedString(createFollowInfo)).append("\n");
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
		out.writeValue(createFollowInfo);
	}

	public int describeContents() {
		return 0;
	}
}
