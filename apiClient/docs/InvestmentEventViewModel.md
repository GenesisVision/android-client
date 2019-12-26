# InvestmentEventViewModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**title** | **String** |  |  [optional]
**icon** | **String** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**assetDetails** | [**AssetDetails**](AssetDetails.md) |  |  [optional]
**amount** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**changeState** | [**ChangeState**](ChangeState.md) |  |  [optional]
**extendedInfo** | [**List&lt;InvestmentEventItemViewModel&gt;**](InvestmentEventItemViewModel.md) |  |  [optional]
**feesInfo** | [**List&lt;FeeDetails&gt;**](FeeDetails.md) |  |  [optional]
**totalFeesAmount** | **Double** |  |  [optional]
**totalFeesCurrency** | [**TotalFeesCurrencyEnum**](#TotalFeesCurrencyEnum) |  |  [optional]

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

<a name="TotalFeesCurrencyEnum"></a>
## Enum: TotalFeesCurrencyEnum
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
