package vision.genesis.clientapp.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import vision.genesis.clientapp.managers.AuthManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/23/18.
 */

public class AuthenticationInterceptor implements Interceptor
{
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request originalRequest = chain.request();
		Request requestWithAuthorization = originalRequest.newBuilder()
				.header("Authorization", AuthManager.token.getValue() != null ? AuthManager.token.getValue() : "")
				.build();

		return chain.proceed(requestWithAuthorization);
	}
}