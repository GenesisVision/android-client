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
 * CaptchaDetails
 */


public class CaptchaDetails implements Parcelable
{
	public static final Parcelable.Creator<CaptchaDetails> CREATOR = new Parcelable.Creator<CaptchaDetails>()
	{
		public CaptchaDetails createFromParcel(Parcel in) {
			return new CaptchaDetails(in);
		}

		public CaptchaDetails[] newArray(int size) {
			return new CaptchaDetails[size];
		}
	};

	@SerializedName("captchaType")
	private CaptchaType captchaType = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("route")
	private String route = null;

	@SerializedName("pow")
	private PowDetails pow = null;

	@SerializedName("geeTest")
	private GeeTestDetails geeTest = null;

	public CaptchaDetails() {
	}

	CaptchaDetails(Parcel in) {
		captchaType = (CaptchaType) in.readValue(CaptchaType.class.getClassLoader());
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		route = (String) in.readValue(null);
		pow = (PowDetails) in.readValue(PowDetails.class.getClassLoader());
		geeTest = (GeeTestDetails) in.readValue(GeeTestDetails.class.getClassLoader());
	}

	public CaptchaDetails captchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
		return this;
	}

	/**
	 * Get captchaType
	 *
	 * @return captchaType
	 **/
	@Schema(description = "")
	public CaptchaType getCaptchaType() {
		return captchaType;
	}

	public void setCaptchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
	}

	public CaptchaDetails id(UUID id) {
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

	public CaptchaDetails route(String route) {
		this.route = route;
		return this;
	}

	/**
	 * Get route
	 *
	 * @return route
	 **/
	@Schema(description = "")
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public CaptchaDetails pow(PowDetails pow) {
		this.pow = pow;
		return this;
	}

	/**
	 * Get pow
	 *
	 * @return pow
	 **/
	@Schema(description = "")
	public PowDetails getPow() {
		return pow;
	}

	public void setPow(PowDetails pow) {
		this.pow = pow;
	}

	public CaptchaDetails geeTest(GeeTestDetails geeTest) {
		this.geeTest = geeTest;
		return this;
	}

	/**
	 * Get geeTest
	 *
	 * @return geeTest
	 **/
	@Schema(description = "")
	public GeeTestDetails getGeeTest() {
		return geeTest;
	}

	public void setGeeTest(GeeTestDetails geeTest) {
		this.geeTest = geeTest;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CaptchaDetails captchaDetails = (CaptchaDetails) o;
		return Objects.equals(this.captchaType, captchaDetails.captchaType) &&
				Objects.equals(this.id, captchaDetails.id) &&
				Objects.equals(this.route, captchaDetails.route) &&
				Objects.equals(this.pow, captchaDetails.pow) &&
				Objects.equals(this.geeTest, captchaDetails.geeTest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(captchaType, id, route, pow, geeTest);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CaptchaDetails {\n");

		sb.append("    captchaType: ").append(toIndentedString(captchaType)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    route: ").append(toIndentedString(route)).append("\n");
		sb.append("    pow: ").append(toIndentedString(pow)).append("\n");
		sb.append("    geeTest: ").append(toIndentedString(geeTest)).append("\n");
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
		out.writeValue(captchaType);
		out.writeValue(id);
		out.writeValue(route);
		out.writeValue(pow);
		out.writeValue(geeTest);
	}

	public int describeContents() {
		return 0;
	}
}
