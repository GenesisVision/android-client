package vision.genesis.clientapp.managers;

import java.io.File;

import io.swagger.client.api.FilesApi;
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
	private FilesApi filesApi;

	public FilesManager(FilesApi filesApi) {
		this.filesApi = filesApi;
	}

	public Observable<UploadResult> uploadFile(File file) {
		return filesApi.apiFilesUploadPost(RequestBody.create(MediaType.parse("multipart/form-data"), file));
	}
}
