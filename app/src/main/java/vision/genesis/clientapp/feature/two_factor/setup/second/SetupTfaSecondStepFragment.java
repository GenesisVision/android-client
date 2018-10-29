package vision.genesis.clientapp.feature.two_factor.setup.second;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

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
 * Created by Vitaly on 30/05/2018.
 */

public class SetupTfaSecondStepFragment extends BaseFragment implements SetupTfaSecondStepView
{
	@BindView(R.id.text_key)
	public TextView keyText;

	@BindView(R.id.image_qrcode)
	public ImageView qrCodeImage;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_next)
	public View nextButton;

	@InjectPresenter
	SetupTfaSecondStepPresenter setupTfaSecondStepPresenter;

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

		nextButton.setEnabled(false);

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

	public void onSetKey(String sharedKey, String authenticatorUri) {
		if (setupTfaSecondStepPresenter != null)
			setupTfaSecondStepPresenter.onSetKey(getContext(), sharedKey, authenticatorUri);
	}

	@Override
	public void setKey(String key) {
		keyText.setText(key);
		progressBar.setVisibility(View.GONE);
		nextButton.setEnabled(true);
	}

	@Override
	public void setQrCode(Bitmap bitmap) {
		qrCodeImage.setImageBitmap(bitmap);
	}
}
