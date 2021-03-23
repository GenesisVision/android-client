package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AmountWithCurrency;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.BrokerTradeServerType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

public class CreateProgramModel implements Parcelable
{
	public static final Creator<CreateProgramModel> CREATOR = new Creator<CreateProgramModel>()
	{
		@Override
		public CreateProgramModel createFromParcel(Parcel in) {
			return new CreateProgramModel(in);
		}

		@Override
		public CreateProgramModel[] newArray(int size) {
			return new CreateProgramModel[size];
		}
	};

	private UUID assetId;

	private AssetType assetType;

	private BrokerTradeServerType serverType;

	private Double currentBalance;

	private String currency;

	private List<AmountWithCurrency> minDepositInfo;

	private Boolean isExternal = false;

	private Boolean isExchange = false;

	public CreateProgramModel() {
	}

	public CreateProgramModel(UUID assetId, AssetType assetType, BrokerTradeServerType serverType, Double currentBalance, String currency, Boolean isExternal, Boolean isExchange) {
		this.assetId = assetId;
		this.assetType = assetType;
		this.serverType = serverType;
		this.currentBalance = currentBalance;
		this.currency = currency;
		this.isExternal = isExternal;
		this.isExchange = isExchange;
	}

	protected CreateProgramModel(Parcel in) {
		assetId = (UUID) in.readSerializable();
		assetType = (AssetType) in.readSerializable();
		serverType = (BrokerTradeServerType) in.readSerializable();
		if (in.readByte() == 0) {
			currentBalance = null;
		}
		else {
			currentBalance = in.readDouble();
		}
		currency = in.readString();
		if (in.readByte() == 0) {
			minDepositInfo = null;
		}
		else {
			in.readList(minDepositInfo, AmountWithCurrency.class.getClassLoader());
		}
		isExternal = in.readByte() == 1;
		isExchange = in.readByte() == 1;
	}

	public UUID getAssetId() {
		return assetId;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public BrokerTradeServerType getServerType() {
		return serverType;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public List<AmountWithCurrency> getMinDepositInfo() {
		return minDepositInfo;
	}

	public void setMinDepositInfo(List<AmountWithCurrency> minDepositInfo) {
		this.minDepositInfo = minDepositInfo;
	}

	public Boolean isExternal() {
		return isExternal;
	}

	public Boolean isExchange() {
		return isExchange;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(assetId);
		parcel.writeSerializable(assetType);
		parcel.writeSerializable(serverType);
		if (currentBalance == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(currentBalance);
		}
		parcel.writeString(currency);
		if (minDepositInfo == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeList(minDepositInfo);
		}
		parcel.writeByte(isExternal ? (byte) 1 : (byte) 0);
		parcel.writeByte(isExchange ? (byte) 1 : (byte) 0);
	}
}
