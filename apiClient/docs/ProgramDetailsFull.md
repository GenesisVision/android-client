
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
**entryFeeSelected** | **Double** |  |  [optional]
**entryFeeCurrent** | **Double** |  |  [optional]
**successFee** | **Double** |  |  [optional]
**stopOutLevel** | **Double** |  |  [optional]
**isReinvesting** | **Boolean** |  |  [optional]
**isSignalProgram** | **Boolean** |  |  [optional]
**signalSuccessFee** | **Double** |  |  [optional]
**signalSubscriptionFee** | **Double** |  |  [optional]
**availableInvestment** | **Double** | In GVT |  [optional]
**availableInvestmentBase** | **Double** | In account currency |  [optional]
**statistic** | [**ProgramStatistic**](ProgramStatistic.md) |  |  [optional]
**rating** | [**ProgramDetailsRating**](ProgramDetailsRating.md) |  |  [optional]
**personalProgramDetails** | [**PersonalProgramDetailsFull**](PersonalProgramDetailsFull.md) | Fields for authorized user |  [optional]
**tags** | [**List&lt;ProgramTag&gt;**](ProgramTag.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**ipfsHash** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]


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



