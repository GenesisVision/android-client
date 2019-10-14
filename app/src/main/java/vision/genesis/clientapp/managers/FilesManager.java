package vision.genesis.clientapp.managers;

import java.io.File;

import io.swagger.client.api.FileApi;
import io.swagger.client.model.UploadResult;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2018.
 */

public class FilesManager
{
	private FileApi fileApi;

	public FilesManager(FileApi fileApi) {
		this.fileApi = fileApi;
	}

	public Observable<UploadResult> uploadFile(File file) {
		return fileApi.uploadFile(RequestBody.create(MediaType.parse("multipart/form-data"), file), AuthManager.token.getValue());
	}
}
