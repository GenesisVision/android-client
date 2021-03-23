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
 * SiteMapInfo
 */


public class SiteMapInfo implements Parcelable
{
	@SerializedName("programs")
	private List<String> programs = null;

	@SerializedName("funds")
	private List<String> funds = null;

	@SerializedName("follow")
	private List<String> follow = null;

	@SerializedName("users")
	private List<String> users = null;

	@SerializedName("actives")
	private List<String> actives = null;

	public SiteMapInfo() {
	}

	public SiteMapInfo programs(List<String> programs) {
		this.programs = programs;
		return this;
	}

	public SiteMapInfo addProgramsItem(String programsItem) {
		if (this.programs == null) {
			this.programs = new ArrayList<String>();
		}
		this.programs.add(programsItem);
		return this;
	}

	/**
	 * Get programs
	 *
	 * @return programs
	 **/
	@Schema(description = "")
	public List<String> getPrograms() {
		return programs;
	}

	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}

	public SiteMapInfo funds(List<String> funds) {
		this.funds = funds;
		return this;
	}

	public SiteMapInfo addFundsItem(String fundsItem) {
		if (this.funds == null) {
			this.funds = new ArrayList<String>();
		}
		this.funds.add(fundsItem);
		return this;
	}

	/**
	 * Get funds
	 *
	 * @return funds
	 **/
	@Schema(description = "")
	public List<String> getFunds() {
		return funds;
	}

	public void setFunds(List<String> funds) {
		this.funds = funds;
	}

	public SiteMapInfo follow(List<String> follow) {
		this.follow = follow;
		return this;
	}

	public SiteMapInfo addFollowItem(String followItem) {
		if (this.follow == null) {
			this.follow = new ArrayList<String>();
		}
		this.follow.add(followItem);
		return this;
	}

	/**
	 * Get follow
	 *
	 * @return follow
	 **/
	@Schema(description = "")
	public List<String> getFollow() {
		return follow;
	}

	public void setFollow(List<String> follow) {
		this.follow = follow;
	}

	public SiteMapInfo users(List<String> users) {
		this.users = users;
		return this;
	}

	public SiteMapInfo addUsersItem(String usersItem) {
		if (this.users == null) {
			this.users = new ArrayList<String>();
		}
		this.users.add(usersItem);
		return this;
	}

	/**
	 * Get users
	 *
	 * @return users
	 **/
	@Schema(description = "")
	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public SiteMapInfo actives(List<String> actives) {
		this.actives = actives;
		return this;
	}

	public SiteMapInfo addActivesItem(String activesItem) {
		if (this.actives == null) {
			this.actives = new ArrayList<String>();
		}
		this.actives.add(activesItem);
		return this;
	}

	/**
	 * Get actives
	 *
	 * @return actives
	 **/
	@Schema(description = "")
	public List<String> getActives() {
		return actives;
	}

	public void setActives(List<String> actives) {
		this.actives = actives;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SiteMapInfo siteMapInfo = (SiteMapInfo) o;
		return Objects.equals(this.programs, siteMapInfo.programs) &&
				Objects.equals(this.funds, siteMapInfo.funds) &&
				Objects.equals(this.follow, siteMapInfo.follow) &&
				Objects.equals(this.users, siteMapInfo.users) &&
				Objects.equals(this.actives, siteMapInfo.actives);
	}

	@Override
	public int hashCode() {
		return Objects.hash(programs, funds, follow, users, actives);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SiteMapInfo {\n");

		sb.append("    programs: ").append(toIndentedString(programs)).append("\n");
		sb.append("    funds: ").append(toIndentedString(funds)).append("\n");
		sb.append("    follow: ").append(toIndentedString(follow)).append("\n");
		sb.append("    users: ").append(toIndentedString(users)).append("\n");
		sb.append("    actives: ").append(toIndentedString(actives)).append("\n");
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
		out.writeValue(users);
		out.writeValue(actives);
	}

	public static final Parcelable.Creator<SiteMapInfo> CREATOR = new Parcelable.Creator<SiteMapInfo>()
	{
		public SiteMapInfo createFromParcel(Parcel in) {
			return new SiteMapInfo(in);
		}

		public SiteMapInfo[] newArray(int size) {
			return new SiteMapInfo[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	SiteMapInfo(Parcel in) {
		programs = (List<String>) in.readValue(null);
		funds = (List<String>) in.readValue(null);
		follow = (List<String>) in.readValue(null);
		users = (List<String>) in.readValue(null);
		actives = (List<String>) in.readValue(null);
	}
}
