package vision.genesis.clientapp.feature.main.settings.security;

import android.content.Context;
import android.os.Build;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.SettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

@InjectViewState
public class SecurityPresenter extends MvpPresenter<SecurityView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription settingsSubscription;

	private SettingsModel settingsModel;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && authManager.hasFingerprintFeature()) {
			getViewState().showFingerprintOption();
		}

		subscribeToSettings();
	}

	@Override
	public void onDestroy() {
		if (settingsSubscription != null) {
			settingsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void onTwoFactorClicked() {
		if (settingsModel.isTwoFactorEnabled()) {
			getViewState().showDisableTfaActivity();
		}
		else {
			getViewState().showSetupTfaActivity();
		}
	}

	public void onPinCodeClicked() {
		if (!settingsModel.isPinCodeEnabled()) {
			getViewState().showSetPinActivity();
		}
		else {
			getViewState().showDisablePin();
		}
	}

	public void onFingerprintClicked() {
		if (!settingsModel.isPinCodeEnabled()) {
			getViewState().showDialogMessage(context.getString(R.string.error_enable_pin_first));
		}
		else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (authManager.hasEnrolledFingerprints()) {
				if (!settingsModel.isFingerprintEnabled()) {
					getViewState().showEnableFingerprint();
				}
				else {
					getViewState().showDisableFingerprint();
				}
			}
			else {
				getViewState().showDialogMessage(context.getString(R.string.error_no_fingerprints));
			}
		}
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

	public void disablePin() {
		settingsManager.setPinCodeEnabled(false);
	}

	public void disableFingerprint() {
		settingsManager.setFingerprintEnabled(false);
	}
}
