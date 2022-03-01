package vision.genesis.clientapp.feature.main.terminal.order_settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinancePositionMode;
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

public class SelectPositionModeBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnPositionModeSelectedListener
	{
		void onPositionModeSelected(BinancePositionMode positionMode);
	}

	public static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static final String EXTRA_POSITION_MODE = "extra_position_mode";

	public static final String EXTRA_CAN_CHANGE = "extra_can_change";

	public static SelectPositionModeBottomSheetFragment with(UUID accountId, BinancePositionMode positionMode, boolean canChange) {
		SelectPositionModeBottomSheetFragment fragment = new SelectPositionModeBottomSheetFragment();
		Bundle arguments = new Bundle(3);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_POSITION_MODE, positionMode.toString());
		arguments.putBoolean(EXTRA_CAN_CHANGE, canChange);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.one_way)
	public AppCompatRadioButton oneWay;

	@BindView(R.id.hedge)
	public AppCompatRadioButton hedge;

	@BindView(R.id.cannot_change_warning)
	public TextView cannotChangeWarning;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private OnPositionModeSelectedListener listener;

	private Unbinder unbinder;

	private UUID accountId;

	private BinancePositionMode currentPositionMode;

	private BinancePositionMode newPositionMode = null;

	private Subscription changePositionModeSubscription;

	private Boolean canChange = true;

	@OnClick(R.id.one_way)
	public void onOneWayClicked() {
		selectPositionMode(BinancePositionMode.ONEWAY);
	}

	@OnClick(R.id.hedge)
	public void onHedgeClicked() {
		selectPositionMode(BinancePositionMode.HEDGE);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		saveCurrentPositionMode();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_position_mode, null);

		dialog.setContentView(contentView);

		unbinder = ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		try {
			setData(Objects.requireNonNull((UUID) requireArguments().getSerializable(EXTRA_ACCOUNT_ID)),
					Objects.requireNonNull(requireArguments().getString(EXTRA_POSITION_MODE)),
					requireArguments().getBoolean(EXTRA_CAN_CHANGE));
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
		if (changePositionModeSubscription != null) {
			changePositionModeSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	public void setListener(OnPositionModeSelectedListener listener) {
		this.listener = listener;
	}

	private void setData(@NonNull UUID accountId, @NonNull String positionModeString, @NonNull Boolean canChange) {
		this.accountId = accountId;
		this.currentPositionMode = BinancePositionMode.fromValue(positionModeString);
		this.canChange = canChange;

		if (!canChange) {
			this.cannotChangeWarning.setVisibility(View.VISIBLE);
		}

		selectPositionMode(currentPositionMode);
	}

	private void selectPositionMode(BinancePositionMode positionMode) {
		if (this.newPositionMode == positionMode) {
			return;
		}
		this.newPositionMode = positionMode;
		clearSelection();

		switch (newPositionMode) {
			case ONEWAY:
				oneWay.setChecked(true);
				break;
			case HEDGE:
				hedge.setChecked(true);
				break;
		}

		this.confirmButton.setEnabled(canChange && !newPositionMode.equals(currentPositionMode));
	}

	private void clearSelection() {
		oneWay.setChecked(false);
		hedge.setChecked(false);
	}

	private void saveCurrentPositionMode() {
		if (changePositionModeSubscription != null) {
			changePositionModeSubscription.unsubscribe();
		}
		showProgress(true);
		changePositionModeSubscription = terminalManager.changeFuturesPositionMode(accountId, newPositionMode)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleSuccess,
						this::handleError);
	}


	private void handleSuccess(Void response) {
		changePositionModeSubscription.unsubscribe();

		listener.onPositionModeSelected(newPositionMode);
		this.dismiss();
	}

	private void handleError(Throwable throwable) {
		changePositionModeSubscription.unsubscribe();
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
