package vision.genesis.clientapp.feature.main.terminal.select_account;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ExchangeAsset;
import timber.log.Timber;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/10/2018.
 */

public class SelectAccountBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnAccountSelectedListener
	{
		void onAccountSelected(ExchangeAsset account);
	}

	public static final String EXTRA_ACCOUNTS = "extra_accounts";

	public static SelectAccountBottomSheetFragment with(ArrayList<ExchangeAsset> accounts) {
		SelectAccountBottomSheetFragment fragment = new SelectAccountBottomSheetFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelableArrayList(EXTRA_ACCOUNTS, accounts);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_accounts)
	public ViewGroup accountsGroup;

	private OnAccountSelectedListener listener;


	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_account, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		try {
			Objects.requireNonNull(getArguments());
			setData(Objects.requireNonNull(getArguments().getParcelableArrayList(EXTRA_ACCOUNTS)));
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

	public void setListener(OnAccountSelectedListener listener) {
		this.listener = listener;
	}

	private void setData(@NonNull List<ExchangeAsset> accounts) {
		for (ExchangeAsset account : accounts) {
			accountsGroup.addView(createAccountView(account));
		}
	}

	private AccountView createAccountView(ExchangeAsset account) {
		AccountView view = new AccountView(getContext());
		view.setData(account);
		view.setOnClickListener(v -> selectAccount(account));
		return view;
	}

	private void selectAccount(ExchangeAsset account) {
		listener.onAccountSelected(account);
		this.dismiss();
	}
}
