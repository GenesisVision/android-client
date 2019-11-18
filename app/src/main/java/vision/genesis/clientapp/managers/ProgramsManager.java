package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.EventsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import io.swagger.client.model.ItemsViewModelProgramDetailsList;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.ProgramProfitCharts;
import io.swagger.client.model.ProgramWithdrawInfo;
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

	public Observable<ItemsViewModelProgramDetailsList> getProgramsList(ProgramsFilter filter) {
		return programsApi.getPrograms(AuthManager.token.getValue(),
				null, null,
				filter.getTags(), filter.getCurrency() == null ? null : filter.getCurrency().getValue(),
				filter.getLevelMin(), filter.getLevelMax(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), false,
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

	public Observable<ProgramDetailsFull> getProgramDetails(UUID programId) {
		return programsApi.getProgramDetails(programId.toString(), AuthManager.token.getValue());
	}

	public Observable<TradesViewModel> getProgramOpenPositions(UUID programId) {
		return programsApi.getProgramOpenTrades(programId, "ByDateDesc", null, null, null, 0, 1000);
	}

	public Observable<ProgramProfitCharts> getProfitChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.getProgramProfitChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null, null);
	}

	public Observable<ProgramBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.getProgramBalanceChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null);
	}

	public Observable<TradesViewModel> getProgramTrades(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return programsApi.getProgramTrades(programId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, skip, take);
	}

	public Observable<InvestmentEventViewModels> getEvents(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(AuthManager.token.getValue(),
				InvestmentEventLocation.ASSET.getValue(), programId,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
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