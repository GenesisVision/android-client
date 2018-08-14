package vision.genesis.clientapp.feature.main.portfolio_events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.portfolio_events.fragment.PortfolioEventsListFragment;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

public class PortfolioEventsActivity extends BaseSwipeBackActivity implements PortfolioEventsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), PortfolioEventsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public FrameLayout content;

	@InjectPresenter
	PortfolioEventsPresenter portfolioEventsPresenter;

	private PortfolioEventsListFragment fragment;

	@OnClick(R.id.button_filters)
	public void onFiltersClicked() {

	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_portfolio_events);

		ButterKnife.bind(this);

		setFonts();
//		setFragment();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());
	}

	private void setFragment() {
		fragment = PortfolioEventsListFragment.with(null);
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.hold, R.anim.hold)
				.add(R.id.content, fragment)
				.commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}
}
