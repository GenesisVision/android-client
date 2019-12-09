package vision.genesis.clientapp.feature.main.follow.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.CreateSignalProvider;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.follow.create.settings.FollowSettingsFragment;
import vision.genesis.clientapp.model.FollowSettingsModel;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/12/2019.
 */

public class EditFollowSettingsActivity extends BaseSwipeBackActivity implements EditFollowSettingsView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startFrom(Activity activity, CreateSignalProvider model) {
		Intent intent = new Intent(activity.getApplicationContext(), EditFollowSettingsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	EditFollowSettingsPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_follow_settings);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			CreateSignalProvider model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				presenter.setData(model);

				if (savedInstanceState == null) {
					getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.content, FollowSettingsFragment.with(new FollowSettingsModel(false, null,
									getString(R.string.fees_settings), getString(R.string.update), model.getVolumeFee(), model.getSuccessFee())))
							.disallowAddToBackStack()
							.commit();
				}

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}