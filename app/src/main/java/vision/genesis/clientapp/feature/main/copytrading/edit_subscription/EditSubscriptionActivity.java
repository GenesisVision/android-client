package vision.genesis.clientapp.feature.main.copytrading.edit_subscription;

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
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class EditSubscriptionActivity extends BaseSwipeBackActivity implements EditSubscriptionView
{
	private static final String EXTRA_MODEL = "extra_model";

	private static final String EXTRA_FOLLOW_ID = "extra_follow_id";

	private static final String EXTRA_TRADING_ACCOUNT_ID = "extra_trading_account_id";

	private static final String EXTRA_IS_EXTERNAL = "extra_is_external";

	public static void startWith(Activity activity, SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean isExternal) {
		Intent intent = new Intent(activity.getApplicationContext(), EditSubscriptionActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.putExtra(EXTRA_FOLLOW_ID, followId);
		intent.putExtra(EXTRA_TRADING_ACCOUNT_ID, tradingAccountId);
		intent.putExtra(EXTRA_IS_EXTERNAL, isExternal);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.edit_subscription_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	EditSubscriptionPresenter presenter;

	private EditSubscriptionPagerAdapter adapter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_subscription);

		ButterKnife.bind(this);

		setFonts();
		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			SubscriptionSettingsModel model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			UUID followId = (UUID) getIntent().getExtras().getSerializable(EXTRA_FOLLOW_ID);
			UUID tradingAccountId = (UUID) getIntent().getExtras().getSerializable(EXTRA_TRADING_ACCOUNT_ID);
			Boolean isExternal = getIntent().getExtras().getBoolean(EXTRA_IS_EXTERNAL);
			presenter.setData(model, followId, tradingAccountId, isExternal);
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
	public void initViewPager(SubscriptionSettingsModel model) {
		this.adapter = new EditSubscriptionPagerAdapter(getSupportFragmentManager(), model);
		viewPager.setAdapter(adapter);
		viewPager.setEnabled(false);
		viewPager.setOffscreenPageLimit(1);
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