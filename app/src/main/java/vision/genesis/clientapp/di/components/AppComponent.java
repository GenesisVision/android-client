package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.ApiModule;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AssetsModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.FilesModule;
import vision.genesis.clientapp.di.modules.NotificationsModule;
import vision.genesis.clientapp.di.modules.SettingsModule;
import vision.genesis.clientapp.di.modules.UtilsModule;
import vision.genesis.clientapp.feature.auth.forgot_password.ForgotPasswordPresenter;
import vision.genesis.clientapp.feature.auth.login.LoginPresenter;
import vision.genesis.clientapp.feature.auth.registration.RegistrationPresenter;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.app_update.AppUpdateDialog;
import vision.genesis.clientapp.feature.main.assets.AssetsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investor.InvestorDashboardPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investor.funds.DashboardFundsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investor.header.InvestorDashboardHeaderPortfolioPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investor.programs.DashboardProgramsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.manager.ManagerDashboardPresenter;
import vision.genesis.clientapp.feature.main.favorites.FavoritesPresenter;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPresenter;
import vision.genesis.clientapp.feature.main.fund.balance.FundBalancePresenter;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.InvestFundPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitPresenter;
import vision.genesis.clientapp.feature.main.fund.structure.FundStructurePresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.WithdrawFundPresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.funds_list.FundsListPresenter;
import vision.genesis.clientapp.feature.main.manager.ManagerDetailsPresenter;
import vision.genesis.clientapp.feature.main.manager.info.ManagerInfoPresenter;
import vision.genesis.clientapp.feature.main.manager.profit.ManagerProfitPresenter;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.notifications.NotificationsPresenter;
import vision.genesis.clientapp.feature.main.notifications.create.CreateCustomNotificationSettingPresenter;
import vision.genesis.clientapp.feature.main.notifications.program.ProgramNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.settings.NotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.portfolio_events.PortfolioEventsPresenter;
import vision.genesis.clientapp.feature.main.profile.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.profile.change_password.ChangePasswordPresenter;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPresenter;
import vision.genesis.clientapp.feature.main.program.balance.ProgramBalancePresenter;
import vision.genesis.clientapp.feature.main.program.chart.ChartPresenter;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramPresenter;
import vision.genesis.clientapp.feature.main.program.create.first.CreateProgramFirstStepPresenter;
import vision.genesis.clientapp.feature.main.program.create.third.CreateProgramThirdStepPresenter;
import vision.genesis.clientapp.feature.main.program.events.ProgramEventsPresenter;
import vision.genesis.clientapp.feature.main.program.info.ProgramInfoPresenter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramPresenter;
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.profit.ProgramProfitPresenter;
import vision.genesis.clientapp.feature.main.program.trades.ProgramTradesPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.confirm.ConfirmProgramWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListPresenter;
import vision.genesis.clientapp.feature.main.search.SearchPresenter;
import vision.genesis.clientapp.feature.main.settings.SettingsPresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
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

@Component(modules = {ApiModule.class, AppModule.class, AssetsModule.class, AuthModule.class, FilesModule.class, NotificationsModule.class, SettingsModule.class, UtilsModule.class})
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

	void inject(ProgramDetailsPresenter programDetailsPresenter);

	void inject(InvestProgramPresenter investProgramPresenter);

	void inject(WithdrawProgramPresenter withdrawProgramPresenter);

	void inject(DepositWalletPresenter depositWalletPresenter);

	void inject(WithdrawWalletPresenter withdrawWalletPresenter);

	void inject(TransactionsPresenter transactionsPresenter);

	void inject(ImageCropActivity imageCropActivity);

	void inject(MessageBottomSheetDialog messageBottomSheetDialog);

	void inject(ProgramTradesPresenter programTradesPresenter);

	void inject(ForgotPasswordPresenter forgotPasswordPresenter);

	void inject(ChangePasswordPresenter changePasswordPresenter);

	void inject(ProgramDataView programDataView);

	void inject(ChartPresenter chartPresenter);

	void inject(ProgramInfoPresenter programInfoPresenter);

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

	void inject(InvestorDashboardHeaderPortfolioPresenter investorDashboardHeaderPortfolioPresenter);

	void inject(DashboardProgramsPresenter dashboardProgramsPresenter);

	void inject(SelectCurrencyFragment selectCurrencyFragment);

	void inject(RequestsBottomSheetFragment requestsBottomSheetFragment);

	void inject(ProgramProfitPresenter programProfitPresenter);

	void inject(ProgramEventsPresenter programEventsPresenter);

	void inject(NotificationsPresenter notificationsPresenter);

	void inject(ConfirmProgramInvestBottomSheetFragment confirmProgramInvestBottomSheetFragment);

	void inject(ConfirmProgramWithdrawBottomSheetFragment confirmProgramWithdrawBottomSheetFragment);

	void inject(PortfolioEventsPresenter PortfolioEventsPresenter);

	void inject(SelectOptionBottomSheetFragment selectOptionBottomSheetFragment);

	void inject(ConfirmWalletWithdrawBottomSheetFragment confirmWalletWithdrawBottomSheetFragment);

	void inject(ManagerDetailsPresenter managerDetailsPresenter);

	void inject(ManagerInfoPresenter managerInfoPresenter);

	void inject(ManagerProfitPresenter managerProfitPresenter);

	void inject(ProgramBalancePresenter programBalancePresenter);

	void inject(NotificationsSettingsPresenter notificationsSettingsPresenter);

	void inject(ProgramNotificationsSettingsPresenter programNotificationsSettingsPresenter);

	void inject(CreateCustomNotificationSettingPresenter createCustomNotificationSettingPresenter);

	void inject(FundsListPresenter fundsListPresenter);

	void inject(FundDetailsPresenter fundDetailsPresenter);

	void inject(FundInfoPresenter fundInfoPresenter);

	void inject(FundStructurePresenter fundStructurePresenter);

	void inject(FundProfitPresenter fundProfitPresenter);

	void inject(FundBalancePresenter fundBalancePresenter);

	void inject(InvestFundPresenter investFundPresenter);

	void inject(ConfirmFundInvestBottomSheetFragment confirmFundInvestBottomSheetFragment);

	void inject(DashboardFundsPresenter dashboardFundsPresenter);

	void inject(WithdrawFundPresenter withdrawFundPresenter);

	void inject(ConfirmFundWithdrawBottomSheetFragment confirmFundWithdrawBottomSheetFragment);
}