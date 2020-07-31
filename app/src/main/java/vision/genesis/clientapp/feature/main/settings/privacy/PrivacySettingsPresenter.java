package vision.genesis.clientapp.feature.main.settings.privacy;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.SocialViewMode;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

@InjectViewState
public class PrivacySettingsPresenter extends MvpPresenter<PrivacySettingsView>
{
	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	private Subscription profileSubscription;

	private Subscription updateSettingsSubscription;

	private SocialViewMode whoCanPostToMyWall = SocialViewMode.ALLUSERS;

	private SocialViewMode whoCanViewCommentsOnMyPost = SocialViewMode.ALLUSERS;

	private SocialViewMode whoCanCommentOnMyPost = SocialViewMode.ALLUSERS;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initOptions();
		getProfileInfo();
	}

	private void initOptions() {
		getViewState().setWhoCanPostToMyWallOptions(StringFormatUtil.getSocialViewModeOptions());
		getViewState().setWhoCanViewCommentsOnMyPostOptions(StringFormatUtil.getSocialViewModeOptions());
		getViewState().setWhoCanCommentOnMyPostOptions(StringFormatUtil.getSocialViewModeOptions());
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}
		if (updateSettingsSubscription != null) {
			updateSettingsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onWhoCanPostToMyWallSelected(Integer position, String text) {
		this.whoCanPostToMyWall = SocialViewMode.values()[position];
		getViewState().setWhoCanPostToMyWall(text, position);
	}

	void onWhoCanViewCommentsOnMyPostSelected(Integer position, String text) {
		this.whoCanViewCommentsOnMyPost = SocialViewMode.values()[position];
		getViewState().setWhoCanViewCommentsOnMyPost(text, position);
	}

	void onWhoCanCommentOnMyPostSelected(Integer position, String text) {
		this.whoCanCommentOnMyPost = SocialViewMode.values()[position];
		getViewState().setWhoCanCommentOnMyPost(text, position);
	}

	void onUpdateClicked() {
		updateSocialSettings();
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull(false)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		int whoCanPostToMyWallPos = 0;
		for (SocialViewMode value : SocialViewMode.values()) {
			if (value.getValue().equals(profile.getWhoCanPostToMayWall().getValue())) {
				this.whoCanPostToMyWall = value;
				break;
			}
			whoCanPostToMyWallPos++;
		}
		onWhoCanPostToMyWallSelected(whoCanPostToMyWallPos, StringFormatUtil.getSocialViewModeString(whoCanPostToMyWall));

		int whoCanViewCommentsOnMyPostPos = 0;
		for (SocialViewMode value : SocialViewMode.values()) {
			if (value.getValue().equals(profile.getWhoCanViewCommentsOnMyPosts().getValue())) {
				this.whoCanViewCommentsOnMyPost = value;
				break;
			}
			whoCanViewCommentsOnMyPostPos++;
		}
		onWhoCanViewCommentsOnMyPostSelected(whoCanViewCommentsOnMyPostPos, StringFormatUtil.getSocialViewModeString(whoCanViewCommentsOnMyPost));

		int whoCanCommentOnMyPostPos = 0;
		for (SocialViewMode value : SocialViewMode.values()) {
			if (value.getValue().equals(profile.getWhoCanCommentOnMyPosts().getValue())) {
				this.whoCanCommentOnMyPost = value;
				break;
			}
			whoCanCommentOnMyPostPos++;
		}
		onWhoCanCommentOnMyPostSelected(whoCanCommentOnMyPostPos, StringFormatUtil.getSocialViewModeString(whoCanCommentOnMyPost));

		getViewState().showProgress(false);
	}

	private void handleGetProfileError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void updateSocialSettings() {
		if (profileManager != null) {
			getViewState().showButtonProgress(true);
			updateSettingsSubscription = profileManager.updateSocialSettings(
					whoCanPostToMyWall,
					whoCanViewCommentsOnMyPost,
					whoCanCommentOnMyPost)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updateSettingsSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updateSettingsSubscription.unsubscribe();
		getViewState().showButtonProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}