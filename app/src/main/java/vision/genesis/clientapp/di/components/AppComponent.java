package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.ApiModule;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.InvestModule;
import vision.genesis.clientapp.di.modules.NavigationModule;
import vision.genesis.clientapp.feature.auth.AuthActivity;
import vision.genesis.clientapp.feature.auth.AuthPresenter;
import vision.genesis.clientapp.feature.auth.login.LoginPresenter;
import vision.genesis.clientapp.feature.auth.registration.RegistrationPresenter;
import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.dashboard.DashboardPresenter;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.traders.TradersPresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenPresenter;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Component(modules = {AppModule.class, NavigationModule.class, ApiModule.class, AuthModule.class, InvestModule.class})
@Singleton
public interface AppComponent
{
	void inject(SplashScreenPresenter splashScreenPresenter);

	void inject(MainActivity mainActivity);

	void inject(MainPresenter mainPresenter);

	void inject(AuthActivity authActivity);

	void inject(AuthPresenter authPresenter);

	void inject(LoginPresenter loginPresenter);

	void inject(RegistrationPresenter registrationPresenter);

	void inject(DashboardPresenter dashboardPresenter);

	void inject(TradersPresenter tradersPresenter);

	void inject(WalletPresenter walletPresenter);

	void inject(ProfilePresenter profilePresenter);
}