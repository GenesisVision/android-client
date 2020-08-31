package vision.genesis.clientapp.feature.main.unregistered.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/08/2020.
 */

public class UnregisteredSettingsFragment extends BaseFragment implements UnregisteredSettingsView
{
	@BindView(R.id.version)
	public TextView version;

	@InjectPresenter
	UnregisteredSettingsPresenter presenter;

	private Unbinder unbinder;

	@OnClick(R.id.terms_conditions)
	public void onTermsConditionsClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_and_conditions_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.privacy_policy)
	public void onPrivacyPolicyClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.contact_us)
	public void onContactUsClicked() {
		showFeedbackDialog();
	}

	@OnClick(R.id.button_get_started)
	public void onGetStartedClicked() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_unregistered_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		version.setText(String.format(Locale.getDefault(), "Version %s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
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
		CharSequence[] options = new CharSequence[]{getString(R.string.visit_feedback_website), getString(R.string.send_email)};

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.feedback_dialog_title));
		builder.setItems(options, (dialog, optionId) -> {
			if (optionId == 0) {
				openFeedbackSite();
			}
			else {
				sendFeedbackEmail();
			}
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

	@Override
	public void onShow() {
		presenter.onResume();
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, version);
	}
}