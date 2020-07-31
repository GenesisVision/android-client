package vision.genesis.clientapp.feature.main.social.post.report;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

@InjectViewState
public class ReportPostPresenter extends MvpPresenter<ReportPostView>
{
	@Inject
	public SocialManager socialManager;

	private Subscription sendReportSubscription;

	private String reason;

	private String description;

	private UUID postId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (sendReportSubscription != null) {
			sendReportSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID postId) {
		this.postId = postId;
	}

	void onReasonClicked(String reason) {
		this.reason = reason.replaceAll(" ", "-").toLowerCase();
		getViewState().setButtonEnabled(true);
	}

	void onDescriptionChanged(String description) {
		this.description = description;
	}

	void onSendClicked() {
		sendReport();
	}

	private void sendReport() {
		if (socialManager != null && postId != null) {
			getViewState().showButtonProgress(true);
			sendReportSubscription = socialManager.reportPost(postId, reason, description)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleSendReportSuccess,
							this::handleSendReportError);
		}
	}

	private void handleSendReportSuccess(Void response) {
		sendReportSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleSendReportError(Throwable throwable) {
		sendReportSubscription.unsubscribe();
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}