package vision.genesis.clientapp.feature.main.programs_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.ProgramDetailsListItem;
import io.swagger.client.model.ProgramType;
import io.swagger.client.model.Tag;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.facet.ProgramFacetView;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.OnListProgramFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.ui.CurrencyView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.TagView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class ProgramsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private static final int TYPE_FACET = 0;

	private static final int TYPE_CARD = 1;

	private List<ProgramDetailsListItem> investmentPrograms = new ArrayList<ProgramDetailsListItem>();

	private List<AssetFacet> facets = new ArrayList<>();

	@Override
	public int getItemViewType(int position) {
		return position == 0 ? TYPE_FACET : TYPE_CARD;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		switch (viewType) {
			case TYPE_FACET:
				return new FacetsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_facets, parent, false));
			case TYPE_CARD:
			default:
				return new InvestmentProgramViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_investment_program, parent, false));
		}
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		switch (holder.getItemViewType()) {
			case TYPE_FACET:
				((FacetsViewHolder) holder).setFacets(facets);
				break;
			case TYPE_CARD:
				((InvestmentProgramViewHolder) holder).setProgram(investmentPrograms.get(position - 1));
				break;
		}
	}

	@Override
	public long getItemId(int position) {
		return position == 0 ? 0 : investmentPrograms.get(position - 1).hashCode();
	}

	@Override
	public int getItemCount() {
		return investmentPrograms.size() + 1;
	}

	public void setFacets(List<AssetFacet> facets) {
		this.facets = facets;
		notifyDataSetChanged();
	}

	public void setInvestmentPrograms(List<ProgramDetailsListItem> investmentPrograms) {
		this.investmentPrograms.clear();
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	public void addInvestmentPrograms(List<ProgramDetailsListItem> investmentPrograms) {
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		for (ProgramDetailsListItem program : investmentPrograms) {
			if (program.getId().equals(programId)) {
				if (program.getPersonalDetails() != null && !program.getPersonalDetails().isIsFavorite().equals(isFavorite)) {
					program.getPersonalDetails().setIsFavorite(isFavorite);
					notifyItemChanged(investmentPrograms.indexOf(program));
				}
				break;
			}
		}
	}

	public void removeProgram(UUID programId) {
		for (int i = 0; i < investmentPrograms.size(); i++) {
			if (investmentPrograms.get(i).getId().equals(programId)) {
				investmentPrograms.remove(i);
				notifyItemRemoved(i);
				break;
			}
		}
	}

	static class FacetsViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.group_facets)
		public ViewGroup facetsGroup;

		private List<AssetFacet> facets;

		FacetsViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setFacets(List<AssetFacet> facets) {
			this.facets = facets;
			updateView();
		}

		private void updateView() {
			facetsGroup.removeAllViews();
			for (AssetFacet facet : facets) {
				ProgramFacetView view = new ProgramFacetView(itemView.getContext());
				view.setData(facet);
				facetsGroup.addView(view);
			}
		}
	}

	static class InvestmentProgramViewHolder extends RecyclerView.ViewHolder
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

		@BindView(R.id.program_currency)
		public CurrencyView programCurrency;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.profit_value)
		public TextView profitValue;

		@BindView(R.id.balance)
		public TextView balance;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.available_to_invest)
		public TextView availableToInvest;

		@BindView(R.id.available_to_invest_label)
		public TextView availableToInvestLabel;

		@BindView(R.id.period)
		public PeriodLeftView period;

		@BindView(R.id.period_label)
		public TextView periodLabel;

		@BindView(R.id.tag_1)
		public TagView tag1;

		@BindView(R.id.tag_2)
		public TagView tag2;

		@BindView(R.id.tags_left)
		public TagView tagsLeft;

		private ProgramDetailsListItem program;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (program != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(program.getId(),
							program.getLogoUrl(),
							program.getColor(),
							program.getLevel(),
							program.getLevelProgress(),
							program.getTitle(),
							program.getOwner().getUsername(),
							program.getCurrency().getValue(),
							program.getPersonalDetails() != null ?
									program.getPersonalDetails().isIsFavorite()
									: false,
//TODO:
//							program.getPersonalDetails() != null ?
//									program.getPersonalDetails().isHasNotifications()
//									: false);
							false,
							AssetType.PROGRAM);
					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		@OnClick(R.id.favorite)
		public void onFavoriteClicked() {
			if (program != null && program.getPersonalDetails() != null) {
				program.getPersonalDetails().setIsFavorite(!program.getPersonalDetails().isIsFavorite());
				updateData();
				EventBus.getDefault().post(new OnListProgramFavoriteClickedEvent(program.getId(), program.getPersonalDetails().isIsFavorite()));
			}
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			managerName.setTypeface(TypefaceUtil.medium());
			profitPercent.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			availableToInvest.setTypeface(TypefaceUtil.semibold());

			balanceLabel.setText(balanceLabel.getText().toString().toLowerCase());
			availableToInvestLabel.setText(availableToInvestLabel.getText().toString().toLowerCase());
			periodLabel.setText(periodLabel.getText().toString().toLowerCase());
		}

		void setProgram(ProgramDetailsListItem program) {
			this.program = program;
			updateData();
		}

		private void updateData() {
			programLogo.setImage(program.getLogoUrl(), program.getColor(), 100, 100);
			programLogo.setLevel(program.getLevel(), program.getLevelProgress());

			if (program.getPersonalDetails() != null) {
				favorite.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, program.getPersonalDetails().isIsFavorite()
						? R.drawable.icon_favorite_fill
						: R.drawable.icon_favorite));
				favorite.setAlpha(program.getPersonalDetails().isIsFavorite() ? 1f : 0.3f);
			}
			favorite.setVisibility(program.getPersonalDetails() != null ? View.VISIBLE : View.INVISIBLE);

			this.programName.setText(program.getTitle());
			this.managerName.setText(program.getOwner().getUsername());

			Double profitPercent = 0.0;
			if (program.getStatistic() != null) {
				this.chart.setChart(program.getStatistic().getChart());
				profitPercent = program.getStatistic().getProfit();
			}

