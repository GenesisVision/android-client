
# InvestmentProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**login** | **String** |  |  [optional]
**logo** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**ownBalance** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**investedTokens** | **Double** |  |  [optional]
**investedAmount** | **Double** |  |  [optional]
**profitFromProgram** | **Double** |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**investorsCount** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**programStartDate** | [**DateTime**](DateTime.md) |  |  [optional]
**startOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**endOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**profitAvg** | **Double** |  |  [optional]
**profitTotal** | **Double** |  |  [optional]
**profitAvgPercent** | **Double** |  |  [optional]
**profitTotalPercent** | **Double** |  |  [optional]
**profitTotalChange** | [**ProfitTotalChangeEnum**](#ProfitTotalChangeEnum) |  |  [optional]
**volumeTotal** | **Double** |  |  [optional]
**volumeAvg** | **Double** |  |  [optional]
**volumeTotalChange** | [**VolumeTotalChangeEnum**](#VolumeTotalChangeEnum) |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**feeSuccess** | **Double** |  |  [optional]
**feeManagement** | **Double** |  |  [optional]
**ipfsHash** | **String** |  |  [optional]
**tradeIpfsHash** | **String** |  |  [optional]
**isEnabled** | **Boolean** |  |  [optional]
**minAccountBalanceUsd** | **Double** |  |  [optional]
**minAccountBalance** | **Double** |  |  [optional]
**chart** | [**List&lt;Chart&gt;**](Chart.md) |  |  [optional]
**token** | [**Token**](Token.md) |  |  [optional]
**manager** | [**ProfilePublicViewModel**](ProfilePublicViewModel.md) |  |  [optional]
**profitDiagram** | [**PeriodProfitDiagram**](PeriodProfitDiagram.md) |  |  [optional]
**hasNewRequests** | **Boolean** |  |  [optional]
**isHistoryEnable** | **Boolean** |  |  [optional]
**isInvestEnable** | **Boolean** |  |  [optional]
**isWithdrawEnable** | **Boolean** |  |  [optional]
**isOwnProgram** | **Boolean** |  |  [optional]


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


<a name="VolumeTotalChangeEnum"></a>
## Enum: VolumeTotalChangeEnum
Name | Value
---- | -----
UNCHANGED | &quot;Unchanged&quot;
UP | &quot;Up&quot;
DOWN | &quot;Down&quot;



