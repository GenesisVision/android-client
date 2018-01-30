package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class DashboardPresenter extends MvpPresenter<DashboardView>
{
	@Inject
	public Context context;

	private Router localRouter;

	public DashboardPresenter(Router router) {
		this.localRouter = router;
	}
	
	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}
}
