package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

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

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private String buttonText;

	private String mode = SubscriptionMode.BYBALANCE.getValue();

	private Double percent = 0.0;

	private Double tolerancePercent = 0.0;

	private Double fixedVolume = 0.0;

	private String fixedCurrency = Currency.USD.getValue();

	private String initialDepositCurrency = Currency.GVT.getValue();

	private Double initialDepositAmount = 0.0;

	private String minDepositCurrency = Currency.GVT.getValue();

	private Double minDeposit = 0.0;

	public SubscriptionSettingsModel() {

	}

	public SubscriptionSettingsModel(Boolean needStep, String stepNumber, String stepTitle, String buttonText,
	                                 String mode, Double percent, Double tolerancePercent, Double fixedVolume, String fixedCurrency) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.buttonText = buttonText;

		this.mode = mode;
		this.percent = percent;
		this.tolerancePercent = tolerancePercent;
		this.fixedVolume = fixedVolume;
		this.fixedCurrency = fixedCurrency;
	}

	protected SubscriptionSettingsModel(Parcel in) {
		needStep = in.readByte() != 0;
		stepNumber = in.readString();
		stepTitle = in.readString();
		buttonText = in.readString();

		mode = in.readString();
		if (in.readByte() == 0) {
			percent = null;
		}
		else {
			percent = in.readDouble();
		}
		if (in.readByte() == 0) {
			tolerancePercent = null;
		}
		else {
			tolerancePercent = in.readDouble();
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
		minDepositCurrency = in.readString();
		if (in.readByte() == 0) {
			minDeposit = null;
		}
		else {
			minDeposit = in.readDouble();
		}
	}

	public boolean isNeedStep() {
		return needStep;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public String getStepTitle() {
		return stepTitle;
	}

	public String getButtonText() {
		return buttonText;
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

	public Double getTolerancePercent() {
		return tolerancePercent;
	}

	public void setTolerancePercent(Double tolerancePercent) {
		this.tolerancePercent = tolerancePercent;
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
		parcel.writeByte((byte) (needStep ? 1 : 0));
		parcel.writeString(stepNumber);
		parcel.writeString(stepTitle);
		parcel.writeString(buttonText);

		parcel.writeString(mode);
		if (percent == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(percent);
		}
		if (tolerancePercent == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(tolerancePercent);
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
		model.setOpenTolerancePercent(getTolerancePercent());
		model.setFixedVolume(getFixedVolume());
		model.setFixedCurrency(Currency.fromValue(getFixedCurrency()));
		return model;
	}
}
