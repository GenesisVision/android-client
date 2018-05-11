package vision.genesis.clientapp.feature.main.filters_sorting;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class SortingBottomSheetFragment extends BottomSheetDialogFragment
{
	interface OnSortingChangedListener
	{
		void onSortingChanged(String option, String direction);
	}

	@BindView(R.id.text_sort_by)
	public TextView sortByText;

	@BindView(R.id.direction)
	public TextView direction;

	@BindView(R.id.profit)
	public SortingOptionView profit;

	@BindView(R.id.level)
	public SortingOptionView level;

	@BindView(R.id.end_of_period)
	public SortingOptionView endOfPeriod;

	@BindView(R.id.title)
	public SortingOptionView title;

	@BindView(R.id.button_apply)
	public ViewGroup applyButton;

	@BindView(R.id.button_close)
	public ViewGroup closeButton;

	private OnSortingChangedListener listener;

	private SortingOptionView selectedOption;

	private String selectedDirection = "asc";

	private String oldOption;

	private String oldDirection;

	@OnClick(R.id.direction)
	public void onDirectionClicked() {
		changeDirection();
	}

	@OnClick(R.id.profit)
	public void onProfitClicked() {
		selectOption(profit);
	}

	@OnClick(R.id.level)
	public void onLevelClicked() {
		selectOption(level);
	}

	@OnClick(R.id.end_of_period)
	public void onEndOfPeriodClicked() {
		selectOption(endOfPeriod);
	}

	@OnClick(R.id.title)
	public void onTitleClicked() {
		selectOption(title);
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		if (listener != null) {
			listener.onSortingChanged(selectedOption.getText().toLowerCase(), selectedDirection);
			this.dismiss();
		}
	}

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		this.dismiss();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_sorting, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		initSortingOptions();
		updateSelections();
	}

	public void setCurrentSorting(@NonNull String oldOption, @NonNull String oldDirection) {
		this.oldOption = oldOption.toLowerCase();
		this.oldDirection = oldDirection.toLowerCase();

		if (profit != null)
			updateSelections();
	}

	public void setListener(OnSortingChangedListener listener) {
		this.listener = listener;
	}

	private void setFonts() {
		sortByText.setTypeface(TypefaceUtil.bold());
	}

	private void initSortingOptions() {
		profit.setText(getString(R.string.profit));
		level.setText(getString(R.string.level));
		endOfPeriod.setText(getString(R.string.end_of_period));
		title.setText(getString(R.string.title));
	}

	private void updateSelections() {
		switch (oldOption) {
			case "profit":
				selectOption(profit);
				break;
			case "level":
				selectOption(level);
				break;
			case "end of period":
				selectOption(endOfPeriod);
				break;
			case "title":
				selectOption(title);
				break;
			default:
				break;
		}

		switch (oldDirection) {
			case "asc":
				setDirection("asc");
				break;
			case "desc":
				setDirection("desc");
				break;
			default:
				break;
		}
	}

	private void selectOption(SortingOptionView newOption) {
		deselectPreviousOption();
		newOption.setSelected(true);
		selectedOption = newOption;

		updateButtons();
	}

	private void deselectPreviousOption() {
		if (selectedOption != null)
			selectedOption.setSelected(false);
	}

	private void setDirection(String direction) {
		if (direction.equals("asc")) {
			this.direction.setText(getString(R.string.sorting_asc));
		}
		else if (direction.equals("desc")) {
			this.direction.setText(getString(R.string.sorting_desc));
		}

		selectedDirection = direction;

		updateButtons();
	}

	private void changeDirection() {
		if (selectedDirection.equals("asc"))
			setDirection("desc");
		else
			setDirection("asc");
	}

	private void updateButtons() {
		boolean isNewSortingSelected = selectedOption != null &&
				(!selectedOption.getText().toLowerCase().equals(oldOption)
						|| !selectedDirection.equals(oldDirection));
		applyButton.setVisibility(isNewSortingSelected ? View.VISIBLE : View.GONE);
		closeButton.setVisibility(!isNewSortingSelected ? View.VISIBLE : View.GONE);
	}
}
