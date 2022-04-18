package vision.genesis.clientapp.feature.main.terminal.positions.change_margin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceFuturesMarginChangeDirectionType;
import io.swagger.client.model.BinanceRawFuturesBracket;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.BinanceRawFuturesPositionMarginResult;
import io.swagger.client.model.Currency;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.PositionsHelper;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TabLayoutUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2022.
 */

public class ChangeMarginBottomSheetFragment extends BottomSheetDialogFragment
{
	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_SYMBOL = "extra_symbol";

	public static final String EXTRA_POSITION = "extra_position";

	public static final String EXTRA_FUTURES_BRACKETS = "extra_futures_brackets";

	public static final String EXTRA_AVAILABLE = "extra_available";

	public static ChangeMarginBottomSheetFragment with(UUID accountId,
	                                                   String symbol,
	                                                   BinanceRawFuturesPosition position,
	                                                   ArrayList<BinanceRawFuturesBracket> futuresBrackets,
	                                                   Double available) {
		ChangeMarginBottomSheetFragment fragment = new ChangeMarginBottomSheetFragment();
		Bundle arguments = new Bundle(4);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		arguments.putParcelable(EXTRA_POSITION, position);
		arguments.putParcelableArrayList(EXTRA_FUTURES_BRACKETS, futuresBrackets);
		arguments.putDouble(EXTRA_AVAILABLE, available);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.max)
	public TextView maxText;

	@BindView(R.id.label_current_margin)
	public TextView labelCurrentMargin;

	@BindView(R.id.current_margin)
	public TextView currentMargin;

	@BindView(R.id.label_max_addable)
	public TextView labelMaxAddable;

	@BindView(R.id.max_addable)
	public TextView maxAddable;

	@BindView(R.id.label_liq_price)
	public TextView labelLiqPrice;

	@BindView(R.id.liq_price)
	public TextView liqPriceText;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.Tab addMarginTab;

	private TabLayout.Tab reduceMarginTab;

	private UUID accountId;

	private String symbol;

	private BinanceRawFuturesPosition position;

	private Double newMargin = 0.0;

	private Subscription changeMarginSubscription;

	private Unbinder unbinder;

	private BinanceFuturesMarginChangeDirectionType type = BinanceFuturesMarginChangeDirectionType.ADD;

	private Double currentMarginValue = 0.0;

	private Double max = 0.0;

	private ArrayList<BinanceRawFuturesBracket> futuresBrackets = new ArrayList<>();

