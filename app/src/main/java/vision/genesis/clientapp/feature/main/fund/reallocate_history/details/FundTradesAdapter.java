package vision.genesis.clientapp.feature.main.fund.reallocate_history.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundTradingEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */

public class FundTradesAdapter extends RecyclerView.Adapter<FundTradesAdapter.TradeViewHolder>
{
	public List<FundTradingEventViewModel> trades = new ArrayList<>();

	@Override
	public TradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund_trade, parent, false);
		return new TradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(TradeViewHolder holder, int position) {
		holder.setTrade(trades.get(position));
	}

	@Override
	public int getItemCount() {
		return trades.size();
	}

	public void setTrades(List<FundTradingEventViewModel> trades) {
		this.trades.clear();
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	static class TradeViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.sold_amount)
		public TextView soldAmount;

		@BindView(R.id.sold_icon)
		public SimpleDraweeView soldIcon;

		@BindView(R.id.sold_asset)
		public TextView soldAsset;

		@BindView(R.id.bought_amount)
		public TextView boughtAmount;

		@BindView(R.id.bought_icon)
		public SimpleDraweeView boughtIcon;

		@BindView(R.id.bought_asset)
		public TextView boughtAsset;

		@BindView(R.id.commission)
		public TextView commission;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setTrade(FundTradingEventViewModel trade) {
			this.date.setText(DateTimeUtil.formatEventDateTime(trade.getDate()));

			this.soldAmount.setText(StringFormatUtil.getValueString(trade.getSoldAmount(), trade.getSoldAsset().getAsset()));
			this.soldIcon.setImageURI(ImageUtils.getImageUri(trade.getSoldAsset().getLogoUrl()));
//			this.soldAsset.setText(trade.getSoldAsset().getAsset());

			this.boughtAmount.setText(StringFormatUtil.getValueString(trade.getBoughtAmount(), trade.getBoughtAsset().getAsset()));
			this.boughtIcon.setImageURI(ImageUtils.getImageUri(trade.getBoughtAsset().getLogoUrl()));
//			this.boughtAsset.setText(trade.getBoughtAsset().getAsset());

			this.commission.setText(StringFormatUtil.getValueString(trade.getCommission(), trade.getCommissionCurrency()));
		}
	}
}