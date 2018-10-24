package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.FundDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */
public class FundDetailsModel implements Parcelable
{
	public static final Creator<FundDetailsModel> CREATOR = new Creator<FundDetailsModel>()
	{
		@Override
		public FundDetailsModel createFromParcel(Parcel in) {
			return new FundDetailsModel(in);
		}

		@Override
		public FundDetailsModel[] newArray(int size) {
			return new FundDetailsModel[size];
		}
	};

	private UUID fundId;

	private String logo;

	private String fundColor;

	private String fundName;

	private String managerName;

	private boolean favorite;

	public FundDetailsModel(UUID fundId, String logo, String fundColor, String fundName, String managerName, boolean isFavorite) {
		this.fundId = fundId;
		this.logo = logo;
		this.fundColor = fundColor;
		this.fundName = fundName;
		this.managerName = managerName;
		this.favorite = isFavorite;
	}

	protected FundDetailsModel(Parcel in) {
		fundId = (UUID) in.readSerializable();
		logo = in.readString();
		fundColor = in.readString();
		fundName = in.readString();
		managerName = in.readString();
		favorite = in.readByte() != 0;
	}

	public UUID getFundId() {
		return fundId;
	}

	public String getLogo() {
		return logo;
	}

	public String getFundColor() {
		return fundColor;
	}

	public String getFundName() {
		return fundName;
	}

	public String getManagerName() {
		return managerName;
	}

	public boolean isFavorite() {
		return favorite;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(fundId);
		dest.writeString(logo);
		dest.writeString(fundColor);
		dest.writeString(fundName);
		dest.writeString(managerName);
		dest.writeByte((byte) (favorite ? 1 : 0));
	}

	public void update(FundDetailsFull fundDetails) {
		this.fundId = fundDetails.getId();
		this.logo = fundDetails.getLogo();
		this.fundColor = fundDetails.getColor();
		this.fundName = fundDetails.getTitle();
		this.managerName = fundDetails.getManager().getUsername();
		this.favorite = fundDetails.getPersonalFundDetails() != null
				? fundDetails.getPersonalFundDetails().isIsFavorite()
				: false;
	}
}
