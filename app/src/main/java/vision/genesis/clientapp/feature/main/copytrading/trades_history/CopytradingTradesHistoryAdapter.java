package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderProgramData;
import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowCopytradingCommissionsEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class CopytradingTradesHistoryAdapter extends RecyclerView.Adapter<CopytradingTradesHistoryAdapter.TradesHistoryViewHolder>
{
	private List<OrderSignalModel> trades = new ArrayList<>();

	@Override
	public TradesHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_trades_history, parent, false);
		return new TradesHistoryViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(TradesHistoryViewHolder holder, int position) {
		if (trades.get(position) != null)
			holder.setTrade(trades.get(position));
	}

	@Override
	public int getItemCount() {
		return trades.size();
	}

	@Override
	public long getItemId(int position) {
		return trades.get(position) != null
				? trades.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	public void setTrades(List<OrderSignalModel> trades) {
		this.trades.clear();
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	void addTrades(List<OrderSignalModel> trades) {
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	static class TradesHistoryViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.symbol_label)
		public TextView symbolLabel;

		@BindView(R.id.symbol)
		public TextView symbol;

		@BindView(R.id.volume_label)
		public TextView volumeLabel;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.dir_label)
		public TextView dirLabel;

		@BindView(R.id.dir)
		public TextView dir;

		@BindView(R.id.commission)
		public TextView commission;

		@BindView(R.id.commission_label)
		public TextView commissionLabel;

		@BindView(R.id.price_label)
		public TextView priceLabel;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.profit)
		public TextView profit;

		private OrderSignalModel trade;

		TradesHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (trade != null) {
//					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(trade.getId(),
//							trade.getLogo(),
//							trade.getColor(),
//							trade.getLevel(),
//							trade.getTitle(),
//							trade.getManager().getUsername(),
//							trade.getCurrencySecondary().getValue(),
//							trade.getPersonalDetails().isIsFavorite(),
//							false);
					EventBus.getDefault().post(new ShowCopytradingCommissionsEvent(trade));
				}
			});
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			date.setTypeface(TypefaceUtil.semibold());
			symbol.setTypeface(TypefaceUtil.semibold());
			volume.setTypeface(TypefaceUtil.semibold());
			dir.setTypeface(TypefaceUtil.semibold());
			commission.setTypeface(TypefaceUtil.semibold());
			price.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());

			symbolLabel.setText(symbolLabel.getText().toString().toLowerCase());
			volumeLabel.setText(volumeLabel.getText().toString().toLowerCase());
			dirLabel.setText(dirLabel.getText().toString().toLowerCase());
			commissionLabel.setText(commissionLabel.getText().toString().toLowerCase());
			priceLabel.setText(priceLabel.getText().toString().toLowerCase());
			profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		}

		void setTrade(OrderSignalModel trade) {
			this.trade = trade;
			updateData();
		}

		private void updateData() {
			if (trade.getProviders().size() > 0) {
				OrderProgramData program = trade.getProviders().get(0).getProgram();

				this.programLogo.setImage(program.getLogo(), program.getColor(), 100, 100);
				this.programLogo.setLevel(program.getLevel(), program.getLevelProgress());

				this.programName.setText(program.getTitle());
				this.date.setText(DateTimeUtil.formatEventDateTime(trade.getDate()));

				this.symbol.setText(trade.getSymbol());
				this.volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 0, 8));
				updateDirection();

				this.commission.setText(StringFormatUtil.formatAmount(trade.getTotalCommission(), 2, 8));
				this.price.setText(StringFormatUtil.formatAmount(trade.getPrice(), 2, 8));
				updateProfit();
			}
		}

		private void updateDirection() {
			int dirColor;
			switch (trade.getDirection()) {
				case BUY:
					dirColor = R.attr.colorGreen;
					break;
				case SELL:
					dirColor = R.attr.colorRed;
					break;
				default:
					dirColor = R.attr.colorGreen;
					break;
			}
			this.dir.setText(trade.getDirection().getValue());
			this.dir.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), dirColor));
		}

		private void updateProfit() {
			Double profitValue = trade.getProfit();
			this.profit.setText(String.format(Locale.getDefault(), "%s%s",
					profitValue > 0 ? "+" : "",
					StringFormatUtil.formatAmount(profitValue, 2, 8)));
			this.profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					profitValue >= 0 ? R.attr.colorGreen : R.attr.colorRed));
		}
	}
}
