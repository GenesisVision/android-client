package vision.genesis.clientapp.feature.main.bottom_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.navigation.LocalCiceroneHolder;
import vision.genesis.clientapp.navigation.ScreenResolver;
import vision.genesis.clientapp.ui.common.BackButtonListener;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public abstract class TabContainerFragment extends BaseFragment implements RouterProvider
{
	protected Navigator navigator;

	@Inject
	LocalCiceroneHolder ciceroneHolder;

	protected abstract ScreenResolver getScreenResolver();

	protected abstract String getContainerName();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		GenesisVisionApplication.getComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	protected Cicerone<Router> getCicerone() {
		return ciceroneHolder.getCicerone(getContainerName());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab_container, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		getCicerone().getNavigatorHolder().setNavigator(getNavigator());
	}

	@Override
	public void onPause() {
		getCicerone().getNavigatorHolder().removeNavigator();
		super.onPause();
	}

	private Navigator getNavigator() {
		if (navigator == null) {
			navigator = new SupportAppNavigator(getActivity(), getChildFragmentManager(), R.id.container)
			{
				@Override
				protected Intent createActivityIntent(String screenKey, Object data) {
					return null;
				}

				@Override
				protected Fragment createFragment(String screenKey, Object data) {
					return getScreenResolver().getFragment(screenKey, data);
				}

				@Override
				protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
//					fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
				}

				@Override
				protected void exit() {
					((RouterProvider) getActivity()).getRouter().exit();
				}
			};
		}
		return navigator;
	}

	@Override
	public Router getRouter() {
		return getCicerone().getRouter();
	}

	@Override
	public boolean onBackPressed() {
		Fragment fragment = getChildFragmentManager().findFragmentById(R.id.container);
		if (fragment != null
				&& fragment instanceof BackButtonListener
				&& ((BackButtonListener) fragment).onBackPressed()) {
			return true;
		}
		else {
			((RouterProvider) getActivity()).getRouter().exit();
			return true;
		}
	}
}
