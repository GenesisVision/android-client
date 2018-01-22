package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.NavigationModule;
import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.dashboard.DashboardPresenter;
import vision.genesis.clientapp.feature.main.invest.InvestPresenter;
import vision.genesis.clientapp.feature.main.login.LoginPresenter;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenPresenter;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Component(modules = {AppModule.class, NavigationModule.class, AuthModule.class})
@Singleton
public interface AppComponent
{
	void inject(SplashScreenPresenter splashScreenPresenter);

	void inject(MainActivity mainActivity);

	void inject(MainPresenter mainPresenter);

	void inject(LoginPresenter loginPresenter);

	void inject(DashboardPresenter dashboardPresenter);

	void inject(InvestPresenter investPresenter);

	void inject(WalletPresenter walletPresenter);

	void inject(ProfilePresenter profilePresenter);
}