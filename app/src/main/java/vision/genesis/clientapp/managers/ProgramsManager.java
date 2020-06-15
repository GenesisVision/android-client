package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.EventsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramDetailsListItemItemsViewModel;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.ProgramWithdrawInfo;
import io.swagger.client.model.ProgramsFilterSorting;
import io.swagger.client.model.TradeSorting;
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

	public Observable<ProgramDetailsListItemItemsViewModel> getProgramsList(ProgramsFilter filter) {
		return programsApi.getPrograms(
				filter.getSorting() != null ? ProgramsFilterSorting.fromValue(filter.getSorting().getValue()) : null,
				filter.getShowIn() != null ? Currency.fromValue(filter.getShowIn().getValue()) : null,
				filter.getTags(),
				filter.getCurrency() == null ? null : Currency.fromValue(filter.getCurrency().getValue()),
				filter.getLevelMin(), filter.getLevelMax(),
				null, null, false,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(),
				false, false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setProgramFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? programFavoritesAdd(programId) : programFavoritesRemove(programId);
	}

	private Observable<Void> programFavoritesAdd(UUID programId) {
		return programsApi.addToFavorites(programId);
	}

	private Observable<Void> programFavoritesRemove(UUID programId) {
		return programsApi.removeFromFavorites(programId);
	}

	public Observable<ProgramFollowDetailsFull> getProgramDetails(UUID programId) {
		return programsApi.getProgramDetails(programId.toString(), ImageQuality.HIGH);
	}

	public Observable<TradesViewModel> getProgramOpenPositions(UUID programId) {
		return programsApi.getProgramOpenTrades(programId, TradeSorting.BYDATEDESC, null, null, null, 0, 1000);
	}

	public Observable<ProgramProfitPercentCharts> getProfitPercentChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency, List<Currency> currencies) {
		return programsApi.getProgramProfitPercentCharts(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency, currencies);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency) {
		return programsApi.getProgramAbsoluteProfitChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<ProgramBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency) {
		return programsApi.getProgramBalanceChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<TradesSignalViewModel> getProgramTrades(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return programsApi.getAssetTrades(programId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, null, skip, take);
	}

	public Observable<InvestmentEventViewModels> getEvents(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(
				InvestmentEventLocation.ASSET, programId,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
				null,
				skip, take);
	}

//	public Observable<ProgramInvestInfo> getInvestInfo(UUID programId, String baseCurrency) {
//		return investorApi.v10InvestorProgramsByIdInvestInfoByCurrencyGet(programId, baseCurrency);
//	}

	public Observable<ProgramWithdrawInfo> getWithdrawInfo(UUID programId) {
		return investmentsApi.getProgramWithdrawInfo(programId);
	}

	public Observable<Void> invest(ProgramRequest investRequest) {
		return investmentsApi.investIntoProgram(investRequest.getProgramId(), investRequest.getAmount(), investRequest.getWalletId());
	}

	public Observable<Void> withdraw(ProgramRequest withdrawRequest) {
		return investmentsApi.withdrawFromProgram(
				withdrawRequest.getProgramId(),
				withdrawRequest.getAmount(),
				withdrawRequest.isWithdrawAll());
	}

	public Observable<Void> setReinvest(UUID programId, boolean reinvest) {
		return reinvest
				? investmentsApi.switchReinvestOn(programId)
				: investmentsApi.switchReinvestOff(programId);
	}

	public Observable<Void> setIgnoreSo(UUID programId, boolean ignoreSo) {
		return ignoreSo
				? investmentsApi.switchAutoJoinOn(programId)
				: investmentsApi.switchAutoJoinOff(programId);
	}

//	public Observable<LevelUpSummary> getRatingInfo() {
//		return programsApi.v10ProgramsLevelupSummaryGet(AuthManager.token.getValue());
//	}

	public Observable<ProgramPeriodsViewModel> getPeriodHistory(UUID programId, DateRange dateRange, int skip, int take) {
		return programsApi.getProgramPeriods(programId.toString(),
				dateRange.getFrom(), dateRange.getTo(),
				null, null, null,
				skip, take);
	}
}