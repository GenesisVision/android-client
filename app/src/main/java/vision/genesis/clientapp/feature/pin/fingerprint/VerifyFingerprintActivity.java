package vision.genesis.clientapp.feature.pin.fingerprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/06/2018.
 */

public class VerifyFingerprintActivity extends BaseSwipeBackActivity implements VerifyFingerprintView
{
	public static final int ENABLE_FINGERPRINT_REQUEST_CODE = 403;

	public static final int DISABLE_FINGERPRINT_REQUEST_CODE = 404;

	private static final String EXTRA_REQUEST_CODE = "request_code";

	public static void startWith(Activity activity, int requestCode) {
		Intent intent = new Intent(activity, VerifyFingerprintActivity.class);
		intent.putExtra(EXTRA_REQUEST_CODE, requestCode);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.image_fingerprint)
	public ImageView fingerprintImage;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.fingerprint_hint)
	public TextView fingerprintHint;

	@BindView(R.id.fingerprint_error)
	public TextView fingerprintError;

	@InjectPresenter
	VerifyFingerprintPresenter verifyFingerprintPresenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_verify_fingerprint);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null) {
			int requestCode = getIntent().getExtras().getInt(EXTRA_REQUEST_CODE);
			verifyFingerprintPresenter.setRequestCode(requestCode);
			setHint(requestCode);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		verifyFingerprintPresenter.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		verifyFingerprintPresenter.onStop();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void setHint(int requestCode) {
		switch (requestCode) {
			case ENABLE_FINGERPRINT_REQUEST_CODE:
				fingerprintHint.setText(getString(R.string.touch_the_sensor_to_enable));
				break;
			case DISABLE_FINGERPRINT_REQUEST_CODE:
				fingerprintHint.setText(getString(R.string.touch_the_sensor_to_disable));
				break;
		}
	}

	@Override
	public void shakeFingerprint() {
		Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake_horizontal);
		fingerprintImage.startAnimation(animShake);
	}

	@Override
	public void disableFingerprint(String message) {
		fingerprintImage.setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorRed));
		fingerprintError.setText(message);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onAuthenticationSucceeded() {
		fingerprintImage.setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}