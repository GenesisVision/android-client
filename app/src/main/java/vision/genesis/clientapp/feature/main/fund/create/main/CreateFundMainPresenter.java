package vision.genesis.clientapp.feature.main.fund.create.main;

import android.content.Context;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class CreateFundMainPresenter extends MvpPresenter<CreateFundMainView>
{
	@Inject
	public Context context;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newLogoFile;

	private Subscription logoUploadSubscription;

	private NewFundRequest request;

	private String name = "";

	private String description = "";

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

	void setRequest(NewFundRequest request) {
		this.request = request;
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
		request.setLogo(null);
		getViewState().removeLogo();
	}

	void onNameChanged(String name) {
		if (request != null) {
			this.name = name.trim();
			request.setTitle(name);
		}
		checkNextButtonAvailability();
	}

	void onDescriptionChanged(String description) {
		if (request != null) {
			this.description = description.trim();
			request.setDescription(description);
		}
		checkNextButtonAvailability();
	}

	private void checkNextButtonAvailability() {
		if (request != null) {
			getViewState().setNextButtonEnabled(name.length() >= Constants.MIN_FUND_NAME_LENGTH
					&& name.length() <= Constants.MAX_FUND_NAME_LENGTH
					&& description.length() >= Constants.MIN_FUND_DESCRIPTION_LENGTH
					&& description.length() <= Constants.MAX_FUND_DESCRIPTION_LENGTH);
		}
		else {
			getViewState().setNextButtonEnabled(false);
		}
	}

	private void uploadLogo() {
		getViewState().showLogoProgress(true);
		logoUploadSubscription = filesManager.uploadFile(newLogoFile)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUploadAvatarResponse,
						this::handleUploadAvatarError);
	}

	private void handleUploadAvatarResponse(UploadResult response) {
		logoUploadSubscription.unsubscribe();
		getViewState().showLogoProgress(false);
		getViewState().updateLogo(response.getId().toString());

		request.setLogo(response.getId().toString());

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
