package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.EventsApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundProfitCharts;
import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import io.swagger.client.model.ItemsViewModelFundDetailsList;
import io.swagger.client.model.ItemsViewModelReallocationModel;
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

	private final EventsApi eventsApi;

	public FundsManager(FundsApi fundsApi, InvestmentsApi investmentsApi, EventsApi eventsApi) {
		this.fundsApi = fundsApi;
		this.investmentsApi = investmentsApi;
		this.eventsApi = eventsApi;
	}

	public Observable<ItemsViewModelFundDetailsList> getFundsList(ProgramsFilter filter) {
		return fundsApi.getFunds(AuthManager.token.getValue(),
				null, null,
				null, null,
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getIsFavorite(),
				filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setFundFavorite(UUID fundId, boolean isFavorite) {
		return isFavorite ? fundFavoritesAdd(fundId) : fundFavoritesRemove(fundId);
	}

	private Observable<Void> fundFavoritesAdd(UUID fundId) {
		return fundsApi.addToFavorites(fundId, AuthManager.token.getValue());
	}

	private Observable<Void> fundFavoritesRemove(UUID fundId) {
		return fundsApi.removeFromFavorites(fundId, AuthManager.token.getValue());
	}

	public Observable<FundDetailsFull> getFundDetails(UUID fundId, CurrencyEnum baseCurrency) {
		return fundsApi.getFundDetails(fundId.toString(), AuthManager.token.getValue(), baseCurrency.getValue());
	}

//	public Observable<FundAssetsListInfo> getFundAssets(UUID fundId) {
//		return fundsApi.v10FundsByIdAssetsGet(fundId);
//	}

	public Observable<FundProfitCharts> getProfitChart(UUID fundId, DateRange dateRange, Integer maxPointCount) {
		return fundsApi.getFundProfitChart(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null, null, null);
	}

	public Observable<FundBalanceChart> getBalanceChart(UUID fundId, DateRange dateRange, Integer maxPointCount) {
		return fundsApi.getFundBalanceChart(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null);
	}

	public Observable<InvestmentEventViewModels> getEvents(UUID fundId, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(AuthManager.token.getValue(), InvestmentEventLocation.ASSET.getValue(), fundId,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
				skip, take);
	}

//	public Observable<FundInvestInfo> getInvestInfo(UUID programId, String baseCurrency) {
//		return investmentsApi.v10InvestorFundsByIdInvestInfoByCurrencyGet(programId, baseCurrency, AuthManager.token.getValue());
//	}

	public Observable<FundWithdrawInfo> getWithdrawInfo(UUID programId, String baseCurrency) {
		return investmentsApi.getFundWithdrawInfo(programId, baseCurrency, AuthManager.token.getValue());
	}

	public Observable<Void> invest(FundRequest fundRequest) {
		return investmentsApi.investIntoFund(fundRequest.getFundId(), AuthManager.token.getValue(), fundRequest.getAmount(), fundRequest.getWalletId());
	}

	public Observable<Void> withdraw(FundRequest fundRequest) {
		return investmentsApi.withdrawFromFund(fundRequest.getFundId(), AuthManager.token.getValue(), fundRequest.getAmount(), fundRequest.getWalletCurrency());
	}

	public Observable<ItemsViewModelReallocationModel> getReallocateHistory(UUID fundId, DateRange dateRange, int skip, int take) {
		return fundsApi.getReallocatingHistory(fundId, dateRange.getFrom(), dateRange.getTo(), skip, take);
	}
}