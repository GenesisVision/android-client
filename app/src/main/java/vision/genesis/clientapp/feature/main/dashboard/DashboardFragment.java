package vision.genesis.clientapp.feature.main.dashboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.Timeframe;
import io.swagger.client.model.UserVerificationStatus;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.feature.main.dashboard.investments.DashboardInvestmentsView;
import vision.genesis.clientapp.feature.main.dashboard.investments.details.InvestmentsDetailsActivity;
import vision.genesis.clientapp.feature.main.dashboard.limit.DashboardLimitView;
import vision.genesis.clientapp.feature.main.dashboard.trading.DashboardTradingView;
import vision.genesis.clientapp.feature.main.dashboard.trading.details.TradingDetailsActivity;
import vision.genesis.clientapp.feature.main.dashboard.wallet.DashboardWalletView;
import vision.genesis.clientapp.feature.main.notifications.NotificationsActivity;
import vision.genesis.clientapp.feature.main.wallet.WalletActivity;
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


	@BindView(R.id.limit_view)
	public DashboardLimitView limitView;


	@BindView(R.id.investments_view)
	public DashboardInvestmentsView investmentsView;


	@BindView(R.id.trading_view)
	public DashboardTradingView tradingView;


	@BindView(R.id.wallet_view)
	public DashboardWalletView walletView;


	@BindView(R.id.notifications_dot)
	public View notificationsDot;

	@BindView(R.id.dashboard_progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.toolbar_height)
	public int toolbarHeight;

	@InjectPresenter
	DashboardPresenter presenter;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private float headerBalanceGroupInitialY = 0;

	private long headerBalanceGroupAnimationDuration = 300;

	private boolean showAnimInProcess = false;

	private boolean hideAnimInProcess = false;

	private DashboardSummary summary;

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

	@OnClick(R.id.wallet_view)
	public void onWalletClicked() {
		if (getActivity() != null) {
			WalletActivity.startFrom(getActivity());
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
		setScrollListener();
		timeframeProfit.setListener(presenter);

		new Handler().postDelayed(this::hideBalanceInHeader, 100);
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onResume();
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
		headerTotal.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
//		refreshLayout.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
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
		if (headerBalanceGroup != null) {
			showAnimInProcess = true;
			hideAnimInProcess = false;
			ValueAnimator yAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY);
			ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 1f);
			yAnim.addUpdateListener(animation -> {
				if (headerBalanceGroup != null) {
					headerBalanceGroup.setY((float) yAnim.getAnimatedValue());
				}
			});
			alphaAnim.addUpdateListener(animation -> {
				if (headerBalanceGroup != null) {
					headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
				}
			});
			yAnim.setDuration(headerBalanceGroupAnimationDuration);
			alphaAnim.setDuration(headerBalanceGroupAnimationDuration);
			alphaAnim.addListener(new AnimatorListenerAdapter()
			{
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					showAnimInProcess = false;
				}
			});
			yAnim.setInterpolator(new AccelerateDecelerateInterpolator());
			alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());
			yAnim.start();
			alphaAnim.start();
		}
	}

	private void hideBalanceInHeader() {
		if (headerBalanceGroup != null) {
			hideAnimInProcess = true;
			showAnimInProcess = false;
			if (headerBalanceGroupInitialY == 0) {
				headerBalanceGroupInitialY = headerBalanceGroup.getY();
			}
			ValueAnimator yAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY + toolbarHeight);
			ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 0f);
			yAnim.addUpdateListener(animation -> {
				if (headerBalanceGroup != null) {
					headerBalanceGroup.setY((float) yAnim.getAnimatedValue());
				}
			});
			alphaAnim.addUpdateListener(animation -> {
				if (headerBalanceGroup != null) {
					headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
				}
			});
			yAnim.setDuration(headerBalanceGroupAnimationDuration);
			alphaAnim.setDuration(headerBalanceGroupAnimationDuration);
			alphaAnim.addListener(new AnimatorListenerAdapter()
			{
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					hideAnimInProcess = false;
				}
			});
			yAnim.setInterpolator(new AccelerateDecelerateInterpolator());
			alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());
			yAnim.start();
			alphaAnim.start();
		}
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
	public void setLimitViewVisibility(boolean visible) {
		limitView.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setLimitViewButtonStatus(UserVerificationStatus verificationStatus) {
		limitView.setButtonStatus(verificationStatus);
	}

	@Override
	public void setSummary(DashboardSummary summary) {
		this.summary = summary;

		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(summary.getTotal(), baseCurrency.getValue()));
			headerTotal.setText(StringFormatUtil.getValueString(summary.getTotal(), baseCurrency.getValue()));

			timeframeProfit.setData(summary.getProfits(), baseCurrency.getValue());

			limitView.setData(summary.getLimitWithoutKyc());

			investmentsView.setShare((int) Math.round(summary.getInvested() / summary.getTotal() * 100));
			tradingView.setShare((int) Math.round(summary.getTrading() / summary.getTotal() * 100));
			walletView.setShare((int) Math.round(summary.getWallets() / summary.getTotal() * 100));
		}
	}

	private void setChange(DashboardTimeframeProfit model, String periodName) {
		String sign = model.getProfit() > 0 ? "+" : "";
		int changeColor = ThemeUtil.getColorByAttrId(getContext(),
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
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null) {
			refreshLayout.setRefreshing(refreshing);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollview);
	}

	@Override
	public void setHaveNewNotifications(boolean have) {
		notificationsDot.setVisibility(have ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void setTimeframe(Timeframe timeframe) {
		if (summary != null) {
			switch (timeframe) {
				case WEEK:
					setChange(summary.getProfits().getWeek(), getString(R.string.week));
					break;
				case MONTH:
					setChange(summary.getProfits().getMonth(), getString(R.string.month));
					break;
				default:
				case DAY:
					setChange(summary.getProfits().getDay(), getString(R.string.day));
					break;
			}
			investmentsView.setTimeframe(timeframe);
			tradingView.setTimeframe(timeframe);
		}
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
	public void updateWallet() {
		if (walletView != null) {
			walletView.update();
		}
	}

	@Override
	public void onShow() {
		presenter.onResume();
	}
}