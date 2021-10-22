package vision.genesis.clientapp.model.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/08/2018.
 */
public class ProgramsFilter implements Parcelable
{
	public static final Creator<ProgramsFilter> CREATOR = new Creator<ProgramsFilter>()
	{
		@Override
		public ProgramsFilter createFromParcel(Parcel in) {
			return new ProgramsFilter(in);
		}

		@Override
		public ProgramsFilter[] newArray(int size) {
			return new ProgramsFilter[size];
		}
	};

	private SortingEnum sorting = SortingEnum.BYPROFITDESC;

	private Integer levelMin;

	private Integer levelMax;

	private Double profitAvgMin;

	private Double profitAvgMax;

	private DateRange dateRange;

	private String mask;

	private UUID facetId;

	private Boolean isFavorite = false;

	private Boolean isEnabled;

	private CurrencyEnum showIn;

	private CurrencyEnum currency;

	private Integer levelUpFrom;

	private List<UUID> ids;

	private UUID managerId;

	private Integer skip;

	private Integer take;

	private List<String> tags;

	private List<String> assets;

	private Integer chartPointsCount;

	private String status;

	private Boolean includeWithInvestments;

	public ProgramsFilter() {
	}

	public ProgramsFilter(ProgramsFilter filter) {
		if (filter != null) {
			this.sorting = filter.getSorting();
			this.levelMin = filter.getLevelMin();
			this.levelMax = filter.getLevelMax();
			this.profitAvgMin = filter.getProfitAvgMin();
			this.profitAvgMax = filter.getProfitAvgMax();
			this.dateRange = DateRange.copy(filter.getDateRange());
			this.mask = filter.getMask();
			this.facetId = filter.getFacetId();
			this.isFavorite = filter.getIsFavorite();
			this.isEnabled = filter.getIsEnabled();
			this.showIn = filter.getShowIn();
			this.currency = filter.getCurrency();
			this.levelUpFrom = filter.getLevelUpFrom();
			this.ids = filter.getIds();
			this.managerId = filter.getManagerId();
			this.skip = filter.getSkip();
			this.take = filter.getTake();
			this.tags = filter.getTags();
			this.assets = filter.getAssets();
			this.chartPointsCount = filter.getChartPointsCount();
		}
	}

	private ProgramsFilter(Parcel in) {
		try {
			sorting = SortingEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			sorting = null;
		}
		if (in.readByte() == 0) {
			levelMin = null;
		}
		else {
			levelMin = in.readInt();
		}
		if (in.readByte() == 0) {
			levelMax = null;
		}
		else {
			levelMax = in.readInt();
		}
		if (in.readByte() == 0) {
			profitAvgMin = null;
		}
		else {
			profitAvgMin = in.readDouble();
		}
		if (in.readByte() == 0) {
			profitAvgMax = null;
		}
		else {
			profitAvgMax = in.readDouble();
		}
		dateRange = in.readParcelable(DateRange.class.getClassLoader());
		mask = in.readString();
		byte tmpIsFavorite = in.readByte();
		isFavorite = tmpIsFavorite == 0 ? null : tmpIsFavorite == 1;
		byte tmpIsEnabled = in.readByte();
		isEnabled = tmpIsEnabled == 0 ? null : tmpIsEnabled == 1;
		try {
			showIn = CurrencyEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			showIn = null;
		}
		try {
			currency = CurrencyEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			currency = null;
		}
		if (in.readByte() == 0) {
			levelUpFrom = null;
		}
		else {
			levelUpFrom = in.readInt();
		}
		in.readList(ids, null);
		managerId = (UUID) in.readSerializable();
		if (in.readByte() == 0) {
			skip = null;
		}
		else {
			skip = in.readInt();
		}
		if (in.readByte() == 0) {
			take = null;
		}
		else {
			take = in.readInt();
		}
		in.readList(tags, String.class.getClassLoader());
		in.readList(assets, String.class.getClassLoader());
		if (in.readByte() == 0) {
			chartPointsCount = null;
		}
		else {
			chartPointsCount = in.readInt();
		}
		status = in.readString();
		byte tmpIncludeWithInvestments = in.readByte();
		includeWithInvestments = tmpIncludeWithInvestments == 0 ? null : tmpIncludeWithInvestments == 1;
	}

