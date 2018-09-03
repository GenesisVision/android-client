
# ProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**periodDateStart** | [**DateTime**](DateTime.md) |  |  [optional]
**periodDateEnd** | [**DateTime**](DateTime.md) |  |  [optional]
**availableForInvestment** | **Double** |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**statistic** | [**ProgramDetailsListStatistic**](ProgramDetailsListStatistic.md) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]
**personalProgramDetails** | [**PersonalProgramDetailsList**](PersonalProgramDetailsList.md) | Fields for authorized user |  [optional]


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



