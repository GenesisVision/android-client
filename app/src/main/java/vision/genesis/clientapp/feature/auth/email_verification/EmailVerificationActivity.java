package vision.genesis.clientapp.feature.auth.email_verification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class EmailVerificationActivity extends MvpAppCompatActivity implements EmailVerificationView
{
	public static void startFrom(Activity activity) {
		activity.startActivity(new Intent(activity, EmailVerificationActivity.class));
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@InjectPresenter
	EmailVerificationPresenter emailVerificationPresenter;

	@OnClick(R.id.button_ok)
	public void onOkClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_email_verification);

		ButterKnife.bind(this);
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}