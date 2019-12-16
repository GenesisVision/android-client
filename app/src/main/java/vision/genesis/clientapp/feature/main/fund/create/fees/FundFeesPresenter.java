package vision.genesis.clientapp.feature.main.fund.create.fees;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.FundCreateAssetPlatformInfo;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.FundSettingsModel;
import vision.genesis.clientapp.model.events.OnFundFeesConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class FundFeesPresenter extends MvpPresenter<FundFeesView>
{
	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private double entryFee = 0;

	private double exitFee = 0;

	private double maxEntryFee = 0;

	private double maxExitFee = 0;

	private FundSettingsModel model;

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

	void setModel(FundSettingsModel model) {
		this.model = model;

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

	void onExitFeeChanged(String exitFeeString) {
		try {
			exitFee = Double.parseDouble(exitFeeString);
		} catch (NumberFormatException e) {
			exitFee = 0;
		}
		if (exitFee > maxExitFee) {
			getViewState().setExitFee(maxExitFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onConfirmClicked() {
		FundSettingsModel newModel = new FundSettingsModel();
		newModel.setEntryFee(entryFee);
		newModel.setExitFee(exitFee);
		EventBus.getDefault().post(new OnFundFeesConfirmEvent(newModel));
	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(entryFee <= maxEntryFee && exitFee <= maxExitFee);
	}

	private void getPlatformInfo() {
		if (settingsManager != null && model != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();
		FundCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getFundInfo().getCreateFundInfo();
		maxEntryFee = info.getMaxEntryFee();
		maxExitFee = info.getMaxExitFee();
		getViewState().updateEntryFeeDescription(maxEntryFee);
		getViewState().updateExitFeeDescription(maxExitFee);

		if (model.getEntryFee() != null) {
			getViewState().setEntryFee(model.getEntryFee());
		}
		if (model.getExitFee() != null) {
			getViewState().setExitFee(model.getExitFee());
		}
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
