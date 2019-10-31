package vision.genesis.clientapp.feature.main.program.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.profile.ImageCropActivity;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.ui.common.ActivityResultListener;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

public class CreateProgramActivity extends MvpAppCompatActivity implements CreateProgramView
{
	public static void startFrom(Context context) {
		Intent activityIntent = new Intent(context, CreateProgramActivity.class);
		context.startActivity(activityIntent);
	}

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.step_view)
	public StepView stepView;

	@BindView(R.id.create_program_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	CreateProgramPresenter createProgramPresenter;

	private CreateProgramPagerAdapter pagerAdapter;

	private int currentStepNumber = 0;

	private ActivityResultListener resultListener;

	@OnClick(R.id.button_close)
	public void onCloseButtonClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_program);

		ButterKnife.bind(this);

		setFonts();

		version.setText(String.format(Locale.getDefault(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		initViewPager();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());
	}

	private void initViewPager() {
		pagerAdapter = new CreateProgramPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setAllowSwipe(false);
		viewPager.setOffscreenPageLimit(3);
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

	public void openCamera(ActivityResultListener listener, ImageUtils imageUtils, File newAvatarFile) {
		resultListener = listener;
		imageUtils.openCameraFrom(this, newAvatarFile);
	}

	public void openGallery(ActivityResultListener listener, ImageUtils imageUtils) {
		resultListener = listener;
		imageUtils.openGalleryFrom(this);
	}

	public void startImageCropActivity(ActivityResultListener listener, String imageUri) {
		resultListener = listener;
		ImageCropActivity.startForResult(this, imageUri, Constants.MIN_LOGO_WIDTH, Constants.MIN_LOGO_HEIGHT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultListener != null)
			resultListener.onResultPassedFromActivity(requestCode, resultCode, data);
	}
}