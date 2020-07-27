package vision.genesis.clientapp.feature.main.social.post.create;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.ImageLocation;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.NewPostImage;
import io.swagger.client.model.Post;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.AutoCompleteGvAssetsView;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class CreatePostPresenter extends MvpPresenter<CreatePostView> implements NewPostImageView.PostImageClickListener, AutoCompleteGvAssetsView.Listener
{
	private static final int MASK_MIN_LENGTH = 3;

	@Inject
	public Context context;

	@Inject
	public SocialManager socialManager;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newImageFile;

	private Subscription uploadImageSubscription;

	private Subscription createPostSubscription;

	private NewPost post = new NewPost();

	private Post repost;

	private int textSelectionPos = -1;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		post.setImages(new ArrayList<>());
	}

	@Override
	public void onDestroy() {
		if (uploadImageSubscription != null) {
			uploadImageSubscription.unsubscribe();
		}
		if (createPostSubscription != null) {
			createPostSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(Post repost) {
		this.repost = repost;
		getViewState().showRepost(repost);
	}

	void onTextChanged(String text, int selectionStart) {
		this.post.setText(text);
		updatePublishButtonEnabled();

		textSelectionPos = selectionStart;

		if (selectionStart != -1) {
			int start = StringFormatUtil.getSocialAssetTagStartPos(text, selectionStart);
			int end = StringFormatUtil.getSocialAssetTagEndPos(text, selectionStart);

			if (end - start >= MASK_MIN_LENGTH) {
				getViewState().showAutoCompleteGvAssetsView(text.substring(start, end));
			}
			else {
				getViewState().hideAutoCompleteGvAssetsView();
			}
		}
	}

	private void updatePublishButtonEnabled() {
		getViewState().setPublishButtonEnabled(!isPostEmpty(post));
	}

	void handleCameraResult() {
		getViewState().startImageCropActivity(newImageFile.toURI().toString());
	}

	void handleGalleryResult(Uri pictureUri) {
		try {
			ImageUtils.copyFiles(new File(imageUtils.getImagePath(context, pictureUri)), newImageFile);
			getViewState().startImageCropActivity(newImageFile.toURI().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void handleImageCropResult() {
		uploadImage();
	}

	void handleCameraFail() {
		ImageUtils.deleteTempFile(newImageFile);
	}

	void handleGalleryFail() {
		ImageUtils.deleteTempFile(newImageFile);
	}

	void handleImageCropFail() {
		ImageUtils.deleteTempFile(newImageFile);
	}

	void onBackPressed() {
		if (isPostEmpty(post)) {
			getViewState().finishActivity();
		}
		else {
			getViewState().showExitDialog();
		}
	}

	void onPublishClicked() {
		getViewState().showProgressBar(true);
		createPost();
	}

	private void createPost() {
		if (socialManager != null && !isPostEmpty(post)) {
			createPostSubscription = (repost != null
					? socialManager.repost(post, repost.getId())
					: socialManager.createPost(post))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCreatePostSuccess,
							this::handleCreatePostError);
		}
	}

	private void handleCreatePostSuccess(Void response) {
		createPostSubscription.unsubscribe();
		getViewState().finishActivity();

	}

	private void handleCreatePostError(Throwable throwable) {
		createPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private boolean isPostEmpty(NewPost post) {
		return (post.getText() == null || post.getText().isEmpty())
				&& (post.getImages() == null || post.getImages().isEmpty());
	}

	private void uploadImage() {
		getViewState().createNewImageView();
		uploadImageSubscription = filesManager.uploadImage(newImageFile, ImageLocation.SOCIAL)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleUploadImageResponse,
						this::handleUploadImageError);
	}

	private void handleUploadImageResponse(UploadResult response) {
		uploadImageSubscription.unsubscribe();
		getViewState().updateNewImageView(response.getId().toString());
		updatePublishButtonEnabled();

		NewPostImage newPostImage = new NewPostImage();
		newPostImage.setImage(response.getId().toString());
		newPostImage.setPosition(post.getImages().size());
		post.getImages().add(newPostImage);

		ImageUtils.deleteTempFile(newImageFile);
	}

	private void handleUploadImageError(Throwable throwable) {
		uploadImageSubscription.unsubscribe();
		getViewState().deleteNewImageView();

		ImageUtils.deleteTempFile(newImageFile);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnPictureChooserCameraClickedEvent event) {
		try {
			newImageFile = imageUtils.createImageFile();
			getViewState().openCamera(imageUtils, newImageFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Subscribe
	public void onEventMainThread(OnPictureChooserGalleryClickedEvent event) {
		try {
			newImageFile = imageUtils.createImageFile();
			getViewState().openGallery(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Override
	public void onPostImageClicked(ImageView image, String imageId) {
		int position = 0;
		for (NewPostImage postImage : post.getImages()) {
			if (postImage.getImage().equals(imageId)) {
				break;
			}
			position++;
		}
		getViewState().showImageViewer(image, position, post.getImages());
	}

	@Override
	public void onDeleteImageClicked(NewPostImageView imageView, String imageId) {
		for (NewPostImage postImage : post.getImages()) {
			if (postImage.getImage().equals(imageId)) {
				post.getImages().remove(postImage);
				break;
			}
		}
		getViewState().deleteImageView(imageView);
		updatePublishButtonEnabled();
	}

	@Override
	public void onAssetClicked(String assetTag) {
		if (textSelectionPos != -1 && post != null) {
			int start = StringFormatUtil.getSocialAssetTagStartPos(post.getText(), textSelectionPos);
			int end = StringFormatUtil.getSocialAssetTagEndPos(post.getText(), textSelectionPos);

			String newText = post.getText().substring(0, start).concat(assetTag).concat(" ").concat(post.getText().substring(end));
			getViewState().setText(newText, start + assetTag.length() + 1);
			getViewState().hideAutoCompleteGvAssetsView();
		}
	}
}
