package vision.genesis.clientapp.feature.main.social.post.details;

import android.widget.ImageView;

import com.arellomobile.mvp.MvpView;

import java.io.File;
import java.util.List;

import io.swagger.client.model.NewPostImage;
import io.swagger.client.model.Post;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface PostDetailsView extends MvpView
{
	void updateView(Post post);

	void openCamera(ImageUtils imageUtils, File newLogoFile);

	void openGallery(ImageUtils imageUtils);

	void startImageCropActivity(String imageUri);

	void createNewImageView();

	void updateNewImageView(String imageId);

	void deleteNewImageView();

	void deleteImageView(NewPostImageView imageView);

	void showImageViewer(ImageView image, int position, List<NewPostImage> images);

	void showAutoCompleteGvAssetsView(String mask);

	void hideAutoCompleteGvAssetsView();

	void setText(String newText, int selection);

	void setSendCommentButtonEnabled(boolean enabled);

	void showReplyGroup(String username);

	void hideReplyGroup();

	void setImageCommentButtonEnabled(boolean enabled);

	void showAddCommentProgressBar(boolean show);

	void clearAddCommentSection();

	void showMyAddedComment();

	void setRefreshing(boolean refreshing);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);
}
