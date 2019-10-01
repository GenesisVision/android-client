package vision.genesis.clientapp.feature.main.fund.reallocate_history.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ReallocationModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */

public class FundReallocationDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.count)
	public TextView count;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	private ReallocationModel reallocation;

	private FundReallocationAdapter adapter;

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

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_reallocation, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

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
		adapter = new FundReallocationAdapter();
		recyclerView.setAdapter(adapter);
	}

	public void setData(ReallocationModel reallocation) {
		this.reallocation = reallocation;

		updateView();
	}

	private void setFonts() {
		this.date.setTypeface(TypefaceUtil.semibold());
		this.count.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (reallocation != null && recyclerView != null && getContext() != null) {
			this.date.setText(DateTimeUtil.formatEventDateTime(reallocation.getDate()));
			int count = reallocation.getParts().size();
			this.count.setText(String.format(Locale.getDefault(), "%d %s", count, getContext().getResources().getQuantityString(R.plurals.assets, count, count, count)));
			adapter.setAssets(reallocation.getParts());
		}
	}
}
