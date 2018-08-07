package vision.genesis.clientapp.managers;

import android.util.Pair;

import java.util.HashMap;

import io.swagger.client.api.RateApi;
import io.swagger.client.model.RateViewModel;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class RateManager
{
	private RateApi rateApi;

	private HashMap<Pair<String, String>, BehaviorSubject<Double>> ratesMap = new HashMap<>();

	private Subscription rateSubscription;

	public RateManager(RateApi rateApi) {
		this.rateApi = rateApi;
	}

	public BehaviorSubject<Double> getRate(String from, String to) {
		BehaviorSubject<Double> rateSubject = getRateSubject(from, to);

		rateSubscription = rateApi.v10RateByFromByToGet(from, to)
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleGetRateResponse(response, rateSubject),
						this::handleGetRateError);

		return rateSubject;
	}

	private void handleGetRateResponse(RateViewModel response, BehaviorSubject<Double> rateSubject) {
		rateSubscription.unsubscribe();
		rateSubject.onNext(response.getRate());
	}

	private void handleGetRateError(Throwable error) {
		rateSubscription.unsubscribe();
//		balanceBehaviorSubject.onError(error);
	}

	private BehaviorSubject<Double> getRateSubject(String from, String to) {
		Pair<String, String> pairKey = new Pair<>(from, to);
		BehaviorSubject<Double> rateSubject = ratesMap.get(pairKey);
		if (rateSubject == null) {
			rateSubject = BehaviorSubject.create();
			ratesMap.put(pairKey, rateSubject);
		}
		return rateSubject;
	}
}
