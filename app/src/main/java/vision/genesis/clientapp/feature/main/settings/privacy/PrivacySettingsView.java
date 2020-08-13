package vision.genesis.clientapp.feature.main.settings.privacy;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

public interface PrivacySettingsView extends MvpView
{
	void setWhoCanPostToMyWallOptions(ArrayList<String> whoCanPostToMyWallOptions);

	void setWhoCanViewCommentsOnMyPostOptions(ArrayList<String> whoCanViewCommentsOnMyPostOptions);

	void setWhoCanCommentOnMyPostOptions(ArrayList<String> whoCanCommentOnMyPostOptions);

	void setWhoCanPostToMyWall(String text, Integer position);

	void setWhoCanViewCommentsOnMyPost(String text, Integer position);

	void setWhoCanCommentOnMyPost(String text, Integer position);

	void setWhoCanCommentOnMyPostEnabled(boolean enabled);

	void showProgress(boolean show);

	void showButtonProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
