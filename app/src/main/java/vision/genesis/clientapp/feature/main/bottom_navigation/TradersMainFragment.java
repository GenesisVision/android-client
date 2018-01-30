package vision.genesis.clientapp.feature.main.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.main.traders.TradersFragment;
import vision.genesis.clientapp.feature.main.traders.details.TraderDetailsFragment;
import vision.genesis.clientapp.feature.main.traders.filter.TradersFiltersFragment;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.navigation.ScreenResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public class TradersMainFragment extends TabContainerFragment
{
	private ScreenResolver screenResolver = (screenKey, data) -> {
		switch (screenKey) {
			case Screens.TRADERS:
				return new TradersFragment();
			case Screens.TRADERS_FILTERS:
				return new TradersFiltersFragment();
			case Screens.TRADER_DETAILS:
				return TraderDetailsFragment.with((InvestmentProgram) data);
		}
		return null;
	};

	@Override
	protected ScreenResolver getScreenResolver() {
		return screenResolver;
	}

	@Override
	protected String getContainerName() {
		return "TRADERS";
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (getChildFragmentManager().findFragmentById(R.id.container) == null) {
			getCicerone().getRouter().replaceScreen(Screens.TRADERS, 0);
		}
	}
}
