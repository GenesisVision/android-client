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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * NewFundRequest
 */


public class NewFundRequest implements Parcelable
{
	public static final Parcelable.Creator<NewFundRequest> CREATOR = new Parcelable.Creator<NewFundRequest>()
	{
		public NewFundRequest createFromParcel(Parcel in) {
			return new NewFundRequest(in);
		}

		public NewFundRequest[] newArray(int size) {
			return new NewFundRequest[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("assets")
	private List<FundAssetPart> assets = null;

	@SerializedName("entryFee")
	private Double entryFee = null;

	@SerializedName("exitFee")
	private Double exitFee = null;

	@SerializedName("depositAmount")
	private Double depositAmount = null;

	@SerializedName("depositWalletId")
	private UUID depositWalletId = null;

	public NewFundRequest() {
	}

	NewFundRequest(Parcel in) {
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		logo = (String) in.readValue(null);
		assets = (List<FundAssetPart>) in.readValue(FundAssetPart.class.getClassLoader());
		entryFee = (Double) in.readValue(null);
		exitFee = (Double) in.readValue(null);
		depositAmount = (Double) in.readValue(null);
		depositWalletId = (UUID) in.readValue(UUID.class.getClassLoader());
	}

	public NewFundRequest title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NewFundRequest description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@Schema(description = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NewFundRequest logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public NewFundRequest assets(List<FundAssetPart> assets) {
		this.assets = assets;
		return this;
	}

	public NewFundRequest addAssetsItem(FundAssetPart assetsItem) {
		if (this.assets == null) {
			this.assets = new ArrayList<FundAssetPart>();
		}
		this.assets.add(assetsItem);
		return this;
	}

	/**
	 * Get assets
	 *
	 * @return assets
	 **/
	@Schema(description = "")
	public List<FundAssetPart> getAssets() {
		return assets;
	}

	public void setAssets(List<FundAssetPart> assets) {
		this.assets = assets;
	}

	public NewFundRequest entryFee(Double entryFee) {
		this.entryFee = entryFee;
		return this;
	}

	/**
	 * Get entryFee
	 *
	 * @return entryFee
	 **/
	@Schema(description = "")
	public Double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Double entryFee) {
		this.entryFee = entryFee;
	}

	public NewFundRequest exitFee(Double exitFee) {
		this.exitFee = exitFee;
		return this;
	}

	/**
	 * Get exitFee
	 *
	 * @return exitFee
	 **/
	@Schema(description = "")
	public Double getExitFee() {
		return exitFee;
	}

	public void setExitFee(Double exitFee) {
		this.exitFee = exitFee;
	}

	public NewFundRequest depositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
		return this;
	}

	/**
	 * Get depositAmount
	 *
	 * @return depositAmount
	 **/
	@Schema(description = "")
	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public NewFundRequest depositWalletId(UUID depositWalletId) {
		this.depositWalletId = depositWalletId;
		return this;
	}

	/**
	 * Get depositWalletId
	 *
	 * @return depositWalletId
	 **/
	@Schema(description = "")
	public UUID getDepositWalletId() {
		return depositWalletId;
	}

	public void setDepositWalletId(UUID depositWalletId) {
		this.depositWalletId = depositWalletId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NewFundRequest newFundRequest = (NewFundRequest) o;
		return Objects.equals(this.title, newFundRequest.title) &&
				Objects.equals(this.description, newFundRequest.description) &&
				Objects.equals(this.logo, newFundRequest.logo) &&
				Objects.equals(this.assets, newFundRequest.assets) &&
				Objects.equals(this.entryFee, newFundRequest.entryFee) &&
				Objects.equals(this.exitFee, newFundRequest.exitFee) &&
				Objects.equals(this.depositAmount, newFundRequest.depositAmount) &&
				Objects.equals(this.depositWalletId, newFundRequest.depositWalletId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, logo, assets, entryFee, exitFee, depositAmount, depositWalletId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NewFundRequest {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
		sb.append("    entryFee: ").append(toIndentedString(entryFee)).append("\n");
		sb.append("    exitFee: ").append(toIndentedString(exitFee)).append("\n");
		sb.append("    depositAmount: ").append(toIndentedString(depositAmount)).append("\n");
		sb.append("    depositWalletId: ").append(toIndentedString(depositWalletId)).append("\n");
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
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(logo);
		out.writeValue(assets);
		out.writeValue(entryFee);
		out.writeValue(exitFee);
		out.writeValue(depositAmount);
		out.writeValue(depositWalletId);
	}

	public int describeContents() {
		return 0;
	}
}
