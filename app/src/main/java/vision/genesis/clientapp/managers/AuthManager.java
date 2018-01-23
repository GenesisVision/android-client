package vision.genesis.clientapp.managers;

import io.swagger.client.api.AccountApi;
import io.swagger.client.model.LoginViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.User;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	private AccountApi api;

	public AuthManager(AccountApi api) {
		this.api = api;
		userSubject.onNext(null);
	}

	public Observable<String> login(String email, String password) {
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);
		return api.apiInvestorAuthSignInPost(model);
	}
}
