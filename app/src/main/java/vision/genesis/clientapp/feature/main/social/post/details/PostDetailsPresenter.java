package vision.genesis.clientapp.feature.main.social.post.details;

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
import java.util.Locale;
import java.util.UUID;

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
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;
import vision.genesis.clientapp.model.events.ShowManagerDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.ui.SocialCommentView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class PostDetailsPresenter extends MvpPresenter<PostDetailsView> implements NewPostImageView.PostImageClickListener, SocialCommentView.Listener
{
	@Inject
	public Context context;

	@Inject
	public SocialManager socialManager;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newImageFile;

	private Subscription getPostSubscription;

	private Subscription uploadImageSubscription;

	private Subscription sendCommentSubscription;

	private UUID postId;

	private Post post;

	private NewPost newComment = new NewPost();

	private Post replyComment;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		newComment.setImages(new ArrayList<>());

		getPostDetails(false);
	}

	@Override
	public void onDestroy() {
		if (getPostSubscription != null) {
			getPostSubscription.unsubscribe();
		}
		if (uploadImageSubscription != null) {
			uploadImageSubscription.unsubscribe();
		}
		if (sendCommentSubscription != null) {
			sendCommentSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	public void setData(UUID postId) {
		this.postId = postId;
	}


	void onCommentTextChanged(String text) {
		this.newComment.setText(text);
		updateSendCommentButtonEnabled();
	}

	private void updateSendCommentButtonEnabled() {
		getViewState().setSendCommentButtonEnabled(!isCommentEmpty(newComment));
	}

	private void updateImageCommentButtonEnabled() {
		getViewState().setImageCommentButtonEnabled(newComment.getImages().size() < Constants.MIN_IMAGES_COUNT_ADD_COMMENT);
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

	void onSendCommentClicked() {
		sendComment();
	}

	private void sendComment() {
		if (socialManager != null && !isCommentEmpty(newComment)) {
			newComment.setPostId(postId);
			if (replyComment != null) {
				newComment.setText(String.format(Locale.getDefault(), "@%s (user) %s",
						replyComment.getAuthor().getUrl(), newComment.getText()));
			}
			getViewState().showAddCommentProgressBar(true);
			sendCommentSubscription = socialManager.sendComment(newComment)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSendCommentSuccess,
							this::handleSendCommentError);
		}
	}

	private void handleSendCommentSuccess(Void response) {
		sendCommentSubscription.unsubscribe();
		getPostDetails(true);
	}

	private void handleSendCommentError(Throwable throwable) {
		sendCommentSubscription.unsubscribe();
		getViewState().showAddCommentProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void clearAddCommentSection() {
		this.newComment = new NewPost();
		this.newComment.setImages(new ArrayList<>());
		this.replyComment = null;
		getViewState().clearAddCommentSection();
		getViewState().hideReplyGroup();
		updateSendCommentButtonEnabled();
		updateImageCommentButtonEnabled();
	}

	private boolean isCommentEmpty(NewPost comment) {
		return (comment.getText() == null || comment.getText().isEmpty())
				&& (comment.getImages() == null || comment.getImages().isEmpty());
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
		updateSendCommentButtonEnabled();

		NewPostImage newPostImage = new NewPostImage();
		newPostImage.setImage(response.getId().toString());
		newPostImage.setPosition(newComment.getImages().size());
		newComment.getImages().add(newPostImage);

		updateImageCommentButtonEnabled();
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
		for (NewPostImage postImage : newComment.getImages()) {
			if (postImage.getImage().equals(imageId)) {
				break;
			}
			position++;
		}
		getViewState().showImageViewer(image, position, newComment.getImages());
	}

	@Override
	public void onDeleteImageClicked(NewPostImageView imageView, String imageId) {
		for (NewPostImage postImage : newComment.getImages()) {
			if (postImage.getImage().equals(imageId)) {
				newComment.getImages().remove(postImage);
				break;
			}
		}
		getViewState().deleteImageView(imageView);
		updateSendCommentButtonEnabled();
		updateImageCommentButtonEnabled();
	}

	private void getPostDetails(boolean isCommentAdded) {
		if (socialManager != null && postId != null) {
			getPostSubscription = socialManager.getPost(postId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleGetPostSuccess(response, isCommentAdded),
							this::handleGetPostError);
		}
	}

	private void handleGetPostSuccess(Post response, boolean isCommentAdded) {
		getPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);
		getViewState().setRefreshing(false);

		if (isCommentAdded) {
			getViewState().showAddCommentProgressBar(false);
			getViewState().showMyAddedComment();
			clearAddCommentSection();
		}

		this.post = response;

		getViewState().updateView(post);
	}

	private void handleGetPostError(Throwable throwable) {
		getPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPostDetails(false);
	}

	@Override
	public void onReplyCommentClicked(Post comment) {
		this.replyComment = comment;
		getViewState().showReplyGroup(comment.getAuthor().getUsername());
	}

	void onReplyNameClicked() {
		if (replyComment != null && replyComment.getAuthor() != null) {
			ManagerDetailsModel model = new ManagerDetailsModel(
					replyComment.getAuthor().getId(),
					replyComment.getAuthor().getLogoUrl(),
					replyComment.getAuthor().getUsername(),
					replyComment.getAuthor().getRegistrationDate());
			EventBus.getDefault().post(new ShowManagerDetailsEvent(model));
		}
	}

	void onCancelReplyClicked() {
		this.replyComment = null;
		getViewState().hideReplyGroup();
	}
}
