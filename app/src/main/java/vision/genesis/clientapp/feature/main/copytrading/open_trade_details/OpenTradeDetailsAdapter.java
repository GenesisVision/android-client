package vision.genesis.clientapp.feature.main.copytrading.open_trade_details;

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
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.OpenTradeProviderModel;
import vision.genesis.clientapp.model.events.OnOpenTradeCloseClickedEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class OpenTradeDetailsAdapter extends RecyclerView.Adapter<OpenTradeDetailsAdapter.OpenTradeViewHolder>
{
	private List<OpenTradeProviderModel> trades = new ArrayList<>();

	@Override
	public OpenTradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_open_trade_provider, parent, false);
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

	public void setTrades(List<OpenTradeProviderModel> trades) {
		this.trades.clear();
		this.trades.addAll(trades);
		notifyDataSetChanged();
	}

	public void deleteTrade(int position) {
		trades.remove(position);
		notifyItemRemoved(position);
	}

	static class OpenTradeViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.volume_label)
		public TextView volumeLabel;

		@BindView(R.id.volume)
		public TextView volume;

		@BindView(R.id.price_open_label)
		public TextView priceOpenLabel;

		@BindView(R.id.price_open)
		public TextView priceOpen;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.profit)
		public TextView profit;

		private OpenTradeProviderModel trade;

		OpenTradeViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();
		}

		@OnClick(R.id.button_close)
		public void onCloseClicked() {
			EventBus.getDefault().post(new OnOpenTradeCloseClickedEvent(trade.getProgramId(), trade.getSymbol(), trade.getVolume()));
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			date.setTypeface(TypefaceUtil.semibold());
			volume.setTypeface(TypefaceUtil.semibold());
			priceOpen.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());

			volumeLabel.setText(volumeLabel.getText().toString().toLowerCase());
			priceOpenLabel.setText(priceOpenLabel.getText().toString().toLowerCase());
			profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		}

		void setTrade(OpenTradeProviderModel trade) {
			this.trade = trade;
			updateData();
		}

		private void updateData() {
			this.programLogo.setImage(trade.getProgramLogo(), trade.getProgramColor(), 100, 100);
			this.programLogo.setLevel(trade.getProgramLevel());

			this.programName.setText(trade.getProgramName());
			this.date.setText(DateTimeUtil.formatEventDateTime(trade.getDate()));

			this.volume.setText(StringFormatUtil.formatAmount(trade.getVolume(), 0, 8));

			this.priceOpen.setText(StringFormatUtil.formatAmount(trade.getPriceOpen(), 2, 8));
			updateProfit();
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
