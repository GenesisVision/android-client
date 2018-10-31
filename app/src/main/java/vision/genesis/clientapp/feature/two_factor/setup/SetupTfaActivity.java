package vision.genesis.clientapp.feature.two_factor.setup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public class SetupTfaActivity extends BaseSwipeBackActivity implements SetupTfaView
{
	public static void startFrom(Activity activity) {
		Intent activityIntent = new Intent(activity, SetupTfaActivity.class);
		activity.startActivity(activityIntent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.button_back)
	public View backButton;

	@BindView(R.id.title)
	public TextView title;

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
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_two_factor);

		ButterKnife.bind(this);

		setFonts();
		initViewPager();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		stepView.getState().typeface(TypefaceUtil.regular()).commit();
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
		if (currentStepNumber == 0)
			finishActivity();
		else if (currentStepNumber < 3) {
			setCurrentStep(currentStepNumber - 1);
		}
		hideKeyboard();
	}

	@Override
	public void setKey(String sharedKey, String authenticatorUri) {
		pagerAdapter.setKey(sharedKey, authenticatorUri);
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
	public void onConfirmSuccess(ArrayList<String> codes) {
		pagerAdapter.setCodes(codes);
		setCurrentStep(3);
		backButton.setVisibility(View.GONE);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
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