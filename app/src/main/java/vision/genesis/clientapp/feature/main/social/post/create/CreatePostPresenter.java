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
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.EditPost;
import io.swagger.client.model.EditablePost;
import io.swagger.client.model.ImageLocation;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.NewPostImage;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostImage;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.events.OnNewPostCreatedEvent;
import vision.genesis.clientapp.model.events.OnNewPostEditedEvent;
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

	private Subscription getOriginalPostSubscription;

	private Subscription uploadImageSubscription;

	private Subscription createPostSubscription;

	private NewPost post = new NewPost();

	private Post repost;

	private Post postToEdit;

	private EditPost editPost;

	private int textSelectionPos = -1;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		post.setImages(new ArrayList<>());

		if (postToEdit != null) {
			getOriginalPost(postToEdit.getId());
		}
	}

	@Override
	public void onDestroy() {
		if (getOriginalPostSubscription != null) {
			getOriginalPostSubscription.unsubscribe();
		}
		if (uploadImageSubscription != null) {
			uploadImageSubscription.unsubscribe();
		}
		if (createPostSubscription != null) {
			createPostSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setRepost(Post repost) {
		this.repost = repost;
		getViewState().showRepost(repost);
	}

	void setPostToEdit(Post postToEdit) {
		this.postToEdit = postToEdit;
		getOriginalPost(postToEdit.getId());
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
		getViewState().showProgressBarButton(true);
		if (editPost == null) {
			createPost();
		}
		else {
			editPost();
		}
	}

	private void getOriginalPost(UUID postId) {
		if (socialManager != null) {
			if (getOriginalPostSubscription != null) {
				getOriginalPostSubscription.unsubscribe();
			}
			getOriginalPostSubscription = socialManager.getOriginalPost(postId)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetOriginalCommentSuccess,
							this::handleGetOriginalCommentError);
		}
	}

	private void handleGetOriginalCommentSuccess(EditablePost post) {
		getOriginalPostSubscription.unsubscribe();

		editPost = new EditPost();
		editPost.setId(post.getId());
		this.post.setImages(new ArrayList<>());
		this.post.setText(post.getTextOriginal());
		int i = 0;
		for (PostImage image : post.getImages()) {
			NewPostImage newPostImage = new NewPostImage();
			newPostImage.setImage(image.getId());
			newPostImage.setPosition(i);
			this.post.getImages().add(newPostImage);
			getViewState().createNewImageView();
			getViewState().updateNewImageView(image.getId());
			i++;
		}
		updatePublishButtonEnabled();
		getViewState().setText(this.post.getText(), this.post.getText().length());

		getViewState().showProgressBar(false);
	}

	private void handleGetOriginalCommentError(Throwable throwable) {
		getOriginalPostSubscription.unsubscribe();
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
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
		EventBus.getDefault().post(new OnNewPostCreatedEvent());
		getViewState().finishActivity();
	}

	private void handleCreatePostError(Throwable throwable) {
		createPostSubscription.unsubscribe();
		getViewState().showProgressBarButton(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void editPost() {
		if (socialManager != null && editPost != null) {
			editPost.setText(post.getText());
			editPost.setImages(post.getImages());
			createPostSubscription = socialManager.editPost(editPost)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleEditPostSuccess,
							this::handleEditPostError);
		}
	}

	private void handleEditPostSuccess(Void response) {
		createPostSubscription.unsubscribe();
		EventBus.getDefault().post(new OnNewPostEditedEvent());
		getViewState().finishActivity();
	}

	private void handleEditPostError(Throwable throwable) {
		createPostSubscription.unsubscribe();
		getViewState().showProgressBarButton(false);

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

	public void setUserId(UUID userId) {
		post.setUserId(userId);
	}
}
