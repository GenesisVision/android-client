
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
**changeState** | [**ChangeStateEnum**](#ChangeStateEnum) |  |  [optional]
**extendedInfo** | [**List&lt;InvestmentEventItemViewModel&gt;**](InvestmentEventItemViewModel.md) |  |  [optional]
**feesInfo** | [**List&lt;FeeDetails&gt;**](FeeDetails.md) |  |  [optional]
**totalFeesAmount** | **Double** |  |  [optional]
**totalFeesCurrency** | [**TotalFeesCurrencyEnum**](#TotalFeesCurrencyEnum) |  |  [optional]


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


<a name="ChangeStateEnum"></a>
## Enum: ChangeStateEnum
Name | Value
---- | -----
INCREASED | &quot;Increased&quot;
DECREASED | &quot;Decreased&quot;
NOTCHANGED | &quot;NotChanged&quot;


<a name="TotalFeesCurrencyEnum"></a>
## Enum: TotalFeesCurrencyEnum
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



