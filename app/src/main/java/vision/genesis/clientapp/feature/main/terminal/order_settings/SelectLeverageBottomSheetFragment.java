package vision.genesis.clientapp.feature.main.terminal.order_settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceRawFuturesBracket;
import io.swagger.client.model.BinanceRawFuturesInitialLeverageChangeResult;
import io.swagger.client.model.Currency;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2022.
 */

public class SelectLeverageBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnLeverageSelectedListener
	{
		void onLeverageSelected(Integer leverage);
	}

	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_SYMBOL = "extra_symbol";

	public static final String EXTRA_LEVERAGE = "extra_leverage";

	public static final String EXTRA_BRACKETS = "extra_brackets";

	public static SelectLeverageBottomSheetFragment with(UUID accountId, String symbol, Integer currentLeverage, ArrayList<BinanceRawFuturesBracket> brackets) {
		SelectLeverageBottomSheetFragment fragment = new SelectLeverageBottomSheetFragment();
		Bundle arguments = new Bundle(4);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		arguments.putInt(EXTRA_LEVERAGE, currentLeverage);
		arguments.putParcelableArrayList(EXTRA_BRACKETS, brackets);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.leverage)
	public TextView leverage;

	@BindView(R.id.slider)
	public SeekBar seekBar;

	@BindView(R.id.button_minus)
	public ImageView minusButton;

	@BindView(R.id.button_plus)
	public ImageView plusButton;

	@BindView(R.id.warning)
	public TextView warning;

	@BindView(R.id.min)
	public TextView min;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private OnLeverageSelectedListener listener;

	private UUID accountId;

	private String symbol;

	private Integer currentLeverage;

	private Integer newLeverage = 0;

	private ArrayList<BinanceRawFuturesBracket> brackets;

	private Subscription changeLeverageSubscription;

	private Unbinder unbinder;

	private Integer minLeverage = 1;

	private Integer maxLeverage = 125;

	@OnClick(R.id.button_minus)
	public void onMinusClicked() {
		setLeverage(newLeverage - 1, false);
	}

	@OnClick(R.id.button_plus)
	public void onPlusClicked() {
		setLeverage(newLeverage + 1, false);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		saveCurrentLeverage();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_leverage, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_SYMBOL)),
					requireArguments().getInt(EXTRA_LEVERAGE, 1),
					Objects.requireNonNull(requireArguments().getParcelableArrayList(EXTRA_BRACKETS)));
			seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
			{
				@Override
				public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
					setLeverage(value + minLeverage, fromUser);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}
			});
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
		if (changeLeverageSubscription != null) {
			changeLeverageSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}


	public void setListener(OnLeverageSelectedListener listener) {
		this.listener = listener;
	}

	private void setData(@NonNull UUID accountId, @NonNull String symbol, @NonNull Integer currentLeverage, @NonNull ArrayList<BinanceRawFuturesBracket> brackets) {
		this.accountId = accountId;
		this.symbol = symbol;
		this.currentLeverage = currentLeverage;
		this.brackets = brackets;

		setMinMax(brackets);
		setLeverage(currentLeverage, false);
	}

	private void setMinMax(ArrayList<BinanceRawFuturesBracket> brackets) {
		if (brackets != null && !brackets.isEmpty()) {
			minLeverage = brackets.get(brackets.size() - 1).getInitialLeverage();
			maxLeverage = brackets.get(0).getInitialLeverage();
			seekBar.setMax(maxLeverage - minLeverage);

			this.min.setText(String.format(Locale.getDefault(), "%dx", minLeverage));
			this.max.setText(String.format(Locale.getDefault(), "%dx", maxLeverage));
		}
	}

	private void setLeverage(Integer leverage, boolean fromUser) {
		if (newLeverage.equals(leverage)
				|| leverage < minLeverage
				|| leverage > maxLeverage) {
			return;
		}
		this.newLeverage = leverage;
		this.leverage.setText(String.format(Locale.getDefault(), "%dx", newLeverage));
		if (!fromUser) {
			this.seekBar.setProgress(leverage - minLeverage);
		}

		setWarning(leverage);

		this.minusButton.setEnabled(newLeverage > minLeverage);
		this.plusButton.setEnabled(newLeverage < maxLeverage);
		this.confirmButton.setEnabled(!newLeverage.equals(currentLeverage));
	}

	private void setWarning(Integer leverage) {
		if (brackets != null && !brackets.isEmpty()) {
			BinanceRawFuturesBracket currentBracket = brackets.get(0);
			for (BinanceRawFuturesBracket bracket : brackets) {
				if (bracket.getInitialLeverage() <= leverage) {
					currentBracket = bracket;
					break;
				}
			}
			this.warning.setText(getString(R.string.template_adjust_leverage_warning,
					StringFormatUtil.getValueString((double) currentBracket.getCap(), Currency.USDT.getValue())));
		}
	}

	private void saveCurrentLeverage() {
		if (changeLeverageSubscription != null) {
			changeLeverageSubscription.unsubscribe();
		}
		showProgress(true);
		changeLeverageSubscription = terminalManager.changeFuturesLeverage(accountId, symbol, newLeverage)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleSuccess,
						this::handleError);
	}


	private void handleSuccess(BinanceRawFuturesInitialLeverageChangeResult response) {
		changeLeverageSubscription.unsubscribe();

		listener.onLeverageSelected(newLeverage);
		this.dismiss();
	}

	private void handleError(Throwable throwable) {
		changeLeverageSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showSnackbarMessage);
	}

	private void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		this.confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showSnackbarMessage(String message) {
		Snackbar.make(title, message, Snackbar.LENGTH_LONG).show();
	}
}
