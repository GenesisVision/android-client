package vision.genesis.clientapp.feature.main.dashboard.investor.open_trades;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderProgramData;
import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class DashboardOpenTradesAdapter extends RecyclerView.Adapter<DashboardOpenTradesAdapter.OpenTradeViewHolder>
{
	private List<OrderSignalModel> trades = new ArrayList<>();

	@Override
	public OpenTradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_open_trade, parent, false);
		return new OpenTradeViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(OpenTradeViewHolder holder, int position) {
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

	static class OpenTradeViewHolder extends RecyclerView.ViewHolder
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

		@BindView(R.id.price_open_label)
		public TextView priceOpenLabel;

		@BindView(R.id.price_open)
		public TextView priceOpen;

		@BindView(R.id.price_label)
		public TextView priceLabel;

		@BindView(R.id.price)
		public TextView price;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.profit)
		public TextView profit;

		private OrderSignalModel trade;

		private Context context;

		OpenTradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			this.context = itemView.getContext();

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
//							trade.getCurrency().getValue(),
//							trade.getPersonalDetails().isIsFavorite(),
//							false);
//					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			date.setTypeface(TypefaceUtil.semibold());
			symbol.setTypeface(TypefaceUtil.semibold());
			volume.setTypeface(TypefaceUtil.semibold());
			dir.setTypeface(TypefaceUtil.semibold());
			priceOpen.setTypeface(TypefaceUtil.semibold());
			price.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());
		}

		void setTrade(OrderSignalModel trade) {
			this.trade = trade;
			updateData();
		}

		private void updateData() {
			if (trade.getProviders().size() > 0) {
				OrderProgramData program = trade.getProviders().get(0).getProgram();

				this.programLogo.setImage(program.getLogo(), program.getColor(), 100, 100);
				this.programLogo.setLevel(program.getLevel());

				this.programName.setText(program.getTitle());
				this.date.setText(DateTimeUtil.formatEventDateTime(trade.getDate()));

				this.symbol.setText(trade.getSymbol());

				this.volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 0, 8));

				Double profitValue = trade.getProfit();
//				this.profit.setText(String.format(Locale.getDefault(), "%s%s %s",
//						profitValue > 0 ? "+" : "",
//						StringFormatUtil.formatCurrencyAmount(profitValue, trade.getProviders().get(0).getProgram().get.getValue()),
//						trade.getCurrency().getValue()));

			}
		}
	}
}
