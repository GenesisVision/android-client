package vision.genesis.clientapp.feature.main.settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.SettingsModel;
import vision.genesis.clientapp.model.events.ShowDisableTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowSetPinActivityEvent;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription profileSubscription;

	private Subscription settingsSubscription;

	private SettingsModel settingsModel;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getProfileInfo();
		subscribeToSettings();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null)
			profileSubscription.unsubscribe();
		if (settingsSubscription != null)
			settingsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
	}

	public void onTwoFactorClicked() {
		EventBus.getDefault().post(settingsModel.isTwoFactorEnabled()
				? new ShowDisableTfaActivityEvent()
				: new ShowSetupTfaActivityEvent());
	}

	public void onPinCodeClicked() {
		if (!settingsModel.isPinCodeEnabled())
			EventBus.getDefault().post(new ShowSetPinActivityEvent());
		else
			getViewState().showDisablePin();
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);

	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		getViewState().updateProfile(profile);
	}

	private void handleGetProfileError(Throwable throwable) {

	}

	private void subscribeToSettings() {
		settingsSubscription = settingsManager.getSettingsSubject()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleSettingsUpdated,
						this::handleSettingsError);

	}

	private void handleSettingsUpdated(SettingsModel settingsModel) {
		this.settingsModel = settingsModel;
		getViewState().updateSettings(settingsModel);
	}

	private void handleSettingsError(Throwable throwable) {

	}

	public void onLogoutClicked() {
		authManager.logout();
	}

	public void disablePin() {
		settingsManager.setPinCodeEnabled(false);
	}
}
