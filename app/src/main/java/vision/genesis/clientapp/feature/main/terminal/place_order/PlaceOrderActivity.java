package vision.genesis.clientapp.feature.main.terminal.place_order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.swagger.client.model.ExchangeAsset;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SelectPercentView;
import vision.genesis.clientapp.utils.DigitsInputFilter;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */

public class PlaceOrderActivity extends BaseSwipeBackActivity implements PlaceOrderView
{
	public static final String OPERATION_TYPE_BUY = "operation_type_buy";

	public static final String OPERATION_TYPE_SELL = "operation_type_sell";

	private static final String EXTRA_SYMBOL = "extra_symbol";

	private static final String EXTRA_OPERATION_TYPE = "extra_operation_type";

	private static final String EXTRA_SELECTED_ACCOUNT = "extra_selected_account";

	public static void startWith(Activity activity, String symbol, ExchangeAsset selectedAccount, String operationType) {
		Intent intent = new Intent(activity.getApplicationContext(), PlaceOrderActivity.class);
		intent.putExtra(EXTRA_SYMBOL, symbol);
		intent.putExtra(EXTRA_OPERATION_TYPE, operationType);
		intent.putExtra(EXTRA_SELECTED_ACCOUNT, selectedAccount);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;


	@BindView(R.id.base_asset)
	public TextView baseAsset;

	@BindView(R.id.quote_asset)
	public TextView quoteAsset;


	@BindView(R.id.button_select_buy)
	public RelativeLayout selectBuyButton;

	@BindView(R.id.button_select_sell)
	public RelativeLayout selectSellButton;

	@BindView(R.id.order_type)
	public TextView orderType;

	@BindView(R.id.group_market_price)
	public RelativeLayout marketPriceGroup;


	@BindView(R.id.group_price)
	public RelativeLayout priceGroup;

	@BindView(R.id.edittext_price)
	public EditText price;

	@BindView(R.id.price_currency)
	public TextView priceCurrency;


	@BindView(R.id.group_stop)
	public RelativeLayout stopGroup;

	@BindView(R.id.edittext_stop)
	public EditText stop;

	@BindView(R.id.stop_currency)
	public TextView stopCurrency;


	@BindView(R.id.group_limit)
	public RelativeLayout limitGroup;

	@BindView(R.id.edittext_limit)
	public EditText limit;

	@BindView(R.id.limit_currency)
	public TextView limitCurrency;


	@BindView(R.id.group_amount)
	public RelativeLayout amountGroup;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;


	@BindView(R.id.group_total)
	public RelativeLayout totalGroup;

	@BindView(R.id.edittext_total)
	public EditText total;

	@BindView(R.id.total_currency)
	public TextView totalCurrency;


	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.view_select_percent)
	public SelectPercentView selectPercentView;


	@BindView(R.id.view_order_book)
	public MiniOrderBookView orderBookView;


	@BindView(R.id.button_buy)
	public PrimaryButton buyButton;

