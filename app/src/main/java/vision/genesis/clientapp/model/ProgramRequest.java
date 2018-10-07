package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class ProgramRequest implements Parcelable
{
	public static final Creator<ProgramRequest> CREATOR = new Creator<ProgramRequest>()
	{
		@Override
		public ProgramRequest createFromParcel(Parcel in) {
			return new ProgramRequest(in);
		}

		@Override
		public ProgramRequest[] newArray(int size) {
			return new ProgramRequest[size];
		}
	};

	@SerializedName("program_id")
	private UUID programId;

	@SerializedName("program_name")
	private String programName;

	@SerializedName("manager_name")
	private String managerName;

	@SerializedName("program_logo")
	private String programLogo;

	@SerializedName("level")
	private Integer level = 1;

	@SerializedName("period_ends_text")
	private String periodEndsText;

	@SerializedName("amount_to_invest_text")
	private String amountToInvestText;

	@SerializedName("entry_fee_text")
	private String entryFeeText;

	@SerializedName("amount_due_text")
	private String amountDueText;

	@SerializedName("amount")
	private Double amount = 0.0;

	public ProgramRequest() {

	}

	protected ProgramRequest(Parcel in) {
		programId = (UUID) in.readValue(UUID.class.getClassLoader());
		programName = in.readString();
		managerName = in.readString();
		programLogo = in.readString();
		level = in.readInt();
		periodEndsText = in.readString();
		amountToInvestText = in.readString();
		entryFeeText = in.readString();
		amountDueText = in.readString();
		amount = in.readDouble();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(programId);
		dest.writeString(programName);
		dest.writeString(managerName);
		dest.writeString(programLogo);
		dest.writeInt(level);
		dest.writeString(periodEndsText);
		dest.writeString(amountToInvestText);
		dest.writeString(entryFeeText);
		dest.writeString(amountDueText);
		dest.writeDouble(amount);
	}

	public UUID getProgramId() {
		return programId;
	}

	public void setProgramId(UUID programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getProgramLogo() {
		return programLogo;
	}

	public void setProgramLogo(String programLogo) {
		this.programLogo = programLogo;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPeriodEndsText() {
		return periodEndsText;
	}

	public void setPeriodEndsText(String periodEndsText) {
		this.periodEndsText = periodEndsText;
	}

	public String getAmountToInvestText() {
		return amountToInvestText;
	}

	public void setAmountToInvestText(String amountToInvestText) {
		this.amountToInvestText = amountToInvestText;
	}

	public String getEntryFeeText() {
		return entryFeeText;
	}

	public void setEntryFeeText(String entryFeeText) {
		this.entryFeeText = entryFeeText;
	}

	public String getAmountDueText() {
		return amountDueText;
	}

	public void setAmountDueText(String amountDueText) {
		this.amountDueText = amountDueText;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
