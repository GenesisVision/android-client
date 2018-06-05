package vision.genesis.clientapp.feature.two_factor.setup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.shuhart.stepview.StepView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.RecoveryCode;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public class SetupTfaActivity extends MvpAppCompatActivity implements SetupTfaView
{
	public static void startFrom(Context context) {
		Intent activityIntent = new Intent(context, SetupTfaActivity.class);
		context.startActivity(activityIntent);
	}

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.step_view)
	public StepView stepView;

	@BindView(R.id.setup_tfa_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	SetupTfaPresenter setupTfaPresenter;

	private SetupTfaPagerAdapter pagerAdapter;

	private int currentStepNumber = 0;

	@OnClick(R.id.button_back)
	public void onBackButtonClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_two_factor);

		ButterKnife.bind(this);

		version.setText(String.format(Locale.getDefault(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		initViewPager();
	}

	private void initViewPager() {
		pagerAdapter = new SetupTfaPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setAllowSwipe(false);
		viewPager.setOffscreenPageLimit(1);
		setCurrentStep(0);
	}

	private void setCurrentStep(int newStepNumber) {
		currentStepNumber = newStepNumber;
		stepView.go(newStepNumber, true);
		viewPager.setCurrentItem(newStepNumber);
	}

	@Override
	public void onBackPressed() {
		if (currentStepNumber == 0 || currentStepNumber == 3)
			finish();
		else {
			setCurrentStep(currentStepNumber - 1);
		}
		hideKeyboard();
	}

	@Override
	public void setKey(String sharedKey) {
		pagerAdapter.setKey(sharedKey);
	}

	@Override
	public void showNextStep() {
		setCurrentStep(currentStepNumber + 1);
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbar(String text) {
		Snackbar.make(stepView, text, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void onConfirmSuccess(List<RecoveryCode> codes) {
		pagerAdapter.setRecoveryCodes(codes);
		setCurrentStep(currentStepNumber + 1);
	}

	@Override
	public void finishActivity() {
		finish();
	}

	@Override
	public void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		stepView.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(stepView.getWindowToken(), 0);
		}
	}
}