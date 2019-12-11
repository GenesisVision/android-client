package vision.genesis.clientapp.feature.main.portfolio_events.details;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FeeDetails;
import io.swagger.client.model.InvestmentEventItemViewModel;
import io.swagger.client.model.InvestmentEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.AssetView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2019.
 */

public class EventDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.action)
	public SimpleDraweeView action;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.view_program)
	public AssetView assetView;

	@BindView(R.id.group_values)
	public ViewGroup valuesGroup;

	@BindView(R.id.divider)
	public View divider;

	@BindView(R.id.group_fees)
	public ViewGroup feesGroup;

	private InvestmentEventViewModel event;

//	@OnClick(R.id.view_program)
//	public void onAssetClicked() {
//		if (event != null && event.getAssetDetails() != null) {
//			AssetDetails details = event.getAssetDetails();
//			if (details.getAssetType().equals(AssetType.PROGRAM) || details.getAssetType().equals(AssetType.FOLLOW)) {
//				ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(details.getId(),
//						details.getLogo(),
//						details.getColor(),
//						0,
//						0.0,
//						details.getTitle(),
//						"",
//						null,
//						false,
//						false,
//						details.getAssetType());
//				EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
//			}
//			else if (details.getAssetType().equals(AssetType.FUND)) {
//				FundDetailsModel fundDetailsModel = new FundDetailsModel(details.getId(),
//						details.getLogo(),
//						details.getColor(),
//						details.getTitle(),
//						"",
//						false,
//						false);
//				EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
//			}
//		}
//	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_event_details, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Window window = getDialog().getWindow();
		if (window != null) {
			window.findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			window.getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	private void setFonts() {
	}

	public void setData(InvestmentEventViewModel event) {
		this.event = event;
		updateView();
	}

	private void updateView() {
		if (event == null || this.action == null) {
			return;
		}

		this.action.setImageURI(ImageUtils.getImageUri(event.getIcon()));

		this.date.setText(DateTimeUtil.formatEventDateTime(event.getDate()));
		this.title.setText(event.getTitle());

		this.assetView.setData(event.getAssetDetails());

		List<InvestmentEventItemViewModel> values = event.getExtendedInfo();
		if (values != null && !values.isEmpty()) {
			for (InvestmentEventItemViewModel value : values) {
				valuesGroup.addView(createAmountView(value));
			}
		}
		else if (event.getAmount() != null) {
			valuesGroup.addView(createAmountView(event.getAmount(), event.getCurrency().getValue()));
		}

		List<FeeDetails> fees = event.getFeesInfo();
		if (fees != null && !fees.isEmpty()) {
			divider.setVisibility(View.VISIBLE);
			feesGroup.setVisibility(View.VISIBLE);

			for (FeeDetails fee : fees) {
				feesGroup.addView(createAmountView(fee));
			}
		}
		else {
			divider.setVisibility(View.GONE);
			feesGroup.setVisibility(View.GONE);
		}
	}

	private EventAmountView createAmountView(FeeDetails fee) {
		EventAmountView view = new EventAmountView(getContext());
		view.setData(fee.getTitle(), fee.getAmount(), fee.getCurrency().getValue());
		return view;
	}

	private EventAmountView createAmountView(InvestmentEventItemViewModel value) {
		EventAmountView view = new EventAmountView(getContext());
		view.setData(value.getTitle(), value.getAmount(), value.getCurrency().getValue());
		return view;
	}

	private EventAmountView createAmountView(Double amount, String currency) {
		EventAmountView view = new EventAmountView(getContext());
		view.setData(getString(R.string.amount), amount, currency);
		return view;
	}
}
