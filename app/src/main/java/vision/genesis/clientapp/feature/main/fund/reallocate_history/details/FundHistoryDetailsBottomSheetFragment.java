package vision.genesis.clientapp.feature.main.fund.reallocate_history.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundHistoryEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */

public class FundHistoryDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.action)
	public SimpleDraweeView action;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	private FundHistoryEventViewModel event;

	private FundTradesAdapter adapter;

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		getDialog().setOnShowListener(dialog1 -> {
			BottomSheetDialog d = (BottomSheetDialog) dialog1;
			View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null) {
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
			}
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_fund_history_details, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		initRecyclerView();

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}


	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new FundTradesAdapter();
		recyclerView.setAdapter(adapter);
	}

	public void setData(FundHistoryEventViewModel event) {
		this.event = event;

		updateView();
	}

	private void updateView() {
		if (event != null && recyclerView != null && getContext() != null) {
			this.date.setText(DateTimeUtil.formatEventDateTime(event.getDate()));

			this.action.setImageURI(ImageUtils.getImageUri(event.getLogoUrl()));
			setType(event);
			adapter.setTrades(event.getTrades());
		}
	}

	private void setType(FundHistoryEventViewModel event) {
		int stringResId = 0;
		if (event.getType() != null) {
			switch (event.getType()) {
				case CREATION:
					stringResId = R.string.creation;
					break;
				case INVESTMENT:
					stringResId = R.string.investment;
					break;
				case WITHDRAWAL:
					stringResId = R.string.withdrawal;
					break;
				case REBALANCE:
					stringResId = R.string.rebalance;
					break;
				case REALLOCATION:
					stringResId = R.string.reallocation;
					break;
				case CHALLENGEWINNER:
					stringResId = R.string.challenge_winner;
					break;
			}
		}
		if (stringResId != 0) {
			this.type.setText(getContext().getString(stringResId));
		}
	}
}
