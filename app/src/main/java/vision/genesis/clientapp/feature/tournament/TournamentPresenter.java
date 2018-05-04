package vision.genesis.clientapp.feature.tournament;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

@InjectViewState
public class TournamentPresenter extends MvpPresenter<TournamentView>
{
	@Inject
	public Context context;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void onResume() {

	}
}
