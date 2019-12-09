# PrivateTradingAccountFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**leverage** | **Integer** |  |  [optional]
**apiKey** | **String** |  |  [optional]
**login** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**type** | [**PrivateTradingAccountType**](PrivateTradingAccountType.md) |  |  [optional]
**status** | [**DashboardTradingAssetStatus**](DashboardTradingAssetStatus.md) |  |  [optional]
**signalSubscriptions** | [**List&lt;SignalSubscription&gt;**](SignalSubscription.md) |  |  [optional]
**brokerDetails** | [**BrokerDetails**](BrokerDetails.md) |  |  [optional]
**ownerActions** | [**PrivateTradingAccountOwnerActions**](PrivateTradingAccountOwnerActions.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;
USDT | &quot;USDT&quot;
GVT | &quot;GVT&quot;
UNDEFINED | &quot;Undefined&quot;
ADA | &quot;ADA&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
EUR | &quot;EUR&quot;
