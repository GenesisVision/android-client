package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class ProgramSettingsModel implements Parcelable
{
	public static final Creator<ProgramSettingsModel> CREATOR = new Creator<ProgramSettingsModel>()
	{
		@Override
		public ProgramSettingsModel createFromParcel(Parcel in) {
			return new ProgramSettingsModel(in);
		}

		@Override
		public ProgramSettingsModel[] newArray(int size) {
			return new ProgramSettingsModel[size];
		}
	};

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private Boolean needWarningInfo;

	private String buttonText;

	private String currency;

	private Integer periodLength;

	private Double stopOutLevel;

	private Double investmentLimit;

	private Double entryFee;

	private Double successFee;

	public ProgramSettingsModel() {

	}

	public ProgramSettingsModel(Boolean needStep, String stepNumber, String stepTitle,
	                            Boolean needWarningInfo, String buttonText, String currency,
	                            Integer periodLength, Double stopOutLevel, Double investmentLimit,
	                            Double entryFee, Double successFee) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.needWarningInfo = needWarningInfo;
		this.buttonText = buttonText;
		this.currency = currency;
		this.periodLength = periodLength;
		this.stopOutLevel = stopOutLevel;
		this.investmentLimit = investmentLimit;
		this.entryFee = entryFee;
		this.successFee = successFee;
	}

	protected ProgramSettingsModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		byte tmpNeedWarningInfo = in.readByte();
		needWarningInfo = tmpNeedWarningInfo == 0 ? null : tmpNeedWarningInfo == 1;
		buttonText = in.readString();
		currency = in.readString();
		if (in.readByte() == 0) {
			periodLength = null;
		}
		else {
			periodLength = in.readInt();
		}
		if (in.readByte() == 0) {
			stopOutLevel = null;
		}
		else {
			stopOutLevel = in.readDouble();
		}
		if (in.readByte() == 0) {
			investmentLimit = null;
		}
		else {
			investmentLimit = in.readDouble();
		}
		if (in.readByte() == 0) {
			entryFee = null;
		}
		else {
			entryFee = in.readDouble();
		}
		if (in.readByte() == 0) {
			successFee = null;
		}
		else {
			successFee = in.readDouble();
		}
	}

	public Boolean isNeedStep() {
		return needStep;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public String getStepTitle() {
		return stepTitle;
	}

	public Boolean isNeedWarningInfo() {
		return needWarningInfo;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getCurrency() {
		return currency;
	}

	public Integer getPeriodLength() {
		return periodLength;
	}

	public void setPeriodLength(Integer periodLength) {
		this.periodLength = periodLength;
	}

	public Double getStopOutLevel() {
		return stopOutLevel;
	}

	public void setStopOutLevel(Double stopOutLevel) {
		this.stopOutLevel = stopOutLevel;
	}

	public Double getInvestmentLimit() {
		return investmentLimit;
	}

	public void setInvestmentLimit(Double investmentLimit) {
		this.investmentLimit = investmentLimit;
	}

	public Double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Double entryFee) {
		this.entryFee = entryFee;
	}

	public Double getSuccessFee() {
		return successFee;
	}

	public void setSuccessFee(Double successFee) {
		this.successFee = successFee;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeByte((byte) (needStep == null ? 0 : needStep ? 1 : 2));
		parcel.writeString(stepNumber);
		parcel.writeString(stepTitle);
		parcel.writeByte((byte) (needWarningInfo == null ? 0 : needWarningInfo ? 1 : 2));
		parcel.writeString(buttonText);
		parcel.writeString(currency);
		if (periodLength == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(periodLength);
		}
		if (stopOutLevel == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(stopOutLevel);
		}
		if (investmentLimit == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(investmentLimit);
		}
		if (entryFee == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(entryFee);
		}
		if (successFee == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(successFee);
		}
	}
}
