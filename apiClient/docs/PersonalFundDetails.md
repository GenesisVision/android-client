# PersonalFundDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**isFavorite** | **Boolean** |  |  [optional]
**isInvested** | **Boolean** |  |  [optional]
**canInvest** | **Boolean** |  |  [optional]
**canWithdraw** | **Boolean** |  |  [optional]
**ownerActions** | [**FundOwnerActions**](FundOwnerActions.md) |  |  [optional]
**hasNotifications** | **Boolean** |  |  [optional]
**value** | **Double** |  |  [optional]
**status** | [**AssetInvestmentStatus**](AssetInvestmentStatus.md) |  |  [optional]
**pendingInput** | **Double** |  |  [optional]
**pendingOutput** | **Double** |  |  [optional]
**pendingInOutCurrency** | [**PendingInOutCurrencyEnum**](#PendingInOutCurrencyEnum) |  |  [optional]
**withdrawPercent** | **Double** |  |  [optional]
**availableReallocationPercents** | **Integer** |  |  [optional]
**nextReallocationPercents** | [**DateTime**](DateTime.md) |  |  [optional]
**exitFeePersonal** | **Double** |  |  [optional]

<a name="PendingInOutCurrencyEnum"></a>
## Enum: PendingInOutCurrencyEnum
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
