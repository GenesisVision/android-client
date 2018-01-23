package vision.genesis.clientapp.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * GenesisVision
 * Created by Vitaly on 1/23/18.
 */

public class ErrorResponse
{
	@SerializedName("errors")
	@Expose
	public List<Error> errors = null;

	@SerializedName("code")
	@Expose
	public long code;

}