	private Double available = 0.0;

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		this.amount.setText(StringFormatUtil.formatAmountWithoutGrouping(max));
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		saveCurrentMargin();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_change_margin, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		initTabs();

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_SYMBOL)),
					Objects.requireNonNull(requireArguments().getParcelable(EXTRA_POSITION)),
					Objects.requireNonNull(requireArguments().getParcelableArrayList(EXTRA_FUTURES_BRACKETS)),
					Objects.requireNonNull(requireArguments().getDouble(EXTRA_AVAILABLE)));

			setTextListener();

		} catch (NullPointerException e) {
			Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
			this.dismiss();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Window window = getDialog().getWindow();
		if (window != null) {
			window.findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			window.getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	@Override
	public void onDestroyView() {
		if (changeMarginSubscription != null) {
			changeMarginSubscription.unsubscribe();
		}
		if (tabSelectedListener != null && tabLayout != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initTabs() {
		addMarginTab = tabLayout.newTab().setCustomView(getTabView(R.string.add_margin)).setTag("add");
		reduceMarginTab = tabLayout.newTab().setCustomView(getTabView(R.string.remove_margin)).setTag("reduce");

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
					changeType(tab.getTag().toString());
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

		addPage(addMarginTab, true);
		addPage(reduceMarginTab, false);
	}

	private void changeType(String tab) {
		if (tab.equals("add")) {
			this.type = BinanceFuturesMarginChangeDirectionType.ADD;
			this.labelMaxAddable.setText(getString(R.string.max_addable));
			this.labelLiqPrice.setText(getString(R.string.liq_price_after_increase));
		}
		else if (tab.equals("reduce")) {
			this.type = BinanceFuturesMarginChangeDirectionType.REDUCE;
			this.labelMaxAddable.setText(getString(R.string.max_removable));
			this.labelLiqPrice.setText(getString(R.string.liq_price_after_reduction));
		}

		updateMax();
		updateLiquidationPrice();
	}

	private View getTabView(int textResId) {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(0, textResId);
		view.setTextSize(14);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, selected ? 0 : 20, 10);
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> onAmountChanged(charSequence.toString()));
	}

	private void setData(@NonNull UUID accountId,
	                     @NonNull String symbol,
	                     @NonNull BinanceRawFuturesPosition position,
	                     @NonNull ArrayList<BinanceRawFuturesBracket> futuresBrackets,
	                     @NonNull Double available) {
		this.accountId = accountId;
		this.symbol = symbol;
		this.position = position;
		this.futuresBrackets = futuresBrackets;
		this.available = available;

		updateMax();
		updateLiquidationPrice();

		this.labelCurrentMargin.setText(String.format(Locale.getDefault(),
				getString(R.string.template_current_margin),
				symbol));

		this.currentMarginValue = PositionsHelper.calculateMargin(position);
		this.currentMargin.setText(StringFormatUtil.getValueString(currentMarginValue, Currency.USDT.getValue()));
	}

	private void onAmountChanged(String newAmount) {
		try {
			newMargin = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			newMargin = 0.0;
		}

		updateMax();
		updateLiquidationPrice();

		setConfirmButtonEnabled(newMargin > 0);
	}

	private void updateMax() {
		if (position == null) {
			return;
		}
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", dfs);
		Double maintMargin = PositionsHelper.calculateMaintMargin(position, futuresBrackets);
		Double truncatedCurrentMargin = Double.valueOf(StringFormatUtil.formatAmount(currentMarginValue, 0, 2));

		max = this.type == BinanceFuturesMarginChangeDirectionType.ADD
				? Double.valueOf(StringFormatUtil.formatAmount(available, 2, 2))
				: PositionsHelper.calculateMaxRemovable(
				position.getEntryPrice(),
				position.getMarkPrice(),
				position.getQuantity(),
				position.getLeverage(),
				truncatedCurrentMargin,
				maintMargin);
		max = Double.valueOf(df.format(max));
		this.maxAddable.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatAmount(max, 2, 2),
				Currency.USDT.getValue()));
	}

	private void updateLiquidationPrice() {
		if (position == null) {
			return;
		}
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", dfs);
		Double truncatedCurrentMargin = Double.valueOf(df.format(currentMarginValue));
		double isolatedWallet = truncatedCurrentMargin +
				(this.type == BinanceFuturesMarginChangeDirectionType.ADD
						? newMargin
						: -newMargin);
		double maintAmount = PositionsHelper.getMaintAmount(position, futuresBrackets);
		double maintMarginRatio = PositionsHelper.getMaintMarginRatio(position, futuresBrackets);

		double liqPrice = PositionsHelper.calculateIsolatedLiquidationPrice(
				position.getEntryPrice(),
				position.getQuantity(),
				isolatedWallet,
				maintAmount,
				maintMarginRatio,
				position.getPositionSide());
		this.liqPriceText.setText(liqPrice >= 0
				? StringFormatUtil.formatAmount(liqPrice, 2, 4).concat(" ").concat(Currency.USDT.getValue())
				: "-");
	}

	private void setConfirmButtonEnabled(boolean enabled) {
		this.confirmButton.setEnabled(enabled);
	}

	private void saveCurrentMargin() {
		if (changeMarginSubscription != null) {
			changeMarginSubscription.unsubscribe();
		}
		showProgress(true);
		changeMarginSubscription = terminalManager.changeFuturesPositionMargin(accountId, symbol, newMargin, type, position.getPositionSide())
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleSuccess,
						this::handleError);
	}


	private void handleSuccess(BinanceRawFuturesPositionMarginResult response) {
		changeMarginSubscription.unsubscribe();

//		listener.onLeverageSelected(newLeverage);
		this.dismiss();
	}

	private void handleError(Throwable throwable) {
		changeMarginSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showSnackbarMessage);
	}

	private void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		this.confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showSnackbarMessage(String message) {
		Snackbar.make(amount, message, Snackbar.LENGTH_LONG).show();
	}

	private void showSoftKeyboard() {
		if (getActivity() != null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			amount.requestFocus();
			if (imm != null) {
				imm.showSoftInput(amount, 0);
			}
		}
	}
}
