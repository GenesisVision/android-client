package vision.genesis.clientapp.feature.main.search;

import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.SearchViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SearchManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView>
{
	private static final Integer TAKE = 10;

	@Inject
	public SearchManager searchManager;

	private Subscription searchSubscription;

	private Handler searchHandler = new Handler();

	private String mask;

	private Runnable searchRunnable = () -> {
		performSearch(mask);
	};

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (searchSubscription != null)
			searchSubscription.unsubscribe();

		super.onDestroy();
	}

	void onMaskChanged(String mask) {
		if (mask != null && !mask.trim().isEmpty() && searchHandler != null) {
			searchHandler.removeCallbacks(searchRunnable);
			this.mask = mask;
			searchHandler.postDelayed(searchRunnable, 200);
		}
	}

	void onSearchClicked(String mask) {
		getViewState().showProgressBar(true);
		performSearch(mask);
	}

	private void performSearch(String mask) {
		if (searchSubscription != null)
			searchSubscription.unsubscribe();
		searchSubscription = searchManager.search(mask, TAKE)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleSearchSuccess,
						this::handleSearchError);
	}

	private void handleSearchSuccess(SearchViewModel result) {
		searchSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		getViewState().sendSearchResults(result);
	}

	private void handleSearchError(Throwable throwable) {
		searchSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
