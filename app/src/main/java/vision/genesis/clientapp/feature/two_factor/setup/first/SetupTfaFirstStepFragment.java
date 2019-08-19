package vision.genesis.clientapp.feature.two_factor.setup.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.events.SetupTfaNextButtonClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public class SetupTfaFirstStepFragment extends BaseFragment
{
	@BindView(R.id.tfa_tutorial_view_pager)
	public ViewPager viewPager;

	@BindView(R.id.indicator)
	public ScrollingPagerIndicator indicator;

	@BindView(R.id.button_next)
	public View nextButton;

	private Unbinder unbinder;

	@OnClick(R.id.button_next)
	public void onNextButtonClicked() {
		EventBus.getDefault().post(new SetupTfaNextButtonClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setup_tfa_first_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
		initViewPager();
	}

	private void initViewPager() {
		viewPager.setAdapter(new TfaTutorialPagerAdapter(getChildFragmentManager()));
		indicator.attachToPager(viewPager);
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
	}
}
