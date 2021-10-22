package vision.genesis.clientapp.model.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/08/2018.
 */

public class UserFilter implements Parcelable
{
	public static final int TYPE_PROGRAMS_LIST_FILTER = 100;

	public static final int TYPE_FUNDS_LIST_FILTER = 101;

	public static final int TYPE_FOLLOWS_LIST_FILTER = 102;

	public static final int TYPE_COINS_LIST_FILTER = 103;

	public static final int TYPE_DASHBOARD_PROGRAMS_FILTER = 104;

	public static final int TYPE_DASHBOARD_FUNDS_FILTER = 105;

	public static final int TYPE_DASHBOARD_SIGNALS_FILTER = 106;

	public static final int TYPE_COINS_HISTORY_FILTER = 107;

	public static final Creator<UserFilter> CREATOR = new Creator<UserFilter>()
	{
		@Override
		public UserFilter createFromParcel(Parcel in) {
			return new UserFilter(in);
		}

		@Override
		public UserFilter[] newArray(int size) {
			return new UserFilter[size];
		}
	};

	private int type = 0;

	private List<FilterOption> options = new ArrayList<>();

	private List<String> assets = new ArrayList<>();

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private SortingEnum sorting;

	private boolean isDateRangeEnabled = true;

	private boolean isSortingEnabled = true;

	private boolean isAssetsEnabled = false;

	private boolean isFavoritesEnabled = false;

	private boolean isFavorite = false;

	public UserFilter() {

	}

	public UserFilter(UserFilter filter) {
		this.type = filter.getType();
		for (FilterOption filterOption : filter.getOptions()) {
			this.options.add(new FilterOption(filterOption));
		}
		this.assets.addAll(filter.getAssets());
		this.dateRange = DateRange.copy(filter.getDateRange());
		this.sorting = filter.getSorting();
		this.isFavoritesEnabled = filter.isFavoritesEnabled();
		this.isFavorite = filter.isFavorite();
	}

	protected UserFilter(Parcel in) {
		type = in.readInt();
		dateRange = in.readParcelable(DateRange.class.getClassLoader());
		in.readTypedList(options, FilterOption.CREATOR);
		in.readList(assets, String.class.getClassLoader());
		sorting = (SortingEnum) in.readSerializable();
		isDateRangeEnabled = in.readByte() != 0;
		isSortingEnabled = in.readByte() != 0;
		isAssetsEnabled = in.readByte() != 0;
		isFavoritesEnabled = in.readByte() != 0;
		isFavorite = in.readByte() != 0;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(type);
		parcel.writeParcelable(dateRange, i);
		parcel.writeTypedList(options);
		parcel.writeList(assets);
		parcel.writeSerializable(sorting);
		parcel.writeByte((byte) (isDateRangeEnabled ? 1 : 0));
		parcel.writeByte((byte) (isSortingEnabled ? 1 : 0));
		parcel.writeByte((byte) (isAssetsEnabled ? 1 : 0));
		parcel.writeByte((byte) (isFavoritesEnabled ? 1 : 0));
		parcel.writeByte((byte) (isFavorite ? 1 : 0));
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public SortingEnum getSorting() {
		return sorting;
	}

	public void setSorting(SortingEnum sorting) {
		this.sorting = sorting;
	}

	public List<FilterOption> getOptions() {
		return options;
	}

	public void setOptions(List<FilterOption> options) {
		this.options = options;
	}

	public List<String> getAssets() {
		return assets;
	}

	public void setAssets(List<String> assets) {
		this.assets = assets;
	}

	public void addAsset(String asset) {
		this.assets.add(asset);
	}

	public void removeAsset(String asset) {
		this.assets.remove(asset);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isDateRangeEnabled() {
		return isDateRangeEnabled;
	}

	public void setDateRangeEnabled(boolean dateRangeEnabled) {
		isDateRangeEnabled = dateRangeEnabled;
	}

	public boolean isAssetsEnabled() {
		return isAssetsEnabled;
	}

	public void setAssetsEnabled(boolean assetsEnabled) {
		this.isAssetsEnabled = assetsEnabled;
	}

	public boolean isSortingEnabled() {
		return isSortingEnabled;
	}

	public void setSortingEnabled(boolean sortingEnabled) {
		isSortingEnabled = sortingEnabled;
	}

	public boolean isFavoritesEnabled() {
		return isFavoritesEnabled;
	}

	public void setFavoritesEnabled(boolean favoritesEnabled) {
		this.isFavoritesEnabled = favoritesEnabled;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserFilter filter = (UserFilter) o;
		return Objects.equals(getSorting(), filter.getSorting()) &&
				getDateRange().equals(filter.getDateRange()) &&
				Objects.equals(getType(), filter.getType()) &&
				Objects.equals(getOptions(), filter.getOptions()) &&
				Objects.equals(getAssets(), filter.getAssets()) &&
				Objects.equals(isFavoritesEnabled(), filter.isFavoritesEnabled()) &&
				Objects.equals(isFavorite(), filter.isFavorite());
	}

}
