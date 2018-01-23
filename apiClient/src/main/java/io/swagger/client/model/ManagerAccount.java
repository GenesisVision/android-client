/*
 * Core API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * ManagerAccount
 */

public class ManagerAccount
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("currency")
	private String currency = null;

	@SerializedName("ipfsHash")
	private String ipfsHash = null;

	@SerializedName("isConfirmed")
	private Boolean isConfirmed = null;

	@SerializedName("brokerTradeServer")
	private BrokerTradeServer brokerTradeServer = null;

	public ManagerAccount id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ManagerAccount login(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Get login
	 *
	 * @return login
	 **/
	@ApiModelProperty(value = "")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ManagerAccount currency(String currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@ApiModelProperty(value = "")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ManagerAccount ipfsHash(String ipfsHash) {
		this.ipfsHash = ipfsHash;
		return this;
	}

	/**
	 * Get ipfsHash
	 *
	 * @return ipfsHash
	 **/
	@ApiModelProperty(value = "")
	public String getIpfsHash() {
		return ipfsHash;
	}

	public void setIpfsHash(String ipfsHash) {
		this.ipfsHash = ipfsHash;
	}

	public ManagerAccount isConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
		return this;
	}

	/**
	 * Get isConfirmed
	 *
	 * @return isConfirmed
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public ManagerAccount brokerTradeServer(BrokerTradeServer brokerTradeServer) {
		this.brokerTradeServer = brokerTradeServer;
		return this;
	}

	/**
	 * Get brokerTradeServer
	 *
	 * @return brokerTradeServer
	 **/
	@ApiModelProperty(value = "")
	public BrokerTradeServer getBrokerTradeServer() {
		return brokerTradeServer;
	}

	public void setBrokerTradeServer(BrokerTradeServer brokerTradeServer) {
		this.brokerTradeServer = brokerTradeServer;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ManagerAccount managerAccount = (ManagerAccount) o;
		return Objects.equals(this.id, managerAccount.id) &&
				Objects.equals(this.login, managerAccount.login) &&
				Objects.equals(this.currency, managerAccount.currency) &&
				Objects.equals(this.ipfsHash, managerAccount.ipfsHash) &&
				Objects.equals(this.isConfirmed, managerAccount.isConfirmed) &&
				Objects.equals(this.brokerTradeServer, managerAccount.brokerTradeServer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, currency, ipfsHash, isConfirmed, brokerTradeServer);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ManagerAccount {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    ipfsHash: ").append(toIndentedString(ipfsHash)).append("\n");
		sb.append("    isConfirmed: ").append(toIndentedString(isConfirmed)).append("\n");
		sb.append("    brokerTradeServer: ").append(toIndentedString(brokerTradeServer)).append("\n");
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

}
