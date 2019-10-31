package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.PlatformAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnAddAssetClickedEvent;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

public class AddAssetListAdapter extends RecyclerView.Adapter<AddAssetListAdapter.AssetViewHolder>
{
	private List<PlatformAsset> assets = new ArrayList<>();

	@NonNull
	@Override
	public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_add_asset, parent, false);
		return new AssetViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AssetViewHolder holder, int position) {
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

	public void setAssets(List<PlatformAsset> assets) {
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

		@BindView(R.id.ticker)
		public TextView ticker;

		private PlatformAsset asset;

		AssetViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> {
				if (asset != null) {
					EventBus.getDefault().post(new OnAddAssetClickedEvent(asset));
				}
			});
		}

		void setAsset(PlatformAsset asset) {
			this.asset = asset;

			this.icon.setImageURI(ImageUtils.getImageUri(asset.getIcon()));
			this.name.setText(asset.getName());
			this.ticker.setText(asset.getAsset());
		}
	}
}
