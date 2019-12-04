package vision.genesis.clientapp.feature.common.public_info;

import android.content.Context;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.model.PublicInfoModel;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class PublicInfoPresenter extends MvpPresenter<PublicInfoView>
{
	@Inject
	public Context context;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newLogoFile;

	private Subscription logoUploadSubscription;

	private String title = "";

	private String description = "";

	private String logo = null;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (logoUploadSubscription != null) {
			logoUploadSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setModel(PublicInfoModel model) {
		if (model.getTitle() != null) {
			getViewState().setTitle(model.getTitle());
		}
		if (model.getDescription() != null) {
			getViewState().setDescription(model.getDescription());
		}
		if (model.getLogo() != null) {
			getViewState().showLogoProgress(false);
			getViewState().updateLogo(model.getLogo());
		}
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
		this.logo = null;
		getViewState().removeLogo();
	}

	void onConfirmClicked() {
		EventBus.getDefault().post(new OnPublicInfoConfirmButtonClickedEvent(title, description, logo));
	}

	void onTitleChanged(String title) {
		this.title = title.trim();
		checkButtonAvailability();
	}

	void onDescriptionChanged(String description) {
		this.description = description.trim();
		checkButtonAvailability();
	}

	private void checkButtonAvailability() {
		getViewState().setConfirmButtonEnabled(this.title.length() >= Constants.MIN_ASSET_NAME_LENGTH
				&& this.title.length() <= Constants.MAX_ASSET_NAME_LENGTH
				&& this.description.length() >= Constants.MIN_ASSET_DESCRIPTION_LENGTH
				&& this.description.length() <= Constants.MAX_ASSET_DESCRIPTION_LENGTH);
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
		getViewState().updateLogo(response.getId().toString());

		this.logo = response.getId().toString();

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

	@Subscribe
	public void onEventMainThread(OnPictureChooserCameraClickedEvent event) {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openCamera(imageUtils, newLogoFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Subscribe
	public void onEventMainThread(OnPictureChooserGalleryClickedEvent event) {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openGallery(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}
}
