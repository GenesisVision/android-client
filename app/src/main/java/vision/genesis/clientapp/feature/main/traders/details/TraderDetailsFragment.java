package vision.genesis.clientapp.feature.main.traders.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TraderDetailsFragment extends BaseFragment implements TraderDetailsView
{
	private static String EXTRA_PROGRAM = "extra_program";

	public static TraderDetailsFragment with(Object data) {
		TraderDetailsFragment traderDetailsFragment = new TraderDetailsFragment();
		Bundle arguments = new Bundle(1);
//		arguments.putParcelable(EXTRA_PROGRAM, data);
		traderDetailsFragment.setArguments(arguments);
		return traderDetailsFragment;
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@InjectPresenter
	TraderDetailsPresenter traderDetailsPresenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trader_details, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trader_details));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> traderDetailsPresenter.onBackClicked());
	}
}
