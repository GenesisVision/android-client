package vision.genesis.clientapp.feature.main.dashboard.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

public class ManagerDashboardFragment extends BaseFragment implements ManagerDashboardView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@InjectPresenter
	ManagerDashboardPresenter managerDashboardPresenter;

	private Unbinder unbinder;

	@OnClick(R.id.fab)
	public void onCreateProgramClicked() {
		CreateProgramActivity.startFrom(getContext());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_manager_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initToolbar();
	}

	@Override
	public void onResume() {
		super.onResume();
		managerDashboardPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.dashboard));
	}

	@Override
	public void showNoInternet(boolean show) {

	}

	@Override
	public void showProgressBar(boolean show) {
	}

	@Override
	public void showEmpty(boolean show) {

	}

	@Override
	public void setRefreshing(boolean show) {

	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void onShow() {
		managerDashboardPresenter.onResume();
	}
}