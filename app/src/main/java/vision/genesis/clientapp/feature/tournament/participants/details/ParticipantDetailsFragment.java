package vision.genesis.clientapp.feature.tournament.participants.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ParticipantViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class ParticipantDetailsFragment extends BaseFragment implements ParticipantDetailsView
{
	private static String EXTRA_PARTICIPANT_ID = "extra_participant_id";

	public static ParticipantDetailsFragment with(UUID participantId) {
		ParticipantDetailsFragment participantDetailsFragment = new ParticipantDetailsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PARTICIPANT_ID, participantId);
		participantDetailsFragment.setArguments(arguments);
		return participantDetailsFragment;
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.avatar)
	public ProgramLogoView avatar;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.chart)
	public ProfitChartView chart;

	@BindView(R.id.text_place_text)
	public TextView placeText;

	@BindView(R.id.text_trades_text)
	public TextView tradesText;

	@BindView(R.id.text_profit_text)
	public TextView profitText;

	@BindView(R.id.text_profit_percent_text)
	public TextView profitPercentText;

	@BindView(R.id.group_details)
	public ViewGroup detailsGroup;

	@BindView(R.id.group_cannot_load)
	public ViewGroup cannotLoadGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ParticipantDetailsPresenter participantDetailsPresenter;

	private ParticipantViewModel participant;

	@OnClick(R.id.button_ipfs)
	public void onIpfsClicked() {
		if (participant.getOrdersCount() == 0)
			return;
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gateway.ipfs.io/ipfs/" + participant.getIpfsHash()));
		startActivity(browserIntent);
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		participantDetailsPresenter.onTryAgainClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_participant_details, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (getArguments() != null && !getArguments().isEmpty()) {
			participantDetailsPresenter.setParticipantId((UUID) getArguments().getSerializable(EXTRA_PARTICIPANT_ID));
		}
		else {
			Timber.e("Passed empty participantId to ParticipantDetailsFragment");
			participantDetailsPresenter.onBackClicked();
		}

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.participant_details));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> participantDetailsPresenter.onBackClicked());
	}

	@Override
	public void setParticipant(ParticipantViewModel participant) {
		this.participant = participant;
		avatar.setImageUrl(participant.getAvatar());
		avatar.hideLevel();
		name.setText(participant.getName());

		placeText.setText(String.valueOf(participant.getPlace()));
		tradesText.setText(String.valueOf(participant.getOrdersCount()));
		profitText.setText(String.valueOf(participant.getTotalProfit()));

		double profitPercent = participant.getTotalProfitInPercent();
		profitPercentText.setText(String.format(Locale.getDefault(), "%.2f%%", profitPercent));
		if (profitPercent > 0)
			profitPercentText.setTextColor(ContextCompat.getColor(getContext(), R.color.transactionGreen));
		else if (profitPercent < 0)
			profitPercentText.setTextColor(ContextCompat.getColor(getContext(), R.color.transactionRed));
		else
			profitPercentText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

		chart.showDetails();
		chart.setDataDouble(participant.getChart());
	}

	@Override
	public void showLoading(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		detailsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showCannotLoad(boolean show) {
		cannotLoadGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		detailsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public boolean onBackPressed() {
		participantDetailsPresenter.onBackClicked();
		return true;
	}
}