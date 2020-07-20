package vision.genesis.clientapp.feature.main.social.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.Post;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.PostsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostsListFragment extends BaseFragment implements PostsListView
{
	private static final String EXTRA_FILTER = "extra_filter";

	public static PostsListFragment with(PostsFilter filter) {
		PostsListFragment programListFragment = new PostsListFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_FILTER, filter);
		programListFragment.setArguments(arguments);
		return programListFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	PostsListPresenter presenter;

	private PostsListAdapter adapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_posts_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			PostsFilter filter = getArguments().getParcelable(EXTRA_FILTER);

			initRefreshLayout();
			initRecyclerView();

			presenter.setData(filter);
		}
		else {
			Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	@Override
	public void onDestroyView() {
		if (recyclerView != null) {
			recyclerView.setAdapter(null);
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new PostsListAdapter();
		adapter.setHasStableIds(true);
		recyclerView.setAdapter(adapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
			}
		});
	}

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0) {
			return;
		}

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			presenter.onLastListItemVisible();
		}
	}

	@Override
	public void setPosts(List<Post> posts) {
		adapter.setPosts(posts);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addPosts(List<Post> posts) {
		adapter.addPosts(posts);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

//	@Override
//	public void changePostIsLiked(UUID programId, boolean isFavorite) {
//		adapter.changeProgramIsFavorite(programId, isFavorite);
//	}

	@Override
	public void showBottomProgress(boolean show) {

	}
}
