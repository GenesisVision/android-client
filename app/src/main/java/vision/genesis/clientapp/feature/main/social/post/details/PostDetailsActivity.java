package vision.genesis.clientapp.feature.main.social.post.details;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.EditablePost;
import io.swagger.client.model.NewPostImage;
import io.swagger.client.model.Post;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.image_crop.ImageCropActivity;
import vision.genesis.clientapp.feature.common.picture_chooser.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.social.post.create.CreatePostActivity;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.ui.AutoCompleteGvAssetsView;
import vision.genesis.clientapp.ui.ImageViewerOverlayView;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.ui.SocialCommentView;
import vision.genesis.clientapp.ui.SocialPostView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@RuntimePermissions
public class PostDetailsActivity extends BaseSwipeBackActivity implements PostDetailsView
{
	private static final String EXTRA_POST_ID = "extra_post_id";

	private static final String EXTRA_POST = "extra_post";

	private static final String EXTRA_SHOW_COMMENTS = "extra_show_comments";

	public static void startWith(Activity activity, UUID postId, Post post, boolean showComments) {
		Intent intent = new Intent(activity.getApplicationContext(), PostDetailsActivity.class);
		intent.putExtra(EXTRA_POST_ID, postId);
		intent.putExtra(EXTRA_POST, post);
		intent.putExtra(EXTRA_SHOW_COMMENTS, showComments);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollview;

	@BindView(R.id.post_view)
	public SocialPostView postView;

	@BindView(R.id.comments_section)
	public ViewGroup commentsSection;

	@BindView(R.id.comments)
	public TextView comments;

	@BindView(R.id.group_comments)
	public LinearLayout commentsGroup;

	@BindView(R.id.view_autocomplete_gv_assets)
	public AutoCompleteGvAssetsView autoCompleteGvAssetsView;

	@BindView(R.id.group_add_comment)
	public ViewGroup addCommentGroup;

	@BindView(R.id.group_reply)
	public ViewGroup replyGroup;

	@BindView(R.id.reply)
	public TextView reply;

	@BindView(R.id.group_comment_images)
	public LinearLayout commentImagesGroup;

	@BindView(R.id.button_comment_image)
	public ImageView commentImageButton;

	@BindView(R.id.button_comment_send)
	public ImageView commentSendButton;

	@BindView(R.id.text_comment)
	public EditText commentText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.add_comment_progress_bar)
	public ProgressBar addCommentProgressBar;

	@InjectPresenter
	PostDetailsPresenter presenter;

