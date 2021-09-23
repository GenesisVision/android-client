package vision.genesis.clientapp.feature.main.terminal.market_watch.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnFavoriteTickersSelectAccountClickedEvent;
import vision.genesis.clientapp.ui.PrimaryButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class FavoriteTickersListFragment extends TickersListFragment
{
	@BindView(R.id.button_select_account)
	public PrimaryButton selectAccountButton;

	@OnClick(R.id.button_select_account)
	public void onSelectAccountClicked() {
		EventBus.getDefault().post(new OnFavoriteTickersSelectAccountClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_favorite_tickers_list, container, false);
	}

	public void showProgress() {
		progressBar.setVisibility(View.VISIBLE);
		selectAccountButton.setVisibility(View.GONE);
	}
}