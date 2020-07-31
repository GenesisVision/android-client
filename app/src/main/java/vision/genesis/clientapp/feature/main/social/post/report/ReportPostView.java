package vision.genesis.clientapp.feature.main.social.post.report;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

public interface ReportPostView extends MvpView
{
	void setButtonEnabled(boolean enabled);

	void showButtonProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
