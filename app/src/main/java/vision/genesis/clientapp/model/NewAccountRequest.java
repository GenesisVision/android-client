package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */
public class NewAccountRequest implements Parcelable
{
	public static final Creator<NewAccountRequest> CREATOR = new Creator<NewAccountRequest>()
	{
		@Override
		public NewAccountRequest createFromParcel(Parcel in) {
			return new NewAccountRequest(in);
		}

		@Override
		public NewAccountRequest[] newArray(int size) {
			return new NewAccountRequest[size];
		}
	};

	private Broker broker;

	private String currency;

	private Integer leverage;

	private String depositWalletId;

	private Double depositAmount;

	public NewAccountRequest() {

	}

	protected NewAccountRequest(Parcel in) {
		broker = in.readParcelable(Broker.class.getClassLoader());
		currency = in.readString();
		if (in.readByte() == 0) {
			leverage = null;
		}
		else {
			leverage = in.readInt();
		}
		depositWalletId = in.readString();
		if (in.readByte() == 0) {
			depositAmount = null;
		}
		else {
			depositAmount = in.readDouble();
		}
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getLeverage() {
		return leverage;
	}

	public void setLeverage(Integer leverage) {
		this.leverage = leverage;
	}

	public String getDepositWalletId() {
		return depositWalletId;
	}

	public void setDepositWalletId(String depositWalletId) {
		this.depositWalletId = depositWalletId;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeParcelable(broker, flags);
		parcel.writeString(currency);
		if (leverage == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(leverage);
		}
		parcel.writeString(depositWalletId);
		if (depositAmount == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(depositAmount);
		}
	}
}
