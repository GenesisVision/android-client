package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/05/2018.
 */
public class AppUpdateModel
{
	public boolean needUpdate;

	private String versionName;

	private int versionCode;

	public AppUpdateModel(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionName() {
		return versionName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
}
