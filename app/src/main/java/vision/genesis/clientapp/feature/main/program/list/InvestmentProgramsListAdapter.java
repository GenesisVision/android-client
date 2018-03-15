package vision.genesis.clientapp.feature.main.program.list;

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
import io.swagger.client.model.InvestmentProgram;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ShortenedAmount;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class InvestmentProgramsListAdapter extends RecyclerView.Adapter<InvestmentProgramsListAdapter.InvestmentProgramViewHolder>
{
	public List<InvestmentProgram> investmentPrograms = new ArrayList<>();

	@Override
	public InvestmentProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_investment_program, parent, false);
		return new InvestmentProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestmentProgramViewHolder holder, int position) {
		holder.setInvestmentProgram(investmentPrograms.get(position));
	}

	@Override
	public int getItemCount() {
		return investmentPrograms.size();
	}

	void setInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.clear();
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	void addInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	static class InvestmentProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.avatar)
		public AvatarView avatar;

		@BindView(R.id.title)
		public TextView title;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.text_total_profit_text)
		public TextView totalProfitText;

		@BindView(R.id.text_total_profit_text_mod)
		public TextView totalProfitTextMod;

		@BindView(R.id.text_total_profit_title)
		public TextView totalProfitTitle;


		@BindView(R.id.text_avg_profit_text)
		public TextView avgProfitText;

		@BindView(R.id.text_avg_profit_text_percent)
		public TextView avgProfitTextPercent;

		@BindView(R.id.text_avg_profit_title)
		public TextView avgProfitTitle;


		@BindView(R.id.text_balance_text)
		public TextView balanceText;

		@BindView(R.id.text_balance_text_mod)
		public TextView balanceTextMod;

		@BindView(R.id.text_balance_title)
		public TextView balanceTitle;


		@BindView(R.id.text_period_text)
		public TextView periodText;

		@BindView(R.id.text_period_title)
		public TextView periodTitle;

		@BindView(R.id.chart)
		public ProfitChartView chart;

		private InvestmentProgram investmentProgram;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram.getId())));
		}

		private void setFonts() {
			title.setTypeface(TypefaceUtil.bold(itemView.getContext()));

			currency.setTypeface(TypefaceUtil.bold(itemView.getContext()));

			totalProfitText.setTypeface(TypefaceUtil.light(itemView.getContext()));
			avgProfitText.setTypeface(TypefaceUtil.light(itemView.getContext()));
			balanceText.setTypeface(TypefaceUtil.light(itemView.getContext()));
			periodText.setTypeface(TypefaceUtil.light(itemView.getContext()));

			totalProfitTextMod.setTypeface(TypefaceUtil.bold(itemView.getContext()));
			avgProfitTextPercent.setTypeface(TypefaceUtil.bold(itemView.getContext()));
			balanceTextMod.setTypeface(TypefaceUtil.bold(itemView.getContext()));

			totalProfitTitle.setTypeface(TypefaceUtil.bold(itemView.getContext()));
			avgProfitTitle.setTypeface(TypefaceUtil.bold(itemView.getContext()));
			balanceTitle.setTypeface(TypefaceUtil.bold(itemView.getContext()));
			periodTitle.setTypeface(TypefaceUtil.bold(itemView.getContext()));
		}

		void setInvestmentProgram(InvestmentProgram investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			avatar.setImage(investmentProgram.getLogo());
			avatar.setLevel(investmentProgram.getLevel());
			title.setText(investmentProgram.getTitle());
			currency.setText(investmentProgram.getCurrency().toString());


			ShortenedAmount totalProfitShortenedAmount = StringFormatUtil.getShortenedAmount(investmentProgram.getProfitTotal());
			totalProfitText.setText(String.format("$%s", totalProfitShortenedAmount.amount));
			totalProfitTextMod.setText(totalProfitShortenedAmount.modifier);

			avgProfitText.setText(String.format(Locale.getDefault(), "%.0f", investmentProgram.getProfitAvg()));

			ShortenedAmount balanceShortenedAmount = StringFormatUtil.getShortenedAmount(investmentProgram.getBalance());
			balanceText.setText(String.format("$%s", balanceShortenedAmount.amount));
			balanceTextMod.setText(balanceShortenedAmount.modifier);

			periodText.setText(String.valueOf(investmentProgram.getPeriodDuration()));

			chart.setChart(investmentProgram.getChart());
		}
	}
}
