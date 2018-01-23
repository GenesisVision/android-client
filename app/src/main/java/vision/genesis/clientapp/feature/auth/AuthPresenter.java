package vision.genesis.clientapp.feature.auth;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.events.ShowMainActivityEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public Router router;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onEventMainThread(ShowMainActivityEvent event) {
		getViewState().showMainActivity();
	}
}