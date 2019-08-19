package vision.genesis.clientapp.feature.common.date_range;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.DateTime;

import java.util.Locale;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2018.
 */

public class DateRangeBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnDateRangeChangedListener
	{
		void onDateRangeChanged(DateRange dateRange);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.day)
	public DateRangeOptionView day;

	@BindView(R.id.week)
	public DateRangeOptionView week;

	@BindView(R.id.month)
	public DateRangeOptionView month;

	@BindView(R.id.year)
	public DateRangeOptionView year;

	@BindView(R.id.all_time)
	public DateRangeOptionView allTime;

	@BindView(R.id.custom)
	public DateRangeOptionView custom;

	@BindView(R.id.label_date_from)
	public TextView dateFromLabel;

	@BindView(R.id.date_from)
	public TextView dateFrom;

	@BindView(R.id.label_date_to)
	public TextView dateToLabel;

	@BindView(R.id.date_to)
	public TextView dateTo;

	@BindView(R.id.button_apply)
	public PrimaryButton applyButton;

	private OnDateRangeChangedListener listener;

	private DateRangeOptionView selectedOption;

	private DateRange oldDateRange;

	private DateRange dateRange = new DateRange();

	@OnClick(R.id.day)
	public void onDayClicked() {
		onOptionButtonClicked(day);
	}

	@OnClick(R.id.week)
	public void onWeekClicked() {
		onOptionButtonClicked(week);
	}

	@OnClick(R.id.month)
	public void onMonthClicked() {
		onOptionButtonClicked(month);
	}

	@OnClick(R.id.year)
	public void onYearClicked() {
		onOptionButtonClicked(year);
	}

	@OnClick(R.id.all_time)
	public void onAllTImeClicked() {
		onOptionButtonClicked(allTime);
	}

	@OnClick(R.id.group_date_from)
	public void onDateFromClicked() {
		showDateFromPicker();
	}

	@OnClick(R.id.group_date_to)
	public void onDateToClicked() {
		showDateToPicker();
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		if (listener != null && selectedOption != null) {
			listener.onDateRangeChanged(dateRange);
			this.dismiss();
		}
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		getDialog().setOnShowListener(dialog1 -> {
			BottomSheetDialog d = (BottomSheetDialog) dialog1;
			View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null)
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_date_range, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		initOptions();
		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	private void initOptions() {
		day.setText(getString(R.string.day));
		week.setText(getString(R.string.week));
		month.setText(getString(R.string.month));
		year.setText(getString(R.string.year));
		allTime.setText(getString(R.string.all_time));
		custom.setText(getString(R.string.custom));
	}

	public void setListener(OnDateRangeChangedListener listener) {
		this.listener = listener;
	}

	public void setDateRange(@NonNull DateRange dateRange) {
		this.dateRange = DateRange.copy(dateRange);
		this.oldDateRange = dateRange;

		if (day != null)
			updateView();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void onOptionButtonClicked(DateRangeOptionView newOption) {
		selectOption(newOption);
		setDateRange(newOption);

		updateView();
	}

	private void selectOption(DateRangeOptionView newOption) {
		deselectPreviousOption();
		newOption.setSelected(true);
		selectedOption = newOption;
	}

	private void deselectPreviousOption() {
		if (selectedOption != null)
			selectedOption.setSelected(false);
	}

	private void setDateRange(DateRangeOptionView newOption) {
		DateRange.DateRangeEnum selected = DateRange.DateRangeEnum.fromValue(newOption.getText().toLowerCase().replace(" ", ""));
		if (selected != null) {
			dateRange.setSelectedRange(selected);
			dateRange.updateDates(selected);
		}
	}

	private void setFrom(DateTime newFrom) {
		dateRange.setSelectedRange(DateRange.DateRangeEnum.CUSTOM);
		dateRange.setFrom(newFrom);
		resetDateRangeTime();

		updateView();
	}

	private void setTo(DateTime newTo) {
		dateRange.setSelectedRange(DateRange.DateRangeEnum.CUSTOM);
		dateRange.setTo(newTo);
		resetDateRangeTime();

		updateView();
	}

	private void resetDateRangeTime() {
		dateRange.setFrom(dateRange.getFrom().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0));
		dateRange.setTo(dateRange.getTo().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59));
	}

	private void updateView() {
		updateSelections();
		updateDates();
		updateButtons();
	}

	private void updateSelections() {
		switch (dateRange.getSelectedRange()) {
			case DAY:
				selectOption(day);
				break;
			case WEEK:
				selectOption(week);
				break;
			case MONTH:
				selectOption(month);
				break;
			case YEAR:
				selectOption(year);
				break;
			case ALL_TIME:
				selectOption(allTime);
				break;
			case CUSTOM:
				selectOption(custom);
				break;
			default:
				break;
		}
	}

	private void updateDates() {
		if (dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.ALL_TIME)) {
			dateFrom.setText(getString(R.string.start));
		}
		else {
			dateFrom.setText(DateTimeUtil.formatShortDate(dateRange.getFrom()));
		}
		dateTo.setText(DateTimeUtil.formatShortDate(dateRange.getTo()));
	}

	private void updateButtons() {
//		applyButton.setEnabled(!dateRange.equals(oldDateRange));
	}

	public void showDateFromPicker() {
		DateTime dateFrom = dateRange.getFrom();
		if (dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.ALL_TIME))
			dateFrom = dateRange.getTo();
		DatePickerDialog dpd = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) ->
						setFrom(new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0, 0)),
				dateFrom.getYear(), dateFrom.getMonthOfYear() - 1, dateFrom.getDayOfMonth());
		dpd.setMaxDate(dateRange.getTo().toCalendar(Locale.getDefault()));
		if (getActivity() != null)
			dpd.show(getActivity().getFragmentManager(), "DateFromPickerDialog");
	}

	public void showDateToPicker() {
		DatePickerDialog dpd = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) ->
						setTo(new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0)),
				dateRange.getTo().getYear(), dateRange.getTo().getMonthOfYear() - 1, dateRange.getTo().getDayOfMonth());
		dpd.setMinDate(dateRange.getFrom().toCalendar(Locale.getDefault()));
		dpd.setMaxDate(DateTime.now().toCalendar(Locale.getDefault()));
		if (getActivity() != null)
			dpd.show(getActivity().getFragmentManager(), "DateFromPickerDialog");
	}
}
