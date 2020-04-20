package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/02/2019.
 */
public class WalletModel implements Parcelable
{
	public static final Creator<WalletModel> CREATOR = new Creator<WalletModel>()
	{
		@Override
		public WalletModel createFromParcel(Parcel in) {
			return new WalletModel(in);
		}

		@Override
		public WalletModel[] newArray(int size) {
			return new WalletModel[size];
		}
	};

	public static WalletModel createFrom(WalletData walletData) {
		return new WalletModel(walletData.getId(), walletData.getLogoUrl(), walletData.getTitle(),
				walletData.getCurrency().getValue(), walletData.getAvailable(), walletData.getDepositAddress());
	}

	private UUID id;

	private String logo;

	private String title;

	private String currency;

	private Double available;

	private String address;

	private WalletModel(Parcel in) {
		id = (UUID) in.readSerializable();
		logo = in.readString();
		title = in.readString();
		currency = in.readString();
		available = in.readDouble();
		address = in.readString();
	}

	private WalletModel(UUID id, String logo, String title, String currency, Double available, String address) {
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.currency = currency;
		this.available = available;
		this.address = address;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAvailable() {
		return available;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(id);
		parcel.writeString(logo);
		parcel.writeString(title);
		parcel.writeString(currency);
		parcel.writeDouble(available);
		parcel.writeString(address);
	}

	public String getAddress() {
		return address;
	}
}
