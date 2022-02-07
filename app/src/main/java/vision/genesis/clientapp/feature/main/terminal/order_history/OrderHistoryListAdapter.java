package vision.genesis.clientapp.feature.main.terminal.order_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.terminal.open_orders.OpenOrdersListAdapter;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class OrderHistoryListAdapter extends RecyclerView.Adapter<OrderHistoryListAdapter.OrderViewHolder>
{
	public interface OnItemClickListener
	{
		void onClicked(BinanceOrder order);
	}

	public List<BinanceOrder> orders = new ArrayList<>();

	private TradingPlatformBinanceOrdersMode mode;

	private OpenOrdersListAdapter.OnItemClickListener listener;

	public OrderHistoryListAdapter(TradingPlatformBinanceOrdersMode mode, OpenOrdersListAdapter.OnItemClickListener listener) {
		this.mode = mode;
		this.listener = listener;
	}

	@NonNull
	@Override
	public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
		return new OrderViewHolder(itemView, mode, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
		holder.setOrder(orders.get(position));
	}

	@Override
	public int getItemCount() {
		return orders.size();
	}

	public void setOrders(List<BinanceOrder> orders) {
		this.orders.clear();
		this.orders.addAll(orders);
		notifyDataSetChanged();
	}

	public void addOrders(List<BinanceOrder> orders) {
		this.orders.addAll(orders);
		notifyDataSetChanged();
	}

	static class OrderViewHolder extends RecyclerView.ViewHolder
	{
		private final TradingPlatformBinanceOrdersMode mode;

		@BindView(R.id.entry)
		public ImageView entry;

		@BindView(R.id.symbol)
		public TextView symbol;

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

		@BindView(R.id.trigger)
		public TextView trigger;

		@BindView(R.id.group_filled)
		public ViewGroup filledGroup;

		@BindView(R.id.filled_progress)
		public ProgressBar filledProgress;

		@BindView(R.id.filled_percent)
		public TextView filledPercent;

		@BindView(R.id.status)
		public TextView status;

		@BindView(R.id.fee)
		public TextView fee;

		@BindView(R.id.button_close)
		public View buttonClose;

		private BinanceOrder order;

		OrderViewHolder(View itemView, TradingPlatformBinanceOrdersMode mode, OpenOrdersListAdapter.OnItemClickListener listener) {
			super(itemView);

			this.mode = mode;

			ButterKnife.bind(this, itemView);

			this.buttonClose.setVisibility(View.GONE);
			this.status.setVisibility(View.VISIBLE);

			if (mode.equals(TradingPlatformBinanceOrdersMode.TRADEHISTORY)) {
				this.filledGroup.setVisibility(View.GONE);
				this.status.setVisibility(View.GONE);
				this.fee.setVisibility(View.VISIBLE);
			}

			itemView.setOnClickListener(view -> {
				if (order != null && listener != null) {
					listener.onClicked(order);
				}
			});
		}

		void setOrder(BinanceOrder order) {
			this.order = order;

			this.symbol.setText(order.getSymbol());

			if (order.getSide().equals(BinanceOrderSide.BUY)) {
				this.entry.setImageDrawable(AppCompatResources.getDrawable(itemView.getContext(), R.drawable.icon_green_arrow_down));
				this.side.setText(itemView.getContext().getString(R.string.buy));
				this.side.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen));
			}
			else if (order.getSide().equals(BinanceOrderSide.SELL)) {
				this.entry.setImageDrawable(AppCompatResources.getDrawable(itemView.getContext(), R.drawable.icon_red_arrow_up));
				this.side.setText(itemView.getContext().getString(R.string.sell));
				this.side.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));
			}

			this.quantity.setText(StringFormatUtil.formatAmount(order.getQuantity()));
			this.date.setText(DateTimeUtil.formatShortDateTime(order.getCreateTime()));

			this.type.setText(order.getType().toString());
			this.price.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getPrice()));
			if (order.getStopPrice() != null && order.getStopPrice() != 0) {
				this.trigger.setVisibility(View.VISIBLE);
				this.trigger.setText(StringFormatUtil.formatAmountWithoutGrouping(order.getStopPrice()));
			}
			else {
				this.trigger.setVisibility(View.GONE);
			}

			if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
				int progress = (int) (order.getQuantityFilled() / order.getQuantity() * 100);
				this.filledPercent.setText(StringFormatUtil.getPercentString((double) progress));
				this.filledProgress.setProgress(progress);
				setStatus();
			}
			else {
				this.fee.setText(String.format("%s %s",
						itemView.getContext().getString(R.string.fee),
						StringFormatUtil.getValueString(order.getCommission(), order.getCommissionAsset())));
			}
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

			this.status.setText(itemView.getContext().getString(textResId));
			this.status.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), colorResId));
		}
	}
}
