package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTimeframeProfit;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investments.DashboardInvestmentsView;
import vision.genesis.clientapp.feature.main.dashboard.investments.details.InvestmentsDetailsActivity;
import vision.genesis.clientapp.feature.main.dashboard.trading.DashboardTradingView;
import vision.genesis.clientapp.feature.main.dashboard.trading.details.TradingDetailsActivity;
import vision.genesis.clientapp.feature.main.notifications.NotificationsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2019.
 */

public class DashboardFragment extends BaseFragment implements DashboardView
{
	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

//	@BindView(R.id.appBarLayout)
//	public AppBarLayout appBarLayout;

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.header)
	public ViewGroup header;

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


	@BindView(R.id.investments_view)
	public DashboardInvestmentsView investmentsView;


	@BindView(R.id.trading_view)
	public DashboardTradingView tradingView;


	@BindView(R.id.notifications_dot)
	public View notificationsDot;

	@BindView(R.id.dashboard_progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	DashboardPresenter dashboardPresenter;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	@OnClick(R.id.investments_view)
	public void onInvestmentsClicked() {
		if (getActivity() != null) {
			InvestmentsDetailsActivity.startWith(getActivity());
		}
	}

	@OnClick(R.id.trading_view)
	public void onTradingClicked() {
		if (getActivity() != null) {
			TradingDetailsActivity.startWith(getActivity());
		}
	}

//	private AppBarLayout.OnOffsetChangedListener appBarLayoutOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener()
//	{
//		Integer previousOffset;
//
//		@Override
//		public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//			if (previousOffset != null && verticalOffset < -100 && verticalOffset < previousOffset) {
////				dashboardHeaderPagerAdapter.chartViewModeTurnOff();
//			}
//			previousOffset = verticalOffset;
//		}
//	};

//	private Boolean assetsBottomSheetShowing = false;

	@OnClick(R.id.group_notifications)
	public void onNotificationsClicked() {
		if (getActivity() != null) {
			NotificationsActivity.startWith(getActivity());
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), ThemeUtil.getCurrentThemeResource());
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
		return localInflater.inflate(R.layout.fragment_dashboard, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		initRefreshLayout();
		setOffsetListener();
	}

	@Override
	public void onResume() {
		super.onResume();
		dashboardPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		total.setTypeface(TypefaceUtil.semibold());

		profitDayValue.setTypeface(TypefaceUtil.semibold());
		profitWeekValue.setTypeface(TypefaceUtil.semibold());
		profitMonthValue.setTypeface(TypefaceUtil.semibold());

		profitDayLabel.setText(getString(R.string.day).toLowerCase());
		profitWeekLabel.setText(getString(R.string.week).toLowerCase());
		profitMonthLabel.setText(getString(R.string.month).toLowerCase());
	}

	private void initRefreshLayout() {
//		refreshLayout.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> dashboardPresenter.onSwipeRefresh());
	}


	private void setOffsetListener() {
//		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
//			refreshLayout.setEnabled(verticalOffset == 0);
////			assetsPagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayoutAssets.getHeight() - header.getHeight());
//		});
//		ScrollView scrollView = new ScrollView(getContext());
//		scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//			@Override
//			public void onScrollChanged() {
//				int scrollY = rootScrollView.getScrollY(); // For ScrollView
//				int scrollX = rootScrollView.getScrollX(); // For HorizontalScrollView
//				// DO SOMETHING WITH THE SCROLL COORDINATES
//			}
//		});
	}
//
//	private void setScrollable(View bottomSheet, RecyclerView recyclerView) {
//		ViewGroup.LayoutParams params = bottomSheet.getLayoutParams();
//		if (params instanceof CoordinatorLayout.LayoutParams) {
//			CoordinatorLayout.LayoutParams coordinatorLayoutParams = (CoordinatorLayout.LayoutParams) params;
//			CoordinatorLayout.Behavior behavior = coordinatorLayoutParams.getBehavior();
//			if (behavior instanceof CustomBottomSheetBehavior) {
//				((CustomBottomSheetBehavior) behavior).setNestedScrollingChildRef(recyclerView);
//			}
//		}
//	}

	@Override
	public void showProgressBar(boolean show) {
		Timber.d("Progress show %s", show);
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setSummary(DashboardSummary summary) {
		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(summary.getTotal(), baseCurrency.getValue()));
			setChangePercent(profitDayValue, summary.getProfits().getDay());
			setChangePercent(profitWeekValue, summary.getProfits().getWeek());
			setChangePercent(profitMonthValue, summary.getProfits().getMonth());

			investmentsView.setShare((int) (summary.getInvested() / summary.getTotal() * 100));
			tradingView.setShare((int) (summary.getPending() / summary.getTotal() * 100));
		}
	}

	private void setChangePercent(TextView view, DashboardTimeframeProfit model) {
		view.setText(String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		view.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				model.getProfitPercent() > 0
						? R.attr.colorGreen
						: model.getProfitPercent() < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null) {
			refreshLayout.setRefreshing(refreshing);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, root);
	}

	@Override
	public void setHaveNewNotifications(boolean have) {
		notificationsDot.setVisibility(have ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void updateInvesting() {
		if (investmentsView != null) {
			investmentsView.update();
		}
	}

	@Override
	public void updateTrading() {
		if (tradingView != null) {
			tradingView.update();
		}
	}

	@Override
	public void onShow() {
		dashboardPresenter.onResume();
	}
}