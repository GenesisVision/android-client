package vision.genesis.clientapp.feature.main.trading_account.create.broker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.Tag;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.BrokerView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.TagView;
import vision.genesis.clientapp.utils.TypedValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class SelectBrokerFragment extends BaseFragment implements SelectBrokerView
{
	private static final String EXTRA_ASSET_ID = "extra_asset_id";

	private static final String EXTRA_BROKER_NAME = "extra_broker_name";

	private static final String EXTRA_IS_EXTERNAL = "extra_is_external";

	public static SelectBrokerFragment with(UUID assetId, String brokerName, Boolean isExternal) {
		SelectBrokerFragment fragment = new SelectBrokerFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_ASSET_ID, assetId);
		arguments.putString(EXTRA_BROKER_NAME, brokerName);
		arguments.putBoolean(EXTRA_IS_EXTERNAL, isExternal);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.broker_change_info)
	public TextView brokerChangeInfo;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.brokers_scrollview)
	public HorizontalScrollView brokersScrollView;

	@BindView(R.id.flex_box)
	public FlexboxLayout flexBox;

	@BindView(R.id.broker_name)
	public TextView brokerName;

	@BindView(R.id.tags_flexbox)
	public FlexboxLayout tagsFlexbox;

	@BindView(R.id.warning_info)
	public TextView warningInfo;

	@BindView(R.id.about)
	public TextView about;

	@BindView(R.id.label_account_type)
	public TextView accountTypeLabel;

	@BindView(R.id.account_type)
	public TextView accountType;

	@BindView(R.id.trading_platform)
	public TextView tradingPlatform;

	@BindView(R.id.assets)
	public TextView assets;

	@BindView(R.id.label_leverage)
	public TextView leverageLabel;

	@BindView(R.id.leverage)
	public TextView leverage;

	@BindView(R.id.read_terms)
	public TextView readTerms;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.broker_view_width)
	public int brokerViewWidth;

	@InjectPresenter
	public SelectBrokerPresenter presenter;

	private Unbinder unbinder;

	private ExchangeInfo selectedExchange;

	private Broker selectedBroker;

	private ArrayList<BrokerView> exchangeViews = new ArrayList<>();

	private ArrayList<BrokerView> brokerViews = new ArrayList<>();

	private String currentBrokerName;

	@OnClick(R.id.read_terms)
	public void onReadTermsClicked() {
		if ((selectedBroker != null || selectedExchange != null) && getActivity() != null) {
			try {
				String terms = selectedExchange != null ? selectedExchange.getTerms() : selectedBroker.getTerms();
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(terms));
				getActivity().startActivity(browserIntent);
			} catch (ActivityNotFoundException e) {
				Snackbar.make(scrollView, getString(R.string.error_cannot_open_link), Snackbar.LENGTH_LONG).show();
			}
		}
	}

	@OnClick(R.id.button_next)
	public void onNextClicked() {
		presenter.onNextClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_select_broker, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
		nextButton.setEnabled(false);

		if (getArguments() != null) {
			UUID assetId = (UUID) getArguments().getSerializable(EXTRA_ASSET_ID);
			currentBrokerName = getArguments().getString(EXTRA_BROKER_NAME, null);
			boolean isExternal = getArguments().getBoolean(EXTRA_IS_EXTERNAL, false);

			presenter.setData(assetId, isExternal);

//			nextButton.setText(String.format(Locale.getDefault(), "%s (1/%d)", getString(R.string.next), assetId != null || isExternal ? 2 : 3));
			brokerChangeInfo.setVisibility(assetId != null ? View.VISIBLE : View.GONE);
			return;
		}

		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
		brokerName.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setExchanges(List<ExchangeInfo> exchanges) {
		exchangeViews = new ArrayList<>();
		flexBox.removeAllViews();
		for (ExchangeInfo exchange : exchanges) {
			BrokerView view = new BrokerView(getContext());
			view.setExchange(exchange);
			view.setOnClickListener(view1 -> presenter.onExchangeSelected(exchange));
			exchangeViews.add(view);
			flexBox.addView(view);

			FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(0,
					0,
					TypedValueFormatter.toDp(10),
					TypedValueFormatter.toDp(10));
			view.setLayoutParams(lp);
		}
	}

	@Override
	public void setBrokers(List<Broker> brokers) {
		brokerViews = new ArrayList<>();
		for (Broker broker : brokers) {
			BrokerView view = new BrokerView(getContext());
			view.setBroker(broker);
			view.setOnClickListener(view1 -> presenter.onBrokerSelected(broker));
			brokerViews.add(view);
			flexBox.addView(view);

			FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(0,
					0,
					TypedValueFormatter.toDp(10),
					TypedValueFormatter.toDp(10));
			view.setLayoutParams(lp);
		}
	}

	@Override
	public void selectExchange(ExchangeInfo selectedExchange, int position) {
		this.selectedExchange = selectedExchange;
		this.selectedBroker = null;
		updateSelections(position);
		brokersScrollView.smoothScrollTo(position * (brokerViewWidth + TypedValueFormatter.toDp(10)), 0);
	}

	@Override
	public void selectBroker(Broker selectedBroker, int position) {
		this.selectedBroker = selectedBroker;
		this.selectedExchange = null;
		updateSelections(position);
		brokersScrollView.smoothScrollTo(position * (brokerViewWidth + TypedValueFormatter.toDp(10)), 0);
	}

	private void updateSelections(int position) {
		int index = 0;
		for (BrokerView exchangeView : exchangeViews) {
			exchangeView.select(index == position);
			index++;
		}
		for (BrokerView brokerView : brokerViews) {
			brokerView.select(index == position);
			index++;
		}
	}

	@Override
	public void showExchangeInfo(ExchangeInfo exchange) {
		this.brokerName.setText(exchange.getName());
		this.about.setText(exchange.getDescription());
		this.assets.setText(exchange.getAssets());

		if (currentBrokerName != null
				&& currentBrokerName.toLowerCase().equals("genesis markets")
				&& exchange.getName().toLowerCase().equals("huobi")) {
			warningInfo.setVisibility(View.VISIBLE);
			warningInfo.setText(getString(R.string.warning_info_switch_gm_to_huobi));
		}
		else {
			warningInfo.setVisibility(View.GONE);
		}

		setTags(exchange.getTags());

		this.accountTypeLabel.setVisibility(View.GONE);
		this.accountType.setVisibility(View.GONE);

		String platforms = "";
		for (ExchangeAccountType type : exchange.getAccountTypes()) {
			platforms = platforms.concat(type.getTypeTitle()).concat(", ");
		}
		if (platforms.length() >= 2) {
			platforms = platforms.substring(0, platforms.length() - 2);
		}
		this.tradingPlatform.setText(platforms);

		this.leverageLabel.setVisibility(View.GONE);
		this.leverage.setVisibility(View.GONE);

		readTerms.setVisibility(selectedExchange.getTerms() == null ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showBrokerInfo(Broker broker) {
		this.brokerName.setText(broker.getName());
		this.about.setText(broker.getDescription());
		this.assets.setText(broker.getAssets());

		if (currentBrokerName != null
				&& currentBrokerName.toLowerCase().equals("genesis markets")
				&& broker.getName().toLowerCase().equals("huobi")) {
			warningInfo.setVisibility(View.VISIBLE);
			warningInfo.setText(getString(R.string.warning_info_switch_gm_to_huobi));
		}
		else {
			warningInfo.setVisibility(View.GONE);
		}

		setTags(broker.getTags());

		String types = "";
		String platforms = "";
		for (BrokerAccountType type : broker.getAccountTypes()) {
			platforms = platforms.concat(type.getTypeTitle()).concat(", ");
			for (String currency : type.getCurrencies()) {
				types = types.concat(currency).concat(", ");
			}
		}

		if (platforms.length() >= 2) {
			platforms = platforms.substring(0, platforms.length() - 2);
		}

		if (types.length() >= 2) {
			types = types.substring(0, types.length() - 2);
		}

		this.accountTypeLabel.setVisibility(View.VISIBLE);
		this.accountType.setVisibility(View.VISIBLE);

		this.accountType.setText(types);
		this.tradingPlatform.setText(platforms);

		this.leverageLabel.setVisibility(View.VISIBLE);
		this.leverage.setVisibility(View.VISIBLE);

		if (broker.getLeverageMin().equals(broker.getLeverageMax())) {
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d",
					broker.getLeverageMin()));
		}
		else {
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d - 1:%d",
					broker.getLeverageMin(), broker.getLeverageMax()));
		}

		readTerms.setVisibility(selectedBroker.getTerms() == null ? View.GONE : View.VISIBLE);
	}

	private void setTags(List<Tag> tags) {
		tagsFlexbox.removeAllViews();
		for (Tag tag : tags) {
			addTag(createTagView(tag), tagsFlexbox);
		}
	}

	private TagView createTagView(Tag tag) {
		TagView view = new TagView(getContext());
		view.setTag(tag);
		return view;
	}

	private void addTag(TagView tagView, FlexboxLayout tagsFlexbox) {
		tagsFlexbox.addView(tagView);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) tagView.getLayoutParams();
		lp.setMargins(0,
				0,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()),
				0);
		tagView.setLayoutParams(lp);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, nextButton);
	}

	@Override
	public void setNextButtonEnabled(boolean enabled) {
		nextButton.setEnabled(enabled);
	}
}