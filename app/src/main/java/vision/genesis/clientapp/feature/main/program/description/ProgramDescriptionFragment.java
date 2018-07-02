package vision.genesis.clientapp.feature.main.program.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgramDetails;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramInfoPagerAdapter;
import vision.genesis.clientapp.ui.AvatarView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ProgramDescriptionFragment extends BaseFragment implements ProgramDescriptionView, ProgramInfoPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static ProgramDescriptionFragment with(UUID programId) {
		ProgramDescriptionFragment programDescriptionFragment = new ProgramDescriptionFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		programDescriptionFragment.setArguments(arguments);
		return programDescriptionFragment;
	}

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.program_logo)
	public AvatarView programLogo;

	@BindView(R.id.description)
	public TextView description;


	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ProgramDescriptionPresenter programDescriptionPresenter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_description, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		programDescriptionPresenter.setProgramId((UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID));

		setFonts();

		initRefreshLayout();
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
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> programDescriptionPresenter.onSwipeRefresh());
	}

	@Override
	public void setProgramDescription(InvestmentProgramDetails programDetails) {
		programLogo.setImage(programDetails.getLogo(), 500, 500);
		programLogo.setLevel(programDetails.getLevel());

		String programDescription = !programDetails.getDescription().isEmpty()
				? programDetails.getDescription()
				: getString(R.string.no_description);
		description.setText(programDescription);

		scrollView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null)
			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void pagerShow() {
		if (programDescriptionPresenter != null)
			programDescriptionPresenter.onShow();
	}

	@Override
	public void pagerHide() {

	}
}
