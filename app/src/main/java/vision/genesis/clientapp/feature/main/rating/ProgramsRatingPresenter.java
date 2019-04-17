package vision.genesis.clientapp.feature.main.rating;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.LevelUpSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

@InjectViewState
public class ProgramsRatingPresenter extends MvpPresenter<ProgramsRatingView>
{
	@Inject
	public ProgramsManager programsManager;

	private Subscription ratingInfoSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getRatingInfo();
	}

	@Override
	public void onDestroy() {
		if (ratingInfoSubscription != null)
			ratingInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	private void getRatingInfo() {
		ratingInfoSubscription = programsManager.getRatingInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetRatingInfoSuccess,
						this::handleGetRatingInfoError);
	}

	private void handleGetRatingInfoSuccess(LevelUpSummary response) {
		ratingInfoSubscription.unsubscribe();

		getViewState().setData(response.getLevelData());
	}

	private void handleGetRatingInfoError(Throwable throwable) {
		ratingInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
