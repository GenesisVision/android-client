package vision.genesis.clientapp.feature.main.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.DateTime;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProfileFullViewModel;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ProfileDataView;
import vision.genesis.clientapp.ui.SpinnerView;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@RuntimePermissions
public class ProfileFragment extends BaseFragment implements ProfileView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.edit_mode_toolbar)
	public ToolbarView editModeToolbar;

	@BindView(R.id.avatar)
	public SimpleDraweeView avatar;

	@BindView(R.id.avatar_progress_bar)
	public ProgressBar avatarProgressBar;

	@BindView(R.id.button_change_avatar)
	public View changeAvatarButton;

	@BindView(R.id.first_name)
	public ProfileDataView firstName;

	@BindView(R.id.middle_name)
	public ProfileDataView middleName;

	@BindView(R.id.last_name)
	public ProfileDataView lastName;

	@BindView(R.id.email)
	public ProfileDataView email;

	@BindView(R.id.gender_spinner)
	public SpinnerView genderSpinner;

	@BindView(R.id.birthday)
	public ProfileDataView birthday;

	@BindView(R.id.button_birthday_calendar)
	public View birthdayCalendarButton;

	@BindView(R.id.country)
	public ProfileDataView country;

	@BindView(R.id.city)
	public ProfileDataView city;

	@BindView(R.id.address)
	public ProfileDataView address;

	@BindView(R.id.phone)
	public ProfileDataView phone;

	@BindView(R.id.document_type)
	public ProfileDataView documentType;

	@BindView(R.id.document_number)
	public ProfileDataView documentNumber;

	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	ProfilePresenter profilePresenter;

	private ProfileFullViewModel profileModel;

	private boolean editMode;

	@OnClick(R.id.avatar)
	public void onChangeAvatarClicked() {
		if (editMode)
			ProfileFragmentPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_birthday_calendar)
	public void onBirthdyCalendarButtonClicked() {
		if (editMode)
			profilePresenter.onBirthdyCalendarButtonClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbars();
		initViews();
		setEditMode(false);
	}

	private void initToolbars() {
		toolbar.setTitle(getString(R.string.profile));
		toolbar.addLeftButton(R.drawable.edit_icon, () -> profilePresenter.onEditModeClicked());
		toolbar.addRightButton(R.drawable.logout, () -> profilePresenter.onLogoutClicked());
		toolbar.setLeftButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getContext().getResources().getDisplayMetrics()));
		toolbar.setRightButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getContext().getResources().getDisplayMetrics()));

		editModeToolbar.addLeftButton(R.drawable.ic_check_black_24dp, this::saveChanges);
		editModeToolbar.addRightButton(R.drawable.ic_close_black_24dp, this::cancelChanges);
		editModeToolbar.setLeftButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getContext().getResources().getDisplayMetrics()));
		editModeToolbar.setRightButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getContext().getResources().getDisplayMetrics()));
	}

	private void initViews() {
		firstName.setHint(getString(R.string.first_name));
		middleName.setHint(getString(R.string.middle_name));
		lastName.setHint(getString(R.string.last_name));
		email.setHint(getString(R.string.email));
		birthday.setHint(getString(R.string.birthday));
		country.setHint(getString(R.string.country));
		city.setHint(getString(R.string.city));
		address.setHint(getString(R.string.address));
		phone.setHint(getString(R.string.phone));
		documentType.setHint(getString(R.string.document_type));
		documentNumber.setHint(getString(R.string.document_number));

		initSpinner();
	}

	private void initSpinner() {
		String[] genderArray = getResources().getStringArray(R.array.gender_list);
		genderSpinner.setData(genderArray, R.layout.item_profile_spinner_dropdown);
		genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (profileModel != null)
					profileModel.setGender(position > 0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		genderSpinner.setPrompt(getString(R.string.none));
	}

	@Override
	public void onResume() {
		super.onResume();

		profilePresenter.onResume();
	}

	private void saveChanges() {
		String firstNameText = firstName.getText().trim();
		profileModel.setFirstName(firstNameText);

		String middleNameText = middleName.getText().trim();
		profileModel.setMiddleName(middleNameText);

		String lastNameText = lastName.getText().trim();
		profileModel.setLastName(lastNameText);

		String countryText = country.getText().trim();
		profileModel.setCountry(countryText);

		String cityText = city.getText().trim();
		profileModel.setCity(cityText);

		String addressText = address.getText().trim();
		profileModel.setAddress(addressText);

		String phoneText = phone.getText().trim();
		profileModel.setPhone(phoneText);

		String documentTypeText = documentType.getText().trim();
		profileModel.setDocumentType(documentTypeText);

		String documentNumberText = documentNumber.getText().trim();
		profileModel.setDocumentNumber(documentNumberText);

		profilePresenter.saveChanges(profileModel);
	}

	private void cancelChanges() {
		profilePresenter.cancelChanges();
	}

	@Override
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		toolbar.setVisibility(editMode ? View.GONE : View.VISIBLE);
		editModeToolbar.setVisibility(editMode ? View.VISIBLE : View.GONE);
		changeAvatarButton.setVisibility(editMode ? View.VISIBLE : View.GONE);
		birthdayCalendarButton.setVisibility(editMode ? View.VISIBLE : View.GONE);

		setAllFieldsEditMode(editMode);
	}

	private void setAllFieldsEditMode(boolean editMode) {
		firstName.setEditMode(editMode);
		middleName.setEditMode(editMode);
		lastName.setEditMode(editMode);
		country.setEditMode(editMode);
		city.setEditMode(editMode);
		address.setEditMode(editMode);
		phone.setEditMode(editMode);
		documentType.setEditMode(editMode);
		documentNumber.setEditMode(editMode);

		genderSpinner.setEditMode(editMode);
	}

	@Override
	public void updateProfile(ProfileFullViewModel profileModel) {
		this.profileModel = profileModel;

		updateAvatar(profileModel.getAvatar());

		firstName.setText(profileModel.getFirstName());
		middleName.setText(profileModel.getMiddleName());
		lastName.setText(profileModel.getLastName());
		email.setText(profileModel.getEmail());
		if (profileModel.isGender() != null)
			genderSpinner.setSelection(profileModel.isGender() ? 1 : 0);
		setBirthday();
		country.setText(profileModel.getCountry());
		city.setText(profileModel.getCity());
		address.setText(profileModel.getAddress());
		phone.setText(profileModel.getPhone());
		documentType.setText(profileModel.getDocumentType());
		documentNumber.setText(profileModel.getDocumentNumber());
	}

	private void setBirthday() {
		birthday.setText(profileModel.getBirthday() == null
				? ""
				: (DateTimeUtil.formatDate(profileModel.getBirthday())));
	}

	@Override
	public void updateAvatar(String imageId) {
		profileModel.setAvatar(imageId);
		if (imageId != null && !imageId.isEmpty())
			this.avatar.setImageURI(ImageUtils.getImageUri(imageId));
		else
			this.avatar.setImageURI("");
	}

	@Override
	public void openCamera(ImageUtils imageUtils, File newAvatarFile) {
		imageUtils.openCameraFrom(ProfileFragment.this, newAvatarFile);
	}

	@Override
	public void openGallery(ImageUtils imageUtils) {
		imageUtils.openGalleryFrom(ProfileFragment.this);
	}

	@Override
	public void showDatePicker() {
		DateTime calendarDate = profileModel.getBirthday();
		if (calendarDate == null)
			calendarDate = DateTime.now();
		DatePickerDialog dpd = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
			DateTime newBirthday = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
			profileModel.setBirthday(newBirthday);
			setBirthday();
		}, calendarDate.getYear(), calendarDate.getMonthOfYear() - 1, calendarDate.getDayOfMonth());
		dpd.setMaxDate(DateTime.now().toCalendar(Locale.getDefault()));
		dpd.show(getActivity().getFragmentManager(), "DatePickerDialog");
	}

	@Override
	public void showAvatarProgress(boolean show) {
		avatarProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showUpdateProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void startImageCropActivity(String imageUri) {
		ImageCropActivity.startForResult(this, imageUri);
	}

	@Override
	public boolean onBackPressed() {
		return profilePresenter.onBackPressed();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case ImageUtils.CAMERA_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					profilePresenter.handleCameraResult();
				}
				else {
					profilePresenter.handleCameraFail();
				}
				break;
			case ImageUtils.GALLERY_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					profilePresenter.handleGalleryResult(data.getData());
				}
				else {
					profilePresenter.handleGalleryFail();
				}
				break;
			case ImageUtils.CROP_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					profilePresenter.handleImageCropResult();
				}
				else {
					profilePresenter.handleImageCropFail();
				}
				break;
			default:
				break;
		}
	}

	@NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	void showPictureChooser() {
		PictureChooserBottomSheetFragment bottomSheetDialog = new PictureChooserBottomSheetFragment();
		bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
	}

	@OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_avatar_rationale), request);
	}

	@OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
	void onStorageDenied() {
		showMessageDialog(getString(R.string.permission_avatar_denied));
	}

	@OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
	void onStorageNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_avatar_never_ask_again));
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}
}