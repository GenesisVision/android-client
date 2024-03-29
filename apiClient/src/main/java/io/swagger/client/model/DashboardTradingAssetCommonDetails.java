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

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * DashboardTradingAssetCommonDetails
 */


public class DashboardTradingAssetCommonDetails implements Parcelable
{
	public static final Parcelable.Creator<DashboardTradingAssetCommonDetails> CREATOR = new Parcelable.Creator<DashboardTradingAssetCommonDetails>()
	{
		public DashboardTradingAssetCommonDetails createFromParcel(Parcel in) {
			return new DashboardTradingAssetCommonDetails(in);
		}

		public DashboardTradingAssetCommonDetails[] newArray(int size) {
			return new DashboardTradingAssetCommonDetails[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("status")
	private DashboardTradingAssetStatus status = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("originalCurrency")
	private Currency originalCurrency = null;

	@SerializedName("leverage")
	private Integer leverage = null;

	@SerializedName("type")
	private PrivateTradingAccountType type = null;

	@SerializedName("balances")
	private List<AmountWithLogoCurrency> balances = null;

	@SerializedName("supportedCurrencies")
	private List<Currency> supportedCurrencies = null;

	@SerializedName("permissions")
	private List<TradingAccountPermission> permissions = null;

	public DashboardTradingAssetCommonDetails() {
	}

	@SerializedName("login")
	private String login = null;

	public DashboardTradingAssetCommonDetails title(String title) {
		this.title = title;
		return this;
	}

	public DashboardTradingAssetCommonDetails status(DashboardTradingAssetStatus status) {
		this.status = status;
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

	DashboardTradingAssetCommonDetails(Parcel in) {
		title = (String) in.readValue(null);
		status = (DashboardTradingAssetStatus) in.readValue(DashboardTradingAssetStatus.class.getClassLoader());
		creationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		balance = (Double) in.readValue(null);
		login = (String) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		originalCurrency = (Currency) in.readValue(Currency.class.getClassLoader());
		leverage = (Integer) in.readValue(null);
		type = (PrivateTradingAccountType) in.readValue(PrivateTradingAccountType.class.getClassLoader());
		balances = (List<AmountWithLogoCurrency>) in.readValue(AmountWithLogoCurrency.class.getClassLoader());
		supportedCurrencies = (List<Currency>) in.readValue(Currency.class.getClassLoader());
		permissions = (List<TradingAccountPermission>) in.readValue(TradingAccountPermission.class.getClassLoader());
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DashboardTradingAssetCommonDetails creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@Schema(description = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DashboardTradingAssetCommonDetails balance(Double balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @Schema(description = "")
  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public DashboardTradingAssetCommonDetails login(String login) {
    this.login = login;
    return this;
  }

   /**
   * Get login
   * @return login
  **/
  @Schema(description = "")
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public DashboardTradingAssetCommonDetails currency(Currency currency) {
    this.currency = currency;
    return this;
  }

   /**
   * Get currency
   * @return currency
  **/
  @Schema(description = "")
  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public DashboardTradingAssetCommonDetails originalCurrency(Currency originalCurrency) {
    this.originalCurrency = originalCurrency;
    return this;
  }

   /**
   * Get originalCurrency
   * @return originalCurrency
  **/
  @Schema(description = "")
  public Currency getOriginalCurrency() {
    return originalCurrency;
  }

  public void setOriginalCurrency(Currency originalCurrency) {
    this.originalCurrency = originalCurrency;
  }

  public DashboardTradingAssetCommonDetails leverage(Integer leverage) {
    this.leverage = leverage;
    return this;
  }

   /**
   * Get leverage
   * @return leverage
  **/
  @Schema(description = "")
  public Integer getLeverage() {
    return leverage;
  }

  public void setLeverage(Integer leverage) {
    this.leverage = leverage;
  }

  public DashboardTradingAssetCommonDetails type(PrivateTradingAccountType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public PrivateTradingAccountType getType() {
	  return type;
  }

	public void setType(PrivateTradingAccountType type) {
		this.type = type;
	}

	public DashboardTradingAssetCommonDetails balances(List<AmountWithLogoCurrency> balances) {
		this.balances = balances;
		return this;
	}

	public DashboardTradingAssetCommonDetails addBalancesItem(AmountWithLogoCurrency balancesItem) {
		if (this.balances == null) {
			this.balances = new ArrayList<AmountWithLogoCurrency>();
		}
		this.balances.add(balancesItem);
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public DashboardTradingAssetStatus getStatus() {
		return status;
	}

	public void setStatus(DashboardTradingAssetStatus status) {
		this.status = status;
	}

	/**
	 * Get balances
	 *
	 * @return balances
	 **/
	@Schema(description = "")
	public List<AmountWithLogoCurrency> getBalances() {
		return balances;
	}

	public DashboardTradingAssetCommonDetails addSupportedCurrenciesItem(Currency supportedCurrenciesItem) {
		if (this.supportedCurrencies == null) {
			this.supportedCurrencies = new ArrayList<Currency>();
		}
		this.supportedCurrencies.add(supportedCurrenciesItem);
		return this;
	}

	/**
	 * Get supportedCurrencies
	 *
	 * @return supportedCurrencies
	 **/
	@Schema(description = "")
	public List<Currency> getSupportedCurrencies() {
		return supportedCurrencies;
	}

	public void setSupportedCurrencies(List<Currency> supportedCurrencies) {
		this.supportedCurrencies = supportedCurrencies;
	}

	public DashboardTradingAssetCommonDetails permissions(List<TradingAccountPermission> permissions) {
		this.permissions = permissions;
		return this;
	}

	public DashboardTradingAssetCommonDetails addPermissionsItem(TradingAccountPermission permissionsItem) {
		if (this.permissions == null) {
			this.permissions = new ArrayList<TradingAccountPermission>();
		}
		this.permissions.add(permissionsItem);
		return this;
	}

	/**
	 * Get permissions
	 *
	 * @return permissions
	 **/
	@Schema(description = "")
	public List<TradingAccountPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TradingAccountPermission> permissions) {
		this.permissions = permissions;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardTradingAssetCommonDetails dashboardTradingAssetCommonDetails = (DashboardTradingAssetCommonDetails) o;
		return Objects.equals(this.title, dashboardTradingAssetCommonDetails.title) &&
				Objects.equals(this.status, dashboardTradingAssetCommonDetails.status) &&
				Objects.equals(this.creationDate, dashboardTradingAssetCommonDetails.creationDate) &&
				Objects.equals(this.balance, dashboardTradingAssetCommonDetails.balance) &&
				Objects.equals(this.login, dashboardTradingAssetCommonDetails.login) &&
				Objects.equals(this.currency, dashboardTradingAssetCommonDetails.currency) &&
				Objects.equals(this.originalCurrency, dashboardTradingAssetCommonDetails.originalCurrency) &&
				Objects.equals(this.leverage, dashboardTradingAssetCommonDetails.leverage) &&
				Objects.equals(this.type, dashboardTradingAssetCommonDetails.type) &&
				Objects.equals(this.balances, dashboardTradingAssetCommonDetails.balances) &&
				Objects.equals(this.supportedCurrencies, dashboardTradingAssetCommonDetails.supportedCurrencies) &&
				Objects.equals(this.permissions, dashboardTradingAssetCommonDetails.permissions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, status, creationDate, balance, login, currency, originalCurrency, leverage, type, balances, supportedCurrencies, permissions);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardTradingAssetCommonDetails {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    originalCurrency: ").append(toIndentedString(originalCurrency)).append("\n");
		sb.append("    leverage: ").append(toIndentedString(leverage)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    balances: ").append(toIndentedString(balances)).append("\n");
		sb.append("    supportedCurrencies: ").append(toIndentedString(supportedCurrencies)).append("\n");
		sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public void setBalances(List<AmountWithLogoCurrency> balances) {
		this.balances = balances;
	}

	public DashboardTradingAssetCommonDetails supportedCurrencies(List<Currency> supportedCurrencies) {
		this.supportedCurrencies = supportedCurrencies;
		return this;
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
		out.writeValue(status);
		out.writeValue(creationDate);
		out.writeValue(balance);
		out.writeValue(login);
		out.writeValue(currency);
		out.writeValue(originalCurrency);
		out.writeValue(leverage);
		out.writeValue(type);
		out.writeValue(balances);
		out.writeValue(supportedCurrencies);
		out.writeValue(permissions);
	}

	public int describeContents() {
		return 0;
	}
}
