package vision.genesis.clientapp.feature.common.select_wallet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/02/2019.
 */

public class SelectWalletBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnWalletSelectedListener
	{
		void onWalletSelected(WalletData wallet);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_wallets)
	public ViewGroup walletsGroup;

	private OnWalletSelectedListener listener;

	private List<WalletData> wallets;

	private String titleText;

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_wallet, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		updateView();
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

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	public void setListener(OnWalletSelectedListener listener) {
		this.listener = listener;
	}

	public void setData(String title, @NonNull List<WalletData> wallets) {
		this.titleText = title;
		this.wallets = wallets;
		updateView();
	}

	private void updateView() {
		if (walletsGroup != null && wallets != null) {
			this.title.setText(titleText);
			for (WalletData wallet : wallets) {
				walletsGroup.addView(createWalletView(wallet));
			}
		}
	}

	private WalletOptionView createWalletView(WalletData wallet) {
		WalletOptionView view = new WalletOptionView(getContext());
		view.setData(wallet);
		view.setOnClickListener(v -> selectOption(wallet));
		return view;
	}

	private void selectOption(WalletData wallet) {
		listener.onWalletSelected(wallet);
		this.dismiss();
	}
}
