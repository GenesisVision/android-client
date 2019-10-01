package vision.genesis.clientapp.feature.main.fund.reallocate_history.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundAssetPartWithIcon;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */

public class FundReallocationAdapter extends RecyclerView.Adapter<FundReallocationAdapter.AssetViewHolder>
{
	public List<FundAssetPartWithIcon> assets = new ArrayList<>();

	@Override
	public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund_reallocation, parent, false);
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

	public void setAssets(List<FundAssetPartWithIcon> assets) {
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

		@BindView(R.id.percent)
		public TextView percent;

		AssetViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setAsset(FundAssetPartWithIcon asset) {
			icon.setImageURI(ImageUtils.getImageUri(asset.getIcon()));
			name.setText(asset.getName());
			percent.setText(String.format(Locale.getDefault(), "%s %%", StringFormatUtil.formatAmount(asset.getPercent(), 0, 2)));
		}
	}
}