package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.swagger.client.model.FundAssetInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */

public class FundAssetsModel implements Parcelable
{
	public static final Creator<FundAssetsModel> CREATOR = new Creator<FundAssetsModel>()
	{
		@Override
		public FundAssetsModel createFromParcel(Parcel in) {
			return new FundAssetsModel(in);
		}

		@Override
		public FundAssetsModel[] newArray(int size) {
			return new FundAssetsModel[size];
		}
	};

	private ArrayList<FundAssetInfo> assets;

	private Boolean needStep;

	private String stepNumber;

	private String stepTitle;

	private String reallocationInfo;

	private String buttonText;

	private Boolean isNew;

	public FundAssetsModel() {

	}

	public FundAssetsModel(Boolean needStep, String stepNumber, String stepTitle,
	                       String reallocationInfo, String buttonText,
	                       ArrayList<FundAssetInfo> assets,
	                       Boolean isNew) {
		this.needStep = needStep;
		this.stepNumber = stepNumber;
		this.stepTitle = stepTitle;
		this.reallocationInfo = reallocationInfo;
		this.buttonText = buttonText;
		this.assets = assets;
		this.isNew = isNew;
	}

	protected FundAssetsModel(Parcel in) {
		byte tmpNeedStep = in.readByte();
		needStep = tmpNeedStep == 0 ? null : tmpNeedStep == 1;
		stepNumber = in.readString();
		stepTitle = in.readString();
		reallocationInfo = in.readString();
		buttonText = in.readString();
		byte tmpIsNew = in.readByte();
		Parcelable[] assetsParcelable = in.readParcelableArray(FundAssetInfo.class.getClassLoader());
		assets = new ArrayList<>();
		if (assetsParcelable != null) {
			for (Parcelable parcelable : assetsParcelable) {
				assets.add((FundAssetInfo) parcelable);
			}
		}
		isNew = tmpIsNew == 0 ? null : tmpIsNew == 1;
	}

	public Boolean isNeedStep() {
		return needStep;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public ArrayList<FundAssetInfo> getAssets() {
		return assets;
	}

	public String getStepTitle() {
		return stepTitle;
	}

	public String getReallocationinfo() {
		return reallocationInfo;
	}

	public String getButtonText() {
		return buttonText;
	}

	public Boolean isNew() {
		return isNew;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeByte((byte) (needStep == null ? 0 : needStep ? 1 : 2));
		parcel.writeString(stepNumber);
		parcel.writeString(stepTitle);
		parcel.writeString(reallocationInfo);
		parcel.writeString(buttonText);
		parcel.writeParcelableArray(assets.toArray(new FundAssetInfo[0]), flags);
		parcel.writeByte((byte) (isNew == null ? 0 : isNew ? 1 : 2));
	}
}
