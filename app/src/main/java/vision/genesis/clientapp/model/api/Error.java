package vision.genesis.clientapp.model.api;
/**
 * GenesisVision
 * Created by Vitaly on 1/23/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error
{
	@SerializedName("message")
	@Expose
	public String message;

	@SerializedName("property")
	@Expose
	public String property;
}