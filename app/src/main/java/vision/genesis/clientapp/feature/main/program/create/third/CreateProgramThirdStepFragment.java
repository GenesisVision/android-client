package vision.genesis.clientapp.feature.main.program.create.third;

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
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/07/2018.
 */

public class CreateProgramThirdStepFragment extends BaseFragment implements CreateProgramThirdStepView
{
	@BindView(R.id.spinner_period)
	public Spinner periods;

	@BindView(R.id.start_date)
	public EditText startDate;

	@BindView(R.id.edit_text_deposit)
	public EditText deposit;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.edit_text_success_fee)
	public EditText successFee;

	@BindView(R.id.max_success_fee)
	public TextView maxSuccessFee;

	@BindView(R.id.max_management_fee)
	public TextView maxManagementFee;

	@BindView(R.id.edit_text_management_fee)
	public EditText managementFee;

	@BindView(R.id.text_input_layout_token_symbol)
	public TextInputLayout tokenSymbolLayout;

	@BindView(R.id.edit_text_token_symbol)
	public EditText tokenSymbol;

	@BindView(R.id.text_input_layout_token_name)
	public TextInputLayout tokenNameLayout;

	@BindView(R.id.edit_text_token_name)
	public EditText tokenName;

	@BindView(R.id.button_create)
	public View createButton;

	@BindView(R.id.text_create_button)
	public TextView createButtonText;

	@BindView(R.id.progress_bar_create)
	public ProgressBar createProgressBar;

	@InjectPresenter
	CreateProgramThirdStepPresenter createProgramThirdStepPresenter;

	private Unbinder unbinder;

	private Subscription depositTextChangeSubscription;

	private Subscription successFeeTextChangeSubscription;

	private Subscription managementFeeTextChangeSubscription;

	private Subscription tokenNameTextChangeSubscription;

	private Subscription tokenSymbolTextChangeSubscription;

	@OnClick(R.id.button_start_date)
	public void onStartDateButtonClicked() {
		hideKeyboard();
		createProgramThirdStepPresenter.onStartDateButtonClicked();
	}

	@OnClick(R.id.button_create)
	public void onCreateButtonClicked() {
		hideKeyboard();
		createProgramThirdStepPresenter.onCreateButtonClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_program_third_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		createButton.setEnabled(false);

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
		if (depositTextChangeSubscription != null)
			depositTextChangeSubscription.unsubscribe();
		if (successFeeTextChangeSubscription != null)
			successFeeTextChangeSubscription.unsubscribe();
		if (managementFeeTextChangeSubscription != null)
			managementFeeTextChangeSubscription.unsubscribe();
		if (tokenNameTextChangeSubscription != null)
			tokenNameTextChangeSubscription.unsubscribe();
		if (tokenSymbolTextChangeSubscription != null)
			tokenSymbolTextChangeSubscription.unsubscribe();
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
		depositTextChangeSubscription = RxTextView.textChanges(deposit)
				.subscribe(text -> createProgramThirdStepPresenter.onDepositChanged(text.toString()));
		successFeeTextChangeSubscription = RxTextView.textChanges(successFee)
				.subscribe(text -> createProgramThirdStepPresenter.onSuccessFeeChanged(text.toString()));
		managementFeeTextChangeSubscription = RxTextView.textChanges(managementFee)
				.subscribe(text -> createProgramThirdStepPresenter.onManagementFeeChanged(text.toString()));
		tokenNameTextChangeSubscription = RxTextView.textChanges(tokenName)
				.subscribe(text -> createProgramThirdStepPresenter.onTokenNameChanged(text.toString()));
		tokenSymbolTextChangeSubscription = RxTextView.textChanges(tokenSymbol)
				.subscribe(text -> createProgramThirdStepPresenter.onTokenSymbolChanged(text.toString()));
	}

	private void setFonts() {
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		tokenName.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(tokenName.getWindowToken(), 0);
		}
	}

	@Override
	public void setPeriods(List<Integer> periodsList) {
		ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, periodsList);
		adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		periods.setAdapter(adapter);
		periods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				createProgramThirdStepPresenter.onPeriodSelected(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	@Override
	public void showDatePicker() {
		DateTime calendarDate = DateTime.now();
		DatePickerDialog dpd = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
			DateTime newStartDate = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
			showTimePicker(newStartDate);
		}, calendarDate.getYear(), calendarDate.getMonthOfYear() - 1, calendarDate.getDayOfMonth());
		dpd.setMinDate(DateTime.now().toCalendar(Locale.getDefault()));
		if (getActivity() != null)
			dpd.show(getActivity().getFragmentManager(), "DatePickerDialog");
	}

	private void showTimePicker(DateTime newStartDate) {
		TimePickerDialog tpd = TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
			DateTime newStartDateTime = newStartDate.withHourOfDay(hourOfDay).withMinuteOfHour(minute);
			setStartDate(newStartDateTime);
			createProgramThirdStepPresenter.onStartDateChanged(newStartDateTime);
		}, true);
		tpd.setTimeInterval(1, 30);
		if (getActivity() != null)
			tpd.show(getActivity().getFragmentManager(), "TimePickerDialog");
	}

	private void setStartDate(DateTime newStartDate) {
		startDate.setText(DateTimeUtil.formatShortDateTime(newStartDate));
	}

	@Override
	public void setCreateButtonAvailability(boolean available) {
		createButton.setEnabled(available);
	}


	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, createButton);
	}

	@Override
	public void showCreateProgress(boolean show) {
		createProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		createButtonText.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
		createButton.setEnabled(!show);
	}

	@Override
	public void setLimits(CreateProgramData createProgramData) {
		maxSuccessFee.setText(String.format(Locale.getDefault(), "max. %d%%", createProgramData.getMaxFeeSuccess()));
		maxManagementFee.setText(String.format(Locale.getDefault(), "max. %d%%", createProgramData.getMaxFeeManagement()));
		tokenSymbolLayout.setCounterMaxLength(createProgramData.getMaxTokenSymbolLength());
		tokenNameLayout.setCounterMaxLength(createProgramData.getMaxTokenNameLength());
	}

	@Override
	public void setBalance(Double balance) {
		this.balance.setText(String.format(Locale.getDefault(), "%s %f GVT", getString(R.string.you_have), balance));
	}
}
