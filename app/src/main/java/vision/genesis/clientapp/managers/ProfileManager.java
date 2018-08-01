package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.ProfileApi;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProfileManager
{
	private ProfileApi profileApi;

	private BehaviorSubject<ProfileFullViewModel> profileBehaviorSubject = BehaviorSubject.create();

	private Subscription getProfileSubscription;

	public ProfileManager(ProfileApi profileApi) {
		this.profileApi = profileApi;

		EventBus.getDefault().register(this);
	}

	public BehaviorSubject<ProfileFullViewModel> getProfileFull() {
		getProfileSubscription = getProfileFullApiObservable()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
		return profileBehaviorSubject;
	}

	private void handleGetProfileSuccess(ProfileFullViewModel model) {
		getProfileSubscription.unsubscribe();
		profileBehaviorSubject.onNext(model);
	}

	private void handleGetProfileError(Throwable error) {
		getProfileSubscription.unsubscribe();
	}

	public Observable<Void> updateProfile(ProfileFullViewModel newProfileModel) {
		return Observable.unsafeCreate(subscriber -> getUpdateProfileApiObservable(newProfileModel)
				.observeOn(Schedulers.newThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> {
							profileBehaviorSubject.onNext(newProfileModel);
							subscriber.onNext(response);
						},
						subscriber::onError));
	}

	private Observable<ProfileFullViewModel> getProfileFullApiObservable() {
		return profileApi.v10ProfileGet(AuthManager.token.getValue());
	}

	private Observable<Void> getUpdateProfileApiObservable(ProfileFullViewModel newProfileModel) {
		return profileApi.v10ProfileUpdatePost(AuthManager.token.getValue(), getUpdateProfileViewModel(newProfileModel));
	}

	private UpdateProfileViewModel getUpdateProfileViewModel(ProfileFullViewModel newProfileModel) {
		UpdateProfileViewModel updateModel = new UpdateProfileViewModel();

		updateModel.setAddress(newProfileModel.getAddress());
		updateModel.setAvatar(newProfileModel.getAvatar());
		updateModel.setBirthday(newProfileModel.getBirthday());
		updateModel.setCity(newProfileModel.getCity());
		updateModel.setCountry(newProfileModel.getCountry());
		updateModel.setCity(newProfileModel.getCity());
		updateModel.setDocumentNumber(newProfileModel.getDocumentNumber());
		updateModel.setDocumentType(newProfileModel.getDocumentType());
		updateModel.setFirstName(newProfileModel.getFirstName());
		updateModel.setMiddleName(newProfileModel.getMiddleName());
		updateModel.setLastName(newProfileModel.getLastName());
		updateModel.setGender(newProfileModel.isGender());
		updateModel.setUserName(newProfileModel.getUserName());

		return updateModel;
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		profileBehaviorSubject = BehaviorSubject.create();
	}
}