	@BindView(R.id.button_sell)
	public PrimaryButton sellButton;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.progress_bar_buttons)
	public ProgressBar progressBarButtons;


	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;


	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_place_order)
	public ViewPager viewPager;

	@InjectPresenter
	PlaceOrderPresenter presenter;

	private String selectedSymbol = "";

	private int selectedOrderTypePosition = -1;

	private ArrayList<String> orderTypeOptions;

	private ExchangeAsset selectedAccount;

	private PlaceOrderPagerAdapter pagerAdapter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab openOrdersTab;

	private TabLayout.Tab orderHistoryTab;

	private TabLayout.Tab tradesHistoryTab;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_select_buy)
	public void onSelectBuyClicked() {
		presenter.onSelectBuyClicked();
	}

	@OnClick(R.id.button_select_sell)
	public void onSelectSellClicked() {
		presenter.onSelectSellClicked();
	}

	@OnClick(R.id.group_order_type)
	public void onOrderTypeClicked() {
		if (orderTypeOptions != null && orderTypeOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.order_type), orderTypeOptions, selectedOrderTypePosition);
			fragment.setListener((position, text) -> presenter.onOrderTypeSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_price)
	public void onPriceGroupClicked() {
		showSoftKeyboard(price);
	}

	@OnClick(R.id.group_stop)
	public void onStopGroupClicked() {
		showSoftKeyboard(stop);
	}

	@OnClick(R.id.group_limit)
	public void onLimitGroupClicked() {
		showSoftKeyboard(limit);
	}

	@OnClick(R.id.group_amount)
	public void onAmountGroupClicked() {
		showSoftKeyboard(amount);
	}

	@OnClick(R.id.group_total)
	public void onTotalGroupClicked() {
		showSoftKeyboard(total);
	}

	@OnClick(R.id.button_buy)
	public void onBuyClicked() {
		presenter.onBuyClicked();
	}

	@OnClick(R.id.button_sell)
	public void onSellClicked() {
		presenter.onSellClicked();
	}

	@OnFocusChange(R.id.edittext_stop)
	public void onStopFocusChange(View view, boolean hasFocus) {
		presenter.onStopFocusChange(hasFocus);
	}

	@OnFocusChange(R.id.edittext_limit)
	public void onLimitFocusChange(View view, boolean hasFocus) {
		presenter.onLimitFocusChange(hasFocus);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_place_order);

		ButterKnife.bind(this);

		this.buyButton.setGreen();
		this.sellButton.setRed();

		orderBookView.setActivity(this);
		orderBookView.setOnPriceSelectedListener(presenter);

		setTextFilters();
		setTextListeners();
		selectPercentView.setListener(presenter);

		if (getIntent().getExtras() != null) {
			selectedSymbol = getIntent().getExtras().getString(EXTRA_SYMBOL, null);
			selectedAccount = getIntent().getExtras().getParcelable(EXTRA_SELECTED_ACCOUNT);
			String operationType = getIntent().getExtras().getString(EXTRA_OPERATION_TYPE, OPERATION_TYPE_BUY);

			initViewPager(selectedAccount.getId(), selectedSymbol);
			initTabs();

			presenter.setData(selectedSymbol, selectedAccount, operationType);

			return;
		}

		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		finishActivity();
	}

	private void setTextFilters() {
		price.setFilters(new InputFilter[]{new DigitsInputFilter(10, 8, -1)});
		stop.setFilters(new InputFilter[]{new DigitsInputFilter(10, 8, -1)});
		limit.setFilters(new InputFilter[]{new DigitsInputFilter(10, 8, -1)});
		amount.setFilters(new InputFilter[]{new DigitsInputFilter(10, 8, -1)});
		total.setFilters(new InputFilter[]{new DigitsInputFilter(10, 8, -1)});
	}

	@Override
	protected void onDestroy() {
		if (orderBookView != null) {
			orderBookView.onDestroy();
		}
		if (tabSelectedListener != null && tabLayout != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}
		if (tabLayoutOnPageChangeListener != null && viewPager != null) {
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
		}
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
		}
		if (viewPager != null) {
			viewPager.clearOnPageChangeListeners();
		}

		super.onDestroy();
	}

	@Override
	protected void onResume() {
		if (orderBookView != null) {
			orderBookView.onResume();
		}

		super.onResume();
	}

	private void initTabs() {
		openOrdersTab = tabLayout.newTab().setCustomView(getTabView(R.string.open_orders)).setTag("open_orders");
		orderHistoryTab = tabLayout.newTab().setCustomView(getTabView(R.string.order_history)).setTag("order_history");
		tradesHistoryTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades_history)).setTag("trades_history");

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
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
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);

		addPage(openOrdersTab, true);
		addPage(orderHistoryTab, false);
		addPage(tradesHistoryTab, false);
	}

	private View getTabView(int textResId) {
		CustomTabView view = new CustomTabView(this);
		view.setData(0, textResId);
		view.setTextSize(14);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null) {
			pagerAdapter.notifyDataSetChanged();
		}
	}

	public void initViewPager(UUID accountId, String symbol) {
		pagerAdapter = new PlaceOrderPagerAdapter(getSupportFragmentManager(), tabLayout, accountId, symbol);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(5);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);

		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
				pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight()));
	}

	private void setTextListeners() {
		RxTextView.textChanges(price)
				.subscribe(charSequence -> presenter.onPriceChanged(charSequence.toString(), price.hasFocus()));
		RxTextView.textChanges(stop)
				.subscribe(charSequence -> presenter.onStopChanged(charSequence.toString(), stop.hasFocus()));
		RxTextView.textChanges(limit)
				.subscribe(charSequence -> presenter.onLimitChanged(charSequence.toString(), limit.hasFocus()));
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onAmountChanged(charSequence.toString(), amount.hasFocus()));
		RxTextView.textChanges(total)
				.subscribe(charSequence -> presenter.onTotalChanged(charSequence.toString(), total.hasFocus()));
	}

	@Override
	public void selectOperationType(String operationType) {
		if (operationType.equals(OPERATION_TYPE_BUY)) {
			this.selectBuyButton.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorGreen));
			this.selectSellButton.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorCard));
			this.buyButton.setVisibility(View.VISIBLE);
			this.sellButton.setVisibility(View.GONE);

		}
		else if (operationType.equals(OPERATION_TYPE_SELL)) {
			this.selectBuyButton.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorCard));
			this.selectSellButton.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorRed));
			this.buyButton.setVisibility(View.GONE);
			this.sellButton.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setOrderTypeOptions(ArrayList<String> orderTypeOptions) {
		this.orderTypeOptions = orderTypeOptions;
	}

	@Override
	public void setOrderType(String orderType, Integer position) {
		this.orderType.setText(orderType);
		this.selectedOrderTypePosition = position;
	}

	@Override
	public void setPrice(String text) {
		this.price.setText(text);
		this.price.setSelection(text.length(), text.length());
	}

	@Override
	public void setStop(String text) {
		this.stop.setText(text);
		this.stop.setSelection(text.length(), text.length());
	}

	@Override
	public void setLimit(String text) {
		this.limit.setText(text);
		this.limit.setSelection(text.length(), text.length());
	}

	@Override
	public void setAmount(String text) {
		this.amount.setText(text);
		this.amount.setSelection(text.length(), text.length());
	}

	@Override
	public void setPercent(int percent) {
		this.selectPercentView.setProgress(percent);
	}

	@Override
	public void setTotal(String text) {
		this.total.setText(text);
		this.total.setSelection(text.length(), text.length());
	}

	@Override
	public void setAmountError(boolean isError) {
		this.amount.setTextColor(ThemeUtil.getColorByAttrId(this, isError ? R.attr.colorRed : R.attr.colorTextPrimary));
	}

	@Override
	public void setTotalError(boolean isError) {
		this.total.setTextColor(ThemeUtil.getColorByAttrId(this, isError ? R.attr.colorRed : R.attr.colorTextPrimary));
	}

	@Override
	public void setPercentSelectEnabled(boolean enabled) {
		this.selectPercentView.setEnabled(enabled);
	}

	@Override
	public void showProgressBarButton(boolean show) {
		this.progressBarButtons.setVisibility(show ? View.VISIBLE : View.GONE);
		this.buttonsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showMarketPrice(boolean show) {
		this.marketPriceGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showPrice(boolean show) {
		this.priceGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showStop(boolean show) {
		this.stopGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showLimit(boolean show) {
		this.limitGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setLotFilter(Double maxQty, int digitsCount) {
		this.amount.setFilters(new InputFilter[]{new DigitsInputFilter(10, digitsCount, maxQty)});
	}

	@Override
	public void setPriceFilter(Double maxPrice, int digitsCount) {
		this.price.setFilters(new InputFilter[]{new DigitsInputFilter(10, digitsCount, maxPrice)});
		this.limit.setFilters(new InputFilter[]{new DigitsInputFilter(10, digitsCount, maxPrice)});
		this.stop.setFilters(new InputFilter[]{new DigitsInputFilter(10, digitsCount, maxPrice)});
	}

	@Override
	public void setSelectedSymbol(String symbol) {
		selectedSymbol = symbol;
		this.orderBookView.setData(symbol, selectedAccount.getId());
	}

	@Override
	public void setAvailable(String availableText) {
		this.balance.setText(availableText);
	}

	@Override
	public void setBaseQuoteAssets(String baseAsset, String quoteAsset) {
		this.baseAsset.setText(baseAsset);
		this.quoteAsset.setText("/".concat(quoteAsset));

		this.buyButton.setText(String.format("%s %s", getString(R.string.buy), baseAsset));
		this.sellButton.setText(String.format("%s %s", getString(R.string.sell), baseAsset));

		this.priceCurrency.setText(quoteAsset);
		this.stopCurrency.setText(quoteAsset);
		this.limitCurrency.setText(quoteAsset);
		this.amountCurrency.setText(baseAsset);
		this.totalCurrency.setText(quoteAsset);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if (!show) {
//			scrollView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setOpenOrdersCount(Integer count) {
		((CustomTabView) openOrdersTab.getCustomView()).setCount(count);
	}

	@Override
	public void setOrderHistoryCount(Integer count) {
		((CustomTabView) orderHistoryTab.getCustomView()).setCount(count);
	}

	@Override
	public void setTradeHistoryCount(Integer count) {
		((CustomTabView) tradesHistoryTab.getCustomView()).setCount(count);
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, progressBar);
	}

	private void showSoftKeyboard(EditText edittext) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		edittext.requestFocus();
		if (imm != null) {
			imm.showSoftInput(edittext, 0);
		}
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