package vision.genesis.clientapp.feature.tournament;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

@InjectViewState
public class TournamentPresenter extends MvpPresenter<TournamentView>
{
	@Inject
	public Router router;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}