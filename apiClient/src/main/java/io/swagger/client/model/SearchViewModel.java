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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SearchViewModel
 */


public class SearchViewModel implements Parcelable
{
	public static final Parcelable.Creator<SearchViewModel> CREATOR = new Parcelable.Creator<SearchViewModel>()
	{
		public SearchViewModel createFromParcel(Parcel in) {
			return new SearchViewModel(in);
		}

		public SearchViewModel[] newArray(int size) {
			return new SearchViewModel[size];
		}
	};

	@SerializedName("programs")
	private ItemsViewModelProgramDetailsList programs = null;

	@SerializedName("funds")
	private ItemsViewModelFundDetailsList funds = null;

	@SerializedName("follow")
	private ItemsViewModelFollowDetailsList follow = null;

	@SerializedName("managers")
	private ItemsViewModelPublicProfile managers = null;

	public SearchViewModel() {
	}

	SearchViewModel(Parcel in) {
		programs = (ItemsViewModelProgramDetailsList) in.readValue(ItemsViewModelProgramDetailsList.class.getClassLoader());
		funds = (ItemsViewModelFundDetailsList) in.readValue(ItemsViewModelFundDetailsList.class.getClassLoader());
		follow = (ItemsViewModelFollowDetailsList) in.readValue(ItemsViewModelFollowDetailsList.class.getClassLoader());
		managers = (ItemsViewModelPublicProfile) in.readValue(ItemsViewModelPublicProfile.class.getClassLoader());
	}

	public SearchViewModel programs(ItemsViewModelProgramDetailsList programs) {
		this.programs = programs;
		return this;
	}

	/**
	 * Get programs
	 *
	 * @return programs
	 **/
	@Schema(description = "")
	public ItemsViewModelProgramDetailsList getPrograms() {
		return programs;
	}

	public void setPrograms(ItemsViewModelProgramDetailsList programs) {
		this.programs = programs;
	}

	public SearchViewModel funds(ItemsViewModelFundDetailsList funds) {
		this.funds = funds;
		return this;
	}

	/**
	 * Get funds
	 *
	 * @return funds
	 **/
	@Schema(description = "")
	public ItemsViewModelFundDetailsList getFunds() {
		return funds;
	}

	public void setFunds(ItemsViewModelFundDetailsList funds) {
		this.funds = funds;
	}

	public SearchViewModel follow(ItemsViewModelFollowDetailsList follow) {
		this.follow = follow;
		return this;
	}

	/**
	 * Get follow
	 *
	 * @return follow
	 **/
	@Schema(description = "")
	public ItemsViewModelFollowDetailsList getFollow() {
		return follow;
	}

	public void setFollow(ItemsViewModelFollowDetailsList follow) {
		this.follow = follow;
	}

	public SearchViewModel managers(ItemsViewModelPublicProfile managers) {
		this.managers = managers;
		return this;
	}

	/**
	 * Get managers
	 *
	 * @return managers
	 **/
	@Schema(description = "")
	public ItemsViewModelPublicProfile getManagers() {
		return managers;
	}

	public void setManagers(ItemsViewModelPublicProfile managers) {
		this.managers = managers;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SearchViewModel searchViewModel = (SearchViewModel) o;
		return Objects.equals(this.programs, searchViewModel.programs) &&
				Objects.equals(this.funds, searchViewModel.funds) &&
				Objects.equals(this.follow, searchViewModel.follow) &&
				Objects.equals(this.managers, searchViewModel.managers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(programs, funds, follow, managers);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SearchViewModel {\n");

		sb.append("    programs: ").append(toIndentedString(programs)).append("\n");
		sb.append("    funds: ").append(toIndentedString(funds)).append("\n");
		sb.append("    follow: ").append(toIndentedString(follow)).append("\n");
		sb.append("    managers: ").append(toIndentedString(managers)).append("\n");
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
		out.writeValue(programs);
		out.writeValue(funds);
		out.writeValue(follow);
		out.writeValue(managers);
	}

	public int describeContents() {
		return 0;
	}
}
