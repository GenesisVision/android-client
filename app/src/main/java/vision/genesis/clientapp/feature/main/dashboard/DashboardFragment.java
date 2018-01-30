package vision.genesis.clientapp.feature.main.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class DashboardFragment extends BaseFragment implements DashboardView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@InjectPresenter
	DashboardPresenter dashboardPresenter;

	@ProvidePresenter
	public DashboardPresenter provideDashboardPresenter() {
		return new DashboardPresenter(((RouterProvider) getParentFragment()).getRouter());
	}

	@OnClick(R.id.button_invest)
	public void onInvestButtonClicked() {
		EventBus.getDefault().post(new OnInvestButtonClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.dashboard));
	}
}
