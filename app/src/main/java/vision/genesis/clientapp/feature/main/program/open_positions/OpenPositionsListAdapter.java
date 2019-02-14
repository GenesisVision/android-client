package vision.genesis.clientapp.feature.main.program.open_positions;

import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnOpenPositionClickedEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/02/2019.
 */

public class OpenPositionsListAdapter extends RecyclerView.Adapter<OpenPositionsListAdapter.TradeViewHolder>
{
	private List<OrderModel> openPositions = new ArrayList<>();

	@NonNull
	@Override
	public TradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_open_position, parent, false);
		return new TradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TradeViewHolder holder, int position) {
		holder.setTrade(openPositions.get(position));
	}

	@Override
	public int getItemCount() {
		return openPositions.size();
	}

	void setOpenPositions(List<OrderModel> trades) {
		this.openPositions.clear();
		this.openPositions.addAll(trades);
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

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.balance)
		public TextView balance;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.date)
		public TextView date;

		private OrderModel trade;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(view -> {
				if (trade != null)
					EventBus.getDefault().post(new OnOpenPositionClickedEvent(trade));
			});

			setFonts();
		}

		private void setFonts() {
			direction.setTypeface(TypefaceUtil.semibold());
			volume.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());
		}

		void setTrade(OrderModel trade) {
			this.trade = trade;
			int dirResId = R.drawable.icon_red_arrow_up;
			switch (trade.getDirection()) {
				case BUY:
					dirResId = R.drawable.icon_arrow_green_down;
					break;
				case SELL:
					dirResId = R.drawable.icon_arrow_red_up;
					break;
				default:
					break;
			}

			entry.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, dirResId));

			symbol.setText(trade.getSymbol());
			direction.setText(trade.getDirection().getValue());
			volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 2, 8));
			balance.setText(StringFormatUtil.formatAmountWithoutGrouping(trade.getPrice()));
//			balance.setText("120.2301");
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
