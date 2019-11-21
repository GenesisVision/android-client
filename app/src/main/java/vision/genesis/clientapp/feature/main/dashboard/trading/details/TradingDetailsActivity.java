package vision.genesis.clientapp.feature.main.dashboard.trading.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.DashboardTradingAsset;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.InvestmentEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.create.CreateFundActivity;
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


	@BindView(R.id.total)
	public TextView total;

	@BindView(R.id.profit_day_value)
	public TextView profitDayValue;

	@BindView(R.id.profit_day_label)
	public TextView profitDayLabel;

	@BindView(R.id.profit_week_value)
	public TextView profitWeekValue;

	@BindView(R.id.profit_week_label)
	public TextView profitWeekLabel;

	@BindView(R.id.profit_month_value)
	public TextView profitMonthValue;

	@BindView(R.id.profit_month_label)
	public TextView profitMonthLabel;


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


	@InjectPresenter
	TradingDetailsPresenter presenter;

	private CurrencyEnum baseCurrency;

	private DashboardTradingDetails details;

	private ArrayList<String> createPrivateOptions;

	private ArrayList<String> createPublicOptions;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
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
		showCreateFundActivity();
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
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

//		total.setTypeface(TypefaceUtil.semibold());

		profitDayValue.setTypeface(TypefaceUtil.semibold());
		profitWeekValue.setTypeface(TypefaceUtil.semibold());
		profitMonthValue.setTypeface(TypefaceUtil.semibold());

		profitDayLabel.setText(getString(R.string.day).toLowerCase());
		profitWeekLabel.setText(getString(R.string.week).toLowerCase());
		profitMonthLabel.setText(getString(R.string.month).toLowerCase());

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
	public void setTrading(DashboardTradingDetails details) {
		this.details = details;
		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));
			setChangePercent(profitDayValue, details.getProfits().getDay());
			setChangePercent(profitWeekValue, details.getProfits().getWeek());
			setChangePercent(profitMonthValue, details.getProfits().getMonth());
		}
	}

	private void setChangePercent(TextView view, DashboardTimeframeProfit model) {
		view.setText(String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		view.setTextColor(ThemeUtil.getColorByAttrId(this,
				model.getProfitPercent() > 0
						? R.attr.colorGreen
						: model.getProfitPercent() < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
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
