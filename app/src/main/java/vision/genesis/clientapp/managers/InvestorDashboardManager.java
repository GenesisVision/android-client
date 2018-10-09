package vision.genesis.clientapp.managers;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.ProgramsList;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2018.
 */

public class InvestorDashboardManager
{
	private InvestorApi investorApi;

	private BehaviorSubject<List<DashboardPortfolioEvent>> portfolioEventsSubject = BehaviorSubject.create();

	public InvestorDashboardManager(InvestorApi investorApi) {
		this.investorApi = investorApi;
	}

	public Observable<DashboardSummary> getDashboard(DateRange dateRange, String baseCurrency) {
		return investorApi.v10InvestorGet(AuthManager.token.getValue(), null, dateRange.getFrom(), dateRange.getTo(),
				null, null, 0, 10, baseCurrency, dateRange.getFrom(), dateRange.getTo(), 0, 100, 0, 100);
	}

	public Observable<ProgramsList> getPrograms(String sorting, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorProgramsGet(AuthManager.token.getValue(), sorting, dateRange.getFrom(), dateRange.getTo(), 10, null, skip, take);
	}

	public BehaviorSubject<List<DashboardPortfolioEvent>> getPortfolioEvents() {
		updatePortfolioEvents();
//		mockUpdatePortfolioEvents();
		return portfolioEventsSubject;
	}

	public BehaviorSubject<List<DashboardPortfolioEvent>> getProgramEvents() {
		updatePortfolioEvents();
//		mockUpdatePortfolioEvents();
		return portfolioEventsSubject;
	}

	private void updatePortfolioEvents() {
		investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(),
				null, null, null, null, null, 0, 10)
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleUpdatePortfolioEventsResponse,
						this::handleUpdatePortfolioEventsError);
	}

	private void handleUpdatePortfolioEventsResponse(DashboardPortfolioEvents response) {
		portfolioEventsSubject.onNext(response.getEvents());
	}

	private void handleUpdatePortfolioEventsError(Throwable error) {
		portfolioEventsSubject.onError(error);
	}

	private void mockUpdatePortfolioEvents() {
		List<DashboardPortfolioEvent> events = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			DashboardPortfolioEvent event = new DashboardPortfolioEvent();
			event.setDate(DateTime.now().minusSeconds(new Random().nextInt(100000)));
			event.setValue(new Random().nextDouble() * 100 - 50);
//			event.setDescription("BlockChainTrader program was reinvested");
			events.add(event);
		}
		portfolioEventsSubject.onNext(events);
	}
}