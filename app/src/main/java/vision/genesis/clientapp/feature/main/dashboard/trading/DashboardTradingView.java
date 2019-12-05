package vision.genesis.clientapp.feature.main.dashboard.trading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.Timeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/11/2019.
 */

public class DashboardTradingView extends RelativeLayout
{
	private static final int EVENTS_TAKE = 5;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public DashboardManager dashboardManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.equity)
	public TextView equity;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.share_progress)
	public ProgressBar shareProgress;

	@BindView(R.id.share_percent)
	public TextView sharePercent;

	@BindView(R.id.aum)
	public TextView aum;

	@BindView(R.id.aum_label)
	public TextView aumLabel;

//	@BindView(R.id.label_events)
//	public TextView eventsLabel;

	@BindView(R.id.group_events)
	public ViewGroup eventsGroup;


	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_equity)
	public ViewGroup equityGroup;

	@BindView(R.id.group_share)
	public ViewGroup shareGroup;

	@BindView(R.id.group_values)
	public ViewGroup valuesGroup;

//	@BindView(R.id.group_events_header)
//	public ViewGroup eventsHeaderGroup;

	@BindView(R.id.scroll_view_events)
	public HorizontalScrollView eventsScrollView;

	public Subscription baseCurrencySubscription;

	public Subscription getDataSubscription;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private DashboardTradingDetails details;

	private Timeframe selectedTimeframe;

	public DashboardTradingView(Context context) {
		super(context);
		initView();
	}

	public DashboardTradingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DashboardTradingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_dashboard_trading, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
	}

	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getDataSubscription != null) {
			getDataSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		aum.setTypeface(TypefaceUtil.semibold());
		equity.setTypeface(TypefaceUtil.semibold());
		sharePercent.setTypeface(TypefaceUtil.semibold());
//		eventsLabel.setTypeface(TypefaceUtil.semibold());
	}

	public void update() {
		getData();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getData();
	}

	private void getData() {
		if (dashboardManager != null && baseCurrency != null) {
			getDataSubscription = dashboardManager.getTradingDetails(baseCurrency.getValue(), EVENTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::updateView, this::onError);
		}
	}

	private void updateView(DashboardTradingDetails details) {
		getDataSubscription.unsubscribe();

		this.details = details;

		equity.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));
		aum.setText(StringFormatUtil.getValueString(details.getAssetsUnderManagement(), baseCurrency.getValue()));
		setEvents(details.getEvents().getItems());
		setTimeframe(selectedTimeframe);

		progressBar.setVisibility(View.GONE);
		equityGroup.setVisibility(View.VISIBLE);
		shareGroup.setVisibility(View.VISIBLE);
		valuesGroup.setVisibility(View.VISIBLE);
//		eventsHeaderGroup.setVisibility(View.VISIBLE);
		eventsScrollView.setVisibility(View.VISIBLE);
	}

	private void updateChangeText(double profit, double profitPercent) {
		String sign = profit > 0 ? "+" : "";
		change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
				sign,
				StringFormatUtil.getValueString(profit, baseCurrency.getValue()),
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profitPercent > 0
						? R.attr.colorGreen
						: profitPercent < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	private void setEvents(List<InvestmentEventViewModel> events) {
		eventsGroup.removeAllViews();
		for (InvestmentEventViewModel event : events) {
			PortfolioEventDashboardView eventView = new PortfolioEventDashboardView(getContext());
			eventView.setEvent(event);
			eventsGroup.addView(eventView);
		}
	}

	private void onError(Throwable throwable) {
		getDataSubscription.unsubscribe();
	}

	public void setShare(int share) {
		this.shareProgress.setProgress(share);
		this.sharePercent.setText(String.format(Locale.getDefault(), "%d%%", share));
	}

	public void setTimeframe(Timeframe timeframe) {
		if (timeframe != null) {
			this.selectedTimeframe = timeframe;
			if (details != null) {
				DashboardTimeframeProfit model;
				switch (timeframe) {
					case WEEK:
						model = details.getProfits().getWeek();
						break;
					case MONTH:
						model = details.getProfits().getMonth();
						break;
					default:
					case DAY:
						model = details.getProfits().getDay();
						break;
				}
				updateChangeText(model.getProfit(), model.getProfitPercent());
			}
		}
	}
}
