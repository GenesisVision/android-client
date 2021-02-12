package vision.genesis.clientapp.feature.main.terminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.terminal.chart.TerminalChartView;
import vision.genesis.clientapp.feature.main.terminal.market_trades.MarketTradesView;
import vision.genesis.clientapp.feature.main.terminal.order_book.OrderBookView;
import vision.genesis.clientapp.feature.main.terminal.symbol_watch.SymbolWatchView;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TerminalActivity extends BaseSwipeBackActivity implements TerminalView
{
	private static final String EXTRA_SYMBOL = "extra_symbol";

	public static void startWith(Activity activity, String symbol) {
		Intent intent = new Intent(activity.getApplicationContext(), TerminalActivity.class);
		intent.putExtra(EXTRA_SYMBOL, symbol);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.view_symbol_watch)
	public SymbolWatchView symbolWatchView;

	@BindView(R.id.view_chart)
	public TerminalChartView chartView;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_order_book)
	public OrderBookView orderBookView;

	@BindView(R.id.view_market_trades)
	public MarketTradesView marketTradesView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	TerminalPresenter terminalPresenter;

	private String selectedSymbol = "";

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_terminal);

		ButterKnife.bind(this);

		initTabs();
		orderBookView.setActivity(this);

		if (getIntent().getExtras() != null) {
			selectedSymbol = getIntent().getExtras().getString(EXTRA_SYMBOL, null);
			terminalPresenter.onSymbolChanged(selectedSymbol);

			return;
		}

		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		finishActivity();
	}

	@Override
	protected void onDestroy() {
		if (symbolWatchView != null) {
			symbolWatchView.onDestroy();
		}
		if (chartView != null) {
			chartView.onDestroy();
		}
		if (orderBookView != null) {
			orderBookView.onDestroy();
		}

		super.onDestroy();
	}

	@Override
	protected void onResume() {
		if (symbolWatchView != null) {
			symbolWatchView.onResume();
		}
		if (chartView != null) {
			chartView.onResume();
		}
		if (orderBookView != null) {
			orderBookView.onResume();
		}

		super.onResume();
	}

	private void initTabs() {
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				switch (tab.getPosition()) {
					case 0:
						showOrderBook();
						break;
					case 1:
						showMarketTrades();
						break;
					case 2:
						showInfo();
						break;
				}

				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		});

		addTab(tabLayout.newTab().setCustomView(getTabView(R.string.order_book)), true);
		addTab(tabLayout.newTab().setCustomView(getTabView(R.string.market_trades)), false);
		addTab(tabLayout.newTab().setCustomView(getTabView(R.string.info)), false);
	}

	private View getTabView(int textResId) {
		CustomTabView view = new CustomTabView(this);
		view.setData(0, textResId);
		view.setTextSize(14);
		return view;
	}

	private void addTab(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
	}

	private void showOrderBook() {
		orderBookView.setVisibility(View.VISIBLE);
		marketTradesView.setVisibility(View.GONE);
//		infoView.setVisibility(View.GONE);
	}

	private void showMarketTrades() {
		marketTradesView.setVisibility(View.VISIBLE);
		orderBookView.setVisibility(View.GONE);
//		infoView.setVisibility(View.GONE);
	}

	private void showInfo() {
//		infoView.setVisibility(View.VISIBLE);
		orderBookView.setVisibility(View.GONE);
		marketTradesView.setVisibility(View.GONE);
	}

	@Override
	public void setSelectedSymbol(String symbol) {
		selectedSymbol = symbol;
		this.symbolWatchView.setSymbol(symbol);
		this.chartView.setSymbol(symbol);
		this.orderBookView.setSymbol(symbol);
		this.marketTradesView.setSymbol(symbol);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
		}
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, progressBar);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}