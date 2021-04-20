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
 * ExchangeInfo
 */


public class ExchangeInfo implements Parcelable
{
	public static final Parcelable.Creator<ExchangeInfo> CREATOR = new Parcelable.Creator<ExchangeInfo>()
	{
		public ExchangeInfo createFromParcel(Parcel in) {
			return new ExchangeInfo(in);
		}

		public ExchangeInfo[] newArray(int size) {
			return new ExchangeInfo[size];
		}
	};

	@SerializedName("name")
	private String name = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("terms")
	private String terms = null;

	@SerializedName("assets")
	private String assets = null;

	@SerializedName("fee")
	private Double fee = null;

	@SerializedName("isKycRequired")
	private Boolean isKycRequired = null;

	@SerializedName("accountTypes")
	private List<ExchangeAccountType> accountTypes = null;

	@SerializedName("tags")
	private List<Tag> tags = null;

	public ExchangeInfo() {
	}

	ExchangeInfo(Parcel in) {
		name = (String) in.readValue(null);
		description = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		terms = (String) in.readValue(null);
		assets = (String) in.readValue(null);
		fee = (Double) in.readValue(null);
		isKycRequired = (Boolean) in.readValue(null);
		accountTypes = (List<ExchangeAccountType>) in.readValue(ExchangeAccountType.class.getClassLoader());
		tags = (List<Tag>) in.readValue(Tag.class.getClassLoader());
	}

	public ExchangeInfo name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@Schema(description = "")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExchangeInfo description(String description) {
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

	public ExchangeInfo logoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		return this;
	}

	/**
	 * Get logoUrl
	 *
	 * @return logoUrl
	 **/
	@Schema(description = "")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public ExchangeInfo terms(String terms) {
		this.terms = terms;
		return this;
	}

	/**
	 * Get terms
	 *
	 * @return terms
	 **/
	@Schema(description = "")
	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public ExchangeInfo assets(String assets) {
		this.assets = assets;
		return this;
	}

	/**
	 * Get assets
	 *
	 * @return assets
	 **/
	@Schema(description = "")
	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public ExchangeInfo fee(Double fee) {
		this.fee = fee;
		return this;
	}

	/**
	 * Get fee
	 *
	 * @return fee
	 **/
	@Schema(description = "")
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * Get isKycRequired
	 *
	 * @return isKycRequired
	 **/
	@Schema(description = "")
	public Boolean isIsKycRequired() {
		return isKycRequired;
	}

	public ExchangeInfo accountTypes(List<ExchangeAccountType> accountTypes) {
		this.accountTypes = accountTypes;
		return this;
	}

	public ExchangeInfo addAccountTypesItem(ExchangeAccountType accountTypesItem) {
		if (this.accountTypes == null) {
			this.accountTypes = new ArrayList<ExchangeAccountType>();
		}
		this.accountTypes.add(accountTypesItem);
		return this;
	}

	/**
	 * Get accountTypes
	 *
	 * @return accountTypes
	 **/
	@Schema(description = "")
	public List<ExchangeAccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<ExchangeAccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}

	public ExchangeInfo tags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public ExchangeInfo addTagsItem(Tag tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<Tag>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	/**
	 * Get tags
	 *
	 * @return tags
	 **/
	@Schema(description = "")
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExchangeInfo exchangeInfo = (ExchangeInfo) o;
		return Objects.equals(this.name, exchangeInfo.name) &&
				Objects.equals(this.description, exchangeInfo.description) &&
				Objects.equals(this.logoUrl, exchangeInfo.logoUrl) &&
				Objects.equals(this.terms, exchangeInfo.terms) &&
				Objects.equals(this.assets, exchangeInfo.assets) &&
				Objects.equals(this.fee, exchangeInfo.fee) &&
				Objects.equals(this.isKycRequired, exchangeInfo.isKycRequired) &&
				Objects.equals(this.accountTypes, exchangeInfo.accountTypes) &&
				Objects.equals(this.tags, exchangeInfo.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, logoUrl, terms, assets, fee, isKycRequired, accountTypes, tags);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExchangeInfo {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    terms: ").append(toIndentedString(terms)).append("\n");
		sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
		sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
		sb.append("    isKycRequired: ").append(toIndentedString(isKycRequired)).append("\n");
		sb.append("    accountTypes: ").append(toIndentedString(accountTypes)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
		out.writeValue(name);
		out.writeValue(description);
		out.writeValue(logoUrl);
		out.writeValue(terms);
		out.writeValue(assets);
		out.writeValue(fee);
		out.writeValue(isKycRequired);
		out.writeValue(accountTypes);
		out.writeValue(tags);
	}

	public int describeContents() {
		return 0;
	}
}
