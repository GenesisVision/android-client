package vision.genesis.clientapp.feature.main.social.post.details;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.EditPost;
import io.swagger.client.model.EditablePost;
import io.swagger.client.model.ImageLocation;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.NewPostImage;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostImage;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.UploadResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.picture_chooser.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.managers.FilesManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.AutoCompleteGvAssetsView;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.ui.SocialCommentView;
import vision.genesis.clientapp.ui.SocialPostView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class PostDetailsPresenter extends MvpPresenter<PostDetailsView> implements NewPostImageView.PostImageClickListener, SocialCommentView.Listener, AutoCompleteGvAssetsView.Listener, SocialPostView.Listener, PictureChooserBottomSheetFragment.Listener
{
	private static final int MASK_MIN_LENGTH = 3;

	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SocialManager socialManager;

	@Inject
	public FilesManager filesManager;

	@Inject
	public ImageUtils imageUtils;

	private File newImageFile;

	private Subscription getProfileSubscription;

	private Subscription getPostSubscription;

	private Subscription getOriginalCommentSubscription;

	private Subscription uploadImageSubscription;

	private Subscription sendCommentSubscription;

	private UUID postId;

	private Post post;

	private NewPost newComment = new NewPost();

	private Post replyComment;

	private int textSelectionPos = -1;

	private ProfileFullViewModel profile;

	private EditPost editComment;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		newComment.setImages(new ArrayList<>());

		getProfileInfo();
		getPostDetails(false);
	}

	@Override
	public void onDestroy() {
		if (getProfileSubscription != null) {
			getProfileSubscription.unsubscribe();
		}
		if (getPostSubscription != null) {
			getPostSubscription.unsubscribe();
		}
		if (uploadImageSubscription != null) {
			uploadImageSubscription.unsubscribe();
		}
		if (sendCommentSubscription != null) {
			sendCommentSubscription.unsubscribe();
		}
		if (getOriginalCommentSubscription != null) {
			getOriginalCommentSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setData(UUID postId) {
		this.postId = postId;
	}

	void onResume() {
		getPostDetails(false);
	}

	void onCommentTextChanged(String text, int selectionStart) {
		this.newComment.setText(text);
		updateSendCommentButtonEnabled();

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

	private void updateSendCommentButtonEnabled() {
		getViewState().setSendCommentButtonEnabled(!isCommentEmpty(newComment));
		if (isCommentEmpty(newComment)) {
			editComment = null;
		}
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
		if (editComment != null) {
			editComment.setText(newComment.getText());
			editComment.setImages(newComment.getImages());
			editComment();
		}
		else {
			sendComment();
		}
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

	private void editComment() {
		if (socialManager != null && editComment != null) {
			getViewState().showAddCommentProgressBar(true);
			sendCommentSubscription = socialManager.editPost(editComment)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleEditCommentSuccess,
							this::handleEditCommentError);
		}
	}

	private void handleEditCommentSuccess(Void response) {
		sendCommentSubscription.unsubscribe();
		getPostDetails(false);

		getViewState().showAddCommentProgressBar(false);
		clearAddCommentSection();
	}

	private void handleEditCommentError(Throwable throwable) {
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
			if (getPostSubscription != null) {
				getPostSubscription.unsubscribe();
			}
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

	private void getProfileInfo() {
		getProfileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		this.profile = profile;
	}

	private void handleGetProfileError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getOriginalComment(Post comment) {
		if (socialManager != null) {
			if (getOriginalCommentSubscription != null) {
				getOriginalCommentSubscription.unsubscribe();
			}
			getOriginalCommentSubscription = socialManager.getOriginalPost(comment.getId())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetOriginalCommentSuccess,
							this::handleGetOriginalCommentError);
		}
	}

	private void handleGetOriginalCommentSuccess(EditablePost comment) {
		getOriginalCommentSubscription.unsubscribe();

		editComment = new EditPost();
		editComment.setId(comment.getId());

		newComment = new NewPost();
		newComment.setImages(new ArrayList<>());
		newComment.setText(comment.getTextOriginal());
		int i = 0;
		for (PostImage image : comment.getImages()) {
			NewPostImage newPostImage = new NewPostImage();
			newPostImage.setImage(image.getId());
			newPostImage.setPosition(i);
			newComment.getImages().add(newPostImage);
			getViewState().createNewImageView();
			getViewState().updateNewImageView(image.getId());
			i++;
		}
		updateImageCommentButtonEnabled();
		updateSendCommentButtonEnabled();
		getViewState().showEditComment(comment);
	}

	private void handleGetOriginalCommentError(Throwable throwable) {
		getOriginalCommentSubscription.unsubscribe();
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

	void onPostMenuButtonClicked(SocialPostActionsBottomSheetFragment.Listener listener) {
		boolean isOwnPost = false;
		if (profile != null) {
			isOwnPost = profile.getId().toString().equals(post.getAuthor().getId().toString());
		}
		getViewState().showSocialPostActions(post, SocialPostType.POST, isOwnPost, listener);
	}

	@Override
	public void onCommentMenuButtonClicked(Post comment, SocialPostActionsBottomSheetFragment.Listener listener) {
		boolean isOwnPost = false;
		if (profile != null) {
			isOwnPost = profile.getId().toString().equals(comment.getAuthor().getId().toString());
		}
		getViewState().showSocialPostActions(comment, SocialPostType.COMMENT, isOwnPost, listener);
	}

	@Override
	public void onCommentEditClicked(Post comment) {
		getOriginalComment(comment);
	}

	@Override
	public void onCommentDeleted(Post comment) {
		getPostDetails(false);
	}

	void onReplyNameClicked() {
		if (replyComment != null && replyComment.getAuthor() != null) {
			UserDetailsModel model = new UserDetailsModel(
					replyComment.getAuthor().getId(),
					replyComment.getAuthor().getLogoUrl(),
					replyComment.getAuthor().getUsername(),
					replyComment.getAuthor().getRegistrationDate());
			EventBus.getDefault().post(new ShowUserDetailsEvent(model));
		}
	}

	void onCancelReplyClicked() {
		this.replyComment = null;
		getViewState().hideReplyGroup();
	}

	@Override
	public void onAssetClicked(String assetTag) {
		if (textSelectionPos != -1 && newComment != null) {
			int start = StringFormatUtil.getSocialAssetTagStartPos(newComment.getText(), textSelectionPos);
			int end = StringFormatUtil.getSocialAssetTagEndPos(newComment.getText(), textSelectionPos);

			String newText = newComment.getText().substring(0, start).concat(assetTag).concat(" ").concat(newComment.getText().substring(end));
			getViewState().setText(newText, start + assetTag.length() + 1);
			getViewState().hideAutoCompleteGvAssetsView();
		}
	}

	@Override
	public void onPostMenuButtonClicked(Post post, SocialPostActionsBottomSheetFragment.Listener listener) {
	}

	@Override
	public void onPostEditClicked(Post post) {
		getViewState().showEditPost(post);
	}

	@Override
	public void onPostDeleted(Post post) {
		getViewState().finishActivity();
	}

	@Override
	public void onPictureChooserCameraClicked() {
		try {
			newImageFile = imageUtils.createImageFile();
			getViewState().openCameraChosen(imageUtils, newImageFile);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}

	@Override
	public void onPictureChooserGalleryClicked() {
		try {
			newImageFile = imageUtils.createImageFile();
			getViewState().openGalleryChosen(imageUtils);
		} catch (IOException e) {
			e.printStackTrace();
			getViewState().showSnackbarMessage(e.getMessage());
		}
	}
}
