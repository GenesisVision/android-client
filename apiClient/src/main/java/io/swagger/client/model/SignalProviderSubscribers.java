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
 * SignalProviderSubscribers
 */


public class SignalProviderSubscribers implements Parcelable
{
	public static final Parcelable.Creator<SignalProviderSubscribers> CREATOR = new Parcelable.Creator<SignalProviderSubscribers>()
	{
		public SignalProviderSubscribers createFromParcel(Parcel in) {
			return new SignalProviderSubscribers(in);
		}

		public SignalProviderSubscribers[] newArray(int size) {
			return new SignalProviderSubscribers[size];
		}
	};

	@SerializedName("subscribers")
	private List<SignalSubscriber> subscribers = null;

	@SerializedName("total")
	private Integer total = null;

	public SignalProviderSubscribers() {
	}

	SignalProviderSubscribers(Parcel in) {
		subscribers = (List<SignalSubscriber>) in.readValue(SignalSubscriber.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	public SignalProviderSubscribers subscribers(List<SignalSubscriber> subscribers) {
		this.subscribers = subscribers;
		return this;
	}

	public SignalProviderSubscribers addSubscribersItem(SignalSubscriber subscribersItem) {
		if (this.subscribers == null) {
			this.subscribers = new ArrayList<SignalSubscriber>();
		}
		this.subscribers.add(subscribersItem);
		return this;
	}

	/**
	 * Get subscribers
	 *
	 * @return subscribers
	 **/
	@Schema(description = "")
	public List<SignalSubscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<SignalSubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public SignalProviderSubscribers total(Integer total) {
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
		SignalProviderSubscribers signalProviderSubscribers = (SignalProviderSubscribers) o;
		return Objects.equals(this.subscribers, signalProviderSubscribers.subscribers) &&
				Objects.equals(this.total, signalProviderSubscribers.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscribers, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SignalProviderSubscribers {\n");

		sb.append("    subscribers: ").append(toIndentedString(subscribers)).append("\n");
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
		out.writeValue(subscribers);
		out.writeValue(total);
	}

	public int describeContents() {
		return 0;
	}
}
