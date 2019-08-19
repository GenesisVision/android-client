package vision.genesis.clientapp.feature.main.filters_sorting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.MinMaxFilterModel;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class FilterMinMaxBottomSheetFragment extends BottomSheetDialogFragment
{
	interface OnValuesChangedListener
	{
		void onValuesChanged(Integer minValue, Integer maxValue);
	}

	@BindView(R.id.filter_name)
	public TextView filterName;

	@BindView(R.id.errors)
	public TextView errors;

	@BindView(R.id.label_min_value)
	public TextView minValueLabel;

	@BindView(R.id.label_max_value)
	public TextView maxValueLabel;

	@BindView(R.id.edittext_min_value)
	public EditText minValueEditText;

	@BindView(R.id.edittext_max_value)
	public EditText maxValueEditText;

	@BindView(R.id.button_clear_min_value)
	public View clearMinValueButton;

	@BindView(R.id.button_clear_max_value)
	public View clearMaxValueButton;

	@BindView(R.id.button_apply)
	public ViewGroup applyButton;

	@BindView(R.id.button_close)
	public ViewGroup closeButton;

	private OnValuesChangedListener listener;

	private Integer minValue;

	private Integer maxValue;

	private MinMaxFilterModel model;

	private Subscription minValueTextChangeSubscription;

	private Subscription maxValueTextChangeSubscription;

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		if (listener != null) {
			listener.onValuesChanged(minValue, maxValue);
			this.dismiss();
		}
	}

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		this.dismiss();
	}

	@OnClick(R.id.button_clear_min_value)
	public void onClearMinValueClicked() {
		minValueEditText.setText(null);
	}

	@OnClick(R.id.button_clear_max_value)
	public void onClearMaxValueClicked() {
		maxValueEditText.setText(null);
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_filter_min_max, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		if (model != null)
			setValues(model);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null)
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
	}

	@Override
	public void onStart() {
		super.onStart();
		setTextListeners();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (minValueTextChangeSubscription != null)
			minValueTextChangeSubscription.unsubscribe();
		if (maxValueTextChangeSubscription != null)
			maxValueTextChangeSubscription.unsubscribe();
		hideSoftKeyboard(minValueEditText);
	}

	private void setTextListeners() {
		minValueTextChangeSubscription = RxTextView.textChanges(minValueEditText)
				.subscribe(text -> onMinValueTextChanged(text.toString()));
		maxValueTextChangeSubscription = RxTextView.textChanges(maxValueEditText)
				.subscribe(text -> onMaxValueTextChanged(text.toString()));
	}

	private void onMinValueTextChanged(String text) {
		try {
			minValue = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			minValue = null;
		}

		updateErrors();
		updateButtons();
	}

	private void onMaxValueTextChanged(String text) {
		try {
			maxValue = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			maxValue = null;
		}

		updateErrors();
		updateButtons();
	}

	public void setData(MinMaxFilterModel model) {
		this.model = model;

		if (minValueEditText != null)
			setValues(model);
	}

	private void setValues(MinMaxFilterModel model) {
		this.filterName.setText(model.getFilterName());
		this.minValueLabel.setText(model.getMinValueLabel());
		this.maxValueLabel.setText(model.getMaxValueLabel());

		this.minValue = model.getCurrentMinValue();
		this.maxValue = model.getCurrentMaxValue();

		if (minValue != null) {
			minValueEditText.setText(String.valueOf(minValue));
		}
		if (maxValue != null) {
			maxValueEditText.setText(String.valueOf(maxValue));
		}
	}

	public void setListener(OnValuesChangedListener listener) {
		this.listener = listener;
	}

	private void setFonts() {
		filterName.setTypeface(TypefaceUtil.bold());
	}

	private void updateErrors() {
		String errorsString = "";
		if (minValue == null && minValueEditText.getText().length() > 0) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "%s %s",
					model.getMinValueLabel(),
					getString(R.string.should_be_a_number)));
		}
		if (maxValue == null && maxValueEditText.getText().length() > 0) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s",
					model.getMaxValueLabel(),
					getString(R.string.should_be_a_number)));
		}

		if (minValue != null && maxValue != null && minValue > maxValue) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s %s",
					model.getMinValueLabel(),
					getString(R.string.should_be_not_greater),
					model.getMaxValueLabel()));
		}

		if (model.getMinValue() != null && minValue != null && minValue < model.getMinValue()) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s %s",
					model.getMinValueLabel(),
					getString(R.string.should_be_not_less),
					model.getMinValue()));
		}

		if (model.getMinValue() != null && maxValue != null && maxValue < model.getMinValue()) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s %s",
					model.getMaxValueLabel(),
					getString(R.string.should_be_not_less),
					model.getMinValue()));
		}

		if (model.getMaxValue() != null && minValue != null && minValue > model.getMaxValue()) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s %s",
					model.getMinValueLabel(),
					getString(R.string.should_be_not_greater),
					model.getMaxValue()));
		}

		if (model.getMaxValue() != null && maxValue != null && maxValue > model.getMaxValue()) {
			errorsString = errorsString.concat(String.format(Locale.getDefault(), "\n%s %s %s",
					model.getMaxValueLabel(),
					getString(R.string.should_be_not_greater),
					model.getMaxValue()));
		}

		errors.setText(errorsString);
	}

	private void updateButtons() {
		boolean isFilterChanged = !valuesAreEqual(model.getCurrentMinValue(), minValue, model.getMinValue())
				|| !valuesAreEqual(model.getCurrentMaxValue(), maxValue, model.getMaxValue());

		boolean minLessMax = (minValue == null || maxValue == null || minValue <= maxValue);
		boolean inBounds = (model.getMinValue() == null || minValue == null || (minValue >= model.getMinValue() && minValue <= model.getMaxValue()))
				&& (model.getMaxValue() == null || maxValue == null || (maxValue >= model.getMinValue() && maxValue <= model.getMaxValue()));

		boolean canApply = isFilterChanged && minLessMax && inBounds;
		applyButton.setVisibility(canApply ? View.VISIBLE : View.GONE);
		closeButton.setVisibility(!canApply ? View.VISIBLE : View.GONE);
	}

	private boolean valuesAreEqual(Object v1, Object v2, Object defaultValue) {
		return (v1 == null && (v2 == null || v2.equals(defaultValue))
				|| (v2 == null && v1.equals(defaultValue)
				|| (v2 != null && v2.equals(v1))));
	}

	private void showSoftKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		if (imm != null) {
			imm.showSoftInput(view, 0);
		}
	}

	private void hideSoftKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		view.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}
