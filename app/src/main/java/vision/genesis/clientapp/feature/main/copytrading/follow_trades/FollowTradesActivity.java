package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

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
import io.swagger.client.model.ProgramFollowDetailsFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class FollowTradesActivity extends BaseSwipeBackActivity implements FollowTradesView
{
	private static final String EXTRA_FOLLOW_DETAILS = "extra_follow_details";

	public static void startFrom(Activity activity, ProgramFollowDetailsFull details) {
		Intent intent = new Intent(activity.getApplicationContext(), FollowTradesActivity.class);
		intent.putExtra(EXTRA_FOLLOW_DETAILS, details);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.follow_trades_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	FollowTradesPresenter presenter;

	private FollowTradesPagerAdapter adapter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_follow_trades);

		ButterKnife.bind(this);

		setFonts();
		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			ProgramFollowDetailsFull details = getIntent().getExtras().getParcelable(EXTRA_FOLLOW_DETAILS);
			presenter.setData(details);
			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		if (viewPager.getCurrentItem() == 0) {
			finishActivity();
		}
		else {
			viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
		}
	}

	@Override
	public void initViewPager(ProgramFollowDetailsFull details) {
		this.adapter = new FollowTradesPagerAdapter(getSupportFragmentManager(), details);
		viewPager.setAdapter(adapter);
		viewPager.setEnabled(false);
		viewPager.setOffscreenPageLimit(2);
	}

	@Override
	public void showSettings() {
		viewPager.setCurrentItem(adapter.getSettingsPosition());
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
		showSnackbar(message, viewPager);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}