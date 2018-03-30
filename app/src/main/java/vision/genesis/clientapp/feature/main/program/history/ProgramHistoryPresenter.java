package vision.genesis.clientapp.feature.main.program.history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */

@InjectViewState
public class ProgramHistoryPresenter extends MvpPresenter<ProgramHistoryView>
{
	@Inject
	public Context context;


	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}
}
