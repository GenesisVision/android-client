package vision.genesis.clientapp.feature.main.traders.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.Screens;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class TraderDetailsPresenter extends MvpPresenter<TraderDetailsView>
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
		router.backTo(Screens.TRADERS);
	}
}
