package vision.genesis.clientapp.feature.main.trading_account.two_factor;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jakewharton.rxbinding.widget.RxTextView;

import net.glxn.qrgen.android.QRCode;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import rx.Subscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2021.
 */

public class SetupAccountTfaActivity extends BaseSwipeBackActivity implements SetupAccountTfaView
{
	private static final String EXTRA_ID = "extra_id";

	public static void startWith(Activity activity, UUID id) {
		Intent intent = new Intent(activity, SetupAccountTfaActivity.class);
		intent.putExtra(EXTRA_ID, id);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.progress_bar_button)
	public ProgressBar progressBarButton;

	@BindView(R.id.button_activate)
	public PrimaryButton activateButton;

	@BindView(R.id.image_qr_code)
	public ImageView qrCodeImage;

	@BindView(R.id.text_key)
	public TextView key;

	@BindView(R.id.edit_text_code)
	public EditText code;

	@InjectPresenter
	SetupAccountTfaPresenter presenter;

	private Subscription codeTextChangeSubscription;

	@OnClick(R.id.button_back)
	public void onBackButtonClicked() {
		onBackPressed();
	}

	@OnEditorAction(R.id.edit_text_code)
	public boolean onCodeEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			presenter.onActivateClicked();
		}
		return false;
	}

	@OnClick(R.id.text_key)
	public void onKeyClicked() {
		ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Activity.CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("key", this.key.getText().toString());
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(this, getString(R.string.copied_to_the_clipboard), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(this, getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}
	}

	@OnClick(R.id.button_activate)
	public void onActivateButtonClicked() {
		presenter.onActivateClicked();
	}

	@OnClick(R.id.button_install)
	public void onInstallButtonClicked() {
		final String appPackageName = "com.google.android.apps.authenticator2";
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
		} catch (android.content.ActivityNotFoundException e) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_account_two_factor);

		ButterKnife.bind(this);

		Bundle extras = getIntent().getExtras();

		if (extras != null && !extras.isEmpty()) {
			UUID id = (UUID) extras.getSerializable(EXTRA_ID);
			if (id != null) {
				presenter.setData(id);

				InputFilter[] filters = new InputFilter[1];
				filters[0] = new InputFilter.LengthFilter(Constants.TWO_FACTOR_CODE_LENGTH);
				code.setFilters(filters);

				return;
			}
		}

		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void onStart() {
		super.onStart();
		setTextListener();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (codeTextChangeSubscription != null) {
			codeTextChangeSubscription.unsubscribe();
		}
	}

	private void setTextListener() {
		codeTextChangeSubscription = RxTextView.textChanges(code)
				.subscribe(text -> presenter.onCodeChanged(text.toString()));
	}

	@Override
	public void setKey(String sharedKey, String authenticatorUri) {
		qrCodeImage.setImageBitmap(QRCode.from(authenticatorUri)
				.withColor(ThemeUtil.getColorByAttrId(this, R.attr.colorBackground),
						ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary))
				.withSize(1000, 1000)
				.withErrorCorrection(ErrorCorrectionLevel.H)
				.bitmap());
		key.setText(sharedKey);
	}

	@Override
	public void setActivateButtonAvailability(boolean enabled) {
		activateButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showButtonProgress(boolean show) {
		progressBarButton.setVisibility(show ? View.VISIBLE : View.GONE);
		activateButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbar(String text) {
		Snackbar.make(scrollView, text, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}