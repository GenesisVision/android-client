package vision.genesis.clientapp.model.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/08/2019.
 */
public class DashboardFilter implements Parcelable
{
	public static final Creator<DashboardFilter> CREATOR = new Creator<DashboardFilter>()
	{
		@Override
		public DashboardFilter createFromParcel(Parcel in) {
			return new DashboardFilter(in);
		}

		@Override
		public DashboardFilter[] newArray(int size) {
			return new DashboardFilter[size];
		}
	};

	private SortingEnum sorting = SortingEnum.BYPROFITDESC;

	private DateRange dateRange;

	private CurrencyEnum currencySecondary;

	private String actionStatus;

	private String dashboardActionStatus = "Active";

	private Integer skip;

	private Integer take;

	private Integer chartPointsCount;

	public DashboardFilter() {
	}

	public DashboardFilter(DashboardFilter filter) {
		if (filter != null) {
			this.sorting = filter.getSorting();
			this.dateRange = DateRange.copy(filter.getDateRange());
			this.currencySecondary = filter.getCurrencySecondary();
			this.actionStatus = filter.getActionStatus();
			this.dashboardActionStatus = filter.getDashboardActionStatus();
			this.skip = filter.getSkip();
			this.take = filter.getTake();
			this.chartPointsCount = filter.getChartPointsCount();
		}
	}

	private DashboardFilter(Parcel in) {
		try {
			sorting = SortingEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			sorting = null;
		}
		dateRange = in.readParcelable(DateRange.class.getClassLoader());
		try {
			currencySecondary = CurrencyEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			currencySecondary = null;
		}
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
		if (in.readByte() == 0) {
			chartPointsCount = null;
		}
		else {
			chartPointsCount = in.readInt();
		}
		actionStatus = in.readString();
		dashboardActionStatus = in.readString();
	}

	public SortingEnum getSorting() {
		return sorting;
	}

	public void setSorting(SortingEnum sorting) {
		this.sorting = sorting;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public CurrencyEnum getCurrencySecondary() {
		return currencySecondary;
	}

	public void setCurrencySecondary(CurrencyEnum currencySecondary) {
		this.currencySecondary = currencySecondary;
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

	public Integer getChartPointsCount() {
		return chartPointsCount;
	}

	public void setChartPointsCount(Integer chartPointsCount) {
		this.chartPointsCount = chartPointsCount;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getDashboardActionStatus() {
		return dashboardActionStatus;
	}

	public void setDashboardActionStatus(String dashboardActionStatus) {
		this.dashboardActionStatus = dashboardActionStatus;
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
		dest.writeParcelable(dateRange, flags);
		if (currencySecondary == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(currencySecondary.name());
		}
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
		if (chartPointsCount == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeInt(chartPointsCount);
		}
		dest.writeString(actionStatus);
		dest.writeString(dashboardActionStatus);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardFilter filter = (DashboardFilter) o;
		return Objects.equals(getSorting(), filter.getSorting()) &&
				getDateRange().equals(filter.getDateRange()) &&
				Objects.equals(getCurrencySecondary(), filter.getCurrencySecondary()) &&
				Objects.equals(getSkip(), filter.getSkip()) &&
				Objects.equals(getTake(), filter.getTake()) &&
				Objects.equals(getActionStatus(), filter.getActionStatus()) &&
				Objects.equals(getDashboardActionStatus(), filter.getDashboardActionStatus()) &&
				Objects.equals(getChartPointsCount(), filter.getChartPointsCount());
	}

	public UserFilter getUserFilter(int type) {
		UserFilter userFilter = new UserFilter();
		userFilter.setType(type);
		userFilter.setDateRange(this.getDateRange());
		userFilter.setSortingEnabled(false);

		List<FilterOption> filterOptionList = new ArrayList<>();
		filterOptionList.add(getStatusFilterOption());
		userFilter.setOptions(filterOptionList);
		return userFilter;
	}

	private FilterOption getStatusFilterOption() {
		ArrayList<String> values = new ArrayList<>();
		int selectedPosition = 0;
		boolean selectedFound = false;
		for (String status : Constants.getStatusesForDashboardProgramsListFilter()) {
			values.add(status);
			if (this.getDashboardActionStatus() != null && this.getDashboardActionStatus().equals(status)) {
				selectedFound = true;
			}
			if (!selectedFound)
				selectedPosition++;
		}
		if (!selectedFound)
			selectedPosition = 0;
		return new FilterOption(FilterOption.TYPE_SINGLE_VALUE, GenesisVisionApplication.INSTANCE.getString(R.string.status), values, selectedPosition);
	}

	public void updateWithUserFilter(UserFilter userFilter) {
		setDateRange(userFilter.getDateRange());
		setSorting(userFilter.getSorting());

		for (FilterOption filterOption : userFilter.getOptions()) {
			switch (filterOption.getName().toLowerCase()) {
				case "status":
					this.setDashboardActionStatus(filterOption.getSelectedPosition() > 0
							? filterOption.getSelectedValue()
							: null);
					break;
			}
		}

	}
}
