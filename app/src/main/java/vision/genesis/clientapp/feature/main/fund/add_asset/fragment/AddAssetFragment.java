package vision.genesis.clientapp.feature.main.fund.add_asset.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.PlatformAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2020.
 */

public class AddAssetFragment extends BaseFragment
{
	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.text_no_assets)
	public TextView noAssetsText;

	private AddAssetListAdapter adapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_add_asset, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(false);

		adapter = new AddAssetListAdapter();
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	public void setAssets(List<PlatformAsset> assets) {
		noAssetsText.setVisibility(assets.isEmpty() ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(assets.isEmpty() ? View.GONE : View.VISIBLE);
		adapter.setAssets(assets);
	}

}
