package vision.genesis.clientapp.feature.main.follow.create.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramCreateAssetPlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.ProgramSettingsModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

@InjectViewState
public class FollowSettingsPresenter extends MvpPresenter<FollowSettingsView>
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private double entryFee = 0;

	private double successFee = 0;

	private double maxEntryFee = 0;

	private double maxSuccessFee = 0;

	private ProgramSettingsModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setModel(ProgramSettingsModel model) {
		this.model = model;
//		if (model.getStopOutLevel() != null) {
//			getViewState().setStopOutLevel(model.getStopOutLevel());
//		}
//		if (model.getInvestmentLimit() != null) {
//			getViewState().setInvestmentLimit(model.getInvestmentLimit());
//		}
		if (model.getEntryFee() != null) {
			getViewState().setEntryFee(model.getEntryFee());
		}
		if (model.getSuccessFee() != null) {
			getViewState().setSuccessFee(model.getSuccessFee());
		}
		getPlatformInfo();
	}

	void onEntryFeeChanged(String entryFeeString) {
		try {
			entryFee = Double.parseDouble(entryFeeString);
		} catch (NumberFormatException e) {
			entryFee = 0;
		}
		if (entryFee > maxEntryFee) {
			getViewState().setEntryFee(maxEntryFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onSuccessFeeChanged(String exitFeeString) {
		try {
			successFee = Double.parseDouble(exitFeeString);
		} catch (NumberFormatException e) {
			successFee = 0;
		}
		if (successFee > maxSuccessFee) {
			getViewState().setSuccessFee(maxSuccessFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onConfirmClicked() {

	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(entryFee <= maxEntryFee && successFee <= maxSuccessFee);
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
		ProgramCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getProgramInfo().getCreateProgramInfo();
		maxEntryFee = info.getMaxEntryFee();
		maxSuccessFee = info.getMaxSuccessFee();
		getViewState().updateEntryFeeDescription(maxEntryFee);
		getViewState().updateSuccessFeeDescription(maxSuccessFee);

//		if (model.getPeriodLength() != null) {
//			getViewState().setPeriodLength(model.getPeriodLength());
//		}
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
