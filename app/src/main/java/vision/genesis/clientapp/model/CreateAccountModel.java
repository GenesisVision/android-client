package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.swagger.client.model.BrokerDetails;
import io.swagger.client.model.Currency;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/11/2020.
 */
public class CreateAccountModel implements Parcelable
{
	public static final Creator<CreateAccountModel> CREATOR = new Creator<CreateAccountModel>()
	{
		@Override
		public CreateAccountModel createFromParcel(Parcel in) {
			return new CreateAccountModel(in);
		}

		@Override
		public CreateAccountModel[] newArray(int size) {
			return new CreateAccountModel[size];
		}
	};

	private BrokerDetails broker;

	private Currency currency;

	private Integer leverage;

	public CreateAccountModel(BrokerDetails broker, Currency currency, Integer leverage) {
		this.broker = broker;
		this.currency = currency;
		this.leverage = leverage;
	}

	protected CreateAccountModel(Parcel in) {
		broker = in.readParcelable(BrokerDetails.class.getClassLoader());
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		if (in.readByte() == 0) {
			leverage = null;
		}
		else {
			leverage = in.readInt();
		}
	}

	public BrokerDetails getBroker() {
		return broker;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Integer getLeverage() {
		return leverage;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(broker, flags);
		dest.writeValue(currency);
		if (leverage == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(leverage);
		}
	}
}
