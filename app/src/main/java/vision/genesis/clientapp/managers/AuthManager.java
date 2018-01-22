package vision.genesis.clientapp.managers;

import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.User;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	public AuthManager() {

	}

	//TODO: api call
	public void login(String email, String password) {

	}
}
