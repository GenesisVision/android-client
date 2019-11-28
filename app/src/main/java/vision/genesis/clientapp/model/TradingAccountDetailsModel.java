package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

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

	private String brokerLogo;

	public TradingAccountDetailsModel(UUID accountId, String accountName, String brokerLogo) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.brokerLogo = brokerLogo;
	}

	protected TradingAccountDetailsModel(Parcel in) {
		accountId = (UUID) in.readSerializable();
		accountName = in.readString();
		brokerLogo = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(accountId);
		parcel.writeString(accountName);
		parcel.writeString(brokerLogo);
	}

	public UUID getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getBrokerLogo() {
		return brokerLogo;
	}
}
