package vision.genesis.clientapp.feature.main.traders.filter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentsFilter;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.model.FilterSortingOption;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TradersFiltersFragment extends BaseFragment implements TradersFiltersView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.spinner_sorting)
	public Spinner sortingSpinner;

	@BindView(R.id.edittext_max_from)
	public EditText maxFrom;

	@BindView(R.id.edittext_max_to)
	public EditText maxTo;

	@BindView(R.id.button_apply)
	public View applyButton;

	@BindView(R.id.button_clear)
	public View clearButton;

	@InjectPresenter
	TradersFiltersPresenter tradersFiltersPresenter;

	private ArrayList<FilterSortingOption> sortingOptions;

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		tradersFiltersPresenter.onApplyClicked();
	}

	@OnClick(R.id.button_clear)
	public void onClearClicked() {
		tradersFiltersPresenter.onClearClicked();
	}

	@ProvidePresenter
	public TradersFiltersPresenter provideTraderFiltersPresenter() {
		return new TradersFiltersPresenter(((RouterProvider) getParentFragment()).getRouter());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_traders_filters, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();

		initSpinner();
	}

	private void initSpinner() {
		sortingOptions = FilterSortingOption.getOptions(getContext());
		ArrayAdapter<FilterSortingOption> sortingAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, sortingOptions);
		sortingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortingSpinner.setAdapter(sortingAdapter);

		sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tradersFiltersPresenter.onSortingSelected(sortingOptions.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.filters));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> tradersFiltersPresenter.onBackClicked());
	}

	@Override
	public boolean onBackPressed() {
		tradersFiltersPresenter.onBackClicked();
		return true;
	}

	@Override
	public void setFilterData(InvestmentsFilter filter) {
		int index = 0;
		for (FilterSortingOption sortingOption : sortingOptions) {
			if (sortingOption.option.equals(filter.getSorting())) {
				sortingSpinner.setSelection(index);
			}
			index++;
		}
	}
}
