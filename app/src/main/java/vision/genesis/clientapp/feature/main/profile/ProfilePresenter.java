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
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.model.events.SetBottomMenuVisibilityEvent;
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
	public ImageUtils imageUtils;

	private Subscription profileSubscription;

	private Subscription avatarUploadSubscription;

	private Subscription updateProfileSubscription;

	private ProfileFullViewModel profileModel;

	private File newAvatarFile;

	private boolean isEditMode = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getProfile();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		EventBus.getDefault().unregister(this);

		if (profileSubscription != null)
			profileSubscription.unsubscribe();

		if (avatarUploadSubscription != null)
			avatarUploadSubscription.unsubscribe();

		if (updateProfileSubscription != null)
			updateProfileSubscription.unsubscribe();
	}

	void onEditModeClicked() {
		setEditMode(true);
	}

	void onLogoutClicked() {
		authManager.logout();
	}

	void saveChanges(ProfileFullViewModel newProfileModel) {
		updateProfile(newProfileModel);
	}

	void cancelChanges() {
		getViewState().updateProfile(profileModel);
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
		EventBus.getDefault().post(new SetBottomMenuVisibilityEvent(!editMode));
	}

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profileModel) {
		this.profileModel = profileModel;
		getViewState().updateProfile(profileModel);
	}

	private void handleGetProfileError(Throwable error) {

	}

	private void updateProfile(ProfileFullViewModel newProfileModel) {
		getViewState().showUpdateProgress(true);
		updateProfileSubscription = profileManager.updateProfile(newProfileModel)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleUpdateProfileSuccess(response, newProfileModel),
						this::handleUpdateProfileError);
	}

	private void handleUpdateProfileSuccess(Void response, ProfileFullViewModel newProfileModel) {
		this.profileModel = newProfileModel;
		getViewState().updateProfile(profileModel);
		getViewState().showSnackbarMessage(context.getResources().getString(R.string.successfully_updated));
		setEditMode(false);
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
		avatarUploadSubscription = profileManager.uploadAvatar(newAvatarFile)
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
