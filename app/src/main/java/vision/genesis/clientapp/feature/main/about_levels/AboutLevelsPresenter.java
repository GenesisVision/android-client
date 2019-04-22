package vision.genesis.clientapp.feature.main.about_levels;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.ProgramsLevelsInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

@InjectViewState
public class AboutLevelsPresenter extends MvpPresenter<AboutLevelsView> implements SelectCurrencyFragment.OnCurrencyChangedListener
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription levelsInfoSubscription;

	private String selectedCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getLevelsInfo(selectedCurrency);
	}

	@Override
	public void onDestroy() {
		if (levelsInfoSubscription != null)
			levelsInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	private void getLevelsInfo(String currency) {
		if (settingsManager != null && selectedCurrency != null) {
			getViewState().showProgressBar(true);
			levelsInfoSubscription = settingsManager.getLevelsInfo(currency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetLevelsInfoSuccess,
							this::handleGetLevelsInfoError);
		}
	}

	private void handleGetLevelsInfoSuccess(ProgramsLevelsInfo response) {
		levelsInfoSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		getViewState().setLevelsInfo(response.getLevels());
	}

	private void handleGetLevelsInfoError(Throwable throwable) {
		levelsInfoSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onCurrencyChanged(CurrencyEnum currency) {
		this.selectedCurrency = currency.getValue();
		getViewState().setSelectedCurrency(selectedCurrency);
		getLevelsInfo(selectedCurrency);
	}
}
