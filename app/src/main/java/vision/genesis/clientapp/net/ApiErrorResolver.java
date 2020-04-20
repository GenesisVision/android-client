package vision.genesis.clientapp.net;

import androidx.annotation.NonNull;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ApiErrorResolver
{
	public interface ResultListener
	{
		void onResult(String message);
	}

	public static boolean isNetworkError(Throwable throwable) {
		try {
			RetrofitException error = RetrofitException.asRetrofitException(throwable);
			return error.getKind().equals(RetrofitException.Kind.NETWORK) && !throwable.getMessage().equals("timeout");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void resolveErrors(@NonNull Throwable throwable, @NonNull ResultListener listener) {
		if (ApiErrorResolver.isNetworkError(throwable)) {
			listener.onResult(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.message != null) {
						listener.onResult(error.message);
						break;
					}
				}
			}
		}
	}
}
