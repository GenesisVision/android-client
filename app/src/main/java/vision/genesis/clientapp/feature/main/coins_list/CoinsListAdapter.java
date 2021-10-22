package vision.genesis.clientapp.feature.main.coins_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.Currency;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnListCoinFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.ShowCoinDetailsEvent;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/10/2021.
 */

public class CoinsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private List<CoinsAsset> coins = new ArrayList<>();

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new CoinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_coin, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		((CoinViewHolder) holder).setData(coins.get(position));
	}

	@Override
	public long getItemId(int position) {
		return coins.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return coins.size();
	}

	public void setCoins(List<CoinsAsset> coins) {
		this.coins.clear();
		this.coins.addAll(coins);
		notifyDataSetChanged();
	}

	public void addFunds(List<CoinsAsset> coins) {
		this.coins.addAll(coins);
		notifyDataSetChanged();
	}

	public void setCoinFavorite(UUID coinId, Boolean favorite) {
		for (CoinsAsset coin : coins) {
			if (coin.getId().equals(coinId)) {
				if (!coin.isIsFavorite().equals(favorite)) {
					coin.setIsFavorite(favorite);
					notifyItemChanged(coins.indexOf(coin));
				}
				break;
			}
		}
	}

	static class CoinViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.root)
		public ViewGroup root;

		@BindView(R.id.logo)
		public SimpleDraweeView logo;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.short_name)
		public TextView shortName;

		@BindView(R.id.favorite)
		public ImageView favorite;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.change)
		public TextView change;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.market_cap)
		public TextView marketCap;

		@BindView(R.id.volume)
		public TextView volume;

		private CoinsAsset coin;

		CoinViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> {
				if (coin != null) {
					EventBus.getDefault().post(new ShowCoinDetailsEvent(coin.getAsset()));
				}
			});
		}

		@OnClick(R.id.favorite)
		public void onFavoriteClicked() {
			if (coin != null) {
				coin.setIsFavorite(!coin.isIsFavorite());
				updateData();
				EventBus.getDefault().post(new OnListCoinFavoriteClickedEvent(coin.getId(), coin.isIsFavorite()));
			}
		}

		void setData(CoinsAsset coin) {
			this.coin = coin;
			updateData();
		}

		private void updateData() {
			logo.setImageURI(coin.getLogoUrl());

			favorite.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, coin.isIsFavorite()
					? R.drawable.icon_favorite_fill
					: R.drawable.icon_favorite));
			favorite.setAlpha(coin.isIsFavorite() ? 1f : 0.3f);
//			favorite.setVisibility(coin.getPersonalDetails() != null ? View.VISIBLE : View.INVISIBLE);

			this.name.setText(coin.getName());
			this.shortName.setText(coin.getAsset());

			this.chart.setChart(coin.getChart().getChart());

			this.price.setText(StringFormatUtil.getValueString(coin.getPrice(), Currency.USD.getValue()));
			this.change.setText(StringFormatUtil.getPercentString(coin.getChange24Percent()));

			setMillionValue(coin.getMarketCap(), marketCap);
			setMillionValue(coin.getTotalVolume(), volume);

			int changeColorResId = R.attr.colorTextPrimary;
			if (coin.getChange24Percent() > 0) {
				changeColorResId = R.attr.colorGreen;
			}
			else if (coin.getChange24Percent() < 0) {
				changeColorResId = R.attr.colorRed;
			}
			this.change.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), changeColorResId));
		}

		private void setMillionValue(Double value, TextView textView) {
			if (value > 1000000) {
				textView.setText("$ ".concat(StringFormatUtil.getShortenedAmount(value).toString()));
			}
			else {
				textView.setText(StringFormatUtil.getValueString(value, Currency.USD.getValue()));
			}
		}
	}
}
