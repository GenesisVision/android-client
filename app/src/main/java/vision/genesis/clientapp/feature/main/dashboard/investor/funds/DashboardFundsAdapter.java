package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class DashboardFundsAdapter extends RecyclerView.Adapter<DashboardFundsAdapter.FundViewHolder>
{
//	private List<FundDetails> funds = new ArrayList<>();

	@Override
	public FundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_fund, parent, false);
		return new FundViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(FundViewHolder holder, int position) {
//		if (funds.get(position) != null)
//			holder.setFund(funds.get(position));
	}

	@Override
	public int getItemCount() {
//		return funds.size();
		return 0;
	}

//	@Override
//	public long getItemId(int position) {
//		return funds.get(position) != null
//				? funds.get(position).hashCode()
//				: RecyclerView.NO_ID;
//	}

//	void setFunds(List<FundDetails> funds) {
//		this.funds.clear();
//		this.funds.addAll(funds);
//		notifyDataSetChanged();
//	}

	public void setFundFavorite(UUID fundId, Boolean favorite) {
//		for (FundDetails fund : funds) {
//			if (fund.getId().equals(fundId)) {
//				if (fund.getPersonalDetails() != null && !fund.getPersonalDetails().isIsFavorite().equals(favorite)) {
//					fund.getPersonalDetails().setIsFavorite(favorite);
//					notifyItemChanged(funds.indexOf(fund));
//				}
//				break;
//			}
//		}
	}

	static class FundViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.fund_logo)
		public ProgramLogoView fundLogo;

		@BindView(R.id.fund_name)
		public TextView fundName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.favorite)
		public ImageView favorite;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.value_label)
		public TextView valueLabel;

		@BindView(R.id.investors)
		public TextView investors;

		@BindView(R.id.investors_label)
		public TextView investorsLabel;

		@BindView(R.id.drawdown)
		public TextView drawdown;

		@BindView(R.id.drawdown_label)
		public TextView drawdownLabel;

		@BindView(R.id.group_asset1)
		public ViewGroup groupAsset1;

		@BindView(R.id.group_asset2)
		public ViewGroup groupAsset2;

		@BindView(R.id.group_asset3)
		public ViewGroup groupAsset3;

		@BindView(R.id.icon_asset1)
		public SimpleDraweeView iconAsset1;

		@BindView(R.id.icon_asset2)
		public SimpleDraweeView iconAsset2;

		@BindView(R.id.icon_asset3)
		public SimpleDraweeView iconAsset3;

		@BindView(R.id.name_asset1)
		public TextView nameAsset1;

		@BindView(R.id.name_asset2)
		public TextView nameAsset2;

		@BindView(R.id.name_asset3)
		public TextView nameAsset3;

		@BindView(R.id.group_assets_left)
		public ViewGroup groupAssetsLeft;

		@BindView(R.id.text_assets_left)
		public TextView assetsLeft;

//		private FundDetails fund;

		FundViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			fundLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
//				if (fund != null) {
//					FundDetailsModel fundDetailsModel = new FundDetailsModel(fund.getId(),
//							fund.getLogo(),
//							fund.getColor(),
//							fund.getTitle(),
//							fund.getManager().getUsername(),
//							fund.getPersonalDetails() != null ?
//									fund.getPersonalDetails().isIsFavorite()
//									: false,
//							fund.getPersonalDetails() != null ?
//									fund.getPersonalDetails().isHasNotifications()
//									: false);
//					EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
//				}
			});
		}

		@OnClick(R.id.favorite)
		public void onFavoriteClicked() {
//			if (fund != null && fund.getPersonalDetails() != null) {
//				fund.getPersonalDetails().setIsFavorite(!fund.getPersonalDetails().isIsFavorite());
//				updateData();
//				EventBus.getDefault().post(new OnDashboardFundFavoriteClickedEvent(fund.getId(), fund.getPersonalDetails().isIsFavorite()));
//			}
		}

		private void setFonts() {
			fundName.setTypeface(TypefaceUtil.semibold());
			managerName.setTypeface(TypefaceUtil.medium());
			profitPercent.setTypeface(TypefaceUtil.semibold());
			value.setTypeface(TypefaceUtil.semibold());
			investors.setTypeface(TypefaceUtil.semibold());
			drawdown.setTypeface(TypefaceUtil.semibold());

			valueLabel.setText(valueLabel.getText().toString().toLowerCase());
			investorsLabel.setText(investorsLabel.getText().toString().toLowerCase());
			drawdownLabel.setText(drawdownLabel.getText().toString().toLowerCase());

			nameAsset1.setTypeface(TypefaceUtil.semibold());
			nameAsset2.setTypeface(TypefaceUtil.semibold());
			nameAsset3.setTypeface(TypefaceUtil.semibold());
			assetsLeft.setTypeface(TypefaceUtil.semibold());
		}

