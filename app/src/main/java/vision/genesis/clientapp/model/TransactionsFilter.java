package vision.genesis.clientapp.model;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2019.
 */
public class TransactionsFilter
{
	private UUID walletId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private Integer skip;

	private Integer take;

	public UUID getWalletId() {
		return walletId;
	}

	public void setWalletId(UUID walletId) {
		this.walletId = walletId;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public Integer getTake() {
		return take;
	}

	public void setTake(Integer take) {
		this.take = take;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}
}
