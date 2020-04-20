package vision.genesis.clientapp.feature.main.profile;

import android.content.Context;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private Subscription profileSubscription;

	private Subscription avatarUploadSubscription;

	private Subscription updateProfileSubscription;

	private ProfileFullViewModel profileModelOriginal;

	private File newAvatarFile;

	private boolean isEditMode = false;

	private Subscription userSubscription;

	private User user;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getProfile();
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}

		if (avatarUploadSubscription != null) {
			avatarUploadSubscription.unsubscribe();
		}

		if (updateProfileSubscription != null) {
			updateProfileSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onEditModeClicked() {
		setEditMode(true);
	}

	void saveChanges(ProfileFullViewModel newProfileModel) {
		updateProfile(newProfileModel);
	}

	void cancelChanges() {
		getViewState().updateProfile(profileModelOriginal);
		setEditMode(false);
	}

	boolean onBackPressed() {
		if (isEditMode) {
			cancelChanges();
			return true;
		}
		else {
			return false;
		}
	}

	void onResume() {
		if (!isEditMode) {
			getProfile();
		}
	}

	void onBirthdyCalendarButtonClicked() {
		getViewState().showDatePicker();
	}

	void handleCameraResult() {
		getViewState().startImageCropActivity(newAvatarFile.toURI().toString());
	}

	void handleGalleryResult(Uri pictureUri) {
		try {
			ImageUtils.copyFiles(new File(imageUtils.getImagePath(context, pictureUri)), newAvatarFile);
			getViewState().startImageCropActivity(newAvatarFile.toURI().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void handleImageCropResult() {
		uploadAvatar();
	}

	void handleCameraFail() {
		ImageUtils.deleteTempFile(newAvatarFile);
	}

	void handleGalleryFail() {
		ImageUtils.deleteTempFile(newAvatarFile);
	}

	void handleImageCropFail() {
		ImageUtils.deleteTempFile(newAvatarFile);
	}

	private void setEditMode(boolean editMode) {
		isEditMode = editMode;
		getViewState().setEditMode(editMode);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, error -> {
				});
	}

	private void userUpdated(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull(true)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profileModel) {
		this.profileModelOriginal = copyProfileModel(profileModel);
		getViewState().updateProfile(profileModel);
	}

	private void handleGetProfileError(Throwable error) {

	}

	private void updateProfile(ProfileFullViewModel newProfileModel) {
		getViewState().showUpdateProgress(true);
		updateProfileSubscription = profileManager.updateProfile(newProfileModel)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleUpdateProfileSuccess,
						this::handleUpdateProfileError);
	}

	private void handleUpdateProfileSuccess(Void response) {
		updateProfileSubscription.unsubscribe();
//		this.profileModelOriginal = copyProfileModel(newProfileModel);
//		getViewState().updateProfile(newProfileModel);
		getViewState().showSnackbarMessage(context.getResources().getString(R.string.successfully_updated));
		setEditMode(false);
		getViewState().showUpdateProgress(false);
	}

	private void handleUpdateProfileError(Throwable throwable) {
		updateProfileSubscription.unsubscribe();
		getViewState().showUpdateProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.message != null) {
						getViewState().showSnackbarMessage(error.message);
						break;
					}
				}
			}
		}
	}

	private void uploadAvatar() {
		getViewState().showAvatarProgress(true);
		avatarUploadSubscription = filesManager.uploadFile(newAvatarFile)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUploadAvatarResponse,
						this::handleUploadAvatarError);
	}

	private void handleUploadAvatarResponse(UploadResult response) {
		avatarUploadSubscription.unsubscribe();
		getViewState().showAvatarProgress(false);
		getViewState().updateAvatar(response.getId().toString());

		ImageUtils.deleteTempFile(newAvatarFile);
	}

	private void handleUploadAvatarError(Throwable throwable) {
		avatarUploadSubscription.unsubscribe();
		getViewState().showAvatarProgress(false);

		ImageUtils.deleteTempFile(newAvatarFile);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.message != null) {
						getViewState().showSnackbarMessage(error.message);
						break;
					}
				}
			}
		}
	}

	private ProfileFullViewModel copyProfileModel(ProfileFullViewModel profile) {
		ProfileFullViewModel newProfile = new ProfileFullViewModel();

		newProfile.setId(profile.getId());
		newProfile.setAddress(profile.getAddress());
		newProfile.setLogoUrl(profile.getLogoUrl());
		newProfile.setBirthday(profile.getBirthday());
		newProfile.setCity(profile.getCity());
		newProfile.setCountry(profile.getCountry());
		newProfile.setCity(profile.getCity());
		newProfile.setEmail(profile.getEmail());
		newProfile.setFirstName(profile.getFirstName());
		newProfile.setMiddleName(profile.getMiddleName());
		newProfile.setLastName(profile.getLastName());
		newProfile.setGender(profile.isGender());
		newProfile.setUserName(profile.getUserName());

		return newProfile;
	}

	@Subscribe
	public void onEventMainThread(OnPictureChooserCameraClickedEvent event) {
		try {
			newAvatarFile = imageUtils.createImageFile();
			getViewState().openCamera(imageUtils, newAvatarFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Subscribe
	public void onEventMainThread(OnPictureChooserGalleryClickedEvent event) {
		try {
			newAvatarFile = imageUtils.createImageFile();
			getViewState().openGallery(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}
}