//			Double profitValue = getProfitValue();
			//TODO:
//			Double profitValue = program.getStatistic().getProfitValue();
			double profitValue = 0.0;

			this.programCurrency.setCurrency(program.getCurrency().getValue());

			this.profitPercent.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(profitPercent, 0, 2)));
			this.profitPercent.setTextColor(profitPercent >= 0
					? ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen)
					: ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));

			this.profitValue.setText(String.format(Locale.getDefault(), "%s%s GVT",
					profitValue > 0 ? "+" : "",
					StringFormatUtil.formatAmount(profitValue, 0, 4)));

			if (program.getType().equals(ProgramType.FIXEDPERIOD)) {
				this.period.setData(program.getPeriodDuration(), program.getPeriodStarts(), program.getPeriodEnds(), true, false);
				this.period.setNoPeriod(false);
			}
			else if (program.getType().equals(ProgramType.DAILYPERIOD)) {
				this.period.setNoPeriod(true);
			}

			this.balance.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.getShortenedAmount(program.getBalance().getAmount()), program.getCurrency().getValue()));

			this.availableToInvest.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.getShortenedAmount(program.getAvailableToInvest()),
					program.getCurrency().getValue()));

			updateTags(program.getTags());
		}

		private void updateTags(List<Tag> tags) {
			if (tags != null) {
				showTagMaybe(tag1, tags, 0);
				showTagMaybe(tag2, tags, 1);
				if (tags.size() > 2) {
					Tag tagLeft = new Tag();
					tagLeft.setName(String.format(Locale.getDefault(), "+%d", tags.size() - 2));
					tagLeft.setColor("#787d82");
					tagsLeft.setTag(tagLeft);
					tagsLeft.setVisibility(View.VISIBLE);
				}
				else {
					tagsLeft.setVisibility(View.GONE);
				}
			}
			else {
				tag1.setVisibility(View.GONE);
				tag2.setVisibility(View.GONE);
				tagsLeft.setVisibility(View.GONE);
			}
		}

		private void showTagMaybe(TagView tagView, List<Tag> tags, Integer position) {
			if (tags != null && tags.size() > position) {
				tagView.setTag(tags.get(position));
				tagView.setVisibility(View.VISIBLE);
			}
			else {
				tagView.setVisibility(View.GONE);
			}
		}

//		private Double getProfitPercent() {
//			Double first = program.getChart().get(0).getValue();
//			Double last = program.getChart().get(program.getChart().size() - 1).getValue();
//
//			return Math.abs(first != 0 ? 100 / first * (first - last) : 0);
//		}
//
//		private Double getProfitValue() {
//			Double first = program.getChart().get(0).getValue();
//			Double last = program.getChart().get(program.getChart().size() - 1).getValue();
//
//			return last - first;
//		}
	}
}
