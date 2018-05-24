package vision.genesis.clientapp.feature.main.program.trades;

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
import io.swagger.client.model.TradesViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

public class TradesListAdapter extends RecyclerView.Adapter<TradesListAdapter.TradeViewHolder>
{
	public List<OrderModel> trades = new ArrayList<>();

	private TradesViewModel.TradeServerTypeEnum tradeServerType = TradesViewModel.TradeServerTypeEnum.METATRADER5;

	@NonNull
	@Override
	public TradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trade, parent, false);
		return new TradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TradeViewHolder holder, int position) {
		holder.setTrade(trades.get(position), tradeServerType);
	}

	@Override
	public int getItemCount() {
		return trades.size();
	}

	void setTrades(List<OrderModel> trades, TradesViewModel.TradeServerTypeEnum tradeServerType) {
		this.tradeServerType = tradeServerType;
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
		@BindView(R.id.date_open)
		public TextView dateOpen;

		@BindView(R.id.date_close)
		public TextView dateClose;

		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.price_open)
		public TextView priceOpen;

		@BindView(R.id.price_close)
		public TextView priceClose;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.direction)
		public TextView direction;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
		}

		void setTrade(OrderModel trade, TradesViewModel.TradeServerTypeEnum tradeServerType) {
			switch (tradeServerType) {
				case METATRADER4:
					dateClose.setVisibility(View.VISIBLE);
					priceClose.setVisibility(View.VISIBLE);

					dateOpen.setText(DateTimeUtil.formatDateTime(trade.getDateOpen()));
					dateClose.setText(DateTimeUtil.formatDateTime(trade.getDateClose()));

					priceOpen.setText(StringFormatUtil.formatAmountWithoutGrouping(trade.getPriceOpen()));
					priceClose.setText(StringFormatUtil.formatAmountWithoutGrouping(trade.getPriceClose()));

					direction.setText(trade.getDirection().toString());
					break;
				default:
					dateClose.setVisibility(View.GONE);
					priceClose.setVisibility(View.GONE);

					dateOpen.setText(DateTimeUtil.formatDateTime(trade.getDate()));
					priceOpen.setText(StringFormatUtil.formatAmountWithoutGrouping(trade.getPrice()));

					direction.setText(String.format(Locale.getDefault(), "%s %s", trade.getDirection(), trade.getEntry()));
					break;
			}

			symbol.setText(trade.getSymbol());
			volume.setText(String.valueOf(trade.getVolume()));
			setProfit(trade);
		}

		private void setProfit(OrderModel trade) {
			double profit = trade.getProfit();
			this.profit.setText(StringFormatUtil.formatAmountWithoutGrouping(profit));
			this.profit.setTextColor(profit >= 0
					? ContextCompat.getColor(itemView.getContext(), R.color.transactionGreen)
					: ContextCompat.getColor(itemView.getContext(), R.color.transactionRed));
		}
	}
}
