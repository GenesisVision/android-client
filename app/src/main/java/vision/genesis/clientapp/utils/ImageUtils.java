package vision.genesis.clientapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class ImageUtils
{
	public static final int CAMERA_REQUEST_CODE = 94;

	public static final int GALLERY_REQUEST_CODE = 25;

	private static final String TAG = ImageUtils.class + "";

	public static String getImageUri(String imageId) {
		String baseUrl = BuildConfig.FLAVOR.equals("tournament")
				? BuildConfig.TOURNAMENT_API_ADDRESS
				: BuildConfig.API_ADDRESS;
		return (baseUrl + "/api/files/" + imageId);
	}

	public void openCameraFrom(Activity activity) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
			activity.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openCameraFrom(Fragment fragment) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
			fragment.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openGalleryFrom(Activity activity) {
		Intent openGalleryIntent = new Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		activity.startActivityForResult(openGalleryIntent, GALLERY_REQUEST_CODE);
	}

	public void openCameraFrom(Fragment fragment, @NonNull File imageFile) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					FileProvider.getUriForFile(fragment.getContext(), BuildConfig.APPLICATION_ID + ".provider", imageFile));
			takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			fragment.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openGalleryFrom(Fragment fragment) {
		Intent openGalleryIntent = new Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		fragment.startActivityForResult(openGalleryIntent, GALLERY_REQUEST_CODE);
	}

	public void addImageToGallery(Uri contentUri, Context context) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}

	public String getImagePath(Context context, Uri data) {
		Timber.d(TAG, "Getting image path from %s", data);
		String[] filePathColumn = {MediaStore.Images.Media.DATA};

		Cursor cursor = context.getContentResolver().query(data,
				filePathColumn, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			return picturePath;
		}
		else {
			throw new RuntimeException("Could not find specified specified uri: " + data);
		}
	}

	public File createImageFile() throws IOException {
		String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
		String imageFileName = "GV_" + timestamp;
		return createImageFile(imageFileName);
	}

	public File createImageFile(String name) throws IOException {
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		return File.createTempFile(name, ".jpg", storageDir);
	}

	public Bitmap getBitmapFromURL(Context context, String bitmapAddress) {
		try {
			URL url = new URL(bitmapAddress);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			return bitmap == null ? BitmapFactory.decodeResource(context.getResources(), R.drawable.profile_placeholder) : bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return BitmapFactory.decodeResource(context.getResources(), R.drawable.profile_placeholder);
		}
	}
}
