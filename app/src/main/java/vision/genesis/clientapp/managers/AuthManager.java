package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public static BehaviorSubject<String> token = BehaviorSubject.create();

	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	private BehaviorSubject<String> getTokenResponseSubject = BehaviorSubject.create();

	private Subscription getTokenSubscription;

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private SharedPreferencesUtil sharedPreferencesUtil;

	public AuthManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
		this.sharedPreferencesUtil = sharedPreferencesUtil;

		EventBus.getDefault().register(this);

		userSubject.onNext(null);
		tryGetSavedToken();
	}

	private void tryGetSavedToken() {
		String token = sharedPreferencesUtil.getToken();
		if (token != null) {
			AuthManager.token.onNext(token);
			createUser();
		}
	}

	public Observable<String> updateToken() {
		if (AuthManager.token.getValue() == null)
			return Observable.error(new Throwable("Token doesn't exist"));
		getToken(getUpdateTokenObservable());
		return getTokenResponseSubject;
	}

	public Observable<String> login(String email, String password) {
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);

		getToken(getLoginApiObservable(model));
		return getTokenResponseSubject;
	}

	private void getToken(Observable<String> apiMethodObservable) {
		getTokenResponseSubject = BehaviorSubject.create();
		getTokenSubscription = apiMethodObservable
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(this::handleGetTokenResponse,
						error -> {
							getTokenSubscription.unsubscribe();
							logout();
							getTokenResponseSubject.onError(error);
						});
	}

	private void handleGetTokenResponse(String token) {
		String newToken = "Bearer " + token;
		sharedPreferencesUtil.saveToken(newToken);
		createUser();
		AuthManager.token.onNext(newToken);
		getTokenResponseSubject.onNext(newToken);
	}

	private void createUser() {
		User user = new User();
		userSubject.onNext(user);
	}

	public Observable<Void> register(String email, String password, String confirmPassword) {
		return BuildConfig.FLAVOR.equals("investor")
				? registerInvestor(email, password, confirmPassword)
				: registerManager(email, password, confirmPassword);
	}

	private Observable<Void> registerInvestor(String email, String password, String confirmPassword) {
		RegisterInvestorViewModel model = new RegisterInvestorViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		return investorApi.apiInvestorAuthSignUpPost(model);
	}

	private Observable<Void> registerManager(String email, String password, String confirmPassword) {
		RegisterManagerViewModel model = new RegisterManagerViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		return managerApi.apiManagerAuthSignUpPost(model);
	}

	public Observable<Void> sendForgotPassword(String email) {
		ForgotPasswordViewModel model = new ForgotPasswordViewModel();
		model.setEmail(email);
		return investorApi.apiInvestorAuthForgotPasswordPost(model);
	}

	public Observable<Void> sendChangePassword(String oldPassword, String newPassword, String confirmPassword) {
		ChangePasswordViewModel model = new ChangePasswordViewModel();
		model.setOldPassword(oldPassword);
		model.setPassword(newPassword);
		model.setConfirmPassword(confirmPassword);
		return investorApi.apiInvestorAuthhangePasswordPost(AuthManager.token.getValue(), model);
	}

	private Observable<String> getUpdateTokenObservable() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuthUpdateTokenGet(AuthManager.token.getValue())
				: managerApi.apiManagerAuthUpdateTokenGet(AuthManager.token.getValue());
	}

	private Observable<String> getLoginApiObservable(LoginViewModel model) {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuthSignInPost(model)
				: managerApi.apiManagerAuthSignInPost(model);
	}

	public void logout() {
		sharedPreferencesUtil.saveToken(null);
		AuthManager.token.onNext(null);
		userSubject.onNext(null);
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		logout();
	}
}
