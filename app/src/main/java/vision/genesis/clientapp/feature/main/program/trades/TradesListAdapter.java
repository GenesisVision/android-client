package vision.genesis.clientapp.feature.main.program.trades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class TradesListAdapter extends RecyclerView.Adapter<TradesListAdapter.TradeViewHolder>
{
	public List<OrderModel> trades = new ArrayList<>();

	@NonNull
	@Override
	public TradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trade, parent, false);
		return new TradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TradeViewHolder holder, int position) {
		holder.setTrade(trades.get(position));
	}

	@Override
	public int getItemCount() {
		return trades.size();
	}

	void setTrades(List<OrderModel> trades) {
		this.trades.clear();
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	void addTrades(List<OrderModel> trades) {
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	static class TradeViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.direction)
		public TextView direction;

		private OrderModel trade;

		private Context context;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			context = itemView.getContext();

			setFonts();
		}

		private void setFonts() {
//			type.setTypeface(TypefaceUtil.bold(context));
//			amount.setTypeface(TypefaceUtil.bold(context));
		}

		void setTrade(OrderModel transaction) {
			this.trade = transaction;
			updateData();
		}

		private void updateData() {
			date.setText(DateTimeUtil.formatDateTime(trade.getDate()));
			symbol.setText(trade.getSymbol());
			price.setText(String.valueOf(trade.getPrice()));
			volume.setText(String.valueOf(trade.getVolume()));
			setProfit();
			setDirection();
		}

		private void setProfit() {
			double profit = trade.getProfit();
			this.profit.setText(String.valueOf(profit));
			this.profit.setTextColor(profit >= 0
					? ContextCompat.getColor(context, R.color.transactionGreen)
					: ContextCompat.getColor(context, R.color.transactionRed));
		}

		private void setDirection() {
			direction.setText(String.format(Locale.getDefault(), "%s %s", trade.getDirection(), trade.getEntry()));
		}
	}
}
