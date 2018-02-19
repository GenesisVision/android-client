package vision.genesis.clientapp.feature.main.traders.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class TraderDetailsPresenter extends MvpPresenter<TraderDetailsView>
{
	@Inject
	public Context context;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}
}
