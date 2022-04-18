package vision.genesis.clientapp.feature.main.terminal.positions.tpsl;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;

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
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinancePositionSide;
import io.swagger.client.model.BinanceRawFuturesOrder;
import io.swagger.client.model.BinanceRawFuturesOrderItemsViewModel;
import io.swagger.client.model.BinanceRawFuturesPlaceOrder;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.BinanceTimeInForce;
import io.swagger.client.model.BinanceWorkingType;
import io.swagger.client.model.Currency;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.events.SetOpenOrdersCountEvent;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.PositionsHelper;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2022.
 */

public class TpSlBottomSheetFragment extends BottomSheetDialogFragment
{
	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_SYMBOL = "extra_symbol";

	public static final String EXTRA_POSITION = "extra_position";

	public static TpSlBottomSheetFragment with(UUID accountId, String symbol, BinanceRawFuturesPosition position) {
		TpSlBottomSheetFragment fragment = new TpSlBottomSheetFragment();
		Bundle arguments = new Bundle(3);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		arguments.putParcelable(EXTRA_POSITION, position);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.symbol)
	public TextView symbolValue;

	@BindView(R.id.entry_price)
	public TextView entryPrice;

	@BindView(R.id.mark_price)
	public TextView markPrice;


	@BindView(R.id.group_tp_order)
	public ViewGroup tpOrderGroup;

	@BindView(R.id.tp_order_info)
	public TextView tpOrderInfo;

	@BindView(R.id.working_type_tp)
	public TextView tpWorkingType;

	@BindView(R.id.group_amount_tp)
	public ViewGroup tpAmountGroup;

	@BindView(R.id.edittext_tp)
	public EditText tpEdittext;

	@BindView(R.id.tp_info)
	public TextView tpInfo;


	@BindView(R.id.group_sl_order)
	public ViewGroup slOrderGroup;

	@BindView(R.id.sl_order_info)
	public TextView slOrderInfo;

	@BindView(R.id.working_type_sl)
	public TextView slWorkingType;

	@BindView(R.id.group_amount_sl)
	public ViewGroup slAmountGroup;

	@BindView(R.id.edittext_sl)
	public EditText slEdittext;

	@BindView(R.id.sl_info)
	public TextView slInfo;


	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	ArrayList<BinanceOrder> orders = new ArrayList<>();

	private UUID accountId;

	private String symbol;

	private BinanceRawFuturesPosition position;

	private Subscription getOrdersSubscription;

	private Subscription closeOrderSubscription;

	private Subscription placeTpSubscription;

	private Subscription placeSlSubscription;

	private Unbinder unbinder;

	private BinanceFuturesMarginChangeDirectionType type = BinanceFuturesMarginChangeDirectionType.ADD;

	private double currentMarginValue = 0.0;

	private int selectedTpWorkingTypePosition = -1;

	private int selectedSlWorkingTypePosition = -1;

	private ArrayList<String> workingTypeOptions;

	private BinanceWorkingType tpWorkingTypeValue = BinanceWorkingType.CONTRACT;

	private BinanceWorkingType slWorkingTypeValue = BinanceWorkingType.CONTRACT;

	private double newTp = 0.0;

	private double newSl = 0.0;

	private BinanceOrder tpOrder = null;

	private BinanceOrder slOrder = null;

	private int requestsCount = 0;

	@OnClick(R.id.group_amount_tp_et)
	public void onAmountTpClicked() {
		showSoftKeyboard(tpEdittext);
	}

	@OnClick(R.id.group_amount_sl_et)
	public void onAmountSlClicked() {
		showSoftKeyboard(slEdittext);
	}

