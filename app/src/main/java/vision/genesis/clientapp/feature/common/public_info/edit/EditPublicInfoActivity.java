package vision.genesis.clientapp.feature.common.public_info.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramUpdate;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.model.PublicInfoModel;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/11/2019.
 */

public class EditPublicInfoActivity extends BaseSwipeBackActivity implements EditPublicInfoView
{
	private static final String EXTRA_ASSET_ID = "extra_asset_id";

	private static final String EXTRA_MODEL = "extra_model";

	public static void startFrom(Activity activity, UUID assetId, ProgramUpdate model) {
		Intent intent = new Intent(activity.getApplicationContext(), EditPublicInfoActivity.class);
		intent.putExtra(EXTRA_ASSET_ID, assetId);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	EditPublicInfoPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_public_info);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID assetId = (UUID) getIntent().getExtras().getSerializable(EXTRA_ASSET_ID);
			ProgramUpdate model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				presenter.setData(assetId, model);

				if (savedInstanceState == null) {
					getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.content, PublicInfoFragment.with(new PublicInfoModel(
									false, null, null, false,
									getString(R.string.update_public_info),
									model.getTitle(), model.getDescription(), model.getLogo())))
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