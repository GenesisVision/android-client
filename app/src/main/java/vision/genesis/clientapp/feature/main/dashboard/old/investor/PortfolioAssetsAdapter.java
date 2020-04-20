package vision.genesis.clientapp.feature.main.dashboard.old.investor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.PortfolioAssetData;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/09/2018.
 */

public class PortfolioAssetsAdapter extends RecyclerView.Adapter<PortfolioAssetsAdapter.ProgramViewHolder>
{
	private List<PortfolioAssetData> assets = new ArrayList<>();

	@Override
	public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_portfolio_asset, parent, false);
		return new ProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ProgramViewHolder holder, int position) {
		if (assets.get(position) != null) {
			holder.setAsset(assets.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return assets.size();
	}

	@Override
	public long getItemId(int position) {
		return assets.get(position) != null
				? assets.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	public void setAssets(List<PortfolioAssetData> assets) {
		this.assets.clear();
		this.assets.addAll(assets);
		notifyDataSetChanged();
	}

	static class ProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.color_indicator)
		public View colorIndicator;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.change_percent)
		public TextView changePercent;

		@BindView(R.id.change_value)
		public TextView changeValue;

		private PortfolioAssetData asset;

		private Context context;

		ProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			this.context = itemView.getContext();

			setFonts();
			itemView.setOnClickListener(v -> {
				if (asset != null) {
//					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programInfoModel));
				}
			});
		}

		private void setFonts() {
			changePercent.setTypeface(TypefaceUtil.semibold());
		}

		void setAsset(PortfolioAssetData asset) {
			this.asset = asset;
			updateData();
		}

		private void updateData() {
			colorIndicator.setBackgroundColor(asset.getColorIndicator());
			name.setText(asset.getName());
			value.setText(asset.getValue());
			changePercent.setText(asset.getChangePercent());
			changePercent.setTextColor(asset.getChangePercentColor());
			changeValue.setText(asset.getChangeValue());
		}
	}
}
