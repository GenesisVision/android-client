package vision.genesis.clientapp.feature.main.social.media.details;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.MediaPost;
import io.swagger.client.model.PostImageResize;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2020.
 */

public class MediaPostDetailsActivity extends BaseSwipeBackActivity implements MediaPostDetailsView
{
	private static final String EXTRA_POST_ID = "extra_post_id";

	public static void startWith(Activity activity, UUID postId) {
		Intent intent = new Intent(activity.getApplicationContext(), MediaPostDetailsActivity.class);
		intent.putExtra(EXTRA_POST_ID, postId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.group_main)
	public ViewGroup mainGroup;

	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.type_image)
	public SimpleDraweeView typeImage;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.author_logo)
	public SimpleDraweeView authorLogo;

	@BindView(R.id.author_name)
	public TextView authorName;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	MediaPostDetailsPresenter presenter;

	private MediaPost post;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_main)
	public void onPostClicked() {
		try {
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getUrl()));
			startActivity(browserIntent);
		} catch (ActivityNotFoundException e) {
			showSnackbarMessage(getString(R.string.error_cannot_open_link));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_media_post_details);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			UUID postId = (UUID) getIntent().getExtras().getSerializable(EXTRA_POST_ID);
			if (postId != null) {
				presenter.setData(postId);
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

	@Override
	public void updateView(MediaPost post) {
		this.post = post;

		setImage(post);
		this.title.setText(post.getTitle());
		this.text.setText(post.getText());
		this.authorLogo.setImageURI(ImageUtils.getImageUri(post.getAuthorLogoUrl()));
		this.authorName.setText(post.getAuthor());
		this.date.setText(DateTimeUtil.formatEventDateTime(post.getDate()));

		this.typeImage.setImageURI(ImageUtils.getImageUri(post.getTypeLogoUrl()));
	}

	private void setImage(MediaPost post) {
		String logoUrl = post.getImage().getResizes().get(post.getImage().getResizes().size() - 1).getLogoUrl();
		for (PostImageResize resize : post.getImage().getResizes()) {
			if (resize.getQuality().equals(ImageQuality.HIGH)) {
				logoUrl = resize.getLogoUrl();
			}
		}
		this.image.setImageURI(ImageUtils.getImageUri(logoUrl));
		this.image.setVisibility(logoUrl == null || logoUrl.isEmpty() ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if (!show) {
			mainGroup.setVisibility(View.VISIBLE);
		}
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, mainGroup);
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
}