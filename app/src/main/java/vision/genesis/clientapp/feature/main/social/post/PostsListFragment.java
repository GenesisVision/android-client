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
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.social.post.create.CreatePostActivity;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.SocialPostType;

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

	@BindView(R.id.progress_bar_bottom)
	public ProgressBar bottomProgressBar;

	@InjectPresenter
	PostsListPresenter presenter;

	private PostsListAdapter adapter;

	private Unbinder unbinder;

	private PostsFilter filter;

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
			filter = getArguments().getParcelable(EXTRA_FILTER);

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
		adapter = new PostsListAdapter(presenter,
				filter != null ? filter.isCanAddNewPost() : false,
				filter != null ? filter.getUserId() : null);
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
		int lastVisible = layoutManager.findLastVisibleItemPosition();
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
	public void setPostDeleted(Post post, boolean isDeleted) {
		adapter.setPostDeleted(post, isDeleted);
	}

	@Override
	public void showSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener) {
		if (getActivity() != null) {
			SocialPostActionsBottomSheetFragment bottomSheetDialog = new SocialPostActionsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setData(post, type, isOwnPost);
			bottomSheetDialog.setListener(listener);
		}
	}

	@Override
	public void showEditPost(Post post) {
		if (getActivity() != null) {
			CreatePostActivity.startWith(getActivity(), null, post, null);
		}
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
		bottomProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void setShowEvents(boolean showEvents) {
		if (filter != null) {
			filter.setShowOnlyUserPosts(!showEvents);
			presenter.setData(filter);
		}
	}

	public void setFilter(PostsFilter filter) {
		presenter.setData(filter);
	}

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}

	public void clearPostsList() {
		if (presenter != null) {
			presenter.clearPostsList();
		}
	}
}
