package vision.genesis.clientapp.feature.main.settings.security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.settings.security.change_password.ChangePasswordActivity;
import vision.genesis.clientapp.feature.pin.check.CheckPinActivity;
import vision.genesis.clientapp.feature.pin.fingerprint.VerifyFingerprintActivity;
import vision.genesis.clientapp.feature.pin.set.SetPinActivity;
import vision.genesis.clientapp.feature.two_factor.disable.DisableTfaActivity;
import vision.genesis.clientapp.feature.two_factor.setup.SetupTfaActivity;
import vision.genesis.clientapp.model.SettingsModel;
import vision.genesis.clientapp.ui.SettingsSwitchButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

public class SecurityActivity extends BaseSwipeBackActivity implements SecurityView
{
	private static final int DISABLE_PIN_REQUEST_CODE = 402;

	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SecurityActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.two_factor)
	public SettingsSwitchButton twoFactor;

	@BindView(R.id.pin_code)
	public SettingsSwitchButton pinCode;

	@BindView(R.id.fingerprint)
	public SettingsSwitchButton fingerprint;

	@BindView(R.id.pin_code_delimiter)
	public View pinCodeDelimiter;

	@BindView(R.id.fingerprint_delimiter)
	public View fingerprintDelimiter;

	@InjectPresenter
	SecurityPresenter securityPresenter;

	@OnClick(R.id.change_password)
	public void onChangePasswordClicked() {
		ChangePasswordActivity.startFrom(this);
	}

	@OnClick(R.id.two_factor)
	public void onTwoFactorClicked() {
		securityPresenter.onTwoFactorClicked();
	}

	@OnClick(R.id.pin_code)
	public void onPinCodeClicked() {
		securityPresenter.onPinCodeClicked();
	}

	@OnClick(R.id.fingerprint)
	public void onFingerprintClicked() {
		securityPresenter.onFingerprintClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_security);

		ButterKnife.bind(this);
		setFonts();
		initSwitchOptions();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void initSwitchOptions() {
		twoFactor.setText(getString(R.string.two_factor_authentication));
		pinCode.setText(getString(R.string.pin_code));
		fingerprint.setText(getString(R.string.use_fingerprint));
	}

	@Override
	public void showFingerprintOption() {
		this.fingerprint.setVisibility(View.VISIBLE);
		this.fingerprintDelimiter.setVisibility(View.VISIBLE);
	}

	@Override
	public void updateSettings(SettingsModel settingsModel) {
		twoFactor.setChecked(settingsModel.isTwoFactorEnabled());
		pinCode.setChecked(settingsModel.isPinCodeEnabled());
		fingerprint.setChecked(settingsModel.isFingerprintEnabled());
	}

	@Override
	public void showDisablePin() {
		CheckPinActivity.startForResult(this, DISABLE_PIN_REQUEST_CODE, true);
	}

	@Override
	public void showEnableFingerprint() {
		VerifyFingerprintActivity.startWith(this, VerifyFingerprintActivity.ENABLE_FINGERPRINT_REQUEST_CODE);
	}

	@Override
	public void showSetupTfaActivity() {
		SetupTfaActivity.startFrom(this);
	}

	@Override
	public void showDisableTfaActivity() {
		DisableTfaActivity.startFrom(this);
	}

	@Override
	public void showSetPinActivity() {
		SetPinActivity.startFrom(this);
	}

	@Override
	public void showDisableFingerprint() {
		VerifyFingerprintActivity.startWith(this, VerifyFingerprintActivity.DISABLE_FINGERPRINT_REQUEST_CODE);
	}

	@Override
	public void showDialogMessage(String message) {
		showMessageDialog(message);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case DISABLE_PIN_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					securityPresenter.disablePin();
					securityPresenter.disableFingerprint();
				}
				break;
			default:
				break;
		}
	}
}