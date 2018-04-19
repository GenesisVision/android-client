package vision.genesis.clientapp.feature.main.program.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.ProgramDescriptionModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ProgramDescriptionFragment extends BaseFragment implements ProgramDescriptionView
{
	@BindView(R.id.program_logo)
	public AvatarView programLogo;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.description)
	public TextView description;

	@InjectPresenter
	ProgramDescriptionPresenter programDescriptionPresenter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_description, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();
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
		programName.setTypeface(TypefaceUtil.bold());
	}

	public void setData(ProgramDescriptionModel model) {
		programLogo.setImage(model.programLogo, 500, 500);
		programLogo.setLevel(model.programLevel);

		programName.setText(model.programName);
		managerName.setText(String.format(Locale.getDefault(), "%s %s", getResources().getString(R.string.by), model.managerName));

		description.setText(model.programDescription);
	}
}
