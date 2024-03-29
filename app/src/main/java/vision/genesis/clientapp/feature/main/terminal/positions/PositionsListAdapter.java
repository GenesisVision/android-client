package vision.genesis.clientapp.feature.main.terminal.positions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.Currency;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.PositionsHelper;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class PositionsListAdapter extends RecyclerView.Adapter<PositionsListAdapter.PositionViewHolder>
{
	public interface OnItemClickListener
	{
		void onAddMarginClicked(BinanceRawFuturesPosition position);

		void onTpSlClicked(BinanceRawFuturesPosition position);

		void onClosePositionClicked(BinanceRawFuturesPosition position);
	}

	public List<BinanceRawFuturesPosition> positions = new ArrayList<>();

	private OnItemClickListener listener;

	private ArrayList<Double> marginRatios = new ArrayList<>();

	public PositionsListAdapter(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_position, parent, false);
		return new PositionViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
		holder.setPosition(positions.get(position),
				positions.get(position).getMarginType().equals(BinanceFuturesMarginType.ISOLATED)
						? marginRatios.get(position)
						: marginRatios.get(0));
	}

	@Override
	public int getItemCount() {
		return positions.size();
	}

	public void setPositions(List<BinanceRawFuturesPosition> positions, ArrayList<Double> marginRatios) {
		this.positions.clear();
		this.positions.addAll(positions);
		this.marginRatios.clear();
		this.marginRatios.addAll(marginRatios);
		notifyDataSetChanged();
	}

	static class PositionViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.size)
		public TextView size;

		@BindView(R.id.pnl)
		public TextView pnl;

		@BindView(R.id.entry_price)
		public TextView entryPrice;

		@BindView(R.id.mark_price)
		public TextView markPrice;

		@BindView(R.id.liq_price)
		public TextView liqPrice;

		@BindView(R.id.margin_ratio)
		public TextView marginRatio;

		@BindView(R.id.margin)
		public TextView margin;

		@BindView(R.id.notional_size)
		public TextView notionalSize;

		@BindView(R.id.button_add_margin)
		public View addMarginButton;

		@BindView(R.id.button_tp_sl)
		public View tpSlButton;

		@BindView(R.id.button_close_position)
		public View closePositionButton;

		private BinanceRawFuturesPosition position;

		private OnItemClickListener listener;

		PositionViewHolder(View itemView, OnItemClickListener listener) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			this.listener = listener;
		}

		@OnClick(R.id.button_add_margin)
		public void onAddMarginClicked() {
			if (position != null && listener != null) {
				listener.onAddMarginClicked(position);
			}
		}

		@OnClick(R.id.button_tp_sl)
		public void onTpSlClicked() {
			if (position != null && listener != null) {
				listener.onTpSlClicked(position);
			}
		}

		@OnClick(R.id.button_close_position)
		public void onClosePositionClicked() {
			if (position != null && listener != null) {
				listener.onClosePositionClicked(position);
			}
		}

		void setPosition(BinanceRawFuturesPosition position, Double marginRatio) {
			this.position = position;

			this.addMarginButton.setVisibility(
					position.getMarginType().equals(BinanceFuturesMarginType.ISOLATED)
							? View.VISIBLE
							: View.GONE);

			Double notionalSize = position.getMarkPrice() * position.getQuantity();

			this.symbol.setText(position.getSymbol());
			this.size.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(position.getQuantity()),
					""));

			double unrealizedPnl = PositionsHelper.calculateUnrealisedPnl(position);
			double roe = PositionsHelper.calculateRoe(position.getQuantity(), unrealizedPnl, position.getLeverage(), position.getMarkPrice());
			this.pnl.setText(String.format(Locale.getDefault(), "%s\n(%s)",
					StringFormatUtil.formatAmount(unrealizedPnl),
					StringFormatUtil.getPercentString(roe * 100)));

			this.pnl.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					unrealizedPnl > 0 ? R.attr.colorGreen :
							unrealizedPnl == 0 ? R.attr.colorTextPrimary
									: R.attr.colorRed));

			this.entryPrice.setText(StringFormatUtil.formatAmount(position.getEntryPrice()));
			this.markPrice.setText(StringFormatUtil.formatAmount(position.getMarkPrice()));
			this.liqPrice.setText(StringFormatUtil.formatAmount(position.getLiquidationPrice()));

			this.marginRatio.setText(StringFormatUtil.getPercentString(marginRatio));
			this.marginRatio.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					marginRatio > 0 ? R.attr.colorGreen :
							marginRatio == 0 ? R.attr.colorTextPrimary
									: R.attr.colorRed));

			if (position.getMarginType().equals(BinanceFuturesMarginType.CROSS)) {
				this.margin.setText(String.format(Locale.getDefault(), "%s\n%s",
						StringFormatUtil.formatAmount(notionalSize / position.getLeverage()),
						"(cross)"));
			}
			else if (position.getMarginType().equals(BinanceFuturesMarginType.ISOLATED)) {
				this.margin.setText(String.format(Locale.getDefault(), "%s\n%s",
						StringFormatUtil.formatAmount(position.getIsolatedMargin() - position.getUnrealizedPnL()),
						"(isolated)"));
			}

			this.notionalSize.setText(StringFormatUtil.getValueString(Math.abs(position.getQuantity() * position.getMarkPrice()), Currency.USDT.getValue()));
		}
	}
}
