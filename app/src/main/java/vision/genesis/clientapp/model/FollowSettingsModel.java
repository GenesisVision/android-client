package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/12/2019.
 */

public class FollowSettingsModel implements Parcelable
{
	public static final Creator<FollowSettingsModel> CREATOR = new Creator<FollowSettingsModel>()
	{
		@Override
		public FollowSettingsModel createFromParcel(Parcel in) {
			return new FollowSettingsModel(in);
		}

		@Override
		public FollowSettingsModel[] newArray(int size) {
			return new FollowSettingsModel[size];
		}
	};

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private String buttonText;

	private Double volumeFee;

	private Double successFee;

	public FollowSettingsModel() {

	}

	public FollowSettingsModel(Boolean needStep, String stepNumber, String stepTitle,
	                           String buttonText, Double volumeFee, Double successFee) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.buttonText = buttonText;
		this.volumeFee = volumeFee;
		this.successFee = successFee;
	}

	protected FollowSettingsModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		buttonText = in.readString();
		if (in.readByte() == 0) {
			volumeFee = null;
		}
		else {
			volumeFee = in.readDouble();
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

	public String getButtonText() {
		return buttonText;
	}

	public Double getVolumeFee() {
		return volumeFee;
	}

	public void setVolumeFee(Double volumeFee) {
		this.volumeFee = volumeFee;
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
		parcel.writeString(buttonText);
		if (volumeFee == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(volumeFee);
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
