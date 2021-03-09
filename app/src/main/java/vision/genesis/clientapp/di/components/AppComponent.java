package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.ApiModule;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AssetsModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.BinanceApiModule;
import vision.genesis.clientapp.di.modules.FilesModule;
import vision.genesis.clientapp.di.modules.NotificationsModule;
import vision.genesis.clientapp.di.modules.SettingsModule;
import vision.genesis.clientapp.di.modules.SocialModule;
import vision.genesis.clientapp.di.modules.UtilsModule;
import vision.genesis.clientapp.fcm.GvFirebaseMessagingService;
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
import vision.genesis.clientapp.feature.main.copytrading.details.CopytradingDetailsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.edit_subscription.EditSubscriptionPresenter;
import vision.genesis.clientapp.feature.main.copytrading.follow_trades.FollowTradesPresenter;
import vision.genesis.clientapp.feature.main.copytrading.open_trade_details.OpenTradeDetailsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.open_trades.CopytradingOpenTradesPresenter;
import vision.genesis.clientapp.feature.main.copytrading.select_account.SelectSubscriptionAccountPresenter;
import vision.genesis.clientapp.feature.main.copytrading.subscription_settings.SubscriptionSettingsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.subscriptions.CopytradingSubscriptionsPresenter;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryPresenter;
import vision.genesis.clientapp.feature.main.copytrading.trading_log.TradingLogPresenter;
import vision.genesis.clientapp.feature.main.copytrading.unfollow_trades.UnfollowTradesPresenter;
import vision.genesis.clientapp.feature.main.dashboard.DashboardPresenter;
import vision.genesis.clientapp.feature.main.dashboard.investments.DashboardInvestmentsView;
import vision.genesis.clientapp.feature.main.dashboard.investments.details.InvestmentsDetailsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.limit.DashboardLimitView;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.copytrading.DashboardCopytradingPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.funds.DashboardFundsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.header.InvestorDashboardHeaderPortfolioPresenter;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.programs.DashboardProgramsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.trading.DashboardTradingView;
import vision.genesis.clientapp.feature.main.dashboard.trading.details.TradingDetailsPresenter;
import vision.genesis.clientapp.feature.main.dashboard.wallet.DashboardWalletView;
import vision.genesis.clientapp.feature.main.events.EventsPresenter;
import vision.genesis.clientapp.feature.main.external.attach.AttachExternalAccountPresenter;
import vision.genesis.clientapp.feature.main.favorites.FavoritesPresenter;
import vision.genesis.clientapp.feature.main.follow.balance.FollowBalancePresenter;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowPresenter;
import vision.genesis.clientapp.feature.main.follow.create.settings.FollowSettingsPresenter;
import vision.genesis.clientapp.feature.main.follow.edit.EditFollowSettingsPresenter;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListPresenter;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPresenter;
import vision.genesis.clientapp.feature.main.fund.add_asset.AddAssetPresenter;
import vision.genesis.clientapp.feature.main.fund.balance.FundBalancePresenter;
import vision.genesis.clientapp.feature.main.fund.change_settings.ChangeFundSettingsPresenter;
import vision.genesis.clientapp.feature.main.fund.create.CreateFundPresenter;
import vision.genesis.clientapp.feature.main.fund.create.assets.FundAssetsPresenter;
import vision.genesis.clientapp.feature.main.fund.create.deposit.CreateFundDepositPresenter;
import vision.genesis.clientapp.feature.main.fund.create.fees.FundFeesPresenter;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoPresenter;
import vision.genesis.clientapp.feature.main.fund.info.owner.FundOwnerInfoPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.InvestFundPresenter;
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.manage.ManageFundPresenter;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitPresenter;
import vision.genesis.clientapp.feature.main.fund.reallocate.ReallocateFundPresenter;
import vision.genesis.clientapp.feature.main.fund.reallocate_history.FundHistoryPresenter;
import vision.genesis.clientapp.feature.main.fund.self_managed.create.CreateSelfManagedFundPresenter;
import vision.genesis.clientapp.feature.main.fund.self_managed.make_public.MakePublicFundPresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.WithdrawFundPresenter;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.funds_challenge.FundsChallengePresenter;
import vision.genesis.clientapp.feature.main.funds_list.FundsListPresenter;
import vision.genesis.clientapp.feature.main.notifications.NotificationsPresenter;
import vision.genesis.clientapp.feature.main.notifications.create.CreateCustomNotificationSettingPresenter;
import vision.genesis.clientapp.feature.main.notifications.follow.FollowNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.fund.FundNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.program.ProgramNotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.notifications.settings.NotificationsSettingsPresenter;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPresenter;
import vision.genesis.clientapp.feature.main.program.analytics.ProgramAnalyticsPresenter;
import vision.genesis.clientapp.feature.main.program.balance.ProgramBalancePresenter;
import vision.genesis.clientapp.feature.main.program.change_settings.ChangeProgramSettingsPresenter;
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
import vision.genesis.clientapp.feature.main.program.manage.ManageProgramPresenter;
import vision.genesis.clientapp.feature.main.program.open_positions.OpenPositionsPresenter;
import vision.genesis.clientapp.feature.main.program.period_history.PeriodHistoryPresenter;
import vision.genesis.clientapp.feature.main.program.profit.ProgramProfitPresenter;
import vision.genesis.clientapp.feature.main.program.reports.ProgramReportsPresenter;
import vision.genesis.clientapp.feature.main.program.trades.ProgramTradesPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.confirm.ConfirmProgramWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListPresenter;
import vision.genesis.clientapp.feature.main.rating.ProgramsRatingPresenter;
import vision.genesis.clientapp.feature.main.search.SearchPresenter;
import vision.genesis.clientapp.feature.main.settings.SettingsPresenter;
import vision.genesis.clientapp.feature.main.settings.privacy.PrivacySettingsPresenter;
import vision.genesis.clientapp.feature.main.settings.public_info.ProfilePublicInfoPresenter;
import vision.genesis.clientapp.feature.main.settings.referral_program.history.ReferralHistoryPresenter;
import vision.genesis.clientapp.feature.main.settings.referral_program.info.ReferralInfoPresenter;
import vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends.ReferralFriendsPresenter;
import vision.genesis.clientapp.feature.main.settings.security.SecurityPresenter;
import vision.genesis.clientapp.feature.main.settings.security.change_password.ChangePasswordPresenter;
import vision.genesis.clientapp.feature.main.settings.social_links.SocialLinksPresenter;
import vision.genesis.clientapp.feature.main.social.SocialMainPresenter;
import vision.genesis.clientapp.feature.main.social.feed.SocialLiveView;
import vision.genesis.clientapp.feature.main.social.feed.SocialPresenter;
import vision.genesis.clientapp.feature.main.social.media.MediaPresenter;
import vision.genesis.clientapp.feature.main.social.media.SocialMediaView;
import vision.genesis.clientapp.feature.main.social.media.details.MediaPostDetailsPresenter;
import vision.genesis.clientapp.feature.main.social.post.PostsListPresenter;
import vision.genesis.clientapp.feature.main.social.post.create.CreatePostPresenter;
import vision.genesis.clientapp.feature.main.social.post.details.PostDetailsPresenter;
import vision.genesis.clientapp.feature.main.social.post.report.ReportPostPresenter;
import vision.genesis.clientapp.feature.main.social.trending.TrendingBottomSheetView;
import vision.genesis.clientapp.feature.main.social.users.SocialUsersListAdapter;
import vision.genesis.clientapp.feature.main.social.users.SocialUsersListPresenter;
import vision.genesis.clientapp.feature.main.social.users.SocialUsersView;
import vision.genesis.clientapp.feature.main.terminal.TerminalPresenter;
import vision.genesis.clientapp.feature.main.terminal.chart.TerminalChartView;
import vision.genesis.clientapp.feature.main.terminal.info.TerminalInfoView;
import vision.genesis.clientapp.feature.main.terminal.market_trades.MarketTradesView;
import vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchPresenter;
import vision.genesis.clientapp.feature.main.terminal.open_orders.OpenOrdersPresenter;
import vision.genesis.clientapp.feature.main.terminal.order_book.OrderBookView;
import vision.genesis.clientapp.feature.main.terminal.order_history.OrderHistoryPresenter;
import vision.genesis.clientapp.feature.main.terminal.place_order.PlaceOrderPresenter;
import vision.genesis.clientapp.feature.main.terminal.symbol_watch.SymbolWatchView;
import vision.genesis.clientapp.feature.main.trading_account.TradingAccountDetailsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.add_demo_funds.AddDemoFundsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.balance.TradingAccountBalancePresenter;
import vision.genesis.clientapp.feature.main.trading_account.change_broker.ChangeBrokerPresenter;
import vision.genesis.clientapp.feature.main.trading_account.change_password.ChangeTradingAccountPasswordPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.deposit.CreateAccountDepositPresenter;
import vision.genesis.clientapp.feature.main.trading_account.create.settings.BrokerSettingsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.info.TradingAccountInfoPresenter;
import vision.genesis.clientapp.feature.main.trading_account.manage.ManageTradingAccountPresenter;
import vision.genesis.clientapp.feature.main.trading_account.open_positions.TradingAccountOpenPositionsPresenter;
import vision.genesis.clientapp.feature.main.trading_account.profit.TradingAccountProfitPresenter;
import vision.genesis.clientapp.feature.main.trading_account.trades.TradingAccountTradesPresenter;
import vision.genesis.clientapp.feature.main.unregistered.dashboard.UnregisteredDashboardPresenter;
import vision.genesis.clientapp.feature.main.user.UserDetailsPresenter;
import vision.genesis.clientapp.feature.main.user.followers.UserFollowersPresenter;
import vision.genesis.clientapp.feature.main.user.info.UserInfoPresenter;
import vision.genesis.clientapp.feature.main.users_list.fragment.UsersListPresenter;
import vision.genesis.clientapp.feature.main.verification.VerificationInfoPresenter;
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
import vision.genesis.clientapp.feature.main.wallet.transfer_funds.TransferFundsPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
import vision.genesis.clientapp.feature.pin.check.CheckPinPresenter;
import vision.genesis.clientapp.feature.pin.fingerprint.VerifyFingerprintPresenter;
import vision.genesis.clientapp.feature.pin.set.SetPinPresenter;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenPresenter;
import vision.genesis.clientapp.feature.three_factor.confirm.EmailConfirmationPresenter;
import vision.genesis.clientapp.feature.two_factor.disable.DisableTfaPresenter;
import vision.genesis.clientapp.feature.two_factor.setup.SetupTfaPresenter;
import vision.genesis.clientapp.ui.AddNewPostView;
import vision.genesis.clientapp.ui.AutoCompleteGvAssetsView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.ui.SocialCommentView;
import vision.genesis.clientapp.ui.SocialPostView;
import vision.genesis.clientapp.ui.UserShortListView;
import vision.genesis.clientapp.utils.AppLifecycleTracker;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Component(modules = {ApiModule.class, AppModule.class, AssetsModule.class, AuthModule.class, BinanceApiModule.class, FilesModule.class, NotificationsModule.class, SettingsModule.class, SocialModule.class, UtilsModule.class})
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

	void inject(EventsPresenter EventsPresenter);

	void inject(SelectOptionBottomSheetFragment selectOptionBottomSheetFragment);

	void inject(ConfirmWalletWithdrawBottomSheetFragment confirmWalletWithdrawBottomSheetFragment);

	void inject(UserDetailsPresenter userDetailsPresenter);

	void inject(UserInfoPresenter userInfoPresenter);

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

	void inject(TransferFundsPresenter transferFundsPresenter);

	void inject(TransactionDetailsPresenter transactionDetailsPresenter);

	void inject(UsersListPresenter usersListPresenter);

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

	void inject(FundHistoryPresenter fundHistoryPresenter);

	void inject(CreateFundPresenter createFundPresenter);

	void inject(PublicInfoPresenter publicInfoPresenter);

	void inject(FundAssetsPresenter fundAssetsPresenter);

	void inject(CreateFundDepositPresenter createFundDepositPresenter);

	void inject(AddAssetPresenter addAssetPresenter);

	void inject(FundFeesPresenter fundFeesPresenter);

	void inject(DashboardInvestmentsView dashboardInvestmentsView);

	void inject(DashboardTradingView dashboardTradingView);

	void inject(DashboardWalletView dashboardWalletView);

	void inject(InvestmentsDetailsPresenter investmentsDetailsPresenter);

	void inject(TradingDetailsPresenter tradingDetailsPresenter);

	void inject(CreateAccountPresenter createAccountPresenter);

	void inject(SelectBrokerPresenter selectBrokerPresenter);

	void inject(BrokerSettingsPresenter brokerSettingsPresenter);

	void inject(CreateAccountDepositPresenter createAccountDepositPresenter);

	void inject(FollowsListPresenter followsListPresenter);

	void inject(FollowInfoPresenter followInfoPresenter);

	void inject(OwnerInfoPresenter ownerInfoPresenter);

	void inject(TradingAccountDetailsPresenter tradingAccountDetailsPresenter);

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

	void inject(EditFollowSettingsPresenter editFollowSettingsPresenter);

	void inject(ManageTradingAccountPresenter manageTradingAccountPresenter);

	void inject(ChangeBrokerPresenter changeBrokerPresenter);

	void inject(ChangeTradingAccountPasswordPresenter changeTradingAccountPasswordPresenter);

	void inject(ManageProgramPresenter manageProgramPresenter);

	void inject(ChangeProgramSettingsPresenter changeProgramSettingsPresenter);

	void inject(FundOwnerInfoPresenter fundOwnerInfoPresenter);

	void inject(ManageFundPresenter manageFundPresenter);

	void inject(ChangeFundSettingsPresenter changeFundSettingsPresenter);

	void inject(ReallocateFundPresenter reallocateFundPresenter);

	void inject(FollowTradesPresenter followTradesPresenter);

	void inject(SelectSubscriptionAccountPresenter selectSubscriptionAccountPresenter);

	void inject(CopytradingDetailsPresenter copytradingDetailsPresenter);

	void inject(CopytradingSubscriptionsPresenter copytradingSubscriptionsPresenter);

	void inject(ProfilePublicInfoPresenter profilePublicInfoPresenter);

	void inject(SocialLinksPresenter socialLinksPresenter);

	void inject(ReferralInfoPresenter referralInfoPresenter);

	void inject(ReferralFriendsPresenter referralFriendsPresenter);

	void inject(ReferralHistoryPresenter referralHistoryPresenter);

	void inject(AttachExternalAccountPresenter attachExternalAccountPresenter);

	void inject(FollowBalancePresenter followBalancePresenter);

	void inject(AddDemoFundsPresenter addDemoFundsPresenter);

	void inject(FundsChallengePresenter fundsChallengePresenter);

	void inject(EditSubscriptionPresenter editSubscriptionPresenter);

	void inject(FollowNotificationsSettingsPresenter followNotificationsSettingsPresenter);

	void inject(SocialMainPresenter socialMainPresenter);

	void inject(SocialMediaView socialMediaView);

	void inject(SocialLiveView socialLiveView);

	void inject(SocialPresenter socialPresenter);

	void inject(SocialPostView socialPostView);

	void inject(PostsListPresenter postsListPresenter);

	void inject(MediaPresenter mediaPresenter);

	void inject(PostDetailsPresenter postDetailsPresenter);

	void inject(SocialCommentView socialCommentView);

	void inject(CreatePostPresenter createPostPresenter);

	void inject(AddNewPostView addNewPostView);

	void inject(AutoCompleteGvAssetsView autoCompleteGvAssetsView);

	void inject(UserFollowersPresenter userFollowersPresenter);

	void inject(UserShortListView userShortListView);

	void inject(PrivacySettingsPresenter privacySettingsPresenter);

	void inject(ReportPostPresenter reportPostPresenter);

	void inject(SocialUsersView socialUsersView);

	void inject(SocialUsersListPresenter socialUsersListPresenter);

	void inject(TrendingBottomSheetView trendingBottomSheetView);

	void inject(SocialUsersListAdapter.UserViewHolder userViewHolder);

	void inject(UnregisteredDashboardPresenter unregisteredDashboardPresenter);

	void inject(GvFirebaseMessagingService gvFirebaseMessagingService);

	void inject(MediaPostDetailsPresenter mediaPostDetailsPresenter);

	void inject(VerificationInfoPresenter verificationInfoPresenter);

	void inject(DashboardLimitView dashboardLimitView);

	void inject(CreateSelfManagedFundPresenter createSelfManagedFundPresenter);

	void inject(MakePublicFundPresenter makePublicFundPresenter);

	void inject(ProgramAnalyticsPresenter programAnalyticsPresenter);

	void inject(ProgramReportsPresenter programReportsPresenter);

	void inject(EmailConfirmationPresenter emailConfirmationPresenter);

	void inject(TerminalPresenter terminalPresenter);

	void inject(MarketWatchPresenter marketWatchPresenter);

	void inject(SymbolWatchView symbolWatchView);

	void inject(TerminalChartView terminalChartView);

	void inject(OrderBookView orderBookView);

	void inject(MarketTradesView marketTradesView);

	void inject(TerminalInfoView terminalInfoView);

	void inject(PlaceOrderPresenter placeOrderPresenter);

	void inject(OpenOrdersPresenter openOrdersPresenter);

	void inject(OrderHistoryPresenter orderHistoryPresenter);
}