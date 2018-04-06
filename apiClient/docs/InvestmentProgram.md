
# InvestmentProgram

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**logo** | **String** |  |  [optional]
**isEnabled** | **Boolean** |  |  [optional]
**balance** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**investorsCount** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**startOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**endOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**profitAvg** | **Double** |  |  [optional]
**profitTotal** | **Double** |  |  [optional]
**profitAvgPercent** | **Double** |  |  [optional]
**profitTotalPercent** | **Double** |  |  [optional]
**profitTotalChange** | [**ProfitTotalChangeEnum**](#ProfitTotalChangeEnum) |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**feeSuccess** | **Double** |  |  [optional]
**feeManagement** | **Double** |  |  [optional]
**chart** | [**List&lt;Chart&gt;**](Chart.md) |  |  [optional]
**manager** | [**ProfilePublicViewModel**](ProfilePublicViewModel.md) |  |  [optional]
**freeTokens** | [**FreeTokens**](FreeTokens.md) |  |  [optional]
**hasNewRequests** | **Boolean** |  |  [optional]
**isInvestEnable** | **Boolean** |  |  [optional]
**isOwnProgram** | **Boolean** |  |  [optional]
**canCloseProgram** | **Boolean** |  |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="ProfitTotalChangeEnum"></a>
## Enum: ProfitTotalChangeEnum
Name | Value
---- | -----
UNCHANGED | &quot;Unchanged&quot;
UP | &quot;Up&quot;
DOWN | &quot;Down&quot;



