package vision.genesis.clientapp.feature.two_factor.setup.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.events.SetupTfaNextButtonClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public class SetupTfaSecondStepFragment extends BaseFragment
{
	@BindView(R.id.button_next)
	public View nextButton;

	private Unbinder unbinder;

	@OnClick(R.id.button_next)
	public void onNextButtonClicked() {
		EventBus.getDefault().post(new SetupTfaNextButtonClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setup_tfa_second_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
	}
}
