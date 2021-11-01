package vision.genesis.clientapp.feature.main.dashboard.investments.coins.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.CoinsHistoryEvent;
import io.swagger.client.model.CoinsTrade;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

public class CoinsHistoryAdapter extends RecyclerView.Adapter<CoinsHistoryAdapter.CoinsHistoryViewHolder>
{
	private List<CoinsHistoryEvent> history = new ArrayList<>();

	@NonNull
	@Override
	public CoinsHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_coins_history, parent, false);
		return new CoinsHistoryViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull CoinsHistoryViewHolder holder, int position) {
		holder.setTrade(history.get(position));
	}

	@Override
	public int getItemCount() {
		return history.size();
	}

	void setHistory(List<CoinsHistoryEvent> history) {
		this.history.clear();
		this.history.addAll(history);
		notifyDataSetChanged();
	}

	void addHistory(List<CoinsHistoryEvent> history) {
		this.history.addAll(history);
		notifyDataSetChanged();
	}

	static class CoinsHistoryViewHolder extends RecyclerView.ViewHolder
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

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.commission)
		public TextView commission;

		CoinsHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setTrade(CoinsHistoryEvent event) {
			this.date.setText(DateTimeUtil.formatEventDateTime(event.getDate()));

			CoinsTrade trade = event.getTrade();

			this.soldAmount.setText(StringFormatUtil.getValueString(trade.getSoldAmount(), trade.getSoldAsset().getAsset()));
			this.soldIcon.setImageURI(ImageUtils.getImageUri(trade.getSoldAsset().getLogoUrl()));

			this.boughtAmount.setText(StringFormatUtil.getValueString(trade.getBoughtAmount(), trade.getBoughtAsset().getAsset()));
			this.boughtIcon.setImageURI(ImageUtils.getImageUri(trade.getBoughtAsset().getLogoUrl()));

			this.price.setText(String.format(Locale.getDefault(), "$ %s", StringFormatUtil.formatAmount(trade.getPrice(), 0, 8)));
			this.commission.setText(StringFormatUtil.getValueString(trade.getCommission(), trade.getCommissionCurrency()));
		}
	}
}
