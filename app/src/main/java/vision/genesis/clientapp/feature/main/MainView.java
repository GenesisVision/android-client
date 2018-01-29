package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public interface MainView extends MvpView
{
	void showBottomNavigation();

	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

	void showSignInButton();

	void hideSignInButton();

	void showAuthActivity();
}
