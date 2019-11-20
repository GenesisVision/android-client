package vision.genesis.clientapp.feature.common.option;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/10/2018.
 */

public class SelectOptionBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnOptionSelectedListener
	{
		void onOptionSelected(Integer position, String text);
	}

	public static final String EXTRA_TITLE = "extra_title";

	public static final String EXTRA_OPTIONS = "extra_options";

	public static final String EXTRA_SELECTED_POSITION = "extra_selected_position";

	public static SelectOptionBottomSheetFragment with(String title, ArrayList<String> options, Integer selectedPosition) {
		SelectOptionBottomSheetFragment fragment = new SelectOptionBottomSheetFragment();
		Bundle arguments = new Bundle(3);
		arguments.putString(EXTRA_TITLE, title);
		arguments.putStringArrayList(EXTRA_OPTIONS, options);
		arguments.putInt(EXTRA_SELECTED_POSITION, selectedPosition);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_options)
	public ViewGroup optionsGroup;

	private OnOptionSelectedListener listener;

	private OptionView selectedOption;

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_select_option, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		try {
			Objects.requireNonNull(getArguments());
			setData(getArguments().getString(EXTRA_TITLE),
					Objects.requireNonNull(getArguments().getStringArrayList(EXTRA_OPTIONS)),
					getArguments().getInt(EXTRA_SELECTED_POSITION));
		} catch (NullPointerException e) {
			Timber.e("Passed empty arguments to SelectOptionBottomSheetFragment");
			this.dismiss();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Window window = getDialog().getWindow();
		if (window != null) {
			window.findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			window.getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	public void setListener(OnOptionSelectedListener listener) {
		this.listener = listener;
	}

	private void setData(String title, @NonNull List<String> options, @NonNull Integer selectedPosition) {
		this.title.setText(title);
		if (title == null || title.isEmpty()) {
			this.title.setVisibility(View.GONE);
		}
		Integer position = 0;
		for (String option : options) {
			optionsGroup.addView(createOptionView(option, position, position.equals(selectedPosition)));
			position++;
		}
	}

	private OptionView createOptionView(String option, Integer position, Boolean isSelected) {
		OptionView view = new OptionView(getContext());
		view.setData(option);
		view.setSelected(isSelected);
		if (isSelected) {
			selectedOption = view;
		}
		view.setOnClickListener(v -> selectOption(view, position, option));
		return view;
	}

	private void selectOption(OptionView newOption, Integer position, String option) {
		if (selectedOption != null) {
			selectedOption.setSelected(false);
		}
		newOption.setSelected(true);

		listener.onOptionSelected(position, option);
		this.dismiss();
	}
}
