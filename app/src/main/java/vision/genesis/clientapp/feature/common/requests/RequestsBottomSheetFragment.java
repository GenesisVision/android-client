package vision.genesis.clientapp.feature.common.requests;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AssetInvestmentRequest;
import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.model.events.OnCancelRequestClickedEvent;
import vision.genesis.clientapp.model.events.OnRequestCancelledEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/09/2018.
 */

public class RequestsBottomSheetFragment extends BottomSheetDialogFragment
{
	@Inject
	public DashboardManager dashboardManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.empty_text)
	public TextView emptyText;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private List<AssetInvestmentRequest> requests = new ArrayList<>();

	private RequestsAdapter requestsAdapter;

	private Subscription cancelRequestSubscription;

	private Subscription getRequestsSubscription;

	private UUID assetId;

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_requests, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);
		EventBus.getDefault().register(this);

		setFonts();

		initRecyclerView();

		if (assetId != null) {
			getRequests(assetId);
		}
	}

	@Override
	public void onDestroyView() {
		if (cancelRequestSubscription != null) {
			cancelRequestSubscription.unsubscribe();
		}
		if (getRequestsSubscription != null) {
			getRequestsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);
		super.onDestroyView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		int paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getContext().getResources().getDisplayMetrics());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				AppCompatResources.getDrawable(getContext(), R.drawable.list_item_divider), paddingLeft, 0);
		recyclerView.addItemDecoration(dividerItemDecoration);
		requestsAdapter = new RequestsAdapter();
		requestsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(requestsAdapter);
		recyclerView.setNestedScrollingEnabled(false);
		setRequests(requests);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	public void setRequests(List<AssetInvestmentRequest> requests) {
		this.requests = requests;
		if (progressBar != null) {
			progressBar.setVisibility(View.GONE);
			showEmpty(requests.isEmpty());
			if (requestsAdapter != null) {
				requestsAdapter.setRequests(requests);
			}
		}
	}

	public void setAssetId(UUID assetId) {
		this.assetId = assetId;
		getRequests(assetId);
	}

	private void showEmpty(boolean empty) {
		if (emptyText != null) {
			emptyText.setVisibility(empty ? View.VISIBLE : View.GONE);
			recyclerView.setVisibility(!empty ? View.VISIBLE : View.GONE);
		}
	}

	private void deleteRequest(UUID requestId) {
		int position = 0;
		for (AssetInvestmentRequest request : requests) {
			if (request.getId().equals(requestId)) {
				requests.remove(request);
				break;
			}
			position++;
		}

		requestsAdapter.deleteRequest(position);
		showEmpty(requests.isEmpty());
	}

	@Subscribe
	public void onEventMainThread(OnCancelRequestClickedEvent event) {
		cancelRequest(event.getRequestId());
	}

	private void getRequests(UUID assetId) {
		if (dashboardManager != null && assetId != null) {
			getRequestsSubscription = dashboardManager.getRequestsByAsset(assetId)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetRequests,
							this::handleGetRequestsError);
		}
	}

	private void handleGetRequests(AssetInvestmentRequestItemsViewModel programRequests) {
		getRequestsSubscription.unsubscribe();

		setRequests(programRequests.getItems());
	}

	private void handleGetRequestsError(Throwable throwable) {
		getRequestsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);

		this.dismiss();
	}

	private void cancelRequest(UUID requestId) {
//		cancelRequestSubscription = dashboardManager.cancelRequest(requestId)
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(response -> handleCancelRequestSuccess(requestId),
//						this::handleCancelRequestError);
	}

	private void handleCancelRequestSuccess(UUID requestId) {
		cancelRequestSubscription.unsubscribe();
		deleteRequest(requestId);
		EventBus.getDefault().post(new OnRequestCancelledEvent());
	}

	private void handleCancelRequestError(Throwable throwable) {
		cancelRequestSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}
}
