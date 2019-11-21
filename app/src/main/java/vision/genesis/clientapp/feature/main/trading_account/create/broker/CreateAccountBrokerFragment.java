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

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.ProgramTag;
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

public class CreateAccountBrokerFragment extends BaseFragment implements CreateAccountBrokerView
{
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

	@BindView(R.id.about)
	public TextView about;

	@BindView(R.id.account_type)
	public TextView accountType;

	@BindView(R.id.trading_platform)
	public TextView tradingPlatform;

	@BindView(R.id.assets)
	public TextView assets;

	@BindView(R.id.leverage)
	public TextView leverage;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.broker_view_width)
	public int brokerViewWidth;

	@InjectPresenter
	public CreateAccountBrokerPresenter presenter;

	private Unbinder unbinder;

	private Broker selectedBroker;

	private ArrayList<BrokerView> brokerViews = new ArrayList<>();

	@OnClick(R.id.read_terms)
	public void onReadTermsClicked() {
		if (selectedBroker != null && getActivity() != null) {
			try {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedBroker.getTerms()));
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
		return inflater.inflate(R.layout.fragment_create_account_broker, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
		nextButton.setText(String.format(Locale.getDefault(), "%s (1/3)", getString(R.string.next)));
		nextButton.setEnabled(false);
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
	public void setBrokers(List<Broker> brokers) {
		brokerViews = new ArrayList<>();
		flexBox.removeAllViews();
		for (Broker broker : brokers) {
			BrokerView view = new BrokerView(getContext());
			view.setData(broker);
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
	public void selectBroker(Broker selectedBroker, int position) {
		this.selectedBroker = selectedBroker;
		int index = 0;
		for (BrokerView brokerView : brokerViews) {
			brokerView.select(index == position);
			index++;
		}
		brokersScrollView.smoothScrollTo(position * (brokerViewWidth + TypedValueFormatter.toDp(10)), 0);
	}

	@Override
	public void showBrokerInfo(Broker broker) {
		this.brokerName.setText(broker.getName());
		this.about.setText(broker.getDescription());
		this.assets.setText(broker.getAssets());

		setTags(broker.getTags());

		String types = "";
		String platforms = "";
		for (BrokerAccountType type : broker.getAccountTypes()) {
			platforms = platforms.concat(type.getType().getValue()).concat(", ");
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

		this.accountType.setText(types);
		this.tradingPlatform.setText(platforms);

		if (broker.getLeverageMin().equals(broker.getLeverageMax())) {
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d",
					broker.getLeverageMin()));
		}
		else {
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d - 1:%d",
					broker.getLeverageMin(), broker.getLeverageMax()));
		}
	}

	private void setTags(List<ProgramTag> tags) {
		tagsFlexbox.removeAllViews();
		for (ProgramTag tag : tags) {
			addTag(createTagView(tag), tagsFlexbox);
		}
	}

	private TagView createTagView(ProgramTag tag) {
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