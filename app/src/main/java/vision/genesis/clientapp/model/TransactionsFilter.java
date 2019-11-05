package vision.genesis.clientapp.model;

import io.swagger.client.model.TransactionFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2019.
 */
public class TransactionsFilter
{
	private String walletCurrency;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private Integer skip;

	private Integer take;

	private TransactionFilter type;

	public String getWalletCurrency() {
		return walletCurrency;
	}

	public void setWalletCurrency(String walletCurrency) {
		this.walletCurrency = walletCurrency;
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

	public TransactionFilter getType() {
		return type;
	}

	public void setType(TransactionFilter type) {
		this.type = type;
	}
}
