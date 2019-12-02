package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class PublicInfoModel implements Parcelable
{
	public static final Creator<PublicInfoModel> CREATOR = new Creator<PublicInfoModel>()
	{
		@Override
		public PublicInfoModel createFromParcel(Parcel in) {
			return new PublicInfoModel(in);
		}

		@Override
		public PublicInfoModel[] newArray(int size) {
			return new PublicInfoModel[size];
		}
	};

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private Boolean needWarningInfo;

	private String buttonText;

	private String title;

	private String description;

	private String logo;

	public PublicInfoModel(Boolean needStep, String stepNumber, String stepTitle,
	                       Boolean needWarningInfo, String buttonText,
	                       String title, String description, String logo) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.needWarningInfo = needWarningInfo;
		this.buttonText = buttonText;
		this.title = title;
		this.description = description;
		this.logo = logo;
	}

	protected PublicInfoModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		byte tmpNeedWarningInfo = in.readByte();
		needWarningInfo = tmpNeedWarningInfo == 0 ? null : tmpNeedWarningInfo == 1;
		buttonText = in.readString();
		title = in.readString();
		description = in.readString();
		logo = in.readString();
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

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLogo() {
		return logo;
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
		parcel.writeString(title);
		parcel.writeString(description);
		parcel.writeString(logo);
	}
}
