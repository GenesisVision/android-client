
# DashboardPortfolioEvent

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**assetId** | [**UUID**](UUID.md) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**title** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**value** | **Double** |  |  [optional]
**valueTotal** | **Double** |  |  [optional]
**feeSuccessManager** | **Double** |  |  [optional]
**feeSuccessManagerCurrency** | [**FeeSuccessManagerCurrencyEnum**](#FeeSuccessManagerCurrencyEnum) |  |  [optional]
**feeSuccessPlatform** | **Double** |  |  [optional]
**feeSuccessPlatformCurrency** | [**FeeSuccessPlatformCurrencyEnum**](#FeeSuccessPlatformCurrencyEnum) |  |  [optional]
**profitPercent** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**logo** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**assetType** | [**AssetTypeEnum**](#AssetTypeEnum) |  |  [optional]


<a name="FeeSuccessManagerCurrencyEnum"></a>
## Enum: FeeSuccessManagerCurrencyEnum
Name | Value
---- | -----
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
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="FeeSuccessPlatformCurrencyEnum"></a>
## Enum: FeeSuccessPlatformCurrencyEnum
Name | Value
---- | -----
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
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
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
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
ALL | &quot;All&quot;
INVEST | &quot;Invest&quot;
WITHDRAW | &quot;Withdraw&quot;
PROFIT | &quot;Profit&quot;
LOSS | &quot;Loss&quot;
REINVEST | &quot;Reinvest&quot;
CANCELED | &quot;Canceled&quot;
ENDED | &quot;Ended&quot;
WITHDRAWBYSTOPOUT | &quot;WithdrawByStopOut&quot;


<a name="AssetTypeEnum"></a>
## Enum: AssetTypeEnum
Name | Value
---- | -----
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;



