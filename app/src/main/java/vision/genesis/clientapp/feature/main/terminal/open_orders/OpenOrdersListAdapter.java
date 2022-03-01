package vision.genesis.clientapp.feature.main.terminal.open_orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.BinanceOrderSide;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnOrderCloseClickedEvent;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class OpenOrdersListAdapter extends RecyclerView.Adapter<OpenOrdersListAdapter.OrderViewHolder>
{
	public interface OnItemClickListener
	{
		void onClicked(BinanceOrder order);
	}

	public List<BinanceOrder> orders = new ArrayList<>();

	private OnItemClickListener listener;

	public OpenOrdersListAdapter(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
		return new OrderViewHolder(itemView, listener);
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

		@BindView(R.id.filled_progress)
		public ProgressBar filledProgress;

		@BindView(R.id.filled_percent)
		public TextView filledPercent;

		private BinanceOrder order;

		OrderViewHolder(View itemView, OnItemClickListener listener) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(view -> {
				if (order != null && listener != null) {
					listener.onClicked(order);
				}
			});
		}

		@OnClick(R.id.button_close)
		public void onCloseClicked() {
			if (order != null) {
				EventBus.getDefault().post(new OnOrderCloseClickedEvent(order));
			}
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

			if (order.isClosePosition()) {
				this.quantity.setText(itemView.getResources().getString(R.string.close_position));
			}
			else {
				this.quantity.setText(StringFormatUtil.formatAmount(order.getQuantity()));
			}
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

			int progress = (int) (order.getQuantityFilled() / order.getQuantity() * 100);
			this.filledPercent.setText(StringFormatUtil.getPercentString((double) progress));
			this.filledProgress.setProgress(progress);
		}
	}
}
