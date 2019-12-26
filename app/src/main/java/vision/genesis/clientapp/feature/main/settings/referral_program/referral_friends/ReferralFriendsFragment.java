package vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ReferralFriend;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.settings.referral_program.ReferralProgramPagerAdapter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class ReferralFriendsFragment extends BaseFragment implements ReferralFriendsView, ReferralProgramPagerAdapter.OnPageVisibilityChanged
{
	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.group_table)
	public ViewGroup groupTable;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.email)
	public TextView email;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public ReferralFriendsPresenter presenter;

	private ReferralFriendsListAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_referral_friends, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setFonts();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		initRecyclerView();
	}

	private void setFonts() {
		date.setTypeface(TypefaceUtil.semibold());
		email.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new ReferralFriendsListAdapter();
		recyclerView.setAdapter(adapter);

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					presenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setReferrals(List<ReferralFriend> referrals) {
		if (referrals.isEmpty()) {
			emptyGroup.setVisibility(View.VISIBLE);
			groupTable.setVisibility(View.GONE);
			return;
		}

		adapter.setReferrals(referrals);
		emptyGroup.setVisibility(View.GONE);
		groupTable.setVisibility(View.VISIBLE);
	}

	@Override
	public void addReferrals(List<ReferralFriend> referrals) {
		adapter.addReferrals(referrals);
	}

	@Override
	public void showEmpty(boolean show) {
		if (emptyGroup != null) {
			emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showProgress(boolean show) {
		if (progressBar != null) {
			progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
			if (!show) {
				groupTable.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, root);
	}

	@Override
	public void pagerShow() {
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}
}