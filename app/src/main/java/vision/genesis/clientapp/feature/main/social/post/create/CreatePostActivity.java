package vision.genesis.clientapp.feature.main.social.post.create;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import vision.genesis.clientapp.feature.common.image_crop.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.PictureChooserBottomSheetFragment;
import vision.genesis.clientapp.ui.AutoCompleteGvAssetsView;
import vision.genesis.clientapp.ui.ImageViewerOverlayView;
import vision.genesis.clientapp.ui.NewPostImageView;
import vision.genesis.clientapp.ui.PrimaryButton;
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
public class CreatePostActivity extends MvpAppCompatActivity implements CreatePostView
{
	private static final String EXTRA_REPOST = "extra_repost";

	private static final String EXTRA_EDIT_POST = "extra_edit_post";

	private static final String EXTRA_USER_ID = "extra_user_id";

	public static void startWith(Activity activity, Post repost, Post editPost, UUID userId) {
		Intent intent = new Intent(activity.getApplicationContext(), CreatePostActivity.class);
		intent.putExtra(EXTRA_REPOST, repost);
		intent.putExtra(EXTRA_EDIT_POST, editPost);
		intent.putExtra(EXTRA_USER_ID, userId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_main)
	public ViewGroup mainGroup;

	@BindView(R.id.scrollview)
	public ScrollView scrollview;

	@BindView(R.id.group_repost)
	public ViewGroup repostGroup;

	@BindView(R.id.repost)
	public SocialPostView repost;

	@BindView(R.id.new_post_text)
	public EditText text;

	@BindView(R.id.view_autocomplete_gv_assets)
	public AutoCompleteGvAssetsView autoCompleteGvAssetsView;

	@BindView(R.id.group_images)
	public LinearLayout imagesGroup;

	@BindView(R.id.button_publish)
	public PrimaryButton publishButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.progress_bar_button)
	public ProgressBar progressBarButton;

	@InjectPresenter
	CreatePostPresenter presenter;

	private Dialog messageDialog;

	private ArrayList<NewPostImageView> imageViews = new ArrayList<>();

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_image)
	public void onImageButtonClicked() {
		CreatePostActivityPermissionsDispatcher.showPictureChooserWithPermissionCheck(this);
	}

	@OnClick(R.id.button_user)
	public void onUserButtonClicked() {
		this.text.getText().insert(this.text.getSelectionStart(), "@");
	}

	@OnClick(R.id.button_asset)
	public void onAssetButtonClicked() {
		this.text.getText().insert(this.text.getSelectionStart(), "#");
	}

	@OnClick(R.id.button_publish)
	public void onPublishClicked() {
		hideSoftKeyboard();
		presenter.onPublishClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_create_post);

		ButterKnife.bind(this);

		publishButton.setEnabled(false);

		repost.setRepostMode(true);

		text.requestFocus();

		setTextListener();

		autoCompleteGvAssetsView.setListener(presenter);

		if (getIntent().getExtras() != null) {
			Post repost = getIntent().getExtras().getParcelable(EXTRA_REPOST);
			Post editPost = getIntent().getExtras().getParcelable(EXTRA_EDIT_POST);
			UUID userId = (UUID) getIntent().getExtras().getSerializable(EXTRA_USER_ID);
			if (repost != null) {
				presenter.setRepost(repost);
			}
			if (editPost != null) {
				this.title.setText(getString(R.string.edit_post));
				presenter.setPostToEdit(editPost);
			}
			else {
				showProgressBar(false);
			}
			if (userId != null) {
				presenter.setUserId(userId);
			}
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(text)
				.subscribe(charSequence -> presenter.onTextChanged(charSequence.toString(), text.getSelectionStart()));
	}

	@Override
	public void showRepost(Post repost) {
		this.repostGroup.setVisibility(View.VISIBLE);
		this.repost.setPost(repost);
	}

	@Override
	public void openCamera(ImageUtils imageUtils, File newLogoFile) {
		imageUtils.openCameraFrom(this, newLogoFile);
	}

	@Override
	public void openGallery(ImageUtils imageUtils) {
		imageUtils.openGalleryFrom(this);
	}

	@Override
	public void startImageCropActivity(String imageUri) {
		ImageCropActivity.startForResult(this, imageUri, Constants.MIN_LOGO_WIDTH, Constants.MIN_LOGO_HEIGHT);
	}

	@Override
	public void createNewImageView() {
		NewPostImageView view = new NewPostImageView(this);
		view.setListener(presenter);
		imagesGroup.addView(view);
		imageViews.add(view);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0, 0, TypedValueFormatter.toDp(10), 0);
		view.setLayoutParams(lp);

		scrollview.smoothScrollTo(-imagesGroup.getWidth(), 0);
	}

	@Override
	public void updateNewImageView(String imageId) {
		imageViews.get(imageViews.size() - 1).setData(imageId);
	}

	@Override
	public void deleteNewImageView() {
		NewPostImageView newImageView = imageViews.get(imageViews.size() - 1);
		imagesGroup.removeView(newImageView);
		imageViews.remove(newImageView);
	}

	@Override
	public void deleteImageView(NewPostImageView imageView) {
		imagesGroup.removeView(imageView);
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
		this.text.setText(newText);
		this.text.setSelection(selection);
	}

	@Override
	public void setPublishButtonEnabled(boolean enabled) {
		publishButton.setEnabled(enabled);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if (!show) {
			mainGroup.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showProgressBarButton(boolean show) {
		progressBarButton.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		publishButton.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
	}

	public void showSnackbarMessage(String message) {
		Snackbar.make(scrollview, message, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void showExitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.create_post_exit_message));
		builder.setPositiveButton(getString(R.string.finish), (dialogInterface, i) -> finishActivity());
		builder.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
	}

	@Override
	public void onBackPressed() {
		presenter.onBackPressed();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
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
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
	}

	@OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void showRationaleForStorage(PermissionRequest request) {
		showRationaleDialog(getString(R.string.permission_picture_rationale), request);
	}

	@OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageDenied() {
		showMessageDialog(getString(R.string.permission_picture_denied));
	}

	@OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
	void onStorageNeverAskAgain() {
		showMessageDialog(getString(R.string.permission_picture_never_ask_again));
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		text.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(text.getWindowToken(), 0);
		}
	}

	protected void showMessageDialog(String message) {
		if (messageDialog != null) {
			messageDialog.cancel();
		}
		messageDialog = showDialogMessage(message);
	}

	private Dialog showDialogMessage(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));

		return dialog;
	}

	protected void showRationaleDialog(String message, final PermissionRequest request) {
		AlertDialog rationaleDialog = new AlertDialog.Builder(this)
				.setPositiveButton(getString(R.string.allow), (dialog, which) -> request.proceed())
				.setNegativeButton(getString(R.string.deny), (dialog, which) -> request.cancel())
				.setCancelable(false)
				.setMessage(message)
				.show();

		rationaleDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
		rationaleDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
	}
}