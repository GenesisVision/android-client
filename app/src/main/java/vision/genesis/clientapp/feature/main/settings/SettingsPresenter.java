package vision.genesis.clientapp.feature.main.settings;

import android.content.Context;
import android.os.Build;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.SettingsModel;
import vision.genesis.clientapp.model.events.OnThemeChangedEvent;
import vision.genesis.clientapp.model.events.ShowDisableTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowSetPinActivityEvent;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.utils.ThemeUtil;

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

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && authManager.hasFingerprintFeature()) {
			getViewState().showFingerprintOption();
		}

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

	public void onFingerprintClicked() {
		if (!settingsModel.isPinCodeEnabled()) {
			getViewState().showDialogMessage(context.getString(R.string.error_enable_pin_first));
		}
		else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (authManager.hasEnrolledFingerprints()) {
				if (!settingsModel.isFingerprintEnabled())
					getViewState().showEnableFingerprint();
				else
					getViewState().showDisableFingerprint();
			}
			else {
				getViewState().showDialogMessage(context.getString(R.string.error_no_fingerprints));
			}
		}
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

	public void disableFingerprint() {
		settingsManager.setFingerprintEnabled(false);
	}

	public void onDarkThemeClicked(boolean checked) {
		String newTheme = checked ? ThemeUtil.THEME_LIGHT : ThemeUtil.THEME_DARK;
		settingsManager.setTheme(newTheme);
		EventBus.getDefault().post(new OnThemeChangedEvent());
		getViewState().changeThemeWithAnim(newTheme);
	}
}
