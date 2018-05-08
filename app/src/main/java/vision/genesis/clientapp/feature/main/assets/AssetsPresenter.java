package vision.genesis.clientapp.feature.main.assets;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.events.SetFavoritesTabCountEvent;
import vision.genesis.clientapp.model.events.SetProgramsTabCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/05/2018.
 */

@InjectViewState
public class AssetsPresenter extends MvpPresenter<AssetsView>
{
	@Inject
	public Context context;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {

	}

	void onSearchTextChanged(String text) {
//		if (text.isEmpty())
//			text = null;
//		if (filter == null
//				|| (filter.getName() != null && filter.getName().equals(text))
//				|| ((filter.getName() == null) && (text == null))
//				)
//			return;
//		filter.setName(text);
//		investManager.setFilter(filter);
	}

	@Subscribe
	public void onEventMainThread(SetFavoritesTabCountEvent event) {
		getViewState().setFavoritesTabCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetProgramsTabCountEvent event) {
		getViewState().setProgramsTabCount(event.getCount());
	}
}
