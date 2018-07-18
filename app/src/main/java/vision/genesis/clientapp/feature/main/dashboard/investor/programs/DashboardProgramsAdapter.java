package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;
import vision.genesis.clientapp.model.ProgramInfoModel;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardProgramsAdapter extends RecyclerView.Adapter<DashboardProgramsAdapter.InvestorProgramViewHolder>
{
	private List<InvestmentProgramDashboardExtended> investorPrograms = new ArrayList<>();

	@Override
	public InvestorProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_program, parent, false);
		return new InvestorProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestorProgramViewHolder holder, int position) {
		if (investorPrograms.get(position) != null)
			holder.setInvestmentProgram(investorPrograms.get(position));
	}

	@Override
	public int getItemCount() {
		return investorPrograms.size();
	}

	@Override
	public long getItemId(int position) {
		return investorPrograms.get(position) != null
				? investorPrograms.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setInvestorPrograms(List<InvestmentProgramDashboardExtended> investorPrograms) {
		this.investorPrograms.clear();
		this.investorPrograms.addAll(investorPrograms);
		notifyDataSetChanged();
	}

	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		for (InvestmentProgramDashboardExtended program : investorPrograms) {
			if (program.getData().getId().equals(programId)) {
				program.getData().isFavorite(isFavorite);
				notifyDataSetChanged();
				break;
			}
		}
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public AvatarView programLogo;

		@BindView(R.id.icon_favorite)
		public ImageView favoriteIcon;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.tokens)
		public TextView tokens;

		@BindView(R.id.tokens_fiat)
		public TextView tokensFiat;

		@BindView(R.id.label_my_tokens)
		public TextView myTokensLabel;

		@BindView(R.id.group_profit_short)
		public ViewGroup profitShortGroup;

		@BindView(R.id.profit_short)
		public TextView profitShort;

		@BindView(R.id.profit_full)
		public TextView profitFull;

		@BindView(R.id.profit_currency)
		public TextView profitCurrency;

		@BindView(R.id.label_profit)
		public TextView profitLabel;

		@BindView(R.id.view_period_left)
		public PeriodLeftView periodLeftView;

		private boolean isProfitFull = false;

		private InvestmentProgramDashboardExtended investmentProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (investmentProgram != null) {
					InvestmentProgramDashboardInvestor data = investmentProgram.getData();

					ProgramInfoModel programInfoModel = new ProgramInfoModel(data.getId(),
							data.getLogo(),
							data.getTitle(),
							data.getManager().getUsername(),
							data.isIsFavorite());
					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programInfoModel));
				}
			});
		}

		@OnClick(R.id.group_profit)
		public void onProfitClicked() {
			isProfitFull = !isProfitFull;
			setProfitVisibility();
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.bold());
			tokensFiat.setTypeface(TypefaceUtil.bold());
			myTokensLabel.setTypeface(TypefaceUtil.bold());
			profitCurrency.setTypeface(TypefaceUtil.bold());
			profitLabel.setTypeface(TypefaceUtil.bold());
		}

		void setInvestmentProgram(InvestmentProgramDashboardExtended investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			InvestmentProgramDashboardInvestor data = investmentProgram.getData();

			programLogo.setImage(data.getLogo(), 100, 100);
			programLogo.setLevel(data.getLevel());

			favoriteIcon.setVisibility(data.isIsFavorite() ? View.VISIBLE : View.GONE);

			programName.setText(data.getTitle());
			managerName.setText(String.format(Locale.getDefault(), "%s %s",
					itemView.getContext().getResources().getString(R.string.by),
					data.getManager().getUsername()));

			chart.setEquityChart(investmentProgram.getEquityChart());

			tokens.setText(investmentProgram.getTokens());
			tokensFiat.setText(investmentProgram.getTokensFiat());

			profitShort.setText(investmentProgram.getProfitShort());
			profitFull.setText(investmentProgram.getProfitFull());

			if (data.isIsEnabled())
				periodLeftView.setDateTo(data.getStartOfPeriod(), data.getEndOfPeriod());
			periodLeftView.setProgramClosed(!data.isIsEnabled());
		}

		private void setProfitVisibility() {
			profitShortGroup.setVisibility(!isProfitFull ? View.VISIBLE : View.GONE);
			profitFull.setVisibility(isProfitFull ? View.VISIBLE : View.GONE);
		}
	}
}
