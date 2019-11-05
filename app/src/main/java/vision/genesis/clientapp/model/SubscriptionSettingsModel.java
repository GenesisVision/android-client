package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.Currency;
import io.swagger.client.model.SubscriptionMode;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/06/2019.
 */
public class SubscriptionSettingsModel implements Parcelable
{
	public static final Double VOLUME_PERCENTAGE_MIN = 1.0;

	public static final Double VOLUME_PERCENTAGE_MAX = 999.0;

	public static final Double EQUIVALENT_MIN = 0.1;

	public static final Double EQUIVALENT_MAX = 99999.0;

	public static final Double TOLERANCE_PERCENTAGE_MIN = 0.01;

	public static final Double TOLERANCE_PERCENTAGE_MAX = 20.0;

	public static final String EQUIVALENT_CURRENCY = "USD";

	public static final Creator<SubscriptionSettingsModel> CREATOR = new Creator<SubscriptionSettingsModel>()
	{
		@Override
		public SubscriptionSettingsModel createFromParcel(Parcel in) {
			return new SubscriptionSettingsModel(in);
		}

		@Override
		public SubscriptionSettingsModel[] newArray(int size) {
			return new SubscriptionSettingsModel[size];
		}
	};

	private String mode = SubscriptionMode.BYBALANCE.getValue();

	private Double percent = 0.0;

	private Double openTolerancePercent = 0.0;

	private Double fixedVolume = 0.0;

	private String fixedCurrency = AttachToSignalProvider.FixedCurrencyEnum.USD.getValue();

	private String initialDepositCurrency = AttachToSignalProvider.FixedCurrencyEnum.GVT.getValue();

	private Double initialDepositAmount = 0.0;

	private String programName;

	private UUID programId;

	private String minDepositCurrency = Currency.GVT.getValue();

	private Double minDeposit = 0.0;

	public SubscriptionSettingsModel() {

	}

	protected SubscriptionSettingsModel(Parcel in) {
		mode = in.readString();
		if (in.readByte() == 0) {
			percent = null;
		}
		else {
			percent = in.readDouble();
		}
		if (in.readByte() == 0) {
			openTolerancePercent = null;
		}
		else {
			openTolerancePercent = in.readDouble();
		}
		if (in.readByte() == 0) {
			fixedVolume = null;
		}
		else {
			fixedVolume = in.readDouble();
		}
		fixedCurrency = in.readString();
		initialDepositCurrency = in.readString();
		if (in.readByte() == 0) {
			initialDepositAmount = null;
		}
		else {
			initialDepositAmount = in.readDouble();
		}
		programName = in.readString();
		programId = (UUID) in.readSerializable();
		minDepositCurrency = in.readString();
		if (in.readByte() == 0) {
			minDeposit = null;
		}
		else {
			minDeposit = in.readDouble();
		}
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getOpenTolerancePercent() {
		return openTolerancePercent;
	}

	public void setOpenTolerancePercent(Double openTolerancePercent) {
		this.openTolerancePercent = openTolerancePercent;
	}

	public Double getFixedVolume() {
		return fixedVolume;
	}

	public void setFixedVolume(Double fixedVolume) {
		this.fixedVolume = fixedVolume;
	}

	public String getFixedCurrency() {
		return fixedCurrency;
	}

	public void setFixedCurrency(String fixedCurrency) {
		this.fixedCurrency = fixedCurrency;
	}

	public String getInitialDepositCurrency() {
		return initialDepositCurrency;
	}

	public void setInitialDepositCurrency(String initialDepositCurrency) {
		this.initialDepositCurrency = initialDepositCurrency;
	}

	public Double getInitialDepositAmount() {
		return initialDepositAmount;
	}

	public void setInitialDepositAmount(Double initialDepositAmount) {
		this.initialDepositAmount = initialDepositAmount;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public UUID getProgramId() {
		return programId;
	}

	public void setProgramId(UUID programId) {
		this.programId = programId;
	}

	public String getMinDepositCurrency() {
		return minDepositCurrency;
	}

	public void setMinDepositCurrency(String minDepositCurrency) {
		this.minDepositCurrency = minDepositCurrency;
	}

	public Double getMinDeposit() {
		return minDeposit;
	}

	public void setMinDeposit(Double minDeposit) {
		this.minDeposit = minDeposit;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(mode);
		if (percent == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(percent);
		}
		if (openTolerancePercent == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(openTolerancePercent);
		}
		if (fixedVolume == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(fixedVolume);
		}
		parcel.writeString(fixedCurrency);
		parcel.writeString(initialDepositCurrency);
		if (initialDepositAmount == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(initialDepositAmount);
		}
		parcel.writeString(programName);
		parcel.writeSerializable(programId);
		parcel.writeString(minDepositCurrency);
		if (minDeposit == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(minDeposit);
		}
	}

	public AttachToSignalProvider getApiModel() {
		AttachToSignalProvider model = new AttachToSignalProvider();
		model.setMode(SubscriptionMode.fromValue(getMode()));
		model.setPercent(getPercent());
		model.setOpenTolerancePercent(getOpenTolerancePercent());
		model.setFixedVolume(getFixedVolume());
		model.setFixedCurrency(AttachToSignalProvider.FixedCurrencyEnum.fromValue(getFixedCurrency()));
//		model.setInitialDepositCurrency(AttachToSignalProvider.InitialDepositCurrencyEnum.fromValue(getInitialDepositCurrency()));
//		model.setInitialDepositAmount(getInitialDepositAmount());
		return model;
	}

}
