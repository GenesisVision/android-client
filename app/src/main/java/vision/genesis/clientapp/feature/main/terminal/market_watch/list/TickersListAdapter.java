package vision.genesis.clientapp.feature.main.terminal.market_watch.list;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TickersListAdapter extends RecyclerView.Adapter<TickersListAdapter.TickerViewHolder>
{
	public List<MarketWatchTickerModel> tickers = new ArrayList<>();

	@NonNull
	@Override
	public TickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ticker, parent, false);
		return new TickerViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TickerViewHolder holder, int position) {
		holder.setTicker(tickers.get(position));
	}

	@Override
	public int getItemCount() {
		return tickers.size();
	}

	public void setTickers(List<MarketWatchTickerModel> tickers) {
		this.tickers.clear();
		this.tickers.addAll(tickers);
		notifyDataSetChanged();
	}

	static class TickerViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.base_asset)
		public TextView baseAsset;

		@BindView(R.id.quote_asset)
		public TextView quoteAsset;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.change)
		public TextView change;

		@BindView(R.id.background_change)
		public View changeBackground;

		private MarketWatchTickerModel ticker;

		private Drawable backgroundProfitWhite;

		private Drawable backgroundProfitGreen;

		private Drawable backgroundProfitRed;

		TickerViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			backgroundProfitWhite = AppCompatResources.getDrawable(itemView.getContext(), R.drawable.background_profit_white);
			backgroundProfitGreen = AppCompatResources.getDrawable(itemView.getContext(), R.drawable.background_profit_green);
			backgroundProfitRed = AppCompatResources.getDrawable(itemView.getContext(), R.drawable.background_profit_red);

			itemView.setOnClickListener(view -> {
				if (ticker != null) {
//					EventBus.getDefault().post(new OnTickerClickedEvent(ticker));
				}
			});
		}

		void setTicker(MarketWatchTickerModel ticker) {
			this.ticker = ticker;

			baseAsset.setText(ticker.getBaseAsset());
			quoteAsset.setText(String.format("/%s", ticker.getQuoteAsset()));

			if (ticker.getVolume() != null) {
				volume.setText(StringFormatUtil.formatAmount(ticker.getVolume(), 2, 8));
//				volume.setText(String.format("%s %s", itemView.getContext().getString(R.string.volume),
//						StringFormatUtil.formatAmount(ticker.getVolume(), 2, 8)));
			}
			else {
				volume.setText(String.format("%s -", itemView.getContext().getString(R.string.volume)));
			}

			if (ticker.getChange() != null) {
				change.setText(String.format("%s%s", ticker.getChange() > 0 ? "+" : "", StringFormatUtil.getPercentString(ticker.getChange())));
				int colorAttrId = R.attr.colorTextPrimary;
				Drawable backgroundDrawable = backgroundProfitWhite;
				if (ticker.getChange() > 0) {
					colorAttrId = R.attr.colorGreen;
					backgroundDrawable = backgroundProfitGreen;
				}
				else if (ticker.getChange() < 0) {
					colorAttrId = R.attr.colorRed;
					backgroundDrawable = backgroundProfitRed;

				}
				this.change.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), colorAttrId));
				this.changeBackground.setBackground(backgroundDrawable);
			}
			else {
				this.change.setText("-");
				this.change.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorTextPrimary));
				this.changeBackground.setBackground(backgroundProfitWhite);
			}

			if (ticker.getLastPrice() != null) {
				price.setText(StringFormatUtil.formatAmount(ticker.getLastPrice()));

				int colorAttrId = R.attr.colorTextPrimary;
				if (ticker.getPreviousPrice() != null) {
					if (ticker.getLastPrice() > ticker.getPreviousPrice()) {
						colorAttrId = R.attr.colorGreen;
					}
					else if (ticker.getLastPrice() < ticker.getPreviousPrice()) {
						colorAttrId = R.attr.colorRed;
					}
				}
				this.price.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), colorAttrId));
			}
			else {
				this.price.setText("-");
				this.price.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorTextPrimary));
			}
		}
	}
}
