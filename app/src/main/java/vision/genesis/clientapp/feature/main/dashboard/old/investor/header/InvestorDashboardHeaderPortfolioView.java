package vision.genesis.clientapp.feature.main.dashboard.old.investor.header;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */

interface InvestorDashboardHeaderPortfolioView extends MvpView
{
	void hideRequests();

	void showRequests();

	void setBalance(String gvtBalance, String baseBalance);

	void setChange(Boolean isChangeNegative, String changePercent, String changeValue, String baseChangeValue);

	void hideHighlight();

	void setInRequests(String inRequests, String baseInRequests);
}
