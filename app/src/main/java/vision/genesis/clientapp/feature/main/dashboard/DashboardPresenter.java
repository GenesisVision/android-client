package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramDashboard;
import io.swagger.client.model.InvestorDashboard;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class DashboardPresenter extends MvpPresenter<DashboardView>
{
	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription getInvestmentsSubscription;

	private List<InvestmentProgramDashboard> investmentPrograms = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void onResume() {
		getInvestments();
	}

	void onStartInvestingClicked() {
		EventBus.getDefault().post(new OnInvestButtonClickedEvent());
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getInvestments();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getInvestments();
	}

	private void getInvestments() {
		getInvestmentsSubscription = investManager.getInvestments()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetInvestmentsSuccess,
						this::handleGetInvestmentsError);
	}

	private void handleGetInvestmentsSuccess(InvestorDashboard dashboard) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

		investmentPrograms = new ArrayList<>();

		List<InvestmentProgramDashboard> programs = dashboard.getInvestmentPrograms();
		for (InvestmentProgramDashboard program : programs) {
			if (program.getInvestedTokens() > 0 || program.isHasNewRequests())
				investmentPrograms.add(program);
		}

		getViewState().setInvestorPrograms(investmentPrograms);
		getViewState().showEmpty(investmentPrograms.size() == 0);
	}

	private void handleGetInvestmentsError(Throwable throwable) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (investmentPrograms.size() == 0)
				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
