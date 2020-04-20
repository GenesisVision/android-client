package vision.genesis.clientapp.feature.main.copytrading.commissions;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderSignalFee;
import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2019.
 */

public class CommissionsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_total)
	public ViewGroup totalGroup;

	@BindView(R.id.total_label)
	public TextView totalLabel;

	@BindView(R.id.total_value)
	public TextView totalValue;

	@BindView(R.id.total_currency)
	public TextView totalCurrency;

	@BindView(R.id.group_fees)
	public ViewGroup feesGroup;

	private OrderSignalModel trade;

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_commissions, null);

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
		totalLabel.setTypeface(TypefaceUtil.semibold());
		totalValue.setTypeface(TypefaceUtil.semibold());
		totalCurrency.setTypeface(TypefaceUtil.light());
	}

	public void setData(OrderSignalModel trade) {
		this.trade = trade;
		updateView();
	}

	private void updateView() {
		if (trade.getProviders() == null || trade.getProviders().isEmpty()) {
			return;
		}

		if (feesGroup != null) {
			feesGroup.addView(createTradingFeeView(trade));

			List<OrderSignalFee> fees = trade.getProviders().get(0).getFees();
			if (fees != null) {
				for (OrderSignalFee fee : fees) {
					feesGroup.addView(createCommissionView(fee));
				}

				if (!fees.isEmpty()) {
					totalGroup.setVisibility(View.VISIBLE);
					totalValue.setText(StringFormatUtil.formatCurrencyAmount(trade.getTotalCommission(), trade.getCurrency().getValue()));
					totalCurrency.setText(trade.getCurrency().getValue());
				}
				else {
					totalGroup.setVisibility(View.GONE);
				}
			}
		}
	}

	private CommissionView createTradingFeeView(OrderSignalModel trade) {
		CommissionView view = new CommissionView(getContext());
		view.setData(getString(R.string.trading_fee), trade.getCommission(), trade.getCurrency().getValue());
		return view;
	}

	private CommissionView createCommissionView(OrderSignalFee fee) {
		CommissionView view = new CommissionView(getContext());
		view.setData(fee);
		return view;
	}
}
