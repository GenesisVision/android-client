package vision.genesis.clientapp.model.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Objects;

import timber.log.Timber;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/08/2018.
 */

public class FilterOption implements Parcelable
{
	public static final String TYPE_SINGLE_VALUE = "type_single_value";

	public static final String TYPE_RANGE = "type_range";

	public static final Creator<FilterOption> CREATOR = new Creator<FilterOption>()
	{
		@Override
		public FilterOption createFromParcel(Parcel in) {
			return new FilterOption(in);
		}

		@Override
		public FilterOption[] newArray(int size) {
			return new FilterOption[size];
		}
	};

	private String name;

	private ArrayList<String> values;

	private int selectedPosition;

	private String type;

	public FilterOption(FilterOption filterOption) {
		this.type = filterOption.getType();
		this.name = filterOption.getName();
		this.values = filterOption.getValues();
		this.selectedPosition = filterOption.getSelectedPosition();
	}

	public FilterOption(String type, String name, ArrayList<String> values, int selectedPosition) {
		this.type = type;
		this.name = name;
		this.values = values;
		this.selectedPosition = selectedPosition;
	}

	private FilterOption(Parcel in) {
		name = in.readString();
		values = in.createStringArrayList();
		selectedPosition = in.readInt();
		type = in.readString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public String getSelectedValue() {
		return values.get(selectedPosition);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeStringList(values);
		parcel.writeInt(selectedPosition);
		parcel.writeString(type);
	}

	public void resetToDefaultValue() {
		switch (type) {
			case TYPE_SINGLE_VALUE:
				setSelectedPosition(0);
				break;
			case TYPE_RANGE:
//				setSelectedPosition(0);
				break;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FilterOption filterOption = (FilterOption) o;
		if (!Objects.equals(getSelectedPosition(), filterOption.getSelectedPosition()))
			Timber.d("stop");
		return Objects.equals(getName(), filterOption.getName()) &&
				getValues().equals(filterOption.getValues()) &&
				Objects.equals(getSelectedPosition(), filterOption.getSelectedPosition()) &&
				Objects.equals(getType(), filterOption.getType());
	}
}