	private ArrayList<NewPostImageView> imageViews = new ArrayList<>();

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_menu)
	public void onMenuClicked() {
		presenter.onPostMenuButtonClicked(postView);
	}

	@OnClick(R.id.button_cancel_reply)
	public void onCancelReplyClicked() {
		presenter.onCancelReplyClicked();
	}

	@OnClick(R.id.button_comment_image)
	public void onCommentImageClicked() {
		PostDetailsActivityPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_comment_send)
	public void onCommentSendClicked() {
		presenter.onSendCommentClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_post_details);

		ButterKnife.bind(this);

		initRefreshLayout();

		setSendCommentButtonEnabled(false);
		setTextListener();

		autoCompleteGvAssetsView.setListener(presenter);

		if (getIntent().getExtras() != null) {
			UUID postId = (UUID) getIntent().getExtras().getSerializable(EXTRA_POST_ID);
			Post post = getIntent().getExtras().getParcelable(EXTRA_POST);
			boolean showComments = getIntent().getExtras().getBoolean(EXTRA_SHOW_COMMENTS, false);
			if (postId != null) {
				if (post != null) {
					updateView(post);
					if (showComments) {
						scrollview.post(() -> scrollview.scrollTo(0, commentsSection.getTop()));
					}
				}
				presenter.setData(postId);
				postView.setDetailsMode(true);
				postView.setListener(presenter);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	protected void onResume() {
		super.onResume();
		if (presenter != null) {
			presenter.onResume();
		}
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
	}

	@Override
	public void updateView(Post post) {
		postView.setPost(post);

		if (post.getComments() != null && !post.getComments().isEmpty()) {
			this.commentsSection.setVisibility(View.VISIBLE);
			this.comments.setText(String.format(Locale.getDefault(), "%d %s",
					post.getComments().size(),
					GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.comments, post.getComments().size())));

			this.commentsGroup.removeAllViews();
			for (Post comment : post.getComments()) {
				SocialCommentView view = new SocialCommentView(this);
				view.setCanCommentPost(post.getPersonalDetails() != null && post.getPersonalDetails().isCanComment());
				view.setComment(comment);
				view.setListener(presenter);
				commentsGroup.addView(view);
			}
		}
		else {
			this.commentsSection.setVisibility(View.GONE);
		}

		if (post.getPersonalDetails() != null && post.getPersonalDetails().isCanComment()) {
			this.addCommentGroup.setVisibility(View.VISIBLE);
		}
		else {
			this.addCommentGroup.setVisibility(View.GONE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, postView);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}

	private void setTextListener() {
		RxTextView.textChanges(commentText)
				.subscribe(charSequence -> presenter.onCommentTextChanged(charSequence.toString(), commentText.getSelectionStart()));
	}

	@Override
	public void openCameraChosen(ImageUtils imageUtils, File newLogoFile) {
		PostDetailsActivityPermissionsDispatcher.openCameraWithPermissionCheck(this, imageUtils, newLogoFile);
	}

	@Override
	public void openGalleryChosen(ImageUtils imageUtils) {
		PostDetailsActivityPermissionsDispatcher.openGalleryWithPermissionCheck(this, imageUtils);
	}

	@Override
	public void startImageCropActivity(String imageUri) {
		ImageCropActivity.startForResult(this, imageUri, Constants.MIN_LOGO_WIDTH, Constants.MIN_LOGO_HEIGHT);
	}

	@Override
	public void createNewImageView() {
		NewPostImageView view = new NewPostImageView(this);
		view.setListener(presenter);
		commentImagesGroup.addView(view);
		imageViews.add(view);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0, 0, TypedValueFormatter.toDp(10), 0);
		view.setLayoutParams(lp);

		scrollview.smoothScrollTo(-commentImagesGroup.getWidth(), 0);
	}

	@Override
	public void updateNewImageView(String imageId) {
		imageViews.get(imageViews.size() - 1).setData(imageId);
	}

	@Override
	public void deleteNewImageView() {
		NewPostImageView newImageView = imageViews.get(imageViews.size() - 1);
		commentImagesGroup.removeView(newImageView);
		imageViews.remove(newImageView);
	}

	@Override
	public void deleteImageView(NewPostImageView imageView) {
		commentImagesGroup.removeView(imageView);
		imageViews.remove(imageView);
	}

	@Override
	public void showImageViewer(ImageView imageView, int position, List<NewPostImage> images) {
		ImageViewerOverlayView overlayView = new ImageViewerOverlayView(this);
		overlayView.setImagesCount(images.size());

		StfalconImageViewer imageViewer = new StfalconImageViewer.Builder<>(this, images,
				(loadingImageView, image) -> Glide.with(this).load(ImageUtils.getImageUriById(image.getImage())).into(loadingImageView))
				.withStartPosition(position)
				.withHiddenStatusBar(false)
				.withTransitionFrom(imageView)
				.withOverlayView(overlayView)
				.withImageChangeListener(overlayView)
				.show();

		overlayView.setImageViewer(imageViewer);
	}

	@Override
	public void showAutoCompleteGvAssetsView(String mask) {
		autoCompleteGvAssetsView.onMaskChanged(mask);
		autoCompleteGvAssetsView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideAutoCompleteGvAssetsView() {
		autoCompleteGvAssetsView.clear();
		autoCompleteGvAssetsView.setVisibility(View.GONE);
	}

	@Override
	public void setText(String newText, int selection) {
		this.commentText.setText(newText);
		this.commentText.setSelection(selection);
	}


	@Override
	public void setSendCommentButtonEnabled(boolean enabled) {
		commentSendButton.setEnabled(enabled);
		commentSendButton.setAlpha(enabled ? 1.0f : 0.3f);
	}

	@Override
	public void showReplyGroup(String username) {
		this.replyGroup.setVisibility(View.VISIBLE);

		SpannableString spannable = new SpannableString(String.format(Locale.getDefault(), "%s %s",
				getString(R.string.reply_to), username));

		spannable.setSpan(new ClickableSpan()
		                  {
			                  @Override
			                  public void onClick(View view) {
				                  presenter.onReplyNameClicked();
			                  }

			                  @Override
			                  public void updateDrawState(TextPaint ds) {
				                  super.updateDrawState(ds);
				                  int linkColor = ContextCompat.getColor(getApplicationContext(), R.color.accent);
				                  ds.setColor(linkColor);
				                  ds.setUnderlineText(false);
			                  }
		                  },
				getString(R.string.reply_to).length() + 1,
				spannable.toString().length(),
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		this.reply.setText(spannable);
		this.reply.setMovementMethod(LinkMovementMethod.getInstance());

		showSoftKeyboard(this.reply);
	}

	@Override
	public void hideReplyGroup() {
		this.replyGroup.setVisibility(View.GONE);
	}

	@Override
	public void setImageCommentButtonEnabled(boolean enabled) {
		commentImageButton.setEnabled(enabled);
		commentImageButton.setAlpha(enabled ? 1.0f : 0.3f);
	}

	@Override
	public void showAddCommentProgressBar(boolean show) {
		addCommentProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		commentSendButton.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void clearAddCommentSection() {
		this.commentText.setText("");
		this.imageViews.clear();
		this.commentImagesGroup.removeAllViews();
		this.commentImageButton.setEnabled(true);
		hideSoftKeyboard();
	}

	@Override
	public void showMyAddedComment() {
		scrollview.post(() -> scrollview.smoothScrollTo(0, commentsSection.getBottom()));
	}

	@Override
	public void showSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener) {
		SocialPostActionsBottomSheetFragment bottomSheetDialog = new SocialPostActionsBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(post, type, isOwnPost);
		bottomSheetDialog.setListener(listener);
	}

	@Override
	public void showEditPost(Post post) {
		CreatePostActivity.startWith(this, null, post, null);
	}

	@Override
	public void showEditComment(EditablePost comment) {
		this.commentText.setText(comment.getTextOriginal());
		this.commentText.setSelection(comment.getTextOriginal().length());
		showSoftKeyboard(commentText);
	}

	private void showSoftKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		if (imm != null) {
			imm.showSoftInput(view, 0);
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		commentText.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(commentText.getWindowToken(), 0);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case ImageUtils.CAMERA_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleCameraResult();
				}
				else {
					presenter.handleCameraFail();
				}
				break;
			case ImageUtils.GALLERY_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleGalleryResult(data.getData());
				}
				else {
					presenter.handleGalleryFail();
				}
				break;
			case ImageUtils.CROP_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					presenter.handleImageCropResult();
				}
				else {
					presenter.handleImageCropFail();
				}
				break;
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showPictureChooser() {
		PictureChooserBottomSheetFragment bottomSheetDialog = new PictureChooserBottomSheetFragment();
		bottomSheetDialog.setListener(presenter);
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
	}

	@NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void openGallery(ImageUtils imageUtils) {
		imageUtils.openGalleryFrom(this);
	}

	@OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_storage_rationale), request);
	}

	@OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageDenied() {
		showMessageDialog(getString(R.string.permission_storage_denied));
	}

	@OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_storage_never_ask_again));
	}

	@NeedsPermission({Manifest.permission.CAMERA})
	void openCamera(ImageUtils imageUtils, File newLogoFile) {
		imageUtils.openCameraFrom(this, newLogoFile);
	}

	@OnShowRationale({Manifest.permission.CAMERA})
	void showRationaleForCamera(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_camera_rationale), request);
	}

	@OnPermissionDenied({Manifest.permission.CAMERA})
	void onCameraDenied() {
		showMessageDialog(getString(R.string.permission_camera_denied));
	}

	@OnNeverAskAgain({Manifest.permission.CAMERA})
	void onCameraNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_camera_never_ask_again));
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		PostDetailsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}
}