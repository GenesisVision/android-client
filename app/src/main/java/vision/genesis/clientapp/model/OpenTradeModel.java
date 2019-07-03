package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.swagger.client.model.OrderSignalModel;
import io.swagger.client.model.OrderSignalProgramInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2019.
 */
public class OpenTradeModel implements Parcelable
{
	public static final Creator<OpenTradeModel> CREATOR = new Creator<OpenTradeModel>()
	{
		@Override
		public OpenTradeModel createFromParcel(Parcel in) {
			return new OpenTradeModel(in);
		}

		@Override
		public OpenTradeModel[] newArray(int size) {
			return new OpenTradeModel[size];
		}
	};

	public static OpenTradeModel createFrom(OrderSignalModel trade) {
		OpenTradeModel model = new OpenTradeModel();

		model.id = trade.getId();
		model.symbol = trade.getSymbol();
		model.volume = trade.getVolume();
		model.dir = trade.getDirection().getValue();

		List<OpenTradeProviderModel> providers = new ArrayList<>();
		for (OrderSignalProgramInfo info : trade.getProviders()) {
			providers.add(OpenTradeProviderModel.createFrom(info, trade.getSymbol()));
		}
		model.providers = providers;

		return model;
	}

	private UUID id;

	private String symbol;

	private Double volume;

	private String dir;

	private List<OpenTradeProviderModel> providers;

	protected OpenTradeModel(Parcel in) {
		id = (UUID) in.readSerializable();
		symbol = in.readString();
		if (in.readByte() == 0) {
			volume = null;
		}
		else {
			volume = in.readDouble();
		}
		dir = in.readString();
		providers = in.createTypedArrayList(OpenTradeProviderModel.CREATOR);
	}

	public OpenTradeModel() {

	}

	public UUID getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getVolume() {
		return volume;
	}

	public String getDir() {
		return dir;
	}

	public List<OpenTradeProviderModel> getProviders() {
		return providers;
	}

	public void setProviders(List<OpenTradeProviderModel> providers) {
		this.providers = providers;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(id);
		parcel.writeString(symbol);
		if (volume == null) {
			parcel.writeByte((byte) 0);
		}
		else {
			parcel.writeByte((byte) 1);
			parcel.writeDouble(volume);
		}
		parcel.writeString(dir);
		parcel.writeTypedList(providers);
	}
}
