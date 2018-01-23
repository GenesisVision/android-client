package vision.genesis.clientapp.managers;

import io.swagger.client.api.AccountApi;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.User;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	private BehaviorSubject<String> loginResponseSubject = BehaviorSubject.create();

	private Subscription loginSubscription;

	private AccountApi api;

	public AuthManager(AccountApi api) {
		this.api = api;
		userSubject.onNext(null);
	}

	public Observable<String> loginInvestor(String email, String password) {
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);
		loginSubscription = api.apiInvestorAuthSignInPost(model)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(this::handleLoginResponse,
						error -> {
							loginSubscription.unsubscribe();
							loginResponseSubject.onError(error);
						});

		return loginResponseSubject;
	}

	private void handleLoginResponse(String response) {
		User user = new User();
		userSubject.onNext(user);
		loginResponseSubject.onNext("success");
	}

	public Observable<Void> registerInvestor(String email, String password, String confirmPassword) {
		RegisterInvestorViewModel model = new RegisterInvestorViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		return api.apiInvestorAuthSignUpPost(model);
	}
}
