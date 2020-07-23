package vision.genesis.clientapp.feature.main.social;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.social.feed.SocialLiveView;
import vision.genesis.clientapp.feature.main.social.media.SocialMediaView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialMainFragment extends BaseFragment implements SocialMainView
{
	@BindView(R.id.scrollview)
	public NestedScrollView scrollview;

	@BindView(R.id.media_view)
	public SocialMediaView mediaView;

	@BindView(R.id.live_view)
	public SocialLiveView liveView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SocialMainPresenter presenter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_social_main, container, false);
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
		super.onShow();

		presenter.onResume();
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();

		presenter.onPause();
	}

	@Override
	public void updateMedia() {
		if (mediaView != null) {
			mediaView.update();
		}
	}

	@Override
	public void updateLive() {
		if (liveView != null) {
			liveView.update();
		}
	}

	@Override
	public void openMediaUrl(String url) {
		if (getActivity() != null) {
			try {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(browserIntent);
			} catch (ActivityNotFoundException e) {
				Snackbar.make(scrollview, getString(R.string.error_cannot_open_link), Snackbar.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollview.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollview);
	}
}