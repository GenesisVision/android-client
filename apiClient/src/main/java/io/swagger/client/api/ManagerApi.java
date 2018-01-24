package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.NewInvestmentRequest;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagerApi
{
	/**
	 * @param request (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/account/newInvestmentRequest")
	Observable<UUID> apiManagerAccountNewInvestmentRequestPost(
			@retrofit2.http.Body NewInvestmentRequest request
	);

	/**
	 * @param investmentProgramId (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/manager/investment/close")
	Observable<Void> apiManagerInvestmentCloseGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId
	);

	/**
	 * @param investmentProgramId (required)
	 * @return Call&lt;InvestmentProgramViewModel&gt;
	 */
	@GET("api/manager/investment")
	Observable<InvestmentProgramViewModel> apiManagerInvestmentGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId
	);

}
