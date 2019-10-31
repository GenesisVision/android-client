package vision.genesis.clientapp.feature.main.fund.create.fees;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramsInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class CreateFundFeesPresenter extends MvpPresenter<CreateFundFeesView>
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private NewFundRequest request;

	private double entryFee = 0;

	private double exitFee = 0;

	private double maxEntryFee = 0;

	private double maxExitFee = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setRequest(NewFundRequest request) {
		this.request = request;
	}

	void onEntryFeeChanged(String entryFeeString) {
		try {
			entryFee = Double.parseDouble(entryFeeString);
		} catch (NumberFormatException e) {
			entryFee = 0;
		}
		if (entryFee > maxEntryFee) {
			getViewState().setEntryFeeText(maxEntryFee);
			return;
		}

		if (this.request != null) {
			this.request.setEntryFee(entryFee);
		}

		updateNextButtonAvailability();
	}

	void onExitFeeChanged(String exitFeeString) {
		try {
			exitFee = Double.parseDouble(exitFeeString);
		} catch (NumberFormatException e) {
			exitFee = 0;
		}
		if (exitFee > maxExitFee) {
			getViewState().setExitFeeText(maxExitFee);
			return;
		}

		if (this.request != null) {
			this.request.setExitFee(exitFee);
		}

		updateNextButtonAvailability();
	}

	private void updateNextButtonAvailability() {
		getViewState().setNextButtonEnabled(entryFee <= maxEntryFee && exitFee <= maxExitFee);
	}

	private void getPlatformInfo() {
		platformInfoSubscription = settingsManager.getPlatformInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetPlatformInfoSuccess,
						this::handleGetPlatformInfoError);
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();
		ProgramsInfo info = platformInfo.getProgramsInfo();
		maxEntryFee = info.getManagerMaxEntryFee();
		maxExitFee = info.getManagerMaxExitFee();
		getViewState().updateEntryFeeDescription(maxEntryFee);
		getViewState().updateExitFeeDescription(maxExitFee);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
