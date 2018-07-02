package vision.genesis.clientapp.feature.main.program.description;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProgramsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

@InjectViewState
public class ProgramDescriptionPresenter extends MvpPresenter<ProgramDescriptionView>
{
	@Inject
	public ProgramsManager programsManager;

	private Subscription programDetailsSubscription;

	private UUID programId;

	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getProgramDetails();
	}

	@Override
	public void onDestroy() {
		if (programDetailsSubscription != null)
			programDetailsSubscription.unsubscribe();

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		getProgramDetails();
	}

	void onShow() {
		getProgramDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getProgramDetails();
	}

	private void getProgramDetails() {
		if (programId != null && programsManager != null) {
			if (programDetailsSubscription != null)
				programDetailsSubscription.unsubscribe();
			programDetailsSubscription = programsManager.getInvestmentProgramDetails(programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestmentProgramDetailsSuccess,
							this::handleInvestmentProgramDetailsError);
		}
	}

	private void handleInvestmentProgramDetailsSuccess(InvestmentProgramViewModel model) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setProgramDescription(model.getInvestmentProgram());
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);
	}
}
