
# ProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**periodStarts** | [**DateTime**](DateTime.md) |  |  [optional]
**periodEnds** | [**DateTime**](DateTime.md) |  |  [optional]
**isReinvesting** | **Boolean** |  |  [optional]
**availableInvestment** | **Double** |  |  [optional]
**statistic** | [**ProgramDetailsListStatistic**](ProgramDetailsListStatistic.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]
**personalProgramDetails** | [**PersonalProgramDetailsList**](PersonalProgramDetailsList.md) | Fields for authorized user |  [optional]
**dashboardProgramDetails** | [**DashboardProgramDetails**](DashboardProgramDetails.md) | Fields for dashboard |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



