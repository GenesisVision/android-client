package vision.genesis.clientapp.feature.main.dashboard.investor.header;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */

public class InvestorDashboardHeaderFragment extends BaseFragment implements InvestorDashboardHeaderView
{
	public static final String TYPE_PORTFOLIO = "type_portfolio";

	public static final String TYPE_PROFIT = "type_profit";

	private static final String EXTRA_TYPE = "extra_type";

	public static InvestorDashboardHeaderFragment with(String type) {
		InvestorDashboardHeaderFragment investorDashboardHeaderFragment = new InvestorDashboardHeaderFragment();
		Bundle arguments = new Bundle(1);
		arguments.putString(EXTRA_TYPE, type);
		investorDashboardHeaderFragment.setArguments(arguments);
		return investorDashboardHeaderFragment;
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.secondary_value)
	public TextView secondaryValue;

	@BindView(R.id.chart)
	public ProfitSmallChartView chart;

	@BindView(R.id.chart_percent)
	public TextView chartPercent;

	@BindView(R.id.chart_value)
	public TextView chartValue;

	@InjectPresenter
	public InvestorDashboardHeaderPresenter investorDashboardHeaderPresenter;

	private String type;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_investor_dashboard_header, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			type = getArguments().getString(EXTRA_TYPE);
			if (type != null) {
				setTitle(type);
				investorDashboardHeaderPresenter.setType(type);
			}
			else {
				Timber.e("Passed empty type to InvestorDashboardHeaderFragment");
			}
		}
	}

	private void setTitle(String type) {
		switch (type) {
			case TYPE_PORTFOLIO:
				title.setText(getString(R.string.portfolio));
				break;
			case TYPE_PROFIT:
				title.setText(getString(R.string.profit));
				break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		investorDashboardHeaderPresenter.onShow();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.light());
		secondaryValue.setTypeface(TypefaceUtil.light());
		chartPercent.setTypeface(TypefaceUtil.regular());
		chartValue.setTypeface(TypefaceUtil.regular());
	}

	@Override
	public void setData(String value, String valueSecondary) {

	}
}