package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.FilesApi;
import vision.genesis.clientapp.managers.FilesManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2018.
 */

@Module
public class FilesModule
{
	@Provides
	@Singleton
	public FilesManager provideFilesManager(FilesApi filesApi) {
		return new FilesManager(filesApi);
	}
}
