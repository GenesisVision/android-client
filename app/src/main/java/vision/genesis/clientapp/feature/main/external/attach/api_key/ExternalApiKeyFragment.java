package vision.genesis.clientapp.feature.main.external.attach.api_key;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BrokerDetails;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

public class ExternalApiKeyFragment extends BaseFragment implements ExternalApiKeyView
{
	private static final String EXTRA_SELECTED_BROKER = "extra_selected_broker";

	public static ExternalApiKeyFragment with(BrokerDetails selectedBroker) {
		ExternalApiKeyFragment fragment = new ExternalApiKeyFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_SELECTED_BROKER, selectedBroker);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.api_key)
	public EditText apiKey;

	@BindView(R.id.api_secret)
	public EditText apiSecret;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public ExternalApiKeyPresenter presenter;

	private Unbinder unbinder;

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		hideSoftKeyboard();
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_external_api_key, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		confirmButton.setEnabled(false);

		setTextListeners();

		if (getArguments() != null) {
			BrokerDetails selectedBroker = getArguments().getParcelable(EXTRA_SELECTED_BROKER);
			updateView(selectedBroker);
			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(BrokerDetails selectedBroker) {
		if (selectedBroker != null) {
			this.stepGroup.setVisibility(View.GONE);
			this.brokerLogo.setVisibility(View.VISIBLE);

			this.brokerLogo.setImageURI(ImageUtils.getImageUri(selectedBroker.getLogoUrl()));
		}
		else {
			this.stepGroup.setVisibility(View.VISIBLE);
			this.brokerLogo.setVisibility(View.GONE);
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setTextListeners() {
		RxTextView.textChanges(apiKey)
				.subscribe(charSequence -> presenter.onApiKeyChanged(charSequence.toString()));
		RxTextView.textChanges(apiSecret)
				.subscribe(charSequence -> presenter.onApiSecretChanged(charSequence.toString()));
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	private void hideSoftKeyboard() {
		if (getContext() != null) {
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			apiKey.clearFocus();
			apiSecret.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(apiKey.getWindowToken(), 0);
			}
		}
	}
}