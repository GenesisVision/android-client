package vision.genesis.clientapp.feature.auth.email_verification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class EmailVerificationFragment extends BaseFragment implements EmailVerificationView
{
	@InjectPresenter
	EmailVerificationPresenter emailVerificationPresenter;

	@OnClick(R.id.button_ok)
	public void onOkClicked() {
		emailVerificationPresenter.onOkClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_email_verification, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);
	}

	@Override
	public boolean onBackPressed() {
		emailVerificationPresenter.onBackPressed();
		return true;
	}
}