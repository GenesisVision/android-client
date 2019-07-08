package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.OrderSignalProgramInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2019.
 */
public class OpenTradeProviderModel implements Parcelable
{
	public static final Creator<OpenTradeProviderModel> CREATOR = new Creator<OpenTradeProviderModel>()
	{
		@Override
		public OpenTradeProviderModel createFromParcel(Parcel in) {
			return new OpenTradeProviderModel(in);
		}

		@Override
		public OpenTradeProviderModel[] newArray(int size) {
			return new OpenTradeProviderModel[size];
		}
	};

	public static OpenTradeProviderModel createFrom(OrderSignalProgramInfo info, String symbol) {
		OpenTradeProviderModel model = new OpenTradeProviderModel();

		model.programId = info.getProgramId();
		model.programName = info.getProgram().getTitle();
		model.programLogo = info.getProgram().getLogo();
		model.programLevel = info.getProgram().getLevel();
		model.programLevelProgress = info.getProgram().getLevelProgress();
		model.programColor = info.getProgram().getColor();

		model.volume = info.getVolume();
		model.priceOpen = info.getPriceOpenAvg();
		model.profit = info.getProfit();
		model.date = info.getFirstOrderDate();
		model.symbol = symbol;

		return model;
	}

	private UUID programId;

	private String programName;

	private String programLogo;

	private Integer programLevel;

	private Double programLevelProgress;

	private String programColor;

	private Double volume;

	private Double priceOpen;

	private Double profit;

	private DateTime date;

	private String symbol;

	protected OpenTradeProviderModel(Parcel in) {
		programId = (UUID) in.readSerializable();
		programName = in.readString();
		programLogo = in.readString();
		if (in.readByte() == 0) {
			programLevel = null;
		}
		else {
			programLevel = in.readInt();
		}
		if (in.readByte() == 0) {
			programLevelProgress = null;
		}
		else {
			programLevelProgress = in.readDouble();
		}
		programColor = in.readString();
		if (in.readByte() == 0) {
			volume = null;
		}
		else {
			volume = in.readDouble();
		}
		if (in.readByte() == 0) {
			priceOpen = null;
		}
		else {
			priceOpen = in.readDouble();
		}
		if (in.readByte() == 0) {
			profit = null;
		}
		else {
			profit = in.readDouble();
		}
		symbol = in.readString();
	}

	public OpenTradeProviderModel() {

	}

	public UUID getProgramId() {
		return programId;
	}

	public String getProgramName() {
		return programName;
	}

	public String getProgramLogo() {
		return programLogo;
	}

	public Integer getProgramLevel() {
		return programLevel;
	}

	public Double getProgramLevelProgress() {
		return programLevelProgress;
	}

	public String getProgramColor() {
		return programColor;
	}

	public Double getVolume() {
		return volume;
	}

	public Double getPriceOpen() {
		return priceOpen;
	}

	public Double getProfit() {
		return profit;
	}

	public DateTime getDate() {
		return date;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(programId);
		parcel.writeString(programName);
		parcel.writeString(programLogo);
		if (programLevel == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(programLevel);
		}
		if (programLevelProgress == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(programLevelProgress);
		}
		parcel.writeString(programColor);
		if (volume == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(volume);
		}
		if (priceOpen == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(priceOpen);
		}
		if (profit == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(profit);
		}
		parcel.writeString(symbol);
	}
}
