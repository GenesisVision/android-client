package vision.genesis.clientapp.net.kyc;

import io.swagger.client.model.ExternalKycAccessToken;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/11/2020.
 */
public interface KycApi
{
	/**
	 * @return Call&lt;ExternalKycAccessToken&gt;
	 */
	@POST("v2.0/profile/verification/mobile/token")
	Call<ExternalKycAccessToken> getMobileVerificationToken();

}
