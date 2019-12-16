package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */

public class FundSettingsModel implements Parcelable
{
	public static final Creator<FundSettingsModel> CREATOR = new Creator<FundSettingsModel>()
	{
		@Override
		public FundSettingsModel createFromParcel(Parcel in) {
			return new FundSettingsModel(in);
		}

		@Override
		public FundSettingsModel[] newArray(int size) {
			return new FundSettingsModel[size];
		}
	};

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private String buttonText;

	private Double entryFee;

	private Double exitFee;

	private Boolean isNew;

	public FundSettingsModel() {

	}

	public FundSettingsModel(Boolean needStep, String stepNumber, String stepTitle, String buttonText,
	                         Double entryFee, Double exitFee, Boolean isNew) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.buttonText = buttonText;
		this.entryFee = entryFee;
		this.exitFee = exitFee;
		this.isNew = isNew;
	}

	protected FundSettingsModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		buttonText = in.readString();
		if (in.readByte() == 0) {
			entryFee = null;
		}
		else {
			entryFee = in.readDouble();
		}
		if (in.readByte() == 0) {
			exitFee = null;
		}
		else {
			exitFee = in.readDouble();
		}
		byte tmpIsNew = in.readByte();
		isNew = tmpIsNew == 0 ? null : tmpIsNew == 1;
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

	public String getButtonText() {
		return buttonText;
	}

	public Double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Double entryFee) {
		this.entryFee = entryFee;
	}

	public Double getExitFee() {
		return exitFee;
	}

	public void setExitFee(Double exitFee) {
		this.exitFee = exitFee;
	}

	public Boolean isNew() {
		return isNew;
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
		parcel.writeString(buttonText);
		if (entryFee == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(entryFee);
		}
		if (exitFee == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(exitFee);
		}
		parcel.writeByte((byte) (isNew == null ? 0 : isNew ? 1 : 2));
	}
}
