package vision.genesis.clientapp.feature.main.unregistered.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Random;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/08/2020.
 */

@InjectViewState
public class UnregisteredDashboardPresenter extends MvpPresenter<UnregisteredDashboardView>
{
	@Inject
	public Context context;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		updateQuote();
	}

	private void updateQuote() {
		String[] quotes = context.getResources().getStringArray(R.array.quote_texts);
		String[] authors = context.getResources().getStringArray(R.array.quote_authors);
		int pos = (new Random()).nextInt(quotes.length);
		getViewState().setQuote(quotes[pos], authors[pos]);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	void onResume() {
	}
}
