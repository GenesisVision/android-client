package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.TradingaccountApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.AccountProfitPercentCharts;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.TradesSignalViewModel;
import io.swagger.client.model.TradesViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountManager
{
	private final TradingaccountApi tradingAccountApi;

	public TradingAccountManager(TradingaccountApi tradingAccountApi) {
		this.tradingAccountApi = tradingAccountApi;
	}

	public Observable<PrivateTradingAccountFull> getAccountDetails(UUID accountId) {
		return tradingAccountApi.getTradingAccountDetails(accountId, AuthManager.token.getValue());
	}

	public Observable<TradesViewModel> getAccountOpenPositions(UUID accountId) {
		return tradingAccountApi.getOpenTrades(accountId, AuthManager.token.getValue(), "ByDateDesc",
				null, null, null,
				0, 1000);
	}

	public Observable<AccountProfitPercentCharts> getProfitPercentChart(UUID accountId, DateRange dateRange, Integer maxPointCount, String currency, List<Object> currencies) {
		return tradingAccountApi.getProfitPercentCharts(accountId, AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency, currencies);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID accountId, DateRange dateRange, Integer maxPointCount, String currency) {
		return tradingAccountApi.getAbsoluteProfitChart(accountId, AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}


	public Observable<AccountBalanceChart> getBalanceChart(UUID accountId, DateRange dateRange, Integer maxPointCount, String currency) {
		return tradingAccountApi.getBalanceChart(accountId, AuthManager.token.getValue(),
				dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<TradesSignalViewModel> getTrades(UUID accountId, DateRange dateRange, Boolean isFollow, Integer skip, Integer take) {
		return tradingAccountApi.getTrades(accountId, AuthManager.token.getValue(),
				dateRange.getFrom(), dateRange.getTo(),
				null, null, null, null, isFollow,
				skip, take);
	}

}