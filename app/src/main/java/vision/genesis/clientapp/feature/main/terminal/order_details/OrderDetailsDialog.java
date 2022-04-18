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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.TradingAccountPermission;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
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

	@BindView(R.id.label_price)
	public TextView priceLabel;

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

	@BindView(R.id.group_fee)
	public ViewGroup feeGroup;

	@BindView(R.id.fee)
	public TextView fee;

	@BindView(R.id.group_reduce_only)
	public ViewGroup reduceOnlyGroup;

	@BindView(R.id.reduce_only)
	public TextView reduceOnly;

	@BindView(R.id.group_trigger_conditions)
	public ViewGroup triggerConditionsGroup;

	@BindView(R.id.trigger_conditions)
	public TextView triggerConditions;

	@BindView(R.id.group_realized_profit)
	public ViewGroup realizedProfitGroup;

	@BindView(R.id.realized_profit)
	public TextView realizedProfit;

	@BindView(R.id.filled_progress)
	public ProgressBar filledProgress;

	@BindView(R.id.filled_percent)
	public TextView filledPercent;

	private BinanceOrder order;

	private TradingPlatformBinanceOrdersMode mode;

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

	public void setData(BinanceOrder order, TradingPlatformBinanceOrdersMode mode) {
		this.order = order;
		this.mode = mode;
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

		int progress = (int) (order.getQuantityFilled() / order.getQuantity() * 100);
		this.filledPercent.setText(StringFormatUtil.getPercentString((double) progress));
		this.filledProgress.setProgress(progress);


		if (order.isClosePosition()) {
			this.quantity.setText(getString(R.string.close_position));
			this.amount.setText(getString(R.string.close_position));
		}
		else {
			this.quantity.setText(StringFormatUtil.formatAmount(order.getQuantity()));
			this.amount.setText(StringFormatUtil.formatAmount(order.getQuantity()));
		}
		this.executed.setText(StringFormatUtil.formatAmount(order.getQuantityFilled()));
		this.date.setText(DateTimeUtil.formatShortDateTime(order.getCreateTime()));

		this.type.setText(order.getType().toString());
		if (order.getMarket().equals(TradingAccountPermission.FUTURES)) {
			if (mode == TradingPlatformBinanceOrdersMode.ORDERHISTORY) {
				this.priceLabel.setText(getString(R.string.average));
				this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getAvgPrice()));
			}
			else {
				this.priceLabel.setText(getString(R.string.price));
				this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getPrice()));
			}

			if (mode != TradingPlatformBinanceOrdersMode.TRADEHISTORY) {
				this.reduceOnlyGroup.setVisibility(View.VISIBLE);
				this.reduceOnly.setText(getString(order.getReduceOnly() ? R.string.yes : R.string.no));
			}
			else {
				if (order.getRealizedProfit() != null) {
					this.realizedProfitGroup.setVisibility(View.VISIBLE);
					this.realizedProfit.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getRealizedProfit()));
				}

				this.priceLabel.setText(getString(R.string.price));
				this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getAvgPrice()));
			}
		}
		else {
			this.priceLabel.setText(getString(R.string.price));
			this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getPrice()));

			this.reduceOnlyGroup.setVisibility(View.GONE);
		}
		if (order.getStopPrice() != null && order.getStopPrice() != 0) {
			this.stopGroup.setVisibility(View.VISIBLE);
			this.stop.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getStopPrice()));
		}
		else {
			this.stopGroup.setVisibility(View.GONE);
		}

		if (mode == TradingPlatformBinanceOrdersMode.TRADEHISTORY) {
			this.feeGroup.setVisibility(View.VISIBLE);
			if (order.getCommission() != null && order.getCommissionAsset() != null) {
				this.fee.setText(StringFormatUtil.getValueString(order.getCommission(), order.getCommissionAsset()));
			}
			else {
				this.fee.setText("-");
			}
		}
		else {
			this.triggerConditionsGroup.setVisibility(View.VISIBLE);
			this.triggerConditions.setText(order.getWorkingType() != null && order.getStopPrice() > 0
					? String.format(Locale.getDefault(), "%s >= %s",
					StringFormatUtil.getWorkingTypeLabel(order.getWorkingType()), StringFormatUtil.formatAmount(order.getStopPrice()))
					: "-");
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