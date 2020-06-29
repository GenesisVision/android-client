package vision.genesis.clientapp.feature.common.public_info;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.image_crop.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.model.PublicInfoModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@RuntimePermissions
public class PublicInfoFragment extends BaseFragment implements PublicInfoView
{
	private static String EXTRA_MODEL = "extra_model";

	public static PublicInfoFragment with(PublicInfoModel model) {
		PublicInfoFragment fragment = new PublicInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_MODEL, model);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.group_warning_info)
	public ViewGroup warningInfoGroup;

	@BindView(R.id.warning_info)
	public TextView warningInfo;

	@BindView(R.id.warning_info_progress)
	public ProgressBar warningInfoProgress;

	@BindView(R.id.text_input_title)
	public TextInputLayout titleTextInput;

	@BindView(R.id.text_input_description)
	public TextInputLayout descriptionTextInput;

	@BindView(R.id.title)
	public EditText title;

	@BindView(R.id.description)
	public EditText description;

	@BindView(R.id.title_minimum)
	public TextView titleMinimum;

	@BindView(R.id.description_minimum)
	public TextView descriptionMinimum;

	@BindView(R.id.logo_label)
	public TextView logoLabel;

	@BindView(R.id.group_upload)
	public ViewGroup uploadGroup;

	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.logo_progress)
	public ProgressBar logoProgress;

	@BindView(R.id.button_upload_logo)
	public PrimaryButton uploadLogoButton;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public PublicInfoPresenter presenter;

	private Unbinder unbinder;

	private Subscription titleTextChangeSubscription;

	private Subscription descriptionTextChangeSubscription;

	private PublicInfoModel model;

	private String warningInfoText;

	@OnFocusChange(R.id.title)
	void onTitleFocusChange(View view, boolean hasFocus) {
		if (!hasFocus) {
			presenter.onTitleFocusLost();
		}
	}

	@OnFocusChange(R.id.description)
	void onDescriptionFocusChange(View view, boolean hasFocus) {
		if (!hasFocus) {
			presenter.onDescriptionFocusLost();
		}
	}

	@OnClick(R.id.button_remove_logo)
	public void onRemoveLogoClicked() {
		presenter.onRemoveLogoClicked();
	}

	@OnClick(R.id.button_upload_logo)
	public void onUploadLogoClicked() {
		PublicInfoFragmentPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		hideSoftKeyboard();
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_public_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			model = getArguments().getParcelable(EXTRA_MODEL);
			if (model != null) {
				setFonts();
				setTextListeners();
				updateView(model);
				updateWarningInfo();

				uploadLogoButton.setEmpty();

				presenter.setModel(model);

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(PublicInfoModel model) {
		setLimits();

		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		warningInfoGroup.setVisibility(model.isNeedWarningInfo() ? View.VISIBLE : View.GONE);

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
		if (titleTextChangeSubscription != null) {
			titleTextChangeSubscription.unsubscribe();
		}
		if (descriptionTextChangeSubscription != null) {
			descriptionTextChangeSubscription.unsubscribe();
		}

		super.onDestroyView();
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
		logoLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void setLimits() {
		titleMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_NAME_LENGTH));
		descriptionMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_DESCRIPTION_LENGTH));
		titleTextInput.setCounterMaxLength(Constants.MAX_ASSET_NAME_LENGTH);
		descriptionTextInput.setCounterMaxLength(Constants.MAX_ASSET_DESCRIPTION_LENGTH);
	}

	private void setTextListeners() {
		titleTextChangeSubscription = RxTextView.textChanges(title)
				.subscribe(text -> presenter.onTitleChanged(text.toString()));
		descriptionTextChangeSubscription = RxTextView.textChanges(description)
				.subscribe(text -> presenter.onDescriptionChanged(text.toString()));
	}

	@Override
	public void setTitle(String title) {
		this.title.setText(title);
	}

	@Override
	public void setDescription(String description) {
		this.description.setText(description);
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
		uploadLogoButton.setVisibility(View.GONE);
		logoProgress.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void removeLogo() {
		uploadGroup.setVisibility(View.GONE);
		uploadLogoButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void updateLogo(String url) {
		logo.setImageURI(ImageUtils.getImageUriById(url));
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, confirmButton);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	@Override
	public void showTitleError(String errorText) {
		this.title.setError(errorText);
	}

	@Override
	public void showDescriptionError(String errorText) {
		this.description.setError(errorText);
	}

	@Override
	public void cleanTitleError() {
		this.title.setError(null);
	}

	@Override
	public void cleanDescriptionError() {
		this.description.setError(null);
	}

	public void setWarningInfo(String warningInfo) {
		this.warningInfoText = warningInfo;
		updateWarningInfo();
	}

	private void updateWarningInfo() {
		if (warningInfoText != null && warningInfo != null) {
			this.warningInfo.setText(warningInfoText);
			this.warningInfo.setVisibility(View.VISIBLE);
			this.warningInfoProgress.setVisibility(View.GONE);
		}
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
		bottomSheetDialog.show(getChildFragmentManager(), bottomSheetDialog.getTag());
	}

	@OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_picture_rationale), request, null);
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
		if (getContext() != null) {
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			title.clearFocus();
			description.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(title.getWindowToken(), 0);
			}
		}
	}
}