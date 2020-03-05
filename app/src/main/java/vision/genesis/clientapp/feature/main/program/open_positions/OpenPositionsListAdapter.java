package vision.genesis.clientapp.feature.main.program.open_positions;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnOpenPositionClickedEvent;
import vision.genesis.clientapp.utils.ImageUtils;
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

	private TradesViewModel model;

	@NonNull
	@Override
	public TradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_open_position, parent, false);
		return new TradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TradeViewHolder holder, int position) {
		holder.setTrade(openPositions.get(position), model);
	}

	@Override
	public int getItemCount() {
		return openPositions.size();
	}

	public void setOpenPositions(TradesViewModel model) {
		this.model = model;
		this.openPositions.clear();
		this.openPositions.addAll(model.getItems());
		notifyDataSetChanged();
	}

	static class TradeViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.entry)
		public ImageView entry;

		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.direction)
		public TextView direction;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.date)
		public TextView date;

		private OrderModel trade;

		private TradesViewModel model;

		TradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(view -> {
				if (trade != null) {
					EventBus.getDefault().post(new OnOpenPositionClickedEvent(trade, model));
				}
			});

			setFonts();
		}

		private void setFonts() {
			direction.setTypeface(TypefaceUtil.semibold());
			volume.setTypeface(TypefaceUtil.semibold());
			price.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());
		}

		void setTrade(OrderModel trade, TradesViewModel model) {
			this.trade = trade;
			this.model = model;

			symbol.setText(trade.getSymbol());
			volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 2, 8));

			if (trade.getAssetData() != null) {
				icon.setImageURI(ImageUtils.getImageUri(trade.getAssetData().getIcon()));
			}
			else {
				icon.setImageURI(ImageUtils.getImageUri(null));
			}

			if (model.isShowDirection()) {
				this.direction.setVisibility(View.VISIBLE);
				this.entry.setVisibility(View.VISIBLE);

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

				direction.setText(trade.getDirection().getValue());
			}
			else {
				this.entry.setVisibility(View.GONE);
				this.direction.setVisibility(View.GONE);
			}

			if (model.isShowPrice()) {
				this.price.setVisibility(View.VISIBLE);
				price.setText(StringFormatUtil.formatAmountWithoutGrouping(trade.getPrice()));
			}
			else {
				this.price.setVisibility(View.GONE);
			}

			if (model.isShowProfit()) {
				this.profit.setVisibility(View.VISIBLE);
				setProfit(trade);
			}
			else {
				this.profit.setVisibility(View.GONE);
			}
		}

		private void setProfit(OrderModel trade) {
			double profit = trade.getProfit();
			this.profit.setText(StringFormatUtil.formatAmountWithoutGrouping(profit));
			this.profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					profit >= 0 ? R.attr.colorGreen : R.attr.colorRed));
		}
	}
}
