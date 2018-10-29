package vision.genesis.clientapp.feature.two_factor.setup.forth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.events.SetupTfaFinishedEvent;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public class SetupTfaForthStepFragment extends BaseFragment
{
	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.text_left_column)
	public TextView leftColumn;

	@BindView(R.id.text_right_column)
	public TextView rightColumn;

	@BindView(R.id.button_ok)
	public PrimaryButton okButton;

	private Unbinder unbinder;

	@OnClick(R.id.button_ok)
	public void onOkButtonClicked() {
		EventBus.getDefault().post(new SetupTfaFinishedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setup_tfa_forth_step, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	public void setCodes(ArrayList<String> codes) {
		for (int i = 0; i < codes.size(); i++) {
			if (i % 2 == 0) {
				leftColumn.append(codes.get(i));
				if (i < codes.size() - 2)
					leftColumn.append("\n");
			}
			else {
				rightColumn.append(codes.get(i));
				if (i < codes.size() - 2)
					rightColumn.append("\n");
			}
		}
	}
}
