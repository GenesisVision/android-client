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

import java.util.List;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.FundInvestingDetailsList;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ItemsViewModelAssetInvestmentRequest;
import io.swagger.client.model.ProgramInvestingDetailsList;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsAdapter;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.model.CurrencyEnum;
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

	@BindView(R.id.programs_arrow)
	public View programsArrow;

	@BindView(R.id.programs)
	public LinearLayout programs;


	@BindView(R.id.label_funds)
	public TextView fundsLabel;

	@BindView(R.id.funds_count_background)
	public ViewGroup fundsCountBackground;

	@BindView(R.id.funds_count)
	public TextView fundsCount;

	@BindView(R.id.funds_progress_bar)
	public ProgressBar fundsProgressBar;

	@BindView(R.id.funds_arrow)
	public View fundsArrow;

	@BindView(R.id.funds)
	public LinearLayout funds;

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

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
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

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResume();
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
	public void setRequests(ItemsViewModelAssetInvestmentRequest data) {
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

			timeframeProfit.setData(details.getProfits());

			showProgramsCountMaybe();
			showFundsCountMaybe();
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
	public void setPrograms(List<ProgramInvestingDetailsList> items) {
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
			programs.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			programsCount.setText(String.valueOf(items.size()));
		}
	}

	@Override
	public void setFunds(List<FundInvestingDetailsList> items) {
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
			funds.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			fundsCount.setText(String.valueOf(items.size()));
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
