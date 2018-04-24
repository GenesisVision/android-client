package vision.genesis.clientapp.feature.main.program;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramDetails;
import io.swagger.client.model.InvestmentProgramViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnPeriodLeftEvent;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class ProgramInfoPresenter extends MvpPresenter<ProgramInfoView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public InvestManager investManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription setProgramFavoriteSubscription;

	private UUID programId;

	private InvestmentProgramDetails programDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();

		if (programDetailsSubscription != null)
			programDetailsSubscription.unsubscribe();

		if (setProgramFavoriteSubscription != null)
			setProgramFavoriteSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
	}

	void onFavoriteButtonClicked(boolean isFavorite) {
		setProgramFavorite(isFavorite);
	}

	void onResume() {
		if (programId != null)
			getProgramDetails();
	}

	private void getProgramDetails() {
		programDetailsSubscription = investManager.getInvestmentProgramDetails(programId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleInvestmentProgramDetailsSuccess,
						this::handleInvestmentProgramDetailsError);
	}

	private void handleInvestmentProgramDetailsSuccess(InvestmentProgramViewModel model) {
		programDetailsSubscription.unsubscribe();

		programDetails = model.getInvestmentProgram();
		getViewState().setProgram(programDetails);
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
	}

	private void setProgramFavorite(boolean isFavorite) {
		setProgramFavoriteSubscription = investManager.setProgramFavorite(programId, isFavorite)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleSetProgramFavoriteSuccess(response, programId, isFavorite),
						this::handleSetProgramFavoriteError);
	}

	private void handleSetProgramFavoriteSuccess(Void response, UUID programId, boolean isFavorite) {
		setProgramFavoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new ProgramIsFavoriteChangedEvent(programId, isFavorite));
	}

	private void handleSetProgramFavoriteError(Throwable throwable) {
		setProgramFavoriteSubscription.unsubscribe();

		getViewState().setProgram(programDetails);
		getViewState().showToast(context.getString(R.string.error_occurred_performing_operation));
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null)
			userLoggedOff();
		else
			userLoggedOn();
	}

	private void userLoggedOn() {
	}

	private void userLoggedOff() {
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnPeriodLeftEvent event) {
		getProgramDetails();
	}
}
