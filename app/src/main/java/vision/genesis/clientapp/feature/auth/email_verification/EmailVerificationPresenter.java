package vision.genesis.clientapp.feature.auth.email_verification;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.model.events.ShowMainActivityEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

@InjectViewState
public class EmailVerificationPresenter extends MvpPresenter<EmailVerificationView>
{
	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	void onBackPressed() {
		showMainActivity();
	}

	void onOkClicked() {
		showMainActivity();
	}

	private void showMainActivity() {
		EventBus.getDefault().post(new ShowMainActivityEvent());
	}
}
