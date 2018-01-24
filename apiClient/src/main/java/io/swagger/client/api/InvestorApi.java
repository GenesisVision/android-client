package io.swagger.client.api;

import io.swagger.client.model.InvestViewModel;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.ProfileShortViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * @return Call&lt;InvestorDashboard&gt;
	 */
	@GET("api/investor/dashboard")
	Observable<InvestorDashboard> apiInvestorDashboardGet();


	/**
	 * @param model (optional)
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments/invest")
	Observable<ProfileShortViewModel> apiInvestorInvestmentsInvestPost(
			@retrofit2.http.Body InvestViewModel model
	);

	/**
	 * @param filter (optional)
	 * @return Call&lt;InvestmentProgramsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments")
	Observable<InvestmentProgramsViewModel> apiInvestorInvestmentsPost(
			@retrofit2.http.Body InvestmentsFilter filter
	);

	/**
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments/withdraw")
	Observable<Void> apiInvestorInvestmentsWithdrawPost(
			@retrofit2.http.Body InvestViewModel model
	);

}
