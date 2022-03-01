package vision.genesis.clientapp.feature.main.terminal.positions.close;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinancePositionSide;
import io.swagger.client.model.BinanceRawFuturesPlaceOrder;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.BinanceTimeInForce;
import io.swagger.client.model.Currency;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2022.
 */

public class ClosePositionBottomSheetFragment extends BottomSheetDialogFragment
{
	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_SYMBOL = "extra_symbol";

	public static final String EXTRA_POSITION = "extra_position";

	public static ClosePositionBottomSheetFragment with(UUID accountId, String symbol, BinanceRawFuturesPosition position) {
		ClosePositionBottomSheetFragment fragment = new ClosePositionBottomSheetFragment();
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


	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.group_price)
	public ViewGroup priceGroup;

	@BindView(R.id.group_market)
	public ViewGroup marketGroup;

	@BindView(R.id.price)
	public EditText price;


	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.amount)
	public EditText amount;

	@BindView(R.id.asset)
	public TextView asset;


	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private UUID accountId;

	private String symbol;

	private BinanceRawFuturesPosition position;

	private Subscription placeCloseOrderSubscription;

	private Unbinder unbinder;

	private int selectedTypePosition = -1;

	private ArrayList<String> typeOptions;

	private double closePrice = 0.0;

	private double closeAmount = 0.0;

	private BinanceOrderType selectedType = BinanceOrderType.LIMIT;

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard(amount);
	}

	@OnClick(R.id.group_price)
	public void onPriceClicked() {
		showSoftKeyboard(price);
	}

	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		if (getActivity() != null) {
			if (typeOptions != null && typeOptions.size() > 1) {
				SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
						getString(R.string.type), typeOptions, selectedTypePosition);
				fragment.setListener(this::onTypeSelected);
				fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
			}
		}
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		placeCloseOrder();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_close_position, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_SYMBOL)),
					Objects.requireNonNull(requireArguments().getParcelable(EXTRA_POSITION)));

			setTextListeners();
			initTypeOptions();

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
		if (placeCloseOrderSubscription != null) {
			placeCloseOrderSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initTypeOptions() {
		typeOptions = new ArrayList<>();
		typeOptions.add(getContext().getString(R.string.limit));
		typeOptions.add(getContext().getString(R.string.market));
		onTypeSelected(0, typeOptions.get(0));
	}

	private void onTypeSelected(Integer position, String text) {
		this.type.setText(text);
		this.selectedTypePosition = position;
		this.selectedType = selectedTypePosition == 0 ? BinanceOrderType.LIMIT : BinanceOrderType.MARKET;

		Pair<String, String> baseQuoteAssets = terminalManager.getBaseQuoteAssets(symbol);
		if (baseQuoteAssets != null) {
			this.asset.setText(baseQuoteAssets.first);
		}

		this.priceGroup.setVisibility(selectedTypePosition == 0 ? View.VISIBLE : View.GONE);
		this.marketGroup.setVisibility(selectedTypePosition == 1 ? View.VISIBLE : View.GONE);
	}

	private void setTextListeners() {
		RxTextView.textChanges(price)
				.subscribe(charSequence -> onPriceChanged(charSequence.toString()));
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> onAmountChanged(charSequence.toString()));
	}

	private void setData(@NonNull UUID accountId, @NonNull String symbol, @NonNull BinanceRawFuturesPosition position) {
		this.accountId = accountId;
		this.symbol = symbol;
		this.position = position;

		this.symbolValue.setText(symbol);
		this.entryPrice.setText(StringFormatUtil.getValueString(position.getEntryPrice(), Currency.USDT.getValue()));
		this.markPrice.setText(StringFormatUtil.getValueString(position.getMarkPrice(), Currency.USDT.getValue()));
	}

	private void onPriceChanged(String newAmount) {
		try {
			closePrice = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			closePrice = 0.0;
		}

		updateConfirmButtonEnabled();
	}

	private void onAmountChanged(String newAmount) {
		try {
			closeAmount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			closeAmount = 0.0;
		}

		updateConfirmButtonEnabled();
	}

	private void updateConfirmButtonEnabled() {
		setConfirmButtonEnabled(closePrice > 0 || closeAmount > 0);
	}

	private void setConfirmButtonEnabled(boolean enabled) {
		this.confirmButton.setEnabled(enabled);
	}


	private void placeCloseOrder() {
		if (terminalManager != null && accountId != null && closeAmount > 0) {
			BinanceRawFuturesPlaceOrder order = new BinanceRawFuturesPlaceOrder();
			BinanceOrderSide side = position.getPositionSide().equals(BinancePositionSide.SHORT)
					? BinanceOrderSide.BUY
					: BinanceOrderSide.SELL;

			order.setType(selectedType);
			order.setSide(side);
			order.setPositionSide(position.getPositionSide());
			order.setSymbol(symbol);
			order.setQuantity(closeAmount);
			order.setReduceOnly(true);
			if (selectedType.equals(BinanceOrderType.LIMIT)) {
				order.setPrice(closePrice);
				order.setTimeInForce(BinanceTimeInForce.GOODTILLCANCEL);
			}

			if (placeCloseOrderSubscription != null) {
				placeCloseOrderSubscription.unsubscribe();
			}
			placeCloseOrderSubscription = terminalManager.placeFuturesOrder(order, accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handlePlaceCloseOrderSuccess,
							this::handlePlaceCloseOrderError);
		}
	}

	private void handlePlaceCloseOrderSuccess(Object response) {
		placeCloseOrderSubscription.unsubscribe();
		this.dismiss();
	}

	private void handlePlaceCloseOrderError(Throwable throwable) {
		placeCloseOrderSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		this.confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
