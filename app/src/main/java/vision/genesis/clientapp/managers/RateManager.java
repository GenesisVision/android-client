package vision.genesis.clientapp.managers;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.client.api.RateApi;
import io.swagger.client.model.Currency;
import io.swagger.client.model.RateItem;
import io.swagger.client.model.RateModel;
import io.swagger.client.model.RatesModel;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class RateManager
{
	private static List<String> baseCurrenciesList = new ArrayList<>();

	static {
		for (Currency currency : Currency.values()) {
			if (currency != Currency.UNDEFINED && currency != Currency.SYSTEMCOINMARKET) {
				baseCurrenciesList.add(currency.getValue());
			}
		}
	}

	private RateApi rateApi;

	private HashMap<Pair<String, String>, BehaviorSubject<Double>> ratesMap = new HashMap<>();

	private BehaviorSubject<HashMap<CurrencyEnum, Double>> baseRatesSubject = BehaviorSubject.create();

	private Subscription rateSubscription;

	private Subscription baseRatesSubscription;

	public RateManager(RateApi rateApi) {
		this.rateApi = rateApi;
	}

	public BehaviorSubject<Double> getRate(String from, String to) {
		BehaviorSubject<Double> rateSubject = getRateSubject(from, to);

		rateSubscription = rateApi.getRate(from, to)
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleGetRateResponse(response, rateSubject),
						this::handleGetRateError);

		return rateSubject;
	}

	public BehaviorSubject<HashMap<CurrencyEnum, Double>> getBaseRates() {
		List<String> from = new ArrayList<>();
		from.add(CurrencyEnum.GVT.getValue());

		baseRatesSubscription = rateApi.getRates(from, baseCurrenciesList)
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetBaseRatesResponse,
						this::handleGetBaseRatesError);

		return baseRatesSubject;
	}

	private void handleGetRateResponse(RateModel response, BehaviorSubject<Double> rateSubject) {
		rateSubscription.unsubscribe();
		rateSubject.onNext(response.getRate());
	}

	private void handleGetRateError(Throwable error) {
		rateSubscription.unsubscribe();
	}

	private void handleGetBaseRatesResponse(RatesModel response) {
		baseRatesSubscription.unsubscribe();

		HashMap<CurrencyEnum, Double> responseMap = new HashMap<>();
		Map<String, List<RateItem>> rates = response.getRates();
		//TODO:
		for (RateItem rateItem : rates.get("GVT")) {
			getRateSubject(CurrencyEnum.GVT.getValue(), rateItem.getCurrency()).onNext(rateItem.getRate());
			responseMap.put(CurrencyEnum.fromValue(rateItem.getCurrency()), rateItem.getRate());
		}
		baseRatesSubject.onNext(responseMap);
	}

	private void handleGetBaseRatesError(Throwable error) {
		baseRatesSubscription.unsubscribe();
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
