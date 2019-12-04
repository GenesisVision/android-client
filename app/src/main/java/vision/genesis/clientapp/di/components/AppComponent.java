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
import vision.genesis.clientapp.feature.common.image_crop.ImageCropActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoPresenter;
import vision.genesis.clientapp.feature.common.public_info.edit.EditPublicInfoPresenter;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.about_levels.AboutLevelsPresenter;
import vision.genesis.clientapp.feature.main.app_update.AppUpdateDialog;
import vision.genesis.clientapp.feature.main.assets.AssetsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.create_account.CreateCopytradingAccountPresenter;
import vision.genesis.clientapp.feature.main.copytrading.open_trade_details.OpenTradeDetailsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.open_trades.CopytradingOpenTradesPresenter;
import vision.genesis.clientapp.feature.main.copytrading.subscription_settings.SubscriptionSettingsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryPresenter;
import vision.genesis.clientapp.feature.main.copytrading.trading_log.TradingLogPresenter;
import vision.genesis.clientapp.feature.main.copytrading.unfollow_trades.UnfollowTradesPresenter;
import vision.genesis.clientapp.feature.main.dashboard.DashboardPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investments.DashboardInvestmentsView;
import vision.genesis.clientapp.feature.main.dashboard.investments.details.InvestmentsDetailsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.copytrading.DashboardCopytradingPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.funds.DashboardFundsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.header.InvestorDashboardHeaderPortfolioPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.programs.DashboardProgramsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.trading.DashboardTradingView;
import vision.genesis.clientapp.feature.main.dashboard.trading.details.TradingDetailsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.wallet.DashboardWalletView;
import vision.genesis.clientapp.feature.main.favorites.FavoritesPresenter;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowPresenter;
import vision.genesis.clientapp.feature.main.follow.create.settings.FollowSettingsPresenter;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListPresenter;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPresenter;
import vision.genesis.clientapp.feature.main.fund.add_asset.AddAssetPresenter;
import vision.genesis.clientapp.feature.main.fund.balance.FundBalancePresenter;
import vision.genesis.clientapp.feature.main.fund.create.CreateFundPresenter;
import vision.genesis.clientapp.feature.main.fund.create.assets.CreateFundAssetsPresenter;
import vision.genesis.clientapp.feature.main.fund.create.deposit.CreateFundDepositPresenter;
import vision.genesis.clientapp.feature.main.fund.create.fees.CreateFundFeesPresenter;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.InvestFundPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitPresenter;
import vision.genesis.clientapp.feature.main.fund.reallocate_history.ReallocateHistoryPresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.WithdrawFundPresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.funds_list.FundsListPresenter;
import vision.genesis.clientapp.feature.main.manager.ManagerDetailsPresenter;
import vision.genesis.clientapp.feature.main.manager.info.ManagerInfoPresenter;
import vision.genesis.clientapp.feature.main.managers_list.ManagersListPresenter;
import vision.genesis.clientapp.feature.main.notifications.NotificationsPresenter;
import vision.genesis.clientapp.feature.main.notifications.create.CreateCustomNotificationSettingPresenter;
import vision.genesis.clientapp.feature.main.notifications.fund.FundNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.program.ProgramNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.settings.NotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.portfolio_events.PortfolioEventsPresenter;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPresenter;
import vision.genesis.clientapp.feature.main.program.balance.ProgramBalancePresenter;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramPresenter;
import vision.genesis.clientapp.feature.main.program.create.deposit.CreateProgramDepositPresenter;
import vision.genesis.clientapp.feature.main.program.create.settings.ProgramSettingsPresenter;
import vision.genesis.clientapp.feature.main.program.events.ProgramEventsPresenter;
import vision.genesis.clientapp.feature.main.program.info.follow.FollowInfoPresenter;
import vision.genesis.clientapp.feature.main.program.info.owner.OwnerInfoPresenter;
import vision.genesis.clientapp.feature.main.program.info.program.ProgramInfoPresenter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramPresenter;
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.level.ProgramLevelBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.open_positions.OpenPositionsPresenter;
import vision.genesis.clientapp.feature.main.program.period_history.PeriodHistoryPresenter;
import vision.genesis.clientapp.feature.main.program.profit.ProgramProfitPresenter;
import vision.genesis.clientapp.feature.main.program.trades.ProgramTradesPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.confirm.ConfirmProgramWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListPresenter;
import vision.genesis.clientapp.feature.main.rating.ProgramsRatingPresenter;
import vision.genesis.clientapp.feature.main.search.SearchPresenter;
import vision.genesis.clientapp.feature.main.settings.SettingsPresenter;
import vision.genesis.clientapp.feature.main.settings.security.SecurityPresenter;
import vision.genesis.clientapp.feature.main.settings.security.change_password.ChangePasswordPresenter;
import vision.genesis.clientapp.feature.main.trading_account.TradingAccountPresenter;
import vision.genesis.clientapp.feature.main.trading_account.balance.TradingAccountBalancePresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.CreateAccountBrokerPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.deposit.CreateAccountDepositPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.settings.CreateAccountSettingsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.info.TradingAccountInfoPresenter;
import vision.genesis.clientapp.feature.main.trading_account.open_positions.TradingAccountOpenPositionsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.profit.TradingAccountProfitPresenter;
import vision.genesis.clientapp.feature.main.trading_account.trades.TradingAccountTradesPresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.copytrading_account_details.CopytradingAccountDetailsPresenter;
import vision.genesis.clientapp.feature.main.wallet.copytrading_accounts.CopytradingAccountsPresenter;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.external_transactions.ExternalTransactionsPresenter;
import vision.genesis.clientapp.feature.main.wallet.my_wallets.MyWalletsPresenter;
import vision.genesis.clientapp.feature.main.wallet.specific_wallet.SpecificWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.TransactionDetailsPresenter;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPresenter;
import vision.genesis.clientapp.feature.main.wallet.transfer_copytrading_account.TransferCopytradingAccountPresenter;
import vision.genesis.clientapp.feature.main.wallet.transfer_wallet.TransferWalletPresenter;
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

	void inject(DashboardPresenter dashboardPresenter);

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

	void inject(ProgramTradesPresenter programTradesPresenter);

	void inject(ForgotPasswordPresenter forgotPasswordPresenter);

	void inject(ChangePasswordPresenter changePasswordPresenter);

	void inject(ProgramDataView programDataView);

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

	void inject(ProgramBalancePresenter programBalancePresenter);

	void inject(NotificationsSettingsPresenter notificationsSettingsPresenter);

	void inject(ProgramNotificationsSettingsPresenter programNotificationsSettingsPresenter);

	void inject(CreateCustomNotificationSettingPresenter createCustomNotificationSettingPresenter);

	void inject(FundsListPresenter fundsListPresenter);

	void inject(FundDetailsPresenter fundDetailsPresenter);

	void inject(FundInfoPresenter fundInfoPresenter);

	void inject(FundProfitPresenter fundProfitPresenter);

	void inject(FundBalancePresenter fundBalancePresenter);

	void inject(InvestFundPresenter investFundPresenter);

	void inject(ConfirmFundInvestBottomSheetFragment confirmFundInvestBottomSheetFragment);

	void inject(DashboardFundsPresenter dashboardFundsPresenter);

	void inject(WithdrawFundPresenter withdrawFundPresenter);

	void inject(ConfirmFundWithdrawBottomSheetFragment confirmFundWithdrawBottomSheetFragment);

	void inject(SecurityPresenter securityPresenter);

	void inject(FundNotificationsSettingsPresenter fundNotificationsSettingsPresenter);

	void inject(OpenPositionsPresenter openPositionsPresenter);

	void inject(MyWalletsPresenter myWalletsPresenter);

	void inject(ExternalTransactionsPresenter externalTransactionsPresenter);

	void inject(SpecificWalletPresenter specificWalletPresenter);

	void inject(TransferWalletPresenter transferWalletPresenter);

	void inject(TransactionDetailsPresenter transactionDetailsPresenter);

	void inject(ManagersListPresenter managersListPresenter);

	void inject(ProgramsRatingPresenter programsRatingPresenter);

	void inject(AboutLevelsPresenter aboutLevelsPresenter);

	void inject(ProgramLevelBottomSheetDialog programLevelBottomSheetDialog);

	void inject(CreateCopytradingAccountPresenter createCopytradingAccountPresenter);

	void inject(SubscriptionSettingsPresenter subscriptionSettingsPresenter);

	void inject(UnfollowTradesPresenter unfollowTradesPresenter);

	void inject(CopytradingAccountsPresenter copytradingAccountsPresenter);

	void inject(CopytradingAccountDetailsPresenter copytradingAccountDetailsPresenter);

	void inject(DashboardCopytradingPresenter dashboardCopytradingPresenter);

	void inject(CopytradingOpenTradesPresenter dashboardOpenTradesPresenter);

	void inject(CopytradingTradesHistoryPresenter dashboardTradesHistoryPresenter);

	void inject(OpenTradeDetailsPresenter openTradeDetailsPresenter);

	void inject(TransferCopytradingAccountPresenter transferCopytradingAccountPresenter);

	void inject(TradingLogPresenter tradingLogPresenter);

	void inject(PeriodHistoryPresenter periodHistoryPresenter);

	void inject(ReallocateHistoryPresenter reallocateHistoryPresenter);

	void inject(CreateFundPresenter createFundPresenter);

	void inject(PublicInfoPresenter publicInfoPresenter);

	void inject(CreateFundAssetsPresenter createFundAssetsPresenter);

	void inject(CreateFundDepositPresenter createFundDepositPresenter);

	void inject(AddAssetPresenter addAssetPresenter);

	void inject(CreateFundFeesPresenter createFundFeesPresenter);

	void inject(DashboardInvestmentsView dashboardInvestmentsView);

	void inject(DashboardTradingView dashboardTradingView);

	void inject(DashboardWalletView dashboardWalletView);

	void inject(InvestmentsDetailsPresenter investmentsDetailsPresenter);

	void inject(TradingDetailsPresenter tradingDetailsPresenter);

	void inject(CreateAccountPresenter createAccountPresenter);

	void inject(CreateAccountBrokerPresenter createAccountBrokerPresenter);

	void inject(CreateAccountSettingsPresenter createAccountSettingsPresenter);

	void inject(CreateAccountDepositPresenter createAccountDepositPresenter);

	void inject(FollowsListPresenter followsListPresenter);

	void inject(FollowInfoPresenter followInfoPresenter);

	void inject(OwnerInfoPresenter ownerInfoPresenter);

	void inject(TradingAccountPresenter tradingAccountPresenter);

	void inject(TradingAccountInfoPresenter tradingAccountInfoPresenter);

	void inject(TradingAccountOpenPositionsPresenter tradingAccountOpenPositionsPresenter);

	void inject(TradingAccountProfitPresenter tradingAccountProfitPresenter);

	void inject(TradingAccountBalancePresenter tradingAccountBalancePresenter);

	void inject(TradingAccountTradesPresenter tradingAccountTradesPresenter);

	void inject(CreateProgramPresenter createProgramPresenter);

	void inject(ProgramSettingsPresenter programSettingsPresenter);

	void inject(CreateProgramDepositPresenter createProgramDepositPresenter);

	void inject(CreateFollowPresenter createFollowPresenter);

	void inject(FollowSettingsPresenter followSettingsPresenter);

	void inject(EditPublicInfoPresenter editPublicInfoPresenter);
}