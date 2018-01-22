package vision.genesis.clientapp.feature.splashscreen;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView>
{
	@Inject
	public Context context;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showMainActivity();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}