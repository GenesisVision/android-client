package vision.genesis.clientapp.feature.main.verification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.UserVerificationStatus;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.events.OnStartKycClickedEvent;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/11/2020.
 */

public class VerificationInfoActivity extends BaseSwipeBackActivity implements VerificationInfoView
{
	private static final String EXTRA_VERIFICATION_STATUS = "extra_verification_status";

	public static void startWith(Activity activity, UserVerificationStatus verificationStatus) {
		Intent intent = new Intent(activity.getApplicationContext(), VerificationInfoActivity.class);
		intent.putExtra(EXTRA_VERIFICATION_STATUS, verificationStatus);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.button_verify)
	public PrimaryButton verifyButton;

	@InjectPresenter
	VerificationInfoPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_verify)
	public void onVerifyClicked() {
		EventBus.getDefault().post(new OnStartKycClickedEvent());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_verification_info);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			UserVerificationStatus verificationStatus = (UserVerificationStatus) getIntent().getExtras().getSerializable(EXTRA_VERIFICATION_STATUS);
			presenter.setData(verificationStatus);
		}
		else {
			Timber.e("Passed empty model to AboutLevelsActivity");
			onBackPressed();
		}
	}

	@Override
	public void setVerifyButtonVisible(boolean visible) {
		this.verifyButton.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setVerifyButtonEnabled(boolean enabled) {
		this.verifyButton.setEnabled(enabled);
	}

	@Override
	public void setVerifyButtonText(String text) {
		this.verifyButton.setText(text);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}