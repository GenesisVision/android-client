package vision.genesis.clientapp.feature.main.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ImageCropActivity extends MvpAppCompatActivity
{
	private static String EXTRA_IMAGE_URI = "extra_image_uri";

	public static void startForResult(Activity activity, String imageUri) {
		Intent intent = new Intent(activity, ImageCropActivity.class);
		intent.putExtra(EXTRA_IMAGE_URI, imageUri);
		activity.startActivityForResult(intent, ImageUtils.CROP_REQUEST_CODE);
	}

	@Inject
	public Context context;

	@Inject
	public ImageUtils imageUtils;

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.crop_image_view)
	public CropImageView cropImageView;

	private String imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_crop);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		initToolbar();
		initCropImageView();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			setImageUri(getIntent().getExtras().getString(EXTRA_IMAGE_URI));
		}
		else {
			Timber.e("Passed empty imageUri to ImageCropActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.addLeftButton(R.drawable.ic_check_black_24dp, this::saveImage);
		toolbar.addRightButton(R.drawable.ic_close_black_24dp, () -> finishActivity(Activity.RESULT_CANCELED));
		toolbar.setLeftButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
		toolbar.setRightButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
	}

	private void initCropImageView() {
		cropImageView.setAspectRatio(1, 1);
		cropImageView.setFixedAspectRatio(true);
		cropImageView.setCropShape(CropImageView.CropShape.RECTANGLE);
		cropImageView.setScaleType(CropImageView.ScaleType.FIT_CENTER);
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
		Timber.d("Set imageUri: %s", imageUri);
		cropImageView.setImageUriAsync(Uri.parse(imageUri));
	}

	private void saveImage() {
		Bitmap croppedImage = cropImageView.getCroppedImage();
		if (ImageUtils.saveImageToFile(this, croppedImage, imageUri)) {
			finishActivity(Activity.RESULT_OK);
		}
		else {
			Snackbar.make(toolbar, "Cannot save the image. Please try again later or contact support", Snackbar.LENGTH_LONG).show();
		}
	}

	public void finishActivity(int resultCode) {
		setResult(resultCode);
		finish();
	}

	@Override
	public void onBackPressed() {
		finishActivity(Activity.RESULT_CANCELED);
	}
}
