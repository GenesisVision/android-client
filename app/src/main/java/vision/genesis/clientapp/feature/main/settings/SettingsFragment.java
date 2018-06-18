package vision.genesis.clientapp.feature.main.settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProfileFullViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.profile.ProfileActivity;
import vision.genesis.clientapp.feature.main.profile.change_password.ChangePasswordActivity;
import vision.genesis.clientapp.feature.pin.check.CheckPinActivity;
import vision.genesis.clientapp.model.SettingsModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.SettingsSwitchButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class SettingsFragment extends BaseFragment implements SettingsView
{
	private static final int CHANGE_PASSWORD_REQUEST_CODE = 401;

	private static final int DISABLE_PIN_REQUEST_CODE = 402;

	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.profile_name)
	public TextView profileName;

	@BindView(R.id.profile_email)
	public TextView profileEmail;

	@BindView(R.id.two_factor)
	public SettingsSwitchButton twoFactor;

	@BindView(R.id.pin_code)
	public SettingsSwitchButton pinCode;

	@BindView(R.id.fingerprint)
	public SettingsSwitchButton fingerprint;

	@BindView(R.id.version)
	public TextView version;

	@InjectPresenter
	SettingsPresenter settingsPresenter;

	private Unbinder unbinder;

	@OnClick(R.id.profile)
	public void onProfileClicked() {
		ProfileActivity.startFrom(this);
	}

	@OnClick(R.id.change_password)
	public void onChangePasswordClicked() {
		ChangePasswordActivity.startWith(this, CHANGE_PASSWORD_REQUEST_CODE);
	}

	@OnClick(R.id.two_factor)
	public void onTwoFactorClicked() {
		settingsPresenter.onTwoFactorClicked();
	}

	@OnClick(R.id.pin_code)
	public void onPinCodeClicked() {
		settingsPresenter.onPinCodeClicked();
	}

	@OnClick(R.id.send_feedback)
	public void onSendFeedbackClicked() {
		showFeedbackDialog();
	}

	@OnClick(R.id.logout)
	public void onLogoutClicked() {
		showLogoutDialog();
	}

	@OnClick(R.id.privacy_policy)
	public void onPrivacyPolicyClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.terms_conditions)
	public void onTermsConditionsClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_and_conditions_address)));
		startActivity(browserIntent);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		version.setText(String.format(Locale.getDefault(), "Version %s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		avatar.hideLevel();

		initSwitchOptions();
	}

	private void initSwitchOptions() {
		twoFactor.setIcon(R.drawable.icon_tfa);
		pinCode.setIcon(R.drawable.icon_pin);
		fingerprint.setIcon(R.drawable.ic_fingerprint_black_24dp);

		twoFactor.setText(getString(R.string.two_factor_authentication));
		pinCode.setText(getString(R.string.pin_code));
		fingerprint.setText(getString(R.string.use_fingerprint));
	}

	@Override
	public void onResume() {
		super.onResume();

		settingsPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void showFeedbackDialog() {
		CharSequence options[] = new CharSequence[]{getString(R.string.visit_feedback_website), getString(R.string.send_email)};

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.feedback_dialog_title));
		builder.setItems(options, (dialog, optionId) -> {
			if (optionId == 0)
				openFeedbackSite();
			else
				sendFeedbackEmail();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	private void openFeedbackSite() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.feedback_web_address)));
		startActivity(browserIntent);
	}

	private void sendFeedbackEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");  //set the email recipient
		String recipient = getString(R.string.feedback_email_address);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android feedback");
		try {
			emailIntent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "\n\nversion %s (%d)\n%s %s\n%s %s",
					BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
					Build.MANUFACTURER, Build.MODEL,
					Build.VERSION.RELEASE, Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		startActivity(Intent.createChooser(emailIntent, "Send email using..."));
	}

	private void showLogoutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.are_you_sure_logout));
		builder.setPositiveButton(getString(R.string.log_out), (dialog, which) -> {
			settingsPresenter.onLogoutClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	public void onShow() {
		settingsPresenter.onResume();
	}

	@Override
	public void updateProfile(ProfileFullViewModel profile) {
		avatar.setImage(profile.getAvatar(), 50, 50);

		if (profile.getUserName() != null && !profile.getUserName().isEmpty()) {
			profileName.setText(profile.getUserName());
			profileName.setVisibility(View.VISIBLE);
		}
		else if (profile.getFirstName() != null && !profile.getFirstName().isEmpty()
				&& profile.getLastName() != null && !profile.getLastName().isEmpty()) {
			profileName.setText(String.format(Locale.getDefault(), "%s %s", profile.getFirstName(), profile.getLastName()));
			profileName.setVisibility(View.VISIBLE);
		}
		else {
			profileName.setVisibility(View.GONE);
		}
		profileEmail.setText(profile.getEmail());
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case CHANGE_PASSWORD_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					showSnackbar(getString(R.string.password_changed), avatar);
				}
				break;
			case DISABLE_PIN_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					settingsPresenter.disablePin();
				}
				break;
			default:
				break;
		}
	}
}