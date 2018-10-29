package vision.genesis.clientapp.feature.two_factor.check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PinKeyboardView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public class CheckTfaActivity extends BaseSwipeBackActivity implements CheckTfaView
{
	private static final String EXTRA_ACTION = "extra_action";

	public static void startWith(Activity activity, String action) {
		Intent intent = new Intent(activity.getApplicationContext(), CheckTfaActivity.class);
		intent.putExtra(EXTRA_ACTION, action);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_code)
	public ViewGroup codeGroup;

	@BindView(R.id.code_1)
	public TextView code1;

	@BindView(R.id.code_2)
	public TextView code2;

	@BindView(R.id.code_3)
	public TextView code3;

	@BindView(R.id.code_4)
	public TextView code4;

	@BindView(R.id.code_5)
	public TextView code5;

	@BindView(R.id.code_6)
	public TextView code6;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@InjectPresenter
	CheckTfaPresenter checkTfaPresenter;

	@OnCheckedChanged(R.id.checkbox_recovery_code)
	public void onRecoveryCodeCheckedChanged(CompoundButton button, boolean checked) {
		checkTfaPresenter.setUseRecoveryCode(checked);
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_check_tfa);

		ButterKnife.bind(this);

		setCode("");

		initKeyboardListener();
		setFonts();
	}

	@Override
	protected void onDestroy() {
		keyboard.onDestroy();

		super.onDestroy();
	}

	private void initKeyboardListener() {
		keyboard.setListener(new PinKeyboardView.InputListener()
		{
			@Override
			public void onNumber(String number) {
				checkTfaPresenter.onNumber(number);
			}

			@Override
			public void onBackspace() {
				checkTfaPresenter.onBackspace();
			}

			@Override
			public void onLongBackspace() {
				checkTfaPresenter.onLongBackspace();
			}
		});
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		code1.setTypeface(TypefaceUtil.light());
		code2.setTypeface(TypefaceUtil.light());
		code3.setTypeface(TypefaceUtil.light());
		code4.setTypeface(TypefaceUtil.light());
		code5.setTypeface(TypefaceUtil.light());
		code6.setTypeface(TypefaceUtil.light());
	}

	@Override
	public void setCode(String code) {
		code1.setText("_");
		code2.setText("_");
		code3.setText("_");
		code4.setText("_");
		code5.setText("_");
		code6.setText("_");

		code1.setAlpha(0.5f);
		code2.setAlpha(0.5f);
		code3.setAlpha(0.5f);
		code4.setAlpha(0.5f);
		code5.setAlpha(0.5f);
		code6.setAlpha(0.5f);

		if (code.length() > 0) {
			code1.setAlpha(1f);
			code1.setText(String.valueOf(code.charAt(0)));
		}
		if (code.length() > 1) {
			code2.setAlpha(1f);
			code2.setText(String.valueOf(code.charAt(1)));
		}
		if (code.length() > 2) {
			code3.setAlpha(1f);
			code3.setText(String.valueOf(code.charAt(2)));
		}
		if (code.length() > 3) {
			code4.setAlpha(1f);
			code4.setText(String.valueOf(code.charAt(3)));
		}
		if (code.length() > 4) {
			code5.setAlpha(1f);
			code5.setText(String.valueOf(code.charAt(4)));
		}
		if (code.length() > 5) {
			code6.setAlpha(1f);
			code6.setText(String.valueOf(code.charAt(5)));
		}
	}

	@Override
	public void setKeyboardKeysEnabled(boolean enabled) {
		keyboard.disableKeyboard(!enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		codeGroup.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
		keyboard.disableKeyboard(show);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}