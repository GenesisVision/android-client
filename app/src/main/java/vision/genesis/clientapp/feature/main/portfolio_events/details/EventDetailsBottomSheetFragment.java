package vision.genesis.clientapp.feature.main.portfolio_events.details;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.FeeDetails;
import io.swagger.client.model.InvestmentEventItemViewModel;
import io.swagger.client.model.InvestmentEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ProgramView;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
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
	public ProgramView assetView;

	@BindView(R.id.group_values)
	public ViewGroup valuesGroup;

	@BindView(R.id.divider)
	public View divider;

	@BindView(R.id.group_fees)
	public ViewGroup feesGroup;

	private InvestmentEventViewModel event;

	@OnClick(R.id.view_program)
	public void onAssetClicked() {
		if (event != null && event.getAssetDetails() != null) {
			AssetDetails details = event.getAssetDetails();
			if (details.getAssetType().equals(AssetType.PROGRAMS) || details.getAssetType().equals(AssetType.COPYTRADING)) {
				ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(details.getId(),
						details.getLogo(),
						details.getColor(),
						0,
						0.0,
						details.getTitle(),
						"",
						null,
						false,
						false);
				EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
			}
			else if (details.getAssetType().equals(AssetType.FUNDS)) {
				FundDetailsModel fundDetailsModel = new FundDetailsModel(details.getId(),
						details.getLogo(),
						details.getColor(),
						details.getTitle(),
						"",
						false,
						false);
				EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
			}
		}
	}

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

		AssetDetails asset = event.getAssetDetails();
		this.assetView.setData(getAssetType(asset.getAssetType()), asset.getLogo(), asset.getColor(), 0, 0.0, asset.getTitle(), "");

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

	private String getAssetType(AssetType assetType) {
		String result = "";
		switch (assetType) {
			case NONE:
				break;
			case PROGRAMS:
				result = getString(R.string.program);
				break;
			case FUNDS:
				result = getString(R.string.fund);
				break;
			case COPYTRADING:
				result = getString(R.string.signal_provider);
				break;
		}
		return result;
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
