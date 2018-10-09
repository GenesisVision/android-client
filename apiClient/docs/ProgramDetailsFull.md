
# ProgramDetailsFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**periodStarts** | [**DateTime**](DateTime.md) |  |  [optional]
**periodEnds** | [**DateTime**](DateTime.md) |  |  [optional]
**entryFee** | **Double** |  |  [optional]
**successFee** | **Double** |  |  [optional]
**isReinvesting** | **Boolean** |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**statistic** | [**ProgramStatistic**](ProgramStatistic.md) |  |  [optional]
**personalProgramDetails** | [**PersonalProgramDetailsFull**](PersonalProgramDetailsFull.md) | Fields for authorized user |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]


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
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
PENDING | &quot;Pending&quot;
ERRORCREATING | &quot;ErrorCreating&quot;
ACTIVE | &quot;Active&quot;
CLOSED | &quot;Closed&quot;
ARCHIVED | &quot;Archived&quot;
CLOSEDDUETOINACTIVITY | &quot;ClosedDueToInactivity&quot;



