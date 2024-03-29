package vision.genesis.clientapp.feature.main.fund.structure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundAssetInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowCoinDetailsEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class FundStructureAdapter extends RecyclerView.Adapter<FundStructureAdapter.AssetViewHolder>
{
	public List<FundAssetInfo> assets = new ArrayList<>();

	@Override
	public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund_structure_asset, parent, false);
		return new AssetViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(AssetViewHolder holder, int position) {
		holder.setAsset(assets.get(position));
	}

	@Override
	public int getItemCount() {
		return assets.size();
	}

	public void setAssets(List<FundAssetInfo> assets) {
		this.assets.clear();
		this.assets.addAll(assets);
		notifyDataSetChanged();
	}

	static class AssetViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.amount)
		public TextView amount;

		@BindView(R.id.target_current)
		public TextView targetCurrent;

		private FundAssetInfo asset;

		AssetViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener((view) -> {
				if (asset != null) {
					EventBus.getDefault().post(new ShowCoinDetailsEvent(asset.getSymbol()));
				}
			});
		}

		void setAsset(FundAssetInfo asset) {
			this.asset = asset;

			icon.setImageURI(ImageUtils.getImageUri(asset.getLogoUrl()));
			name.setText(asset.getAsset());
			amount.setText(String.format(Locale.getDefault(), "%s", StringFormatUtil.formatAmount(asset.getCurrentAmount(), 2, 8)));
			targetCurrent.setText(String.format(Locale.getDefault(), "%s %% (%s %%)",
					StringFormatUtil.formatAmount(asset.getTarget(), 2, 2),
					StringFormatUtil.formatAmount(asset.getCurrent(), 2, 2)));
		}
	}
}
