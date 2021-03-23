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
 * ProgramPeriodsViewModel
 */


public class ProgramPeriodsViewModel implements Parcelable
{
	@SerializedName("periods")
	private List<ProgramPeriodViewModel> periods = null;

	@SerializedName("total")
	private Integer total = null;

	public ProgramPeriodsViewModel() {
	}

	public ProgramPeriodsViewModel periods(List<ProgramPeriodViewModel> periods) {
		this.periods = periods;
		return this;
	}

	public ProgramPeriodsViewModel addPeriodsItem(ProgramPeriodViewModel periodsItem) {
		if (this.periods == null) {
			this.periods = new ArrayList<ProgramPeriodViewModel>();
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
	public List<ProgramPeriodViewModel> getPeriods() {
		return periods;
	}

	public void setPeriods(List<ProgramPeriodViewModel> periods) {
		this.periods = periods;
	}

	public ProgramPeriodsViewModel total(Integer total) {
		this.total = total;
		return this;
	}

	/**
	 * Get total
	 *
	 * @return total
	 **/
	@Schema(description = "")
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramPeriodsViewModel programPeriodsViewModel = (ProgramPeriodsViewModel) o;
		return Objects.equals(this.periods, programPeriodsViewModel.periods) &&
				Objects.equals(this.total, programPeriodsViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(periods, total);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramPeriodsViewModel {\n");

		sb.append("    periods: ").append(toIndentedString(periods)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
		out.writeValue(periods);
		out.writeValue(total);
	}

	public static final Parcelable.Creator<ProgramPeriodsViewModel> CREATOR = new Parcelable.Creator<ProgramPeriodsViewModel>()
	{
		public ProgramPeriodsViewModel createFromParcel(Parcel in) {
			return new ProgramPeriodsViewModel(in);
		}

		public ProgramPeriodsViewModel[] newArray(int size) {
			return new ProgramPeriodsViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ProgramPeriodsViewModel(Parcel in) {
		periods = (List<ProgramPeriodViewModel>) in.readValue(ProgramPeriodViewModel.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}
}
