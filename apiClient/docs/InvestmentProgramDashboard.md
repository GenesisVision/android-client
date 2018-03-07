
# InvestmentProgramDashboard

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**logo** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**investedTokens** | **Double** |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**investorsCount** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**endOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**profitAvg** | **Double** |  |  [optional]
**profitTotal** | **Double** |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**feeSuccess** | **Double** |  |  [optional]
**feeManagement** | **Double** |  |  [optional]
**chart** | [**List&lt;Chart&gt;**](Chart.md) |  |  [optional]
**manager** | [**ProfilePublicViewModel**](ProfilePublicViewModel.md) |  |  [optional]
**hasNewRequests** | **Boolean** |  |  [optional]
**isHistoryEnable** | **Boolean** |  |  [optional]
**isInvestEnable** | **Boolean** |  |  [optional]
**isWithdrawEnable** | **Boolean** |  |  [optional]
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



