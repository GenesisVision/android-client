package vision.genesis.clientapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vision.genesis.clientapp.di.modules.ApiModule;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.di.modules.AuthModule;
import vision.genesis.clientapp.di.modules.InvestModule;
import vision.genesis.clientapp.di.modules.NavigationModule;
import vision.genesis.clientapp.di.modules.TournamentModule;
import vision.genesis.clientapp.di.modules.UtilsModule;
import vision.genesis.clientapp.feature.auth.forgot_password.ForgotPasswordPresenter;
import vision.genesis.clientapp.feature.auth.login.LoginPresenter;
import vision.genesis.clientapp.feature.auth.registration.RegistrationPresenter;
import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.main.MainPresenter;
import vision.genesis.clientapp.feature.main.bottom_navigation.TabContainerFragment;
import vision.genesis.clientapp.feature.main.dashboard.DashboardPresenter;
import vision.genesis.clientapp.feature.main.message.MessageActivity;
import vision.genesis.clientapp.feature.main.profile.ImageCropActivity;
import vision.genesis.clientapp.feature.main.profile.ProfilePresenter;
import vision.genesis.clientapp.feature.main.program.details.ProgramDetailsPresenter;
import vision.genesis.clientapp.feature.main.program.filter.ProgramsFiltersPresenter;
import vision.genesis.clientapp.feature.main.program.history.ProgramHistoryPresenter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramPresenter;
import vision.genesis.clientapp.feature.main.program.list.ProgramsListPresenter;
import vision.genesis.clientapp.feature.main.program.requests.RequestsPresenter;
import vision.genesis.clientapp.feature.main.program.trades.TradesPresenter;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramPresenter;
import vision.genesis.clientapp.feature.main.wallet.WalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletPresenter;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPresenter;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletPresenter;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenPresenter;
import vision.genesis.clientapp.feature.tournament.TournamentActivity;
import vision.genesis.clientapp.feature.tournament.TournamentPresenter;
import vision.genesis.clientapp.feature.tournament.leaderboard.LeaderboardPresenter;
import vision.genesis.clientapp.feature.tournament.participants.ParticipantsPresenter;
import vision.genesis.clientapp.feature.tournament.participants.details.ParticipantDetailsPresenter;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Component(modules = {AppModule.class, NavigationModule.class, ApiModule.class, AuthModule.class, InvestModule.class, UtilsModule.class, TournamentModule.class})
@Singleton
public interface AppComponent
{
	void inject(SplashScreenPresenter splashScreenPresenter);

	void inject(MainActivity mainActivity);

	void inject(MainPresenter mainPresenter);

	void inject(LoginPresenter loginPresenter);

	void inject(RegistrationPresenter registrationPresenter);

	void inject(DashboardPresenter dashboardPresenter);

	void inject(ProgramsListPresenter programsListPresenter);

	void inject(WalletPresenter walletPresenter);

	void inject(ProfilePresenter profilePresenter);

	void inject(ProgramsFiltersPresenter programsFiltersPresenter);

	void inject(ProgramDetailsPresenter programDetailsPresenter);

	void inject(TabContainerFragment tabContainerFragment);

	void inject(TournamentActivity tournamentActivity);

	void inject(TournamentPresenter tournamentPresenter);

	void inject(ParticipantsPresenter participantsPresenter);

	void inject(ParticipantDetailsPresenter participantDetailsPresenter);

	void inject(LeaderboardPresenter leaderboardPresenter);

	void inject(InvestProgramPresenter investProgramPresenter);

	void inject(WithdrawProgramPresenter withdrawProgramPresenter);

	void inject(DepositWalletPresenter depositWalletPresenter);

	void inject(WithdrawWalletPresenter withdrawWalletPresenter);

	void inject(RequestsPresenter requestsPresenter);

	void inject(TransactionsPresenter transactionsPresenter);

	void inject(ImageCropActivity imageCropActivity);

	void inject(MessageActivity messageActivity);

	void inject(ProgramHistoryPresenter programHistoryPresenter);

	void inject(TradesPresenter tradesPresenter);

	void inject(ForgotPasswordPresenter forgotPasswordPresenter);
}