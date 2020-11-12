package vision.genesis.clientapp.feature.main.verification;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.UserVerificationStatus;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/11/2020.
 */

@InjectViewState
public class VerificationInfoPresenter extends MvpPresenter<VerificationInfoView>
{
	@Inject
	public Context context;

	private UserVerificationStatus verificationStatus;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		updateButton();
	}

	public void setData(UserVerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
		updateButton();
	}

	private void updateButton() {
		if (verificationStatus != null && context != null) {
			switch (verificationStatus) {
				case NOTVERIFIED:
				case REJECTED:
					getViewState().setVerifyButtonVisible(true);
					getViewState().setVerifyButtonEnabled(true);
					getViewState().setVerifyButtonText(context.getString(R.string.verify_to_remove_limit));
					break;
				case UNDERREVIEW:
					getViewState().setVerifyButtonVisible(true);
					getViewState().setVerifyButtonEnabled(false);
					getViewState().setVerifyButtonText(context.getString(R.string.under_review));
					break;
				case VERIFIED:
					getViewState().setVerifyButtonVisible(false);
					break;
			}

		}
	}
}
