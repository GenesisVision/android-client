package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.swagger.client.model.InvestmentProgramDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ProgramDescriptionModel implements Parcelable
{

	public static final Creator<ProgramDescriptionModel> CREATOR = new Creator<ProgramDescriptionModel>()
	{
		@Override
		public ProgramDescriptionModel createFromParcel(Parcel in) {
			return new ProgramDescriptionModel(in);
		}

		@Override
		public ProgramDescriptionModel[] newArray(int size) {
			return new ProgramDescriptionModel[size];
		}
	};

	public static ProgramDescriptionModel fromProgram(InvestmentProgramDetails programDetails) {
		ProgramDescriptionModel model = new ProgramDescriptionModel();
		model.programId = programDetails.getId().toString();
		model.programName = programDetails.getTitle();
		model.programLogo = programDetails.getLogo();
		model.programLevel = programDetails.getLevel();
		model.programDescription = programDetails.getDescription();
		model.managerId = programDetails.getManager().getId().toString();
		model.managerName = programDetails.getManager().getUsername();
		return model;
	}

	@SerializedName("program_id")
	public String programId;

	@SerializedName("program_name")
	public String programName;

	@SerializedName("program_logo")
	public String programLogo;

	@SerializedName("program_level")
	public Integer programLevel;

	@SerializedName("program_description")
	public String programDescription;

	@SerializedName("manager_id")
	public String managerId;

	@SerializedName("manager_name")
	public String managerName;

	public ProgramDescriptionModel() {

	}

	protected ProgramDescriptionModel(Parcel in) {
		programId = in.readString();
		programName = in.readString();
		programLogo = in.readString();
		if (in.readByte() == 0) {
			programLevel = null;
		}
		else {
			programLevel = in.readInt();
		}
		programDescription = in.readString();
		managerId = in.readString();
		managerName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(programId);
		dest.writeString(programName);
		dest.writeString(programLogo);
		if (programLevel == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(programLevel);
		}
		dest.writeString(programDescription);
		dest.writeString(managerId);
		dest.writeString(managerName);
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
