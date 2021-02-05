package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class TerminalPresenter extends MvpPresenter<TerminalView>
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription levelsInfoSubscription;

	private String selectedSymbol;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		getLevelsInfo(selectedSymbol);
	}

	@Override
	public void onDestroy() {
		if (levelsInfoSubscription != null) {
			levelsInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	//	private void getLevelsInfo(String currency) {
//		if (settingsManager != null && selectedSymbol != null) {
//			getViewState().showProgressBar(true);
//			levelsInfoSubscription = settingsManager.getLevelsInfo(currency)
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.io())
//					.subscribe(this::handleGetLevelsInfoSuccess,
//							this::handleGetLevelsInfoError);
//		}
//	}
//
//	private void handleGetLevelsInfoSuccess(ProgramsLevelsInfo response) {
//		levelsInfoSubscription.unsubscribe();
//		getViewState().showProgressBar(false);
//
//		getViewState().setLevelsInfo(response.getLevels());
//	}
//
//	private void handleGetLevelsInfoError(Throwable throwable) {
//		levelsInfoSubscription.unsubscribe();
//		getViewState().showProgressBar(false);
//
//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
//	}
	void onSymbolChanged(String selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
		getViewState().showProgressBar(false);
		getViewState().setSelectedSymbol(this.selectedSymbol);
//		getLevelsInfo(this.selectedSymbol);
	}
}
