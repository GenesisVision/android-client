package vision.genesis.clientapp.feature.main.filters.sorting;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.content.res.AppCompatResources;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class SortingDialogFragment extends AppCompatDialogFragment
{
	public interface OnSortingChangedListener
	{
		void onSortingChanged(String option, String direction);
	}

	@BindView(R.id.text_sort_by)
	public TextView sortByText;

	@BindView(R.id.direction_icon)
	public ImageView directionIcon;

	@BindView(R.id.direction_value)
	public TextView directionValue;

	@BindView(R.id.profit)
	public SortingOptionView profit;

	@BindView(R.id.level)
	public SortingOptionView level;

	@BindView(R.id.end_of_period)
	public SortingOptionView endOfPeriod;

	@BindView(R.id.equity)
	public SortingOptionView equity;

	@BindView(R.id.size)
	public SortingOptionView size;

	@BindView(R.id.title)
	public SortingOptionView title;

	@BindView(R.id.subscribers)
	public SortingOptionView subscribers;

	@BindView(R.id.investors)
	public SortingOptionView investors;

	@BindView(R.id.drawdown)
	public SortingOptionView drawdown;

	@BindView(R.id.profit_delimiter)
	public View profitDelimiter;

	@BindView(R.id.level_delimiter)
	public View levelDelimiter;

	@BindView(R.id.end_of_period_delimiter)
	public View endOfPeriodDelimiter;

	@BindView(R.id.equity_delimiter)
	public View equityDelimiter;

	@BindView(R.id.size_delimiter)
	public View sizeDelimiter;

	@BindView(R.id.title_delimiter)
	public View titleDelimiter;

	@BindView(R.id.subscribers_delimiter)
	public View subscribersDelimiter;

	@BindView(R.id.investors_delimiter)
	public View investorsDelimiter;

	@BindView(R.id.drawdown_delimiter)
	public View drawdownDelimiter;

	@BindView(R.id.button_apply)
	public TextView applyButton;

	@BindView(R.id.button_cancel)
	public TextView cancelButton;

	private OnSortingChangedListener listener;

	private SortingOptionView selectedOption;

	private String selectedDirection = "asc";

	private String oldOption;

	private String oldDirection;

	private int assetType;

	@OnClick(R.id.group_direction)
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

	@OnClick(R.id.equity)
	public void onEquityClicked() {
		selectOption(equity);
	}

	@OnClick(R.id.size)
	public void onSizeClicked() {
		selectOption(size);
	}

	@OnClick(R.id.end_of_period)
	public void onEndOfPeriodClicked() {
		selectOption(endOfPeriod);
	}

	@OnClick(R.id.title)
	public void onTitleClicked() {
		selectOption(title);
	}

	@OnClick(R.id.subscribers)
	public void onSubscribersClicked() {
		selectOption(subscribers);
	}

	@OnClick(R.id.investors)
	public void onInvestorsClicked() {
		selectOption(investors);
	}

	@OnClick(R.id.drawdown)
	public void onDrawdownClicked() {
		selectOption(drawdown);
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		if (listener != null && selectedOption != null) {
			listener.onSortingChanged(selectedOption.getText().toLowerCase(), selectedDirection);
			this.dismiss();
		}
	}

	@OnClick(R.id.button_cancel)
	public void onCancelClicked() {
		this.dismiss();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_dialog_sorting, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		updateAvailableOptions();
		initSortingOptions();
		updateSelections();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}
	}

	public void setCurrentSorting(@NonNull String oldOption, @NonNull String oldDirection) {
		this.oldOption = oldOption.toLowerCase();
		this.oldDirection = oldDirection.toLowerCase();

		if (profit != null) {
			updateSelections();
		}
	}

	public void setAssetType(int type) {
		this.assetType = type;
		updateAvailableOptions();
	}

	private void updateAvailableOptions() {
		if (profit != null) {
			if (assetType == UserFilter.TYPE_PROGRAMS_LIST_FILTER) {
				profit.setVisibility(View.VISIBLE);
				profitDelimiter.setVisibility(View.VISIBLE);
				level.setVisibility(View.VISIBLE);
				levelDelimiter.setVisibility(View.VISIBLE);
				endOfPeriod.setVisibility(View.VISIBLE);
				endOfPeriodDelimiter.setVisibility(View.VISIBLE);
				equity.setVisibility(View.VISIBLE);
				equityDelimiter.setVisibility(View.VISIBLE);
				size.setVisibility(View.GONE);
				sizeDelimiter.setVisibility(View.GONE);
				title.setVisibility(View.VISIBLE);
				titleDelimiter.setVisibility(View.VISIBLE);
				subscribers.setVisibility(View.GONE);
				subscribersDelimiter.setVisibility(View.GONE);
				investors.setVisibility(View.GONE);
				investorsDelimiter.setVisibility(View.GONE);
				drawdown.setVisibility(View.GONE);
				drawdownDelimiter.setVisibility(View.GONE);
			}
			else if (assetType == UserFilter.TYPE_FUNDS_LIST_FILTER) {
				profit.setVisibility(View.VISIBLE);
				profitDelimiter.setVisibility(View.VISIBLE);
				level.setVisibility(View.GONE);
				levelDelimiter.setVisibility(View.GONE);
				endOfPeriod.setVisibility(View.GONE);
				endOfPeriodDelimiter.setVisibility(View.GONE);
				equity.setVisibility(View.GONE);
				equityDelimiter.setVisibility(View.GONE);
				size.setVisibility(View.VISIBLE);
				sizeDelimiter.setVisibility(View.VISIBLE);
				title.setVisibility(View.VISIBLE);
				titleDelimiter.setVisibility(View.VISIBLE);
				subscribers.setVisibility(View.GONE);
				subscribersDelimiter.setVisibility(View.GONE);
				investors.setVisibility(View.VISIBLE);
				investorsDelimiter.setVisibility(View.VISIBLE);
				drawdown.setVisibility(View.VISIBLE);
				drawdownDelimiter.setVisibility(View.VISIBLE);
			}
			else if (assetType == UserFilter.TYPE_FOLLOWS_LIST_FILTER) {
				profit.setVisibility(View.VISIBLE);
				profitDelimiter.setVisibility(View.VISIBLE);
				level.setVisibility(View.GONE);
				levelDelimiter.setVisibility(View.GONE);
				endOfPeriod.setVisibility(View.GONE);
				endOfPeriodDelimiter.setVisibility(View.GONE);
				equity.setVisibility(View.VISIBLE);
				equityDelimiter.setVisibility(View.VISIBLE);
				size.setVisibility(View.GONE);
				sizeDelimiter.setVisibility(View.GONE);
				title.setVisibility(View.VISIBLE);
				titleDelimiter.setVisibility(View.VISIBLE);
				subscribers.setVisibility(View.VISIBLE);
				subscribersDelimiter.setVisibility(View.VISIBLE);
				investors.setVisibility(View.GONE);
				investorsDelimiter.setVisibility(View.GONE);
				drawdown.setVisibility(View.VISIBLE);
				drawdownDelimiter.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setListener(OnSortingChangedListener listener) {
		this.listener = listener;
	}

	private void setFonts() {
		sortByText.setTypeface(TypefaceUtil.semibold());
		directionValue.setTypeface(TypefaceUtil.medium());
		applyButton.setTypeface(TypefaceUtil.semibold());
		cancelButton.setTypeface(TypefaceUtil.semibold());
	}

	private void initSortingOptions() {
		profit.setText(getString(R.string.profit));
		level.setText(getString(R.string.level));
		endOfPeriod.setText(getString(R.string.end_of_period));
		equity.setText(getString(R.string.equity));
		size.setText(getString(R.string.size));
		title.setText(getString(R.string.title));
		subscribers.setText(getString(R.string.subscribers));
		investors.setText(getString(R.string.investors));
		drawdown.setText(getString(R.string.drawdown));
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
			case "equity":
				selectOption(equity);
				break;
			case "size":
				selectOption(size);
				break;
			case "title":
				selectOption(title);
				break;
			case "subscribers":
				selectOption(subscribers);
				break;
			case "investors":
				selectOption(investors);
				break;
			case "drawdown":
				selectOption(drawdown);
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
	}

	private void deselectPreviousOption() {
		if (selectedOption != null) {
			selectedOption.setSelected(false);
		}
	}

	private void setDirection(String direction) {
		if (direction.equals("asc")) {
			this.directionIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
					R.drawable.icon_sorting_low_to_high));
			this.directionValue.setText(getString(R.string.low_to_high));
		}
		else if (direction.equals("desc")) {
			this.directionIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
					R.drawable.icon_sorting_high_to_low));
			this.directionValue.setText(getString(R.string.high_to_low));
		}

		selectedDirection = direction;
	}

	private void changeDirection() {
		if (selectedDirection.equals("asc")) {
			setDirection("desc");
		}
		else {
			setDirection("asc");
		}
	}
}
