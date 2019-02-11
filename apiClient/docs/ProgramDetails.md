
# ProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**periodStarts** | [**DateTime**](DateTime.md) |  |  [optional]
**periodEnds** | [**DateTime**](DateTime.md) |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**statistic** | [**ProgramDetailsListStatistic**](ProgramDetailsListStatistic.md) |  |  [optional]
**rating** | [**ProgramDetailsRating**](ProgramDetailsRating.md) |  |  [optional]
**personalDetails** | [**PersonalProgramDetailsFull**](PersonalProgramDetailsFull.md) | Fields for authorized user |  [optional]
**tags** | [**List&lt;ProgramTag&gt;**](ProgramTag.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]
**dashboardAssetsDetails** | [**DashboardProgramDetails**](DashboardProgramDetails.md) | Fields for dashboard |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
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



