package vision.genesis.clientapp.feature.main.message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PrimaryButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */

public class MessageActivity extends MvpAppCompatActivity
{
	private static String EXTRA_MESSAGE = "extra_message";

	private static String EXTRA_IMAGE_RESOURCE_ID = "extra_image_resource_id";

	private static String EXTRA_MUST_READ = "extra_must_read";

	public static void startWith(Activity activity, String message, int imageResourceId, boolean mustRead) {
		Intent intent = new Intent(activity, MessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra(EXTRA_IMAGE_RESOURCE_ID, imageResourceId);
		intent.putExtra(EXTRA_MUST_READ, mustRead);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@Inject
	public Context context;

	@BindView(R.id.image)
	public ImageView image;

	@BindView(R.id.message)
	public TextView message;

	@BindView(R.id.button)
	public PrimaryButton button;

	private boolean mustRead = false;

	@OnClick(R.id.button)
	public void onButtonClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_message);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		button.setWhite();

		Bundle extras = getIntent().getExtras();

		if (extras != null && !extras.isEmpty()) {
			image.setImageDrawable(ContextCompat.getDrawable(context, extras.getInt(EXTRA_IMAGE_RESOURCE_ID)));
			message.setText(extras.getString(EXTRA_MESSAGE));
			mustRead = extras.getBoolean(EXTRA_MUST_READ);
		}
		else {
			Timber.e("Passed empty params to MessageActivity");
			onBackPressed();
		}
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void onBackPressed() {
		if (!mustRead)
			finishActivity();
	}
}
