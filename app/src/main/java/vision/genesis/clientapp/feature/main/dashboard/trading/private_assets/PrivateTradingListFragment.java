package vision.genesis.clientapp.feature.main.dashboard.trading.private_assets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardTradingAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.TradingAssetDashboardShortView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

public class PrivateTradingListFragment extends BaseFragment
{
	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.scroll_view)
	public ScrollView scrollView;

	@BindView(R.id.group_items)
	public ViewGroup itemsGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	private Unbinder unbinder;

	private List<DashboardTradingAsset> items = null;

	private String baseCurrency = null;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_private_trading_list, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (items != null) {
			setItems(items, baseCurrency);
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	public void setItems(List<DashboardTradingAsset> items, String baseCurrency) {
		this.items = items;
		this.baseCurrency = baseCurrency;
		if (itemsGroup != null) {
			itemsGroup.removeAllViews();
			int index = 0;
			for (DashboardTradingAsset item : items) {
				TradingAssetDashboardShortView view = new TradingAssetDashboardShortView(getContext());
				view.setData(item, baseCurrency);
				itemsGroup.addView(view);
				if (index == items.size() - 1) {
					view.removeDelimiter();
				}
				index++;
			}
			scrollView.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			emptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
		}
	}
}
