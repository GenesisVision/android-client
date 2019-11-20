package vision.genesis.clientapp.feature.common.image_crop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ImageCropActivity extends MvpAppCompatActivity
{
	private static String EXTRA_IMAGE_URI = "extra_image_uri";

	private static String EXTRA_MIN_WIDTH = "extra_min_width";

	private static String EXTRA_MIN_HEIGHT = "extra_min_height";

	public static void startForResult(Activity activity, String imageUri, int minWidth, int minHeight) {
		Intent intent = new Intent(activity, ImageCropActivity.class);
		intent.putExtra(EXTRA_IMAGE_URI, imageUri);
		intent.putExtra(EXTRA_MIN_WIDTH, minWidth);
		intent.putExtra(EXTRA_MIN_HEIGHT, minHeight);
		activity.startActivityForResult(intent, ImageUtils.CROP_REQUEST_CODE);
	}

	public static void startForResult(Fragment fragment, String imageUri, int minWidth, int minHeight) {
		Intent intent = new Intent(fragment.getContext(), ImageCropActivity.class);
		intent.putExtra(EXTRA_IMAGE_URI, imageUri);
		intent.putExtra(EXTRA_MIN_WIDTH, minWidth);
		intent.putExtra(EXTRA_MIN_HEIGHT, minHeight);
		fragment.startActivityForResult(intent, ImageUtils.CROP_REQUEST_CODE);
	}

	@Inject
	public Context context;

	@Inject
	public ImageUtils imageUtils;

	@BindView(R.id.crop_image_view)
	public CropImageView cropImageView;

	private String imageUri;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity(Activity.RESULT_CANCELED);
	}

	@OnClick(R.id.button_ok)
	public void onOkClicked() {
		saveImage();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_crop);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);


		Bundle extras = getIntent().getExtras();
		if (extras != null && !extras.isEmpty()) {
			initCropImageView(extras.getInt(EXTRA_MIN_WIDTH), extras.getInt(EXTRA_MIN_HEIGHT));
			setImageUri(extras.getString(EXTRA_IMAGE_URI));
		}
		else {
			Timber.e("Passed empty imageUri to ImageCropActivity");
			onBackPressed();
		}
	}

	private void initCropImageView(int minWidth, int minHeight) {
		cropImageView.setAspectRatio(1, 1);
		cropImageView.setFixedAspectRatio(true);
		cropImageView.setMinCropResultSize(minWidth, minHeight);
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
			Snackbar.make(cropImageView, "Cannot save the image. Please try again later or contact support", Snackbar.LENGTH_LONG).show();
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
