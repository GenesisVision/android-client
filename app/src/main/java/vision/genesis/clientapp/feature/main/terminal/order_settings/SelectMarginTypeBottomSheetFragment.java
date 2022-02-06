package vision.genesis.clientapp.feature.main.terminal.order_settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinanceRawFuturesChangeMarginTypeResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2022.
 */

public class SelectMarginTypeBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnMarginTypeSelectedListener
	{
		void onMarginTypeSelected(BinanceFuturesMarginType marginType);
	}

	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_SYMBOL = "extra_symbol";

	public static final String EXTRA_MARGIN_TYPE = "extra_margin_type";

	public static SelectMarginTypeBottomSheetFragment with(UUID accountId, String symbol, BinanceFuturesMarginType marginType) {
		SelectMarginTypeBottomSheetFragment fragment = new SelectMarginTypeBottomSheetFragment();
		Bundle arguments = new Bundle(3);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		arguments.putString(EXTRA_MARGIN_TYPE, marginType.toString());
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.cross)
	public TextView cross;

	@BindView(R.id.isolated)
	public TextView isolated;

	@BindView(R.id.cross_check)
	public ImageView crossCheck;

	@BindView(R.id.isolated_check)
	public ImageView isolatedCheck;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private OnMarginTypeSelectedListener listener;

	private UUID accountId;

	private String symbol;

	private BinanceFuturesMarginType currentMarginType;

	private BinanceFuturesMarginType newMarginType;

	private Subscription changeMarginTypeSubscription;

	private Unbinder unbinder;

	@OnClick(R.id.cross)
	public void onCrossClicked() {
		selectMarginType(BinanceFuturesMarginType.CROSS);
	}

	@OnClick(R.id.isolated)
	public void onIsolatedClicked() {
		selectMarginType(BinanceFuturesMarginType.ISOLATED);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		saveCurrentMarginType();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_margin_type, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_SYMBOL)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_MARGIN_TYPE)));
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
		if (changeMarginTypeSubscription != null) {
			changeMarginTypeSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	public void setListener(OnMarginTypeSelectedListener listener) {
		this.listener = listener;
	}

	private void setData(@NonNull UUID accountId, @NonNull String symbol, @NonNull String marginTypeString) {
		this.accountId = accountId;
		this.symbol = symbol;
		this.currentMarginType = BinanceFuturesMarginType.fromValue(marginTypeString);

		this.title.setText(getString(R.string.template_margin_mode, symbol));
		selectMarginType(currentMarginType);
	}

	private void selectMarginType(BinanceFuturesMarginType marginType) {
		this.newMarginType = marginType;
		clearSelection();
		if (marginType != null) {
			switch (marginType) {
				case CROSS:
					cross.setAlpha(0.4f);
					crossCheck.setVisibility(View.VISIBLE);
					break;
				case ISOLATED:
					isolated.setAlpha(0.4f);
					isolatedCheck.setVisibility(View.VISIBLE);
					break;
			}
		}

		this.confirmButton.setEnabled(!newMarginType.equals(currentMarginType));
	}

	private void clearSelection() {
		cross.setAlpha(1f);
		isolated.setAlpha(1f);
		crossCheck.setVisibility(View.INVISIBLE);
		isolatedCheck.setVisibility(View.INVISIBLE);
	}

	private void saveCurrentMarginType() {
		if (changeMarginTypeSubscription != null) {
			changeMarginTypeSubscription.unsubscribe();
		}
		showProgress(true);
		changeMarginTypeSubscription = terminalManager.changeFuturesMarginType(accountId, symbol, newMarginType)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleSuccess,
						this::handleError);
	}


	private void handleSuccess(BinanceRawFuturesChangeMarginTypeResult response) {
		changeMarginTypeSubscription.unsubscribe();

		listener.onMarginTypeSelected(newMarginType);
		this.dismiss();
	}

	private void handleError(Throwable throwable) {
		changeMarginTypeSubscription.unsubscribe();
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
