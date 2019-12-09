package vision.genesis.clientapp.feature.main.follow.create.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.FollowCreateAssetPlatformInfo;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.FollowSettingsModel;
import vision.genesis.clientapp.model.events.OnFollowSettingsConfirmEvent;
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

	private double volumeFee = 0;

	private double successFee = 0;

	private double minVolumeFee = 0;

	private double minSuccessFee = 0;

	private double maxVolumeFee = 0;

	private double maxSuccessFee = 0;

	private FollowSettingsModel model;

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

	void setModel(FollowSettingsModel model) {
		this.model = model;

		getPlatformInfo();
	}

	void onVolumeFeeChanged(String entryFeeString) {
		try {
			volumeFee = Double.parseDouble(entryFeeString);
		} catch (NumberFormatException e) {
			volumeFee = 0;
		}
		if (volumeFee > maxVolumeFee) {
			getViewState().setVolumeFee(maxVolumeFee);
			return;
		}
		else if (volumeFee < minVolumeFee) {
			getViewState().setVolumeFee(minVolumeFee);
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
		else if (successFee < minSuccessFee) {
			getViewState().setSuccessFee(minSuccessFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onConfirmClicked() {
		EventBus.getDefault().post(new OnFollowSettingsConfirmEvent(volumeFee, successFee));
	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(volumeFee >= minVolumeFee && successFee >= minSuccessFee
				&& volumeFee <= maxVolumeFee && successFee <= maxSuccessFee);
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
		FollowCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getFollowInfo().getCreateFollowInfo();
		minVolumeFee = info.getMinVolumeFee();
		minSuccessFee = info.getMinSuccessFee();
		maxVolumeFee = info.getMaxVolumeFee();
		maxSuccessFee = info.getMaxSuccessFee();
		getViewState().updateVolumeFeeDescription(minVolumeFee, maxVolumeFee);
		getViewState().updateSuccessFeeDescription(minSuccessFee, maxSuccessFee);

		if (model.getVolumeFee() != null) {
			getViewState().setVolumeFee(model.getVolumeFee());
		}
		if (model.getSuccessFee() != null) {
			getViewState().setSuccessFee(model.getSuccessFee());
		}
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
