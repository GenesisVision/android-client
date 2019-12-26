package vision.genesis.clientapp.feature.main.dashboard.trading.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.DashboardTradingAsset;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.feature.main.events.EventsActivity;
import vision.genesis.clientapp.feature.main.fund.create.CreateFundActivity;
import vision.genesis.clientapp.feature.main.settings.public_info.ProfilePublicInfoActivity;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.ui.TradingAssetDashboardShortView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class TradingDetailsActivity extends BaseSwipeBackActivity implements TradingDetailsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), TradingDetailsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollview;


	@BindView(R.id.group_header_balance)
	public ViewGroup headerBalanceGroup;

	@BindView(R.id.header_total)
	public TextView headerTotal;

	@BindView(R.id.header_change_value)
	public TextView headerChangeValue;

	@BindView(R.id.header_change_period)
	public TextView headerChangePeriod;


	@BindView(R.id.total)
	public TextView total;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.view_timeframe_profit)
	public TimeframeProfitView timeframeProfit;


	@BindView(R.id.group_events)
	public ViewGroup eventsGroup;

	@BindView(R.id.label_events)
	public TextView eventsLabel;

	@BindView(R.id.events)
	public LinearLayout events;


	@BindView(R.id.label_private)
	public TextView privateLabel;

	@BindView(R.id.group_private_actions)
	public ViewGroup privateActionsGroup;

	@BindView(R.id.private_count)
	public TextView privateCount;

	@BindView(R.id.private_progress_bar)
	public ProgressBar privateProgressBar;

	@BindView(R.id.group_private_empty)
	public ViewGroup privateEmptyGroup;

	@BindView(R.id.private_assets)
	public LinearLayout privateAssetsGroup;


	@BindView(R.id.label_public)
	public TextView publicLabel;

	@BindView(R.id.group_public_actions)
	public ViewGroup publicActionsGroup;

	@BindView(R.id.public_count)
	public TextView publicCount;

	@BindView(R.id.public_progress_bar)
	public ProgressBar publicProgressBar;

	@BindView(R.id.group_public_empty)
	public ViewGroup publicEmptyGroup;

	@BindView(R.id.public_assets)
	public LinearLayout publicAssetsGroup;

	@BindDimen(R.dimen.toolbar_height)
	public int toolbarHeight;


	@InjectPresenter
	TradingDetailsPresenter presenter;

	private CurrencyEnum baseCurrency;

	private DashboardTradingDetails details;

	private ArrayList<String> createPrivateOptions;

	private ArrayList<String> createPublicOptions;

	private float titleInitialY = 0;

	private float headerBalanceGroupInitialY = 0;

	private long headerBalanceGroupAnimationDuration = 300;

	private boolean showAnimInProcess = false;

	private boolean hideAnimInProcess = false;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.group_events_header)
	public void onEventsClicked() {
		EventsActivity.startWith(this, EventsActivity.GROUP_TRADING);
	}

	@OnClick(R.id.button_create_trading_account)
	public void onCreateTradingAccountClicked() {
		showCreateTradingAccountActivity();
	}

	@OnClick(R.id.button_attach_external_account)
	public void onAttachExternalAccountClicked() {
		showAttachAccountActivity();
	}

	@OnClick(R.id.button_create_fund)
	public void onCreateFundClicked() {
		presenter.onCreateFundClicked();
	}

	@OnClick(R.id.button_create_private)
	public void onCreatePrivateClicked() {
		if (createPrivateOptions != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					"", createPrivateOptions, -1);
			fragment.setListener((position, text) -> presenter.onCreatePrivateOptionSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}


	@OnClick(R.id.button_create_public)
	public void onCreatePublicClicked() {
		if (createPublicOptions != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					"", createPublicOptions, -1);
			fragment.setListener((position, text) -> presenter.onCreatePublicOptionSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_trading_details);

		ButterKnife.bind(this);

		setFonts();

		initRefreshLayout();
		setScrollListener();

		timeframeProfit.setListener(presenter);
		new Handler().postDelayed(this::hideBalanceInHeader, 100);
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

		total.setTypeface(TypefaceUtil.semibold());
		headerTotal.setTypeface(TypefaceUtil.semibold());

		eventsLabel.setTypeface(TypefaceUtil.semibold());
		privateLabel.setTypeface(TypefaceUtil.semibold());
		publicLabel.setTypeface(TypefaceUtil.semibold());

		privateCount.setTypeface(TypefaceUtil.semibold());
		publicCount.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(presenter::onSwipeRefresh);
	}

	private void setScrollListener() {
		scrollview.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
			if (scrollY > 150 && !showAnimInProcess) {
				showBalanceInHeader();
			}
			else if (scrollY < 100 && !hideAnimInProcess) {
				hideBalanceInHeader();
			}
		});
	}

	private void showBalanceInHeader() {
		showAnimInProcess = true;
		hideAnimInProcess = false;

		ValueAnimator balanceYAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY);
		ValueAnimator titleYAnim = ValueAnimator.ofFloat(title.getY(), titleInitialY - toolbarHeight);
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 1f);

		balanceYAnim.addUpdateListener(animation -> headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue()));
		titleYAnim.addUpdateListener(animation -> title.setY((float) titleYAnim.getAnimatedValue()));
		alphaAnim.addUpdateListener(animation -> headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue()));

		balanceYAnim.setDuration(headerBalanceGroupAnimationDuration);
		titleYAnim.setDuration(headerBalanceGroupAnimationDuration);
		alphaAnim.setDuration(headerBalanceGroupAnimationDuration);

		alphaAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				showAnimInProcess = false;
			}
		});

		balanceYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		titleYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());

		balanceYAnim.start();
		titleYAnim.start();
		alphaAnim.start();
	}

	private void hideBalanceInHeader() {
		hideAnimInProcess = true;
		showAnimInProcess = false;

		if (titleInitialY == 0) {
			titleInitialY = title.getY();
		}
		if (headerBalanceGroupInitialY == 0) {
			headerBalanceGroupInitialY = headerBalanceGroup.getY();
		}

		ValueAnimator balanceYAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY + toolbarHeight);
		ValueAnimator titleYAnim = ValueAnimator.ofFloat(title.getY(), titleInitialY);
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 0f);

		balanceYAnim.addUpdateListener(animation -> headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue()));
		titleYAnim.addUpdateListener(animation -> title.setY((float) titleYAnim.getAnimatedValue()));
		alphaAnim.addUpdateListener(animation -> headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue()));

		balanceYAnim.setDuration(headerBalanceGroupAnimationDuration);
		titleYAnim.setDuration(headerBalanceGroupAnimationDuration);
		alphaAnim.setDuration(headerBalanceGroupAnimationDuration);

		alphaAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				hideAnimInProcess = false;
			}
		});

		balanceYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		titleYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());

		balanceYAnim.start();
		titleYAnim.start();
		alphaAnim.start();
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
	protected void onResume() {
		super.onResume();
		presenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setCreateOptions(ArrayList<String> createPrivateOptions, ArrayList<String> createPublicOptions) {
		this.createPrivateOptions = createPrivateOptions;
		this.createPublicOptions = createPublicOptions;
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setTimeframe(Timeframe timeframe) {
		if (details != null) {
			switch (timeframe) {
				case WEEK:
					setChange(details.getProfits().getWeek(), getString(R.string.week));
					break;
				case MONTH:
					setChange(details.getProfits().getMonth(), getString(R.string.month));
					break;
				default:
				case DAY:
					setChange(details.getProfits().getDay(), getString(R.string.day));
					break;
			}
		}
	}

	@Override
	public void setTrading(DashboardTradingDetails details) {
		this.details = details;
		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));
			headerTotal.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));

			timeframeProfit.setData(details.getProfits());
		}
	}

	private void setChange(DashboardTimeframeProfit model, String periodName) {
		String sign = model.getProfit() > 0 ? "+" : "";
		int changeColor = ThemeUtil.getColorByAttrId(this,
				model.getProfit() > 0
						? R.attr.colorGreen
						: model.getProfit() < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary);
		String changeValueText = String.format(Locale.getDefault(), "%s%s",
				sign,
				StringFormatUtil.getValueString(model.getProfit(), baseCurrency.getValue()));

		headerChangeValue.setText(String.format(Locale.getDefault(), "%s (%s%%)",
				changeValueText,
				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		headerChangeValue.setTextColor(changeColor);

		change.setText(changeValueText);
		change.setTextColor(changeColor);

		headerChangePeriod.setText(String.format(Locale.getDefault(),
				getString(R.string.template_dashboard_header_change_period),
				periodName.toLowerCase()));
	}

	@Override
	public void setEvents(List<InvestmentEventViewModel> items) {
		eventsGroup.setVisibility(items.isEmpty() ? View.GONE : View.VISIBLE);
		events.removeAllViews();
		for (InvestmentEventViewModel event : items) {
			PortfolioEventDashboardView eventView = new PortfolioEventDashboardView(this);
			eventView.setEvent(event);
			events.addView(eventView);
		}
	}

	@Override
	public void setPrivate(List<DashboardTradingAsset> items) {
		if (baseCurrency != null) {
			privateAssetsGroup.removeAllViews();
			int index = 0;
			for (DashboardTradingAsset asset : items) {
				TradingAssetDashboardShortView assetView = new TradingAssetDashboardShortView(this);
				assetView.setData(asset, baseCurrency.getValue());
				privateAssetsGroup.addView(assetView);
				if (index == items.size() - 1) {
					assetView.removeDelimiter();
				}
				index++;
			}
			showPrivateEmpty(items.isEmpty());
		}
	}

	private void showPrivateEmpty(boolean show) {
		privateEmptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		privateAssetsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setPublic(List<DashboardTradingAsset> items) {
		if (baseCurrency != null) {
			publicAssetsGroup.removeAllViews();
			int index = 0;
			for (DashboardTradingAsset asset : items) {
				TradingAssetDashboardShortView assetView = new TradingAssetDashboardShortView(this);
				assetView.setData(asset, baseCurrency.getValue());
				publicAssetsGroup.addView(assetView);
				if (index == items.size() - 1) {
					assetView.removeDelimiter();
				}
				index++;
			}
			showPublicEmpty(items.isEmpty());
		}
	}

	private void showPublicEmpty(boolean show) {
		publicEmptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		publicAssetsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setPrivateCount(int count) {
		if (count > 0) {
			privateActionsGroup.setVisibility(View.VISIBLE);
			privateCount.setText(String.valueOf(count));
			hidePrivateProgress();
		}
	}

	@Override
	public void setPublicCount(int count) {
		if (count > 0) {
			publicActionsGroup.setVisibility(View.VISIBLE);
			publicCount.setText(String.valueOf(count));
			hidePublicProgress();
		}
	}

	@Override
	public void hidePrivateProgress() {
		privateProgressBar.setVisibility(View.GONE);
	}

	@Override
	public void hidePublicProgress() {
		publicProgressBar.setVisibility(View.GONE);
	}

	@Override
	public void showCreateTradingAccountActivity() {
		CreateAccountActivity.startFrom(this);
	}

	@Override
	public void showAttachAccountActivity() {

	}

	@Override
	public void showCreateFundActivity() {
		CreateFundActivity.startFrom(this);
	}

	@Override
	public void showProfilePublicInfoActivity() {
		ProfilePublicInfoActivity.startFrom(this, true);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}


	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}
}
