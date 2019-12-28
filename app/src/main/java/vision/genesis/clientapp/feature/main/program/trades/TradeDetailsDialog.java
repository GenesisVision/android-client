package vision.genesis.clientapp.feature.main.program.trades;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */

public class TradeDetailsDialog extends BottomSheetDialogFragment
{
	@BindView(R.id.icon_direction)
	public ImageView iconDirection;

	@BindView(R.id.direction)
	public TextView direction;

	@BindView(R.id.entry)
	public TextView entry;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.symbol)
	public TextView symbol;

	@BindView(R.id.volume)
	public TextView volume;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.commission)
	public TextView commission;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.swap)
	public TextView swap;

	@BindView(R.id.ticket)
	public TextView ticket;

	@BindView(R.id.group_swap)
	public ViewGroup swapGroup;

	@BindView(R.id.group_ticket)
	public ViewGroup ticketGroup;

	private OrderSignalModel trade;

	private Boolean showSwaps = false;

	private Boolean showTickets = false;

	@OnClick(R.id.group_commission)
	public void onCommissionClicked() {
		if (trade != null && trade.isShowOriginalCommission()) {
			Toast.makeText(getContext(),
					StringFormatUtil.getValueString(trade.getOriginalCommission(), trade.getOriginalCommissionCurrency()),
					Toast.LENGTH_SHORT)
					.show();
		}
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_trade, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

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

	public void setData(OrderSignalModel trade, Boolean showSwaps, Boolean showTickets) {
		this.trade = trade;
		this.showSwaps = showSwaps;
		this.showTickets = showTickets;
		updateView();
	}

	private void setFonts() {
		direction.setTypeface(TypefaceUtil.semibold());
		entry.setTypeface(TypefaceUtil.semibold());
		symbol.setTypeface(TypefaceUtil.semibold());
		volume.setTypeface(TypefaceUtil.semibold());
		price.setTypeface(TypefaceUtil.semibold());
		commission.setTypeface(TypefaceUtil.semibold());
		profit.setTypeface(TypefaceUtil.semibold());
		swap.setTypeface(TypefaceUtil.semibold());
		ticket.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (this.iconDirection != null && trade != null) {
			int dirResId = R.drawable.icon_red_arrow_up;
			int dirColor = R.attr.colorGreen;
			switch (trade.getDirection()) {
				case BUY:
					dirResId = R.drawable.icon_arrow_green_down;
					dirColor = R.attr.colorGreen;
					break;
				case SELL:
					dirResId = R.drawable.icon_arrow_red_up;
					dirColor = R.attr.colorRed;
					break;
				default:
					break;
			}

			iconDirection.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, dirResId));
			direction.setText(trade.getDirection().getValue());
			direction.setTextColor(ThemeUtil.getColorByAttrId(getDialog().getContext(), dirColor));
			entry.setText(String.format(Locale.getDefault(), "/ %s", trade.getEntry().getValue()));

			date.setText(DateTimeUtil.formatEventDateTime(trade.getDate()));
			symbol.setText(trade.getSymbol());

			volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 2, 8));
			price.setText(StringFormatUtil.formatAmount(trade.getPrice(), 2, 8));

			commission.setText(StringFormatUtil.formatAmount(trade.getCommission(), 2, 8));
			setProfit(trade);

			if (trade.getSwap() != null) {
				swap.setText(StringFormatUtil.formatAmount(trade.getSwap(), 2, 8));
			}
			ticket.setText(trade.getTicket());

			swapGroup.setVisibility(showSwaps ? View.VISIBLE : View.GONE);
			ticketGroup.setVisibility(showTickets ? View.VISIBLE : View.GONE);
		}
	}

	private void setProfit(OrderSignalModel trade) {
		double profit = trade.getProfit();
		this.profit.setText(StringFormatUtil.formatAmountWithoutGrouping(profit));
		this.profit.setTextColor(ThemeUtil.getColorByAttrId(getDialog().getContext(),
				profit >= 0 ? R.attr.colorGreen : R.attr.colorRed));
	}
}
