package vision.genesis.clientapp.feature.common.currency;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/09/2018.
 */

public class SelectCurrencyFragment extends BottomSheetDialogFragment
{
	public interface OnCurrencyChangedListener
	{
		void onCurrencyChanged(CurrencyEnum currency);
	}

	public static final String EXTRA_SELECTED_CURRENCY = "extra_selected_currency";

	public static SelectCurrencyFragment with(String selectedCurrency) {
		SelectCurrencyFragment fragment = new SelectCurrencyFragment();
		Bundle arguments = new Bundle(1);
		arguments.putString(EXTRA_SELECTED_CURRENCY, selectedCurrency);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public RateManager rateManager;

	@BindView(R.id.group_options)
	public ViewGroup optionsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private OnCurrencyChangedListener listener;

	private String selectedCurrency;

	private CurrencyOptionView selectedOption;

	private Subscription ratesSubscription;

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_currency, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		if (getArguments() != null) {
			selectedCurrency = getArguments().getString(EXTRA_SELECTED_CURRENCY);
		}

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToRates();
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
		if (ratesSubscription != null)
			ratesSubscription.unsubscribe();

		super.onDestroyView();
	}

	public void setListener(OnCurrencyChangedListener listener) {
		this.listener = listener;
	}

	private void setData(HashMap<CurrencyEnum, Double> currenciesRatesMap) {
		for (String currency : RateManager.baseCurrenciesList) {
			CurrencyEnum currencyEnum = CurrencyEnum.fromValue(currency);
			optionsGroup.addView(createCurrencyOptionView(currencyEnum, currenciesRatesMap.get(currencyEnum), currency.equals(selectedCurrency)));
		}
	}

	private CurrencyOptionView createCurrencyOptionView(CurrencyEnum currency, Double rate, Boolean isSelected) {
		CurrencyOptionView view = new CurrencyOptionView(getContext());
		view.setData(currency, StringFormatUtil.formatAmount(rate));
		view.setSelected(isSelected);
		if (isSelected)
			selectedOption = view;
		view.setOnClickListener(v -> selectOption(view));
		return view;
	}

	private void subscribeToRates() {
		ratesSubscription = rateManager.getBaseRates()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetRatesSuccess,
						this::handleGetRatesError);
	}

	private void handleGetRatesSuccess(HashMap<CurrencyEnum, Double> response) {
		ratesSubscription.unsubscribe();
		progressBar.setVisibility(View.GONE);
		setData(response);
	}

	private void handleGetRatesError(Throwable throwable) {
		ratesSubscription.unsubscribe();
	}

	private void selectOption(CurrencyOptionView newOption) {
		if (selectedOption != null)
			selectedOption.setSelected(false);
		newOption.setSelected(true);

		listener.onCurrencyChanged(newOption.getCurrency());
		this.dismiss();
	}
}
