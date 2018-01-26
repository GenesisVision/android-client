package vision.genesis.clientapp.net;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ApiErrorResolver
{
	public static boolean isNetworkError(Throwable throwable) {
		try {
			RetrofitException error = RetrofitException.asRetrofitException(throwable);
			if (error.getKind().equals(RetrofitException.Kind.NETWORK)) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
