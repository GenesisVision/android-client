package vision.genesis.clientapp.feature.main.dashboard.investor.header;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardChartValue;
import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */

@InjectViewState
public class InvestorDashboardHeaderPortfolioPresenter extends MvpPresenter<InvestorDashboardHeaderPortfolioView>
{
	@Inject
	public Context context;

	private DashboardChartValue chartValue;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	void onShow() {
	}

	void setData(DashboardChartValue chartValue) {
		this.chartValue = chartValue;
	}
}
