package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PrimaryButton;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/05/2019.
 */
public class ButtonsView extends RelativeLayout
{
	public interface ButtonsActionListener
	{
		void onCancelClicked();

		void onResendEmailClicked();
	}

	@BindView(R.id.cancel)
	public PrimaryButton cancel;

	@BindView(R.id.group_email)
	public ViewGroup emailGroup;

	@BindView(R.id.resend_email)
	public PrimaryButton resendEmail;

	@BindView(R.id.text_email_sent)
	public TextView emailSentText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private ButtonsActionListener listener;

	public ButtonsView(Context context) {
		super(context);
		initView();
	}

	public ButtonsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.resend_email)
	public void onResendEmailClicked() {
		showProgress(true);

		if (listener != null)
			listener.onResendEmailClicked();
	}

	@OnClick(R.id.cancel)
	public void onCancelClicked() {
		showProgress(true);

		if (listener != null)
			listener.onCancelClicked();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_buttons, this);

		ButterKnife.bind(this);

		cancel.setEmpty();
	}

	public void onEmailResent() {
		emailSentText.setVisibility(View.VISIBLE);
		resendEmail.setVisibility(View.GONE);

		showProgress(false);
	}

	public void setListener(ButtonsActionListener listener) {
		this.listener = listener;
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		cancel.setVisibility(!show ? View.VISIBLE : View.GONE);
		emailGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}
}