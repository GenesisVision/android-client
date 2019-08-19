package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/05/2018.
 */

public class TournamentRoundButton extends RelativeLayout
{
	public interface OnRoundClickedListener
	{
		void onRoundClicked(int roundNumber);
	}

	@BindView(R.id.text)
	public TextView text;

	private OnRoundClickedListener onRoundClickedListener;

	private int roundNumber;

	public TournamentRoundButton(Context context, int roundNumber) {
		super(context);
		this.roundNumber = roundNumber;
		initView();
	}

	public TournamentRoundButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TournamentRoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.text)
	public void onClick() {
		if (isEnabled() && onRoundClickedListener != null)
			onRoundClickedListener.onRoundClicked(roundNumber);
	}

	private void initView() {
		inflate(getContext(), R.layout.view_tournament_round_button, this);

		ButterKnife.bind(this);

		text.setText(String.format(Locale.getDefault(), "%s %d", getContext().getString(R.string.round).toUpperCase(), roundNumber));
	}

	public void setSelected(boolean selected) {
		text.setBackgroundColor(selected
				? ContextCompat.getColor(getContext(), R.color.colorAccent)
				: ContextCompat.getColor(getContext(), android.R.color.transparent));
	}

	public void setOnRoundClickedListener(OnRoundClickedListener listener) {
		onRoundClickedListener = listener;
	}
}
