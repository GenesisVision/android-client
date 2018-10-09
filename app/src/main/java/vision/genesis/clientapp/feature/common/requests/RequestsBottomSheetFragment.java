package vision.genesis.clientapp.feature.common.requests;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ProgramRequest;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCancelRequestClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/09/2018.
 */

public class RequestsBottomSheetFragment extends BottomSheetDialogFragment
{
	@Inject
	public ProgramsManager programsManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.empty_text)
	public TextView emptyText;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	private List<ProgramRequest> requests = new ArrayList<>();

	private RequestsAdapter requestsAdapter;

	private Subscription cancelRequestSubscription;

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
	}

	@Override
	public void onDestroyView() {
		if (cancelRequestSubscription != null)
			cancelRequestSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
		super.onDestroyView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		int paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getContext().getResources().getDisplayMetrics());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider), paddingLeft, 0);
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

	public void setRequests(List<ProgramRequest> requests) {
		this.requests = requests;
		showEmpty(requests.isEmpty());
		if (requestsAdapter != null)
			requestsAdapter.setRequests(requests);
	}

	private void showEmpty(boolean empty) {
		if (emptyText != null) {
			emptyText.setVisibility(empty ? View.VISIBLE : View.GONE);
			recyclerView.setVisibility(!empty ? View.VISIBLE : View.GONE);
		}
	}

	private void deleteRequest(UUID requestId) {
		int position = 0;
		for (ProgramRequest request : requests) {
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

	private void cancelRequest(UUID requestId) {
		cancelRequestSubscription = programsManager.cancelRequest(requestId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(response -> handleCancelRequestSuccess(requestId),
						this::handleCancelRequestError);
	}

	private void handleCancelRequestSuccess(UUID requestId) {
		cancelRequestSubscription.unsubscribe();
		deleteRequest(requestId);
	}

	private void handleCancelRequestError(Throwable throwable) {
		cancelRequestSubscription.unsubscribe();
		if (ApiErrorResolver.isNetworkError(throwable)) {
			showToast(getContext().getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && response.errors.get(0) != null) {
				showToast(response.errors.get(0).message);
			}
		}
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}
}
