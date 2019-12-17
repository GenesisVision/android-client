package vision.genesis.clientapp.feature.main.fund.reallocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundAssetInfo;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.fund.create.assets.FundAssetsFragment;
import vision.genesis.clientapp.model.FundAssetsModel;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ReallocateFundActivity extends BaseSwipeBackActivity implements ReallocateFundView
{
	private static final String EXTRA_FUND_ID = "extra_fund_id";

	private static final String EXTRA_REALLOCATION_INFO = "extra_reallocation_info";

	private static final String EXTRA_ASSETS = "extra_assets";

	public static void startFrom(Activity activity, UUID assetId, String reallocationInfo, ArrayList<FundAssetInfo> assets) {
		Intent intent = new Intent(activity.getApplicationContext(), ReallocateFundActivity.class);
		intent.putExtra(EXTRA_FUND_ID, assetId);
		intent.putExtra(EXTRA_REALLOCATION_INFO, reallocationInfo);
		intent.putParcelableArrayListExtra(EXTRA_ASSETS, assets);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	ReallocateFundPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reallocate_fund);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID fundId = (UUID) getIntent().getExtras().getSerializable(EXTRA_FUND_ID);
			String reallocationInfo = getIntent().getExtras().getString(EXTRA_REALLOCATION_INFO);
			ArrayList<FundAssetInfo> assets = getIntent().getExtras().getParcelableArrayList(EXTRA_ASSETS);
			if (assets != null) {
				presenter.setData(fundId);

				if (savedInstanceState == null) {
					getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.content, FundAssetsFragment.with(new FundAssetsModel(false, "", "", reallocationInfo,
									getString(R.string.reallocate), assets, false)))
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