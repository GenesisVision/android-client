package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.ApiModule;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AssetsModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.FilesModule;
import vision.genesis.clientapp.di.modules.SettingsModule;
import vision.genesis.clientapp.di.modules.UtilsModule;
import vision.genesis.clientapp.feature.auth.forgot_password.ForgotPasswordPresenter;
import vision.genesis.clientapp.feature.auth.login.LoginPresenter;
import vision.genesis.clientapp.feature.auth.registration.RegistrationPresenter;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.app_update.AppUpdateDialog;
import vision.genesis.clientapp.feature.main.assets.AssetsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investor.InvestorDashboardPresenter;
import vision.genesis.clientapp.feature.main.dashboard.manager.ManagerDashboardPresenter;
import vision.genesis.clientapp.feature.main.favorites.FavoritesPresenter;
import vision.genesis.clientapp.feature.main.message.MessageActivity;
import vision.genesis.clientapp.feature.main.profile.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.profile.change_password.ChangePasswordPresenter;
import vision.genesis.clientapp.feature.main.program.ProgramInfoPresenter;
import vision.genesis.clientapp.feature.main.program.chart.ChartPresenter;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramPresenter;
import vision.genesis.clientapp.feature.main.program.create.first.CreateProgramFirstStepPresenter;
import vision.genesis.clientapp.feature.main.program.create.third.CreateProgramThirdStepPresenter;
import vision.genesis.clientapp.feature.main.program.description.ProgramDescriptionPresenter;
import vision.genesis.clientapp.feature.main.program.details.ProgramDetailsPresenter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramPresenter;
import vision.genesis.clientapp.feature.main.program.requests.RequestsPresenter;
import vision.genesis.clientapp.feature.main.program.trades.TradesPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramPresenter;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListPresenter;
import vision.genesis.clientapp.feature.main.search.SearchPresenter;
import vision.genesis.clientapp.feature.main.settings.SettingsPresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletPresenter;
import vision.genesis.clientapp.feature.pin.check.CheckPinPresenter;
import vision.genesis.clientapp.feature.pin.fingerprint.VerifyFingerprintPresenter;
import vision.genesis.clientapp.feature.pin.set.SetPinPresenter;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenPresenter;
import vision.genesis.clientapp.feature.two_factor.disable.DisableTfaPresenter;
import vision.genesis.clientapp.feature.two_factor.setup.SetupTfaPresenter;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.utils.AppLifecycleTracker;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Component(modules = {ApiModule.class, AppModule.class, AssetsModule.class, AuthModule.class, FilesModule.class, SettingsModule.class, UtilsModule.class})
@Singleton
public interface AppComponent
{
	void inject(SplashScreenPresenter splashScreenPresenter);

	void inject(MainPresenter mainPresenter);

	void inject(LoginPresenter loginPresenter);

	void inject(RegistrationPresenter registrationPresenter);

	void inject(InvestorDashboardPresenter investorDashboardPresenter);

	void inject(ProgramsListPresenter programsListPresenter);

	void inject(WalletPresenter walletPresenter);

	void inject(ProfilePresenter profilePresenter);

	void inject(ProgramInfoPresenter programInfoPresenter);

	void inject(InvestProgramPresenter investProgramPresenter);

	void inject(WithdrawProgramPresenter withdrawProgramPresenter);

	void inject(DepositWalletPresenter depositWalletPresenter);

	void inject(WithdrawWalletPresenter withdrawWalletPresenter);

	void inject(RequestsPresenter requestsPresenter);

	void inject(TransactionsPresenter transactionsPresenter);

	void inject(ImageCropActivity imageCropActivity);

	void inject(MessageActivity messageActivity);

	void inject(TradesPresenter tradesPresenter);

	void inject(ForgotPasswordPresenter forgotPasswordPresenter);

	void inject(ChangePasswordPresenter changePasswordPresenter);

	void inject(ProgramDataView programDataView);

	void inject(ChartPresenter chartPresenter);

	void inject(ProgramDetailsPresenter programDetailsPresenter);

	void inject(ProgramDescriptionPresenter programDescriptionPresenter);

	void inject(AssetsPresenter assetsPresenter);

	void inject(FavoritesPresenter favoritesPresenter);

	void inject(SearchPresenter searchPresenter);

	void inject(AppUpdateDialog appUpdateDialog);

	void inject(SetupTfaPresenter setupTfaPresenter);

	void inject(DisableTfaPresenter disableTfaPresenter);

	void inject(SettingsPresenter settingsPresenter);

	void inject(SetPinPresenter setPinPresenter);

	void inject(CheckPinPresenter checkPinPresenter);

	void inject(AppLifecycleTracker appLifecycleTracker);

	void inject(VerifyFingerprintPresenter verifyFingerprintPresenter);

	void inject(ManagerDashboardPresenter managerDashboardPresenter);

	void inject(CreateProgramPresenter createProgramPresenter);

	void inject(CreateProgramFirstStepPresenter createProgramFirstStepPresenter);

	void inject(CreateProgramThirdStepPresenter createProgramThirdStepPresenter);
}