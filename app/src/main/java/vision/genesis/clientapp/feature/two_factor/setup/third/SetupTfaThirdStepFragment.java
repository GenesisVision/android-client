package vision.genesis.clientapp.feature.two_factor.setup.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public class SetupTfaThirdStepFragment extends BaseFragment implements SetupTfaThirdStepView
{
	@BindView(R.id.edit_text_password)
	public EditText password;

	@BindView(R.id.edit_text_code)
	public EditText code;

	@BindView(R.id.button_confirm)
	public View confirmButton;

	@InjectPresenter
	SetupTfaThirdStepPresenter setupTfaThirdStepPresenter;

	private Unbinder unbinder;

	private Subscription passwordTextChangeSubscription;

	private Subscription codeTextChangeSubscription;

	@OnClick(R.id.button_confirm)
	public void onConfirmButtonClicked() {
		setupTfaThirdStepPresenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setup_tfa_third_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		confirmButton.setEnabled(false);

		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter.LengthFilter(Constants.TWO_FACTOR_CODE_LENGTH);
		code.setFilters(filters);

		setFonts();
	}

	@Override
	public void onStart() {
		super.onStart();
		setTextListeners();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (passwordTextChangeSubscription != null)
			passwordTextChangeSubscription.unsubscribe();
		if (codeTextChangeSubscription != null)
			codeTextChangeSubscription.unsubscribe();
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
		passwordTextChangeSubscription = RxTextView.textChanges(password)
				.subscribe(text -> setupTfaThirdStepPresenter.onPasswordChanged(text.toString()));
		codeTextChangeSubscription = RxTextView.textChanges(code)
				.subscribe(text -> setupTfaThirdStepPresenter.onCodeChanged(text.toString()));
	}

	private void setFonts() {
	}

	@Override
	public void setConfirmButtonAvailability(boolean available) {
		confirmButton.setEnabled(available);
	}
}
