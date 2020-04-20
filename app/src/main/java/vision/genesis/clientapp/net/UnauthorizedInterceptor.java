package vision.genesis.clientapp.net;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

public class UnauthorizedInterceptor implements Interceptor
{
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Response response = chain.proceed(request);
		String rawJson = response.body().string();

		if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
			EventBus.getDefault().post(new OnUnauthorizedResponseGetEvent());
		}

		return response.newBuilder()
				.body(ResponseBody.create(response.body().contentType(), rawJson)).build();
	}
}