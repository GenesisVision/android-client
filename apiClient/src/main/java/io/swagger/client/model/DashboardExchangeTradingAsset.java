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
 * DashboardExchangeTradingAsset
 */


public class DashboardExchangeTradingAsset implements Parcelable
{
	public static final Parcelable.Creator<DashboardExchangeTradingAsset> CREATOR = new Parcelable.Creator<DashboardExchangeTradingAsset>()
	{
		public DashboardExchangeTradingAsset createFromParcel(Parcel in) {
			return new DashboardExchangeTradingAsset(in);
		}

		public DashboardExchangeTradingAsset[] newArray(int size) {
			return new DashboardExchangeTradingAsset[size];
		}
	};

	@SerializedName("credentials")
	private DashboardTradingAssetCredentials credentials = null;

	@SerializedName("broker")
	private DashboardTradingAssetBrokerDetails broker = null;

	public DashboardExchangeTradingAsset() {
	}

	DashboardExchangeTradingAsset(Parcel in) {
		credentials = (DashboardTradingAssetCredentials) in.readValue(DashboardTradingAssetCredentials.class.getClassLoader());
		broker = (DashboardTradingAssetBrokerDetails) in.readValue(DashboardTradingAssetBrokerDetails.class.getClassLoader());
	}

	public DashboardExchangeTradingAsset credentials(DashboardTradingAssetCredentials credentials) {
		this.credentials = credentials;
		return this;
	}

	/**
	 * Get credentials
	 *
	 * @return credentials
	 **/
	@Schema(description = "")
	public DashboardTradingAssetCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(DashboardTradingAssetCredentials credentials) {
		this.credentials = credentials;
	}

	public DashboardExchangeTradingAsset broker(DashboardTradingAssetBrokerDetails broker) {
		this.broker = broker;
		return this;
	}

	/**
	 * Get broker
	 *
	 * @return broker
	 **/
	@Schema(description = "")
	public DashboardTradingAssetBrokerDetails getBroker() {
		return broker;
	}

	public void setBroker(DashboardTradingAssetBrokerDetails broker) {
		this.broker = broker;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardExchangeTradingAsset dashboardExchangeTradingAsset = (DashboardExchangeTradingAsset) o;
		return Objects.equals(this.credentials, dashboardExchangeTradingAsset.credentials) &&
				Objects.equals(this.broker, dashboardExchangeTradingAsset.broker);
	}

	@Override
	public int hashCode() {
		return Objects.hash(credentials, broker);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardExchangeTradingAsset {\n");

		sb.append("    credentials: ").append(toIndentedString(credentials)).append("\n");
		sb.append("    broker: ").append(toIndentedString(broker)).append("\n");
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
		out.writeValue(credentials);
		out.writeValue(broker);
	}

	public int describeContents() {
		return 0;
	}
}
