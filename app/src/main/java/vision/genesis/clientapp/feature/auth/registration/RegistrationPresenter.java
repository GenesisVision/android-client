package vision.genesis.clientapp.feature.auth.registration;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.Screens;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView>
{
	@Inject
	public Context context;

	@Inject
	public Router router;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void onBackClicked() {
		router.backTo(Screens.LOGIN);
	}
}
