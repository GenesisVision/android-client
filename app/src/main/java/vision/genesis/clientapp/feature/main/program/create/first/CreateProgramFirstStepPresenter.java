package vision.genesis.clientapp.feature.main.program.create.first;

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
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCreateProgramFirstStepPassedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.model.events.SetCreateProgramDataEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/07/2018.
 */

@InjectViewState
public class CreateProgramFirstStepPresenter extends MvpPresenter<CreateProgramFirstStepView>
{
	@Inject
	public Context context;

	@Inject
	public ImageUtils imageUtils;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ProgramsManager programsManager;

	private File newLogoFile;

	private String logo;

	private String title;

	private String description;

	private Subscription logoUploadSubscription;

	private CreateProgramData createProgramData;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (logoUploadSubscription != null)
			logoUploadSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void checkNextButtonAvailability() {
		if (createProgramData == null)
			return;
		String title = this.title.trim();
		String description = this.description.trim();
		boolean titleLengthOk = !title.isEmpty() && title.length() <= createProgramData.getMaxTitleLength();
		boolean descriptionLengthOk = !description.isEmpty() && description.length() <= createProgramData.getMaxDescriptionLength();
		getViewState().setNextButtonAvailability(titleLengthOk && descriptionLengthOk);
	}

	void onTitleChanged(String title) {
		this.title = title;
		checkNextButtonAvailability();
	}

	void onDescriptionChanged(String description) {
		this.description = description;
		checkNextButtonAvailability();
	}

	void onNextButtonClicked() {
		EventBus.getDefault().post(new OnCreateProgramFirstStepPassedEvent(logo, title, description));
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

	private void uploadLogo() {
		getViewState().showLogoProgress(true);
		logoUploadSubscription = filesManager.uploadFile(newLogoFile)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUploadLogoResponse,
						this::handleUploadLogoError);
	}

	private void handleUploadLogoResponse(UploadResult response) {
		logoUploadSubscription.unsubscribe();
		getViewState().showLogoProgress(false);
		getViewState().updateLogo(response.getId().toString());

		this.logo = response.getId().toString();

		ImageUtils.deleteTempFile(newLogoFile);
	}

	private void handleUploadLogoError(Throwable throwable) {
		logoUploadSubscription.unsubscribe();
		getViewState().showLogoProgress(false);

		ImageUtils.deleteTempFile(newLogoFile);

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

	@Subscribe
	public void onEventMainThread(SetCreateProgramDataEvent event) {
		this.createProgramData = event.getCreateProgramData();
		getViewState().setLimits(createProgramData);
	}
}