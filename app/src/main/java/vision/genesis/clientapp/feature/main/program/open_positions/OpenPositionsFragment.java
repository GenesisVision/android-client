package vision.genesis.clientapp.feature.main.program.open_positions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.OrderModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/02/2019.
 */

public class OpenPositionsFragment extends BaseFragment implements OpenPositionsView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static OpenPositionsFragment with(UUID programId) {
		OpenPositionsFragment openPositionsFragment = new OpenPositionsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		openPositionsFragment.setArguments(arguments);
		return openPositionsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_positions)
	public View groupNoPositions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public OpenPositionsPresenter openPositionsPresenter;

	private OpenPositionsListAdapter openPositionsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_open_positions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();
		setFonts();

		if (getArguments() != null) {
			openPositionsPresenter.setProgramId((UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID));
		}
		else {
			Timber.e("Passed empty programId to OpenPositionsFragment");
			onBackPressed();
		}
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

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		openPositionsListAdapter = new OpenPositionsListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), R.layout.list_item_trades_date_section, R.id.text, openPositionsListAdapter);
		recyclerView.setAdapter(sectionedAdapter);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setOpenPositions(List<OrderModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (trades.isEmpty()) {
			groupNoPositions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		openPositionsListAdapter.setOpenPositions(trades);
		groupNoPositions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showOpenPositionDetails(OrderModel openPosition) {
		if (getActivity() != null) {
			OpenPositionDetailsDialog dialog = new OpenPositionDetailsDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(openPosition);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (openPositionsPresenter != null)
			openPositionsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (openPositionsPresenter != null)
			openPositionsPresenter.onSwipeRefresh();
	}
}