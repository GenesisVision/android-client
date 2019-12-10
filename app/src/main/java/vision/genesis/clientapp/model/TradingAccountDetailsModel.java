package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.MigrationRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountDetailsModel implements Parcelable
{
	public static final Creator<TradingAccountDetailsModel> CREATOR = new Creator<TradingAccountDetailsModel>()
	{
		@Override
		public TradingAccountDetailsModel createFromParcel(Parcel in) {
			return new TradingAccountDetailsModel(in);
		}

		@Override
		public TradingAccountDetailsModel[] newArray(int size) {
			return new TradingAccountDetailsModel[size];
		}
	};

	private UUID accountId;

	private String accountName;

	private String brokerName;

	private String brokerLogo;

	private DateTime creationDate;

	private Integer leverage;

	private String currency;

	private MigrationRequest migration;


	public TradingAccountDetailsModel(UUID accountId, String accountName, String brokerLogo) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.brokerLogo = brokerLogo;
	}

	public TradingAccountDetailsModel(UUID accountId, String accountName, String brokerName, String brokerLogo, DateTime creationDate,
	                                  Integer leverage, String currency, MigrationRequest migration) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.brokerName = brokerName;
		this.brokerLogo = brokerLogo;
		this.creationDate = creationDate;
		this.leverage = leverage;
		this.currency = currency;
		this.migration = migration;
	}

	protected TradingAccountDetailsModel(Parcel in) {
		accountId = (UUID) in.readSerializable();
		accountName = in.readString();
		brokerName = in.readString();
		brokerLogo = in.readString();
		creationDate = (DateTime) in.readSerializable();
		if (in.readByte() == 0) {
			leverage = null;
		}
		else {
			leverage = in.readInt();
		}
		currency = in.readString();
		migration = in.readParcelable(MigrationRequest.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeSerializable(accountId);
		parcel.writeString(accountName);
		parcel.writeString(brokerName);
		parcel.writeString(brokerLogo);
		parcel.writeSerializable(creationDate);
		if (leverage == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(leverage);
		}
		parcel.writeString(currency);
		parcel.writeParcelable(migration, flags);
	}

	public UUID getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public String getBrokerLogo() {
		return brokerLogo;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public Integer getLeverage() {
		return leverage;
	}

	public String getCurrency() {
		return currency;
	}

	public MigrationRequest getMigration() {
		return migration;
	}

	public void setMigration(MigrationRequest migration) {
		this.migration = migration;
	}
}
