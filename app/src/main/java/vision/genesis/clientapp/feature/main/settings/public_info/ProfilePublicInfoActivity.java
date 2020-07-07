package vision.genesis.clientapp.feature.main.settings.public_info;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.image_crop.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/12/2019.
 */

@RuntimePermissions
public class ProfilePublicInfoActivity extends BaseSwipeBackActivity implements ProfilePublicInfoView
{
	private static final String EXTRA_NEED_FILL_PROFILE = "extra_need_fill_profile";

	public static void startFrom(Activity activity, Boolean needFillProfile) {
		Intent intent = new Intent(activity.getApplicationContext(), ProfilePublicInfoActivity.class);
		intent.putExtra(EXTRA_NEED_FILL_PROFILE, needFillProfile);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.scrollview)
	public ScrollView scrollview;

	@BindView(R.id.group_warning_info)
	public ViewGroup warningInfoGroup;

	@BindView(R.id.text_input_username)
	public TextInputLayout usernameTextInput;

	@BindView(R.id.text_input_about)
	public TextInputLayout aboutTextInput;

	@BindView(R.id.username)
	public EditText username;

	@BindView(R.id.about)
	public EditText about;

	@BindView(R.id.username_minimum)
	public TextView usernameMinimum;

	@BindView(R.id.about_minimum)
	public TextView aboutMinimum;

	@BindView(R.id.logo_label)
	public TextView logoLabel;

	@BindView(R.id.group_upload)
	public ViewGroup uploadGroup;

	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.logo_progress)
	public ProgressBar logoProgress;

	@BindView(R.id.button_upload_avatar)
	public PrimaryButton uploadAvatarButton;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.button_progress)
	public ProgressBar buttonProgress;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public ProfilePublicInfoPresenter presenter;

	private Subscription usernameTextChangeSubscription;

	private Subscription aboutTextChangeSubscription;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_remove_logo)
	public void onRemoveLogoClicked() {
		presenter.onRemoveLogoClicked();
	}

	@OnClick(R.id.button_upload_avatar)
	public void onUploadAvatarClicked() {
		ProfilePublicInfoActivityPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		hideSoftKeyboard();
		presenter.onConfirmClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_public_info);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			boolean needFillProfile = getIntent().getExtras().getBoolean(EXTRA_NEED_FILL_PROFILE, false);
			setTextListeners();
			presenter.setData(needFillProfile);
			updateView(needFillProfile);

			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(boolean needFillProfile) {
		setLimits();

		warningInfoGroup.setVisibility(needFillProfile ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onDestroy() {
		if (usernameTextChangeSubscription != null) {
			usernameTextChangeSubscription.unsubscribe();
		}
		if (aboutTextChangeSubscription != null) {
			aboutTextChangeSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		logoLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void setLimits() {
		usernameMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_USER_NAME_LENGTH));
		aboutMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_USER_ABOUT_LENGTH));
		usernameTextInput.setCounterMaxLength(Constants.MAX_USER_NAME_LENGTH);
		aboutTextInput.setCounterMaxLength(Constants.MAX_USER_ABOUT_LENGTH);
	}

	private void setTextListeners() {
		usernameTextChangeSubscription = RxTextView.textChanges(username)
				.subscribe(text -> presenter.onUsernameChanged(text.toString()));
		aboutTextChangeSubscription = RxTextView.textChanges(about)
				.subscribe(text -> presenter.onAboutChanged(text.toString()));
	}

	@Override
	public void setUsername(String title) {
		this.username.setText(title);
	}

	@Override
	public void setAbout(String description) {
		this.about.setText(description);
	}

	@Override
	public void openCamera(ImageUtils imageUtils, File newLogoFile) {
		imageUtils.openCameraFrom(this, newLogoFile);
	}

	@Override
	public void openGallery(ImageUtils imageUtils) {
		imageUtils.openGalleryFrom(this);
	}

	@Override
	public void startImageCropActivity(String imageUri) {
		ImageCropActivity.startForResult(this, imageUri, Constants.MIN_LOGO_WIDTH, Constants.MIN_LOGO_HEIGHT);
	}

	@Override
	public void showLogoProgress(boolean show) {
		uploadGroup.setVisibility(View.VISIBLE);
		uploadAvatarButton.setVisibility(View.GONE);
		logoProgress.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void removeLogo() {
		uploadGroup.setVisibility(View.GONE);
		uploadAvatarButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void updateLogo(String url) {
		logo.setImageURI(ImageUtils.getImageUri(url));
	}

	@Override
	public void updateLogoById(String logoId) {
		logo.setImageURI(ImageUtils.getImageUriById(logoId));
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollview.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showButtonProgress(boolean show) {
		buttonProgress.setVisibility(show ? View.VISIBLE : View.GONE);
		uploadAvatarButton.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
		confirmButton.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case ImageUtils.CAMERA_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleCameraResult();
				}
				else {
					presenter.handleCameraFail();
				}
				break;
			case ImageUtils.GALLERY_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleGalleryResult(data.getData());
				}
				else {
					presenter.handleGalleryFail();
				}
				break;
			case ImageUtils.CROP_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleImageCropResult();
				}
				else {
					presenter.handleImageCropFail();
				}
				break;
			default:
				break;
		}
	}

	@NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showPictureChooser() {
		PictureChooserBottomSheetFragment bottomSheetDialog = new PictureChooserBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
	}

	@OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_picture_rationale), request);
	}

	@OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageDenied() {
		showMessageDialog(getString(R.string.permission_picture_denied));
	}

	@OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_picture_never_ask_again));
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		username.clearFocus();
		about.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
		}
	}
}