	@OnClick(R.id.group_working_type_tp)
	public void onWorkingTypeTpClicked() {
		if (getActivity() != null) {
			if (workingTypeOptions != null && workingTypeOptions.size() > 1) {
				SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
						getString(R.string.working_type), workingTypeOptions, selectedTpWorkingTypePosition);
				fragment.setListener(this::onTpWorkingTypeSelected);
				fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
			}
		}
	}

	@OnClick(R.id.group_working_type_sl)
	public void onWorkingTypeSlClicked() {
		if (getActivity() != null) {
			if (workingTypeOptions != null && workingTypeOptions.size() > 1) {
				SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
						getString(R.string.working_type), workingTypeOptions, selectedSlWorkingTypePosition);
				fragment.setListener(this::onSlWorkingTypeSelected);
				fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
			}
		}
	}

	@OnClick(R.id.tp_order_cancel)
	public void onTpOrderCancelClicked() {
		closeOrder(tpOrder);
	}

	@OnClick(R.id.sl_order_cancel)
	public void onSlOrderCancelClicked() {
		closeOrder(slOrder);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		setTpSlOrders();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_tpsl, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_SYMBOL)),
					Objects.requireNonNull(requireArguments().getParcelable(EXTRA_POSITION)));

			setTextListeners();
			initWorkingTypeOptions();
			getOrders();

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
		if (getOrdersSubscription != null) {
			getOrdersSubscription.unsubscribe();
		}
		if (closeOrderSubscription != null) {
			closeOrderSubscription.unsubscribe();
		}
		if (placeTpSubscription != null) {
			placeTpSubscription.unsubscribe();
		}
		if (placeSlSubscription != null) {
			placeSlSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initWorkingTypeOptions() {
		workingTypeOptions = new ArrayList<>();
		workingTypeOptions.add(getContext().getString(R.string.last));
		workingTypeOptions.add(getContext().getString(R.string.mark));
		onTpWorkingTypeSelected(0, workingTypeOptions.get(0));
		onSlWorkingTypeSelected(0, workingTypeOptions.get(0));
	}

	private void onTpWorkingTypeSelected(Integer position, String text) {
		tpWorkingTypeValue = position == 0 ? BinanceWorkingType.CONTRACT : BinanceWorkingType.MARK;
		this.tpWorkingType.setText(text);
		this.selectedTpWorkingTypePosition = position;

		updateTpInfo();
	}

	private void onSlWorkingTypeSelected(Integer position, String text) {
		slWorkingTypeValue = position == 0 ? BinanceWorkingType.CONTRACT : BinanceWorkingType.MARK;
		this.slWorkingType.setText(text);
		this.selectedSlWorkingTypePosition = position;

		updateSlInfo();
	}

	private void setTextListeners() {
		RxTextView.textChanges(tpEdittext)
				.subscribe(charSequence -> onTakeProfitChanged(charSequence.toString()));
		RxTextView.textChanges(slEdittext)
				.subscribe(charSequence -> onStopLossChanged(charSequence.toString()));
	}

	private void setData(@NonNull UUID accountId, @NonNull String symbol, @NonNull BinanceRawFuturesPosition position) {
		this.accountId = accountId;
		this.symbol = symbol;
		this.position = position;

		this.symbolValue.setText(symbol);
		this.entryPrice.setText(StringFormatUtil.getValueString(position.getEntryPrice(), Currency.USDT.getValue()));
		this.markPrice.setText(StringFormatUtil.getValueString(position.getMarkPrice(), Currency.USDT.getValue()));
	}

	private void onTakeProfitChanged(String newAmount) {
		try {
			newTp = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			newTp = 0.0;
		}

		updateTpInfo();
		updateConfirmButtonEnabled();
	}

	private void updateTpInfo() {
		double pnl = PositionsHelper.calculateRealisedPnl(position.getEntryPrice(),
				tpOrder != null ? tpOrder.getStopPrice() : newTp,
				position.getQuantity());
		String pnlText = StringFormatUtil.formatAmount(pnl, 2, 2)
				.concat(" ")
				.concat(Currency.USDT.getValue());
		String text = getString(R.string.template_tp_info,
				StringFormatUtil.getWorkingTypeLabel(tpOrder != null ? tpOrder.getWorkingType() : tpWorkingTypeValue),
				StringFormatUtil.getValueString(tpOrder != null ? tpOrder.getStopPrice() : newTp,
						Currency.USDT.getValue()),
				pnlText);
		int pnlColorResId = pnl >= 0 ? R.attr.colorGreen : R.attr.colorRed;
		StringFormatUtil.setColorSpan(getContext(), this.tpInfo, text, pnlText, pnlColorResId);
	}

	private void onStopLossChanged(String newAmount) {
		try {
			newSl = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			newSl = 0.0;
		}

		updateSlInfo();
		updateConfirmButtonEnabled();
	}

	private void updateSlInfo() {
		double pnl = PositionsHelper.calculateRealisedPnl(position.getEntryPrice(),
				slOrder != null ? slOrder.getStopPrice() : newSl,
				position.getQuantity());
		String pnlText = StringFormatUtil.formatAmount(pnl, 2, 2)
				.concat(" ")
				.concat(Currency.USDT.getValue());
		String text = getString(R.string.template_sl_info,
				StringFormatUtil.getWorkingTypeLabel(slOrder != null ? slOrder.getWorkingType() : slWorkingTypeValue),
				StringFormatUtil.getValueString(slOrder != null ? slOrder.getStopPrice() : newSl, Currency.USDT.getValue()),
				pnlText);
		int pnlColorResId = pnl >= 0 ? R.attr.colorGreen : R.attr.colorRed;
		StringFormatUtil.setColorSpan(getContext(), this.slInfo, text, pnlText, pnlColorResId);
	}

	private void updateConfirmButtonEnabled() {
		setConfirmButtonEnabled(newTp > 0 || newSl > 0);
	}

	private void setConfirmButtonEnabled(boolean enabled) {
		this.confirmButton.setEnabled(enabled);
	}

	private void getOrders() {
		if (terminalManager != null && accountId != null) {
			if (getOrdersSubscription != null) {
				getOrdersSubscription.unsubscribe();
			}
			getOrdersSubscription = terminalManager.getFuturesOpenOrders(accountId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetFuturesOrdersResponse,
							this::handleGetOrdersError);
		}
	}

	private void handleGetFuturesOrdersResponse(BinanceRawFuturesOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();

		EventBus.getDefault().post(new SetOpenOrdersCountEvent(model.getTotal()));

		BinanceOrder newTpOrder = null;
		BinanceOrder newSlOrder = null;
		for (BinanceRawFuturesOrder item : model.getItems()) {
			if (item.getAccountId().equals(accountId) && item.isClosePosition()) {
				if (item.getType().equals(BinanceOrderType.TAKEPROFITMARKET)) {
					newTpOrder = BinanceOrder.from(item);
				}
				else if (item.getType().equals(BinanceOrderType.STOPMARKET)) {
					newSlOrder = BinanceOrder.from(item);
				}
			}
		}
		setTpOrder(newTpOrder);
		setSlOrder(newSlOrder);
	}

	private void setTpOrder(BinanceOrder order) {
		tpOrder = order;
		tpOrderGroup.setVisibility(order != null ? View.VISIBLE : View.GONE);
		tpAmountGroup.setVisibility(order != null ? View.GONE : View.VISIBLE);
		tpOrderInfo.setText(order != null
				? String.format(Locale.getDefault(), "%s >= %s",
				StringFormatUtil.getWorkingTypeLabel(order.getWorkingType()), StringFormatUtil.formatAmount(order.getStopPrice()))
				: "");
		updateTpInfo();
	}

	private void setSlOrder(BinanceOrder order) {
		slOrder = order;
		slOrderGroup.setVisibility(order != null ? View.VISIBLE : View.GONE);
		slAmountGroup.setVisibility(order != null ? View.GONE : View.VISIBLE);
		slOrderInfo.setText(order != null
				? String.format(Locale.getDefault(), "%s <= %s",
				StringFormatUtil.getWorkingTypeLabel(order.getWorkingType()), StringFormatUtil.formatAmount(order.getStopPrice()))
				: "");
		updateSlInfo();
	}

	private void handleGetOrdersError(Throwable error) {
		getOrdersSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(error)) {
			showSnackbarMessage(getContext().getResources().getString(R.string.network_error));
		}
	}

	private void closeOrder(BinanceOrder order) {
		if (terminalManager != null && order != null) {
			order.setAccountId(accountId);
			closeOrderSubscription = terminalManager.cancelFuturesOrder(order)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCloseOrderResponse,
							this::handleCloseOrderError);
		}
	}

	private void handleCloseOrderResponse(Object response) {
		closeOrderSubscription.unsubscribe();
		getOrders();
	}

	private void handleCloseOrderError(Throwable error) {
		closeOrderSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(error)) {
			showSnackbarMessage(getContext().getResources().getString(R.string.network_error));
		}
	}

	private void setTpSlOrders() {
		showProgress(true);

		requestsCount = 0;
		if (newTp > 0) {
			requestsCount++;
		}
		if (newSl > 0) {
			requestsCount++;
		}

		if (newTp > 0) {
			placeTp();
		}
		if (newSl > 0) {
			placeSl();
		}
	}

	private void placeTp() {
		if (terminalManager != null && accountId != null && newTp > 0) {
			BinanceRawFuturesPlaceOrder order = new BinanceRawFuturesPlaceOrder();
			BinanceOrderSide side = position.getPositionSide().equals(BinancePositionSide.SHORT)
					? BinanceOrderSide.BUY
					: BinanceOrderSide.SELL;
			order.setSide(side);
			order.setPositionSide(position.getPositionSide());
			order.setSymbol(symbol);
			order.setType(BinanceOrderType.TAKEPROFITMARKET);
			order.setStopPrice(newTp);
			order.setTimeInForce(BinanceTimeInForce.GOODTILLEXPIREDORCANCELED);
			order.setWorkingType(tpWorkingTypeValue);
			order.setClosePosition(true);

			if (placeTpSubscription != null) {
				placeTpSubscription.unsubscribe();
			}
			placeTpSubscription = terminalManager.placeFuturesOrder(order, accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handlePlaceTpSuccess,
							this::handlePlaceTpError);
		}
	}

	private void handlePlaceTpSuccess(Object response) {
		placeTpSubscription.unsubscribe();
		requestsCount--;
		checkIfFinishedAndClose();
	}

	private void handlePlaceTpError(Throwable throwable) {
		placeTpSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showSnackbarMessage);
	}

	private void placeSl() {
		if (terminalManager != null && accountId != null && newSl > 0) {
			BinanceRawFuturesPlaceOrder order = new BinanceRawFuturesPlaceOrder();
			BinanceOrderSide side = position.getPositionSide().equals(BinancePositionSide.SHORT)
					? BinanceOrderSide.BUY
					: BinanceOrderSide.SELL;
			order.setSide(side);
			order.setPositionSide(position.getPositionSide());
			order.setSymbol(symbol);
			order.setType(BinanceOrderType.STOPMARKET);
			order.setStopPrice(newSl);
			order.setTimeInForce(BinanceTimeInForce.GOODTILLEXPIREDORCANCELED);
			order.setWorkingType(slWorkingTypeValue);
			order.setClosePosition(true);

			if (placeSlSubscription != null) {
				placeSlSubscription.unsubscribe();
			}
			placeSlSubscription = terminalManager.placeFuturesOrder(order, accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handlePlaceSlSuccess,
							this::handlePlaceSlError);
		}
	}

	private void handlePlaceSlSuccess(Object response) {
		placeSlSubscription.unsubscribe();
		requestsCount--;
		checkIfFinishedAndClose();
	}

	private void handlePlaceSlError(Throwable throwable) {
		placeSlSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showSnackbarMessage);
	}

	private void checkIfFinishedAndClose() {
		if (requestsCount == 0) {
			this.dismiss();
		}
	}

	private void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		this.confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showSnackbarMessage(String message) {
		Snackbar.make(tpInfo, message, Snackbar.LENGTH_LONG).show();
	}

	private void showSoftKeyboard(EditText edittext) {
		if (getActivity() != null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			edittext.requestFocus();
			if (imm != null) {
				imm.showSoftInput(edittext, 0);
			}
		}
	}
}
