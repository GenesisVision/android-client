package vision.genesis.clientapp.feature.main.profile;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	void onLogoutClicked() {
		authManager.logout();
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}
}
