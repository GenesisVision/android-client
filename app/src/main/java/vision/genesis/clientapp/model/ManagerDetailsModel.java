package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.PublicProfile;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */
public class ManagerDetailsModel implements Parcelable
{
	public static final Creator<ManagerDetailsModel> CREATOR = new Creator<ManagerDetailsModel>()
	{
		@Override
		public ManagerDetailsModel createFromParcel(Parcel in) {
			return new ManagerDetailsModel(in);
		}

		@Override
		public ManagerDetailsModel[] newArray(int size) {
			return new ManagerDetailsModel[size];
		}
	};

	private UUID managerId;

	private String avatar;

	private String managerName;

	private DateTime managerDate;

	public ManagerDetailsModel(UUID managerId, String avatar, String managerName, DateTime managerDate) {
		this.managerId = managerId;
		this.avatar = avatar;
		this.managerName = managerName;
		this.managerDate = managerDate;
	}

	protected ManagerDetailsModel(Parcel in) {
		managerId = (UUID) in.readSerializable();
		avatar = in.readString();
		managerName = in.readString();
		managerDate = DateTime.parse(in.readString());
	}

	public UUID getManagerId() {
		return managerId;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getManagerName() {
		return managerName;
	}

	public DateTime getManagerDate() {
		return managerDate;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(managerId);
		dest.writeString(avatar);
		dest.writeString(managerName);
		if (managerDate == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(managerDate.toString());
		}
	}

	public void update(PublicProfile managerDetails) {
		this.managerId = managerDetails.getId();
		this.avatar = managerDetails.getLogoUrl();
		this.managerName = managerDetails.getUsername();
		this.managerDate = managerDetails.getRegDate();
	}
}
