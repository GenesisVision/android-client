package io.swagger.client.api;

import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments/invest")
	Observable<Void> apiInvestorInvestmentsInvestPost(
			@retrofit2.http.Body Invest model
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

}
