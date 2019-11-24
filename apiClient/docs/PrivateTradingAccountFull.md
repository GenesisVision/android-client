# PrivateTradingAccountFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**leverage** | **Integer** |  |  [optional]
**apiKey** | **String** |  |  [optional]
**login** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**type** | [**PrivateTradingAccountType**](PrivateTradingAccountType.md) |  |  [optional]
**status** | [**DashboardTradingAssetStatus**](DashboardTradingAssetStatus.md) |  |  [optional]
**brokerDetails** | [**BrokerDetails**](BrokerDetails.md) |  |  [optional]
**ownerActions** | [**PrivateTradingAccountOwnerActions**](PrivateTradingAccountOwnerActions.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USDT | &quot;USDT&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;
