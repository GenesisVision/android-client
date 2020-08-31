package vision.genesis.clientapp.feature.main.unregistered.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/08/2020.
 */

public class UnregisteredDashboardFragment extends BaseFragment implements UnregisteredDashboardView
{
	@BindView(R.id.quote_text)
	public TextView quote;

	@BindView(R.id.quote_author)
	public TextView author;

	@InjectPresenter
	UnregisteredDashboardPresenter presenter;

	private Unbinder unbinder;

	@OnClick(R.id.button_get_started)
	public void onGetStartedClicked() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_unregistered_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	@Override
	public void onShow() {
		presenter.onResume();
	}

	@Override
	public void setQuote(String quote, String author) {
		this.quote.setText(quote);
		this.author.setText("— ".concat(author));
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, quote);
	}
}