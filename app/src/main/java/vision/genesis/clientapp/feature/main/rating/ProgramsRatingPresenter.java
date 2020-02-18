package vision.genesis.clientapp.feature.main.rating;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.Currency;
import io.swagger.client.model.ProgramsLevelsInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

@InjectViewState
public class ProgramsRatingPresenter extends MvpPresenter<ProgramsRatingView>
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription ratingInfoSubscription;

	private Integer selectedLevel = 0;

	private FacetModel facetModel;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getLevels();
	}

	@Override
	public void onDestroy() {
		if (ratingInfoSubscription != null) {
			ratingInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(FacetModel model) {
		this.facetModel = model;
		getLevels();
	}

	private void getLevels() {
		if (settingsManager != null && facetModel != null) {
			getViewState().showProgress(true);
			ratingInfoSubscription = settingsManager.getLevelsInfo(Currency.GVT.getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetLevelsSuccess,
							this::handleGetLevelsError);
		}
	}

	private void handleGetLevelsSuccess(ProgramsLevelsInfo response) {
		ratingInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setData(response.getLevels());

		getViewState().showAllLevels();
	}

	private void handleGetLevelsError(Throwable throwable) {
		ratingInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	void onLevelClicked(Integer level) {
		if (!selectedLevel.equals(level)) {
			getViewState().showLevel(level);
			this.selectedLevel = level;
		}
		else {
			getViewState().showAllLevels();
			this.selectedLevel = 0;
		}
	}
}
