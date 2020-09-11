package vision.genesis.clientapp.feature.main.settings.public_info;

import android.content.Context;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.model.events.OnProfilePublicInfoFilledEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/12/2019.
 */

@InjectViewState
public class ProfilePublicInfoPresenter extends MvpPresenter<ProfilePublicInfoView> implements PictureChooserBottomSheetFragment.Listener
{
	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newLogoFile;

	private Subscription profileSubscription;

	private Subscription logoUploadSubscription;

	private Subscription updateProfileSubscription;

	private Subscription updateAvatarSubscription;

	private String username = "";

	private String about = "";

	private String avatar = null;

	private Boolean needFillProfile;

	private ProfileFullViewModel profile;

	private boolean isFinalProfileUpdating = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getProfileInfo();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}
		if (logoUploadSubscription != null) {
			logoUploadSubscription.unsubscribe();
		}
		if (updateProfileSubscription != null) {
			updateProfileSubscription.unsubscribe();
		}
		if (updateAvatarSubscription != null) {
			updateAvatarSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(boolean needFillProfile) {
		this.needFillProfile = needFillProfile;
		if (needFillProfile) {
			getViewState().showProgress(false);
		}
		else {
			getProfileInfo();
		}
	}

	void onUsernameChanged(String username) {
		this.username = username.trim();
		checkButtonAvailability();
	}

	void onAboutChanged(String about) {
		this.about = about.trim();
		checkButtonAvailability();
	}

	void handleCameraResult() {
		getViewState().startImageCropActivity(newLogoFile.toURI().toString());
	}

	void handleGalleryResult(Uri pictureUri) {
		try {
			ImageUtils.copyFiles(new File(imageUtils.getImagePath(context, pictureUri)), newLogoFile);
			getViewState().startImageCropActivity(newLogoFile.toURI().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void handleImageCropResult() {
		uploadLogo();
	}

	void handleCameraFail() {
		ImageUtils.deleteTempFile(newLogoFile);
	}

	void handleGalleryFail() {
		ImageUtils.deleteTempFile(newLogoFile);
	}

	void handleImageCropFail() {
		ImageUtils.deleteTempFile(newLogoFile);
	}

	void onRemoveLogoClicked() {
		if (logoUploadSubscription != null) {
			logoUploadSubscription.unsubscribe();
		}
		if (newLogoFile != null) {
			ImageUtils.deleteTempFile(newLogoFile);
		}
		this.avatar = null;
		getViewState().removeLogo();
	}

	void onConfirmClicked() {
		updateProfile();
	}

	private void getProfileInfo() {
		if (profileManager != null && needFillProfile != null) {
			profileSubscription = profileManager.getProfileFull(true)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetProfileSuccess,
							this::handleGetProfileError);
		}

	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		profileSubscription.unsubscribe();

		if (isFinalProfileUpdating) {
			EventBus.getDefault().post(new OnProfilePublicInfoFilledEvent());
			getViewState().finishActivity();
			return;
		}

		getViewState().showProgress(false);

		this.profile = profile;

		if (profile.getUserName() != null) {
			getViewState().setUsername(profile.getUserName());
		}
		if (profile.getAbout() != null) {
			getViewState().setAbout(profile.getAbout());
		}
		if (profile.getLogoUrl() != null) {
			this.avatar = profile.getLogoUrl();
			getViewState().showLogoProgress(false);
			getViewState().updateLogo(profile.getLogoUrl());
		}
	}

	private void handleGetProfileError(Throwable throwable) {
		profileSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void updateProfile() {
		getViewState().showButtonProgress(true);

		UpdateProfileViewModel model = new UpdateProfileViewModel();
		model.setUserName(username);
		model.setAbout(about);

		updateProfileSubscription = profileManager.updateProfile(model)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUpdateProfileResponse,
						this::handleUpdateProfileError);
	}

	private void handleUpdateProfileResponse(Void response) {
		updateProfileSubscription.unsubscribe();

		if (!Objects.equals(profile.getLogoUrl(), avatar)) {
			if (avatar == null || avatar.isEmpty()) {
				removeAvatar();
			}
			else {
				updateAvatar();
			}
		}
		else {
			onUpdateSuccess();
		}
	}

	private void handleUpdateProfileError(Throwable throwable) {
		updateProfileSubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void updateAvatar() {
		getViewState().showButtonProgress(true);

		updateAvatarSubscription = profileManager.updateAvatar(avatar != null ? avatar : "")
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUpdateAvatarResponse,
						this::handleUpdateAvatarError);
	}

	private void handleUpdateAvatarResponse(Void response) {
		updateAvatarSubscription.unsubscribe();

		onUpdateSuccess();
	}

	private void handleUpdateAvatarError(Throwable throwable) {
		updateAvatarSubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void removeAvatar() {
		getViewState().showButtonProgress(true);

		updateAvatarSubscription = profileManager.removeAvatar()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUpdateAvatarResponse,
						this::handleUpdateAvatarError);
	}

	private void onUpdateSuccess() {
		isFinalProfileUpdating = true;
		getProfileInfo();
	}

	private void checkButtonAvailability() {
		getViewState().setConfirmButtonEnabled(this.username.length() >= Constants.MIN_USER_NAME_LENGTH
				&& this.username.length() <= Constants.MAX_USER_NAME_LENGTH);
	}

	private void uploadLogo() {
		getViewState().showLogoProgress(true);
		logoUploadSubscription = filesManager.uploadImage(newLogoFile)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUploadAvatarResponse,
						this::handleUploadAvatarError);
	}

	private void handleUploadAvatarResponse(UploadResult response) {
		logoUploadSubscription.unsubscribe();
		getViewState().showLogoProgress(false);
		getViewState().updateLogoById(response.getId().toString());

		this.avatar = response.getId().toString();

		ImageUtils.deleteTempFile(newLogoFile);
	}

	private void handleUploadAvatarError(Throwable throwable) {
		logoUploadSubscription.unsubscribe();
		getViewState().showLogoProgress(false);

		ImageUtils.deleteTempFile(newLogoFile);

		getViewState().removeLogo();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onPictureChooserCameraClicked() {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openCamera(imageUtils, newLogoFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Override
	public void onPictureChooserGalleryClicked() {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openGallery(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}
}
