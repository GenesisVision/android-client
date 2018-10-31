package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
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
import io.swagger.client.model.PersonalProgramDetailsFull;
import io.swagger.client.model.ProgramDetails;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.OnDashboardProgramFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardReinvestClickedEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramRequestsEvent;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardProgramsAdapter extends RecyclerView.Adapter<DashboardProgramsAdapter.ProgramViewHolder>
{
	private List<ProgramDetails> programs = new ArrayList<>();

	@Override
	public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_program, parent, false);
		return new ProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ProgramViewHolder holder, int position) {
		if (programs.get(position) != null)
			holder.setProgram(programs.get(position));
	}

	@Override
	public int getItemCount() {
		return programs.size();
	}

	@Override
	public long getItemId(int position) {
		return programs.get(position) != null
				? programs.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setPrograms(List<ProgramDetails> programs) {
		this.programs.clear();
		this.programs.addAll(programs);
		notifyDataSetChanged();
	}

	public void setProgramReinvest(UUID programId, Boolean reinvest) {
		for (ProgramDetails program : programs) {
			if (program.getId().equals(programId)) {
				if (program.getPersonalDetails() != null && !program.getPersonalDetails().isIsFavorite().equals(reinvest)) {
					program.getPersonalDetails().setIsReinvest(reinvest);
					notifyItemChanged(programs.indexOf(program));
				}
				break;
			}
		}
	}

	public void setProgramFavorite(UUID programId, Boolean favorite) {
		for (ProgramDetails program : programs) {
			if (program.getId().equals(programId)) {
				if (program.getPersonalDetails() != null && !program.getPersonalDetails().isIsFavorite().equals(favorite)) {
					program.getPersonalDetails().setIsFavorite(favorite);
					notifyItemChanged(programs.indexOf(program));
				}
				break;
			}
		}
	}

	static class ProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.favorite)
		public ImageView favorite;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.profit_value)
		public TextView profitValue;

		@BindView(R.id.current_value_label)
		public TextView currentValueLabel;

		@BindView(R.id.current_value)
		public TextView currentValue;

		@BindView(R.id.share_label)
		public TextView shareLabel;

		@BindView(R.id.share)
		public TextView share;

		@BindView(R.id.time_left_label)
		public TextView timeLeftLabel;

		@BindView(R.id.time_left)
		public PeriodLeftView timeLeft;

		@BindView(R.id.status)
		public InvestmentStatusView status;

		@BindView(R.id.label_reinvest)
		public TextView reinvestLabel;

		@BindView(R.id.switch_reinvest)
		public SwitchCompat reinvest;

		private ProgramDetails program;

		private Context context;

		ProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			this.context = itemView.getContext();

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (program != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(program.getId(),
							program.getLogo(),
							program.getColor(),
							program.getLevel(),
							program.getTitle(),
							program.getManager().getUsername(),
							program.getCurrency().getValue(),
							program.getPersonalDetails().isIsFavorite(),
							program.getPersonalDetails().isHasNotifications());
					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		@OnClick(R.id.switch_reinvest)
		public void onReinvestClicked() {
			if (program != null) {
				EventBus.getDefault().post(new OnDashboardReinvestClickedEvent(program.getId(), !program.getPersonalDetails().isIsReinvest()));
			}
		}

		@OnClick(R.id.favorite)
		public void onFavoriteClicked() {
			if (program != null && program.getPersonalDetails() != null) {
				program.getPersonalDetails().setIsFavorite(!program.getPersonalDetails().isIsFavorite());
				updateData();
				EventBus.getDefault().post(new OnDashboardProgramFavoriteClickedEvent(program.getId(), program.getPersonalDetails().isIsFavorite()));
			}
		}

		@OnClick(R.id.status)
		public void onStatusClicked() {
			if (program != null && program.getPersonalDetails() != null) {
				if (program.getPersonalDetails().getStatus().equals(PersonalProgramDetailsFull.StatusEnum.INVESTING) ||
						(program.getPersonalDetails().getStatus().equals(PersonalProgramDetailsFull.StatusEnum.WITHDRAWING))) {
					EventBus.getDefault().post(new ShowProgramRequestsEvent(program.getId()));
				}
			}
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			managerName.setTypeface(TypefaceUtil.medium());
			profitPercent.setTypeface(TypefaceUtil.semibold());
			share.setTypeface(TypefaceUtil.semibold());
			currentValue.setTypeface(TypefaceUtil.semibold());
			reinvestLabel.setTypeface(TypefaceUtil.semibold());
		}

		void setProgram(ProgramDetails program) {
			this.program = program;
			updateData();
		}

		private void updateData() {
			this.programLogo.setImage(program.getLogo(), program.getColor(), 100, 100);
			this.programLogo.setLevel(program.getLevel());

			this.programName.setText(program.getTitle());
			this.managerName.setText(program.getManager().getUsername());

			favorite.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, program.getPersonalDetails().isIsFavorite()
					? R.drawable.icon_favorite_fill
					: R.drawable.icon_favorite));
			favorite.setAlpha(program.getPersonalDetails().isIsFavorite() ? 1f : 0.3f);

			this.chart.setChart(program.getChart());

//			Double profitPercent = getProfitPercent();
			Double profitPercent = program.getStatistic().getProfitPercent();
//			Double profitValue = getProfitValue();
			Double profitValue = program.getStatistic().getProfitValue();
			this.profitPercent.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(profitPercent, 0, 2)));
			this.profitPercent.setTextColor(profitPercent >= 0
					? ThemeUtil.getColorByAttrId(context, R.attr.colorGreen)
					: ThemeUtil.getColorByAttrId(context, R.attr.colorRed));

			this.profitValue.setText(String.format(Locale.getDefault(), "%s%s GVT",
					profitValue > 0 ? "+" : "",
					StringFormatUtil.formatAmount(profitValue, 0, 4)));

			this.share.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(program.getDashboardAssetsDetails().getShare(), 0, 2)));

			this.currentValue.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(program.getPersonalDetails().getValue(), 0,
							StringFormatUtil.getCurrencyMaxFraction(program.getCurrency().getValue())),
					program.getCurrency().getValue()));

			this.timeLeft.setData(program.getPeriodDuration(), program.getPeriodStarts(), program.getPeriodEnds(), false, true);

			this.status.setStatus(program.getPersonalDetails().getStatus().getValue());

			this.reinvest.setChecked(program.getPersonalDetails().isIsReinvest());
		}

		private Double getProfitPercent() {
			Double first = program.getChart().get(0).getValue();
			Double last = program.getChart().get(program.getChart().size() - 1).getValue();

			return Math.abs(first != 0 ? 100 / first * (first - last) : 0);
		}

		private Double getProfitValue() {
			Double first = program.getChart().get(0).getValue();
			Double last = program.getChart().get(program.getChart().size() - 1).getValue();

			return last - first;
		}
	}
}
