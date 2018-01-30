package vision.genesis.clientapp.feature.main.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;
import vision.genesis.clientapp.navigation.ScreenResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public class WalletMainFragment extends TabContainerFragment
{
	private ScreenResolver screenResolver = (screenKey, data) -> {
		switch (screenKey) {
			case Screens.WALLET:
				return new WalletFragment();
		}
		return null;
	};

	@Override
	protected ScreenResolver getScreenResolver() {
		return screenResolver;
	}

	@Override
	protected String getContainerName() {
		return "WALLET";
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (getChildFragmentManager().findFragmentById(R.id.container) == null) {
			getCicerone().getRouter().replaceScreen(Screens.WALLET, 0);
		}
	}
}
