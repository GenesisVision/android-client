# SignalSubscription

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**subscriberInfo** | [**SignalSubscriberInfo**](SignalSubscriberInfo.md) |  |  [optional]
**asset** | [**AssetDetails**](AssetDetails.md) |  |  [optional]
**status** | **String** |  |  [optional]
**subscriptionDate** | [**DateTime**](DateTime.md) |  |  [optional]
**unsubscriptionDate** | [**DateTime**](DateTime.md) |  |  [optional]
**hasSignalAccount** | **Boolean** |  |  [optional]
**hasActiveSubscription** | **Boolean** |  |  [optional]
**isExternal** | **Boolean** |  |  [optional]
**mode** | [**SubscriptionMode**](SubscriptionMode.md) |  |  [optional]
**detachMode** | [**DetachModeEnum**](#DetachModeEnum) |  |  [optional]
**percent** | **Double** |  |  [optional]
**openTolerancePercent** | **Double** |  |  [optional]
**fixedVolume** | **Double** |  |  [optional]
**fixedCurrency** | [**FixedCurrencyEnum**](#FixedCurrencyEnum) |  |  [optional]
**totalProfit** | **Double** |  |  [optional]
**totalVolume** | **Double** |  |  [optional]

<a name="DetachModeEnum"></a>
## Enum: DetachModeEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
PROVIDERCLOSEONLY | &quot;ProviderCloseOnly&quot;
CLOSEALLIMMEDIATELY | &quot;CloseAllImmediately&quot;

<a name="FixedCurrencyEnum"></a>
## Enum: FixedCurrencyEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
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
EUR | &quot;EUR&quot;
