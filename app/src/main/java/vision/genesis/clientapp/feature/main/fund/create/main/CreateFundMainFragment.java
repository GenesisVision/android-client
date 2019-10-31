package vision.genesis.clientapp.feature.main.fund.create.main;

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

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.NewFundRequest;
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
import vision.genesis.clientapp.feature.main.profile.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.model.events.OnCreateFundNextButtonClickedEvent;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@RuntimePermissions
public class CreateFundMainFragment extends BaseFragment implements CreateFundMainView
{
	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.deposit_notification)
	public TextView depositNotification;

	@BindView(R.id.deposit_notification_progress)
	public ProgressBar depositNotificationProgress;

	@BindView(R.id.text_input_name)
	public TextInputLayout nameTextInput;

	@BindView(R.id.text_input_description)
	public TextInputLayout descriptionTextInput;

	@BindView(R.id.name)
	public EditText name;

	@BindView(R.id.description)
	public EditText description;

	@BindView(R.id.name_minimum)
	public TextView nameMinimum;

	@BindView(R.id.description_minimum)
	public TextView descriptionMinimum;

	@BindView(R.id.fund_logo_label)
	public TextView fundLogoLabel;

	@BindView(R.id.group_upload)
	public ViewGroup uploadGroup;

	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.logo_progress)
	public ProgressBar logoProgress;

	@BindView(R.id.button_upload_logo)
	public PrimaryButton uploadLogoButton;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@InjectPresenter
	public CreateFundMainPresenter presenter;

	private Unbinder unbinder;

	private NewFundRequest request;

	private Subscription nameTextChangeSubscription;

	private Subscription descriptionTextChangeSubscription;

	@OnClick(R.id.button_remove_logo)
	public void onRemoveLogoClicked() {
		presenter.onRemoveLogoClicked();
	}

	@OnClick(R.id.button_upload_logo)
	public void onUploadLogoClicked() {
		CreateFundMainFragmentPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_next)
	public void onNextClicked() {
		hideSoftKeyboard();
		EventBus.getDefault().post(new OnCreateFundNextButtonClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_fund_main, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
		setLimits();
		setTextListeners();

		uploadLogoButton.setEmpty();
		nextButton.setText(String.format(Locale.getDefault(), "%s (1/4)", getString(R.string.next)));
		nextButton.setEnabled(false);

		if (request != null) {
			presenter.setRequest(request);
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
		if (nameTextChangeSubscription != null) {
			nameTextChangeSubscription.unsubscribe();
		}
		if (descriptionTextChangeSubscription != null) {
			descriptionTextChangeSubscription.unsubscribe();
		}

		super.onDestroyView();
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
		fundLogoLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void setLimits() {
		nameMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_FUND_NAME_LENGTH));
		descriptionMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_FUND_DESCRIPTION_LENGTH));
		nameTextInput.setCounterMaxLength(Constants.MAX_FUND_NAME_LENGTH);
		descriptionTextInput.setCounterMaxLength(Constants.MAX_FUND_DESCRIPTION_LENGTH);
	}

	private void setTextListeners() {
		nameTextChangeSubscription = RxTextView.textChanges(name)
				.subscribe(text -> presenter.onNameChanged(text.toString()));
		descriptionTextChangeSubscription = RxTextView.textChanges(description)
				.subscribe(text -> presenter.onDescriptionChanged(text.toString()));
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
		logo.setImageURI(ImageUtils.getImageUri(url));
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, nextButton);
	}

	@Override
	public void setNextButtonEnabled(boolean enabled) {
		nextButton.setEnabled(enabled);
	}

	public void setRequest(NewFundRequest request) {
		this.request = request;
		if (presenter != null) {
			presenter.setRequest(request);
		}
	}

	public void setMinDepositAmount(Double minDepositAmount) {
		depositNotification.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_deposit_first_notification),
				StringFormatUtil.formatAmount(minDepositAmount, 0, 8)));
		depositNotification.setVisibility(View.VISIBLE);
		depositNotificationProgress.setVisibility(View.GONE);
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
			name.clearFocus();
			description.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(name.getWindowToken(), 0);
			}
		}
	}
}