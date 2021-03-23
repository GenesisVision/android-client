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
 * TradingScheduleInfo
 */


public class TradingScheduleInfo implements Parcelable
{
	@SerializedName("hasTradingSchedule")
	private Boolean hasTradingSchedule = null;

	@SerializedName("dayStart")
	private DayOfWeekType dayStart = null;

	@SerializedName("hourStart")
	private Integer hourStart = null;

	@SerializedName("minuteStart")
	private Integer minuteStart = null;

	@SerializedName("dayEnd")
	private DayOfWeekType dayEnd = null;

	@SerializedName("hourEnd")
	private Integer hourEnd = null;

	@SerializedName("minuteEnd")
	private Integer minuteEnd = null;

	public TradingScheduleInfo() {
	}

	public TradingScheduleInfo hasTradingSchedule(Boolean hasTradingSchedule) {
		this.hasTradingSchedule = hasTradingSchedule;
		return this;
	}

	/**
	 * Get hasTradingSchedule
	 *
	 * @return hasTradingSchedule
	 **/
	@Schema(description = "")
	public Boolean isHasTradingSchedule() {
		return hasTradingSchedule;
	}

	public void setHasTradingSchedule(Boolean hasTradingSchedule) {
		this.hasTradingSchedule = hasTradingSchedule;
	}

	public TradingScheduleInfo dayStart(DayOfWeekType dayStart) {
		this.dayStart = dayStart;
		return this;
	}

	/**
	 * Get dayStart
	 *
	 * @return dayStart
	 **/
	@Schema(description = "")
	public DayOfWeekType getDayStart() {
		return dayStart;
	}

	public void setDayStart(DayOfWeekType dayStart) {
		this.dayStart = dayStart;
	}

	public TradingScheduleInfo hourStart(Integer hourStart) {
		this.hourStart = hourStart;
		return this;
	}

	/**
	 * Get hourStart
	 *
	 * @return hourStart
	 **/
	@Schema(description = "")
	public Integer getHourStart() {
		return hourStart;
	}

	public void setHourStart(Integer hourStart) {
		this.hourStart = hourStart;
	}

	public TradingScheduleInfo minuteStart(Integer minuteStart) {
		this.minuteStart = minuteStart;
		return this;
	}

	/**
	 * Get minuteStart
	 *
	 * @return minuteStart
	 **/
	@Schema(description = "")
	public Integer getMinuteStart() {
		return minuteStart;
	}

	public void setMinuteStart(Integer minuteStart) {
		this.minuteStart = minuteStart;
	}

	public TradingScheduleInfo dayEnd(DayOfWeekType dayEnd) {
		this.dayEnd = dayEnd;
		return this;
	}

	/**
	 * Get dayEnd
	 *
	 * @return dayEnd
	 **/
	@Schema(description = "")
	public DayOfWeekType getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(DayOfWeekType dayEnd) {
		this.dayEnd = dayEnd;
	}

	public TradingScheduleInfo hourEnd(Integer hourEnd) {
		this.hourEnd = hourEnd;
		return this;
	}

	/**
	 * Get hourEnd
	 *
	 * @return hourEnd
	 **/
	@Schema(description = "")
	public Integer getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(Integer hourEnd) {
		this.hourEnd = hourEnd;
	}

	public TradingScheduleInfo minuteEnd(Integer minuteEnd) {
		this.minuteEnd = minuteEnd;
		return this;
	}

	/**
	 * Get minuteEnd
	 *
	 * @return minuteEnd
	 **/
	@Schema(description = "")
	public Integer getMinuteEnd() {
		return minuteEnd;
	}

	public void setMinuteEnd(Integer minuteEnd) {
		this.minuteEnd = minuteEnd;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradingScheduleInfo tradingScheduleInfo = (TradingScheduleInfo) o;
		return Objects.equals(this.hasTradingSchedule, tradingScheduleInfo.hasTradingSchedule) &&
				Objects.equals(this.dayStart, tradingScheduleInfo.dayStart) &&
				Objects.equals(this.hourStart, tradingScheduleInfo.hourStart) &&
				Objects.equals(this.minuteStart, tradingScheduleInfo.minuteStart) &&
				Objects.equals(this.dayEnd, tradingScheduleInfo.dayEnd) &&
				Objects.equals(this.hourEnd, tradingScheduleInfo.hourEnd) &&
				Objects.equals(this.minuteEnd, tradingScheduleInfo.minuteEnd);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hasTradingSchedule, dayStart, hourStart, minuteStart, dayEnd, hourEnd, minuteEnd);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradingScheduleInfo {\n");

		sb.append("    hasTradingSchedule: ").append(toIndentedString(hasTradingSchedule)).append("\n");
		sb.append("    dayStart: ").append(toIndentedString(dayStart)).append("\n");
		sb.append("    hourStart: ").append(toIndentedString(hourStart)).append("\n");
		sb.append("    minuteStart: ").append(toIndentedString(minuteStart)).append("\n");
		sb.append("    dayEnd: ").append(toIndentedString(dayEnd)).append("\n");
		sb.append("    hourEnd: ").append(toIndentedString(hourEnd)).append("\n");
		sb.append("    minuteEnd: ").append(toIndentedString(minuteEnd)).append("\n");
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
		out.writeValue(hasTradingSchedule);
		out.writeValue(dayStart);
		out.writeValue(hourStart);
		out.writeValue(minuteStart);
		out.writeValue(dayEnd);
		out.writeValue(hourEnd);
		out.writeValue(minuteEnd);
	}

	public static final Parcelable.Creator<TradingScheduleInfo> CREATOR = new Parcelable.Creator<TradingScheduleInfo>()
	{
		public TradingScheduleInfo createFromParcel(Parcel in) {
			return new TradingScheduleInfo(in);
		}

		public TradingScheduleInfo[] newArray(int size) {
			return new TradingScheduleInfo[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	TradingScheduleInfo(Parcel in) {
		hasTradingSchedule = (Boolean) in.readValue(null);
		dayStart = (DayOfWeekType) in.readValue(DayOfWeekType.class.getClassLoader());
		hourStart = (Integer) in.readValue(null);
		minuteStart = (Integer) in.readValue(null);
		dayEnd = (DayOfWeekType) in.readValue(DayOfWeekType.class.getClassLoader());
		hourEnd = (Integer) in.readValue(null);
		minuteEnd = (Integer) in.readValue(null);
	}
}
