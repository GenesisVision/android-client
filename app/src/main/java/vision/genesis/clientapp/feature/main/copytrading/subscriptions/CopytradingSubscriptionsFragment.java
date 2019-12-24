package vision.genesis.clientapp.feature.main.copytrading.subscriptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.SignalSubscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.ui.SignalProviderView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/12/2019.
 */

public class CopytradingSubscriptionsFragment extends BaseFragment implements CopytradingSubscriptionsView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static CopytradingSubscriptionsFragment with(UUID accountId) {
		CopytradingSubscriptionsFragment copytradingSubscriptionsFragment = new CopytradingSubscriptionsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		copytradingSubscriptionsFragment.setArguments(arguments);
		return copytradingSubscriptionsFragment;
	}

	@BindView(R.id.group_active)
	public ViewGroup activeGroup;

	@BindView(R.id.label_active)
	public TextView labelActive;

	@BindView(R.id.active_count)
	public TextView activeCount;

	@BindView(R.id.active_subscriptions)
	public LinearLayout activeSubscriptions;

	@BindView(R.id.group_active_empty)
	public ViewGroup activeEmptyGroup;


	@BindView(R.id.group_archive)
	public ViewGroup archiveGroup;

	@BindView(R.id.label_archive)
	public TextView labelArchive;

	@BindView(R.id.archive_count)
	public TextView archiveCount;

	@BindView(R.id.archive_subscriptions)
	public LinearLayout archiveSubscriptions;

	@BindView(R.id.group_archive_empty)
	public ViewGroup archiveEmptyGroup;


	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public CopytradingSubscriptionsPresenter presenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_copytrading_subscriptions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			UUID accountId = (UUID) getArguments().getSerializable(EXTRA_ACCOUNT_ID);
			if (accountId != null) {
				presenter.setData(accountId);

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		labelActive.setTypeface(TypefaceUtil.semibold());
		labelArchive.setTypeface(TypefaceUtil.semibold());
		activeCount.setTypeface(TypefaceUtil.semibold());
		archiveCount.setTypeface(TypefaceUtil.semibold());
	}


	@Override
	public void setData(List<SignalSubscription> activeList, List<SignalSubscription> archiveList) {
		activeCount.setText(String.valueOf(activeList.size()));
		archiveCount.setText(String.valueOf(archiveList.size()));
		activeSubscriptions.removeAllViews();
		archiveSubscriptions.removeAllViews();
		activeEmptyGroup.setVisibility(activeList.size() > 0 ? View.GONE : View.VISIBLE);
		archiveEmptyGroup.setVisibility(archiveList.size() > 0 ? View.GONE : View.VISIBLE);

		int index = 0;
		for (SignalSubscription active : activeList) {
			SignalProviderView subscriptionView = new SignalProviderView(getContext());
			subscriptionView.setData(active);
			activeSubscriptions.addView(subscriptionView);
			if (index == activeList.size() - 1) {
				subscriptionView.removeDelimiter();
			}
			index++;
		}

		index = 0;
		for (SignalSubscription archive : archiveList) {
			SignalProviderView subscriptionView = new SignalProviderView(getContext());
			subscriptionView.setData(archive);
			archiveSubscriptions.addView(subscriptionView);
			if (index == archiveList.size() - 1) {
				subscriptionView.removeDelimiter();
			}
			index++;
		}
	}

	@Override
	public void showProgress(boolean show) {
		if (progressBar != null) {
			progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
			if (!show) {
				activeGroup.setVisibility(View.VISIBLE);
				archiveGroup.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, activeGroup);
	}

	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
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