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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * NotificationViewModel
 */

public class NotificationViewModel
{
	@SerializedName("text")
	private String text = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("type")
	private TypeEnum type = null;

	@SerializedName("programId")
	private UUID programId = null;

	@SerializedName("managerId")
	private UUID managerId = null;

	@SerializedName("logo")
	private String logo = null;

	public NotificationViewModel text(String text) {
		this.text = text;
		return this;
	}

	/**
	 * Get text
	 *
	 * @return text
	 **/
	@ApiModelProperty(value = "")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public NotificationViewModel date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@ApiModelProperty(value = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public NotificationViewModel type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@ApiModelProperty(value = "")
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public NotificationViewModel programId(UUID programId) {
		this.programId = programId;
		return this;
	}

	/**
	 * Get programId
	 *
	 * @return programId
	 **/
	@ApiModelProperty(value = "")
	public UUID getProgramId() {
		return programId;
	}

	public void setProgramId(UUID programId) {
		this.programId = programId;
	}

	public NotificationViewModel managerId(UUID managerId) {
		this.managerId = managerId;
		return this;
	}

	/**
	 * Get managerId
	 *
	 * @return managerId
	 **/
	@ApiModelProperty(value = "")
	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	public NotificationViewModel logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@ApiModelProperty(value = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NotificationViewModel notificationViewModel = (NotificationViewModel) o;
		return Objects.equals(this.text, notificationViewModel.text) &&
				Objects.equals(this.date, notificationViewModel.date) &&
				Objects.equals(this.type, notificationViewModel.type) &&
				Objects.equals(this.programId, notificationViewModel.programId) &&
				Objects.equals(this.managerId, notificationViewModel.managerId) &&
				Objects.equals(this.logo, notificationViewModel.logo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(text, date, type, programId, managerId, logo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NotificationViewModel {\n");

		sb.append("    text: ").append(toIndentedString(text)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    programId: ").append(toIndentedString(programId)).append("\n");
		sb.append("    managerId: ").append(toIndentedString(managerId)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
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

	/**
	 * Gets or Sets type
	 */
	@JsonAdapter(TypeEnum.Adapter.class)
	public enum TypeEnum
	{
		PLATFORMNEWSANDUPDATES("PlatformNewsAndUpdates"),

		PLATFORMEMERGENCY("PlatformEmergency"),

		PLATFORMOTHER("PlatformOther"),

		PROFILEUPDATED("ProfileUpdated"),

		PROFILEPWDUPDATED("ProfilePwdUpdated"),

		PROFILEVERIFICATION("ProfileVerification"),

		PROFILE2FA("Profile2FA"),

		PROFILESECURITY("ProfileSecurity"),

		PROGRAMNEWSANDUPDATES("ProgramNewsAndUpdates"),

		PROGRAMENDOFPERIOD("ProgramEndOfPeriod"),

		PROGRAMCONDITION("ProgramCondition"),

		MANAGERNEWPROGRAM("ManagerNewProgram");

		public static TypeEnum fromValue(String text) {
			for (TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<TypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public TypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return TypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

