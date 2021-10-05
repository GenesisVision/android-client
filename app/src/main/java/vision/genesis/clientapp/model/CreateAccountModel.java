package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

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

	private UUID brokerId;

	private String logoUrl;

	private Currency currency;

	private Integer leverage;

	public CreateAccountModel(UUID brokerId, String logoUrl, Currency currency, Integer leverage) {
		this.brokerId = brokerId;
		this.logoUrl = logoUrl;
		this.currency = currency;
		this.leverage = leverage;
	}

	protected CreateAccountModel(Parcel in) {
		brokerId = (UUID) in.readSerializable();
		logoUrl = in.readString();
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		if (in.readByte() == 0) {
			leverage = null;
		}
		else {
			leverage = in.readInt();
		}
	}

	public UUID getBrokerId() {
		return brokerId;
	}

	public String getLogoUrl() {
		return logoUrl;
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
		dest.writeSerializable(brokerId);
		dest.writeString(logoUrl);
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
