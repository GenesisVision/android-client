package vision.genesis.clientapp.feature.main.program.create.first;

import com.arellomobile.mvp.MvpView;

import java.io.File;

import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/07/2018.
 */

public interface CreateProgramFirstStepView extends MvpView
{
	void setLimits(CreateProgramData createProgramData);

	void updateLogo(String avatarId);

	void openCamera(ImageUtils imageUtils, File newAvatarFile);

	void openGallery(ImageUtils imageUtils);

	void startImageCropActivity(String path);

	void setNextButtonAvailability(boolean available);

	void showSnackbarMessage(String message);

	void showLogoProgress(boolean show);

	void showNextProgress(boolean show);
}
