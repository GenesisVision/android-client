package vision.genesis.clientapp.feature.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.auth.login.LoginFragment;
import vision.genesis.clientapp.feature.auth.registration.RegistrationFragment;
import vision.genesis.clientapp.feature.main.MainActivity;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class AuthActivity extends MvpAppCompatActivity implements AuthView
{
	public static void startFrom(Context context) {
		Intent authActivityIntent = new Intent(context, AuthActivity.class);
		authActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		authActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(authActivityIntent);
	}

	@Inject
	public NavigatorHolder navigatorHolder;

	@InjectPresenter
	AuthPresenter authPresenter;

	private Navigator navigator = new SupportAppNavigator(this, R.id.content)
	{

		@Override
		protected Intent createActivityIntent(String screenKey, Object data) {
			return null;
		}

		@Override
		protected Fragment createFragment(String screenKey, Object data) {
			switch (screenKey) {
				case Screens.LOGIN:
					return new LoginFragment();
				case Screens.REGISTRATION:
					return new RegistrationFragment();
			}
			return null;
		}

		@Override
		protected void setupFragmentTransactionAnimation(
				Command command,
				Fragment currentFragment,
				Fragment nextFragment,
				FragmentTransaction fragmentTransaction) {
			//setup animation
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		if (savedInstanceState == null) {
			navigator.applyCommand(new Replace(Screens.LOGIN, 1));
		}
	}

	@Override
	public void onResumeFragments() {
		super.onResumeFragments();
		navigatorHolder.setNavigator(navigator);
	}

	public void onPause() {
		navigatorHolder.removeNavigator();
		super.onPause();
	}

	@Override
	public void showMainActivity() {
		MainActivity.startFrom(this);
		finish();
	}
}