package vision.genesis.clientapp.feature.main.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class ProfileFragment extends BaseFragment implements ProfileView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.first_name)
	public TextView firstName;

	@BindView(R.id.middle_name)
	public TextView middleName;

	@BindView(R.id.last_name)
	public TextView lastName;

	@BindView(R.id.email)
	public TextView email;

	@BindView(R.id.gender)
	public TextView gender;

	@BindView(R.id.birthday)
	public TextView birthday;

	@BindView(R.id.country)
	public TextView country;

	@BindView(R.id.city)
	public TextView city;

	@BindView(R.id.address)
	public TextView address;

	@BindView(R.id.phone)
	public TextView phone;

	@BindView(R.id.document_type)
	public TextView documentType;

	@BindView(R.id.document_number)
	public TextView documentNumber;

	@InjectPresenter
	ProfilePresenter profilePresenter;

	@ProvidePresenter
	public ProfilePresenter provideProfilePresenter() {
		return new ProfilePresenter(((RouterProvider) getParentFragment()).getRouter());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.profile));
		toolbar.addRightButton(R.drawable.ic_exit_to_app_black_24dp, () -> profilePresenter.onLogoutClicked());
	}

	@Override
	public void updateProfile(ProfileFullViewModel profile) {
		firstName.setText(profile.getFirstName());
		middleName.setText(profile.getMiddleName());
		lastName.setText(profile.getLastName());
		email.setText(profile.getEmail());
		gender.setText(profile.isGender() ? "M" : "F");
		birthday.setText((DateTimeUtil.formatDate(profile.getBirthday())));
		country.setText(profile.getCountry());
		city.setText(profile.getCity());
		address.setText(profile.getAddress());
		phone.setText(profile.getPhone());
		documentType.setText(profile.getDocumentType());
		documentNumber.setText(profile.getDocumentNumber());
	}
}
