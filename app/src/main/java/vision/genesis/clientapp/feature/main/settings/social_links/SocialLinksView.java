package vision.genesis.clientapp.feature.main.settings.social_links;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.SocialLinkViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/12/2019.
 */

interface SocialLinksView extends MvpView
{
	void showSocialLinks(List<SocialLinkViewModel> socialLinks);

	void showProgressBar(boolean show);

	void showButtonProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
