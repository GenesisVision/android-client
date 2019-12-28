package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.EventsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import io.swagger.client.model.ItemsViewModelProgramDetailsListItem;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.ProgramWithdrawInfo;
import io.swagger.client.model.TradesSignalViewModel;
import io.swagger.client.model.TradesViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class ProgramsManager
{
	private final ProgramsApi programsApi;

	private final InvestmentsApi investmentsApi;

	private final EventsApi eventsApi;

	public ProgramsManager(ProgramsApi programsApi, InvestmentsApi investmentsApi, EventsApi eventsApi) {
		this.programsApi = programsApi;
		this.investmentsApi = investmentsApi;
		this.eventsApi = eventsApi;
	}

	public Observable<ItemsViewModelProgramDetailsListItem> getProgramsList(ProgramsFilter filter) {
		return programsApi.getPrograms(AuthManager.token.getValue(),
				null, filter.getShowIn().getValue(),
				filter.getTags(), filter.getCurrency() == null ? null : filter.getCurrency().getValue(),
				filter.getLevelMin(), filter.getLevelMax(),
				null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setProgramFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? programFavoritesAdd(programId) : programFavoritesRemove(programId);
	}

	private Observable<Void> programFavoritesAdd(UUID programId) {
		return programsApi.addToFavorites(programId, AuthManager.token.getValue());
	}

	private Observable<Void> programFavoritesRemove(UUID programId) {
		return programsApi.removeFromFavorites(programId, AuthManager.token.getValue());
	}

	public Observable<ProgramFollowDetailsFull> getProgramDetails(UUID programId) {
		return programsApi.getProgramDetails(programId.toString(), AuthManager.token.getValue());
	}

	public Observable<TradesViewModel> getProgramOpenPositions(UUID programId) {
		return programsApi.getProgramOpenTrades(programId, "ByDateDesc", null, null, null, 0, 1000);
	}

	public Observable<ProgramProfitPercentCharts> getProfitPercentChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency, List<Object> currencies) {
		return programsApi.getProgramProfitPercentCharts(programId, AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency, currencies);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency) {
		return programsApi.getProgramAbsoluteProfitChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<ProgramBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency) {
		return programsApi.getProgramBalanceChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<TradesSignalViewModel> getProgramTrades(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return programsApi.getAssetTrades(programId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, null, skip, take);
	}

	public Observable<InvestmentEventViewModels> getEvents(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(AuthManager.token.getValue(),
				InvestmentEventLocation.ASSET.getValue(), programId,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
				null,
				skip, take);
	}

//	public Observable<ProgramInvestInfo> getInvestInfo(UUID programId, String baseCurrency) {
//		return investorApi.v10InvestorProgramsByIdInvestInfoByCurrencyGet(programId, baseCurrency, AuthManager.token.getValue());
//	}

	public Observable<ProgramWithdrawInfo> getWithdrawInfo(UUID programId) {
		return investmentsApi.getProgramWithdrawInfo(programId, AuthManager.token.getValue());
	}

	public Observable<Void> invest(ProgramRequest investRequest) {
		return investmentsApi.investIntoProgram(investRequest.getProgramId(), AuthManager.token.getValue(), investRequest.getAmount(), investRequest.getWalletId());
	}

	public Observable<Void> withdraw(ProgramRequest withdrawRequest) {
		return investmentsApi.withdrawFromProgram(
				withdrawRequest.getProgramId(),
				AuthManager.token.getValue(),
				withdrawRequest.getAmount(),
				withdrawRequest.isWithdrawAll());
	}

	public Observable<Void> setReinvest(UUID programId, boolean reinvest) {
		return reinvest
				? investmentsApi.switchReinvestOn(programId, AuthManager.token.getValue())
				: investmentsApi.switchReinvestOff(programId, AuthManager.token.getValue());
	}

//	public Observable<LevelUpSummary> getRatingInfo() {
//		return programsApi.v10ProgramsLevelupSummaryGet(AuthManager.token.getValue());
//	}

	public Observable<ProgramPeriodsViewModel> getPeriodHistory(UUID programId, DateRange dateRange, int skip, int take) {
		return programsApi.getProgramPeriods(programId.toString(), AuthManager.token.getValue(),
				dateRange.getFrom(), dateRange.getTo(),
				null, null, null,
				skip, take);
	}
}