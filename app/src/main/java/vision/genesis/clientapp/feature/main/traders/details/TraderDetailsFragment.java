package vision.genesis.clientapp.feature.main.traders.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.ui.ManagerAvatarView;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TraderDetailsFragment extends BaseFragment implements TraderDetailsView
{
	private static String EXTRA_PROGRAM = "extra_program";

	public static TraderDetailsFragment with(InvestmentProgram program) {
		TraderDetailsFragment traderDetailsFragment = new TraderDetailsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_PROGRAM, program);
		traderDetailsFragment.setArguments(arguments);
		return traderDetailsFragment;
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.avatar)
	public ManagerAvatarView avatar;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.text_description)
	public TextView description;

	@InjectPresenter
	TraderDetailsPresenter traderDetailsPresenter;

	private InvestmentProgram program;

	@ProvidePresenter
	public TraderDetailsPresenter provideTraderDetailsPresenter() {
		return new TraderDetailsPresenter(((RouterProvider) getParentFragment()).getRouter());
	}

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

		if (getArguments() != null && !getArguments().isEmpty()) {
			program = getArguments().getParcelable(EXTRA_PROGRAM);
			setData();
		}
		else {
			Timber.e("Passed empty program to TraderDetailsFragment");
			traderDetailsPresenter.onBackClicked();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trader_details));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> traderDetailsPresenter.onBackClicked());
	}

	private void setData() {
		avatar.setImageUrl(program.logo);
		avatar.setLevel(program.getRating());

		managerName.setText(program.managerName);
		description.setText(program.description);
	}

	@Override
	public boolean onBackPressed() {
		traderDetailsPresenter.onBackClicked();
		return true;
	}
}
