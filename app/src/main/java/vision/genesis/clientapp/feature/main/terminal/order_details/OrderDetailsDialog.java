package vision.genesis.clientapp.feature.main.terminal.order_details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.BinanceOrderSide;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/09/2021.
 */
public class OrderDetailsDialog extends BottomSheetDialogFragment
{
	@BindView(R.id.entry)
	public ImageView entry;

	@BindView(R.id.symbol)
	public TextView symbol;

	@BindView(R.id.status)
	public TextView status;

	@BindView(R.id.side)
	public TextView side;

	@BindView(R.id.quantity)
	public TextView quantity;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.amount)
	public TextView amount;

	@BindView(R.id.group_stop)
	public ViewGroup stopGroup;

	@BindView(R.id.stop)
	public TextView stop;

	@BindView(R.id.executed)
	public TextView executed;

	@BindView(R.id.fee)
	public TextView fee;

	@BindView(R.id.filled_progress)
	public ProgressBar filledProgress;

	@BindView(R.id.filled_percent)
	public TextView filledPercent;

	private BinanceOrder order;

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_order, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setData(BinanceOrder order) {
		this.order = order;
		updateView();
	}

	void updateView() {
		if (this.symbol == null || this.order == null) {
			return;
		}
		this.symbol.setText(order.getSymbol());
		this.status.setText(order.getSymbol());

		if (order.getSide().equals(BinanceOrderSide.BUY)) {
			this.entry.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_green_arrow_down));
			this.side.setText(getContext().getString(R.string.buy));
			this.side.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
		}
		else if (order.getSide().equals(BinanceOrderSide.SELL)) {
			this.entry.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_red_arrow_up));
			this.side.setText(getContext().getString(R.string.sell));
			this.side.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
		}

		if (order.isClosePosition()) {
			this.quantity.setText(getString(R.string.close_position));
		}
		else {
			this.quantity.setText(StringFormatUtil.formatAmount(order.getQuantity()));
		}
		this.amount.setText(StringFormatUtil.formatAmount(order.getQuantity()));
		this.executed.setText(StringFormatUtil.formatAmount(order.getQuantityFilled()));
		this.date.setText(DateTimeUtil.formatShortDateTime(order.getCreateTime()));

		this.type.setText(order.getType().toString());
		this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getPrice()));
		if (order.getStopPrice() != null && order.getStopPrice() != 0) {
			this.stopGroup.setVisibility(View.VISIBLE);
			this.stop.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getStopPrice()));
		}
		else {
			this.stopGroup.setVisibility(View.GONE);
		}

		int progress = (int) (order.getQuantityFilled() / order.getQuantity() * 100);
		this.filledPercent.setText(StringFormatUtil.getPercentString((double) progress));
		this.filledProgress.setProgress(progress);

		if (order.getCommission() != null && order.getCommissionAsset() != null) {
			this.fee.setText(StringFormatUtil.getValueString(order.getCommission(), order.getCommissionAsset()));
		}
		else {
			this.fee.setText("-");
		}

		setStatus();
	}

	private void setStatus() {
		int textResId = 0;
		int colorResId = 0;
		switch (order.getStatus()) {
			case NEW:
				textResId = R.string.status_new;
				colorResId = R.attr.colorTextPrimary;
				break;
			case PARTIALLYFILLED:
				textResId = R.string.status_partially_filled;
				colorResId = R.attr.colorPending;
				break;
			case FILLED:
				textResId = R.string.status_filled;
				colorResId = R.attr.colorGreen;
				break;
			case CANCELED:
				textResId = R.string.status_canceled;
				colorResId = R.attr.colorRed;
				break;
			case PENDINGCANCEL:
				textResId = R.string.status_pending_cancel;
				colorResId = R.attr.colorRed;
				break;
			case REJECTED:
				textResId = R.string.status_rejected;
				colorResId = R.attr.colorRed;
				break;
			case EXPIRED:
				textResId = R.string.status_expired;
				colorResId = R.attr.colorRed;
				break;
			case INSURANCE:
				textResId = R.string.status_insurance;
				colorResId = R.attr.colorTextPrimary;
				break;
			case ADL:
				textResId = R.string.status_adl;
				colorResId = R.attr.colorTextPrimary;
				break;
		}

		this.status.setText(getContext().getString(textResId));
		this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), colorResId));
	}
}