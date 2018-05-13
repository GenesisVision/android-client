package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/05/2018.
 */

public class TournamentRoundsButtonBar extends RelativeLayout implements TournamentRoundButton.OnRoundClickedListener
{
	public interface OnRoundSelectedListener
	{
		void onRoundSelected(int roundNumber);
	}

	@BindView(R.id.rounds_layout)
	public LinearLayout roundsLayout;

	private List<TournamentRoundButton> roundButtons = new ArrayList<>();

	private int selectedRound;

	private OnRoundSelectedListener onRoundSelectedListener;

	public TournamentRoundsButtonBar(Context context) {
		super(context);
		initView();
	}

	public TournamentRoundsButtonBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TournamentRoundsButtonBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_tournament_rounds_button_bar, this);

		ButterKnife.bind(this);
	}

	public void setRounds(Integer tournamentCurrentRound, Integer tournamentTotalRounds) {
		createRoundButtons(tournamentTotalRounds);
		selectRound(tournamentCurrentRound);
	}

	private void createRoundButtons(Integer tournamentTotalRounds) {
		roundsLayout.removeAllViews();
		for (int round = 1; round <= tournamentTotalRounds; round++) {
			TournamentRoundButton newRoundButton = createRoundButton(round);
			roundButtons.add(newRoundButton);
			addRoundButtonToLayout(newRoundButton);
		}
	}

	private TournamentRoundButton createRoundButton(int round) {
		TournamentRoundButton newRoundButton = new TournamentRoundButton(getContext(), round);
		newRoundButton.setOnRoundClickedListener(this);
		return newRoundButton;
	}

	private void addRoundButtonToLayout(TournamentRoundButton newRoundButton) {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		newRoundButton.setLayoutParams(lp);
		roundsLayout.addView(newRoundButton);
	}

	public void selectRound(int roundNumber) {
		clearRoundButtonsStates();
		TournamentRoundButton roundButtonToSelect = roundButtons.get(roundNumber - 1);
		roundButtonToSelect.setSelected(true);
		selectedRound = roundNumber;
		if (onRoundSelectedListener != null)
			onRoundSelectedListener.onRoundSelected(roundNumber);
	}


	private void clearRoundButtonsStates() {
		for (TournamentRoundButton roundButton : roundButtons) {
			roundButton.setSelected(false);
		}
	}

	public void setOnRoundSelectedListener(OnRoundSelectedListener listener) {
		onRoundSelectedListener = listener;
	}

	@Override
	public void onRoundClicked(int roundNumber) {
		selectRound(roundNumber);
	}
}
