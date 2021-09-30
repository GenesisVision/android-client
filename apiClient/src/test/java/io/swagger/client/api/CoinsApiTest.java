package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.CoinsFilterSorting;
import io.swagger.client.model.Currency;
import io.swagger.client.model.InternalTransferRequest;

/**
 * API tests for CoinsApi
 */
public class CoinsApiTest
{

    private CoinsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(CoinsApi.class);
    }


    /**
     * Coins list
     */
    @Test
    public void getCoinsTest() {
        CoinsFilterSorting sorting = null;
        Currency showIn = null;
        List<String> assets = null;
        DateTime dateFrom = null;
        DateTime dateTo = null;
        Integer chartPointsCount = null;
        String facetId = null;
        String mask = null;
        UUID ownerId = null;
        Boolean showFavorites = null;
        Boolean skipStatistic = null;
        Integer skip = null;
        Integer take = null;
        // CoinsAssetItemsViewModel response = api.getCoins(sorting, showIn, assets, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);

        // TODO: test validations
    }

    /**
     * Get coins history
     */
    @Test
    public void getCoinsConvertingHistoryTest() {
        DateTime dateFrom = null;
        DateTime dateTo = null;
        List<String> assets = null;
        Integer skip = null;
        Integer take = null;
        // CoinsHistoryEventItemsViewModel response = api.getCoinsConvertingHistory(dateFrom, dateTo, assets, skip, take);

        // TODO: test validations
    }

    /**
     * Get user coins
     */
    @Test
    public void getUserCoinsTest() {
        CoinsFilterSorting sorting = null;
        Currency showIn = null;
        List<String> assets = null;
        DateTime dateFrom = null;
        DateTime dateTo = null;
        Integer chartPointsCount = null;
        String facetId = null;
        String mask = null;
        UUID ownerId = null;
        Boolean showFavorites = null;
        Boolean skipStatistic = null;
        Integer skip = null;
        Integer take = null;
        // CoinsAssetItemsViewModel response = api.getUserCoins(sorting, showIn, assets, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);

        // TODO: test validations
    }

    /**
     * Transfer money
     */
    @Test
    public void transferTest() {
        InternalTransferRequest body = null;
        // Void response = api.transfer(body);

        // TODO: test validations
    }
}
