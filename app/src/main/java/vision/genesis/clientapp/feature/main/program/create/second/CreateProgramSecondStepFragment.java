package vision.genesis.clientapp.feature.main.program.create.second;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/07/2018.
 */

public class CreateProgramSecondStepFragment extends BaseFragment implements CreateProgramSecondStepView
{
	@BindView(R.id.spinner_brokers)
	public Spinner brokers;

	@BindView(R.id.spinner_leverages)
	public Spinner leverages;

	@BindView(R.id.edit_text_password)
	public EditText password;

	@BindView(R.id.edit_text_confirm_password)
	public EditText confirmPassword;

	@BindView(R.id.button_next)
	public View nextButton;

	@BindView(R.id.text_next_button)
	public TextView nextButtonText;

	@BindView(R.id.progress_bar_next)
	public ProgressBar nextProgressBar;

	@InjectPresenter
	CreateProgramSecondStepPresenter createProgramSecondStepPresenter;

	private Unbinder unbinder;

	private Subscription passwordTextChangeSubscription;

	private Subscription confirmPasswordTextChangeSubscription;

	@OnClick(R.id.button_next)
	public void onNextButtonClicked() {
		hideKeyboard();
		createProgramSecondStepPresenter.onNextButtonClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_program_second_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		nextButton.setEnabled(false);

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
		if (confirmPasswordTextChangeSubscription != null)
			confirmPasswordTextChangeSubscription.unsubscribe();
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
				.subscribe(text -> createProgramSecondStepPresenter.onPasswordChanged(text.toString()));
		confirmPasswordTextChangeSubscription = RxTextView.textChanges(confirmPassword)
				.subscribe(text -> createProgramSecondStepPresenter.onConfirmPasswordChanged(text.toString()));
	}

	private void setFonts() {
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		password.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
		}
	}

//	@Override
//	public void setBrokers(List<BrokerTradeServer> brokersList) {
//		BrokersArrayAdapter adapter = new BrokersArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, brokersList.toArray());
//		adapter.setDropDownViewResource(R.layout.spinner_two_lines_item);
//		brokers.setAdapter(adapter);
//		brokers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//		{
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				createProgramSecondStepPresenter.onBrokerSelected(position);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}

	@Override
	public void setLeverages(List<Integer> leveragesList) {
		ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, leveragesList);
		adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		leverages.setAdapter(adapter);
		leverages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				createProgramSecondStepPresenter.onLeverageSelected(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	@Override
	public void setNextButtonAvailability(boolean available) {
		nextButton.setEnabled(available);
	}


	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, nextButton);
	}

	@Override
	public void showNextProgress(boolean show) {
		nextProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		nextButtonText.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
		nextButton.setEnabled(!show);
	}

	@Override
	public void setConfirmError(String message) {
		confirmPassword.setError(message);
	}
}
