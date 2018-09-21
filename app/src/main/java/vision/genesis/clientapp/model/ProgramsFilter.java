package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

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

	private DateTime statisticDateFrom;

	private DateTime statisticDateTo;

	private String mask;

	private UUID facetId;

	private Boolean isFavorite;

	private CurrencyEnum currency;

	private List<UUID> ids;

	private Integer skip;

	private Integer take;

	public ProgramsFilter() {

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
		statisticDateFrom = DateTime.parse(in.readString());
		statisticDateTo = DateTime.parse(in.readString());
		mask = in.readString();
		byte tmpIsFavorite = in.readByte();
		isFavorite = tmpIsFavorite == 0 ? null : tmpIsFavorite == 1;
		try {
			currency = CurrencyEnum.valueOf(in.readString());
		} catch (IllegalArgumentException e) {
			currency = null;
		}
		in.readList(ids, null);
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

	public DateTime getStatisticDateFrom() {
		return statisticDateFrom;
	}

	public void setStatisticDateFrom(DateTime statisticDateFrom) {
		this.statisticDateFrom = statisticDateFrom;
	}

	public DateTime getStatisticDateTo() {
		return statisticDateTo;
	}

	public void setStatisticDateTo(DateTime statisticDateTo) {
		this.statisticDateTo = statisticDateTo;
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

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public List<UUID> getIds() {
		return ids;
	}

	public void setIds(List<UUID> ids) {
		this.ids = ids;
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
		if (statisticDateFrom == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(statisticDateFrom.toString());
		}
		if (statisticDateTo == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(statisticDateTo.toString());
		}
		dest.writeString(mask);
		dest.writeByte((byte) (isFavorite == null ? 0 : isFavorite ? 1 : 2));
		if (currency == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(currency.name());
		}
		dest.writeList(ids);
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
	}
}
