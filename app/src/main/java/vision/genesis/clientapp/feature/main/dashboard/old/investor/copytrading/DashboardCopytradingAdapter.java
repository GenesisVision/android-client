package vision.genesis.clientapp.feature.main.dashboard.old.investor.copytrading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.CopyTradingAccountInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class DashboardCopytradingAdapter extends RecyclerView.Adapter<DashboardCopytradingAdapter.SignalViewHolder>
{
	private List<CopyTradingAccountInfo> signals = new ArrayList<>();

	@Override
	public SignalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_signal, parent, false);
		return new SignalViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SignalViewHolder holder, int position) {
		if (signals.get(position) != null) {
			holder.setSignal(signals.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return signals.size();
	}

	@Override
	public long getItemId(int position) {
		return signals.get(position) != null
				? signals.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setSignals(List<CopyTradingAccountInfo> signals) {
		this.signals.clear();
		this.signals.addAll(signals);
		notifyDataSetChanged();
	}

	static class SignalViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.currency_label)
		public TextView currencyLabel;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.trades_label)
		public TextView tradesLabel;

		@BindView(R.id.trades)
		public TextView trades;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.date_label)
		public TextView dateLabel;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.status)
		public InvestmentStatusView status;


		private CopyTradingAccountInfo signal;

		private Context context;

		SignalViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			this.context = itemView.getContext();

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (signal != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(signal.getId(),
							signal.getLogo(),
//							signal.getColor(),
//							signal.getLevel(),
//							signal.getLevelProgress(),
							"",
							0,
							0.0,
							signal.getTitle(),
//							signal.getManager().getUsername(),
							"",
							signal.getCurrency().getValue(),
//							signal.getPersonalDetails().isIsFavorite(),
							false,
							false,
							AssetType.FOLLOW);
					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			managerName.setTypeface(TypefaceUtil.medium());
			currency.setTypeface(TypefaceUtil.semibold());
			trades.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());
			date.setTypeface(TypefaceUtil.semibold());
		}

		void setSignal(CopyTradingAccountInfo signal) {
			this.signal = signal;
			updateData();
		}

		private void updateData() {
//			this.programLogo.setImage(signal.getLogo(), signal.getColor(), 100, 100);
			this.programLogo.setImage(signal.getLogo(), "", 100, 100);
//			this.programLogo.setLevel(signal.getLevel(), signal.getLevelProgress());

			this.programName.setText(signal.getTitle());
//			this.managerName.setText(signal.getManager().getUsername());

			this.currency.setText(signal.getCurrency().getValue());

//			this.trades.setText(StringFormatUtil.formatAmount(signal.getPersonalDetails().getTradesCount(), 0, 0));

			updateProfit();

//			this.status.setStatus(signal.getPersonalDetails().getStatus().getValue());

//			this.date.setText(DateTimeUtil.formatEventDateTime(signal.getPersonalDetails().getSubscriptionDate()));
		}

		private void updateProfit() {
//			Double profitValue = signal.getPersonalDetails().getProfit();
//			this.profit.setText(String.format(Locale.getDefault(), "%s%s",
//					profitValue > 0 ? "+" : "",
//					StringFormatUtil.formatAmount(profitValue, 2, 8)));
//			this.profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
//					profitValue >= 0 ? R.attr.colorGreen : R.attr.colorRed));
		}
	}
}
