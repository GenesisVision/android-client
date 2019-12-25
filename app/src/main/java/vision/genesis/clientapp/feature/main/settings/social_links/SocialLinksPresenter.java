package vision.genesis.clientapp.feature.main.settings.social_links;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.SocialLinkViewModel;
import io.swagger.client.model.SocialLinksViewModel;
import io.swagger.client.model.UpdateSocialLinkViewModel;
import io.swagger.client.model.UpdateSocialLinksViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/12/2019.
 */

@InjectViewState
public class SocialLinksPresenter extends MvpPresenter<SocialLinksView>
{
	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	private Subscription getSocialLinksSubscription;

	private Subscription updateSocialLinksSubscription;

	private List<SocialLinkViewModel> socialLinks = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getSocialLinks();
	}

	@Override
	public void onDestroy() {
		if (getSocialLinksSubscription != null) {
			getSocialLinksSubscription.unsubscribe();
		}
		if (updateSocialLinksSubscription != null) {
			updateSocialLinksSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSaveChangesButtonClicked() {
		updateSocialLinks();
	}

	private void getSocialLinks() {
		getViewState().showProgressBar(true);
		getSocialLinksSubscription = profileManager.getSocialLinks()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetSocialLinksResponse,
						this::handleGetSocialLinksError);
	}

	private void handleGetSocialLinksResponse(SocialLinksViewModel response) {
		getSocialLinksSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		socialLinks = response.getSocialLinks();

		getViewState().showSocialLinks(socialLinks);
	}

	private void handleGetSocialLinksError(Throwable throwable) {
		getSocialLinksSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void updateSocialLinks() {
		getViewState().showButtonProgressBar(true);

		UpdateSocialLinksViewModel model = new UpdateSocialLinksViewModel();
		List<UpdateSocialLinkViewModel> links = new ArrayList<>();
		for (SocialLinkViewModel socialLink : socialLinks) {
			UpdateSocialLinkViewModel link = new UpdateSocialLinkViewModel();
			link.setType(socialLink.getType());
			link.setValue(socialLink.getValue());
			links.add(link);
		}
		model.setLinks(links);

		updateSocialLinksSubscription = profileManager.updateSocialLinks(model)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleUpdateSocialLinksResponse,
						this::handleUpdateSocialLinksError);
	}

	private void handleUpdateSocialLinksResponse(Void response) {
		updateSocialLinksSubscription.unsubscribe();

		getViewState().finishActivity();
	}

	private void handleUpdateSocialLinksError(Throwable throwable) {
		updateSocialLinksSubscription.unsubscribe();
		getViewState().showButtonProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
