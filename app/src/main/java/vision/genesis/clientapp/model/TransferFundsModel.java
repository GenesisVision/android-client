package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.ProgramFollowDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 09/12/2019.
 */

public class TransferFundsModel implements Parcelable
{
	public static final Creator<TransferFundsModel> CREATOR = new Creator<TransferFundsModel>()
	{
		@Override
		public TransferFundsModel createFromParcel(Parcel in) {
			return new TransferFundsModel(in);
		}

		@Override
		public TransferFundsModel[] newArray(int size) {
			return new TransferFundsModel[size];
		}
	};

	public static TransferFundsModel createFrom(WalletModel model) {
		return new TransferFundsModel(model.getId(), model.getLogo(), model.getTitle(),
				model.getCurrency(), model.getAvailable());
	}

	public static TransferFundsModel createFrom(PrivateTradingAccountFull account) {
		return new TransferFundsModel(account.getId(), account.getBrokerDetails().getLogo(), account.getPublicInfo().getTitle(),
				account.getTradingAccountInfo().getCurrency().getValue(), account.getTradingAccountInfo().getBalance());
	}

	public static TransferFundsModel createFromFollow(ProgramFollowDetailsFull follow) {
		TransferFundsModel model = new TransferFundsModel(follow.getId(), follow.getPublicInfo().getLogo(), follow.getPublicInfo().getTitle(),
				follow.getTradingAccountInfo().getCurrency().getValue(), follow.getTradingAccountInfo().getBalance());
		model.setColor(follow.getPublicInfo().getColor());
		return model;
	}

	private UUID id;

	private String logo;

	private String color;

	private String title;

	private String currency;

	private Double available;

	private TransferDirection transferDirection;

	private InternalTransferRequestType assetType;

	private TransferFundsModel(Parcel in) {
		id = (UUID) in.readSerializable();
		logo = in.readString();
		color = in.readString();
		title = in.readString();
		currency = in.readString();
		available = in.readDouble();
		transferDirection = (TransferDirection) in.readSerializable();
		assetType = (InternalTransferRequestType) in.readSerializable();
	}

	private TransferFundsModel(UUID id, String logo, String title, String currency, Double available) {
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.currency = currency;
		this.available = available;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public TransferDirection getTransferDirection() {
		return transferDirection;
	}

	public void setTransferDirection(TransferDirection transferDirection) {
		this.transferDirection = transferDirection;
	}

	public InternalTransferRequestType getAssetType() {
		return assetType;
	}

	public void setAssetType(InternalTransferRequestType assetType) {
		this.assetType = assetType;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(id);
		parcel.writeString(logo);
		parcel.writeString(color);
		parcel.writeString(title);
		parcel.writeString(currency);
		parcel.writeDouble(available);
		parcel.writeSerializable(transferDirection);
		parcel.writeSerializable(assetType);
	}

	public enum TransferDirection
	{
		DEPOSIT(),
		WITHDRAW()
	}
}
