package vision.genesis.clientapp.feature.main.program.create.first;

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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.ui.common.ActivityResultListener;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/07/2018.
 */

@RuntimePermissions
public class CreateProgramFirstStepFragment extends BaseFragment implements CreateProgramFirstStepView, ActivityResultListener
{
	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.progress_bar_logo)
	public ProgressBar logoProgressBar;

	@BindView(R.id.progress_bar_next)
	public ProgressBar nextProgressBar;

	@BindView(R.id.text_next_button)
	public TextView nextButtonText;

	@BindView(R.id.group_add_logo)
	public ViewGroup addLogoGroup;

	@BindView(R.id.text_input_layout_title)
	public TextInputLayout titleLayout;

	@BindView(R.id.edit_text_title)
	public EditText title;

	@BindView(R.id.text_input_layout_description)
	public TextInputLayout descriptionLayout;

	@BindView(R.id.edit_text_description)
	public EditText description;

	@BindView(R.id.button_next)
	public View nextButton;

	@InjectPresenter
	CreateProgramFirstStepPresenter createProgramFirstStepPresenter;

	private Unbinder unbinder;

	private Subscription titleTextChangeSubscription;

	private Subscription descriptionTextChangeSubscription;

	@OnClick(R.id.button_next)
	public void onNextButtonClicked() {
		hideKeyboard();
		createProgramFirstStepPresenter.onNextButtonClicked();
	}


	@OnClick(R.id.logo)
	public void onLogoClicked() {
		CreateProgramFirstStepFragmentPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_program_first_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		nextButton.setEnabled(false);

		setFonts();
	}

	@Override
	public void onStart() {
		super.onStart();
		setTextListeners();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (titleTextChangeSubscription != null)
			titleTextChangeSubscription.unsubscribe();
		if (descriptionTextChangeSubscription != null)
			descriptionTextChangeSubscription.unsubscribe();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setTextListeners() {
		titleTextChangeSubscription = RxTextView.textChanges(title)
				.subscribe(text -> createProgramFirstStepPresenter.onTitleChanged(text.toString()));
		descriptionTextChangeSubscription = RxTextView.textChanges(description)
				.subscribe(text -> createProgramFirstStepPresenter.onDescriptionChanged(text.toString()));
	}

	private void setFonts() {
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		description.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(description.getWindowToken(), 0);
		}
	}

	@Override
	public void setLimits(CreateProgramData createProgramData) {
		titleLayout.setCounterMaxLength(createProgramData.getMaxTitleLength());
		descriptionLayout.setCounterMaxLength(createProgramData.getMaxDescriptionLength());
	}

	@Override
	public void updateLogo(String imageId) {
		if (imageId != null && !imageId.isEmpty()) {
			this.logo.setImageURI(ImageUtils.getImageUri(imageId));
			addLogoGroup.setVisibility(View.GONE);
		}
		else {
			this.logo.setImageURI("");
			addLogoGroup.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void openCamera(ImageUtils imageUtils, File newLogoFile) {
		if (getActivity() != null)
			((CreateProgramActivity) getActivity()).openCamera(this, imageUtils, newLogoFile);
	}

	@Override
	public void openGallery(ImageUtils imageUtils) {
		if (getActivity() != null)
			((CreateProgramActivity) getActivity()).openGallery(this, imageUtils);
	}

	@Override
	public void startImageCropActivity(String imageUri) {
		if (getActivity() != null)
			((CreateProgramActivity) getActivity()).startImageCropActivity(this, imageUri);
	}

	@Override
	public void setNextButtonAvailability(boolean available) {
		nextButton.setEnabled(available);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, nextButton);
	}

	@Override
	public void showLogoProgress(boolean show) {
		logoProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		addLogoGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showNextProgress(boolean show) {
		nextProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		nextButtonText.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
		nextButton.setEnabled(!show);
	}

	@Override
	public void onResultPassedFromActivity(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case ImageUtils.CAMERA_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					createProgramFirstStepPresenter.handleCameraResult();
				}
				else {
					createProgramFirstStepPresenter.handleCameraFail();
				}
				break;
			case ImageUtils.GALLERY_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					createProgramFirstStepPresenter.handleGalleryResult(data.getData());
				}
				else {
					createProgramFirstStepPresenter.handleGalleryFail();
				}
				break;
			case ImageUtils.CROP_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					createProgramFirstStepPresenter.handleImageCropResult();
				}
				else {
					createProgramFirstStepPresenter.handleImageCropFail();
				}
				break;
			default:
				break;
		}
	}

	@NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showPictureChooser() {
		PictureChooserBottomSheetFragment bottomSheetDialog = new PictureChooserBottomSheetFragment();
		bottomSheetDialog.show(this.getFragmentManager(), bottomSheetDialog.getTag());
	}

	@OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_logo_rationale), request);
	}

	@OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageDenied() {
		showMessageDialog(getString(R.string.permission_logo_denied));
	}

	@OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_logo_never_ask_again));
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		CreateProgramFirstStepFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}
}