	public SortingEnum getSorting() {
		return sorting;
	}

	public void setSorting(SortingEnum sorting) {
		this.sorting = sorting;
	}

	public Integer getLevelMin() {
		return levelMin;
	}

	public void setLevelMin(Integer levelMin) {
		this.levelMin = levelMin;
	}

	public Integer getLevelMax() {
		return levelMax;
	}

	public void setLevelMax(Integer levelMax) {
		this.levelMax = levelMax;
	}

	public Double getProfitAvgMin() {
		return profitAvgMin;
	}

	public void setProfitAvgMin(Double profitAvgMin) {
		this.profitAvgMin = profitAvgMin;
	}

	public Double getProfitAvgMax() {
		return profitAvgMax;
	}

	public void setProfitAvgMax(Double profitAvgMax) {
		this.profitAvgMax = profitAvgMax;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public UUID getFacetId() {
		return facetId;
	}

	public void setFacetId(UUID facetId) {
		this.facetId = facetId;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean favorite) {
		isFavorite = favorite;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean enabled) {
		isEnabled = enabled;
	}

	public CurrencyEnum getShowIn() {
		return showIn;
	}

	public void setShowIn(CurrencyEnum showIn) {
		this.showIn = showIn;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public Integer getLevelUpFrom() {
		return levelUpFrom;
	}

	public void setLevelUpFrom(Integer levelUpFrom) {
		this.levelUpFrom = levelUpFrom;
	}

	public List<UUID> getIds() {
		return ids;
	}

	public void setIds(List<UUID> ids) {
		this.ids = ids;
	}

	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getTake() {
		return take;
	}

	public void setTake(Integer take) {
		this.take = take;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getAssets() {
		return assets;
	}

	public void setAssets(List<String> assets) {
		this.assets = assets;
	}

	public Integer getChartPointsCount() {
		return chartPointsCount;
	}

	public void setChartPointsCount(Integer chartPointsCount) {
		this.chartPointsCount = chartPointsCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIncludeWithInvestments() {
		return includeWithInvestments;
	}

	public void setIncludeWithInvestments(Boolean includeWithInvestments) {
		this.includeWithInvestments = includeWithInvestments;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (sorting == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(sorting.name());
		}
		if (levelMin == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(levelMin);
		}
		if (levelMax == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(levelMax);
		}
		if (profitAvgMin == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(profitAvgMin);
		}
		if (profitAvgMax == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(profitAvgMax);
		}
		dest.writeParcelable(dateRange, flags);
		dest.writeString(mask);
		dest.writeByte((byte) (isFavorite == null ? 0 : isFavorite ? 1 : 2));
		dest.writeByte((byte) (isEnabled == null ? 0 : isEnabled ? 1 : 2));
		if (showIn == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(showIn.name());
		}
		if (currency == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(currency.name());
		}
		if (levelUpFrom == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(levelUpFrom);
		}
		dest.writeList(ids);
		dest.writeSerializable(managerId);
		if (skip == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(skip);
		}
		if (take == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(take);
		}
		dest.writeList(tags);
		dest.writeList(assets);
		if (chartPointsCount == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(chartPointsCount);
		}
		dest.writeString(status);
		dest.writeByte((byte) (includeWithInvestments == null ? 0 : includeWithInvestments ? 1 : 2));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramsFilter filter = (ProgramsFilter) o;
		return Objects.equals(getSorting(), filter.getSorting()) &&
				Objects.equals(getLevelMin(), filter.getLevelMin()) &&
				Objects.equals(getLevelMax(), filter.getLevelMax()) &&
				Objects.equals(getProfitAvgMin(), filter.getProfitAvgMin()) &&
				Objects.equals(getProfitAvgMax(), filter.getProfitAvgMax()) &&
				getDateRange().equals(filter.getDateRange()) &&
				Objects.equals(getMask(), filter.getMask()) &&
				Objects.equals(getFacetId(), filter.getFacetId()) &&
				Objects.equals(getIsFavorite(), filter.getIsFavorite()) &&
				Objects.equals(getIsEnabled(), filter.getIsEnabled()) &&
				Objects.equals(getShowIn(), filter.getShowIn()) &&
				Objects.equals(getCurrency(), filter.getCurrency()) &&
				Objects.equals(getLevelUpFrom(), filter.getLevelUpFrom()) &&
				Objects.equals(getIds(), filter.getIds()) &&
				Objects.equals(getManagerId(), filter.getManagerId()) &&
				Objects.equals(getSkip(), filter.getSkip()) &&
				Objects.equals(getTake(), filter.getTake()) &&
				Objects.equals(getTags(), filter.getTags()) &&
				Objects.equals(getAssets(), filter.getAssets()) &&
				Objects.equals(getChartPointsCount(), filter.getChartPointsCount());
	}

	public UserFilter getUserFilter(int type) {
		UserFilter userFilter = new UserFilter();
		userFilter.setType(type);
		userFilter.setDateRange(this.getDateRange());
		userFilter.setSorting(this.getSorting());

		List<FilterOption> filterOptionList = new ArrayList<>();
		switch (type) {
			case UserFilter.TYPE_PROGRAMS_LIST_FILTER:
				userFilter.setFavoritesEnabled(true);
				userFilter.setFavorite(this.getIsFavorite());
				filterOptionList.add(getCurrencyFilterOption());
				break;
			case UserFilter.TYPE_FUNDS_LIST_FILTER:
				userFilter.setFavoritesEnabled(true);
				userFilter.setFavorite(this.getIsFavorite());
				userFilter.setAssetsEnabled(true);
				userFilter.setAssets(this.getAssets());
				break;
			case UserFilter.TYPE_FOLLOWS_LIST_FILTER:
				userFilter.setFavoritesEnabled(true);
				userFilter.setFavorite(this.getIsFavorite());
				break;
			case UserFilter.TYPE_COINS_LIST_FILTER:
				userFilter.setFavoritesEnabled(true);
				userFilter.setFavorite(this.getIsFavorite());
				userFilter.setDateRangeEnabled(false);
				userFilter.setAssetsEnabled(true);
				userFilter.setAssets(this.getAssets());
				break;
			case UserFilter.TYPE_COINS_HISTORY_FILTER:
				userFilter.setSortingEnabled(false);
				userFilter.setAssetsEnabled(true);
				userFilter.setAssets(this.getAssets());
				break;
		}
		userFilter.setOptions(filterOptionList);
		return userFilter;
	}

	private FilterOption getCurrencyFilterOption() {
		ArrayList<String> values = new ArrayList<>();
		int selectedPosition = 0;
		boolean selectedFound = false;
		for (String currency : Constants.getCurrenciesForProgramsListFilter()) {
			values.add(currency);
			if (this.getCurrency() != null && this.getCurrency().getValue().equals(currency)) {
				selectedFound = true;
			}
			if (!selectedFound) {
				selectedPosition++;
			}
		}
		if (!selectedFound) {
			selectedPosition = 0;
		}
		return new FilterOption(FilterOption.TYPE_SINGLE_VALUE, GenesisVisionApplication.INSTANCE.getString(R.string.currency), values, selectedPosition);
	}

	public void updateWithUserFilter(UserFilter userFilter) {
		setDateRange(userFilter.getDateRange());
		setSorting(userFilter.getSorting());
		setAssets(userFilter.getAssets());
		setIsFavorite(userFilter.isFavorite());

		for (FilterOption filterOption : userFilter.getOptions()) {
			switch (filterOption.getName().toLowerCase()) {
				case "currency":
					this.setCurrency(filterOption.getSelectedPosition() > 0
							? CurrencyEnum.fromValue(filterOption.getSelectedValue())
							: null);
					break;
			}
		}

	}
}
