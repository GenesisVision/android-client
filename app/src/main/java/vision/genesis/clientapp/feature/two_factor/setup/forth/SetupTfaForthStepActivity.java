package vision.genesis.clientapp.feature.two_factor.setup.forth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PrimaryButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public class SetupTfaForthStepActivity extends MvpAppCompatActivity
{
	private static String EXTRA_RECOVERY_CODES = "extra_recovery_codes";

	public static void startWith(Activity activity, ArrayList<String> recoveryCodes) {
		Intent intent = new Intent(activity.getApplicationContext(), SetupTfaForthStepActivity.class);
		intent.putStringArrayListExtra(EXTRA_RECOVERY_CODES, recoveryCodes);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.text_left_column)
	public TextView leftColumn;

	@BindView(R.id.text_right_column)
	public TextView rightColumn;

	@BindView(R.id.button_ok)
	public PrimaryButton okButton;

	@OnClick(R.id.button_ok)
	public void onOkButtonClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_setup_tfa_forth_step);

		ButterKnife.bind(this);

		Bundle extras = getIntent().getExtras();

		if (extras != null && !extras.isEmpty()) {
			setRecoveryCodes(extras.getStringArrayList(EXTRA_RECOVERY_CODES));
		}
		else {
			Timber.e("Passed empty params to SetupTfaForthStepActivity");
			onBackPressed();
		}

		okButton.setWhite();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void onBackPressed() {
	}

	public void setRecoveryCodes(List<String> codes) {
		for (int i = 0; i < codes.size(); i++) {
			if (i % 2 == 0) {
				leftColumn.append(codes.get(i));
				if (i < codes.size() - 2)
					leftColumn.append("\n");
			}
			else {
				rightColumn.append(codes.get(i));
				if (i < codes.size() - 2)
					rightColumn.append("\n");
			}
		}
	}
}
