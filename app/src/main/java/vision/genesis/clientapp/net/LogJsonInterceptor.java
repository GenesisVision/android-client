package vision.genesis.clientapp.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;

/**
 * GenesisVision
 * Created by Vitaly on 1/23/18.
 */

public class LogJsonInterceptor implements Interceptor
{
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Response response = chain.proceed(request);
		String rawJson = response.body().string();

		NetworkManager.serverAvailabilitySubject.onNext(response.code() < 500 && response.code() >= 200);
		if (!BuildConfig.PROD) {
			Timber.tag("REST");
			Timber.d("REQUEST %s\nRESPONSE %s", request.url(), rawJson);
		}
		// Re-create the response before returning it because body can be read only once
		return response.newBuilder()
				.body(ResponseBody.create(response.body().contentType(), rawJson)).build();
	}
}