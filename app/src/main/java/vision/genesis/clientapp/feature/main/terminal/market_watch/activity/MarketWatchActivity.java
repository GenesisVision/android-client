package vision.genesis.clientapp.feature.main.terminal.market_watch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchFragment;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2022.
 */

public class MarketWatchActivity extends BaseSwipeBackActivity implements MarketWatchView
{
	private static final String EXTRA_ASSET_ID = "extra_asset_id";

	private static final String EXTRA_ASSET_PERMISSIONS = "extra_asset_permissions";

	public static void startFrom(Activity activity, UUID assetId, ArrayList<String> permissions) {
		Intent intent = new Intent(activity.getApplicationContext(), MarketWatchActivity.class);
		intent.putExtra(EXTRA_ASSET_ID, assetId);
		intent.putStringArrayListExtra(EXTRA_ASSET_PERMISSIONS, permissions);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.content)
	public View content;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	MarketWatchPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market_watch);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID assetId = (UUID) getIntent().getExtras().getSerializable(EXTRA_ASSET_ID);
			ArrayList<String> permissions = getIntent().getExtras().getStringArrayList(EXTRA_ASSET_PERMISSIONS);

			presenter.setData(assetId, permissions);

			if (savedInstanceState == null) {
				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content, MarketWatchFragment.with(assetId, permissions))
						.disallowAddToBackStack()
						.commit();
			}
		}
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		this.content.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, content);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}