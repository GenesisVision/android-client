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

	@SerializedName("program_color")
	private String programColor;

	@SerializedName("level")
	private Integer level = 1;

	@SerializedName("level_progress")
	private Double levelProgress = 0.0;

	@SerializedName("period_ends_text")
	private String periodEndsText;

	@SerializedName("amount_to_invest_text")
	private String amountTopText;

	@SerializedName("entry_fee_text")
	private String infoMiddleText;

	@SerializedName("amount_due_text")
	private String amountBottomText;

	@SerializedName("amount")
	private Double amount = 0.0;

	@SerializedName("currency")
	private String programCurrency;

	@SerializedName("wallet_currency")
	private String walletCurrency;

	@SerializedName("withdraw_all")
	private Boolean withdrawAll = false;

	public ProgramRequest() {

	}

	protected ProgramRequest(Parcel in) {
		programId = (UUID) in.readValue(UUID.class.getClassLoader());
		programName = in.readString();
		managerName = in.readString();
		programLogo = in.readString();
		programColor = in.readString();
		programCurrency = in.readString();
		level = in.readInt();
		levelProgress = in.readDouble();
		periodEndsText = in.readString();
		amountTopText = in.readString();
		infoMiddleText = in.readString();
		amountBottomText = in.readString();
		amount = in.readDouble();
		walletCurrency = in.readString();
		withdrawAll = in.readByte() != 0;
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
		dest.writeString(programColor);
		dest.writeString(programCurrency);
		dest.writeInt(level);
		dest.writeDouble(levelProgress);
		dest.writeString(periodEndsText);
		dest.writeString(amountTopText);
		dest.writeString(infoMiddleText);
		dest.writeString(amountBottomText);
		dest.writeDouble(amount);
		dest.writeString(walletCurrency);
		dest.writeByte((byte) (withdrawAll ? 1 : 0));
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

	public String getProgramColor() {
		return programColor;
	}

	public void setProgramColor(String programColor) {
		this.programColor = programColor;
	}

	public String getProgramCurrency() {
		return programCurrency;
	}

	public void setProgramCurrency(String programCurrency) {
		this.programCurrency = programCurrency;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getLevelProgress() {
		return levelProgress;
	}

	public void setLevelProgress(Double levelProgress) {
		this.levelProgress = levelProgress;
	}

	public String getPeriodEndsText() {
		return periodEndsText;
	}

	public void setPeriodEndsText(String periodEndsText) {
		this.periodEndsText = periodEndsText;
	}

	public String getAmountTopText() {
		return amountTopText;
	}

	public void setAmountTopText(String amountTopText) {
		this.amountTopText = amountTopText;
	}

	public String getInfoMiddleText() {
		return infoMiddleText;
	}

	public void setInfoMiddleText(String infoMiddleText) {
		this.infoMiddleText = infoMiddleText;
	}

	public String getAmountBottomText() {
		return amountBottomText;
	}

	public void setAmountBottomText(String amountBottomText) {
		this.amountBottomText = amountBottomText;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getWalletCurrency() {
		return walletCurrency;
	}

	public void setWalletCurrency(String walletCurrency) {
		this.walletCurrency = walletCurrency;
	}

	public Boolean isWithdrawAll() {
		return withdrawAll;
	}

	public void setWithdrawAll(Boolean withdrawAll) {
		this.withdrawAll = withdrawAll;
	}
}
