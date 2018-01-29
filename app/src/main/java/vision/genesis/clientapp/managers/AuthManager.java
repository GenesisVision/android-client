package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public static BehaviorSubject<String> token = BehaviorSubject.create();

	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	private BehaviorSubject<String> loginResponseSubject = BehaviorSubject.create();

	private Subscription loginSubscription;

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private SharedPreferencesUtil sharedPreferencesUtil;

	public AuthManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
		this.sharedPreferencesUtil = sharedPreferencesUtil;
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

	public Observable<String> login(String email, String password) {
		loginResponseSubject = BehaviorSubject.create();
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);
		Observable<String> loginApiObservable = getLoginApiObservable(model);
		loginSubscription = loginApiObservable
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(this::handleLoginResponse,
						error -> {
							loginSubscription.unsubscribe();
							loginResponseSubject.onError(error);
						});

		return loginResponseSubject;
	}

	private void handleLoginResponse(String token) {
		String newToken = "Bearer " + token;
		sharedPreferencesUtil.saveToken(newToken);
		createUser();
		AuthManager.token.onNext(newToken);
		loginResponseSubject.onNext("success");
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
}
