package vision.genesis.clientapp.feature.common.public_info;

import android.content.Context;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.model.PublicInfoModel;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ValidatorUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class PublicInfoPresenter extends MvpPresenter<PublicInfoView> implements PictureChooserBottomSheetFragment.Listener
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

	private PublicInfoModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (logoUploadSubscription != null) {
			logoUploadSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setModel(PublicInfoModel model) {
		this.model = model;
		if (model.getTitle() != null) {
			getViewState().setTitle(model.getTitle());
		}
		if (model.getDescription() != null) {
			getViewState().setDescription(model.getDescription());
		}
		if (model.getLogo() != null && !model.getLogo().isEmpty()) {
			this.logo = model.getLogo();
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
		checkTitleError();
		if (model != null && !model.isShowDescription()) {
			checkIfTitleOk();
		}
		checkButtonAvailability();
	}

	void onDescriptionChanged(String description) {
		this.description = description.trim();
		checkDescriptionError();
		checkButtonAvailability();
	}

	void onTitleFocusLost() {
		checkIfTitleOk();
	}

	private void checkIfTitleOk() {
		if (this.title.length() < Constants.MIN_ASSET_NAME_LENGTH && context != null) {
			getViewState().showTitleError(String.format(Locale.getDefault(), context.getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_NAME_LENGTH));
		}
	}

	void onDescriptionFocusLost() {
		if (this.description.length() < Constants.MIN_ASSET_DESCRIPTION_LENGTH && context != null) {
			getViewState().showDescriptionError(String.format(Locale.getDefault(), context.getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_DESCRIPTION_LENGTH));
		}
	}

	private void checkTitleError() {
		if (this.title.isEmpty()) {
			getViewState().cleanTitleError();
			return;
		}
		if (!ValidatorUtil.isTitleValid(this.title) && context != null) {
			getViewState().showTitleError(context.getString(R.string.error_title_not_valid));
		}
		else if (this.title.length() >= Constants.MIN_ASSET_NAME_LENGTH) {
			getViewState().cleanTitleError();
		}
	}

	private void checkDescriptionError() {
		if (this.description.isEmpty()) {
			getViewState().cleanDescriptionError();
			return;
		}
		if (this.description.length() >= Constants.MIN_ASSET_DESCRIPTION_LENGTH) {
			getViewState().cleanDescriptionError();
		}
	}

	private void checkButtonAvailability() {
		boolean titleOk = this.title.length() >= Constants.MIN_ASSET_NAME_LENGTH
				&& this.title.length() <= Constants.MAX_ASSET_NAME_LENGTH
				&& ValidatorUtil.isTitleValid(this.title);

		boolean descriptionOk = false;
		if (model != null) {
			if (model.isShowDescription()) {
				descriptionOk = this.description.length() >= Constants.MIN_ASSET_DESCRIPTION_LENGTH
						&& this.description.length() <= Constants.MAX_ASSET_DESCRIPTION_LENGTH;
			}
			else {
				descriptionOk = true;
			}
		}

		getViewState().setConfirmButtonEnabled(titleOk && descriptionOk);
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

	@Override
	public void onPictureChooserCameraClicked() {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openCameraChosen(imageUtils, newLogoFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Override
	public void onPictureChooserGalleryClicked() {
		try {
			newLogoFile = imageUtils.createImageFile();
			getViewState().openGalleryChosen(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}
}
