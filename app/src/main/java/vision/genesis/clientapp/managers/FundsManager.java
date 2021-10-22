package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.AssetsApi;
import io.swagger.client.api.EventsApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundDetailsListItem;
import io.swagger.client.model.FundDetailsListItemItemsViewModel;
import io.swagger.client.model.FundHistoryEventViewModelItemsViewModel;
import io.swagger.client.model.FundProfitPercentCharts;
import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.FundsFilterSorting;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import io.swagger.client.model.ReallocationModelItemsViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class FundsManager
{
	private final FundsApi fundsApi;

	private final InvestmentsApi investmentsApi;

	private final AssetsApi assetsApi;

	private final EventsApi eventsApi;

	public FundsManager(FundsApi fundsApi, InvestmentsApi investmentsApi, AssetsApi assetsApi, EventsApi eventsApi) {
		this.fundsApi = fundsApi;
		this.investmentsApi = investmentsApi;
		this.assetsApi = assetsApi;
		this.eventsApi = eventsApi;
	}

	public Observable<FundDetailsListItemItemsViewModel> getFundsList(ProgramsFilter filter) {
		return fundsApi.getFunds(
				FundsFilterSorting.fromValue(filter.getSorting().getValue()), Currency.fromValue(filter.getShowIn().getValue()),
				filter.getAssets(), null, filter.getIncludeWithInvestments(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(),
				filter.getIsFavorite(), false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setFundFavorite(UUID fundId, boolean isFavorite) {
		return isFavorite ? fundFavoritesAdd(fundId) : fundFavoritesRemove(fundId);
	}

	private Observable<Void> fundFavoritesAdd(UUID fundId) {
		return fundsApi.addToFavorites(fundId);
	}

	private Observable<Void> fundFavoritesRemove(UUID fundId) {
		return fundsApi.removeFromFavorites(fundId);
	}

	public Observable<FundDetailsFull> getFundDetails(UUID fundId, CurrencyEnum baseCurrency) {
		return fundsApi.getFundDetails(fundId.toString(), Currency.fromValue(baseCurrency.getValue()), ImageQuality.HIGH);
	}

//	public Observable<FundAssetsListInfo> getFundAssets(UUID fundId) {
//		return fundsApi.v10FundsByIdAssetsGet(fundId);
//	}

	public Observable<FundProfitPercentCharts> getProfitPercentChart(UUID fundId, DateRange dateRange, Integer maxPointCount,
	                                                                 Currency currency, List<Currency> currencies, Integer chartAssetsCount) {
		return fundsApi.getFundProfitPercentCharts(fundId, dateRange.getFrom(), dateRange.getTo(),
				maxPointCount, currency, currencies, chartAssetsCount);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID fundId, DateRange dateRange,
	                                                              Integer maxPointCount, Currency currency) {
		return fundsApi.getFundAbsoluteProfitChart(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<FundBalanceChart> getBalanceChart(UUID fundId, DateRange dateRange, Integer maxPointCount, Currency currency) {
		return fundsApi.getFundBalanceChart(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<InvestmentEventViewModels> getEvents(UUID fundId, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(InvestmentEventLocation.ASSET, fundId,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
				null,
				null, null,
				skip, take);
	}

//	public Observable<FundInvestInfo> getInvestInfo(UUID programId, String baseCurrency) {
//		return investmentsApi.v10InvestorFundsByIdInvestInfoByCurrencyGet(programId, baseCurrency);
//	}

	public Observable<FundWithdrawInfo> getWithdrawInfo(UUID programId, Currency baseCurrency) {
		return investmentsApi.getFundWithdrawInfo(programId, baseCurrency);
	}

	public Observable<Void> invest(FundRequest fundRequest) {
		return investmentsApi.investIntoFund(fundRequest.getFundId(), fundRequest.getAmount(), fundRequest.getWalletId());
	}

	public Observable<Void> withdraw(FundRequest fundRequest) {
		return investmentsApi.withdrawFromFund(fundRequest.getFundId(), fundRequest.getAmount(), Currency.fromValue(fundRequest.getWalletCurrency()));
	}

	public Observable<ReallocationModelItemsViewModel> getReallocateHistory(UUID fundId, DateRange dateRange, int skip, int take) {
		return fundsApi.getReallocatingHistory(fundId, dateRange.getFrom(), dateRange.getTo(), skip, take);
	}

	public Observable<FundDetailsListItem> getChallengeWinner() {
		return fundsApi.getLastChallengeWinner(null);
	}

	public Observable<FundHistoryEventViewModelItemsViewModel> getHistory(UUID fundId, DateRange dateRange, int skip, int take) {
		return fundsApi.getFundsHistoryEvents(fundId, dateRange.getFrom(), dateRange.getTo(), null, skip, take);
	}
}