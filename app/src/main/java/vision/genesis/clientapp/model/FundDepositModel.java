package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */
public class FundDepositModel implements Parcelable
{
	public static final Creator<FundDepositModel> CREATOR = new Creator<FundDepositModel>()
	{
		@Override
		public FundDepositModel createFromParcel(Parcel in) {
			return new FundDepositModel(in);
		}

		@Override
		public FundDepositModel[] newArray(int size) {
			return new FundDepositModel[size];
		}
	};

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private String buttonText;

	public FundDepositModel(Boolean needStep, String stepNumber, String stepTitle, String buttonText) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.buttonText = buttonText;
	}

	protected FundDepositModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		buttonText = in.readString();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte((byte) (needStep == null ? 0 : needStep ? 1 : 2));
		dest.writeString(stepNumber);
		dest.writeString(stepTitle);
		dest.writeString(buttonText);
	}
}