//		void setFund(FundDetails fund) {
//			this.fund = fund;
//			updateData();
//		}

//		private void updateData() {
//			fundLogo.setImage(fund.getLogo(), fund.getColor(), 100, 100);
//			fundLogo.hideLevel();
//
//			if (fund.getPersonalDetails() != null) {
//				favorite.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, fund.getPersonalDetails().isIsFavorite()
//						? R.drawable.icon_favorite_fill
//						: R.drawable.icon_favorite));
//				favorite.setAlpha(fund.getPersonalDetails().isIsFavorite() ? 1f : 0.3f);
//			}
//
//			this.fundName.setText(fund.getTitle());
//			this.managerName.setText(fund.getManager().getUsername());
//
//			this.chart.setChart(fund.getChart());
//
//			Double profitPercent = fund.getStatistic().getProfitPercent();
//
//			this.profitPercent.setText(String.format(Locale.getDefault(), "%s%%",
//					StringFormatUtil.formatAmount(profitPercent, 0, 2)));
//			this.profitPercent.setTextColor(profitPercent >= 0
//					? ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen)
//					: ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));
//
//			this.value.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(fund.getPersonalDetails().getValue(), CurrencyEnum.GVT.toString())));
//
//			this.investors.setText(String.format(Locale.getDefault(), "%s",
//					StringFormatUtil.getShortenedAmount(fund.getStatistic().getInvestorsCount())));
//
//			this.drawdown.setText(String.format(Locale.getDefault(), "%s%%",
//					StringFormatUtil.formatAmount(fund.getStatistic().getDrawdownPercent(), 0, 2)));
//
//			updateAssets();
//
//		}
//
//		private void updateAssets() {
//			groupAsset1.setVisibility(View.INVISIBLE);
//			groupAsset2.setVisibility(View.INVISIBLE);
//			groupAsset3.setVisibility(View.INVISIBLE);
//			groupAssetsLeft.setVisibility(View.INVISIBLE);
//
//			try {
//				updateAsset(groupAsset1, iconAsset1, nameAsset1, fund.getTopFundAssets().get(0));
//				updateAsset(groupAsset2, iconAsset2, nameAsset2, fund.getTopFundAssets().get(1));
//				updateAsset(groupAsset3, iconAsset3, nameAsset3, fund.getTopFundAssets().get(2));
//				updateAssetsLeft();
//			} catch (IndexOutOfBoundsException e) {
//				Timber.d(e.getMessage());
//			} catch (NullPointerException e) {
//				Timber.e(e.getMessage());
//			}
//		}
//
//		private void updateAssetsLeft() {
//			int assetsLeft = fund.getTotalAssetsCount();
//			if (fund.getTopFundAssets() != null)
//				assetsLeft -= fund.getTopFundAssets().size();
//
//			if (assetsLeft > 0) {
//				groupAssetsLeft.setVisibility(View.VISIBLE);
//				this.assetsLeft.setText(String.format(Locale.getDefault(), "+%d", assetsLeft));
//
//			}
//		}
//
//		private void updateAsset(ViewGroup groupAsset, SimpleDraweeView iconAsset, TextView nameAsset, FundAssetPercent fundAsset) {
//			groupAsset.setVisibility(View.VISIBLE);
//			iconAsset.setImageURI(ImageUtils.getImageUri(fundAsset.getIcon()));
//			nameAsset.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(fundAsset.getPercent(), 0, 0)));
//		}

//		private Double getProfitPercent() {
//			Double first = fund.getChart().get(0).getValue();
//			Double last = fund.getChart().get(fund.getChart().size() - 1).getValue();
//
//			return Math.abs(first != 0 ? 100 / first * (first - last) : 0);
//		}
//
//		private Double getProfitValue() {
//			Double first = fund.getChart().get(0).getValue();
//			Double last = fund.getChart().get(fund.getChart().size() - 1).getValue();
//
//			return last - first;
//		}
	}
}
