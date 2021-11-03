package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.FundInvestingDetailsList;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramInvestingDetailsList;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.common.requests.RequestsAdapter;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.feature.main.dashboard.investments.coins.CoinsPortfolioActivity;
import vision.genesis.clientapp.feature.main.dashboard.investments.funds.FundsPortfolioActivity;
import vision.genesis.clientapp.feature.main.dashboard.investments.programs.ProgramsPortfolioActivity;
import vision.genesis.clientapp.feature.main.events.EventsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.events.ShowAssetsListEvent;
import vision.genesis.clientapp.model.events.ShowFundsListEvent;
import vision.genesis.clientapp.model.events.ShowProgramsListEvent;
import vision.genesis.clientapp.ui.CoinDashboardShortView;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.ui.FundDashboardShortView;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.ui.ProgramDashboardShortView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class InvestmentsDetailsActivity extends BaseSwipeBackActivity implements InvestmentsDetailsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), InvestmentsDetailsActivity.class);
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


	@BindView(R.id.group_requests)
	public LinearLayout requestsGroup;

	@BindView(R.id.requests_recycler_view)
	public RecyclerView requestsRecyclerView;

	@BindView(R.id.label_requests)
	public TextView requestsLabel;

	@BindView(R.id.requests_count)
	public TextView requestsCount;


	@BindView(R.id.group_events)
	public ViewGroup eventsGroup;

	@BindView(R.id.label_events)
	public TextView eventsLabel;

	@BindView(R.id.events)
	public LinearLayout events;


	@BindView(R.id.label_programs)
	public TextView programsLabel;

	@BindView(R.id.programs_count_background)
	public ViewGroup programsCountBackground;

	@BindView(R.id.programs_count)
	public TextView programsCount;

	@BindView(R.id.programs_progress_bar)
	public ProgressBar programsProgressBar;

	@BindView(R.id.programs_status)
	public TextView programsStatus;

	@BindView(R.id.programs_arrow)
	public View programsArrow;

	@BindView(R.id.programs)
	public LinearLayout programs;

	@BindView(R.id.group_programs_empty)
	public LinearLayout programsEmptyGroup;


	@BindView(R.id.label_funds)
	public TextView fundsLabel;

	@BindView(R.id.funds_count_background)
	public ViewGroup fundsCountBackground;

	@BindView(R.id.funds_count)
	public TextView fundsCount;

	@BindView(R.id.funds_progress_bar)
	public ProgressBar fundsProgressBar;

	@BindView(R.id.funds_status)
	public TextView fundsStatus;

	@BindView(R.id.funds_arrow)
	public View fundsArrow;

	@BindView(R.id.funds)
	public LinearLayout funds;

	@BindView(R.id.group_funds_empty)
	public LinearLayout fundsEmptyGroup;


	@BindView(R.id.label_assets)
	public TextView assetsLabel;

	@BindView(R.id.assets_count_background)
	public ViewGroup assetsCountBackground;

	@BindView(R.id.assets_count)
	public TextView assetsCount;

	@BindView(R.id.assets_progress_bar)
	public ProgressBar assetsProgressBar;

	@BindView(R.id.assets_arrow)
	public View assetsArrow;

	@BindView(R.id.assets)
	public LinearLayout assets;

	@BindView(R.id.group_assets_empty)
	public LinearLayout assetsEmptyGroup;

	@BindDimen(R.dimen.toolbar_height)
	public int toolbarHeight;


	@InjectPresenter
	InvestmentsDetailsPresenter presenter;

	private CurrencyEnum baseCurrency;

	private DashboardInvestingDetails details;

	private RequestsAdapter requestsAdapter;

	private float titleInitialY = 0;

	private float headerBalanceGroupInitialY = 0;

	private long headerBalanceGroupAnimationDuration = 300;

	private boolean showAnimInProcess = false;

	private boolean hideAnimInProcess = false;

	private ArrayList<String> statusProgramsOptions;

	private ArrayList<String> statusFundsOptions;

	private Integer selectedProgramsStatusPosition = -1;

	private Integer selectedFundsStatusPosition = -1;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.group_events_header)
	public void onEventsClicked() {
		EventsActivity.startWith(this, EventsActivity.GROUP_INVESTMENT);
	}

	@OnClick(R.id.button_find_program)
	public void onFindProgramClicked() {
		EventBus.getDefault().post(new ShowProgramsListEvent());
		finishActivity();
	}

	@OnClick(R.id.button_find_fund)
	public void onFindFundClicked() {
		EventBus.getDefault().post(new ShowFundsListEvent());
		finishActivity();
	}

	@OnClick(R.id.button_buy_assets)
	public void onBuyAssetsClicked() {
		EventBus.getDefault().post(new ShowAssetsListEvent());
		finishActivity();
	}

	@OnClick(R.id.programs_arrow)
	public void onProgramsArrowClicked() {
		ProgramsPortfolioActivity.startWith(this);
	}

	@OnClick(R.id.funds_arrow)
	public void onFundsArrowClicked() {
		FundsPortfolioActivity.startWith(this);
	}

	@OnClick(R.id.assets_arrow)
	public void onAssetsArrowClicked() {
		CoinsPortfolioActivity.startWith(this);
	}

	@OnClick(R.id.group_programs_status)
	public void onProgramsStatusClicked() {
		if (statusProgramsOptions != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					"", statusProgramsOptions, selectedProgramsStatusPosition);
			fragment.setListener((position, text) -> presenter.onProgramsStatusOptionSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_funds_status)
	public void onFundsStatusClicked() {
		if (statusFundsOptions != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					"", statusFundsOptions, selectedFundsStatusPosition);
			fragment.setListener((position, text) -> presenter.onFundsStatusOptionSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_investments_details);

		ButterKnife.bind(this);

		setFonts();

		initRefreshLayout();
		initRequestsAdapter();
		setScrollListener();

		timeframeProfit.setListener(presenter);
		new Handler().postDelayed(this::hideBalanceInHeader, 100);
	}

	private void initRequestsAdapter() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		requestsRecyclerView.setLayoutManager(layoutManager);
		int paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
				AppCompatResources.getDrawable(this, R.drawable.list_item_divider), paddingLeft, 0);
		requestsRecyclerView.addItemDecoration(dividerItemDecoration);
		requestsAdapter = new RequestsAdapter();
		requestsAdapter.setHasStableIds(true);
		requestsRecyclerView.setAdapter(requestsAdapter);
		requestsRecyclerView.setNestedScrollingEnabled(false);
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

		total.setTypeface(TypefaceUtil.semibold());
		headerTotal.setTypeface(TypefaceUtil.semibold());

		requestsLabel.setTypeface(TypefaceUtil.semibold());
		eventsLabel.setTypeface(TypefaceUtil.semibold());
		programsLabel.setTypeface(TypefaceUtil.semibold());
		fundsLabel.setTypeface(TypefaceUtil.semibold());

		requestsCount.setTypeface(TypefaceUtil.semibold());
		programsCount.setTypeface(TypefaceUtil.semibold());
		fundsCount.setTypeface(TypefaceUtil.semibold());
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

		balanceYAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue());
			}
		});
		titleYAnim.addUpdateListener(animation -> {
			if (title != null) {
				title.setY((float) titleYAnim.getAnimatedValue());
			}
		});
		alphaAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
			}
		});

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

		balanceYAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue());
			}
		});
		titleYAnim.addUpdateListener(animation -> {
			if (title != null) {
				title.setY((float) titleYAnim.getAnimatedValue());
			}
		});
		alphaAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
			}
		});

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

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		presenter.onPause();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
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
	public void setRequests(AssetInvestmentRequestItemsViewModel data) {
		requestsGroup.setVisibility(data.getTotal() > 0 ? View.VISIBLE : View.GONE);
		requestsCount.setText(String.valueOf(data.getTotal()));

		requestsAdapter.setRequests(data.getItems());
	}

	@Override
	public void setInvesting(DashboardInvestingDetails details) {
		this.details = details;
		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));
			headerTotal.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));

			timeframeProfit.setData(details.getProfits(), baseCurrency.getValue());

			showProgramsCountMaybe();
			showFundsCountMaybe();
			showAssetsCountMaybe();
		}
	}

	private void showProgramsCountMaybe() {
		if (details != null && details.getProgramsCount() > 0 && programsProgressBar.getVisibility() != View.VISIBLE) {
			programsArrow.setVisibility(View.VISIBLE);
			programsCountBackground.setVisibility(View.VISIBLE);
		}
	}

	private void showFundsCountMaybe() {
		if (details != null && details.getFundsCount() > 0 && fundsProgressBar.getVisibility() != View.VISIBLE) {
			fundsArrow.setVisibility(View.VISIBLE);
			fundsCountBackground.setVisibility(View.VISIBLE);
		}
	}

	private void showAssetsCountMaybe() {
		if (details != null && details.getCoinsCount() > 0 && assetsProgressBar.getVisibility() != View.VISIBLE) {
			assetsArrow.setVisibility(View.VISIBLE);
			assetsCountBackground.setVisibility(View.VISIBLE);
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

//		headerChangeValue.setText(String.format(Locale.getDefault(), "%s (%s%%)",
//				changeValueText,
//				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		headerChangeValue.setText(String.format(Locale.getDefault(), "%s",
				changeValueText));
		headerChangeValue.setTextColor(changeColor);

		change.setText(changeValueText);
		change.setTextColor(changeColor);

		headerChangePeriod.setText(String.format(Locale.getDefault(),
				getString(R.string.template_dashboard_header_change_period),
				periodName.toLowerCase()));
	}

	@Override
	public void setStatusOptions(ArrayList<String> statusProgramsOptions, ArrayList<String> statusFundsOptions) {
		this.statusProgramsOptions = statusProgramsOptions;
		this.statusFundsOptions = statusFundsOptions;
	}

	@Override
	public void setProgramsStatus(String text, Integer position) {
		this.selectedProgramsStatusPosition = position;
		this.programsStatus.setText(text);
	}

	@Override
	public void setFundsStatus(String text, Integer position) {
		this.selectedFundsStatusPosition = position;
		this.fundsStatus.setText(text);
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
	public void setPrograms(List<ProgramInvestingDetailsList> items, int size) {
		if (baseCurrency != null) {
			programs.removeAllViews();
			int index = 0;
			for (ProgramInvestingDetailsList program : items) {
				ProgramDashboardShortView programView = new ProgramDashboardShortView(this);
				programView.setData(program, baseCurrency.getValue());
				programs.addView(programView);
				if (index == items.size() - 1) {
					programView.removeDelimiter();
				}
				index++;
			}
			programsCount.setText(String.valueOf(size));
			programs.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			programsEmptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void setFunds(List<FundInvestingDetailsList> items, int size) {
		if (baseCurrency != null) {
			funds.removeAllViews();
			int index = 0;
			for (FundInvestingDetailsList fund : items) {
				FundDashboardShortView fundView = new FundDashboardShortView(this);
				fundView.setData(fund, baseCurrency.getValue());
				funds.addView(fundView);
				if (index == items.size() - 1) {
					fundView.removeDelimiter();
				}
				index++;
			}
			fundsCount.setText(String.valueOf(size));
			funds.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			fundsEmptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void setAssets(List<CoinsAsset> items, int size) {
		if (baseCurrency != null) {
			assets.removeAllViews();
			int index = 0;
			for (CoinsAsset coin : items) {
				CoinDashboardShortView view = new CoinDashboardShortView(this);
				view.setData(coin);
				assets.addView(view);
				if (index == items.size() - 1) {
					view.removeDelimiter();
				}
				index++;
			}
			assetsCount.setText(String.valueOf(size));
			assets.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			assetsEmptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void hideProgramsProgress() {
		programsProgressBar.setVisibility(View.GONE);
		showProgramsCountMaybe();
	}

	@Override
	public void hideFundsProgress() {
		fundsProgressBar.setVisibility(View.GONE);
		showFundsCountMaybe();
	}

	@Override
	public void hideAssetsProgress() {
		assetsProgressBar.setVisibility(View.GONE);
		showAssetsCountMaybe();
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
