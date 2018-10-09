package vision.genesis.clientapp.feature.main.program.trades;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
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
		@BindView(R.id.entry)
		public ImageView entry;

		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.direction)
		public TextView direction;

		@BindView(R.id.balance)
		public TextView balance;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.date)
		public TextView date;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			direction.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());
		}

		void setTrade(OrderModel trade) {
			int entryResId = R.drawable.icon_red_arrow_up;
			switch (trade.getEntry()) {
				case IN:
					entryResId = R.drawable.icon_arrow_green_down;
					break;
				case OUT:
					entryResId = R.drawable.icon_arrow_red_up;
					break;
				default:
					break;
			}

			entry.setImageDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, entryResId));

			symbol.setText(trade.getSymbol());
			direction.setText(trade.getDirection().getValue());
//			balance.setText(trade.getBalance());
			balance.setText("120.2301");
//			time.setText(DateTimeUtil.formatShortDateTime(trade.getDate()));

			setProfit(trade);
		}

		private void setProfit(OrderModel trade) {
			double profit = trade.getProfit();
			this.profit.setText(StringFormatUtil.formatAmountWithoutGrouping(profit));
			this.profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					profit >= 0 ? R.attr.colorGreen : R.attr.colorRed));
		}
	}
}
