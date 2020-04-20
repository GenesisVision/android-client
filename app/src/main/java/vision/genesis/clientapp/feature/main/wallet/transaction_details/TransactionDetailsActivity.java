package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AmountItem;
import io.swagger.client.model.MultiWalletTransactionStatus;
import io.swagger.client.model.TransactionAssetDetails;
import io.swagger.client.model.TransactionDetailItem;
import io.swagger.client.model.TransactionDetailsActions;
import io.swagger.client.model.TransactionViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.AssetView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ButtonsView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.StatusView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ValueView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.WalletView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

public class TransactionDetailsActivity extends BaseSwipeBackActivity implements TransactionDetailsView
{
	private static final String EXTRA_TRANSACTION = "extra_transaction";

	public static void startWith(Activity activity, TransactionViewModel transaction) {
		Intent intent = new Intent(activity.getApplicationContext(), TransactionDetailsActivity.class);
		intent.putExtra(EXTRA_TRANSACTION, transaction);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.description)
	public TextView description;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.content)
	public LinearLayout content;

	@InjectPresenter
	TransactionDetailsPresenter presenter;

	private ButtonsView buttonsView;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_transaction_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null) {
			TransactionViewModel transaction = getIntent().getExtras().getParcelable(EXTRA_TRANSACTION);
			if (transaction != null) {
				presenter.setTransaction(transaction);
				setDetails(transaction);

				return;
			}
		}
		Timber.e("Passed empty params to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setDate(DateTime date) {
		this.date.setText(DateTimeUtil.formatEventDateTime(date));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	public void setDetails(TransactionViewModel details) {
		setDate(details.getDate());
		setDescription(details.getDescription());

		if (details.getAsset() != null) {
			addAssetView(details.getAsset());
		}

		AmountItem first = details.getAmount().getFirst();
		AmountItem second = details.getAmount().getSecond();

		if (first != null) {
			addWallet(first.getLogoUrl(), first.getCurrency().getValue());
			addValue(details.getAmount().getTitle(), StringFormatUtil.getValueString(first.getAmount(), first.getCurrency().getValue()));
		}
		if (second != null) {
			addWallet(second.getLogoUrl(), second.getCurrency().getValue());
			addValue(details.getAmount().getTitle(), StringFormatUtil.getValueString(second.getAmount(), second.getCurrency().getValue()));
		}

		if (details.getDetails() != null && !details.getDetails().isEmpty()) {
			for (TransactionDetailItem detail : details.getDetails()) {
				addValue(detail.getTitle(), detail.getDetails());
			}
		}

		if (details.getStatus() != null) {
			addStatus(details.getStatus());
		}

		if (details.getActions() != null) {
			addButtons(details.getActions());
		}

		addEmptyView();
	}

	private void setDescription(String description) {
		this.description.setText(description);
	}

	private void addAssetView(TransactionAssetDetails asset) {
		AssetView view = new AssetView(this);
		view.setData(asset);
		addView(view);
	}

	private void addWallet(String logo, String name) {
		WalletView view = new WalletView(this);
		view.setData(logo, name);
		addView(view);
	}

	private void addValue(String title, String value) {
		ValueView view = new ValueView(this);
		view.setData(title, value);
		addView(view);
	}

	private void addStatus(MultiWalletTransactionStatus status) {
		StatusView view = new StatusView(this);
		view.setData(status);
		addView(view);
	}

	private void addButtons(TransactionDetailsActions actions) {
		buttonsView = new ButtonsView(this);
		buttonsView.setData(actions, presenter);
		addView(buttonsView);
	}

	private void addEmptyView() {
		View view = new View(this);
		view.setMinimumHeight(50);
		addView(view);
	}

	private void addView(View view) {
		content.addView(view);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()),
				0,
				0);
		view.setLayoutParams(lp);
	}

	@Override
	public void setEmailResent() {
		if (buttonsView != null) {
			buttonsView.onEmailResent();
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}