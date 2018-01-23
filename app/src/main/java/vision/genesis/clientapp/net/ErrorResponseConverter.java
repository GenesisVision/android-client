package vision.genesis.clientapp.net;

import com.google.gson.Gson;

import retrofit2.HttpException;
import vision.genesis.clientapp.model.api.ErrorResponse;

/**
 * GenesisVision
 * Created by Vitaly on 1/23/18.
 */

public class ErrorResponseConverter
{
	public static ErrorResponse createFromThrowable(Throwable error) {
		try {
			String jsonString = ((HttpException) error).response().errorBody().string();
			return new Gson().fromJson(jsonString, ErrorResponse.class);
		} catch (Exception e) {
			return null;
		}
	}
}
